package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;

/* JADX INFO: loaded from: classes2.dex */
public interface DeclarationDescriptor extends Named, Annotated {
    Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor, Object obj);

    DeclarationDescriptor getContainingDeclaration();

    DeclarationDescriptor getOriginal();
}
