package androidx.compose.foundation.text;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: TextFieldScroll.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextFieldScrollKt {

    /* JADX INFO: compiled from: TextFieldScroll.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            try {
                iArr[Orientation.Vertical.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Orientation.Horizontal.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Modifier textFieldScroll(Modifier modifier, TextFieldScrollerPosition textFieldScrollerPosition, TextFieldValue textFieldValue, VisualTransformation visualTransformation, Function0 function0) {
        Modifier verticalScrollLayoutModifier;
        Orientation orientation = textFieldScrollerPosition.getOrientation();
        int iM452getOffsetToFollow5zctL8 = textFieldScrollerPosition.m452getOffsetToFollow5zctL8(textFieldValue.m2239getSelectiond9O1mEE());
        textFieldScrollerPosition.m453setPreviousSelection5zctL8(textFieldValue.m2239getSelectiond9O1mEE());
        TransformedText transformedTextFilterWithValidation = ValidatingOffsetMappingKt.filterWithValidation(visualTransformation, textFieldValue.getAnnotatedString());
        int i = WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()];
        if (i == 1) {
            verticalScrollLayoutModifier = new VerticalScrollLayoutModifier(textFieldScrollerPosition, iM452getOffsetToFollow5zctL8, transformedTextFilterWithValidation, function0);
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            verticalScrollLayoutModifier = new HorizontalScrollLayoutModifier(textFieldScrollerPosition, iM452getOffsetToFollow5zctL8, transformedTextFilterWithValidation, function0);
        }
        return ClipKt.clipToBounds(modifier).then(verticalScrollLayoutModifier);
    }

    public static final Modifier textFieldScrollable(Modifier modifier, final TextFieldScrollerPosition textFieldScrollerPosition, final MutableInteractionSource mutableInteractionSource, final boolean z) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1() { // from class: androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                invoke((InspectorInfo) null);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                throw null;
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.foundation.text.TextFieldScrollKt.textFieldScrollable.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
            }

            public final Modifier invoke(Modifier modifier2, Composer composer, int i) {
                composer.startReplaceGroup(805428266);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(805428266, i, -1, "androidx.compose.foundation.text.textFieldScrollable.<anonymous> (TextFieldScroll.kt:68)");
                }
                boolean z2 = textFieldScrollerPosition.getOrientation() == Orientation.Vertical || !(composer.consume(CompositionLocalsKt.getLocalLayoutDirection()) == LayoutDirection.Rtl);
                boolean zChanged = composer.changed(textFieldScrollerPosition);
                final TextFieldScrollerPosition textFieldScrollerPosition2 = textFieldScrollerPosition;
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2$scrollableState$1$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            return invoke(((Number) obj).floatValue());
                        }

                        public final Float invoke(float f) {
                            float offset = textFieldScrollerPosition2.getOffset() + f;
                            if (offset > textFieldScrollerPosition2.getMaximum()) {
                                f = textFieldScrollerPosition2.getMaximum() - textFieldScrollerPosition2.getOffset();
                            } else if (offset < 0.0f) {
                                f = -textFieldScrollerPosition2.getOffset();
                            }
                            TextFieldScrollerPosition textFieldScrollerPosition3 = textFieldScrollerPosition2;
                            textFieldScrollerPosition3.setOffset(textFieldScrollerPosition3.getOffset() + f);
                            return Float.valueOf(f);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                final ScrollableState scrollableStateRememberScrollableState = ScrollableStateKt.rememberScrollableState((Function1) objRememberedValue, composer, 0);
                boolean zChanged2 = composer.changed(scrollableStateRememberScrollableState) | composer.changed(textFieldScrollerPosition);
                final TextFieldScrollerPosition textFieldScrollerPosition3 = textFieldScrollerPosition;
                Object objRememberedValue2 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                    objRememberedValue2 = new ScrollableState(textFieldScrollerPosition3) { // from class: androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1
                        private final State canScrollBackward$delegate;
                        private final State canScrollForward$delegate;

                        @Override // androidx.compose.foundation.gestures.ScrollableState
                        public float dispatchRawDelta(float f) {
                            return this.$$delegate_0.dispatchRawDelta(f);
                        }

                        @Override // androidx.compose.foundation.gestures.ScrollableState
                        public boolean isScrollInProgress() {
                            return this.$$delegate_0.isScrollInProgress();
                        }

                        @Override // androidx.compose.foundation.gestures.ScrollableState
                        public Object scroll(MutatePriority mutatePriority, Function2 function2, Continuation continuation) {
                            return this.$$delegate_0.scroll(mutatePriority, function2, continuation);
                        }

                        {
                            this.canScrollForward$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1$canScrollForward$2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    return Boolean.valueOf(textFieldScrollerPosition3.getOffset() < textFieldScrollerPosition3.getMaximum());
                                }
                            });
                            this.canScrollBackward$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1$canScrollBackward$2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    return Boolean.valueOf(textFieldScrollerPosition3.getOffset() > 0.0f);
                                }
                            });
                        }

                        @Override // androidx.compose.foundation.gestures.ScrollableState
                        public boolean getCanScrollForward() {
                            return ((Boolean) this.canScrollForward$delegate.getValue()).booleanValue();
                        }

                        @Override // androidx.compose.foundation.gestures.ScrollableState
                        public boolean getCanScrollBackward() {
                            return ((Boolean) this.canScrollBackward$delegate.getValue()).booleanValue();
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifierScrollable$default = ScrollableKt.scrollable$default(Modifier.Companion, (TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1) objRememberedValue2, textFieldScrollerPosition.getOrientation(), z && textFieldScrollerPosition.getMaximum() != 0.0f, z2, null, mutableInteractionSource, 16, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceGroup();
                return modifierScrollable$default;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect getCursorRectInScroller(Density density, int i, TransformedText transformedText, TextLayoutResult textLayoutResult, boolean z, int i2) {
        Rect zero;
        float left;
        float left2;
        if (textLayoutResult == null || (zero = textLayoutResult.getCursorRect(transformedText.getOffsetMapping().originalToTransformed(i))) == null) {
            zero = Rect.Companion.getZero();
        }
        Rect rect = zero;
        int iMo204roundToPx0680j_4 = density.mo204roundToPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness());
        if (z) {
            left = (i2 - rect.getLeft()) - iMo204roundToPx0680j_4;
        } else {
            left = rect.getLeft();
        }
        float f = left;
        if (z) {
            left2 = i2 - rect.getLeft();
        } else {
            left2 = rect.getLeft() + iMo204roundToPx0680j_4;
        }
        return Rect.copy$default(rect, f, 0.0f, left2, 0.0f, 10, null);
    }
}
