package kotlin.reflect.jvm.internal;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: compiled from: EmptyContainerForLocal.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class EmptyContainerForLocal extends KDeclarationContainerImpl {
    public static final EmptyContainerForLocal INSTANCE = new EmptyContainerForLocal();

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public PropertyDescriptor getLocalProperty(int i) {
        return null;
    }

    private EmptyContainerForLocal() {
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class getJClass() {
        fail();
        throw null;
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getConstructorDescriptors() {
        fail();
        throw null;
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getProperties(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        fail();
        throw null;
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getFunctions(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        fail();
        throw null;
    }

    private final Void fail() {
        throw new KotlinReflectionInternalError("Introspecting local functions, lambdas, anonymous functions, local variables and typealiases is not yet fully supported in Kotlin reflection");
    }
}
