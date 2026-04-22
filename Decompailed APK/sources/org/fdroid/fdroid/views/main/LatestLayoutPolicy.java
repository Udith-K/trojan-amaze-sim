package org.fdroid.fdroid.views.main;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
public class LatestLayoutPolicy {
    private final Context context;

    public LatestLayoutPolicy(Context context) {
        this.context = context.getApplicationContext();
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new ItemDecorator(this.context);
    }

    public int getItemViewType(int i) {
        int i2 = i % 5;
        if (i == 0) {
            return R.id.latest_regular_list;
        }
        if (i2 == 1 || i2 == 2) {
            return R.id.latest_large_tile;
        }
        if (i2 == 3 || i2 == 4) {
            return R.id.latest_small_tile;
        }
        return R.id.latest_regular_list;
    }

    public int getSpanSize(int i) {
        return i % 5 == 0 ? 2 : 1;
    }

    private static class ItemDecorator extends RecyclerView.ItemDecoration {
        private final Context context;

        ItemDecorator(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            Resources resources = this.context.getResources();
            int dimension = (int) resources.getDimension(R.dimen.latest__padding__app_card__horizontal);
            int dimension2 = (int) resources.getDimension(R.dimen.latest__padding__app_card__vertical);
            int i = childAdapterPosition % 5;
            if (childAdapterPosition == 0) {
                rect.set(dimension, dimension2, dimension, dimension2);
                return;
            }
            if (i != 0) {
                boolean z = ViewCompat.getLayoutDirection(recyclerView) == 0;
                int i2 = (i == 1 || i == 3) ? dimension : 0;
                int i3 = z ? i2 : dimension;
                if (!z) {
                    dimension = i2;
                }
                rect.set(i3, 0, dimension, dimension2);
                return;
            }
            rect.set(dimension, 0, dimension, dimension2);
        }
    }
}
