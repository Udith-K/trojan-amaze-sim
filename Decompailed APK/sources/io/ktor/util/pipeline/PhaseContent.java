package io.ktor.util.pipeline;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: PhaseContent.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PhaseContent {
    public static final Companion Companion = new Companion(null);
    private static final List SharedArrayList = new ArrayList();
    private List interceptors;
    private final PipelinePhase phase;
    private final PipelinePhaseRelation relation;
    private boolean shared;

    public PhaseContent(PipelinePhase phase, PipelinePhaseRelation relation, List interceptors) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(relation, "relation");
        Intrinsics.checkNotNullParameter(interceptors, "interceptors");
        this.phase = phase;
        this.relation = relation;
        this.interceptors = interceptors;
        this.shared = true;
    }

    public final PipelinePhase getPhase() {
        return this.phase;
    }

    public final PipelinePhaseRelation getRelation() {
        return this.relation;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PhaseContent(PipelinePhase phase, PipelinePhaseRelation relation) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(relation, "relation");
        List list = SharedArrayList;
        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.MutableList<kotlin.Function3<io.ktor.util.pipeline.PipelineContext<TSubject of io.ktor.util.pipeline.PhaseContent, Call of io.ktor.util.pipeline.PhaseContent>, TSubject of io.ktor.util.pipeline.PhaseContent, kotlin.coroutines.Continuation<kotlin.Unit>, kotlin.Any?>{ io.ktor.util.pipeline.PipelineKt.PipelineInterceptorFunction<TSubject of io.ktor.util.pipeline.PhaseContent, Call of io.ktor.util.pipeline.PhaseContent> }>");
        this(phase, relation, TypeIntrinsics.asMutableList(list));
        if (!list.isEmpty()) {
            throw new IllegalStateException("The shared empty array list has been modified");
        }
    }

    public final boolean isEmpty() {
        return this.interceptors.isEmpty();
    }

    public final int getSize() {
        return this.interceptors.size();
    }

    public final void addInterceptor(Function3 interceptor) {
        Intrinsics.checkNotNullParameter(interceptor, "interceptor");
        if (this.shared) {
            copyInterceptors();
        }
        this.interceptors.add(interceptor);
    }

    public final void addTo(List destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        List list = this.interceptors;
        if (destination instanceof ArrayList) {
            ((ArrayList) destination).ensureCapacity(destination.size() + list.size());
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            destination.add(list.get(i));
        }
    }

    public final List sharedInterceptors() {
        this.shared = true;
        return this.interceptors;
    }

    public final List copiedInterceptors() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.interceptors);
        return arrayList;
    }

    public String toString() {
        return "Phase `" + this.phase.getName() + "`, " + getSize() + " handlers";
    }

    private final void copyInterceptors() {
        this.interceptors = copiedInterceptors();
        this.shared = false;
    }

    /* JADX INFO: compiled from: PhaseContent.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
