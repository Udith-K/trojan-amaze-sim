package org.fdroid;

import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.index.v2.PackageManifest;

/* JADX INFO: compiled from: CompatibilityChecker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B5\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR*\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00070\u000fj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0007`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/fdroid/CompatibilityCheckerImpl;", "Lorg/fdroid/CompatibilityChecker;", "packageManager", "Landroid/content/pm/PackageManager;", "forceTouchApps", "", "sdkInt", "", "supportedAbis", "", "", "<init>", "(Landroid/content/pm/PackageManager;ZI[Ljava/lang/String;)V", "[Ljava/lang/String;", "features", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "isCompatible", "manifest", "Lorg/fdroid/index/v2/PackageManifest;", "isNativeCodeCompatible", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CompatibilityCheckerImpl implements CompatibilityChecker {
    private final HashMap<String, Integer> features;
    private final boolean forceTouchApps;
    private final int sdkInt;
    private final String[] supportedAbis;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CompatibilityCheckerImpl(PackageManager packageManager) {
        this(packageManager, false, 0, null, 14, null);
        Intrinsics.checkNotNullParameter(packageManager, "packageManager");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CompatibilityCheckerImpl(PackageManager packageManager, boolean z) {
        this(packageManager, z, 0, null, 12, null);
        Intrinsics.checkNotNullParameter(packageManager, "packageManager");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CompatibilityCheckerImpl(PackageManager packageManager, boolean z, int i) {
        this(packageManager, z, i, null, 8, null);
        Intrinsics.checkNotNullParameter(packageManager, "packageManager");
    }

    public CompatibilityCheckerImpl(PackageManager packageManager, boolean z, int i, String[] supportedAbis) {
        Intrinsics.checkNotNullParameter(packageManager, "packageManager");
        Intrinsics.checkNotNullParameter(supportedAbis, "supportedAbis");
        this.forceTouchApps = z;
        this.sdkInt = i;
        this.supportedAbis = supportedAbis;
        HashMap<String, Integer> map = new HashMap<>();
        FeatureInfo[] systemAvailableFeatures = packageManager.getSystemAvailableFeatures();
        if (systemAvailableFeatures != null) {
            for (FeatureInfo featureInfo : systemAvailableFeatures) {
                map.put(featureInfo.name, Integer.valueOf(Build.VERSION.SDK_INT >= 24 ? featureInfo.version : 0));
            }
        }
        this.features = map;
    }

    public /* synthetic */ CompatibilityCheckerImpl(PackageManager packageManager, boolean z, int i, String[] strArr, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(packageManager, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? Build.VERSION.SDK_INT : i, (i2 & 8) != 0 ? Build.SUPPORTED_ABIS : strArr);
    }

    @Override // org.fdroid.CompatibilityChecker
    public boolean isCompatible(PackageManifest manifest) {
        Intrinsics.checkNotNullParameter(manifest, "manifest");
        int i = this.sdkInt;
        Integer minSdkVersion = manifest.getMinSdkVersion();
        if (i < (minSdkVersion != null ? minSdkVersion.intValue() : 0)) {
            return false;
        }
        int i2 = this.sdkInt;
        Integer maxSdkVersion = manifest.getMaxSdkVersion();
        if (i2 > (maxSdkVersion != null ? maxSdkVersion.intValue() : Integer.MAX_VALUE)) {
            return false;
        }
        Integer targetSdkVersion = manifest.getTargetSdkVersion();
        if ((targetSdkVersion != null ? targetSdkVersion.intValue() : 1) < CompatibilityCheckerUtils.INSTANCE.minInstallableTargetSdk(this.sdkInt) || !isNativeCodeCompatible(manifest)) {
            return false;
        }
        List<String> featureNames = manifest.getFeatureNames();
        if (featureNames != null && (r6 = featureNames.iterator()) != null) {
            for (String str : featureNames) {
                if (!this.forceTouchApps || !Intrinsics.areEqual(str, "android.hardware.touchscreen")) {
                    if (!this.features.containsKey(str)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private final boolean isNativeCodeCompatible(PackageManifest manifest) {
        List<String> nativecode = manifest.getNativecode();
        if (nativecode == null || nativecode.isEmpty()) {
            return true;
        }
        for (String str : this.supportedAbis) {
            if (nativecode.contains(str)) {
                return true;
            }
        }
        return false;
    }
}
