package kotlin.reflect.jvm.internal;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

/* JADX INFO: compiled from: KProperty2Impl.kt */
/* JADX INFO: loaded from: classes2.dex */
public class KProperty2Impl extends KPropertyImpl implements KProperty, Function2 {
    private final Lazy _getter;
    private final Lazy delegateSource;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KProperty2Impl(KDeclarationContainerImpl container, PropertyDescriptor descriptor) {
        super(container, descriptor);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this._getter = LazyKt.lazy(lazyThreadSafetyMode, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KProperty2Impl$$Lambda$0
            private final KProperty2Impl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KProperty2Impl._getter$lambda$0(this.arg$0);
            }
        });
        this.delegateSource = LazyKt.lazy(lazyThreadSafetyMode, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KProperty2Impl$$Lambda$1
            private final KProperty2Impl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.arg$0.computeDelegateSource();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Getter _getter$lambda$0(KProperty2Impl kProperty2Impl) {
        return new Getter(kProperty2Impl);
    }

    @Override // kotlin.reflect.jvm.internal.KPropertyImpl, kotlin.reflect.KProperty
    public Getter getGetter() {
        return (Getter) this._getter.getValue();
    }

    public Object get(Object obj, Object obj2) {
        return getGetter().call(obj, obj2);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    /* JADX INFO: compiled from: KProperty2Impl.kt */
    public static final class Getter extends KPropertyImpl.Getter implements KProperty.Getter, Function2 {
        private final KProperty2Impl property;

        public Getter(KProperty2Impl property) {
            Intrinsics.checkNotNullParameter(property, "property");
            this.property = property;
        }

        @Override // kotlin.reflect.jvm.internal.KPropertyImpl.Accessor, kotlin.reflect.KProperty.Accessor
        public KProperty2Impl getProperty() {
            return this.property;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(Object obj, Object obj2) {
            return getProperty().get(obj, obj2);
        }
    }
}
