package androidx.compose.ui.text.platform.extensions;

import androidx.compose.foundation.text.input.internal.LocaleListHelper$$ExternalSyntheticApiModelOutline0;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: LocaleExtensions.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LocaleListHelperMethods {
    public static final LocaleListHelperMethods INSTANCE = new LocaleListHelperMethods();

    private LocaleListHelperMethods() {
    }

    public final Object localeSpan(LocaleList localeList) {
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(localeList, 10));
        Iterator<E> it = localeList.iterator();
        while (it.hasNext()) {
            arrayList.add(((Locale) it.next()).getPlatformLocale());
        }
        java.util.Locale[] localeArr = (java.util.Locale[]) arrayList.toArray(new java.util.Locale[0]);
        return LocaleListHelperMethods$$ExternalSyntheticApiModelOutline1.m(LocaleListHelper$$ExternalSyntheticApiModelOutline0.m((java.util.Locale[]) Arrays.copyOf(localeArr, localeArr.length)));
    }

    public final void setTextLocales(AndroidTextPaint androidTextPaint, LocaleList localeList) {
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(localeList, 10));
        Iterator<E> it = localeList.iterator();
        while (it.hasNext()) {
            arrayList.add(((Locale) it.next()).getPlatformLocale());
        }
        java.util.Locale[] localeArr = (java.util.Locale[]) arrayList.toArray(new java.util.Locale[0]);
        androidTextPaint.setTextLocales(LocaleListHelper$$ExternalSyntheticApiModelOutline0.m((java.util.Locale[]) Arrays.copyOf(localeArr, localeArr.length)));
    }
}
