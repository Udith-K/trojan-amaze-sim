package androidx.core.view;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewGroupCompat {
    public static int getLayoutMode(ViewGroup viewGroup) {
        return viewGroup.getLayoutMode();
    }

    public static boolean isTransitionGroup(ViewGroup viewGroup) {
        return Api21Impl.isTransitionGroup(viewGroup);
    }

    static class Api21Impl {
        static boolean isTransitionGroup(ViewGroup viewGroup) {
            return viewGroup.isTransitionGroup();
        }
    }
}
