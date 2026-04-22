package io.ktor.utils.io.jvm.javaio;

/* JADX INFO: compiled from: Pollers.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Parking {
    void park(long j);

    void unpark(Object obj);
}
