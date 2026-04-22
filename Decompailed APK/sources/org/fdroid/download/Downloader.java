package org.fdroid.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KotlinLogging;
import org.fdroid.IndexFile;
import org.fdroid.fdroid.HashUtilsKt;
import org.fdroid.fdroid.ProgressListener;

/* JADX INFO: compiled from: Downloader.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 72\u00020\u0001:\u00017B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0016\u001a\u00020\u0017H&J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0013H$J\u001e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0094@¢\u0006\u0002\u0010\u001eJ\b\u0010\u001f\u001a\u00020 H$J\b\u0010!\u001a\u00020\u0013H'J\b\u0010\"\u001a\u00020\u0017H&J\u000e\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u0015J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0013H\u0004J\u0016\u0010'\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0013H\u0084@¢\u0006\u0002\u0010(J\u0018\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020,H\u0002J \u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u00020 2\u0006\u00100\u001a\u00020 H\u0002J\u0006\u00101\u001a\u00020\u0017J\u0006\u00102\u001a\u00020\u0013J\b\u00103\u001a\u00020\u0017H\u0002J\u0014\u00104\u001a\u00020\u0017*\u00020\u00052\u0006\u00105\u001a\u000206H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\u0004\u001a\u00020\u00058\u0004X\u0085\u0004¢\u0006\u0002\n\u0000R&\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lorg/fdroid/download/Downloader;", "", "indexFile", "Lorg/fdroid/IndexFile;", "outputFile", "Ljava/io/File;", "<init>", "(Lorg/fdroid/IndexFile;Ljava/io/File;)V", "getIndexFile", "()Lorg/fdroid/IndexFile;", "cacheTag", "", "getCacheTag$annotations", "()V", "getCacheTag", "()Ljava/lang/String;", "setCacheTag", "(Ljava/lang/String;)V", "cancelled", "", "progressListener", "Lorg/fdroid/fdroid/ProgressListener;", "download", "", "getInputStream", "Ljava/io/InputStream;", "resumable", "getBytes", "receiver", "Lorg/fdroid/download/BytesReceiver;", "(ZLorg/fdroid/download/BytesReceiver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "totalDownloadSize", "", "hasChanged", "close", "setListener", "listener", "downloadFromStream", "isResume", "downloadFromBytesReceiver", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyInputToOutputStream", "input", "output", "Ljava/io/OutputStream;", "reportProgress", "lastTimeReported", "bytesRead", "bytesTotal", "cancelDownload", "wasCancelled", "throwExceptionIfInterrupted", "initDigest", "messageDigest", "Ljava/security/MessageDigest;", "Companion", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class Downloader {
    private static final KLogger log = KotlinLogging.INSTANCE.logger(new Function0() { // from class: org.fdroid.download.Downloader$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Unit.INSTANCE;
        }
    });
    private String cacheTag;
    private volatile boolean cancelled;
    private final IndexFile indexFile;
    protected final File outputFile;
    private volatile ProgressListener progressListener;

    /* JADX INFO: renamed from: org.fdroid.download.Downloader$downloadFromBytesReceiver$1, reason: invalid class name */
    /* JADX INFO: compiled from: Downloader.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Downloader.this.downloadFromBytesReceiver(false, this);
        }
    }

    @Deprecated
    public static /* synthetic */ void getCacheTag$annotations() {
    }

    public abstract void close();

    public abstract void download() throws InterruptedException, IOException, NotFoundException;

    protected Object getBytes(boolean z, BytesReceiver bytesReceiver, Continuation continuation) {
        return getBytes$suspendImpl(this, z, bytesReceiver, continuation);
    }

    protected abstract InputStream getInputStream(boolean resumable) throws IOException, NotFoundException;

    @Deprecated
    public abstract boolean hasChanged();

    protected abstract long totalDownloadSize();

    public Downloader(IndexFile indexFile, File outputFile) {
        Intrinsics.checkNotNullParameter(indexFile, "indexFile");
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        this.indexFile = indexFile;
        this.outputFile = outputFile;
    }

    protected final IndexFile getIndexFile() {
        return this.indexFile;
    }

    public final String getCacheTag() {
        return this.cacheTag;
    }

    public final void setCacheTag(String str) {
        this.cacheTag = str;
    }

    static /* synthetic */ Object getBytes$suspendImpl(Downloader downloader, boolean z, BytesReceiver bytesReceiver, Continuation continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    public final void setListener(ProgressListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.progressListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object downloadFromStream$lambda$0() {
        return "Downloading from stream";
    }

    protected final void downloadFromStream(boolean isResume) throws InterruptedException, IOException {
        log.debug(new Function0() { // from class: org.fdroid.download.Downloader$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Downloader.downloadFromStream$lambda$0();
            }
        });
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.outputFile, isResume);
            try {
                InputStream inputStream = getInputStream(isResume);
                try {
                    throwExceptionIfInterrupted();
                    copyInputToOutputStream(inputStream, fileOutputStream);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(inputStream, null);
                    CloseableKt.closeFinally(fileOutputStream, null);
                    throwExceptionIfInterrupted();
                } finally {
                }
            } finally {
            }
        } finally {
            close();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final java.lang.Object downloadFromBytesReceiver(boolean r20, kotlin.coroutines.Continuation r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 265
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.download.Downloader.downloadFromBytesReceiver(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void copyInputToOutputStream(InputStream input, OutputStream output) throws Throwable {
        MessageDigest messageDigest = this.indexFile.getSha256() == null ? null : MessageDigest.getInstance("SHA-256");
        try {
            long length = this.outputFile.length();
            if (length > 0 && messageDigest != null) {
                initDigest(this.outputFile, messageDigest);
            }
            long j = totalDownloadSize();
            byte[] bArr = new byte[8192];
            int i = input.read(bArr);
            long jReportProgress = 0;
            long j2 = length;
            while (i >= 0) {
                throwExceptionIfInterrupted();
                try {
                    output.write(bArr, 0, i);
                    if (messageDigest != null) {
                        messageDigest.update(bArr, 0, i);
                    }
                    long j3 = j2 + ((long) i);
                    jReportProgress = reportProgress(jReportProgress, j3, j);
                    i = input.read(bArr);
                    j2 = j3;
                } catch (Throwable th) {
                    th = th;
                    output.flush();
                    this.progressListener = null;
                    throw th;
                }
            }
            String sha256 = this.indexFile.getSha256();
            if (sha256 != null && !HashUtilsKt.isMatching(messageDigest, sha256)) {
                throw new IOException("Hash not matching");
            }
            reportProgress(0L, j2, j);
            output.flush();
            this.progressListener = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long reportProgress(long lastTimeReported, final long bytesRead, final long bytesTotal) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - lastTimeReported <= 100) {
            return lastTimeReported;
        }
        log.debug(new Function0() { // from class: org.fdroid.download.Downloader$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Downloader.reportProgress$lambda$6(bytesRead, bytesTotal);
            }
        });
        ProgressListener progressListener = this.progressListener;
        if (progressListener != null) {
            progressListener.onProgress(bytesRead, bytesTotal);
        }
        return jCurrentTimeMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object reportProgress$lambda$6(long j, long j2) {
        return "onProgress: " + j + "/" + j2;
    }

    public final void cancelDownload() {
        this.cancelled = true;
    }

    /* JADX INFO: renamed from: wasCancelled, reason: from getter */
    public final boolean getCancelled() {
        return this.cancelled;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void throwExceptionIfInterrupted() throws InterruptedException {
        if (this.cancelled) {
            log.info(new Function0() { // from class: org.fdroid.download.Downloader$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Downloader.throwExceptionIfInterrupted$lambda$7();
                }
            });
            Thread.currentThread().interrupt();
            throw new InterruptedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object throwExceptionIfInterrupted$lambda$7() {
        return "Received interrupt, cancelling download";
    }

    private final void initDigest(File file, MessageDigest messageDigest) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] bArr = new byte[8192];
            for (int i = fileInputStream.read(bArr); i >= 0; i = fileInputStream.read(bArr)) {
                messageDigest.update(bArr, 0, i);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileInputStream, null);
        } finally {
        }
    }
}
