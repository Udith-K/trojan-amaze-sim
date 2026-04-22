package io.ktor.utils.io;

import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Coroutines.kt */
/* JADX INFO: loaded from: classes.dex */
public interface WriterJob extends Job {
    ByteReadChannel getChannel();
}
