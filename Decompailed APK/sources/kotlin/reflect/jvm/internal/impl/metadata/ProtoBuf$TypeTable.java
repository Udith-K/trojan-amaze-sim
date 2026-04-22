package kotlin.reflect.jvm.internal.impl.metadata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public final class ProtoBuf$TypeTable extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static Parser PARSER = new AbstractParser() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable.1
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
        public ProtoBuf$TypeTable parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return new ProtoBuf$TypeTable(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ProtoBuf$TypeTable defaultInstance;
    private int bitField0_;
    private int firstNullable_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private List type_;
    private final ByteString unknownFields;

    private ProtoBuf$TypeTable(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private ProtoBuf$TypeTable(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }

    public static ProtoBuf$TypeTable getDefaultInstance() {
        return defaultInstance;
    }

    private ProtoBuf$TypeTable(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        initFields();
        ByteString.Output outputNewOutput = ByteString.newOutput();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            if (!z2) {
                                this.type_ = new ArrayList();
                                z2 = true;
                            }
                            this.type_.add(codedInputStream.readMessage(ProtoBuf$Type.PARSER, extensionRegistryLite));
                        } else if (tag != 16) {
                            if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.bitField0_ |= 1;
                            this.firstNullable_ = codedInputStream.readInt32();
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                if (z2) {
                    this.type_ = Collections.unmodifiableList(this.type_);
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
        if (z2) {
            this.type_ = Collections.unmodifiableList(this.type_);
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
        ProtoBuf$TypeTable protoBuf$TypeTable = new ProtoBuf$TypeTable(true);
        defaultInstance = protoBuf$TypeTable;
        protoBuf$TypeTable.initFields();
    }

    public List getTypeList() {
        return this.type_;
    }

    public int getTypeCount() {
        return this.type_.size();
    }

    public ProtoBuf$Type getType(int i) {
        return (ProtoBuf$Type) this.type_.get(i);
    }

    public boolean hasFirstNullable() {
        return (this.bitField0_ & 1) == 1;
    }

    public int getFirstNullable() {
        return this.firstNullable_;
    }

    private void initFields() {
        this.type_ = Collections.emptyList();
        this.firstNullable_ = -1;
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
        for (int i = 0; i < getTypeCount(); i++) {
            if (!getType(i).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        for (int i = 0; i < this.type_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.type_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeInt32(2, this.firstNullable_);
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt32Size = 0;
        for (int i2 = 0; i2 < this.type_.size(); i2++) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(1, (MessageLite) this.type_.get(i2));
        }
        if ((this.bitField0_ & 1) == 1) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(2, this.firstNullable_);
        }
        int size = iComputeInt32Size + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder(ProtoBuf$TypeTable protoBuf$TypeTable) {
        return newBuilder().mergeFrom(protoBuf$TypeTable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public Builder toBuilder() {
        return newBuilder(this);
    }

    public static final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private int bitField0_;
        private List type_ = Collections.emptyList();
        private int firstNullable_ = -1;

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
        public ProtoBuf$TypeTable build() {
            ProtoBuf$TypeTable protoBuf$TypeTableBuildPartial = buildPartial();
            if (protoBuf$TypeTableBuildPartial.isInitialized()) {
                return protoBuf$TypeTableBuildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(protoBuf$TypeTableBuildPartial);
        }

        public ProtoBuf$TypeTable buildPartial() {
            ProtoBuf$TypeTable protoBuf$TypeTable = new ProtoBuf$TypeTable(this);
            int i = this.bitField0_;
            if ((i & 1) == 1) {
                this.type_ = Collections.unmodifiableList(this.type_);
                this.bitField0_ &= -2;
            }
            protoBuf$TypeTable.type_ = this.type_;
            int i2 = (i & 2) != 2 ? 0 : 1;
            protoBuf$TypeTable.firstNullable_ = this.firstNullable_;
            protoBuf$TypeTable.bitField0_ = i2;
            return protoBuf$TypeTable;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
        public Builder mergeFrom(ProtoBuf$TypeTable protoBuf$TypeTable) {
            if (protoBuf$TypeTable == ProtoBuf$TypeTable.getDefaultInstance()) {
                return this;
            }
            if (!protoBuf$TypeTable.type_.isEmpty()) {
                if (this.type_.isEmpty()) {
                    this.type_ = protoBuf$TypeTable.type_;
                    this.bitField0_ &= -2;
                } else {
                    ensureTypeIsMutable();
                    this.type_.addAll(protoBuf$TypeTable.type_);
                }
            }
            if (protoBuf$TypeTable.hasFirstNullable()) {
                setFirstNullable(protoBuf$TypeTable.getFirstNullable());
            }
            setUnknownFields(getUnknownFields().concat(protoBuf$TypeTable.unknownFields));
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable.Builder mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream r3, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                kotlin.reflect.jvm.internal.impl.protobuf.Parser r1 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable.PARSER     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable r3 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable) r3     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
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
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable r4 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable) r4     // Catch: java.lang.Throwable -> Lf
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
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable.Builder.mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite):kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable$Builder");
        }

        private void ensureTypeIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.type_ = new ArrayList(this.type_);
                this.bitField0_ |= 1;
            }
        }

        public Builder setFirstNullable(int i) {
            this.bitField0_ |= 2;
            this.firstNullable_ = i;
            return this;
        }
    }
}
