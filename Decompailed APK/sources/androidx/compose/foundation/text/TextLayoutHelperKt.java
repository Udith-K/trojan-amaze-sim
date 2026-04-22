package androidx.compose.foundation.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextLayoutHelper.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextLayoutHelperKt {
    /* JADX INFO: renamed from: canReuse-7_7YC6M, reason: not valid java name */
    public static final boolean m457canReuse7_7YC6M(TextLayoutResult textLayoutResult, AnnotatedString annotatedString, TextStyle textStyle, List list, int i, boolean z, int i2, Density density, LayoutDirection layoutDirection, FontFamily.Resolver resolver, long j) {
        TextLayoutInput layoutInput = textLayoutResult.getLayoutInput();
        if (textLayoutResult.getMultiParagraph().getIntrinsics().getHasStaleResolvedFonts() || !Intrinsics.areEqual(layoutInput.getText(), annotatedString) || !layoutInput.getStyle().hasSameLayoutAffectingAttributes(textStyle) || !Intrinsics.areEqual(layoutInput.getPlaceholders(), list) || layoutInput.getMaxLines() != i || layoutInput.getSoftWrap() != z || !TextOverflow.m2372equalsimpl0(layoutInput.m2099getOverflowgIe3tQ8(), i2) || !Intrinsics.areEqual(layoutInput.getDensity(), density) || layoutInput.getLayoutDirection() != layoutDirection || !Intrinsics.areEqual(layoutInput.getFontFamilyResolver(), resolver) || Constraints.m2391getMinWidthimpl(j) != Constraints.m2391getMinWidthimpl(layoutInput.m2098getConstraintsmsEJaDk())) {
            return false;
        }
        if (z || TextOverflow.m2372equalsimpl0(i2, TextOverflow.Companion.m2376getEllipsisgIe3tQ8())) {
            return Constraints.m2389getMaxWidthimpl(j) == Constraints.m2389getMaxWidthimpl(layoutInput.m2098getConstraintsmsEJaDk()) && Constraints.m2388getMaxHeightimpl(j) == Constraints.m2388getMaxHeightimpl(layoutInput.m2098getConstraintsmsEJaDk());
        }
        return true;
    }
}
