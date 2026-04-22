package androidx.compose.foundation.contextmenu;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;

/* JADX INFO: compiled from: ContextMenuUi.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ContextMenuSpec {
    public static final ContextMenuSpec INSTANCE = new ContextMenuSpec();
    private static final float ContainerWidthMin = Dp.m2416constructorimpl(112);
    private static final float ContainerWidthMax = Dp.m2416constructorimpl(280);
    private static final float ListItemHeight = Dp.m2416constructorimpl(48);
    private static final float MenuContainerElevation = Dp.m2416constructorimpl(3);
    private static final float CornerRadius = Dp.m2416constructorimpl(4);
    private static final Alignment.Vertical LabelVerticalTextAlignment = Alignment.Companion.getCenterVertically();
    private static final int LabelHorizontalTextAlignment = TextAlign.Companion.m2345getStarte0LSkKk();
    private static final float HorizontalPadding = Dp.m2416constructorimpl(12);
    private static final float VerticalPadding = Dp.m2416constructorimpl(8);
    private static final float IconSize = Dp.m2416constructorimpl(24);
    private static final long FontSize = TextUnitKt.getSp(14);
    private static final FontWeight FontWeight = FontWeight.Companion.getMedium();
    private static final long LineHeight = TextUnitKt.getSp(20);
    private static final long LetterSpacing = TextUnitKt.getSp(0.1f);

    private ContextMenuSpec() {
    }

    /* JADX INFO: renamed from: getContainerWidthMin-D9Ej5fM, reason: not valid java name */
    public final float m153getContainerWidthMinD9Ej5fM() {
        return ContainerWidthMin;
    }

    /* JADX INFO: renamed from: getContainerWidthMax-D9Ej5fM, reason: not valid java name */
    public final float m152getContainerWidthMaxD9Ej5fM() {
        return ContainerWidthMax;
    }

    /* JADX INFO: renamed from: getListItemHeight-D9Ej5fM, reason: not valid java name */
    public final float m157getListItemHeightD9Ej5fM() {
        return ListItemHeight;
    }

    /* JADX INFO: renamed from: getMenuContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m158getMenuContainerElevationD9Ej5fM() {
        return MenuContainerElevation;
    }

    /* JADX INFO: renamed from: getCornerRadius-D9Ej5fM, reason: not valid java name */
    public final float m154getCornerRadiusD9Ej5fM() {
        return CornerRadius;
    }

    public final Alignment.Vertical getLabelVerticalTextAlignment() {
        return LabelVerticalTextAlignment;
    }

    /* JADX INFO: renamed from: getHorizontalPadding-D9Ej5fM, reason: not valid java name */
    public final float m155getHorizontalPaddingD9Ej5fM() {
        return HorizontalPadding;
    }

    /* JADX INFO: renamed from: getVerticalPadding-D9Ej5fM, reason: not valid java name */
    public final float m159getVerticalPaddingD9Ej5fM() {
        return VerticalPadding;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m156getIconSizeD9Ej5fM() {
        return IconSize;
    }

    /* JADX INFO: renamed from: textStyle-8_81llA, reason: not valid java name */
    public final TextStyle m160textStyle8_81llA(long j) {
        int i = LabelHorizontalTextAlignment;
        return new TextStyle(j, FontSize, FontWeight, null, null, null, null, LetterSpacing, null, null, null, 0L, null, null, null, i, 0, LineHeight, null, null, null, 0, 0, null, 16613240, null);
    }
}
