package androidx.activity;

import android.view.Window;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EdgeToEdge.kt */
/* JADX INFO: loaded from: classes.dex */
abstract class EdgeToEdgeBase implements EdgeToEdgeImpl {
    @Override // androidx.activity.EdgeToEdgeImpl
    public void adjustLayoutInDisplayCutoutMode(Window window) {
        Intrinsics.checkNotNullParameter(window, "window");
    }
}
