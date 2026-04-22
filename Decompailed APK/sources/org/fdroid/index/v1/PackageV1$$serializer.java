package org.fdroid.index.v1;

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
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: PackageV1.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"org/fdroid/index/v1/PackageV1.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lorg/fdroid/index/v1/PackageV1;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", Action.VALUE_ATTRIBUTE, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated
public /* synthetic */ class PackageV1$$serializer implements GeneratedSerializer {
    public static final PackageV1$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    private PackageV1$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        PackageV1$$serializer packageV1$$serializer = new PackageV1$$serializer();
        INSTANCE = packageV1$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("org.fdroid.index.v1.PackageV1", packageV1$$serializer, 19);
        pluginGeneratedSerialDescriptor.addElement("added", true);
        pluginGeneratedSerialDescriptor.addElement("apkName", false);
        pluginGeneratedSerialDescriptor.addElement("hash", false);
        pluginGeneratedSerialDescriptor.addElement("hashType", false);
        pluginGeneratedSerialDescriptor.addElement("minSdkVersion", true);
        pluginGeneratedSerialDescriptor.addElement("maxSdkVersion", true);
        pluginGeneratedSerialDescriptor.addElement("targetSdkVersion", true);
        pluginGeneratedSerialDescriptor.addElement("packageName", false);
        pluginGeneratedSerialDescriptor.addElement("sig", true);
        pluginGeneratedSerialDescriptor.addElement("signer", true);
        pluginGeneratedSerialDescriptor.addElement("size", false);
        pluginGeneratedSerialDescriptor.addElement("srcname", true);
        pluginGeneratedSerialDescriptor.addElement("uses-permission", true);
        pluginGeneratedSerialDescriptor.addElement("uses-permission-sdk-23", true);
        pluginGeneratedSerialDescriptor.addElement("versionCode", true);
        pluginGeneratedSerialDescriptor.addElement("versionName", false);
        pluginGeneratedSerialDescriptor.addElement("nativecode", true);
        pluginGeneratedSerialDescriptor.addElement("features", true);
        pluginGeneratedSerialDescriptor.addElement("antiFeatures", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer[] childSerializers() {
        KSerializer[] kSerializerArr = PackageV1.$childSerializers;
        LongSerializer longSerializer = LongSerializer.INSTANCE;
        KSerializer nullable = BuiltinSerializersKt.getNullable(longSerializer);
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        IntSerializer intSerializer = IntSerializer.INSTANCE;
        return new KSerializer[]{nullable, stringSerializer, stringSerializer, stringSerializer, BuiltinSerializersKt.getNullable(intSerializer), BuiltinSerializersKt.getNullable(intSerializer), BuiltinSerializersKt.getNullable(intSerializer), stringSerializer, BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), longSerializer, BuiltinSerializersKt.getNullable(stringSerializer), kSerializerArr[12], kSerializerArr[13], BuiltinSerializersKt.getNullable(longSerializer), stringSerializer, BuiltinSerializersKt.getNullable(kSerializerArr[16]), BuiltinSerializersKt.getNullable(kSerializerArr[17]), BuiltinSerializersKt.getNullable(kSerializerArr[18])};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final PackageV1 deserialize(Decoder decoder) {
        Long l;
        int i;
        List list;
        List list2;
        List list3;
        List list4;
        List list5;
        String str;
        String str2;
        Long l2;
        String str3;
        Integer num;
        String str4;
        String str5;
        Integer num2;
        String str6;
        Integer num3;
        String str7;
        String str8;
        long j;
        Integer num4;
        int i2;
        Integer num5;
        KSerializer[] kSerializerArr;
        Integer num6;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        KSerializer[] kSerializerArr2 = PackageV1.$childSerializers;
        List list6 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            LongSerializer longSerializer = LongSerializer.INSTANCE;
            Long l3 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, longSerializer, null);
            String strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
            String strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 2);
            String strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 3);
            IntSerializer intSerializer = IntSerializer.INSTANCE;
            Integer num7 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, intSerializer, null);
            Integer num8 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, intSerializer, null);
            Integer num9 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, intSerializer, null);
            String strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 7);
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            String str9 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, stringSerializer, null);
            String str10 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, stringSerializer, null);
            long jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 10);
            String str11 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, stringSerializer, null);
            List list7 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 12, kSerializerArr2[12], null);
            List list8 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 13, kSerializerArr2[13], null);
            Long l4 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, longSerializer, null);
            String strDecodeStringElement5 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 15);
            List list9 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, kSerializerArr2[16], null);
            List list10 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 17, kSerializerArr2[17], null);
            l2 = l4;
            list = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 18, kSerializerArr2[18], null);
            list2 = list10;
            i = 524287;
            l = l3;
            num = num9;
            str3 = str10;
            str6 = strDecodeStringElement4;
            str7 = strDecodeStringElement5;
            num3 = num8;
            str5 = strDecodeStringElement3;
            str2 = str9;
            num2 = num7;
            str4 = strDecodeStringElement2;
            list3 = list9;
            list4 = list8;
            list5 = list7;
            str = str11;
            str8 = strDecodeStringElement;
            j = jDecodeLongElement;
        } else {
            boolean z = true;
            int i3 = 0;
            Integer num10 = null;
            Integer num11 = null;
            List list11 = null;
            List list12 = null;
            List list13 = null;
            List list14 = null;
            String str12 = null;
            String str13 = null;
            Long l5 = null;
            String str14 = null;
            Integer num12 = null;
            String strDecodeStringElement6 = null;
            String strDecodeStringElement7 = null;
            String strDecodeStringElement8 = null;
            Long l6 = null;
            long jDecodeLongElement2 = 0;
            String strDecodeStringElement9 = null;
            String strDecodeStringElement10 = null;
            while (z) {
                Integer num13 = num11;
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        kSerializerArr2 = kSerializerArr2;
                        num11 = num13;
                        break;
                    case 0:
                        num5 = num10;
                        l6 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, LongSerializer.INSTANCE, l6);
                        i3 |= 1;
                        kSerializerArr2 = kSerializerArr2;
                        num11 = num13;
                        num10 = num5;
                        break;
                    case 1:
                        kSerializerArr = kSerializerArr2;
                        num5 = num10;
                        num6 = num13;
                        strDecodeStringElement8 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
                        i3 |= 2;
                        num11 = num6;
                        kSerializerArr2 = kSerializerArr;
                        num10 = num5;
                        break;
                    case 2:
                        kSerializerArr = kSerializerArr2;
                        num5 = num10;
                        num6 = num13;
                        strDecodeStringElement9 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 2);
                        i3 |= 4;
                        num11 = num6;
                        kSerializerArr2 = kSerializerArr;
                        num10 = num5;
                        break;
                    case 3:
                        kSerializerArr = kSerializerArr2;
                        num5 = num10;
                        num6 = num13;
                        strDecodeStringElement10 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 3);
                        i3 |= 8;
                        num11 = num6;
                        kSerializerArr2 = kSerializerArr;
                        num10 = num5;
                        break;
                    case 4:
                        kSerializerArr = kSerializerArr2;
                        num5 = num10;
                        num6 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, IntSerializer.INSTANCE, num13);
                        i3 |= 16;
                        num11 = num6;
                        kSerializerArr2 = kSerializerArr;
                        num10 = num5;
                        break;
                    case 5:
                        num10 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, IntSerializer.INSTANCE, num10);
                        i3 |= 32;
                        kSerializerArr2 = kSerializerArr2;
                        num11 = num13;
                        break;
                    case 6:
                        num4 = num10;
                        num12 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, IntSerializer.INSTANCE, num12);
                        i3 |= 64;
                        num10 = num4;
                        num11 = num13;
                        break;
                    case 7:
                        num4 = num10;
                        strDecodeStringElement6 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 7);
                        i3 |= 128;
                        num10 = num4;
                        num11 = num13;
                        break;
                    case 8:
                        num4 = num10;
                        str13 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, str13);
                        i3 |= 256;
                        num10 = num4;
                        num11 = num13;
                        break;
                    case 9:
                        num4 = num10;
                        str14 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, StringSerializer.INSTANCE, str14);
                        i3 |= 512;
                        num10 = num4;
                        num11 = num13;
                        break;
                    case 10:
                        num4 = num10;
                        jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 10);
                        i3 |= 1024;
                        num10 = num4;
                        num11 = num13;
                        break;
                    case 11:
                        num4 = num10;
                        str12 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, StringSerializer.INSTANCE, str12);
                        i3 |= 2048;
                        num10 = num4;
                        num11 = num13;
                        break;
                    case 12:
                        num4 = num10;
                        list14 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 12, kSerializerArr2[12], list14);
                        i3 |= PKIFailureInfo.certConfirmed;
                        num10 = num4;
                        num11 = num13;
                        break;
                    case 13:
                        num4 = num10;
                        list13 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 13, kSerializerArr2[13], list13);
                        i3 |= 8192;
                        num10 = num4;
                        num11 = num13;
                        break;
                    case 14:
                        num4 = num10;
                        l5 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, LongSerializer.INSTANCE, l5);
                        i3 |= 16384;
                        num10 = num4;
                        num11 = num13;
                        break;
                    case 15:
                        num4 = num10;
                        strDecodeStringElement7 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 15);
                        i3 |= 32768;
                        num10 = num4;
                        num11 = num13;
                        break;
                    case 16:
                        num4 = num10;
                        list12 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, kSerializerArr2[16], list12);
                        i2 = PKIFailureInfo.notAuthorized;
                        i3 |= i2;
                        num10 = num4;
                        num11 = num13;
                        break;
                    case 17:
                        num4 = num10;
                        list11 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 17, kSerializerArr2[17], list11);
                        i3 |= PKIFailureInfo.unsupportedVersion;
                        num10 = num4;
                        num11 = num13;
                        break;
                    case 18:
                        num4 = num10;
                        list6 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 18, kSerializerArr2[18], list6);
                        i2 = PKIFailureInfo.transactionIdInUse;
                        i3 |= i2;
                        num10 = num4;
                        num11 = num13;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            l = l6;
            i = i3;
            list = list6;
            list2 = list11;
            list3 = list12;
            list4 = list13;
            list5 = list14;
            str = str12;
            str2 = str13;
            l2 = l5;
            str3 = str14;
            num = num12;
            str4 = strDecodeStringElement9;
            str5 = strDecodeStringElement10;
            num2 = num11;
            str6 = strDecodeStringElement6;
            num3 = num10;
            str7 = strDecodeStringElement7;
            str8 = strDecodeStringElement8;
            j = jDecodeLongElement2;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new PackageV1(i, l, str8, str4, str5, num2, num3, num, str6, str2, str3, j, str, list5, list4, l2, str7, list3, list2, list, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, PackageV1 value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        PackageV1.write$Self$index_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
