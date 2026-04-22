package kotlin.reflect.jvm.internal;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.full.IllegalCallableAccessException;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

/* JADX INFO: compiled from: KProperty2Impl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class KMutableProperty2Impl extends KProperty2Impl implements KProperty, Function2, KMutableProperty {
    private final Lazy _setter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KMutableProperty2Impl(KDeclarationContainerImpl container, PropertyDescriptor descriptor) {
        super(container, descriptor);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this._setter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KMutableProperty2Impl$$Lambda$0
            private final KMutableProperty2Impl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KMutableProperty2Impl._setter$lambda$0(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Setter _setter$lambda$0(KMutableProperty2Impl kMutableProperty2Impl) {
        return new Setter(kMutableProperty2Impl);
    }

    @Override // kotlin.reflect.KMutableProperty
    public Setter getSetter() {
        return (Setter) this._setter.getValue();
    }

    public void set(Object obj, Object obj2, Object obj3) throws IllegalCallableAccessException {
        getSetter().call(obj, obj2, obj3);
    }

    /* JADX INFO: compiled from: KProperty2Impl.kt */
    public static final class Setter extends KPropertyImpl.Setter implements KMutableProperty.Setter, Function3 {
        private final KMutableProperty2Impl property;

        public Setter(KMutableProperty2Impl property) {
            Intrinsics.checkNotNullParameter(property, "property");
            this.property = property;
        }

        @Override // kotlin.reflect.jvm.internal.KPropertyImpl.Accessor, kotlin.reflect.KProperty.Accessor
        public KMutableProperty2Impl getProperty() {
            return this.property;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) throws IllegalCallableAccessException {
            m2744invoke(obj, obj2, obj3);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public void m2744invoke(Object obj, Object obj2, Object obj3) throws IllegalCallableAccessException {
            getProperty().set(obj, obj2, obj3);
        }
    }
}
