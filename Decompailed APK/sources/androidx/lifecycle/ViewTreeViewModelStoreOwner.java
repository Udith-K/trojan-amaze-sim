package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.viewmodel.R$id;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: ViewTreeViewModelStoreOwner.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ViewTreeViewModelStoreOwner {
    public static final void set(View view, ViewModelStoreOwner viewModelStoreOwner) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setTag(R$id.view_tree_view_model_store_owner, viewModelStoreOwner);
    }

    public static final ViewModelStoreOwner get(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return (ViewModelStoreOwner) SequencesKt.firstOrNull(SequencesKt.mapNotNull(SequencesKt.generateSequence(view, new Function1() { // from class: androidx.lifecycle.ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$1
            @Override // kotlin.jvm.functions.Function1
            public final View invoke(View view2) {
                Intrinsics.checkNotNullParameter(view2, "view");
                Object parent = view2.getParent();
                if (parent instanceof View) {
                    return (View) parent;
                }
                return null;
            }
        }), new Function1() { // from class: androidx.lifecycle.ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$2
            @Override // kotlin.jvm.functions.Function1
            public final ViewModelStoreOwner invoke(View view2) {
                Intrinsics.checkNotNullParameter(view2, "view");
                Object tag = view2.getTag(R$id.view_tree_view_model_store_owner);
                if (tag instanceof ViewModelStoreOwner) {
                    return (ViewModelStoreOwner) tag;
                }
                return null;
            }
        }));
    }
}
