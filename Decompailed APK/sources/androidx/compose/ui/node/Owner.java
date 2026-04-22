package androidx.compose.ui.node;

import androidx.compose.ui.autofill.Autofill;
import androidx.compose.ui.autofill.AutofillTree;
import androidx.compose.ui.draganddrop.DragAndDropManager;
import androidx.compose.ui.focus.FocusOwner;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.InputModeManager;
import androidx.compose.ui.input.pointer.PointerIconService;
import androidx.compose.ui.input.pointer.PositionCalculator;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.modifier.ModifierLocalManager;
import androidx.compose.ui.platform.AccessibilityManager;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.text.font.Font$ResourceLoader;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Owner.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Owner extends PositionCalculator {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: Owner.kt */
    public interface OnLayoutCompletedListener {
        void onLayoutComplete();
    }

    /* JADX INFO: renamed from: calculateLocalPosition-MK-Hz9U, reason: not valid java name */
    long mo1903calculateLocalPositionMKHz9U(long j);

    /* JADX INFO: renamed from: calculatePositionInWindow-MK-Hz9U, reason: not valid java name */
    long mo1904calculatePositionInWindowMKHz9U(long j);

    OwnedLayer createLayer(Function2 function2, Function0 function0, GraphicsLayer graphicsLayer);

    void forceMeasureTheSubtree(LayoutNode layoutNode, boolean z);

    AccessibilityManager getAccessibilityManager();

    Autofill getAutofill();

    AutofillTree getAutofillTree();

    ClipboardManager getClipboardManager();

    CoroutineContext getCoroutineContext();

    Density getDensity();

    DragAndDropManager getDragAndDropManager();

    FocusOwner getFocusOwner();

    FontFamily.Resolver getFontFamilyResolver();

    Font$ResourceLoader getFontLoader();

    GraphicsContext getGraphicsContext();

    HapticFeedback getHapticFeedBack();

    InputModeManager getInputModeManager();

    LayoutDirection getLayoutDirection();

    ModifierLocalManager getModifierLocalManager();

    Placeable.PlacementScope getPlacementScope();

    PointerIconService getPointerIconService();

    LayoutNode getRoot();

    LayoutNodeDrawScope getSharedDrawScope();

    boolean getShowLayoutBounds();

    OwnerSnapshotObserver getSnapshotObserver();

    SoftwareKeyboardController getSoftwareKeyboardController();

    TextInputService getTextInputService();

    TextToolbar getTextToolbar();

    ViewConfiguration getViewConfiguration();

    WindowInfo getWindowInfo();

    void measureAndLayout(boolean z);

    /* JADX INFO: renamed from: measureAndLayout-0kLqBqw, reason: not valid java name */
    void mo1905measureAndLayout0kLqBqw(LayoutNode layoutNode, long j);

    void onAttach(LayoutNode layoutNode);

    void onDetach(LayoutNode layoutNode);

    void onEndApplyChanges();

    void onLayoutChange(LayoutNode layoutNode);

    void onRequestMeasure(LayoutNode layoutNode, boolean z, boolean z2, boolean z3);

    void onRequestRelayout(LayoutNode layoutNode, boolean z, boolean z2);

    void onSemanticsChange();

    void registerOnEndApplyChangesListener(Function0 function0);

    void requestOnPositionedCallback(LayoutNode layoutNode);

    void setShowLayoutBounds(boolean z);

    Object textInputSession(Function2 function2, Continuation continuation);

    /* JADX INFO: renamed from: androidx.compose.ui.node.Owner$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Owner.kt */
    public abstract /* synthetic */ class CC {
        static {
            Companion companion = Owner.Companion;
        }

        public static /* synthetic */ void onRequestMeasure$default(Owner owner, LayoutNode layoutNode, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onRequestMeasure");
            }
            if ((i & 2) != 0) {
                z = false;
            }
            if ((i & 4) != 0) {
                z2 = false;
            }
            if ((i & 8) != 0) {
                z3 = true;
            }
            owner.onRequestMeasure(layoutNode, z, z2, z3);
        }

        public static /* synthetic */ void onRequestRelayout$default(Owner owner, LayoutNode layoutNode, boolean z, boolean z2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onRequestRelayout");
            }
            if ((i & 2) != 0) {
                z = false;
            }
            if ((i & 4) != 0) {
                z2 = false;
            }
            owner.onRequestRelayout(layoutNode, z, z2);
        }

        public static /* synthetic */ void measureAndLayout$default(Owner owner, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: measureAndLayout");
            }
            if ((i & 1) != 0) {
                z = true;
            }
            owner.measureAndLayout(z);
        }

        public static /* synthetic */ void forceMeasureTheSubtree$default(Owner owner, LayoutNode layoutNode, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: forceMeasureTheSubtree");
            }
            if ((i & 2) != 0) {
                z = false;
            }
            owner.forceMeasureTheSubtree(layoutNode, z);
        }

        public static /* synthetic */ OwnedLayer createLayer$default(Owner owner, Function2 function2, Function0 function0, GraphicsLayer graphicsLayer, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createLayer");
            }
            if ((i & 4) != 0) {
                graphicsLayer = null;
            }
            return owner.createLayer(function2, function0, graphicsLayer);
        }
    }

    /* JADX INFO: compiled from: Owner.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static boolean enableExtraAssertions;

        private Companion() {
        }

        public final boolean getEnableExtraAssertions() {
            return enableExtraAssertions;
        }
    }
}
