package kotlin.reflect.jvm.internal.impl.name;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: NameUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class NameUtils {
    public static final NameUtils INSTANCE = new NameUtils();
    private static final Regex SANITIZE_AS_JAVA_INVALID_CHARACTERS = new Regex("[^\\p{L}\\p{Digit}]");
    private static final String CONTEXT_RECEIVER_PREFIX = "$context_receiver";

    private NameUtils() {
    }

    public static final String sanitizeAsJavaIdentifier(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return SANITIZE_AS_JAVA_INVALID_CHARACTERS.replace(name, "_");
    }

    public static final Name contextReceiverName(int i) {
        Name nameIdentifier = Name.identifier(CONTEXT_RECEIVER_PREFIX + '_' + i);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(...)");
        return nameIdentifier;
    }
}
