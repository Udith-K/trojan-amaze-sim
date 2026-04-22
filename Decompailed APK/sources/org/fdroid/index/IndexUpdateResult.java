package org.fdroid.index;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IndexUpdater.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lorg/fdroid/index/IndexUpdateResult;", "", "<init>", "()V", "Unchanged", "Processed", "NotFound", "Error", "Lorg/fdroid/index/IndexUpdateResult$Error;", "Lorg/fdroid/index/IndexUpdateResult$NotFound;", "Lorg/fdroid/index/IndexUpdateResult$Processed;", "Lorg/fdroid/index/IndexUpdateResult$Unchanged;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class IndexUpdateResult {
    public /* synthetic */ IndexUpdateResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: IndexUpdater.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/fdroid/index/IndexUpdateResult$Unchanged;", "Lorg/fdroid/index/IndexUpdateResult;", "<init>", "()V", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Unchanged extends IndexUpdateResult {
        public static final Unchanged INSTANCE = new Unchanged();

        private Unchanged() {
            super(null);
        }
    }

    private IndexUpdateResult() {
    }

    /* JADX INFO: compiled from: IndexUpdater.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/fdroid/index/IndexUpdateResult$Processed;", "Lorg/fdroid/index/IndexUpdateResult;", "<init>", "()V", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Processed extends IndexUpdateResult {
        public static final Processed INSTANCE = new Processed();

        private Processed() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: IndexUpdater.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/fdroid/index/IndexUpdateResult$NotFound;", "Lorg/fdroid/index/IndexUpdateResult;", "<init>", "()V", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class NotFound extends IndexUpdateResult {
        public static final NotFound INSTANCE = new NotFound();

        private NotFound() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: IndexUpdater.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/fdroid/index/IndexUpdateResult$Error;", "Lorg/fdroid/index/IndexUpdateResult;", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "<init>", "(Ljava/lang/Exception;)V", "getE", "()Ljava/lang/Exception;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Error extends IndexUpdateResult {
        private final Exception e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Error(Exception e) {
            super(null);
            Intrinsics.checkNotNullParameter(e, "e");
            this.e = e;
        }

        public final Exception getE() {
            return this.e;
        }
    }
}
