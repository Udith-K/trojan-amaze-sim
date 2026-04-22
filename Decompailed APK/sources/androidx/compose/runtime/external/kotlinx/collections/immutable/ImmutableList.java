package androidx.compose.runtime.external.kotlinx.collections.immutable;

import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation;
import java.util.List;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: ImmutableList.kt */
/* JADX INFO: loaded from: classes.dex */
public interface ImmutableList extends List, ImmutableCollection, KMappedMarker {

    /* JADX INFO: renamed from: androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableList$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ImmutableList.kt */
    public abstract /* synthetic */ class CC {
        public static ImmutableList $default$subList(ImmutableList immutableList, int i, int i2) {
            return new SubList(immutableList, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ImmutableList.kt */
    protected static final class SubList extends AbstractList implements ImmutableList {
        private int _size;
        private final int fromIndex;
        private final ImmutableList source;
        private final int toIndex;

        public SubList(ImmutableList immutableList, int i, int i2) {
            this.source = immutableList;
            this.fromIndex = i;
            this.toIndex = i2;
            ListImplementation.checkRangeIndexes$runtime_release(i, i2, immutableList.size());
            this._size = i2 - i;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public Object get(int i) {
            ListImplementation.checkElementIndex$runtime_release(i, this._size);
            return this.source.get(this.fromIndex + i);
        }

        @Override // kotlin.collections.AbstractCollection
        public int getSize() {
            return this._size;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public ImmutableList subList(int i, int i2) {
            ListImplementation.checkRangeIndexes$runtime_release(i, i2, this._size);
            ImmutableList immutableList = this.source;
            int i3 = this.fromIndex;
            return new SubList(immutableList, i + i3, i3 + i2);
        }
    }
}
