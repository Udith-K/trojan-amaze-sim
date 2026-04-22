package androidx.compose.ui.text.intl;

import android.util.Log;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidLocaleDelegate.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidLocaleDelegateAPI23 implements PlatformLocaleDelegate {
    @Override // androidx.compose.ui.text.intl.PlatformLocaleDelegate
    public LocaleList getCurrent() {
        return new LocaleList(CollectionsKt.listOf(new Locale(java.util.Locale.getDefault())));
    }

    @Override // androidx.compose.ui.text.intl.PlatformLocaleDelegate
    public java.util.Locale parseLanguageTag(String str) {
        java.util.Locale localeForLanguageTag = java.util.Locale.forLanguageTag(str);
        if (Intrinsics.areEqual(localeForLanguageTag.toLanguageTag(), "und")) {
            Log.e(AndroidLocaleDelegate_androidKt.TAG, "The language tag " + str + " is not well-formed. Locale is resolved to Undetermined. Note that underscore '_' is not a valid subtags delimiter and must be replaced with '-'.");
        }
        return localeForLanguageTag;
    }
}
