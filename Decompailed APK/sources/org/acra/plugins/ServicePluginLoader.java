package org.acra.plugins;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;

/* JADX INFO: compiled from: ServicePluginLoader.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ServicePluginLoader implements PluginLoader {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean loadEnabled$lambda$1(CoreConfiguration coreConfiguration, Plugin it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.enabled(coreConfiguration);
    }

    @Override // org.acra.plugins.PluginLoader
    public List loadEnabled(final CoreConfiguration config, Class clazz) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return loadInternal(clazz, new Function1() { // from class: org.acra.plugins.ServicePluginLoader$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ServicePluginLoader.loadEnabled$lambda$1(config, (Plugin) obj));
            }
        });
    }

    private final List loadInternal(Class cls, Function1 function1) {
        ArrayList arrayList = new ArrayList();
        ServiceLoader serviceLoaderLoad = ServiceLoader.load(cls, ServicePluginLoader.class.getClassLoader());
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "ServicePluginLoader loading services from ServiceLoader : " + serviceLoaderLoad);
        }
        Iterator it = serviceLoaderLoad.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            try {
                try {
                    Plugin plugin = (Plugin) it.next();
                    if (((Boolean) function1.invoke(plugin)).booleanValue()) {
                        if (ACRA.DEV_LOGGING) {
                            ACRA.log.d(ACRA.LOG_TAG, "Loaded " + cls.getSimpleName() + " of type " + plugin.getClass().getName());
                        }
                        arrayList.add(plugin);
                    } else if (ACRA.DEV_LOGGING) {
                        ACRA.log.d(ACRA.LOG_TAG, "Ignoring disabled " + cls.getSimpleName() + " of type " + plugin.getClass().getSimpleName());
                    }
                } catch (ServiceConfigurationError e) {
                    ACRA.log.e(ACRA.LOG_TAG, "Unable to load " + cls.getSimpleName(), e);
                }
            } catch (ServiceConfigurationError e2) {
                ACRA.log.e(ACRA.LOG_TAG, "Broken ServiceLoader for " + cls.getSimpleName(), e2);
            }
        }
        return arrayList;
    }
}
