package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;

/* JADX INFO: compiled from: VisibilityUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class VisibilityUtilKt {
    public static final CallableMemberDescriptor findMemberWithMaxVisibility(Collection descriptors) {
        Integer numCompare;
        Intrinsics.checkNotNullParameter(descriptors, "descriptors");
        descriptors.isEmpty();
        Iterator it = descriptors.iterator();
        CallableMemberDescriptor callableMemberDescriptor = null;
        while (it.hasNext()) {
            CallableMemberDescriptor callableMemberDescriptor2 = (CallableMemberDescriptor) it.next();
            if (callableMemberDescriptor == null || ((numCompare = DescriptorVisibilities.compare(callableMemberDescriptor.getVisibility(), callableMemberDescriptor2.getVisibility())) != null && numCompare.intValue() < 0)) {
                callableMemberDescriptor = callableMemberDescriptor2;
            }
        }
        Intrinsics.checkNotNull(callableMemberDescriptor);
        return callableMemberDescriptor;
    }
}
