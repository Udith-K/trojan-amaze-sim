package kotlinx.serialization.json.internal;

import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AbstractJsonLexer.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractJsonLexer {
    protected int currentPosition;
    private String peekedString;
    public final JsonPath path = new JsonPath();
    private StringBuilder escapedString = new StringBuilder();

    public abstract boolean canConsumeValue();

    public abstract String consumeKeyString();

    public abstract String consumeLeadingMatchingValue(String str, boolean z);

    public abstract byte consumeNextToken();

    public void ensureHaveChars() {
    }

    protected abstract CharSequence getSource();

    protected final boolean isValidValueStart(char c) {
        return !(c == '}' || c == ']' || c == ':' || c == ',');
    }

    public abstract int prefetchOrEof(int i);

    public abstract boolean tryConsumeComma();

    public final void expectEof() {
        if (consumeNextToken() == 10) {
            return;
        }
        fail$default(this, "Expected EOF after parsing, but had " + getSource().charAt(this.currentPosition - 1) + " instead", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    protected final StringBuilder getEscapedString() {
        return this.escapedString;
    }

    public final byte consumeNextToken(byte b) {
        byte bConsumeNextToken = consumeNextToken();
        if (bConsumeNextToken == b) {
            return bConsumeNextToken;
        }
        fail$kotlinx_serialization_json(b);
        throw new KotlinNothingValueException();
    }

    public void consumeNextToken(char c) {
        ensureHaveChars();
        CharSequence source = getSource();
        int i = this.currentPosition;
        while (true) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof != -1) {
                int i2 = iPrefetchOrEof + 1;
                char cCharAt = source.charAt(iPrefetchOrEof);
                if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                    this.currentPosition = i2;
                    if (cCharAt == c) {
                        return;
                    } else {
                        unexpectedToken(c);
                    }
                }
                i = i2;
            } else {
                this.currentPosition = iPrefetchOrEof;
                unexpectedToken(c);
                return;
            }
        }
    }

    protected final void unexpectedToken(char c) {
        int i = this.currentPosition - 1;
        this.currentPosition = i;
        if (i >= 0 && c == '\"' && Intrinsics.areEqual(consumeStringLenient(), "null")) {
            fail("Expected string literal but 'null' literal was found", this.currentPosition - 4, "Use 'coerceInputValues = true' in 'Json {}` builder to coerce nulls to default values.");
            throw new KotlinNothingValueException();
        }
        fail$kotlinx_serialization_json(AbstractJsonLexerKt.charToTokenClass(c));
        throw new KotlinNothingValueException();
    }

    public final Void fail$kotlinx_serialization_json(byte b) {
        String str;
        if (b == 1) {
            str = "quotation mark '\"'";
        } else if (b == 4) {
            str = "comma ','";
        } else if (b == 5) {
            str = "colon ':'";
        } else if (b == 6) {
            str = "start of the object '{'";
        } else if (b == 7) {
            str = "end of the object '}'";
        } else if (b == 8) {
            str = "start of the array '['";
        } else if (b == 9) {
            str = "end of the array ']'";
        } else {
            str = "valid token";
        }
        fail$default(this, "Expected " + str + ", but had '" + ((this.currentPosition == getSource().length() || this.currentPosition <= 0) ? "EOF" : String.valueOf(getSource().charAt(this.currentPosition - 1))) + "' instead", this.currentPosition - 1, null, 4, null);
        throw new KotlinNothingValueException();
    }

    public final byte peekNextToken() {
        CharSequence source = getSource();
        int i = this.currentPosition;
        while (true) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof != -1) {
                char cCharAt = source.charAt(iPrefetchOrEof);
                if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                    this.currentPosition = iPrefetchOrEof;
                    return AbstractJsonLexerKt.charToTokenClass(cCharAt);
                }
                i = iPrefetchOrEof + 1;
            } else {
                this.currentPosition = iPrefetchOrEof;
                return (byte) 10;
            }
        }
    }

    public final boolean tryConsumeNotNull() {
        int iPrefetchOrEof = prefetchOrEof(skipWhitespaces());
        int length = getSource().length() - iPrefetchOrEof;
        if (length < 4 || iPrefetchOrEof == -1) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            if ("null".charAt(i) != getSource().charAt(iPrefetchOrEof + i)) {
                return true;
            }
        }
        if (length > 4 && AbstractJsonLexerKt.charToTokenClass(getSource().charAt(iPrefetchOrEof + 4)) == 0) {
            return true;
        }
        this.currentPosition = iPrefetchOrEof + 4;
        return false;
    }

    public int skipWhitespaces() {
        int iPrefetchOrEof;
        char cCharAt;
        int i = this.currentPosition;
        while (true) {
            iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof == -1 || !((cCharAt = getSource().charAt(iPrefetchOrEof)) == ' ' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == '\t')) {
                break;
            }
            i = iPrefetchOrEof + 1;
        }
        this.currentPosition = iPrefetchOrEof;
        return iPrefetchOrEof;
    }

    public final String peekString(boolean z) {
        String strConsumeString;
        byte bPeekNextToken = peekNextToken();
        if (z) {
            if (bPeekNextToken != 1 && bPeekNextToken != 0) {
                return null;
            }
            strConsumeString = consumeStringLenient();
        } else {
            if (bPeekNextToken != 1) {
                return null;
            }
            strConsumeString = consumeString();
        }
        this.peekedString = strConsumeString;
        return strConsumeString;
    }

    public String substring(int i, int i2) {
        return getSource().subSequence(i, i2).toString();
    }

    public final String consumeString() {
        if (this.peekedString != null) {
            return takePeeked();
        }
        return consumeKeyString();
    }

    protected final String consumeString(CharSequence source, int i, int i2) {
        String strDecodedString;
        int iPrefetchOrEof;
        Intrinsics.checkNotNullParameter(source, "source");
        char cCharAt = source.charAt(i2);
        boolean z = false;
        while (cCharAt != '\"') {
            if (cCharAt == '\\') {
                iPrefetchOrEof = prefetchOrEof(appendEscape(i, i2));
                if (iPrefetchOrEof == -1) {
                    fail$default(this, "EOF", iPrefetchOrEof, null, 4, null);
                    throw new KotlinNothingValueException();
                }
            } else {
                i2++;
                if (i2 >= source.length()) {
                    appendRange(i, i2);
                    iPrefetchOrEof = prefetchOrEof(i2);
                    if (iPrefetchOrEof == -1) {
                        fail$default(this, "EOF", iPrefetchOrEof, null, 4, null);
                        throw new KotlinNothingValueException();
                    }
                } else {
                    continue;
                    cCharAt = source.charAt(i2);
                }
            }
            z = true;
            i = iPrefetchOrEof;
            i2 = i;
            cCharAt = source.charAt(i2);
        }
        if (!z) {
            strDecodedString = substring(i, i2);
        } else {
            strDecodedString = decodedString(i, i2);
        }
        this.currentPosition = i2 + 1;
        return strDecodedString;
    }

    private final int appendEscape(int i, int i2) {
        appendRange(i, i2);
        return appendEsc(i2 + 1);
    }

    private final String decodedString(int i, int i2) {
        appendRange(i, i2);
        String string = this.escapedString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "escapedString.toString()");
        this.escapedString.setLength(0);
        return string;
    }

    private final String takePeeked() {
        String str = this.peekedString;
        Intrinsics.checkNotNull(str);
        this.peekedString = null;
        return str;
    }

    public final String consumeStringLenientNotNull() {
        String strConsumeStringLenient = consumeStringLenient();
        if (!Intrinsics.areEqual(strConsumeStringLenient, "null") || !wasUnquotedString()) {
            return strConsumeStringLenient;
        }
        fail$default(this, "Unexpected 'null' value instead of string literal", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    private final boolean wasUnquotedString() {
        return getSource().charAt(this.currentPosition - 1) != '\"';
    }

    public final String consumeStringLenient() {
        String strDecodedString;
        if (this.peekedString != null) {
            return takePeeked();
        }
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces >= getSource().length() || iSkipWhitespaces == -1) {
            fail$default(this, "EOF", iSkipWhitespaces, null, 4, null);
            throw new KotlinNothingValueException();
        }
        byte bCharToTokenClass = AbstractJsonLexerKt.charToTokenClass(getSource().charAt(iSkipWhitespaces));
        if (bCharToTokenClass == 1) {
            return consumeString();
        }
        if (bCharToTokenClass != 0) {
            fail$default(this, "Expected beginning of the string, but got " + getSource().charAt(iSkipWhitespaces), 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        boolean z = false;
        while (AbstractJsonLexerKt.charToTokenClass(getSource().charAt(iSkipWhitespaces)) == 0) {
            iSkipWhitespaces++;
            if (iSkipWhitespaces >= getSource().length()) {
                appendRange(this.currentPosition, iSkipWhitespaces);
                int iPrefetchOrEof = prefetchOrEof(iSkipWhitespaces);
                if (iPrefetchOrEof == -1) {
                    this.currentPosition = iSkipWhitespaces;
                    return decodedString(0, 0);
                }
                iSkipWhitespaces = iPrefetchOrEof;
                z = true;
            }
        }
        if (!z) {
            strDecodedString = substring(this.currentPosition, iSkipWhitespaces);
        } else {
            strDecodedString = decodedString(this.currentPosition, iSkipWhitespaces);
        }
        this.currentPosition = iSkipWhitespaces;
        return strDecodedString;
    }

    protected void appendRange(int i, int i2) {
        this.escapedString.append(getSource(), i, i2);
    }

    private final int appendEsc(int i) {
        int iPrefetchOrEof = prefetchOrEof(i);
        if (iPrefetchOrEof == -1) {
            fail$default(this, "Expected escape sequence to continue, got EOF", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int i2 = iPrefetchOrEof + 1;
        char cCharAt = getSource().charAt(iPrefetchOrEof);
        if (cCharAt == 'u') {
            return appendHex(getSource(), i2);
        }
        char cEscapeToChar = AbstractJsonLexerKt.escapeToChar(cCharAt);
        if (cEscapeToChar == 0) {
            fail$default(this, "Invalid escaped char '" + cCharAt + CoreConstants.SINGLE_QUOTE_CHAR, 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        this.escapedString.append(cEscapeToChar);
        return i2;
    }

    private final int appendHex(CharSequence charSequence, int i) {
        int i2 = i + 4;
        if (i2 >= charSequence.length()) {
            this.currentPosition = i;
            ensureHaveChars();
            if (this.currentPosition + 4 >= charSequence.length()) {
                fail$default(this, "Unexpected EOF during unicode escape", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            return appendHex(charSequence, this.currentPosition);
        }
        this.escapedString.append((char) ((fromHexChar(charSequence, i) << 12) + (fromHexChar(charSequence, i + 1) << 8) + (fromHexChar(charSequence, i + 2) << 4) + fromHexChar(charSequence, i + 3)));
        return i2;
    }

    private final int fromHexChar(CharSequence charSequence, int i) {
        char cCharAt = charSequence.charAt(i);
        if ('0' <= cCharAt && cCharAt < ':') {
            return cCharAt - '0';
        }
        if ('a' <= cCharAt && cCharAt < 'g') {
            return cCharAt - 'W';
        }
        if ('A' <= cCharAt && cCharAt < 'G') {
            return cCharAt - '7';
        }
        fail$default(this, "Invalid toHexChar char '" + cCharAt + "' in unicode escape", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    public final void skipElement(boolean z) {
        ArrayList arrayList = new ArrayList();
        byte bPeekNextToken = peekNextToken();
        if (bPeekNextToken != 8 && bPeekNextToken != 6) {
            consumeStringLenient();
            return;
        }
        while (true) {
            byte bPeekNextToken2 = peekNextToken();
            if (bPeekNextToken2 != 1) {
                if (bPeekNextToken2 == 8 || bPeekNextToken2 == 6) {
                    arrayList.add(Byte.valueOf(bPeekNextToken2));
                } else if (bPeekNextToken2 == 9) {
                    if (((Number) CollectionsKt.last((List) arrayList)).byteValue() != 8) {
                        throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found ] instead of } at path: " + this.path, getSource());
                    }
                    CollectionsKt.removeLast(arrayList);
                } else if (bPeekNextToken2 == 7) {
                    if (((Number) CollectionsKt.last((List) arrayList)).byteValue() != 6) {
                        throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found } instead of ] at path: " + this.path, getSource());
                    }
                    CollectionsKt.removeLast(arrayList);
                } else if (bPeekNextToken2 == 10) {
                    fail$default(this, "Unexpected end of input due to malformed JSON during ignoring unknown keys", 0, null, 6, null);
                    throw new KotlinNothingValueException();
                }
                consumeNextToken();
                if (arrayList.size() == 0) {
                    return;
                }
            } else if (z) {
                consumeStringLenient();
            } else {
                consumeKeyString();
            }
        }
    }

    public String toString() {
        return "JsonReader(source='" + ((Object) getSource()) + "', currentPosition=" + this.currentPosition + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public final void failOnUnknownKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        fail("Encountered an unknown key '" + key + CoreConstants.SINGLE_QUOTE_CHAR, StringsKt.lastIndexOf$default((CharSequence) substring(0, this.currentPosition), key, 0, false, 6, (Object) null), "Use 'ignoreUnknownKeys = true' in 'Json {}' builder to ignore unknown keys.");
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ Void fail$default(AbstractJsonLexer abstractJsonLexer, String str, int i, String str2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fail");
        }
        if ((i2 & 2) != 0) {
            i = abstractJsonLexer.currentPosition;
        }
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        return abstractJsonLexer.fail(str, i, str2);
    }

    public final Void fail(String message, int i, String hint) {
        String str;
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(hint, "hint");
        if (hint.length() == 0) {
            str = "";
        } else {
            str = '\n' + hint;
        }
        throw JsonExceptionsKt.JsonDecodingException(i, message + " at path: " + this.path.getPath() + str, getSource());
    }

    public final long consumeNumericLiteral() {
        boolean z;
        int iPrefetchOrEof = prefetchOrEof(skipWhitespaces());
        if (iPrefetchOrEof >= getSource().length() || iPrefetchOrEof == -1) {
            fail$default(this, "EOF", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(iPrefetchOrEof) == '\"') {
            iPrefetchOrEof++;
            if (iPrefetchOrEof == getSource().length()) {
                fail$default(this, "EOF", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            z = true;
        } else {
            z = false;
        }
        int i = iPrefetchOrEof;
        long j = 0;
        boolean z2 = true;
        boolean z3 = false;
        while (z2) {
            char cCharAt = getSource().charAt(i);
            if (cCharAt != '-') {
                if (AbstractJsonLexerKt.charToTokenClass(cCharAt) != 0) {
                    break;
                }
                i++;
                z2 = i != getSource().length();
                int i2 = cCharAt - '0';
                if (i2 < 0 || i2 >= 10) {
                    fail$default(this, "Unexpected symbol '" + cCharAt + "' in numeric literal", 0, null, 6, null);
                    throw new KotlinNothingValueException();
                }
                j = (j * ((long) 10)) - ((long) i2);
                if (j > 0) {
                    fail$default(this, "Numeric value overflow", 0, null, 6, null);
                    throw new KotlinNothingValueException();
                }
            } else {
                if (i != iPrefetchOrEof) {
                    fail$default(this, "Unexpected symbol '-' in numeric literal", 0, null, 6, null);
                    throw new KotlinNothingValueException();
                }
                i++;
                z3 = true;
            }
        }
        if (iPrefetchOrEof == i || (z3 && iPrefetchOrEof == i - 1)) {
            fail$default(this, "Expected numeric literal", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        if (z) {
            if (!z2) {
                fail$default(this, "EOF", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            if (getSource().charAt(i) != '\"') {
                fail$default(this, "Expected closing quotation mark", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            i++;
        }
        this.currentPosition = i;
        if (z3) {
            return j;
        }
        if (j != Long.MIN_VALUE) {
            return -j;
        }
        fail$default(this, "Numeric value overflow", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    public final boolean consumeBoolean() {
        return consumeBoolean(skipWhitespaces());
    }

    public final boolean consumeBooleanLenient() {
        boolean z;
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces == getSource().length()) {
            fail$default(this, "EOF", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(iSkipWhitespaces) == '\"') {
            iSkipWhitespaces++;
            z = true;
        } else {
            z = false;
        }
        boolean zConsumeBoolean = consumeBoolean(iSkipWhitespaces);
        if (z) {
            if (this.currentPosition == getSource().length()) {
                fail$default(this, "EOF", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            if (getSource().charAt(this.currentPosition) != '\"') {
                fail$default(this, "Expected closing quotation mark", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            this.currentPosition++;
        }
        return zConsumeBoolean;
    }

    private final boolean consumeBoolean(int i) {
        int iPrefetchOrEof = prefetchOrEof(i);
        if (iPrefetchOrEof >= getSource().length() || iPrefetchOrEof == -1) {
            fail$default(this, "EOF", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int i2 = iPrefetchOrEof + 1;
        int iCharAt = getSource().charAt(iPrefetchOrEof) | ' ';
        if (iCharAt == 102) {
            consumeBooleanLiteral("alse", i2);
            return false;
        }
        if (iCharAt == 116) {
            consumeBooleanLiteral("rue", i2);
            return true;
        }
        fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + CoreConstants.SINGLE_QUOTE_CHAR, 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    private final void consumeBooleanLiteral(String str, int i) {
        if (getSource().length() - i < str.length()) {
            fail$default(this, "Unexpected end of boolean literal", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (str.charAt(i2) != (getSource().charAt(i + i2) | ' ')) {
                fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + CoreConstants.SINGLE_QUOTE_CHAR, 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
        }
        this.currentPosition = i + str.length();
    }
}
