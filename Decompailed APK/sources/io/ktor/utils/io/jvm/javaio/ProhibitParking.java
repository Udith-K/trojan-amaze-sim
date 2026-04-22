package io.ktor.utils.io.jvm.javaio;

import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Pollers.kt */
/* JADX INFO: loaded from: classes.dex */
final class ProhibitParking implements Parking {
    public static final ProhibitParking INSTANCE = new ProhibitParking();

    private ProhibitParking() {
    }

    @Override // io.ktor.utils.io.jvm.javaio.Parking
    public void park(long j) {
        fail();
        throw new KotlinNothingValueException();
    }

    @Override // io.ktor.utils.io.jvm.javaio.Parking
    public void unpark(Thread token) {
        Intrinsics.checkNotNullParameter(token, "token");
        DefaultParking.INSTANCE.unpark(token);
    }

    private final Void fail() {
        throw new UnsupportedOperationException("Parking is prohibited on this thread. Most likely you are using blocking operation on the wrong thread/dispatcher that doesn't allow blocking. Consider wrapping you blocking code withContext(Dispatchers.IO) {...}.");
    }
}
