package io.ktor.http;

import ch.qos.logback.core.CoreConstants;
import io.ktor.utils.io.charsets.EncodingKt;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.CharRange;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* JADX INFO: compiled from: Codecs.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CodecsKt {
    private static final Set ATTRIBUTE_CHARACTERS;
    private static final Set HEX_ALPHABET;
    private static final List SPECIAL_SYMBOLS;
    private static final Set URL_ALPHABET;
    private static final Set URL_ALPHABET_CHARS;
    private static final List URL_PROTOCOL_PART;
    private static final Set VALID_PATH_PART;

    private static final int charToHexDigit(char c) {
        if ('0' <= c && c < ':') {
            return c - '0';
        }
        if ('A' <= c && c < 'G') {
            return c - '7';
        }
        if ('a' > c || c >= 'g') {
            return -1;
        }
        return c - 'W';
    }

    private static final char hexDigitToChar(int i) {
        return (char) ((i < 0 || i >= 10) ? ((char) (i + 65)) - '\n' : i + 48);
    }

    static {
        List listPlus = CollectionsKt.plus((Collection) CollectionsKt.plus((Iterable) new CharRange('a', 'z'), (Iterable) new CharRange('A', Matrix.MATRIX_TYPE_ZERO)), (Iterable) new CharRange('0', '9'));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
        Iterator it = listPlus.iterator();
        while (it.hasNext()) {
            arrayList.add(Byte.valueOf((byte) ((Character) it.next()).charValue()));
        }
        URL_ALPHABET = CollectionsKt.toSet(arrayList);
        URL_ALPHABET_CHARS = CollectionsKt.toSet(CollectionsKt.plus((Collection) CollectionsKt.plus((Iterable) new CharRange('a', 'z'), (Iterable) new CharRange('A', Matrix.MATRIX_TYPE_ZERO)), (Iterable) new CharRange('0', '9')));
        HEX_ALPHABET = CollectionsKt.toSet(CollectionsKt.plus((Collection) CollectionsKt.plus((Iterable) new CharRange('a', 'f'), (Iterable) new CharRange('A', 'F')), (Iterable) new CharRange('0', '9')));
        Set of = SetsKt.setOf((Object[]) new Character[]{Character.valueOf(CoreConstants.COLON_CHAR), '/', '?', '#', '[', ']', '@', '!', Character.valueOf(CoreConstants.DOLLAR), '&', Character.valueOf(CoreConstants.SINGLE_QUOTE_CHAR), Character.valueOf(CoreConstants.LEFT_PARENTHESIS_CHAR), Character.valueOf(CoreConstants.RIGHT_PARENTHESIS_CHAR), '*', Character.valueOf(CoreConstants.COMMA_CHAR), ';', '=', Character.valueOf(CoreConstants.DASH_CHAR), Character.valueOf(CoreConstants.DOT), '_', '~', '+'});
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(of, 10));
        Iterator it2 = of.iterator();
        while (it2.hasNext()) {
            arrayList2.add(Byte.valueOf((byte) ((Character) it2.next()).charValue()));
        }
        URL_PROTOCOL_PART = arrayList2;
        VALID_PATH_PART = SetsKt.setOf((Object[]) new Character[]{Character.valueOf(CoreConstants.COLON_CHAR), '@', '!', Character.valueOf(CoreConstants.DOLLAR), '&', Character.valueOf(CoreConstants.SINGLE_QUOTE_CHAR), Character.valueOf(CoreConstants.LEFT_PARENTHESIS_CHAR), Character.valueOf(CoreConstants.RIGHT_PARENTHESIS_CHAR), '*', '+', Character.valueOf(CoreConstants.COMMA_CHAR), ';', '=', Character.valueOf(CoreConstants.DASH_CHAR), Character.valueOf(CoreConstants.DOT), '_', '~'});
        ATTRIBUTE_CHARACTERS = SetsKt.plus(URL_ALPHABET_CHARS, (Iterable) SetsKt.setOf((Object[]) new Character[]{'!', '#', Character.valueOf(CoreConstants.DOLLAR), '&', '+', Character.valueOf(CoreConstants.DASH_CHAR), Character.valueOf(CoreConstants.DOT), '^', '_', '`', '|', '~'}));
        List listListOf = CollectionsKt.listOf((Object[]) new Character[]{Character.valueOf(CoreConstants.DASH_CHAR), Character.valueOf(CoreConstants.DOT), '_', '~'});
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        Iterator it3 = listListOf.iterator();
        while (it3.hasNext()) {
            arrayList3.add(Byte.valueOf((byte) ((Character) it3.next()).charValue()));
        }
        SPECIAL_SYMBOLS = arrayList3;
    }

    public static /* synthetic */ String encodeURLQueryComponent$default(String str, boolean z, boolean z2, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            charset = Charsets.UTF_8;
        }
        return encodeURLQueryComponent(str, z, z2, charset);
    }

    public static final String encodeURLQueryComponent(String str, final boolean z, final boolean z2, Charset charset) throws Throwable {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        final StringBuilder sb = new StringBuilder();
        CharsetEncoder charsetEncoderNewEncoder = charset.newEncoder();
        Intrinsics.checkNotNullExpressionValue(charsetEncoderNewEncoder, "charset.newEncoder()");
        forEach(EncodingKt.encode$default(charsetEncoderNewEncoder, str, 0, 0, 6, null), new Function1() { // from class: io.ktor.http.CodecsKt$encodeURLQueryComponent$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke(((Number) obj).byteValue());
                return Unit.INSTANCE;
            }

            public final void invoke(byte b) {
                if (b != 32) {
                    if (!CodecsKt.URL_ALPHABET.contains(Byte.valueOf(b)) && (z || !CodecsKt.URL_PROTOCOL_PART.contains(Byte.valueOf(b)))) {
                        sb.append(CodecsKt.percentEncode(b));
                        return;
                    } else {
                        sb.append((char) b);
                        return;
                    }
                }
                if (z2) {
                    sb.append('+');
                } else {
                    sb.append("%20");
                }
            }
        });
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static final String encodeURLPathPart(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return encodeURLPath(str, true);
    }

    public static final String encodeURLPath(String str, boolean z) throws Throwable {
        int i;
        Intrinsics.checkNotNullParameter(str, "<this>");
        final StringBuilder sb = new StringBuilder();
        Charset charset = Charsets.UTF_8;
        int i2 = 0;
        while (i2 < str.length()) {
            char cCharAt = str.charAt(i2);
            if ((!z && cCharAt == '/') || URL_ALPHABET_CHARS.contains(Character.valueOf(cCharAt)) || VALID_PATH_PART.contains(Character.valueOf(cCharAt))) {
                sb.append(cCharAt);
                i2++;
            } else {
                if (cCharAt == '%' && (i = i2 + 2) < str.length()) {
                    Set set = HEX_ALPHABET;
                    int i3 = i2 + 1;
                    if (set.contains(Character.valueOf(str.charAt(i3))) && set.contains(Character.valueOf(str.charAt(i)))) {
                        sb.append(cCharAt);
                        sb.append(str.charAt(i3));
                        sb.append(str.charAt(i));
                        i2 += 3;
                    }
                }
                int i4 = CharsKt.isSurrogate(cCharAt) ? 2 : 1;
                CharsetEncoder charsetEncoderNewEncoder = charset.newEncoder();
                Intrinsics.checkNotNullExpressionValue(charsetEncoderNewEncoder, "charset.newEncoder()");
                int i5 = i4 + i2;
                forEach(EncodingKt.encode(charsetEncoderNewEncoder, str, i2, i5), new Function1() { // from class: io.ktor.http.CodecsKt$encodeURLPath$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke(((Number) obj).byteValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(byte b) {
                        sb.append(CodecsKt.percentEncode(b));
                    }
                });
                i2 = i5;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static /* synthetic */ String encodeURLParameter$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return encodeURLParameter(str, z);
    }

    public static final String encodeURLParameter(String str, final boolean z) throws Throwable {
        Intrinsics.checkNotNullParameter(str, "<this>");
        final StringBuilder sb = new StringBuilder();
        CharsetEncoder charsetEncoderNewEncoder = Charsets.UTF_8.newEncoder();
        Intrinsics.checkNotNullExpressionValue(charsetEncoderNewEncoder, "UTF_8.newEncoder()");
        forEach(EncodingKt.encode$default(charsetEncoderNewEncoder, str, 0, 0, 6, null), new Function1() { // from class: io.ktor.http.CodecsKt$encodeURLParameter$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke(((Number) obj).byteValue());
                return Unit.INSTANCE;
            }

            public final void invoke(byte b) {
                if (CodecsKt.URL_ALPHABET.contains(Byte.valueOf(b)) || CodecsKt.SPECIAL_SYMBOLS.contains(Byte.valueOf(b))) {
                    sb.append((char) b);
                } else if (z && b == 32) {
                    sb.append('+');
                } else {
                    sb.append(CodecsKt.percentEncode(b));
                }
            }
        });
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static final String encodeURLParameterValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return encodeURLParameter(str, true);
    }

    public static /* synthetic */ String decodeURLQueryComponent$default(String str, int i, int i2, boolean z, Charset charset, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            charset = Charsets.UTF_8;
        }
        return decodeURLQueryComponent(str, i, i2, z, charset);
    }

    public static final String decodeURLQueryComponent(String str, int i, int i2, boolean z, Charset charset) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return decodeScan(str, i, i2, z, charset);
    }

    public static /* synthetic */ String decodeURLPart$default(String str, int i, int i2, Charset charset, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            charset = Charsets.UTF_8;
        }
        return decodeURLPart(str, i, i2, charset);
    }

    public static final String decodeURLPart(String str, int i, int i2, Charset charset) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return decodeScan(str, i, i2, false, charset);
    }

    private static final String decodeScan(String str, int i, int i2, boolean z, Charset charset) {
        for (int i3 = i; i3 < i2; i3++) {
            char cCharAt = str.charAt(i3);
            if (cCharAt == '%' || (z && cCharAt == '+')) {
                return decodeImpl(str, i, i2, i3, z, charset);
            }
        }
        if (i == 0 && i2 == str.length()) {
            return str.toString();
        }
        String strSubstring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    private static final String decodeImpl(CharSequence charSequence, int i, int i2, int i3, boolean z, Charset charset) throws URLDecodeException {
        int i4 = i2 - i;
        if (i4 > 255) {
            i4 /= 3;
        }
        StringBuilder sb = new StringBuilder(i4);
        if (i3 > i) {
            sb.append(charSequence, i, i3);
        }
        byte[] bArr = null;
        while (i3 < i2) {
            char cCharAt = charSequence.charAt(i3);
            if (z && cCharAt == '+') {
                sb.append(' ');
            } else if (cCharAt == '%') {
                if (bArr == null) {
                    bArr = new byte[(i2 - i3) / 3];
                }
                int i5 = 0;
                while (i3 < i2 && charSequence.charAt(i3) == '%') {
                    int i6 = i3 + 2;
                    if (i6 >= i2) {
                        throw new URLDecodeException("Incomplete trailing HEX escape: " + charSequence.subSequence(i3, charSequence.length()).toString() + ", in " + ((Object) charSequence) + " at " + i3);
                    }
                    int i7 = i3 + 1;
                    int iCharToHexDigit = charToHexDigit(charSequence.charAt(i7));
                    int iCharToHexDigit2 = charToHexDigit(charSequence.charAt(i6));
                    if (iCharToHexDigit == -1 || iCharToHexDigit2 == -1) {
                        throw new URLDecodeException("Wrong HEX escape: %" + charSequence.charAt(i7) + charSequence.charAt(i6) + ", in " + ((Object) charSequence) + ", at " + i3);
                    }
                    bArr[i5] = (byte) ((iCharToHexDigit * 16) + iCharToHexDigit2);
                    i3 += 3;
                    i5++;
                }
                sb.append(new String(bArr, 0, i5, charset));
            } else {
                sb.append(cCharAt);
            }
            i3++;
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String percentEncode(byte b) {
        return StringsKt.concatToString(new char[]{CoreConstants.PERCENT_CHAR, hexDigitToChar((b & 255) >> 4), hexDigitToChar(b & 15)});
    }

    private static final void forEach(ByteReadPacket byteReadPacket, Function1 function1) throws Throwable {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(byteReadPacket, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            return;
        }
        while (true) {
            try {
                if (chunkBufferPrepareReadFirstHead.getWritePosition() > chunkBufferPrepareReadFirstHead.getReadPosition()) {
                    function1.invoke(Byte.valueOf(chunkBufferPrepareReadFirstHead.readByte()));
                } else {
                    try {
                        chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadNextHead(byteReadPacket, chunkBufferPrepareReadFirstHead);
                        if (chunkBufferPrepareReadFirstHead == null) {
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        if (z) {
                            UnsafeKt.completeReadHead(byteReadPacket, chunkBufferPrepareReadFirstHead);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }
}
