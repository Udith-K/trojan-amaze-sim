package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ThrowingCaller.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ThrowingCaller implements Caller {
    public static final ThrowingCaller INSTANCE = new ThrowingCaller();

    public Void getMember() {
        return null;
    }

    private ThrowingCaller() {
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    /* JADX INFO: renamed from: getMember */
    public /* bridge */ /* synthetic */ Member mo2745getMember() {
        return (Member) getMember();
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public List getParameterTypes() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public Type getReturnType() {
        Class TYPE = Void.TYPE;
        Intrinsics.checkNotNullExpressionValue(TYPE, "TYPE");
        return TYPE;
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public Object call(Object[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        throw new UnsupportedOperationException("call/callBy are not supported for this declaration.");
    }
}
