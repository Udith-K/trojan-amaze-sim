package kotlin.reflect.jvm.internal.impl.descriptors;

/* JADX INFO: loaded from: classes2.dex */
public interface DeclarationDescriptorVisitor {
    Object visitClassDescriptor(ClassDescriptor classDescriptor, Object obj);

    Object visitConstructorDescriptor(ConstructorDescriptor constructorDescriptor, Object obj);

    Object visitFunctionDescriptor(FunctionDescriptor functionDescriptor, Object obj);

    Object visitModuleDeclaration(ModuleDescriptor moduleDescriptor, Object obj);

    Object visitPackageFragmentDescriptor(PackageFragmentDescriptor packageFragmentDescriptor, Object obj);

    Object visitPackageViewDescriptor(PackageViewDescriptor packageViewDescriptor, Object obj);

    Object visitPropertyDescriptor(PropertyDescriptor propertyDescriptor, Object obj);

    Object visitPropertyGetterDescriptor(PropertyGetterDescriptor propertyGetterDescriptor, Object obj);

    Object visitPropertySetterDescriptor(PropertySetterDescriptor propertySetterDescriptor, Object obj);

    Object visitReceiverParameterDescriptor(ReceiverParameterDescriptor receiverParameterDescriptor, Object obj);

    Object visitTypeAliasDescriptor(TypeAliasDescriptor typeAliasDescriptor, Object obj);

    Object visitTypeParameterDescriptor(TypeParameterDescriptor typeParameterDescriptor, Object obj);

    Object visitValueParameterDescriptor(ValueParameterDescriptor valueParameterDescriptor, Object obj);
}
