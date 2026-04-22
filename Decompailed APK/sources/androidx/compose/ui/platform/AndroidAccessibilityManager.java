package androidx.compose.ui.platform;

import android.content.Context;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidAccessibilityManager.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidAccessibilityManager implements AccessibilityManager {
    private final android.view.accessibility.AccessibilityManager accessibilityManager;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: AndroidAccessibilityManager.android.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public AndroidAccessibilityManager(Context context) {
        Object systemService = context.getSystemService("accessibility");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        this.accessibilityManager = (android.view.accessibility.AccessibilityManager) systemService;
    }
}
