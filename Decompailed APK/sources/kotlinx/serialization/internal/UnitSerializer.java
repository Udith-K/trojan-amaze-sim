package kotlinx.serialization.internal;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: Primitives.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class UnitSerializer implements KSerializer {
    public static final UnitSerializer INSTANCE = new UnitSerializer();
    private final /* synthetic */ ObjectSerializer $$delegate_0 = new ObjectSerializer("kotlin.Unit", Unit.INSTANCE);

    /* JADX INFO: renamed from: deserialize, reason: collision with other method in class */
    public void m2883deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        this.$$delegate_0.deserialize(decoder);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.$$delegate_0.getDescriptor();
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Unit value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        this.$$delegate_0.serialize(encoder, value);
    }

    private UnitSerializer() {
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object deserialize(Decoder decoder) {
        m2883deserialize(decoder);
        return Unit.INSTANCE;
    }
}
