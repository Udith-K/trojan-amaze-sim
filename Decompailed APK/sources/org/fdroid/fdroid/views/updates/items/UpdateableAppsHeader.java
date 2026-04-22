package org.fdroid.fdroid.views.updates.items;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.views.updates.UpdatesAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateableAppsHeader extends AppUpdateData {
    public final UpdatesAdapter adapter;
    public final List<UpdateableApp> apps;

    public UpdateableAppsHeader(AppCompatActivity appCompatActivity, UpdatesAdapter updatesAdapter, List<UpdateableApp> list) {
        super(appCompatActivity);
        this.apps = list;
        this.adapter = updatesAdapter;
    }

    public static class Delegate extends AdapterDelegate {
        private final LayoutInflater inflater;

        @Override // com.hannesdorfmann.adapterdelegates4.AdapterDelegate
        protected /* bridge */ /* synthetic */ void onBindViewHolder(Object obj, int i, RecyclerView.ViewHolder viewHolder, List list) {
            onBindViewHolder((List<AppUpdateData>) obj, i, viewHolder, (List<Object>) list);
        }

        public Delegate(AppCompatActivity appCompatActivity) {
            this.inflater = appCompatActivity.getLayoutInflater();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.hannesdorfmann.adapterdelegates4.AdapterDelegate
        public boolean isForViewType(List<AppUpdateData> list, int i) {
            return list.get(i) instanceof UpdateableAppsHeader;
        }

        @Override // com.hannesdorfmann.adapterdelegates4.AdapterDelegate
        protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
            return new ViewHolder(this.inflater.inflate(R.layout.updates_header, viewGroup, false));
        }

        protected void onBindViewHolder(List<AppUpdateData> list, int i, RecyclerView.ViewHolder viewHolder, List<Object> list2) {
            ((ViewHolder) viewHolder).bindHeader((UpdateableAppsHeader) list.get(i));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView appsToUpdate;
        private final Button downloadAll;
        private UpdateableAppsHeader header;
        private final Button toggleAppsToUpdate;
        private final TextView updatesAvailable;

        public ViewHolder(View view) {
            super(view);
            this.updatesAvailable = (TextView) view.findViewById(R.id.text_updates_available);
            this.appsToUpdate = (TextView) view.findViewById(R.id.text_apps_to_update);
            Button button = (Button) view.findViewById(R.id.button_download_all);
            this.downloadAll = button;
            Button button2 = (Button) view.findViewById(R.id.button_toggle_apps_to_update);
            this.toggleAppsToUpdate = button2;
            button2.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.updates.items.UpdateableAppsHeader$ViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$new$0(view2);
                }
            });
            button.setVisibility(0);
            button.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.updates.items.UpdateableAppsHeader$ViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$new$1(view2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(View view) {
            this.header.adapter.toggleAllUpdateableApps();
            updateToggleButtonText();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$1(View view) {
            this.downloadAll.setVisibility(8);
            AppUpdateStatusManager.getInstance(this.header.activity).checkForUpdatesAndInstall();
        }

        void bindHeader(UpdateableAppsHeader updateableAppsHeader) {
            this.header = updateableAppsHeader;
            this.updatesAvailable.setText(this.itemView.getResources().getQuantityString(R.plurals.updates__download_updates_for_apps, updateableAppsHeader.apps.size(), Integer.valueOf(updateableAppsHeader.apps.size())));
            ArrayList arrayList = new ArrayList(updateableAppsHeader.apps.size());
            Iterator<UpdateableApp> it = updateableAppsHeader.apps.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().app.name);
            }
            this.appsToUpdate.setText(TextUtils.join(", ", arrayList));
            updateToggleButtonText();
        }

        private void updateToggleButtonText() {
            if (this.header.adapter.canViewAllUpdateableApps()) {
                this.toggleAppsToUpdate.setText(R.string.updates__hide_updateable_apps);
            } else {
                this.toggleAppsToUpdate.setText(R.string.updates__show_updateable_apps);
            }
        }
    }
}
