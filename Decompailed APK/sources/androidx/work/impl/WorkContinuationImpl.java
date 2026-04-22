package androidx.work.impl;

import android.text.TextUtils;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.OperationKt;
import androidx.work.WorkContinuation;
import androidx.work.WorkRequest;
import androidx.work.impl.utils.EnqueueRunnable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: loaded from: classes.dex */
public class WorkContinuationImpl extends WorkContinuation {
    private static final String TAG = Logger.tagWithPrefix("WorkContinuationImpl");
    private final List mAllIds;
    private boolean mEnqueued;
    private final ExistingWorkPolicy mExistingWorkPolicy;
    private final List mIds;
    private final String mName;
    private Operation mOperation;
    private final List mParents;
    private final List mWork;
    private final WorkManagerImpl mWorkManagerImpl;

    public WorkManagerImpl getWorkManagerImpl() {
        return this.mWorkManagerImpl;
    }

    public String getName() {
        return this.mName;
    }

    public ExistingWorkPolicy getExistingWorkPolicy() {
        return this.mExistingWorkPolicy;
    }

    public List getWork() {
        return this.mWork;
    }

    public List getIds() {
        return this.mIds;
    }

    public boolean isEnqueued() {
        return this.mEnqueued;
    }

    public void markEnqueued() {
        this.mEnqueued = true;
    }

    public List getParents() {
        return this.mParents;
    }

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl, List list) {
        this(workManagerImpl, null, ExistingWorkPolicy.KEEP, list, null);
    }

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl, String str, ExistingWorkPolicy existingWorkPolicy, List list) {
        this(workManagerImpl, str, existingWorkPolicy, list, null);
    }

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl, String str, ExistingWorkPolicy existingWorkPolicy, List list, List list2) {
        this.mWorkManagerImpl = workManagerImpl;
        this.mName = str;
        this.mExistingWorkPolicy = existingWorkPolicy;
        this.mWork = list;
        this.mParents = list2;
        this.mIds = new ArrayList(list.size());
        this.mAllIds = new ArrayList();
        if (list2 != null) {
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                this.mAllIds.addAll(((WorkContinuationImpl) it.next()).mAllIds);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (existingWorkPolicy == ExistingWorkPolicy.REPLACE && ((WorkRequest) list.get(i)).getWorkSpec().getNextScheduleTimeOverride() != Preferences.UPDATE_INTERVAL_DISABLED) {
                throw new IllegalArgumentException("Next Schedule Time Override must be used with ExistingPeriodicWorkPolicyUPDATE (preferably) or KEEP");
            }
            String stringId = ((WorkRequest) list.get(i)).getStringId();
            this.mIds.add(stringId);
            this.mAllIds.add(stringId);
        }
    }

    public Operation enqueue() {
        if (!this.mEnqueued) {
            this.mOperation = OperationKt.launchOperation(this.mWorkManagerImpl.getConfiguration().getTracer(), "EnqueueRunnable_" + getExistingWorkPolicy().name(), this.mWorkManagerImpl.getWorkTaskExecutor().getSerialTaskExecutor(), new Function0() { // from class: androidx.work.impl.WorkContinuationImpl$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f$0.lambda$enqueue$0();
                }
            });
        } else {
            Logger.get().warning(TAG, "Already enqueued work ids (" + TextUtils.join(", ", this.mIds) + ")");
        }
        return this.mOperation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$enqueue$0() {
        EnqueueRunnable.enqueue(this);
        return Unit.INSTANCE;
    }

    public boolean hasCycles() {
        return hasCycles(this, new HashSet());
    }

    private static boolean hasCycles(WorkContinuationImpl workContinuationImpl, Set set) {
        set.addAll(workContinuationImpl.getIds());
        Set setPrerequisitesFor = prerequisitesFor(workContinuationImpl);
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (setPrerequisitesFor.contains((String) it.next())) {
                return true;
            }
        }
        List parents = workContinuationImpl.getParents();
        if (parents != null && !parents.isEmpty()) {
            Iterator it2 = parents.iterator();
            while (it2.hasNext()) {
                if (hasCycles((WorkContinuationImpl) it2.next(), set)) {
                    return true;
                }
            }
        }
        set.removeAll(workContinuationImpl.getIds());
        return false;
    }

    public static Set prerequisitesFor(WorkContinuationImpl workContinuationImpl) {
        HashSet hashSet = new HashSet();
        List parents = workContinuationImpl.getParents();
        if (parents != null && !parents.isEmpty()) {
            Iterator it = parents.iterator();
            while (it.hasNext()) {
                hashSet.addAll(((WorkContinuationImpl) it.next()).getIds());
            }
        }
        return hashSet;
    }
}
