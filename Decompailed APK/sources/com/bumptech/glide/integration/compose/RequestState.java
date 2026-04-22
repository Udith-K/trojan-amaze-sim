package com.bumptech.glide.integration.compose;

import ch.qos.logback.core.CoreConstants;
import com.bumptech.glide.load.DataSource;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GlideImage.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RequestState {
    public /* synthetic */ RequestState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private RequestState() {
    }

    /* JADX INFO: compiled from: GlideImage.kt */
    public static final class Loading extends RequestState {
        public static final Loading INSTANCE = new Loading();

        private Loading() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: GlideImage.kt */
    public static final class Success extends RequestState {
        private final DataSource dataSource;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Success) && this.dataSource == ((Success) obj).dataSource;
        }

        public int hashCode() {
            return this.dataSource.hashCode();
        }

        public String toString() {
            return "Success(dataSource=" + this.dataSource + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Success(DataSource dataSource) {
            super(null);
            Intrinsics.checkNotNullParameter(dataSource, "dataSource");
            this.dataSource = dataSource;
        }
    }

    /* JADX INFO: compiled from: GlideImage.kt */
    public static final class Failure extends RequestState {
        public static final Failure INSTANCE = new Failure();

        private Failure() {
            super(null);
        }
    }
}
