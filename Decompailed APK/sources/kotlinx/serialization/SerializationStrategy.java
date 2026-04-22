package kotlinx.serialization;

import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: KSerializer.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface SerializationStrategy {
    SerialDescriptor getDescriptor();

    void serialize(Encoder encoder, Object obj);
}
