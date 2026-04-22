package androidx.compose.foundation.text.modifiers;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
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
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import ch.qos.logback.core.CoreConstants;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextAnnotatedStringNode.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextAnnotatedStringNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, SemanticsModifierNode {
    private MultiParagraphLayoutCache _layoutCache;
    private Map baselineCache;
    private FontFamily.Resolver fontFamilyResolver;
    private int maxLines;
    private int minLines;
    private Function1 onPlaceholderLayout;
    private Function1 onShowTranslation;
    private Function1 onTextLayout;
    private int overflow;
    private ColorProducer overrideColor;
    private List placeholders;
    private SelectionController selectionController;
    private Function1 semanticsTextLayoutResult;
    private boolean softWrap;
    private TextStyle style;
    private AnnotatedString text;
    private TextSubstitutionValue textSubstitution;

    public /* synthetic */ TextAnnotatedStringNode(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, Function1 function1, int i, boolean z, int i2, int i3, List list, Function1 function12, SelectionController selectionController, ColorProducer colorProducer, Function1 function13, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, resolver, function1, i, z, i2, i3, list, function12, selectionController, colorProducer, function13);
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

    private TextAnnotatedStringNode(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, Function1 function1, int i, boolean z, int i2, int i3, List list, Function1 function12, SelectionController selectionController, ColorProducer colorProducer, Function1 function13) {
        this.text = annotatedString;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.onTextLayout = function1;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        this.placeholders = list;
        this.onPlaceholderLayout = function12;
        this.overrideColor = colorProducer;
        this.onShowTranslation = function13;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MultiParagraphLayoutCache getLayoutCache() {
        if (this._layoutCache == null) {
            this._layoutCache = new MultiParagraphLayoutCache(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, this.placeholders, null);
        }
        MultiParagraphLayoutCache multiParagraphLayoutCache = this._layoutCache;
        Intrinsics.checkNotNull(multiParagraphLayoutCache);
        return multiParagraphLayoutCache;
    }

    private final MultiParagraphLayoutCache getLayoutCache(Density density) {
        MultiParagraphLayoutCache layoutCache;
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null && textSubstitutionValue.isShowingSubstitution() && (layoutCache = textSubstitutionValue.getLayoutCache()) != null) {
            layoutCache.setDensity$foundation_release(density);
            return layoutCache;
        }
        MultiParagraphLayoutCache layoutCache2 = getLayoutCache();
        layoutCache2.setDensity$foundation_release(density);
        return layoutCache2;
    }

    public final boolean updateDraw(ColorProducer colorProducer, TextStyle textStyle) {
        boolean zAreEqual = Intrinsics.areEqual(colorProducer, this.overrideColor);
        this.overrideColor = colorProducer;
        return (zAreEqual && textStyle.hasSameDrawAffectingAttributes(this.style)) ? false : true;
    }

    public final boolean updateText$foundation_release(AnnotatedString annotatedString) {
        boolean zAreEqual = Intrinsics.areEqual(this.text.getText(), annotatedString.getText());
        boolean z = (zAreEqual && Intrinsics.areEqual(this.text.getSpanStyles(), annotatedString.getSpanStyles()) && Intrinsics.areEqual(this.text.getParagraphStyles(), annotatedString.getParagraphStyles()) && this.text.hasEqualAnnotations(annotatedString)) ? false : true;
        if (z) {
            this.text = annotatedString;
        }
        if (!zAreEqual) {
            clearSubstitution$foundation_release();
        }
        return z;
    }

    /* JADX INFO: renamed from: updateLayoutRelatedArgs-MPT68mk, reason: not valid java name */
    public final boolean m530updateLayoutRelatedArgsMPT68mk(TextStyle textStyle, List list, int i, int i2, boolean z, FontFamily.Resolver resolver, int i3) {
        boolean z2 = !this.style.hasSameLayoutAffectingAttributes(textStyle);
        this.style = textStyle;
        if (!Intrinsics.areEqual(this.placeholders, list)) {
            this.placeholders = list;
            z2 = true;
        }
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

    public final boolean updateCallbacks(Function1 function1, Function1 function12, SelectionController selectionController, Function1 function13) {
        boolean z;
        if (this.onTextLayout != function1) {
            this.onTextLayout = function1;
            z = true;
        } else {
            z = false;
        }
        if (this.onPlaceholderLayout != function12) {
            this.onPlaceholderLayout = function12;
            z = true;
        }
        if (!Intrinsics.areEqual(this.selectionController, selectionController)) {
            z = true;
        }
        if (this.onShowTranslation == function13) {
            return z;
        }
        this.onShowTranslation = function13;
        return true;
    }

    public final void doInvalidations(boolean z, boolean z2, boolean z3, boolean z4) {
        if (z2 || z3 || z4) {
            getLayoutCache().m522updateZNqEYIc(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, this.placeholders);
        }
        if (isAttached()) {
            if (z2 || (z && this.semanticsTextLayoutResult != null)) {
                SemanticsModifierNodeKt.invalidateSemantics(this);
            }
            if (z2 || z3 || z4) {
                LayoutModifierNodeKt.invalidateMeasurement(this);
                DrawModifierNodeKt.invalidateDraw(this);
            }
            if (z) {
                DrawModifierNodeKt.invalidateDraw(this);
            }
        }
    }

    /* JADX INFO: compiled from: TextAnnotatedStringNode.kt */
    public static final class TextSubstitutionValue {
        private boolean isShowingSubstitution;
        private MultiParagraphLayoutCache layoutCache;
        private final AnnotatedString original;
        private AnnotatedString substitution;

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
            MultiParagraphLayoutCache multiParagraphLayoutCache = this.layoutCache;
            return iHashCode + (multiParagraphLayoutCache == null ? 0 : multiParagraphLayoutCache.hashCode());
        }

        public String toString() {
            return "TextSubstitutionValue(original=" + ((Object) this.original) + ", substitution=" + ((Object) this.substitution) + ", isShowingSubstitution=" + this.isShowingSubstitution + ", layoutCache=" + this.layoutCache + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public TextSubstitutionValue(AnnotatedString annotatedString, AnnotatedString annotatedString2, boolean z, MultiParagraphLayoutCache multiParagraphLayoutCache) {
            this.original = annotatedString;
            this.substitution = annotatedString2;
            this.isShowingSubstitution = z;
            this.layoutCache = multiParagraphLayoutCache;
        }

        public /* synthetic */ TextSubstitutionValue(AnnotatedString annotatedString, AnnotatedString annotatedString2, boolean z, MultiParagraphLayoutCache multiParagraphLayoutCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(annotatedString, annotatedString2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : multiParagraphLayoutCache);
        }

        public final AnnotatedString getOriginal() {
            return this.original;
        }

        public final AnnotatedString getSubstitution() {
            return this.substitution;
        }

        public final void setSubstitution(AnnotatedString annotatedString) {
            this.substitution = annotatedString;
        }

        public final boolean isShowingSubstitution() {
            return this.isShowingSubstitution;
        }

        public final void setShowingSubstitution(boolean z) {
            this.isShowingSubstitution = z;
        }

        public final MultiParagraphLayoutCache getLayoutCache() {
            return this.layoutCache;
        }

        public final void setLayoutCache(MultiParagraphLayoutCache multiParagraphLayoutCache) {
            this.layoutCache = multiParagraphLayoutCache;
        }
    }

    public final TextSubstitutionValue getTextSubstitution$foundation_release() {
        return this.textSubstitution;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean setSubstitution(AnnotatedString annotatedString) {
        Unit unit;
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null) {
            if (Intrinsics.areEqual(annotatedString, textSubstitutionValue.getSubstitution())) {
                return false;
            }
            textSubstitutionValue.setSubstitution(annotatedString);
            MultiParagraphLayoutCache layoutCache = textSubstitutionValue.getLayoutCache();
            if (layoutCache != null) {
                layoutCache.m522updateZNqEYIc(annotatedString, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, this.placeholders);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            return unit != null;
        }
        TextSubstitutionValue textSubstitutionValue2 = new TextSubstitutionValue(this.text, annotatedString, false, null, 12, null);
        MultiParagraphLayoutCache multiParagraphLayoutCache = new MultiParagraphLayoutCache(annotatedString, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, this.placeholders, null);
        multiParagraphLayoutCache.setDensity$foundation_release(getLayoutCache().getDensity$foundation_release());
        textSubstitutionValue2.setLayoutCache(multiParagraphLayoutCache);
        this.textSubstitution = textSubstitutionValue2;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invalidateForTranslate() {
        SemanticsModifierNodeKt.invalidateSemantics(this);
        LayoutModifierNodeKt.invalidateMeasurement(this);
        DrawModifierNodeKt.invalidateDraw(this);
    }

    public final void clearSubstitution$foundation_release() {
        this.textSubstitution = null;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        Function1 function1 = this.semanticsTextLayoutResult;
        if (function1 == null) {
            function1 = new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.applySemantics.1
                {
                    super(1);
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x00b8  */
                @Override // kotlin.jvm.functions.Function1
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Boolean invoke(java.util.List r38) {
                    /*
                        r37 = this;
                        r0 = r37
                        androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode r1 = androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.this
                        androidx.compose.foundation.text.modifiers.MultiParagraphLayoutCache r1 = androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.access$getLayoutCache(r1)
                        androidx.compose.ui.text.TextLayoutResult r2 = r1.getLayoutOrNull()
                        if (r2 == 0) goto Lb8
                        androidx.compose.ui.text.TextLayoutInput r1 = new androidx.compose.ui.text.TextLayoutInput
                        androidx.compose.ui.text.TextLayoutInput r3 = r2.getLayoutInput()
                        androidx.compose.ui.text.AnnotatedString r4 = r3.getText()
                        androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode r3 = androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.this
                        androidx.compose.ui.text.TextStyle r5 = androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.access$getStyle$p(r3)
                        androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode r3 = androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.this
                        androidx.compose.ui.graphics.ColorProducer r3 = androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.access$getOverrideColor$p(r3)
                        if (r3 == 0) goto L2b
                        long r6 = r3.mo670invoke0d7_KjU()
                        goto L31
                    L2b:
                        androidx.compose.ui.graphics.Color$Companion r3 = androidx.compose.ui.graphics.Color.Companion
                        long r6 = r3.m1309getUnspecified0d7_KjU()
                    L31:
                        r35 = 16777214(0xfffffe, float:2.3509884E-38)
                        r36 = 0
                        r8 = 0
                        r10 = 0
                        r11 = 0
                        r12 = 0
                        r13 = 0
                        r14 = 0
                        r15 = 0
                        r17 = 0
                        r18 = 0
                        r19 = 0
                        r20 = 0
                        r22 = 0
                        r23 = 0
                        r24 = 0
                        r25 = 0
                        r26 = 0
                        r27 = 0
                        r29 = 0
                        r30 = 0
                        r31 = 0
                        r32 = 0
                        r33 = 0
                        r34 = 0
                        androidx.compose.ui.text.TextStyle r5 = androidx.compose.ui.text.TextStyle.m2126mergedA7vx0o$default(r5, r6, r8, r10, r11, r12, r13, r14, r15, r17, r18, r19, r20, r22, r23, r24, r25, r26, r27, r29, r30, r31, r32, r33, r34, r35, r36)
                        androidx.compose.ui.text.TextLayoutInput r3 = r2.getLayoutInput()
                        java.util.List r6 = r3.getPlaceholders()
                        androidx.compose.ui.text.TextLayoutInput r3 = r2.getLayoutInput()
                        int r7 = r3.getMaxLines()
                        androidx.compose.ui.text.TextLayoutInput r3 = r2.getLayoutInput()
                        boolean r8 = r3.getSoftWrap()
                        androidx.compose.ui.text.TextLayoutInput r3 = r2.getLayoutInput()
                        int r9 = r3.m2099getOverflowgIe3tQ8()
                        androidx.compose.ui.text.TextLayoutInput r3 = r2.getLayoutInput()
                        androidx.compose.ui.unit.Density r10 = r3.getDensity()
                        androidx.compose.ui.text.TextLayoutInput r3 = r2.getLayoutInput()
                        androidx.compose.ui.unit.LayoutDirection r11 = r3.getLayoutDirection()
                        androidx.compose.ui.text.TextLayoutInput r3 = r2.getLayoutInput()
                        androidx.compose.ui.text.font.FontFamily$Resolver r12 = r3.getFontFamilyResolver()
                        androidx.compose.ui.text.TextLayoutInput r3 = r2.getLayoutInput()
                        long r13 = r3.m2098getConstraintsmsEJaDk()
                        r15 = 0
                        r3 = r1
                        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r15)
                        r6 = 2
                        r7 = 0
                        r4 = 0
                        androidx.compose.ui.text.TextLayoutResult r1 = androidx.compose.ui.text.TextLayoutResult.m2100copyO0kMr_c$default(r2, r3, r4, r6, r7)
                        if (r1 == 0) goto Lb8
                        r2 = r38
                        r2.add(r1)
                        goto Lb9
                    Lb8:
                        r1 = 0
                    Lb9:
                        if (r1 == 0) goto Lbd
                        r1 = 1
                        goto Lbe
                    Lbd:
                        r1 = 0
                    Lbe:
                        java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.AnonymousClass1.invoke(java.util.List):java.lang.Boolean");
                }
            };
            this.semanticsTextLayoutResult = function1;
        }
        SemanticsPropertiesKt.setText(semanticsPropertyReceiver, this.text);
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null) {
            SemanticsPropertiesKt.setTextSubstitution(semanticsPropertyReceiver, textSubstitutionValue.getSubstitution());
            SemanticsPropertiesKt.setShowingTextSubstitution(semanticsPropertyReceiver, textSubstitutionValue.isShowingSubstitution());
        }
        SemanticsPropertiesKt.setTextSubstitution$default(semanticsPropertyReceiver, null, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.applySemantics.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(AnnotatedString annotatedString) {
                TextAnnotatedStringNode.this.setSubstitution(annotatedString);
                TextAnnotatedStringNode.this.invalidateForTranslate();
                return Boolean.TRUE;
            }
        }, 1, null);
        SemanticsPropertiesKt.showTextSubstitution$default(semanticsPropertyReceiver, null, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.applySemantics.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Boolean) obj).booleanValue());
            }

            public final Boolean invoke(boolean z) {
                if (TextAnnotatedStringNode.this.getTextSubstitution$foundation_release() != null) {
                    Function1 function12 = TextAnnotatedStringNode.this.onShowTranslation;
                    if (function12 != null) {
                        TextSubstitutionValue textSubstitution$foundation_release = TextAnnotatedStringNode.this.getTextSubstitution$foundation_release();
                        Intrinsics.checkNotNull(textSubstitution$foundation_release);
                        function12.invoke(textSubstitution$foundation_release);
                    }
                    TextSubstitutionValue textSubstitution$foundation_release2 = TextAnnotatedStringNode.this.getTextSubstitution$foundation_release();
                    if (textSubstitution$foundation_release2 != null) {
                        textSubstitution$foundation_release2.setShowingSubstitution(z);
                    }
                    TextAnnotatedStringNode.this.invalidateForTranslate();
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
        }, 1, null);
        SemanticsPropertiesKt.clearTextSubstitution$default(semanticsPropertyReceiver, null, new Function0() { // from class: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.applySemantics.4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                TextAnnotatedStringNode.this.clearSubstitution$foundation_release();
                TextAnnotatedStringNode.this.invalidateForTranslate();
                return Boolean.TRUE;
            }
        }, 1, null);
        SemanticsPropertiesKt.getTextLayoutResult$default(semanticsPropertyReceiver, null, function1, 1, null);
    }

    /* JADX INFO: renamed from: measureNonExtension-3p2s80s, reason: not valid java name */
    public final MeasureResult m529measureNonExtension3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        return mo36measure3p2s80s(measureScope, measurable, j);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo36measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MultiParagraphLayoutCache layoutCache = getLayoutCache(measureScope);
        boolean zM521layoutWithConstraintsK40F9xA = layoutCache.m521layoutWithConstraintsK40F9xA(j, measureScope.getLayoutDirection());
        TextLayoutResult textLayoutResult = layoutCache.getTextLayoutResult();
        textLayoutResult.getMultiParagraph().getIntrinsics().getHasStaleResolvedFonts();
        if (zM521layoutWithConstraintsK40F9xA) {
            LayoutModifierNodeKt.invalidateLayer(this);
            Function1 function1 = this.onTextLayout;
            if (function1 != null) {
                function1.invoke(textLayoutResult);
            }
            Map linkedHashMap = this.baselineCache;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap(2);
            }
            linkedHashMap.put(AlignmentLineKt.getFirstBaseline(), Integer.valueOf(Math.round(textLayoutResult.getFirstBaseline())));
            linkedHashMap.put(AlignmentLineKt.getLastBaseline(), Integer.valueOf(Math.round(textLayoutResult.getLastBaseline())));
            this.baselineCache = linkedHashMap;
        }
        Function1 function12 = this.onPlaceholderLayout;
        if (function12 != null) {
            function12.invoke(textLayoutResult.getPlaceholderRects());
        }
        final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(Constraints.Companion.m2397fitPrioritizingWidthZbe2FdA(IntSize.m2476getWidthimpl(textLayoutResult.m2103getSizeYbymL2g()), IntSize.m2476getWidthimpl(textLayoutResult.m2103getSizeYbymL2g()), IntSize.m2475getHeightimpl(textLayoutResult.m2103getSizeYbymL2g()), IntSize.m2475getHeightimpl(textLayoutResult.m2103getSizeYbymL2g())));
        int iM2476getWidthimpl = IntSize.m2476getWidthimpl(textLayoutResult.m2103getSizeYbymL2g());
        int iM2475getHeightimpl = IntSize.m2475getHeightimpl(textLayoutResult.m2103getSizeYbymL2g());
        Map map = this.baselineCache;
        Intrinsics.checkNotNull(map);
        return measureScope.layout(iM2476getWidthimpl, iM2475getHeightimpl, map, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode$measure$1
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

    public final int minIntrinsicWidthNonExtension(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return minIntrinsicWidth(intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCache(intrinsicMeasureScope).minIntrinsicWidth(intrinsicMeasureScope.getLayoutDirection());
    }

    public final int minIntrinsicHeightNonExtension(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return minIntrinsicHeight(intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCache(intrinsicMeasureScope).intrinsicHeight(i, intrinsicMeasureScope.getLayoutDirection());
    }

    public final int maxIntrinsicWidthNonExtension(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return maxIntrinsicWidth(intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCache(intrinsicMeasureScope).maxIntrinsicWidth(intrinsicMeasureScope.getLayoutDirection());
    }

    public final int maxIntrinsicHeightNonExtension(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return maxIntrinsicHeight(intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCache(intrinsicMeasureScope).intrinsicHeight(i, intrinsicMeasureScope.getLayoutDirection());
    }

    public final void drawNonExtension(ContentDrawScope contentDrawScope) {
        draw(contentDrawScope);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        if (isAttached()) {
            Canvas canvas = contentDrawScope.getDrawContext().getCanvas();
            TextLayoutResult textLayoutResult = getLayoutCache(contentDrawScope).getTextLayoutResult();
            MultiParagraph multiParagraph = textLayoutResult.getMultiParagraph();
            boolean z = true;
            boolean z2 = textLayoutResult.getHasVisualOverflow() && !TextOverflow.m2372equalsimpl0(this.overflow, TextOverflow.Companion.m2377getVisiblegIe3tQ8());
            if (z2) {
                Rect rectM1183Recttz77jQw = RectKt.m1183Recttz77jQw(Offset.Companion.m1171getZeroF1C5BW0(), SizeKt.Size(IntSize.m2476getWidthimpl(textLayoutResult.m2103getSizeYbymL2g()), IntSize.m2475getHeightimpl(textLayoutResult.m2103getSizeYbymL2g())));
                canvas.save();
                Canvas.CC.m1285clipRectmtrdDE$default(canvas, rectM1183Recttz77jQw, 0, 2, null);
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
                    multiParagraph.m2054painthn5TExg(canvas, brush, (64 & 4) != 0 ? Float.NaN : this.style.getAlpha(), (64 & 8) != 0 ? null : shadow2, (64 & 16) != 0 ? null : textDecoration2, (64 & 32) != 0 ? null : drawStyle2, (64 & 64) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : 0);
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
                    multiParagraph.m2053paintLG529CI(canvas, (32 & 2) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : jMo670invoke0d7_KjU, (32 & 4) != 0 ? null : shadow2, (32 & 8) != 0 ? null : textDecoration2, (32 & 16) == 0 ? drawStyle2 : null, (32 & 32) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : 0);
                }
                if (z2) {
                    canvas.restore();
                }
                TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
                if (!((textSubstitutionValue == null || !textSubstitutionValue.isShowingSubstitution()) ? TextAnnotatedStringNodeKt.hasLinks(this.text) : false)) {
                    List list = this.placeholders;
                    if (list != null && !list.isEmpty()) {
                        z = false;
                    }
                    if (z) {
                        return;
                    }
                }
                contentDrawScope.drawContent();
            } catch (Throwable th) {
                if (z2) {
                    canvas.restore();
                }
                throw th;
            }
        }
    }
}
