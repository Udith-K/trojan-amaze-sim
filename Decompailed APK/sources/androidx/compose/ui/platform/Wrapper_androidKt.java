package androidx.compose.ui.platform;

import android.view.View;
import android.view.ViewGroup;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionKt;
import androidx.compose.runtime.ReusableComposition;
import androidx.compose.ui.R$id;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.UiApplier;
import java.util.Collections;
import java.util.WeakHashMap;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Wrapper.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Wrapper_androidKt {
    private static final ViewGroup.LayoutParams DefaultLayoutParams = new ViewGroup.LayoutParams(-2, -2);

    public static final ReusableComposition createSubcomposition(LayoutNode layoutNode, CompositionContext compositionContext) {
        return CompositionKt.ReusableComposition(new UiApplier(layoutNode), compositionContext);
    }

    public static final Composition setContent(AbstractComposeView abstractComposeView, CompositionContext compositionContext, Function2 function2) {
        GlobalSnapshotManager.INSTANCE.ensureStarted();
        AndroidComposeView androidComposeView = null;
        if (abstractComposeView.getChildCount() > 0) {
            View childAt = abstractComposeView.getChildAt(0);
            if (childAt instanceof AndroidComposeView) {
                androidComposeView = (AndroidComposeView) childAt;
            }
        } else {
            abstractComposeView.removeAllViews();
        }
        if (androidComposeView == null) {
            androidComposeView = new AndroidComposeView(abstractComposeView.getContext(), compositionContext.getEffectCoroutineContext());
            abstractComposeView.addView(androidComposeView.getView(), DefaultLayoutParams);
        }
        return doSetContent(androidComposeView, compositionContext, function2);
    }

    private static final Composition doSetContent(AndroidComposeView androidComposeView, CompositionContext compositionContext, Function2 function2) {
        if (InspectableValueKt.isDebugInspectorInfoEnabled() && androidComposeView.getTag(R$id.inspection_slot_table_set) == null) {
            androidComposeView.setTag(R$id.inspection_slot_table_set, Collections.newSetFromMap(new WeakHashMap()));
        }
        Composition Composition = CompositionKt.Composition(new UiApplier(androidComposeView.getRoot()), compositionContext);
        Object tag = androidComposeView.getView().getTag(R$id.wrapped_composition_tag);
        WrappedComposition wrappedComposition = tag instanceof WrappedComposition ? (WrappedComposition) tag : null;
        if (wrappedComposition == null) {
            wrappedComposition = new WrappedComposition(androidComposeView, Composition);
            androidComposeView.getView().setTag(R$id.wrapped_composition_tag, wrappedComposition);
        }
        wrappedComposition.setContent(function2);
        if (!Intrinsics.areEqual(androidComposeView.getCoroutineContext(), compositionContext.getEffectCoroutineContext())) {
            androidComposeView.setCoroutineContext(compositionContext.getEffectCoroutineContext());
        }
        return wrappedComposition;
    }
}
