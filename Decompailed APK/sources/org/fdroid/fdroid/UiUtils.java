package org.fdroid.fdroid;

import android.view.View;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.UiUtils;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lorg/fdroid/fdroid/UiUtils;", "", "<init>", "()V", "Companion", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class UiUtils {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static final void setupEdgeToEdge(View view) {
        INSTANCE.setupEdgeToEdge(view);
    }

    public static final void setupEdgeToEdge(View view, boolean z) {
        INSTANCE.setupEdgeToEdge(view, z);
    }

    public static final void setupEdgeToEdge(View view, boolean z, boolean z2) {
        INSTANCE.setupEdgeToEdge(view, z, z2);
    }

    /* JADX INFO: compiled from: Utils.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH\u0007¨\u0006\u000b"}, d2 = {"Lorg/fdroid/fdroid/UiUtils$Companion;", "", "<init>", "()V", "setupEdgeToEdge", "", "view", "Landroid/view/View;", "top", "", "bottom", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void setupEdgeToEdge(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            setupEdgeToEdge$default(this, view, false, false, 6, null);
        }

        public final void setupEdgeToEdge(View view, boolean z) {
            Intrinsics.checkNotNullParameter(view, "view");
            setupEdgeToEdge$default(this, view, z, false, 4, null);
        }

        private Companion() {
        }

        public static /* synthetic */ void setupEdgeToEdge$default(Companion companion, View view, boolean z, boolean z2, int i, Object obj) {
            if ((i & 2) != 0) {
                z = true;
            }
            if ((i & 4) != 0) {
                z2 = true;
            }
            companion.setupEdgeToEdge(view, z, z2);
        }

        public final void setupEdgeToEdge(View view, final boolean top, final boolean bottom) {
            Intrinsics.checkNotNullParameter(view, "view");
            ViewCompat.setOnApplyWindowInsetsListener(view, new OnApplyWindowInsetsListener() { // from class: org.fdroid.fdroid.UiUtils$Companion$$ExternalSyntheticLambda0
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public final WindowInsetsCompat onApplyWindowInsets(View view2, WindowInsetsCompat windowInsetsCompat) {
                    return UiUtils.Companion.setupEdgeToEdge$lambda$0(top, bottom, view2, windowInsetsCompat);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WindowInsetsCompat setupEdgeToEdge$lambda$0(boolean z, boolean z2, View v, WindowInsetsCompat insets) {
            Intrinsics.checkNotNullParameter(v, "v");
            Intrinsics.checkNotNullParameter(insets, "insets");
            Insets insets2 = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
            Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
            v.setPadding(insets2.left, v.getPaddingTop(), insets2.right, v.getPaddingBottom());
            if (z) {
                v.setPadding(v.getPaddingLeft(), insets2.top, v.getPaddingRight(), v.getPaddingBottom());
            }
            if (z2) {
                v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), insets2.bottom);
            }
            return WindowInsetsCompat.CONSUMED;
        }
    }
}
