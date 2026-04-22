package org.fdroid.fdroid.views.main;

import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import org.fdroid.database.AppOverviewItem;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.views.categories.AppCardController;

/* JADX INFO: loaded from: classes2.dex */
public class LatestAdapter extends RecyclerView.Adapter {
    private final AppCompatActivity activity;
    private final RecyclerView.ItemDecoration appListDecorator;
    private List<AppOverviewItem> apps;
    private final LatestLayoutPolicy layoutPolicy;
    private final GridLayoutManager.SpanSizeLookup spanSizeLookup;

    LatestAdapter(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
        LatestLayoutPolicy latestLayoutPolicy = new LatestLayoutPolicy(appCompatActivity);
        this.layoutPolicy = latestLayoutPolicy;
        this.appListDecorator = latestLayoutPolicy.getItemDecoration();
        this.spanSizeLookup = new SpanSizeLookup(latestLayoutPolicy);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(this.appListDecorator);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        recyclerView.removeItemDecoration(this.appListDecorator);
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public AppCardController onCreateViewHolder(ViewGroup viewGroup, int i) {
        int i2;
        if (i == R.id.latest_large_tile) {
            i2 = R.layout.app_card_large;
        } else if (i == R.id.latest_small_tile) {
            i2 = R.layout.app_card_horizontal;
        } else if (i == R.id.latest_regular_list) {
            i2 = R.layout.app_card_list_item;
        } else {
            throw new IllegalArgumentException("Unknown view type when rendering \"What's New\": " + i);
        }
        AppCompatActivity appCompatActivity = this.activity;
        return new AppCardController(appCompatActivity, appCompatActivity.getLayoutInflater().inflate(i2, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.layoutPolicy.getItemViewType(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(AppCardController appCardController, int i) {
        appCardController.bindApp(this.apps.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<AppOverviewItem> list = this.apps;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setApps(List<AppOverviewItem> list) {
        if (this.apps == list) {
            return;
        }
        this.apps = list;
        notifyDataSetChanged();
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return this.spanSizeLookup;
    }

    private static final class SpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        private final LatestLayoutPolicy layoutPolicy;

        private SpanSizeLookup(LatestLayoutPolicy latestLayoutPolicy) {
            this.layoutPolicy = latestLayoutPolicy;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i) {
            return this.layoutPolicy.getSpanSize(i);
        }
    }
}
