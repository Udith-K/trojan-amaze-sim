package kotlin.reflect.jvm.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeParameterReference;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.internal.ReflectProperties;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX INFO: compiled from: KTypeParameterImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class KTypeParameterImpl implements KTypeParameter, KClassifierImpl {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KTypeParameterImpl.class), "upperBounds", "getUpperBounds()Ljava/util/List;"))};
    private final KTypeParameterOwnerImpl container;
    private final TypeParameterDescriptor descriptor;
    private final ReflectProperties.LazySoftVal upperBounds$delegate;

    /* JADX INFO: compiled from: KTypeParameterImpl.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            try {
                iArr[Variance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Variance.IN_VARIANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Variance.OUT_VARIANCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public KTypeParameterImpl(KTypeParameterOwnerImpl kTypeParameterOwnerImpl, TypeParameterDescriptor descriptor) {
        KClassImpl kClassImpl;
        Object objAccept;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.descriptor = descriptor;
        this.upperBounds$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KTypeParameterImpl$$Lambda$0
            private final KTypeParameterImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KTypeParameterImpl.upperBounds_delegate$lambda$1(this.arg$0);
            }
        });
        if (kTypeParameterOwnerImpl == null) {
            DeclarationDescriptor containingDeclaration = getDescriptor().getContainingDeclaration();
            Intrinsics.checkNotNullExpressionValue(containingDeclaration, "getContainingDeclaration(...)");
            if (containingDeclaration instanceof ClassDescriptor) {
                objAccept = toKClassImpl((ClassDescriptor) containingDeclaration);
            } else if (containingDeclaration instanceof CallableMemberDescriptor) {
                DeclarationDescriptor containingDeclaration2 = ((CallableMemberDescriptor) containingDeclaration).getContainingDeclaration();
                Intrinsics.checkNotNullExpressionValue(containingDeclaration2, "getContainingDeclaration(...)");
                if (containingDeclaration2 instanceof ClassDescriptor) {
                    kClassImpl = toKClassImpl((ClassDescriptor) containingDeclaration2);
                } else {
                    DeserializedMemberDescriptor deserializedMemberDescriptor = containingDeclaration instanceof DeserializedMemberDescriptor ? (DeserializedMemberDescriptor) containingDeclaration : null;
                    if (deserializedMemberDescriptor == null) {
                        throw new KotlinReflectionInternalError("Non-class callable descriptor must be deserialized: " + containingDeclaration);
                    }
                    KClass kotlinClass = JvmClassMappingKt.getKotlinClass(getContainerClass(deserializedMemberDescriptor));
                    Intrinsics.checkNotNull(kotlinClass, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<*>");
                    kClassImpl = (KClassImpl) kotlinClass;
                }
                objAccept = containingDeclaration.accept(new CreateKCallableVisitor(kClassImpl), Unit.INSTANCE);
            } else {
                throw new KotlinReflectionInternalError("Unknown type parameter container: " + containingDeclaration);
            }
            kTypeParameterOwnerImpl = (KTypeParameterOwnerImpl) objAccept;
        }
        this.container = kTypeParameterOwnerImpl;
    }

    @Override // kotlin.reflect.jvm.internal.KClassifierImpl
    public TypeParameterDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlin.reflect.KTypeParameter
    public String getName() {
        String strAsString = getDescriptor().getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
        return strAsString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List upperBounds_delegate$lambda$1(KTypeParameterImpl kTypeParameterImpl) {
        List upperBounds = kTypeParameterImpl.getDescriptor().getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(upperBounds, 10));
        Iterator it = upperBounds.iterator();
        while (it.hasNext()) {
            arrayList.add(new KTypeImpl((KotlinType) it.next(), null, 2, null));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.KTypeParameter
    public List getUpperBounds() {
        Object value = this.upperBounds$delegate.getValue(this, $$delegatedProperties[0]);
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (List) value;
    }

    @Override // kotlin.reflect.KTypeParameter
    public KVariance getVariance() {
        int i = WhenMappings.$EnumSwitchMapping$0[getDescriptor().getVariance().ordinal()];
        if (i == 1) {
            return KVariance.INVARIANT;
        }
        if (i == 2) {
            return KVariance.IN;
        }
        if (i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        return KVariance.OUT;
    }

    private final KClassImpl toKClassImpl(ClassDescriptor classDescriptor) {
        Class javaClass = UtilKt.toJavaClass(classDescriptor);
        KClassImpl kClassImpl = (KClassImpl) (javaClass != null ? JvmClassMappingKt.getKotlinClass(javaClass) : null);
        if (kClassImpl != null) {
            return kClassImpl;
        }
        throw new KotlinReflectionInternalError("Type parameter container is not resolved: " + classDescriptor.getContainingDeclaration());
    }

    private final Class getContainerClass(DeserializedMemberDescriptor deserializedMemberDescriptor) {
        Class klass;
        DeserializedContainerSource containerSource = deserializedMemberDescriptor.getContainerSource();
        JvmPackagePartSource jvmPackagePartSource = containerSource instanceof JvmPackagePartSource ? (JvmPackagePartSource) containerSource : null;
        KotlinJvmBinaryClass knownJvmBinaryClass = jvmPackagePartSource != null ? jvmPackagePartSource.getKnownJvmBinaryClass() : null;
        ReflectKotlinClass reflectKotlinClass = knownJvmBinaryClass instanceof ReflectKotlinClass ? (ReflectKotlinClass) knownJvmBinaryClass : null;
        if (reflectKotlinClass != null && (klass = reflectKotlinClass.getKlass()) != null) {
            return klass;
        }
        throw new KotlinReflectionInternalError("Container of deserialized member is not resolved: " + deserializedMemberDescriptor);
    }

    public boolean equals(Object obj) {
        if (obj instanceof KTypeParameterImpl) {
            KTypeParameterImpl kTypeParameterImpl = (KTypeParameterImpl) obj;
            if (Intrinsics.areEqual(this.container, kTypeParameterImpl.container) && Intrinsics.areEqual(getName(), kTypeParameterImpl.getName())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.container.hashCode() * 31) + getName().hashCode();
    }

    public String toString() {
        return TypeParameterReference.Companion.toString(this);
    }
}
