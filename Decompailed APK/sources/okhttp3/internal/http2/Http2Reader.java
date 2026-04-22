package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Hpack;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: compiled from: Http2Reader.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class Http2Reader implements Closeable {
    public static final Companion Companion = new Companion(null);
    private static final Logger logger;
    private final boolean client;
    private final ContinuationSource continuation;
    private final Hpack.Reader hpackReader;
    private final BufferedSource source;

    /* JADX INFO: compiled from: Http2Reader.kt */
    public interface Handler {
        void ackSettings();

        void data(boolean z, int i, BufferedSource bufferedSource, int i2);

        void goAway(int i, ErrorCode errorCode, ByteString byteString);

        void headers(boolean z, int i, int i2, List list);

        void ping(boolean z, int i, int i2);

        void priority(int i, int i2, int i3, boolean z);

        void pushPromise(int i, int i2, List list);

        void rstStream(int i, ErrorCode errorCode);

        void settings(boolean z, Settings settings);

        void windowUpdate(int i, long j);
    }

    public Http2Reader(BufferedSource source, boolean z) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        this.client = z;
        ContinuationSource continuationSource = new ContinuationSource(source);
        this.continuation = continuationSource;
        this.hpackReader = new Hpack.Reader(continuationSource, PKIFailureInfo.certConfirmed, 0, 4, null);
    }

    public final void readConnectionPreface(Handler handler) throws IOException {
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (this.client) {
            if (!nextFrame(true, handler)) {
                throw new IOException("Required SETTINGS preface not received");
            }
            return;
        }
        BufferedSource bufferedSource = this.source;
        ByteString byteString = Http2.CONNECTION_PREFACE;
        ByteString byteString2 = bufferedSource.readByteString(byteString.size());
        Logger logger2 = logger;
        if (logger2.isLoggable(Level.FINE)) {
            logger2.fine(Util.format("<< CONNECTION " + byteString2.hex(), new Object[0]));
        }
        if (Intrinsics.areEqual(byteString, byteString2)) {
            return;
        }
        throw new IOException("Expected a connection header but was " + byteString2.utf8());
    }

    public final boolean nextFrame(boolean z, Handler handler) throws IOException {
        Intrinsics.checkNotNullParameter(handler, "handler");
        try {
            this.source.require(9L);
            int medium = Util.readMedium(this.source);
            if (medium > 16384) {
                throw new IOException("FRAME_SIZE_ERROR: " + medium);
            }
            int iAnd = Util.and(this.source.readByte(), GF2Field.MASK);
            int iAnd2 = Util.and(this.source.readByte(), GF2Field.MASK);
            int i = this.source.readInt() & Integer.MAX_VALUE;
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.fine(Http2.INSTANCE.frameLog(true, i, medium, iAnd, iAnd2));
            }
            if (z && iAnd != 4) {
                throw new IOException("Expected a SETTINGS frame but was " + Http2.INSTANCE.formattedType$okhttp(iAnd));
            }
            switch (iAnd) {
                case 0:
                    readData(handler, medium, iAnd2, i);
                    return true;
                case 1:
                    readHeaders(handler, medium, iAnd2, i);
                    return true;
                case 2:
                    readPriority(handler, medium, iAnd2, i);
                    return true;
                case 3:
                    readRstStream(handler, medium, iAnd2, i);
                    return true;
                case 4:
                    readSettings(handler, medium, iAnd2, i);
                    return true;
                case 5:
                    readPushPromise(handler, medium, iAnd2, i);
                    return true;
                case 6:
                    readPing(handler, medium, iAnd2, i);
                    return true;
                case 7:
                    readGoAway(handler, medium, iAnd2, i);
                    return true;
                case 8:
                    readWindowUpdate(handler, medium, iAnd2, i);
                    return true;
                default:
                    this.source.skip(medium);
                    return true;
            }
        } catch (EOFException unused) {
            return false;
        }
    }

    private final void readHeaders(Handler handler, int i, int i2, int i3) throws IOException {
        if (i3 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
        }
        boolean z = (i2 & 1) != 0;
        int iAnd = (i2 & 8) != 0 ? Util.and(this.source.readByte(), GF2Field.MASK) : 0;
        if ((i2 & 32) != 0) {
            readPriority(handler, i3);
            i -= 5;
        }
        handler.headers(z, i3, -1, readHeaderBlock(Companion.lengthWithoutPadding(i, i2, iAnd), iAnd, i2, i3));
    }

    private final List readHeaderBlock(int i, int i2, int i3, int i4) throws IOException {
        this.continuation.setLeft(i);
        ContinuationSource continuationSource = this.continuation;
        continuationSource.setLength(continuationSource.getLeft());
        this.continuation.setPadding(i2);
        this.continuation.setFlags(i3);
        this.continuation.setStreamId(i4);
        this.hpackReader.readHeaders();
        return this.hpackReader.getAndResetHeaderList();
    }

    private final void readData(Handler handler, int i, int i2, int i3) throws IOException {
        if (i3 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_DATA streamId == 0");
        }
        boolean z = (i2 & 1) != 0;
        if ((i2 & 32) != 0) {
            throw new IOException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA");
        }
        int iAnd = (i2 & 8) != 0 ? Util.and(this.source.readByte(), GF2Field.MASK) : 0;
        handler.data(z, i3, this.source, Companion.lengthWithoutPadding(i, i2, iAnd));
        this.source.skip(iAnd);
    }

    private final void readPriority(Handler handler, int i, int i2, int i3) throws IOException {
        if (i == 5) {
            if (i3 == 0) {
                throw new IOException("TYPE_PRIORITY streamId == 0");
            }
            readPriority(handler, i3);
        } else {
            throw new IOException("TYPE_PRIORITY length: " + i + " != 5");
        }
    }

    private final void readPriority(Handler handler, int i) {
        int i2 = this.source.readInt();
        handler.priority(i, i2 & Integer.MAX_VALUE, Util.and(this.source.readByte(), GF2Field.MASK) + 1, (Integer.MIN_VALUE & i2) != 0);
    }

    private final void readRstStream(Handler handler, int i, int i2, int i3) throws IOException {
        if (i != 4) {
            throw new IOException("TYPE_RST_STREAM length: " + i + " != 4");
        }
        if (i3 == 0) {
            throw new IOException("TYPE_RST_STREAM streamId == 0");
        }
        int i4 = this.source.readInt();
        ErrorCode errorCodeFromHttp2 = ErrorCode.Companion.fromHttp2(i4);
        if (errorCodeFromHttp2 == null) {
            throw new IOException("TYPE_RST_STREAM unexpected error code: " + i4);
        }
        handler.rstStream(i3, errorCodeFromHttp2);
    }

    private final void readSettings(Handler handler, int i, int i2, int i3) throws IOException {
        int i4;
        if (i3 != 0) {
            throw new IOException("TYPE_SETTINGS streamId != 0");
        }
        if ((i2 & 1) != 0) {
            if (i != 0) {
                throw new IOException("FRAME_SIZE_ERROR ack frame should be empty!");
            }
            handler.ackSettings();
            return;
        }
        if (i % 6 != 0) {
            throw new IOException("TYPE_SETTINGS length % 6 != 0: " + i);
        }
        Settings settings = new Settings();
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, i), 6);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (true) {
                int iAnd = Util.and(this.source.readShort(), 65535);
                i4 = this.source.readInt();
                if (iAnd != 2) {
                    if (iAnd == 3) {
                        iAnd = 4;
                    } else if (iAnd != 4) {
                        if (iAnd == 5 && (i4 < 16384 || i4 > 16777215)) {
                            break;
                        }
                    } else {
                        if (i4 < 0) {
                            throw new IOException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1");
                        }
                        iAnd = 7;
                    }
                } else if (i4 != 0 && i4 != 1) {
                    throw new IOException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1");
                }
                settings.set(iAnd, i4);
                if (first == last) {
                    break;
                } else {
                    first += step;
                }
            }
            throw new IOException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: " + i4);
        }
        handler.settings(false, settings);
    }

    private final void readPushPromise(Handler handler, int i, int i2, int i3) throws IOException {
        if (i3 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
        }
        int iAnd = (i2 & 8) != 0 ? Util.and(this.source.readByte(), GF2Field.MASK) : 0;
        handler.pushPromise(i3, this.source.readInt() & Integer.MAX_VALUE, readHeaderBlock(Companion.lengthWithoutPadding(i - 4, i2, iAnd), iAnd, i2, i3));
    }

    private final void readPing(Handler handler, int i, int i2, int i3) throws IOException {
        if (i != 8) {
            throw new IOException("TYPE_PING length != 8: " + i);
        }
        if (i3 != 0) {
            throw new IOException("TYPE_PING streamId != 0");
        }
        handler.ping((i2 & 1) != 0, this.source.readInt(), this.source.readInt());
    }

    private final void readGoAway(Handler handler, int i, int i2, int i3) throws IOException {
        if (i < 8) {
            throw new IOException("TYPE_GOAWAY length < 8: " + i);
        }
        if (i3 != 0) {
            throw new IOException("TYPE_GOAWAY streamId != 0");
        }
        int i4 = this.source.readInt();
        int i5 = this.source.readInt();
        int i6 = i - 8;
        ErrorCode errorCodeFromHttp2 = ErrorCode.Companion.fromHttp2(i5);
        if (errorCodeFromHttp2 == null) {
            throw new IOException("TYPE_GOAWAY unexpected error code: " + i5);
        }
        ByteString byteString = ByteString.EMPTY;
        if (i6 > 0) {
            byteString = this.source.readByteString(i6);
        }
        handler.goAway(i4, errorCodeFromHttp2, byteString);
    }

    private final void readWindowUpdate(Handler handler, int i, int i2, int i3) throws IOException {
        if (i != 4) {
            throw new IOException("TYPE_WINDOW_UPDATE length !=4: " + i);
        }
        long jAnd = Util.and(this.source.readInt(), 2147483647L);
        if (jAnd == 0) {
            throw new IOException("windowSizeIncrement was 0");
        }
        handler.windowUpdate(i3, jAnd);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.source.close();
    }

    /* JADX INFO: compiled from: Http2Reader.kt */
    public static final class ContinuationSource implements Source {
        private int flags;
        private int left;
        private int length;
        private int padding;
        private final BufferedSource source;
        private int streamId;

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        public ContinuationSource(BufferedSource source) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.source = source;
        }

        public final void setLength(int i) {
            this.length = i;
        }

        public final void setFlags(int i) {
            this.flags = i;
        }

        public final void setStreamId(int i) {
            this.streamId = i;
        }

        public final int getLeft() {
            return this.left;
        }

        public final void setLeft(int i) {
            this.left = i;
        }

        public final void setPadding(int i) {
            this.padding = i;
        }

        @Override // okio.Source
        public long read(Buffer sink, long j) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            while (true) {
                int i = this.left;
                if (i == 0) {
                    this.source.skip(this.padding);
                    this.padding = 0;
                    if ((this.flags & 4) != 0) {
                        return -1L;
                    }
                    readContinuationHeader();
                } else {
                    long j2 = this.source.read(sink, Math.min(j, i));
                    if (j2 == -1) {
                        return -1L;
                    }
                    this.left -= (int) j2;
                    return j2;
                }
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.source.timeout();
        }

        private final void readContinuationHeader() throws IOException {
            int i = this.streamId;
            int medium = Util.readMedium(this.source);
            this.left = medium;
            this.length = medium;
            int iAnd = Util.and(this.source.readByte(), GF2Field.MASK);
            this.flags = Util.and(this.source.readByte(), GF2Field.MASK);
            Companion companion = Http2Reader.Companion;
            if (companion.getLogger().isLoggable(Level.FINE)) {
                companion.getLogger().fine(Http2.INSTANCE.frameLog(true, this.streamId, this.length, iAnd, this.flags));
            }
            int i2 = this.source.readInt() & Integer.MAX_VALUE;
            this.streamId = i2;
            if (iAnd == 9) {
                if (i2 != i) {
                    throw new IOException("TYPE_CONTINUATION streamId changed");
                }
            } else {
                throw new IOException(iAnd + " != TYPE_CONTINUATION");
            }
        }
    }

    /* JADX INFO: compiled from: Http2Reader.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Logger getLogger() {
            return Http2Reader.logger;
        }

        public final int lengthWithoutPadding(int i, int i2, int i3) throws IOException {
            if ((i2 & 8) != 0) {
                i--;
            }
            if (i3 <= i) {
                return i - i3;
            }
            throw new IOException("PROTOCOL_ERROR padding " + i3 + " > remaining length " + i);
        }
    }

    static {
        Logger logger2 = Logger.getLogger(Http2.class.getName());
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(Http2::class.java.name)");
        logger = logger2;
    }
}
