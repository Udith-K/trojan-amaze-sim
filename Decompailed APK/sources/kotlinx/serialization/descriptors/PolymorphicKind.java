package kotlinx.serialization.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SerialKinds.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class PolymorphicKind extends SerialKind {
    public /* synthetic */ PolymorphicKind(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private PolymorphicKind() {
        super(null);
    }

    /* JADX INFO: compiled from: SerialKinds.kt */
    public static final class SEALED extends PolymorphicKind {
        public static final SEALED INSTANCE = new SEALED();

        private SEALED() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: SerialKinds.kt */
    public static final class OPEN extends PolymorphicKind {
        public static final OPEN INSTANCE = new OPEN();

        private OPEN() {
            super(null);
        }
    }
}
