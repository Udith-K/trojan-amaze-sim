package io.ktor.util.pipeline;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PipelinePhase.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PipelinePhase {
    private final String name;

    public PipelinePhase(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }

    public String toString() {
        return "Phase('" + this.name + "')";
    }
}
