package javax.jmdns.impl;

import ch.qos.logback.core.CoreConstants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.constants.DNSLabel;
import javax.jmdns.impl.constants.DNSOptionCode;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public final class DNSIncoming extends DNSMessage {
    private final MessageInputStream _messageInputStream;
    private final DatagramPacket _packet;
    private final long _receivedTime;
    private int _senderUDPPayload;
    private static Logger logger = LoggerFactory.getLogger(DNSIncoming.class.getName());
    public static boolean USE_DOMAIN_NAME_FORMAT_FOR_SRV_TARGET = true;
    private static final char[] _nibbleToHex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static class MessageInputStream extends ByteArrayInputStream {
        private static Logger logger1 = LoggerFactory.getLogger(MessageInputStream.class.getName());
        final Map _names;

        public MessageInputStream(byte[] bArr, int i) {
            this(bArr, 0, i);
        }

        public MessageInputStream(byte[] bArr, int i, int i2) {
            super(bArr, i, i2);
            this._names = new HashMap();
        }

        public int readUnsignedByte() {
            return read() & GF2Field.MASK;
        }

        public int readUnsignedShort() {
            return (readUnsignedByte() << 8) | readUnsignedByte();
        }

        public int readInt() {
            return (readUnsignedShort() << 16) | readUnsignedShort();
        }

        public byte[] readBytes(int i) throws IOException {
            byte[] bArr = new byte[i];
            read(bArr, 0, i);
            return bArr;
        }

        public String readUTF(int i) {
            int i2;
            int unsignedByte;
            StringBuilder sb = new StringBuilder(i);
            int i3 = 0;
            while (i3 < i) {
                int unsignedByte2 = readUnsignedByte();
                switch (unsignedByte2 >> 4) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        sb.append((char) unsignedByte2);
                        i3++;
                        break;
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    default:
                        i2 = (unsignedByte2 & 63) << 4;
                        unsignedByte = readUnsignedByte() & 15;
                        break;
                    case 12:
                    case 13:
                        i2 = (unsignedByte2 & 31) << 6;
                        unsignedByte = readUnsignedByte() & 63;
                        break;
                    case 14:
                        unsignedByte2 = ((unsignedByte2 & 15) << 12) | ((readUnsignedByte() & 63) << 6) | (readUnsignedByte() & 63);
                        i3 += 2;
                        continue;
                        sb.append((char) unsignedByte2);
                        i3++;
                        break;
                }
                unsignedByte2 = i2 | unsignedByte;
                i3++;
                sb.append((char) unsignedByte2);
                i3++;
            }
            return sb.toString();
        }

        public String readName() {
            HashMap map = new HashMap();
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            while (!z) {
                int unsignedByte = readUnsignedByte();
                if (unsignedByte == 0) {
                    break;
                }
                int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSLabel[DNSLabel.labelForByte(unsignedByte).ordinal()];
                if (i == 1) {
                    int i2 = ((ByteArrayInputStream) this).pos - 1;
                    String str = readUTF(unsignedByte) + ".";
                    sb.append(str);
                    Iterator it = map.values().iterator();
                    while (it.hasNext()) {
                        ((StringBuilder) it.next()).append(str);
                    }
                    map.put(Integer.valueOf(i2), new StringBuilder(str));
                } else if (i == 2) {
                    int iLabelValue = (DNSLabel.labelValue(unsignedByte) << 8) | readUnsignedByte();
                    String str2 = (String) this._names.get(Integer.valueOf(iLabelValue));
                    if (str2 == null) {
                        logger1.warn("Bad domain name: possible circular name detected. Bad offset: 0x{} at 0x{}", Integer.toHexString(iLabelValue), Integer.toHexString(((ByteArrayInputStream) this).pos - 2));
                        str2 = "";
                    }
                    sb.append(str2);
                    Iterator it2 = map.values().iterator();
                    while (it2.hasNext()) {
                        ((StringBuilder) it2.next()).append(str2);
                    }
                    z = true;
                } else if (i == 3) {
                    logger1.debug("Extended label are not currently supported.");
                } else {
                    logger1.warn("Unsupported DNS label type: '{}'", Integer.toHexString(unsignedByte & 192));
                }
            }
            for (Map.Entry entry : map.entrySet()) {
                this._names.put((Integer) entry.getKey(), ((StringBuilder) entry.getValue()).toString());
            }
            return sb.toString();
        }

        public String readNonNameString() {
            return readUTF(readUnsignedByte());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DNSIncoming(DatagramPacket datagramPacket) {
        super(0, 0, datagramPacket.getPort() == DNSConstants.MDNS_PORT);
        this._packet = datagramPacket;
        InetAddress address = datagramPacket.getAddress();
        MessageInputStream messageInputStream = new MessageInputStream(datagramPacket.getData(), datagramPacket.getLength());
        this._messageInputStream = messageInputStream;
        this._receivedTime = System.currentTimeMillis();
        this._senderUDPPayload = 1460;
        try {
            try {
                setId(messageInputStream.readUnsignedShort());
                setFlags(messageInputStream.readUnsignedShort());
                if (getOperationCode() > 0) {
                    throw new IOException("Received a message with a non standard operation code. Currently unsupported in the specification.");
                }
                int unsignedShort = messageInputStream.readUnsignedShort();
                int unsignedShort2 = messageInputStream.readUnsignedShort();
                int unsignedShort3 = messageInputStream.readUnsignedShort();
                int unsignedShort4 = messageInputStream.readUnsignedShort();
                logger.debug("DNSIncoming() questions:{} answers:{} authorities:{} additionals:{}", Integer.valueOf(unsignedShort), Integer.valueOf(unsignedShort2), Integer.valueOf(unsignedShort3), Integer.valueOf(unsignedShort4));
                if ((unsignedShort * 5) + ((unsignedShort2 + unsignedShort3 + unsignedShort4) * 11) > datagramPacket.getLength()) {
                    throw new IOException("questions:" + unsignedShort + " answers:" + unsignedShort2 + " authorities:" + unsignedShort3 + " additionals:" + unsignedShort4);
                }
                if (unsignedShort > 0) {
                    for (int i = 0; i < unsignedShort; i++) {
                        this._questions.add(readQuestion());
                    }
                }
                if (unsignedShort2 > 0) {
                    for (int i2 = 0; i2 < unsignedShort2; i2++) {
                        DNSRecord answer = readAnswer(address);
                        if (answer != null) {
                            this._answers.add(answer);
                        }
                    }
                }
                if (unsignedShort3 > 0) {
                    for (int i3 = 0; i3 < unsignedShort3; i3++) {
                        DNSRecord answer2 = readAnswer(address);
                        if (answer2 != null) {
                            this._authoritativeAnswers.add(answer2);
                        }
                    }
                }
                if (unsignedShort4 > 0) {
                    for (int i4 = 0; i4 < unsignedShort4; i4++) {
                        DNSRecord answer3 = readAnswer(address);
                        if (answer3 != null) {
                            this._additionals.add(answer3);
                        }
                    }
                }
                if (this._messageInputStream.available() > 0) {
                    throw new IOException("Received a message with the wrong length.");
                }
                try {
                    this._messageInputStream.close();
                } catch (Exception unused) {
                    logger.warn("MessageInputStream close error");
                }
            } catch (Exception e) {
                logger.warn("DNSIncoming() dump " + print(true) + "\n exception ", (Throwable) e);
                IOException iOException = new IOException("DNSIncoming corrupted message");
                iOException.initCause(e);
                throw iOException;
            }
        } catch (Throwable th) {
            try {
                this._messageInputStream.close();
            } catch (Exception unused2) {
                logger.warn("MessageInputStream close error");
            }
            throw th;
        }
    }

    private DNSIncoming(int i, int i2, boolean z, DatagramPacket datagramPacket, long j) {
        super(i, i2, z);
        this._packet = datagramPacket;
        this._messageInputStream = new MessageInputStream(datagramPacket.getData(), datagramPacket.getLength());
        this._receivedTime = j;
    }

    public DNSIncoming clone() {
        DNSIncoming dNSIncoming = new DNSIncoming(getFlags(), getId(), isMulticast(), this._packet, this._receivedTime);
        dNSIncoming._senderUDPPayload = this._senderUDPPayload;
        dNSIncoming._questions.addAll(this._questions);
        dNSIncoming._answers.addAll(this._answers);
        dNSIncoming._authoritativeAnswers.addAll(this._authoritativeAnswers);
        dNSIncoming._additionals.addAll(this._additionals);
        return dNSIncoming;
    }

    private DNSQuestion readQuestion() {
        String name = this._messageInputStream.readName();
        DNSRecordType dNSRecordTypeTypeForIndex = DNSRecordType.typeForIndex(this._messageInputStream.readUnsignedShort());
        if (dNSRecordTypeTypeForIndex == DNSRecordType.TYPE_IGNORE) {
            logger.warn("Could not find record type: {}", print(true));
        }
        int unsignedShort = this._messageInputStream.readUnsignedShort();
        DNSRecordClass dNSRecordClassClassForIndex = DNSRecordClass.classForIndex(unsignedShort);
        return DNSQuestion.newQuestion(name, dNSRecordTypeTypeForIndex, dNSRecordClassClassForIndex, dNSRecordClassClassForIndex.isUnique(unsignedShort));
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0227  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private javax.jmdns.impl.DNSRecord readAnswer(java.net.InetAddress r24) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 802
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.DNSIncoming.readAnswer(java.net.InetAddress):javax.jmdns.impl.DNSRecord");
    }

    /* JADX INFO: renamed from: javax.jmdns.impl.DNSIncoming$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$constants$DNSLabel;
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$constants$DNSOptionCode;
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$constants$DNSRecordType;

        static {
            int[] iArr = new int[DNSRecordType.values().length];
            $SwitchMap$javax$jmdns$impl$constants$DNSRecordType = iArr;
            try {
                iArr[DNSRecordType.TYPE_A.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_AAAA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_CNAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_PTR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_TXT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_SRV.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_HINFO.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_OPT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[DNSOptionCode.values().length];
            $SwitchMap$javax$jmdns$impl$constants$DNSOptionCode = iArr2;
            try {
                iArr2[DNSOptionCode.Owner.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSOptionCode[DNSOptionCode.LLQ.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSOptionCode[DNSOptionCode.NSID.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSOptionCode[DNSOptionCode.UL.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSOptionCode[DNSOptionCode.Unknown.ordinal()] = 5;
            } catch (NoSuchFieldError unused13) {
            }
            int[] iArr3 = new int[DNSLabel.values().length];
            $SwitchMap$javax$jmdns$impl$constants$DNSLabel = iArr3;
            try {
                iArr3[DNSLabel.Standard.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSLabel[DNSLabel.Compressed.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSLabel[DNSLabel.Extended.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSLabel[DNSLabel.Unknown.ordinal()] = 4;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    String print(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(print());
        if (z) {
            int length = this._packet.getLength();
            byte[] bArr = new byte[length];
            System.arraycopy(this._packet.getData(), 0, bArr, 0, length);
            sb.append(print(bArr));
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isQuery() ? "dns[query," : "dns[response,");
        if (this._packet.getAddress() != null) {
            sb.append(this._packet.getAddress().getHostAddress());
        }
        sb.append(CoreConstants.COLON_CHAR);
        sb.append(this._packet.getPort());
        sb.append(", length=");
        sb.append(this._packet.getLength());
        sb.append(", id=0x");
        sb.append(Integer.toHexString(getId()));
        if (getFlags() != 0) {
            sb.append(", flags=0x");
            sb.append(Integer.toHexString(getFlags()));
            if ((getFlags() & 32768) != 0) {
                sb.append(":r");
            }
            if ((getFlags() & 1024) != 0) {
                sb.append(":aa");
            }
            if ((getFlags() & 512) != 0) {
                sb.append(":tc");
            }
        }
        if (getNumberOfQuestions() > 0) {
            sb.append(", questions=");
            sb.append(getNumberOfQuestions());
        }
        if (getNumberOfAnswers() > 0) {
            sb.append(", answers=");
            sb.append(getNumberOfAnswers());
        }
        if (getNumberOfAuthorities() > 0) {
            sb.append(", authorities=");
            sb.append(getNumberOfAuthorities());
        }
        if (getNumberOfAdditionals() > 0) {
            sb.append(", additionals=");
            sb.append(getNumberOfAdditionals());
        }
        if (getNumberOfQuestions() > 0) {
            sb.append("\nquestions:");
            for (DNSQuestion dNSQuestion : this._questions) {
                sb.append("\n\t");
                sb.append(dNSQuestion);
            }
        }
        if (getNumberOfAnswers() > 0) {
            sb.append("\nanswers:");
            for (DNSRecord dNSRecord : this._answers) {
                sb.append("\n\t");
                sb.append(dNSRecord);
            }
        }
        if (getNumberOfAuthorities() > 0) {
            sb.append("\nauthorities:");
            for (DNSRecord dNSRecord2 : this._authoritativeAnswers) {
                sb.append("\n\t");
                sb.append(dNSRecord2);
            }
        }
        if (getNumberOfAdditionals() > 0) {
            sb.append("\nadditionals:");
            for (DNSRecord dNSRecord3 : this._additionals) {
                sb.append("\n\t");
                sb.append(dNSRecord3);
            }
        }
        sb.append(']');
        return sb.toString();
    }

    void append(DNSIncoming dNSIncoming) {
        if (isQuery() && isTruncated() && dNSIncoming.isQuery()) {
            this._questions.addAll(dNSIncoming.getQuestions());
            this._answers.addAll(dNSIncoming.getAnswers());
            this._authoritativeAnswers.addAll(dNSIncoming.getAuthorities());
            this._additionals.addAll(dNSIncoming.getAdditionals());
            return;
        }
        throw new IllegalArgumentException();
    }

    public int elapseSinceArrival() {
        return (int) (System.currentTimeMillis() - this._receivedTime);
    }

    public int getSenderUDPPayload() {
        return this._senderUDPPayload;
    }

    private String _hexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            char[] cArr = _nibbleToHex;
            sb.append(cArr[i / 16]);
            sb.append(cArr[i % 16]);
        }
        return sb.toString();
    }
}
