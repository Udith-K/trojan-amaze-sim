package androidx.compose.foundation;

import android.os.Build;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Magnifier.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Magnifier_androidKt {
    private static final SemanticsPropertyKey MagnifierPositionInRoot = new SemanticsPropertyKey("MagnifierPositionInRoot", null, 2, null);

    public static final boolean isPlatformMagnifierSupported(int i) {
        return i >= 28;
    }

    public static final SemanticsPropertyKey getMagnifierPositionInRoot() {
        return MagnifierPositionInRoot;
    }

    /* JADX INFO: renamed from: magnifier-jPUL71Q, reason: not valid java name */
    public static final Modifier m139magnifierjPUL71Q(Modifier modifier, Function1 function1, Function1 function12, Function1 function13, float f, boolean z, long j, float f2, float f3, boolean z2, PlatformMagnifierFactory platformMagnifierFactory) {
        if (isPlatformMagnifierSupported$default(0, 1, null)) {
            return modifier.then(new MagnifierElement(function1, function12, function13, f, z, j, f2, f3, z2, platformMagnifierFactory == null ? PlatformMagnifierFactory.Companion.getForCurrentPlatform() : platformMagnifierFactory, null));
        }
        return modifier;
    }

    public static /* synthetic */ boolean isPlatformMagnifierSupported$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Build.VERSION.SDK_INT;
        }
        return isPlatformMagnifierSupported(i);
    }

    public static final boolean equalsIncludingNaN(float f, float f2) {
        return (Float.isNaN(f) && Float.isNaN(f2)) || f == f2;
    }
}
