package io.ktor.util.pipeline;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PipelinePhaseRelation.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PipelinePhaseRelation {
    public /* synthetic */ PipelinePhaseRelation(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private PipelinePhaseRelation() {
    }

    /* JADX INFO: compiled from: PipelinePhaseRelation.kt */
    public static final class After extends PipelinePhaseRelation {
        private final PipelinePhase relativeTo;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public After(PipelinePhase relativeTo) {
            super(null);
            Intrinsics.checkNotNullParameter(relativeTo, "relativeTo");
            this.relativeTo = relativeTo;
        }

        public final PipelinePhase getRelativeTo() {
            return this.relativeTo;
        }
    }

    /* JADX INFO: compiled from: PipelinePhaseRelation.kt */
    public static final class Before extends PipelinePhaseRelation {
        private final PipelinePhase relativeTo;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Before(PipelinePhase relativeTo) {
            super(null);
            Intrinsics.checkNotNullParameter(relativeTo, "relativeTo");
            this.relativeTo = relativeTo;
        }
    }

    /* JADX INFO: compiled from: PipelinePhaseRelation.kt */
    public static final class Last extends PipelinePhaseRelation {
        public static final Last INSTANCE = new Last();

        private Last() {
            super(null);
        }
    }
}
