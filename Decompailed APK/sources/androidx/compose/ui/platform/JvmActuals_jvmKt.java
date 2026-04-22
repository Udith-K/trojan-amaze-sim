package androidx.compose.ui.platform;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: JvmActuals.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class JvmActuals_jvmKt {
    public static final String simpleIdentityToString(Object obj, String str) {
        if (str == null) {
            if (obj.getClass().isAnonymousClass()) {
                str = obj.getClass().getName();
            } else {
                str = obj.getClass().getSimpleName();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append('@');
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = String.format("%07x", Arrays.copyOf(new Object[]{Integer.valueOf(System.identityHashCode(obj))}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        sb.append(str2);
        return sb.toString();
    }
}
