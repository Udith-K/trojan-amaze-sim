package androidx.collection;

/* JADX INFO: compiled from: ObjectLongMap.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ObjectLongMapKt {
    private static final MutableObjectLongMap EmptyObjectLongMap = new MutableObjectLongMap(0);

    public static final MutableObjectLongMap mutableObjectLongMapOf() {
        return new MutableObjectLongMap(0, 1, null);
    }
}
