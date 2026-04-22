package androidx.compose.ui.platform;

import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.compose.ui.SessionMutex;
import androidx.compose.ui.text.input.TextInputService;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AndroidPlatformTextInputSession.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidPlatformTextInputSession implements PlatformTextInputSession, CoroutineScope {
    private final CoroutineScope coroutineScope;
    private final AtomicReference methodSessionMutex = SessionMutex.m1067constructorimpl();
    private final TextInputService textInputService;
    private final View view;

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$1, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidPlatformTextInputSession.android.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidPlatformTextInputSession.this.startInputMethod(null, this);
        }
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineScope.getCoroutineContext();
    }

    public AndroidPlatformTextInputSession(View view, TextInputService textInputService, CoroutineScope coroutineScope) {
        this.view = view;
        this.textInputService = textInputService;
        this.coroutineScope = coroutineScope;
    }

    @Override // androidx.compose.ui.platform.PlatformTextInputSession
    public View getView() {
        return this.view;
    }

    public final boolean isReadyForConnection() {
        InputMethodSession inputMethodSession = (InputMethodSession) SessionMutex.m1069getCurrentSessionimpl(this.methodSessionMutex);
        return inputMethodSession != null && inputMethodSession.isActive();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.ui.platform.PlatformTextInputSession
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object startInputMethod(final androidx.compose.ui.platform.PlatformTextInputMethodRequest r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof androidx.compose.ui.platform.AndroidPlatformTextInputSession.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$1 r0 = (androidx.compose.ui.platform.AndroidPlatformTextInputSession.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$1 r0 = new androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2d:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4a
        L31:
            kotlin.ResultKt.throwOnFailure(r7)
            java.util.concurrent.atomic.AtomicReference r7 = r5.methodSessionMutex
            androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$2 r2 = new androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$2
            r2.<init>()
            androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$3 r6 = new androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$3
            r4 = 0
            r6.<init>(r4)
            r0.label = r3
            java.lang.Object r6 = androidx.compose.ui.SessionMutex.m1070withSessionCancellingPreviousimpl(r7, r2, r6, r0)
            if (r6 != r1) goto L4a
            return r1
        L4a:
            kotlin.KotlinNothingValueException r6 = new kotlin.KotlinNothingValueException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidPlatformTextInputSession.startInputMethod(androidx.compose.ui.platform.PlatformTextInputMethodRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$3, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidPlatformTextInputSession.android.kt */
    static final class AnonymousClass3 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        Object L$1;
        int label;

        AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass3 anonymousClass3 = AndroidPlatformTextInputSession.this.new AnonymousClass3(continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(InputMethodSession inputMethodSession, Continuation continuation) {
            return ((AnonymousClass3) create(inputMethodSession, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final InputMethodSession inputMethodSession = (InputMethodSession) this.L$0;
                final AndroidPlatformTextInputSession androidPlatformTextInputSession = AndroidPlatformTextInputSession.this;
                this.L$0 = inputMethodSession;
                this.L$1 = androidPlatformTextInputSession;
                this.label = 1;
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(this), 1);
                cancellableContinuationImpl.initCancellability();
                androidPlatformTextInputSession.textInputService.startInput();
                cancellableContinuationImpl.invokeOnCancellation(new Function1() { // from class: androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$3$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                        invoke((Throwable) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Throwable th) {
                        inputMethodSession.dispose();
                        androidPlatformTextInputSession.textInputService.stopInput();
                    }
                });
                Object result = cancellableContinuationImpl.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(this);
                }
                if (result == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    public final InputConnection createInputConnection(EditorInfo editorInfo) {
        InputMethodSession inputMethodSession = (InputMethodSession) SessionMutex.m1069getCurrentSessionimpl(this.methodSessionMutex);
        if (inputMethodSession != null) {
            return inputMethodSession.createInputConnection(editorInfo);
        }
        return null;
    }
}
