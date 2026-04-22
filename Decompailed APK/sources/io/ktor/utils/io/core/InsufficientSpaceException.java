package io.ktor.utils.io.core;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Buffer.kt */
/* JADX INFO: loaded from: classes.dex */
public final class InsufficientSpaceException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InsufficientSpaceException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public InsufficientSpaceException(String name, int i, int i2) {
        this("Not enough free space to write " + name + " of " + i + " bytes, available " + i2 + " bytes.");
        Intrinsics.checkNotNullParameter(name, "name");
    }
}
