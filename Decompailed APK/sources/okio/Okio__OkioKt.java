package okio;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Okio.kt */
/* JADX INFO: loaded from: classes2.dex */
abstract /* synthetic */ class Okio__OkioKt {
    public static final BufferedSource buffer(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return new RealBufferedSource(source);
    }

    public static final BufferedSink buffer(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        return new RealBufferedSink(sink);
    }
}
