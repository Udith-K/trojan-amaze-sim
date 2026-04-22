package kotlin;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Lazy.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LazyKt__LazyKt extends LazyKt__LazyJVMKt {
    public static Lazy lazyOf(Object obj) {
        return new InitializedLazyImpl(obj);
    }
}
