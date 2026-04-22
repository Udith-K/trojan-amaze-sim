package androidx.compose.ui.text.input;

import android.view.inputmethod.CursorAnchorInfo;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.input.pointer.PositionCalculator;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CursorAnchorInfoController.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CursorAnchorInfoController {
    private Rect decorationBoxBounds;
    private boolean hasPendingImmediateRequest;
    private boolean includeCharacterBounds;
    private boolean includeEditorBounds;
    private boolean includeInsertionMarker;
    private boolean includeLineBounds;
    private Rect innerTextFieldBounds;
    private final InputMethodManager inputMethodManager;
    private boolean monitorEnabled;
    private OffsetMapping offsetMapping;
    private final PositionCalculator rootPositionCalculator;
    private TextFieldValue textFieldValue;
    private TextLayoutResult textLayoutResult;
    private final Object lock = new Object();
    private Function1 textFieldToRootTransform = new Function1() { // from class: androidx.compose.ui.text.input.CursorAnchorInfoController$textFieldToRootTransform$1
        /* JADX INFO: renamed from: invoke-58bKbWc, reason: not valid java name */
        public final void m2183invoke58bKbWc(float[] fArr) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m2183invoke58bKbWc(((Matrix) obj).m1371unboximpl());
            return Unit.INSTANCE;
        }
    };
    private final CursorAnchorInfo.Builder builder = new CursorAnchorInfo.Builder();
    private final float[] matrix = Matrix.m1356constructorimpl$default(null, 1, null);
    private final android.graphics.Matrix androidMatrix = new android.graphics.Matrix();

    public CursorAnchorInfoController(PositionCalculator positionCalculator, InputMethodManager inputMethodManager) {
        this.rootPositionCalculator = positionCalculator;
        this.inputMethodManager = inputMethodManager;
    }

    public final void requestUpdate(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        synchronized (this.lock) {
            try {
                this.includeInsertionMarker = z3;
                this.includeCharacterBounds = z4;
                this.includeEditorBounds = z5;
                this.includeLineBounds = z6;
                if (z) {
                    this.hasPendingImmediateRequest = true;
                    if (this.textFieldValue != null) {
                        updateCursorAnchorInfo();
                    }
                }
                this.monitorEnabled = z2;
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void updateTextLayoutResult(TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Function1 function1, Rect rect, Rect rect2) {
        synchronized (this.lock) {
            try {
                this.textFieldValue = textFieldValue;
                this.offsetMapping = offsetMapping;
                this.textLayoutResult = textLayoutResult;
                this.textFieldToRootTransform = function1;
                this.innerTextFieldBounds = rect;
                this.decorationBoxBounds = rect2;
                if (this.hasPendingImmediateRequest || this.monitorEnabled) {
                    updateCursorAnchorInfo();
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void invalidate() {
        synchronized (this.lock) {
            this.textFieldValue = null;
            this.offsetMapping = null;
            this.textLayoutResult = null;
            this.textFieldToRootTransform = new Function1() { // from class: androidx.compose.ui.text.input.CursorAnchorInfoController$invalidate$1$1
                /* JADX INFO: renamed from: invoke-58bKbWc, reason: not valid java name */
                public final void m2182invoke58bKbWc(float[] fArr) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m2182invoke58bKbWc(((Matrix) obj).m1371unboximpl());
                    return Unit.INSTANCE;
                }
            };
            this.innerTextFieldBounds = null;
            this.decorationBoxBounds = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void updateCursorAnchorInfo() {
        if (this.inputMethodManager.isActive()) {
            this.textFieldToRootTransform.invoke(Matrix.m1354boximpl(this.matrix));
            this.rootPositionCalculator.mo1709localToScreen58bKbWc(this.matrix);
            AndroidMatrixConversions_androidKt.m1220setFromEL8BTi8(this.androidMatrix, this.matrix);
            InputMethodManager inputMethodManager = this.inputMethodManager;
            CursorAnchorInfo.Builder builder = this.builder;
            TextFieldValue textFieldValue = this.textFieldValue;
            Intrinsics.checkNotNull(textFieldValue);
            OffsetMapping offsetMapping = this.offsetMapping;
            Intrinsics.checkNotNull(offsetMapping);
            TextLayoutResult textLayoutResult = this.textLayoutResult;
            Intrinsics.checkNotNull(textLayoutResult);
            android.graphics.Matrix matrix = this.androidMatrix;
            Rect rect = this.innerTextFieldBounds;
            Intrinsics.checkNotNull(rect);
            Rect rect2 = this.decorationBoxBounds;
            Intrinsics.checkNotNull(rect2);
            inputMethodManager.updateCursorAnchorInfo(CursorAnchorInfoBuilder_androidKt.build(builder, textFieldValue, offsetMapping, textLayoutResult, matrix, rect, rect2, this.includeInsertionMarker, this.includeCharacterBounds, this.includeEditorBounds, this.includeLineBounds));
            this.hasPendingImmediateRequest = false;
        }
    }
}
