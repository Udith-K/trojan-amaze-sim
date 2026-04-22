package kotlin.reflect.jvm.internal.impl.util;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ArrayMapOwner.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractArrayMapOwner implements Iterable, KMappedMarker {
    protected abstract ArrayMap getArrayMap();

    protected abstract TypeRegistry getTypeRegistry();

    protected abstract void registerComponent(String str, Object obj);

    /* JADX INFO: compiled from: ArrayMapOwner.kt */
    public static abstract class AbstractArrayMapAccessor {
        private final int id;

        public AbstractArrayMapAccessor(int i) {
            this.id = i;
        }

        protected final Object extractValue(AbstractArrayMapOwner thisRef) {
            Intrinsics.checkNotNullParameter(thisRef, "thisRef");
            return thisRef.getArrayMap().get(this.id);
        }
    }

    protected final void registerComponent(KClass tClass, Object value) {
        Intrinsics.checkNotNullParameter(tClass, "tClass");
        Intrinsics.checkNotNullParameter(value, "value");
        String qualifiedName = tClass.getQualifiedName();
        Intrinsics.checkNotNull(qualifiedName);
        registerComponent(qualifiedName, value);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return getArrayMap().iterator();
    }

    public final boolean isEmpty() {
        return getArrayMap().getSize() == 0;
    }
}
