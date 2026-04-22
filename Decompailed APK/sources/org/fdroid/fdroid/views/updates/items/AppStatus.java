package org.fdroid.fdroid.views.updates.items;

import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import java.util.List;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
public class AppStatus extends AppUpdateData {
    public final AppUpdateStatusManager.AppUpdateStatus status;

    public AppStatus(AppCompatActivity appCompatActivity, AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        super(appCompatActivity);
        this.status = appUpdateStatus;
    }

    public static class Delegate extends AdapterDelegate {
        private final AppCompatActivity activity;

        @Override // com.hannesdorfmann.adapterdelegates4.AdapterDelegate
        protected /* bridge */ /* synthetic */ void onBindViewHolder(Object obj, int i, RecyclerView.ViewHolder viewHolder, List list) {
            onBindViewHolder((List<AppUpdateData>) obj, i, viewHolder, (List<Object>) list);
        }

        public Delegate(AppCompatActivity appCompatActivity) {
            this.activity = appCompatActivity;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.hannesdorfmann.adapterdelegates4.AdapterDelegate
        public boolean isForViewType(List<AppUpdateData> list, int i) {
            return list.get(i) instanceof AppStatus;
        }

        @Override // com.hannesdorfmann.adapterdelegates4.AdapterDelegate
        protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
            AppCompatActivity appCompatActivity = this.activity;
            return new AppStatusListItemController(appCompatActivity, appCompatActivity.getLayoutInflater().inflate(R.layout.updateable_app_status_item, viewGroup, false));
        }

        protected void onBindViewHolder(List<AppUpdateData> list, int i, RecyclerView.ViewHolder viewHolder, List<Object> list2) {
            AppUpdateStatusManager.AppUpdateStatus appUpdateStatus = ((AppStatus) list.get(i)).status;
            ((AppStatusListItemController) viewHolder).bindModel(appUpdateStatus.app, appUpdateStatus.apk, appUpdateStatus);
        }
    }
}
