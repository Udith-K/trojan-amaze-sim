package kotlin.reflect.jvm.internal.impl.types.error;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;

/* JADX INFO: compiled from: ErrorFunctionDescriptor.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ErrorFunctionDescriptor extends SimpleFunctionDescriptorImpl {
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public SimpleFunctionDescriptor copy(DeclarationDescriptor newOwner, Modality modality, DescriptorVisibility visibility, CallableMemberDescriptor.Kind kind, boolean z) {
        Intrinsics.checkNotNullParameter(newOwner, "newOwner");
        Intrinsics.checkNotNullParameter(modality, "modality");
        Intrinsics.checkNotNullParameter(visibility, "visibility");
        Intrinsics.checkNotNullParameter(kind, "kind");
        return this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    protected FunctionDescriptorImpl createSubstitutedCopy(DeclarationDescriptor newOwner, FunctionDescriptor functionDescriptor, CallableMemberDescriptor.Kind kind, Name name, Annotations annotations, SourceElement source) {
        Intrinsics.checkNotNullParameter(newOwner, "newOwner");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(source, "source");
        return this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public Object getUserData(CallableDescriptor.UserDataKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isSuspend() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public void setOverriddenDescriptors(Collection overriddenDescriptors) {
        Intrinsics.checkNotNullParameter(overriddenDescriptors, "overriddenDescriptors");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ErrorFunctionDescriptor(ClassDescriptor containingDeclaration) {
        super(containingDeclaration, null, Annotations.Companion.getEMPTY(), Name.special(ErrorEntity.ERROR_FUNCTION.getDebugText()), CallableMemberDescriptor.Kind.DECLARATION, SourceElement.NO_SOURCE);
        Intrinsics.checkNotNullParameter(containingDeclaration, "containingDeclaration");
        initialize((ReceiverParameterDescriptor) null, (ReceiverParameterDescriptor) null, CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), (KotlinType) ErrorUtils.createErrorType(ErrorTypeKind.RETURN_TYPE_FOR_FUNCTION, new String[0]), Modality.OPEN, DescriptorVisibilities.PUBLIC);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public FunctionDescriptor.CopyBuilder newCopyBuilder() {
        return new FunctionDescriptor.CopyBuilder() { // from class: kotlin.reflect.jvm.internal.impl.types.error.ErrorFunctionDescriptor.newCopyBuilder.1
            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder putUserData(CallableDescriptor.UserDataKey userDataKey, Object obj) {
                Intrinsics.checkNotNullParameter(userDataKey, "userDataKey");
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setAdditionalAnnotations(Annotations additionalAnnotations) {
                Intrinsics.checkNotNullParameter(additionalAnnotations, "additionalAnnotations");
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setCopyOverrides(boolean z) {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setDispatchReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor) {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setDropOriginalInContainingParts() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setExtensionReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor) {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setHiddenForResolutionEverywhereBesideSupercalls() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setHiddenToOvercomeSignatureClash() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setKind(CallableMemberDescriptor.Kind kind) {
                Intrinsics.checkNotNullParameter(kind, "kind");
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setModality(Modality modality) {
                Intrinsics.checkNotNullParameter(modality, "modality");
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setName(Name name) {
                Intrinsics.checkNotNullParameter(name, "name");
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setOriginal(CallableMemberDescriptor callableMemberDescriptor) {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setOwner(DeclarationDescriptor owner) {
                Intrinsics.checkNotNullParameter(owner, "owner");
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setPreserveSourceElement() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setReturnType(KotlinType type) {
                Intrinsics.checkNotNullParameter(type, "type");
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setSignatureChange() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setSubstitution(TypeSubstitution substitution) {
                Intrinsics.checkNotNullParameter(substitution, "substitution");
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setTypeParameters(List parameters) {
                Intrinsics.checkNotNullParameter(parameters, "parameters");
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setValueParameters(List parameters) {
                Intrinsics.checkNotNullParameter(parameters, "parameters");
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder setVisibility(DescriptorVisibility visibility) {
                Intrinsics.checkNotNullParameter(visibility, "visibility");
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public SimpleFunctionDescriptor build() {
                return ErrorFunctionDescriptor.this;
            }
        };
    }
}
