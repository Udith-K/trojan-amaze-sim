package io.ktor.client.engine;

import io.ktor.client.plugins.HttpTimeout;
import io.ktor.util.AttributeKey;
import java.util.Set;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: HttpClientEngineCapability.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpClientEngineCapabilityKt {
    private static final AttributeKey ENGINE_CAPABILITIES_KEY = new AttributeKey("EngineCapabilities");
    private static final Set DEFAULT_CAPABILITIES = SetsKt.setOf(HttpTimeout.Plugin);

    public static final AttributeKey getENGINE_CAPABILITIES_KEY() {
        return ENGINE_CAPABILITIES_KEY;
    }
}
