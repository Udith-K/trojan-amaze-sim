package androidx.compose.ui.scrollcapture;

import android.graphics.Point;
import android.view.ScrollCaptureTarget;
import android.view.View;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRectKt;
import java.util.function.Consumer;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: ScrollCapture.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ScrollCapture implements ComposeScrollCaptureCallback.ScrollCaptureSessionListener {
    private final MutableState scrollCaptureInProgress$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);

    private final void setScrollCaptureInProgress(boolean z) {
        this.scrollCaptureInProgress$delegate.setValue(Boolean.valueOf(z));
    }

    public final boolean getScrollCaptureInProgress() {
        return ((Boolean) this.scrollCaptureInProgress$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: androidx.compose.ui.scrollcapture.ScrollCapture$onScrollCaptureSearch$1, reason: invalid class name */
    /* JADX INFO: compiled from: ScrollCapture.android.kt */
    /* synthetic */ class AnonymousClass1 extends AdaptedFunctionReference implements Function1 {
        AnonymousClass1(Object obj) {
            super(1, obj, MutableVector.class, "add", "add(Ljava/lang/Object;)Z", 8);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ScrollCaptureCandidate) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ScrollCaptureCandidate scrollCaptureCandidate) {
            ((MutableVector) this.receiver).add(scrollCaptureCandidate);
        }
    }

    @Override // androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback.ScrollCaptureSessionListener
    public void onSessionStarted() {
        setScrollCaptureInProgress(true);
    }

    @Override // androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback.ScrollCaptureSessionListener
    public void onSessionEnded() {
        setScrollCaptureInProgress(false);
    }

    public final void onScrollCaptureSearch(View view, SemanticsOwner semanticsOwner, CoroutineContext coroutineContext, Consumer<ScrollCaptureTarget> consumer) {
        MutableVector mutableVector = new MutableVector(new ScrollCaptureCandidate[16], 0);
        ScrollCapture_androidKt.visitScrollCaptureCandidates$default(semanticsOwner.getUnmergedRootSemanticsNode(), 0, new AnonymousClass1(mutableVector), 2, null);
        mutableVector.sortWith(ComparisonsKt.compareBy(new Function1() { // from class: androidx.compose.ui.scrollcapture.ScrollCapture.onScrollCaptureSearch.2
            @Override // kotlin.jvm.functions.Function1
            public final Comparable invoke(ScrollCaptureCandidate scrollCaptureCandidate) {
                return Integer.valueOf(scrollCaptureCandidate.getDepth());
            }
        }, new Function1() { // from class: androidx.compose.ui.scrollcapture.ScrollCapture.onScrollCaptureSearch.3
            @Override // kotlin.jvm.functions.Function1
            public final Comparable invoke(ScrollCaptureCandidate scrollCaptureCandidate) {
                return Integer.valueOf(scrollCaptureCandidate.getViewportBoundsInWindow().getHeight());
            }
        }));
        ScrollCaptureCandidate scrollCaptureCandidate = (ScrollCaptureCandidate) (mutableVector.isEmpty() ? null : mutableVector.getContent()[mutableVector.getSize() - 1]);
        if (scrollCaptureCandidate == null) {
            return;
        }
        ComposeScrollCaptureCallback composeScrollCaptureCallback = new ComposeScrollCaptureCallback(scrollCaptureCandidate.getNode(), scrollCaptureCandidate.getViewportBoundsInWindow(), CoroutineScopeKt.CoroutineScope(coroutineContext), this);
        Rect rectBoundsInRoot = LayoutCoordinatesKt.boundsInRoot(scrollCaptureCandidate.getCoordinates());
        long jM2469getTopLeftnOccac = scrollCaptureCandidate.getViewportBoundsInWindow().m2469getTopLeftnOccac();
        ScrollCaptureTarget scrollCaptureTargetM = ScrollCapture$$ExternalSyntheticApiModelOutline0.m(view, RectHelper_androidKt.toAndroidRect(IntRectKt.roundToIntRect(rectBoundsInRoot)), new Point(IntOffset.m2457getXimpl(jM2469getTopLeftnOccac), IntOffset.m2458getYimpl(jM2469getTopLeftnOccac)), ScrollCapture$$ExternalSyntheticApiModelOutline1.m(composeScrollCaptureCallback));
        scrollCaptureTargetM.setScrollBounds(RectHelper_androidKt.toAndroidRect(scrollCaptureCandidate.getViewportBoundsInWindow()));
        consumer.accept(scrollCaptureTargetM);
    }
}
