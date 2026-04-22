package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.CursorAnchorInfo;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LegacyCursorAnchorInfoController.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LegacyCursorAnchorInfoController {
    private Rect decorationBoxBounds;
    private boolean hasPendingImmediateRequest;
    private boolean includeCharacterBounds;
    private boolean includeEditorBounds;
    private boolean includeInsertionMarker;
    private boolean includeLineBounds;
    private Rect innerTextFieldBounds;
    private final InputMethodManager inputMethodManager;
    private final Function1 localToScreen;
    private boolean monitorEnabled;
    private OffsetMapping offsetMapping;
    private TextFieldValue textFieldValue;
    private TextLayoutResult textLayoutResult;
    private final Object lock = new Object();
    private final CursorAnchorInfo.Builder builder = new CursorAnchorInfo.Builder();
    private final float[] matrix = Matrix.m1356constructorimpl$default(null, 1, null);
    private final android.graphics.Matrix androidMatrix = new android.graphics.Matrix();

    public LegacyCursorAnchorInfoController(Function1 function1, InputMethodManager inputMethodManager) {
        this.localToScreen = function1;
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

    public final void updateTextLayoutResult(TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Rect rect, Rect rect2) {
        synchronized (this.lock) {
            try {
                this.textFieldValue = textFieldValue;
                this.offsetMapping = offsetMapping;
                this.textLayoutResult = textLayoutResult;
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
            this.innerTextFieldBounds = null;
            this.decorationBoxBounds = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void updateCursorAnchorInfo() {
        if (!this.inputMethodManager.isActive() || this.textFieldValue == null || this.offsetMapping == null || this.textLayoutResult == null || this.innerTextFieldBounds == null || this.decorationBoxBounds == null) {
            return;
        }
        Matrix.m1361resetimpl(this.matrix);
        this.localToScreen.invoke(Matrix.m1354boximpl(this.matrix));
        float[] fArr = this.matrix;
        Rect rect = this.decorationBoxBounds;
        Intrinsics.checkNotNull(rect);
        float f = -rect.getLeft();
        Rect rect2 = this.decorationBoxBounds;
        Intrinsics.checkNotNull(rect2);
        Matrix.m1369translateimpl(fArr, f, -rect2.getTop(), 0.0f);
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
        Rect rect3 = this.innerTextFieldBounds;
        Intrinsics.checkNotNull(rect3);
        Rect rect4 = this.decorationBoxBounds;
        Intrinsics.checkNotNull(rect4);
        inputMethodManager.updateCursorAnchorInfo(LegacyCursorAnchorInfoBuilder_androidKt.build(builder, textFieldValue, offsetMapping, textLayoutResult, matrix, rect3, rect4, this.includeInsertionMarker, this.includeCharacterBounds, this.includeEditorBounds, this.includeLineBounds));
        this.hasPendingImmediateRequest = false;
    }
}
