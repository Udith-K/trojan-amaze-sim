package io.ktor.http;

import ch.qos.logback.core.joran.action.Action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: URLBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class URLBuilderKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Appendable appendTo(URLBuilder uRLBuilder, Appendable appendable) throws IOException {
        appendable.append(uRLBuilder.getProtocol().getName());
        String name = uRLBuilder.getProtocol().getName();
        if (Intrinsics.areEqual(name, Action.FILE_ATTRIBUTE)) {
            appendFile(appendable, uRLBuilder.getHost(), getEncodedPath(uRLBuilder));
            return appendable;
        }
        if (Intrinsics.areEqual(name, "mailto")) {
            appendMailto(appendable, getEncodedUserAndPassword(uRLBuilder), uRLBuilder.getHost());
            return appendable;
        }
        appendable.append("://");
        appendable.append(getAuthority(uRLBuilder));
        URLUtilsKt.appendUrlFullPath(appendable, getEncodedPath(uRLBuilder), uRLBuilder.getEncodedParameters(), uRLBuilder.getTrailingQuery());
        if (uRLBuilder.getEncodedFragment().length() > 0) {
            appendable.append('#');
            appendable.append(uRLBuilder.getEncodedFragment());
        }
        return appendable;
    }

    private static final void appendMailto(Appendable appendable, String str, String str2) throws IOException {
        appendable.append(":");
        appendable.append(str);
        appendable.append(str2);
    }

    private static final void appendFile(Appendable appendable, String str, String str2) throws IOException {
        appendable.append("://");
        appendable.append(str);
        if (!StringsKt.startsWith$default((CharSequence) str2, '/', false, 2, (Object) null)) {
            appendable.append('/');
        }
        appendable.append(str2);
    }

    public static final String getEncodedUserAndPassword(URLBuilder uRLBuilder) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        StringBuilder sb = new StringBuilder();
        URLUtilsKt.appendUserAndPassword(sb, uRLBuilder.getEncodedUser(), uRLBuilder.getEncodedPassword());
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static /* synthetic */ URLBuilder appendPathSegments$default(URLBuilder uRLBuilder, String[] strArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return appendPathSegments(uRLBuilder, strArr, z);
    }

    public static final URLBuilder appendPathSegments(URLBuilder uRLBuilder, String[] components, boolean z) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(components, "components");
        return appendPathSegments(uRLBuilder, ArraysKt.toList(components), z);
    }

    public static final URLBuilder appendEncodedPathSegments(URLBuilder uRLBuilder, List segments) {
        List listPlus;
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(segments, "segments");
        boolean z = false;
        boolean z2 = uRLBuilder.getEncodedPathSegments().size() > 1 && ((CharSequence) CollectionsKt.last(uRLBuilder.getEncodedPathSegments())).length() == 0 && !segments.isEmpty();
        if (segments.size() > 1 && ((CharSequence) CollectionsKt.first(segments)).length() == 0 && !uRLBuilder.getEncodedPathSegments().isEmpty()) {
            z = true;
        }
        if (z2 && z) {
            listPlus = CollectionsKt.plus((Collection) CollectionsKt.dropLast(uRLBuilder.getEncodedPathSegments(), 1), (Iterable) CollectionsKt.drop(segments, 1));
        } else if (z2) {
            listPlus = CollectionsKt.plus((Collection) CollectionsKt.dropLast(uRLBuilder.getEncodedPathSegments(), 1), (Iterable) segments);
        } else if (z) {
            listPlus = CollectionsKt.plus((Collection) uRLBuilder.getEncodedPathSegments(), (Iterable) CollectionsKt.drop(segments, 1));
        } else {
            listPlus = CollectionsKt.plus((Collection) uRLBuilder.getEncodedPathSegments(), (Iterable) segments);
        }
        uRLBuilder.setEncodedPathSegments(listPlus);
        return uRLBuilder;
    }

    public static final String getAuthority(URLBuilder uRLBuilder) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        StringBuilder sb = new StringBuilder();
        sb.append(getEncodedUserAndPassword(uRLBuilder));
        sb.append(uRLBuilder.getHost());
        if (uRLBuilder.getPort() != 0 && uRLBuilder.getPort() != uRLBuilder.getProtocol().getDefaultPort()) {
            sb.append(":");
            sb.append(String.valueOf(uRLBuilder.getPort()));
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static final String getEncodedPath(URLBuilder uRLBuilder) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        return joinPath(uRLBuilder.getEncodedPathSegments());
    }

    public static final void setEncodedPath(URLBuilder uRLBuilder, String value) {
        List root_path;
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        if (StringsKt.isBlank(value)) {
            root_path = CollectionsKt.emptyList();
        } else {
            root_path = Intrinsics.areEqual(value, "/") ? URLParserKt.getROOT_PATH() : CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) value, new char[]{'/'}, false, 0, 6, (Object) null));
        }
        uRLBuilder.setEncodedPathSegments(root_path);
    }

    private static final String joinPath(List list) {
        if (list.isEmpty()) {
            return "";
        }
        if (list.size() == 1) {
            return ((CharSequence) CollectionsKt.first(list)).length() == 0 ? "/" : (String) CollectionsKt.first(list);
        }
        return CollectionsKt.joinToString$default(list, "/", null, null, 0, null, null, 62, null);
    }

    public static final URLBuilder appendPathSegments(URLBuilder uRLBuilder, List segments, boolean z) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(segments, "segments");
        if (!z) {
            ArrayList arrayList = new ArrayList();
            Iterator it = segments.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, StringsKt.split$default((CharSequence) it.next(), new char[]{'/'}, false, 0, 6, (Object) null));
            }
            segments = arrayList;
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(segments, 10));
        Iterator it2 = segments.iterator();
        while (it2.hasNext()) {
            arrayList2.add(CodecsKt.encodeURLPathPart((String) it2.next()));
        }
        appendEncodedPathSegments(uRLBuilder, arrayList2);
        return uRLBuilder;
    }
}
