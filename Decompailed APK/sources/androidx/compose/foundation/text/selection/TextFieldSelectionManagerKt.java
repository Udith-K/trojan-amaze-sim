package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextDelegate;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextFieldSelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextFieldSelectionManagerKt {

    /* JADX INFO: compiled from: TextFieldSelectionManager.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Handle.values().length];
            try {
                iArr[Handle.Cursor.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Handle.SelectionStart.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Handle.SelectionEnd.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void TextFieldSelectionHandle(final boolean z, final ResolvedTextDirection resolvedTextDirection, final TextFieldSelectionManager textFieldSelectionManager, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1344558920);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(resolvedTextDirection) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(textFieldSelectionManager) ? 256 : 128;
        }
        if ((i2 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1344558920, i2, -1, "androidx.compose.foundation.text.selection.TextFieldSelectionHandle (TextFieldSelectionManager.kt:1000)");
            }
            int i3 = i2 & 14;
            boolean zChanged = (i3 == 4) | composerStartRestartGroup.changed(textFieldSelectionManager);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = textFieldSelectionManager.handleDragObserver$foundation_release(z);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            TextDragObserver textDragObserver = (TextDragObserver) objRememberedValue;
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(textFieldSelectionManager) | (i3 == 4);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.Companion.getEmpty()) {
                objRememberedValue2 = new OffsetProvider() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$1$1
                    @Override // androidx.compose.foundation.text.selection.OffsetProvider
                    /* JADX INFO: renamed from: provide-F1C5BW0 */
                    public final long mo380provideF1C5BW0() {
                        return textFieldSelectionManager.m558getHandlePositiontuRUvjQ$foundation_release(z);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            OffsetProvider offsetProvider = (OffsetProvider) objRememberedValue2;
            boolean zM2116getReversedimpl = TextRange.m2116getReversedimpl(textFieldSelectionManager.getValue$foundation_release().m2239getSelectiond9O1mEE());
            Modifier.Companion companion = Modifier.Companion;
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(textDragObserver);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue3 == Composer.Companion.getEmpty()) {
                objRememberedValue3 = new TextFieldSelectionManagerKt$TextFieldSelectionHandle$2$1(textDragObserver, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            AndroidSelectionHandles_androidKt.m532SelectionHandlepzduO1o(offsetProvider, z, resolvedTextDirection, zM2116getReversedimpl, 0L, SuspendingPointerInputFilterKt.pointerInput(companion, textDragObserver, (Function2) objRememberedValue3), composerStartRestartGroup, (i2 << 3) & 1008, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt.TextFieldSelectionHandle.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i4) {
                    TextFieldSelectionManagerKt.TextFieldSelectionHandle(z, resolvedTextDirection, textFieldSelectionManager, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static final boolean isSelectionHandleInVisibleBound(TextFieldSelectionManager textFieldSelectionManager, boolean z) {
        LayoutCoordinates layoutCoordinates;
        Rect rectVisibleBounds;
        LegacyTextFieldState state$foundation_release = textFieldSelectionManager.getState$foundation_release();
        if (state$foundation_release == null || (layoutCoordinates = state$foundation_release.getLayoutCoordinates()) == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(layoutCoordinates)) == null) {
            return false;
        }
        return SelectionManagerKt.m547containsInclusiveUv8p0NA(rectVisibleBounds, textFieldSelectionManager.m558getHandlePositiontuRUvjQ$foundation_release(z));
    }

    /* JADX INFO: renamed from: calculateSelectionMagnifierCenterAndroid-O0kMr_c, reason: not valid java name */
    public static final long m565calculateSelectionMagnifierCenterAndroidO0kMr_c(TextFieldSelectionManager textFieldSelectionManager, long j) {
        int iM2117getStartimpl;
        TextLayoutResultProxy layoutResult;
        TextDelegate textDelegate;
        AnnotatedString text;
        Offset offsetM556getCurrentDragPosition_m7T9E = textFieldSelectionManager.m556getCurrentDragPosition_m7T9E();
        if (offsetM556getCurrentDragPosition_m7T9E == null) {
            return Offset.Companion.m1170getUnspecifiedF1C5BW0();
        }
        long jM1168unboximpl = offsetM556getCurrentDragPosition_m7T9E.m1168unboximpl();
        AnnotatedString transformedText$foundation_release = textFieldSelectionManager.getTransformedText$foundation_release();
        if (transformedText$foundation_release == null || transformedText$foundation_release.length() == 0) {
            return Offset.Companion.m1170getUnspecifiedF1C5BW0();
        }
        Handle draggingHandle = textFieldSelectionManager.getDraggingHandle();
        int i = draggingHandle == null ? -1 : WhenMappings.$EnumSwitchMapping$0[draggingHandle.ordinal()];
        if (i == -1) {
            return Offset.Companion.m1170getUnspecifiedF1C5BW0();
        }
        if (i == 1 || i == 2) {
            iM2117getStartimpl = TextRange.m2117getStartimpl(textFieldSelectionManager.getValue$foundation_release().m2239getSelectiond9O1mEE());
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            iM2117getStartimpl = TextRange.m2112getEndimpl(textFieldSelectionManager.getValue$foundation_release().m2239getSelectiond9O1mEE());
        }
        LegacyTextFieldState state$foundation_release = textFieldSelectionManager.getState$foundation_release();
        if (state$foundation_release == null || (layoutResult = state$foundation_release.getLayoutResult()) == null) {
            return Offset.Companion.m1170getUnspecifiedF1C5BW0();
        }
        LegacyTextFieldState state$foundation_release2 = textFieldSelectionManager.getState$foundation_release();
        if (state$foundation_release2 == null || (textDelegate = state$foundation_release2.getTextDelegate()) == null || (text = textDelegate.getText()) == null) {
            return Offset.Companion.m1170getUnspecifiedF1C5BW0();
        }
        int iCoerceIn = RangesKt.coerceIn(textFieldSelectionManager.getOffsetMapping$foundation_release().originalToTransformed(iM2117getStartimpl), 0, text.length());
        float fM1159getXimpl = Offset.m1159getXimpl(layoutResult.m462translateDecorationToInnerCoordinatesMKHz9U$foundation_release(jM1168unboximpl));
        TextLayoutResult value = layoutResult.getValue();
        int lineForOffset = value.getLineForOffset(iCoerceIn);
        float lineLeft = value.getLineLeft(lineForOffset);
        float lineRight = value.getLineRight(lineForOffset);
        float fCoerceIn = RangesKt.coerceIn(fM1159getXimpl, Math.min(lineLeft, lineRight), Math.max(lineLeft, lineRight));
        if (!IntSize.m2474equalsimpl0(j, IntSize.Companion.m2480getZeroYbymL2g()) && Math.abs(fM1159getXimpl - fCoerceIn) > IntSize.m2476getWidthimpl(j) / 2) {
            return Offset.Companion.m1170getUnspecifiedF1C5BW0();
        }
        float lineTop = value.getLineTop(lineForOffset);
        return OffsetKt.Offset(fCoerceIn, ((value.getLineBottom(lineForOffset) - lineTop) / 2) + lineTop);
    }
}
