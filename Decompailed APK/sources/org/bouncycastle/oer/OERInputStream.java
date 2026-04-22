package org.bouncycastle.oer;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Iterator;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.oer.OERDefinition;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.Streams;

/* JADX INFO: loaded from: classes2.dex */
public class OERInputStream extends FilterInputStream {
    private static final int[] bits = {1, 2, 4, 8, 16, 32, 64, 128};
    private static final int[] bitsR = {128, 64, 32, 16, 8, 4, 2, 1};
    protected PrintWriter debugOutput;
    protected PrintWriter debugStream;
    private int maxByteAllocation;

    /* JADX INFO: renamed from: org.bouncycastle.oer.OERInputStream$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType;

        static {
            int[] iArr = new int[OERDefinition.BaseType.values().length];
            $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType = iArr;
            try {
                iArr[OERDefinition.BaseType.OPAQUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.Switch.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.Supplier.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.SEQ_OF.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.SEQ.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.CHOICE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.ENUM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.INT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.OCTET_STRING.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.IA5String.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.UTF8_STRING.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.BIT_STRING.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.NULL.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.EXTENSION.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.BOOLEAN.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    public static class Choice extends OERInputStream {
        final int preamble;
        final int tag;
        final int tagClass;

        public Choice(InputStream inputStream) throws IOException {
            int i;
            super(inputStream);
            int i2 = read();
            this.preamble = i2;
            if (i2 < 0) {
                throw new EOFException("expecting preamble byte of choice");
            }
            this.tagClass = i2 & 192;
            int i3 = i2 & 63;
            if (i3 >= 63) {
                i3 = 0;
                do {
                    i = inputStream.read();
                    if (i < 0) {
                        throw new EOFException("expecting further tag bytes");
                    }
                    i3 = (i3 << 7) | (i & 127);
                } while ((i & 128) != 0);
            }
            this.tag = i3;
        }

        public int getTag() {
            return this.tag;
        }

        public int getTagClass() {
            return this.tagClass;
        }

        public boolean isApplicationTagClass() {
            return this.tagClass == 64;
        }

        public boolean isContextSpecific() {
            return this.tagClass == 128;
        }

        public boolean isPrivateTagClass() {
            return this.tagClass == 192;
        }

        public boolean isUniversalTagClass() {
            return this.tagClass == 0;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("CHOICE(");
            int i = this.tagClass;
            if (i == 0) {
                str = "Universal ";
            } else if (i == 64) {
                str = "Application ";
            } else {
                if (i != 128) {
                    if (i == 192) {
                        str = "Private ";
                    }
                    sb.append("Tag = " + this.tag);
                    sb.append(")");
                    return sb.toString();
                }
                str = "ContextSpecific ";
            }
            sb.append(str);
            sb.append("Tag = " + this.tag);
            sb.append(")");
            return sb.toString();
        }
    }

    private final class LengthInfo {
        private final BigInteger length;
        private final boolean shortForm;

        public LengthInfo(BigInteger bigInteger, boolean z) {
            this.length = bigInteger;
            this.shortForm = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int intLength() {
            return BigIntegers.intValueExact(this.length);
        }
    }

    public static class Sequence extends OERInputStream {
        private final boolean extensionFlagSet;
        private final int preamble;
        private final boolean[] valuePresent;

        public Sequence(InputStream inputStream, Element element) throws IOException {
            int i;
            super(inputStream);
            if (!element.hasPopulatedExtension() && element.getOptionals() <= 0 && !element.hasDefaultChildren()) {
                this.preamble = 0;
                this.extensionFlagSet = false;
                this.valuePresent = null;
                return;
            }
            int i2 = ((FilterInputStream) this).in.read();
            this.preamble = i2;
            if (i2 < 0) {
                throw new EOFException("expecting preamble byte of sequence");
            }
            this.extensionFlagSet = element.hasPopulatedExtension() && (i2 & 128) == 128;
            this.valuePresent = new boolean[element.getChildren().size()];
            int i3 = element.hasPopulatedExtension() ? 6 : 7;
            int i4 = 0;
            for (Element element2 : element.getChildren()) {
                if (element2.getBaseType() != OERDefinition.BaseType.EXTENSION) {
                    if (element2.getBlock() != 0) {
                        return;
                    }
                    if (element2.isExplicit()) {
                        i = i4 + 1;
                        this.valuePresent[i4] = true;
                    } else {
                        if (i3 < 0) {
                            i2 = inputStream.read();
                            if (i2 < 0) {
                                throw new EOFException("expecting mask byte sequence");
                            }
                            i3 = 7;
                        }
                        i = i4 + 1;
                        this.valuePresent[i4] = (OERInputStream.bits[i3] & i2) > 0;
                        i3--;
                    }
                    i4 = i;
                }
            }
        }

        public boolean hasExtension() {
            return this.extensionFlagSet;
        }

        public boolean hasOptional(int i) {
            return this.valuePresent[i];
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("SEQ(");
            sb.append(hasExtension() ? "Ext " : "");
            if (this.valuePresent != null) {
                int i = 0;
                while (true) {
                    boolean[] zArr = this.valuePresent;
                    if (i >= zArr.length) {
                        break;
                    }
                    sb.append(zArr[i] ? "1" : "0");
                    i++;
                }
            } else {
                sb.append("*");
            }
            sb.append(")");
            return sb.toString();
        }
    }

    public OERInputStream(InputStream inputStream) {
        super(inputStream);
        this.debugOutput = null;
        this.maxByteAllocation = PKIFailureInfo.badCertTemplate;
        this.debugStream = null;
    }

    public OERInputStream(InputStream inputStream, int i) {
        super(inputStream);
        this.debugOutput = null;
        this.debugStream = null;
        this.maxByteAllocation = i;
    }

    private ASN1Encodable absent(Element element) {
        debugPrint(element + "Absent");
        return OEROptional.ABSENT;
    }

    private byte[] allocateArray(int i) {
        if (i <= this.maxByteAllocation) {
            return new byte[i];
        }
        throw new IllegalArgumentException("required byte array size " + i + " was greater than " + this.maxByteAllocation);
    }

    private int countOptionalChildTypes(Element element) {
        Iterator<Element> it = element.getChildren().iterator();
        int i = 0;
        while (it.hasNext()) {
            i += !it.next().isExplicit() ? 1 : 0;
        }
        return i;
    }

    public static ASN1Encodable parse(byte[] bArr, Element element) throws IOException {
        return new OERInputStream(new ByteArrayInputStream(bArr)).parse(element);
    }

    public Choice choice() throws IOException {
        return new Choice(this);
    }

    protected void debugPrint(String str) {
        if (this.debugOutput == null) {
            return;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int i = -1;
        for (int i2 = 0; i2 != stackTrace.length; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            if (stackTraceElement.getMethodName().equals("debugPrint")) {
                i = 0;
            } else if (stackTraceElement.getClassName().contains("OERInput")) {
                i++;
            }
        }
        while (true) {
            PrintWriter printWriter = this.debugOutput;
            if (i <= 0) {
                printWriter.append((CharSequence) str).append((CharSequence) "\n");
                this.debugOutput.flush();
                return;
            } else {
                printWriter.append((CharSequence) "    ");
                i--;
            }
        }
    }

    public BigInteger enumeration() throws IOException {
        int i = read();
        if (i == -1) {
            throw new EOFException("expecting prefix of enumeration");
        }
        if ((i & 128) != 128) {
            return BigInteger.valueOf(i);
        }
        int i2 = i & 127;
        if (i2 == 0) {
            return BigInteger.ZERO;
        }
        byte[] bArr = new byte[i2];
        if (Streams.readFully(this, bArr) == i2) {
            return new BigInteger(1, bArr);
        }
        throw new EOFException("unable to fully read integer component of enumeration");
    }

    public BigInteger int16() throws Exception {
        return parseInt(false, 2);
    }

    public BigInteger int32() throws Exception {
        return parseInt(false, 4);
    }

    public BigInteger int64() throws Exception {
        return parseInt(false, 8);
    }

    public BigInteger int8() throws Exception {
        return parseInt(false, 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x02a8 A[PHI: r2
  0x02a8: PHI (r2v19 byte[]) = (r2v18 byte[]), (r2v20 byte[]) binds: [B:99:0x02a6, B:95:0x028c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x046c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.bouncycastle.asn1.ASN1Object parse(org.bouncycastle.oer.Element r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1476
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.oer.OERInputStream.parse(org.bouncycastle.oer.Element):org.bouncycastle.asn1.ASN1Object");
    }

    public BigInteger parseInt(boolean z, int i) throws Exception {
        byte[] bArr = new byte[i];
        if (Streams.readFully(this, bArr) == i) {
            return z ? new BigInteger(1, bArr) : new BigInteger(bArr);
        }
        throw new IllegalStateException("integer not fully read");
    }

    protected ASN1Encodable parseOpenType(Element element) throws Throwable {
        OERInputStream oERInputStream;
        byte[] bArrAllocateArray = allocateArray(readLength().intLength());
        if (Streams.readFully(((FilterInputStream) this).in, bArrAllocateArray) != bArrAllocateArray.length) {
            throw new IOException("did not fully read open type as raw bytes");
        }
        OERInputStream oERInputStream2 = null;
        try {
            oERInputStream = new OERInputStream(new ByteArrayInputStream(bArrAllocateArray));
        } catch (Throwable th) {
            th = th;
        }
        try {
            ASN1Object aSN1Object = oERInputStream.parse(element);
            oERInputStream.close();
            return aSN1Object;
        } catch (Throwable th2) {
            th = th2;
            oERInputStream2 = oERInputStream;
            if (oERInputStream2 != null) {
                oERInputStream2.close();
            }
            throw th;
        }
    }

    public LengthInfo readLength() throws IOException {
        int i = read();
        if (i == -1) {
            throw new EOFException("expecting length");
        }
        if ((i & 128) == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Len (Short form): ");
            int i2 = i & 127;
            sb.append(i2);
            debugPrint(sb.toString());
            return new LengthInfo(BigInteger.valueOf(i2), true);
        }
        int i3 = i & 127;
        byte[] bArr = new byte[i3];
        if (Streams.readFully(this, bArr) != i3) {
            throw new EOFException("did not read all bytes of length definition");
        }
        debugPrint("Len (Long Form): " + i3 + " actual len: " + Hex.toHexString(bArr));
        return new LengthInfo(BigIntegers.fromUnsignedByteArray(bArr), false);
    }

    public BigInteger uint16() throws Exception {
        return parseInt(true, 2);
    }

    public BigInteger uint32() throws Exception {
        return parseInt(true, 4);
    }

    public BigInteger uint64() throws Exception {
        return parseInt(false, 8);
    }

    public BigInteger uint8() throws Exception {
        return parseInt(true, 1);
    }
}
