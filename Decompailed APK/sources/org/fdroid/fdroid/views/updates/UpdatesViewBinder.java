package org.fdroid.fdroid.views.updates;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.work.RepoUpdateWorker;

/* JADX INFO: loaded from: classes2.dex */
public class UpdatesViewBinder {
    private final AppCompatActivity activity;
    private final UpdatesAdapter adapter;
    private final RecyclerView.AdapterDataObserver adapterChangeListener;
    private final ImageView emptyImage;
    private final TextView emptyState;
    private final CircularProgressIndicator emptyUpdatingProgress;
    private final LiveData isUpdatingLiveData;
    private final RecyclerView list;

    public UpdatesViewBinder(final AppCompatActivity appCompatActivity, FrameLayout frameLayout) {
        RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() { // from class: org.fdroid.fdroid.views.updates.UpdatesViewBinder.1
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                UpdatesViewBinder.this.updateEmptyState();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int i, int i2) {
                UpdatesViewBinder.this.updateEmptyState();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i, int i2) {
                UpdatesViewBinder.this.updateEmptyState();
            }
        };
        this.adapterChangeListener = adapterDataObserver;
        this.activity = appCompatActivity;
        View viewInflate = appCompatActivity.getLayoutInflater().inflate(R.layout.main_tab_updates, (ViewGroup) frameLayout, true);
        UpdatesAdapter updatesAdapter = new UpdatesAdapter(appCompatActivity);
        this.adapter = updatesAdapter;
        updatesAdapter.registerAdapterDataObserver(adapterDataObserver);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.list);
        this.list = recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(appCompatActivity));
        recyclerView.setAdapter(updatesAdapter);
        new ItemTouchHelper(new UpdatesItemTouchCallback(updatesAdapter)).attachToRecyclerView(recyclerView);
        this.emptyState = (TextView) viewInflate.findViewById(R.id.empty_state);
        this.emptyImage = (ImageView) viewInflate.findViewById(R.id.image);
        this.emptyUpdatingProgress = (CircularProgressIndicator) viewInflate.findViewById(R.id.empty_updating_progress);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) viewInflate.findViewById(R.id.swipe_to_refresh);
        Utils.applySwipeLayoutColors(swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: org.fdroid.fdroid.views.updates.UpdatesViewBinder$$ExternalSyntheticLambda0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                UpdatesViewBinder.lambda$new$0(swipeRefreshLayout, appCompatActivity);
            }
        });
        this.isUpdatingLiveData = FDroidApp.getRepoUpdateManager(appCompatActivity).getIsUpdatingLiveData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(SwipeRefreshLayout swipeRefreshLayout, AppCompatActivity appCompatActivity) {
        swipeRefreshLayout.setRefreshing(false);
        RepoUpdateWorker.updateNow(appCompatActivity);
    }

    public void bind() {
        this.adapter.setIsActive();
    }

    public void unbind() {
        this.adapter.stopListeningForStatusUpdates();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEmptyState() {
        if (this.adapter.getItemCount() == 0) {
            this.list.setVisibility(8);
            this.emptyImage.setVisibility(0);
            setUpEmptyUpdatingProgress(((Boolean) FDroidApp.getRepoUpdateManager(this.activity).getIsUpdating().getValue()).booleanValue());
            this.isUpdatingLiveData.observe(this.activity, new Observer() { // from class: org.fdroid.fdroid.views.updates.UpdatesViewBinder$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.setUpEmptyUpdatingProgress(((Boolean) obj).booleanValue());
                }
            });
            return;
        }
        this.list.setVisibility(0);
        this.emptyState.setVisibility(8);
        this.emptyImage.setVisibility(8);
        this.isUpdatingLiveData.removeObserver(new Observer() { // from class: org.fdroid.fdroid.views.updates.UpdatesViewBinder$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setUpEmptyUpdatingProgress(((Boolean) obj).booleanValue());
            }
        });
        this.emptyUpdatingProgress.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpEmptyUpdatingProgress(boolean z) {
        if (z) {
            this.emptyState.setVisibility(8);
            this.emptyUpdatingProgress.setVisibility(0);
        } else {
            this.emptyState.setVisibility(0);
            this.emptyUpdatingProgress.setVisibility(8);
        }
    }
}
