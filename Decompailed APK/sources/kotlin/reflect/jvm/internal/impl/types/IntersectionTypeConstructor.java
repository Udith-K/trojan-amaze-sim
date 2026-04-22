package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.model.IntersectionTypeConstructorMarker;

/* JADX INFO: compiled from: IntersectionTypeConstructor.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class IntersectionTypeConstructor implements TypeConstructor, IntersectionTypeConstructorMarker {
    private KotlinType alternative;
    private final int hashCode;
    private final LinkedHashSet intersectedTypes;

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    /* JADX INFO: renamed from: getDeclarationDescriptor */
    public ClassifierDescriptor mo2781getDeclarationDescriptor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    public IntersectionTypeConstructor(Collection typesToIntersect) {
        Intrinsics.checkNotNullParameter(typesToIntersect, "typesToIntersect");
        typesToIntersect.isEmpty();
        LinkedHashSet linkedHashSet = new LinkedHashSet(typesToIntersect);
        this.intersectedTypes = linkedHashSet;
        this.hashCode = linkedHashSet.hashCode();
    }

    private IntersectionTypeConstructor(Collection collection, KotlinType kotlinType) {
        this(collection);
        this.alternative = kotlinType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public List getParameters() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    /* JADX INFO: renamed from: getSupertypes */
    public Collection mo2782getSupertypes() {
        return this.intersectedTypes;
    }

    public final MemberScope createScopeForKotlinType() {
        return TypeIntersectionScope.Companion.create("member scope for intersection type", this.intersectedTypes);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public KotlinBuiltIns getBuiltIns() {
        KotlinBuiltIns builtIns = ((KotlinType) this.intersectedTypes.iterator().next()).getConstructor().getBuiltIns();
        Intrinsics.checkNotNullExpressionValue(builtIns, "getBuiltIns(...)");
        return builtIns;
    }

    public String toString() {
        return makeDebugNameForIntersectionType$default(this, null, 1, null);
    }

    public static /* synthetic */ String makeDebugNameForIntersectionType$default(IntersectionTypeConstructor intersectionTypeConstructor, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor$$Lambda$1
                @Override // kotlin.jvm.functions.Function1
                public Object invoke(Object obj2) {
                    return IntersectionTypeConstructor.makeDebugNameForIntersectionType$lambda$1((KotlinType) obj2);
                }
            };
        }
        return intersectionTypeConstructor.makeDebugNameForIntersectionType(function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String makeDebugNameForIntersectionType$lambda$1(KotlinType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.toString();
    }

    public final String makeDebugNameForIntersectionType(final Function1 getProperTypeRelatedToStringify) {
        Intrinsics.checkNotNullParameter(getProperTypeRelatedToStringify, "getProperTypeRelatedToStringify");
        return CollectionsKt.joinToString$default(CollectionsKt.sortedWith(this.intersectedTypes, new Comparator() { // from class: kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor$makeDebugNameForIntersectionType$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                KotlinType kotlinType = (KotlinType) obj;
                Function1 function1 = getProperTypeRelatedToStringify;
                Intrinsics.checkNotNull(kotlinType);
                String string = function1.invoke(kotlinType).toString();
                KotlinType kotlinType2 = (KotlinType) obj2;
                Function1 function12 = getProperTypeRelatedToStringify;
                Intrinsics.checkNotNull(kotlinType2);
                return ComparisonsKt.compareValues(string, function12.invoke(kotlinType2).toString());
            }
        }), " & ", "{", "}", 0, null, new Function1(getProperTypeRelatedToStringify) { // from class: kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor$$Lambda$0
            private final Function1 arg$0;

            {
                this.arg$0 = getProperTypeRelatedToStringify;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return IntersectionTypeConstructor.makeDebugNameForIntersectionType$lambda$3(this.arg$0, (KotlinType) obj);
            }
        }, 24, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence makeDebugNameForIntersectionType$lambda$3(Function1 function1, KotlinType kotlinType) {
        Intrinsics.checkNotNull(kotlinType);
        return function1.invoke(kotlinType).toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IntersectionTypeConstructor) {
            return Intrinsics.areEqual(this.intersectedTypes, ((IntersectionTypeConstructor) obj).intersectedTypes);
        }
        return false;
    }

    public final SimpleType createType() {
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(TypeAttributes.Companion.getEmpty(), this, CollectionsKt.emptyList(), false, createScopeForKotlinType(), new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor$$Lambda$2
            private final IntersectionTypeConstructor arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return IntersectionTypeConstructor.createType$lambda$4(this.arg$0, (KotlinTypeRefiner) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SimpleType createType$lambda$4(IntersectionTypeConstructor intersectionTypeConstructor, KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return intersectionTypeConstructor.refine(kotlinTypeRefiner).createType();
    }

    public int hashCode() {
        return this.hashCode;
    }

    public final IntersectionTypeConstructor setAlternative(KotlinType kotlinType) {
        return new IntersectionTypeConstructor(this.intersectedTypes, kotlinType);
    }

    public final KotlinType getAlternativeType() {
        return this.alternative;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public IntersectionTypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        Collection collectionMo2782getSupertypes = mo2782getSupertypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionMo2782getSupertypes, 10));
        Iterator it = collectionMo2782getSupertypes.iterator();
        boolean z = false;
        while (it.hasNext()) {
            arrayList.add(((KotlinType) it.next()).refine(kotlinTypeRefiner));
            z = true;
        }
        IntersectionTypeConstructor alternative = null;
        if (z) {
            KotlinType alternativeType = getAlternativeType();
            alternative = new IntersectionTypeConstructor(arrayList).setAlternative(alternativeType != null ? alternativeType.refine(kotlinTypeRefiner) : null);
        }
        return alternative == null ? this : alternative;
    }
}
