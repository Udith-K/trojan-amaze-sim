package androidx.compose.foundation.text;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.UriHandler;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextLinkStyles;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Arrays;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

/* JADX INFO: compiled from: TextLinkScope.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextLinkScope {
    private final SnapshotStateList annotators;
    private final AnnotatedString initialText;
    private AnnotatedString text;
    private final MutableState textLayoutResult$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    public TextLinkScope(AnnotatedString annotatedString) {
        SpanStyle style;
        this.initialText = annotatedString;
        AnnotatedString.Builder builder = new AnnotatedString.Builder(annotatedString);
        List linkAnnotations = annotatedString.getLinkAnnotations(0, annotatedString.length());
        int size = linkAnnotations.size();
        for (int i = 0; i < size; i++) {
            AnnotatedString.Range range = (AnnotatedString.Range) linkAnnotations.get(i);
            TextLinkStyles styles = ((LinkAnnotation) range.getItem()).getStyles();
            if (styles != null && (style = styles.getStyle()) != null) {
                builder.addStyle(style, range.getStart(), range.getEnd());
            }
        }
        this.text = builder.toAnnotatedString();
        this.annotators = SnapshotStateKt.mutableStateListOf();
    }

    public final TextLayoutResult getTextLayoutResult() {
        return (TextLayoutResult) this.textLayoutResult$delegate.getValue();
    }

    public final void setTextLayoutResult(TextLayoutResult textLayoutResult) {
        this.textLayoutResult$delegate.setValue(textLayoutResult);
    }

    public final AnnotatedString getText$foundation_release() {
        return this.text;
    }

    public final Function0 getShouldMeasureLinks() {
        return new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$shouldMeasureLinks$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                TextLayoutInput layoutInput;
                AnnotatedString text$foundation_release = this.this$0.getText$foundation_release();
                TextLayoutResult textLayoutResult = this.this$0.getTextLayoutResult();
                return Boolean.valueOf(Intrinsics.areEqual(text$foundation_release, (textLayoutResult == null || (layoutInput = textLayoutResult.getLayoutInput()) == null) ? null : layoutInput.getText()));
            }
        };
    }

    private final Modifier textRange(Modifier modifier, final AnnotatedString.Range range) {
        return modifier.then(new TextRangeLayoutModifier(new TextRangeScopeMeasurePolicy() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda0
            @Override // androidx.compose.foundation.text.TextRangeScopeMeasurePolicy
            public final TextRangeLayoutMeasureResult measure(TextRangeLayoutMeasureScope textRangeLayoutMeasureScope) {
                return TextLinkScope.textRange$lambda$3(this.f$0, range, textRangeLayoutMeasureScope);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextRangeLayoutMeasureResult textRange$lambda$3(TextLinkScope textLinkScope, AnnotatedString.Range range, TextRangeLayoutMeasureScope textRangeLayoutMeasureScope) {
        TextLayoutResult textLayoutResult = textLinkScope.getTextLayoutResult();
        if (textLayoutResult == null) {
            return textRangeLayoutMeasureScope.layout(0, 0, new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$textRange$1$layoutResult$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    return IntOffset.m2451boximpl(m468invokenOccac());
                }

                /* JADX INFO: renamed from: invoke-nOcc-ac, reason: not valid java name */
                public final long m468invokenOccac() {
                    return IntOffset.Companion.m2464getZeronOccac();
                }
            });
        }
        AnnotatedString.Range rangeCalculateVisibleLinkRange = textLinkScope.calculateVisibleLinkRange(range, textLayoutResult);
        if (rangeCalculateVisibleLinkRange == null) {
            return textRangeLayoutMeasureScope.layout(0, 0, new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$textRange$1$updatedRange$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    return IntOffset.m2451boximpl(m469invokenOccac());
                }

                /* JADX INFO: renamed from: invoke-nOcc-ac, reason: not valid java name */
                public final long m469invokenOccac() {
                    return IntOffset.Companion.m2464getZeronOccac();
                }
            });
        }
        final IntRect intRectRoundToIntRect = IntRectKt.roundToIntRect(textLayoutResult.getPathForRange(rangeCalculateVisibleLinkRange.getStart(), rangeCalculateVisibleLinkRange.getEnd()).getBounds());
        return textRangeLayoutMeasureScope.layout(intRectRoundToIntRect.getWidth(), intRectRoundToIntRect.getHeight(), new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$textRange$1$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                return IntOffset.m2451boximpl(m467invokenOccac());
            }

            /* JADX INFO: renamed from: invoke-nOcc-ac, reason: not valid java name */
            public final long m467invokenOccac() {
                return intRectRoundToIntRect.m2469getTopLeftnOccac();
            }
        });
    }

    private final Modifier clipLink(Modifier modifier, final AnnotatedString.Range range) {
        return GraphicsLayerModifierKt.graphicsLayer(modifier, new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope.clipLink.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((GraphicsLayerScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(GraphicsLayerScope graphicsLayerScope) {
                Shape shapeShapeForRange = TextLinkScope.this.shapeForRange(range);
                if (shapeShapeForRange != null) {
                    graphicsLayerScope.setShape(shapeShapeForRange);
                    graphicsLayerScope.setClip(true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Shape shapeForRange(AnnotatedString.Range range) {
        final Path pathPathForRangeInRangeCoordinates = pathForRangeInRangeCoordinates(range);
        if (pathPathForRangeInRangeCoordinates != null) {
            return new Shape() { // from class: androidx.compose.foundation.text.TextLinkScope$shapeForRange$1$1
                @Override // androidx.compose.ui.graphics.Shape
                /* JADX INFO: renamed from: createOutline-Pq9zytI */
                public Outline mo125createOutlinePq9zytI(long j, LayoutDirection layoutDirection, Density density) {
                    return new Outline.Generic(pathPathForRangeInRangeCoordinates);
                }
            };
        }
        return null;
    }

    private final Path pathForRangeInRangeCoordinates(AnnotatedString.Range range) {
        TextLayoutResult textLayoutResult;
        Path pathForRange = null;
        if (((Boolean) getShouldMeasureLinks().invoke()).booleanValue() && (textLayoutResult = getTextLayoutResult()) != null) {
            AnnotatedString.Range rangeCalculateVisibleLinkRange = calculateVisibleLinkRange(range, textLayoutResult);
            if (rangeCalculateVisibleLinkRange == null) {
                return null;
            }
            pathForRange = textLayoutResult.getPathForRange(rangeCalculateVisibleLinkRange.getStart(), rangeCalculateVisibleLinkRange.getEnd());
            Rect boundingBox = textLayoutResult.getBoundingBox(rangeCalculateVisibleLinkRange.getStart());
            pathForRange.mo1243translatek4lQ0M(Offset.m1167unaryMinusF1C5BW0(OffsetKt.Offset(textLayoutResult.getLineForOffset(rangeCalculateVisibleLinkRange.getStart()) == textLayoutResult.getLineForOffset(rangeCalculateVisibleLinkRange.getEnd() + (-1)) ? Math.min(textLayoutResult.getBoundingBox(rangeCalculateVisibleLinkRange.getEnd() - 1).getLeft(), boundingBox.getLeft()) : 0.0f, boundingBox.getTop())));
        }
        return pathForRange;
    }

    private final AnnotatedString.Range calculateVisibleLinkRange(AnnotatedString.Range range, TextLayoutResult textLayoutResult) {
        int lineEnd$default = TextLayoutResult.getLineEnd$default(textLayoutResult, textLayoutResult.getLineCount() - 1, false, 2, null);
        if (range.getStart() < lineEnd$default) {
            return AnnotatedString.Range.copy$default(range, null, 0, Math.min(range.getEnd(), lineEnd$default), null, 11, null);
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v5 */
    public final void LinksComposables(Composer composer, final int i) {
        int i2;
        char c;
        UriHandler uriHandler;
        char c2;
        int i3;
        ?? r2 = 0;
        char c3 = 3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1154651354);
        char c4 = 6;
        int i4 = 2;
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(this) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1154651354, i2, -1, "androidx.compose.foundation.text.TextLinkScope.LinksComposables (TextLinkScope.kt:200)");
            }
            final UriHandler uriHandler2 = (UriHandler) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalUriHandler());
            AnnotatedString annotatedString = this.text;
            List linkAnnotations = annotatedString.getLinkAnnotations(0, annotatedString.length());
            int size = linkAnnotations.size();
            int i5 = 0;
            while (i5 < size) {
                final AnnotatedString.Range range = (AnnotatedString.Range) linkAnnotations.get(i5);
                if (range.getStart() != range.getEnd()) {
                    composerStartRestartGroup.startReplaceGroup(1385536272);
                    Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                    Composer.Companion companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
                    Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(PointerIconKt.pointerHoverIcon$default(HoverableKt.hoverable$default(textRange(clipLink(Modifier.Companion, range), range), mutableInteractionSource, r2, i4, null), PointerIcon.Companion.getHand(), r2, i4, null), r2, new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope$LinksComposables$1$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((SemanticsPropertyReceiver) obj);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.invisibleToUser(semanticsPropertyReceiver);
                        }
                    }, 1, null);
                    boolean zChangedInstance = composerStartRestartGroup.changedInstance(this) | composerStartRestartGroup.changed(range) | composerStartRestartGroup.changedInstance(uriHandler2);
                    Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$LinksComposables$1$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Object invoke() {
                                m466invoke();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                            public final void m466invoke() {
                                this.this$0.handleLink((LinkAnnotation) range.getItem(), uriHandler2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    BoxKt.Box(ClickableKt.m117combinedClickableXVZzFYc(modifierSemantics$default, mutableInteractionSource, null, (252 & 4) != 0, (252 & 8) != 0 ? null : null, (252 & 16) != 0 ? null : null, (252 & 32) != 0 ? null : null, (252 & 64) != 0 ? null : null, (252 & 128) != 0 ? null : null, (Function0) objRememberedValue2), composerStartRestartGroup, r2);
                    if (TextLinkScopeKt.isNullOrEmpty(((LinkAnnotation) range.getItem()).getStyles())) {
                        uriHandler = uriHandler2;
                        c2 = 6;
                        i3 = 2;
                        c = 3;
                        composerStartRestartGroup.startReplaceGroup(1388165134);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1386296950);
                        Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == companion.getEmpty()) {
                            objRememberedValue3 = new LinkStateInteractionSourceObserver();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        final LinkStateInteractionSourceObserver linkStateInteractionSourceObserver = (LinkStateInteractionSourceObserver) objRememberedValue3;
                        Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == companion.getEmpty()) {
                            objRememberedValue4 = new TextLinkScope$LinksComposables$1$3$1(linkStateInteractionSourceObserver, mutableInteractionSource, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2) objRememberedValue4, composerStartRestartGroup, 6);
                        Object objValueOf = Boolean.valueOf(linkStateInteractionSourceObserver.isHovered());
                        Object objValueOf2 = Boolean.valueOf(linkStateInteractionSourceObserver.isFocused());
                        Object objValueOf3 = Boolean.valueOf(linkStateInteractionSourceObserver.isPressed());
                        TextLinkStyles styles = ((LinkAnnotation) range.getItem()).getStyles();
                        Object style = styles != null ? styles.getStyle() : null;
                        TextLinkStyles styles2 = ((LinkAnnotation) range.getItem()).getStyles();
                        Object focusedStyle = styles2 != null ? styles2.getFocusedStyle() : null;
                        TextLinkStyles styles3 = ((LinkAnnotation) range.getItem()).getStyles();
                        Object hoveredStyle = styles3 != null ? styles3.getHoveredStyle() : null;
                        TextLinkStyles styles4 = ((LinkAnnotation) range.getItem()).getStyles();
                        Object pressedStyle = styles4 != null ? styles4.getPressedStyle() : null;
                        uriHandler = uriHandler2;
                        Object[] objArr = new Object[7];
                        objArr[r2] = objValueOf;
                        objArr[1] = objValueOf2;
                        i3 = 2;
                        objArr[2] = objValueOf3;
                        c = 3;
                        objArr[3] = style;
                        objArr[4] = focusedStyle;
                        objArr[5] = hoveredStyle;
                        objArr[6] = pressedStyle;
                        boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(this) | composerStartRestartGroup.changed(range);
                        Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance2 || objRememberedValue5 == companion.getEmpty()) {
                            objRememberedValue5 = new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope$LinksComposables$1$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                    invoke((TextAnnotatorScope) obj);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(TextAnnotatorScope textAnnotatorScope) {
                                    TextLinkStyles styles5;
                                    TextLinkStyles styles6;
                                    TextLinkStyles styles7;
                                    TextLinkScope textLinkScope = this.this$0;
                                    TextLinkStyles styles8 = ((LinkAnnotation) range.getItem()).getStyles();
                                    SpanStyle pressedStyle2 = null;
                                    SpanStyle spanStyleMergeOrUse = textLinkScope.mergeOrUse(textLinkScope.mergeOrUse(styles8 != null ? styles8.getStyle() : null, (!linkStateInteractionSourceObserver.isFocused() || (styles7 = ((LinkAnnotation) range.getItem()).getStyles()) == null) ? null : styles7.getFocusedStyle()), (!linkStateInteractionSourceObserver.isHovered() || (styles6 = ((LinkAnnotation) range.getItem()).getStyles()) == null) ? null : styles6.getHoveredStyle());
                                    if (linkStateInteractionSourceObserver.isPressed() && (styles5 = ((LinkAnnotation) range.getItem()).getStyles()) != null) {
                                        pressedStyle2 = styles5.getPressedStyle();
                                    }
                                    SpanStyle spanStyleMergeOrUse2 = textLinkScope.mergeOrUse(spanStyleMergeOrUse, pressedStyle2);
                                    if (spanStyleMergeOrUse2 != null) {
                                        AnnotatedString.Range range2 = range;
                                        textAnnotatorScope.replaceStyle(spanStyleMergeOrUse2, range2.getStart(), range2.getEnd());
                                    }
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        c2 = 6;
                        StyleAnnotation(objArr, (Function1) objRememberedValue5, composerStartRestartGroup, (i2 << 6) & 896);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    c = c3;
                    uriHandler = uriHandler2;
                    c2 = c4;
                    i3 = i4;
                    composerStartRestartGroup.startReplaceGroup(1388179022);
                    composerStartRestartGroup.endReplaceGroup();
                }
                i5++;
                c4 = c2;
                i4 = i3;
                c3 = c;
                uriHandler2 = uriHandler;
                r2 = 0;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.TextLinkScope.LinksComposables.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i6) {
                    TextLinkScope.this.LinksComposables(composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SpanStyle mergeOrUse(SpanStyle spanStyle, SpanStyle spanStyle2) {
        SpanStyle spanStyleMerge;
        return (spanStyle == null || (spanStyleMerge = spanStyle.merge(spanStyle2)) == null) ? spanStyle2 : spanStyleMerge;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleLink(LinkAnnotation linkAnnotation, UriHandler uriHandler) {
        if (linkAnnotation instanceof LinkAnnotation.Url) {
            linkAnnotation.getLinkInteractionListener();
            try {
                uriHandler.openUri(((LinkAnnotation.Url) linkAnnotation).getUrl());
            } catch (IllegalArgumentException unused) {
            }
        } else if (linkAnnotation instanceof LinkAnnotation.Clickable) {
            linkAnnotation.getLinkInteractionListener();
        }
    }

    public final AnnotatedString applyAnnotators$foundation_release() {
        AnnotatedString annotatedString;
        if (this.annotators.isEmpty()) {
            annotatedString = this.text;
        } else {
            AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, null);
            builder.append(this.initialText);
            TextAnnotatorScope textAnnotatorScope = new TextAnnotatorScope(builder);
            SnapshotStateList snapshotStateList = this.annotators;
            int size = snapshotStateList.size();
            for (int i = 0; i < size; i++) {
                ((Function1) snapshotStateList.get(i)).invoke(textAnnotatorScope);
            }
            annotatedString = builder.toAnnotatedString();
        }
        this.text = annotatedString;
        return annotatedString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void StyleAnnotation(final Object[] objArr, final Function1 function1, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-2083052099);
        int i2 = (i & 48) == 0 ? (composerStartRestartGroup.changedInstance(function1) ? 32 : 16) | i : i;
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(this) ? 256 : 128;
        }
        composerStartRestartGroup.startMovableGroup(-416630839, Integer.valueOf(objArr.length));
        for (Object obj : objArr) {
            i2 |= composerStartRestartGroup.changedInstance(obj) ? 4 : 0;
        }
        composerStartRestartGroup.endMovableGroup();
        if ((i2 & 14) == 0) {
            i2 |= 2;
        }
        if ((i2 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2083052099, i2, -1, "androidx.compose.foundation.text.TextLinkScope.StyleAnnotation (TextLinkScope.kt:298)");
            }
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(function1);
            spreadBuilder.addSpread(objArr);
            Object[] array = spreadBuilder.toArray(new Object[spreadBuilder.size()]);
            boolean zChangedInstance = ((i2 & 112) == 32) | composerStartRestartGroup.changedInstance(this);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope$StyleAnnotation$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        this.this$0.annotators.add(function1);
                        final TextLinkScope textLinkScope = this.this$0;
                        final Function1 function12 = function1;
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.TextLinkScope$StyleAnnotation$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                textLinkScope.annotators.remove(function12);
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            EffectsKt.DisposableEffect(array, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.TextLinkScope.StyleAnnotation.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    invoke((Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    TextLinkScope textLinkScope = TextLinkScope.this;
                    Object[] objArr2 = objArr;
                    textLinkScope.StyleAnnotation(Arrays.copyOf(objArr2, objArr2.length), function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }
}
