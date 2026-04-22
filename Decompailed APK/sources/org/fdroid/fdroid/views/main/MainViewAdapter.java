package org.fdroid.fdroid.views.main;

import android.util.SparseIntArray;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
class MainViewAdapter extends RecyclerView.Adapter {
    private final AppCompatActivity activity;
    private final SparseIntArray positionToId;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MainViewController mainViewController, int i) {
    }

    MainViewAdapter(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
        setHasStableIds(true);
        Menu menu = new PopupMenu(appCompatActivity, null).getMenu();
        appCompatActivity.getMenuInflater().inflate(R.menu.main_activity_screens, menu);
        this.positionToId = new SparseIntArray(menu.size());
        for (int i = 0; i < menu.size(); i++) {
            this.positionToId.append(i, menu.getItem(i).getItemId());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(MainViewController mainViewController) {
        if (getItemId(mainViewController.getAbsoluteAdapterPosition()) == R.id.updates) {
            mainViewController.unbindUpdates();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(MainViewController mainViewController) {
        long itemId = getItemId(mainViewController.getAbsoluteAdapterPosition());
        if (itemId == R.id.updates) {
            mainViewController.bindUpdates();
        } else if (itemId == R.id.nearby) {
            NearbyViewBinder.updateUsbOtg(this.activity);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MainViewController onCreateViewHolder(ViewGroup viewGroup, int i) {
        MainViewController mainViewControllerCreateEmptyView = createEmptyView(this.activity);
        if (i == R.id.latest) {
            mainViewControllerCreateEmptyView.bindLatestView();
        } else if (i == R.id.categories) {
            mainViewControllerCreateEmptyView.bindCategoriesView();
        } else if (i == R.id.nearby) {
            mainViewControllerCreateEmptyView.bindSwapView();
        } else if (i != R.id.updates) {
            if (i == R.id.settings) {
                mainViewControllerCreateEmptyView.bindSettingsView();
            } else {
                throw new IllegalStateException("Unknown view type " + i);
            }
        }
        return mainViewControllerCreateEmptyView;
    }

    private static MainViewController createEmptyView(AppCompatActivity appCompatActivity) {
        FrameLayout frameLayout = new FrameLayout(appCompatActivity);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return new MainViewController(appCompatActivity, frameLayout);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.positionToId.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.positionToId.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return this.positionToId.get(i);
    }

    int adapterPositionFromItemId(int i) {
        return this.positionToId.indexOfValue(i);
    }
}
