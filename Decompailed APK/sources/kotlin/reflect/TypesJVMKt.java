package kotlin.reflect;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KTypeBase;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TypesJVM.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class TypesJVMKt {

    /* JADX INFO: compiled from: TypesJVM.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KVariance.values().length];
            try {
                iArr[KVariance.IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KVariance.INVARIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KVariance.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Type getJavaType(KType kType) {
        Type javaType;
        Intrinsics.checkNotNullParameter(kType, "<this>");
        return (!(kType instanceof KTypeBase) || (javaType = ((KTypeBase) kType).getJavaType()) == null) ? computeJavaType$default(kType, false, 1, null) : javaType;
    }

    static /* synthetic */ Type computeJavaType$default(KType kType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return computeJavaType(kType, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Type computeJavaType(KType kType, boolean z) {
        KClassifier classifier = kType.getClassifier();
        if (classifier instanceof KTypeParameter) {
            return new TypeVariableImpl((KTypeParameter) classifier);
        }
        if (classifier instanceof KClass) {
            KClass kClass = (KClass) classifier;
            Class javaObjectType = z ? JvmClassMappingKt.getJavaObjectType(kClass) : JvmClassMappingKt.getJavaClass(kClass);
            List arguments = kType.getArguments();
            if (arguments.isEmpty()) {
                return javaObjectType;
            }
            if (javaObjectType.isArray()) {
                if (javaObjectType.getComponentType().isPrimitive()) {
                    return javaObjectType;
                }
                KTypeProjection kTypeProjection = (KTypeProjection) CollectionsKt.singleOrNull(arguments);
                if (kTypeProjection == null) {
                    throw new IllegalArgumentException("kotlin.Array must have exactly one type argument: " + kType);
                }
                KVariance kVarianceComponent1 = kTypeProjection.component1();
                KType kTypeComponent2 = kTypeProjection.component2();
                int i = kVarianceComponent1 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kVarianceComponent1.ordinal()];
                if (i == -1 || i == 1) {
                    return javaObjectType;
                }
                if (i == 2 || i == 3) {
                    Intrinsics.checkNotNull(kTypeComponent2);
                    Type typeComputeJavaType$default = computeJavaType$default(kTypeComponent2, false, 1, null);
                    return typeComputeJavaType$default instanceof Class ? javaObjectType : new GenericArrayTypeImpl(typeComputeJavaType$default);
                }
                throw new NoWhenBranchMatchedException();
            }
            return createPossiblyInnerType(javaObjectType, arguments);
        }
        throw new UnsupportedOperationException("Unsupported type classifier: " + kType);
    }

    private static final Type createPossiblyInnerType(Class cls, List list) {
        Class<?> declaringClass = cls.getDeclaringClass();
        if (declaringClass == null) {
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(getJavaType((KTypeProjection) it.next()));
            }
            return new ParameterizedTypeImpl(cls, null, arrayList);
        }
        if (Modifier.isStatic(cls.getModifiers())) {
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList2.add(getJavaType((KTypeProjection) it2.next()));
            }
            return new ParameterizedTypeImpl(cls, declaringClass, arrayList2);
        }
        int length = cls.getTypeParameters().length;
        Type typeCreatePossiblyInnerType = createPossiblyInnerType(declaringClass, list.subList(length, list.size()));
        List listSubList = list.subList(0, length);
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSubList, 10));
        Iterator it3 = listSubList.iterator();
        while (it3.hasNext()) {
            arrayList3.add(getJavaType((KTypeProjection) it3.next()));
        }
        return new ParameterizedTypeImpl(cls, typeCreatePossiblyInnerType, arrayList3);
    }

    private static final Type getJavaType(KTypeProjection kTypeProjection) {
        KVariance variance = kTypeProjection.getVariance();
        if (variance == null) {
            return WildcardTypeImpl.Companion.getSTAR();
        }
        KType type = kTypeProjection.getType();
        Intrinsics.checkNotNull(type);
        int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
        if (i == 1) {
            return new WildcardTypeImpl(null, computeJavaType(type, true));
        }
        if (i == 2) {
            return computeJavaType(type, true);
        }
        if (i == 3) {
            return new WildcardTypeImpl(computeJavaType(type, true), null);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String typeToString(Type type) {
        String name;
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (cls.isArray()) {
                Sequence sequenceGenerateSequence = SequencesKt.generateSequence(type, TypesJVMKt$typeToString$unwrap$1.INSTANCE);
                name = ((Class) SequencesKt.last(sequenceGenerateSequence)).getName() + StringsKt.repeat("[]", SequencesKt.count(sequenceGenerateSequence));
            } else {
                name = cls.getName();
            }
            Intrinsics.checkNotNull(name);
            return name;
        }
        return type.toString();
    }
}
