package androidx.compose.ui.input.pointer;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.FontScaling;
import androidx.compose.ui.unit.IntSize;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SuspendingPointerInputModifierNodeImpl extends Modifier.Node implements SuspendingPointerInputModifierNode, PointerInputScope, Density {
    private Function2 _pointerInputHandler;
    private Object key1;
    private Object key2;
    private Object[] keys;
    private PointerEvent lastPointerEvent;
    private Job pointerInputJob;
    private PointerEvent currentEvent = SuspendingPointerInputFilterKt.EmptyPointerEvent;
    private final MutableVector pointerHandlers = new MutableVector(new PointerEventHandlerCoroutine[16], 0);
    private final MutableVector dispatchingPointerHandlers = new MutableVector(new PointerEventHandlerCoroutine[16], 0);
    private long boundsSize = IntSize.Companion.m2480getZeroYbymL2g();

    /* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PointerEventPass.values().length];
            try {
                iArr[PointerEventPass.Initial.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PointerEventPass.Final.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PointerEventPass.Main.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ boolean interceptOutOfBoundsChildEvents() {
        return PointerInputModifierNode.CC.$default$interceptOutOfBoundsChildEvents(this);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public /* synthetic */ int mo204roundToPx0680j_4(float f) {
        return Density.CC.m2406$default$roundToPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ boolean sharePointerInputWithSiblings() {
        return PointerInputModifierNode.CC.$default$sharePointerInputWithSiblings(this);
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
    final class PointerEventHandlerCoroutine implements AwaitPointerEventScope, Density, Continuation {
        private final /* synthetic */ SuspendingPointerInputModifierNodeImpl $$delegate_0;
        private final Continuation completion;
        private CancellableContinuation pointerAwaiter;
        private PointerEventPass awaitPass = PointerEventPass.Main;
        private final CoroutineContext context = EmptyCoroutineContext.INSTANCE;

        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return this.$$delegate_0.getDensity();
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public float getFontScale() {
            return this.$$delegate_0.getFontScale();
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx-0680j_4 */
        public int mo204roundToPx0680j_4(float f) {
            return this.$$delegate_0.mo204roundToPx0680j_4(f);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toDp-GaN1DYA */
        public float mo205toDpGaN1DYA(long j) {
            return this.$$delegate_0.mo205toDpGaN1DYA(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public float mo206toDpu2uoSUM(float f) {
            return this.$$delegate_0.mo206toDpu2uoSUM(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public float mo207toDpu2uoSUM(int i) {
            return this.$$delegate_0.mo207toDpu2uoSUM(i);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDpSize-k-rfVVM */
        public long mo208toDpSizekrfVVM(long j) {
            return this.$$delegate_0.mo208toDpSizekrfVVM(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx--R2X_6o */
        public float mo209toPxR2X_6o(long j) {
            return this.$$delegate_0.mo209toPxR2X_6o(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx-0680j_4 */
        public float mo210toPx0680j_4(float f) {
            return this.$$delegate_0.mo210toPx0680j_4(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSize-XkaWNTQ */
        public long mo211toSizeXkaWNTQ(long j) {
            return this.$$delegate_0.mo211toSizeXkaWNTQ(j);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toSp-0xMU5do */
        public long mo212toSp0xMU5do(float f) {
            return this.$$delegate_0.mo212toSp0xMU5do(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public long mo213toSpkPz2Gy4(float f) {
            return this.$$delegate_0.mo213toSpkPz2Gy4(f);
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        public Object awaitPointerEvent(PointerEventPass pointerEventPass, Continuation continuation) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            this.awaitPass = pointerEventPass;
            this.pointerAwaiter = cancellableContinuationImpl;
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }

        public PointerEventHandlerCoroutine(Continuation continuation) {
            this.completion = continuation;
            this.$$delegate_0 = SuspendingPointerInputModifierNodeImpl.this;
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        public PointerEvent getCurrentEvent() {
            return SuspendingPointerInputModifierNodeImpl.this.currentEvent;
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        /* JADX INFO: renamed from: getSize-YbymL2g */
        public long mo1645getSizeYbymL2g() {
            return SuspendingPointerInputModifierNodeImpl.this.boundsSize;
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        public ViewConfiguration getViewConfiguration() {
            return SuspendingPointerInputModifierNodeImpl.this.getViewConfiguration();
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc */
        public long mo1644getExtendedTouchPaddingNHjbRc() {
            return SuspendingPointerInputModifierNodeImpl.this.m1715getExtendedTouchPaddingNHjbRc();
        }

        public final void offerPointerEvent(PointerEvent pointerEvent, PointerEventPass pointerEventPass) {
            CancellableContinuation cancellableContinuation;
            if (pointerEventPass != this.awaitPass || (cancellableContinuation = this.pointerAwaiter) == null) {
                return;
            }
            this.pointerAwaiter = null;
            cancellableContinuation.resumeWith(Result.m2639constructorimpl(pointerEvent));
        }

        public final void cancel(Throwable th) {
            CancellableContinuation cancellableContinuation = this.pointerAwaiter;
            if (cancellableContinuation != null) {
                cancellableContinuation.cancel(th);
            }
            this.pointerAwaiter = null;
        }

        @Override // kotlin.coroutines.Continuation
        public CoroutineContext getContext() {
            return this.context;
        }

        @Override // kotlin.coroutines.Continuation
        public void resumeWith(Object obj) {
            MutableVector mutableVector = SuspendingPointerInputModifierNodeImpl.this.pointerHandlers;
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = SuspendingPointerInputModifierNodeImpl.this;
            synchronized (mutableVector) {
                suspendingPointerInputModifierNodeImpl.pointerHandlers.remove(this);
                Unit unit = Unit.INSTANCE;
            }
            this.completion.resumeWith(obj);
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object withTimeoutOrNull(long r5, kotlin.jvm.functions.Function2 r7, kotlin.coroutines.Continuation r8) {
            /*
                r4 = this;
                boolean r0 = r8 instanceof androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeoutOrNull$1
                if (r0 == 0) goto L13
                r0 = r8
                androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeoutOrNull$1 r0 = (androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeoutOrNull$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeoutOrNull$1 r0 = new androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeoutOrNull$1
                r0.<init>(r4, r8)
            L18:
                java.lang.Object r8 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L31
                if (r2 != r3) goto L29
                kotlin.ResultKt.throwOnFailure(r8)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L3d
                goto L3e
            L29:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L31:
                kotlin.ResultKt.throwOnFailure(r8)
                r0.label = r3     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L3d
                java.lang.Object r8 = r4.withTimeout(r5, r7, r0)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L3d
                if (r8 != r1) goto L3e
                return r1
            L3d:
                r8 = 0
            L3e:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.PointerEventHandlerCoroutine.withTimeoutOrNull(long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        /* JADX WARN: Type inference failed for: r11v0, types: [long] */
        /* JADX WARN: Type inference failed for: r11v1, types: [kotlinx.coroutines.Job] */
        /* JADX WARN: Type inference failed for: r11v3, types: [kotlinx.coroutines.Job] */
        /* JADX WARN: Type inference failed for: r11v7 */
        /* JADX WARN: Type inference failed for: r11v8 */
        /* JADX WARN: Type inference failed for: r13v0, types: [kotlin.jvm.functions.Function2] */
        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object withTimeout(long r11, kotlin.jvm.functions.Function2 r13, kotlin.coroutines.Continuation r14) {
            /*
                r10 = this;
                boolean r0 = r14 instanceof androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$1
                if (r0 == 0) goto L13
                r0 = r14
                androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$1 r0 = (androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$1 r0 = new androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$1
                r0.<init>(r10, r14)
            L18:
                java.lang.Object r14 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L37
                if (r2 != r3) goto L2f
                java.lang.Object r11 = r0.L$0
                kotlinx.coroutines.Job r11 = (kotlinx.coroutines.Job) r11
                kotlin.ResultKt.throwOnFailure(r14)     // Catch: java.lang.Throwable -> L2d
                goto L75
            L2d:
                r12 = move-exception
                goto L7b
            L2f:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r12)
                throw r11
            L37:
                kotlin.ResultKt.throwOnFailure(r14)
                r4 = 0
                int r14 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
                if (r14 > 0) goto L56
                kotlinx.coroutines.CancellableContinuation r14 = r10.pointerAwaiter
                if (r14 == 0) goto L56
                kotlin.Result$Companion r2 = kotlin.Result.Companion
                androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException r2 = new androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException
                r2.<init>(r11)
                java.lang.Object r2 = kotlin.ResultKt.createFailure(r2)
                java.lang.Object r2 = kotlin.Result.m2639constructorimpl(r2)
                r14.resumeWith(r2)
            L56:
                androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl r14 = androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.this
                kotlinx.coroutines.CoroutineScope r4 = r14.getCoroutineScope()
                androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$job$1 r7 = new androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$job$1
                r14 = 0
                r7.<init>(r11, r10, r14)
                r8 = 3
                r9 = 0
                r5 = 0
                r6 = 0
                kotlinx.coroutines.Job r11 = kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)
                r0.L$0 = r11     // Catch: java.lang.Throwable -> L2d
                r0.label = r3     // Catch: java.lang.Throwable -> L2d
                java.lang.Object r14 = r13.invoke(r10, r0)     // Catch: java.lang.Throwable -> L2d
                if (r14 != r1) goto L75
                return r1
            L75:
                androidx.compose.ui.input.pointer.CancelTimeoutCancellationException r12 = androidx.compose.ui.input.pointer.CancelTimeoutCancellationException.INSTANCE
                r11.cancel(r12)
                return r14
            L7b:
                androidx.compose.ui.input.pointer.CancelTimeoutCancellationException r13 = androidx.compose.ui.input.pointer.CancelTimeoutCancellationException.INSTANCE
                r11.cancel(r13)
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.PointerEventHandlerCoroutine.withTimeout(long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputScope
    public Object awaitPointerEventScope(Function2 function2, Continuation continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final PointerEventHandlerCoroutine pointerEventHandlerCoroutine = new PointerEventHandlerCoroutine(cancellableContinuationImpl);
        synchronized (this.pointerHandlers) {
            this.pointerHandlers.add(pointerEventHandlerCoroutine);
            Continuation continuationCreateCoroutine = ContinuationKt.createCoroutine(function2, pointerEventHandlerCoroutine, pointerEventHandlerCoroutine);
            Result.Companion companion = Result.Companion;
            continuationCreateCoroutine.resumeWith(Result.m2639constructorimpl(Unit.INSTANCE));
        }
        cancellableContinuationImpl.invokeOnCancellation(new Function1() { // from class: androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$awaitPointerEventScope$2$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable th) {
                pointerEventHandlerCoroutine.cancel(th);
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public SuspendingPointerInputModifierNodeImpl(Object obj, Object obj2, Object[] objArr, Function2 function2) {
        this.key1 = obj;
        this.key2 = obj2;
        this.keys = objArr;
        this._pointerInputHandler = function2;
    }

    public final void update$ui_release(Object obj, Object obj2, Object[] objArr, Function2 function2) {
        boolean z = !Intrinsics.areEqual(this.key1, obj);
        this.key1 = obj;
        if (!Intrinsics.areEqual(this.key2, obj2)) {
            z = true;
        }
        this.key2 = obj2;
        Object[] objArr2 = this.keys;
        if (objArr2 != null && objArr == null) {
            z = true;
        }
        if (objArr2 == null && objArr != null) {
            z = true;
        }
        boolean z2 = (objArr2 == null || objArr == null || Arrays.equals(objArr, objArr2)) ? z : true;
        this.keys = objArr;
        if (z2) {
            resetPointerInputHandler();
        }
        this._pointerInputHandler = function2;
    }

    public Function2 getPointerInputHandler() {
        return this._pointerInputHandler;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return DelegatableNodeKt.requireLayoutNode(this).getDensity().getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return DelegatableNodeKt.requireLayoutNode(this).getDensity().getFontScale();
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputScope
    public ViewConfiguration getViewConfiguration() {
        return DelegatableNodeKt.requireLayoutNode(this).getViewConfiguration();
    }

    /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name */
    public long m1716getSizeYbymL2g() {
        return this.boundsSize;
    }

    /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
    public long m1715getExtendedTouchPaddingNHjbRc() {
        long jMo211toSizeXkaWNTQ = mo211toSizeXkaWNTQ(getViewConfiguration().mo1813getMinimumTouchTargetSizeMYxV2XQ());
        long jM1716getSizeYbymL2g = m1716getSizeYbymL2g();
        return SizeKt.Size(Math.max(0.0f, Size.m1196getWidthimpl(jMo211toSizeXkaWNTQ) - IntSize.m2476getWidthimpl(jM1716getSizeYbymL2g)) / 2.0f, Math.max(0.0f, Size.m1194getHeightimpl(jMo211toSizeXkaWNTQ) - IntSize.m2475getHeightimpl(jM1716getSizeYbymL2g)) / 2.0f);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        resetPointerInputHandler();
        super.onDetach();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onDensityChange() {
        resetPointerInputHandler();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onViewConfigurationChange() {
        resetPointerInputHandler();
    }

    @Override // androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode
    public void resetPointerInputHandler() {
        Job job = this.pointerInputJob;
        if (job != null) {
            job.cancel(new PointerInputResetException());
            this.pointerInputJob = null;
        }
    }

    private final void dispatchPointerEvent(PointerEvent pointerEvent, PointerEventPass pointerEventPass) {
        MutableVector mutableVector;
        int size;
        synchronized (this.pointerHandlers) {
            MutableVector mutableVector2 = this.dispatchingPointerHandlers;
            mutableVector2.addAll(mutableVector2.getSize(), this.pointerHandlers);
        }
        try {
            int i = WhenMappings.$EnumSwitchMapping$0[pointerEventPass.ordinal()];
            if (i == 1 || i == 2) {
                MutableVector mutableVector3 = this.dispatchingPointerHandlers;
                int size2 = mutableVector3.getSize();
                if (size2 > 0) {
                    Object[] content = mutableVector3.getContent();
                    int i2 = 0;
                    do {
                        ((PointerEventHandlerCoroutine) content[i2]).offerPointerEvent(pointerEvent, pointerEventPass);
                        i2++;
                    } while (i2 < size2);
                }
            } else if (i == 3 && (size = (mutableVector = this.dispatchingPointerHandlers).getSize()) > 0) {
                int i3 = size - 1;
                Object[] content2 = mutableVector.getContent();
                do {
                    ((PointerEventHandlerCoroutine) content2[i3]).offerPointerEvent(pointerEvent, pointerEventPass);
                    i3--;
                } while (i3 >= 0);
            }
        } finally {
            this.dispatchingPointerHandlers.clear();
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo84onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        this.boundsSize = j;
        if (pointerEventPass == PointerEventPass.Initial) {
            this.currentEvent = pointerEvent;
        }
        if (this.pointerInputJob == null) {
            this.pointerInputJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, CoroutineStart.UNDISPATCHED, new SuspendingPointerInputModifierNodeImpl$onPointerEvent$1(this, null), 1, null);
        }
        dispatchPointerEvent(pointerEvent, pointerEventPass);
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                z = true;
                break;
            } else if (!PointerEventKt.changedToUpIgnoreConsumed((PointerInputChange) changes.get(i))) {
                break;
            } else {
                i++;
            }
        }
        if (z) {
            pointerEvent = null;
        }
        this.lastPointerEvent = pointerEvent;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        PointerEvent pointerEvent = this.lastPointerEvent;
        if (pointerEvent == null) {
            return;
        }
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i = 0; i < size; i++) {
            if (((PointerInputChange) changes.get(i)).getPressed()) {
                List changes2 = pointerEvent.getChanges();
                ArrayList arrayList = new ArrayList(changes2.size());
                int size2 = changes2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    PointerInputChange pointerInputChange = (PointerInputChange) changes2.get(i2);
                    arrayList.add(new PointerInputChange(pointerInputChange.m1680getIdJ3iCeTQ(), pointerInputChange.getUptimeMillis(), pointerInputChange.m1682getPositionF1C5BW0(), false, pointerInputChange.getPressure(), pointerInputChange.getUptimeMillis(), pointerInputChange.m1682getPositionF1C5BW0(), pointerInputChange.getPressed(), pointerInputChange.getPressed(), 0, 0L, 1536, (DefaultConstructorMarker) null));
                }
                PointerEvent pointerEvent2 = new PointerEvent(arrayList);
                this.currentEvent = pointerEvent2;
                dispatchPointerEvent(pointerEvent2, PointerEventPass.Initial);
                dispatchPointerEvent(pointerEvent2, PointerEventPass.Main);
                dispatchPointerEvent(pointerEvent2, PointerEventPass.Final);
                this.lastPointerEvent = null;
                return;
            }
        }
    }
}
