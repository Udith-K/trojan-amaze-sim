package androidx.compose.runtime;

/* JADX INFO: compiled from: Applier.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Applier {
    void clear();

    void down(Object obj);

    Object getCurrent();

    void insertBottomUp(int i, Object obj);

    void insertTopDown(int i, Object obj);

    void move(int i, int i2, int i3);

    void onBeginChanges();

    void onEndChanges();

    void remove(int i, int i2);

    void up();

    /* JADX INFO: renamed from: androidx.compose.runtime.Applier$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Applier.kt */
    public abstract /* synthetic */ class CC {
        public static void $default$onBeginChanges(Applier applier) {
        }

        public static void $default$onEndChanges(Applier applier) {
        }
    }
}
