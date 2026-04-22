package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl;

/* JADX INFO: compiled from: InternalUnderlyingValOfInlineClass.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class InternalUnderlyingValOfInlineClass implements Caller {
    private final List parameterTypes;
    private final Type returnType;
    private final Method unboxMethod;

    public /* synthetic */ InternalUnderlyingValOfInlineClass(Method method, List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(method, list);
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    /* JADX INFO: renamed from: getMember */
    public final Method mo2745getMember() {
        return null;
    }

    private InternalUnderlyingValOfInlineClass(Method method, List list) {
        this.unboxMethod = method;
        this.parameterTypes = list;
        Class<?> returnType = method.getReturnType();
        Intrinsics.checkNotNullExpressionValue(returnType, "getReturnType(...)");
        this.returnType = returnType;
    }

    public void checkArguments(Object[] objArr) {
        Caller.DefaultImpls.checkArguments(this, objArr);
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public final List getParameterTypes() {
        return this.parameterTypes;
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public final Type getReturnType() {
        return this.returnType;
    }

    protected final Object callMethod(Object obj, Object[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return this.unboxMethod.invoke(obj, Arrays.copyOf(args, args.length));
    }

    /* JADX INFO: compiled from: InternalUnderlyingValOfInlineClass.kt */
    public static final class Unbound extends InternalUnderlyingValOfInlineClass {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Unbound(Method unboxMethod) {
            super(unboxMethod, CollectionsKt.listOf(unboxMethod.getDeclaringClass()), null);
            Intrinsics.checkNotNullParameter(unboxMethod, "unboxMethod");
        }

        @Override // kotlin.reflect.jvm.internal.calls.Caller
        public Object call(Object[] args) {
            Intrinsics.checkNotNullParameter(args, "args");
            checkArguments(args);
            Object obj = args[0];
            CallerImpl.Companion companion = CallerImpl.Companion;
            return callMethod(obj, args.length <= 1 ? new Object[0] : ArraysKt.copyOfRange(args, 1, args.length));
        }
    }

    /* JADX INFO: compiled from: InternalUnderlyingValOfInlineClass.kt */
    public static final class Bound extends InternalUnderlyingValOfInlineClass implements BoundCaller {
        private final Object boundReceiver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Bound(Method unboxMethod, Object obj) {
            super(unboxMethod, CollectionsKt.emptyList(), null);
            Intrinsics.checkNotNullParameter(unboxMethod, "unboxMethod");
            this.boundReceiver = obj;
        }

        @Override // kotlin.reflect.jvm.internal.calls.Caller
        public Object call(Object[] args) {
            Intrinsics.checkNotNullParameter(args, "args");
            checkArguments(args);
            return callMethod(this.boundReceiver, args);
        }
    }
}
