package io.ktor.client.statement;

import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: HttpResponsePipeline.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpReceivePipeline extends Pipeline {
    private final boolean developmentMode;
    public static final Phases Phases = new Phases(null);
    private static final PipelinePhase Before = new PipelinePhase("Before");
    private static final PipelinePhase State = new PipelinePhase("State");
    private static final PipelinePhase After = new PipelinePhase("After");

    @Override // io.ktor.util.pipeline.Pipeline
    public boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    /* JADX INFO: compiled from: HttpResponsePipeline.kt */
    public static final class Phases {
        public /* synthetic */ Phases(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Phases() {
        }

        public final PipelinePhase getAfter() {
            return HttpReceivePipeline.After;
        }
    }

    public HttpReceivePipeline(boolean z) {
        super(Before, State, After);
        this.developmentMode = z;
    }
}
