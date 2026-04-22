package io.ktor.util.pipeline;

import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.pipeline.PipelinePhaseRelation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: Pipeline.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Pipeline {
    private volatile /* synthetic */ Object _interceptors;
    private final Attributes attributes;
    private boolean interceptorsListShared;
    private PipelinePhase interceptorsListSharedPhase;
    private int interceptorsQuantity;
    private final List phasesRaw;

    public void afterIntercepted() {
    }

    public abstract boolean getDevelopmentMode();

    public Pipeline(PipelinePhase... phases) {
        Intrinsics.checkNotNullParameter(phases, "phases");
        this.attributes = AttributesJvmKt.Attributes(true);
        this.phasesRaw = CollectionsKt.mutableListOf(Arrays.copyOf(phases, phases.length));
        this._interceptors = null;
    }

    private final List getInterceptors() {
        return (List) this._interceptors;
    }

    private final void setInterceptors(List list) {
        this._interceptors = list;
    }

    public final Object execute(Object obj, Object obj2, Continuation continuation) {
        return createContext(obj, obj2, continuation.getContext()).execute$ktor_utils(obj2, continuation);
    }

    public final void insertPhaseAfter(PipelinePhase reference, PipelinePhase phase) throws InvalidPhaseException {
        PipelinePhaseRelation relation;
        PipelinePhase relativeTo;
        Intrinsics.checkNotNullParameter(reference, "reference");
        Intrinsics.checkNotNullParameter(phase, "phase");
        if (hasPhase(phase)) {
            return;
        }
        int iFindPhaseIndex = findPhaseIndex(reference);
        if (iFindPhaseIndex == -1) {
            throw new InvalidPhaseException("Phase " + reference + " was not registered for this pipeline");
        }
        int i = iFindPhaseIndex + 1;
        int lastIndex = CollectionsKt.getLastIndex(this.phasesRaw);
        if (i <= lastIndex) {
            while (true) {
                Object obj = this.phasesRaw.get(i);
                PhaseContent phaseContent = obj instanceof PhaseContent ? (PhaseContent) obj : null;
                if (phaseContent != null && (relation = phaseContent.getRelation()) != null) {
                    PipelinePhaseRelation.After after = relation instanceof PipelinePhaseRelation.After ? (PipelinePhaseRelation.After) relation : null;
                    if (after != null && (relativeTo = after.getRelativeTo()) != null && Intrinsics.areEqual(relativeTo, reference)) {
                        iFindPhaseIndex = i;
                    }
                    if (i == lastIndex) {
                        break;
                    } else {
                        i++;
                    }
                } else {
                    break;
                }
            }
        }
        this.phasesRaw.add(iFindPhaseIndex + 1, new PhaseContent(phase, new PipelinePhaseRelation.After(reference)));
    }

    public final void insertPhaseBefore(PipelinePhase reference, PipelinePhase phase) throws InvalidPhaseException {
        Intrinsics.checkNotNullParameter(reference, "reference");
        Intrinsics.checkNotNullParameter(phase, "phase");
        if (hasPhase(phase)) {
            return;
        }
        int iFindPhaseIndex = findPhaseIndex(reference);
        if (iFindPhaseIndex == -1) {
            throw new InvalidPhaseException("Phase " + reference + " was not registered for this pipeline");
        }
        this.phasesRaw.add(iFindPhaseIndex, new PhaseContent(phase, new PipelinePhaseRelation.Before(reference)));
    }

    public final void intercept(PipelinePhase phase, Function3 block) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(block, "block");
        PhaseContent phaseContentFindPhase = findPhase(phase);
        if (phaseContentFindPhase == null) {
            throw new InvalidPhaseException("Phase " + phase + " was not registered for this pipeline");
        }
        if (tryAddToPhaseFastPath(phase, block)) {
            this.interceptorsQuantity++;
            return;
        }
        phaseContentFindPhase.addInterceptor(block);
        this.interceptorsQuantity++;
        resetInterceptorsList();
        afterIntercepted();
    }

    private final PipelineContext createContext(Object obj, Object obj2, CoroutineContext coroutineContext) {
        return PipelineContextKt.pipelineContextFor(obj, sharedInterceptorsList(), obj2, coroutineContext, getDevelopmentMode());
    }

    private final PhaseContent findPhase(PipelinePhase pipelinePhase) {
        List list = this.phasesRaw;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (obj == pipelinePhase) {
                PhaseContent phaseContent = new PhaseContent(pipelinePhase, PipelinePhaseRelation.Last.INSTANCE);
                list.set(i, phaseContent);
                return phaseContent;
            }
            if (obj instanceof PhaseContent) {
                PhaseContent phaseContent2 = (PhaseContent) obj;
                if (phaseContent2.getPhase() == pipelinePhase) {
                    return phaseContent2;
                }
            }
        }
        return null;
    }

    private final int findPhaseIndex(PipelinePhase pipelinePhase) {
        List list = this.phasesRaw;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (obj == pipelinePhase || ((obj instanceof PhaseContent) && ((PhaseContent) obj).getPhase() == pipelinePhase)) {
                return i;
            }
        }
        return -1;
    }

    private final boolean hasPhase(PipelinePhase pipelinePhase) {
        List list = this.phasesRaw;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (obj == pipelinePhase) {
                return true;
            }
            if ((obj instanceof PhaseContent) && ((PhaseContent) obj).getPhase() == pipelinePhase) {
                return true;
            }
        }
        return false;
    }

    private final List cacheInterceptors() {
        int lastIndex;
        int i = this.interceptorsQuantity;
        if (i == 0) {
            notSharedInterceptorsList(CollectionsKt.emptyList());
            return CollectionsKt.emptyList();
        }
        List list = this.phasesRaw;
        int i2 = 0;
        if (i == 1 && (lastIndex = CollectionsKt.getLastIndex(list)) >= 0) {
            int i3 = 0;
            while (true) {
                Object obj = list.get(i3);
                PhaseContent phaseContent = obj instanceof PhaseContent ? (PhaseContent) obj : null;
                if (phaseContent != null && !phaseContent.isEmpty()) {
                    List listSharedInterceptors = phaseContent.sharedInterceptors();
                    setInterceptorsListFromPhase(phaseContent);
                    return listSharedInterceptors;
                }
                if (i3 == lastIndex) {
                    break;
                }
                i3++;
            }
        }
        ArrayList arrayList = new ArrayList();
        int lastIndex2 = CollectionsKt.getLastIndex(list);
        if (lastIndex2 >= 0) {
            while (true) {
                Object obj2 = list.get(i2);
                PhaseContent phaseContent2 = obj2 instanceof PhaseContent ? (PhaseContent) obj2 : null;
                if (phaseContent2 != null) {
                    phaseContent2.addTo(arrayList);
                }
                if (i2 == lastIndex2) {
                    break;
                }
                i2++;
            }
        }
        notSharedInterceptorsList(arrayList);
        return arrayList;
    }

    private final List sharedInterceptorsList() {
        if (getInterceptors() == null) {
            cacheInterceptors();
        }
        this.interceptorsListShared = true;
        List interceptors = getInterceptors();
        Intrinsics.checkNotNull(interceptors);
        return interceptors;
    }

    private final void resetInterceptorsList() {
        setInterceptors(null);
        this.interceptorsListShared = false;
        this.interceptorsListSharedPhase = null;
    }

    private final void notSharedInterceptorsList(List list) {
        setInterceptors(list);
        this.interceptorsListShared = false;
        this.interceptorsListSharedPhase = null;
    }

    private final void setInterceptorsListFromPhase(PhaseContent phaseContent) {
        setInterceptors(phaseContent.sharedInterceptors());
        this.interceptorsListShared = false;
        this.interceptorsListSharedPhase = phaseContent.getPhase();
    }

    private final boolean tryAddToPhaseFastPath(PipelinePhase pipelinePhase, Function3 function3) {
        List interceptors = getInterceptors();
        if (this.phasesRaw.isEmpty() || interceptors == null || this.interceptorsListShared || !TypeIntrinsics.isMutableList(interceptors)) {
            return false;
        }
        if (Intrinsics.areEqual(this.interceptorsListSharedPhase, pipelinePhase)) {
            interceptors.add(function3);
            return true;
        }
        if (!Intrinsics.areEqual(pipelinePhase, CollectionsKt.last(this.phasesRaw)) && findPhaseIndex(pipelinePhase) != CollectionsKt.getLastIndex(this.phasesRaw)) {
            return false;
        }
        PhaseContent phaseContentFindPhase = findPhase(pipelinePhase);
        Intrinsics.checkNotNull(phaseContentFindPhase);
        phaseContentFindPhase.addInterceptor(function3);
        interceptors.add(function3);
        return true;
    }
}
