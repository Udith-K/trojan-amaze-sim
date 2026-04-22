package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.customview.poolingcontainer.PoolingContainer;
import androidx.customview.poolingcontainer.PoolingContainerListener;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref$ObjectRef;

/* JADX INFO: compiled from: ViewCompositionStrategy.android.kt */
/* JADX INFO: loaded from: classes.dex */
public interface ViewCompositionStrategy {
    public static final Companion Companion = Companion.$$INSTANCE;

    Function0 installFor(AbstractComposeView abstractComposeView);

    /* JADX INFO: compiled from: ViewCompositionStrategy.android.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final ViewCompositionStrategy getDefault() {
            return DisposeOnDetachedFromWindowOrReleasedFromPool.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: ViewCompositionStrategy.android.kt */
    public static final class DisposeOnDetachedFromWindowOrReleasedFromPool implements ViewCompositionStrategy {
        public static final DisposeOnDetachedFromWindowOrReleasedFromPool INSTANCE = new DisposeOnDetachedFromWindowOrReleasedFromPool();

        private DisposeOnDetachedFromWindowOrReleasedFromPool() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [android.view.View$OnAttachStateChangeListener, androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$listener$1] */
        @Override // androidx.compose.ui.platform.ViewCompositionStrategy
        public Function0 installFor(final AbstractComposeView abstractComposeView) {
            final ?? r0 = new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$listener$1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view) {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view) {
                    if (PoolingContainer.isWithinPoolingContainer(abstractComposeView)) {
                        return;
                    }
                    abstractComposeView.disposeComposition();
                }
            };
            abstractComposeView.addOnAttachStateChangeListener(r0);
            final PoolingContainerListener poolingContainerListener = new PoolingContainerListener() { // from class: androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$$ExternalSyntheticLambda0
                @Override // androidx.customview.poolingcontainer.PoolingContainerListener
                public final void onRelease() {
                    abstractComposeView.disposeComposition();
                }
            };
            PoolingContainer.addPoolingContainerListener(abstractComposeView, poolingContainerListener);
            return new Function0() { // from class: androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m1988invoke();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m1988invoke() {
                    abstractComposeView.removeOnAttachStateChangeListener(r0);
                    PoolingContainer.removePoolingContainerListener(abstractComposeView, poolingContainerListener);
                }
            };
        }
    }

    /* JADX INFO: compiled from: ViewCompositionStrategy.android.kt */
    public static final class DisposeOnViewTreeLifecycleDestroyed implements ViewCompositionStrategy {
        public static final DisposeOnViewTreeLifecycleDestroyed INSTANCE = new DisposeOnViewTreeLifecycleDestroyed();

        private DisposeOnViewTreeLifecycleDestroyed() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [android.view.View$OnAttachStateChangeListener, androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnViewTreeLifecycleDestroyed$installFor$listener$1] */
        @Override // androidx.compose.ui.platform.ViewCompositionStrategy
        public Function0 installFor(final AbstractComposeView abstractComposeView) {
            if (abstractComposeView.isAttachedToWindow()) {
                LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(abstractComposeView);
                if (lifecycleOwner != null) {
                    return ViewCompositionStrategy_androidKt.installForLifecycle(abstractComposeView, lifecycleOwner.getLifecycle());
                }
                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("View tree for " + abstractComposeView + " has no ViewTreeLifecycleOwner");
                throw new KotlinNothingValueException();
            }
            final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            final ?? r1 = new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnViewTreeLifecycleDestroyed$installFor$listener$1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view) {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view) {
                    LifecycleOwner lifecycleOwner2 = ViewTreeLifecycleOwner.get(abstractComposeView);
                    AbstractComposeView abstractComposeView2 = abstractComposeView;
                    if (lifecycleOwner2 == null) {
                        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("View tree for " + abstractComposeView2 + " has no ViewTreeLifecycleOwner");
                        throw new KotlinNothingValueException();
                    }
                    ref$ObjectRef.element = ViewCompositionStrategy_androidKt.installForLifecycle(abstractComposeView2, lifecycleOwner2.getLifecycle());
                    abstractComposeView.removeOnAttachStateChangeListener(this);
                }
            };
            abstractComposeView.addOnAttachStateChangeListener(r1);
            ref$ObjectRef.element = new Function0() { // from class: androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnViewTreeLifecycleDestroyed$installFor$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m1989invoke();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m1989invoke() {
                    abstractComposeView.removeOnAttachStateChangeListener(r1);
                }
            };
            return new Function0() { // from class: androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnViewTreeLifecycleDestroyed$installFor$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m1990invoke();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m1990invoke() {
                    ((Function0) ref$ObjectRef.element).invoke();
                }
            };
        }
    }
}
