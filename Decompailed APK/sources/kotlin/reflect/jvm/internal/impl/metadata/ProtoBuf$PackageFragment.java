package kotlin.reflect.jvm.internal.impl.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable;
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
public final class ProtoBuf$PackageFragment extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static Parser PARSER = new AbstractParser() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$PackageFragment.1
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
        public ProtoBuf$PackageFragment parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return new ProtoBuf$PackageFragment(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ProtoBuf$PackageFragment defaultInstance;
    private int bitField0_;
    private List class__;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private ProtoBuf$Package package_;
    private ProtoBuf$QualifiedNameTable qualifiedNames_;
    private ProtoBuf$StringTable strings_;
    private final ByteString unknownFields;

    private ProtoBuf$PackageFragment(GeneratedMessageLite.ExtendableBuilder extendableBuilder) {
        super(extendableBuilder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = extendableBuilder.getUnknownFields();
    }

    private ProtoBuf$PackageFragment(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }

    public static ProtoBuf$PackageFragment getDefaultInstance() {
        return defaultInstance;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
    public ProtoBuf$PackageFragment getDefaultInstanceForType() {
        return defaultInstance;
    }

    private ProtoBuf$PackageFragment(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        initFields();
        ByteString.Output outputNewOutput = ByteString.newOutput();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
        boolean z = false;
        char c = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            ProtoBuf$StringTable.Builder builder = (this.bitField0_ & 1) == 1 ? this.strings_.toBuilder() : null;
                            ProtoBuf$StringTable protoBuf$StringTable = (ProtoBuf$StringTable) codedInputStream.readMessage(ProtoBuf$StringTable.PARSER, extensionRegistryLite);
                            this.strings_ = protoBuf$StringTable;
                            if (builder != null) {
                                builder.mergeFrom(protoBuf$StringTable);
                                this.strings_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            ProtoBuf$QualifiedNameTable.Builder builder2 = (this.bitField0_ & 2) == 2 ? this.qualifiedNames_.toBuilder() : null;
                            ProtoBuf$QualifiedNameTable protoBuf$QualifiedNameTable = (ProtoBuf$QualifiedNameTable) codedInputStream.readMessage(ProtoBuf$QualifiedNameTable.PARSER, extensionRegistryLite);
                            this.qualifiedNames_ = protoBuf$QualifiedNameTable;
                            if (builder2 != null) {
                                builder2.mergeFrom(protoBuf$QualifiedNameTable);
                                this.qualifiedNames_ = builder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            ProtoBuf$Package.Builder builder3 = (this.bitField0_ & 4) == 4 ? this.package_.toBuilder() : null;
                            ProtoBuf$Package protoBuf$Package = (ProtoBuf$Package) codedInputStream.readMessage(ProtoBuf$Package.PARSER, extensionRegistryLite);
                            this.package_ = protoBuf$Package;
                            if (builder3 != null) {
                                builder3.mergeFrom(protoBuf$Package);
                                this.package_ = builder3.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag != 34) {
                            if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                            }
                        } else {
                            int i = (c == true ? 1 : 0) & '\b';
                            c = c;
                            if (i != 8) {
                                this.class__ = new ArrayList();
                                c = '\b';
                            }
                            this.class__.add(codedInputStream.readMessage(ProtoBuf$Class.PARSER, extensionRegistryLite));
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                if (((c == true ? 1 : 0) & '\b') == 8) {
                    this.class__ = Collections.unmodifiableList(this.class__);
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
        if (((c == true ? 1 : 0) & '\b') == 8) {
            this.class__ = Collections.unmodifiableList(this.class__);
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
        ProtoBuf$PackageFragment protoBuf$PackageFragment = new ProtoBuf$PackageFragment(true);
        defaultInstance = protoBuf$PackageFragment;
        protoBuf$PackageFragment.initFields();
    }

    public boolean hasStrings() {
        return (this.bitField0_ & 1) == 1;
    }

    public ProtoBuf$StringTable getStrings() {
        return this.strings_;
    }

    public boolean hasQualifiedNames() {
        return (this.bitField0_ & 2) == 2;
    }

    public ProtoBuf$QualifiedNameTable getQualifiedNames() {
        return this.qualifiedNames_;
    }

    public boolean hasPackage() {
        return (this.bitField0_ & 4) == 4;
    }

    public ProtoBuf$Package getPackage() {
        return this.package_;
    }

    public List getClass_List() {
        return this.class__;
    }

    public int getClass_Count() {
        return this.class__.size();
    }

    public ProtoBuf$Class getClass_(int i) {
        return (ProtoBuf$Class) this.class__.get(i);
    }

    private void initFields() {
        this.strings_ = ProtoBuf$StringTable.getDefaultInstance();
        this.qualifiedNames_ = ProtoBuf$QualifiedNameTable.getDefaultInstance();
        this.package_ = ProtoBuf$Package.getDefaultInstance();
        this.class__ = Collections.emptyList();
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
        if (hasQualifiedNames() && !getQualifiedNames().isInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        if (hasPackage() && !getPackage().isInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        for (int i = 0; i < getClass_Count(); i++) {
            if (!getClass_(i).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
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
            codedOutputStream.writeMessage(1, this.strings_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeMessage(2, this.qualifiedNames_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeMessage(3, this.package_);
        }
        for (int i = 0; i < this.class__.size(); i++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.class__.get(i));
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
        int iComputeMessageSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeMessageSize(1, this.strings_) : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, this.qualifiedNames_);
        }
        if ((this.bitField0_ & 4) == 4) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, this.package_);
        }
        for (int i2 = 0; i2 < this.class__.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, (MessageLite) this.class__.get(i2));
        }
        int iExtensionsSerializedSize = iComputeMessageSize + extensionsSerializedSize() + this.unknownFields.size();
        this.memoizedSerializedSize = iExtensionsSerializedSize;
        return iExtensionsSerializedSize;
    }

    public static ProtoBuf$PackageFragment parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ProtoBuf$PackageFragment) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder(ProtoBuf$PackageFragment protoBuf$PackageFragment) {
        return newBuilder().mergeFrom(protoBuf$PackageFragment);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public Builder toBuilder() {
        return newBuilder(this);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder implements MessageLiteOrBuilder {
        private int bitField0_;
        private ProtoBuf$StringTable strings_ = ProtoBuf$StringTable.getDefaultInstance();
        private ProtoBuf$QualifiedNameTable qualifiedNames_ = ProtoBuf$QualifiedNameTable.getDefaultInstance();
        private ProtoBuf$Package package_ = ProtoBuf$Package.getDefaultInstance();
        private List class__ = Collections.emptyList();

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
        public ProtoBuf$PackageFragment build() {
            ProtoBuf$PackageFragment protoBuf$PackageFragmentBuildPartial = buildPartial();
            if (protoBuf$PackageFragmentBuildPartial.isInitialized()) {
                return protoBuf$PackageFragmentBuildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(protoBuf$PackageFragmentBuildPartial);
        }

        public ProtoBuf$PackageFragment buildPartial() {
            ProtoBuf$PackageFragment protoBuf$PackageFragment = new ProtoBuf$PackageFragment(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            protoBuf$PackageFragment.strings_ = this.strings_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            protoBuf$PackageFragment.qualifiedNames_ = this.qualifiedNames_;
            if ((i & 4) == 4) {
                i2 |= 4;
            }
            protoBuf$PackageFragment.package_ = this.package_;
            if ((this.bitField0_ & 8) == 8) {
                this.class__ = Collections.unmodifiableList(this.class__);
                this.bitField0_ &= -9;
            }
            protoBuf$PackageFragment.class__ = this.class__;
            protoBuf$PackageFragment.bitField0_ = i2;
            return protoBuf$PackageFragment;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
        public Builder mergeFrom(ProtoBuf$PackageFragment protoBuf$PackageFragment) {
            if (protoBuf$PackageFragment == ProtoBuf$PackageFragment.getDefaultInstance()) {
                return this;
            }
            if (protoBuf$PackageFragment.hasStrings()) {
                mergeStrings(protoBuf$PackageFragment.getStrings());
            }
            if (protoBuf$PackageFragment.hasQualifiedNames()) {
                mergeQualifiedNames(protoBuf$PackageFragment.getQualifiedNames());
            }
            if (protoBuf$PackageFragment.hasPackage()) {
                mergePackage(protoBuf$PackageFragment.getPackage());
            }
            if (!protoBuf$PackageFragment.class__.isEmpty()) {
                if (this.class__.isEmpty()) {
                    this.class__ = protoBuf$PackageFragment.class__;
                    this.bitField0_ &= -9;
                } else {
                    ensureClass_IsMutable();
                    this.class__.addAll(protoBuf$PackageFragment.class__);
                }
            }
            mergeExtensionFields(protoBuf$PackageFragment);
            setUnknownFields(getUnknownFields().concat(protoBuf$PackageFragment.unknownFields));
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$PackageFragment.Builder mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream r3, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                kotlin.reflect.jvm.internal.impl.protobuf.Parser r1 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$PackageFragment.PARSER     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$PackageFragment r3 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$PackageFragment) r3     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
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
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$PackageFragment r4 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$PackageFragment) r4     // Catch: java.lang.Throwable -> Lf
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
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$PackageFragment.Builder.mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite):kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$PackageFragment$Builder");
        }

        public Builder mergeStrings(ProtoBuf$StringTable protoBuf$StringTable) {
            if ((this.bitField0_ & 1) == 1 && this.strings_ != ProtoBuf$StringTable.getDefaultInstance()) {
                this.strings_ = ProtoBuf$StringTable.newBuilder(this.strings_).mergeFrom(protoBuf$StringTable).buildPartial();
            } else {
                this.strings_ = protoBuf$StringTable;
            }
            this.bitField0_ |= 1;
            return this;
        }

        public Builder mergeQualifiedNames(ProtoBuf$QualifiedNameTable protoBuf$QualifiedNameTable) {
            if ((this.bitField0_ & 2) == 2 && this.qualifiedNames_ != ProtoBuf$QualifiedNameTable.getDefaultInstance()) {
                this.qualifiedNames_ = ProtoBuf$QualifiedNameTable.newBuilder(this.qualifiedNames_).mergeFrom(protoBuf$QualifiedNameTable).buildPartial();
            } else {
                this.qualifiedNames_ = protoBuf$QualifiedNameTable;
            }
            this.bitField0_ |= 2;
            return this;
        }

        public Builder mergePackage(ProtoBuf$Package protoBuf$Package) {
            if ((this.bitField0_ & 4) == 4 && this.package_ != ProtoBuf$Package.getDefaultInstance()) {
                this.package_ = ProtoBuf$Package.newBuilder(this.package_).mergeFrom(protoBuf$Package).buildPartial();
            } else {
                this.package_ = protoBuf$Package;
            }
            this.bitField0_ |= 4;
            return this;
        }

        private void ensureClass_IsMutable() {
            if ((this.bitField0_ & 8) != 8) {
                this.class__ = new ArrayList(this.class__);
                this.bitField0_ |= 8;
            }
        }
    }
}
