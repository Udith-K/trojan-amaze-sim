package androidx.compose.material3;

import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.material3.tokens.ProgressIndicatorTokens;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ProgressIndicator.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ProgressIndicatorKt {
    private static final CubicBezierEasing CircularEasing;
    private static final float CircularIndicatorDiameter;
    private static final CubicBezierEasing FirstLineHeadEasing;
    private static final CubicBezierEasing FirstLineTailEasing;
    private static final Modifier IncreaseSemanticsBounds;
    private static final float LinearIndicatorHeight;
    private static final float LinearIndicatorWidth;
    private static final CubicBezierEasing SecondLineHeadEasing;
    private static final CubicBezierEasing SecondLineTailEasing;
    private static final float SemanticsBoundsPadding;

    /* JADX WARN: Removed duplicated region for block: B:108:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x022c  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: LinearProgressIndicator-rIrjwxo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m717LinearProgressIndicatorrIrjwxo(androidx.compose.ui.Modifier r28, long r29, long r31, int r33, float r34, androidx.compose.runtime.Composer r35, final int r36, final int r37) {
        /*
            Method dump skipped, instruction units count: 706
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m717LinearProgressIndicatorrIrjwxo(androidx.compose.ui.Modifier, long, long, int, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawLinearIndicator-qYKTg0g, reason: not valid java name */
    public static final void m724drawLinearIndicatorqYKTg0g(DrawScope drawScope, float f, float f2, long j, float f3, int i) {
        float fM1196getWidthimpl = Size.m1196getWidthimpl(drawScope.mo1483getSizeNHjbRc());
        float fM1194getHeightimpl = Size.m1194getHeightimpl(drawScope.mo1483getSizeNHjbRc());
        float f4 = 2;
        float f5 = fM1194getHeightimpl / f4;
        boolean z = drawScope.getLayoutDirection() == LayoutDirection.Ltr;
        float f6 = (z ? f : 1.0f - f2) * fM1196getWidthimpl;
        float f7 = (z ? f2 : 1.0f - f) * fM1196getWidthimpl;
        if (StrokeCap.m1412equalsimpl0(i, StrokeCap.Companion.m1415getButtKaPHkGw()) || fM1194getHeightimpl > fM1196getWidthimpl) {
            DrawScope.CC.m1503drawLineNGM6Ib0$default(drawScope, j, OffsetKt.Offset(f6, f5), OffsetKt.Offset(f7, f5), f3, 0, null, 0.0f, null, 0, 496, null);
            return;
        }
        float f8 = f3 / f4;
        ClosedFloatingPointRange closedFloatingPointRangeRangeTo = RangesKt.rangeTo(f8, fM1196getWidthimpl - f8);
        float fFloatValue = ((Number) RangesKt.coerceIn(Float.valueOf(f6), closedFloatingPointRangeRangeTo)).floatValue();
        float fFloatValue2 = ((Number) RangesKt.coerceIn(Float.valueOf(f7), closedFloatingPointRangeRangeTo)).floatValue();
        if (Math.abs(f2 - f) > 0.0f) {
            DrawScope.CC.m1503drawLineNGM6Ib0$default(drawScope, j, OffsetKt.Offset(fFloatValue, f5), OffsetKt.Offset(fFloatValue2, f5), f3, i, null, 0.0f, null, 0, 480, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0274  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: CircularProgressIndicator-LxG7B9w, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m716CircularProgressIndicatorLxG7B9w(androidx.compose.ui.Modifier r36, long r37, float r39, long r40, int r42, androidx.compose.runtime.Composer r43, final int r44, final int r45) {
        /*
            Method dump skipped, instruction units count: 793
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m716CircularProgressIndicatorLxG7B9w(androidx.compose.ui.Modifier, long, float, long, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: drawCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m721drawCircularIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, Stroke stroke) {
        float f3 = 2;
        float width = stroke.getWidth() / f3;
        float fM1196getWidthimpl = Size.m1196getWidthimpl(drawScope.mo1483getSizeNHjbRc()) - (f3 * width);
        DrawScope.CC.m1498drawArcyD3GUKo$default(drawScope, j, f, f2, false, OffsetKt.Offset(width, width), SizeKt.Size(fM1196getWidthimpl, fM1196getWidthimpl), 0.0f, stroke, null, 0, 832, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawCircularIndicatorTrack-bw27NRU, reason: not valid java name */
    public static final void m722drawCircularIndicatorTrackbw27NRU(DrawScope drawScope, long j, Stroke stroke) {
        m721drawCircularIndicator42QJj7c(drawScope, 0.0f, 360.0f, j, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawIndeterminateCircularIndicator-hrjfTZI, reason: not valid java name */
    public static final void m723drawIndeterminateCircularIndicatorhrjfTZI(DrawScope drawScope, float f, float f2, float f3, long j, Stroke stroke) {
        m721drawCircularIndicator42QJj7c(drawScope, f + (StrokeCap.m1412equalsimpl0(stroke.m1511getCapKaPHkGw(), StrokeCap.Companion.m1415getButtKaPHkGw()) ? 0.0f : ((f2 / Dp.m2416constructorimpl(CircularIndicatorDiameter / 2)) * 57.29578f) / 2.0f), Math.max(f3, 0.1f), j, stroke);
    }

    static {
        float fM2416constructorimpl = Dp.m2416constructorimpl(10);
        SemanticsBoundsPadding = fM2416constructorimpl;
        IncreaseSemanticsBounds = PaddingKt.m263paddingVpY3zN4$default(SemanticsModifierKt.semantics(LayoutModifierKt.layout(Modifier.Companion, new Function3() { // from class: androidx.compose.material3.ProgressIndicatorKt$IncreaseSemanticsBounds$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return m725invoke3p2s80s((MeasureScope) obj, (Measurable) obj2, ((Constraints) obj3).m2395unboximpl());
            }

            /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m725invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                final int iMo204roundToPx0680j_4 = measureScope.mo204roundToPx0680j_4(ProgressIndicatorKt.SemanticsBoundsPadding);
                int i = iMo204roundToPx0680j_4 * 2;
                final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(ConstraintsKt.m2404offsetNN6EwU(j, 0, i));
                return MeasureScope.CC.layout$default(measureScope, placeableMo1743measureBRTryo0.getWidth(), placeableMo1743measureBRTryo0.getHeight() - i, null, new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$IncreaseSemanticsBounds$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((Placeable.PlacementScope) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Placeable.PlacementScope placementScope) {
                        Placeable.PlacementScope.place$default(placementScope, placeableMo1743measureBRTryo0, 0, -iMo204roundToPx0680j_4, 0.0f, 4, null);
                    }
                }, 4, null);
            }
        }), true, new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$IncreaseSemanticsBounds$2
            public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SemanticsPropertyReceiver) obj);
                return Unit.INSTANCE;
            }
        }), 0.0f, fM2416constructorimpl, 1, null);
        LinearIndicatorWidth = Dp.m2416constructorimpl(240);
        ProgressIndicatorTokens progressIndicatorTokens = ProgressIndicatorTokens.INSTANCE;
        LinearIndicatorHeight = progressIndicatorTokens.m951getTrackThicknessD9Ej5fM();
        CircularIndicatorDiameter = Dp.m2416constructorimpl(progressIndicatorTokens.m949getSizeD9Ej5fM() - Dp.m2416constructorimpl(progressIndicatorTokens.m951getTrackThicknessD9Ej5fM() * 2));
        FirstLineHeadEasing = new CubicBezierEasing(0.2f, 0.0f, 0.8f, 1.0f);
        FirstLineTailEasing = new CubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f);
        SecondLineHeadEasing = new CubicBezierEasing(0.0f, 0.0f, 0.65f, 1.0f);
        SecondLineTailEasing = new CubicBezierEasing(0.1f, 0.0f, 0.45f, 1.0f);
        CircularEasing = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);
    }
}
