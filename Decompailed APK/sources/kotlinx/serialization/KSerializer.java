package kotlinx.serialization;

import kotlinx.serialization.descriptors.SerialDescriptor;

/* JADX INFO: compiled from: KSerializer.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KSerializer extends SerializationStrategy, DeserializationStrategy {
    @Override // kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    SerialDescriptor getDescriptor();
}
