package kotlin.reflect.jvm.internal.impl.types;

import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

/* JADX INFO: compiled from: ErasureTypeAttributes.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ErasureTypeAttributes {
    private final SimpleType defaultType;
    private final TypeUsage howThisTypeIsUsed;
    private final Set visitedTypeParameters;

    public abstract SimpleType getDefaultType();

    public abstract TypeUsage getHowThisTypeIsUsed();

    public abstract Set getVisitedTypeParameters();

    public abstract int hashCode();

    public abstract ErasureTypeAttributes withNewVisitedTypeParameter(TypeParameterDescriptor typeParameterDescriptor);

    public ErasureTypeAttributes(TypeUsage howThisTypeIsUsed, Set set, SimpleType simpleType) {
        Intrinsics.checkNotNullParameter(howThisTypeIsUsed, "howThisTypeIsUsed");
        this.howThisTypeIsUsed = howThisTypeIsUsed;
        this.visitedTypeParameters = set;
        this.defaultType = simpleType;
    }
}
