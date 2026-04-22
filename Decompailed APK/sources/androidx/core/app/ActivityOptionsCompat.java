package androidx.core.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.view.View;
import androidx.core.util.Pair;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActivityOptionsCompat {
    public abstract Bundle toBundle();

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, Pair... pairArr) {
        android.util.Pair[] pairArr2;
        if (pairArr != null) {
            pairArr2 = new android.util.Pair[pairArr.length];
            for (int i = 0; i < pairArr.length; i++) {
                Pair pair = pairArr[i];
                pairArr2[i] = android.util.Pair.create((View) pair.first, (String) pair.second);
            }
        } else {
            pairArr2 = null;
        }
        return new ActivityOptionsCompatImpl(Api21Impl.makeSceneTransitionAnimation(activity, pairArr2));
    }

    private static class ActivityOptionsCompatImpl extends ActivityOptionsCompat {
        private final ActivityOptions mActivityOptions;

        ActivityOptionsCompatImpl(ActivityOptions activityOptions) {
            this.mActivityOptions = activityOptions;
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        public Bundle toBundle() {
            return this.mActivityOptions.toBundle();
        }
    }

    protected ActivityOptionsCompat() {
    }

    static class Api21Impl {
        static ActivityOptions makeSceneTransitionAnimation(Activity activity, android.util.Pair... pairArr) {
            return ActivityOptions.makeSceneTransitionAnimation(activity, pairArr);
        }
    }
}
