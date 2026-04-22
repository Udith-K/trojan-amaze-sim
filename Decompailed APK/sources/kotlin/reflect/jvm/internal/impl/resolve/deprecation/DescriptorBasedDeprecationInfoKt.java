package kotlin.reflect.jvm.internal.impl.resolve.deprecation;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;

/* JADX INFO: compiled from: DescriptorBasedDeprecationInfo.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class DescriptorBasedDeprecationInfoKt {
    private static final CallableDescriptor.UserDataKey DEPRECATED_FUNCTION_KEY = new CallableDescriptor.UserDataKey() { // from class: kotlin.reflect.jvm.internal.impl.resolve.deprecation.DescriptorBasedDeprecationInfoKt$DEPRECATED_FUNCTION_KEY$1
    };

    public static final CallableDescriptor.UserDataKey getDEPRECATED_FUNCTION_KEY() {
        return DEPRECATED_FUNCTION_KEY;
    }
}
