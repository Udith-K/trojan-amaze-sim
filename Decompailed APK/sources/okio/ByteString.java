package okio;

import ch.qos.logback.core.CoreConstants;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: compiled from: ByteString.kt */
/* JADX INFO: loaded from: classes2.dex */
public class ByteString implements Serializable, Comparable {
    public static final Companion Companion = new Companion(null);
    public static final ByteString EMPTY = new ByteString(new byte[0]);
    private final byte[] data;
    private transient int hashCode;
    private transient String utf8;

    public String utf8() {
        String utf8$okio = getUtf8$okio();
        if (utf8$okio != null) {
            return utf8$okio;
        }
        String utf8String = _JvmPlatformKt.toUtf8String(internalArray$okio());
        setUtf8$okio(utf8String);
        return utf8String;
    }

    public String base64() {
        return Base64.encodeBase64$default(getData$okio(), null, 1, null);
    }

    public ByteString(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }

    public final byte[] getData$okio() {
        return this.data;
    }

    public final int getHashCode$okio() {
        return this.hashCode;
    }

    public final void setHashCode$okio(int i) {
        this.hashCode = i;
    }

    public final String getUtf8$okio() {
        return this.utf8;
    }

    public String hex() {
        char[] cArr = new char[getData$okio().length * 2];
        int i = 0;
        for (byte b : getData$okio()) {
            int i2 = i + 1;
            cArr[i] = okio.internal.ByteString.getHEX_DIGIT_CHARS()[(b >> 4) & 15];
            i += 2;
            cArr[i2] = okio.internal.ByteString.getHEX_DIGIT_CHARS()[b & 15];
        }
        return StringsKt.concatToString(cArr);
    }

    public final void setUtf8$okio(String str) {
        this.utf8 = str;
    }

    public final ByteString sha1() {
        return digest$okio(McElieceCCA2KeyGenParameterSpec.SHA1);
    }

    public final ByteString sha256() {
        return digest$okio("SHA-256");
    }

    public ByteString toAsciiLowercase() {
        for (int i = 0; i < getData$okio().length; i++) {
            byte b = getData$okio()[i];
            if (b >= 65 && b <= 90) {
                byte[] data$okio = getData$okio();
                byte[] bArrCopyOf = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
                bArrCopyOf[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < bArrCopyOf.length; i2++) {
                    byte b2 = bArrCopyOf[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        bArrCopyOf[i2] = (byte) (b2 + 32);
                    }
                }
                return new ByteString(bArrCopyOf);
            }
        }
        return this;
    }

