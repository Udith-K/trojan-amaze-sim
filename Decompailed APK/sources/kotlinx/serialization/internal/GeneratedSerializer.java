package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: PluginHelperInterfaces.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface GeneratedSerializer extends KSerializer {
    KSerializer[] childSerializers();

    KSerializer[] typeParametersSerializers();

    /* JADX INFO: compiled from: PluginHelperInterfaces.kt */
    public static final class DefaultImpls {
        public static KSerializer[] typeParametersSerializers(GeneratedSerializer generatedSerializer) {
            return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
        }
    }
}
