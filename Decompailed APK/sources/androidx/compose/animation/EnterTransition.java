package androidx.compose.animation;

import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EnterExitTransition.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class EnterTransition {
    public static final Companion Companion = new Companion(null);
    private static final EnterTransition None = new EnterTransitionImpl(new TransitionData(null, null, null, null, false, null, 63, null));

    public /* synthetic */ EnterTransition(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract TransitionData getData$animation_release();

    private EnterTransition() {
    }

    public final EnterTransition plus(EnterTransition enterTransition) {
        Fade fade = enterTransition.getData$animation_release().getFade();
        if (fade == null) {
            fade = getData$animation_release().getFade();
        }
        Fade fade2 = fade;
        enterTransition.getData$animation_release().getSlide();
        getData$animation_release().getSlide();
        ChangeSize changeSize = enterTransition.getData$animation_release().getChangeSize();
        if (changeSize == null) {
            changeSize = getData$animation_release().getChangeSize();
        }
        ChangeSize changeSize2 = changeSize;
        enterTransition.getData$animation_release().getScale();
        getData$animation_release().getScale();
        return new EnterTransitionImpl(new TransitionData(fade2, null, changeSize2, null, false, MapsKt.plus(getData$animation_release().getEffectsMap(), enterTransition.getData$animation_release().getEffectsMap()), 16, null));
    }

    public String toString() {
        if (Intrinsics.areEqual(this, None)) {
            return "EnterTransition.None";
        }
        TransitionData data$animation_release = getData$animation_release();
        StringBuilder sb = new StringBuilder();
        sb.append("EnterTransition: \nFade - ");
        Fade fade = data$animation_release.getFade();
        sb.append(fade != null ? fade.toString() : null);
        sb.append(",\nSlide - ");
        data$animation_release.getSlide();
        sb.append((String) null);
        sb.append(",\nShrink - ");
        ChangeSize changeSize = data$animation_release.getChangeSize();
        sb.append(changeSize != null ? changeSize.toString() : null);
        sb.append(",\nScale - ");
        data$animation_release.getScale();
        sb.append((String) null);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        return (obj instanceof EnterTransition) && Intrinsics.areEqual(((EnterTransition) obj).getData$animation_release(), getData$animation_release());
    }

    public int hashCode() {
        return getData$animation_release().hashCode();
    }

    /* JADX INFO: compiled from: EnterExitTransition.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EnterTransition getNone() {
            return EnterTransition.None;
        }
    }
}
