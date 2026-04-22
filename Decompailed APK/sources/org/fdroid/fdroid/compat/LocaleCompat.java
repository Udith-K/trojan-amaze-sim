package org.fdroid.fdroid.compat;

import android.os.Build;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class LocaleCompat {
    public static Locale getDefault() {
        if (Build.VERSION.SDK_INT >= 24) {
            return Locale.getDefault(Locale.Category.DISPLAY);
        }
        return Locale.getDefault();
    }
}
