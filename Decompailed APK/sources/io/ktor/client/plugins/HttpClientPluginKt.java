package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpClientPlugin.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpClientPluginKt {
    private static final AttributeKey PLUGIN_INSTALLED_LIST = new AttributeKey("ApplicationPluginRegistry");

    public static final AttributeKey getPLUGIN_INSTALLED_LIST() {
        return PLUGIN_INSTALLED_LIST;
    }

    public static final Object pluginOrNull(HttpClient httpClient, HttpClientPlugin plugin) {
        Intrinsics.checkNotNullParameter(httpClient, "<this>");
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        Attributes attributes = (Attributes) httpClient.getAttributes().getOrNull(PLUGIN_INSTALLED_LIST);
        if (attributes != null) {
            return attributes.getOrNull(plugin.getKey());
        }
        return null;
    }

    public static final Object plugin(HttpClient httpClient, HttpClientPlugin plugin) {
        Intrinsics.checkNotNullParameter(httpClient, "<this>");
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        Object objPluginOrNull = pluginOrNull(httpClient, plugin);
        if (objPluginOrNull != null) {
            return objPluginOrNull;
        }
        throw new IllegalStateException("Plugin " + plugin + " is not installed. Consider using `install(" + plugin.getKey() + ")` in client config first.");
    }
}
