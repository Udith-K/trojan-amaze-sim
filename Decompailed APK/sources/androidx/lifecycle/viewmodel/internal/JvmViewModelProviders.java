package androidx.lifecycle.viewmodel.internal;

import androidx.lifecycle.ViewModel;
import java.lang.reflect.InvocationTargetException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JvmViewModelProviders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class JvmViewModelProviders {
    public static final JvmViewModelProviders INSTANCE = new JvmViewModelProviders();

    private JvmViewModelProviders() {
    }

    public final ViewModel createViewModel(Class modelClass) throws InvocationTargetException {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        try {
            Object objNewInstance = modelClass.getDeclaredConstructor(null).newInstance(null);
            Intrinsics.checkNotNullExpressionValue(objNewInstance, "{\n            modelClass…).newInstance()\n        }");
            return (ViewModel) objNewInstance;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e3);
        }
    }
}
