package androidx.compose.animation;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.Alignment;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EnterExitTransition.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ChangeSize {
    private final Alignment alignment;
    private final FiniteAnimationSpec animationSpec;
    private final boolean clip;
    private final Function1 size;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChangeSize)) {
            return false;
        }
        ChangeSize changeSize = (ChangeSize) obj;
        return Intrinsics.areEqual(this.alignment, changeSize.alignment) && Intrinsics.areEqual(this.size, changeSize.size) && Intrinsics.areEqual(this.animationSpec, changeSize.animationSpec) && this.clip == changeSize.clip;
    }

    public int hashCode() {
        return (((((this.alignment.hashCode() * 31) + this.size.hashCode()) * 31) + this.animationSpec.hashCode()) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.clip);
    }

    public String toString() {
        return "ChangeSize(alignment=" + this.alignment + ", size=" + this.size + ", animationSpec=" + this.animationSpec + ", clip=" + this.clip + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public ChangeSize(Alignment alignment, Function1 function1, FiniteAnimationSpec finiteAnimationSpec, boolean z) {
        this.alignment = alignment;
        this.size = function1;
        this.animationSpec = finiteAnimationSpec;
        this.clip = z;
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    public final Function1 getSize() {
        return this.size;
    }

    public final FiniteAnimationSpec getAnimationSpec() {
        return this.animationSpec;
    }

    public final boolean getClip() {
        return this.clip;
    }
}
