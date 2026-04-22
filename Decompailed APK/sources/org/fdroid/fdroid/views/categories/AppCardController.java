package org.fdroid.fdroid.views.categories;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import org.fdroid.database.AppOverviewItem;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.views.AppDetailsActivity;

/* JADX INFO: loaded from: classes2.dex */
public class AppCardController extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int DAYS_TO_CONSIDER_NEW = 14;
    private final AppCompatActivity activity;
    private AppOverviewItem currentApp;
    private final ImageView icon;
    private final TextView name;
    private final TextView newTag;
    private final TextView summary;

    public AppCardController(AppCompatActivity appCompatActivity, View view) {
        super(view);
        this.activity = appCompatActivity;
        this.icon = (ImageView) ViewCompat.requireViewById(view, R.id.icon);
        this.name = (TextView) ViewCompat.requireViewById(view, R.id.name);
        this.summary = (TextView) ViewCompat.requireViewById(view, R.id.summary);
        this.newTag = (TextView) view.findViewById(R.id.new_tag);
        view.setOnClickListener(this);
    }

    public void bindApp(AppOverviewItem appOverviewItem) {
        this.currentApp = appOverviewItem;
        this.name.setText(appOverviewItem.getName());
        this.summary.setText(appOverviewItem.getSummary());
        if (this.newTag != null) {
            if (isConsideredNew(appOverviewItem)) {
                this.newTag.setVisibility(0);
            } else {
                this.newTag.setVisibility(8);
            }
        }
        ImageView imageView = this.icon;
        Utils.setIconFromRepoOrPM(appOverviewItem, imageView, imageView.getContext());
    }

    private boolean isConsideredNew(AppOverviewItem appOverviewItem) {
        return appOverviewItem.getAdded() == appOverviewItem.getLastUpdated() && Utils.daysSince(appOverviewItem.getAdded()) <= 14;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.currentApp == null) {
            return;
        }
        Intent intent = new Intent(this.activity, (Class<?>) AppDetailsActivity.class);
        intent.putExtra(AppDetailsActivity.EXTRA_APPID, this.currentApp.getPackageName());
        ContextCompat.startActivity(this.activity, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity, Pair.create(this.icon, this.activity.getString(R.string.transition_app_item_icon))).toBundle());
    }
}
