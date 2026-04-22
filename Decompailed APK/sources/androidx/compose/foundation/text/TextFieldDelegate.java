package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextPainter;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.EditProcessor;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref$ObjectRef;

/* JADX INFO: compiled from: TextFieldDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextFieldDelegate {
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: compiled from: TextFieldDelegate.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: layout-_EkL_-Y$foundation_release, reason: not valid java name */
        public final Triple m439layout_EkL_Y$foundation_release(TextDelegate textDelegate, long j, LayoutDirection layoutDirection, TextLayoutResult textLayoutResult) {
            TextLayoutResult textLayoutResultM430layoutNN6EwU = textDelegate.m430layoutNN6EwU(j, layoutDirection, textLayoutResult);
            return new Triple(Integer.valueOf(IntSize.m2476getWidthimpl(textLayoutResultM430layoutNN6EwU.m2103getSizeYbymL2g())), Integer.valueOf(IntSize.m2475getHeightimpl(textLayoutResultM430layoutNN6EwU.m2103getSizeYbymL2g())), textLayoutResultM430layoutNN6EwU);
        }

        /* JADX INFO: renamed from: draw-Q1vqE60$foundation_release, reason: not valid java name */
        public final void m438drawQ1vqE60$foundation_release(Canvas canvas, TextFieldValue textFieldValue, long j, long j2, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Paint paint, long j3) {
            if (!TextRange.m2111getCollapsedimpl(j)) {
                paint.mo1228setColor8_81llA(j3);
                m436drawHighlightLepunE(canvas, j, offsetMapping, textLayoutResult, paint);
            } else if (!TextRange.m2111getCollapsedimpl(j2)) {
                Color colorM1290boximpl = Color.m1290boximpl(textLayoutResult.getLayoutInput().getStyle().m2130getColor0d7_KjU());
                if (colorM1290boximpl.m1304unboximpl() == 16) {
                    colorM1290boximpl = null;
                }
                long jM1304unboximpl = colorM1290boximpl != null ? colorM1290boximpl.m1304unboximpl() : Color.Companion.m1305getBlack0d7_KjU();
                paint.mo1228setColor8_81llA(Color.m1294copywmQWz5c$default(jM1304unboximpl, Color.m1297getAlphaimpl(jM1304unboximpl) * 0.2f, 0.0f, 0.0f, 0.0f, 14, null));
                m436drawHighlightLepunE(canvas, j2, offsetMapping, textLayoutResult, paint);
            } else if (!TextRange.m2111getCollapsedimpl(textFieldValue.m2239getSelectiond9O1mEE())) {
                paint.mo1228setColor8_81llA(j3);
                m436drawHighlightLepunE(canvas, textFieldValue.m2239getSelectiond9O1mEE(), offsetMapping, textLayoutResult, paint);
            }
            TextPainter.INSTANCE.paint(canvas, textLayoutResult);
        }

        /* JADX INFO: renamed from: drawHighlight-Le-punE, reason: not valid java name */
        private final void m436drawHighlightLepunE(Canvas canvas, long j, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Paint paint) {
            int iOriginalToTransformed = offsetMapping.originalToTransformed(TextRange.m2115getMinimpl(j));
            int iOriginalToTransformed2 = offsetMapping.originalToTransformed(TextRange.m2114getMaximpl(j));
            if (iOriginalToTransformed != iOriginalToTransformed2) {
                canvas.drawPath(textLayoutResult.getPathForRange(iOriginalToTransformed, iOriginalToTransformed2), paint);
            }
        }

        public final void notifyFocusedRect$foundation_release(TextFieldValue textFieldValue, TextDelegate textDelegate, TextLayoutResult textLayoutResult, LayoutCoordinates layoutCoordinates, TextInputSession textInputSession, boolean z, OffsetMapping offsetMapping) {
            Rect rect;
            if (z) {
                int iOriginalToTransformed = offsetMapping.originalToTransformed(TextRange.m2114getMaximpl(textFieldValue.m2239getSelectiond9O1mEE()));
                if (iOriginalToTransformed < textLayoutResult.getLayoutInput().getText().length()) {
                    rect = textLayoutResult.getBoundingBox(iOriginalToTransformed);
                } else if (iOriginalToTransformed != 0) {
                    rect = textLayoutResult.getBoundingBox(iOriginalToTransformed - 1);
                } else {
                    rect = new Rect(0.0f, 0.0f, 1.0f, IntSize.m2475getHeightimpl(TextFieldDelegateKt.computeSizeForDefaultText$default(textDelegate.getStyle(), textDelegate.getDensity(), textDelegate.getFontFamilyResolver(), null, 0, 24, null)));
                }
                long jMo1748localToRootMKHz9U = layoutCoordinates.mo1748localToRootMKHz9U(OffsetKt.Offset(rect.getLeft(), rect.getTop()));
                textInputSession.notifyFocusedRect(RectKt.m1183Recttz77jQw(OffsetKt.Offset(Offset.m1159getXimpl(jMo1748localToRootMKHz9U), Offset.m1160getYimpl(jMo1748localToRootMKHz9U)), SizeKt.Size(rect.getWidth(), rect.getHeight())));
            }
        }

        public final void updateTextLayoutResult$foundation_release(TextInputSession textInputSession, TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResultProxy textLayoutResultProxy) {
            LayoutCoordinates decorationBoxCoordinates;
            final LayoutCoordinates innerTextFieldCoordinates = textLayoutResultProxy.getInnerTextFieldCoordinates();
            if (innerTextFieldCoordinates == null || !innerTextFieldCoordinates.isAttached() || (decorationBoxCoordinates = textLayoutResultProxy.getDecorationBoxCoordinates()) == null) {
                return;
            }
            textInputSession.updateTextLayoutResult(textFieldValue, offsetMapping, textLayoutResultProxy.getValue(), new Function1() { // from class: androidx.compose.foundation.text.TextFieldDelegate$Companion$updateTextLayoutResult$1$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m441invoke58bKbWc(((Matrix) obj).m1371unboximpl());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-58bKbWc, reason: not valid java name */
                public final void m441invoke58bKbWc(float[] fArr) {
                    if (innerTextFieldCoordinates.isAttached()) {
                        LayoutCoordinatesKt.findRootCoordinates(innerTextFieldCoordinates).mo1751transformFromEL8BTi8(innerTextFieldCoordinates, fArr);
                    }
                }
            }, SelectionManagerKt.visibleBounds(innerTextFieldCoordinates), innerTextFieldCoordinates.localBoundingBoxOf(decorationBoxCoordinates, false));
        }

        public final void onEditCommand$foundation_release(List list, EditProcessor editProcessor, Function1 function1, TextInputSession textInputSession) {
            TextFieldValue textFieldValueApply = editProcessor.apply(list);
            if (textInputSession != null) {
                textInputSession.updateState(null, textFieldValueApply);
            }
            function1.invoke(textFieldValueApply);
        }

        /* JADX INFO: renamed from: setCursorOffset-ULxng0E$foundation_release, reason: not valid java name */
        public final void m440setCursorOffsetULxng0E$foundation_release(long j, TextLayoutResultProxy textLayoutResultProxy, EditProcessor editProcessor, OffsetMapping offsetMapping, Function1 function1) {
            function1.invoke(TextFieldValue.m2236copy3r_uNRQ$default(editProcessor.toTextFieldValue(), null, TextRangeKt.TextRange(offsetMapping.transformedToOriginal(TextLayoutResultProxy.m459getOffsetForPosition3MmeM6k$default(textLayoutResultProxy, j, false, 2, null))), null, 5, null));
        }

        public final TextInputSession restartInput$foundation_release(TextInputService textInputService, TextFieldValue textFieldValue, final EditProcessor editProcessor, ImeOptions imeOptions, final Function1 function1, Function1 function12) {
            final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            TextInputSession textInputSessionStartInput = textInputService.startInput(textFieldValue, imeOptions, new Function1() { // from class: androidx.compose.foundation.text.TextFieldDelegate$Companion$restartInput$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((List) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(List list) {
                    TextFieldDelegate.Companion.onEditCommand$foundation_release(list, editProcessor, function1, (TextInputSession) ref$ObjectRef.element);
                }
            }, function12);
            ref$ObjectRef.element = textInputSessionStartInput;
            return textInputSessionStartInput;
        }

        public final TextInputSession onFocus$foundation_release(TextInputService textInputService, TextFieldValue textFieldValue, EditProcessor editProcessor, ImeOptions imeOptions, Function1 function1, Function1 function12) {
            return restartInput$foundation_release(textInputService, textFieldValue, editProcessor, imeOptions, function1, function12);
        }

        public final void onBlur$foundation_release(TextInputSession textInputSession, EditProcessor editProcessor, Function1 function1) {
            function1.invoke(TextFieldValue.m2236copy3r_uNRQ$default(editProcessor.toTextFieldValue(), null, 0L, null, 3, null));
            textInputSession.dispose();
        }

        /* JADX INFO: renamed from: applyCompositionDecoration-72CqOWE, reason: not valid java name */
        public final TransformedText m437applyCompositionDecoration72CqOWE(long j, TransformedText transformedText) {
            int iOriginalToTransformed = transformedText.getOffsetMapping().originalToTransformed(TextRange.m2117getStartimpl(j));
            int iOriginalToTransformed2 = transformedText.getOffsetMapping().originalToTransformed(TextRange.m2112getEndimpl(j));
            int iMin = Math.min(iOriginalToTransformed, iOriginalToTransformed2);
            int iMax = Math.max(iOriginalToTransformed, iOriginalToTransformed2);
            AnnotatedString.Builder builder = new AnnotatedString.Builder(transformedText.getText());
            builder.addStyle(new SpanStyle(0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, TextDecoration.Companion.getUnderline(), null, null, null, 61439, null), iMin, iMax);
            return new TransformedText(builder.toAnnotatedString(), transformedText.getOffsetMapping());
        }
    }
}
