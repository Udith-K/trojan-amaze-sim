package androidx.compose.ui.text.intl;

import android.os.Build;

/* JADX INFO: compiled from: AndroidPlatformLocale.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidPlatformLocale_androidKt {
    public static final PlatformLocaleDelegate createPlatformLocaleDelegate() {
        if (Build.VERSION.SDK_INT >= 24) {
            return new AndroidLocaleDelegateAPI24();
        }
        return new AndroidLocaleDelegateAPI23();
    }
}
