package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Savers.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SaversKt {
    private static final Saver AnnotatedStringSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$AnnotatedStringSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, AnnotatedString annotatedString) {
            return CollectionsKt.arrayListOf(SaversKt.save(annotatedString.getText()), SaversKt.save(annotatedString.getSpanStyles(), SaversKt.AnnotationRangeListSaver, saverScope), SaversKt.save(annotatedString.getParagraphStyles(), SaversKt.AnnotationRangeListSaver, saverScope), SaversKt.save(annotatedString.getAnnotations$ui_text_release(), SaversKt.AnnotationRangeListSaver, saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$AnnotatedStringSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final AnnotatedString invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            Object obj2 = list.get(1);
            Saver saver = SaversKt.AnnotationRangeListSaver;
            Boolean bool = Boolean.FALSE;
            List list2 = null;
            List list3 = ((!Intrinsics.areEqual(obj2, bool) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? (List) saver.restore(obj2) : null;
            Object obj3 = list.get(2);
            Saver saver2 = SaversKt.AnnotationRangeListSaver;
            List list4 = ((!Intrinsics.areEqual(obj3, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) ? (List) saver2.restore(obj3) : null;
            Object obj4 = list.get(0);
            String str = obj4 != null ? (String) obj4 : null;
            Intrinsics.checkNotNull(str);
            if (list3 == null || list3.isEmpty()) {
                list3 = null;
            }
            if (list4 == null || list4.isEmpty()) {
                list4 = null;
            }
            Object obj5 = list.get(3);
            Saver saver3 = SaversKt.AnnotationRangeListSaver;
            if ((!Intrinsics.areEqual(obj5, bool) || (saver3 instanceof NonNullValueClassSaver)) && obj5 != null) {
                list2 = (List) saver3.restore(obj5);
            }
            return new AnnotatedString(str, list3, list4, list2);
        }
    });
    private static final Saver AnnotationRangeListSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeListSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, List list) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(SaversKt.save((AnnotatedString.Range) list.get(i), SaversKt.AnnotationRangeSaver, saverScope));
            }
            return arrayList;
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeListSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final List invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                Saver saver = SaversKt.AnnotationRangeSaver;
                AnnotatedString.Range range = null;
                if ((!Intrinsics.areEqual(obj2, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) {
                    range = (AnnotatedString.Range) saver.restore(obj2);
                }
                Intrinsics.checkNotNull(range);
                arrayList.add(range);
            }
            return arrayList;
        }
    });
    private static final Saver AnnotationRangeSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeSaver$1

        /* JADX INFO: compiled from: Savers.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AnnotationType.values().length];
                try {
                    iArr[AnnotationType.Paragraph.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[AnnotationType.Span.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[AnnotationType.VerbatimTts.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[AnnotationType.Url.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[AnnotationType.Link.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[AnnotationType.Clickable.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[AnnotationType.String.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, AnnotatedString.Range range) {
            AnnotationType annotationType;
            Object objSave;
            Object item = range.getItem();
            if (item instanceof ParagraphStyle) {
                annotationType = AnnotationType.Paragraph;
            } else if (item instanceof SpanStyle) {
                annotationType = AnnotationType.Span;
            } else if (item instanceof VerbatimTtsAnnotation) {
                annotationType = AnnotationType.VerbatimTts;
            } else if (item instanceof UrlAnnotation) {
                annotationType = AnnotationType.Url;
            } else if (item instanceof LinkAnnotation.Url) {
                annotationType = AnnotationType.Link;
            } else {
                annotationType = item instanceof LinkAnnotation.Clickable ? AnnotationType.Clickable : AnnotationType.String;
            }
            switch (WhenMappings.$EnumSwitchMapping$0[annotationType.ordinal()]) {
                case 1:
                    Object item2 = range.getItem();
                    Intrinsics.checkNotNull(item2, "null cannot be cast to non-null type androidx.compose.ui.text.ParagraphStyle");
                    objSave = SaversKt.save((ParagraphStyle) item2, SaversKt.getParagraphStyleSaver(), saverScope);
                    break;
                case 2:
                    Object item3 = range.getItem();
                    Intrinsics.checkNotNull(item3, "null cannot be cast to non-null type androidx.compose.ui.text.SpanStyle");
                    objSave = SaversKt.save((SpanStyle) item3, SaversKt.getSpanStyleSaver(), saverScope);
                    break;
                case 3:
                    Object item4 = range.getItem();
                    Intrinsics.checkNotNull(item4, "null cannot be cast to non-null type androidx.compose.ui.text.VerbatimTtsAnnotation");
                    objSave = SaversKt.save((VerbatimTtsAnnotation) item4, SaversKt.VerbatimTtsAnnotationSaver, saverScope);
                    break;
                case 4:
                    Object item5 = range.getItem();
                    Intrinsics.checkNotNull(item5, "null cannot be cast to non-null type androidx.compose.ui.text.UrlAnnotation");
                    objSave = SaversKt.save((UrlAnnotation) item5, SaversKt.UrlAnnotationSaver, saverScope);
                    break;
                case 5:
                    Object item6 = range.getItem();
                    Intrinsics.checkNotNull(item6, "null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Url");
                    objSave = SaversKt.save((LinkAnnotation.Url) item6, SaversKt.LinkSaver, saverScope);
                    break;
                case 6:
                    Object item7 = range.getItem();
                    Intrinsics.checkNotNull(item7, "null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Clickable");
                    objSave = SaversKt.save((LinkAnnotation.Clickable) item7, SaversKt.ClickableSaver, saverScope);
                    break;
                case 7:
                    objSave = SaversKt.save(range.getItem());
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            return CollectionsKt.arrayListOf(SaversKt.save(annotationType), objSave, SaversKt.save(Integer.valueOf(range.getStart())), SaversKt.save(Integer.valueOf(range.getEnd())), SaversKt.save(range.getTag()));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeSaver$2

        /* JADX INFO: compiled from: Savers.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AnnotationType.values().length];
                try {
                    iArr[AnnotationType.Paragraph.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[AnnotationType.Span.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[AnnotationType.VerbatimTts.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[AnnotationType.Url.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[AnnotationType.Link.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[AnnotationType.Clickable.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[AnnotationType.String.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // kotlin.jvm.functions.Function1
        public final AnnotatedString.Range invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            AnnotationType annotationType = obj2 != null ? (AnnotationType) obj2 : null;
            Intrinsics.checkNotNull(annotationType);
            Object obj3 = list.get(2);
            Integer num = obj3 != null ? (Integer) obj3 : null;
            Intrinsics.checkNotNull(num);
            int iIntValue = num.intValue();
            Object obj4 = list.get(3);
            Integer num2 = obj4 != null ? (Integer) obj4 : null;
            Intrinsics.checkNotNull(num2);
            int iIntValue2 = num2.intValue();
            Object obj5 = list.get(4);
            String str = obj5 != null ? (String) obj5 : null;
            Intrinsics.checkNotNull(str);
            switch (WhenMappings.$EnumSwitchMapping$0[annotationType.ordinal()]) {
                case 1:
                    Object obj6 = list.get(1);
                    Saver paragraphStyleSaver = SaversKt.getParagraphStyleSaver();
                    if ((!Intrinsics.areEqual(obj6, Boolean.FALSE) || (paragraphStyleSaver instanceof NonNullValueClassSaver)) && obj6 != null) {
                        obj = (ParagraphStyle) paragraphStyleSaver.restore(obj6);
                    }
                    Intrinsics.checkNotNull(obj);
                    return new AnnotatedString.Range(obj, iIntValue, iIntValue2, str);
                case 2:
                    Object obj7 = list.get(1);
                    Saver spanStyleSaver = SaversKt.getSpanStyleSaver();
                    if ((!Intrinsics.areEqual(obj7, Boolean.FALSE) || (spanStyleSaver instanceof NonNullValueClassSaver)) && obj7 != null) {
                        obj = (SpanStyle) spanStyleSaver.restore(obj7);
                    }
                    Intrinsics.checkNotNull(obj);
                    return new AnnotatedString.Range(obj, iIntValue, iIntValue2, str);
                case 3:
                    Object obj8 = list.get(1);
                    Saver saver = SaversKt.VerbatimTtsAnnotationSaver;
                    if ((!Intrinsics.areEqual(obj8, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj8 != null) {
                        obj = (VerbatimTtsAnnotation) saver.restore(obj8);
                    }
                    Intrinsics.checkNotNull(obj);
                    return new AnnotatedString.Range(obj, iIntValue, iIntValue2, str);
                case 4:
                    Object obj9 = list.get(1);
                    Saver saver2 = SaversKt.UrlAnnotationSaver;
                    if ((!Intrinsics.areEqual(obj9, Boolean.FALSE) || (saver2 instanceof NonNullValueClassSaver)) && obj9 != null) {
                        obj = (UrlAnnotation) saver2.restore(obj9);
                    }
                    Intrinsics.checkNotNull(obj);
                    return new AnnotatedString.Range(obj, iIntValue, iIntValue2, str);
                case 5:
                    Object obj10 = list.get(1);
                    Saver saver3 = SaversKt.LinkSaver;
                    if ((!Intrinsics.areEqual(obj10, Boolean.FALSE) || (saver3 instanceof NonNullValueClassSaver)) && obj10 != null) {
                        obj = (LinkAnnotation.Url) saver3.restore(obj10);
                    }
                    Intrinsics.checkNotNull(obj);
                    return new AnnotatedString.Range(obj, iIntValue, iIntValue2, str);
                case 6:
                    Object obj11 = list.get(1);
                    Saver saver4 = SaversKt.ClickableSaver;
                    if ((!Intrinsics.areEqual(obj11, Boolean.FALSE) || (saver4 instanceof NonNullValueClassSaver)) && obj11 != null) {
                        obj = (LinkAnnotation.Clickable) saver4.restore(obj11);
                    }
                    Intrinsics.checkNotNull(obj);
                    return new AnnotatedString.Range(obj, iIntValue, iIntValue2, str);
                case 7:
                    Object obj12 = list.get(1);
                    obj = obj12 != null ? (String) obj12 : null;
                    Intrinsics.checkNotNull(obj);
                    return new AnnotatedString.Range(obj, iIntValue, iIntValue2, str);
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    });
    private static final Saver VerbatimTtsAnnotationSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$VerbatimTtsAnnotationSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, VerbatimTtsAnnotation verbatimTtsAnnotation) {
            return SaversKt.save(verbatimTtsAnnotation.getVerbatim());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$VerbatimTtsAnnotationSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final VerbatimTtsAnnotation invoke(Object obj) {
            String str = obj != null ? (String) obj : null;
            Intrinsics.checkNotNull(str);
            return new VerbatimTtsAnnotation(str);
        }
    });
    private static final Saver UrlAnnotationSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$UrlAnnotationSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, UrlAnnotation urlAnnotation) {
            return SaversKt.save(urlAnnotation.getUrl());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$UrlAnnotationSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final UrlAnnotation invoke(Object obj) {
            String str = obj != null ? (String) obj : null;
            Intrinsics.checkNotNull(str);
            return new UrlAnnotation(str);
        }
    });
    private static final Saver LinkSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$LinkSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, LinkAnnotation.Url url) {
            return CollectionsKt.arrayListOf(SaversKt.save(url.getUrl()), SaversKt.save(url.getStyles(), SaversKt.getTextLinkStylesSaver(), saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$LinkSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final LinkAnnotation.Url invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            TextLinkStyles textLinkStyles = null;
            String str = obj2 != null ? (String) obj2 : null;
            Intrinsics.checkNotNull(str);
            Object obj3 = list.get(1);
            Saver textLinkStylesSaver = SaversKt.getTextLinkStylesSaver();
            if ((!Intrinsics.areEqual(obj3, Boolean.FALSE) || (textLinkStylesSaver instanceof NonNullValueClassSaver)) && obj3 != null) {
                textLinkStyles = (TextLinkStyles) textLinkStylesSaver.restore(obj3);
            }
            return new LinkAnnotation.Url(str, textLinkStyles, null, 4, null);
        }
    });
    private static final Saver ClickableSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$ClickableSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, LinkAnnotation.Clickable clickable) {
            return CollectionsKt.arrayListOf(SaversKt.save(clickable.getTag()), SaversKt.save(clickable.getStyles(), SaversKt.getTextLinkStylesSaver(), saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$ClickableSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final LinkAnnotation.Clickable invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            String str = obj2 != null ? (String) obj2 : null;
            Intrinsics.checkNotNull(str);
            Object obj3 = list.get(1);
            Saver textLinkStylesSaver = SaversKt.getTextLinkStylesSaver();
            return new LinkAnnotation.Clickable(str, ((!Intrinsics.areEqual(obj3, Boolean.FALSE) || (textLinkStylesSaver instanceof NonNullValueClassSaver)) && obj3 != null) ? (TextLinkStyles) textLinkStylesSaver.restore(obj3) : null, null);
        }
    });
    private static final Saver ParagraphStyleSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$ParagraphStyleSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, ParagraphStyle paragraphStyle) {
            return CollectionsKt.arrayListOf(SaversKt.save(TextAlign.m2333boximpl(paragraphStyle.m2069getTextAligne0LSkKk())), SaversKt.save(TextDirection.m2347boximpl(paragraphStyle.m2070getTextDirections_7Xco())), SaversKt.save(TextUnit.m2484boximpl(paragraphStyle.m2068getLineHeightXSAIIZE()), SaversKt.getSaver(TextUnit.Companion), saverScope), SaversKt.save(paragraphStyle.getTextIndent(), SaversKt.getSaver(TextIndent.Companion), saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$ParagraphStyleSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final ParagraphStyle invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            TextAlign textAlign = obj2 != null ? (TextAlign) obj2 : null;
            Intrinsics.checkNotNull(textAlign);
            int iM2339unboximpl = textAlign.m2339unboximpl();
            Object obj3 = list.get(1);
            TextDirection textDirection = obj3 != null ? (TextDirection) obj3 : null;
            Intrinsics.checkNotNull(textDirection);
            int iM2353unboximpl = textDirection.m2353unboximpl();
            Object obj4 = list.get(2);
            Saver saver = SaversKt.getSaver(TextUnit.Companion);
            Boolean bool = Boolean.FALSE;
            TextUnit textUnit = ((!Intrinsics.areEqual(obj4, bool) || (saver instanceof NonNullValueClassSaver)) && obj4 != null) ? (TextUnit) saver.restore(obj4) : null;
            Intrinsics.checkNotNull(textUnit);
            long jM2493unboximpl = textUnit.m2493unboximpl();
            Object obj5 = list.get(3);
            Saver saver2 = SaversKt.getSaver(TextIndent.Companion);
            return new ParagraphStyle(iM2339unboximpl, iM2353unboximpl, jM2493unboximpl, ((!Intrinsics.areEqual(obj5, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj5 != null) ? (TextIndent) saver2.restore(obj5) : null, null, null, 0, 0, null, 496, null);
        }
    });
    private static final Saver SpanStyleSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$SpanStyleSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, SpanStyle spanStyle) {
            Color colorM1290boximpl = Color.m1290boximpl(spanStyle.m2087getColor0d7_KjU());
            Color.Companion companion = Color.Companion;
            Object objSave = SaversKt.save(colorM1290boximpl, SaversKt.getSaver(companion), saverScope);
            TextUnit textUnitM2484boximpl = TextUnit.m2484boximpl(spanStyle.m2088getFontSizeXSAIIZE());
            TextUnit.Companion companion2 = TextUnit.Companion;
            return CollectionsKt.arrayListOf(objSave, SaversKt.save(textUnitM2484boximpl, SaversKt.getSaver(companion2), saverScope), SaversKt.save(spanStyle.getFontWeight(), SaversKt.getSaver(FontWeight.Companion), saverScope), SaversKt.save(spanStyle.m2089getFontStyle4Lr2A7w()), SaversKt.save(spanStyle.m2090getFontSynthesisZQGJjVo()), SaversKt.save(-1), SaversKt.save(spanStyle.getFontFeatureSettings()), SaversKt.save(TextUnit.m2484boximpl(spanStyle.m2091getLetterSpacingXSAIIZE()), SaversKt.getSaver(companion2), saverScope), SaversKt.save(spanStyle.m2086getBaselineShift5SSeXJ0(), SaversKt.getSaver(BaselineShift.Companion), saverScope), SaversKt.save(spanStyle.getTextGeometricTransform(), SaversKt.getSaver(TextGeometricTransform.Companion), saverScope), SaversKt.save(spanStyle.getLocaleList(), SaversKt.getSaver(LocaleList.Companion), saverScope), SaversKt.save(Color.m1290boximpl(spanStyle.m2085getBackground0d7_KjU()), SaversKt.getSaver(companion), saverScope), SaversKt.save(spanStyle.getTextDecoration(), SaversKt.getSaver(TextDecoration.Companion), saverScope), SaversKt.save(spanStyle.getShadow(), SaversKt.getSaver(Shadow.Companion), saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$SpanStyleSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final SpanStyle invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Color.Companion companion = Color.Companion;
            Saver saver = SaversKt.getSaver(companion);
            Boolean bool = Boolean.FALSE;
            Color color = ((!Intrinsics.areEqual(obj2, bool) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? (Color) saver.restore(obj2) : null;
            Intrinsics.checkNotNull(color);
            long jM1304unboximpl = color.m1304unboximpl();
            Object obj3 = list.get(1);
            TextUnit.Companion companion2 = TextUnit.Companion;
            Saver saver2 = SaversKt.getSaver(companion2);
            TextUnit textUnit = ((!Intrinsics.areEqual(obj3, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) ? (TextUnit) saver2.restore(obj3) : null;
            Intrinsics.checkNotNull(textUnit);
            long jM2493unboximpl = textUnit.m2493unboximpl();
            Object obj4 = list.get(2);
            Saver saver3 = SaversKt.getSaver(FontWeight.Companion);
            FontWeight fontWeight = ((!Intrinsics.areEqual(obj4, bool) || (saver3 instanceof NonNullValueClassSaver)) && obj4 != null) ? (FontWeight) saver3.restore(obj4) : null;
            Object obj5 = list.get(3);
            FontStyle fontStyle = obj5 != null ? (FontStyle) obj5 : null;
            Object obj6 = list.get(4);
            FontSynthesis fontSynthesis = obj6 != null ? (FontSynthesis) obj6 : null;
            Object obj7 = list.get(6);
            String str = obj7 != null ? (String) obj7 : null;
            Object obj8 = list.get(7);
            Saver saver4 = SaversKt.getSaver(companion2);
            TextUnit textUnit2 = ((!Intrinsics.areEqual(obj8, bool) || (saver4 instanceof NonNullValueClassSaver)) && obj8 != null) ? (TextUnit) saver4.restore(obj8) : null;
            Intrinsics.checkNotNull(textUnit2);
            long jM2493unboximpl2 = textUnit2.m2493unboximpl();
            Object obj9 = list.get(8);
            Saver saver5 = SaversKt.getSaver(BaselineShift.Companion);
            BaselineShift baselineShift = ((!Intrinsics.areEqual(obj9, bool) || (saver5 instanceof NonNullValueClassSaver)) && obj9 != null) ? (BaselineShift) saver5.restore(obj9) : null;
            Object obj10 = list.get(9);
            Saver saver6 = SaversKt.getSaver(TextGeometricTransform.Companion);
            TextGeometricTransform textGeometricTransform = ((!Intrinsics.areEqual(obj10, bool) || (saver6 instanceof NonNullValueClassSaver)) && obj10 != null) ? (TextGeometricTransform) saver6.restore(obj10) : null;
            Object obj11 = list.get(10);
            Saver saver7 = SaversKt.getSaver(LocaleList.Companion);
            LocaleList localeList = ((!Intrinsics.areEqual(obj11, bool) || (saver7 instanceof NonNullValueClassSaver)) && obj11 != null) ? (LocaleList) saver7.restore(obj11) : null;
            Object obj12 = list.get(11);
            Saver saver8 = SaversKt.getSaver(companion);
            Color color2 = ((!Intrinsics.areEqual(obj12, bool) || (saver8 instanceof NonNullValueClassSaver)) && obj12 != null) ? (Color) saver8.restore(obj12) : null;
            Intrinsics.checkNotNull(color2);
            long jM1304unboximpl2 = color2.m1304unboximpl();
            Object obj13 = list.get(12);
            Saver saver9 = SaversKt.getSaver(TextDecoration.Companion);
            TextDecoration textDecoration = ((!Intrinsics.areEqual(obj13, bool) || (saver9 instanceof NonNullValueClassSaver)) && obj13 != null) ? (TextDecoration) saver9.restore(obj13) : null;
            Object obj14 = list.get(13);
            Saver saver10 = SaversKt.getSaver(Shadow.Companion);
            return new SpanStyle(jM1304unboximpl, jM2493unboximpl, fontWeight, fontStyle, fontSynthesis, null, str, jM2493unboximpl2, baselineShift, textGeometricTransform, localeList, jM1304unboximpl2, textDecoration, ((!Intrinsics.areEqual(obj14, bool) || (saver10 instanceof NonNullValueClassSaver)) && obj14 != null) ? (Shadow) saver10.restore(obj14) : null, null, null, 49184, null);
        }
    });
    private static final Saver TextLinkStylesSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextLinkStylesSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, TextLinkStyles textLinkStyles) {
            return CollectionsKt.arrayListOf(SaversKt.save(textLinkStyles.getStyle(), SaversKt.getSpanStyleSaver(), saverScope), SaversKt.save(textLinkStyles.getFocusedStyle(), SaversKt.getSpanStyleSaver(), saverScope), SaversKt.save(textLinkStyles.getHoveredStyle(), SaversKt.getSpanStyleSaver(), saverScope), SaversKt.save(textLinkStyles.getPressedStyle(), SaversKt.getSpanStyleSaver(), saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextLinkStylesSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final TextLinkStyles invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Saver spanStyleSaver = SaversKt.getSpanStyleSaver();
            Boolean bool = Boolean.FALSE;
            SpanStyle spanStyle = null;
            SpanStyle spanStyle2 = ((!Intrinsics.areEqual(obj2, bool) || (spanStyleSaver instanceof NonNullValueClassSaver)) && obj2 != null) ? (SpanStyle) spanStyleSaver.restore(obj2) : null;
            Object obj3 = list.get(1);
            Saver spanStyleSaver2 = SaversKt.getSpanStyleSaver();
            SpanStyle spanStyle3 = ((!Intrinsics.areEqual(obj3, bool) || (spanStyleSaver2 instanceof NonNullValueClassSaver)) && obj3 != null) ? (SpanStyle) spanStyleSaver2.restore(obj3) : null;
            Object obj4 = list.get(2);
            Saver spanStyleSaver3 = SaversKt.getSpanStyleSaver();
            SpanStyle spanStyle4 = ((!Intrinsics.areEqual(obj4, bool) || (spanStyleSaver3 instanceof NonNullValueClassSaver)) && obj4 != null) ? (SpanStyle) spanStyleSaver3.restore(obj4) : null;
            Object obj5 = list.get(3);
            Saver spanStyleSaver4 = SaversKt.getSpanStyleSaver();
            if ((!Intrinsics.areEqual(obj5, bool) || (spanStyleSaver4 instanceof NonNullValueClassSaver)) && obj5 != null) {
                spanStyle = (SpanStyle) spanStyleSaver4.restore(obj5);
            }
            return new TextLinkStyles(spanStyle2, spanStyle3, spanStyle4, spanStyle);
        }
    });
    private static final Saver TextDecorationSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextDecorationSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, TextDecoration textDecoration) {
            return Integer.valueOf(textDecoration.getMask());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextDecorationSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final TextDecoration invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            return new TextDecoration(((Integer) obj).intValue());
        }
    });
    private static final Saver TextGeometricTransformSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextGeometricTransformSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, TextGeometricTransform textGeometricTransform) {
            return CollectionsKt.arrayListOf(Float.valueOf(textGeometricTransform.getScaleX()), Float.valueOf(textGeometricTransform.getSkewX()));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextGeometricTransformSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final TextGeometricTransform invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Float>");
            List list = (List) obj;
            return new TextGeometricTransform(((Number) list.get(0)).floatValue(), ((Number) list.get(1)).floatValue());
        }
    });
    private static final Saver TextIndentSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextIndentSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, TextIndent textIndent) {
            TextUnit textUnitM2484boximpl = TextUnit.m2484boximpl(textIndent.m2362getFirstLineXSAIIZE());
            TextUnit.Companion companion = TextUnit.Companion;
            return CollectionsKt.arrayListOf(SaversKt.save(textUnitM2484boximpl, SaversKt.getSaver(companion), saverScope), SaversKt.save(TextUnit.m2484boximpl(textIndent.m2363getRestLineXSAIIZE()), SaversKt.getSaver(companion), saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextIndentSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final TextIndent invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            TextUnit.Companion companion = TextUnit.Companion;
            Saver saver = SaversKt.getSaver(companion);
            Boolean bool = Boolean.FALSE;
            TextUnit textUnit = null;
            TextUnit textUnit2 = ((!Intrinsics.areEqual(obj2, bool) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? (TextUnit) saver.restore(obj2) : null;
            Intrinsics.checkNotNull(textUnit2);
            long jM2493unboximpl = textUnit2.m2493unboximpl();
            Object obj3 = list.get(1);
            Saver saver2 = SaversKt.getSaver(companion);
            if ((!Intrinsics.areEqual(obj3, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) {
                textUnit = (TextUnit) saver2.restore(obj3);
            }
            Intrinsics.checkNotNull(textUnit);
            return new TextIndent(jM2493unboximpl, textUnit.m2493unboximpl(), null);
        }
    });
    private static final Saver FontWeightSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$FontWeightSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, FontWeight fontWeight) {
            return Integer.valueOf(fontWeight.getWeight());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$FontWeightSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final FontWeight invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            return new FontWeight(((Integer) obj).intValue());
        }
    });
    private static final Saver BaselineShiftSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$BaselineShiftSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return m2073invoke8a2Sb4w((SaverScope) obj, ((BaselineShift) obj2).m2273unboximpl());
        }

        /* JADX INFO: renamed from: invoke-8a2Sb4w, reason: not valid java name */
        public final Object m2073invoke8a2Sb4w(SaverScope saverScope, float f) {
            return Float.valueOf(f);
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$BaselineShiftSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: invoke-jTk7eUs, reason: not valid java name and merged with bridge method [inline-methods] */
        public final BaselineShift invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Float");
            return BaselineShift.m2267boximpl(BaselineShift.m2268constructorimpl(((Float) obj).floatValue()));
        }
    });
    private static final Saver TextRangeSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextRangeSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return m2079invokeFDrldGo((SaverScope) obj, ((TextRange) obj2).m2121unboximpl());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: invoke-FDrldGo, reason: not valid java name */
        public final Object m2079invokeFDrldGo(SaverScope saverScope, long j) {
            return CollectionsKt.arrayListOf(SaversKt.save(Integer.valueOf(TextRange.m2117getStartimpl(j))), SaversKt.save(Integer.valueOf(TextRange.m2112getEndimpl(j))));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextRangeSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: invoke-VqIyPBM, reason: not valid java name and merged with bridge method [inline-methods] */
        public final TextRange invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Integer num = obj2 != null ? (Integer) obj2 : null;
            Intrinsics.checkNotNull(num);
            int iIntValue = num.intValue();
            Object obj3 = list.get(1);
            Integer num2 = obj3 != null ? (Integer) obj3 : null;
            Intrinsics.checkNotNull(num2);
            return TextRange.m2105boximpl(TextRangeKt.TextRange(iIntValue, num2.intValue()));
        }
    });
    private static final Saver ShadowSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$ShadowSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, Shadow shadow) {
            return CollectionsKt.arrayListOf(SaversKt.save(Color.m1290boximpl(shadow.m1400getColor0d7_KjU()), SaversKt.getSaver(Color.Companion), saverScope), SaversKt.save(Offset.m1150boximpl(shadow.m1401getOffsetF1C5BW0()), SaversKt.getSaver(Offset.Companion), saverScope), SaversKt.save(Float.valueOf(shadow.getBlurRadius())));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$ShadowSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Shadow invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Saver saver = SaversKt.getSaver(Color.Companion);
            Boolean bool = Boolean.FALSE;
            Color color = ((!Intrinsics.areEqual(obj2, bool) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? (Color) saver.restore(obj2) : null;
            Intrinsics.checkNotNull(color);
            long jM1304unboximpl = color.m1304unboximpl();
            Object obj3 = list.get(1);
            Saver saver2 = SaversKt.getSaver(Offset.Companion);
            Offset offset = ((!Intrinsics.areEqual(obj3, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) ? (Offset) saver2.restore(obj3) : null;
            Intrinsics.checkNotNull(offset);
            long jM1168unboximpl = offset.m1168unboximpl();
            Object obj4 = list.get(2);
            Float f = obj4 != null ? (Float) obj4 : null;
            Intrinsics.checkNotNull(f);
            return new Shadow(jM1304unboximpl, jM1168unboximpl, f.floatValue(), null);
        }
    });
    private static final NonNullValueClassSaver ColorSaver = NonNullValueClassSaver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return m2075invoke4WTKRHQ((SaverScope) obj, ((Color) obj2).m1304unboximpl());
        }

        /* JADX INFO: renamed from: invoke-4WTKRHQ, reason: not valid java name */
        public final Object m2075invoke4WTKRHQ(SaverScope saverScope, long j) {
            return j == 16 ? Boolean.FALSE : Integer.valueOf(ColorKt.m1316toArgb8_81llA(j));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: invoke-ijrfgN4, reason: not valid java name and merged with bridge method [inline-methods] */
        public final Color invoke(Object obj) {
            long jColor;
            if (Intrinsics.areEqual(obj, Boolean.FALSE)) {
                jColor = Color.Companion.m1309getUnspecified0d7_KjU();
            } else {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                jColor = ColorKt.Color(((Integer) obj).intValue());
            }
            return Color.m1290boximpl(jColor);
        }
    });
    private static final NonNullValueClassSaver TextUnitSaver = NonNullValueClassSaver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextUnitSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return m2081invokempE4wyQ((SaverScope) obj, ((TextUnit) obj2).m2493unboximpl());
        }

        /* JADX INFO: renamed from: invoke-mpE4wyQ, reason: not valid java name */
        public final Object m2081invokempE4wyQ(SaverScope saverScope, long j) {
            return TextUnit.m2487equalsimpl0(j, TextUnit.Companion.m2494getUnspecifiedXSAIIZE()) ? Boolean.FALSE : CollectionsKt.arrayListOf(SaversKt.save(Float.valueOf(TextUnit.m2490getValueimpl(j))), SaversKt.save(TextUnitType.m2500boximpl(TextUnit.m2489getTypeUIouoOA(j))));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextUnitSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: invoke-XNhUCwk, reason: not valid java name and merged with bridge method [inline-methods] */
        public final TextUnit invoke(Object obj) {
            if (Intrinsics.areEqual(obj, Boolean.FALSE)) {
                return TextUnit.m2484boximpl(TextUnit.Companion.m2494getUnspecifiedXSAIIZE());
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Float f = obj2 != null ? (Float) obj2 : null;
            Intrinsics.checkNotNull(f);
            float fFloatValue = f.floatValue();
            Object obj3 = list.get(1);
            TextUnitType textUnitType = obj3 != null ? (TextUnitType) obj3 : null;
            Intrinsics.checkNotNull(textUnitType);
            return TextUnit.m2484boximpl(TextUnitKt.m2495TextUnitanM5pPY(fFloatValue, textUnitType.m2506unboximpl()));
        }
    });
    private static final NonNullValueClassSaver OffsetSaver = NonNullValueClassSaver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$OffsetSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return m2077invokeUv8p0NA((SaverScope) obj, ((Offset) obj2).m1168unboximpl());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
        public final Object m2077invokeUv8p0NA(SaverScope saverScope, long j) {
            return Offset.m1156equalsimpl0(j, Offset.Companion.m1170getUnspecifiedF1C5BW0()) ? Boolean.FALSE : CollectionsKt.arrayListOf(SaversKt.save(Float.valueOf(Offset.m1159getXimpl(j))), SaversKt.save(Float.valueOf(Offset.m1160getYimpl(j))));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$OffsetSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: invoke-x-9fifI, reason: not valid java name and merged with bridge method [inline-methods] */
        public final Offset invoke(Object obj) {
            if (Intrinsics.areEqual(obj, Boolean.FALSE)) {
                return Offset.m1150boximpl(Offset.Companion.m1170getUnspecifiedF1C5BW0());
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Float f = obj2 != null ? (Float) obj2 : null;
            Intrinsics.checkNotNull(f);
            float fFloatValue = f.floatValue();
            Object obj3 = list.get(1);
            Float f2 = obj3 != null ? (Float) obj3 : null;
            Intrinsics.checkNotNull(f2);
            return Offset.m1150boximpl(OffsetKt.Offset(fFloatValue, f2.floatValue()));
        }
    });
    private static final Saver LocaleListSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$LocaleListSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, LocaleList localeList) {
            List localeList2 = localeList.getLocaleList();
            ArrayList arrayList = new ArrayList(localeList2.size());
            int size = localeList2.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(SaversKt.save((Locale) localeList2.get(i), SaversKt.getSaver(Locale.Companion), saverScope));
            }
            return arrayList;
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$LocaleListSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final LocaleList invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                Saver saver = SaversKt.getSaver(Locale.Companion);
                Locale locale = null;
                if ((!Intrinsics.areEqual(obj2, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) {
                    locale = (Locale) saver.restore(obj2);
                }
                Intrinsics.checkNotNull(locale);
                arrayList.add(locale);
            }
            return new LocaleList(arrayList);
        }
    });
    private static final Saver LocaleSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$LocaleSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, Locale locale) {
            return locale.toLanguageTag();
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$LocaleSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Locale invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
            return new Locale((String) obj);
        }
    });

    public static final Object save(Object obj) {
        return obj;
    }

    public static final Object save(Object obj, Saver saver, SaverScope saverScope) {
        Object objSave;
        return (obj == null || (objSave = saver.save(saverScope, obj)) == null) ? Boolean.FALSE : objSave;
    }

    private static final NonNullValueClassSaver NonNullValueClassSaver(final Function2 function2, final Function1 function1) {
        return new NonNullValueClassSaver() { // from class: androidx.compose.ui.text.SaversKt.NonNullValueClassSaver.1
            @Override // androidx.compose.runtime.saveable.Saver
            public Object save(SaverScope saverScope, Object obj) {
                return function2.invoke(saverScope, obj);
            }

            @Override // androidx.compose.runtime.saveable.Saver
            public Object restore(Object obj) {
                return function1.invoke(obj);
            }
        };
    }

    public static final Saver getAnnotatedStringSaver() {
        return AnnotatedStringSaver;
    }

    public static final Saver getParagraphStyleSaver() {
        return ParagraphStyleSaver;
    }

    public static final Saver getSpanStyleSaver() {
        return SpanStyleSaver;
    }

    public static final Saver getTextLinkStylesSaver() {
        return TextLinkStylesSaver;
    }

    public static final Saver getSaver(TextDecoration.Companion companion) {
        return TextDecorationSaver;
    }

    public static final Saver getSaver(TextGeometricTransform.Companion companion) {
        return TextGeometricTransformSaver;
    }

    public static final Saver getSaver(TextIndent.Companion companion) {
        return TextIndentSaver;
    }

    public static final Saver getSaver(FontWeight.Companion companion) {
        return FontWeightSaver;
    }

    public static final Saver getSaver(BaselineShift.Companion companion) {
        return BaselineShiftSaver;
    }

    public static final Saver getSaver(TextRange.Companion companion) {
        return TextRangeSaver;
    }

    public static final Saver getSaver(Shadow.Companion companion) {
        return ShadowSaver;
    }

    public static final Saver getSaver(Color.Companion companion) {
        return ColorSaver;
    }

    public static final Saver getSaver(TextUnit.Companion companion) {
        return TextUnitSaver;
    }

    public static final Saver getSaver(Offset.Companion companion) {
        return OffsetSaver;
    }

    public static final Saver getSaver(LocaleList.Companion companion) {
        return LocaleListSaver;
    }

    public static final Saver getSaver(Locale.Companion companion) {
        return LocaleSaver;
    }
}
