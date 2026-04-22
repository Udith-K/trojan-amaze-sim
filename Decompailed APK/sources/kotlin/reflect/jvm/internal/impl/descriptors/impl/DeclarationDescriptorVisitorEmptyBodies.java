package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DeclarationDescriptorVisitorEmptyBodies implements DeclarationDescriptorVisitor {
    public Object visitDeclarationDescriptor(DeclarationDescriptor declarationDescriptor, Object obj) {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public abstract Object visitFunctionDescriptor(FunctionDescriptor functionDescriptor, Object obj);

    public Object visitVariableDescriptor(VariableDescriptor variableDescriptor, Object obj) {
        return visitDeclarationDescriptor(variableDescriptor, obj);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitTypeParameterDescriptor(TypeParameterDescriptor typeParameterDescriptor, Object obj) {
        return visitDeclarationDescriptor(typeParameterDescriptor, obj);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitPackageFragmentDescriptor(PackageFragmentDescriptor packageFragmentDescriptor, Object obj) {
        return visitDeclarationDescriptor(packageFragmentDescriptor, obj);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitPackageViewDescriptor(PackageViewDescriptor packageViewDescriptor, Object obj) {
        return visitDeclarationDescriptor(packageViewDescriptor, obj);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitClassDescriptor(ClassDescriptor classDescriptor, Object obj) {
        return visitDeclarationDescriptor(classDescriptor, obj);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitTypeAliasDescriptor(TypeAliasDescriptor typeAliasDescriptor, Object obj) {
        return visitDeclarationDescriptor(typeAliasDescriptor, obj);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitModuleDeclaration(ModuleDescriptor moduleDescriptor, Object obj) {
        return visitDeclarationDescriptor(moduleDescriptor, obj);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitConstructorDescriptor(ConstructorDescriptor constructorDescriptor, Object obj) {
        return visitFunctionDescriptor(constructorDescriptor, obj);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitValueParameterDescriptor(ValueParameterDescriptor valueParameterDescriptor, Object obj) {
        return visitVariableDescriptor(valueParameterDescriptor, obj);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitPropertyGetterDescriptor(PropertyGetterDescriptor propertyGetterDescriptor, Object obj) {
        return visitFunctionDescriptor(propertyGetterDescriptor, obj);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitPropertySetterDescriptor(PropertySetterDescriptor propertySetterDescriptor, Object obj) {
        return visitFunctionDescriptor(propertySetterDescriptor, obj);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitReceiverParameterDescriptor(ReceiverParameterDescriptor receiverParameterDescriptor, Object obj) {
        return visitDeclarationDescriptor(receiverParameterDescriptor, obj);
    }
}
