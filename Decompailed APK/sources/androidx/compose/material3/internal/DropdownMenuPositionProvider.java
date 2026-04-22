package androidx.compose.material3.internal;

import androidx.compose.material3.MenuKt;
import androidx.compose.material3.internal.MenuPosition;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import ch.qos.logback.core.CoreConstants;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MenuPosition.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DropdownMenuPositionProvider implements PopupPositionProvider {
    private final MenuPosition.Vertical bottomToAnchorTop;
    private final MenuPosition.Vertical bottomToWindowBottom;
    private final MenuPosition.Vertical centerToAnchorTop;
    private final long contentOffset;
    private final Density density;
    private final MenuPosition.Horizontal endToAnchorEnd;
    private final MenuPosition.Horizontal leftToWindowLeft;
    private final Function2 onPositionCalculated;
    private final MenuPosition.Horizontal rightToWindowRight;
    private final MenuPosition.Horizontal startToAnchorStart;
    private final MenuPosition.Vertical topToAnchorBottom;
    private final MenuPosition.Vertical topToWindowTop;
    private final int verticalMargin;

    public /* synthetic */ DropdownMenuPositionProvider(long j, Density density, int i, Function2 function2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, density, i, function2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DropdownMenuPositionProvider)) {
            return false;
        }
        DropdownMenuPositionProvider dropdownMenuPositionProvider = (DropdownMenuPositionProvider) obj;
        return DpOffset.m2432equalsimpl0(this.contentOffset, dropdownMenuPositionProvider.contentOffset) && Intrinsics.areEqual(this.density, dropdownMenuPositionProvider.density) && this.verticalMargin == dropdownMenuPositionProvider.verticalMargin && Intrinsics.areEqual(this.onPositionCalculated, dropdownMenuPositionProvider.onPositionCalculated);
    }

    public int hashCode() {
        return (((((DpOffset.m2435hashCodeimpl(this.contentOffset) * 31) + this.density.hashCode()) * 31) + this.verticalMargin) * 31) + this.onPositionCalculated.hashCode();
    }

    public String toString() {
        return "DropdownMenuPositionProvider(contentOffset=" + ((Object) DpOffset.m2436toStringimpl(this.contentOffset)) + ", density=" + this.density + ", verticalMargin=" + this.verticalMargin + ", onPositionCalculated=" + this.onPositionCalculated + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    private DropdownMenuPositionProvider(long j, Density density, int i, Function2 function2) {
        this.contentOffset = j;
        this.density = density;
        this.verticalMargin = i;
        this.onPositionCalculated = function2;
        int iMo204roundToPx0680j_4 = density.mo204roundToPx0680j_4(DpOffset.m2433getXD9Ej5fM(j));
        MenuPosition menuPosition = MenuPosition.INSTANCE;
        this.startToAnchorStart = menuPosition.startToAnchorStart(iMo204roundToPx0680j_4);
        this.endToAnchorEnd = menuPosition.endToAnchorEnd(iMo204roundToPx0680j_4);
        this.leftToWindowLeft = menuPosition.leftToWindowLeft(0);
        this.rightToWindowRight = menuPosition.rightToWindowRight(0);
        int iMo204roundToPx0680j_42 = density.mo204roundToPx0680j_4(DpOffset.m2434getYD9Ej5fM(j));
        this.topToAnchorBottom = menuPosition.topToAnchorBottom(iMo204roundToPx0680j_42);
        this.bottomToAnchorTop = menuPosition.bottomToAnchorTop(iMo204roundToPx0680j_42);
        this.centerToAnchorTop = menuPosition.centerToAnchorTop(iMo204roundToPx0680j_42);
        this.topToWindowTop = menuPosition.topToWindowTop(i);
        this.bottomToWindowBottom = menuPosition.bottomToWindowBottom(i);
    }

    public /* synthetic */ DropdownMenuPositionProvider(long j, Density density, int i, Function2 function2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, density, (i2 & 4) != 0 ? density.mo204roundToPx0680j_4(MenuKt.getMenuVerticalMargin()) : i, (i2 & 8) != 0 ? new Function2() { // from class: androidx.compose.material3.internal.DropdownMenuPositionProvider.2
            public final void invoke(IntRect intRect, IntRect intRect2) {
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((IntRect) obj, (IntRect) obj2);
                return Unit.INSTANCE;
            }
        } : function2, null);
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
    public long mo151calculatePositionllwVHH4(IntRect intRect, long j, LayoutDirection layoutDirection, long j2) {
        MenuPosition.Horizontal horizontal;
        int iMo779position95KtPRI;
        MenuPosition.Vertical vertical;
        int i = 0;
        MenuPosition.Horizontal horizontal2 = this.startToAnchorStart;
        MenuPosition.Horizontal horizontal3 = this.endToAnchorEnd;
        if (IntOffset.m2457getXimpl(intRect.m2468getCenternOccac()) < IntSize.m2476getWidthimpl(j) / 2) {
            horizontal = this.leftToWindowLeft;
        } else {
            horizontal = this.rightToWindowRight;
        }
        List listListOf = CollectionsKt.listOf((Object[]) new MenuPosition.Horizontal[]{horizontal2, horizontal3, horizontal});
        int size = listListOf.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                iMo779position95KtPRI = 0;
                break;
            }
            iMo779position95KtPRI = ((MenuPosition.Horizontal) listListOf.get(i2)).mo779position95KtPRI(intRect, j, IntSize.m2476getWidthimpl(j2), layoutDirection);
            if (i2 == CollectionsKt.getLastIndex(listListOf) || (iMo779position95KtPRI >= 0 && IntSize.m2476getWidthimpl(j2) + iMo779position95KtPRI <= IntSize.m2476getWidthimpl(j))) {
                break;
            }
            i2++;
        }
        MenuPosition.Vertical vertical2 = this.topToAnchorBottom;
        MenuPosition.Vertical vertical3 = this.bottomToAnchorTop;
        MenuPosition.Vertical vertical4 = this.centerToAnchorTop;
        if (IntOffset.m2458getYimpl(intRect.m2468getCenternOccac()) < IntSize.m2475getHeightimpl(j) / 2) {
            vertical = this.topToWindowTop;
        } else {
            vertical = this.bottomToWindowBottom;
        }
        List listListOf2 = CollectionsKt.listOf((Object[]) new MenuPosition.Vertical[]{vertical2, vertical3, vertical4, vertical});
        int size2 = listListOf2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            int iMo780positionJVtK1S4 = ((MenuPosition.Vertical) listListOf2.get(i3)).mo780positionJVtK1S4(intRect, j, IntSize.m2475getHeightimpl(j2));
            if (i3 == CollectionsKt.getLastIndex(listListOf2) || (iMo780positionJVtK1S4 >= this.verticalMargin && IntSize.m2475getHeightimpl(j2) + iMo780positionJVtK1S4 <= IntSize.m2475getHeightimpl(j) - this.verticalMargin)) {
                i = iMo780positionJVtK1S4;
                break;
            }
        }
        long jIntOffset = IntOffsetKt.IntOffset(iMo779position95KtPRI, i);
        this.onPositionCalculated.invoke(intRect, IntRectKt.m2470IntRectVbeCjmY(jIntOffset, j2));
        return jIntOffset;
    }
}
