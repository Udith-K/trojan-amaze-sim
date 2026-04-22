package kotlin.reflect.jvm.internal.impl.metadata;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Contract;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractParser;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.asn1.eac.EACTags;

/* JADX INFO: loaded from: classes2.dex */
public final class ProtoBuf$Function extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static Parser PARSER = new AbstractParser() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function.1
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
        public ProtoBuf$Function parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return new ProtoBuf$Function(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ProtoBuf$Function defaultInstance;
    private int bitField0_;
    private int contextReceiverTypeIdMemoizedSerializedSize;
    private List contextReceiverTypeId_;
    private List contextReceiverType_;
    private ProtoBuf$Contract contract_;
    private int flags_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private int name_;
    private int oldFlags_;
    private int receiverTypeId_;
    private ProtoBuf$Type receiverType_;
    private int returnTypeId_;
    private ProtoBuf$Type returnType_;
    private List typeParameter_;
    private ProtoBuf$TypeTable typeTable_;
    private final ByteString unknownFields;
    private List valueParameter_;
    private List versionRequirement_;

    private ProtoBuf$Function(GeneratedMessageLite.ExtendableBuilder extendableBuilder) {
        super(extendableBuilder);
        this.contextReceiverTypeIdMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = extendableBuilder.getUnknownFields();
    }

    private ProtoBuf$Function(boolean z) {
        this.contextReceiverTypeIdMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }

    public static ProtoBuf$Function getDefaultInstance() {
        return defaultInstance;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
    public ProtoBuf$Function getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean] */
    private ProtoBuf$Function(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        this.contextReceiverTypeIdMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        initFields();
        ByteString.Output outputNewOutput = ByteString.newOutput();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
        boolean z = false;
        int i = 0;
        while (true) {
            ?? unknownField = 1024;
            if (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        switch (tag) {
                            case 0:
                                z = true;
                                break;
                            case 8:
                                this.bitField0_ |= 2;
                                this.oldFlags_ = codedInputStream.readInt32();
                                break;
                            case 16:
                                this.bitField0_ |= 4;
                                this.name_ = codedInputStream.readInt32();
                                break;
                            case 26:
                                ProtoBuf$Type.Builder builder = (this.bitField0_ & 8) == 8 ? this.returnType_.toBuilder() : null;
                                ProtoBuf$Type protoBuf$Type = (ProtoBuf$Type) codedInputStream.readMessage(ProtoBuf$Type.PARSER, extensionRegistryLite);
                                this.returnType_ = protoBuf$Type;
                                if (builder != null) {
                                    builder.mergeFrom(protoBuf$Type);
                                    this.returnType_ = builder.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                break;
                            case 34:
                                int i2 = (i == true ? 1 : 0) & 32;
                                i = i;
                                if (i2 != 32) {
                                    this.typeParameter_ = new ArrayList();
                                    i = (i == true ? 1 : 0) | 32;
                                }
                                this.typeParameter_.add(codedInputStream.readMessage(ProtoBuf$TypeParameter.PARSER, extensionRegistryLite));
                                break;
                            case EACTags.CURRENCY_CODE /* 42 */:
                                ProtoBuf$Type.Builder builder2 = (this.bitField0_ & 32) == 32 ? this.receiverType_.toBuilder() : null;
                                ProtoBuf$Type protoBuf$Type2 = (ProtoBuf$Type) codedInputStream.readMessage(ProtoBuf$Type.PARSER, extensionRegistryLite);
                                this.receiverType_ = protoBuf$Type2;
                                if (builder2 != null) {
                                    builder2.mergeFrom(protoBuf$Type2);
                                    this.receiverType_ = builder2.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 50:
                                int i3 = (i == true ? 1 : 0) & 1024;
                                i = i;
                                if (i3 != 1024) {
                                    this.valueParameter_ = new ArrayList();
                                    i = (i == true ? 1 : 0) | 1024;
                                }
                                this.valueParameter_.add(codedInputStream.readMessage(ProtoBuf$ValueParameter.PARSER, extensionRegistryLite));
                                break;
                            case 56:
                                this.bitField0_ |= 16;
                                this.returnTypeId_ = codedInputStream.readInt32();
                                break;
                            case 64:
                                this.bitField0_ |= 64;
                                this.receiverTypeId_ = codedInputStream.readInt32();
                                break;
                            case 72:
                                this.bitField0_ |= 1;
                                this.flags_ = codedInputStream.readInt32();
                                break;
                            case EACTags.HISTORICAL_BYTES /* 82 */:
                                int i4 = (i == true ? 1 : 0) & 256;
                                i = i;
                                if (i4 != 256) {
                                    this.contextReceiverType_ = new ArrayList();
                                    i = (i == true ? 1 : 0) | 256;
                                }
                                this.contextReceiverType_.add(codedInputStream.readMessage(ProtoBuf$Type.PARSER, extensionRegistryLite));
                                break;
                            case 88:
                                int i5 = (i == true ? 1 : 0) & 512;
                                i = i;
                                if (i5 != 512) {
                                    this.contextReceiverTypeId_ = new ArrayList();
                                    i = (i == true ? 1 : 0) | 512;
                                }
                                this.contextReceiverTypeId_.add(Integer.valueOf(codedInputStream.readInt32()));
                                break;
                            case 90:
                                int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                int i6 = (i == true ? 1 : 0) & 512;
                                i = i;
                                if (i6 != 512) {
                                    i = i;
                                    if (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.contextReceiverTypeId_ = new ArrayList();
                                        i = (i == true ? 1 : 0) | 512;
                                    }
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.contextReceiverTypeId_.add(Integer.valueOf(codedInputStream.readInt32()));
                                }
                                codedInputStream.popLimit(iPushLimit);
                                break;
                            case 242:
                                ProtoBuf$TypeTable.Builder builder3 = (this.bitField0_ & 128) == 128 ? this.typeTable_.toBuilder() : null;
                                ProtoBuf$TypeTable protoBuf$TypeTable = (ProtoBuf$TypeTable) codedInputStream.readMessage(ProtoBuf$TypeTable.PARSER, extensionRegistryLite);
                                this.typeTable_ = protoBuf$TypeTable;
                                if (builder3 != null) {
                                    builder3.mergeFrom(protoBuf$TypeTable);
                                    this.typeTable_ = builder3.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 248:
                                int i7 = (i == true ? 1 : 0) & PKIFailureInfo.certConfirmed;
                                i = i;
                                if (i7 != 4096) {
                                    this.versionRequirement_ = new ArrayList();
                                    i = (i == true ? 1 : 0) | PKIFailureInfo.certConfirmed;
                                }
                                this.versionRequirement_.add(Integer.valueOf(codedInputStream.readInt32()));
                                break;
                            case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /* 250 */:
                                int iPushLimit2 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                int i8 = (i == true ? 1 : 0) & PKIFailureInfo.certConfirmed;
                                i = i;
                                if (i8 != 4096) {
                                    i = i;
                                    if (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.versionRequirement_ = new ArrayList();
                                        i = (i == true ? 1 : 0) | PKIFailureInfo.certConfirmed;
                                    }
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_.add(Integer.valueOf(codedInputStream.readInt32()));
                                }
                                codedInputStream.popLimit(iPushLimit2);
                                break;
                            case 258:
                                ProtoBuf$Contract.Builder builder4 = (this.bitField0_ & 256) == 256 ? this.contract_.toBuilder() : null;
                                ProtoBuf$Contract protoBuf$Contract = (ProtoBuf$Contract) codedInputStream.readMessage(ProtoBuf$Contract.PARSER, extensionRegistryLite);
                                this.contract_ = protoBuf$Contract;
                                if (builder4 != null) {
                                    builder4.mergeFrom(protoBuf$Contract);
                                    this.contract_ = builder4.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            default:
                                unknownField = parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag);
                                if (unknownField == 0) {
                                    z = true;
                                }
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable th) {
                    if (((i == true ? 1 : 0) & 32) == 32) {
                        this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    }
                    if (((i == true ? 1 : 0) & 1024) == unknownField) {
                        this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                    }
                    if (((i == true ? 1 : 0) & 256) == 256) {
                        this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
                    }
                    if (((i == true ? 1 : 0) & 512) == 512) {
                        this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
                    }
                    if (((i == true ? 1 : 0) & PKIFailureInfo.certConfirmed) == 4096) {
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
            } else {
                if (((i == true ? 1 : 0) & 32) == 32) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                }
                if (((i == true ? 1 : 0) & 1024) == 1024) {
                    this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                }
                if (((i == true ? 1 : 0) & 256) == 256) {
                    this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
                }
                if (((i == true ? 1 : 0) & 512) == 512) {
                    this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
                }
                if (((i == true ? 1 : 0) & PKIFailureInfo.certConfirmed) == 4096) {
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
                return;
            }
        }
    }

    static {
        ProtoBuf$Function protoBuf$Function = new ProtoBuf$Function(true);
        defaultInstance = protoBuf$Function;
        protoBuf$Function.initFields();
    }

    public boolean hasFlags() {
        return (this.bitField0_ & 1) == 1;
    }

    public int getFlags() {
        return this.flags_;
    }

    public boolean hasOldFlags() {
        return (this.bitField0_ & 2) == 2;
    }

    public int getOldFlags() {
        return this.oldFlags_;
    }

    public boolean hasName() {
        return (this.bitField0_ & 4) == 4;
    }

    public int getName() {
        return this.name_;
    }

    public boolean hasReturnType() {
        return (this.bitField0_ & 8) == 8;
    }

    public ProtoBuf$Type getReturnType() {
        return this.returnType_;
    }

    public boolean hasReturnTypeId() {
        return (this.bitField0_ & 16) == 16;
    }

    public int getReturnTypeId() {
        return this.returnTypeId_;
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

    public boolean hasReceiverType() {
        return (this.bitField0_ & 32) == 32;
    }

    public ProtoBuf$Type getReceiverType() {
        return this.receiverType_;
    }

    public boolean hasReceiverTypeId() {
        return (this.bitField0_ & 64) == 64;
    }

    public int getReceiverTypeId() {
        return this.receiverTypeId_;
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

    public List getValueParameterList() {
        return this.valueParameter_;
    }

    public int getValueParameterCount() {
        return this.valueParameter_.size();
    }

    public ProtoBuf$ValueParameter getValueParameter(int i) {
        return (ProtoBuf$ValueParameter) this.valueParameter_.get(i);
    }

    public boolean hasTypeTable() {
        return (this.bitField0_ & 128) == 128;
    }

    public ProtoBuf$TypeTable getTypeTable() {
        return this.typeTable_;
    }

    public List getVersionRequirementList() {
        return this.versionRequirement_;
    }

    public boolean hasContract() {
        return (this.bitField0_ & 256) == 256;
    }

    public ProtoBuf$Contract getContract() {
        return this.contract_;
    }

    private void initFields() {
        this.flags_ = 6;
        this.oldFlags_ = 6;
        this.name_ = 0;
        this.returnType_ = ProtoBuf$Type.getDefaultInstance();
        this.returnTypeId_ = 0;
        this.typeParameter_ = Collections.emptyList();
        this.receiverType_ = ProtoBuf$Type.getDefaultInstance();
        this.receiverTypeId_ = 0;
        this.contextReceiverType_ = Collections.emptyList();
        this.contextReceiverTypeId_ = Collections.emptyList();
        this.valueParameter_ = Collections.emptyList();
        this.typeTable_ = ProtoBuf$TypeTable.getDefaultInstance();
        this.versionRequirement_ = Collections.emptyList();
        this.contract_ = ProtoBuf$Contract.getDefaultInstance();
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
        if (!hasName()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        if (hasReturnType() && !getReturnType().isInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        for (int i = 0; i < getTypeParameterCount(); i++) {
            if (!getTypeParameter(i).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        if (hasReceiverType() && !getReceiverType().isInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        for (int i2 = 0; i2 < getContextReceiverTypeCount(); i2++) {
            if (!getContextReceiverType(i2).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        for (int i3 = 0; i3 < getValueParameterCount(); i3++) {
            if (!getValueParameter(i3).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        if (hasTypeTable() && !getTypeTable().isInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        if (hasContract() && !getContract().isInitialized()) {
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
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(1, this.oldFlags_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt32(2, this.name_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeMessage(3, this.returnType_);
        }
        for (int i = 0; i < this.typeParameter_.size(); i++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.typeParameter_.get(i));
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeMessage(5, this.receiverType_);
        }
        for (int i2 = 0; i2 < this.valueParameter_.size(); i2++) {
            codedOutputStream.writeMessage(6, (MessageLite) this.valueParameter_.get(i2));
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeInt32(7, this.returnTypeId_);
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeInt32(8, this.receiverTypeId_);
        }
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeInt32(9, this.flags_);
        }
        for (int i3 = 0; i3 < this.contextReceiverType_.size(); i3++) {
            codedOutputStream.writeMessage(10, (MessageLite) this.contextReceiverType_.get(i3));
        }
        if (getContextReceiverTypeIdList().size() > 0) {
            codedOutputStream.writeRawVarint32(90);
            codedOutputStream.writeRawVarint32(this.contextReceiverTypeIdMemoizedSerializedSize);
        }
        for (int i4 = 0; i4 < this.contextReceiverTypeId_.size(); i4++) {
            codedOutputStream.writeInt32NoTag(((Integer) this.contextReceiverTypeId_.get(i4)).intValue());
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeMessage(30, this.typeTable_);
        }
        for (int i5 = 0; i5 < this.versionRequirement_.size(); i5++) {
            codedOutputStream.writeInt32(31, ((Integer) this.versionRequirement_.get(i5)).intValue());
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeMessage(32, this.contract_);
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
        int iComputeInt32Size = (this.bitField0_ & 2) == 2 ? CodedOutputStream.computeInt32Size(1, this.oldFlags_) : 0;
        if ((this.bitField0_ & 4) == 4) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(2, this.name_);
        }
        if ((this.bitField0_ & 8) == 8) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(3, this.returnType_);
        }
        for (int i2 = 0; i2 < this.typeParameter_.size(); i2++) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(4, (MessageLite) this.typeParameter_.get(i2));
        }
        if ((this.bitField0_ & 32) == 32) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(5, this.receiverType_);
        }
        for (int i3 = 0; i3 < this.valueParameter_.size(); i3++) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(6, (MessageLite) this.valueParameter_.get(i3));
        }
        if ((this.bitField0_ & 16) == 16) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(7, this.returnTypeId_);
        }
        if ((this.bitField0_ & 64) == 64) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(8, this.receiverTypeId_);
        }
        if ((this.bitField0_ & 1) == 1) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(9, this.flags_);
        }
        for (int i4 = 0; i4 < this.contextReceiverType_.size(); i4++) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(10, (MessageLite) this.contextReceiverType_.get(i4));
        }
        int iComputeInt32SizeNoTag = 0;
        for (int i5 = 0; i5 < this.contextReceiverTypeId_.size(); i5++) {
            iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.contextReceiverTypeId_.get(i5)).intValue());
        }
        int iComputeMessageSize = iComputeInt32Size + iComputeInt32SizeNoTag;
        if (!getContextReceiverTypeIdList().isEmpty()) {
            iComputeMessageSize = iComputeMessageSize + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag);
        }
        this.contextReceiverTypeIdMemoizedSerializedSize = iComputeInt32SizeNoTag;
        if ((this.bitField0_ & 128) == 128) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(30, this.typeTable_);
        }
        int iComputeInt32SizeNoTag2 = 0;
        for (int i6 = 0; i6 < this.versionRequirement_.size(); i6++) {
            iComputeInt32SizeNoTag2 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.versionRequirement_.get(i6)).intValue());
        }
        int size = iComputeMessageSize + iComputeInt32SizeNoTag2 + (getVersionRequirementList().size() * 2);
        if ((this.bitField0_ & 256) == 256) {
            size += CodedOutputStream.computeMessageSize(32, this.contract_);
        }
        int iExtensionsSerializedSize = size + extensionsSerializedSize() + this.unknownFields.size();
        this.memoizedSerializedSize = iExtensionsSerializedSize;
        return iExtensionsSerializedSize;
    }

    public static ProtoBuf$Function parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ProtoBuf$Function) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder(ProtoBuf$Function protoBuf$Function) {
        return newBuilder().mergeFrom(protoBuf$Function);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public Builder toBuilder() {
        return newBuilder(this);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder implements MessageLiteOrBuilder {
        private int bitField0_;
        private int name_;
        private int receiverTypeId_;
        private int returnTypeId_;
        private int flags_ = 6;
        private int oldFlags_ = 6;
        private ProtoBuf$Type returnType_ = ProtoBuf$Type.getDefaultInstance();
        private List typeParameter_ = Collections.emptyList();
        private ProtoBuf$Type receiverType_ = ProtoBuf$Type.getDefaultInstance();
        private List contextReceiverType_ = Collections.emptyList();
        private List contextReceiverTypeId_ = Collections.emptyList();
        private List valueParameter_ = Collections.emptyList();
        private ProtoBuf$TypeTable typeTable_ = ProtoBuf$TypeTable.getDefaultInstance();
        private List versionRequirement_ = Collections.emptyList();
        private ProtoBuf$Contract contract_ = ProtoBuf$Contract.getDefaultInstance();

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
        public ProtoBuf$Function build() {
            ProtoBuf$Function protoBuf$FunctionBuildPartial = buildPartial();
            if (protoBuf$FunctionBuildPartial.isInitialized()) {
                return protoBuf$FunctionBuildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(protoBuf$FunctionBuildPartial);
        }

        public ProtoBuf$Function buildPartial() {
            ProtoBuf$Function protoBuf$Function = new ProtoBuf$Function(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            protoBuf$Function.flags_ = this.flags_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            protoBuf$Function.oldFlags_ = this.oldFlags_;
            if ((i & 4) == 4) {
                i2 |= 4;
            }
            protoBuf$Function.name_ = this.name_;
            if ((i & 8) == 8) {
                i2 |= 8;
            }
            protoBuf$Function.returnType_ = this.returnType_;
            if ((i & 16) == 16) {
                i2 |= 16;
            }
            protoBuf$Function.returnTypeId_ = this.returnTypeId_;
            if ((this.bitField0_ & 32) == 32) {
                this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                this.bitField0_ &= -33;
            }
            protoBuf$Function.typeParameter_ = this.typeParameter_;
            if ((i & 64) == 64) {
                i2 |= 32;
            }
            protoBuf$Function.receiverType_ = this.receiverType_;
            if ((i & 128) == 128) {
                i2 |= 64;
            }
            protoBuf$Function.receiverTypeId_ = this.receiverTypeId_;
            if ((this.bitField0_ & 256) == 256) {
                this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
                this.bitField0_ &= -257;
            }
            protoBuf$Function.contextReceiverType_ = this.contextReceiverType_;
            if ((this.bitField0_ & 512) == 512) {
                this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
                this.bitField0_ &= -513;
            }
            protoBuf$Function.contextReceiverTypeId_ = this.contextReceiverTypeId_;
            if ((this.bitField0_ & 1024) == 1024) {
                this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                this.bitField0_ &= -1025;
            }
            protoBuf$Function.valueParameter_ = this.valueParameter_;
            if ((i & 2048) == 2048) {
                i2 |= 128;
            }
            protoBuf$Function.typeTable_ = this.typeTable_;
            if ((this.bitField0_ & PKIFailureInfo.certConfirmed) == 4096) {
                this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                this.bitField0_ &= -4097;
            }
            protoBuf$Function.versionRequirement_ = this.versionRequirement_;
            if ((i & 8192) == 8192) {
                i2 |= 256;
            }
            protoBuf$Function.contract_ = this.contract_;
            protoBuf$Function.bitField0_ = i2;
            return protoBuf$Function;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
        public Builder mergeFrom(ProtoBuf$Function protoBuf$Function) {
            if (protoBuf$Function == ProtoBuf$Function.getDefaultInstance()) {
                return this;
            }
            if (protoBuf$Function.hasFlags()) {
                setFlags(protoBuf$Function.getFlags());
            }
            if (protoBuf$Function.hasOldFlags()) {
                setOldFlags(protoBuf$Function.getOldFlags());
            }
            if (protoBuf$Function.hasName()) {
                setName(protoBuf$Function.getName());
            }
            if (protoBuf$Function.hasReturnType()) {
                mergeReturnType(protoBuf$Function.getReturnType());
            }
            if (protoBuf$Function.hasReturnTypeId()) {
                setReturnTypeId(protoBuf$Function.getReturnTypeId());
            }
            if (!protoBuf$Function.typeParameter_.isEmpty()) {
                if (this.typeParameter_.isEmpty()) {
                    this.typeParameter_ = protoBuf$Function.typeParameter_;
                    this.bitField0_ &= -33;
                } else {
                    ensureTypeParameterIsMutable();
                    this.typeParameter_.addAll(protoBuf$Function.typeParameter_);
                }
            }
            if (protoBuf$Function.hasReceiverType()) {
                mergeReceiverType(protoBuf$Function.getReceiverType());
            }
            if (protoBuf$Function.hasReceiverTypeId()) {
                setReceiverTypeId(protoBuf$Function.getReceiverTypeId());
            }
            if (!protoBuf$Function.contextReceiverType_.isEmpty()) {
                if (this.contextReceiverType_.isEmpty()) {
                    this.contextReceiverType_ = protoBuf$Function.contextReceiverType_;
                    this.bitField0_ &= -257;
                } else {
                    ensureContextReceiverTypeIsMutable();
                    this.contextReceiverType_.addAll(protoBuf$Function.contextReceiverType_);
                }
            }
            if (!protoBuf$Function.contextReceiverTypeId_.isEmpty()) {
                if (this.contextReceiverTypeId_.isEmpty()) {
                    this.contextReceiverTypeId_ = protoBuf$Function.contextReceiverTypeId_;
                    this.bitField0_ &= -513;
                } else {
                    ensureContextReceiverTypeIdIsMutable();
                    this.contextReceiverTypeId_.addAll(protoBuf$Function.contextReceiverTypeId_);
                }
            }
            if (!protoBuf$Function.valueParameter_.isEmpty()) {
                if (this.valueParameter_.isEmpty()) {
                    this.valueParameter_ = protoBuf$Function.valueParameter_;
                    this.bitField0_ &= -1025;
                } else {
                    ensureValueParameterIsMutable();
                    this.valueParameter_.addAll(protoBuf$Function.valueParameter_);
                }
            }
            if (protoBuf$Function.hasTypeTable()) {
                mergeTypeTable(protoBuf$Function.getTypeTable());
            }
            if (!protoBuf$Function.versionRequirement_.isEmpty()) {
                if (this.versionRequirement_.isEmpty()) {
                    this.versionRequirement_ = protoBuf$Function.versionRequirement_;
                    this.bitField0_ &= -4097;
                } else {
                    ensureVersionRequirementIsMutable();
                    this.versionRequirement_.addAll(protoBuf$Function.versionRequirement_);
                }
            }
            if (protoBuf$Function.hasContract()) {
                mergeContract(protoBuf$Function.getContract());
            }
            mergeExtensionFields(protoBuf$Function);
            setUnknownFields(getUnknownFields().concat(protoBuf$Function.unknownFields));
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function.Builder mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream r3, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                kotlin.reflect.jvm.internal.impl.protobuf.Parser r1 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function.PARSER     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function r3 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function) r3     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
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
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function r4 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function) r4     // Catch: java.lang.Throwable -> Lf
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
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function.Builder.mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite):kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function$Builder");
        }

        public Builder setFlags(int i) {
            this.bitField0_ |= 1;
            this.flags_ = i;
            return this;
        }

        public Builder setOldFlags(int i) {
            this.bitField0_ |= 2;
            this.oldFlags_ = i;
            return this;
        }

        public Builder setName(int i) {
            this.bitField0_ |= 4;
            this.name_ = i;
            return this;
        }

        public Builder mergeReturnType(ProtoBuf$Type protoBuf$Type) {
            if ((this.bitField0_ & 8) == 8 && this.returnType_ != ProtoBuf$Type.getDefaultInstance()) {
                this.returnType_ = ProtoBuf$Type.newBuilder(this.returnType_).mergeFrom(protoBuf$Type).buildPartial();
            } else {
                this.returnType_ = protoBuf$Type;
            }
            this.bitField0_ |= 8;
            return this;
        }

        public Builder setReturnTypeId(int i) {
            this.bitField0_ |= 16;
            this.returnTypeId_ = i;
            return this;
        }

        private void ensureTypeParameterIsMutable() {
            if ((this.bitField0_ & 32) != 32) {
                this.typeParameter_ = new ArrayList(this.typeParameter_);
                this.bitField0_ |= 32;
            }
        }

        public Builder mergeReceiverType(ProtoBuf$Type protoBuf$Type) {
            if ((this.bitField0_ & 64) == 64 && this.receiverType_ != ProtoBuf$Type.getDefaultInstance()) {
                this.receiverType_ = ProtoBuf$Type.newBuilder(this.receiverType_).mergeFrom(protoBuf$Type).buildPartial();
            } else {
                this.receiverType_ = protoBuf$Type;
            }
            this.bitField0_ |= 64;
            return this;
        }

        public Builder setReceiverTypeId(int i) {
            this.bitField0_ |= 128;
            this.receiverTypeId_ = i;
            return this;
        }

        private void ensureContextReceiverTypeIsMutable() {
            if ((this.bitField0_ & 256) != 256) {
                this.contextReceiverType_ = new ArrayList(this.contextReceiverType_);
                this.bitField0_ |= 256;
            }
        }

        private void ensureContextReceiverTypeIdIsMutable() {
            if ((this.bitField0_ & 512) != 512) {
                this.contextReceiverTypeId_ = new ArrayList(this.contextReceiverTypeId_);
                this.bitField0_ |= 512;
            }
        }

        private void ensureValueParameterIsMutable() {
            if ((this.bitField0_ & 1024) != 1024) {
                this.valueParameter_ = new ArrayList(this.valueParameter_);
                this.bitField0_ |= 1024;
            }
        }

        public Builder mergeTypeTable(ProtoBuf$TypeTable protoBuf$TypeTable) {
            if ((this.bitField0_ & 2048) == 2048 && this.typeTable_ != ProtoBuf$TypeTable.getDefaultInstance()) {
                this.typeTable_ = ProtoBuf$TypeTable.newBuilder(this.typeTable_).mergeFrom(protoBuf$TypeTable).buildPartial();
            } else {
                this.typeTable_ = protoBuf$TypeTable;
            }
            this.bitField0_ |= 2048;
            return this;
        }

        private void ensureVersionRequirementIsMutable() {
            if ((this.bitField0_ & PKIFailureInfo.certConfirmed) != 4096) {
                this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                this.bitField0_ |= PKIFailureInfo.certConfirmed;
            }
        }

        public Builder mergeContract(ProtoBuf$Contract protoBuf$Contract) {
            if ((this.bitField0_ & 8192) == 8192 && this.contract_ != ProtoBuf$Contract.getDefaultInstance()) {
                this.contract_ = ProtoBuf$Contract.newBuilder(this.contract_).mergeFrom(protoBuf$Contract).buildPartial();
            } else {
                this.contract_ = protoBuf$Contract;
            }
            this.bitField0_ |= 8192;
            return this;
        }
    }
}
