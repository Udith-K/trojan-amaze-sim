package androidx.compose.material.ripple;

import androidx.collection.MutableScatterMap;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: CommonRipple.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CommonRippleNode extends RippleNode {
    private final MutableScatterMap ripples;

    public /* synthetic */ CommonRippleNode(InteractionSource interactionSource, boolean z, float f, ColorProducer colorProducer, Function0 function0, DefaultConstructorMarker defaultConstructorMarker) {
        this(interactionSource, z, f, colorProducer, function0);
    }

    private CommonRippleNode(InteractionSource interactionSource, boolean z, float f, ColorProducer colorProducer, Function0 function0) {
        super(interactionSource, z, f, colorProducer, function0, null);
        this.ripples = new MutableScatterMap(0, 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004c  */
    @Override // androidx.compose.material.ripple.RippleNode
    /* JADX INFO: renamed from: addRipple-12SF9DM */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo576addRipple12SF9DM(androidx.compose.foundation.interaction.PressInteraction.Press r18, long r19, float r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            androidx.collection.MutableScatterMap r2 = r0.ripples
            java.lang.Object[] r3 = r2.keys
            java.lang.Object[] r4 = r2.values
            long[] r2 = r2.metadata
            int r5 = r2.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L51
            r6 = 0
            r7 = r6
        L13:
            r8 = r2[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L4c
            int r10 = r7 - r5
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r6
        L2d:
            if (r12 >= r10) goto L4a
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L46
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r3[r13]
            r13 = r4[r13]
            androidx.compose.material.ripple.RippleAnimation r13 = (androidx.compose.material.ripple.RippleAnimation) r13
            androidx.compose.foundation.interaction.PressInteraction$Press r14 = (androidx.compose.foundation.interaction.PressInteraction.Press) r14
            r13.finish()
        L46:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L2d
        L4a:
            if (r10 != r11) goto L51
        L4c:
            if (r7 == r5) goto L51
            int r7 = r7 + 1
            goto L13
        L51:
            boolean r2 = r17.getBounded()
            r3 = 0
            if (r2 == 0) goto L61
            long r4 = r18.m244getPressPositionF1C5BW0()
            androidx.compose.ui.geometry.Offset r2 = androidx.compose.ui.geometry.Offset.m1150boximpl(r4)
            goto L62
        L61:
            r2 = r3
        L62:
            androidx.compose.material.ripple.RippleAnimation r4 = new androidx.compose.material.ripple.RippleAnimation
            boolean r5 = r17.getBounded()
            r6 = r21
            r4.<init>(r2, r6, r5, r3)
            androidx.collection.MutableScatterMap r2 = r0.ripples
            r2.set(r1, r4)
            kotlinx.coroutines.CoroutineScope r5 = r17.getCoroutineScope()
            androidx.compose.material.ripple.CommonRippleNode$addRipple$2 r8 = new androidx.compose.material.ripple.CommonRippleNode$addRipple$2
            r8.<init>(r4, r0, r1, r3)
            r9 = 3
            r10 = 0
            r6 = 0
            r7 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r5, r6, r7, r8, r9, r10)
            androidx.compose.ui.node.DrawModifierNodeKt.invalidateDraw(r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ripple.CommonRippleNode.mo576addRipple12SF9DM(androidx.compose.foundation.interaction.PressInteraction$Press, long, float):void");
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public void removeRipple(PressInteraction.Press press) {
        RippleAnimation rippleAnimation = (RippleAnimation) this.ripples.get(press);
        if (rippleAnimation != null) {
            rippleAnimation.finish();
        }
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public void drawRipples(DrawScope drawScope) {
        float f;
        float f2;
        int i;
        int i2;
        int i3;
        float pressedAlpha = ((RippleAlpha) getRippleAlpha().invoke()).getPressedAlpha();
        if (pressedAlpha == 0.0f) {
            return;
        }
        MutableScatterMap mutableScatterMap = this.ripples;
        Object[] objArr = mutableScatterMap.keys;
        Object[] objArr2 = mutableScatterMap.values;
        long[] jArr = mutableScatterMap.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i4 = 0;
        while (true) {
            long j = jArr[i4];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i5 = 8;
                int i6 = 8 - ((~(i4 - length)) >>> 31);
                long j2 = j;
                int i7 = 0;
                while (i7 < i6) {
                    if ((j2 & 255) < 128) {
                        int i8 = (i4 << 3) + i7;
                        float f3 = pressedAlpha;
                        i = i7;
                        i2 = i6;
                        f2 = pressedAlpha;
                        i3 = i5;
                        ((RippleAnimation) objArr2[i8]).m580draw4WTKRHQ(drawScope, Color.m1294copywmQWz5c$default(m589getRippleColor0d7_KjU(), f3, 0.0f, 0.0f, 0.0f, 14, null));
                    } else {
                        f2 = pressedAlpha;
                        i = i7;
                        i2 = i6;
                        i3 = i5;
                    }
                    j2 >>= i3;
                    i7 = i + 1;
                    i5 = i3;
                    pressedAlpha = f2;
                    i6 = i2;
                }
                f = pressedAlpha;
                if (i6 != i5) {
                    return;
                }
            } else {
                f = pressedAlpha;
            }
            if (i4 == length) {
                return;
            }
            i4++;
            pressedAlpha = f;
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.ripples.clear();
    }
}
