package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface ClassifierDescriptorWithTypeParameters extends ClassifierDescriptor, MemberDescriptor, Substitutable {
    List getDeclaredTypeParameters();

    boolean isInner();
}
