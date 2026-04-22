package androidx.compose.ui.text.input;

/* JADX INFO: compiled from: OffsetMapping.kt */
/* JADX INFO: loaded from: classes.dex */
public interface OffsetMapping {
    public static final Companion Companion = Companion.$$INSTANCE;

    int originalToTransformed(int i);

    int transformedToOriginal(int i);

    /* JADX INFO: compiled from: OffsetMapping.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final OffsetMapping Identity = new OffsetMapping() { // from class: androidx.compose.ui.text.input.OffsetMapping$Companion$Identity$1
            @Override // androidx.compose.ui.text.input.OffsetMapping
            public int originalToTransformed(int i) {
                return i;
            }

            @Override // androidx.compose.ui.text.input.OffsetMapping
            public int transformedToOriginal(int i) {
                return i;
            }
        };

        private Companion() {
        }

        public final OffsetMapping getIdentity() {
            return Identity;
        }
    }
}
