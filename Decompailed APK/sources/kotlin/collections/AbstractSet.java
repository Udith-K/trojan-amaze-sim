package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: AbstractSet.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractSet extends AbstractCollection implements Set, KMappedMarker {
    public static final Companion Companion = new Companion(null);

    protected AbstractSet() {
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            return Companion.setEquals$kotlin_stdlib(this, (Set) obj);
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return Companion.unorderedHashCode$kotlin_stdlib(this);
    }

    /* JADX INFO: compiled from: AbstractSet.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int unorderedHashCode$kotlin_stdlib(Collection c) {
            Intrinsics.checkNotNullParameter(c, "c");
            Iterator it = c.iterator();
            int iHashCode = 0;
            while (it.hasNext()) {
                Object next = it.next();
                iHashCode += next != null ? next.hashCode() : 0;
            }
            return iHashCode;
        }

        public final boolean setEquals$kotlin_stdlib(Set c, Set other) {
            Intrinsics.checkNotNullParameter(c, "c");
            Intrinsics.checkNotNullParameter(other, "other");
            if (c.size() != other.size()) {
                return false;
            }
            return c.containsAll(other);
        }
    }
}
