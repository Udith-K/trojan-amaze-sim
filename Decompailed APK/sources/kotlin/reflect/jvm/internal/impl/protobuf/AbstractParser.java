package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.InputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractParser implements Parser {
    private static final ExtensionRegistryLite EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();

    private UninitializedMessageException newUninitializedMessageException(MessageLite messageLite) {
        if (messageLite instanceof AbstractMessageLite) {
            return ((AbstractMessageLite) messageLite).newUninitializedMessageException();
        }
        return new UninitializedMessageException(messageLite);
    }

    private MessageLite checkMessageInitialized(MessageLite messageLite) throws InvalidProtocolBufferException {
        if (messageLite == null || messageLite.isInitialized()) {
            return messageLite;
        }
        throw newUninitializedMessageException(messageLite).asInvalidProtocolBufferException().setUnfinishedMessage(messageLite);
    }

    public MessageLite parsePartialFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(inputStream);
        MessageLite messageLite = (MessageLite) parsePartialFrom(codedInputStreamNewInstance, extensionRegistryLite);
        try {
            codedInputStreamNewInstance.checkLastTagWas(0);
            return messageLite;
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(messageLite);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
    public MessageLite parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return checkMessageInitialized(parsePartialFrom(inputStream, extensionRegistryLite));
    }

    public MessageLite parsePartialDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            int i = inputStream.read();
            if (i == -1) {
                return null;
            }
            return parsePartialFrom(new AbstractMessageLite.Builder.LimitedInputStream(inputStream, CodedInputStream.readRawVarint32(i, inputStream)), extensionRegistryLite);
        } catch (IOException e) {
            throw new InvalidProtocolBufferException(e.getMessage());
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
    public MessageLite parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return checkMessageInitialized(parsePartialDelimitedFrom(inputStream, extensionRegistryLite));
    }
}
