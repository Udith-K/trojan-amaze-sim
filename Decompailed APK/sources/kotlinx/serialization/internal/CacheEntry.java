package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: Caching.kt */
/* JADX INFO: loaded from: classes2.dex */
final class CacheEntry {
    public final KSerializer serializer;

    public CacheEntry(KSerializer kSerializer) {
        this.serializer = kSerializer;
    }
}
