package kotlin.reflect.jvm.internal.impl.descriptors;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InvalidModuleException.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class InvalidModuleExceptionKt {
    private static final ModuleCapability INVALID_MODULE_NOTIFIER_CAPABILITY = new ModuleCapability("InvalidModuleNotifier");

    public static final void moduleInvalidated(ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkNotNullParameter(moduleDescriptor, "<this>");
        ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(moduleDescriptor.getCapability(INVALID_MODULE_NOTIFIER_CAPABILITY));
        throw new InvalidModuleException("Accessing invalid module descriptor " + moduleDescriptor);
    }
}
