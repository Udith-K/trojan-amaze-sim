package org.fdroid;

import android.os.Build;
import kotlin.Metadata;
import org.bouncycastle.asn1.eac.EACTags;

/* JADX INFO: compiled from: CompatibilityChecker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007¨\u0006\u0007"}, d2 = {"Lorg/fdroid/CompatibilityCheckerUtils;", "", "<init>", "()V", "minInstallableTargetSdk", "", "sdkInt", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CompatibilityCheckerUtils {
    public static final CompatibilityCheckerUtils INSTANCE = new CompatibilityCheckerUtils();

    public final int minInstallableTargetSdk() {
        return minInstallableTargetSdk$default(this, 0, 1, null);
    }

    public final int minInstallableTargetSdk(int sdkInt) {
        switch (sdkInt) {
            case 34:
                return 23;
            case 35:
            case EACTags.APPLICATION_EXPIRATION_DATE /* 36 */:
                return 24;
            default:
                return 1;
        }
    }

    private CompatibilityCheckerUtils() {
    }

    public static /* synthetic */ int minInstallableTargetSdk$default(CompatibilityCheckerUtils compatibilityCheckerUtils, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Build.VERSION.SDK_INT;
        }
        return compatibilityCheckerUtils.minInstallableTargetSdk(i);
    }
}
