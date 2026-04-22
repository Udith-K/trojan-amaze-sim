package androidx.compose.foundation.relocation;

import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: BringIntoView.kt */
/* JADX INFO: loaded from: classes.dex */
public interface BringIntoViewParent {
    Object bringChildIntoView(LayoutCoordinates layoutCoordinates, Function0 function0, Continuation continuation);
}
