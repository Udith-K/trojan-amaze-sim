package androidx.compose.ui.draw;

import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.FontScaling;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: DrawModifier.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CacheDrawScope implements Density {
    private BuildDrawCacheParams cacheParams = EmptyBuildDrawCacheParams.INSTANCE;
    private ContentDrawScope contentDrawScope;
    private DrawResult drawResult;
    private Function0 graphicsContextProvider;

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public /* synthetic */ int mo204roundToPx0680j_4(float f) {
        return Density.CC.m2406$default$roundToPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public /* synthetic */ float mo205toDpGaN1DYA(long j) {
        return FontScaling.CC.m2449$default$toDpGaN1DYA(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public /* synthetic */ float mo206toDpu2uoSUM(float f) {
        return Dp.m2416constructorimpl(f / getDensity());
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public /* synthetic */ float mo207toDpu2uoSUM(int i) {
        return Dp.m2416constructorimpl(i / getDensity());
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public /* synthetic */ long mo208toDpSizekrfVVM(long j) {
        return Density.CC.m2409$default$toDpSizekrfVVM(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public /* synthetic */ float mo209toPxR2X_6o(long j) {
        return Density.CC.m2410$default$toPxR2X_6o(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public /* synthetic */ float mo210toPx0680j_4(float f) {
        return Density.CC.m2411$default$toPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public /* synthetic */ long mo211toSizeXkaWNTQ(long j) {
        return Density.CC.m2412$default$toSizeXkaWNTQ(this, j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public /* synthetic */ long mo212toSp0xMU5do(float f) {
        return FontScaling.CC.m2450$default$toSp0xMU5do(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public /* synthetic */ long mo213toSpkPz2Gy4(float f) {
        return mo212toSp0xMU5do(mo206toDpu2uoSUM(f));
    }

    public final void setCacheParams$ui_release(BuildDrawCacheParams buildDrawCacheParams) {
        this.cacheParams = buildDrawCacheParams;
    }

    public final DrawResult getDrawResult$ui_release() {
        return this.drawResult;
    }

    public final void setDrawResult$ui_release(DrawResult drawResult) {
        this.drawResult = drawResult;
    }

    public final void setContentDrawScope$ui_release(ContentDrawScope contentDrawScope) {
        this.contentDrawScope = contentDrawScope;
    }

    public final void setGraphicsContextProvider$ui_release(Function0 function0) {
        this.graphicsContextProvider = function0;
    }

    /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
    public final long m1077getSizeNHjbRc() {
        return this.cacheParams.mo1075getSizeNHjbRc();
    }

    public final LayoutDirection getLayoutDirection() {
        return this.cacheParams.getLayoutDirection();
    }

    public final DrawResult onDrawBehind(final Function1 function1) {
        return onDrawWithContent(new Function1() { // from class: androidx.compose.ui.draw.CacheDrawScope.onDrawBehind.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((ContentDrawScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(ContentDrawScope contentDrawScope) {
                function1.invoke(contentDrawScope);
                contentDrawScope.drawContent();
            }
        });
    }

    public final DrawResult onDrawWithContent(Function1 function1) {
        DrawResult drawResult = new DrawResult(function1);
        this.drawResult = drawResult;
        return drawResult;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.cacheParams.getDensity().getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.cacheParams.getDensity().getFontScale();
    }
}
