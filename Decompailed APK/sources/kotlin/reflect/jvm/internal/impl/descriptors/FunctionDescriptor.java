package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

/* JADX INFO: loaded from: classes2.dex */
public interface FunctionDescriptor extends CallableMemberDescriptor {

    public interface CopyBuilder {
        FunctionDescriptor build();

        CopyBuilder putUserData(CallableDescriptor.UserDataKey userDataKey, Object obj);

        CopyBuilder setAdditionalAnnotations(Annotations annotations);

        CopyBuilder setCopyOverrides(boolean z);

        CopyBuilder setDispatchReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor);

        CopyBuilder setDropOriginalInContainingParts();

        CopyBuilder setExtensionReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor);

        CopyBuilder setHiddenForResolutionEverywhereBesideSupercalls();

        CopyBuilder setHiddenToOvercomeSignatureClash();

        CopyBuilder setKind(CallableMemberDescriptor.Kind kind);

        CopyBuilder setModality(Modality modality);

        CopyBuilder setName(Name name);

        CopyBuilder setOriginal(CallableMemberDescriptor callableMemberDescriptor);

        CopyBuilder setOwner(DeclarationDescriptor declarationDescriptor);

        CopyBuilder setPreserveSourceElement();

        CopyBuilder setReturnType(KotlinType kotlinType);

        CopyBuilder setSignatureChange();

        CopyBuilder setSubstitution(TypeSubstitution typeSubstitution);

        CopyBuilder setTypeParameters(List list);

        CopyBuilder setValueParameters(List list);

        CopyBuilder setVisibility(DescriptorVisibility descriptorVisibility);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    DeclarationDescriptor getContainingDeclaration();

    FunctionDescriptor getInitialSignatureDescriptor();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    FunctionDescriptor getOriginal();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    Collection getOverriddenDescriptors();

    boolean isHiddenForResolutionEverywhereBesideSupercalls();

    boolean isHiddenToOvercomeSignatureClash();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    boolean isSuspend();

    boolean isTailrec();

    CopyBuilder newCopyBuilder();

    FunctionDescriptor substitute(TypeSubstitutor typeSubstitutor);
}
