package androidx.compose.ui.window;

import android.R;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.R$id;
import androidx.compose.ui.R$string;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.platform.AbstractComposeView;
import androidx.compose.ui.platform.ViewRootForInspector;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.util.UUID;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$LongRef;

/* JADX INFO: compiled from: AndroidPopup.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PopupLayout extends AbstractComposeView implements ViewRootForInspector {
    private Object backCallback;
    private final State canCalculatePosition$delegate;
    private final View composeView;
    private final MutableState content$delegate;
    private final int[] locationOnScreen;
    private final float maxSupportedElevation;
    private Function0 onDismissRequest;
    private final WindowManager.LayoutParams params;
    private IntRect parentBounds;
    private final MutableState parentLayoutCoordinates$delegate;
    private LayoutDirection parentLayoutDirection;
    private final MutableState popupContentSize$delegate;
    private final PopupLayoutHelper popupLayoutHelper;
    private PopupPositionProvider positionProvider;
    private final Rect previousWindowVisibleFrame;
    private PopupProperties properties;
    private boolean shouldCreateCompositionOnAttachedToWindow;
    private final SnapshotStateObserver snapshotStateObserver;
    private String testTag;
    private final WindowManager windowManager;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final Function1 onCommitAffectingPopupPosition = new Function1() { // from class: androidx.compose.ui.window.PopupLayout$Companion$onCommitAffectingPopupPosition$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((PopupLayout) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(PopupLayout popupLayout) {
            if (popupLayout.isAttachedToWindow()) {
                popupLayout.updatePosition();
            }
        }
    };

    /* JADX INFO: compiled from: AndroidPopup.android.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ void getParams$ui_release$annotations() {
    }

    public AbstractComposeView getSubCompositionView() {
        return this;
    }

    public /* bridge */ /* synthetic */ View getViewRoot() {
        return ViewRootForInspector.CC.$default$getViewRoot(this);
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
    }

    public final String getTestTag() {
        return this.testTag;
    }

    public final void setTestTag(String str) {
        this.testTag = str;
    }

    public /* synthetic */ PopupLayout(Function0 function0, PopupProperties popupProperties, String str, View view, Density density, PopupPositionProvider popupPositionProvider, UUID uuid, PopupLayoutHelper popupLayoutHelper, int i, DefaultConstructorMarker defaultConstructorMarker) {
        PopupLayoutHelper popupLayoutHelper2;
        PopupLayoutHelper popupLayoutHelperImpl;
        if ((i & 128) != 0) {
            if (Build.VERSION.SDK_INT >= 29) {
                popupLayoutHelperImpl = new PopupLayoutHelperImpl29();
            } else {
                popupLayoutHelperImpl = new PopupLayoutHelperImpl();
            }
            popupLayoutHelper2 = popupLayoutHelperImpl;
        } else {
            popupLayoutHelper2 = popupLayoutHelper;
        }
        this(function0, popupProperties, str, view, density, popupPositionProvider, uuid, popupLayoutHelper2);
    }

    public PopupLayout(Function0 function0, PopupProperties popupProperties, String str, View view, Density density, PopupPositionProvider popupPositionProvider, UUID uuid, PopupLayoutHelper popupLayoutHelper) {
        super(view.getContext(), null, 0, 6, null);
        this.onDismissRequest = function0;
        this.properties = popupProperties;
        this.testTag = str;
        this.composeView = view;
        this.popupLayoutHelper = popupLayoutHelper;
        Object systemService = view.getContext().getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        this.windowManager = (WindowManager) systemService;
        this.params = createLayoutParams();
        this.positionProvider = popupPositionProvider;
        this.parentLayoutDirection = LayoutDirection.Ltr;
        this.popupContentSize$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.parentLayoutCoordinates$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.canCalculatePosition$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.ui.window.PopupLayout$canCalculatePosition$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                LayoutCoordinates parentLayoutCoordinates = this.this$0.getParentLayoutCoordinates();
                if (parentLayoutCoordinates == null || !parentLayoutCoordinates.isAttached()) {
                    parentLayoutCoordinates = null;
                }
                return Boolean.valueOf((parentLayoutCoordinates == null || this.this$0.m2528getPopupContentSizebOM6tXw() == null) ? false : true);
            }
        });
        float fM2416constructorimpl = Dp.m2416constructorimpl(8);
        this.maxSupportedElevation = fM2416constructorimpl;
        this.previousWindowVisibleFrame = new Rect();
        this.snapshotStateObserver = new SnapshotStateObserver(new PopupLayout$snapshotStateObserver$1(this));
        setId(R.id.content);
        ViewTreeLifecycleOwner.set(this, ViewTreeLifecycleOwner.get(view));
        ViewTreeViewModelStoreOwner.set(this, ViewTreeViewModelStoreOwner.get(view));
        ViewTreeSavedStateRegistryOwner.set(this, ViewTreeSavedStateRegistryOwner.get(view));
        setTag(R$id.compose_view_saveable_id_tag, "Popup:" + uuid);
        setClipChildren(false);
        setElevation(density.mo210toPx0680j_4(fM2416constructorimpl));
        setOutlineProvider(new ViewOutlineProvider() { // from class: androidx.compose.ui.window.PopupLayout.2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view2, Outline outline) {
                outline.setRect(0, 0, view2.getWidth(), view2.getHeight());
                outline.setAlpha(0.0f);
            }
        });
        this.content$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ComposableSingletons$AndroidPopup_androidKt.INSTANCE.m2527getLambda1$ui_release(), null, 2, null);
        this.locationOnScreen = new int[2];
    }

    public final WindowManager.LayoutParams getParams$ui_release() {
        return this.params;
    }

    public final PopupPositionProvider getPositionProvider() {
        return this.positionProvider;
    }

    public final void setPositionProvider(PopupPositionProvider popupPositionProvider) {
        this.positionProvider = popupPositionProvider;
    }

    public final LayoutDirection getParentLayoutDirection() {
        return this.parentLayoutDirection;
    }

    public final void setParentLayoutDirection(LayoutDirection layoutDirection) {
        this.parentLayoutDirection = layoutDirection;
    }

    /* JADX INFO: renamed from: getPopupContentSize-bOM6tXw, reason: not valid java name */
    public final IntSize m2528getPopupContentSizebOM6tXw() {
        return (IntSize) this.popupContentSize$delegate.getValue();
    }

    /* JADX INFO: renamed from: setPopupContentSize-fhxjrPA, reason: not valid java name */
    public final void m2529setPopupContentSizefhxjrPA(IntSize intSize) {
        this.popupContentSize$delegate.setValue(intSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutCoordinates getParentLayoutCoordinates() {
        return (LayoutCoordinates) this.parentLayoutCoordinates$delegate.getValue();
    }

    private final void setParentLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        this.parentLayoutCoordinates$delegate.setValue(layoutCoordinates);
    }

    public final boolean getCanCalculatePosition() {
        return ((Boolean) this.canCalculatePosition$delegate.getValue()).booleanValue();
    }

    private final Function2 getContent() {
        return (Function2) this.content$delegate.getValue();
    }

    private final void setContent(Function2 function2) {
        this.content$delegate.setValue(function2);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    protected boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.shouldCreateCompositionOnAttachedToWindow;
    }

    public final void show() {
        this.windowManager.addView(this, this.params);
    }

    public final void setContent(CompositionContext compositionContext, Function2 function2) {
        setParentCompositionContext(compositionContext);
        setContent(function2);
        this.shouldCreateCompositionOnAttachedToWindow = true;
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void Content(Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-857613600);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(this) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-857613600, i2, -1, "androidx.compose.ui.window.PopupLayout.Content (AndroidPopup.android.kt:572)");
            }
            getContent().invoke(composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.ui.window.PopupLayout.Content.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    PopupLayout.this.Content(composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.snapshotStateObserver.start();
        maybeRegisterBackCallback();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.snapshotStateObserver.stop();
        this.snapshotStateObserver.clear();
        maybeUnregisterBackCallback();
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void internalOnMeasure$ui_release(int i, int i2) {
        if (this.properties.getUsePlatformDefaultWidth()) {
            super.internalOnMeasure$ui_release(i, i2);
        } else {
            super.internalOnMeasure$ui_release(View.MeasureSpec.makeMeasureSpec(getDisplayWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getDisplayHeight(), Integer.MIN_VALUE));
        }
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void internalOnLayout$ui_release(boolean z, int i, int i2, int i3, int i4) {
        View childAt;
        super.internalOnLayout$ui_release(z, i, i2, i3, i4);
        if (this.properties.getUsePlatformDefaultWidth() || (childAt = getChildAt(0)) == null) {
            return;
        }
        this.params.width = childAt.getMeasuredWidth();
        this.params.height = childAt.getMeasuredHeight();
        this.popupLayoutHelper.updateViewLayout(this.windowManager, this, this.params);
    }

    private final int getDisplayWidth() {
        return Math.round(getContext().getResources().getConfiguration().screenWidthDp * getContext().getResources().getDisplayMetrics().density);
    }

    private final int getDisplayHeight() {
        return Math.round(getContext().getResources().getConfiguration().screenHeightDp * getContext().getResources().getDisplayMetrics().density);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        KeyEvent.DispatcherState keyDispatcherState;
        if (keyEvent.getKeyCode() == 4 && this.properties.getDismissOnBackPress()) {
            if (getKeyDispatcherState() == null) {
                return super.dispatchKeyEvent(keyEvent);
            }
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                }
                return true;
            }
            if (keyEvent.getAction() == 1 && (keyDispatcherState = getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent) && !keyEvent.isCanceled()) {
                Function0 function0 = this.onDismissRequest;
                if (function0 != null) {
                    function0.invoke();
                }
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    private final void maybeRegisterBackCallback() {
        if (!this.properties.getDismissOnBackPress() || Build.VERSION.SDK_INT < 33) {
            return;
        }
        if (this.backCallback == null) {
            this.backCallback = Api33Impl.createBackCallback(this.onDismissRequest);
        }
        Api33Impl.maybeRegisterBackCallback(this, this.backCallback);
    }

    private final void maybeUnregisterBackCallback() {
        if (Build.VERSION.SDK_INT >= 33) {
            Api33Impl.maybeUnregisterBackCallback(this, this.backCallback);
        }
        this.backCallback = null;
    }

    public final void updateParameters(Function0 function0, PopupProperties popupProperties, String str, LayoutDirection layoutDirection) {
        this.onDismissRequest = function0;
        this.testTag = str;
        updatePopupProperties(popupProperties);
        superSetLayoutDirection(layoutDirection);
    }

    private final void updatePopupProperties(PopupProperties popupProperties) {
        if (Intrinsics.areEqual(this.properties, popupProperties)) {
            return;
        }
        if (popupProperties.getUsePlatformDefaultWidth() && !this.properties.getUsePlatformDefaultWidth()) {
            WindowManager.LayoutParams layoutParams = this.params;
            layoutParams.width = -2;
            layoutParams.height = -2;
        }
        this.properties = popupProperties;
        this.params.flags = AndroidPopup_androidKt.flagsWithSecureFlagInherited(popupProperties, AndroidPopup_androidKt.isFlagSecureEnabled(this.composeView));
        this.popupLayoutHelper.updateViewLayout(this.windowManager, this, this.params);
    }

    public final void updateParentLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        setParentLayoutCoordinates(layoutCoordinates);
        updateParentBounds$ui_release();
    }

    public final void pollForLocationOnScreenChange() {
        int[] iArr = this.locationOnScreen;
        int i = iArr[0];
        int i2 = iArr[1];
        this.composeView.getLocationOnScreen(iArr);
        int[] iArr2 = this.locationOnScreen;
        if (i == iArr2[0] && i2 == iArr2[1]) {
            return;
        }
        updateParentBounds$ui_release();
    }

    public final void updateParentBounds$ui_release() {
        LayoutCoordinates parentLayoutCoordinates = getParentLayoutCoordinates();
        if (parentLayoutCoordinates != null) {
            if (!parentLayoutCoordinates.isAttached()) {
                parentLayoutCoordinates = null;
            }
            if (parentLayoutCoordinates == null) {
                return;
            }
            long jMo1745getSizeYbymL2g = parentLayoutCoordinates.mo1745getSizeYbymL2g();
            long jPositionInWindow = LayoutCoordinatesKt.positionInWindow(parentLayoutCoordinates);
            IntRect intRectM2470IntRectVbeCjmY = IntRectKt.m2470IntRectVbeCjmY(IntOffsetKt.IntOffset(Math.round(Offset.m1159getXimpl(jPositionInWindow)), Math.round(Offset.m1160getYimpl(jPositionInWindow))), jMo1745getSizeYbymL2g);
            if (Intrinsics.areEqual(intRectM2470IntRectVbeCjmY, this.parentBounds)) {
                return;
            }
            this.parentBounds = intRectM2470IntRectVbeCjmY;
            updatePosition();
        }
    }

    public final void updatePosition() {
        IntSize intSizeM2528getPopupContentSizebOM6tXw;
        final IntRect intRect = this.parentBounds;
        if (intRect == null || (intSizeM2528getPopupContentSizebOM6tXw = m2528getPopupContentSizebOM6tXw()) == null) {
            return;
        }
        final long jM2479unboximpl = intSizeM2528getPopupContentSizebOM6tXw.m2479unboximpl();
        Rect rect = this.previousWindowVisibleFrame;
        this.popupLayoutHelper.getWindowVisibleDisplayFrame(this.composeView, rect);
        IntRect intBounds = AndroidPopup_androidKt.toIntBounds(rect);
        final long jIntSize = IntSizeKt.IntSize(intBounds.getWidth(), intBounds.getHeight());
        final Ref$LongRef ref$LongRef = new Ref$LongRef();
        ref$LongRef.element = IntOffset.Companion.m2464getZeronOccac();
        this.snapshotStateObserver.observeReads(this, onCommitAffectingPopupPosition, new Function0() { // from class: androidx.compose.ui.window.PopupLayout.updatePosition.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m2530invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m2530invoke() {
                ref$LongRef.element = this.getPositionProvider().mo151calculatePositionllwVHH4(intRect, jIntSize, this.getParentLayoutDirection(), jM2479unboximpl);
            }
        });
        this.params.x = IntOffset.m2457getXimpl(ref$LongRef.element);
        this.params.y = IntOffset.m2458getYimpl(ref$LongRef.element);
        if (this.properties.getExcludeFromSystemGesture()) {
            this.popupLayoutHelper.setGestureExclusionRects(this, IntSize.m2476getWidthimpl(jIntSize), IntSize.m2475getHeightimpl(jIntSize));
        }
        this.popupLayoutHelper.updateViewLayout(this.windowManager, this, this.params);
    }

    public final void dismiss() {
        ViewTreeLifecycleOwner.set(this, null);
        this.windowManager.removeViewImmediate(this);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.properties.getDismissOnClickOutside()) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent != null && motionEvent.getAction() == 0 && (motionEvent.getX() < 0.0f || motionEvent.getX() >= getWidth() || motionEvent.getY() < 0.0f || motionEvent.getY() >= getHeight())) {
            Function0 function0 = this.onDismissRequest;
            if (function0 != null) {
                function0.invoke();
            }
            return true;
        }
        if (motionEvent != null && motionEvent.getAction() == 4) {
            Function0 function02 = this.onDismissRequest;
            if (function02 != null) {
                function02.invoke();
            }
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    private final void superSetLayoutDirection(LayoutDirection layoutDirection) {
        int i = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
        int i2 = 1;
        if (i == 1) {
            i2 = 0;
        } else if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        super.setLayoutDirection(i2);
    }

    private final WindowManager.LayoutParams createLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 8388659;
        layoutParams.flags = AndroidPopup_androidKt.flagsWithSecureFlagInherited(this.properties, AndroidPopup_androidKt.isFlagSecureEnabled(this.composeView));
        layoutParams.type = 1002;
        layoutParams.token = this.composeView.getApplicationWindowToken();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.setTitle(this.composeView.getContext().getResources().getString(R$string.default_popup_window_title));
        return layoutParams;
    }

    /* JADX INFO: compiled from: AndroidPopup.android.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
