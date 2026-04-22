package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.internal.ViewModelProviders;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ViewModelProviderImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ViewModelProviderImpl {
    private final CreationExtras extras;
    private final ViewModelProvider.Factory factory;
    private final ViewModelStore store;

    public ViewModelProviderImpl(ViewModelStore store, ViewModelProvider.Factory factory, CreationExtras extras) {
        Intrinsics.checkNotNullParameter(store, "store");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(extras, "extras");
        this.store = store;
        this.factory = factory;
        this.extras = extras;
    }

    public static /* synthetic */ ViewModel getViewModel$lifecycle_viewmodel_release$default(ViewModelProviderImpl viewModelProviderImpl, KClass kClass, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = ViewModelProviders.INSTANCE.getDefaultKey$lifecycle_viewmodel_release(kClass);
        }
        return viewModelProviderImpl.getViewModel$lifecycle_viewmodel_release(kClass, str);
    }

    public final ViewModel getViewModel$lifecycle_viewmodel_release(KClass modelClass, String key) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        Intrinsics.checkNotNullParameter(key, "key");
        ViewModel viewModel = this.store.get(key);
        if (modelClass.isInstance(viewModel)) {
            Object obj = this.factory;
            if (obj instanceof ViewModelProvider.OnRequeryFactory) {
                Intrinsics.checkNotNull(viewModel);
                ((ViewModelProvider.OnRequeryFactory) obj).onRequery(viewModel);
            }
            Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type T of androidx.lifecycle.viewmodel.ViewModelProviderImpl.getViewModel");
            return viewModel;
        }
        MutableCreationExtras mutableCreationExtras = new MutableCreationExtras(this.extras);
        mutableCreationExtras.set(ViewModelProviders.ViewModelKey.INSTANCE, key);
        ViewModel viewModelCreateViewModel = ViewModelProviderImpl_androidKt.createViewModel(this.factory, modelClass, mutableCreationExtras);
        this.store.put(key, viewModelCreateViewModel);
        return viewModelCreateViewModel;
    }
}
