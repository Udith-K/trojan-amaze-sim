package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.input.VisualTransformation;

/* JADX INFO: compiled from: VisualTransformation.kt */
/* JADX INFO: loaded from: classes.dex */
public interface VisualTransformation {
    public static final Companion Companion = Companion.$$INSTANCE;

    TransformedText filter(AnnotatedString annotatedString);

    /* JADX INFO: compiled from: VisualTransformation.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final VisualTransformation None = new VisualTransformation() { // from class: androidx.compose.ui.text.input.VisualTransformation$Companion$$ExternalSyntheticLambda0
            @Override // androidx.compose.ui.text.input.VisualTransformation
            public final TransformedText filter(AnnotatedString annotatedString) {
                return VisualTransformation.Companion.None$lambda$0(annotatedString);
            }
        };

        private Companion() {
        }

        public final VisualTransformation getNone() {
            return None;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final TransformedText None$lambda$0(AnnotatedString annotatedString) {
            return new TransformedText(annotatedString, OffsetMapping.Companion.getIdentity());
        }
    }
}
