package kotlinx.serialization.modules;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.internal.PlatformKt;

/* JADX INFO: compiled from: SerializersModule.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class SerialModuleImpl extends SerializersModule {
    private final Map class2ContextualFactory;
    private final Map polyBase2DefaultDeserializerProvider;
    private final Map polyBase2DefaultSerializerProvider;
    private final Map polyBase2NamedSerializers;
    public final Map polyBase2Serializers;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SerialModuleImpl(Map class2ContextualFactory, Map polyBase2Serializers, Map polyBase2DefaultSerializerProvider, Map polyBase2NamedSerializers, Map polyBase2DefaultDeserializerProvider) {
        super(null);
        Intrinsics.checkNotNullParameter(class2ContextualFactory, "class2ContextualFactory");
        Intrinsics.checkNotNullParameter(polyBase2Serializers, "polyBase2Serializers");
        Intrinsics.checkNotNullParameter(polyBase2DefaultSerializerProvider, "polyBase2DefaultSerializerProvider");
        Intrinsics.checkNotNullParameter(polyBase2NamedSerializers, "polyBase2NamedSerializers");
        Intrinsics.checkNotNullParameter(polyBase2DefaultDeserializerProvider, "polyBase2DefaultDeserializerProvider");
        this.class2ContextualFactory = class2ContextualFactory;
        this.polyBase2Serializers = polyBase2Serializers;
        this.polyBase2DefaultSerializerProvider = polyBase2DefaultSerializerProvider;
        this.polyBase2NamedSerializers = polyBase2NamedSerializers;
        this.polyBase2DefaultDeserializerProvider = polyBase2DefaultDeserializerProvider;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public SerializationStrategy getPolymorphic(KClass baseClass, Object value) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(value, "value");
        if (!PlatformKt.isInstanceOf(value, baseClass)) {
            return null;
        }
        Map map = (Map) this.polyBase2Serializers.get(baseClass);
        KSerializer kSerializer = map != null ? (KSerializer) map.get(Reflection.getOrCreateKotlinClass(value.getClass())) : null;
        if (!(kSerializer instanceof SerializationStrategy)) {
            kSerializer = null;
        }
        if (kSerializer != null) {
            return kSerializer;
        }
        Object obj = this.polyBase2DefaultSerializerProvider.get(baseClass);
        Function1 function1 = TypeIntrinsics.isFunctionOfArity(obj, 1) ? (Function1) obj : null;
        if (function1 != null) {
            return (SerializationStrategy) function1.invoke(value);
        }
        return null;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public DeserializationStrategy getPolymorphic(KClass baseClass, String str) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Map map = (Map) this.polyBase2NamedSerializers.get(baseClass);
        KSerializer kSerializer = map != null ? (KSerializer) map.get(str) : null;
        if (!(kSerializer instanceof KSerializer)) {
            kSerializer = null;
        }
        if (kSerializer != null) {
            return kSerializer;
        }
        Object obj = this.polyBase2DefaultDeserializerProvider.get(baseClass);
        Function1 function1 = TypeIntrinsics.isFunctionOfArity(obj, 1) ? (Function1) obj : null;
        if (function1 != null) {
            return (DeserializationStrategy) function1.invoke(str);
        }
        return null;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public KSerializer getContextual(KClass kClass, List typeArgumentsSerializers) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(typeArgumentsSerializers, "typeArgumentsSerializers");
        ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.class2ContextualFactory.get(kClass));
        return null;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public void dumpTo(SerializersModuleCollector collector) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        for (Map.Entry entry : this.class2ContextualFactory.entrySet()) {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(entry.getValue());
        }
        for (Map.Entry entry2 : this.polyBase2Serializers.entrySet()) {
            KClass kClass = (KClass) entry2.getKey();
            for (Map.Entry entry3 : ((Map) entry2.getValue()).entrySet()) {
                KClass kClass2 = (KClass) entry3.getKey();
                KSerializer kSerializer = (KSerializer) entry3.getValue();
                Intrinsics.checkNotNull(kClass, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                Intrinsics.checkNotNull(kClass2, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                Intrinsics.checkNotNull(kSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                collector.polymorphic(kClass, kClass2, kSerializer);
            }
        }
        for (Map.Entry entry4 : this.polyBase2DefaultSerializerProvider.entrySet()) {
            KClass kClass3 = (KClass) entry4.getKey();
            Function1 function1 = (Function1) entry4.getValue();
            Intrinsics.checkNotNull(kClass3, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
            Intrinsics.checkNotNull(function1, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'value')] kotlin.Any, kotlinx.serialization.SerializationStrategy<kotlin.Any>?>{ kotlinx.serialization.modules.SerializersModuleKt.PolymorphicSerializerProvider<kotlin.Any> }");
            collector.polymorphicDefaultSerializer(kClass3, (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1));
        }
        for (Map.Entry entry5 : this.polyBase2DefaultDeserializerProvider.entrySet()) {
            KClass kClass4 = (KClass) entry5.getKey();
            Function1 function12 = (Function1) entry5.getValue();
            Intrinsics.checkNotNull(kClass4, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
            Intrinsics.checkNotNull(function12, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'className')] kotlin.String?, kotlinx.serialization.DeserializationStrategy<out kotlin.Any>?>{ kotlinx.serialization.modules.SerializersModuleKt.PolymorphicDeserializerProvider<out kotlin.Any> }");
            collector.polymorphicDefaultDeserializer(kClass4, (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function12, 1));
        }
    }
}
