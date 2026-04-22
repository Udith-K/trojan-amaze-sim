package androidx.compose.ui.platform;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.input.pointer.PointerEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: WindowInfo.kt */
/* JADX INFO: loaded from: classes.dex */
public final class WindowInfoImpl implements WindowInfo {
    public static final Companion Companion = new Companion(null);
    private static final MutableState GlobalKeyboardModifiers = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(PointerKeyboardModifiers.m1694boximpl(PointerEvent_androidKt.EmptyPointerKeyboardModifiers()), null, 2, null);
    private final MutableState _isWindowFocused = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);

    public void setWindowFocused(boolean z) {
        this._isWindowFocused.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.ui.platform.WindowInfo
    public boolean isWindowFocused() {
        return ((Boolean) this._isWindowFocused.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: setKeyboardModifiers-5xRPYO0, reason: not valid java name */
    public void m1994setKeyboardModifiers5xRPYO0(int i) {
        GlobalKeyboardModifiers.setValue(PointerKeyboardModifiers.m1694boximpl(i));
    }

    /* JADX INFO: compiled from: WindowInfo.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
