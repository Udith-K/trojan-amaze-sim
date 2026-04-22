package kotlinx.serialization.internal;

/* JADX INFO: compiled from: CollectionSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class PrimitiveArrayBuilder {
    public abstract Object build$kotlinx_serialization_core();

    public abstract void ensureCapacity$kotlinx_serialization_core(int i);

    public abstract int getPosition$kotlinx_serialization_core();

    public static /* synthetic */ void ensureCapacity$kotlinx_serialization_core$default(PrimitiveArrayBuilder primitiveArrayBuilder, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: ensureCapacity");
        }
        if ((i2 & 1) != 0) {
            i = primitiveArrayBuilder.getPosition$kotlinx_serialization_core() + 1;
        }
        primitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core(i);
    }
}
