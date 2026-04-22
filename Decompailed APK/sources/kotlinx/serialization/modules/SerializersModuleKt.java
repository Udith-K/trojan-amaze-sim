package kotlinx.serialization.modules;

import kotlin.collections.MapsKt;

/* JADX INFO: compiled from: SerializersModule.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class SerializersModuleKt {
    private static final SerializersModule EmptySerializersModule = new SerialModuleImpl(MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap());

    public static final SerializersModule getEmptySerializersModule() {
        return EmptySerializersModule;
    }
}
