package androidx.activity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import androidx.activity.SystemBarStyle;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: compiled from: EdgeToEdge.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class EdgeToEdge {
    private static EdgeToEdgeImpl Impl;
    private static final int DefaultLightScrim = Color.argb(230, GF2Field.MASK, GF2Field.MASK, GF2Field.MASK);
    private static final int DefaultDarkScrim = Color.argb(128, 27, 27, 27);

    public static final void enable(ComponentActivity componentActivity) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        enable$default(componentActivity, null, null, 3, null);
    }

    public static /* synthetic */ void enable$default(ComponentActivity componentActivity, SystemBarStyle systemBarStyle, SystemBarStyle systemBarStyle2, int i, Object obj) {
        if ((i & 1) != 0) {
            systemBarStyle = SystemBarStyle.Companion.auto$default(SystemBarStyle.Companion, 0, 0, null, 4, null);
        }
        if ((i & 2) != 0) {
            systemBarStyle2 = SystemBarStyle.Companion.auto$default(SystemBarStyle.Companion, DefaultLightScrim, DefaultDarkScrim, null, 4, null);
        }
        enable(componentActivity, systemBarStyle, systemBarStyle2);
    }

    public static final void enable(ComponentActivity componentActivity, SystemBarStyle statusBarStyle, SystemBarStyle navigationBarStyle) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        Intrinsics.checkNotNullParameter(statusBarStyle, "statusBarStyle");
        Intrinsics.checkNotNullParameter(navigationBarStyle, "navigationBarStyle");
        View decorView = componentActivity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "window.decorView");
        Function1 detectDarkMode$activity_release = statusBarStyle.getDetectDarkMode$activity_release();
        Resources resources = decorView.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "view.resources");
        boolean zBooleanValue = ((Boolean) detectDarkMode$activity_release.invoke(resources)).booleanValue();
        Function1 detectDarkMode$activity_release2 = navigationBarStyle.getDetectDarkMode$activity_release();
        Resources resources2 = decorView.getResources();
        Intrinsics.checkNotNullExpressionValue(resources2, "view.resources");
        boolean zBooleanValue2 = ((Boolean) detectDarkMode$activity_release2.invoke(resources2)).booleanValue();
        EdgeToEdgeImpl edgeToEdgeApi23 = Impl;
        if (edgeToEdgeApi23 == null) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                edgeToEdgeApi23 = new EdgeToEdgeApi30();
            } else if (i >= 29) {
                edgeToEdgeApi23 = new EdgeToEdgeApi29();
            } else if (i >= 28) {
                edgeToEdgeApi23 = new EdgeToEdgeApi28();
            } else if (i >= 26) {
                edgeToEdgeApi23 = new EdgeToEdgeApi26();
            } else {
                edgeToEdgeApi23 = new EdgeToEdgeApi23();
            }
        }
        Window window = componentActivity.getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "window");
        edgeToEdgeApi23.setUp(statusBarStyle, navigationBarStyle, window, decorView, zBooleanValue, zBooleanValue2);
        Window window2 = componentActivity.getWindow();
        Intrinsics.checkNotNullExpressionValue(window2, "window");
        edgeToEdgeApi23.adjustLayoutInDisplayCutoutMode(window2);
    }
}
