package kotlin.reflect.jvm.internal.impl.resolve;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleCapability;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;

/* JADX INFO: compiled from: ResolutionAnchorProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ResolutionAnchorProviderKt {
    private static final ModuleCapability RESOLUTION_ANCHOR_PROVIDER_CAPABILITY = new ModuleCapability("ResolutionAnchorProvider");

    public static final ModuleDescriptor getResolutionAnchorIfAny(ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkNotNullParameter(moduleDescriptor, "<this>");
        ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(moduleDescriptor.getCapability(RESOLUTION_ANCHOR_PROVIDER_CAPABILITY));
        return null;
    }
}
