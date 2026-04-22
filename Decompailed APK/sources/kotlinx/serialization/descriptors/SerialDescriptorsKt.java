package kotlinx.serialization.descriptors;

import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.internal.PrimitivesKt;

/* JADX INFO: compiled from: SerialDescriptors.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class SerialDescriptorsKt {
    public static final SerialDescriptor buildClassSerialDescriptor(String serialName, SerialDescriptor[] typeParameters, Function1 builderAction) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(typeParameters, "typeParameters");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        if (StringsKt.isBlank(serialName)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        }
        ClassSerialDescriptorBuilder classSerialDescriptorBuilder = new ClassSerialDescriptorBuilder(serialName);
        builderAction.invoke(classSerialDescriptorBuilder);
        return new SerialDescriptorImpl(serialName, StructureKind.CLASS.INSTANCE, classSerialDescriptorBuilder.getElementNames$kotlinx_serialization_core().size(), ArraysKt.toList(typeParameters), classSerialDescriptorBuilder);
    }

    public static final SerialDescriptor PrimitiveSerialDescriptor(String serialName, PrimitiveKind kind) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (StringsKt.isBlank(serialName)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        }
        return PrimitivesKt.PrimitiveDescriptorSafe(serialName, kind);
    }

    public static /* synthetic */ SerialDescriptor buildSerialDescriptor$default(String str, SerialKind serialKind, SerialDescriptor[] serialDescriptorArr, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: kotlinx.serialization.descriptors.SerialDescriptorsKt.buildSerialDescriptor.1
                public final void invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
                    Intrinsics.checkNotNullParameter(classSerialDescriptorBuilder, "$this$null");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((ClassSerialDescriptorBuilder) obj2);
                    return Unit.INSTANCE;
                }
            };
        }
        return buildSerialDescriptor(str, serialKind, serialDescriptorArr, function1);
    }

    public static final SerialDescriptor buildSerialDescriptor(String serialName, SerialKind kind, SerialDescriptor[] typeParameters, Function1 builder) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(typeParameters, "typeParameters");
        Intrinsics.checkNotNullParameter(builder, "builder");
        if (StringsKt.isBlank(serialName)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        }
        if (Intrinsics.areEqual(kind, StructureKind.CLASS.INSTANCE)) {
            throw new IllegalArgumentException("For StructureKind.CLASS please use 'buildClassSerialDescriptor' instead");
        }
        ClassSerialDescriptorBuilder classSerialDescriptorBuilder = new ClassSerialDescriptorBuilder(serialName);
        builder.invoke(classSerialDescriptorBuilder);
        return new SerialDescriptorImpl(serialName, kind, classSerialDescriptorBuilder.getElementNames$kotlinx_serialization_core().size(), ArraysKt.toList(typeParameters), classSerialDescriptorBuilder);
    }
}
