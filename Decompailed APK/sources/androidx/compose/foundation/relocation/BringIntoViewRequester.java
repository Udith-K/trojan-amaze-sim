package androidx.compose.foundation.relocation;

import androidx.compose.ui.geometry.Rect;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: BringIntoViewRequester.kt */
/* JADX INFO: loaded from: classes.dex */
public interface BringIntoViewRequester {
    Object bringIntoView(Rect rect, Continuation continuation);
}
