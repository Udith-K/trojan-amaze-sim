package org.acra.plugins;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.acra.config.ConfigUtils;
import org.acra.config.Configuration;
import org.acra.config.CoreConfiguration;

/* JADX INFO: compiled from: HasConfigPlugin.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0016\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/acra/plugins/HasConfigPlugin;", "Lorg/acra/plugins/Plugin;", "configClass", "Ljava/lang/Class;", "Lorg/acra/config/Configuration;", "<init>", "(Ljava/lang/Class;)V", "enabled", "", "config", "Lorg/acra/config/CoreConfiguration;", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class HasConfigPlugin implements Plugin {
    private final Class<? extends Configuration> configClass;

    public HasConfigPlugin(Class<? extends Configuration> configClass) {
        Intrinsics.checkNotNullParameter(configClass, "configClass");
        this.configClass = configClass;
    }

    @Override // org.acra.plugins.Plugin
    public boolean enabled(CoreConfiguration config) {
        Intrinsics.checkNotNullParameter(config, "config");
        Configuration configurationFindPluginConfiguration = ConfigUtils.findPluginConfiguration(config, this.configClass);
        if (configurationFindPluginConfiguration != null) {
            return configurationFindPluginConfiguration.getEnabled();
        }
        return false;
    }
}
