package io.ktor.utils.io.jvm.javaio;

import java.util.concurrent.locks.LockSupport;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Pollers.kt */
/* JADX INFO: loaded from: classes.dex */
final class DefaultParking implements Parking {
    public static final DefaultParking INSTANCE = new DefaultParking();

    private DefaultParking() {
    }

    @Override // io.ktor.utils.io.jvm.javaio.Parking
    public void park(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        LockSupport.parkNanos(j);
    }

    @Override // io.ktor.utils.io.jvm.javaio.Parking
    public void unpark(Thread token) {
        Intrinsics.checkNotNullParameter(token, "token");
        LockSupport.unpark(token);
    }
}
