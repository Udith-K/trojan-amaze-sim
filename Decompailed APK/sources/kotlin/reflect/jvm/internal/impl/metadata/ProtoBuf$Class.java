package kotlin.reflect.jvm.internal.impl.metadata;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractParser;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.asn1.eac.EACTags;

/* JADX INFO: loaded from: classes2.dex */
public final class ProtoBuf$Class extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static Parser PARSER = new AbstractParser() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class.1
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
        public ProtoBuf$Class parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return new ProtoBuf$Class(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ProtoBuf$Class defaultInstance;
    private int bitField0_;
    private int companionObjectName_;
    private List constructor_;
    private int contextReceiverTypeIdMemoizedSerializedSize;
    private List contextReceiverTypeId_;
    private List contextReceiverType_;
    private List enumEntry_;
    private int flags_;
    private int fqName_;
    private List function_;
    private int inlineClassUnderlyingPropertyName_;
    private int inlineClassUnderlyingTypeId_;
    private ProtoBuf$Type inlineClassUnderlyingType_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private int multiFieldValueClassUnderlyingNameMemoizedSerializedSize;
    private List multiFieldValueClassUnderlyingName_;
    private int multiFieldValueClassUnderlyingTypeIdMemoizedSerializedSize;
    private List multiFieldValueClassUnderlyingTypeId_;
    private List multiFieldValueClassUnderlyingType_;
    private int nestedClassNameMemoizedSerializedSize;
    private List nestedClassName_;
    private List property_;
    private int sealedSubclassFqNameMemoizedSerializedSize;
    private List sealedSubclassFqName_;
    private int supertypeIdMemoizedSerializedSize;
    private List supertypeId_;
    private List supertype_;
    private List typeAlias_;
    private List typeParameter_;
    private ProtoBuf$TypeTable typeTable_;
    private final ByteString unknownFields;
    private ProtoBuf$VersionRequirementTable versionRequirementTable_;
    private List versionRequirement_;

    private ProtoBuf$Class(GeneratedMessageLite.ExtendableBuilder extendableBuilder) {
        super(extendableBuilder);
        this.supertypeIdMemoizedSerializedSize = -1;
        this.nestedClassNameMemoizedSerializedSize = -1;
        this.contextReceiverTypeIdMemoizedSerializedSize = -1;
        this.sealedSubclassFqNameMemoizedSerializedSize = -1;
        this.multiFieldValueClassUnderlyingNameMemoizedSerializedSize = -1;
        this.multiFieldValueClassUnderlyingTypeIdMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = extendableBuilder.getUnknownFields();
    }

    private ProtoBuf$Class(boolean z) {
        this.supertypeIdMemoizedSerializedSize = -1;
        this.nestedClassNameMemoizedSerializedSize = -1;
        this.contextReceiverTypeIdMemoizedSerializedSize = -1;
        this.sealedSubclassFqNameMemoizedSerializedSize = -1;
        this.multiFieldValueClassUnderlyingNameMemoizedSerializedSize = -1;
        this.multiFieldValueClassUnderlyingTypeIdMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }

    public static ProtoBuf$Class getDefaultInstance() {
        return defaultInstance;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
    public ProtoBuf$Class getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ProtoBuf$Class(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        boolean z;
        this.supertypeIdMemoizedSerializedSize = -1;
        this.nestedClassNameMemoizedSerializedSize = -1;
        this.contextReceiverTypeIdMemoizedSerializedSize = -1;
        this.sealedSubclassFqNameMemoizedSerializedSize = -1;
        this.multiFieldValueClassUnderlyingNameMemoizedSerializedSize = -1;
        this.multiFieldValueClassUnderlyingTypeIdMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        initFields();
        ByteString.Output outputNewOutput = ByteString.newOutput();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
        boolean z2 = false;
        int i = 0;
        while (!z2) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0:
                            z = true;
                            z2 = true;
                            break;
                        case 8:
                            z = true;
                            this.bitField0_ |= 1;
                            this.flags_ = codedInputStream.readInt32();
                            break;
                        case 16:
                            if ((i & 32) != 32) {
                                this.supertypeId_ = new ArrayList();
                                i |= 32;
                            }
                            this.supertypeId_.add(Integer.valueOf(codedInputStream.readInt32()));
                            z = true;
                            break;
                        case 18:
                            int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 32) != 32 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.supertypeId_ = new ArrayList();
                                i |= 32;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.supertypeId_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(iPushLimit);
                            z = true;
                            break;
                        case 24:
                            this.bitField0_ |= 2;
                            this.fqName_ = codedInputStream.readInt32();
                            z = true;
                            break;
                        case 32:
                            this.bitField0_ |= 4;
                            this.companionObjectName_ = codedInputStream.readInt32();
                            z = true;
                            break;
                        case EACTags.CURRENCY_CODE /* 42 */:
                            if ((i & 8) != 8) {
                                this.typeParameter_ = new ArrayList();
                                i |= 8;
                            }
                            this.typeParameter_.add(codedInputStream.readMessage(ProtoBuf$TypeParameter.PARSER, extensionRegistryLite));
                            z = true;
                            break;
                        case 50:
                            if ((i & 16) != 16) {
                                this.supertype_ = new ArrayList();
                                i |= 16;
                            }
                            this.supertype_.add(codedInputStream.readMessage(ProtoBuf$Type.PARSER, extensionRegistryLite));
                            z = true;
                            break;
                        case 56:
                            if ((i & 64) != 64) {
                                this.nestedClassName_ = new ArrayList();
                                i |= 64;
                            }
                            this.nestedClassName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            z = true;
                            break;
                        case EACTags.DYNAMIC_INTERNAL_AUTHENTIFICATION /* 58 */:
                            int iPushLimit2 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 64) != 64 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.nestedClassName_ = new ArrayList();
                                i |= 64;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.nestedClassName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(iPushLimit2);
                            z = true;
                            break;
                        case EACTags.ADDRESS /* 66 */:
                            if ((i & 512) != 512) {
                                this.constructor_ = new ArrayList();
                                i |= 512;
                            }
                            this.constructor_.add(codedInputStream.readMessage(ProtoBuf$Constructor.PARSER, extensionRegistryLite));
                            z = true;
                            break;
                        case EACTags.CERTIFICATION_AUTHORITY_PUBLIC_KEY /* 74 */:
                            if ((i & 1024) != 1024) {
                                this.function_ = new ArrayList();
                                i |= 1024;
                            }
                            this.function_.add(codedInputStream.readMessage(ProtoBuf$Function.PARSER, extensionRegistryLite));
                            z = true;
                            break;
                        case EACTags.HISTORICAL_BYTES /* 82 */:
                            if ((i & 2048) != 2048) {
                                this.property_ = new ArrayList();
                                i |= 2048;
                            }
                            this.property_.add(codedInputStream.readMessage(ProtoBuf$Property.PARSER, extensionRegistryLite));
                            z = true;
                            break;
                        case 90:
                            if ((i & PKIFailureInfo.certConfirmed) != 4096) {
                                this.typeAlias_ = new ArrayList();
                                i |= PKIFailureInfo.certConfirmed;
                            }
                            this.typeAlias_.add(codedInputStream.readMessage(ProtoBuf$TypeAlias.PARSER, extensionRegistryLite));
                            z = true;
                            break;
                        case 106:
                            if ((i & 8192) != 8192) {
                                this.enumEntry_ = new ArrayList();
                                i |= 8192;
                            }
                            this.enumEntry_.add(codedInputStream.readMessage(ProtoBuf$EnumEntry.PARSER, extensionRegistryLite));
                            z = true;
                            break;
                        case 128:
                            if ((i & 16384) != 16384) {
                                this.sealedSubclassFqName_ = new ArrayList();
                                i |= 16384;
                            }
                            this.sealedSubclassFqName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            z = true;
                            break;
                        case 130:
                            int iPushLimit3 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 16384) != 16384 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.sealedSubclassFqName_ = new ArrayList();
                                i |= 16384;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.sealedSubclassFqName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(iPushLimit3);
                            z = true;
                            break;
                        case 136:
                            this.bitField0_ |= 8;
                            this.inlineClassUnderlyingPropertyName_ = codedInputStream.readInt32();
                            z = true;
                            break;
                        case 146:
                            ProtoBuf$Type.Builder builder = (this.bitField0_ & 16) == 16 ? this.inlineClassUnderlyingType_.toBuilder() : null;
                            ProtoBuf$Type protoBuf$Type = (ProtoBuf$Type) codedInputStream.readMessage(ProtoBuf$Type.PARSER, extensionRegistryLite);
                            this.inlineClassUnderlyingType_ = protoBuf$Type;
                            if (builder != 0) {
                                builder.mergeFrom(protoBuf$Type);
                                this.inlineClassUnderlyingType_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 16;
                            z = true;
                            break;
                        case 152:
                            this.bitField0_ |= 32;
                            this.inlineClassUnderlyingTypeId_ = codedInputStream.readInt32();
                            z = true;
                            break;
                        case 162:
                            if ((i & 128) != 128) {
                                this.contextReceiverType_ = new ArrayList();
                                i |= 128;
                            }
                            this.contextReceiverType_.add(codedInputStream.readMessage(ProtoBuf$Type.PARSER, extensionRegistryLite));
                            z = true;
                            break;
                        case 168:
                            if ((i & 256) != 256) {
                                this.contextReceiverTypeId_ = new ArrayList();
                                i |= 256;
                            }
                            this.contextReceiverTypeId_.add(Integer.valueOf(codedInputStream.readInt32()));
                            z = true;
                            break;
                        case 170:
                            int iPushLimit4 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 256) != 256 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.contextReceiverTypeId_ = new ArrayList();
                                i |= 256;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.contextReceiverTypeId_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(iPushLimit4);
                            z = true;
                            break;
                        case 176:
                            if ((i & PKIFailureInfo.transactionIdInUse) != 262144) {
                                this.multiFieldValueClassUnderlyingName_ = new ArrayList();
                                i |= PKIFailureInfo.transactionIdInUse;
                            }
                            this.multiFieldValueClassUnderlyingName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            z = true;
                            break;
                        case 178:
                            int iPushLimit5 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & PKIFailureInfo.transactionIdInUse) != 262144 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.multiFieldValueClassUnderlyingName_ = new ArrayList();
                                i |= PKIFailureInfo.transactionIdInUse;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.multiFieldValueClassUnderlyingName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(iPushLimit5);
                            z = true;
                            break;
                        case 186:
                            if ((i & PKIFailureInfo.signerNotTrusted) != 524288) {
                                this.multiFieldValueClassUnderlyingType_ = new ArrayList();
                                i |= PKIFailureInfo.signerNotTrusted;
                            }
                            this.multiFieldValueClassUnderlyingType_.add(codedInputStream.readMessage(ProtoBuf$Type.PARSER, extensionRegistryLite));
                            z = true;
                            break;
                        case 192:
                            if ((i & PKIFailureInfo.badCertTemplate) != 1048576) {
                                this.multiFieldValueClassUnderlyingTypeId_ = new ArrayList();
                                i |= PKIFailureInfo.badCertTemplate;
                            }
                            this.multiFieldValueClassUnderlyingTypeId_.add(Integer.valueOf(codedInputStream.readInt32()));
                            z = true;
                            break;
                        case 194:
                            int iPushLimit6 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & PKIFailureInfo.badCertTemplate) != 1048576 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.multiFieldValueClassUnderlyingTypeId_ = new ArrayList();
                                i |= PKIFailureInfo.badCertTemplate;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.multiFieldValueClassUnderlyingTypeId_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(iPushLimit6);
                            z = true;
                            break;
                        case 242:
                            ProtoBuf$TypeTable.Builder builder2 = (this.bitField0_ & 64) == 64 ? this.typeTable_.toBuilder() : null;
                            ProtoBuf$TypeTable protoBuf$TypeTable = (ProtoBuf$TypeTable) codedInputStream.readMessage(ProtoBuf$TypeTable.PARSER, extensionRegistryLite);
                            this.typeTable_ = protoBuf$TypeTable;
                            if (builder2 != 0) {
                                builder2.mergeFrom(protoBuf$TypeTable);
                                this.typeTable_ = builder2.buildPartial();
                            }
                            this.bitField0_ |= 64;
                            z = true;
                            break;
                        case 248:
                            if ((i & 4194304) != 4194304) {
                                this.versionRequirement_ = new ArrayList();
                                i |= 4194304;
                            }
                            this.versionRequirement_.add(Integer.valueOf(codedInputStream.readInt32()));
                            z = true;
                            break;
                        case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /* 250 */:
                            int iPushLimit7 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 4194304) != 4194304 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.versionRequirement_ = new ArrayList();
                                i |= 4194304;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.versionRequirement_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(iPushLimit7);
                            z = true;
                            break;
                        case 258:
                            ProtoBuf$VersionRequirementTable.Builder builder3 = (this.bitField0_ & 128) == 128 ? this.versionRequirementTable_.toBuilder() : null;
                            ProtoBuf$VersionRequirementTable protoBuf$VersionRequirementTable = (ProtoBuf$VersionRequirementTable) codedInputStream.readMessage(ProtoBuf$VersionRequirementTable.PARSER, extensionRegistryLite);
                            this.versionRequirementTable_ = protoBuf$VersionRequirementTable;
                            if (builder3 != 0) {
                                builder3.mergeFrom(protoBuf$VersionRequirementTable);
                                this.versionRequirementTable_ = builder3.buildPartial();
                            }
                            this.bitField0_ |= 128;
                            z = true;
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                                z2 = true;
                            }
                            z = true;
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                if ((i & 32) == 32) {
                    this.supertypeId_ = Collections.unmodifiableList(this.supertypeId_);
                }
                if ((i & 8) == 8) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                }
                if ((i & 16) == 16) {
                    this.supertype_ = Collections.unmodifiableList(this.supertype_);
                }
                if ((i & 64) == 64) {
                    this.nestedClassName_ = Collections.unmodifiableList(this.nestedClassName_);
                }
                if ((i & 512) == 512) {
                    this.constructor_ = Collections.unmodifiableList(this.constructor_);
                }
                if ((i & 1024) == 1024) {
                    this.function_ = Collections.unmodifiableList(this.function_);
                }
                if ((i & 2048) == 2048) {
                    this.property_ = Collections.unmodifiableList(this.property_);
                }
                if ((i & PKIFailureInfo.certConfirmed) == 4096) {
                    this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                }
                if ((i & 8192) == 8192) {
                    this.enumEntry_ = Collections.unmodifiableList(this.enumEntry_);
                }
                if ((i & 16384) == 16384) {
                    this.sealedSubclassFqName_ = Collections.unmodifiableList(this.sealedSubclassFqName_);
                }
                if ((i & 128) == 128) {
                    this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
                }
                if ((i & 256) == 256) {
                    this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
                }
                if ((i & PKIFailureInfo.transactionIdInUse) == 262144) {
                    this.multiFieldValueClassUnderlyingName_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingName_);
                }
                if ((i & PKIFailureInfo.signerNotTrusted) == 524288) {
                    this.multiFieldValueClassUnderlyingType_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingType_);
                }
                if ((i & PKIFailureInfo.badCertTemplate) == 1048576) {
                    this.multiFieldValueClassUnderlyingTypeId_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingTypeId_);
                }
                if ((i & 4194304) == 4194304) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                }
                try {
                    codedOutputStreamNewInstance.flush();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.unknownFields = outputNewOutput.toByteString();
                    throw th2;
                }
                this.unknownFields = outputNewOutput.toByteString();
                makeExtensionsImmutable();
                throw th;
            }
        }
        if ((i & 32) == 32) {
            this.supertypeId_ = Collections.unmodifiableList(this.supertypeId_);
        }
        if ((i & 8) == 8) {
            this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
        }
        if ((i & 16) == 16) {
            this.supertype_ = Collections.unmodifiableList(this.supertype_);
        }
        if ((i & 64) == 64) {
            this.nestedClassName_ = Collections.unmodifiableList(this.nestedClassName_);
        }
        if ((i & 512) == 512) {
            this.constructor_ = Collections.unmodifiableList(this.constructor_);
        }
        if ((i & 1024) == 1024) {
            this.function_ = Collections.unmodifiableList(this.function_);
        }
        if ((i & 2048) == 2048) {
            this.property_ = Collections.unmodifiableList(this.property_);
        }
        if ((i & PKIFailureInfo.certConfirmed) == 4096) {
            this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
        }
        if ((i & 8192) == 8192) {
            this.enumEntry_ = Collections.unmodifiableList(this.enumEntry_);
        }
        if ((i & 16384) == 16384) {
            this.sealedSubclassFqName_ = Collections.unmodifiableList(this.sealedSubclassFqName_);
        }
        if ((i & 128) == 128) {
            this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
        }
        if ((i & 256) == 256) {
            this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
        }
        if ((i & PKIFailureInfo.transactionIdInUse) == 262144) {
            this.multiFieldValueClassUnderlyingName_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingName_);
        }
        if ((i & PKIFailureInfo.signerNotTrusted) == 524288) {
            this.multiFieldValueClassUnderlyingType_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingType_);
        }
        if ((i & PKIFailureInfo.badCertTemplate) == 1048576) {
            this.multiFieldValueClassUnderlyingTypeId_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingTypeId_);
        }
        if ((i & 4194304) == 4194304) {
            this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
        }
        try {
            codedOutputStreamNewInstance.flush();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.unknownFields = outputNewOutput.toByteString();
            throw th3;
        }
        this.unknownFields = outputNewOutput.toByteString();
        makeExtensionsImmutable();
    }

    static {
        ProtoBuf$Class protoBuf$Class = new ProtoBuf$Class(true);
        defaultInstance = protoBuf$Class;
        protoBuf$Class.initFields();
    }

    public enum Kind implements Internal.EnumLite {
        CLASS(0, 0),
        INTERFACE(1, 1),
        ENUM_CLASS(2, 2),
        ENUM_ENTRY(3, 3),
        ANNOTATION_CLASS(4, 4),
        OBJECT(5, 5),
        COMPANION_OBJECT(6, 6);

        private static Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Kind.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
            public Kind findValueByNumber(int i) {
                return Kind.valueOf(i);
            }
        };
        private final int value;

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static Kind valueOf(int i) {
            switch (i) {
                case 0:
                    return CLASS;
                case 1:
                    return INTERFACE;
                case 2:
                    return ENUM_CLASS;
                case 3:
                    return ENUM_ENTRY;
                case 4:
                    return ANNOTATION_CLASS;
                case 5:
                    return OBJECT;
                case 6:
                    return COMPANION_OBJECT;
                default:
                    return null;
            }
        }

        Kind(int i, int i2) {
            this.value = i2;
        }
    }

    public boolean hasFlags() {
        return (this.bitField0_ & 1) == 1;
    }

    public int getFlags() {
        return this.flags_;
    }

    public boolean hasFqName() {
        return (this.bitField0_ & 2) == 2;
    }

    public int getFqName() {
        return this.fqName_;
    }

    public boolean hasCompanionObjectName() {
        return (this.bitField0_ & 4) == 4;
    }

    public int getCompanionObjectName() {
        return this.companionObjectName_;
    }

    public List getTypeParameterList() {
        return this.typeParameter_;
    }

    public int getTypeParameterCount() {
        return this.typeParameter_.size();
    }

    public ProtoBuf$TypeParameter getTypeParameter(int i) {
        return (ProtoBuf$TypeParameter) this.typeParameter_.get(i);
    }

    public List getSupertypeList() {
        return this.supertype_;
    }

    public int getSupertypeCount() {
        return this.supertype_.size();
    }

    public ProtoBuf$Type getSupertype(int i) {
        return (ProtoBuf$Type) this.supertype_.get(i);
    }

    public List getSupertypeIdList() {
        return this.supertypeId_;
    }

    public List getNestedClassNameList() {
        return this.nestedClassName_;
    }

    public List getContextReceiverTypeList() {
        return this.contextReceiverType_;
    }

    public int getContextReceiverTypeCount() {
        return this.contextReceiverType_.size();
    }

    public ProtoBuf$Type getContextReceiverType(int i) {
        return (ProtoBuf$Type) this.contextReceiverType_.get(i);
    }

    public List getContextReceiverTypeIdList() {
        return this.contextReceiverTypeId_;
    }

    public List getConstructorList() {
        return this.constructor_;
    }

    public int getConstructorCount() {
        return this.constructor_.size();
    }

    public ProtoBuf$Constructor getConstructor(int i) {
        return (ProtoBuf$Constructor) this.constructor_.get(i);
    }

    public List getFunctionList() {
        return this.function_;
    }

    public int getFunctionCount() {
        return this.function_.size();
    }

    public ProtoBuf$Function getFunction(int i) {
        return (ProtoBuf$Function) this.function_.get(i);
    }

    public List getPropertyList() {
        return this.property_;
    }

    public int getPropertyCount() {
        return this.property_.size();
    }

    public ProtoBuf$Property getProperty(int i) {
        return (ProtoBuf$Property) this.property_.get(i);
    }

    public List getTypeAliasList() {
        return this.typeAlias_;
    }

    public int getTypeAliasCount() {
        return this.typeAlias_.size();
    }

    public ProtoBuf$TypeAlias getTypeAlias(int i) {
        return (ProtoBuf$TypeAlias) this.typeAlias_.get(i);
    }

    public List getEnumEntryList() {
        return this.enumEntry_;
    }

    public int getEnumEntryCount() {
        return this.enumEntry_.size();
    }

    public ProtoBuf$EnumEntry getEnumEntry(int i) {
        return (ProtoBuf$EnumEntry) this.enumEntry_.get(i);
    }

    public List getSealedSubclassFqNameList() {
        return this.sealedSubclassFqName_;
    }

    public boolean hasInlineClassUnderlyingPropertyName() {
        return (this.bitField0_ & 8) == 8;
    }

    public int getInlineClassUnderlyingPropertyName() {
        return this.inlineClassUnderlyingPropertyName_;
    }

    public boolean hasInlineClassUnderlyingType() {
        return (this.bitField0_ & 16) == 16;
    }

    public ProtoBuf$Type getInlineClassUnderlyingType() {
        return this.inlineClassUnderlyingType_;
    }

    public boolean hasInlineClassUnderlyingTypeId() {
        return (this.bitField0_ & 32) == 32;
    }

    public int getInlineClassUnderlyingTypeId() {
        return this.inlineClassUnderlyingTypeId_;
    }

    public List getMultiFieldValueClassUnderlyingNameList() {
        return this.multiFieldValueClassUnderlyingName_;
    }

    public int getMultiFieldValueClassUnderlyingNameCount() {
        return this.multiFieldValueClassUnderlyingName_.size();
    }

    public List getMultiFieldValueClassUnderlyingTypeList() {
        return this.multiFieldValueClassUnderlyingType_;
    }

    public int getMultiFieldValueClassUnderlyingTypeCount() {
        return this.multiFieldValueClassUnderlyingType_.size();
    }

    public ProtoBuf$Type getMultiFieldValueClassUnderlyingType(int i) {
        return (ProtoBuf$Type) this.multiFieldValueClassUnderlyingType_.get(i);
    }

    public List getMultiFieldValueClassUnderlyingTypeIdList() {
        return this.multiFieldValueClassUnderlyingTypeId_;
    }

    public int getMultiFieldValueClassUnderlyingTypeIdCount() {
        return this.multiFieldValueClassUnderlyingTypeId_.size();
    }

    public boolean hasTypeTable() {
        return (this.bitField0_ & 64) == 64;
    }

    public ProtoBuf$TypeTable getTypeTable() {
        return this.typeTable_;
    }

    public List getVersionRequirementList() {
        return this.versionRequirement_;
    }

    public boolean hasVersionRequirementTable() {
        return (this.bitField0_ & 128) == 128;
    }

    public ProtoBuf$VersionRequirementTable getVersionRequirementTable() {
        return this.versionRequirementTable_;
    }

    private void initFields() {
        this.flags_ = 6;
        this.fqName_ = 0;
        this.companionObjectName_ = 0;
        this.typeParameter_ = Collections.emptyList();
        this.supertype_ = Collections.emptyList();
        this.supertypeId_ = Collections.emptyList();
        this.nestedClassName_ = Collections.emptyList();
        this.contextReceiverType_ = Collections.emptyList();
        this.contextReceiverTypeId_ = Collections.emptyList();
        this.constructor_ = Collections.emptyList();
        this.function_ = Collections.emptyList();
        this.property_ = Collections.emptyList();
        this.typeAlias_ = Collections.emptyList();
        this.enumEntry_ = Collections.emptyList();
        this.sealedSubclassFqName_ = Collections.emptyList();
        this.inlineClassUnderlyingPropertyName_ = 0;
        this.inlineClassUnderlyingType_ = ProtoBuf$Type.getDefaultInstance();
        this.inlineClassUnderlyingTypeId_ = 0;
        this.multiFieldValueClassUnderlyingName_ = Collections.emptyList();
        this.multiFieldValueClassUnderlyingType_ = Collections.emptyList();
        this.multiFieldValueClassUnderlyingTypeId_ = Collections.emptyList();
        this.typeTable_ = ProtoBuf$TypeTable.getDefaultInstance();
        this.versionRequirement_ = Collections.emptyList();
        this.versionRequirementTable_ = ProtoBuf$VersionRequirementTable.getDefaultInstance();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if (!hasFqName()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        for (int i = 0; i < getTypeParameterCount(); i++) {
            if (!getTypeParameter(i).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        for (int i2 = 0; i2 < getSupertypeCount(); i2++) {
            if (!getSupertype(i2).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        for (int i3 = 0; i3 < getContextReceiverTypeCount(); i3++) {
            if (!getContextReceiverType(i3).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        for (int i4 = 0; i4 < getConstructorCount(); i4++) {
            if (!getConstructor(i4).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        for (int i5 = 0; i5 < getFunctionCount(); i5++) {
            if (!getFunction(i5).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        for (int i6 = 0; i6 < getPropertyCount(); i6++) {
            if (!getProperty(i6).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        for (int i7 = 0; i7 < getTypeAliasCount(); i7++) {
            if (!getTypeAlias(i7).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        for (int i8 = 0; i8 < getEnumEntryCount(); i8++) {
            if (!getEnumEntry(i8).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        if (hasInlineClassUnderlyingType() && !getInlineClassUnderlyingType().isInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        for (int i9 = 0; i9 < getMultiFieldValueClassUnderlyingTypeCount(); i9++) {
            if (!getMultiFieldValueClassUnderlyingType(i9).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        if (hasTypeTable() && !getTypeTable().isInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        if (!extensionsAreInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        GeneratedMessageLite.ExtendableMessage.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeInt32(1, this.flags_);
        }
        if (getSupertypeIdList().size() > 0) {
            codedOutputStream.writeRawVarint32(18);
            codedOutputStream.writeRawVarint32(this.supertypeIdMemoizedSerializedSize);
        }
        for (int i = 0; i < this.supertypeId_.size(); i++) {
            codedOutputStream.writeInt32NoTag(((Integer) this.supertypeId_.get(i)).intValue());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(3, this.fqName_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt32(4, this.companionObjectName_);
        }
        for (int i2 = 0; i2 < this.typeParameter_.size(); i2++) {
            codedOutputStream.writeMessage(5, (MessageLite) this.typeParameter_.get(i2));
        }
        for (int i3 = 0; i3 < this.supertype_.size(); i3++) {
            codedOutputStream.writeMessage(6, (MessageLite) this.supertype_.get(i3));
        }
        if (getNestedClassNameList().size() > 0) {
            codedOutputStream.writeRawVarint32(58);
            codedOutputStream.writeRawVarint32(this.nestedClassNameMemoizedSerializedSize);
        }
        for (int i4 = 0; i4 < this.nestedClassName_.size(); i4++) {
            codedOutputStream.writeInt32NoTag(((Integer) this.nestedClassName_.get(i4)).intValue());
        }
        for (int i5 = 0; i5 < this.constructor_.size(); i5++) {
            codedOutputStream.writeMessage(8, (MessageLite) this.constructor_.get(i5));
        }
        for (int i6 = 0; i6 < this.function_.size(); i6++) {
            codedOutputStream.writeMessage(9, (MessageLite) this.function_.get(i6));
        }
        for (int i7 = 0; i7 < this.property_.size(); i7++) {
            codedOutputStream.writeMessage(10, (MessageLite) this.property_.get(i7));
        }
        for (int i8 = 0; i8 < this.typeAlias_.size(); i8++) {
            codedOutputStream.writeMessage(11, (MessageLite) this.typeAlias_.get(i8));
        }
        for (int i9 = 0; i9 < this.enumEntry_.size(); i9++) {
            codedOutputStream.writeMessage(13, (MessageLite) this.enumEntry_.get(i9));
        }
        if (getSealedSubclassFqNameList().size() > 0) {
            codedOutputStream.writeRawVarint32(130);
            codedOutputStream.writeRawVarint32(this.sealedSubclassFqNameMemoizedSerializedSize);
        }
        for (int i10 = 0; i10 < this.sealedSubclassFqName_.size(); i10++) {
            codedOutputStream.writeInt32NoTag(((Integer) this.sealedSubclassFqName_.get(i10)).intValue());
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeInt32(17, this.inlineClassUnderlyingPropertyName_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeMessage(18, this.inlineClassUnderlyingType_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeInt32(19, this.inlineClassUnderlyingTypeId_);
        }
        for (int i11 = 0; i11 < this.contextReceiverType_.size(); i11++) {
            codedOutputStream.writeMessage(20, (MessageLite) this.contextReceiverType_.get(i11));
        }
        if (getContextReceiverTypeIdList().size() > 0) {
            codedOutputStream.writeRawVarint32(170);
            codedOutputStream.writeRawVarint32(this.contextReceiverTypeIdMemoizedSerializedSize);
        }
        for (int i12 = 0; i12 < this.contextReceiverTypeId_.size(); i12++) {
            codedOutputStream.writeInt32NoTag(((Integer) this.contextReceiverTypeId_.get(i12)).intValue());
        }
        if (getMultiFieldValueClassUnderlyingNameList().size() > 0) {
            codedOutputStream.writeRawVarint32(178);
            codedOutputStream.writeRawVarint32(this.multiFieldValueClassUnderlyingNameMemoizedSerializedSize);
        }
        for (int i13 = 0; i13 < this.multiFieldValueClassUnderlyingName_.size(); i13++) {
            codedOutputStream.writeInt32NoTag(((Integer) this.multiFieldValueClassUnderlyingName_.get(i13)).intValue());
        }
        for (int i14 = 0; i14 < this.multiFieldValueClassUnderlyingType_.size(); i14++) {
            codedOutputStream.writeMessage(23, (MessageLite) this.multiFieldValueClassUnderlyingType_.get(i14));
        }
        if (getMultiFieldValueClassUnderlyingTypeIdList().size() > 0) {
            codedOutputStream.writeRawVarint32(194);
            codedOutputStream.writeRawVarint32(this.multiFieldValueClassUnderlyingTypeIdMemoizedSerializedSize);
        }
        for (int i15 = 0; i15 < this.multiFieldValueClassUnderlyingTypeId_.size(); i15++) {
            codedOutputStream.writeInt32NoTag(((Integer) this.multiFieldValueClassUnderlyingTypeId_.get(i15)).intValue());
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeMessage(30, this.typeTable_);
        }
        for (int i16 = 0; i16 < this.versionRequirement_.size(); i16++) {
            codedOutputStream.writeInt32(31, ((Integer) this.versionRequirement_.get(i16)).intValue());
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeMessage(32, this.versionRequirementTable_);
        }
        extensionWriterNewExtensionWriter.writeUntil(19000, codedOutputStream);
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.flags_) : 0;
        int iComputeInt32SizeNoTag = 0;
        for (int i2 = 0; i2 < this.supertypeId_.size(); i2++) {
            iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.supertypeId_.get(i2)).intValue());
        }
        int iComputeMessageSize = iComputeInt32Size + iComputeInt32SizeNoTag;
        if (!getSupertypeIdList().isEmpty()) {
            iComputeMessageSize = iComputeMessageSize + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag);
        }
        this.supertypeIdMemoizedSerializedSize = iComputeInt32SizeNoTag;
        if ((this.bitField0_ & 2) == 2) {
            iComputeMessageSize += CodedOutputStream.computeInt32Size(3, this.fqName_);
        }
        if ((this.bitField0_ & 4) == 4) {
            iComputeMessageSize += CodedOutputStream.computeInt32Size(4, this.companionObjectName_);
        }
        for (int i3 = 0; i3 < this.typeParameter_.size(); i3++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, (MessageLite) this.typeParameter_.get(i3));
        }
        for (int i4 = 0; i4 < this.supertype_.size(); i4++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(6, (MessageLite) this.supertype_.get(i4));
        }
        int iComputeInt32SizeNoTag2 = 0;
        for (int i5 = 0; i5 < this.nestedClassName_.size(); i5++) {
            iComputeInt32SizeNoTag2 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.nestedClassName_.get(i5)).intValue());
        }
        int iComputeMessageSize2 = iComputeMessageSize + iComputeInt32SizeNoTag2;
        if (!getNestedClassNameList().isEmpty()) {
            iComputeMessageSize2 = iComputeMessageSize2 + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag2);
        }
        this.nestedClassNameMemoizedSerializedSize = iComputeInt32SizeNoTag2;
        for (int i6 = 0; i6 < this.constructor_.size(); i6++) {
            iComputeMessageSize2 += CodedOutputStream.computeMessageSize(8, (MessageLite) this.constructor_.get(i6));
        }
        for (int i7 = 0; i7 < this.function_.size(); i7++) {
            iComputeMessageSize2 += CodedOutputStream.computeMessageSize(9, (MessageLite) this.function_.get(i7));
        }
        for (int i8 = 0; i8 < this.property_.size(); i8++) {
            iComputeMessageSize2 += CodedOutputStream.computeMessageSize(10, (MessageLite) this.property_.get(i8));
        }
        for (int i9 = 0; i9 < this.typeAlias_.size(); i9++) {
            iComputeMessageSize2 += CodedOutputStream.computeMessageSize(11, (MessageLite) this.typeAlias_.get(i9));
        }
        for (int i10 = 0; i10 < this.enumEntry_.size(); i10++) {
            iComputeMessageSize2 += CodedOutputStream.computeMessageSize(13, (MessageLite) this.enumEntry_.get(i10));
        }
        int iComputeInt32SizeNoTag3 = 0;
        for (int i11 = 0; i11 < this.sealedSubclassFqName_.size(); i11++) {
            iComputeInt32SizeNoTag3 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.sealedSubclassFqName_.get(i11)).intValue());
        }
        int iComputeMessageSize3 = iComputeMessageSize2 + iComputeInt32SizeNoTag3;
        if (!getSealedSubclassFqNameList().isEmpty()) {
            iComputeMessageSize3 = iComputeMessageSize3 + 2 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag3);
        }
        this.sealedSubclassFqNameMemoizedSerializedSize = iComputeInt32SizeNoTag3;
        if ((this.bitField0_ & 8) == 8) {
            iComputeMessageSize3 += CodedOutputStream.computeInt32Size(17, this.inlineClassUnderlyingPropertyName_);
        }
        if ((this.bitField0_ & 16) == 16) {
            iComputeMessageSize3 += CodedOutputStream.computeMessageSize(18, this.inlineClassUnderlyingType_);
        }
        if ((this.bitField0_ & 32) == 32) {
            iComputeMessageSize3 += CodedOutputStream.computeInt32Size(19, this.inlineClassUnderlyingTypeId_);
        }
        for (int i12 = 0; i12 < this.contextReceiverType_.size(); i12++) {
            iComputeMessageSize3 += CodedOutputStream.computeMessageSize(20, (MessageLite) this.contextReceiverType_.get(i12));
        }
        int iComputeInt32SizeNoTag4 = 0;
        for (int i13 = 0; i13 < this.contextReceiverTypeId_.size(); i13++) {
            iComputeInt32SizeNoTag4 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.contextReceiverTypeId_.get(i13)).intValue());
        }
        int iComputeInt32SizeNoTag5 = iComputeMessageSize3 + iComputeInt32SizeNoTag4;
        if (!getContextReceiverTypeIdList().isEmpty()) {
            iComputeInt32SizeNoTag5 = iComputeInt32SizeNoTag5 + 2 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag4);
        }
        this.contextReceiverTypeIdMemoizedSerializedSize = iComputeInt32SizeNoTag4;
        int iComputeInt32SizeNoTag6 = 0;
        for (int i14 = 0; i14 < this.multiFieldValueClassUnderlyingName_.size(); i14++) {
            iComputeInt32SizeNoTag6 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.multiFieldValueClassUnderlyingName_.get(i14)).intValue());
        }
        int iComputeMessageSize4 = iComputeInt32SizeNoTag5 + iComputeInt32SizeNoTag6;
        if (!getMultiFieldValueClassUnderlyingNameList().isEmpty()) {
            iComputeMessageSize4 = iComputeMessageSize4 + 2 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag6);
        }
        this.multiFieldValueClassUnderlyingNameMemoizedSerializedSize = iComputeInt32SizeNoTag6;
        for (int i15 = 0; i15 < this.multiFieldValueClassUnderlyingType_.size(); i15++) {
            iComputeMessageSize4 += CodedOutputStream.computeMessageSize(23, (MessageLite) this.multiFieldValueClassUnderlyingType_.get(i15));
        }
        int iComputeInt32SizeNoTag7 = 0;
        for (int i16 = 0; i16 < this.multiFieldValueClassUnderlyingTypeId_.size(); i16++) {
            iComputeInt32SizeNoTag7 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.multiFieldValueClassUnderlyingTypeId_.get(i16)).intValue());
        }
        int iComputeMessageSize5 = iComputeMessageSize4 + iComputeInt32SizeNoTag7;
        if (!getMultiFieldValueClassUnderlyingTypeIdList().isEmpty()) {
            iComputeMessageSize5 = iComputeMessageSize5 + 2 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag7);
        }
        this.multiFieldValueClassUnderlyingTypeIdMemoizedSerializedSize = iComputeInt32SizeNoTag7;
        if ((this.bitField0_ & 64) == 64) {
            iComputeMessageSize5 += CodedOutputStream.computeMessageSize(30, this.typeTable_);
        }
        int iComputeInt32SizeNoTag8 = 0;
        for (int i17 = 0; i17 < this.versionRequirement_.size(); i17++) {
            iComputeInt32SizeNoTag8 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.versionRequirement_.get(i17)).intValue());
        }
        int size = iComputeMessageSize5 + iComputeInt32SizeNoTag8 + (getVersionRequirementList().size() * 2);
        if ((this.bitField0_ & 128) == 128) {
            size += CodedOutputStream.computeMessageSize(32, this.versionRequirementTable_);
        }
        int iExtensionsSerializedSize = size + extensionsSerializedSize() + this.unknownFields.size();
        this.memoizedSerializedSize = iExtensionsSerializedSize;
        return iExtensionsSerializedSize;
    }

    public static ProtoBuf$Class parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ProtoBuf$Class) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder(ProtoBuf$Class protoBuf$Class) {
        return newBuilder().mergeFrom(protoBuf$Class);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public Builder toBuilder() {
        return newBuilder(this);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder implements MessageLiteOrBuilder {
        private int bitField0_;
        private int companionObjectName_;
        private int fqName_;
        private int inlineClassUnderlyingPropertyName_;
        private int inlineClassUnderlyingTypeId_;
        private int flags_ = 6;
        private List typeParameter_ = Collections.emptyList();
        private List supertype_ = Collections.emptyList();
        private List supertypeId_ = Collections.emptyList();
        private List nestedClassName_ = Collections.emptyList();
        private List contextReceiverType_ = Collections.emptyList();
        private List contextReceiverTypeId_ = Collections.emptyList();
        private List constructor_ = Collections.emptyList();
        private List function_ = Collections.emptyList();
        private List property_ = Collections.emptyList();
        private List typeAlias_ = Collections.emptyList();
        private List enumEntry_ = Collections.emptyList();
        private List sealedSubclassFqName_ = Collections.emptyList();
        private ProtoBuf$Type inlineClassUnderlyingType_ = ProtoBuf$Type.getDefaultInstance();
        private List multiFieldValueClassUnderlyingName_ = Collections.emptyList();
        private List multiFieldValueClassUnderlyingType_ = Collections.emptyList();
        private List multiFieldValueClassUnderlyingTypeId_ = Collections.emptyList();
        private ProtoBuf$TypeTable typeTable_ = ProtoBuf$TypeTable.getDefaultInstance();
        private List versionRequirement_ = Collections.emptyList();
        private ProtoBuf$VersionRequirementTable versionRequirementTable_ = ProtoBuf$VersionRequirementTable.getDefaultInstance();

        private void maybeForceBuilderInitialization() {
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Builder create() {
            return new Builder();
        }

        public Builder clone() {
            return create().mergeFrom(buildPartial());
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
        public ProtoBuf$Class build() {
            ProtoBuf$Class protoBuf$ClassBuildPartial = buildPartial();
            if (protoBuf$ClassBuildPartial.isInitialized()) {
                return protoBuf$ClassBuildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(protoBuf$ClassBuildPartial);
        }

        public ProtoBuf$Class buildPartial() {
            ProtoBuf$Class protoBuf$Class = new ProtoBuf$Class(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            protoBuf$Class.flags_ = this.flags_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            protoBuf$Class.fqName_ = this.fqName_;
            if ((i & 4) == 4) {
                i2 |= 4;
            }
            protoBuf$Class.companionObjectName_ = this.companionObjectName_;
            if ((this.bitField0_ & 8) == 8) {
                this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                this.bitField0_ &= -9;
            }
            protoBuf$Class.typeParameter_ = this.typeParameter_;
            if ((this.bitField0_ & 16) == 16) {
                this.supertype_ = Collections.unmodifiableList(this.supertype_);
                this.bitField0_ &= -17;
            }
            protoBuf$Class.supertype_ = this.supertype_;
            if ((this.bitField0_ & 32) == 32) {
                this.supertypeId_ = Collections.unmodifiableList(this.supertypeId_);
                this.bitField0_ &= -33;
            }
            protoBuf$Class.supertypeId_ = this.supertypeId_;
            if ((this.bitField0_ & 64) == 64) {
                this.nestedClassName_ = Collections.unmodifiableList(this.nestedClassName_);
                this.bitField0_ &= -65;
            }
            protoBuf$Class.nestedClassName_ = this.nestedClassName_;
            if ((this.bitField0_ & 128) == 128) {
                this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
                this.bitField0_ &= -129;
            }
            protoBuf$Class.contextReceiverType_ = this.contextReceiverType_;
            if ((this.bitField0_ & 256) == 256) {
                this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
                this.bitField0_ &= -257;
            }
            protoBuf$Class.contextReceiverTypeId_ = this.contextReceiverTypeId_;
            if ((this.bitField0_ & 512) == 512) {
                this.constructor_ = Collections.unmodifiableList(this.constructor_);
                this.bitField0_ &= -513;
            }
            protoBuf$Class.constructor_ = this.constructor_;
            if ((this.bitField0_ & 1024) == 1024) {
                this.function_ = Collections.unmodifiableList(this.function_);
                this.bitField0_ &= -1025;
            }
            protoBuf$Class.function_ = this.function_;
            if ((this.bitField0_ & 2048) == 2048) {
                this.property_ = Collections.unmodifiableList(this.property_);
                this.bitField0_ &= -2049;
            }
            protoBuf$Class.property_ = this.property_;
            if ((this.bitField0_ & PKIFailureInfo.certConfirmed) == 4096) {
                this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                this.bitField0_ &= -4097;
            }
            protoBuf$Class.typeAlias_ = this.typeAlias_;
            if ((this.bitField0_ & 8192) == 8192) {
                this.enumEntry_ = Collections.unmodifiableList(this.enumEntry_);
                this.bitField0_ &= -8193;
            }
            protoBuf$Class.enumEntry_ = this.enumEntry_;
            if ((this.bitField0_ & 16384) == 16384) {
                this.sealedSubclassFqName_ = Collections.unmodifiableList(this.sealedSubclassFqName_);
                this.bitField0_ &= -16385;
            }
            protoBuf$Class.sealedSubclassFqName_ = this.sealedSubclassFqName_;
            if ((i & 32768) == 32768) {
                i2 |= 8;
            }
            protoBuf$Class.inlineClassUnderlyingPropertyName_ = this.inlineClassUnderlyingPropertyName_;
            if ((i & PKIFailureInfo.notAuthorized) == 65536) {
                i2 |= 16;
            }
            protoBuf$Class.inlineClassUnderlyingType_ = this.inlineClassUnderlyingType_;
            if ((i & PKIFailureInfo.unsupportedVersion) == 131072) {
                i2 |= 32;
            }
            protoBuf$Class.inlineClassUnderlyingTypeId_ = this.inlineClassUnderlyingTypeId_;
            if ((this.bitField0_ & PKIFailureInfo.transactionIdInUse) == 262144) {
                this.multiFieldValueClassUnderlyingName_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingName_);
                this.bitField0_ &= -262145;
            }
            protoBuf$Class.multiFieldValueClassUnderlyingName_ = this.multiFieldValueClassUnderlyingName_;
            if ((this.bitField0_ & PKIFailureInfo.signerNotTrusted) == 524288) {
                this.multiFieldValueClassUnderlyingType_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingType_);
                this.bitField0_ &= -524289;
            }
            protoBuf$Class.multiFieldValueClassUnderlyingType_ = this.multiFieldValueClassUnderlyingType_;
            if ((this.bitField0_ & PKIFailureInfo.badCertTemplate) == 1048576) {
                this.multiFieldValueClassUnderlyingTypeId_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingTypeId_);
                this.bitField0_ &= -1048577;
            }
            protoBuf$Class.multiFieldValueClassUnderlyingTypeId_ = this.multiFieldValueClassUnderlyingTypeId_;
            if ((i & PKIFailureInfo.badSenderNonce) == 2097152) {
                i2 |= 64;
            }
            protoBuf$Class.typeTable_ = this.typeTable_;
            if ((this.bitField0_ & 4194304) == 4194304) {
                this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                this.bitField0_ &= -4194305;
            }
            protoBuf$Class.versionRequirement_ = this.versionRequirement_;
            if ((i & 8388608) == 8388608) {
                i2 |= 128;
            }
            protoBuf$Class.versionRequirementTable_ = this.versionRequirementTable_;
            protoBuf$Class.bitField0_ = i2;
            return protoBuf$Class;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
        public Builder mergeFrom(ProtoBuf$Class protoBuf$Class) {
            if (protoBuf$Class == ProtoBuf$Class.getDefaultInstance()) {
                return this;
            }
            if (protoBuf$Class.hasFlags()) {
                setFlags(protoBuf$Class.getFlags());
            }
            if (protoBuf$Class.hasFqName()) {
                setFqName(protoBuf$Class.getFqName());
            }
            if (protoBuf$Class.hasCompanionObjectName()) {
                setCompanionObjectName(protoBuf$Class.getCompanionObjectName());
            }
            if (!protoBuf$Class.typeParameter_.isEmpty()) {
                if (this.typeParameter_.isEmpty()) {
                    this.typeParameter_ = protoBuf$Class.typeParameter_;
                    this.bitField0_ &= -9;
                } else {
                    ensureTypeParameterIsMutable();
                    this.typeParameter_.addAll(protoBuf$Class.typeParameter_);
                }
            }
            if (!protoBuf$Class.supertype_.isEmpty()) {
                if (this.supertype_.isEmpty()) {
                    this.supertype_ = protoBuf$Class.supertype_;
                    this.bitField0_ &= -17;
                } else {
                    ensureSupertypeIsMutable();
                    this.supertype_.addAll(protoBuf$Class.supertype_);
                }
            }
            if (!protoBuf$Class.supertypeId_.isEmpty()) {
                if (this.supertypeId_.isEmpty()) {
                    this.supertypeId_ = protoBuf$Class.supertypeId_;
                    this.bitField0_ &= -33;
                } else {
                    ensureSupertypeIdIsMutable();
                    this.supertypeId_.addAll(protoBuf$Class.supertypeId_);
                }
            }
            if (!protoBuf$Class.nestedClassName_.isEmpty()) {
                if (this.nestedClassName_.isEmpty()) {
                    this.nestedClassName_ = protoBuf$Class.nestedClassName_;
                    this.bitField0_ &= -65;
                } else {
                    ensureNestedClassNameIsMutable();
                    this.nestedClassName_.addAll(protoBuf$Class.nestedClassName_);
                }
            }
            if (!protoBuf$Class.contextReceiverType_.isEmpty()) {
                if (this.contextReceiverType_.isEmpty()) {
                    this.contextReceiverType_ = protoBuf$Class.contextReceiverType_;
                    this.bitField0_ &= -129;
                } else {
                    ensureContextReceiverTypeIsMutable();
                    this.contextReceiverType_.addAll(protoBuf$Class.contextReceiverType_);
                }
            }
            if (!protoBuf$Class.contextReceiverTypeId_.isEmpty()) {
                if (this.contextReceiverTypeId_.isEmpty()) {
                    this.contextReceiverTypeId_ = protoBuf$Class.contextReceiverTypeId_;
                    this.bitField0_ &= -257;
                } else {
                    ensureContextReceiverTypeIdIsMutable();
                    this.contextReceiverTypeId_.addAll(protoBuf$Class.contextReceiverTypeId_);
                }
            }
            if (!protoBuf$Class.constructor_.isEmpty()) {
                if (this.constructor_.isEmpty()) {
                    this.constructor_ = protoBuf$Class.constructor_;
                    this.bitField0_ &= -513;
                } else {
                    ensureConstructorIsMutable();
                    this.constructor_.addAll(protoBuf$Class.constructor_);
                }
            }
            if (!protoBuf$Class.function_.isEmpty()) {
                if (this.function_.isEmpty()) {
                    this.function_ = protoBuf$Class.function_;
                    this.bitField0_ &= -1025;
                } else {
                    ensureFunctionIsMutable();
                    this.function_.addAll(protoBuf$Class.function_);
                }
            }
            if (!protoBuf$Class.property_.isEmpty()) {
                if (this.property_.isEmpty()) {
                    this.property_ = protoBuf$Class.property_;
                    this.bitField0_ &= -2049;
                } else {
                    ensurePropertyIsMutable();
                    this.property_.addAll(protoBuf$Class.property_);
                }
            }
            if (!protoBuf$Class.typeAlias_.isEmpty()) {
                if (this.typeAlias_.isEmpty()) {
                    this.typeAlias_ = protoBuf$Class.typeAlias_;
                    this.bitField0_ &= -4097;
                } else {
                    ensureTypeAliasIsMutable();
                    this.typeAlias_.addAll(protoBuf$Class.typeAlias_);
                }
            }
            if (!protoBuf$Class.enumEntry_.isEmpty()) {
                if (this.enumEntry_.isEmpty()) {
                    this.enumEntry_ = protoBuf$Class.enumEntry_;
                    this.bitField0_ &= -8193;
                } else {
                    ensureEnumEntryIsMutable();
                    this.enumEntry_.addAll(protoBuf$Class.enumEntry_);
                }
            }
            if (!protoBuf$Class.sealedSubclassFqName_.isEmpty()) {
                if (this.sealedSubclassFqName_.isEmpty()) {
                    this.sealedSubclassFqName_ = protoBuf$Class.sealedSubclassFqName_;
                    this.bitField0_ &= -16385;
                } else {
                    ensureSealedSubclassFqNameIsMutable();
                    this.sealedSubclassFqName_.addAll(protoBuf$Class.sealedSubclassFqName_);
                }
            }
            if (protoBuf$Class.hasInlineClassUnderlyingPropertyName()) {
                setInlineClassUnderlyingPropertyName(protoBuf$Class.getInlineClassUnderlyingPropertyName());
            }
            if (protoBuf$Class.hasInlineClassUnderlyingType()) {
                mergeInlineClassUnderlyingType(protoBuf$Class.getInlineClassUnderlyingType());
            }
            if (protoBuf$Class.hasInlineClassUnderlyingTypeId()) {
                setInlineClassUnderlyingTypeId(protoBuf$Class.getInlineClassUnderlyingTypeId());
            }
            if (!protoBuf$Class.multiFieldValueClassUnderlyingName_.isEmpty()) {
                if (this.multiFieldValueClassUnderlyingName_.isEmpty()) {
                    this.multiFieldValueClassUnderlyingName_ = protoBuf$Class.multiFieldValueClassUnderlyingName_;
                    this.bitField0_ &= -262145;
                } else {
                    ensureMultiFieldValueClassUnderlyingNameIsMutable();
                    this.multiFieldValueClassUnderlyingName_.addAll(protoBuf$Class.multiFieldValueClassUnderlyingName_);
                }
            }
            if (!protoBuf$Class.multiFieldValueClassUnderlyingType_.isEmpty()) {
                if (this.multiFieldValueClassUnderlyingType_.isEmpty()) {
                    this.multiFieldValueClassUnderlyingType_ = protoBuf$Class.multiFieldValueClassUnderlyingType_;
                    this.bitField0_ &= -524289;
                } else {
                    ensureMultiFieldValueClassUnderlyingTypeIsMutable();
                    this.multiFieldValueClassUnderlyingType_.addAll(protoBuf$Class.multiFieldValueClassUnderlyingType_);
                }
            }
            if (!protoBuf$Class.multiFieldValueClassUnderlyingTypeId_.isEmpty()) {
                if (this.multiFieldValueClassUnderlyingTypeId_.isEmpty()) {
                    this.multiFieldValueClassUnderlyingTypeId_ = protoBuf$Class.multiFieldValueClassUnderlyingTypeId_;
                    this.bitField0_ &= -1048577;
                } else {
                    ensureMultiFieldValueClassUnderlyingTypeIdIsMutable();
                    this.multiFieldValueClassUnderlyingTypeId_.addAll(protoBuf$Class.multiFieldValueClassUnderlyingTypeId_);
                }
            }
            if (protoBuf$Class.hasTypeTable()) {
                mergeTypeTable(protoBuf$Class.getTypeTable());
            }
            if (!protoBuf$Class.versionRequirement_.isEmpty()) {
                if (this.versionRequirement_.isEmpty()) {
                    this.versionRequirement_ = protoBuf$Class.versionRequirement_;
                    this.bitField0_ &= -4194305;
                } else {
                    ensureVersionRequirementIsMutable();
                    this.versionRequirement_.addAll(protoBuf$Class.versionRequirement_);
                }
            }
            if (protoBuf$Class.hasVersionRequirementTable()) {
                mergeVersionRequirementTable(protoBuf$Class.getVersionRequirementTable());
            }
            mergeExtensionFields(protoBuf$Class);
            setUnknownFields(getUnknownFields().concat(protoBuf$Class.unknownFields));
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class.Builder mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream r3, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                kotlin.reflect.jvm.internal.impl.protobuf.Parser r1 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class.PARSER     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class r3 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class) r3     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                if (r3 == 0) goto Le
                r2.mergeFrom(r3)
            Le:
                return r2
            Lf:
                r3 = move-exception
                goto L1b
            L11:
                r3 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class r4 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class) r4     // Catch: java.lang.Throwable -> Lf
                throw r3     // Catch: java.lang.Throwable -> L19
            L19:
                r3 = move-exception
                r0 = r4
            L1b:
                if (r0 == 0) goto L20
                r2.mergeFrom(r0)
            L20:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class.Builder.mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite):kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class$Builder");
        }

        public Builder setFlags(int i) {
            this.bitField0_ |= 1;
            this.flags_ = i;
            return this;
        }

        public Builder setFqName(int i) {
            this.bitField0_ |= 2;
            this.fqName_ = i;
            return this;
        }

        public Builder setCompanionObjectName(int i) {
            this.bitField0_ |= 4;
            this.companionObjectName_ = i;
            return this;
        }

        private void ensureTypeParameterIsMutable() {
            if ((this.bitField0_ & 8) != 8) {
                this.typeParameter_ = new ArrayList(this.typeParameter_);
                this.bitField0_ |= 8;
            }
        }

        private void ensureSupertypeIsMutable() {
            if ((this.bitField0_ & 16) != 16) {
                this.supertype_ = new ArrayList(this.supertype_);
                this.bitField0_ |= 16;
            }
        }

        private void ensureSupertypeIdIsMutable() {
            if ((this.bitField0_ & 32) != 32) {
                this.supertypeId_ = new ArrayList(this.supertypeId_);
                this.bitField0_ |= 32;
            }
        }

        private void ensureNestedClassNameIsMutable() {
            if ((this.bitField0_ & 64) != 64) {
                this.nestedClassName_ = new ArrayList(this.nestedClassName_);
                this.bitField0_ |= 64;
            }
        }

        private void ensureContextReceiverTypeIsMutable() {
            if ((this.bitField0_ & 128) != 128) {
                this.contextReceiverType_ = new ArrayList(this.contextReceiverType_);
                this.bitField0_ |= 128;
            }
        }

        private void ensureContextReceiverTypeIdIsMutable() {
            if ((this.bitField0_ & 256) != 256) {
                this.contextReceiverTypeId_ = new ArrayList(this.contextReceiverTypeId_);
                this.bitField0_ |= 256;
            }
        }

        private void ensureConstructorIsMutable() {
            if ((this.bitField0_ & 512) != 512) {
                this.constructor_ = new ArrayList(this.constructor_);
                this.bitField0_ |= 512;
            }
        }

        private void ensureFunctionIsMutable() {
            if ((this.bitField0_ & 1024) != 1024) {
                this.function_ = new ArrayList(this.function_);
                this.bitField0_ |= 1024;
            }
        }

        private void ensurePropertyIsMutable() {
            if ((this.bitField0_ & 2048) != 2048) {
                this.property_ = new ArrayList(this.property_);
                this.bitField0_ |= 2048;
            }
        }

        private void ensureTypeAliasIsMutable() {
            if ((this.bitField0_ & PKIFailureInfo.certConfirmed) != 4096) {
                this.typeAlias_ = new ArrayList(this.typeAlias_);
                this.bitField0_ |= PKIFailureInfo.certConfirmed;
            }
        }

        private void ensureEnumEntryIsMutable() {
            if ((this.bitField0_ & 8192) != 8192) {
                this.enumEntry_ = new ArrayList(this.enumEntry_);
                this.bitField0_ |= 8192;
            }
        }

        private void ensureSealedSubclassFqNameIsMutable() {
            if ((this.bitField0_ & 16384) != 16384) {
                this.sealedSubclassFqName_ = new ArrayList(this.sealedSubclassFqName_);
                this.bitField0_ |= 16384;
            }
        }

        public Builder setInlineClassUnderlyingPropertyName(int i) {
            this.bitField0_ |= 32768;
            this.inlineClassUnderlyingPropertyName_ = i;
            return this;
        }

        public Builder mergeInlineClassUnderlyingType(ProtoBuf$Type protoBuf$Type) {
            if ((this.bitField0_ & PKIFailureInfo.notAuthorized) == 65536 && this.inlineClassUnderlyingType_ != ProtoBuf$Type.getDefaultInstance()) {
                this.inlineClassUnderlyingType_ = ProtoBuf$Type.newBuilder(this.inlineClassUnderlyingType_).mergeFrom(protoBuf$Type).buildPartial();
            } else {
                this.inlineClassUnderlyingType_ = protoBuf$Type;
            }
            this.bitField0_ |= PKIFailureInfo.notAuthorized;
            return this;
        }

        public Builder setInlineClassUnderlyingTypeId(int i) {
            this.bitField0_ |= PKIFailureInfo.unsupportedVersion;
            this.inlineClassUnderlyingTypeId_ = i;
            return this;
        }

        private void ensureMultiFieldValueClassUnderlyingNameIsMutable() {
            if ((this.bitField0_ & PKIFailureInfo.transactionIdInUse) != 262144) {
                this.multiFieldValueClassUnderlyingName_ = new ArrayList(this.multiFieldValueClassUnderlyingName_);
                this.bitField0_ |= PKIFailureInfo.transactionIdInUse;
            }
        }

        private void ensureMultiFieldValueClassUnderlyingTypeIsMutable() {
            if ((this.bitField0_ & PKIFailureInfo.signerNotTrusted) != 524288) {
                this.multiFieldValueClassUnderlyingType_ = new ArrayList(this.multiFieldValueClassUnderlyingType_);
                this.bitField0_ |= PKIFailureInfo.signerNotTrusted;
            }
        }

        private void ensureMultiFieldValueClassUnderlyingTypeIdIsMutable() {
            if ((this.bitField0_ & PKIFailureInfo.badCertTemplate) != 1048576) {
                this.multiFieldValueClassUnderlyingTypeId_ = new ArrayList(this.multiFieldValueClassUnderlyingTypeId_);
                this.bitField0_ |= PKIFailureInfo.badCertTemplate;
            }
        }

        public Builder mergeTypeTable(ProtoBuf$TypeTable protoBuf$TypeTable) {
            if ((this.bitField0_ & PKIFailureInfo.badSenderNonce) == 2097152 && this.typeTable_ != ProtoBuf$TypeTable.getDefaultInstance()) {
                this.typeTable_ = ProtoBuf$TypeTable.newBuilder(this.typeTable_).mergeFrom(protoBuf$TypeTable).buildPartial();
            } else {
                this.typeTable_ = protoBuf$TypeTable;
            }
            this.bitField0_ |= PKIFailureInfo.badSenderNonce;
            return this;
        }

        private void ensureVersionRequirementIsMutable() {
            if ((this.bitField0_ & 4194304) != 4194304) {
                this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                this.bitField0_ |= 4194304;
            }
        }

        public Builder mergeVersionRequirementTable(ProtoBuf$VersionRequirementTable protoBuf$VersionRequirementTable) {
            if ((this.bitField0_ & 8388608) == 8388608 && this.versionRequirementTable_ != ProtoBuf$VersionRequirementTable.getDefaultInstance()) {
                this.versionRequirementTable_ = ProtoBuf$VersionRequirementTable.newBuilder(this.versionRequirementTable_).mergeFrom(protoBuf$VersionRequirementTable).buildPartial();
            } else {
                this.versionRequirementTable_ = protoBuf$VersionRequirementTable;
            }
            this.bitField0_ |= 8388608;
            return this;
        }
    }
}
