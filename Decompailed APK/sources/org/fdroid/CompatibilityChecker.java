package org.fdroid;

import kotlin.Metadata;
import org.fdroid.index.v2.PackageManifest;

/* JADX INFO: compiled from: CompatibilityChecker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lorg/fdroid/CompatibilityChecker;", "", "isCompatible", "", "manifest", "Lorg/fdroid/index/v2/PackageManifest;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface CompatibilityChecker {
    boolean isCompatible(PackageManifest manifest);
}
