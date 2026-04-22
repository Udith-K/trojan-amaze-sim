package androidx.work.impl.model;

import androidx.arch.core.util.Function;
import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.OverwritingInputMerger;
import androidx.work.WorkInfo;
import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: compiled from: WorkSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public final class WorkSpec {
    public static final Companion Companion = new Companion(null);
    private static final String TAG;
    public static final Function WORK_INFO_MAPPER;
    public long backoffDelayDuration;
    public BackoffPolicy backoffPolicy;
    public Constraints constraints;
    public boolean expedited;
    public long flexDuration;
    private final int generation;
    public final String id;
    public long initialDelay;
    public Data input;
    public String inputMergerClassName;
    public long intervalDuration;
    public long lastEnqueueTime;
    public long minimumRetentionDuration;
    private long nextScheduleTimeOverride;
    private int nextScheduleTimeOverrideGeneration;
    public OutOfQuotaPolicy outOfQuotaPolicy;
    public Data output;
    private int periodCount;
    public int runAttemptCount;
    public long scheduleRequestedAt;
    public WorkInfo.State state;
    private final int stopReason;
    private String traceTag;
    public String workerClassName;

    public static /* synthetic */ WorkSpec copy$default(WorkSpec workSpec, String str, WorkInfo.State state, String str2, String str3, Data data, Data data2, long j, long j2, long j3, Constraints constraints, int i, BackoffPolicy backoffPolicy, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy, int i2, int i3, long j8, int i4, int i5, String str4, int i6, Object obj) {
        String str5 = (i6 & 1) != 0 ? workSpec.id : str;
        WorkInfo.State state2 = (i6 & 2) != 0 ? workSpec.state : state;
        String str6 = (i6 & 4) != 0 ? workSpec.workerClassName : str2;
        String str7 = (i6 & 8) != 0 ? workSpec.inputMergerClassName : str3;
        Data data3 = (i6 & 16) != 0 ? workSpec.input : data;
        Data data4 = (i6 & 32) != 0 ? workSpec.output : data2;
        long j9 = (i6 & 64) != 0 ? workSpec.initialDelay : j;
        long j10 = (i6 & 128) != 0 ? workSpec.intervalDuration : j2;
        long j11 = (i6 & 256) != 0 ? workSpec.flexDuration : j3;
        Constraints constraints2 = (i6 & 512) != 0 ? workSpec.constraints : constraints;
        return workSpec.copy(str5, state2, str6, str7, data3, data4, j9, j10, j11, constraints2, (i6 & 1024) != 0 ? workSpec.runAttemptCount : i, (i6 & 2048) != 0 ? workSpec.backoffPolicy : backoffPolicy, (i6 & PKIFailureInfo.certConfirmed) != 0 ? workSpec.backoffDelayDuration : j4, (i6 & 8192) != 0 ? workSpec.lastEnqueueTime : j5, (i6 & 16384) != 0 ? workSpec.minimumRetentionDuration : j6, (i6 & 32768) != 0 ? workSpec.scheduleRequestedAt : j7, (i6 & PKIFailureInfo.notAuthorized) != 0 ? workSpec.expedited : z, (131072 & i6) != 0 ? workSpec.outOfQuotaPolicy : outOfQuotaPolicy, (i6 & PKIFailureInfo.transactionIdInUse) != 0 ? workSpec.periodCount : i2, (i6 & PKIFailureInfo.signerNotTrusted) != 0 ? workSpec.generation : i3, (i6 & PKIFailureInfo.badCertTemplate) != 0 ? workSpec.nextScheduleTimeOverride : j8, (i6 & PKIFailureInfo.badSenderNonce) != 0 ? workSpec.nextScheduleTimeOverrideGeneration : i4, (4194304 & i6) != 0 ? workSpec.stopReason : i5, (i6 & 8388608) != 0 ? workSpec.traceTag : str4);
    }

    public final WorkSpec copy(String id, WorkInfo.State state, String workerClassName, String inputMergerClassName, Data input, Data output, long j, long j2, long j3, Constraints constraints, int i, BackoffPolicy backoffPolicy, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy, int i2, int i3, long j8, int i4, int i5, String str) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(workerClassName, "workerClassName");
        Intrinsics.checkNotNullParameter(inputMergerClassName, "inputMergerClassName");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(constraints, "constraints");
        Intrinsics.checkNotNullParameter(backoffPolicy, "backoffPolicy");
        Intrinsics.checkNotNullParameter(outOfQuotaPolicy, "outOfQuotaPolicy");
        return new WorkSpec(id, state, workerClassName, inputMergerClassName, input, output, j, j2, j3, constraints, i, backoffPolicy, j4, j5, j6, j7, z, outOfQuotaPolicy, i2, i3, j8, i4, i5, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WorkSpec)) {
            return false;
        }
        WorkSpec workSpec = (WorkSpec) obj;
        return Intrinsics.areEqual(this.id, workSpec.id) && this.state == workSpec.state && Intrinsics.areEqual(this.workerClassName, workSpec.workerClassName) && Intrinsics.areEqual(this.inputMergerClassName, workSpec.inputMergerClassName) && Intrinsics.areEqual(this.input, workSpec.input) && Intrinsics.areEqual(this.output, workSpec.output) && this.initialDelay == workSpec.initialDelay && this.intervalDuration == workSpec.intervalDuration && this.flexDuration == workSpec.flexDuration && Intrinsics.areEqual(this.constraints, workSpec.constraints) && this.runAttemptCount == workSpec.runAttemptCount && this.backoffPolicy == workSpec.backoffPolicy && this.backoffDelayDuration == workSpec.backoffDelayDuration && this.lastEnqueueTime == workSpec.lastEnqueueTime && this.minimumRetentionDuration == workSpec.minimumRetentionDuration && this.scheduleRequestedAt == workSpec.scheduleRequestedAt && this.expedited == workSpec.expedited && this.outOfQuotaPolicy == workSpec.outOfQuotaPolicy && this.periodCount == workSpec.periodCount && this.generation == workSpec.generation && this.nextScheduleTimeOverride == workSpec.nextScheduleTimeOverride && this.nextScheduleTimeOverrideGeneration == workSpec.nextScheduleTimeOverrideGeneration && this.stopReason == workSpec.stopReason && Intrinsics.areEqual(this.traceTag, workSpec.traceTag);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((((((((((((((((((((((((((((((((this.id.hashCode() * 31) + this.state.hashCode()) * 31) + this.workerClassName.hashCode()) * 31) + this.inputMergerClassName.hashCode()) * 31) + this.input.hashCode()) * 31) + this.output.hashCode()) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.initialDelay)) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.intervalDuration)) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.flexDuration)) * 31) + this.constraints.hashCode()) * 31) + this.runAttemptCount) * 31) + this.backoffPolicy.hashCode()) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.backoffDelayDuration)) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.lastEnqueueTime)) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.minimumRetentionDuration)) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.scheduleRequestedAt)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.expedited)) * 31) + this.outOfQuotaPolicy.hashCode()) * 31) + this.periodCount) * 31) + this.generation) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.nextScheduleTimeOverride)) * 31) + this.nextScheduleTimeOverrideGeneration) * 31) + this.stopReason) * 31;
        String str = this.traceTag;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public WorkSpec(String id, WorkInfo.State state, String workerClassName, String inputMergerClassName, Data input, Data output, long j, long j2, long j3, Constraints constraints, int i, BackoffPolicy backoffPolicy, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy, int i2, int i3, long j8, int i4, int i5, String str) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(workerClassName, "workerClassName");
        Intrinsics.checkNotNullParameter(inputMergerClassName, "inputMergerClassName");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(constraints, "constraints");
        Intrinsics.checkNotNullParameter(backoffPolicy, "backoffPolicy");
        Intrinsics.checkNotNullParameter(outOfQuotaPolicy, "outOfQuotaPolicy");
        this.id = id;
        this.state = state;
        this.workerClassName = workerClassName;
        this.inputMergerClassName = inputMergerClassName;
        this.input = input;
        this.output = output;
        this.initialDelay = j;
        this.intervalDuration = j2;
        this.flexDuration = j3;
        this.constraints = constraints;
        this.runAttemptCount = i;
        this.backoffPolicy = backoffPolicy;
        this.backoffDelayDuration = j4;
        this.lastEnqueueTime = j5;
        this.minimumRetentionDuration = j6;
        this.scheduleRequestedAt = j7;
        this.expedited = z;
        this.outOfQuotaPolicy = outOfQuotaPolicy;
        this.periodCount = i2;
        this.generation = i3;
        this.nextScheduleTimeOverride = j8;
        this.nextScheduleTimeOverrideGeneration = i4;
        this.stopReason = i5;
        this.traceTag = str;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ WorkSpec(String str, WorkInfo.State state, String str2, String str3, Data data, Data data2, long j, long j2, long j3, Constraints constraints, int i, BackoffPolicy backoffPolicy, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy, int i2, int i3, long j8, int i4, int i5, String str4, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        String str5;
        WorkInfo.State state2 = (i6 & 2) != 0 ? WorkInfo.State.ENQUEUED : state;
        if ((i6 & 8) != 0) {
            String name = OverwritingInputMerger.class.getName();
            Intrinsics.checkNotNullExpressionValue(name, "OverwritingInputMerger::class.java.name");
            str5 = name;
        } else {
            str5 = str3;
        }
        this(str, state2, str2, str5, (i6 & 16) != 0 ? Data.EMPTY : data, (i6 & 32) != 0 ? Data.EMPTY : data2, (i6 & 64) != 0 ? 0L : j, (i6 & 128) != 0 ? 0L : j2, (i6 & 256) != 0 ? 0L : j3, (i6 & 512) != 0 ? Constraints.NONE : constraints, (i6 & 1024) != 0 ? 0 : i, (i6 & 2048) != 0 ? BackoffPolicy.EXPONENTIAL : backoffPolicy, (i6 & PKIFailureInfo.certConfirmed) != 0 ? 30000L : j4, (i6 & 8192) != 0 ? -1L : j5, (i6 & 16384) != 0 ? 0L : j6, (32768 & i6) != 0 ? -1L : j7, (65536 & i6) != 0 ? false : z, (131072 & i6) != 0 ? OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST : outOfQuotaPolicy, (262144 & i6) != 0 ? 0 : i2, (524288 & i6) != 0 ? 0 : i3, (1048576 & i6) != 0 ? Long.MAX_VALUE : j8, (2097152 & i6) != 0 ? 0 : i4, (4194304 & i6) != 0 ? -256 : i5, (i6 & 8388608) != 0 ? null : str4);
    }

    public final int getPeriodCount() {
        return this.periodCount;
    }

    public final int getGeneration() {
        return this.generation;
    }

    public final long getNextScheduleTimeOverride() {
        return this.nextScheduleTimeOverride;
    }

    public final void setNextScheduleTimeOverride(long j) {
        this.nextScheduleTimeOverride = j;
    }

    public final int getNextScheduleTimeOverrideGeneration() {
        return this.nextScheduleTimeOverrideGeneration;
    }

    public final void setNextScheduleTimeOverrideGeneration(int i) {
        this.nextScheduleTimeOverrideGeneration = i;
    }

    public final int getStopReason() {
        return this.stopReason;
    }

    public final String getTraceTag() {
        return this.traceTag;
    }

    public final void setTraceTag(String str) {
        this.traceTag = str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WorkSpec(String id, String workerClassName_) {
        this(id, null, workerClassName_, null, null, null, 0L, 0L, 0L, null, 0, null, 0L, 0L, 0L, 0L, false, null, 0, 0, 0L, 0, 0, null, 16777210, null);
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(workerClassName_, "workerClassName_");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WorkSpec(String newId, WorkSpec other) {
        this(newId, other.state, other.workerClassName, other.inputMergerClassName, new Data(other.input), new Data(other.output), other.initialDelay, other.intervalDuration, other.flexDuration, new Constraints(other.constraints), other.runAttemptCount, other.backoffPolicy, other.backoffDelayDuration, other.lastEnqueueTime, other.minimumRetentionDuration, other.scheduleRequestedAt, other.expedited, other.outOfQuotaPolicy, other.periodCount, 0, other.nextScheduleTimeOverride, other.nextScheduleTimeOverrideGeneration, other.stopReason, other.traceTag, PKIFailureInfo.signerNotTrusted, null);
        Intrinsics.checkNotNullParameter(newId, "newId");
        Intrinsics.checkNotNullParameter(other, "other");
    }

    public final boolean isPeriodic() {
        return this.intervalDuration != 0;
    }

    public final boolean isBackedOff() {
        return this.state == WorkInfo.State.ENQUEUED && this.runAttemptCount > 0;
    }

    public final void setPeriodic(long j) {
        if (j < 900000) {
            Logger.get().warning(TAG, "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        setPeriodic(RangesKt.coerceAtLeast(j, 900000L), RangesKt.coerceAtLeast(j, 900000L));
    }

    public final void setPeriodic(long j, long j2) {
        if (j < 900000) {
            Logger.get().warning(TAG, "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        this.intervalDuration = RangesKt.coerceAtLeast(j, 900000L);
        if (j2 < 300000) {
            Logger.get().warning(TAG, "Flex duration lesser than minimum allowed value; Changed to 300000");
        }
        if (j2 > this.intervalDuration) {
            Logger.get().warning(TAG, "Flex duration greater than interval duration; Changed to " + j);
        }
        this.flexDuration = RangesKt.coerceIn(j2, 300000L, this.intervalDuration);
    }

    public final long calculateNextRunTime() {
        return Companion.calculateNextRunTime(isBackedOff(), this.runAttemptCount, this.backoffPolicy, this.backoffDelayDuration, this.lastEnqueueTime, this.periodCount, isPeriodic(), this.initialDelay, this.flexDuration, this.intervalDuration, this.nextScheduleTimeOverride);
    }

    public final boolean hasConstraints() {
        return !Intrinsics.areEqual(Constraints.NONE, this.constraints);
    }

    public String toString() {
        return "{WorkSpec: " + this.id + CoreConstants.CURLY_RIGHT;
    }

    /* JADX INFO: compiled from: WorkSpec.kt */
    public static final class IdAndState {
        public String id;
        public WorkInfo.State state;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IdAndState)) {
                return false;
            }
            IdAndState idAndState = (IdAndState) obj;
            return Intrinsics.areEqual(this.id, idAndState.id) && this.state == idAndState.state;
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + this.state.hashCode();
        }

        public String toString() {
            return "IdAndState(id=" + this.id + ", state=" + this.state + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public IdAndState(String id, WorkInfo.State state) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(state, "state");
            this.id = id;
            this.state = state;
        }
    }

    /* JADX INFO: compiled from: WorkSpec.kt */
    public static final class WorkInfoPojo {
        private long backoffDelayDuration;
        private BackoffPolicy backoffPolicy;
        private final Constraints constraints;
        private final long flexDuration;
        private final int generation;
        private final String id;
        private final long initialDelay;
        private final long intervalDuration;
        private long lastEnqueueTime;
        private final long nextScheduleTimeOverride;
        private final Data output;
        private int periodCount;
        private final List progress;
        private final int runAttemptCount;
        private final WorkInfo.State state;
        private final int stopReason;
        private final List tags;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WorkInfoPojo)) {
                return false;
            }
            WorkInfoPojo workInfoPojo = (WorkInfoPojo) obj;
            return Intrinsics.areEqual(this.id, workInfoPojo.id) && this.state == workInfoPojo.state && Intrinsics.areEqual(this.output, workInfoPojo.output) && this.initialDelay == workInfoPojo.initialDelay && this.intervalDuration == workInfoPojo.intervalDuration && this.flexDuration == workInfoPojo.flexDuration && Intrinsics.areEqual(this.constraints, workInfoPojo.constraints) && this.runAttemptCount == workInfoPojo.runAttemptCount && this.backoffPolicy == workInfoPojo.backoffPolicy && this.backoffDelayDuration == workInfoPojo.backoffDelayDuration && this.lastEnqueueTime == workInfoPojo.lastEnqueueTime && this.periodCount == workInfoPojo.periodCount && this.generation == workInfoPojo.generation && this.nextScheduleTimeOverride == workInfoPojo.nextScheduleTimeOverride && this.stopReason == workInfoPojo.stopReason && Intrinsics.areEqual(this.tags, workInfoPojo.tags) && Intrinsics.areEqual(this.progress, workInfoPojo.progress);
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((((this.id.hashCode() * 31) + this.state.hashCode()) * 31) + this.output.hashCode()) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.initialDelay)) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.intervalDuration)) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.flexDuration)) * 31) + this.constraints.hashCode()) * 31) + this.runAttemptCount) * 31) + this.backoffPolicy.hashCode()) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.backoffDelayDuration)) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.lastEnqueueTime)) * 31) + this.periodCount) * 31) + this.generation) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.nextScheduleTimeOverride)) * 31) + this.stopReason) * 31) + this.tags.hashCode()) * 31) + this.progress.hashCode();
        }

        public String toString() {
            return "WorkInfoPojo(id=" + this.id + ", state=" + this.state + ", output=" + this.output + ", initialDelay=" + this.initialDelay + ", intervalDuration=" + this.intervalDuration + ", flexDuration=" + this.flexDuration + ", constraints=" + this.constraints + ", runAttemptCount=" + this.runAttemptCount + ", backoffPolicy=" + this.backoffPolicy + ", backoffDelayDuration=" + this.backoffDelayDuration + ", lastEnqueueTime=" + this.lastEnqueueTime + ", periodCount=" + this.periodCount + ", generation=" + this.generation + ", nextScheduleTimeOverride=" + this.nextScheduleTimeOverride + ", stopReason=" + this.stopReason + ", tags=" + this.tags + ", progress=" + this.progress + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public WorkInfoPojo(String id, WorkInfo.State state, Data output, long j, long j2, long j3, Constraints constraints, int i, BackoffPolicy backoffPolicy, long j4, long j5, int i2, int i3, long j6, int i4, List tags, List progress) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(constraints, "constraints");
            Intrinsics.checkNotNullParameter(backoffPolicy, "backoffPolicy");
            Intrinsics.checkNotNullParameter(tags, "tags");
            Intrinsics.checkNotNullParameter(progress, "progress");
            this.id = id;
            this.state = state;
            this.output = output;
            this.initialDelay = j;
            this.intervalDuration = j2;
            this.flexDuration = j3;
            this.constraints = constraints;
            this.runAttemptCount = i;
            this.backoffPolicy = backoffPolicy;
            this.backoffDelayDuration = j4;
            this.lastEnqueueTime = j5;
            this.periodCount = i2;
            this.generation = i3;
            this.nextScheduleTimeOverride = j6;
            this.stopReason = i4;
            this.tags = tags;
            this.progress = progress;
        }

        public final boolean isPeriodic() {
            return this.intervalDuration != 0;
        }

        public final boolean isBackedOff() {
            return this.state == WorkInfo.State.ENQUEUED && this.runAttemptCount > 0;
        }

        public final WorkInfo toWorkInfo() {
            Data data = !this.progress.isEmpty() ? (Data) this.progress.get(0) : Data.EMPTY;
            UUID uuidFromString = UUID.fromString(this.id);
            Intrinsics.checkNotNullExpressionValue(uuidFromString, "fromString(id)");
            return new WorkInfo(uuidFromString, this.state, new HashSet(this.tags), this.output, data, this.runAttemptCount, this.generation, this.constraints, this.initialDelay, getPeriodicityOrNull(), calculateNextRunTimeMillis(), this.stopReason);
        }

        private final WorkInfo.PeriodicityInfo getPeriodicityOrNull() {
            long j = this.intervalDuration;
            if (j != 0) {
                return new WorkInfo.PeriodicityInfo(j, this.flexDuration);
            }
            return null;
        }

        private final long calculateNextRunTimeMillis() {
            return this.state == WorkInfo.State.ENQUEUED ? WorkSpec.Companion.calculateNextRunTime(isBackedOff(), this.runAttemptCount, this.backoffPolicy, this.backoffDelayDuration, this.lastEnqueueTime, this.periodCount, isPeriodic(), this.initialDelay, this.flexDuration, this.intervalDuration, this.nextScheduleTimeOverride) : Preferences.UPDATE_INTERVAL_DISABLED;
        }
    }

    /* JADX INFO: compiled from: WorkSpec.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long calculateNextRunTime(boolean z, int i, BackoffPolicy backoffPolicy, long j, long j2, int i2, boolean z2, long j3, long j4, long j5, long j6) {
            Intrinsics.checkNotNullParameter(backoffPolicy, "backoffPolicy");
            if (j6 != Preferences.UPDATE_INTERVAL_DISABLED && z2) {
                return i2 == 0 ? j6 : RangesKt.coerceAtLeast(j6, 900000 + j2);
            }
            if (z) {
                return j2 + RangesKt.coerceAtMost(backoffPolicy == BackoffPolicy.LINEAR ? ((long) i) * j : (long) Math.scalb(j, i - 1), 18000000L);
            }
            if (!z2) {
                return j2 == -1 ? Preferences.UPDATE_INTERVAL_DISABLED : j2 + j3;
            }
            long j7 = i2 == 0 ? j2 + j3 : j2 + j5;
            if (j4 != j5 && i2 == 0) {
                j7 += j5 - j4;
            }
            return j7;
        }
    }

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("WorkSpec");
        Intrinsics.checkNotNullExpressionValue(strTagWithPrefix, "tagWithPrefix(\"WorkSpec\")");
        TAG = strTagWithPrefix;
        WORK_INFO_MAPPER = new Function() { // from class: androidx.work.impl.model.WorkSpec$$ExternalSyntheticLambda0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return WorkSpec.WORK_INFO_MAPPER$lambda$1((List) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List WORK_INFO_MAPPER$lambda$1(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((WorkInfoPojo) it.next()).toWorkInfo());
        }
        return arrayList;
    }
}
