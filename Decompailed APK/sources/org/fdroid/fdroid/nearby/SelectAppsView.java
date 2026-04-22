package org.fdroid.fdroid.nearby;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;

/* JADX INFO: loaded from: classes2.dex */
public class SelectAppsView extends SwapView {
    private AppListAdapter adapter;
    private ListView listView;

    public SelectAppsView(Context context) {
        super(context);
    }

    public SelectAppsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SelectAppsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SelectAppsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.listView = (ListView) findViewById(R.id.list);
        AppListAdapter appListAdapter = new AppListAdapter(this.listView, getContext().getPackageManager().getInstalledPackages(0));
        this.adapter = appListAdapter;
        this.listView.setAdapter((ListAdapter) appListAdapter);
        this.listView.setChoiceMode(2);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: org.fdroid.fdroid.nearby.SelectAppsView$$ExternalSyntheticLambda0
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                this.f$0.lambda$onFinishInflate$0(adapterView, view, i, j);
            }
        });
        afterAppsLoaded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onFinishInflate$0(AdapterView adapterView, View view, int i, long j) {
        toggleAppSelected(i);
    }

    @Override // org.fdroid.fdroid.nearby.SwapView
    public void setCurrentFilterString(String str) {
        super.setCurrentFilterString(str);
        this.adapter.setSearchTerm(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleAppSelected(int i) {
        String str = this.adapter.getItem(i).packageName;
        if (getActivity().getSwapService().hasSelectedPackage(str)) {
            getActivity().getSwapService().deselectPackage(str);
        } else {
            getActivity().getSwapService().selectPackage(str);
        }
        LocalRepoService.create(getContext(), getActivity().getSwapService().getAppsToSwap());
    }

    public void afterAppsLoaded() {
        for (int i = 0; i < this.listView.getCount(); i++) {
            InstalledApp installedApp = (InstalledApp) this.listView.getItemAtPosition(i);
            getActivity().getSwapService().ensureFDroidSelected();
            Iterator<String> it = getActivity().getSwapService().getAppsToSwap().iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(installedApp.packageName, it.next())) {
                    this.listView.setItemChecked(i, true);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class AppListAdapter extends BaseAdapter {
        private final List<InstalledApp> allPackages;
        private final Context context;
        private Drawable defaultAppIcon;
        private final List<InstalledApp> filteredPackages = new ArrayList();
        private LayoutInflater inflater;
        private final ListView listView;

        AppListAdapter(ListView listView, List<PackageInfo> list) {
            this.context = SelectAppsView.this.getContext();
            this.listView = listView;
            this.allPackages = new ArrayList(list.size());
            Iterator<PackageInfo> it = list.iterator();
            while (it.hasNext()) {
                this.allPackages.add(new InstalledApp(this.context, it.next()));
            }
            this.filteredPackages.addAll(this.allPackages);
        }

        void setSearchTerm(String str) {
            this.filteredPackages.clear();
            if (TextUtils.isEmpty(str)) {
                this.filteredPackages.addAll(this.allPackages);
            } else {
                Objects.requireNonNull(str);
                String lowerCase = str.toLowerCase(Locale.US);
                for (InstalledApp installedApp : this.allPackages) {
                    if (installedApp.name.toLowerCase(Locale.US).contains(lowerCase)) {
                        this.filteredPackages.add(installedApp);
                    }
                }
            }
            notifyDataSetChanged();
        }

        private LayoutInflater getInflater(Context context) {
            if (this.inflater == null) {
                this.inflater = (LayoutInflater) ContextCompat.getSystemService(new ContextThemeWrapper(context, R.style.SwapTheme_AppList_ListItem), LayoutInflater.class);
            }
            return this.inflater;
        }

        private Drawable getDefaultAppIcon(Context context) {
            if (this.defaultAppIcon == null) {
                this.defaultAppIcon = ContextCompat.getDrawable(context, android.R.drawable.sym_def_app_icon);
            }
            return this.defaultAppIcon;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getInflater(this.context).inflate(R.layout.select_local_apps_list_item, viewGroup, false);
            }
            bindView(view, this.context, i);
            return view;
        }

        public void bindView(View view, Context context, final int i) {
            Drawable defaultAppIcon;
            InstalledApp item = getItem(i);
            TextView textView = (TextView) view.findViewById(R.id.package_name);
            TextView textView2 = (TextView) view.findViewById(R.id.application_label);
            ImageView imageView = (ImageView) view.findViewById(android.R.id.icon);
            try {
                defaultAppIcon = context.getPackageManager().getApplicationIcon(item.packageName);
            } catch (PackageManager.NameNotFoundException unused) {
                defaultAppIcon = getDefaultAppIcon(context);
            }
            textView.setText(item.packageName);
            textView2.setText(item.name);
            imageView.setImageDrawable(defaultAppIcon);
            View viewFindViewById = view.findViewById(R.id.checkbox);
            if (viewFindViewById != null) {
                CheckBox checkBox = (CheckBox) viewFindViewById;
                checkBox.setOnCheckedChangeListener(null);
                checkBox.setChecked(this.listView.isItemChecked(i));
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.fdroid.fdroid.nearby.SelectAppsView$AppListAdapter$$ExternalSyntheticLambda0
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        this.f$0.lambda$bindView$0(i, compoundButton, z);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindView$0(int i, CompoundButton compoundButton, boolean z) {
            this.listView.setItemChecked(i, z);
            SelectAppsView.this.toggleAppSelected(i);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.filteredPackages.size();
        }

        @Override // android.widget.Adapter
        public InstalledApp getItem(int i) {
            return this.filteredPackages.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return getItem(i).hashCode();
        }
    }

    private static class InstalledApp {
        final String name;
        final String packageName;

        InstalledApp(String str, String str2) {
            this.packageName = str;
            this.name = str2;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        InstalledApp(Context context, PackageInfo packageInfo) {
            String str = packageInfo.packageName;
            this(str, Utils.getApplicationLabel(context, str));
        }
    }
}
