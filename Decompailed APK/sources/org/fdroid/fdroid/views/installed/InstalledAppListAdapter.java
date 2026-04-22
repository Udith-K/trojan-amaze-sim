package org.fdroid.fdroid.views.installed;

import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.fdroid.database.AppListItem;
import org.fdroid.database.AppPrefs;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.data.App;

/* JADX INFO: loaded from: classes2.dex */
public class InstalledAppListAdapter extends RecyclerView.Adapter {
    protected final AppCompatActivity activity;
    private final List<App> items = new ArrayList();

    protected InstalledAppListAdapter(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public InstalledAppListItemController onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new InstalledAppListItemController(this.activity, this.activity.getLayoutInflater().inflate(R.layout.installed_app_list_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(InstalledAppListItemController installedAppListItemController, int i) {
        installedAppListItemController.bindModel(this.items.get(i), null, null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public void setApps(List<AppListItem> list) {
        this.items.clear();
        Iterator<AppListItem> it = list.iterator();
        while (it.hasNext()) {
            this.items.add(new App(it.next()));
        }
        notifyDataSetChanged();
    }

    public void updateItem(AppListItem appListItem, AppPrefs appPrefs) {
        for (int i = 0; i < this.items.size(); i++) {
            App app = this.items.get(i);
            if (app.packageName.equals(appListItem.getPackageName())) {
                app.prefs = appPrefs;
                notifyItemChanged(i);
                return;
            }
        }
    }

    App getItem(int i) {
        return this.items.get(i);
    }
}
