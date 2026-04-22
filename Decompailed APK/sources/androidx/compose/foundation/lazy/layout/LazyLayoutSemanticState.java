package androidx.compose.foundation.lazy.layout;

import androidx.compose.ui.semantics.CollectionInfo;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: LazyLayoutSemantics.kt */
/* JADX INFO: loaded from: classes.dex */
public interface LazyLayoutSemanticState {
    CollectionInfo collectionInfo();

    int getContentPadding();

    float getMaxScrollOffset();

    float getScrollOffset();

    int getViewport();

    Object scrollToItem(int i, Continuation continuation);
}
