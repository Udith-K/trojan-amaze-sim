package io.ktor.http;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.core.CoreConstants;
import io.ktor.util.StringValuesKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: URLUtils.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class URLUtilsKt {
    public static final Url Url(String urlString) {
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        return URLBuilder(urlString).build();
    }

    public static final URLBuilder URLBuilder(String urlString) {
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        return URLParserKt.takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), urlString);
    }

    public static final URLBuilder URLBuilder(Url url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), url);
    }

    public static final URLBuilder takeFrom(URLBuilder uRLBuilder, URLBuilder url) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        uRLBuilder.setProtocol(url.getProtocol());
        uRLBuilder.setHost(url.getHost());
        uRLBuilder.setPort(url.getPort());
        uRLBuilder.setEncodedPathSegments(url.getEncodedPathSegments());
        uRLBuilder.setEncodedUser(url.getEncodedUser());
        uRLBuilder.setEncodedPassword(url.getEncodedPassword());
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        StringValuesKt.appendAll(parametersBuilderParametersBuilder$default, url.getEncodedParameters());
        uRLBuilder.setEncodedParameters(parametersBuilderParametersBuilder$default);
        uRLBuilder.setEncodedFragment(url.getEncodedFragment());
        uRLBuilder.setTrailingQuery(url.getTrailingQuery());
        return uRLBuilder;
    }

    public static final URLBuilder takeFrom(URLBuilder uRLBuilder, Url url) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        uRLBuilder.setProtocol(url.getProtocol());
        uRLBuilder.setHost(url.getHost());
        uRLBuilder.setPort(url.getPort());
        URLBuilderKt.setEncodedPath(uRLBuilder, url.getEncodedPath());
        uRLBuilder.setEncodedUser(url.getEncodedUser());
        uRLBuilder.setEncodedPassword(url.getEncodedPassword());
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        parametersBuilderParametersBuilder$default.appendAll(QueryKt.parseQueryString$default(url.getEncodedQuery(), 0, 0, false, 6, null));
        uRLBuilder.setEncodedParameters(parametersBuilderParametersBuilder$default);
        uRLBuilder.setEncodedFragment(url.getEncodedFragment());
        uRLBuilder.setTrailingQuery(url.getTrailingQuery());
        return uRLBuilder;
    }

    public static final String getHostWithPort(Url url) {
        Intrinsics.checkNotNullParameter(url, "<this>");
        return url.getHost() + CoreConstants.COLON_CHAR + url.getPort();
    }

    public static final void appendUrlFullPath(Appendable appendable, String encodedPath, ParametersBuilder encodedQueryParameters, boolean z) {
        List listListOf;
        Intrinsics.checkNotNullParameter(appendable, "<this>");
        Intrinsics.checkNotNullParameter(encodedPath, "encodedPath");
        Intrinsics.checkNotNullParameter(encodedQueryParameters, "encodedQueryParameters");
        if (!StringsKt.isBlank(encodedPath) && !StringsKt.startsWith$default(encodedPath, "/", false, 2, (Object) null)) {
            appendable.append('/');
        }
        appendable.append(encodedPath);
        if (!encodedQueryParameters.isEmpty() || z) {
            appendable.append(CallerData.NA);
        }
        Set<Map.Entry> setEntries = encodedQueryParameters.entries();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : setEntries) {
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            if (list.isEmpty()) {
                listListOf = CollectionsKt.listOf(TuplesKt.to(str, null));
            } else {
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList2.add(TuplesKt.to(str, (String) it.next()));
                }
                listListOf = arrayList2;
            }
            CollectionsKt.addAll(arrayList, listListOf);
        }
        CollectionsKt___CollectionsKt.joinTo(arrayList, appendable, (124 & 2) != 0 ? ", " : "&", (124 & 4) != 0 ? "" : null, (124 & 8) == 0 ? null : "", (124 & 16) != 0 ? -1 : 0, (124 & 32) != 0 ? "..." : null, (124 & 64) != 0 ? null : new Function1() { // from class: io.ktor.http.URLUtilsKt.appendUrlFullPath.2
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(Pair it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                String str2 = (String) it2.getFirst();
                if (it2.getSecond() == null) {
                    return str2;
                }
                return str2 + '=' + String.valueOf(it2.getSecond());
            }
        });
    }

    public static final void appendUserAndPassword(StringBuilder sb, String str, String str2) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        if (str == null) {
            return;
        }
        sb.append(str);
        if (str2 != null) {
            sb.append(CoreConstants.COLON_CHAR);
            sb.append(str2);
        }
        sb.append("@");
    }
}
