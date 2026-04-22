package androidx.compose.animation;

import ch.qos.logback.core.CoreConstants;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EnterExitTransition.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TransitionData {
    private final ChangeSize changeSize;
    private final Map effectsMap;
    private final Fade fade;
    private final boolean hold;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransitionData)) {
            return false;
        }
        TransitionData transitionData = (TransitionData) obj;
        return Intrinsics.areEqual(this.fade, transitionData.fade) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual(this.changeSize, transitionData.changeSize) && Intrinsics.areEqual((Object) null, (Object) null) && this.hold == transitionData.hold && Intrinsics.areEqual(this.effectsMap, transitionData.effectsMap);
    }

    public final Scale getScale() {
        return null;
    }

    public final Slide getSlide() {
        return null;
    }

    public int hashCode() {
        Fade fade = this.fade;
        int iHashCode = (fade == null ? 0 : fade.hashCode()) * 961;
        ChangeSize changeSize = this.changeSize;
        return ((((iHashCode + (changeSize != null ? changeSize.hashCode() : 0)) * 961) + ChangeSize$$ExternalSyntheticBackport0.m(this.hold)) * 31) + this.effectsMap.hashCode();
    }

    public String toString() {
        return "TransitionData(fade=" + this.fade + ", slide=" + ((Object) null) + ", changeSize=" + this.changeSize + ", scale=" + ((Object) null) + ", hold=" + this.hold + ", effectsMap=" + this.effectsMap + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public TransitionData(Fade fade, Slide slide, ChangeSize changeSize, Scale scale, boolean z, Map map) {
        this.fade = fade;
        this.changeSize = changeSize;
        this.hold = z;
        this.effectsMap = map;
    }

    public final Fade getFade() {
        return this.fade;
    }

    public final ChangeSize getChangeSize() {
        return this.changeSize;
    }

    public final boolean getHold() {
        return this.hold;
    }

    public /* synthetic */ TransitionData(Fade fade, Slide slide, ChangeSize changeSize, Scale scale, boolean z, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : fade, (i & 2) != 0 ? null : slide, (i & 4) != 0 ? null : changeSize, (i & 8) == 0 ? scale : null, (i & 16) != 0 ? false : z, (i & 32) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map getEffectsMap() {
        return this.effectsMap;
    }
}
