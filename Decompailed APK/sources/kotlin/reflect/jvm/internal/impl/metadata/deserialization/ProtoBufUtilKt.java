package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;

/* JADX INFO: compiled from: ProtoBufUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ProtoBufUtilKt {
    public static final Object getExtensionOrNull(GeneratedMessageLite.ExtendableMessage extendableMessage, GeneratedMessageLite.GeneratedExtension extension) {
        Intrinsics.checkNotNullParameter(extendableMessage, "<this>");
        Intrinsics.checkNotNullParameter(extension, "extension");
        if (extendableMessage.hasExtension(extension)) {
            return extendableMessage.getExtension(extension);
        }
        return null;
    }

    public static final Object getExtensionOrNull(GeneratedMessageLite.ExtendableMessage extendableMessage, GeneratedMessageLite.GeneratedExtension extension, int i) {
        Intrinsics.checkNotNullParameter(extendableMessage, "<this>");
        Intrinsics.checkNotNullParameter(extension, "extension");
        if (i < extendableMessage.getExtensionCount(extension)) {
            return extendableMessage.getExtension(extension, i);
        }
        return null;
    }
}
