package kotlinx.serialization.descriptors;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: Iterables.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class SerialDescriptorKt$special$$inlined$Iterable$2 implements Iterable, KMappedMarker {
    final /* synthetic */ SerialDescriptor $this_elementNames$inlined;

    public SerialDescriptorKt$special$$inlined$Iterable$2(SerialDescriptor serialDescriptor) {
        this.$this_elementNames$inlined = serialDescriptor;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new SerialDescriptorKt$elementNames$1$1(this.$this_elementNames$inlined);
    }
}
