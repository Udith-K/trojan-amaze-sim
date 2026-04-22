package androidx.compose.ui.platform;

import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: InspectableValue.kt */
/* JADX INFO: loaded from: classes.dex */
public final class InspectableModifier extends InspectorValueInfo implements Modifier.Element {
    private final End end;

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ boolean all(Function1 function1) {
        return ((Boolean) function1.invoke(this)).booleanValue();
    }

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ Object foldIn(Object obj, Function2 function2) {
        return function2.invoke(obj, this);
    }

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ Modifier then(Modifier modifier) {
        return Modifier.CC.$default$then(this, modifier);
    }

    /* JADX INFO: compiled from: InspectableValue.kt */
    public final class End implements Modifier.Element {
        @Override // androidx.compose.ui.Modifier
        public /* synthetic */ boolean all(Function1 function1) {
            return ((Boolean) function1.invoke(this)).booleanValue();
        }

        @Override // androidx.compose.ui.Modifier
        public /* synthetic */ Object foldIn(Object obj, Function2 function2) {
            return function2.invoke(obj, this);
        }

        @Override // androidx.compose.ui.Modifier
        public /* synthetic */ Modifier then(Modifier modifier) {
            return Modifier.CC.$default$then(this, modifier);
        }

        public End() {
        }
    }

    public InspectableModifier(Function1 function1) {
        super(function1);
        this.end = new End();
    }
}
