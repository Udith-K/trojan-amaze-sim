package kotlin.reflect.jvm.internal;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;

/* JADX INFO: compiled from: moduleByClassLoader.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ModuleByClassLoaderKt {
    private static final ConcurrentMap moduleByClassLoader = new ConcurrentHashMap();

    public static final RuntimeModuleData getOrCreateModule(Class cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        ClassLoader safeClassLoader = ReflectClassUtilKt.getSafeClassLoader(cls);
        WeakClassLoaderBox weakClassLoaderBox = new WeakClassLoaderBox(safeClassLoader);
        ConcurrentMap concurrentMap = moduleByClassLoader;
        WeakReference weakReference = (WeakReference) concurrentMap.get(weakClassLoaderBox);
        if (weakReference != null) {
            RuntimeModuleData runtimeModuleData = (RuntimeModuleData) weakReference.get();
            if (runtimeModuleData != null) {
                return runtimeModuleData;
            }
            concurrentMap.remove(weakClassLoaderBox, weakReference);
        }
        RuntimeModuleData runtimeModuleDataCreate = RuntimeModuleData.Companion.create(safeClassLoader);
        while (true) {
            try {
                ConcurrentMap concurrentMap2 = moduleByClassLoader;
                WeakReference weakReference2 = (WeakReference) concurrentMap2.putIfAbsent(weakClassLoaderBox, new WeakReference(runtimeModuleDataCreate));
                if (weakReference2 == null) {
                    return runtimeModuleDataCreate;
                }
                RuntimeModuleData runtimeModuleData2 = (RuntimeModuleData) weakReference2.get();
                if (runtimeModuleData2 != null) {
                    return runtimeModuleData2;
                }
                concurrentMap2.remove(weakClassLoaderBox, weakReference2);
            } finally {
                weakClassLoaderBox.setTemporaryStrongRef(null);
            }
        }
    }
}
