package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

/* JADX INFO: compiled from: Substitutable.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface Substitutable {
    DeclarationDescriptorNonRoot substitute(TypeSubstitutor typeSubstitutor);
}
