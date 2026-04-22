package org.fdroid.fdroid.views.main;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.views.updates.UpdatesViewBinder;

/* JADX INFO: loaded from: classes2.dex */
class MainViewController extends RecyclerView.ViewHolder {
    private final AppCompatActivity activity;
    private final FrameLayout frame;
    private UpdatesViewBinder updatesView;

    MainViewController(AppCompatActivity appCompatActivity, FrameLayout frameLayout) {
        super(frameLayout);
        this.updatesView = null;
        this.activity = appCompatActivity;
        this.frame = frameLayout;
    }

    public void bindLatestView() {
        new LatestViewBinder(this.activity, this.frame);
    }

    public void bindUpdates() {
        if (this.updatesView == null) {
            this.updatesView = new UpdatesViewBinder(this.activity, this.frame);
        }
        this.updatesView.bind();
    }

    public void unbindUpdates() {
        UpdatesViewBinder updatesViewBinder = this.updatesView;
        if (updatesViewBinder != null) {
            updatesViewBinder.unbind();
        }
    }

    public void bindCategoriesView() {
        new CategoriesViewBinder(this.activity, this.frame);
    }

    public void bindSwapView() {
        try {
            new NearbyViewBinder(this.activity, this.frame);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void bindSettingsView() {
        this.activity.getLayoutInflater().inflate(R.layout.main_tab_settings, (ViewGroup) this.frame, true);
    }
}
