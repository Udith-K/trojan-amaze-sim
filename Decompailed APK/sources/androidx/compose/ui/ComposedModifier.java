package androidx.compose.ui;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectorValueInfo;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: ComposedModifier.kt */
/* JADX INFO: loaded from: classes.dex */
class ComposedModifier extends InspectorValueInfo implements Modifier.Element {
    private final Function3 factory;

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

    public final Function3 getFactory() {
        return this.factory;
    }

    public ComposedModifier(Function1 function1, Function3 function3) {
        super(function1);
        this.factory = function3;
    }
}
