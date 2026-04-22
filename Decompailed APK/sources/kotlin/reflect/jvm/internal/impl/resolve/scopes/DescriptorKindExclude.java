package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;

/* JADX INFO: compiled from: MemberScope.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class DescriptorKindExclude {
    public abstract int getFullyExcludedDescriptorKinds();

    public String toString() {
        return getClass().getSimpleName();
    }

    /* JADX INFO: compiled from: MemberScope.kt */
    public static final class NonExtensions extends DescriptorKindExclude {
        public static final NonExtensions INSTANCE = new NonExtensions();
        private static final int fullyExcludedDescriptorKinds;

        private NonExtensions() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindExclude
        public int getFullyExcludedDescriptorKinds() {
            return fullyExcludedDescriptorKinds;
        }

        static {
            DescriptorKindFilter.Companion companion = DescriptorKindFilter.Companion;
            fullyExcludedDescriptorKinds = (~(companion.getVARIABLES_MASK() | companion.getFUNCTIONS_MASK())) & companion.getALL_KINDS_MASK();
        }
    }

    /* JADX INFO: compiled from: MemberScope.kt */
    public static final class TopLevelPackages extends DescriptorKindExclude {
        public static final TopLevelPackages INSTANCE = new TopLevelPackages();

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindExclude
        public int getFullyExcludedDescriptorKinds() {
            return 0;
        }

        private TopLevelPackages() {
        }
    }
}
