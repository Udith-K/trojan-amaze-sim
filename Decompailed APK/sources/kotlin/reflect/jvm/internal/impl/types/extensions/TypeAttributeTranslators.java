package kotlin.reflect.jvm.internal.impl.types.extensions;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TypeAttributeTranslators.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class TypeAttributeTranslators {
    private final List translators;

    public TypeAttributeTranslators(List translators) {
        Intrinsics.checkNotNullParameter(translators, "translators");
        this.translators = translators;
    }

    public final List getTranslators() {
        return this.translators;
    }
}
