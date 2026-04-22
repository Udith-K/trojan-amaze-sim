package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: CoreTextField.kt */
/* JADX INFO: loaded from: classes.dex */
final class CoreTextFieldKt$TextFieldCursorHandle$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ TextFieldSelectionManager $manager;
    final /* synthetic */ TextDragObserver $observer;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CoreTextFieldKt$TextFieldCursorHandle$2$1(TextDragObserver textDragObserver, TextFieldSelectionManager textFieldSelectionManager, Continuation continuation) {
        super(2, continuation);
        this.$observer = textDragObserver;
        this.$manager = textFieldSelectionManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CoreTextFieldKt$TextFieldCursorHandle$2$1 coreTextFieldKt$TextFieldCursorHandle$2$1 = new CoreTextFieldKt$TextFieldCursorHandle$2$1(this.$observer, this.$manager, continuation);
        coreTextFieldKt$TextFieldCursorHandle$2$1.L$0 = obj;
        return coreTextFieldKt$TextFieldCursorHandle$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
        return ((CoreTextFieldKt$TextFieldCursorHandle$2$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: CoreTextField.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ PointerInputScope $$this$pointerInput;
        final /* synthetic */ TextFieldSelectionManager $manager;
        final /* synthetic */ TextDragObserver $observer;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, TextFieldSelectionManager textFieldSelectionManager, Continuation continuation) {
            super(2, continuation);
            this.$$this$pointerInput = pointerInputScope;
            this.$observer = textDragObserver;
            this.$manager = textFieldSelectionManager;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$pointerInput, this.$observer, this.$manager, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            CoroutineStart coroutineStart = CoroutineStart.UNDISPATCHED;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, coroutineStart, new C00191(this.$$this$pointerInput, this.$observer, null), 1, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, coroutineStart, new AnonymousClass2(this.$$this$pointerInput, this.$manager, null), 1, null);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: CoreTextField.kt */
        static final class C00191 extends SuspendLambda implements Function2 {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ TextDragObserver $observer;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00191(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, Continuation continuation) {
                super(2, continuation);
                this.$$this$pointerInput = pointerInputScope;
                this.$observer = textDragObserver;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00191(this.$$this$pointerInput, this.$observer, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((C00191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PointerInputScope pointerInputScope = this.$$this$pointerInput;
                    TextDragObserver textDragObserver = this.$observer;
                    this.label = 1;
                    if (LongPressTextDragObserverKt.detectDownAndDragGesturesWithObserver(pointerInputScope, textDragObserver, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: CoreTextField.kt */
        static final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ TextFieldSelectionManager $manager;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(PointerInputScope pointerInputScope, TextFieldSelectionManager textFieldSelectionManager, Continuation continuation) {
                super(2, continuation);
                this.$$this$pointerInput = pointerInputScope;
                this.$manager = textFieldSelectionManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$$this$pointerInput, this.$manager, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PointerInputScope pointerInputScope = this.$$this$pointerInput;
                    final TextFieldSelectionManager textFieldSelectionManager = this.$manager;
                    Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.TextFieldCursorHandle.2.1.1.2.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                            m381invokek4lQ0M(((Offset) obj2).m1168unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                        public final void m381invokek4lQ0M(long j) {
                            textFieldSelectionManager.showSelectionToolbar$foundation_release();
                        }
                    };
                    this.label = 1;
                    if (TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, function1, this, 7, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1((PointerInputScope) this.L$0, this.$observer, this.$manager, null);
            this.label = 1;
            if (CoroutineScopeKt.coroutineScope(anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
