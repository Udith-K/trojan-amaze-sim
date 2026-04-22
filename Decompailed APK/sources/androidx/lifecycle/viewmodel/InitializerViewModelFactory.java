package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.internal.ViewModelProviders;
import java.util.Arrays;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: InitializerViewModelFactory.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class InitializerViewModelFactory implements ViewModelProvider.Factory {
    private final ViewModelInitializer[] initializers;

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public /* synthetic */ ViewModel create(Class cls) {
        return ViewModelProvider.Factory.CC.$default$create(this, cls);
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public /* synthetic */ ViewModel create(KClass kClass, CreationExtras creationExtras) {
        return ViewModelProvider.Factory.CC.$default$create(this, kClass, creationExtras);
    }

    public InitializerViewModelFactory(ViewModelInitializer... initializers) {
        Intrinsics.checkNotNullParameter(initializers, "initializers");
        this.initializers = initializers;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public ViewModel create(Class modelClass, CreationExtras extras) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        Intrinsics.checkNotNullParameter(extras, "extras");
        ViewModelProviders viewModelProviders = ViewModelProviders.INSTANCE;
        KClass kotlinClass = JvmClassMappingKt.getKotlinClass(modelClass);
        ViewModelInitializer[] viewModelInitializerArr = this.initializers;
        return viewModelProviders.createViewModelFromInitializers$lifecycle_viewmodel_release(kotlinClass, extras, (ViewModelInitializer[]) Arrays.copyOf(viewModelInitializerArr, viewModelInitializerArr.length));
    }
}
