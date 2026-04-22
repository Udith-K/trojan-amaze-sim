package kotlin.reflect.jvm;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.reflect.jvm.internal.EmptyContainerForLocal;
import kotlin.reflect.jvm.internal.KFunctionImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;

/* JADX INFO: compiled from: reflectLambda.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ReflectLambdaKt {
    public static final KFunction reflect(Function function) {
        Intrinsics.checkNotNullParameter(function, "<this>");
        Metadata metadata = (Metadata) function.getClass().getAnnotation(Metadata.class);
        if (metadata == null) {
            return null;
        }
        String[] strArrD1 = metadata.d1();
        if (strArrD1.length == 0) {
            strArrD1 = null;
        }
        if (strArrD1 == null) {
            return null;
        }
        Pair functionDataFrom = JvmProtoBufUtil.readFunctionDataFrom(strArrD1, metadata.d2());
        JvmNameResolver jvmNameResolver = (JvmNameResolver) functionDataFrom.component1();
        ProtoBuf$Function protoBuf$Function = (ProtoBuf$Function) functionDataFrom.component2();
        JvmMetadataVersion jvmMetadataVersion = new JvmMetadataVersion(metadata.mv(), (metadata.xi() & 8) != 0);
        Class<?> cls = function.getClass();
        ProtoBuf$TypeTable typeTable = protoBuf$Function.getTypeTable();
        Intrinsics.checkNotNullExpressionValue(typeTable, "getTypeTable(...)");
        return new KFunctionImpl(EmptyContainerForLocal.INSTANCE, (SimpleFunctionDescriptor) UtilKt.deserializeToDescriptor(cls, protoBuf$Function, jvmNameResolver, new TypeTable(typeTable), jvmMetadataVersion, ReflectLambdaKt$reflect$descriptor$1.INSTANCE));
    }
}
