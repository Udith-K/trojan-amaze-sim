package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.internal.ViewModelProviders;
import androidx.lifecycle.viewmodel.internal.ViewModelProviders_jvmKt;
import ch.qos.logback.core.CoreConstants;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: InitializerViewModelFactory.kt */
/* JADX INFO: loaded from: classes.dex */
public final class InitializerViewModelFactoryBuilder {
    private final Map initializers = new LinkedHashMap();

    public final void addInitializer(KClass clazz, Function1 initializer) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        if (this.initializers.containsKey(clazz)) {
            throw new IllegalArgumentException(("A `initializer` with the same `clazz` has already been added: " + ViewModelProviders_jvmKt.getCanonicalName(clazz) + CoreConstants.DOT).toString());
        }
        this.initializers.put(clazz, new ViewModelInitializer(clazz, initializer));
    }

    public final ViewModelProvider.Factory build() {
        return ViewModelProviders.INSTANCE.createInitializerFactory$lifecycle_viewmodel_release(this.initializers.values());
    }
}
