package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.EditorInfo;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EditorInfo.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LocaleListHelper {
    public static final LocaleListHelper INSTANCE = new LocaleListHelper();

    private LocaleListHelper() {
    }

    public final void setHintLocales(EditorInfo editorInfo, LocaleList localeList) {
        if (Intrinsics.areEqual(localeList, LocaleList.Companion.getEmpty())) {
            editorInfo.hintLocales = null;
            return;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(localeList, 10));
        Iterator<E> it = localeList.iterator();
        while (it.hasNext()) {
            arrayList.add(((Locale) it.next()).getPlatformLocale());
        }
        java.util.Locale[] localeArr = (java.util.Locale[]) arrayList.toArray(new java.util.Locale[0]);
        editorInfo.hintLocales = LocaleListHelper$$ExternalSyntheticApiModelOutline0.m((java.util.Locale[]) Arrays.copyOf(localeArr, localeArr.length));
    }
}
