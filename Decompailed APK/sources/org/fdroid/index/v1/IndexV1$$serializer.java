package org.fdroid.index.v1;

import ch.qos.logback.core.joran.action.Action;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: IndexV1.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"org/fdroid/index/v1/IndexV1.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lorg/fdroid/index/v1/IndexV1;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", Action.VALUE_ATTRIBUTE, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated
public /* synthetic */ class IndexV1$$serializer implements GeneratedSerializer {
    public static final IndexV1$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    private IndexV1$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        IndexV1$$serializer indexV1$$serializer = new IndexV1$$serializer();
        INSTANCE = indexV1$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("org.fdroid.index.v1.IndexV1", indexV1$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement("repo", false);
        pluginGeneratedSerialDescriptor.addElement("requests", true);
        pluginGeneratedSerialDescriptor.addElement("apps", true);
        pluginGeneratedSerialDescriptor.addElement("packages", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer[] childSerializers() {
        KSerializer[] kSerializerArr = IndexV1.$childSerializers;
        return new KSerializer[]{RepoV1$$serializer.INSTANCE, Requests$$serializer.INSTANCE, kSerializerArr[2], kSerializerArr[3]};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final IndexV1 deserialize(Decoder decoder) {
        int i;
        RepoV1 repoV1;
        Requests requests;
        List list;
        Map map;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        KSerializer[] kSerializerArr = IndexV1.$childSerializers;
        RepoV1 repoV12 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            RepoV1 repoV13 = (RepoV1) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, RepoV1$$serializer.INSTANCE, null);
            Requests requests2 = (Requests) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, Requests$$serializer.INSTANCE, null);
            List list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, kSerializerArr[2], null);
            map = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, kSerializerArr[3], null);
            repoV1 = repoV13;
            i = 15;
            list = list2;
            requests = requests2;
        } else {
            boolean z = true;
            int i2 = 0;
            Requests requests3 = null;
            List list3 = null;
            Map map2 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    repoV12 = (RepoV1) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, RepoV1$$serializer.INSTANCE, repoV12);
                    i2 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    requests3 = (Requests) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, Requests$$serializer.INSTANCE, requests3);
                    i2 |= 2;
                } else if (iDecodeElementIndex == 2) {
                    list3 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, kSerializerArr[2], list3);
                    i2 |= 4;
                } else {
                    if (iDecodeElementIndex != 3) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    map2 = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, kSerializerArr[3], map2);
                    i2 |= 8;
                }
            }
            i = i2;
            repoV1 = repoV12;
            requests = requests3;
            list = list3;
            map = map2;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new IndexV1(i, repoV1, requests, list, map, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, IndexV1 value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        IndexV1.write$Self$index_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
