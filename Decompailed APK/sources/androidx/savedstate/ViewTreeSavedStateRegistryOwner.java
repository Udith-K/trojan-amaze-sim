package androidx.savedstate;

import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: ViewTreeSavedStateRegistryOwner.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ViewTreeSavedStateRegistryOwner {
    public static final void set(View view, SavedStateRegistryOwner savedStateRegistryOwner) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setTag(R$id.view_tree_saved_state_registry_owner, savedStateRegistryOwner);
    }

    public static final SavedStateRegistryOwner get(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return (SavedStateRegistryOwner) SequencesKt.firstOrNull(SequencesKt.mapNotNull(SequencesKt.generateSequence(view, new Function1() { // from class: androidx.savedstate.ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$1
            @Override // kotlin.jvm.functions.Function1
            public final View invoke(View view2) {
                Intrinsics.checkNotNullParameter(view2, "view");
                Object parent = view2.getParent();
                if (parent instanceof View) {
                    return (View) parent;
                }
                return null;
            }
        }), new Function1() { // from class: androidx.savedstate.ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$2
            @Override // kotlin.jvm.functions.Function1
            public final SavedStateRegistryOwner invoke(View view2) {
                Intrinsics.checkNotNullParameter(view2, "view");
                Object tag = view2.getTag(R$id.view_tree_saved_state_registry_owner);
                if (tag instanceof SavedStateRegistryOwner) {
                    return (SavedStateRegistryOwner) tag;
                }
                return null;
            }
        }));
    }
}
