package androidx.compose.ui.platform;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.ui.draganddrop.DragAndDropTransferData;
import androidx.compose.ui.geometry.Size;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: AndroidComposeView.android.kt */
/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class AndroidComposeView$dragAndDropModifierOnDragListener$1 extends FunctionReferenceImpl implements Function3 {
    AndroidComposeView$dragAndDropModifierOnDragListener$1(Object obj) {
        super(3, obj, AndroidComposeView.class, "startDrag", "startDrag-12SF9DM(Landroidx/compose/ui/draganddrop/DragAndDropTransferData;JLkotlin/jvm/functions/Function1;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
        return m1931invoke12SF9DM(null, ((Size) obj2).m1200unboximpl(), (Function1) obj3);
    }

    /* JADX INFO: renamed from: invoke-12SF9DM, reason: not valid java name */
    public final Boolean m1931invoke12SF9DM(DragAndDropTransferData dragAndDropTransferData, long j, Function1 function1) {
        return Boolean.valueOf(((AndroidComposeView) this.receiver).m1928startDrag12SF9DM(dragAndDropTransferData, j, function1));
    }
}
