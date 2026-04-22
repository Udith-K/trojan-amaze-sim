package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.InlineClassHelperKt;
import androidx.compose.ui.graphics.Path;

/* JADX INFO: compiled from: CanvasDrawScope.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CanvasDrawScopeKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final DrawTransform asDrawTransform(final DrawContext drawContext) {
        return new DrawTransform() { // from class: androidx.compose.ui.graphics.drawscope.CanvasDrawScopeKt.asDrawTransform.1
            /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
            public long m1491getSizeNHjbRc() {
                return drawContext.mo1487getSizeNHjbRc();
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            public void inset(float f, float f2, float f3, float f4) {
                Canvas canvas = drawContext.getCanvas();
                DrawContext drawContext2 = drawContext;
                long jSize = SizeKt.Size(Size.m1196getWidthimpl(m1491getSizeNHjbRc()) - (f3 + f), Size.m1194getHeightimpl(m1491getSizeNHjbRc()) - (f4 + f2));
                if (!(Size.m1196getWidthimpl(jSize) >= 0.0f && Size.m1194getHeightimpl(jSize) >= 0.0f)) {
                    InlineClassHelperKt.throwIllegalArgumentException("Width and height must be greater than or equal to zero");
                }
                drawContext2.mo1488setSizeuvyYCjk(jSize);
                canvas.translate(f, f2);
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* JADX INFO: renamed from: clipRect-N_I0leg, reason: not valid java name */
            public void mo1490clipRectN_I0leg(float f, float f2, float f3, float f4, int i) {
                drawContext.getCanvas().mo1208clipRectN_I0leg(f, f2, f3, f4, i);
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* JADX INFO: renamed from: clipPath-mtrdD-E, reason: not valid java name */
            public void mo1489clipPathmtrdDE(Path path, int i) {
                drawContext.getCanvas().mo1207clipPathmtrdDE(path, i);
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            public void translate(float f, float f2) {
                drawContext.getCanvas().translate(f, f2);
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* JADX INFO: renamed from: rotate-Uv8p0NA, reason: not valid java name */
            public void mo1492rotateUv8p0NA(float f, long j) {
                Canvas canvas = drawContext.getCanvas();
                canvas.translate(Offset.m1159getXimpl(j), Offset.m1160getYimpl(j));
                canvas.rotate(f);
                canvas.translate(-Offset.m1159getXimpl(j), -Offset.m1160getYimpl(j));
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* JADX INFO: renamed from: scale-0AR0LA0, reason: not valid java name */
            public void mo1493scale0AR0LA0(float f, float f2, long j) {
                Canvas canvas = drawContext.getCanvas();
                canvas.translate(Offset.m1159getXimpl(j), Offset.m1160getYimpl(j));
                canvas.scale(f, f2);
                canvas.translate(-Offset.m1159getXimpl(j), -Offset.m1160getYimpl(j));
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* JADX INFO: renamed from: transform-58bKbWc, reason: not valid java name */
            public void mo1494transform58bKbWc(float[] fArr) {
                drawContext.getCanvas().mo1210concat58bKbWc(fArr);
            }
        };
    }
}
