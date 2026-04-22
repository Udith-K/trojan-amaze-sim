package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.PlatformTextInputModifierNode;
import androidx.compose.ui.platform.PlatformTextInputModifierNodeKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.ViewConfiguration;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: LegacyAdaptingPlatformTextInputModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LegacyAdaptingPlatformTextInputModifierNode extends Modifier.Node implements PlatformTextInputModifierNode, CompositionLocalConsumerModifierNode, GlobalPositionAwareModifierNode, LegacyPlatformTextInputServiceAdapter.LegacyPlatformTextInputNode {
    private final MutableState layoutCoordinates$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    private LegacyTextFieldState legacyTextFieldState;
    private LegacyPlatformTextInputServiceAdapter serviceAdapter;
    private TextFieldSelectionManager textFieldSelectionManager;

    @Override // androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter.LegacyPlatformTextInputNode
    public LegacyTextFieldState getLegacyTextFieldState() {
        return this.legacyTextFieldState;
    }

    public void setLegacyTextFieldState(LegacyTextFieldState legacyTextFieldState) {
        this.legacyTextFieldState = legacyTextFieldState;
    }

    @Override // androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter.LegacyPlatformTextInputNode
    public TextFieldSelectionManager getTextFieldSelectionManager() {
        return this.textFieldSelectionManager;
    }

    public void setTextFieldSelectionManager(TextFieldSelectionManager textFieldSelectionManager) {
        this.textFieldSelectionManager = textFieldSelectionManager;
    }

    public LegacyAdaptingPlatformTextInputModifierNode(LegacyPlatformTextInputServiceAdapter legacyPlatformTextInputServiceAdapter, LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager) {
        this.serviceAdapter = legacyPlatformTextInputServiceAdapter;
        this.legacyTextFieldState = legacyTextFieldState;
        this.textFieldSelectionManager = textFieldSelectionManager;
    }

    private void setLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        this.layoutCoordinates$delegate.setValue(layoutCoordinates);
    }

    @Override // androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter.LegacyPlatformTextInputNode
    public LayoutCoordinates getLayoutCoordinates() {
        return (LayoutCoordinates) this.layoutCoordinates$delegate.getValue();
    }

    @Override // androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter.LegacyPlatformTextInputNode
    public SoftwareKeyboardController getSoftwareKeyboardController() {
        return (SoftwareKeyboardController) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalSoftwareKeyboardController());
    }

    public final void setServiceAdapter(LegacyPlatformTextInputServiceAdapter legacyPlatformTextInputServiceAdapter) {
        if (isAttached()) {
            this.serviceAdapter.stopInput();
            this.serviceAdapter.unregisterModifier(this);
        }
        this.serviceAdapter = legacyPlatformTextInputServiceAdapter;
        if (isAttached()) {
            this.serviceAdapter.registerModifier(this);
        }
    }

    @Override // androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter.LegacyPlatformTextInputNode
    public ViewConfiguration getViewConfiguration() {
        return (ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalViewConfiguration());
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        this.serviceAdapter.registerModifier(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.serviceAdapter.unregisterModifier(this);
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates layoutCoordinates) {
        setLayoutCoordinates(layoutCoordinates);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.LegacyAdaptingPlatformTextInputModifierNode$launchTextInputSession$1, reason: invalid class name */
    /* JADX INFO: compiled from: LegacyAdaptingPlatformTextInputModifierNode.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function2 $block;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Function2 function2, Continuation continuation) {
            super(2, continuation);
            this.$block = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return LegacyAdaptingPlatformTextInputModifierNode.this.new AnonymousClass1(this.$block, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LegacyAdaptingPlatformTextInputModifierNode legacyAdaptingPlatformTextInputModifierNode = LegacyAdaptingPlatformTextInputModifierNode.this;
                Function2 function2 = this.$block;
                this.label = 1;
                if (PlatformTextInputModifierNodeKt.establishTextInputSession(legacyAdaptingPlatformTextInputModifierNode, function2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    @Override // androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter.LegacyPlatformTextInputNode
    public Job launchTextInputSession(Function2 function2) {
        if (isAttached()) {
            return BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(function2, null), 1, null);
        }
        return null;
    }
}
