package androidx.compose.foundation.gestures;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.MotionDurationScale;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.FontScaling;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ScrollableKt {
    private static final Function1 CanDragCalculation = new Function1() { // from class: androidx.compose.foundation.gestures.ScrollableKt$CanDragCalculation$1
        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(PointerInputChange pointerInputChange) {
            return Boolean.valueOf(!PointerType.m1701equalsimpl0(pointerInputChange.m1685getTypeT8wyACA(), PointerType.Companion.m1705getMouseT8wyACA()));
        }
    };
    private static final ScrollScope NoOpScrollScope = new ScrollScope() { // from class: androidx.compose.foundation.gestures.ScrollableKt$NoOpScrollScope$1
        @Override // androidx.compose.foundation.gestures.ScrollScope
        public float scrollBy(float f) {
            return f;
        }
    };
    private static final MotionDurationScale DefaultScrollMotionDurationScale = new MotionDurationScale() { // from class: androidx.compose.foundation.gestures.ScrollableKt$DefaultScrollMotionDurationScale$1
        @Override // kotlin.coroutines.CoroutineContext.Element
        public /* synthetic */ CoroutineContext.Key getKey() {
            return MotionDurationScale.Key;
        }

        @Override // androidx.compose.ui.MotionDurationScale
        public float getScaleFactor() {
            return 1.0f;
        }

        @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
        public Object fold(Object obj, Function2 function2) {
            return MotionDurationScale.DefaultImpls.fold(this, obj, function2);
        }

        @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
        public CoroutineContext.Element get(CoroutineContext.Key key) {
            return MotionDurationScale.DefaultImpls.get(this, key);
        }

        @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
        public CoroutineContext minusKey(CoroutineContext.Key key) {
            return MotionDurationScale.DefaultImpls.minusKey(this, key);
        }

        @Override // kotlin.coroutines.CoroutineContext
        public CoroutineContext plus(CoroutineContext coroutineContext) {
            return MotionDurationScale.DefaultImpls.plus(this, coroutineContext);
        }
    };
    private static final ScrollableKt$UnityDensity$1 UnityDensity = new Density() { // from class: androidx.compose.foundation.gestures.ScrollableKt$UnityDensity$1
        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return 1.0f;
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public float getFontScale() {
            return 1.0f;
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx-0680j_4 */
        public /* synthetic */ int mo204roundToPx0680j_4(float f) {
            return Density.CC.m2406$default$roundToPx0680j_4(this, f);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toDp-GaN1DYA */
        public /* synthetic */ float mo205toDpGaN1DYA(long j) {
            return FontScaling.CC.m2449$default$toDpGaN1DYA(this, j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public /* synthetic */ float mo206toDpu2uoSUM(float f) {
            return Dp.m2416constructorimpl(f / getDensity());
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public /* synthetic */ float mo207toDpu2uoSUM(int i) {
            return Dp.m2416constructorimpl(i / getDensity());
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDpSize-k-rfVVM */
        public /* synthetic */ long mo208toDpSizekrfVVM(long j) {
            return Density.CC.m2409$default$toDpSizekrfVVM(this, j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx--R2X_6o */
        public /* synthetic */ float mo209toPxR2X_6o(long j) {
            return Density.CC.m2410$default$toPxR2X_6o(this, j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx-0680j_4 */
        public /* synthetic */ float mo210toPx0680j_4(float f) {
            return Density.CC.m2411$default$toPx0680j_4(this, f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSize-XkaWNTQ */
        public /* synthetic */ long mo211toSizeXkaWNTQ(long j) {
            return Density.CC.m2412$default$toSizeXkaWNTQ(this, j);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toSp-0xMU5do */
        public /* synthetic */ long mo212toSp0xMU5do(float f) {
            return FontScaling.CC.m2450$default$toSp0xMU5do(this, f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public /* synthetic */ long mo213toSpkPz2Gy4(float f) {
            return mo212toSp0xMU5do(mo206toDpu2uoSUM(f));
        }
    };

    public static /* synthetic */ Modifier scrollable$default(Modifier modifier, ScrollableState scrollableState, Orientation orientation, boolean z, boolean z2, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        boolean z3 = z;
        if ((i & 8) != 0) {
            z2 = false;
        }
        return scrollable(modifier, scrollableState, orientation, z3, z2, (i & 16) != 0 ? null : flingBehavior, (i & 32) != 0 ? null : mutableInteractionSource);
    }

    public static final Modifier scrollable(Modifier modifier, ScrollableState scrollableState, Orientation orientation, boolean z, boolean z2, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource) {
        return scrollable$default(modifier, scrollableState, orientation, null, z, z2, flingBehavior, mutableInteractionSource, null, 128, null);
    }

    public static /* synthetic */ Modifier scrollable$default(Modifier modifier, ScrollableState scrollableState, Orientation orientation, OverscrollEffect overscrollEffect, boolean z, boolean z2, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource, BringIntoViewSpec bringIntoViewSpec, int i, Object obj) {
        return scrollable(modifier, scrollableState, orientation, overscrollEffect, (i & 8) != 0 ? true : z, (i & 16) != 0 ? false : z2, (i & 32) != 0 ? null : flingBehavior, (i & 64) != 0 ? null : mutableInteractionSource, (i & 128) != 0 ? null : bringIntoViewSpec);
    }

    public static final Modifier scrollable(Modifier modifier, ScrollableState scrollableState, Orientation orientation, OverscrollEffect overscrollEffect, boolean z, boolean z2, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource, BringIntoViewSpec bringIntoViewSpec) {
        return modifier.then(new ScrollableElement(scrollableState, orientation, overscrollEffect, z, z2, flingBehavior, mutableInteractionSource, bringIntoViewSpec));
    }

    public static final MotionDurationScale getDefaultScrollMotionDurationScale() {
        return DefaultScrollMotionDurationScale;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: semanticsScrollBy-d-4ec7I, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object m215semanticsScrollByd4ec7I(androidx.compose.foundation.gestures.ScrollingLogic r11, long r12, kotlin.coroutines.Continuation r14) {
        /*
            boolean r0 = r14 instanceof androidx.compose.foundation.gestures.ScrollableKt$semanticsScrollBy$1
            if (r0 == 0) goto L13
            r0 = r14
            androidx.compose.foundation.gestures.ScrollableKt$semanticsScrollBy$1 r0 = (androidx.compose.foundation.gestures.ScrollableKt$semanticsScrollBy$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.ScrollableKt$semanticsScrollBy$1 r0 = new androidx.compose.foundation.gestures.ScrollableKt$semanticsScrollBy$1
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r11 = r0.L$1
            kotlin.jvm.internal.Ref$FloatRef r11 = (kotlin.jvm.internal.Ref$FloatRef) r11
            java.lang.Object r12 = r0.L$0
            androidx.compose.foundation.gestures.ScrollingLogic r12 = (androidx.compose.foundation.gestures.ScrollingLogic) r12
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = r11
            r11 = r12
            goto L5c
        L33:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L3b:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.jvm.internal.Ref$FloatRef r14 = new kotlin.jvm.internal.Ref$FloatRef
            r14.<init>()
            androidx.compose.foundation.MutatePriority r2 = androidx.compose.foundation.MutatePriority.Default
            androidx.compose.foundation.gestures.ScrollableKt$semanticsScrollBy$2 r10 = new androidx.compose.foundation.gestures.ScrollableKt$semanticsScrollBy$2
            r9 = 0
            r4 = r10
            r5 = r11
            r6 = r12
            r8 = r14
            r4.<init>(r5, r6, r8, r9)
            r0.L$0 = r11
            r0.L$1 = r14
            r0.label = r3
            java.lang.Object r12 = r11.scroll(r2, r10, r0)
            if (r12 != r1) goto L5c
            return r1
        L5c:
            float r12 = r14.element
            long r11 = r11.m236toOffsettuRUvjQ(r12)
            androidx.compose.ui.geometry.Offset r11 = androidx.compose.ui.geometry.Offset.m1150boximpl(r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollableKt.m215semanticsScrollByd4ec7I(androidx.compose.foundation.gestures.ScrollingLogic, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
