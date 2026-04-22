package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: compiled from: ConstUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ConstUtil {
    public static final ConstUtil INSTANCE = new ConstUtil();

    private ConstUtil() {
    }

    public static final boolean canBeUsedForConstVal(KotlinType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return ConstUtilKt.canBeUsedForConstVal(type);
    }
}
