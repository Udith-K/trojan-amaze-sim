package androidx.compose.ui.autofill;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ContentDataType.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ContentDataType {
    public static final Companion Companion = new Companion(null);
    private static final int Text = m1071constructorimpl(1);
    private static final int List = m1071constructorimpl(3);
    private static final int Date = m1071constructorimpl(4);
    private static final int Toggle = m1071constructorimpl(2);
    private static final int None = m1071constructorimpl(0);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1071constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: compiled from: ContentDataType.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getText-A48pgw8, reason: not valid java name */
        public final int m1072getTextA48pgw8() {
            return ContentDataType.Text;
        }
    }
}
