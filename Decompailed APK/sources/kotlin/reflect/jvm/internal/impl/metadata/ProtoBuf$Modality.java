package kotlin.reflect.jvm.internal.impl.metadata;

import kotlin.reflect.jvm.internal.impl.protobuf.Internal;

/* JADX INFO: loaded from: classes2.dex */
public enum ProtoBuf$Modality implements Internal.EnumLite {
    FINAL(0, 0),
    OPEN(1, 1),
    ABSTRACT(2, 2),
    SEALED(3, 3);

    private static Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality.1
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
        public ProtoBuf$Modality findValueByNumber(int i) {
            return ProtoBuf$Modality.valueOf(i);
        }
    };
    private final int value;

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    public static ProtoBuf$Modality valueOf(int i) {
        if (i == 0) {
            return FINAL;
        }
        if (i == 1) {
            return OPEN;
        }
        if (i == 2) {
            return ABSTRACT;
        }
        if (i != 3) {
            return null;
        }
        return SEALED;
    }

    ProtoBuf$Modality(int i, int i2) {
        this.value = i2;
    }
}
