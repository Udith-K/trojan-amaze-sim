package kotlinx.serialization.json.internal;

import ch.qos.logback.core.CoreConstants;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: StreamingJsonDecoder.kt */
/* JADX INFO: loaded from: classes2.dex */
public class StreamingJsonDecoder extends AbstractDecoder implements JsonDecoder {
    private final JsonConfiguration configuration;
    private int currentIndex;
    private DiscriminatorHolder discriminatorHolder;
    private final JsonElementMarker elementMarker;
    private final Json json;
    public final AbstractJsonLexer lexer;
    private final WriteMode mode;
    private final SerializersModule serializersModule;

    /* JADX INFO: compiled from: StreamingJsonDecoder.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WriteMode.values().length];
            try {
                iArr[WriteMode.LIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WriteMode.MAP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WriteMode.POLY_OBJ.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[WriteMode.OBJ.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Void decodeNull() {
        return null;
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public final Json getJson() {
        return this.json;
    }

    public StreamingJsonDecoder(Json json, WriteMode mode, AbstractJsonLexer lexer, SerialDescriptor descriptor, DiscriminatorHolder discriminatorHolder) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.json = json;
        this.mode = mode;
        this.lexer = lexer;
        this.serializersModule = json.getSerializersModule();
        this.currentIndex = -1;
        this.discriminatorHolder = discriminatorHolder;
        JsonConfiguration configuration = json.getConfiguration();
        this.configuration = configuration;
        this.elementMarker = configuration.getExplicitNulls() ? null : new JsonElementMarker(descriptor);
    }

    /* JADX INFO: compiled from: StreamingJsonDecoder.kt */
    public static final class DiscriminatorHolder {
        public String discriminatorToSkip;

        public DiscriminatorHolder(String str) {
            this.discriminatorToSkip = str;
        }
    }

