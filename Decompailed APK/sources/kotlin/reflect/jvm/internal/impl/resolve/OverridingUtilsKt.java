package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

/* JADX INFO: compiled from: overridingUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class OverridingUtilsKt {
    public static final Collection selectMostSpecificInEachOverridableGroup(Collection collection, Function1 descriptorByHandle) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(descriptorByHandle, "descriptorByHandle");
        if (collection.size() <= 1) {
            return collection;
        }
        LinkedList linkedList = new LinkedList(collection);
        SmartSet smartSetCreate = SmartSet.Companion.create();
        while (!linkedList.isEmpty()) {
            Object objFirst = CollectionsKt.first((List) linkedList);
            final SmartSet smartSetCreate2 = SmartSet.Companion.create();
            Collection collectionExtractMembersOverridableInBothWays = OverridingUtil.extractMembersOverridableInBothWays(objFirst, linkedList, descriptorByHandle, new Function1(smartSetCreate2) { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt$$Lambda$1
                private final SmartSet arg$0;

                {
                    this.arg$0 = smartSetCreate2;
                }

                @Override // kotlin.jvm.functions.Function1
                public Object invoke(Object obj) {
                    return OverridingUtilsKt.selectMostSpecificInEachOverridableGroup$lambda$2(this.arg$0, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(collectionExtractMembersOverridableInBothWays, "extractMembersOverridableInBothWays(...)");
            if (collectionExtractMembersOverridableInBothWays.size() == 1 && smartSetCreate2.isEmpty()) {
                Object objSingle = CollectionsKt.single(collectionExtractMembersOverridableInBothWays);
                Intrinsics.checkNotNullExpressionValue(objSingle, "single(...)");
                smartSetCreate.add(objSingle);
            } else {
                Object objSelectMostSpecificMember = OverridingUtil.selectMostSpecificMember(collectionExtractMembersOverridableInBothWays, descriptorByHandle);
                CallableDescriptor callableDescriptor = (CallableDescriptor) descriptorByHandle.invoke(objSelectMostSpecificMember);
                for (Object obj : collectionExtractMembersOverridableInBothWays) {
                    Intrinsics.checkNotNull(obj);
                    if (!OverridingUtil.isMoreSpecific(callableDescriptor, (CallableDescriptor) descriptorByHandle.invoke(obj))) {
                        smartSetCreate2.add(obj);
                    }
                }
                if (!smartSetCreate2.isEmpty()) {
                    smartSetCreate.addAll(smartSetCreate2);
                }
                smartSetCreate.add(objSelectMostSpecificMember);
            }
        }
        return smartSetCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit selectMostSpecificInEachOverridableGroup$lambda$2(SmartSet smartSet, Object obj) {
        Intrinsics.checkNotNull(obj);
        smartSet.add(obj);
        return Unit.INSTANCE;
    }
}
