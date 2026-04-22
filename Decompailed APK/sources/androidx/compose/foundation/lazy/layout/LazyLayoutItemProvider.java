package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.Composer;

/* JADX INFO: compiled from: LazyLayoutItemProvider.kt */
/* JADX INFO: loaded from: classes.dex */
public interface LazyLayoutItemProvider {
    void Item(int i, Object obj, Composer composer, int i2);

    Object getContentType(int i);

    int getIndex(Object obj);

    int getItemCount();

    Object getKey(int i);
}
