package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* JADX INFO: compiled from: ScopesHolderForClass.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ScopesHolderForClass {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ScopesHolderForClass.class), "scopeForOwnerModule", "getScopeForOwnerModule()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;"))};
    public static final Companion Companion = new Companion(null);
    private final ClassDescriptor classDescriptor;
    private final KotlinTypeRefiner kotlinTypeRefinerForOwnerModule;
    private final Function1 scopeFactory;
    private final NotNullLazyValue scopeForOwnerModule$delegate;

    public /* synthetic */ ScopesHolderForClass(ClassDescriptor classDescriptor, StorageManager storageManager, Function1 function1, KotlinTypeRefiner kotlinTypeRefiner, DefaultConstructorMarker defaultConstructorMarker) {
        this(classDescriptor, storageManager, function1, kotlinTypeRefiner);
    }

    private ScopesHolderForClass(ClassDescriptor classDescriptor, StorageManager storageManager, Function1 function1, KotlinTypeRefiner kotlinTypeRefiner) {
        this.classDescriptor = classDescriptor;
        this.scopeFactory = function1;
        this.kotlinTypeRefinerForOwnerModule = kotlinTypeRefiner;
        this.scopeForOwnerModule$delegate = storageManager.createLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.ScopesHolderForClass$$Lambda$0
            private final ScopesHolderForClass arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return ScopesHolderForClass.scopeForOwnerModule_delegate$lambda$0(this.arg$0);
            }
        });
    }

    private final MemberScope getScopeForOwnerModule() {
        return (MemberScope) StorageKt.getValue(this.scopeForOwnerModule$delegate, this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MemberScope scopeForOwnerModule_delegate$lambda$0(ScopesHolderForClass scopesHolderForClass) {
        return (MemberScope) scopesHolderForClass.scopeFactory.invoke(scopesHolderForClass.kotlinTypeRefinerForOwnerModule);
    }

    public final MemberScope getScope(final KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        if (!kotlinTypeRefiner.isRefinementNeededForModule(DescriptorUtilsKt.getModule(this.classDescriptor))) {
            return getScopeForOwnerModule();
        }
        TypeConstructor typeConstructor = this.classDescriptor.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "getTypeConstructor(...)");
        return !kotlinTypeRefiner.isRefinementNeededForTypeConstructor(typeConstructor) ? getScopeForOwnerModule() : kotlinTypeRefiner.getOrPutScopeForClass(this.classDescriptor, new Function0(this, kotlinTypeRefiner) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.ScopesHolderForClass$$Lambda$1
            private final ScopesHolderForClass arg$0;
            private final KotlinTypeRefiner arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = kotlinTypeRefiner;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return ScopesHolderForClass.getScope$lambda$1(this.arg$0, this.arg$1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MemberScope getScope$lambda$1(ScopesHolderForClass scopesHolderForClass, KotlinTypeRefiner kotlinTypeRefiner) {
        return (MemberScope) scopesHolderForClass.scopeFactory.invoke(kotlinTypeRefiner);
    }

    /* JADX INFO: compiled from: ScopesHolderForClass.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScopesHolderForClass create(ClassDescriptor classDescriptor, StorageManager storageManager, KotlinTypeRefiner kotlinTypeRefinerForOwnerModule, Function1 scopeFactory) {
            Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
            Intrinsics.checkNotNullParameter(storageManager, "storageManager");
            Intrinsics.checkNotNullParameter(kotlinTypeRefinerForOwnerModule, "kotlinTypeRefinerForOwnerModule");
            Intrinsics.checkNotNullParameter(scopeFactory, "scopeFactory");
            return new ScopesHolderForClass(classDescriptor, storageManager, scopeFactory, kotlinTypeRefinerForOwnerModule, null);
        }
    }
}
