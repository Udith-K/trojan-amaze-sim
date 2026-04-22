package kotlinx.serialization.internal;

import ch.qos.logback.core.CoreConstants;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.SerializationException;

/* JADX INFO: compiled from: AbstractPolymorphicSerializer.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractPolymorphicSerializerKt {
    public static final Void throwSubtypeNotRegistered(String str, KClass baseClass) {
        String str2;
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        String str3 = "in the scope of '" + baseClass.getSimpleName() + CoreConstants.SINGLE_QUOTE_CHAR;
        if (str == null) {
            str2 = "Class discriminator was missing and no default polymorphic serializers were registered " + str3;
        } else {
            str2 = "Class '" + str + "' is not registered for polymorphic serialization " + str3 + ".\nMark the base class as 'sealed' or register the serializer explicitly.";
        }
        throw new SerializationException(str2);
    }

    public static final Void throwSubtypeNotRegistered(KClass subClass, KClass baseClass) {
        Intrinsics.checkNotNullParameter(subClass, "subClass");
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        String simpleName = subClass.getSimpleName();
        if (simpleName == null) {
            simpleName = String.valueOf(subClass);
        }
        throwSubtypeNotRegistered(simpleName, baseClass);
        throw new KotlinNothingValueException();
    }
}
