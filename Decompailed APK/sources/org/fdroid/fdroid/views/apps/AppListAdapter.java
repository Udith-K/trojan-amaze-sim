package org.fdroid.fdroid.views.apps;

import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import org.fdroid.database.AppListItem;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.data.App;

/* JADX INFO: loaded from: classes2.dex */
class AppListAdapter extends RecyclerView.Adapter {
    private final AppCompatActivity activity;
    private Runnable hasHiddenAppsCallback;
    private final List<AppListItem> items = new ArrayList();
    private boolean hideInstallButton = false;

    AppListAdapter(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
    }

    void setItems(List<AppListItem> list) {
        this.items.clear();
        this.items.addAll(list);
        notifyDataSetChanged();
    }

    void setHideInstallButton(boolean z) {
        this.hideInstallButton = z;
    }

    void setHasHiddenAppsCallback(Runnable runnable) {
        this.hasHiddenAppsCallback = runnable;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public StandardAppListItemController onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppCompatActivity appCompatActivity = this.activity;
        return new StandardAppListItemController(appCompatActivity, appCompatActivity.getLayoutInflater().inflate(R.layout.app_list_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(StandardAppListItemController standardAppListItemController, int i) {
        App app = new App(this.items.get(i));
        standardAppListItemController.bindModel(app, null, null);
        if (this.hideInstallButton) {
            standardAppListItemController.hideInstallButton();
        }
        if (app.isDisabledByAntiFeatures(this.activity)) {
            standardAppListItemController.itemView.setVisibility(8);
            standardAppListItemController.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            Runnable runnable = this.hasHiddenAppsCallback;
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        standardAppListItemController.itemView.setVisibility(0);
        standardAppListItemController.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }
}
