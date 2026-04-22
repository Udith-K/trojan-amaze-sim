package androidx.lifecycle.viewmodel.internal;

import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactory;
import androidx.lifecycle.viewmodel.ViewModelInitializer;
import java.util.Arrays;
import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ViewModelProviders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ViewModelProviders {
    public static final ViewModelProviders INSTANCE = new ViewModelProviders();

    private ViewModelProviders() {
    }

    public final String getDefaultKey$lifecycle_viewmodel_release(KClass modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        String canonicalName = ViewModelProviders_jvmKt.getCanonicalName(modelClass);
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return "androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName;
    }

    /* JADX INFO: compiled from: ViewModelProviders.kt */
    public static final class ViewModelKey implements CreationExtras.Key {
        public static final ViewModelKey INSTANCE = new ViewModelKey();

        private ViewModelKey() {
        }
    }

    public final ViewModel unsupportedCreateViewModel$lifecycle_viewmodel_release() {
        throw new UnsupportedOperationException("`Factory.create(String, CreationExtras)` is not implemented. You may need to override the method and provide a custom implementation. Note that using `Factory.create(String)` is not supported and considered an error.");
    }

    public final ViewModelProvider.Factory createInitializerFactory$lifecycle_viewmodel_release(Collection initializers) {
        Intrinsics.checkNotNullParameter(initializers, "initializers");
        ViewModelInitializer[] viewModelInitializerArr = (ViewModelInitializer[]) initializers.toArray(new ViewModelInitializer[0]);
        return new InitializerViewModelFactory((ViewModelInitializer[]) Arrays.copyOf(viewModelInitializerArr, viewModelInitializerArr.length));
    }

    public final ViewModelProvider.Factory getDefaultFactory$lifecycle_viewmodel_release(ViewModelStoreOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        if (owner instanceof HasDefaultViewModelProviderFactory) {
            return ((HasDefaultViewModelProviderFactory) owner).getDefaultViewModelProviderFactory();
        }
        return DefaultViewModelProviderFactory.INSTANCE;
    }

    public final CreationExtras getDefaultCreationExtras$lifecycle_viewmodel_release(ViewModelStoreOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        if (owner instanceof HasDefaultViewModelProviderFactory) {
            return ((HasDefaultViewModelProviderFactory) owner).getDefaultViewModelCreationExtras();
        }
        return CreationExtras.Empty.INSTANCE;
    }

    public final ViewModel createViewModelFromInitializers$lifecycle_viewmodel_release(KClass modelClass, CreationExtras extras, ViewModelInitializer... initializers) {
        ViewModel viewModel;
        ViewModelInitializer viewModelInitializer;
        Function1 initializer$lifecycle_viewmodel_release;
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        Intrinsics.checkNotNullParameter(extras, "extras");
        Intrinsics.checkNotNullParameter(initializers, "initializers");
        int length = initializers.length;
        int i = 0;
        while (true) {
            viewModel = null;
            if (i >= length) {
                viewModelInitializer = null;
                break;
            }
            viewModelInitializer = initializers[i];
            if (Intrinsics.areEqual(viewModelInitializer.getClazz$lifecycle_viewmodel_release(), modelClass)) {
                break;
            }
            i++;
        }
        if (viewModelInitializer != null && (initializer$lifecycle_viewmodel_release = viewModelInitializer.getInitializer$lifecycle_viewmodel_release()) != null) {
            viewModel = (ViewModel) initializer$lifecycle_viewmodel_release.invoke(extras);
        }
        if (viewModel != null) {
            return viewModel;
        }
        throw new IllegalArgumentException(("No initializer set for given class " + ViewModelProviders_jvmKt.getCanonicalName(modelClass)).toString());
    }
}
