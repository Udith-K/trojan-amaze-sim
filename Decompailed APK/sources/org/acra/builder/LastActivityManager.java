package org.acra.builder;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.collections.WeakStack;

/* JADX INFO: compiled from: LastActivityManager.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class LastActivityManager {
    private final WeakStack activityStack;
    private final Condition destroyedCondition;
    private final ReentrantLock lock;

    public LastActivityManager(Application application) {
        Intrinsics.checkNotNullParameter(application, "application");
        this.activityStack = new WeakStack();
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.destroyedCondition = reentrantLock.newCondition();
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: org.acra.builder.LastActivityManager.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                if (ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, "onActivityCreated " + activity.getClass());
                }
                LastActivityManager.this.activityStack.add(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                if (ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, "onActivityDestroyed " + activity.getClass());
                }
                ReentrantLock reentrantLock2 = LastActivityManager.this.lock;
                LastActivityManager lastActivityManager = LastActivityManager.this;
                reentrantLock2.lock();
                try {
                    lastActivityManager.activityStack.remove(activity);
                    lastActivityManager.destroyedCondition.signalAll();
                    Unit unit = Unit.INSTANCE;
                } finally {
                    reentrantLock2.unlock();
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                if (ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, "onActivityPaused " + activity.getClass());
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                if (ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, "onActivityResumed " + activity.getClass());
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                Intrinsics.checkNotNullParameter(outState, "outState");
                if (ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, "onActivitySaveInstanceState " + activity.getClass());
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                if (ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, "onActivityStarted " + activity.getClass());
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                if (ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, "onActivityStopped " + activity.getClass());
                }
            }
        });
    }

    public final List getLastActivities() {
        return new ArrayList(this.activityStack);
    }

    public final void clearLastActivities() {
        this.activityStack.clear();
    }

    public final void waitForAllActivitiesDestroy(int i) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jCurrentTimeMillis2 = jCurrentTimeMillis;
            while (!this.activityStack.isEmpty()) {
                long j = i;
                if (jCurrentTimeMillis + j <= jCurrentTimeMillis2) {
                    break;
                }
                this.destroyedCondition.await((jCurrentTimeMillis - jCurrentTimeMillis2) + j, TimeUnit.MILLISECONDS);
                jCurrentTimeMillis2 = System.currentTimeMillis();
            }
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }
}
