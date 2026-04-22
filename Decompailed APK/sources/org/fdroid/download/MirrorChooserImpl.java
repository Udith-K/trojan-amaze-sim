package org.fdroid.download;

import io.ktor.client.plugins.ResponseException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import mu.KLogger;
import mu.KotlinLogging;

/* JADX INFO: compiled from: MirrorChooser.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b \u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\r\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0006\u001a\u00020\u00052(\u0010\f\u001a$\b\u0001\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0007H\u0096@¢\u0006\u0004\b\r\u0010\u000eJ3\u0010\u0017\u001a\u00020\u00162\n\u0010\u0011\u001a\u00060\u000fj\u0002`\u00102\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lorg/fdroid/download/MirrorChooserImpl;", "Lorg/fdroid/download/MirrorChooser;", "<init>", "()V", "T", "Lorg/fdroid/download/DownloadRequest;", "downloadRequest", "Lkotlin/Function3;", "Lorg/fdroid/download/Mirror;", "Lio/ktor/http/Url;", "Lkotlin/coroutines/Continuation;", "", "request", "mirrorRequest", "(Lorg/fdroid/download/DownloadRequest;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "mirror", "", "mirrorIndex", "mirrorCount", "", "handleException", "(Ljava/lang/Exception;Lorg/fdroid/download/Mirror;II)V", "Companion", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class MirrorChooserImpl implements MirrorChooser {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KLogger log = KotlinLogging.INSTANCE.logger(new Function0() { // from class: org.fdroid.download.MirrorChooserImpl$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Unit.INSTANCE;
        }
    });

    /* JADX INFO: renamed from: org.fdroid.download.MirrorChooserImpl$mirrorRequest$1, reason: invalid class name */
    /* JADX INFO: compiled from: MirrorChooser.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MirrorChooserImpl.mirrorRequest$suspendImpl(MirrorChooserImpl.this, null, null, this);
        }
    }

    @Override // org.fdroid.download.MirrorChooser
    public <T> Object mirrorRequest(DownloadRequest downloadRequest, Function3 function3, Continuation continuation) {
        return mirrorRequest$suspendImpl(this, downloadRequest, function3, continuation);
    }

    /* JADX INFO: compiled from: MirrorChooser.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/fdroid/download/MirrorChooserImpl$Companion;", "", "<init>", "()V", "log", "Lmu/KLogger;", "getLog", "()Lmu/KLogger;", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        protected final KLogger getLog() {
            return MirrorChooserImpl.log;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x0113 -> B:61:0x012b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ <T> java.lang.Object mirrorRequest$suspendImpl(org.fdroid.download.MirrorChooserImpl r11, org.fdroid.download.DownloadRequest r12, kotlin.jvm.functions.Function3 r13, kotlin.coroutines.Continuation r14) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 384
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.download.MirrorChooserImpl.mirrorRequest$suspendImpl(org.fdroid.download.MirrorChooserImpl, org.fdroid.download.DownloadRequest, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void handleException(final Exception e, Mirror mirror, int mirrorIndex, int mirrorCount) throws Exception {
        Intrinsics.checkNotNullParameter(e, "e");
        Intrinsics.checkNotNullParameter(mirror, "mirror");
        final boolean z = mirrorIndex == mirrorCount - 1;
        log.info(new Function0() { // from class: org.fdroid.download.MirrorChooserImpl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MirrorChooserImpl.handleException$lambda$2(e, z);
            }
        });
        if (z) {
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object handleException$lambda$2(Exception exc, boolean z) {
        String simpleName;
        if (exc instanceof ResponseException) {
            simpleName = ((ResponseException) exc).getResponse().getStatus().toString();
        } else {
            simpleName = Reflection.getOrCreateKotlinClass(exc.getClass()).getSimpleName();
            if (simpleName == null) {
                simpleName = "";
            }
        }
        if (z) {
            return "Last mirror, rethrowing... (" + simpleName + ")";
        }
        return "Trying other mirror now... (" + simpleName + ")";
    }
}
