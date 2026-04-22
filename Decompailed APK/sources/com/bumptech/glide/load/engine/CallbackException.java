package com.bumptech.glide.load.engine;

/* JADX INFO: loaded from: classes.dex */
final class CallbackException extends RuntimeException {
    CallbackException(Throwable th) {
        super("Unexpected exception thrown by non-Glide code", th);
    }
}
