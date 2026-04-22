package androidx.compose.ui.platform;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Build;
import android.os.SystemClock;
import android.os.Trace;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.SessionMutex;
import androidx.compose.ui.autofill.AndroidAutofill;
import androidx.compose.ui.autofill.AndroidAutofill_androidKt;
import androidx.compose.ui.autofill.Autofill;
import androidx.compose.ui.autofill.AutofillCallback;
import androidx.compose.ui.autofill.AutofillTree;
import androidx.compose.ui.contentcapture.AndroidContentCaptureManager;
import androidx.compose.ui.draganddrop.ComposeDragShadowBuilder;
import androidx.compose.ui.draganddrop.DragAndDropManager;
import androidx.compose.ui.draganddrop.DragAndDropTransferData;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusInteropUtils_androidKt;
import androidx.compose.ui.focus.FocusOwner;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusOwnerImplKt;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.focus.FocusTransactionsKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidGraphicsContext_androidKt;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.PlatformHapticFeedback;
import androidx.compose.ui.input.InputMode;
import androidx.compose.ui.input.InputModeManager;
import androidx.compose.ui.input.InputModeManagerImpl;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.MotionEventAdapter;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconService;
import androidx.compose.ui.input.pointer.PointerInputEvent;
import androidx.compose.ui.input.pointer.PointerInputEventData;
import androidx.compose.ui.input.pointer.PointerInputEventProcessor;
import androidx.compose.ui.input.pointer.PointerInputEventProcessorKt;
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers;
import androidx.compose.ui.input.pointer.PositionCalculator;
import androidx.compose.ui.input.pointer.ProcessResult;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryInputModifierKt;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.layout.RootMeasurePolicy;
import androidx.compose.ui.modifier.ModifierLocalManager;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.MeasureAndLayoutDelegate;
import androidx.compose.ui.node.NestedVectorStack;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.OwnerSnapshotObserver;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.RootForTest;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.ViewLayer;
import androidx.compose.ui.scrollcapture.ScrollCapture;
import androidx.compose.ui.semantics.EmptySemanticsElement;
import androidx.compose.ui.semantics.EmptySemanticsModifier;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.text.font.Font$ResourceLoader;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontFamilyResolver_androidKt;
import androidx.compose.ui.text.input.PlatformTextInputService;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputServiceAndroid;
import androidx.compose.ui.unit.AndroidDensity_androidKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.ULong;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: AndroidComposeView.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidComposeView extends ViewGroup implements Owner, ViewRootForTest, PositionCalculator, DefaultLifecycleObserver {
    private static Method getBooleanMethod;
    private static Class systemPropertiesClass;
    private AndroidViewsHandler _androidViewsHandler;
    private final AndroidAutofill _autofill;
    private final InputModeManagerImpl _inputModeManager;
    private final MutableState _viewTreeOwners$delegate;
    private final WindowInfoImpl _windowInfo;
    private final AndroidAccessibilityManager accessibilityManager;
    private final AutofillTree autofillTree;
    private final CanvasHolder canvasHolder;
    private final AndroidClipboardManager clipboardManager;
    private final AndroidComposeViewAccessibilityDelegateCompat composeAccessibilityDelegate;
    private Function1 configurationChangeObserver;
    private AndroidContentCaptureManager contentCaptureManager;
    private CoroutineContext coroutineContext;
    private int currentFontWeightAdjustment;
    private final MutableState density$delegate;
    private final List dirtyLayers;
    private final DragAndDropManager dragAndDropManager;
    private final DragAndDropModifierOnDragListener dragAndDropModifierOnDragListener;
    private final MutableVector endApplyChangesListeners;
    private final FocusOwner focusOwner;
    private final MutableState fontFamilyResolver$delegate;
    private final Font$ResourceLoader fontLoader;
    private boolean forceUseMatrixCache;
    private final ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
    private long globalPosition;
    private final GraphicsContext graphicsContext;
    private final HapticFeedback hapticFeedBack;
    private boolean hoverExitReceived;
    private boolean isDrawingContent;
    private boolean isPendingInteropViewLayoutChangeDispatch;
    private boolean isRenderNodeCompatible;
    private final Modifier keyInputModifier;
    private boolean keyboardModifiersRequireUpdate;
    private long lastDownPointerPosition;
    private long lastMatrixRecalculationAnimationTime;
    private final WeakCache layerCache;
    private final MutableState layoutDirection$delegate;
    private final TextInputServiceAndroid legacyTextInputServiceAndroid;
    private final CalculateMatrixToWindow matrixToWindow;
    private final MeasureAndLayoutDelegate measureAndLayoutDelegate;
    private final ModifierLocalManager modifierLocalManager;
    private final MotionEventAdapter motionEventAdapter;
    private boolean observationClearRequested;
    private Constraints onMeasureConstraints;
    private Function1 onViewTreeOwnersAvailable;
    private final PointerIconService pointerIconService;
    private final PointerInputEventProcessor pointerInputEventProcessor;
    private List postponedDirtyLayers;
    private MotionEvent previousMotionEvent;
    private long relayoutTime;
    private final Function0 resendMotionEventOnLayout;
    private final AndroidComposeView$resendMotionEventRunnable$1 resendMotionEventRunnable;
    private final LayoutNode root;
    private final RootForTest rootForTest;
    private final EmptySemanticsModifier rootSemanticsNode;
    private final Modifier rotaryInputModifier;
    private final ScrollCapture scrollCapture;
    private final ViewTreeObserver.OnScrollChangedListener scrollChangedListener;
    private final EmptySemanticsElement semanticsModifier;
    private final SemanticsOwner semanticsOwner;
    private final Runnable sendHoverExitEvent;
    private final LayoutNodeDrawScope sharedDrawScope;
    private boolean showLayoutBounds;
    private final OwnerSnapshotObserver snapshotObserver;
    private final SoftwareKeyboardController softwareKeyboardController;
    private boolean superclassInitComplete;
    private final TextInputService textInputService;
    private final AtomicReference textInputSessionMutex;
    private final TextToolbar textToolbar;
    private final float[] tmpMatrix;
    private final int[] tmpPositionArray;
    private final ViewTreeObserver.OnTouchModeChangeListener touchModeChangeListener;
    private final ViewConfiguration viewConfiguration;
    private DrawChildContainer viewLayersContainer;
    private final float[] viewToWindowMatrix;
    private final State viewTreeOwners$delegate;
    private boolean wasMeasuredWithMultipleConstraints;
    private long windowPosition;
    private final float[] windowToViewMatrix;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidComposeView$textInputSession$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AndroidComposeView.android.kt */
    static final class C01561 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C01561(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidComposeView.this.textInputSession(null, this);
        }
    }

    @Deprecated
    public static /* synthetic */ void getFontLoader$annotations() {
    }

    public static /* synthetic */ void getLastMatrixRecalculationAnimationTime$ui_release$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    public View getView() {
        return this;
    }

    @Override // androidx.compose.ui.node.Owner
    public void onAttach(LayoutNode layoutNode) {
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onStart(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX WARN: Type inference failed for: r14v13, types: [androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1] */
    public AndroidComposeView(Context context, CoroutineContext coroutineContext) {
        super(context);
        Offset.Companion companion = Offset.Companion;
        this.lastDownPointerPosition = companion.m1170getUnspecifiedF1C5BW0();
        this.superclassInitComplete = true;
        byte b = 0;
        byte b2 = 0;
        this.sharedDrawScope = new LayoutNodeDrawScope(0 == true ? 1 : 0, 1, 0 == true ? 1 : 0);
        this.density$delegate = SnapshotStateKt.mutableStateOf(AndroidDensity_androidKt.Density(context), SnapshotStateKt.referentialEqualityPolicy());
        EmptySemanticsModifier emptySemanticsModifier = new EmptySemanticsModifier();
        this.rootSemanticsNode = emptySemanticsModifier;
        EmptySemanticsElement emptySemanticsElement = new EmptySemanticsElement(emptySemanticsModifier);
        this.semanticsModifier = emptySemanticsElement;
        this.focusOwner = new FocusOwnerImpl(new AndroidComposeView$focusOwner$1(this), new AndroidComposeView$focusOwner$2(this), new AndroidComposeView$focusOwner$3(this), new AndroidComposeView$focusOwner$4(this), new AndroidComposeView$focusOwner$5(this), new MutablePropertyReference0Impl(this) { // from class: androidx.compose.ui.platform.AndroidComposeView$focusOwner$6
            @Override // kotlin.reflect.KProperty0
            public Object get() {
                return ((AndroidComposeView) this.receiver).getLayoutDirection();
            }
        });
        DragAndDropModifierOnDragListener dragAndDropModifierOnDragListener = new DragAndDropModifierOnDragListener(new AndroidComposeView$dragAndDropModifierOnDragListener$1(this));
        this.dragAndDropModifierOnDragListener = dragAndDropModifierOnDragListener;
        this.coroutineContext = coroutineContext;
        this.dragAndDropManager = dragAndDropModifierOnDragListener;
        this._windowInfo = new WindowInfoImpl();
        Modifier.Companion companion2 = Modifier.Companion;
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(companion2, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$keyInputModifier$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m1935invokeZmokQxo(((KeyEvent) obj).m1620unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m1935invokeZmokQxo(android.view.KeyEvent keyEvent) {
                final FocusDirection focusDirectionM1929getFocusDirectionP8AzH3I = this.this$0.m1929getFocusDirectionP8AzH3I(keyEvent);
                if (focusDirectionM1929getFocusDirectionP8AzH3I != null && KeyEventType.m1622equalsimpl0(KeyEvent_androidKt.m1627getTypeZmokQxo(keyEvent), KeyEventType.Companion.m1623getKeyDownCS__XNY())) {
                    Rect rectOnFetchFocusRect = this.this$0.onFetchFocusRect();
                    Boolean boolMo1109focusSearchULY8qGw = this.this$0.getFocusOwner().mo1109focusSearchULY8qGw(focusDirectionM1929getFocusDirectionP8AzH3I.m1094unboximpl(), rectOnFetchFocusRect, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$keyInputModifier$1$focusWasMovedOrCancelled$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Boolean invoke(FocusTargetNode focusTargetNode) {
                            Boolean boolM1126requestFocusMxy_nc0 = FocusTransactionsKt.m1126requestFocusMxy_nc0(focusTargetNode, focusDirectionM1929getFocusDirectionP8AzH3I.m1094unboximpl());
                            return Boolean.valueOf(boolM1126requestFocusMxy_nc0 != null ? boolM1126requestFocusMxy_nc0.booleanValue() : true);
                        }
                    });
                    if (boolMo1109focusSearchULY8qGw != null ? boolMo1109focusSearchULY8qGw.booleanValue() : true) {
                        return Boolean.TRUE;
                    }
                    if (!FocusOwnerImplKt.m1116is1dFocusSearch3ESFkO8(focusDirectionM1929getFocusDirectionP8AzH3I.m1094unboximpl())) {
                        return Boolean.FALSE;
                    }
                    Integer numM1103toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m1103toAndroidFocusDirection3ESFkO8(focusDirectionM1929getFocusDirectionP8AzH3I.m1094unboximpl());
                    if (numM1103toAndroidFocusDirection3ESFkO8 == null) {
                        throw new IllegalStateException("Invalid focus direction");
                    }
                    int iIntValue = numM1103toAndroidFocusDirection3ESFkO8.intValue();
                    android.graphics.Rect androidRect = rectOnFetchFocusRect != null ? RectHelper_androidKt.toAndroidRect(rectOnFetchFocusRect) : null;
                    if (androidRect != null) {
                        View viewFindNextNonChildView = this.this$0.findNextNonChildView(iIntValue);
                        if (Intrinsics.areEqual(viewFindNextNonChildView, this.this$0)) {
                            viewFindNextNonChildView = null;
                        }
                        if (viewFindNextNonChildView != null && FocusInteropUtils_androidKt.requestInteropFocus(viewFindNextNonChildView, Integer.valueOf(iIntValue), androidRect)) {
                            return Boolean.TRUE;
                        }
                        if (!this.this$0.getFocusOwner().mo1106clearFocusI7lrPNg(false, true, false, focusDirectionM1929getFocusDirectionP8AzH3I.m1094unboximpl())) {
                            return Boolean.TRUE;
                        }
                        Boolean boolMo1109focusSearchULY8qGw2 = this.this$0.getFocusOwner().mo1109focusSearchULY8qGw(focusDirectionM1929getFocusDirectionP8AzH3I.m1094unboximpl(), null, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$keyInputModifier$1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                                Boolean boolM1126requestFocusMxy_nc0 = FocusTransactionsKt.m1126requestFocusMxy_nc0(focusTargetNode, focusDirectionM1929getFocusDirectionP8AzH3I.m1094unboximpl());
                                return Boolean.valueOf(boolM1126requestFocusMxy_nc0 != null ? boolM1126requestFocusMxy_nc0.booleanValue() : true);
                            }
                        });
                        return Boolean.valueOf(boolMo1109focusSearchULY8qGw2 != null ? boolMo1109focusSearchULY8qGw2.booleanValue() : true);
                    }
                    throw new IllegalStateException("Invalid rect");
                }
                return Boolean.FALSE;
            }
        });
        this.keyInputModifier = modifierOnKeyEvent;
        Modifier modifierOnRotaryScrollEvent = RotaryInputModifierKt.onRotaryScrollEvent(companion2, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$rotaryInputModifier$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(RotaryScrollEvent rotaryScrollEvent) {
                return Boolean.FALSE;
            }
        });
        this.rotaryInputModifier = modifierOnRotaryScrollEvent;
        this.canvasHolder = new CanvasHolder();
        LayoutNode layoutNode = new LayoutNode(false, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        layoutNode.setMeasurePolicy(RootMeasurePolicy.INSTANCE);
        layoutNode.setDensity(getDensity());
        layoutNode.setModifier(companion2.then(emptySemanticsElement).then(modifierOnRotaryScrollEvent).then(modifierOnKeyEvent).then(getFocusOwner().getModifier()).then(dragAndDropModifierOnDragListener.getModifier()));
        this.root = layoutNode;
        this.rootForTest = this;
        this.semanticsOwner = new SemanticsOwner(getRoot(), emptySemanticsModifier);
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = new AndroidComposeViewAccessibilityDelegateCompat(this);
        this.composeAccessibilityDelegate = androidComposeViewAccessibilityDelegateCompat;
        this.contentCaptureManager = new AndroidContentCaptureManager(this, new AndroidComposeView$contentCaptureManager$1(this));
        this.accessibilityManager = new AndroidAccessibilityManager(context);
        this.graphicsContext = AndroidGraphicsContext_androidKt.GraphicsContext(this);
        this.autofillTree = new AutofillTree();
        this.dirtyLayers = new ArrayList();
        this.motionEventAdapter = new MotionEventAdapter();
        this.pointerInputEventProcessor = new PointerInputEventProcessor(getRoot());
        this.configurationChangeObserver = new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$configurationChangeObserver$1
            public final void invoke(Configuration configuration) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Configuration) obj);
                return Unit.INSTANCE;
            }
        };
        this._autofill = autofillSupported() ? new AndroidAutofill(this, getAutofillTree()) : null;
        this.clipboardManager = new AndroidClipboardManager(context);
        this.snapshotObserver = new OwnerSnapshotObserver(new AndroidComposeView$snapshotObserver$1(this));
        this.measureAndLayoutDelegate = new MeasureAndLayoutDelegate(getRoot());
        this.viewConfiguration = new AndroidViewConfiguration(android.view.ViewConfiguration.get(context));
        this.globalPosition = IntOffsetKt.IntOffset(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.tmpPositionArray = new int[]{0, 0};
        float[] fArrM1356constructorimpl$default = Matrix.m1356constructorimpl$default(null, 1, null);
        this.tmpMatrix = fArrM1356constructorimpl$default;
        this.viewToWindowMatrix = Matrix.m1356constructorimpl$default(null, 1, null);
        this.windowToViewMatrix = Matrix.m1356constructorimpl$default(null, 1, null);
        this.lastMatrixRecalculationAnimationTime = -1L;
        this.windowPosition = companion.m1169getInfiniteF1C5BW0();
        this.isRenderNodeCompatible = true;
        this._viewTreeOwners$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.viewTreeOwners$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.ui.platform.AndroidComposeView$viewTreeOwners$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final AndroidComposeView.ViewTreeOwners invoke() {
                return this.this$0.get_viewTreeOwners();
            }
        });
        this.globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f$0.updatePositionCacheAndDispatch();
            }
        };
        this.scrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda2
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                this.f$0.updatePositionCacheAndDispatch();
            }
        };
        this.touchModeChangeListener = new ViewTreeObserver.OnTouchModeChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda3
            @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
            public final void onTouchModeChanged(boolean z) {
                AndroidComposeView.touchModeChangeListener$lambda$6(this.f$0, z);
            }
        };
        TextInputServiceAndroid textInputServiceAndroid = new TextInputServiceAndroid(getView(), this);
        this.legacyTextInputServiceAndroid = textInputServiceAndroid;
        this.textInputService = new TextInputService((PlatformTextInputService) AndroidComposeView_androidKt.getPlatformTextInputServiceInterceptor().invoke(textInputServiceAndroid));
        this.textInputSessionMutex = SessionMutex.m1067constructorimpl();
        this.softwareKeyboardController = new DelegatingSoftwareKeyboardController(getTextInputService());
        this.fontLoader = new AndroidFontResourceLoader(context);
        this.fontFamilyResolver$delegate = SnapshotStateKt.mutableStateOf(FontFamilyResolver_androidKt.createFontFamilyResolver(context), SnapshotStateKt.referentialEqualityPolicy());
        this.currentFontWeightAdjustment = getFontWeightAdjustmentCompat(context.getResources().getConfiguration());
        LayoutDirection layoutDirection = FocusInteropUtils_androidKt.toLayoutDirection(context.getResources().getConfiguration().getLayoutDirection());
        this.layoutDirection$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(layoutDirection == null ? LayoutDirection.Ltr : layoutDirection, null, 2, null);
        this.hapticFeedBack = new PlatformHapticFeedback(this);
        this._inputModeManager = new InputModeManagerImpl(isInTouchMode() ? InputMode.Companion.m1593getTouchaOaMEAU() : InputMode.Companion.m1592getKeyboardaOaMEAU(), new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$_inputModeManager$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m1930invokeiuPiT84(((InputMode) obj).m1591unboximpl());
            }

            /* JADX INFO: renamed from: invoke-iuPiT84, reason: not valid java name */
            public final Boolean m1930invokeiuPiT84(int i) {
                boolean zRequestFocusFromTouch;
                InputMode.Companion companion3 = InputMode.Companion;
                if (InputMode.m1588equalsimpl0(i, companion3.m1593getTouchaOaMEAU())) {
                    zRequestFocusFromTouch = this.this$0.isInTouchMode();
                } else {
                    zRequestFocusFromTouch = InputMode.m1588equalsimpl0(i, companion3.m1592getKeyboardaOaMEAU()) ? this.this$0.isInTouchMode() ? this.this$0.requestFocusFromTouch() : true : false;
                }
                return Boolean.valueOf(zRequestFocusFromTouch);
            }
        }, b2 == true ? 1 : 0);
        this.modifierLocalManager = new ModifierLocalManager(this);
        this.textToolbar = new AndroidTextToolbar(this);
        this.layerCache = new WeakCache();
        this.endApplyChangesListeners = new MutableVector(new Function0[16], 0);
        this.resendMotionEventRunnable = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                this.this$0.removeCallbacks(this);
                MotionEvent motionEvent = this.this$0.previousMotionEvent;
                if (motionEvent != null) {
                    boolean z = motionEvent.getToolType(0) == 3;
                    int actionMasked = motionEvent.getActionMasked();
                    if (z) {
                        if (actionMasked == 10 || actionMasked == 1) {
                            return;
                        }
                    } else if (actionMasked == 1) {
                        return;
                    }
                    int i = 7;
                    if (actionMasked != 7 && actionMasked != 9) {
                        i = 2;
                    }
                    AndroidComposeView androidComposeView = this.this$0;
                    androidComposeView.sendSimulatedEvent(motionEvent, i, androidComposeView.relayoutTime, false);
                }
            }
        };
        this.sendHoverExitEvent = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeView.sendHoverExitEvent$lambda$8(this.f$0);
            }
        };
        this.resendMotionEventOnLayout = new Function0() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventOnLayout$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m1936invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1936invoke() {
                MotionEvent motionEvent = this.this$0.previousMotionEvent;
                if (motionEvent != null) {
                    int actionMasked = motionEvent.getActionMasked();
                    if (actionMasked == 7 || actionMasked == 9) {
                        this.this$0.relayoutTime = SystemClock.uptimeMillis();
                        AndroidComposeView androidComposeView = this.this$0;
                        androidComposeView.post(androidComposeView.resendMotionEventRunnable);
                    }
                }
            }
        };
        int i = Build.VERSION.SDK_INT;
        this.matrixToWindow = i < 29 ? new CalculateMatrixToWindowApi21(fArrM1356constructorimpl$default, b == true ? 1 : 0) : new CalculateMatrixToWindowApi29();
        addOnAttachStateChangeListener(this.contentCaptureManager);
        setWillNotDraw(false);
        setFocusable(true);
        if (i >= 26) {
            AndroidComposeViewVerificationHelperMethodsO.INSTANCE.focusable(this, 1, false);
        }
        setFocusableInTouchMode(true);
        setClipChildren(false);
        ViewCompat.setAccessibilityDelegate(this, androidComposeViewAccessibilityDelegateCompat);
        Function1 onViewCreatedCallback = ViewRootForTest.Companion.getOnViewCreatedCallback();
        if (onViewCreatedCallback != null) {
            onViewCreatedCallback.invoke(this);
        }
        setOnDragListener(dragAndDropModifierOnDragListener);
        getRoot().attach$ui_release(this);
        if (i >= 29) {
            AndroidComposeViewForceDarkModeQ.INSTANCE.disallowForceDark(this);
        }
        this.scrollCapture = i >= 31 ? new ScrollCapture() : null;
        this.pointerIconService = new PointerIconService() { // from class: androidx.compose.ui.platform.AndroidComposeView$pointerIconService$1
            private PointerIcon currentIcon = PointerIcon.Companion.getDefault();

            @Override // androidx.compose.ui.input.pointer.PointerIconService
            public void setIcon(PointerIcon pointerIcon) {
                if (pointerIcon == null) {
                    pointerIcon = PointerIcon.Companion.getDefault();
                }
                this.currentIcon = pointerIcon;
                if (Build.VERSION.SDK_INT >= 24) {
                    AndroidComposeViewVerificationHelperMethodsN.INSTANCE.setPointerIcon(this.this$0, pointerIcon);
                }
            }
        };
    }

    @Override // androidx.compose.ui.node.Owner
    public LayoutNodeDrawScope getSharedDrawScope() {
        return this.sharedDrawScope;
    }

    private void setDensity(Density density) {
        this.density$delegate.setValue(density);
    }

    @Override // androidx.compose.ui.node.Owner
    public Density getDensity() {
        return (Density) this.density$delegate.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public FocusOwner getFocusOwner() {
        return this.focusOwner;
    }

    @Override // androidx.compose.ui.node.Owner
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v5 */
    public void setCoroutineContext(CoroutineContext coroutineContext) {
        this.coroutineContext = coroutineContext;
        DelegatableNode head$ui_release = getRoot().getNodes$ui_release().getHead$ui_release();
        if (head$ui_release instanceof SuspendingPointerInputModifierNode) {
            ((SuspendingPointerInputModifierNode) head$ui_release).resetPointerInputHandler();
        }
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(16);
        if (!head$ui_release.getNode().isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtree called on an unattached node");
        }
        Modifier.Node child$ui_release = head$ui_release.getNode().getChild$ui_release();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(head$ui_release);
        NestedVectorStack nestedVectorStack = new NestedVectorStack();
        while (layoutNodeRequireLayoutNode != null) {
            if (child$ui_release == null) {
                child$ui_release = layoutNodeRequireLayoutNode.getNodes$ui_release().getHead$ui_release();
            }
            if ((child$ui_release.getAggregateChildKindSet$ui_release() & iM1895constructorimpl) != 0) {
                while (child$ui_release != null) {
                    if ((child$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                        ?? Pop = child$ui_release;
                        ?? mutableVector = 0;
                        while (Pop != 0) {
                            if (!(Pop instanceof PointerInputModifierNode)) {
                                if ((Pop.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (Pop instanceof DelegatingNode)) {
                                    Modifier.Node delegate$ui_release = ((DelegatingNode) Pop).getDelegate$ui_release();
                                    int i = 0;
                                    Pop = Pop;
                                    mutableVector = mutableVector;
                                    while (delegate$ui_release != null) {
                                        if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                                            i++;
                                            mutableVector = mutableVector;
                                            if (i == 1) {
                                                Pop = delegate$ui_release;
                                            } else {
                                                if (mutableVector == 0) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (Pop != 0) {
                                                    mutableVector.add(Pop);
                                                    Pop = 0;
                                                }
                                                mutableVector.add(delegate$ui_release);
                                            }
                                        }
                                        delegate$ui_release = delegate$ui_release.getChild$ui_release();
                                        Pop = Pop;
                                        mutableVector = mutableVector;
                                    }
                                    if (i == 1) {
                                    }
                                }
                            } else {
                                PointerInputModifierNode pointerInputModifierNode = (PointerInputModifierNode) Pop;
                                if (pointerInputModifierNode instanceof SuspendingPointerInputModifierNode) {
                                    ((SuspendingPointerInputModifierNode) pointerInputModifierNode).resetPointerInputHandler();
                                }
                            }
                            Pop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    child$ui_release = child$ui_release.getChild$ui_release();
                }
            }
            nestedVectorStack.push(layoutNodeRequireLayoutNode.get_children$ui_release());
            layoutNodeRequireLayoutNode = nestedVectorStack.isNotEmpty() ? (LayoutNode) nestedVectorStack.pop() : null;
            child$ui_release = null;
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public DragAndDropManager getDragAndDropManager() {
        return this.dragAndDropManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public WindowInfo getWindowInfo() {
        return this._windowInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onMoveFocusInChildren-3ESFkO8, reason: not valid java name */
    public final boolean m1924onMoveFocusInChildren3ESFkO8(int i) {
        View viewFindNextFocusFromRect;
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (FocusDirection.m1091equalsimpl0(i, companion.m1096getEnterdhqQ8s()) || FocusDirection.m1091equalsimpl0(i, companion.m1097getExitdhqQ8s())) {
            return false;
        }
        Integer numM1103toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m1103toAndroidFocusDirection3ESFkO8(i);
        if (numM1103toAndroidFocusDirection3ESFkO8 == null) {
            throw new IllegalStateException("Invalid focus direction");
        }
        int iIntValue = numM1103toAndroidFocusDirection3ESFkO8.intValue();
        Rect rectOnFetchFocusRect = onFetchFocusRect();
        android.graphics.Rect androidRect = rectOnFetchFocusRect != null ? RectHelper_androidKt.toAndroidRect(rectOnFetchFocusRect) : null;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (androidRect == null) {
            viewFindNextFocusFromRect = focusFinder.findNextFocus(this, findFocus(), iIntValue);
        } else {
            viewFindNextFocusFromRect = focusFinder.findNextFocusFromRect(this, androidRect, iIntValue);
        }
        if (viewFindNextFocusFromRect != null) {
            return FocusInteropUtils_androidKt.requestInteropFocus(viewFindNextFocusFromRect, Integer.valueOf(iIntValue), androidRect);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect onFetchFocusRect() {
        if (isFocused()) {
            return getFocusOwner().getFocusRect();
        }
        View viewFindFocus = findFocus();
        if (viewFindFocus != null) {
            return FocusInteropUtils_androidKt.calculateBoundingRect(viewFindFocus);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final View findNextNonChildView(int i) {
        View viewFindNextFocus = this;
        while (viewFindNextFocus != null) {
            FocusFinder focusFinder = FocusFinder.getInstance();
            View rootView = getRootView();
            Intrinsics.checkNotNull(rootView, "null cannot be cast to non-null type android.view.ViewGroup");
            viewFindNextFocus = focusFinder.findNextFocus((ViewGroup) rootView, viewFindNextFocus, i);
            if (viewFindNextFocus != null && !AndroidComposeView_androidKt.containsDescendant(this, viewFindNextFocus)) {
                return viewFindNextFocus;
            }
        }
        return null;
    }

    @Override // androidx.compose.ui.node.Owner
    public LayoutNode getRoot() {
        return this.root;
    }

    public RootForTest getRootForTest() {
        return this.rootForTest;
    }

    public SemanticsOwner getSemanticsOwner() {
        return this.semanticsOwner;
    }

    public final AndroidContentCaptureManager getContentCaptureManager$ui_release() {
        return this.contentCaptureManager;
    }

    public final void setContentCaptureManager$ui_release(AndroidContentCaptureManager androidContentCaptureManager) {
        this.contentCaptureManager = androidContentCaptureManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidAccessibilityManager getAccessibilityManager() {
        return this.accessibilityManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public GraphicsContext getGraphicsContext() {
        return this.graphicsContext;
    }

    @Override // androidx.compose.ui.node.Owner
    public AutofillTree getAutofillTree() {
        return this.autofillTree;
    }

    public final Function1 getConfigurationChangeObserver() {
        return this.configurationChangeObserver;
    }

    public final void setConfigurationChangeObserver(Function1 function1) {
        this.configurationChangeObserver = function1;
    }

    @Override // androidx.compose.ui.node.Owner
    public Autofill getAutofill() {
        return this._autofill;
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public OwnerSnapshotObserver getSnapshotObserver() {
        return this.snapshotObserver;
    }

    @Override // androidx.compose.ui.node.Owner
    public boolean getShowLayoutBounds() {
        return this.showLayoutBounds;
    }

    @Override // androidx.compose.ui.node.Owner
    public void setShowLayoutBounds(boolean z) {
        this.showLayoutBounds = z;
    }

    public final AndroidViewsHandler getAndroidViewsHandler$ui_release() {
        if (this._androidViewsHandler == null) {
            AndroidViewsHandler androidViewsHandler = new AndroidViewsHandler(getContext());
            this._androidViewsHandler = androidViewsHandler;
            addView(androidViewsHandler);
            requestLayout();
        }
        AndroidViewsHandler androidViewsHandler2 = this._androidViewsHandler;
        Intrinsics.checkNotNull(androidViewsHandler2);
        return androidViewsHandler2;
    }

    public long getMeasureIteration() {
        return this.measureAndLayoutDelegate.getMeasureIteration();
    }

    @Override // androidx.compose.ui.node.Owner
    public ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    public boolean getHasPendingMeasureOrLayout() {
        return this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout();
    }

    public final long getLastMatrixRecalculationAnimationTime$ui_release() {
        return this.lastMatrixRecalculationAnimationTime;
    }

    public final void setLastMatrixRecalculationAnimationTime$ui_release(long j) {
        this.lastMatrixRecalculationAnimationTime = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ViewTreeOwners get_viewTreeOwners() {
        return (ViewTreeOwners) this._viewTreeOwners$delegate.getValue();
    }

    private final void set_viewTreeOwners(ViewTreeOwners viewTreeOwners) {
        this._viewTreeOwners$delegate.setValue(viewTreeOwners);
    }

    public final ViewTreeOwners getViewTreeOwners() {
        return (ViewTreeOwners) this.viewTreeOwners$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void touchModeChangeListener$lambda$6(AndroidComposeView androidComposeView, boolean z) {
        androidComposeView._inputModeManager.m1595setInputModeiuPiT84(z ? InputMode.Companion.m1593getTouchaOaMEAU() : InputMode.Companion.m1592getKeyboardaOaMEAU());
    }

    @Override // androidx.compose.ui.node.Owner
    public TextInputService getTextInputService() {
        return this.textInputService;
    }

    @Override // androidx.compose.ui.node.Owner
    public SoftwareKeyboardController getSoftwareKeyboardController() {
        return this.softwareKeyboardController;
    }

    @Override // androidx.compose.ui.node.Owner
    public Placeable.PlacementScope getPlacementScope() {
        return PlaceableKt.PlacementScope(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.ui.node.Owner
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object textInputSession(kotlin.jvm.functions.Function2 r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.compose.ui.platform.AndroidComposeView.C01561
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.ui.platform.AndroidComposeView$textInputSession$1 r0 = (androidx.compose.ui.platform.AndroidComposeView.C01561) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.platform.AndroidComposeView$textInputSession$1 r0 = new androidx.compose.ui.platform.AndroidComposeView$textInputSession$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L44
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            java.util.concurrent.atomic.AtomicReference r6 = r4.textInputSessionMutex
            androidx.compose.ui.platform.AndroidComposeView$textInputSession$2 r2 = new androidx.compose.ui.platform.AndroidComposeView$textInputSession$2
            r2.<init>()
            r0.label = r3
            java.lang.Object r5 = androidx.compose.ui.SessionMutex.m1070withSessionCancellingPreviousimpl(r6, r2, r5, r0)
            if (r5 != r1) goto L44
            return r1
        L44:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.textInputSession(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.compose.ui.node.Owner
    public Font$ResourceLoader getFontLoader() {
        return this.fontLoader;
    }

    private void setFontFamilyResolver(FontFamily.Resolver resolver) {
        this.fontFamilyResolver$delegate.setValue(resolver);
    }

    @Override // androidx.compose.ui.node.Owner
    public FontFamily.Resolver getFontFamilyResolver() {
        return (FontFamily.Resolver) this.fontFamilyResolver$delegate.getValue();
    }

    private final int getFontWeightAdjustmentCompat(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 31) {
            return configuration.fontWeightAdjustment;
        }
        return 0;
    }

    private void setLayoutDirection(LayoutDirection layoutDirection) {
        this.layoutDirection$delegate.setValue(layoutDirection);
    }

    @Override // android.view.View, android.view.ViewParent, androidx.compose.ui.node.Owner
    public LayoutDirection getLayoutDirection() {
        return (LayoutDirection) this.layoutDirection$delegate.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    @Override // androidx.compose.ui.node.Owner
    public InputModeManager getInputModeManager() {
        return this._inputModeManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public ModifierLocalManager getModifierLocalManager() {
        return this.modifierLocalManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendHoverExitEvent$lambda$8(AndroidComposeView androidComposeView) {
        androidComposeView.hoverExitReceived = false;
        MotionEvent motionEvent = androidComposeView.previousMotionEvent;
        Intrinsics.checkNotNull(motionEvent);
        if (motionEvent.getActionMasked() != 10) {
            throw new IllegalStateException("The ACTION_HOVER_EXIT event was not cleared.");
        }
        androidComposeView.m1927sendMotionEvent8iAsVTc(motionEvent);
    }

    @Override // android.view.View
    public void getFocusedRect(android.graphics.Rect rect) {
        Unit unit;
        Rect rectOnFetchFocusRect = onFetchFocusRect();
        if (rectOnFetchFocusRect != null) {
            rect.left = Math.round(rectOnFetchFocusRect.getLeft());
            rect.top = Math.round(rectOnFetchFocusRect.getTop());
            rect.right = Math.round(rectOnFetchFocusRect.getRight());
            rect.bottom = Math.round(rectOnFetchFocusRect.getBottom());
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            super.getFocusedRect(rect);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        if (Build.VERSION.SDK_INT < 28) {
            AndroidComposeViewAssistHelperMethodsO.INSTANCE.setClassName(viewStructure, getView());
        } else {
            super.dispatchProvideStructure(viewStructure);
        }
    }

    public final boolean getScrollCaptureInProgress$ui_release() {
        ScrollCapture scrollCapture;
        if (Build.VERSION.SDK_INT < 31 || (scrollCapture = this.scrollCapture) == null) {
            return false;
        }
        return scrollCapture.getScrollCaptureInProgress();
    }

    @Override // android.view.View
    public void onScrollCaptureSearch(android.graphics.Rect rect, Point point, Consumer consumer) {
        ScrollCapture scrollCapture;
        if (Build.VERSION.SDK_INT < 31 || (scrollCapture = this.scrollCapture) == null) {
            return;
        }
        scrollCapture.onScrollCaptureSearch(this, getSemanticsOwner(), getCoroutineContext(), consumer);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onResume(LifecycleOwner lifecycleOwner) {
        setShowLayoutBounds(Companion.getIsShowingLayoutBounds());
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i) {
        if (view != null) {
            Rect rectCalculateBoundingRect = FocusInteropUtils_androidKt.calculateBoundingRect(view);
            FocusDirection focusDirection = FocusInteropUtils_androidKt.toFocusDirection(i);
            if (Intrinsics.areEqual(getFocusOwner().mo1109focusSearchULY8qGw(focusDirection != null ? focusDirection.m1094unboximpl() : FocusDirection.Companion.m1095getDowndhqQ8s(), rectCalculateBoundingRect, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView.focusSearch.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(FocusTargetNode focusTargetNode) {
                    return Boolean.TRUE;
                }
            }), Boolean.TRUE)) {
                return this;
            }
        }
        return super.focusSearch(view, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i, android.graphics.Rect rect) {
        if (isFocused()) {
            return true;
        }
        if (getFocusOwner().getRootState().getHasFocus()) {
            return super.requestFocus(i, rect);
        }
        FocusDirection focusDirection = FocusInteropUtils_androidKt.toFocusDirection(i);
        final int iM1094unboximpl = focusDirection != null ? focusDirection.m1094unboximpl() : FocusDirection.Companion.m1096getEnterdhqQ8s();
        Boolean boolMo1109focusSearchULY8qGw = getFocusOwner().mo1109focusSearchULY8qGw(iM1094unboximpl, rect != null ? RectHelper_androidKt.toComposeRect(rect) : null, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView.requestFocus.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                Boolean boolM1126requestFocusMxy_nc0 = FocusTransactionsKt.m1126requestFocusMxy_nc0(focusTargetNode, iM1094unboximpl);
                return Boolean.valueOf(boolM1126requestFocusMxy_nc0 != null ? boolM1126requestFocusMxy_nc0.booleanValue() : false);
            }
        });
        if (boolMo1109focusSearchULY8qGw != null) {
            return boolMo1109focusSearchULY8qGw.booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onRequestFocusForOwner-7o62pno, reason: not valid java name */
    public final boolean m1925onRequestFocusForOwner7o62pno(FocusDirection focusDirection, Rect rect) {
        Integer numM1103toAndroidFocusDirection3ESFkO8;
        if (isFocused() || hasFocus()) {
            return true;
        }
        return super.requestFocus((focusDirection == null || (numM1103toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m1103toAndroidFocusDirection3ESFkO8(focusDirection.m1094unboximpl())) == null) ? 130 : numM1103toAndroidFocusDirection3ESFkO8.intValue(), rect != null ? RectHelper_androidKt.toAndroidRect(rect) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClearFocusForOwner() {
        if (isFocused() || hasFocus()) {
            super.clearFocus();
        }
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, android.graphics.Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z || hasFocus()) {
            return;
        }
        getFocusOwner().releaseFocus();
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        boolean isShowingLayoutBounds;
        this._windowInfo.setWindowFocused(z);
        this.keyboardModifiersRequireUpdate = true;
        super.onWindowFocusChanged(z);
        if (!z || getShowLayoutBounds() == (isShowingLayoutBounds = Companion.getIsShowingLayoutBounds())) {
            return;
        }
        setShowLayoutBounds(isShowingLayoutBounds);
        invalidateDescendants();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(final android.view.KeyEvent keyEvent) {
        if (isFocused()) {
            this._windowInfo.m1994setKeyboardModifiers5xRPYO0(PointerKeyboardModifiers.m1695constructorimpl(keyEvent.getMetaState()));
            return FocusOwner.CC.m1111dispatchKeyEventYhN2O0w$default(getFocusOwner(), KeyEvent.m1616constructorimpl(keyEvent), null, 2, null) || super.dispatchKeyEvent(keyEvent);
        }
        return getFocusOwner().mo1108dispatchKeyEventYhN2O0w(KeyEvent.m1616constructorimpl(keyEvent), new Function0() { // from class: androidx.compose.ui.platform.AndroidComposeView.dispatchKeyEvent.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(AndroidComposeView.super.dispatchKeyEvent(keyEvent));
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEventPreIme(android.view.KeyEvent keyEvent) {
        return (isFocused() && getFocusOwner().mo1107dispatchInterceptedSoftKeyboardEventZmokQxo(KeyEvent.m1616constructorimpl(keyEvent))) || super.dispatchKeyEventPreIme(keyEvent);
    }

    public void setAccessibilityEventBatchIntervalMillis(long j) {
        this.composeAccessibilityDelegate.setSendRecurringAccessibilityEventsIntervalMillis$ui_release(j);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onDetach(LayoutNode layoutNode) {
        this.measureAndLayoutDelegate.onNodeDetached(layoutNode);
        requestClearInvalidObservations();
    }

    public final void requestClearInvalidObservations() {
        this.observationClearRequested = true;
    }

    @Override // androidx.compose.ui.node.Owner
    public void onEndApplyChanges() {
        if (this.observationClearRequested) {
            getSnapshotObserver().clearInvalidObservations$ui_release();
            this.observationClearRequested = false;
        }
        AndroidViewsHandler androidViewsHandler = this._androidViewsHandler;
        if (androidViewsHandler != null) {
            clearChildInvalidObservations(androidViewsHandler);
        }
        while (this.endApplyChangesListeners.isNotEmpty()) {
            int size = this.endApplyChangesListeners.getSize();
            for (int i = 0; i < size; i++) {
                Function0 function0 = (Function0) this.endApplyChangesListeners.getContent()[i];
                this.endApplyChangesListeners.set(i, null);
                if (function0 != null) {
                    function0.invoke();
                }
            }
            this.endApplyChangesListeners.removeRange(0, size);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void registerOnEndApplyChangesListener(Function0 function0) {
        if (this.endApplyChangesListeners.contains(function0)) {
            return;
        }
        this.endApplyChangesListeners.add(function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startDrag-12SF9DM, reason: not valid java name */
    public final boolean m1928startDrag12SF9DM(DragAndDropTransferData dragAndDropTransferData, long j, Function1 function1) {
        Resources resources = getContext().getResources();
        ComposeDragShadowBuilder composeDragShadowBuilder = new ComposeDragShadowBuilder(DensityKt.Density(resources.getDisplayMetrics().density, resources.getConfiguration().fontScale), j, function1, null);
        if (Build.VERSION.SDK_INT >= 24) {
            return AndroidComposeViewStartDragAndDropN.INSTANCE.startDragAndDrop(this, dragAndDropTransferData, composeDragShadowBuilder);
        }
        throw null;
    }

    private final void clearChildInvalidObservations(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof AndroidComposeView) {
                ((AndroidComposeView) childAt).onEndApplyChanges();
            } else if (childAt instanceof ViewGroup) {
                clearChildInvalidObservations((ViewGroup) childAt);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        addView(view, -1);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
        Intrinsics.checkNotNull(view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        }
        addView(view, i, layoutParams);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParamsGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        layoutParamsGenerateDefaultLayoutParams.width = i;
        layoutParamsGenerateDefaultLayoutParams.height = i2;
        Unit unit = Unit.INSTANCE;
        addView(view, -1, layoutParamsGenerateDefaultLayoutParams);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addView(view, -1, layoutParams);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        addViewInLayout(view, i, layoutParams, true);
    }

    static /* synthetic */ void scheduleMeasureAndLayout$default(AndroidComposeView androidComposeView, LayoutNode layoutNode, int i, Object obj) {
        if ((i & 1) != 0) {
            layoutNode = null;
        }
        androidComposeView.scheduleMeasureAndLayout(layoutNode);
    }

    private final void scheduleMeasureAndLayout(LayoutNode layoutNode) {
        if (isLayoutRequested() || !isAttachedToWindow()) {
            return;
        }
        if (layoutNode != null) {
            while (layoutNode != null && layoutNode.getMeasuredByParent$ui_release() == LayoutNode.UsageByParent.InMeasureBlock && childSizeCanAffectParentSize(layoutNode)) {
                layoutNode = layoutNode.getParent$ui_release();
            }
            if (layoutNode == getRoot()) {
                requestLayout();
                return;
            }
        }
        if (getWidth() == 0 || getHeight() == 0) {
            requestLayout();
        } else {
            invalidate();
        }
    }

    private final boolean childSizeCanAffectParentSize(LayoutNode layoutNode) {
        LayoutNode parent$ui_release;
        return this.wasMeasuredWithMultipleConstraints || !((parent$ui_release = layoutNode.getParent$ui_release()) == null || parent$ui_release.getHasFixedInnerContentConstraints$ui_release());
    }

    @Override // androidx.compose.ui.node.Owner
    public void measureAndLayout(boolean z) {
        Function0 function0;
        if (this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout() || this.measureAndLayoutDelegate.getHasPendingOnPositionedCallbacks()) {
            Trace.beginSection("AndroidOwner:measureAndLayout");
            if (z) {
                try {
                    function0 = this.resendMotionEventOnLayout;
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            } else {
                function0 = null;
            }
            if (this.measureAndLayoutDelegate.measureAndLayout(function0)) {
                requestLayout();
            }
            MeasureAndLayoutDelegate.dispatchOnPositionedCallbacks$default(this.measureAndLayoutDelegate, false, 1, null);
            dispatchPendingInteropLayoutCallbacks();
            Unit unit = Unit.INSTANCE;
            Trace.endSection();
        }
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: measureAndLayout-0kLqBqw */
    public void mo1905measureAndLayout0kLqBqw(LayoutNode layoutNode, long j) {
        Trace.beginSection("AndroidOwner:measureAndLayout");
        try {
            this.measureAndLayoutDelegate.m1852measureAndLayout0kLqBqw(layoutNode, j);
            if (!this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout()) {
                MeasureAndLayoutDelegate.dispatchOnPositionedCallbacks$default(this.measureAndLayoutDelegate, false, 1, null);
                dispatchPendingInteropLayoutCallbacks();
            }
            Unit unit = Unit.INSTANCE;
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    private final void dispatchPendingInteropLayoutCallbacks() {
        if (this.isPendingInteropViewLayoutChangeDispatch) {
            getViewTreeObserver().dispatchOnGlobalLayout();
            this.isPendingInteropViewLayoutChangeDispatch = false;
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void forceMeasureTheSubtree(LayoutNode layoutNode, boolean z) {
        this.measureAndLayoutDelegate.forceMeasureTheSubtree(layoutNode, z);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onRequestMeasure(LayoutNode layoutNode, boolean z, boolean z2, boolean z3) {
        if (z) {
            if (this.measureAndLayoutDelegate.requestLookaheadRemeasure(layoutNode, z2) && z3) {
                scheduleMeasureAndLayout(layoutNode);
                return;
            }
            return;
        }
        if (this.measureAndLayoutDelegate.requestRemeasure(layoutNode, z2) && z3) {
            scheduleMeasureAndLayout(layoutNode);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void onRequestRelayout(LayoutNode layoutNode, boolean z, boolean z2) {
        if (z) {
            if (this.measureAndLayoutDelegate.requestLookaheadRelayout(layoutNode, z2)) {
                scheduleMeasureAndLayout$default(this, null, 1, null);
            }
        } else if (this.measureAndLayoutDelegate.requestRelayout(layoutNode, z2)) {
            scheduleMeasureAndLayout$default(this, null, 1, null);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void requestOnPositionedCallback(LayoutNode layoutNode) {
        this.measureAndLayoutDelegate.requestOnPositionedCallback(layoutNode);
        scheduleMeasureAndLayout$default(this, null, 1, null);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        Trace.beginSection("AndroidOwner:onMeasure");
        try {
            if (!isAttachedToWindow()) {
                invalidateLayoutNodeMeasurement(getRoot());
            }
            long jM1922convertMeasureSpecI7RO_PI = m1922convertMeasureSpecI7RO_PI(i);
            int iM2693constructorimpl = (int) ULong.m2693constructorimpl(jM1922convertMeasureSpecI7RO_PI >>> 32);
            int iM2693constructorimpl2 = (int) ULong.m2693constructorimpl(jM1922convertMeasureSpecI7RO_PI & BodyPartID.bodyIdMax);
            long jM1922convertMeasureSpecI7RO_PI2 = m1922convertMeasureSpecI7RO_PI(i2);
            long jM2396fitPrioritizingHeightZbe2FdA = Constraints.Companion.m2396fitPrioritizingHeightZbe2FdA(iM2693constructorimpl, iM2693constructorimpl2, (int) ULong.m2693constructorimpl(jM1922convertMeasureSpecI7RO_PI2 >>> 32), (int) ULong.m2693constructorimpl(BodyPartID.bodyIdMax & jM1922convertMeasureSpecI7RO_PI2));
            Constraints constraints = this.onMeasureConstraints;
            boolean zM2383equalsimpl0 = false;
            if (constraints == null) {
                this.onMeasureConstraints = Constraints.m2378boximpl(jM2396fitPrioritizingHeightZbe2FdA);
                this.wasMeasuredWithMultipleConstraints = false;
            } else {
                if (constraints != null) {
                    zM2383equalsimpl0 = Constraints.m2383equalsimpl0(constraints.m2395unboximpl(), jM2396fitPrioritizingHeightZbe2FdA);
                }
                if (!zM2383equalsimpl0) {
                    this.wasMeasuredWithMultipleConstraints = true;
                }
            }
            this.measureAndLayoutDelegate.m1853updateRootConstraintsBRTryo0(jM2396fitPrioritizingHeightZbe2FdA);
            this.measureAndLayoutDelegate.measureOnly();
            setMeasuredDimension(getRoot().getWidth(), getRoot().getHeight());
            if (this._androidViewsHandler != null) {
                getAndroidViewsHandler$ui_release().measure(View.MeasureSpec.makeMeasureSpec(getRoot().getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getRoot().getHeight(), 1073741824));
            }
            Unit unit = Unit.INSTANCE;
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    /* JADX INFO: renamed from: pack-ZIaKswc, reason: not valid java name */
    private final long m1926packZIaKswc(int i, int i2) {
        return ULong.m2693constructorimpl(ULong.m2693constructorimpl(i2) | ULong.m2693constructorimpl(ULong.m2693constructorimpl(i) << 32));
    }

    /* JADX INFO: renamed from: convertMeasureSpec-I7RO_PI, reason: not valid java name */
    private final long m1922convertMeasureSpecI7RO_PI(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            return m1926packZIaKswc(0, size);
        }
        if (mode == 0) {
            return m1926packZIaKswc(0, Integer.MAX_VALUE);
        }
        if (mode == 1073741824) {
            return m1926packZIaKswc(size, size);
        }
        throw new IllegalStateException();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.measureAndLayoutDelegate.measureAndLayout(this.resendMotionEventOnLayout);
        this.onMeasureConstraints = null;
        updatePositionCacheAndDispatch();
        if (this._androidViewsHandler != null) {
            getAndroidViewsHandler$ui_release().layout(0, 0, i3 - i, i4 - i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePositionCacheAndDispatch() {
        getLocationOnScreen(this.tmpPositionArray);
        long j = this.globalPosition;
        int iM2457getXimpl = IntOffset.m2457getXimpl(j);
        int iM2458getYimpl = IntOffset.m2458getYimpl(j);
        int[] iArr = this.tmpPositionArray;
        boolean z = false;
        int i = iArr[0];
        if (iM2457getXimpl != i || iM2458getYimpl != iArr[1]) {
            this.globalPosition = IntOffsetKt.IntOffset(i, iArr[1]);
            if (iM2457getXimpl != Integer.MAX_VALUE && iM2458getYimpl != Integer.MAX_VALUE) {
                getRoot().getLayoutDelegate$ui_release().getMeasurePassDelegate$ui_release().notifyChildrenUsingCoordinatesWhilePlacing();
                z = true;
            }
        }
        this.measureAndLayoutDelegate.dispatchOnPositionedCallbacks(z);
    }

    @Override // androidx.compose.ui.node.Owner
    public OwnedLayer createLayer(Function2 function2, Function0 function0, GraphicsLayer graphicsLayer) {
        DrawChildContainer viewLayerContainer;
        if (graphicsLayer != null) {
            return new GraphicsLayerOwnerLayer(graphicsLayer, null, this, function2, function0);
        }
        OwnedLayer ownedLayer = (OwnedLayer) this.layerCache.pop();
        if (ownedLayer != null) {
            ownedLayer.reuseLayer(function2, function0);
            return ownedLayer;
        }
        if (isHardwareAccelerated() && Build.VERSION.SDK_INT != 28) {
            return new GraphicsLayerOwnerLayer(getGraphicsContext().createGraphicsLayer(), getGraphicsContext(), this, function2, function0);
        }
        if (isHardwareAccelerated() && this.isRenderNodeCompatible) {
            try {
                return new RenderNodeLayer(this, function2, function0);
            } catch (Throwable unused) {
                this.isRenderNodeCompatible = false;
            }
        }
        if (this.viewLayersContainer == null) {
            ViewLayer.Companion companion = ViewLayer.Companion;
            if (!companion.getHasRetrievedMethod()) {
                companion.updateDisplayList(new View(getContext()));
            }
            if (companion.getShouldUseDispatchDraw()) {
                viewLayerContainer = new DrawChildContainer(getContext());
            } else {
                viewLayerContainer = new ViewLayerContainer(getContext());
            }
            this.viewLayersContainer = viewLayerContainer;
            addView(viewLayerContainer);
        }
        DrawChildContainer drawChildContainer = this.viewLayersContainer;
        Intrinsics.checkNotNull(drawChildContainer);
        return new ViewLayer(this, drawChildContainer, function2, function0);
    }

    public final boolean recycle$ui_release(OwnedLayer ownedLayer) {
        if (this.viewLayersContainer != null) {
            ViewLayer.Companion.getShouldUseDispatchDraw();
        }
        this.layerCache.push(ownedLayer);
        return true;
    }

    @Override // androidx.compose.ui.node.Owner
    public void onSemanticsChange() {
        this.composeAccessibilityDelegate.onSemanticsChange$ui_release();
        this.contentCaptureManager.onSemanticsChange$ui_release();
    }

    @Override // androidx.compose.ui.node.Owner
    public void onLayoutChange(LayoutNode layoutNode) {
        this.composeAccessibilityDelegate.onLayoutChange$ui_release(layoutNode);
        this.contentCaptureManager.onLayoutChange$ui_release(layoutNode);
    }

    /* JADX INFO: renamed from: getFocusDirection-P8AzH3I, reason: not valid java name */
    public FocusDirection m1929getFocusDirectionP8AzH3I(android.view.KeyEvent keyEvent) {
        long jM1626getKeyZmokQxo = KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent);
        Key.Companion companion = Key.Companion;
        if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1614getTabEK5gGoQ())) {
            return FocusDirection.m1088boximpl(KeyEvent_androidKt.m1631isShiftPressedZmokQxo(keyEvent) ? FocusDirection.Companion.m1100getPreviousdhqQ8s() : FocusDirection.Companion.m1099getNextdhqQ8s());
        }
        if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1607getDirectionRightEK5gGoQ())) {
            return FocusDirection.m1088boximpl(FocusDirection.Companion.m1101getRightdhqQ8s());
        }
        if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1606getDirectionLeftEK5gGoQ())) {
            return FocusDirection.m1088boximpl(FocusDirection.Companion.m1098getLeftdhqQ8s());
        }
        if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1608getDirectionUpEK5gGoQ()) ? true : Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1613getPageUpEK5gGoQ())) {
            return FocusDirection.m1088boximpl(FocusDirection.Companion.m1102getUpdhqQ8s());
        }
        if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1605getDirectionDownEK5gGoQ()) ? true : Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1612getPageDownEK5gGoQ())) {
            return FocusDirection.m1088boximpl(FocusDirection.Companion.m1095getDowndhqQ8s());
        }
        if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1604getDirectionCenterEK5gGoQ()) ? true : Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1609getEnterEK5gGoQ()) ? true : Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1611getNumPadEnterEK5gGoQ())) {
            return FocusDirection.m1088boximpl(FocusDirection.Companion.m1096getEnterdhqQ8s());
        }
        if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1603getBackEK5gGoQ()) ? true : Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1610getEscapeEK5gGoQ())) {
            return FocusDirection.m1088boximpl(FocusDirection.Companion.m1097getExitdhqQ8s());
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (!isAttachedToWindow()) {
            invalidateLayers(getRoot());
        }
        Owner.CC.measureAndLayout$default(this, false, 1, null);
        Snapshot.Companion.sendApplyNotifications();
        this.isDrawingContent = true;
        CanvasHolder canvasHolder = this.canvasHolder;
        Canvas internalCanvas = canvasHolder.getAndroidCanvas().getInternalCanvas();
        canvasHolder.getAndroidCanvas().setInternalCanvas(canvas);
        getRoot().draw$ui_release(canvasHolder.getAndroidCanvas(), null);
        canvasHolder.getAndroidCanvas().setInternalCanvas(internalCanvas);
        if (!this.dirtyLayers.isEmpty()) {
            int size = this.dirtyLayers.size();
            for (int i = 0; i < size; i++) {
                ((OwnedLayer) this.dirtyLayers.get(i)).updateDisplayList();
            }
        }
        if (ViewLayer.Companion.getShouldUseDispatchDraw()) {
            int iSave = canvas.save();
            canvas.clipRect(0.0f, 0.0f, 0.0f, 0.0f);
            super.dispatchDraw(canvas);
            canvas.restoreToCount(iSave);
        }
        this.dirtyLayers.clear();
        this.isDrawingContent = false;
        List list = this.postponedDirtyLayers;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            this.dirtyLayers.addAll(list);
            list.clear();
        }
    }

    public final void notifyLayerIsDirty$ui_release(OwnedLayer ownedLayer, boolean z) {
        if (!z) {
            if (this.isDrawingContent) {
                return;
            }
            this.dirtyLayers.remove(ownedLayer);
            List list = this.postponedDirtyLayers;
            if (list != null) {
                list.remove(ownedLayer);
                return;
            }
            return;
        }
        if (!this.isDrawingContent) {
            this.dirtyLayers.add(ownedLayer);
            return;
        }
        List arrayList = this.postponedDirtyLayers;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.postponedDirtyLayers = arrayList;
        }
        arrayList.add(ownedLayer);
    }

    public final void setOnViewTreeOwnersAvailable(Function1 function1) {
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null) {
            function1.invoke(viewTreeOwners);
        }
        if (isAttachedToWindow()) {
            return;
        }
        this.onViewTreeOwnersAvailable = function1;
    }

    public final Object boundsUpdatesContentCaptureEventLoop(Continuation continuation) throws Throwable {
        Object objBoundsUpdatesEventLoop$ui_release = this.contentCaptureManager.boundsUpdatesEventLoop$ui_release(continuation);
        return objBoundsUpdatesEventLoop$ui_release == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objBoundsUpdatesEventLoop$ui_release : Unit.INSTANCE;
    }

    public final Object boundsUpdatesAccessibilityEventLoop(Continuation continuation) {
        Object objBoundsUpdatesEventLoop$ui_release = this.composeAccessibilityDelegate.boundsUpdatesEventLoop$ui_release(continuation);
        return objBoundsUpdatesEventLoop$ui_release == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objBoundsUpdatesEventLoop$ui_release : Unit.INSTANCE;
    }

    private final void invalidateLayoutNodeMeasurement(LayoutNode layoutNode) {
        int i = 0;
        MeasureAndLayoutDelegate.requestRemeasure$default(this.measureAndLayoutDelegate, layoutNode, false, 2, null);
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            do {
                invalidateLayoutNodeMeasurement((LayoutNode) content[i]);
                i++;
            } while (i < size);
        }
    }

    private final void invalidateLayers(LayoutNode layoutNode) {
        layoutNode.invalidateLayers$ui_release();
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            do {
                invalidateLayers((LayoutNode) content[i]);
                i++;
            } while (i < size);
        }
    }

    public void invalidateDescendants() {
        invalidateLayers(getRoot());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycle;
        LifecycleOwner lifecycleOwner2;
        AndroidAutofill androidAutofill;
        super.onAttachedToWindow();
        this._windowInfo.setWindowFocused(hasWindowFocus());
        invalidateLayoutNodeMeasurement(getRoot());
        invalidateLayers(getRoot());
        getSnapshotObserver().startObserving$ui_release();
        if (autofillSupported() && (androidAutofill = this._autofill) != null) {
            AutofillCallback.INSTANCE.register(androidAutofill);
        }
        LifecycleOwner lifecycleOwner3 = ViewTreeLifecycleOwner.get(this);
        SavedStateRegistryOwner savedStateRegistryOwner = ViewTreeSavedStateRegistryOwner.get(this);
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        Lifecycle lifecycle2 = null;
        if (viewTreeOwners == null || (lifecycleOwner3 != null && savedStateRegistryOwner != null && (lifecycleOwner3 != viewTreeOwners.getLifecycleOwner() || savedStateRegistryOwner != viewTreeOwners.getLifecycleOwner()))) {
            if (lifecycleOwner3 == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagate ViewTreeLifecycleOwner!");
            }
            if (savedStateRegistryOwner == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagateViewTreeSavedStateRegistryOwner!");
            }
            if (viewTreeOwners != null && (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
                lifecycle.removeObserver(this);
            }
            lifecycleOwner3.getLifecycle().addObserver(this);
            ViewTreeOwners viewTreeOwners2 = new ViewTreeOwners(lifecycleOwner3, savedStateRegistryOwner);
            set_viewTreeOwners(viewTreeOwners2);
            Function1 function1 = this.onViewTreeOwnersAvailable;
            if (function1 != null) {
                function1.invoke(viewTreeOwners2);
            }
            this.onViewTreeOwnersAvailable = null;
        }
        this._inputModeManager.m1595setInputModeiuPiT84(isInTouchMode() ? InputMode.Companion.m1593getTouchaOaMEAU() : InputMode.Companion.m1592getKeyboardaOaMEAU());
        ViewTreeOwners viewTreeOwners3 = getViewTreeOwners();
        if (viewTreeOwners3 != null && (lifecycleOwner2 = viewTreeOwners3.getLifecycleOwner()) != null) {
            lifecycle2 = lifecycleOwner2.getLifecycle();
        }
        if (lifecycle2 == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("No lifecycle owner exists");
            throw new KotlinNothingValueException();
        }
        lifecycle2.addObserver(this);
        lifecycle2.addObserver(this.contentCaptureManager);
        getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
        getViewTreeObserver().addOnScrollChangedListener(this.scrollChangedListener);
        getViewTreeObserver().addOnTouchModeChangeListener(this.touchModeChangeListener);
        if (Build.VERSION.SDK_INT >= 31) {
            AndroidComposeViewTranslationCallbackS.INSTANCE.setViewTranslationCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        AndroidAutofill androidAutofill;
        LifecycleOwner lifecycleOwner;
        super.onDetachedFromWindow();
        getSnapshotObserver().stopObserving$ui_release();
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        Lifecycle lifecycle = (viewTreeOwners == null || (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) == null) ? null : lifecycleOwner.getLifecycle();
        if (lifecycle == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("No lifecycle owner exists");
            throw new KotlinNothingValueException();
        }
        lifecycle.removeObserver(this.contentCaptureManager);
        lifecycle.removeObserver(this);
        if (autofillSupported() && (androidAutofill = this._autofill) != null) {
            AutofillCallback.INSTANCE.unregister(androidAutofill);
        }
        getViewTreeObserver().removeOnGlobalLayoutListener(this.globalLayoutListener);
        getViewTreeObserver().removeOnScrollChangedListener(this.scrollChangedListener);
        getViewTreeObserver().removeOnTouchModeChangeListener(this.touchModeChangeListener);
        if (Build.VERSION.SDK_INT >= 31) {
            AndroidComposeViewTranslationCallbackS.INSTANCE.clearViewTranslationCallback(this);
        }
    }

    @Override // android.view.View
    public void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i) {
        AndroidAutofill androidAutofill;
        if (!autofillSupported() || viewStructure == null || (androidAutofill = this._autofill) == null) {
            return;
        }
        AndroidAutofill_androidKt.populateViewStructure(androidAutofill, viewStructure);
    }

    @Override // android.view.View
    public void autofill(SparseArray sparseArray) {
        AndroidAutofill androidAutofill;
        if (!autofillSupported() || (androidAutofill = this._autofill) == null) {
            return;
        }
        AndroidAutofill_androidKt.performAutofill(androidAutofill, sparseArray);
    }

    @Override // android.view.View
    public void onCreateVirtualViewTranslationRequests(long[] jArr, int[] iArr, Consumer consumer) {
        this.contentCaptureManager.onCreateVirtualViewTranslationRequests$ui_release(jArr, iArr, consumer);
    }

    @Override // android.view.View
    public void onVirtualViewTranslationResponses(LongSparseArray longSparseArray) {
        AndroidContentCaptureManager androidContentCaptureManager = this.contentCaptureManager;
        androidContentCaptureManager.onVirtualViewTranslationResponses$ui_release(androidContentCaptureManager, longSparseArray);
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            if (motionEvent.getActionMasked() == 8) {
                this.hoverExitReceived = false;
            } else {
                this.sendHoverExitEvent.run();
            }
        }
        if (motionEvent.getActionMasked() == 8) {
            if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
                return super.dispatchGenericMotionEvent(motionEvent);
            }
            return motionEvent.isFromSource(4194304) ? handleRotaryEvent(motionEvent) : ProcessResult.m1714getDispatchedToAPointerInputModifierimpl(m1923handleMotionEvent8iAsVTc(motionEvent));
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            MotionEvent motionEvent2 = this.previousMotionEvent;
            Intrinsics.checkNotNull(motionEvent2);
            if (motionEvent.getActionMasked() != 0 || hasChangedDevices(motionEvent, motionEvent2)) {
                this.sendHoverExitEvent.run();
            } else {
                this.hoverExitReceived = false;
            }
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
            return false;
        }
        if (motionEvent.getActionMasked() == 2 && !isPositionChanged(motionEvent)) {
            return false;
        }
        int iM1923handleMotionEvent8iAsVTc = m1923handleMotionEvent8iAsVTc(motionEvent);
        if (ProcessResult.m1713getAnyMovementConsumedimpl(iM1923handleMotionEvent8iAsVTc)) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return ProcessResult.m1714getDispatchedToAPointerInputModifierimpl(iM1923handleMotionEvent8iAsVTc);
    }

    private final boolean handleRotaryEvent(MotionEvent motionEvent) {
        android.view.ViewConfiguration viewConfiguration = android.view.ViewConfiguration.get(getContext());
        float f = -motionEvent.getAxisValue(26);
        return getFocusOwner().dispatchRotaryEvent(new RotaryScrollEvent(f * ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, getContext()), f * ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, getContext()), motionEvent.getEventTime(), motionEvent.getDeviceId()));
    }

    /* JADX INFO: renamed from: handleMotionEvent-8iAsVTc, reason: not valid java name */
    private final int m1923handleMotionEvent8iAsVTc(MotionEvent motionEvent) {
        int i;
        int i2;
        removeCallbacks(this.resendMotionEventRunnable);
        try {
            recalculateWindowPosition(motionEvent);
            this.forceUseMatrixCache = true;
            measureAndLayout(false);
            Trace.beginSection("AndroidOwner:onTouch");
            try {
                int actionMasked = motionEvent.getActionMasked();
                MotionEvent motionEvent2 = this.previousMotionEvent;
                boolean z = motionEvent2 != null && motionEvent2.getToolType(0) == 3;
                if (motionEvent2 == null || !hasChangedDevices(motionEvent, motionEvent2)) {
                    i = 10;
                } else {
                    if (isDevicePressEvent(motionEvent2)) {
                        this.pointerInputEventProcessor.processCancel();
                    } else if (motionEvent2.getActionMasked() != 10 && z) {
                        i = 10;
                        sendSimulatedEvent$default(this, motionEvent2, 10, motionEvent2.getEventTime(), false, 8, null);
                    }
                    i = 10;
                }
                boolean z2 = motionEvent.getToolType(0) == 3;
                if (z || !z2 || actionMasked == 3 || actionMasked == 9 || !isInBounds(motionEvent)) {
                    i2 = 9;
                } else {
                    i2 = 9;
                    sendSimulatedEvent$default(this, motionEvent, 9, motionEvent.getEventTime(), false, 8, null);
                }
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                }
                MotionEvent motionEvent3 = this.previousMotionEvent;
                if (motionEvent3 != null && motionEvent3.getAction() == i) {
                    MotionEvent motionEvent4 = this.previousMotionEvent;
                    int pointerId = motionEvent4 != null ? motionEvent4.getPointerId(0) : -1;
                    if (motionEvent.getAction() == i2 && motionEvent.getHistorySize() == 0) {
                        if (pointerId >= 0) {
                            this.motionEventAdapter.endStream(pointerId);
                        }
                    } else if (motionEvent.getAction() == 0 && motionEvent.getHistorySize() == 0) {
                        MotionEvent motionEvent5 = this.previousMotionEvent;
                        float x = motionEvent5 != null ? motionEvent5.getX() : Float.NaN;
                        MotionEvent motionEvent6 = this.previousMotionEvent;
                        boolean z3 = (x == motionEvent.getX() && (motionEvent6 != null ? motionEvent6.getY() : Float.NaN) == motionEvent.getY()) ? false : true;
                        MotionEvent motionEvent7 = this.previousMotionEvent;
                        boolean z4 = (motionEvent7 != null ? motionEvent7.getEventTime() : -1L) != motionEvent.getEventTime();
                        if (z3 || z4) {
                            if (pointerId >= 0) {
                                this.motionEventAdapter.endStream(pointerId);
                            }
                            this.pointerInputEventProcessor.clearPreviouslyHitModifierNodes();
                        }
                    }
                }
                this.previousMotionEvent = MotionEvent.obtainNoHistory(motionEvent);
                int iM1927sendMotionEvent8iAsVTc = m1927sendMotionEvent8iAsVTc(motionEvent);
                Trace.endSection();
                return iM1927sendMotionEvent8iAsVTc;
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        } finally {
            this.forceUseMatrixCache = false;
        }
    }

    private final boolean hasChangedDevices(MotionEvent motionEvent, MotionEvent motionEvent2) {
        return (motionEvent2.getSource() == motionEvent.getSource() && motionEvent2.getToolType(0) == motionEvent.getToolType(0)) ? false : true;
    }

    private final boolean isDevicePressEvent(MotionEvent motionEvent) {
        int actionMasked;
        return motionEvent.getButtonState() != 0 || (actionMasked = motionEvent.getActionMasked()) == 0 || actionMasked == 2 || actionMasked == 6;
    }

    /* JADX INFO: renamed from: sendMotionEvent-8iAsVTc, reason: not valid java name */
    private final int m1927sendMotionEvent8iAsVTc(MotionEvent motionEvent) {
        Object obj;
        if (this.keyboardModifiersRequireUpdate) {
            this.keyboardModifiersRequireUpdate = false;
            this._windowInfo.m1994setKeyboardModifiers5xRPYO0(PointerKeyboardModifiers.m1695constructorimpl(motionEvent.getMetaState()));
        }
        PointerInputEvent pointerInputEventConvertToPointerInputEvent$ui_release = this.motionEventAdapter.convertToPointerInputEvent$ui_release(motionEvent, this);
        if (pointerInputEventConvertToPointerInputEvent$ui_release != null) {
            List pointers = pointerInputEventConvertToPointerInputEvent$ui_release.getPointers();
            int size = pointers.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i = size - 1;
                    obj = pointers.get(size);
                    if (((PointerInputEventData) obj).getDown()) {
                        break;
                    }
                    if (i < 0) {
                        break;
                    }
                    size = i;
                }
                obj = null;
            } else {
                obj = null;
            }
            PointerInputEventData pointerInputEventData = (PointerInputEventData) obj;
            if (pointerInputEventData != null) {
                this.lastDownPointerPosition = pointerInputEventData.m1689getPositionF1C5BW0();
            }
            int iM1693processBIzXfog = this.pointerInputEventProcessor.m1693processBIzXfog(pointerInputEventConvertToPointerInputEvent$ui_release, this, isInBounds(motionEvent));
            int actionMasked = motionEvent.getActionMasked();
            if ((actionMasked != 0 && actionMasked != 5) || ProcessResult.m1714getDispatchedToAPointerInputModifierimpl(iM1693processBIzXfog)) {
                return iM1693processBIzXfog;
            }
            this.motionEventAdapter.endStream(motionEvent.getPointerId(motionEvent.getActionIndex()));
            return iM1693processBIzXfog;
        }
        this.pointerInputEventProcessor.processCancel();
        return PointerInputEventProcessorKt.ProcessResult(false, false);
    }

    static /* synthetic */ void sendSimulatedEvent$default(AndroidComposeView androidComposeView, MotionEvent motionEvent, int i, long j, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z = true;
        }
        androidComposeView.sendSimulatedEvent(motionEvent, i, j, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSimulatedEvent(MotionEvent motionEvent, int i, long j, boolean z) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = -1;
        if (actionMasked != 1) {
            if (actionMasked == 6) {
                actionIndex = motionEvent.getActionIndex();
            }
        } else if (i != 9 && i != 10) {
            actionIndex = 0;
        }
        int pointerCount = motionEvent.getPointerCount() - (actionIndex >= 0 ? 1 : 0);
        if (pointerCount == 0) {
            return;
        }
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        for (int i2 = 0; i2 < pointerCount; i2++) {
            pointerPropertiesArr[i2] = new MotionEvent.PointerProperties();
        }
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        for (int i3 = 0; i3 < pointerCount; i3++) {
            pointerCoordsArr[i3] = new MotionEvent.PointerCoords();
        }
        int i4 = 0;
        while (i4 < pointerCount) {
            int i5 = ((actionIndex < 0 || i4 < actionIndex) ? 0 : 1) + i4;
            motionEvent.getPointerProperties(i5, pointerPropertiesArr[i4]);
            MotionEvent.PointerCoords pointerCoords = pointerCoordsArr[i4];
            motionEvent.getPointerCoords(i5, pointerCoords);
            long jMo1710localToScreenMKHz9U = mo1710localToScreenMKHz9U(OffsetKt.Offset(pointerCoords.x, pointerCoords.y));
            pointerCoords.x = Offset.m1159getXimpl(jMo1710localToScreenMKHz9U);
            pointerCoords.y = Offset.m1160getYimpl(jMo1710localToScreenMKHz9U);
            i4++;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent.getDownTime() == motionEvent.getEventTime() ? j : motionEvent.getDownTime(), j, i, pointerCount, pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), z ? 0 : motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
        PointerInputEvent pointerInputEventConvertToPointerInputEvent$ui_release = this.motionEventAdapter.convertToPointerInputEvent$ui_release(motionEventObtain, this);
        Intrinsics.checkNotNull(pointerInputEventConvertToPointerInputEvent$ui_release);
        this.pointerInputEventProcessor.m1693processBIzXfog(pointerInputEventConvertToPointerInputEvent$ui_release, this, true);
        motionEventObtain.recycle();
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        return this.composeAccessibilityDelegate.m1941canScroll0AR0LA0$ui_release(false, i, this.lastDownPointerPosition);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i) {
        return this.composeAccessibilityDelegate.m1941canScroll0AR0LA0$ui_release(true, i, this.lastDownPointerPosition);
    }

    private final boolean isInBounds(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        return 0.0f <= x && x <= ((float) getWidth()) && 0.0f <= y && y <= ((float) getHeight());
    }

    @Override // androidx.compose.ui.input.pointer.PositionCalculator
    /* JADX INFO: renamed from: localToScreen-MK-Hz9U */
    public long mo1710localToScreenMKHz9U(long j) {
        recalculateWindowPosition();
        long jM1359mapMKHz9U = Matrix.m1359mapMKHz9U(this.viewToWindowMatrix, j);
        return OffsetKt.Offset(Offset.m1159getXimpl(jM1359mapMKHz9U) + Offset.m1159getXimpl(this.windowPosition), Offset.m1160getYimpl(jM1359mapMKHz9U) + Offset.m1160getYimpl(this.windowPosition));
    }

    @Override // androidx.compose.ui.input.pointer.PositionCalculator
    /* JADX INFO: renamed from: localToScreen-58bKbWc */
    public void mo1709localToScreen58bKbWc(float[] fArr) {
        recalculateWindowPosition();
        Matrix.m1367timesAssign58bKbWc(fArr, this.viewToWindowMatrix);
        AndroidComposeView_androidKt.m1947preTranslatecG2Xzmc(fArr, Offset.m1159getXimpl(this.windowPosition), Offset.m1160getYimpl(this.windowPosition), this.tmpMatrix);
    }

    @Override // androidx.compose.ui.input.pointer.PositionCalculator
    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U */
    public long mo1711screenToLocalMKHz9U(long j) {
        recalculateWindowPosition();
        return Matrix.m1359mapMKHz9U(this.windowToViewMatrix, OffsetKt.Offset(Offset.m1159getXimpl(j) - Offset.m1159getXimpl(this.windowPosition), Offset.m1160getYimpl(j) - Offset.m1160getYimpl(this.windowPosition)));
    }

    private final void recalculateWindowPosition() {
        if (this.forceUseMatrixCache) {
            return;
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (jCurrentAnimationTimeMillis != this.lastMatrixRecalculationAnimationTime) {
            this.lastMatrixRecalculationAnimationTime = jCurrentAnimationTimeMillis;
            recalculateWindowViewTransforms();
            ViewParent parent = getParent();
            View view = this;
            while (parent instanceof ViewGroup) {
                view = (View) parent;
                parent = ((ViewGroup) view).getParent();
            }
            view.getLocationOnScreen(this.tmpPositionArray);
            int[] iArr = this.tmpPositionArray;
            float f = iArr[0];
            float f2 = iArr[1];
            view.getLocationInWindow(iArr);
            int[] iArr2 = this.tmpPositionArray;
            this.windowPosition = OffsetKt.Offset(f - iArr2[0], f2 - iArr2[1]);
        }
    }

    private final void recalculateWindowPosition(MotionEvent motionEvent) {
        this.lastMatrixRecalculationAnimationTime = AnimationUtils.currentAnimationTimeMillis();
        recalculateWindowViewTransforms();
        long jM1359mapMKHz9U = Matrix.m1359mapMKHz9U(this.viewToWindowMatrix, OffsetKt.Offset(motionEvent.getX(), motionEvent.getY()));
        this.windowPosition = OffsetKt.Offset(motionEvent.getRawX() - Offset.m1159getXimpl(jM1359mapMKHz9U), motionEvent.getRawY() - Offset.m1160getYimpl(jM1359mapMKHz9U));
    }

    private final void recalculateWindowViewTransforms() {
        this.matrixToWindow.mo1950calculateMatrixToWindowEL8BTi8(this, this.viewToWindowMatrix);
        InvertMatrixKt.m1972invertToJiSxe2E(this.viewToWindowMatrix, this.windowToViewMatrix);
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        AndroidPlatformTextInputSession androidPlatformTextInputSession = (AndroidPlatformTextInputSession) SessionMutex.m1069getCurrentSessionimpl(this.textInputSessionMutex);
        if (androidPlatformTextInputSession == null) {
            return this.legacyTextInputServiceAndroid.isEditorFocused();
        }
        return androidPlatformTextInputSession.isReadyForConnection();
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        AndroidPlatformTextInputSession androidPlatformTextInputSession = (AndroidPlatformTextInputSession) SessionMutex.m1069getCurrentSessionimpl(this.textInputSessionMutex);
        if (androidPlatformTextInputSession == null) {
            return this.legacyTextInputServiceAndroid.createInputConnection(editorInfo);
        }
        return androidPlatformTextInputSession.createInputConnection(editorInfo);
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: calculateLocalPosition-MK-Hz9U */
    public long mo1903calculateLocalPositionMKHz9U(long j) {
        recalculateWindowPosition();
        return Matrix.m1359mapMKHz9U(this.windowToViewMatrix, j);
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: calculatePositionInWindow-MK-Hz9U */
    public long mo1904calculatePositionInWindowMKHz9U(long j) {
        recalculateWindowPosition();
        return Matrix.m1359mapMKHz9U(this.viewToWindowMatrix, j);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setDensity(AndroidDensity_androidKt.Density(getContext()));
        if (getFontWeightAdjustmentCompat(configuration) != this.currentFontWeightAdjustment) {
            this.currentFontWeightAdjustment = getFontWeightAdjustmentCompat(configuration);
            setFontFamilyResolver(FontFamilyResolver_androidKt.createFontFamilyResolver(getContext()));
        }
        this.configurationChangeObserver.invoke(configuration);
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        if (this.superclassInitComplete) {
            LayoutDirection layoutDirection = FocusInteropUtils_androidKt.toLayoutDirection(i);
            if (layoutDirection == null) {
                layoutDirection = LayoutDirection.Ltr;
            }
            setLayoutDirection(layoutDirection);
        }
    }

    private final boolean autofillSupported() {
        return Build.VERSION.SDK_INT >= 26;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            this.sendHoverExitEvent.run();
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
            return false;
        }
        this.composeAccessibilityDelegate.dispatchHoverEvent$ui_release(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 7) {
            if (actionMasked == 10 && isInBounds(motionEvent)) {
                if (motionEvent.getToolType(0) == 3 && motionEvent.getButtonState() != 0) {
                    return false;
                }
                MotionEvent motionEvent2 = this.previousMotionEvent;
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                }
                this.previousMotionEvent = MotionEvent.obtainNoHistory(motionEvent);
                this.hoverExitReceived = true;
                postDelayed(this.sendHoverExitEvent, 8L);
                return false;
            }
        } else if (!isPositionChanged(motionEvent)) {
            return false;
        }
        return ProcessResult.m1714getDispatchedToAPointerInputModifierimpl(m1923handleMotionEvent8iAsVTc(motionEvent));
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0082 A[LOOP:0: B:22:0x004c->B:39:0x0082, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0085 A[EDGE_INSN: B:41:0x0085->B:40:0x0085 BREAK  A[LOOP:0: B:22:0x004c->B:39:0x0082], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean isBadMotionEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            float r0 = r7.getX()
            boolean r1 = java.lang.Float.isInfinite(r0)
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            float r0 = r7.getY()
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            float r0 = r7.getRawX()
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            float r0 = r7.getRawY()
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            r0 = r2
            goto L45
        L44:
            r0 = r3
        L45:
            if (r0 != 0) goto L85
            int r1 = r7.getPointerCount()
            r4 = r3
        L4c:
            if (r4 >= r1) goto L85
            float r0 = r7.getX(r4)
            boolean r5 = java.lang.Float.isInfinite(r0)
            if (r5 != 0) goto L7f
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L7f
            float r0 = r7.getY(r4)
            boolean r5 = java.lang.Float.isInfinite(r0)
            if (r5 != 0) goto L7f
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L7f
            int r0 = android.os.Build.VERSION.SDK_INT
            r5 = 29
            if (r0 < r5) goto L7d
            androidx.compose.ui.platform.MotionEventVerifierApi29 r0 = androidx.compose.ui.platform.MotionEventVerifierApi29.INSTANCE
            boolean r0 = r0.isValidMotionEvent(r7, r4)
            if (r0 != 0) goto L7d
            goto L7f
        L7d:
            r0 = r2
            goto L80
        L7f:
            r0 = r3
        L80:
            if (r0 != 0) goto L85
            int r4 = r4 + 1
            goto L4c
        L85:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.isBadMotionEvent(android.view.MotionEvent):boolean");
    }

    private final boolean isPositionChanged(MotionEvent motionEvent) {
        MotionEvent motionEvent2;
        return (motionEvent.getPointerCount() == 1 && (motionEvent2 = this.previousMotionEvent) != null && motionEvent2.getPointerCount() == motionEvent.getPointerCount() && motionEvent.getRawX() == motionEvent2.getRawX() && motionEvent.getRawY() == motionEvent2.getRawY()) ? false : true;
    }

    private final View findViewByAccessibilityIdRootedAtCurrentView(int i, View view) throws NoSuchMethodException {
        if (Build.VERSION.SDK_INT < 29) {
            Method declaredMethod = View.class.getDeclaredMethod("getAccessibilityViewId", null);
            declaredMethod.setAccessible(true);
            if (Intrinsics.areEqual(declaredMethod.invoke(view, null), Integer.valueOf(i))) {
                return view;
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View viewFindViewByAccessibilityIdRootedAtCurrentView = findViewByAccessibilityIdRootedAtCurrentView(i, viewGroup.getChildAt(i2));
                    if (viewFindViewByAccessibilityIdRootedAtCurrentView != null) {
                        return viewFindViewByAccessibilityIdRootedAtCurrentView;
                    }
                }
            }
        }
        return null;
    }

    @Override // androidx.compose.ui.node.Owner
    public PointerIconService getPointerIconService() {
        return this.pointerIconService;
    }

    public final View findViewByAccessibilityIdTraversal(int i) throws IllegalAccessException, InvocationTargetException {
        View viewFindViewByAccessibilityIdRootedAtCurrentView = null;
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                Method declaredMethod = View.class.getDeclaredMethod("findViewByAccessibilityIdTraversal", Integer.TYPE);
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(this, Integer.valueOf(i));
                if (objInvoke instanceof View) {
                    viewFindViewByAccessibilityIdRootedAtCurrentView = (View) objInvoke;
                }
            } else {
                viewFindViewByAccessibilityIdRootedAtCurrentView = findViewByAccessibilityIdRootedAtCurrentView(i, this);
            }
        } catch (NoSuchMethodException unused) {
        }
        return viewFindViewByAccessibilityIdRootedAtCurrentView;
    }

    /* JADX INFO: compiled from: AndroidComposeView.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean getIsShowingLayoutBounds() {
            try {
                if (AndroidComposeView.systemPropertiesClass == null) {
                    AndroidComposeView.systemPropertiesClass = Class.forName("android.os.SystemProperties");
                    Class cls = AndroidComposeView.systemPropertiesClass;
                    AndroidComposeView.getBooleanMethod = cls != null ? cls.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE) : null;
                }
                Method method = AndroidComposeView.getBooleanMethod;
                Object objInvoke = method != null ? method.invoke(null, "debug.layout", Boolean.FALSE) : null;
                Boolean bool = objInvoke instanceof Boolean ? (Boolean) objInvoke : null;
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: AndroidComposeView.android.kt */
    public static final class ViewTreeOwners {
        private final LifecycleOwner lifecycleOwner;
        private final SavedStateRegistryOwner savedStateRegistryOwner;

        public ViewTreeOwners(LifecycleOwner lifecycleOwner, SavedStateRegistryOwner savedStateRegistryOwner) {
            this.lifecycleOwner = lifecycleOwner;
            this.savedStateRegistryOwner = savedStateRegistryOwner;
        }

        public final LifecycleOwner getLifecycleOwner() {
            return this.lifecycleOwner;
        }

        public final SavedStateRegistryOwner getSavedStateRegistryOwner() {
            return this.savedStateRegistryOwner;
        }
    }
}
