package org.fdroid.fdroid.views.categories;

import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.fdroid.database.AppOverviewItem;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
class AppPreviewAdapter extends RecyclerView.Adapter {
    private final AppCompatActivity activity;
    private final List<String> antiFeatures;
    private final boolean hideIncompatibleVersions;
    private List<AppOverviewItem> items = Collections.emptyList();
    private final boolean showOtherAntiFeatures;
    private final Set<String> shownAntiFeatures;

    AppPreviewAdapter(AppCompatActivity appCompatActivity) {
        Set<String> setShowAppsWithAntiFeatures = Preferences.get().showAppsWithAntiFeatures();
        this.shownAntiFeatures = setShowAppsWithAntiFeatures;
        this.hideIncompatibleVersions = !Preferences.get().showIncompatibleVersions();
        this.activity = appCompatActivity;
        this.antiFeatures = Arrays.asList(appCompatActivity.getResources().getStringArray(R.array.antifeaturesValues));
        this.showOtherAntiFeatures = setShowAppsWithAntiFeatures.contains(appCompatActivity.getResources().getString(R.string.antiothers_key));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public AppCardController onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppCompatActivity appCompatActivity = this.activity;
        return new AppCardController(appCompatActivity, appCompatActivity.getLayoutInflater().inflate(R.layout.app_card_normal, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(AppCardController appCardController, int i) {
        appCardController.bindApp(this.items.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    void setAppCursor(List<AppOverviewItem> list) {
        if (this.items == list) {
            return;
        }
        Iterator<AppOverviewItem> it = list.iterator();
        while (it.hasNext()) {
            AppOverviewItem next = it.next();
            if (isFilteredByAntiFeature(next, this.antiFeatures, this.shownAntiFeatures, this.showOtherAntiFeatures)) {
                it.remove();
            } else if (this.hideIncompatibleVersions && !next.isCompatible()) {
                it.remove();
            }
        }
        this.items = list;
        notifyDataSetChanged();
    }

    private boolean isFilteredByAntiFeature(AppOverviewItem appOverviewItem, List<String> list, Set<String> set, boolean z) {
        for (String str : appOverviewItem.getAntiFeatureKeys()) {
            if (list.contains(str)) {
                if (!set.contains(str)) {
                    return true;
                }
            } else if (!z) {
                return true;
            }
        }
        return false;
    }
}
