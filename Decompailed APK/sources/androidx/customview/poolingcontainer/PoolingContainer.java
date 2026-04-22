package androidx.customview.poolingcontainer;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewGroupKt;
import androidx.core.view.ViewKt;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PoolingContainer.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PoolingContainer {
    private static final int PoolingContainerListenerHolderTag = R$id.pooling_container_listener_holder_tag;
    private static final int IsPoolingContainerTag = R$id.is_pooling_container_tag;

    public static final void addPoolingContainerListener(View view, PoolingContainerListener listener) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(listener, "listener");
        getPoolingContainerListenerHolder(view).addListener(listener);
    }

    public static final void removePoolingContainerListener(View view, PoolingContainerListener listener) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(listener, "listener");
        getPoolingContainerListenerHolder(view).removeListener(listener);
    }

    public static final boolean isPoolingContainer(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Object tag = view.getTag(IsPoolingContainerTag);
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static final void setPoolingContainer(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setTag(IsPoolingContainerTag, Boolean.valueOf(z));
    }

    public static final boolean isWithinPoolingContainer(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        for (Object obj : ViewKt.getAncestors(view)) {
            if ((obj instanceof View) && isPoolingContainer((View) obj)) {
                return true;
            }
        }
        return false;
    }

    public static final void callPoolingContainerOnRelease(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Iterator it = ViewKt.getAllViews(view).iterator();
        while (it.hasNext()) {
            getPoolingContainerListenerHolder((View) it.next()).onRelease();
        }
    }

    public static final void callPoolingContainerOnReleaseForChildren(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        Iterator it = ViewGroupKt.getChildren(viewGroup).iterator();
        while (it.hasNext()) {
            getPoolingContainerListenerHolder((View) it.next()).onRelease();
        }
    }

    private static final PoolingContainerListenerHolder getPoolingContainerListenerHolder(View view) {
        int i = PoolingContainerListenerHolderTag;
        PoolingContainerListenerHolder poolingContainerListenerHolder = (PoolingContainerListenerHolder) view.getTag(i);
        if (poolingContainerListenerHolder != null) {
            return poolingContainerListenerHolder;
        }
        PoolingContainerListenerHolder poolingContainerListenerHolder2 = new PoolingContainerListenerHolder();
        view.setTag(i, poolingContainerListenerHolder2);
        return poolingContainerListenerHolder2;
    }
}
