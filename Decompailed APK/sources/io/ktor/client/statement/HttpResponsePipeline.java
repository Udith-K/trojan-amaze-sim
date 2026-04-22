package io.ktor.client.statement;

import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: HttpResponsePipeline.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpResponsePipeline extends Pipeline {
    private final boolean developmentMode;
    public static final Phases Phases = new Phases(null);
    private static final PipelinePhase Receive = new PipelinePhase("Receive");
    private static final PipelinePhase Parse = new PipelinePhase("Parse");
    private static final PipelinePhase Transform = new PipelinePhase("Transform");
    private static final PipelinePhase State = new PipelinePhase("State");
    private static final PipelinePhase After = new PipelinePhase("After");

    @Override // io.ktor.util.pipeline.Pipeline
    public boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public HttpResponsePipeline(boolean z) {
        super(Receive, Parse, Transform, State, After);
        this.developmentMode = z;
    }

    /* JADX INFO: compiled from: HttpResponsePipeline.kt */
    public static final class Phases {
        public /* synthetic */ Phases(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Phases() {
        }

        public final PipelinePhase getReceive() {
            return HttpResponsePipeline.Receive;
        }

        public final PipelinePhase getParse() {
            return HttpResponsePipeline.Parse;
        }

        public final PipelinePhase getTransform() {
            return HttpResponsePipeline.Transform;
        }
    }
}