    private final boolean trySkip(DiscriminatorHolder discriminatorHolder, String str) {
        if (discriminatorHolder == null || !Intrinsics.areEqual(discriminatorHolder.discriminatorToSkip, str)) {
            return false;
        }
        discriminatorHolder.discriminatorToSkip = null;
        return true;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public JsonElement decodeJsonElement() {
        return new JsonTreeReader(this.json.getConfiguration(), this.lexer).read();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Object decodeSerializableValue(DeserializationStrategy deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        try {
            if ((deserializer instanceof AbstractPolymorphicSerializer) && !this.json.getConfiguration().getUseArrayPolymorphism()) {
                String strClassDiscriminator = PolymorphicKt.classDiscriminator(deserializer.getDescriptor(), this.json);
                String strConsumeLeadingMatchingValue = this.lexer.consumeLeadingMatchingValue(strClassDiscriminator, this.configuration.isLenient());
                DeserializationStrategy deserializationStrategyFindPolymorphicSerializerOrNull = strConsumeLeadingMatchingValue != null ? ((AbstractPolymorphicSerializer) deserializer).findPolymorphicSerializerOrNull(this, strConsumeLeadingMatchingValue) : null;
                if (deserializationStrategyFindPolymorphicSerializerOrNull == null) {
                    return PolymorphicKt.decodeSerializableValuePolymorphic(this, deserializer);
                }
                this.discriminatorHolder = new DiscriminatorHolder(strClassDiscriminator);
                return deserializationStrategyFindPolymorphicSerializerOrNull.deserialize(this);
            }
            return deserializer.deserialize(this);
        } catch (MissingFieldException e) {
            throw new MissingFieldException(e.getMissingFields(), e.getMessage() + " at path: " + this.lexer.path.getPath(), e);
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        WriteMode writeModeSwitchMode = WriteModeKt.switchMode(this.json, descriptor);
        this.lexer.path.pushDescriptor(descriptor);
        this.lexer.consumeNextToken(writeModeSwitchMode.begin);
        checkLeadingComma();
        int i = WhenMappings.$EnumSwitchMapping$0[writeModeSwitchMode.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return new StreamingJsonDecoder(this.json, writeModeSwitchMode, this.lexer, descriptor, this.discriminatorHolder);
        }
        return (this.mode == writeModeSwitchMode && this.json.getConfiguration().getExplicitNulls()) ? this : new StreamingJsonDecoder(this.json, writeModeSwitchMode, this.lexer, descriptor, this.discriminatorHolder);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (this.json.getConfiguration().getIgnoreUnknownKeys() && descriptor.getElementsCount() == 0) {
            skipLeftoverElements(descriptor);
        }
        this.lexer.consumeNextToken(this.mode.end);
        this.lexer.path.popDescriptor();
    }

    private final void skipLeftoverElements(SerialDescriptor serialDescriptor) {
        while (decodeElementIndex(serialDescriptor) != -1) {
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        JsonElementMarker jsonElementMarker = this.elementMarker;
        return !(jsonElementMarker != null ? jsonElementMarker.isUnmarkedNull$kotlinx_serialization_json() : false) && this.lexer.tryConsumeNotNull();
    }

    private final void checkLeadingComma() {
        if (this.lexer.peekNextToken() != 4) {
            return;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public Object decodeSerializableElement(SerialDescriptor descriptor, int i, DeserializationStrategy deserializer, Object obj) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        boolean z = this.mode == WriteMode.MAP && (i & 1) == 0;
        if (z) {
            this.lexer.path.resetCurrentMapKey();
        }
        Object objDecodeSerializableElement = super.decodeSerializableElement(descriptor, i, deserializer, obj);
        if (z) {
            this.lexer.path.updateCurrentMapKey(objDecodeSerializableElement);
        }
        return objDecodeSerializableElement;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor descriptor) {
        int iDecodeMapIndex;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        int i = WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()];
        if (i == 2) {
            iDecodeMapIndex = decodeMapIndex();
        } else if (i == 4) {
            iDecodeMapIndex = decodeObjectIndex(descriptor);
        } else {
            iDecodeMapIndex = decodeListIndex();
        }
        if (this.mode != WriteMode.MAP) {
            this.lexer.path.updateDescriptorIndex(iDecodeMapIndex);
        }
        return iDecodeMapIndex;
    }

    private final int decodeMapIndex() {
        int i = this.currentIndex;
        boolean zTryConsumeComma = false;
        boolean z = i % 2 != 0;
        if (!z) {
            this.lexer.consumeNextToken(CoreConstants.COLON_CHAR);
        } else if (i != -1) {
            zTryConsumeComma = this.lexer.tryConsumeComma();
        }
        if (!this.lexer.canConsumeValue()) {
            if (!zTryConsumeComma) {
                return -1;
            }
            AbstractJsonLexer.fail$default(this.lexer, "Expected '}', but had ',' instead", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        if (z) {
            if (this.currentIndex == -1) {
                AbstractJsonLexer abstractJsonLexer = this.lexer;
                int i2 = abstractJsonLexer.currentPosition;
                if (zTryConsumeComma) {
                    AbstractJsonLexer.fail$default(abstractJsonLexer, "Unexpected trailing comma", i2, null, 4, null);
                    throw new KotlinNothingValueException();
                }
            } else {
                AbstractJsonLexer abstractJsonLexer2 = this.lexer;
                int i3 = abstractJsonLexer2.currentPosition;
                if (!zTryConsumeComma) {
                    AbstractJsonLexer.fail$default(abstractJsonLexer2, "Expected comma after the key-value pair", i3, null, 4, null);
                    throw new KotlinNothingValueException();
                }
            }
        }
        int i4 = this.currentIndex + 1;
        this.currentIndex = i4;
        return i4;
    }

    private final boolean coerceInputValue(SerialDescriptor serialDescriptor, int i) {
        String strPeekString;
        Json json = this.json;
        SerialDescriptor elementDescriptor = serialDescriptor.getElementDescriptor(i);
        if (!elementDescriptor.isNullable() && !this.lexer.tryConsumeNotNull()) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || (strPeekString = this.lexer.peekString(this.configuration.isLenient())) == null || JsonNamesMapKt.getJsonNameIndex(elementDescriptor, json, strPeekString) != -3) {
            return false;
        }
        this.lexer.consumeString();
        return true;
    }

    private final int decodeObjectIndex(SerialDescriptor serialDescriptor) {
        boolean zTryConsumeComma;
        boolean zTryConsumeComma2 = this.lexer.tryConsumeComma();
        while (this.lexer.canConsumeValue()) {
            String strDecodeStringKey = decodeStringKey();
            this.lexer.consumeNextToken(CoreConstants.COLON_CHAR);
            int jsonNameIndex = JsonNamesMapKt.getJsonNameIndex(serialDescriptor, this.json, strDecodeStringKey);
            boolean z = false;
            if (jsonNameIndex == -3) {
                z = true;
                zTryConsumeComma = false;
            } else if (this.configuration.getCoerceInputValues() && coerceInputValue(serialDescriptor, jsonNameIndex)) {
                zTryConsumeComma = this.lexer.tryConsumeComma();
            } else {
                JsonElementMarker jsonElementMarker = this.elementMarker;
                if (jsonElementMarker != null) {
                    jsonElementMarker.mark$kotlinx_serialization_json(jsonNameIndex);
                }
                return jsonNameIndex;
            }
            zTryConsumeComma2 = z ? handleUnknown(strDecodeStringKey) : zTryConsumeComma;
        }
        if (zTryConsumeComma2) {
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        JsonElementMarker jsonElementMarker2 = this.elementMarker;
        if (jsonElementMarker2 != null) {
            return jsonElementMarker2.nextUnmarkedIndex$kotlinx_serialization_json();
        }
        return -1;
    }

    private final boolean handleUnknown(String str) {
        if (this.configuration.getIgnoreUnknownKeys() || trySkip(this.discriminatorHolder, str)) {
            this.lexer.skipElement(this.configuration.isLenient());
        } else {
            this.lexer.failOnUnknownKey(str);
        }
        return this.lexer.tryConsumeComma();
    }

    private final int decodeListIndex() {
        boolean zTryConsumeComma = this.lexer.tryConsumeComma();
        if (!this.lexer.canConsumeValue()) {
            if (!zTryConsumeComma) {
                return -1;
            }
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int i = this.currentIndex;
        if (i != -1 && !zTryConsumeComma) {
            AbstractJsonLexer.fail$default(this.lexer, "Expected end of the array or comma", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int i2 = i + 1;
        this.currentIndex = i2;
        return i2;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        if (this.configuration.isLenient()) {
            return this.lexer.consumeBooleanLenient();
        }
        return this.lexer.consumeBoolean();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public byte decodeByte() {
        long jConsumeNumericLiteral = this.lexer.consumeNumericLiteral();
        byte b = (byte) jConsumeNumericLiteral;
        if (jConsumeNumericLiteral == b) {
            return b;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse byte for input '" + jConsumeNumericLiteral + CoreConstants.SINGLE_QUOTE_CHAR, 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public short decodeShort() {
        long jConsumeNumericLiteral = this.lexer.consumeNumericLiteral();
        short s = (short) jConsumeNumericLiteral;
        if (jConsumeNumericLiteral == s) {
            return s;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse short for input '" + jConsumeNumericLiteral + CoreConstants.SINGLE_QUOTE_CHAR, 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeInt() {
        long jConsumeNumericLiteral = this.lexer.consumeNumericLiteral();
        int i = (int) jConsumeNumericLiteral;
        if (jConsumeNumericLiteral == i) {
            return i;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse int for input '" + jConsumeNumericLiteral + CoreConstants.SINGLE_QUOTE_CHAR, 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        return this.lexer.consumeNumericLiteral();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public float decodeFloat() {
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        String strConsumeStringLenient = abstractJsonLexer.consumeStringLenient();
        try {
            float f = Float.parseFloat(strConsumeStringLenient);
            if (this.json.getConfiguration().getAllowSpecialFloatingPointValues() || !(Float.isInfinite(f) || Float.isNaN(f))) {
                return f;
            }
            JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Float.valueOf(f));
            throw new KotlinNothingValueException();
        } catch (IllegalArgumentException unused) {
            AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse type 'float' for input '" + strConsumeStringLenient + CoreConstants.SINGLE_QUOTE_CHAR, 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        String strConsumeStringLenient = abstractJsonLexer.consumeStringLenient();
        try {
            double d = Double.parseDouble(strConsumeStringLenient);
            if (this.json.getConfiguration().getAllowSpecialFloatingPointValues() || !(Double.isInfinite(d) || Double.isNaN(d))) {
                return d;
            }
            JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Double.valueOf(d));
            throw new KotlinNothingValueException();
        } catch (IllegalArgumentException unused) {
            AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse type 'double' for input '" + strConsumeStringLenient + CoreConstants.SINGLE_QUOTE_CHAR, 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        String strConsumeStringLenient = this.lexer.consumeStringLenient();
        if (strConsumeStringLenient.length() != 1) {
            AbstractJsonLexer.fail$default(this.lexer, "Expected single char, but got '" + strConsumeStringLenient + CoreConstants.SINGLE_QUOTE_CHAR, 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        return strConsumeStringLenient.charAt(0);
    }

    private final String decodeStringKey() {
        if (this.configuration.isLenient()) {
            return this.lexer.consumeStringLenientNotNull();
        }
        return this.lexer.consumeKeyString();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        if (this.configuration.isLenient()) {
            return this.lexer.consumeStringLenientNotNull();
        }
        return this.lexer.consumeString();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Decoder decodeInline(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return StreamingJsonEncoderKt.isUnsignedNumber(descriptor) ? new JsonDecoderForUnsignedTypes(this.lexer, this.json) : super.decodeInline(descriptor);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeEnum(SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        return JsonNamesMapKt.getJsonNameIndexOrThrow(enumDescriptor, this.json, decodeString(), " at path " + this.lexer.path.getPath());
    }
}
