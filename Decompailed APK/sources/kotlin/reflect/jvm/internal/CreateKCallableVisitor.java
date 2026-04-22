package kotlin.reflect.jvm.internal;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies;

/* JADX INFO: compiled from: util.kt */
/* JADX INFO: loaded from: classes2.dex */
public class CreateKCallableVisitor extends DeclarationDescriptorVisitorEmptyBodies {
    private final KDeclarationContainerImpl container;

    public CreateKCallableVisitor(KDeclarationContainerImpl container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.container = container;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public KCallableImpl visitPropertyDescriptor(PropertyDescriptor descriptor, Unit data) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(data, "data");
        int i = (descriptor.getDispatchReceiverParameter() != null ? 1 : 0) + (descriptor.getExtensionReceiverParameter() != null ? 1 : 0);
        if (descriptor.isVar()) {
            if (i == 0) {
                return new KMutableProperty0Impl(this.container, descriptor);
            }
            if (i == 1) {
                return new KMutableProperty1Impl(this.container, descriptor);
            }
            if (i == 2) {
                return new KMutableProperty2Impl(this.container, descriptor);
            }
        } else {
            if (i == 0) {
                return new KProperty0Impl(this.container, descriptor);
            }
            if (i == 1) {
                return new KProperty1Impl(this.container, descriptor);
            }
            if (i == 2) {
                return new KProperty2Impl(this.container, descriptor);
            }
        }
        throw new KotlinReflectionInternalError("Unsupported property: " + descriptor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public KCallableImpl visitFunctionDescriptor(FunctionDescriptor descriptor, Unit data) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(data, "data");
        return new KFunctionImpl(this.container, descriptor);
    }
}
