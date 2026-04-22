package org.fdroid.fdroid.views.main;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.database.AppOverviewItem;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.panic.HidingManager;
import org.fdroid.fdroid.views.apps.AppListActivity;

/* JADX INFO: loaded from: classes2.dex */
class LatestViewBinder implements Observer, Preferences.ChangeListener {
    private final AppCompatActivity activity;
    private final RecyclerView appList;
    private final FDroidDatabase db;
    private final TextView emptyState;
    private final LatestAdapter latestAdapter;
    private final CircularProgressIndicator progressBar;

    LatestViewBinder(final AppCompatActivity appCompatActivity, FrameLayout frameLayout) {
        this.activity = appCompatActivity;
        appCompatActivity.getLifecycle().addObserver(new DefaultLifecycleObserver() { // from class: org.fdroid.fdroid.views.main.LatestViewBinder.1
            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public /* bridge */ /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
                Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
            }

            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public /* bridge */ /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
                Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
            }

            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public /* bridge */ /* synthetic */ void onStart(LifecycleOwner lifecycleOwner) {
                Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
            }

            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public /* bridge */ /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
                Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
            }

            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public void onCreate(LifecycleOwner lifecycleOwner) {
                Preferences.get().registerAppsRequiringAntiFeaturesChangeListener(LatestViewBinder.this);
                Preferences.get().registerShowIncompatibleListener(LatestViewBinder.this);
            }

            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public void onDestroy(LifecycleOwner lifecycleOwner) {
                Preferences.get().unregisterAppsRequiringAntiFeaturesChangeListener(LatestViewBinder.this);
                Preferences.get().unregisterShowIncompatibleListener(LatestViewBinder.this);
            }
        });
        FDroidDatabase db = DBHelper.getDb(appCompatActivity);
        this.db = db;
        Transformations.distinctUntilChanged(db.getAppDao().getAppOverviewItems(200)).observe(appCompatActivity, this);
        View viewInflate = appCompatActivity.getLayoutInflater().inflate(R.layout.main_tab_latest, (ViewGroup) frameLayout, true);
        LatestAdapter latestAdapter = new LatestAdapter(appCompatActivity);
        this.latestAdapter = latestAdapter;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(appCompatActivity, 2);
        gridLayoutManager.setSpanSizeLookup(latestAdapter.getSpanSizeLookup());
        this.emptyState = (TextView) viewInflate.findViewById(R.id.empty_state);
        this.progressBar = (CircularProgressIndicator) viewInflate.findViewById(R.id.progress_bar);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.app_list);
        this.appList = recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(latestAdapter);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) viewInflate.findViewById(R.id.swipe_to_refresh);
        Utils.applySwipeLayoutColors(swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: org.fdroid.fdroid.views.main.LatestViewBinder$$ExternalSyntheticLambda1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                LatestViewBinder.lambda$new$1(swipeRefreshLayout, appCompatActivity);
            }
        });
        FloatingActionButton floatingActionButton = (FloatingActionButton) viewInflate.findViewById(R.id.fab_search);
        floatingActionButton.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.main.LatestViewBinder$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LatestViewBinder.lambda$new$2(appCompatActivity, view);
            }
        });
        floatingActionButton.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.fdroid.fdroid.views.main.LatestViewBinder$$ExternalSyntheticLambda3
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return LatestViewBinder.lambda$new$3(appCompatActivity, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$1(SwipeRefreshLayout swipeRefreshLayout, final AppCompatActivity appCompatActivity) {
        swipeRefreshLayout.setRefreshing(false);
        Utils.runOffUiThread(new Runnable() { // from class: org.fdroid.fdroid.views.main.LatestViewBinder$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LatestViewBinder.lambda$new$0(appCompatActivity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(AppCompatActivity appCompatActivity) {
        FDroidApp.getRepoUpdateManager(appCompatActivity).updateRepos();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$2(AppCompatActivity appCompatActivity, View view) {
        appCompatActivity.startActivity(new Intent(appCompatActivity, (Class<?>) AppListActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$3(AppCompatActivity appCompatActivity, View view) {
        if (!Preferences.get().hideOnLongPressSearch()) {
            return false;
        }
        HidingManager.showHideDialog(appCompatActivity);
        return true;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(List<AppOverviewItem> list) {
        filterApps(list);
        this.latestAdapter.setApps(list);
        if (this.latestAdapter.getItemCount() == 0) {
            this.emptyState.setVisibility(0);
            this.appList.setVisibility(8);
            explainEmptyStateToUser();
        } else {
            this.progressBar.setVisibility(8);
            this.emptyState.setVisibility(8);
            this.appList.setVisibility(0);
        }
    }

    @Override // org.fdroid.fdroid.Preferences.ChangeListener
    public void onPreferenceChange() {
        final LiveData appOverviewItems = this.db.getAppDao().getAppOverviewItems(200);
        appOverviewItems.observe(this.activity, new Observer() { // from class: org.fdroid.fdroid.views.main.LatestViewBinder.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(List<AppOverviewItem> list) {
                LatestViewBinder.this.onChanged(list);
                appOverviewItems.removeObserver(this);
            }
        });
    }

    private void filterApps(List<AppOverviewItem> list) {
        List<String> listAsList = Arrays.asList(this.activity.getResources().getStringArray(R.array.antifeaturesValues));
        Set<String> setShowAppsWithAntiFeatures = Preferences.get().showAppsWithAntiFeatures();
        boolean zContains = setShowAppsWithAntiFeatures.contains(this.activity.getResources().getString(R.string.antiothers_key));
        boolean zShowIncompatibleVersions = Preferences.get().showIncompatibleVersions();
        Iterator<AppOverviewItem> it = list.iterator();
        while (it.hasNext()) {
            AppOverviewItem next = it.next();
            if (isFilteredByAntiFeature(next, listAsList, setShowAppsWithAntiFeatures, zContains)) {
                it.remove();
            } else if (!zShowIncompatibleVersions && !next.isCompatible()) {
                it.remove();
            }
        }
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

    private void explainEmptyStateToUser() {
        int i = 0;
        if (Preferences.get().isIndexNeverUpdated() && ((Boolean) FDroidApp.getRepoUpdateManager(this.activity).getIsUpdating().getValue()).booleanValue()) {
            this.progressBar.setVisibility(0);
            this.emptyState.setVisibility(8);
            this.appList.setVisibility(8);
            return;
        }
        this.progressBar.setVisibility(8);
        StringBuilder sb = new StringBuilder();
        sb.append(this.activity.getString(R.string.latest__empty_state__no_recent_apps));
        sb.append("\n\n");
        Long lastUpdated = null;
        for (Repository repository : FDroidApp.getRepoManager(this.activity).getRepositories()) {
            if (repository.getEnabled()) {
                i++;
                if (lastUpdated == null && repository.getLastUpdated() != null) {
                    lastUpdated = repository.getLastUpdated();
                } else if (lastUpdated != null && repository.getLastUpdated() != null && repository.getLastUpdated().longValue() > lastUpdated.longValue()) {
                    lastUpdated = repository.getLastUpdated();
                }
            }
        }
        if (i == 0) {
            sb.append(this.activity.getString(R.string.latest__empty_state__no_enabled_repos));
        } else if (lastUpdated == null) {
            sb.append(this.activity.getString(R.string.latest__empty_state__never_updated));
        } else {
            sb.append(Utils.formatLastUpdated(this.activity.getResources(), lastUpdated.longValue()));
        }
        this.emptyState.setText(sb.toString());
    }
}
