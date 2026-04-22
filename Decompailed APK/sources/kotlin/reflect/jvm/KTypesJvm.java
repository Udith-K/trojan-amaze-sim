package kotlin.reflect.jvm;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;

/* JADX INFO: compiled from: KTypesJvm.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class KTypesJvm {
    public static final KClass getJvmErasure(KType kType) {
        KClass jvmErasure;
        Intrinsics.checkNotNullParameter(kType, "<this>");
        KClassifier classifier = kType.getClassifier();
        if (classifier != null && (jvmErasure = getJvmErasure(classifier)) != null) {
            return jvmErasure;
        }
        throw new KotlinReflectionInternalError("Cannot calculate JVM erasure for type: " + kType);
    }

    public static final KClass getJvmErasure(KClassifier kClassifier) {
        Object obj;
        KClass jvmErasure;
        Intrinsics.checkNotNullParameter(kClassifier, "<this>");
        if (kClassifier instanceof KClass) {
            return (KClass) kClassifier;
        }
        if (kClassifier instanceof KTypeParameter) {
            List upperBounds = ((KTypeParameter) kClassifier).getUpperBounds();
            Iterator it = upperBounds.iterator();
            while (true) {
                obj = null;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                KType kType = (KType) next;
                Intrinsics.checkNotNull(kType, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
                ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = ((KTypeImpl) kType).getType().getConstructor().mo2781getDeclarationDescriptor();
                ClassDescriptor classDescriptor = classifierDescriptorMo2781getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo2781getDeclarationDescriptor : null;
                if (classDescriptor != null && classDescriptor.getKind() != ClassKind.INTERFACE && classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
                    obj = next;
                    break;
                }
            }
            KType kType2 = (KType) obj;
            if (kType2 == null) {
                kType2 = (KType) CollectionsKt.firstOrNull(upperBounds);
            }
            return (kType2 == null || (jvmErasure = getJvmErasure(kType2)) == null) ? Reflection.getOrCreateKotlinClass(Object.class) : jvmErasure;
        }
        throw new KotlinReflectionInternalError("Cannot calculate JVM erasure for type: " + kClassifier);
    }
}
