package kotlin.reflect.jvm.internal;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty0;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

/* JADX INFO: compiled from: KProperty0Impl.kt */
/* JADX INFO: loaded from: classes2.dex */
public class KProperty0Impl extends KPropertyImpl implements KProperty0 {
    private final Lazy _getter;
    private final Lazy delegateValue;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KProperty0Impl(KDeclarationContainerImpl container, PropertyDescriptor descriptor) {
        super(container, descriptor);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this._getter = LazyKt.lazy(lazyThreadSafetyMode, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KProperty0Impl$$Lambda$0
            private final KProperty0Impl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KProperty0Impl._getter$lambda$0(this.arg$0);
            }
        });
        this.delegateValue = LazyKt.lazy(lazyThreadSafetyMode, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KProperty0Impl$$Lambda$1
            private final KProperty0Impl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KProperty0Impl.delegateValue$lambda$1(this.arg$0);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KProperty0Impl(KDeclarationContainerImpl container, String name, String signature, Object obj) {
        super(container, name, signature, obj);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signature, "signature");
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this._getter = LazyKt.lazy(lazyThreadSafetyMode, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KProperty0Impl$$Lambda$0
            private final KProperty0Impl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KProperty0Impl._getter$lambda$0(this.arg$0);
            }
        });
        this.delegateValue = LazyKt.lazy(lazyThreadSafetyMode, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KProperty0Impl$$Lambda$1
            private final KProperty0Impl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KProperty0Impl.delegateValue$lambda$1(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Getter _getter$lambda$0(KProperty0Impl kProperty0Impl) {
        return new Getter(kProperty0Impl);
    }

    @Override // kotlin.reflect.jvm.internal.KPropertyImpl, kotlin.reflect.KProperty
    public Getter getGetter() {
        return (Getter) this._getter.getValue();
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return getGetter().call(new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object delegateValue$lambda$1(KProperty0Impl kProperty0Impl) {
        return kProperty0Impl.getDelegateImpl(kProperty0Impl.computeDelegateSource(), null, null);
    }

    @Override // kotlin.jvm.functions.Function0
    public Object invoke() {
        return get();
    }

    /* JADX INFO: compiled from: KProperty0Impl.kt */
    public static final class Getter extends KPropertyImpl.Getter implements KProperty0.Getter {
        private final KProperty0Impl property;

        public Getter(KProperty0Impl property) {
            Intrinsics.checkNotNullParameter(property, "property");
            this.property = property;
        }

        @Override // kotlin.reflect.jvm.internal.KPropertyImpl.Accessor, kotlin.reflect.KProperty.Accessor
        public KProperty0Impl getProperty() {
            return this.property;
        }

        @Override // kotlin.jvm.functions.Function0
        public Object invoke() {
            return getProperty().get();
        }
    }
}
