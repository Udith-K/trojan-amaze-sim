package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;

/* JADX INFO: loaded from: classes2.dex */
public interface ClassDescriptor extends ClassOrPackageFragmentDescriptor, ClassifierDescriptorWithTypeParameters {
    /* JADX INFO: renamed from: getCompanionObjectDescriptor */
    ClassDescriptor mo2746getCompanionObjectDescriptor();

    Collection getConstructors();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    DeclarationDescriptor getContainingDeclaration();

    List getContextReceivers();

    List getDeclaredTypeParameters();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    SimpleType getDefaultType();

    ClassKind getKind();

    MemberScope getMemberScope(TypeSubstitution typeSubstitution);

    Modality getModality();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    ClassDescriptor getOriginal();

    Collection getSealedSubclasses();

    MemberScope getStaticScope();

    ReceiverParameterDescriptor getThisAsReceiverParameter();

    MemberScope getUnsubstitutedInnerClassesScope();

    MemberScope getUnsubstitutedMemberScope();

    /* JADX INFO: renamed from: getUnsubstitutedPrimaryConstructor */
    ClassConstructorDescriptor mo2747getUnsubstitutedPrimaryConstructor();

    ValueClassRepresentation getValueClassRepresentation();

    DescriptorVisibility getVisibility();

    boolean isCompanionObject();

    boolean isData();

    boolean isFun();

    boolean isInline();

    boolean isValue();
}
