package org.fdroid.index.v2;

import ch.qos.logback.core.joran.action.Action;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: PackageV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"org/fdroid/index/v2/ManifestV2.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lorg/fdroid/index/v2/ManifestV2;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", Action.VALUE_ATTRIBUTE, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated
public /* synthetic */ class ManifestV2$$serializer implements GeneratedSerializer {
    public static final ManifestV2$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    private ManifestV2$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        ManifestV2$$serializer manifestV2$$serializer = new ManifestV2$$serializer();
        INSTANCE = manifestV2$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("org.fdroid.index.v2.ManifestV2", manifestV2$$serializer, 12);
        pluginGeneratedSerialDescriptor.addElement("versionName", false);
        pluginGeneratedSerialDescriptor.addElement("versionCode", false);
        pluginGeneratedSerialDescriptor.addElement("usesSdk", true);
        pluginGeneratedSerialDescriptor.addElement("maxSdkVersion", true);
        pluginGeneratedSerialDescriptor.addElement("signer", true);
        pluginGeneratedSerialDescriptor.addElement("usesPermission", true);
        pluginGeneratedSerialDescriptor.addElement("usesPermissionSdk23", true);
        pluginGeneratedSerialDescriptor.addElement("nativecode", true);
        pluginGeneratedSerialDescriptor.addElement("features", true);
        pluginGeneratedSerialDescriptor.addElement("minSdkVersion", true);
        pluginGeneratedSerialDescriptor.addElement("featureNames", true);
        pluginGeneratedSerialDescriptor.addElement("targetSdkVersion", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer[] childSerializers() {
        KSerializer[] kSerializerArr = ManifestV2.$childSerializers;
        KSerializer nullable = BuiltinSerializersKt.getNullable(UsesSdkV2$$serializer.INSTANCE);
        IntSerializer intSerializer = IntSerializer.INSTANCE;
        return new KSerializer[]{StringSerializer.INSTANCE, LongSerializer.INSTANCE, nullable, BuiltinSerializersKt.getNullable(intSerializer), BuiltinSerializersKt.getNullable(SignerV2$$serializer.INSTANCE), kSerializerArr[5], kSerializerArr[6], kSerializerArr[7], kSerializerArr[8], BuiltinSerializersKt.getNullable(intSerializer), kSerializerArr[10], BuiltinSerializersKt.getNullable(intSerializer)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final ManifestV2 deserialize(Decoder decoder) {
        int i;
        UsesSdkV2 usesSdkV2;
        List list;
        List list2;
        List list3;
        Integer num;
        Integer num2;
        List list4;
        Integer num3;
        List list5;
        SignerV2 signerV2;
        long j;
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        KSerializer[] kSerializerArr = ManifestV2.$childSerializers;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
            long jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 1);
            UsesSdkV2 usesSdkV22 = (UsesSdkV2) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, UsesSdkV2$$serializer.INSTANCE, null);
            IntSerializer intSerializer = IntSerializer.INSTANCE;
            Integer num4 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, intSerializer, null);
            SignerV2 signerV22 = (SignerV2) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, SignerV2$$serializer.INSTANCE, null);
            List list6 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 5, kSerializerArr[5], null);
            List list7 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, kSerializerArr[6], null);
            List list8 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 7, kSerializerArr[7], null);
            List list9 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 8, kSerializerArr[8], null);
            Integer num5 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, intSerializer, null);
            list4 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 10, kSerializerArr[10], null);
            num2 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, intSerializer, null);
            i = 4095;
            signerV2 = signerV22;
            num = num4;
            list5 = list6;
            usesSdkV2 = usesSdkV22;
            list = list8;
            list3 = list7;
            list2 = list9;
            num3 = num5;
            j = jDecodeLongElement;
            str = strDecodeStringElement;
        } else {
            String strDecodeStringElement2 = null;
            UsesSdkV2 usesSdkV23 = null;
            List list10 = null;
            List list11 = null;
            List list12 = null;
            Integer num6 = null;
            Integer num7 = null;
            List list13 = null;
            Integer num8 = null;
            boolean z = true;
            long jDecodeLongElement2 = 0;
            List list14 = null;
            int i2 = 0;
            SignerV2 signerV23 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        str2 = strDecodeStringElement2;
                        z = false;
                        strDecodeStringElement2 = str2;
                        break;
                    case 0:
                        i2 |= 1;
                        strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
                        break;
                    case 1:
                        str2 = strDecodeStringElement2;
                        jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 1);
                        i2 |= 2;
                        strDecodeStringElement2 = str2;
                        break;
                    case 2:
                        str2 = strDecodeStringElement2;
                        usesSdkV23 = (UsesSdkV2) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, UsesSdkV2$$serializer.INSTANCE, usesSdkV23);
                        i2 |= 4;
                        strDecodeStringElement2 = str2;
                        break;
                    case 3:
                        str2 = strDecodeStringElement2;
                        num6 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, num6);
                        i2 |= 8;
                        strDecodeStringElement2 = str2;
                        break;
                    case 4:
                        str2 = strDecodeStringElement2;
                        signerV23 = (SignerV2) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, SignerV2$$serializer.INSTANCE, signerV23);
                        i2 |= 16;
                        strDecodeStringElement2 = str2;
                        break;
                    case 5:
                        str2 = strDecodeStringElement2;
                        list14 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 5, kSerializerArr[5], list14);
                        i2 |= 32;
                        strDecodeStringElement2 = str2;
                        break;
                    case 6:
                        str2 = strDecodeStringElement2;
                        list12 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, kSerializerArr[6], list12);
                        i2 |= 64;
                        strDecodeStringElement2 = str2;
                        break;
                    case 7:
                        str2 = strDecodeStringElement2;
                        list10 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 7, kSerializerArr[7], list10);
                        i2 |= 128;
                        strDecodeStringElement2 = str2;
                        break;
                    case 8:
                        list11 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 8, kSerializerArr[8], list11);
                        i2 |= 256;
                        strDecodeStringElement2 = strDecodeStringElement2;
                        break;
                    case 9:
                        str2 = strDecodeStringElement2;
                        num8 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, IntSerializer.INSTANCE, num8);
                        i2 |= 512;
                        strDecodeStringElement2 = str2;
                        break;
                    case 10:
                        str2 = strDecodeStringElement2;
                        list13 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 10, kSerializerArr[10], list13);
                        i2 |= 1024;
                        strDecodeStringElement2 = str2;
                        break;
                    case 11:
                        str2 = strDecodeStringElement2;
                        num7 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, IntSerializer.INSTANCE, num7);
                        i2 |= 2048;
                        strDecodeStringElement2 = str2;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            i = i2;
            usesSdkV2 = usesSdkV23;
            list = list10;
            list2 = list11;
            list3 = list12;
            num = num6;
            num2 = num7;
            list4 = list13;
            num3 = num8;
            list5 = list14;
            signerV2 = signerV23;
            j = jDecodeLongElement2;
            str = strDecodeStringElement2;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new ManifestV2(i, str, j, usesSdkV2, num, signerV2, list5, list3, list, list2, num3, list4, num2, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, ManifestV2 value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        ManifestV2.write$Self$index_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
