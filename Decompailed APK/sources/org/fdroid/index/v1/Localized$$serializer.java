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
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.i18n.ErrorBundle;

/* JADX INFO: compiled from: AppV1.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"org/fdroid/index/v1/Localized.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lorg/fdroid/index/v1/Localized;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", Action.VALUE_ATTRIBUTE, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated
public /* synthetic */ class Localized$$serializer implements GeneratedSerializer {
    public static final Localized$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    private Localized$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Localized$$serializer localized$$serializer = new Localized$$serializer();
        INSTANCE = localized$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("org.fdroid.index.v1.Localized", localized$$serializer, 14);
        pluginGeneratedSerialDescriptor.addElement("description", true);
        pluginGeneratedSerialDescriptor.addElement("name", true);
        pluginGeneratedSerialDescriptor.addElement("icon", true);
        pluginGeneratedSerialDescriptor.addElement("whatsNew", true);
        pluginGeneratedSerialDescriptor.addElement("video", true);
        pluginGeneratedSerialDescriptor.addElement("phoneScreenshots", true);
        pluginGeneratedSerialDescriptor.addElement("sevenInchScreenshots", true);
        pluginGeneratedSerialDescriptor.addElement("tenInchScreenshots", true);
        pluginGeneratedSerialDescriptor.addElement("wearScreenshots", true);
        pluginGeneratedSerialDescriptor.addElement("tvScreenshots", true);
        pluginGeneratedSerialDescriptor.addElement("featureGraphic", true);
        pluginGeneratedSerialDescriptor.addElement("promoGraphic", true);
        pluginGeneratedSerialDescriptor.addElement("tvBanner", true);
        pluginGeneratedSerialDescriptor.addElement(ErrorBundle.SUMMARY_ENTRY, true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer[] childSerializers() {
        KSerializer[] kSerializerArr = Localized.$childSerializers;
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(kSerializerArr[5]), BuiltinSerializersKt.getNullable(kSerializerArr[6]), BuiltinSerializersKt.getNullable(kSerializerArr[7]), BuiltinSerializersKt.getNullable(kSerializerArr[8]), BuiltinSerializersKt.getNullable(kSerializerArr[9]), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final Localized deserialize(Decoder decoder) {
        String str;
        int i;
        String str2;
        String str3;
        List list;
        List list2;
        String str4;
        String str5;
        List list3;
        List list4;
        List list5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        KSerializer[] kSerializerArr;
        String str11;
        String str12;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        KSerializer[] kSerializerArr2 = Localized.$childSerializers;
        String str13 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            String str14 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, stringSerializer, null);
            String str15 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, stringSerializer, null);
            String str16 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, stringSerializer, null);
            String str17 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, stringSerializer, null);
            String str18 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, stringSerializer, null);
            List list6 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, kSerializerArr2[5], null);
            List list7 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, kSerializerArr2[6], null);
            List list8 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, kSerializerArr2[7], null);
            List list9 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, kSerializerArr2[8], null);
            List list10 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, kSerializerArr2[9], null);
            String str19 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, stringSerializer, null);
            String str20 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, stringSerializer, null);
            String str21 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, stringSerializer, null);
            str3 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, stringSerializer, null);
            i = 16383;
            str7 = str15;
            str = str14;
            list = list6;
            str9 = str17;
            str4 = str18;
            str8 = str16;
            str6 = str19;
            list5 = list8;
            list2 = list7;
            list4 = list9;
            str5 = str20;
            str2 = str21;
            list3 = list10;
        } else {
            boolean z = true;
            int i2 = 0;
            String str22 = null;
            String str23 = null;
            String str24 = null;
            List list11 = null;
            List list12 = null;
            String str25 = null;
            String str26 = null;
            List list13 = null;
            List list14 = null;
            List list15 = null;
            String str27 = null;
            String str28 = null;
            String str29 = null;
            while (z) {
                String str30 = str23;
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        kSerializerArr = kSerializerArr2;
                        z = false;
                        str23 = str30;
                        kSerializerArr2 = kSerializerArr;
                        break;
                    case 0:
                        kSerializerArr = kSerializerArr2;
                        str11 = str22;
                        str12 = str30;
                        str28 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, str28);
                        i2 |= 1;
                        str29 = str29;
                        str23 = str12;
                        str22 = str11;
                        kSerializerArr2 = kSerializerArr;
                        break;
                    case 1:
                        kSerializerArr = kSerializerArr2;
                        str11 = str22;
                        str12 = str30;
                        str29 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, str29);
                        i2 |= 2;
                        str23 = str12;
                        str22 = str11;
                        kSerializerArr2 = kSerializerArr;
                        break;
                    case 2:
                        kSerializerArr = kSerializerArr2;
                        str11 = str22;
                        i2 |= 4;
                        str23 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, str30);
                        str22 = str11;
                        kSerializerArr2 = kSerializerArr;
                        break;
                    case 3:
                        str22 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, str22);
                        i2 |= 8;
                        kSerializerArr2 = kSerializerArr2;
                        str23 = str30;
                        break;
                    case 4:
                        str10 = str22;
                        str25 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, str25);
                        i2 |= 16;
                        str23 = str30;
                        str22 = str10;
                        break;
                    case 5:
                        str10 = str22;
                        list11 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, kSerializerArr2[5], list11);
                        i2 |= 32;
                        str23 = str30;
                        str22 = str10;
                        break;
                    case 6:
                        str10 = str22;
                        list12 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, kSerializerArr2[6], list12);
                        i2 |= 64;
                        str23 = str30;
                        str22 = str10;
                        break;
                    case 7:
                        str10 = str22;
                        list15 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, kSerializerArr2[7], list15);
                        i2 |= 128;
                        str23 = str30;
                        str22 = str10;
                        break;
                    case 8:
                        str10 = str22;
                        list14 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, kSerializerArr2[8], list14);
                        i2 |= 256;
                        str23 = str30;
                        str22 = str10;
                        break;
                    case 9:
                        str10 = str22;
                        list13 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, kSerializerArr2[9], list13);
                        i2 |= 512;
                        str23 = str30;
                        str22 = str10;
                        break;
                    case 10:
                        str10 = str22;
                        str27 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, StringSerializer.INSTANCE, str27);
                        i2 |= 1024;
                        str23 = str30;
                        str22 = str10;
                        break;
                    case 11:
                        str10 = str22;
                        str26 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, StringSerializer.INSTANCE, str26);
                        i2 |= 2048;
                        str23 = str30;
                        str22 = str10;
                        break;
                    case 12:
                        str10 = str22;
                        str13 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, StringSerializer.INSTANCE, str13);
                        i2 |= PKIFailureInfo.certConfirmed;
                        str23 = str30;
                        str22 = str10;
                        break;
                    case 13:
                        str10 = str22;
                        str24 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, StringSerializer.INSTANCE, str24);
                        i2 |= 8192;
                        str23 = str30;
                        str22 = str10;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            str = str28;
            i = i2;
            str2 = str13;
            str3 = str24;
            list = list11;
            list2 = list12;
            str4 = str25;
            str5 = str26;
            list3 = list13;
            list4 = list14;
            list5 = list15;
            str6 = str27;
            str7 = str29;
            str8 = str23;
            str9 = str22;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new Localized(i, str, str7, str8, str9, str4, list, list2, list5, list4, list3, str6, str5, str2, str3, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, Localized value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        Localized.write$Self$index_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
