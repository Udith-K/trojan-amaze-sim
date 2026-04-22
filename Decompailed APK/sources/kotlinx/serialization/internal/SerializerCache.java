package kotlinx.serialization.internal;

import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: Platform.common.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface SerializerCache {
    KSerializer get(KClass kClass);
}
