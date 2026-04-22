package kotlinx.serialization.descriptors;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: SerialDescriptor.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class SerialDescriptorKt$elementNames$1$1 implements Iterator, KMappedMarker {
    final /* synthetic */ SerialDescriptor $this_elementNames;
    private int elementsLeft;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    SerialDescriptorKt$elementNames$1$1(SerialDescriptor serialDescriptor) {
        this.$this_elementNames = serialDescriptor;
        this.elementsLeft = serialDescriptor.getElementsCount();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.elementsLeft > 0;
    }

    @Override // java.util.Iterator
    public String next() {
        SerialDescriptor serialDescriptor = this.$this_elementNames;
        int elementsCount = serialDescriptor.getElementsCount();
        int i = this.elementsLeft;
        this.elementsLeft = i - 1;
        return serialDescriptor.getElementName(elementsCount - i);
    }
}
