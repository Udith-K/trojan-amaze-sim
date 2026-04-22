package kotlin.text;

import kotlin.KotlinNothingValueException;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: compiled from: UStrings.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class UStringsKt {
    public static final byte toUByte(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UByte uByteOrNull = toUByteOrNull(str);
        if (uByteOrNull != null) {
            return uByteOrNull.m2653unboximpl();
        }
        StringsKt__StringNumberConversionsKt.numberFormatError(str);
        throw new KotlinNothingValueException();
    }

    public static final short toUShort(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UShort uShortOrNull = toUShortOrNull(str);
        if (uShortOrNull != null) {
            return uShortOrNull.m2720unboximpl();
        }
        StringsKt__StringNumberConversionsKt.numberFormatError(str);
        throw new KotlinNothingValueException();
    }

    public static final int toUInt(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UInt uIntOrNull = toUIntOrNull(str);
        if (uIntOrNull != null) {
            return uIntOrNull.m2675unboximpl();
        }
        StringsKt__StringNumberConversionsKt.numberFormatError(str);
        throw new KotlinNothingValueException();
    }

    public static final long toULong(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        ULong uLongOrNull = toULongOrNull(str);
        if (uLongOrNull != null) {
            return uLongOrNull.m2698unboximpl();
        }
        StringsKt__StringNumberConversionsKt.numberFormatError(str);
        throw new KotlinNothingValueException();
    }

    public static final UByte toUByteOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return toUByteOrNull(str, 10);
    }

    public static final UByte toUByteOrNull(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UInt uIntOrNull = toUIntOrNull(str, i);
        if (uIntOrNull == null) {
            return null;
        }
        int iM2675unboximpl = uIntOrNull.m2675unboximpl();
        if (Integer.compare(iM2675unboximpl ^ Integer.MIN_VALUE, UInt.m2671constructorimpl(GF2Field.MASK) ^ Integer.MIN_VALUE) > 0) {
            return null;
        }
        return UByte.m2648boximpl(UByte.m2649constructorimpl((byte) iM2675unboximpl));
    }

    public static final UShort toUShortOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return toUShortOrNull(str, 10);
    }

    public static final UShort toUShortOrNull(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UInt uIntOrNull = toUIntOrNull(str, i);
        if (uIntOrNull == null) {
            return null;
        }
        int iM2675unboximpl = uIntOrNull.m2675unboximpl();
        if (Integer.compare(iM2675unboximpl ^ Integer.MIN_VALUE, UInt.m2671constructorimpl(65535) ^ Integer.MIN_VALUE) > 0) {
            return null;
        }
        return UShort.m2715boximpl(UShort.m2716constructorimpl((short) iM2675unboximpl));
    }

    public static final UInt toUIntOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return toUIntOrNull(str, 10);
    }

    public static final UInt toUIntOrNull(String str, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharsKt.checkRadix(i);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i3 = 0;
        char cCharAt = str.charAt(0);
        if (Intrinsics.compare((int) cCharAt, 48) < 0) {
            i2 = 1;
            if (length == 1 || cCharAt != '+') {
                return null;
            }
        } else {
            i2 = 0;
        }
        int iM2671constructorimpl = UInt.m2671constructorimpl(i);
        int iM = 119304647;
        while (i2 < length) {
            int iDigitOf = CharsKt__CharJVMKt.digitOf(str.charAt(i2), i);
            if (iDigitOf < 0) {
                return null;
            }
            if (Integer.compare(i3 ^ Integer.MIN_VALUE, iM ^ Integer.MIN_VALUE) > 0) {
                if (iM == 119304647) {
                    iM = UStringsKt$$ExternalSyntheticBackport2.m(-1, iM2671constructorimpl);
                    if (Integer.compare(i3 ^ Integer.MIN_VALUE, iM ^ Integer.MIN_VALUE) > 0) {
                    }
                }
                return null;
            }
            int iM2671constructorimpl2 = UInt.m2671constructorimpl(i3 * iM2671constructorimpl);
            int iM2671constructorimpl3 = UInt.m2671constructorimpl(UInt.m2671constructorimpl(iDigitOf) + iM2671constructorimpl2);
            if (Integer.compare(iM2671constructorimpl3 ^ Integer.MIN_VALUE, iM2671constructorimpl2 ^ Integer.MIN_VALUE) < 0) {
                return null;
            }
            i2++;
            i3 = iM2671constructorimpl3;
        }
        return UInt.m2670boximpl(i3);
    }

    public static final ULong toULongOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return toULongOrNull(str, 10);
    }

    public static final ULong toULongOrNull(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharsKt.checkRadix(i);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i2 = 0;
        char cCharAt = str.charAt(0);
        if (Intrinsics.compare((int) cCharAt, 48) < 0) {
            i2 = 1;
            if (length == 1 || cCharAt != '+') {
                return null;
            }
        }
        long jM2693constructorimpl = ULong.m2693constructorimpl(i);
        long j = 0;
        long jM = 512409557603043100L;
        while (i2 < length) {
            int iDigitOf = CharsKt__CharJVMKt.digitOf(str.charAt(i2), i);
            if (iDigitOf < 0) {
                return null;
            }
            if (Long.compare(j ^ Long.MIN_VALUE, jM ^ Long.MIN_VALUE) > 0) {
                if (jM == 512409557603043100L) {
                    jM = UStringsKt$$ExternalSyntheticBackport0.m(-1L, jM2693constructorimpl);
                    if (Long.compare(j ^ Long.MIN_VALUE, jM ^ Long.MIN_VALUE) > 0) {
                    }
                }
                return null;
            }
            long jM2693constructorimpl2 = ULong.m2693constructorimpl(j * jM2693constructorimpl);
            long jM2693constructorimpl3 = ULong.m2693constructorimpl(ULong.m2693constructorimpl(((long) UInt.m2671constructorimpl(iDigitOf)) & BodyPartID.bodyIdMax) + jM2693constructorimpl2);
            if (Long.compare(jM2693constructorimpl3 ^ Long.MIN_VALUE, jM2693constructorimpl2 ^ Long.MIN_VALUE) < 0) {
                return null;
            }
            i2++;
            j = jM2693constructorimpl3;
        }
        return ULong.m2692boximpl(j);
    }
}
