package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Brush.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ShaderBrush extends Brush {
    private long createdSize;
    private Shader internalShader;

    /* JADX INFO: renamed from: createShader-uvyYCjk */
    public abstract Shader mo1281createShaderuvyYCjk(long j);

    public ShaderBrush() {
        super(null);
        this.createdSize = Size.Companion.m1201getUnspecifiedNHjbRc();
    }

    @Override // androidx.compose.ui.graphics.Brush
    /* JADX INFO: renamed from: applyTo-Pq9zytI */
    public final void mo1280applyToPq9zytI(long j, Paint paint, float f) {
        Shader shaderMo1281createShaderuvyYCjk = this.internalShader;
        if (shaderMo1281createShaderuvyYCjk == null || !Size.m1193equalsimpl0(this.createdSize, j)) {
            if (Size.m1198isEmptyimpl(j)) {
                shaderMo1281createShaderuvyYCjk = null;
                this.internalShader = null;
                this.createdSize = Size.Companion.m1201getUnspecifiedNHjbRc();
            } else {
                shaderMo1281createShaderuvyYCjk = mo1281createShaderuvyYCjk(j);
                this.internalShader = shaderMo1281createShaderuvyYCjk;
                this.createdSize = j;
            }
        }
        long jMo1223getColor0d7_KjU = paint.mo1223getColor0d7_KjU();
        Color.Companion companion = Color.Companion;
        if (!Color.m1296equalsimpl0(jMo1223getColor0d7_KjU, companion.m1305getBlack0d7_KjU())) {
            paint.mo1228setColor8_81llA(companion.m1305getBlack0d7_KjU());
        }
        if (!Intrinsics.areEqual(paint.getShader(), shaderMo1281createShaderuvyYCjk)) {
            paint.setShader(shaderMo1281createShaderuvyYCjk);
        }
        if (paint.getAlpha() == f) {
            return;
        }
        paint.setAlpha(f);
    }
}
