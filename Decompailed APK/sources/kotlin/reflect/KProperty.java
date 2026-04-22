package kotlin.reflect;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KProperty extends KCallable {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Accessor {
        KProperty getProperty();
    }

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Getter extends Accessor, KFunction {
    }

    Getter getGetter();
}
