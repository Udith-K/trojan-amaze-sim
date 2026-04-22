package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import java.util.concurrent.Callable;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IdGenerator.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IdGenerator {
    private final WorkDatabase workDatabase;

    public IdGenerator(WorkDatabase workDatabase) {
        Intrinsics.checkNotNullParameter(workDatabase, "workDatabase");
        this.workDatabase = workDatabase;
    }

    public final int nextJobSchedulerIdWithRange(final int i, final int i2) {
        Object objRunInTransaction = this.workDatabase.runInTransaction((Callable<Object>) new Callable() { // from class: androidx.work.impl.utils.IdGenerator$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return IdGenerator.nextJobSchedulerIdWithRange$lambda$0(this.f$0, i, i2);
            }
        });
        Intrinsics.checkNotNullExpressionValue(objRunInTransaction, "workDatabase.runInTransa…d\n            }\n        )");
        return ((Number) objRunInTransaction).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer nextJobSchedulerIdWithRange$lambda$0(IdGenerator idGenerator, int i, int i2) {
        int iNextId = IdGeneratorKt.nextId(idGenerator.workDatabase, "next_job_scheduler_id");
        if (i > iNextId || iNextId > i2) {
            IdGeneratorKt.updatePreference(idGenerator.workDatabase, "next_job_scheduler_id", i + 1);
        } else {
            i = iNextId;
        }
        return Integer.valueOf(i);
    }

    public final int nextAlarmManagerId() {
        Object objRunInTransaction = this.workDatabase.runInTransaction((Callable<Object>) new Callable() { // from class: androidx.work.impl.utils.IdGenerator$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return IdGenerator.nextAlarmManagerId$lambda$1(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(objRunInTransaction, "workDatabase.runInTransa…NAGER_ID_KEY) }\n        )");
        return ((Number) objRunInTransaction).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer nextAlarmManagerId$lambda$1(IdGenerator idGenerator) {
        return Integer.valueOf(IdGeneratorKt.nextId(idGenerator.workDatabase, "next_alarm_manager_id"));
    }
}
