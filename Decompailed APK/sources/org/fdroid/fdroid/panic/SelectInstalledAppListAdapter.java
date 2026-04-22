package org.fdroid.fdroid.panic;

import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Set;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.views.installed.InstalledAppListAdapter;
import org.fdroid.fdroid.views.installed.InstalledAppListItemController;

/* JADX INFO: loaded from: classes2.dex */
public class SelectInstalledAppListAdapter extends InstalledAppListAdapter {
    private final Set<String> selectedApps;

    SelectInstalledAppListAdapter(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
        Preferences preferences = Preferences.get();
        Set<String> panicWipeSet = preferences.getPanicWipeSet();
        this.selectedApps = panicWipeSet;
        preferences.setPanicTmpSelectedSet(panicWipeSet);
    }

    @Override // org.fdroid.fdroid.views.installed.InstalledAppListAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public InstalledAppListItemController onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SelectInstalledAppListItemController(this.activity, this.activity.getLayoutInflater().inflate(R.layout.installed_app_list_item, viewGroup, false), this.selectedApps);
    }
}
