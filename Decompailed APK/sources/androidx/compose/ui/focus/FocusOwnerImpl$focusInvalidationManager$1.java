package androidx.compose.ui.focus;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: FocusOwnerImpl.kt */
/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class FocusOwnerImpl$focusInvalidationManager$1 extends FunctionReferenceImpl implements Function0 {
    FocusOwnerImpl$focusInvalidationManager$1(Object obj) {
        super(0, obj, FocusOwnerImpl.class, "invalidateOwnerFocusState", "invalidateOwnerFocusState()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m1115invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m1115invoke() {
        ((FocusOwnerImpl) this.receiver).invalidateOwnerFocusState();
    }
}
