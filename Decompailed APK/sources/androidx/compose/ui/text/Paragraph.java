package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.text.style.TextDecoration;
import java.util.List;

/* JADX INFO: compiled from: Paragraph.android.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Paragraph {
    /* JADX INFO: renamed from: fillBoundingBoxes-8ffj60Q */
    void mo2018fillBoundingBoxes8ffj60Q(long j, float[] fArr, int i);

    ResolvedTextDirection getBidiRunDirection(int i);

    Rect getBoundingBox(int i);

    Rect getCursorRect(int i);

    boolean getDidExceedMaxLines();

    float getFirstBaseline();

    float getHeight();

    float getHorizontalPosition(int i, boolean z);

    float getLastBaseline();

    float getLineBottom(int i);

    int getLineCount();

    int getLineEnd(int i, boolean z);

    int getLineForOffset(int i);

    int getLineForVerticalPosition(float f);

    float getLineLeft(int i);

    float getLineRight(int i);

    int getLineStart(int i);

    float getLineTop(int i);

    float getMaxIntrinsicWidth();

    float getMinIntrinsicWidth();

    /* JADX INFO: renamed from: getOffsetForPosition-k-4lQ0M */
    int mo2019getOffsetForPositionk4lQ0M(long j);

    ResolvedTextDirection getParagraphDirection(int i);

    Path getPathForRange(int i, int i2);

    List getPlaceholderRects();

    /* JADX INFO: renamed from: getRangeForRect-8-6BmAI */
    long mo2020getRangeForRect86BmAI(Rect rect, int i, TextInclusionStrategy textInclusionStrategy);

    float getWidth();

    /* JADX INFO: renamed from: getWordBoundary--jx7JFs */
    long mo2021getWordBoundaryjx7JFs(int i);

    /* JADX INFO: renamed from: paint-LG529CI */
    void mo2022paintLG529CI(Canvas canvas, long j, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i);

    /* JADX INFO: renamed from: paint-hn5TExg */
    void mo2023painthn5TExg(Canvas canvas, Brush brush, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i);

    /* JADX INFO: renamed from: androidx.compose.ui.text.Paragraph$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Paragraph.android.kt */
    public abstract /* synthetic */ class CC {
        /* JADX INFO: renamed from: paint-LG529CI$default, reason: not valid java name */
        public static /* synthetic */ void m2056paintLG529CI$default(Paragraph paragraph, Canvas canvas, long j, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: paint-LG529CI");
            }
            paragraph.mo2022paintLG529CI(canvas, (i2 & 2) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j, (i2 & 4) != 0 ? null : shadow, (i2 & 8) != 0 ? null : textDecoration, (i2 & 16) == 0 ? drawStyle : null, (i2 & 32) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: paint-hn5TExg$default, reason: not valid java name */
        public static /* synthetic */ void m2057painthn5TExg$default(Paragraph paragraph, Canvas canvas, Brush brush, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: paint-hn5TExg");
            }
            paragraph.mo2023painthn5TExg(canvas, brush, (i2 & 4) != 0 ? Float.NaN : f, (i2 & 8) != 0 ? null : shadow, (i2 & 16) != 0 ? null : textDecoration, (i2 & 32) != 0 ? null : drawStyle, (i2 & 64) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : i);
        }
    }
}
