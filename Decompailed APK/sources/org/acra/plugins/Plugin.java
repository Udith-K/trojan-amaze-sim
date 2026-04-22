package org.acra.plugins;

import kotlin.jvm.internal.Intrinsics;
import org.acra.config.CoreConfiguration;

/* JADX INFO: compiled from: Plugin.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface Plugin {

    /* JADX INFO: renamed from: org.acra.plugins.Plugin$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Plugin.kt */
    public abstract /* synthetic */ class CC {
        public static boolean $default$enabled(Plugin plugin, CoreConfiguration config) {
            Intrinsics.checkNotNullParameter(config, "config");
            return true;
        }
    }

    boolean enabled(CoreConfiguration coreConfiguration);
}
