package kotlinx.serialization;

import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;

/* JADX INFO: compiled from: KSerializer.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface DeserializationStrategy {
    Object deserialize(Decoder decoder);

    SerialDescriptor getDescriptor();
}