    public ByteString digest$okio(String algorithm) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(this.data, 0, size());
        byte[] bArrDigest = messageDigest.digest();
        Intrinsics.checkNotNull(bArrDigest);
        return new ByteString(bArrDigest);
    }

    public final byte getByte(int i) {
        return internalGet$okio(i);
    }

    public final int size() {
        return getSize$okio();
    }

    public byte internalGet$okio(int i) {
        return getData$okio()[i];
    }

    public void write$okio(Buffer buffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        okio.internal.ByteString.commonWrite(this, buffer, i, i2);
    }

    public int getSize$okio() {
        return getData$okio().length;
    }

    public byte[] toByteArray() {
        byte[] data$okio = getData$okio();
        byte[] bArrCopyOf = Arrays.copyOf(data$okio, data$okio.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    public byte[] internalArray$okio() {
        return getData$okio();
    }

    public boolean rangeEquals(int i, ByteString other, int i2, int i3) {
        Intrinsics.checkNotNullParameter(other, "other");
        return other.rangeEquals(i2, getData$okio(), i, i3);
    }

    public boolean rangeEquals(int i, byte[] other, int i2, int i3) {
        Intrinsics.checkNotNullParameter(other, "other");
        return i >= 0 && i <= getData$okio().length - i3 && i2 >= 0 && i2 <= other.length - i3 && SegmentedByteString.arrayRangeEquals(getData$okio(), i, other, i2, i3);
    }

    public final boolean startsWith(ByteString prefix) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return rangeEquals(0, prefix, 0, prefix.size());
    }

    /* JADX INFO: compiled from: ByteString.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ ByteString of$default(Companion companion, byte[] bArr, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = SegmentedByteString.getDEFAULT__ByteString_size();
            }
            return companion.of(bArr, i, i2);
        }

        private Companion() {
        }

        public final ByteString encodeString(String str, Charset charset) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            Intrinsics.checkNotNullParameter(charset, "charset");
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            return new ByteString(bytes);
        }

        public final ByteString of(byte[] bArr, int i, int i2) {
            Intrinsics.checkNotNullParameter(bArr, "<this>");
            int iResolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(bArr, i2);
            SegmentedByteString.checkOffsetAndCount(bArr.length, i, iResolveDefaultParameter);
            return new ByteString(ArraysKt.copyOfRange(bArr, i, iResolveDefaultParameter + i));
        }

        public final ByteString encodeUtf8(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            ByteString byteString = new ByteString(_JvmPlatformKt.asUtf8ToByteArray(str));
            byteString.setUtf8$okio(str);
            return byteString;
        }

        public final ByteString decodeHex(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            if (str.length() % 2 != 0) {
                throw new IllegalArgumentException(("Unexpected hex string: " + str).toString());
            }
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                bArr[i] = (byte) ((okio.internal.ByteString.decodeHexDigit(str.charAt(i2)) << 4) + okio.internal.ByteString.decodeHexDigit(str.charAt(i2 + 1)));
            }
            return new ByteString(bArr);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == getData$okio().length && byteString.rangeEquals(0, getData$okio(), 0, getData$okio().length)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode$okio = getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int iHashCode = Arrays.hashCode(getData$okio());
        setHashCode$okio(iHashCode);
        return iHashCode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0031, code lost:
    
        if (r0 < r1) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0034, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:?, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0028, code lost:
    
        if (r7 < r8) goto L9;
     */
    @Override // java.lang.Comparable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int compareTo(okio.ByteString r10) {
        /*
            r9 = this;
            java.lang.String r0 = "other"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            int r0 = r9.size()
            int r1 = r10.size()
            int r2 = java.lang.Math.min(r0, r1)
            r3 = 0
            r4 = r3
        L13:
            r5 = -1
            r6 = 1
            if (r4 >= r2) goto L2e
            byte r7 = r9.getByte(r4)
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r8 = r10.getByte(r4)
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L28
            int r4 = r4 + 1
            goto L13
        L28:
            if (r7 >= r8) goto L2c
        L2a:
            r3 = r5
            goto L34
        L2c:
            r3 = r6
            goto L34
        L2e:
            if (r0 != r1) goto L31
            goto L34
        L31:
            if (r0 >= r1) goto L2c
            goto L2a
        L34:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ByteString.compareTo(okio.ByteString):int");
    }

    public String toString() {
        String str;
        if (getData$okio().length == 0) {
            str = "[size=0]";
        } else {
            int iCodePointIndexToCharIndex = okio.internal.ByteString.codePointIndexToCharIndex(getData$okio(), 64);
            if (iCodePointIndexToCharIndex == -1) {
                if (getData$okio().length <= 64) {
                    str = "[hex=" + hex() + ']';
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[size=");
                    sb.append(getData$okio().length);
                    sb.append(" hex=");
                    int iResolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(this, 64);
                    if (iResolveDefaultParameter <= getData$okio().length) {
                        if (iResolveDefaultParameter < 0) {
                            throw new IllegalArgumentException("endIndex < beginIndex");
                        }
                        sb.append((iResolveDefaultParameter == getData$okio().length ? this : new ByteString(ArraysKt.copyOfRange(getData$okio(), 0, iResolveDefaultParameter))).hex());
                        sb.append("…]");
                        return sb.toString();
                    }
                    throw new IllegalArgumentException(("endIndex > length(" + getData$okio().length + CoreConstants.RIGHT_PARENTHESIS_CHAR).toString());
                }
            } else {
                String strUtf8 = utf8();
                String strSubstring = strUtf8.substring(0, iCodePointIndexToCharIndex);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                String strReplace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(strSubstring, "\\", "\\\\", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
                if (iCodePointIndexToCharIndex < strUtf8.length()) {
                    return "[size=" + getData$okio().length + " text=" + strReplace$default + "…]";
                }
                return "[text=" + strReplace$default + ']';
            }
        }
        return str;
    }
}
