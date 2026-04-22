package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Caller.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface Caller {
    Object call(Object[] objArr);

    /* JADX INFO: renamed from: getMember */
    Member mo2745getMember();

    List getParameterTypes();

    Type getReturnType();

    /* JADX INFO: compiled from: Caller.kt */
    public static final class DefaultImpls {
        public static void checkArguments(Caller caller, Object[] args) {
            Intrinsics.checkNotNullParameter(args, "args");
            if (CallerKt.getArity(caller) == args.length) {
                return;
            }
            throw new IllegalArgumentException("Callable expects " + CallerKt.getArity(caller) + " arguments, but " + args.length + " were provided.");
        }
    }
}
