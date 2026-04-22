package kotlinx.serialization.json.internal;

import ch.qos.logback.core.CoreConstants;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: StringJsonLexer.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class StringJsonLexer extends AbstractJsonLexer {
    private final String source;

    public StringJsonLexer(String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public String getSource() {
        return this.source;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int prefetchOrEof(int i) {
        if (i < getSource().length()) {
            return i;
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte consumeNextToken() {
        byte bCharToTokenClass;
        String source = getSource();
        do {
            int i = this.currentPosition;
            if (i == -1 || i >= source.length()) {
                return (byte) 10;
            }
            int i2 = this.currentPosition;
            this.currentPosition = i2 + 1;
            bCharToTokenClass = AbstractJsonLexerKt.charToTokenClass(source.charAt(i2));
        } while (bCharToTokenClass == 3);
        return bCharToTokenClass;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean tryConsumeComma() {
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces == getSource().length() || iSkipWhitespaces == -1 || getSource().charAt(iSkipWhitespaces) != ',') {
            return false;
        }
        this.currentPosition++;
        return true;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean canConsumeValue() {
        int i = this.currentPosition;
        if (i == -1) {
            return false;
        }
        while (i < getSource().length()) {
            char cCharAt = getSource().charAt(i);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                this.currentPosition = i;
                return isValidValueStart(cCharAt);
            }
            i++;
        }
        this.currentPosition = i;
        return false;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int skipWhitespaces() {
        char cCharAt;
        int i = this.currentPosition;
        if (i == -1) {
            return i;
        }
        while (i < getSource().length() && ((cCharAt = getSource().charAt(i)) == ' ' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == '\t')) {
            i++;
        }
        this.currentPosition = i;
        return i;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public void consumeNextToken(char c) {
        if (this.currentPosition == -1) {
            unexpectedToken(c);
        }
        String source = getSource();
        while (this.currentPosition < source.length()) {
            int i = this.currentPosition;
            this.currentPosition = i + 1;
            char cCharAt = source.charAt(i);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                if (cCharAt == c) {
                    return;
                } else {
                    unexpectedToken(c);
                }
            }
        }
        unexpectedToken(c);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public String consumeKeyString() {
        consumeNextToken(CoreConstants.DOUBLE_QUOTE_CHAR);
        int i = this.currentPosition;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) getSource(), CoreConstants.DOUBLE_QUOTE_CHAR, i, false, 4, (Object) null);
        if (iIndexOf$default == -1) {
            fail$kotlinx_serialization_json((byte) 1);
            throw new KotlinNothingValueException();
        }
        for (int i2 = i; i2 < iIndexOf$default; i2++) {
            if (getSource().charAt(i2) == '\\') {
                return consumeString(getSource(), this.currentPosition, i2);
            }
        }
        this.currentPosition = iIndexOf$default + 1;
        String strSubstring = getSource().substring(i, iIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public String consumeLeadingMatchingValue(String keyToMatch, boolean z) {
        Intrinsics.checkNotNullParameter(keyToMatch, "keyToMatch");
        int i = this.currentPosition;
        try {
            if (consumeNextToken() == 6) {
                if (!Intrinsics.areEqual(z ? consumeKeyString() : consumeStringLenientNotNull(), keyToMatch)) {
                    this.currentPosition = i;
                    return null;
                }
                if (consumeNextToken() == 5) {
                    String strConsumeString = z ? consumeString() : consumeStringLenientNotNull();
                    this.currentPosition = i;
                    return strConsumeString;
                }
                this.currentPosition = i;
                return null;
            }
            this.currentPosition = i;
            return null;
        } catch (Throwable th) {
            this.currentPosition = i;
            throw th;
        }
    }
}
