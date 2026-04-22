package kotlin;

/* JADX INFO: compiled from: KotlinVersion.kt */
/* JADX INFO: loaded from: classes.dex */
final class KotlinVersionCurrentValue {
    public static final KotlinVersionCurrentValue INSTANCE = new KotlinVersionCurrentValue();

    private KotlinVersionCurrentValue() {
    }

    public static final KotlinVersion get() {
        return new KotlinVersion(2, 0, 21);
    }
}
