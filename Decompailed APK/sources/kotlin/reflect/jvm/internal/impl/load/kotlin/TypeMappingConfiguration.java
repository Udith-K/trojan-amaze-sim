package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: compiled from: descriptorBasedTypeSignatureMapping.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface TypeMappingConfiguration {
    KotlinType commonSupertype(Collection collection);

    String getPredefinedFullInternalNameForClass(ClassDescriptor classDescriptor);

    String getPredefinedInternalNameForClass(ClassDescriptor classDescriptor);

    Object getPredefinedTypeForClass(ClassDescriptor classDescriptor);

    KotlinType preprocessType(KotlinType kotlinType);

    void processErrorType(KotlinType kotlinType, ClassDescriptor classDescriptor);
}
