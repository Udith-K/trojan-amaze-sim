package androidx.compose.ui.graphics.vector;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import kotlin.Unit;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: VectorPainter.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class VectorPainterKt {
    public static final VectorPainter rememberVectorPainter(ImageVector imageVector, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1413834416, i, -1, "androidx.compose.ui.graphics.vector.rememberVectorPainter (VectorPainter.kt:171)");
        }
        Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        float genId$ui_release = imageVector.getGenId$ui_release();
        boolean zChanged = composer.changed((((long) Float.floatToRawIntBits(density.getDensity())) & BodyPartID.bodyIdMax) | (Float.floatToRawIntBits(genId$ui_release) << 32));
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            GroupComponent groupComponent = new GroupComponent();
            createGroupComponent(groupComponent, imageVector.getRoot());
            Unit unit = Unit.INSTANCE;
            objRememberedValue = createVectorPainterFromImageVector(density, imageVector, groupComponent);
            composer.updateRememberedValue(objRememberedValue);
        }
        VectorPainter vectorPainter = (VectorPainter) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return vectorPainter;
    }

    /* JADX INFO: renamed from: obtainSizePx-VpY3zN4, reason: not valid java name */
    private static final long m1573obtainSizePxVpY3zN4(Density density, float f, float f2) {
        return SizeKt.Size(density.mo210toPx0680j_4(f), density.mo210toPx0680j_4(f2));
    }

    /* JADX INFO: renamed from: obtainViewportSize-Pq9zytI, reason: not valid java name */
    private static final long m1574obtainViewportSizePq9zytI(long j, float f, float f2) {
        if (Float.isNaN(f)) {
            f = Size.m1196getWidthimpl(j);
        }
        if (Float.isNaN(f2)) {
            f2 = Size.m1194getHeightimpl(j);
        }
        return SizeKt.Size(f, f2);
    }

    /* JADX INFO: renamed from: createColorFilter-xETnrds, reason: not valid java name */
    private static final ColorFilter m1572createColorFilterxETnrds(long j, int i) {
        if (j != 16) {
            return ColorFilter.Companion.m1312tintxETnrds(j, i);
        }
        return null;
    }

    /* JADX INFO: renamed from: configureVectorPainter-T4PVSW8, reason: not valid java name */
    public static final VectorPainter m1571configureVectorPainterT4PVSW8(VectorPainter vectorPainter, long j, long j2, String str, ColorFilter colorFilter, boolean z) {
        vectorPainter.m1568setSizeuvyYCjk$ui_release(j);
        vectorPainter.setAutoMirror$ui_release(z);
        vectorPainter.setIntrinsicColorFilter$ui_release(colorFilter);
        vectorPainter.m1569setViewportSizeuvyYCjk$ui_release(j2);
        vectorPainter.setName$ui_release(str);
        return vectorPainter;
    }

    public static final VectorPainter createVectorPainterFromImageVector(Density density, ImageVector imageVector, GroupComponent groupComponent) {
        long jM1573obtainSizePxVpY3zN4 = m1573obtainSizePxVpY3zN4(density, imageVector.m1554getDefaultWidthD9Ej5fM(), imageVector.m1553getDefaultHeightD9Ej5fM());
        return m1571configureVectorPainterT4PVSW8(new VectorPainter(groupComponent), jM1573obtainSizePxVpY3zN4, m1574obtainViewportSizePq9zytI(jM1573obtainSizePxVpY3zN4, imageVector.getViewportWidth(), imageVector.getViewportHeight()), imageVector.getName(), m1572createColorFilterxETnrds(imageVector.m1556getTintColor0d7_KjU(), imageVector.m1555getTintBlendMode0nO6VwU()), imageVector.getAutoMirror());
    }

    public static final GroupComponent createGroupComponent(GroupComponent groupComponent, VectorGroup vectorGroup) {
        int size = vectorGroup.getSize();
        for (int i = 0; i < size; i++) {
            VectorNode vectorNode = vectorGroup.get(i);
            if (vectorNode instanceof VectorPath) {
                PathComponent pathComponent = new PathComponent();
                VectorPath vectorPath = (VectorPath) vectorNode;
                pathComponent.setPathData(vectorPath.getPathData());
                pathComponent.m1559setPathFillTypeoQ8Xj4U(vectorPath.m1575getPathFillTypeRgk1Os());
                pathComponent.setName(vectorPath.getName());
                pathComponent.setFill(vectorPath.getFill());
                pathComponent.setFillAlpha(vectorPath.getFillAlpha());
                pathComponent.setStroke(vectorPath.getStroke());
                pathComponent.setStrokeAlpha(vectorPath.getStrokeAlpha());
                pathComponent.setStrokeLineWidth(vectorPath.getStrokeLineWidth());
                pathComponent.m1560setStrokeLineCapBeK7IIE(vectorPath.m1576getStrokeLineCapKaPHkGw());
                pathComponent.m1561setStrokeLineJoinWw9F2mQ(vectorPath.m1577getStrokeLineJoinLxFBmk8());
                pathComponent.setStrokeLineMiter(vectorPath.getStrokeLineMiter());
                pathComponent.setTrimPathStart(vectorPath.getTrimPathStart());
                pathComponent.setTrimPathEnd(vectorPath.getTrimPathEnd());
                pathComponent.setTrimPathOffset(vectorPath.getTrimPathOffset());
                groupComponent.insertAt(i, pathComponent);
            } else if (vectorNode instanceof VectorGroup) {
                GroupComponent groupComponent2 = new GroupComponent();
                VectorGroup vectorGroup2 = (VectorGroup) vectorNode;
                groupComponent2.setName(vectorGroup2.getName());
                groupComponent2.setRotation(vectorGroup2.getRotation());
                groupComponent2.setScaleX(vectorGroup2.getScaleX());
                groupComponent2.setScaleY(vectorGroup2.getScaleY());
                groupComponent2.setTranslationX(vectorGroup2.getTranslationX());
                groupComponent2.setTranslationY(vectorGroup2.getTranslationY());
                groupComponent2.setPivotX(vectorGroup2.getPivotX());
                groupComponent2.setPivotY(vectorGroup2.getPivotY());
                groupComponent2.setClipPathData(vectorGroup2.getClipPathData());
                createGroupComponent(groupComponent2, vectorGroup2);
                groupComponent.insertAt(i, groupComponent2);
            }
        }
        return groupComponent;
    }
}
