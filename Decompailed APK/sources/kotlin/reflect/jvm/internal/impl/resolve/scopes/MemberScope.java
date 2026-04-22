package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

/* JADX INFO: compiled from: MemberScope.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface MemberScope extends ResolutionScope {
    public static final Companion Companion = Companion.$$INSTANCE;

    Set getClassifierNames();

    Collection getContributedFunctions(Name name, LookupLocation lookupLocation);

    Collection getContributedVariables(Name name, LookupLocation lookupLocation);

    Set getFunctionNames();

    Set getVariableNames();

    /* JADX INFO: compiled from: MemberScope.kt */
    public static final class Empty extends MemberScopeImpl {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        public Set getFunctionNames() {
            return SetsKt.emptySet();
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        public Set getVariableNames() {
            return SetsKt.emptySet();
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        public Set getClassifierNames() {
            return SetsKt.emptySet();
        }
    }

    /* JADX INFO: compiled from: MemberScope.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Function1 ALL_NAME_FILTER = new Function1() { // from class: kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope$Companion$$Lambda$0
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return Boolean.valueOf(MemberScope.Companion.ALL_NAME_FILTER$lambda$0((Name) obj));
            }
        };

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean ALL_NAME_FILTER$lambda$0(Name it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return true;
        }

        private Companion() {
        }

        public final Function1 getALL_NAME_FILTER() {
            return ALL_NAME_FILTER;
        }
    }
}
