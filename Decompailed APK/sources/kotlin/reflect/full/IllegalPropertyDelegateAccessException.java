package kotlin.reflect.full;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: exceptions.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class IllegalPropertyDelegateAccessException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IllegalPropertyDelegateAccessException(IllegalAccessException cause) {
        super("Cannot obtain the delegate of a non-accessible property. Use \"isAccessible = true\" to make the property accessible", cause);
        Intrinsics.checkNotNullParameter(cause, "cause");
    }
}
