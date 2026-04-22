package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ReflectKotlinClassFinder.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ReflectKotlinClassFinderKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String toRuntimeFqName(ClassId classId) {
        String strAsString = classId.getRelativeClassName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
        String strReplace$default = StringsKt.replace$default(strAsString, CoreConstants.DOT, CoreConstants.DOLLAR, false, 4, (Object) null);
        if (classId.getPackageFqName().isRoot()) {
            return strReplace$default;
        }
        return classId.getPackageFqName() + CoreConstants.DOT + strReplace$default;
    }
}
