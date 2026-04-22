package kotlin.reflect.jvm.internal.impl.util;

import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: ArrayMap.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ArrayMap implements Iterable, KMappedMarker {
    public /* synthetic */ ArrayMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Object get(int i);

    public abstract int getSize();

    @Override // java.lang.Iterable
    public abstract Iterator iterator();

    public abstract void set(int i, Object obj);

    private ArrayMap() {
    }
}
