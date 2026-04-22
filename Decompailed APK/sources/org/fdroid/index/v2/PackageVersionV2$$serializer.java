package org.fdroid.index.v2;

import ch.qos.logback.core.joran.action.Action;
import java.util.List;
import java.util.Map;
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
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

/* JADX INFO: compiled from: PackageV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"org/fdroid/index/v2/PackageVersionV2.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lorg/fdroid/index/v2/PackageVersionV2;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", Action.VALUE_ATTRIBUTE, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated
public /* synthetic */ class PackageVersionV2$$serializer implements GeneratedSerializer {
    public static final PackageVersionV2$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    private PackageVersionV2$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        PackageVersionV2$$serializer packageVersionV2$$serializer = new PackageVersionV2$$serializer();
        INSTANCE = packageVersionV2$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("org.fdroid.index.v2.PackageVersionV2", packageVersionV2$$serializer, 10);
        pluginGeneratedSerialDescriptor.addElement("added", false);
        pluginGeneratedSerialDescriptor.addElement(Action.FILE_ATTRIBUTE, false);
        pluginGeneratedSerialDescriptor.addElement("src", true);
        pluginGeneratedSerialDescriptor.addElement("manifest", false);
        pluginGeneratedSerialDescriptor.addElement("releaseChannels", true);
        pluginGeneratedSerialDescriptor.addElement("antiFeatures", true);
        pluginGeneratedSerialDescriptor.addElement("whatsNew", true);
        pluginGeneratedSerialDescriptor.addElement("versionCode", true);
        pluginGeneratedSerialDescriptor.addElement("signer", true);
        pluginGeneratedSerialDescriptor.addElement("packageManifest", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer[] childSerializers() {
        KSerializer[] kSerializerArr = PackageVersionV2.$childSerializers;
        KSerializer nullable = BuiltinSerializersKt.getNullable(FileV2$$serializer.INSTANCE);
        KSerializer kSerializer = kSerializerArr[4];
        KSerializer kSerializer2 = kSerializerArr[5];
        KSerializer kSerializer3 = kSerializerArr[6];
        KSerializer nullable2 = BuiltinSerializersKt.getNullable(SignerV2$$serializer.INSTANCE);
        KSerializer kSerializer4 = kSerializerArr[9];
        LongSerializer longSerializer = LongSerializer.INSTANCE;
        return new KSerializer[]{longSerializer, FileV1$$serializer.INSTANCE, nullable, ManifestV2$$serializer.INSTANCE, kSerializer, kSerializer2, kSerializer3, longSerializer, nullable2, kSerializer4};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final PackageVersionV2 deserialize(Decoder decoder) {
        int i;
        Map map;
        List list;
        PackageManifest packageManifest;
        SignerV2 signerV2;
        Map map2;
        ManifestV2 manifestV2;
        FileV2 fileV2;
        FileV1 fileV1;
        long j;
        long j2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        KSerializer[] kSerializerArr = PackageVersionV2.$childSerializers;
        int i2 = 8;
        FileV1 fileV12 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            long jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 0);
            FileV1 fileV13 = (FileV1) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, FileV1$$serializer.INSTANCE, null);
            FileV2 fileV22 = (FileV2) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, FileV2$$serializer.INSTANCE, null);
            ManifestV2 manifestV22 = (ManifestV2) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, ManifestV2$$serializer.INSTANCE, null);
            List list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 4, kSerializerArr[4], null);
            Map map3 = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 5, kSerializerArr[5], null);
            Map map4 = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, kSerializerArr[6], null);
            long jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 7);
            SignerV2 signerV22 = (SignerV2) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, SignerV2$$serializer.INSTANCE, null);
            packageManifest = (PackageManifest) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 9, kSerializerArr[9], null);
            fileV1 = fileV13;
            signerV2 = signerV22;
            manifestV2 = manifestV22;
            i = 1023;
            fileV2 = fileV22;
            map2 = map4;
            map = map3;
            list = list2;
            j = jDecodeLongElement2;
            j2 = jDecodeLongElement;
        } else {
            long jDecodeLongElement3 = 0;
            boolean z = true;
            int i3 = 0;
            Map map5 = null;
            List list3 = null;
            PackageManifest packageManifest2 = null;
            SignerV2 signerV23 = null;
            Map map6 = null;
            ManifestV2 manifestV23 = null;
            FileV2 fileV23 = null;
            long jDecodeLongElement4 = 0;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 8;
                        break;
                    case 0:
                        jDecodeLongElement4 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 0);
                        i3 |= 1;
                        i2 = 8;
                        break;
                    case 1:
                        fileV12 = (FileV1) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, FileV1$$serializer.INSTANCE, fileV12);
                        i3 |= 2;
                        i2 = 8;
                        break;
                    case 2:
                        fileV23 = (FileV2) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, FileV2$$serializer.INSTANCE, fileV23);
                        i3 |= 4;
                        i2 = 8;
                        break;
                    case 3:
                        manifestV23 = (ManifestV2) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, ManifestV2$$serializer.INSTANCE, manifestV23);
                        i3 |= 8;
                        i2 = 8;
                        break;
                    case 4:
                        list3 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 4, kSerializerArr[4], list3);
                        i3 |= 16;
                        i2 = 8;
                        break;
                    case 5:
                        map5 = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 5, kSerializerArr[5], map5);
                        i3 |= 32;
                        i2 = 8;
                        break;
                    case 6:
                        map6 = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, kSerializerArr[6], map6);
                        i3 |= 64;
                        i2 = 8;
                        break;
                    case 7:
                        jDecodeLongElement3 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 7);
                        i3 |= 128;
                        break;
                    case 8:
                        signerV23 = (SignerV2) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, i2, SignerV2$$serializer.INSTANCE, signerV23);
                        i3 |= 256;
                        break;
                    case 9:
                        packageManifest2 = (PackageManifest) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 9, kSerializerArr[9], packageManifest2);
                        i3 |= 512;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            i = i3;
            map = map5;
            list = list3;
            packageManifest = packageManifest2;
            signerV2 = signerV23;
            map2 = map6;
            manifestV2 = manifestV23;
            fileV2 = fileV23;
            fileV1 = fileV12;
            j = jDecodeLongElement3;
            j2 = jDecodeLongElement4;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new PackageVersionV2(i, j2, fileV1, fileV2, manifestV2, list, map, map2, j, signerV2, packageManifest, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, PackageVersionV2 value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        PackageVersionV2.write$Self$index_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
