package androidx.compose.foundation.lazy.layout;

/* JADX INFO: compiled from: LazyLayoutKeyIndexMap.kt */
/* JADX INFO: loaded from: classes.dex */
public interface LazyLayoutKeyIndexMap {
    public static final Empty Empty = Empty.$$INSTANCE;

    int getIndex(Object obj);

    Object getKey(int i);

    /* JADX INFO: compiled from: LazyLayoutKeyIndexMap.kt */
    public static final class Empty implements LazyLayoutKeyIndexMap {
        static final /* synthetic */ Empty $$INSTANCE = new Empty();

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap
        public int getIndex(Object obj) {
            return -1;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap
        public Void getKey(int i) {
            return null;
        }

        private Empty() {
        }
    }
}
