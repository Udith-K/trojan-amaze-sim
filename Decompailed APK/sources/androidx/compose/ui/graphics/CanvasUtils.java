package androidx.compose.ui.graphics;

import android.os.Build;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CanvasUtils.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CanvasUtils {
    public static final CanvasUtils INSTANCE = new CanvasUtils();
    private static Method inorderBarrierMethod;
    private static boolean orderMethodsFetched;
    private static Method reorderBarrierMethod;

    private CanvasUtils() {
    }

    public final void enableZ(android.graphics.Canvas canvas, boolean z) {
        Method method;
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            CanvasZHelper.INSTANCE.enableZ(canvas, z);
            return;
        }
        if (!orderMethodsFetched) {
            try {
                if (i == 28) {
                    Method declaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, new Class[0].getClass());
                    reorderBarrierMethod = (Method) declaredMethod.invoke(android.graphics.Canvas.class, "insertReorderBarrier", new Class[0]);
                    inorderBarrierMethod = (Method) declaredMethod.invoke(android.graphics.Canvas.class, "insertInorderBarrier", new Class[0]);
                } else {
                    reorderBarrierMethod = android.graphics.Canvas.class.getDeclaredMethod("insertReorderBarrier", null);
                    inorderBarrierMethod = android.graphics.Canvas.class.getDeclaredMethod("insertInorderBarrier", null);
                }
                Method method2 = reorderBarrierMethod;
                if (method2 != null) {
                    method2.setAccessible(true);
                }
                Method method3 = inorderBarrierMethod;
                if (method3 != null) {
                    method3.setAccessible(true);
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
            orderMethodsFetched = true;
        }
        if (z) {
            try {
                Method method4 = reorderBarrierMethod;
                if (method4 != null) {
                    Intrinsics.checkNotNull(method4);
                    method4.invoke(canvas, null);
                }
            } catch (IllegalAccessException | InvocationTargetException unused2) {
                return;
            }
        }
        if (z || (method = inorderBarrierMethod) == null) {
            return;
        }
        Intrinsics.checkNotNull(method);
        method.invoke(canvas, null);
    }
}
