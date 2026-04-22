package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FloatingActionButton.kt */
/* JADX INFO: loaded from: classes.dex */
final class FloatingActionButtonElevationAnimatable {
    private final Animatable animatable;
    private float defaultElevation;
    private float focusedElevation;
    private float hoveredElevation;
    private Interaction lastTargetInteraction;
    private float pressedElevation;
    private Interaction targetInteraction;

    /* JADX INFO: renamed from: androidx.compose.material3.FloatingActionButtonElevationAnimatable$animateElevation$1, reason: invalid class name */
    /* JADX INFO: compiled from: FloatingActionButton.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FloatingActionButtonElevationAnimatable.this.animateElevation(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.FloatingActionButtonElevationAnimatable$snapElevation$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FloatingActionButton.kt */
    static final class C01291 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C01291(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FloatingActionButtonElevationAnimatable.this.snapElevation(this);
        }
    }

    public /* synthetic */ FloatingActionButtonElevationAnimatable(float f, float f2, float f3, float f4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4);
    }

    private FloatingActionButtonElevationAnimatable(float f, float f2, float f3, float f4) {
        this.defaultElevation = f;
        this.pressedElevation = f2;
        this.hoveredElevation = f3;
        this.focusedElevation = f4;
        this.animatable = new Animatable(Dp.m2414boximpl(this.defaultElevation), VectorConvertersKt.getVectorConverter(Dp.Companion), null, null, 12, null);
    }

    /* JADX INFO: renamed from: calculateTarget-u2uoSUM, reason: not valid java name */
    private final float m681calculateTargetu2uoSUM(Interaction interaction) {
        return interaction instanceof PressInteraction.Press ? this.pressedElevation : interaction instanceof HoverInteraction$Enter ? this.hoveredElevation : interaction instanceof FocusInteraction$Focus ? this.focusedElevation : this.defaultElevation;
    }

    /* JADX INFO: renamed from: updateElevation-lDy3nrA, reason: not valid java name */
    public final Object m682updateElevationlDy3nrA(float f, float f2, float f3, float f4, Continuation continuation) throws Throwable {
        this.defaultElevation = f;
        this.pressedElevation = f2;
        this.hoveredElevation = f3;
        this.focusedElevation = f4;
        Object objSnapElevation = snapElevation(continuation);
        return objSnapElevation == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapElevation : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object snapElevation(kotlin.coroutines.Continuation r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof androidx.compose.material3.FloatingActionButtonElevationAnimatable.C01291
            if (r0 == 0) goto L13
            r0 = r5
            androidx.compose.material3.FloatingActionButtonElevationAnimatable$snapElevation$1 r0 = (androidx.compose.material3.FloatingActionButtonElevationAnimatable.C01291) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.FloatingActionButtonElevationAnimatable$snapElevation$1 r0 = new androidx.compose.material3.FloatingActionButtonElevationAnimatable$snapElevation$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r0 = r0.L$0
            androidx.compose.material3.FloatingActionButtonElevationAnimatable r0 = (androidx.compose.material3.FloatingActionButtonElevationAnimatable) r0
            kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Throwable -> L2d
            goto L64
        L2d:
            r5 = move-exception
            goto L6b
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L37:
            kotlin.ResultKt.throwOnFailure(r5)
            androidx.compose.foundation.interaction.Interaction r5 = r4.targetInteraction
            float r5 = r4.m681calculateTargetu2uoSUM(r5)
            androidx.compose.animation.core.Animatable r2 = r4.animatable
            java.lang.Object r2 = r2.getTargetValue()
            androidx.compose.ui.unit.Dp r2 = (androidx.compose.ui.unit.Dp) r2
            float r2 = r2.m2422unboximpl()
            boolean r2 = androidx.compose.ui.unit.Dp.m2418equalsimpl0(r2, r5)
            if (r2 != 0) goto L70
            androidx.compose.animation.core.Animatable r2 = r4.animatable     // Catch: java.lang.Throwable -> L69
            androidx.compose.ui.unit.Dp r5 = androidx.compose.ui.unit.Dp.m2414boximpl(r5)     // Catch: java.lang.Throwable -> L69
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L69
            r0.label = r3     // Catch: java.lang.Throwable -> L69
            java.lang.Object r5 = r2.snapTo(r5, r0)     // Catch: java.lang.Throwable -> L69
            if (r5 != r1) goto L63
            return r1
        L63:
            r0 = r4
        L64:
            androidx.compose.foundation.interaction.Interaction r5 = r0.targetInteraction
            r0.lastTargetInteraction = r5
            goto L70
        L69:
            r5 = move-exception
            r0 = r4
        L6b:
            androidx.compose.foundation.interaction.Interaction r1 = r0.targetInteraction
            r0.lastTargetInteraction = r1
            throw r5
        L70:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.FloatingActionButtonElevationAnimatable.snapElevation(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object animateElevation(androidx.compose.foundation.interaction.Interaction r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof androidx.compose.material3.FloatingActionButtonElevationAnimatable.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            androidx.compose.material3.FloatingActionButtonElevationAnimatable$animateElevation$1 r0 = (androidx.compose.material3.FloatingActionButtonElevationAnimatable.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.FloatingActionButtonElevationAnimatable$animateElevation$1 r0 = new androidx.compose.material3.FloatingActionButtonElevationAnimatable$animateElevation$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r6 = r0.L$1
            androidx.compose.foundation.interaction.Interaction r6 = (androidx.compose.foundation.interaction.Interaction) r6
            java.lang.Object r0 = r0.L$0
            androidx.compose.material3.FloatingActionButtonElevationAnimatable r0 = (androidx.compose.material3.FloatingActionButtonElevationAnimatable) r0
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L31
            goto L6b
        L31:
            r7 = move-exception
            goto L70
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            kotlin.ResultKt.throwOnFailure(r7)
            float r7 = r5.m681calculateTargetu2uoSUM(r6)
            r5.targetInteraction = r6
            androidx.compose.animation.core.Animatable r2 = r5.animatable     // Catch: java.lang.Throwable -> L67
            java.lang.Object r2 = r2.getTargetValue()     // Catch: java.lang.Throwable -> L67
            androidx.compose.ui.unit.Dp r2 = (androidx.compose.ui.unit.Dp) r2     // Catch: java.lang.Throwable -> L67
            float r2 = r2.m2422unboximpl()     // Catch: java.lang.Throwable -> L67
            boolean r2 = androidx.compose.ui.unit.Dp.m2418equalsimpl0(r2, r7)     // Catch: java.lang.Throwable -> L67
            if (r2 != 0) goto L6a
            androidx.compose.animation.core.Animatable r2 = r5.animatable     // Catch: java.lang.Throwable -> L67
            androidx.compose.foundation.interaction.Interaction r4 = r5.lastTargetInteraction     // Catch: java.lang.Throwable -> L67
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L67
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L67
            r0.label = r3     // Catch: java.lang.Throwable -> L67
            java.lang.Object r7 = androidx.compose.material3.internal.ElevationKt.m781animateElevationrAjV9yQ(r2, r7, r4, r6, r0)     // Catch: java.lang.Throwable -> L67
            if (r7 != r1) goto L6a
            return r1
        L67:
            r7 = move-exception
            r0 = r5
            goto L70
        L6a:
            r0 = r5
        L6b:
            r0.lastTargetInteraction = r6
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L70:
            r0.lastTargetInteraction = r6
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.FloatingActionButtonElevationAnimatable.animateElevation(androidx.compose.foundation.interaction.Interaction, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final State asState() {
        return this.animatable.asState();
    }
}
