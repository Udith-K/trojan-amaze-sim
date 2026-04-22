package androidx.compose.foundation.text.modifiers;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.TextUnit;
import ch.qos.logback.core.CoreConstants;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: TextStringSimpleNode.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextStringSimpleNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, SemanticsModifierNode {
    private ParagraphLayoutCache _layoutCache;
    private Map baselineCache;
    private FontFamily.Resolver fontFamilyResolver;
    private int maxLines;
    private int minLines;
    private int overflow;
    private ColorProducer overrideColor;
    private Function1 semanticsTextLayoutResult;
    private boolean softWrap;
    private TextStyle style;
    private String text;
    private TextSubstitutionValue textSubstitution;

    public /* synthetic */ TextStringSimpleNode(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, ColorProducer colorProducer, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, resolver, i, z, i2, i3, colorProducer);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public /* synthetic */ boolean getShouldClearDescendantSemantics() {
        return SemanticsModifierNode.CC.$default$getShouldClearDescendantSemantics(this);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public /* synthetic */ boolean getShouldMergeDescendantSemantics() {
        return SemanticsModifierNode.CC.$default$getShouldMergeDescendantSemantics(this);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public /* synthetic */ void onMeasureResultChanged() {
        DrawModifierNode.CC.$default$onMeasureResultChanged(this);
    }

    private TextStringSimpleNode(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, ColorProducer colorProducer) {
        this.text = str;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        this.overrideColor = colorProducer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ParagraphLayoutCache getLayoutCache() {
        if (this._layoutCache == null) {
            this._layoutCache = new ParagraphLayoutCache(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, null);
        }
        ParagraphLayoutCache paragraphLayoutCache = this._layoutCache;
        Intrinsics.checkNotNull(paragraphLayoutCache);
        return paragraphLayoutCache;
    }

    private final ParagraphLayoutCache getLayoutCache(Density density) {
        ParagraphLayoutCache layoutCache;
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null && textSubstitutionValue.isShowingSubstitution() && (layoutCache = textSubstitutionValue.getLayoutCache()) != null) {
            layoutCache.setDensity$foundation_release(density);
            return layoutCache;
        }
        ParagraphLayoutCache layoutCache2 = getLayoutCache();
        layoutCache2.setDensity$foundation_release(density);
        return layoutCache2;
    }

    public final boolean updateDraw(ColorProducer colorProducer, TextStyle textStyle) {
        boolean zAreEqual = Intrinsics.areEqual(colorProducer, this.overrideColor);
        this.overrideColor = colorProducer;
        return (zAreEqual && textStyle.hasSameDrawAffectingAttributes(this.style)) ? false : true;
    }

    public final boolean updateText(String str) {
        if (Intrinsics.areEqual(this.text, str)) {
            return false;
        }
        this.text = str;
        clearSubstitution();
        return true;
    }

    /* JADX INFO: renamed from: updateLayoutRelatedArgs-HuAbxIM, reason: not valid java name */
    public final boolean m531updateLayoutRelatedArgsHuAbxIM(TextStyle textStyle, int i, int i2, boolean z, FontFamily.Resolver resolver, int i3) {
        boolean z2 = !this.style.hasSameLayoutAffectingAttributes(textStyle);
        this.style = textStyle;
        if (this.minLines != i) {
            this.minLines = i;
            z2 = true;
        }
        if (this.maxLines != i2) {
            this.maxLines = i2;
            z2 = true;
        }
        if (this.softWrap != z) {
            this.softWrap = z;
            z2 = true;
        }
        if (!Intrinsics.areEqual(this.fontFamilyResolver, resolver)) {
            this.fontFamilyResolver = resolver;
            z2 = true;
        }
        if (TextOverflow.m2372equalsimpl0(this.overflow, i3)) {
            return z2;
        }
        this.overflow = i3;
        return true;
    }

    public final void doInvalidations(boolean z, boolean z2, boolean z3) {
        if (z2 || z3) {
            getLayoutCache().m527updateL6sJoHM(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines);
        }
        if (isAttached()) {
            if (z2 || (z && this.semanticsTextLayoutResult != null)) {
                SemanticsModifierNodeKt.invalidateSemantics(this);
            }
            if (z2 || z3) {
                LayoutModifierNodeKt.invalidateMeasurement(this);
                DrawModifierNodeKt.invalidateDraw(this);
            }
            if (z) {
                DrawModifierNodeKt.invalidateDraw(this);
            }
        }
    }

    /* JADX INFO: compiled from: TextStringSimpleNode.kt */
    public static final class TextSubstitutionValue {
        private boolean isShowingSubstitution;
        private ParagraphLayoutCache layoutCache;
        private final String original;
        private String substitution;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TextSubstitutionValue)) {
                return false;
            }
            TextSubstitutionValue textSubstitutionValue = (TextSubstitutionValue) obj;
            return Intrinsics.areEqual(this.original, textSubstitutionValue.original) && Intrinsics.areEqual(this.substitution, textSubstitutionValue.substitution) && this.isShowingSubstitution == textSubstitutionValue.isShowingSubstitution && Intrinsics.areEqual(this.layoutCache, textSubstitutionValue.layoutCache);
        }

        public int hashCode() {
            int iHashCode = ((((this.original.hashCode() * 31) + this.substitution.hashCode()) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.isShowingSubstitution)) * 31;
            ParagraphLayoutCache paragraphLayoutCache = this.layoutCache;
            return iHashCode + (paragraphLayoutCache == null ? 0 : paragraphLayoutCache.hashCode());
        }

        public TextSubstitutionValue(String str, String str2, boolean z, ParagraphLayoutCache paragraphLayoutCache) {
            this.original = str;
            this.substitution = str2;
            this.isShowingSubstitution = z;
            this.layoutCache = paragraphLayoutCache;
        }

        public /* synthetic */ TextSubstitutionValue(String str, String str2, boolean z, ParagraphLayoutCache paragraphLayoutCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : paragraphLayoutCache);
        }

        public final String getSubstitution() {
            return this.substitution;
        }

        public final void setSubstitution(String str) {
            this.substitution = str;
        }

        public final boolean isShowingSubstitution() {
            return this.isShowingSubstitution;
        }

        public final void setShowingSubstitution(boolean z) {
            this.isShowingSubstitution = z;
        }

        public final ParagraphLayoutCache getLayoutCache() {
            return this.layoutCache;
        }

        public final void setLayoutCache(ParagraphLayoutCache paragraphLayoutCache) {
            this.layoutCache = paragraphLayoutCache;
        }

        public String toString() {
            return "TextSubstitution(layoutCache=" + this.layoutCache + ", isShowingSubstitution=" + this.isShowingSubstitution + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean setSubstitution(String str) {
        Unit unit;
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null) {
            if (Intrinsics.areEqual(str, textSubstitutionValue.getSubstitution())) {
                return false;
            }
            textSubstitutionValue.setSubstitution(str);
            ParagraphLayoutCache layoutCache = textSubstitutionValue.getLayoutCache();
            if (layoutCache != null) {
                layoutCache.m527updateL6sJoHM(str, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            return unit != null;
        }
        TextSubstitutionValue textSubstitutionValue2 = new TextSubstitutionValue(this.text, str, false, null, 12, null);
        ParagraphLayoutCache paragraphLayoutCache = new ParagraphLayoutCache(str, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, null);
        paragraphLayoutCache.setDensity$foundation_release(getLayoutCache().getDensity$foundation_release());
        textSubstitutionValue2.setLayoutCache(paragraphLayoutCache);
        this.textSubstitution = textSubstitutionValue2;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearSubstitution() {
        this.textSubstitution = null;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        Function1 function1 = this.semanticsTextLayoutResult;
        if (function1 == null) {
            function1 = new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode.applySemantics.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(List list) {
                    ParagraphLayoutCache layoutCache = TextStringSimpleNode.this.getLayoutCache();
                    TextStyle textStyle = TextStringSimpleNode.this.style;
                    ColorProducer colorProducer = TextStringSimpleNode.this.overrideColor;
                    TextLayoutResult textLayoutResultSlowCreateTextLayoutResultOrNull = layoutCache.slowCreateTextLayoutResultOrNull(textStyle.m2140mergedA7vx0o((16777214 & 1) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : colorProducer != null ? colorProducer.mo670invoke0d7_KjU() : Color.Companion.m1309getUnspecified0d7_KjU(), (16777214 & 2) != 0 ? TextUnit.Companion.m2494getUnspecifiedXSAIIZE() : 0L, (16777214 & 4) != 0 ? null : null, (16777214 & 8) != 0 ? null : null, (16777214 & 16) != 0 ? null : null, (16777214 & 32) != 0 ? null : null, (16777214 & 64) != 0 ? null : null, (16777214 & 128) != 0 ? TextUnit.Companion.m2494getUnspecifiedXSAIIZE() : 0L, (16777214 & 256) != 0 ? null : null, (16777214 & 512) != 0 ? null : null, (16777214 & 1024) != 0 ? null : null, (16777214 & 2048) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : 0L, (16777214 & PKIFailureInfo.certConfirmed) != 0 ? null : null, (16777214 & 8192) != 0 ? null : null, (16777214 & 16384) != 0 ? null : null, (16777214 & 32768) != 0 ? TextAlign.Companion.m2346getUnspecifiede0LSkKk() : 0, (16777214 & PKIFailureInfo.notAuthorized) != 0 ? TextDirection.Companion.m2359getUnspecifieds_7Xco() : 0, (16777214 & PKIFailureInfo.unsupportedVersion) != 0 ? TextUnit.Companion.m2494getUnspecifiedXSAIIZE() : 0L, (16777214 & PKIFailureInfo.transactionIdInUse) != 0 ? null : null, (16777214 & PKIFailureInfo.signerNotTrusted) != 0 ? null : null, (16777214 & PKIFailureInfo.badCertTemplate) != 0 ? LineBreak.Companion.m2298getUnspecifiedrAG3T2k() : 0, (16777214 & PKIFailureInfo.badSenderNonce) != 0 ? Hyphens.Companion.m2286getUnspecifiedvmbZdU8() : 0, (16777214 & 4194304) != 0 ? null : null, (16777214 & 8388608) != 0 ? null : null));
                    if (textLayoutResultSlowCreateTextLayoutResultOrNull != null) {
                        list.add(textLayoutResultSlowCreateTextLayoutResultOrNull);
                    } else {
                        textLayoutResultSlowCreateTextLayoutResultOrNull = null;
                    }
                    return Boolean.valueOf(textLayoutResultSlowCreateTextLayoutResultOrNull != null);
                }
            };
            this.semanticsTextLayoutResult = function1;
        }
        SemanticsPropertiesKt.setText(semanticsPropertyReceiver, new AnnotatedString(this.text, null, null, 6, null));
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null) {
            SemanticsPropertiesKt.setShowingTextSubstitution(semanticsPropertyReceiver, textSubstitutionValue.isShowingSubstitution());
            SemanticsPropertiesKt.setTextSubstitution(semanticsPropertyReceiver, new AnnotatedString(textSubstitutionValue.getSubstitution(), null, null, 6, null));
        }
        SemanticsPropertiesKt.setTextSubstitution$default(semanticsPropertyReceiver, null, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode.applySemantics.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(AnnotatedString annotatedString) {
                TextStringSimpleNode.this.setSubstitution(annotatedString.getText());
                TextStringSimpleNode.this.invalidateForTranslate();
                return Boolean.TRUE;
            }
        }, 1, null);
        SemanticsPropertiesKt.showTextSubstitution$default(semanticsPropertyReceiver, null, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode.applySemantics.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Boolean) obj).booleanValue());
            }

            public final Boolean invoke(boolean z) {
                if (TextStringSimpleNode.this.textSubstitution != null) {
                    TextSubstitutionValue textSubstitutionValue2 = TextStringSimpleNode.this.textSubstitution;
                    if (textSubstitutionValue2 != null) {
                        textSubstitutionValue2.setShowingSubstitution(z);
                    }
                    TextStringSimpleNode.this.invalidateForTranslate();
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
        }, 1, null);
        SemanticsPropertiesKt.clearTextSubstitution$default(semanticsPropertyReceiver, null, new Function0() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode.applySemantics.4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                TextStringSimpleNode.this.clearSubstitution();
                TextStringSimpleNode.this.invalidateForTranslate();
                return Boolean.TRUE;
            }
        }, 1, null);
        SemanticsPropertiesKt.getTextLayoutResult$default(semanticsPropertyReceiver, null, function1, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invalidateForTranslate() {
        SemanticsModifierNodeKt.invalidateSemantics(this);
        LayoutModifierNodeKt.invalidateMeasurement(this);
        DrawModifierNodeKt.invalidateDraw(this);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo36measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        ParagraphLayoutCache layoutCache = getLayoutCache(measureScope);
        boolean zM526layoutWithConstraintsK40F9xA = layoutCache.m526layoutWithConstraintsK40F9xA(j, measureScope.getLayoutDirection());
        layoutCache.getObserveFontChanges$foundation_release();
        Paragraph paragraph$foundation_release = layoutCache.getParagraph$foundation_release();
        Intrinsics.checkNotNull(paragraph$foundation_release);
        long jM525getLayoutSizeYbymL2g$foundation_release = layoutCache.m525getLayoutSizeYbymL2g$foundation_release();
        if (zM526layoutWithConstraintsK40F9xA) {
            LayoutModifierNodeKt.invalidateLayer(this);
            Map linkedHashMap = this.baselineCache;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap(2);
            }
            linkedHashMap.put(AlignmentLineKt.getFirstBaseline(), Integer.valueOf(Math.round(paragraph$foundation_release.getFirstBaseline())));
            linkedHashMap.put(AlignmentLineKt.getLastBaseline(), Integer.valueOf(Math.round(paragraph$foundation_release.getLastBaseline())));
            this.baselineCache = linkedHashMap;
        }
        final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(Constraints.Companion.m2397fitPrioritizingWidthZbe2FdA(IntSize.m2476getWidthimpl(jM525getLayoutSizeYbymL2g$foundation_release), IntSize.m2476getWidthimpl(jM525getLayoutSizeYbymL2g$foundation_release), IntSize.m2475getHeightimpl(jM525getLayoutSizeYbymL2g$foundation_release), IntSize.m2475getHeightimpl(jM525getLayoutSizeYbymL2g$foundation_release)));
        int iM2476getWidthimpl = IntSize.m2476getWidthimpl(jM525getLayoutSizeYbymL2g$foundation_release);
        int iM2475getHeightimpl = IntSize.m2475getHeightimpl(jM525getLayoutSizeYbymL2g$foundation_release);
        Map map = this.baselineCache;
        Intrinsics.checkNotNull(map);
        return measureScope.layout(iM2476getWidthimpl, iM2475getHeightimpl, map, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$measure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                Placeable.PlacementScope.place$default(placementScope, placeableMo1743measureBRTryo0, 0, 0, 0.0f, 4, null);
            }
        });
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCache(intrinsicMeasureScope).minIntrinsicWidth(intrinsicMeasureScope.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCache(intrinsicMeasureScope).intrinsicHeight(i, intrinsicMeasureScope.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCache(intrinsicMeasureScope).maxIntrinsicWidth(intrinsicMeasureScope.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCache(intrinsicMeasureScope).intrinsicHeight(i, intrinsicMeasureScope.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        if (isAttached()) {
            ParagraphLayoutCache layoutCache = getLayoutCache(contentDrawScope);
            Paragraph paragraph$foundation_release = layoutCache.getParagraph$foundation_release();
            if (paragraph$foundation_release != null) {
                Canvas canvas = contentDrawScope.getDrawContext().getCanvas();
                boolean didOverflow$foundation_release = layoutCache.getDidOverflow$foundation_release();
                if (didOverflow$foundation_release) {
                    float fM2476getWidthimpl = IntSize.m2476getWidthimpl(layoutCache.m525getLayoutSizeYbymL2g$foundation_release());
                    float fM2475getHeightimpl = IntSize.m2475getHeightimpl(layoutCache.m525getLayoutSizeYbymL2g$foundation_release());
                    canvas.save();
                    Canvas.CC.m1284clipRectN_I0leg$default(canvas, 0.0f, 0.0f, fM2476getWidthimpl, fM2475getHeightimpl, 0, 16, null);
                }
                try {
                    TextDecoration textDecoration = this.style.getTextDecoration();
                    if (textDecoration == null) {
                        textDecoration = TextDecoration.Companion.getNone();
                    }
                    TextDecoration textDecoration2 = textDecoration;
                    Shadow shadow = this.style.getShadow();
                    if (shadow == null) {
                        shadow = Shadow.Companion.getNone();
                    }
                    Shadow shadow2 = shadow;
                    DrawStyle drawStyle = this.style.getDrawStyle();
                    if (drawStyle == null) {
                        drawStyle = Fill.INSTANCE;
                    }
                    DrawStyle drawStyle2 = drawStyle;
                    Brush brush = this.style.getBrush();
                    if (brush != null) {
                        Paragraph.CC.m2057painthn5TExg$default(paragraph$foundation_release, canvas, brush, this.style.getAlpha(), shadow2, textDecoration2, drawStyle2, 0, 64, null);
                    } else {
                        ColorProducer colorProducer = this.overrideColor;
                        long jMo670invoke0d7_KjU = colorProducer != null ? colorProducer.mo670invoke0d7_KjU() : Color.Companion.m1309getUnspecified0d7_KjU();
                        if (jMo670invoke0d7_KjU == 16) {
                            if (this.style.m2130getColor0d7_KjU() != 16) {
                                jMo670invoke0d7_KjU = this.style.m2130getColor0d7_KjU();
                            } else {
                                jMo670invoke0d7_KjU = Color.Companion.m1305getBlack0d7_KjU();
                            }
                        }
                        Paragraph.CC.m2056paintLG529CI$default(paragraph$foundation_release, canvas, jMo670invoke0d7_KjU, shadow2, textDecoration2, drawStyle2, 0, 32, null);
                    }
                    if (didOverflow$foundation_release) {
                        canvas.restore();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    if (didOverflow$foundation_release) {
                        canvas.restore();
                    }
                    throw th;
                }
            }
            throw new IllegalArgumentException(("no paragraph (layoutCache=" + this._layoutCache + ", textSubstitution=" + this.textSubstitution + CoreConstants.RIGHT_PARENTHESIS_CHAR).toString());
        }
    }
}
