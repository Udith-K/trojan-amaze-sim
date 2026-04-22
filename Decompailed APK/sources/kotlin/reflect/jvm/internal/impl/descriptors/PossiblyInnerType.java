package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: typeParameterUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class PossiblyInnerType {
    private final List arguments;
    private final ClassifierDescriptorWithTypeParameters classifierDescriptor;
    private final PossiblyInnerType outerType;

    public PossiblyInnerType(ClassifierDescriptorWithTypeParameters classifierDescriptor, List arguments, PossiblyInnerType possiblyInnerType) {
        Intrinsics.checkNotNullParameter(classifierDescriptor, "classifierDescriptor");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        this.classifierDescriptor = classifierDescriptor;
        this.arguments = arguments;
        this.outerType = possiblyInnerType;
    }

    public final ClassifierDescriptorWithTypeParameters getClassifierDescriptor() {
        return this.classifierDescriptor;
    }

    public final List getArguments() {
        return this.arguments;
    }

    public final PossiblyInnerType getOuterType() {
        return this.outerType;
    }
}
