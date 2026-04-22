package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;

/* JADX INFO: compiled from: ContractDeserializer.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface ContractDeserializer {
    public static final Companion Companion = Companion.$$INSTANCE;

    Pair deserializeContractFromFunction(ProtoBuf$Function protoBuf$Function, FunctionDescriptor functionDescriptor, TypeTable typeTable, TypeDeserializer typeDeserializer);

    /* JADX INFO: compiled from: ContractDeserializer.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final ContractDeserializer DEFAULT = new ContractDeserializer() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer$Companion$DEFAULT$1
            @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer
            public Pair deserializeContractFromFunction(ProtoBuf$Function proto, FunctionDescriptor ownerFunction, TypeTable typeTable, TypeDeserializer typeDeserializer) {
                Intrinsics.checkNotNullParameter(proto, "proto");
                Intrinsics.checkNotNullParameter(ownerFunction, "ownerFunction");
                Intrinsics.checkNotNullParameter(typeTable, "typeTable");
                Intrinsics.checkNotNullParameter(typeDeserializer, "typeDeserializer");
                return null;
            }
        };

        private Companion() {
        }

        public final ContractDeserializer getDEFAULT() {
            return DEFAULT;
        }
    }
}
