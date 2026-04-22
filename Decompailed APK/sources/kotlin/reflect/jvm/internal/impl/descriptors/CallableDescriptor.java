package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: loaded from: classes2.dex */
public interface CallableDescriptor extends DeclarationDescriptorNonRoot, DeclarationDescriptorWithVisibility, Substitutable {

    public interface UserDataKey {
    }

    List getContextReceiverParameters();

    ReceiverParameterDescriptor getDispatchReceiverParameter();

    ReceiverParameterDescriptor getExtensionReceiverParameter();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    CallableDescriptor getOriginal();

    Collection getOverriddenDescriptors();

    KotlinType getReturnType();

    List getTypeParameters();

    Object getUserData(UserDataKey userDataKey);

    List getValueParameters();

    boolean hasSynthesizedParameterNames();
}
