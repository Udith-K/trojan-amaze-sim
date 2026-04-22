package androidx.compose.foundation.text;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.unit.Density;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: TextLinkScope.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextRangeLayoutModifier implements ParentDataModifier {
    private final TextRangeScopeMeasurePolicy measurePolicy;

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ boolean all(Function1 function1) {
        return ((Boolean) function1.invoke(this)).booleanValue();
    }

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ Object foldIn(Object obj, Function2 function2) {
        return function2.invoke(obj, this);
    }

    @Override // androidx.compose.ui.layout.ParentDataModifier
    public TextRangeLayoutModifier modifyParentData(Density density, Object obj) {
        return this;
    }

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ Modifier then(Modifier modifier) {
        return Modifier.CC.$default$then(this, modifier);
    }

    public TextRangeLayoutModifier(TextRangeScopeMeasurePolicy textRangeScopeMeasurePolicy) {
        this.measurePolicy = textRangeScopeMeasurePolicy;
    }

    public final TextRangeScopeMeasurePolicy getMeasurePolicy() {
        return this.measurePolicy;
    }
}
