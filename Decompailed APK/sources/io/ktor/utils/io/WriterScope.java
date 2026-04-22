package io.ktor.utils.io;

import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Coroutines.kt */
/* JADX INFO: loaded from: classes.dex */
public interface WriterScope extends CoroutineScope {
    ByteWriteChannel getChannel();
}
