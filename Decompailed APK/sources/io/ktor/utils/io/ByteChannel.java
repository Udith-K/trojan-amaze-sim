package io.ktor.utils.io;

import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: ByteChannelCtor.kt */
/* JADX INFO: loaded from: classes.dex */
public interface ByteChannel extends ByteReadChannel, ByteWriteChannel {
    void attachJob(Job job);
}
