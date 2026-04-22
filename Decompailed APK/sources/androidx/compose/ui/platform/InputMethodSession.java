package androidx.compose.ui.platform;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.text.input.NullableInputConnectionWrapper;
import androidx.compose.ui.text.input.NullableInputConnectionWrapper_androidKt;
import java.lang.ref.WeakReference;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidPlatformTextInputSession.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class InputMethodSession {
    private boolean disposed;
    private final Function0 onAllConnectionsClosed;
    private final PlatformTextInputMethodRequest request;
    private final Object lock = new Object();
    private MutableVector connections = new MutableVector(new WeakReference[16], 0);

    public InputMethodSession(PlatformTextInputMethodRequest platformTextInputMethodRequest, Function0 function0) {
        this.request = platformTextInputMethodRequest;
        this.onAllConnectionsClosed = function0;
    }

    public final boolean isActive() {
        return !this.disposed;
    }

    public final InputConnection createInputConnection(EditorInfo editorInfo) {
        synchronized (this.lock) {
            if (this.disposed) {
                return null;
            }
            NullableInputConnectionWrapper NullableInputConnectionWrapper = NullableInputConnectionWrapper_androidKt.NullableInputConnectionWrapper(this.request.createInputConnection(editorInfo), new Function1() { // from class: androidx.compose.ui.platform.InputMethodSession$createInputConnection$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((NullableInputConnectionWrapper) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(NullableInputConnectionWrapper nullableInputConnectionWrapper) {
                    int i;
                    nullableInputConnectionWrapper.disposeDelegate();
                    MutableVector mutableVector = this.this$0.connections;
                    int size = mutableVector.getSize();
                    if (size <= 0) {
                        i = -1;
                        break;
                    }
                    Object[] content = mutableVector.getContent();
                    i = 0;
                    while (!Intrinsics.areEqual((WeakReference) content[i], nullableInputConnectionWrapper)) {
                        i++;
                        if (i >= size) {
                            i = -1;
                            break;
                        }
                    }
                    if (i >= 0) {
                        this.this$0.connections.removeAt(i);
                    }
                    if (this.this$0.connections.isEmpty()) {
                        this.this$0.onAllConnectionsClosed.invoke();
                    }
                }
            });
            this.connections.add(new WeakReference(NullableInputConnectionWrapper));
            return NullableInputConnectionWrapper;
        }
    }

    public final void dispose() {
        synchronized (this.lock) {
            try {
                this.disposed = true;
                MutableVector mutableVector = this.connections;
                int size = mutableVector.getSize();
                if (size > 0) {
                    Object[] content = mutableVector.getContent();
                    int i = 0;
                    do {
                        NullableInputConnectionWrapper nullableInputConnectionWrapper = (NullableInputConnectionWrapper) ((WeakReference) content[i]).get();
                        if (nullableInputConnectionWrapper != null) {
                            nullableInputConnectionWrapper.disposeDelegate();
                        }
                        i++;
                    } while (i < size);
                }
                this.connections.clear();
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
