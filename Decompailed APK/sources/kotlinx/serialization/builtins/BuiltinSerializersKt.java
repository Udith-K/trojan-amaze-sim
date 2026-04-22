package kotlinx.serialization.builtins;

import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.Unit;
import kotlin.jvm.internal.BooleanCompanionObject;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.ShortCompanionObject;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KClass;
import kotlin.time.Duration;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanArraySerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.ByteSerializer;
import kotlinx.serialization.internal.CharArraySerializer;
import kotlinx.serialization.internal.CharSerializer;
import kotlinx.serialization.internal.DoubleArraySerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.DurationSerializer;
import kotlinx.serialization.internal.FloatArraySerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.IntArraySerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LongArraySerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.MapEntrySerializer;
import kotlinx.serialization.internal.NullableSerializer;
import kotlinx.serialization.internal.PairSerializer;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.ShortArraySerializer;
import kotlinx.serialization.internal.ShortSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.internal.TripleSerializer;
import kotlinx.serialization.internal.UByteArraySerializer;
import kotlinx.serialization.internal.UByteSerializer;
import kotlinx.serialization.internal.UIntArraySerializer;
import kotlinx.serialization.internal.UIntSerializer;
import kotlinx.serialization.internal.ULongArraySerializer;
import kotlinx.serialization.internal.ULongSerializer;
import kotlinx.serialization.internal.UShortArraySerializer;
import kotlinx.serialization.internal.UShortSerializer;
import kotlinx.serialization.internal.UnitSerializer;

/* JADX INFO: compiled from: BuiltinSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class BuiltinSerializersKt {
    public static final KSerializer getNullable(KSerializer kSerializer) {
        Intrinsics.checkNotNullParameter(kSerializer, "<this>");
        return kSerializer.getDescriptor().isNullable() ? kSerializer : new NullableSerializer(kSerializer);
    }

    public static final KSerializer PairSerializer(KSerializer keySerializer, KSerializer valueSerializer) {
        Intrinsics.checkNotNullParameter(keySerializer, "keySerializer");
        Intrinsics.checkNotNullParameter(valueSerializer, "valueSerializer");
        return new PairSerializer(keySerializer, valueSerializer);
    }

    public static final KSerializer MapEntrySerializer(KSerializer keySerializer, KSerializer valueSerializer) {
        Intrinsics.checkNotNullParameter(keySerializer, "keySerializer");
        Intrinsics.checkNotNullParameter(valueSerializer, "valueSerializer");
        return new MapEntrySerializer(keySerializer, valueSerializer);
    }

    public static final KSerializer TripleSerializer(KSerializer aSerializer, KSerializer bSerializer, KSerializer cSerializer) {
        Intrinsics.checkNotNullParameter(aSerializer, "aSerializer");
        Intrinsics.checkNotNullParameter(bSerializer, "bSerializer");
        Intrinsics.checkNotNullParameter(cSerializer, "cSerializer");
        return new TripleSerializer(aSerializer, bSerializer, cSerializer);
    }

    public static final KSerializer serializer(CharCompanionObject charCompanionObject) {
        Intrinsics.checkNotNullParameter(charCompanionObject, "<this>");
        return CharSerializer.INSTANCE;
    }

    public static final KSerializer CharArraySerializer() {
        return CharArraySerializer.INSTANCE;
    }

    public static final KSerializer serializer(ByteCompanionObject byteCompanionObject) {
        Intrinsics.checkNotNullParameter(byteCompanionObject, "<this>");
        return ByteSerializer.INSTANCE;
    }

    public static final KSerializer ByteArraySerializer() {
        return ByteArraySerializer.INSTANCE;
    }

    public static final KSerializer UByteArraySerializer() {
        return UByteArraySerializer.INSTANCE;
    }

    public static final KSerializer serializer(ShortCompanionObject shortCompanionObject) {
        Intrinsics.checkNotNullParameter(shortCompanionObject, "<this>");
        return ShortSerializer.INSTANCE;
    }

    public static final KSerializer ShortArraySerializer() {
        return ShortArraySerializer.INSTANCE;
    }

    public static final KSerializer UShortArraySerializer() {
        return UShortArraySerializer.INSTANCE;
    }

    public static final KSerializer serializer(IntCompanionObject intCompanionObject) {
        Intrinsics.checkNotNullParameter(intCompanionObject, "<this>");
        return IntSerializer.INSTANCE;
    }

    public static final KSerializer IntArraySerializer() {
        return IntArraySerializer.INSTANCE;
    }

    public static final KSerializer UIntArraySerializer() {
        return UIntArraySerializer.INSTANCE;
    }

    public static final KSerializer serializer(LongCompanionObject longCompanionObject) {
        Intrinsics.checkNotNullParameter(longCompanionObject, "<this>");
        return LongSerializer.INSTANCE;
    }

    public static final KSerializer LongArraySerializer() {
        return LongArraySerializer.INSTANCE;
    }

    public static final KSerializer ULongArraySerializer() {
        return ULongArraySerializer.INSTANCE;
    }

    public static final KSerializer serializer(FloatCompanionObject floatCompanionObject) {
        Intrinsics.checkNotNullParameter(floatCompanionObject, "<this>");
        return FloatSerializer.INSTANCE;
    }

    public static final KSerializer FloatArraySerializer() {
        return FloatArraySerializer.INSTANCE;
    }

    public static final KSerializer serializer(DoubleCompanionObject doubleCompanionObject) {
        Intrinsics.checkNotNullParameter(doubleCompanionObject, "<this>");
        return DoubleSerializer.INSTANCE;
    }

    public static final KSerializer DoubleArraySerializer() {
        return DoubleArraySerializer.INSTANCE;
    }

    public static final KSerializer serializer(BooleanCompanionObject booleanCompanionObject) {
        Intrinsics.checkNotNullParameter(booleanCompanionObject, "<this>");
        return BooleanSerializer.INSTANCE;
    }

    public static final KSerializer BooleanArraySerializer() {
        return BooleanArraySerializer.INSTANCE;
    }

    public static final KSerializer serializer(Unit unit) {
        Intrinsics.checkNotNullParameter(unit, "<this>");
        return UnitSerializer.INSTANCE;
    }

    public static final KSerializer serializer(StringCompanionObject stringCompanionObject) {
        Intrinsics.checkNotNullParameter(stringCompanionObject, "<this>");
        return StringSerializer.INSTANCE;
    }

    public static final KSerializer ArraySerializer(KClass kClass, KSerializer elementSerializer) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(elementSerializer, "elementSerializer");
        return new ReferenceArraySerializer(kClass, elementSerializer);
    }

    public static final KSerializer ListSerializer(KSerializer elementSerializer) {
        Intrinsics.checkNotNullParameter(elementSerializer, "elementSerializer");
        return new ArrayListSerializer(elementSerializer);
    }

    public static final KSerializer MapSerializer(KSerializer keySerializer, KSerializer valueSerializer) {
        Intrinsics.checkNotNullParameter(keySerializer, "keySerializer");
        Intrinsics.checkNotNullParameter(valueSerializer, "valueSerializer");
        return new LinkedHashMapSerializer(keySerializer, valueSerializer);
    }

    public static final KSerializer serializer(UInt.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return UIntSerializer.INSTANCE;
    }

    public static final KSerializer serializer(ULong.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ULongSerializer.INSTANCE;
    }

    public static final KSerializer serializer(UByte.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return UByteSerializer.INSTANCE;
    }

    public static final KSerializer serializer(UShort.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return UShortSerializer.INSTANCE;
    }

    public static final KSerializer serializer(Duration.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return DurationSerializer.INSTANCE;
    }
}
