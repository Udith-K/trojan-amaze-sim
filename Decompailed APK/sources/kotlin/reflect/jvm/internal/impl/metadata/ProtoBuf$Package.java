package kotlin.reflect.jvm.internal.impl.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable;
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

/* JADX INFO: loaded from: classes2.dex */
public final class ProtoBuf$Package extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static Parser PARSER = new AbstractParser() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package.1
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
        public ProtoBuf$Package parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return new ProtoBuf$Package(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ProtoBuf$Package defaultInstance;
    private int bitField0_;
    private List function_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private List property_;
    private List typeAlias_;
    private ProtoBuf$TypeTable typeTable_;
    private final ByteString unknownFields;
    private ProtoBuf$VersionRequirementTable versionRequirementTable_;

    private ProtoBuf$Package(GeneratedMessageLite.ExtendableBuilder extendableBuilder) {
        super(extendableBuilder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = extendableBuilder.getUnknownFields();
    }

    private ProtoBuf$Package(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }

    public static ProtoBuf$Package getDefaultInstance() {
        return defaultInstance;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
    public ProtoBuf$Package getDefaultInstanceForType() {
        return defaultInstance;
    }

    private ProtoBuf$Package(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        initFields();
        ByteString.Output outputNewOutput = ByteString.newOutput();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 26) {
                            int i2 = (i == true ? 1 : 0) & 1;
                            i = i;
                            if (i2 != 1) {
                                this.function_ = new ArrayList();
                                i = (i == true ? 1 : 0) | 1;
                            }
                            this.function_.add(codedInputStream.readMessage(ProtoBuf$Function.PARSER, extensionRegistryLite));
                        } else if (tag == 34) {
                            int i3 = (i == true ? 1 : 0) & 2;
                            i = i;
                            if (i3 != 2) {
                                this.property_ = new ArrayList();
                                i = (i == true ? 1 : 0) | 2;
                            }
                            this.property_.add(codedInputStream.readMessage(ProtoBuf$Property.PARSER, extensionRegistryLite));
                        } else if (tag != 42) {
                            if (tag == 242) {
                                ProtoBuf$TypeTable.Builder builder = (this.bitField0_ & 1) == 1 ? this.typeTable_.toBuilder() : null;
                                ProtoBuf$TypeTable protoBuf$TypeTable = (ProtoBuf$TypeTable) codedInputStream.readMessage(ProtoBuf$TypeTable.PARSER, extensionRegistryLite);
                                this.typeTable_ = protoBuf$TypeTable;
                                if (builder != null) {
                                    builder.mergeFrom(protoBuf$TypeTable);
                                    this.typeTable_ = builder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag != 258) {
                                if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                                }
                            } else {
                                ProtoBuf$VersionRequirementTable.Builder builder2 = (this.bitField0_ & 2) == 2 ? this.versionRequirementTable_.toBuilder() : null;
                                ProtoBuf$VersionRequirementTable protoBuf$VersionRequirementTable = (ProtoBuf$VersionRequirementTable) codedInputStream.readMessage(ProtoBuf$VersionRequirementTable.PARSER, extensionRegistryLite);
                                this.versionRequirementTable_ = protoBuf$VersionRequirementTable;
                                if (builder2 != null) {
                                    builder2.mergeFrom(protoBuf$VersionRequirementTable);
                                    this.versionRequirementTable_ = builder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            }
                        } else {
                            int i4 = (i == true ? 1 : 0) & 4;
                            i = i;
                            if (i4 != 4) {
                                this.typeAlias_ = new ArrayList();
                                i = (i == true ? 1 : 0) | 4;
                            }
                            this.typeAlias_.add(codedInputStream.readMessage(ProtoBuf$TypeAlias.PARSER, extensionRegistryLite));
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                if (((i == true ? 1 : 0) & 1) == 1) {
                    this.function_ = Collections.unmodifiableList(this.function_);
                }
                if (((i == true ? 1 : 0) & 2) == 2) {
                    this.property_ = Collections.unmodifiableList(this.property_);
                }
                if (((i == true ? 1 : 0) & 4) == 4) {
                    this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
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
        if (((i == true ? 1 : 0) & 1) == 1) {
            this.function_ = Collections.unmodifiableList(this.function_);
        }
        if (((i == true ? 1 : 0) & 2) == 2) {
            this.property_ = Collections.unmodifiableList(this.property_);
        }
        if (((i == true ? 1 : 0) & 4) == 4) {
            this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
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
        ProtoBuf$Package protoBuf$Package = new ProtoBuf$Package(true);
        defaultInstance = protoBuf$Package;
        protoBuf$Package.initFields();
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

    public boolean hasTypeTable() {
        return (this.bitField0_ & 1) == 1;
    }

    public ProtoBuf$TypeTable getTypeTable() {
        return this.typeTable_;
    }

    public boolean hasVersionRequirementTable() {
        return (this.bitField0_ & 2) == 2;
    }

    public ProtoBuf$VersionRequirementTable getVersionRequirementTable() {
        return this.versionRequirementTable_;
    }

    private void initFields() {
        this.function_ = Collections.emptyList();
        this.property_ = Collections.emptyList();
        this.typeAlias_ = Collections.emptyList();
        this.typeTable_ = ProtoBuf$TypeTable.getDefaultInstance();
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
        for (int i = 0; i < getFunctionCount(); i++) {
            if (!getFunction(i).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        for (int i2 = 0; i2 < getPropertyCount(); i2++) {
            if (!getProperty(i2).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        for (int i3 = 0; i3 < getTypeAliasCount(); i3++) {
            if (!getTypeAlias(i3).isInitialized()) {
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
        for (int i = 0; i < this.function_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.function_.get(i));
        }
        for (int i2 = 0; i2 < this.property_.size(); i2++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.property_.get(i2));
        }
        for (int i3 = 0; i3 < this.typeAlias_.size(); i3++) {
            codedOutputStream.writeMessage(5, (MessageLite) this.typeAlias_.get(i3));
        }
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeMessage(30, this.typeTable_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeMessage(32, this.versionRequirementTable_);
        }
        extensionWriterNewExtensionWriter.writeUntil(200, codedOutputStream);
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.function_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, (MessageLite) this.function_.get(i2));
        }
        for (int i3 = 0; i3 < this.property_.size(); i3++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, (MessageLite) this.property_.get(i3));
        }
        for (int i4 = 0; i4 < this.typeAlias_.size(); i4++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, (MessageLite) this.typeAlias_.get(i4));
        }
        if ((this.bitField0_ & 1) == 1) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(30, this.typeTable_);
        }
        if ((this.bitField0_ & 2) == 2) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(32, this.versionRequirementTable_);
        }
        int iExtensionsSerializedSize = iComputeMessageSize + extensionsSerializedSize() + this.unknownFields.size();
        this.memoizedSerializedSize = iExtensionsSerializedSize;
        return iExtensionsSerializedSize;
    }

    public static ProtoBuf$Package parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ProtoBuf$Package) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder(ProtoBuf$Package protoBuf$Package) {
        return newBuilder().mergeFrom(protoBuf$Package);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public Builder toBuilder() {
        return newBuilder(this);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder implements MessageLiteOrBuilder {
        private int bitField0_;
        private List function_ = Collections.emptyList();
        private List property_ = Collections.emptyList();
        private List typeAlias_ = Collections.emptyList();
        private ProtoBuf$TypeTable typeTable_ = ProtoBuf$TypeTable.getDefaultInstance();
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
        public ProtoBuf$Package build() {
            ProtoBuf$Package protoBuf$PackageBuildPartial = buildPartial();
            if (protoBuf$PackageBuildPartial.isInitialized()) {
                return protoBuf$PackageBuildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(protoBuf$PackageBuildPartial);
        }

        public ProtoBuf$Package buildPartial() {
            ProtoBuf$Package protoBuf$Package = new ProtoBuf$Package(this);
            int i = this.bitField0_;
            if ((i & 1) == 1) {
                this.function_ = Collections.unmodifiableList(this.function_);
                this.bitField0_ &= -2;
            }
            protoBuf$Package.function_ = this.function_;
            if ((this.bitField0_ & 2) == 2) {
                this.property_ = Collections.unmodifiableList(this.property_);
                this.bitField0_ &= -3;
            }
            protoBuf$Package.property_ = this.property_;
            if ((this.bitField0_ & 4) == 4) {
                this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                this.bitField0_ &= -5;
            }
            protoBuf$Package.typeAlias_ = this.typeAlias_;
            int i2 = (i & 8) != 8 ? 0 : 1;
            protoBuf$Package.typeTable_ = this.typeTable_;
            if ((i & 16) == 16) {
                i2 |= 2;
            }
            protoBuf$Package.versionRequirementTable_ = this.versionRequirementTable_;
            protoBuf$Package.bitField0_ = i2;
            return protoBuf$Package;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
        public Builder mergeFrom(ProtoBuf$Package protoBuf$Package) {
            if (protoBuf$Package == ProtoBuf$Package.getDefaultInstance()) {
                return this;
            }
            if (!protoBuf$Package.function_.isEmpty()) {
                if (this.function_.isEmpty()) {
                    this.function_ = protoBuf$Package.function_;
                    this.bitField0_ &= -2;
                } else {
                    ensureFunctionIsMutable();
                    this.function_.addAll(protoBuf$Package.function_);
                }
            }
            if (!protoBuf$Package.property_.isEmpty()) {
                if (this.property_.isEmpty()) {
                    this.property_ = protoBuf$Package.property_;
                    this.bitField0_ &= -3;
                } else {
                    ensurePropertyIsMutable();
                    this.property_.addAll(protoBuf$Package.property_);
                }
            }
            if (!protoBuf$Package.typeAlias_.isEmpty()) {
                if (this.typeAlias_.isEmpty()) {
                    this.typeAlias_ = protoBuf$Package.typeAlias_;
                    this.bitField0_ &= -5;
                } else {
                    ensureTypeAliasIsMutable();
                    this.typeAlias_.addAll(protoBuf$Package.typeAlias_);
                }
            }
            if (protoBuf$Package.hasTypeTable()) {
                mergeTypeTable(protoBuf$Package.getTypeTable());
            }
            if (protoBuf$Package.hasVersionRequirementTable()) {
                mergeVersionRequirementTable(protoBuf$Package.getVersionRequirementTable());
            }
            mergeExtensionFields(protoBuf$Package);
            setUnknownFields(getUnknownFields().concat(protoBuf$Package.unknownFields));
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package.Builder mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream r3, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                kotlin.reflect.jvm.internal.impl.protobuf.Parser r1 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package.PARSER     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package r3 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package) r3     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
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
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package r4 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package) r4     // Catch: java.lang.Throwable -> Lf
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
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package.Builder.mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite):kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package$Builder");
        }

        private void ensureFunctionIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.function_ = new ArrayList(this.function_);
                this.bitField0_ |= 1;
            }
        }

        private void ensurePropertyIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.property_ = new ArrayList(this.property_);
                this.bitField0_ |= 2;
            }
        }

        private void ensureTypeAliasIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.typeAlias_ = new ArrayList(this.typeAlias_);
                this.bitField0_ |= 4;
            }
        }

        public Builder mergeTypeTable(ProtoBuf$TypeTable protoBuf$TypeTable) {
            if ((this.bitField0_ & 8) == 8 && this.typeTable_ != ProtoBuf$TypeTable.getDefaultInstance()) {
                this.typeTable_ = ProtoBuf$TypeTable.newBuilder(this.typeTable_).mergeFrom(protoBuf$TypeTable).buildPartial();
            } else {
                this.typeTable_ = protoBuf$TypeTable;
            }
            this.bitField0_ |= 8;
            return this;
        }

        public Builder mergeVersionRequirementTable(ProtoBuf$VersionRequirementTable protoBuf$VersionRequirementTable) {
            if ((this.bitField0_ & 16) == 16 && this.versionRequirementTable_ != ProtoBuf$VersionRequirementTable.getDefaultInstance()) {
                this.versionRequirementTable_ = ProtoBuf$VersionRequirementTable.newBuilder(this.versionRequirementTable_).mergeFrom(protoBuf$VersionRequirementTable).buildPartial();
            } else {
                this.versionRequirementTable_ = protoBuf$VersionRequirementTable;
            }
            this.bitField0_ |= 16;
            return this;
        }
    }
}
