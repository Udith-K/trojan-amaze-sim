package androidx.compose.ui.input;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: InputModeManager.kt */
/* JADX INFO: loaded from: classes.dex */
public final class InputModeManagerImpl implements InputModeManager {
    private final MutableState inputMode$delegate;
    private final Function1 onRequestInputModeChange;

    public /* synthetic */ InputModeManagerImpl(int i, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, function1);
    }

    private InputModeManagerImpl(int i, Function1 function1) {
        this.onRequestInputModeChange = function1;
        this.inputMode$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(InputMode.m1585boximpl(i), null, 2, null);
    }

    @Override // androidx.compose.ui.input.InputModeManager
    /* JADX INFO: renamed from: getInputMode-aOaMEAU */
    public int mo1594getInputModeaOaMEAU() {
        return ((InputMode) this.inputMode$delegate.getValue()).m1591unboximpl();
    }

    /* JADX INFO: renamed from: setInputMode-iuPiT84, reason: not valid java name */
    public void m1595setInputModeiuPiT84(int i) {
        this.inputMode$delegate.setValue(InputMode.m1585boximpl(i));
    }
}
