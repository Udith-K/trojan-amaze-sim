package okhttp3.internal.http2;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;

/* JADX INFO: compiled from: PushObserver.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface PushObserver {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final PushObserver CANCEL = new Companion.PushObserverCancel();

    boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z);

    boolean onHeaders(int i, List list, boolean z);

    boolean onRequest(int i, List list);

    void onReset(int i, ErrorCode errorCode);

    /* JADX INFO: compiled from: PushObserver.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        /* JADX INFO: compiled from: PushObserver.kt */
        private static final class PushObserverCancel implements PushObserver {
            @Override // okhttp3.internal.http2.PushObserver
            public boolean onHeaders(int i, List responseHeaders, boolean z) {
                Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
                return true;
            }

            @Override // okhttp3.internal.http2.PushObserver
            public boolean onRequest(int i, List requestHeaders) {
                Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
                return true;
            }

            @Override // okhttp3.internal.http2.PushObserver
            public void onReset(int i, ErrorCode errorCode) {
                Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            }

            @Override // okhttp3.internal.http2.PushObserver
            public boolean onData(int i, BufferedSource source, int i2, boolean z) {
                Intrinsics.checkNotNullParameter(source, "source");
                source.skip(i2);
                return true;
            }
        }
    }
}
