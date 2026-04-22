package io.ktor.client.request;

import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: HttpRequestPipeline.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpSendPipeline extends Pipeline {
    private final boolean developmentMode;
    public static final Phases Phases = new Phases(null);
    private static final PipelinePhase Before = new PipelinePhase("Before");
    private static final PipelinePhase State = new PipelinePhase("State");
    private static final PipelinePhase Monitoring = new PipelinePhase("Monitoring");
    private static final PipelinePhase Engine = new PipelinePhase("Engine");
    private static final PipelinePhase Receive = new PipelinePhase("Receive");

    @Override // io.ktor.util.pipeline.Pipeline
    public boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public HttpSendPipeline(boolean z) {
        super(Before, State, Monitoring, Engine, Receive);
        this.developmentMode = z;
    }

    /* JADX INFO: compiled from: HttpRequestPipeline.kt */
    public static final class Phases {
        public /* synthetic */ Phases(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Phases() {
        }

        public final PipelinePhase getEngine() {
            return HttpSendPipeline.Engine;
        }

        public final PipelinePhase getReceive() {
            return HttpSendPipeline.Receive;
        }
    }
}
