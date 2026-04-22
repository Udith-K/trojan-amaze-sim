package androidx.lifecycle;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;

/* JADX INFO: compiled from: Transformations.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Transformations {
    public static final LiveData map(LiveData liveData, final Function1 transform) {
        final MediatorLiveData mediatorLiveData;
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        if (liveData.isInitialized()) {
            mediatorLiveData = new MediatorLiveData(transform.invoke(liveData.getValue()));
        } else {
            mediatorLiveData = new MediatorLiveData();
        }
        mediatorLiveData.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: androidx.lifecycle.Transformations.map.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m2541invoke(obj);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m2541invoke(Object obj) {
                mediatorLiveData.setValue(transform.invoke(obj));
            }
        }));
        return mediatorLiveData;
    }

    public static final LiveData distinctUntilChanged(LiveData liveData) {
        final MediatorLiveData mediatorLiveData;
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        ref$BooleanRef.element = true;
        if (liveData.isInitialized()) {
            ref$BooleanRef.element = false;
            mediatorLiveData = new MediatorLiveData(liveData.getValue());
        } else {
            mediatorLiveData = new MediatorLiveData();
        }
        mediatorLiveData.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: androidx.lifecycle.Transformations.distinctUntilChanged.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m2540invoke(obj);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m2540invoke(Object obj) {
                Object value = mediatorLiveData.getValue();
                if (ref$BooleanRef.element || ((value == null && obj != null) || !(value == null || Intrinsics.areEqual(value, obj)))) {
                    ref$BooleanRef.element = false;
                    mediatorLiveData.setValue(obj);
                }
            }
        }));
        return mediatorLiveData;
    }
}
