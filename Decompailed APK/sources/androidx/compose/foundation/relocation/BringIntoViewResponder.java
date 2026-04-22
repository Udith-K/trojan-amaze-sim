package androidx.compose.foundation.relocation;

import androidx.compose.ui.geometry.Rect;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: BringIntoViewResponder.kt */
/* JADX INFO: loaded from: classes.dex */
public interface BringIntoViewResponder {
    Object bringChildIntoView(Function0 function0, Continuation continuation);

    Rect calculateRectForParent(Rect rect);
}
