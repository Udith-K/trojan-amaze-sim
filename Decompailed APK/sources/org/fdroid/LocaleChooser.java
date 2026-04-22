package org.fdroid;

import androidx.core.os.LocaleListCompat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.fdroid.index.IndexConverterKt;

/* JADX INFO: compiled from: LocaleChooser.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u0004\u0018\u0001H\u0005\"\u0004\b\u0000\u0010\u0005*\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ-\u0010\u000b\u001a\u0004\u0018\u0001H\u0005\"\u0004\b\u0000\u0010\u0005*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00050\u00062\u0006\u0010\f\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/fdroid/LocaleChooser;", "", "<init>", "()V", "getBestLocale", "T", "", "", "localeList", "Landroidx/core/os/LocaleListCompat;", "(Ljava/util/Map;Landroidx/core/os/LocaleListCompat;)Ljava/lang/Object;", "getOrStartsWith", "s", "(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/Object;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LocaleChooser {
    public static final LocaleChooser INSTANCE = new LocaleChooser();

    private LocaleChooser() {
    }

    public final <T> T getBestLocale(Map<String, ? extends T> map, LocaleListCompat localeList) {
        Locale firstMatch;
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        if (map == null || map.isEmpty() || (firstMatch = localeList.getFirstMatch((String[]) map.keySet().toArray(new String[0]))) == null) {
            return null;
        }
        T t = map.get(firstMatch.toLanguageTag());
        if (t != null) {
            return t;
        }
        String str = firstMatch.getLanguage() + "-" + firstMatch.getCountry();
        LocaleChooser localeChooser = INSTANCE;
        T t2 = (T) localeChooser.getOrStartsWith(map, str);
        if (t2 != null) {
            return t2;
        }
        String language = firstMatch.getLanguage();
        Intrinsics.checkNotNull(language);
        T t3 = (T) localeChooser.getOrStartsWith(map, language);
        if (t3 == null && (t3 = map.get(IndexConverterKt.DEFAULT_LOCALE)) == null && (t3 = map.get("en")) == null) {
            t3 = (T) CollectionsKt.first(map.values());
        }
        return t3;
    }

    private final <T> T getOrStartsWith(Map<String, ? extends T> map, String str) {
        T t = map.get(str);
        if (t != null) {
            return t;
        }
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str2 = (String) entry.getKey();
            T t2 = (T) entry.getValue();
            if (StringsKt.startsWith$default(str2, str, false, 2, (Object) null)) {
                return t2;
            }
        }
        return null;
    }
}
