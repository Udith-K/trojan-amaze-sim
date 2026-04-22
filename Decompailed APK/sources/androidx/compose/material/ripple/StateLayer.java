package androidx.compose.material.ripple;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.foundation.interaction.DragInteraction$Cancel;
import androidx.compose.foundation.interaction.DragInteraction$Start;
import androidx.compose.foundation.interaction.DragInteraction$Stop;
import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.FocusInteraction$Unfocus;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.HoverInteraction$Exit;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Ripple.kt */
/* JADX INFO: loaded from: classes.dex */
final class StateLayer {
    private final boolean bounded;
    private Interaction currentInteraction;
    private final Function0 rippleAlpha;
    private final Animatable animatedAlpha = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
    private final List interactions = new ArrayList();

    public StateLayer(boolean z, Function0 function0) {
        this.bounded = z;
        this.rippleAlpha = function0;
    }

    public final void handleInteraction$material_ripple_release(Interaction interaction, CoroutineScope coroutineScope) {
        float draggedAlpha;
        boolean z = interaction instanceof HoverInteraction$Enter;
        if (z) {
            this.interactions.add(interaction);
        } else if (interaction instanceof HoverInteraction$Exit) {
            this.interactions.remove(((HoverInteraction$Exit) interaction).getEnter());
        } else if (interaction instanceof FocusInteraction$Focus) {
            this.interactions.add(interaction);
        } else if (interaction instanceof FocusInteraction$Unfocus) {
            this.interactions.remove(((FocusInteraction$Unfocus) interaction).getFocus());
        } else if (interaction instanceof DragInteraction$Start) {
            this.interactions.add(interaction);
        } else if (interaction instanceof DragInteraction$Stop) {
            this.interactions.remove(((DragInteraction$Stop) interaction).getStart());
        } else if (!(interaction instanceof DragInteraction$Cancel)) {
            return;
        } else {
            this.interactions.remove(((DragInteraction$Cancel) interaction).getStart());
        }
        Interaction interaction2 = (Interaction) CollectionsKt.lastOrNull(this.interactions);
        if (Intrinsics.areEqual(this.currentInteraction, interaction2)) {
            return;
        }
        if (interaction2 == null) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new StateLayer$handleInteraction$2(this, RippleKt.outgoingStateLayerAnimationSpecFor(this.currentInteraction), null), 3, null);
        } else {
            RippleAlpha rippleAlpha = (RippleAlpha) this.rippleAlpha.invoke();
            if (z) {
                draggedAlpha = rippleAlpha.getHoveredAlpha();
            } else if (interaction instanceof FocusInteraction$Focus) {
                draggedAlpha = rippleAlpha.getFocusedAlpha();
            } else {
                draggedAlpha = interaction instanceof DragInteraction$Start ? rippleAlpha.getDraggedAlpha() : 0.0f;
            }
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new StateLayer$handleInteraction$1(this, draggedAlpha, RippleKt.incomingStateLayerAnimationSpecFor(interaction2), null), 3, null);
        }
        this.currentInteraction = interaction2;
    }

    /* JADX INFO: renamed from: drawStateLayer-mxwnekA, reason: not valid java name */
    public final void m594drawStateLayermxwnekA(DrawScope drawScope, float f, long j) throws Throwable {
        long j2;
        float fFloatValue = ((Number) this.animatedAlpha.getValue()).floatValue();
        if (fFloatValue > 0.0f) {
            long jM1294copywmQWz5c$default = Color.m1294copywmQWz5c$default(j, fFloatValue, 0.0f, 0.0f, 0.0f, 14, null);
            if (this.bounded) {
                float fM1196getWidthimpl = Size.m1196getWidthimpl(drawScope.mo1483getSizeNHjbRc());
                float fM1194getHeightimpl = Size.m1194getHeightimpl(drawScope.mo1483getSizeNHjbRc());
                int iM1289getIntersectrtfAjoo = ClipOp.Companion.m1289getIntersectrtfAjoo();
                DrawContext drawContext = drawScope.getDrawContext();
                long jMo1487getSizeNHjbRc = drawContext.mo1487getSizeNHjbRc();
                drawContext.getCanvas().save();
                try {
                    drawContext.getTransform().mo1490clipRectN_I0leg(0.0f, 0.0f, fM1196getWidthimpl, fM1194getHeightimpl, iM1289getIntersectrtfAjoo);
                    j2 = jMo1487getSizeNHjbRc;
                } catch (Throwable th) {
                    th = th;
                    j2 = jMo1487getSizeNHjbRc;
                }
                try {
                    DrawScope.CC.m1499drawCircleVaOC9Bg$default(drawScope, jM1294copywmQWz5c$default, f, 0L, 0.0f, null, null, 0, 124, null);
                    drawContext.getCanvas().restore();
                    drawContext.mo1488setSizeuvyYCjk(j2);
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    drawContext.getCanvas().restore();
                    drawContext.mo1488setSizeuvyYCjk(j2);
                    throw th;
                }
            }
            DrawScope.CC.m1499drawCircleVaOC9Bg$default(drawScope, jM1294copywmQWz5c$default, f, 0L, 0.0f, null, null, 0, 124, null);
        }
    }
}
