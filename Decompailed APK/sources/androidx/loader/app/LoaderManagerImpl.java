package androidx.loader.app;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.collection.SparseArrayCompat;
import androidx.core.util.DebugUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: classes.dex */
class LoaderManagerImpl extends LoaderManager {
    private final LifecycleOwner mLifecycleOwner;
    private final LoaderViewModel mLoaderViewModel;

    static class LoaderViewModel extends ViewModel {
        private static final ViewModelProvider.Factory FACTORY = new ViewModelProvider.Factory() { // from class: androidx.loader.app.LoaderManagerImpl.LoaderViewModel.1
            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public /* synthetic */ ViewModel create(Class cls, CreationExtras creationExtras) {
                return ViewModelProvider.Factory.CC.$default$create(this, cls, creationExtras);
            }

            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public /* synthetic */ ViewModel create(KClass kClass, CreationExtras creationExtras) {
                return ViewModelProvider.Factory.CC.$default$create(this, kClass, creationExtras);
            }

            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public ViewModel create(Class cls) {
                return new LoaderViewModel();
            }
        };
        private SparseArrayCompat mLoaders = new SparseArrayCompat();
        private boolean mCreatingLoader = false;

        LoaderViewModel() {
        }

        static LoaderViewModel getInstance(ViewModelStore viewModelStore) {
            return (LoaderViewModel) new ViewModelProvider(viewModelStore, FACTORY).get(LoaderViewModel.class);
        }

        void markForRedelivery() {
            if (this.mLoaders.size() <= 0) {
                return;
            }
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.mLoaders.valueAt(0));
            throw null;
        }

        @Override // androidx.lifecycle.ViewModel
        protected void onCleared() {
            super.onCleared();
            if (this.mLoaders.size() > 0) {
                ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.mLoaders.valueAt(0));
                throw null;
            }
            this.mLoaders.clear();
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.mLoaders.size() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("    ");
                if (this.mLoaders.size() <= 0) {
                    return;
                }
                ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.mLoaders.valueAt(0));
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.mLoaders.keyAt(0));
                printWriter.print(": ");
                throw null;
            }
        }
    }

    LoaderManagerImpl(LifecycleOwner lifecycleOwner, ViewModelStore viewModelStore) {
        this.mLifecycleOwner = lifecycleOwner;
        this.mLoaderViewModel = LoaderViewModel.getInstance(viewModelStore);
    }

    @Override // androidx.loader.app.LoaderManager
    public void markForRedelivery() {
        this.mLoaderViewModel.markForRedelivery();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        DebugUtils.buildShortClassTag(this.mLifecycleOwner, sb);
        sb.append("}}");
        return sb.toString();
    }

    @Override // androidx.loader.app.LoaderManager
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.mLoaderViewModel.dump(str, fileDescriptor, printWriter, strArr);
    }
}
