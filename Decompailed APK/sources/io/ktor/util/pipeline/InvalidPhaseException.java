package io.ktor.util.pipeline;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PipelinePhase.kt */
/* JADX INFO: loaded from: classes.dex */
public final class InvalidPhaseException extends Throwable {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InvalidPhaseException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
