package androidx.compose.foundation.lazy;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

/* JADX INFO: compiled from: LazyDsl.kt */
/* JADX INFO: loaded from: classes.dex */
public interface LazyListScope {
    void item(Object obj, Object obj2, Function3 function3);

    void items(int i, Function1 function1, Function1 function12, Function4 function4);

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyListScope$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: LazyDsl.kt */
    public abstract /* synthetic */ class CC {
        public static /* synthetic */ void item$default(LazyListScope lazyListScope, Object obj, Object obj2, Function3 function3, int i, Object obj3) {
            if (obj3 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: item");
            }
            if ((i & 1) != 0) {
                obj = null;
            }
            if ((i & 2) != 0) {
                obj2 = null;
            }
            lazyListScope.item(obj, obj2, function3);
        }
    }
}
