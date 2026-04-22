package androidx.lifecycle.compose;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.lifecycle.LifecycleOwner;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import kotlin.Deprecated;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocalLifecycleOwner.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LocalLifecycleOwnerKt {
    private static final ProvidableCompositionLocal LocalLifecycleOwner;

    public static final ProvidableCompositionLocal getLocalLifecycleOwner() {
        return LocalLifecycleOwner;
    }

    static {
        Object objM2639constructorimpl;
        ProvidableCompositionLocal providableCompositionLocal;
        try {
            Result.Companion companion = Result.Companion;
            ClassLoader classLoader = LifecycleOwner.class.getClassLoader();
            Intrinsics.checkNotNull(classLoader);
            Method method = classLoader.loadClass("androidx.compose.ui.platform.AndroidCompositionLocals_androidKt").getMethod("getLocalLifecycleOwner", null);
            Annotation[] annotations = method.getAnnotations();
            int length = annotations.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    if (annotations[i] instanceof Deprecated) {
                        break;
                    } else {
                        i++;
                    }
                } else {
                    Object objInvoke = method.invoke(null, null);
                    if (objInvoke instanceof ProvidableCompositionLocal) {
                        providableCompositionLocal = (ProvidableCompositionLocal) objInvoke;
                    }
                }
            }
            providableCompositionLocal = null;
            objM2639constructorimpl = Result.m2639constructorimpl(providableCompositionLocal);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th));
        }
        ProvidableCompositionLocal providableCompositionLocalStaticCompositionLocalOf = (ProvidableCompositionLocal) (Result.m2644isFailureimpl(objM2639constructorimpl) ? null : objM2639constructorimpl);
        if (providableCompositionLocalStaticCompositionLocalOf == null) {
            providableCompositionLocalStaticCompositionLocalOf = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.lifecycle.compose.LocalLifecycleOwnerKt$LocalLifecycleOwner$1$1
                @Override // kotlin.jvm.functions.Function0
                public final LifecycleOwner invoke() {
                    throw new IllegalStateException("CompositionLocal LocalLifecycleOwner not present");
                }
            });
        }
        LocalLifecycleOwner = providableCompositionLocalStaticCompositionLocalOf;
    }
}
