package androidx.compose.ui.graphics.layer;

import android.os.Build;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LayerManager.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LayerManager {
    public static final Companion Companion = new Companion(null);
    private static final boolean isRobolectric;

    /* JADX INFO: compiled from: LayerManager.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isRobolectric() {
            return LayerManager.isRobolectric;
        }
    }

    static {
        String lowerCase = Build.FINGERPRINT.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        isRobolectric = Intrinsics.areEqual(lowerCase, "robolectric");
    }
}
