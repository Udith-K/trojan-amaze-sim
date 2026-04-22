package org.fdroid.fdroid.views;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.compose.ui.platform.ComposeView;
import androidx.compose.ui.platform.ViewCompositionStrategy;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.os.ConfigurationCompat;
import androidx.core.text.HtmlCompat;
import androidx.core.text.util.LinkifyCompat;
import androidx.core.util.Consumer;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.io.FilenameUtils;
import org.fdroid.database.AppPrefs;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.installer.Installer;
import org.fdroid.fdroid.installer.SessionInstallManager;
import org.fdroid.fdroid.privileged.views.AppDiff;
import org.fdroid.fdroid.privileged.views.AppSecurityPermissions;
import org.fdroid.fdroid.views.ScreenShotsRecyclerViewAdapter;
import org.fdroid.fdroid.views.appdetails.AntiFeaturesListingView;
import org.fdroid.fdroid.views.appdetails.RepoChooserKt;
import org.fdroid.fdroid.views.main.MainActivity;

/* JADX INFO: loaded from: classes2.dex */
public class AppDetailsRecyclerViewAdapter extends RecyclerView.Adapter {
    public static final String TAG = "AppDetailsRecyclerViewA";
    private static final int VIEWTYPE_DONATE = 2;
    private static final int VIEWTYPE_HEADER = 0;
    private static final int VIEWTYPE_LINKS = 3;
    private static final int VIEWTYPE_NO_VERSIONS = 6;
    private static final int VIEWTYPE_PERMISSIONS = 4;
    private static final int VIEWTYPE_SCREENSHOTS = 1;
    private static final int VIEWTYPE_VERSION = 8;
    private static final int VIEWTYPE_VERSIONS = 5;
    private static final int VIEWTYPE_VERSIONS_LOADING = 7;
    private App app;
    private final AppDetailsRecyclerViewAdapterCallbacks callbacks;
    private final Context context;
    private Apk downloadedApk;
    private HeaderViewHolder headerView;
    private RecyclerView recyclerView;
    private boolean showVersions;
    private Apk suggestedApk;
    private final List<Object> items = new ArrayList();
    private final List<Repository> repos = new ArrayList();
    private Long preferredRepoId = null;
    private final List<Apk> versions = new ArrayList();
    private final List<Apk> compatibleVersionsDifferentSigner = new ArrayList();
    private boolean versionsLoading = true;
    private final HashMap<String, Boolean> versionsExpandTracker = new HashMap<>();

    public interface AppDetailsRecyclerViewAdapterCallbacks {
        void installApk(Apk apk);

        void installCancel();

        boolean isAppDownloading();

        void launchApk();

        void onPreferredRepoChanged(long j);

        void onRepoChanged(long j);

        void openUrl(String str);

        void uninstallApk();
    }

    public AppDetailsRecyclerViewAdapter(Context context, App app, AppDetailsRecyclerViewAdapterCallbacks appDetailsRecyclerViewAdapterCallbacks) {
        this.context = context;
        this.callbacks = appDetailsRecyclerViewAdapterCallbacks;
        this.app = app;
        addItem(0);
    }

    public void updateItems(App app, List<Apk> list, AppPrefs appPrefs) {
        this.app = app;
        this.versionsLoading = list == null;
        this.items.clear();
        this.versions.clear();
        this.suggestedApk = null;
        this.compatibleVersionsDifferentSigner.clear();
        boolean zShowIncompatibleVersions = Preferences.get().showIncompatibleVersions();
        if (list != null) {
            for (Apk apk : list) {
                boolean z = apk.compatible || zShowIncompatibleVersions;
                String str = app.installedSigner;
                boolean z2 = str == null || zShowIncompatibleVersions || TextUtils.equals(str, apk.signer);
                if (z) {
                    this.compatibleVersionsDifferentSigner.add(apk);
                    if (z2) {
                        this.versions.add(apk);
                        if (!this.versionsExpandTracker.containsKey(apk.getApkPath())) {
                            this.versionsExpandTracker.put(apk.getApkPath(), Boolean.FALSE);
                        }
                    }
                }
            }
        }
        if (list != null) {
            Apk apkFindSuggestedApk = app.findSuggestedApk(list, appPrefs);
            String str2 = app.installedSigner;
            if (str2 == null || (apkFindSuggestedApk != null && str2.equals(apkFindSuggestedApk.signer))) {
                this.suggestedApk = apkFindSuggestedApk;
            }
        }
        addItem(0);
        if (!app.getAllScreenshots().isEmpty()) {
            addItem(1);
        }
        addItem(2);
        addItem(3);
        addItem(4);
        if (this.versionsLoading) {
            addItem(7);
        } else if (this.versions.isEmpty()) {
            addItem(6);
        } else {
            addItem(5);
            if (this.showVersions) {
                setShowVersions(true);
            }
        }
        notifyDataSetChanged();
    }

    void setRepos(List<Repository> list, long j) {
        this.repos.clear();
        this.repos.addAll(list);
        this.preferredRepoId = Long.valueOf(j);
        notifyItemChanged(0);
    }

    void setShowVersions(boolean z) {
        setShowVersions(z, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setShowVersions(boolean z, boolean z2) {
        this.showVersions = z;
        boolean zRemoveAll = this.items.removeAll(this.versions);
        int iIndexOf = this.items.indexOf(5);
        int i = iIndexOf + 1;
        if (!z) {
            if (zRemoveAll) {
                notifyItemRangeRemoved(i, this.versions.size());
                RecyclerView recyclerView = this.recyclerView;
                if (recyclerView == null || !z2) {
                    return;
                }
                recyclerView.smoothScrollToPosition(iIndexOf);
                return;
            }
            return;
        }
        this.items.addAll(i, this.versions);
        notifyItemRangeInserted(i, this.versions.size());
        if (this.recyclerView == null || !z2) {
            return;
        }
        LinearSmoothScroller linearSmoothScroller = getLinearSmoothScroller(i);
        RecyclerView.LayoutManager layoutManager = this.recyclerView.getLayoutManager();
        Objects.requireNonNull(layoutManager);
        layoutManager.startSmoothScroll(linearSmoothScroller);
    }

    private LinearSmoothScroller getLinearSmoothScroller(int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(this.context) { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter.1
            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 75.0f / displayMetrics.densityDpi;
            }
        };
        linearSmoothScroller.setTargetPosition((i + Math.min(this.versions.size(), 5)) - 1);
        return linearSmoothScroller;
    }

    private void addItem(int i) {
        if (i != 2 || shouldShowDonate()) {
            if (i != 4 || shouldShowPermissions()) {
                this.items.add(Integer.valueOf(i));
            }
        }
    }

    private boolean shouldShowPermissions() {
        App app = this.app;
        if (app == null) {
            return false;
        }
        Apk apk = app.installedApk;
        if (apk == null) {
            apk = this.suggestedApk;
        }
        boolean z = apk != null && apk.compatible;
        if (this.versions.isEmpty()) {
            return false;
        }
        return z || Preferences.get().showIncompatibleVersions();
    }

    private boolean shouldShowDonate() {
        App app = this.app;
        if (app == null) {
            return false;
        }
        return uriIsSetAndCanBeOpened(app.donate) || uriIsSetAndCanBeOpened(this.app.getBitcoinUri()) || uriIsSetAndCanBeOpened(this.app.getLitecoinUri()) || uriIsSetAndCanBeOpened(this.app.getFlattrUri()) || uriIsSetAndCanBeOpened(this.app.getLiberapayUri()) || uriIsSetAndCanBeOpened(this.app.getOpenCollectiveUri());
    }

    private void notifyVersionViewsChanged() {
        notifyItemRangeChanged(this.items.indexOf(5) + 1, this.versions.size());
    }

    void notifyAboutDownloadedApk(Apk apk) {
        this.downloadedApk = apk;
        notifyVersionViewsChanged();
    }

    void clearProgress() {
        HeaderViewHolder headerViewHolder = this.headerView;
        if (headerViewHolder != null) {
            headerViewHolder.clearProgress();
        }
        if (this.downloadedApk != null) {
            notifyVersionViewsChanged();
            this.downloadedApk = null;
        }
    }

    void setIndeterminateProgress(int i) {
        HeaderViewHolder headerViewHolder = this.headerView;
        if (headerViewHolder != null) {
            headerViewHolder.setIndeterminateProgress(i);
        }
    }

    void setProgress(long j, long j2) {
        HeaderViewHolder headerViewHolder = this.headerView;
        if (headerViewHolder != null) {
            headerViewHolder.setProgress(j, j2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        switch (i) {
            case 0:
                return new HeaderViewHolder(layoutInflaterFrom.inflate(R.layout.app_details2_header, viewGroup, false));
            case 1:
                return new ScreenShotsViewHolder(layoutInflaterFrom.inflate(R.layout.app_details2_screenshots, viewGroup, false));
            case 2:
                return new DonateViewHolder(layoutInflaterFrom.inflate(R.layout.app_details2_donate, viewGroup, false));
            case 3:
                return new LinksViewHolder(layoutInflaterFrom.inflate(R.layout.app_details2_links, viewGroup, false));
            case 4:
                return new PermissionsViewHolder(layoutInflaterFrom.inflate(R.layout.app_details2_links, viewGroup, false));
            case 5:
                return new VersionsViewHolder(layoutInflaterFrom.inflate(R.layout.app_details2_links, viewGroup, false));
            case 6:
                return new NoVersionsViewHolder(layoutInflaterFrom.inflate(R.layout.app_details2_links, viewGroup, false));
            case 7:
                return new VersionsLoadingViewHolder(layoutInflaterFrom.inflate(R.layout.app_details2_loading, viewGroup, false));
            case 8:
                return new VersionViewHolder(layoutInflaterFrom.inflate(R.layout.app_details2_version_item, viewGroup, false));
            default:
                throw new IllegalStateException("Unknown view type: " + i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case 0:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
                this.headerView = headerViewHolder;
                headerViewHolder.bindModel();
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                ((AppDetailsViewHolder) viewHolder).bindModel();
                break;
            case 8:
                ((VersionViewHolder) viewHolder).bindModel((Apk) this.items.get(i));
                break;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof HeaderViewHolder) {
            this.headerView = null;
        }
        super.onViewRecycled(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.items.get(i) instanceof Apk) {
            return 8;
        }
        return ((Integer) this.items.get(i)).intValue();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        private static final int MAX_LINES = 5;
        final TextView antiFeaturesLabelView;
        final AntiFeaturesListingView antiFeaturesListingView;
        final View antiFeaturesSectionView;
        final View antiFeaturesWarningView;
        final TextView authorView;
        final Button buttonPrimaryView;
        final Button buttonSecondaryView;
        boolean descriptionIsExpanded;
        final Button descriptionMoreView;
        final TextView descriptionView;
        final ImageView iconView;
        final TextView lastUpdateView;
        final LinearProgressIndicator progressBar;
        final View progressCancel;
        final TextView progressLabel;
        final View progressLayout;
        final TextView progressPercent;
        final ComposeView repoChooserView;
        final TextView summaryView;
        final TextView titleView;
        final TextView warningView;
        final TextView whatsNewView;

        HeaderViewHolder(View view) {
            super(view);
            this.iconView = (ImageView) view.findViewById(R.id.icon);
            this.titleView = (TextView) view.findViewById(R.id.title);
            this.authorView = (TextView) view.findViewById(R.id.author);
            this.lastUpdateView = (TextView) view.findViewById(R.id.text_last_update);
            ComposeView composeView = (ComposeView) view.findViewById(R.id.repoChooserView);
            this.repoChooserView = composeView;
            composeView.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed.INSTANCE);
            this.warningView = (TextView) view.findViewById(R.id.warning);
            this.summaryView = (TextView) view.findViewById(R.id.summary);
            this.whatsNewView = (TextView) view.findViewById(R.id.latest);
            TextView textView = (TextView) view.findViewById(R.id.description);
            this.descriptionView = textView;
            Button button = (Button) view.findViewById(R.id.description_more);
            this.descriptionMoreView = button;
            this.antiFeaturesSectionView = view.findViewById(R.id.anti_features_section);
            this.antiFeaturesLabelView = (TextView) view.findViewById(R.id.label_anti_features);
            this.antiFeaturesWarningView = view.findViewById(R.id.anti_features_warning);
            this.antiFeaturesListingView = (AntiFeaturesListingView) view.findViewById(R.id.anti_features_full_listing);
            this.buttonPrimaryView = (Button) view.findViewById(R.id.primaryButtonView);
            this.buttonSecondaryView = (Button) view.findViewById(R.id.secondaryButtonView);
            this.progressLayout = view.findViewById(R.id.progress_layout);
            this.progressBar = (LinearProgressIndicator) view.findViewById(R.id.progress_bar);
            this.progressLabel = (TextView) view.findViewById(R.id.progress_label);
            this.progressPercent = (TextView) view.findViewById(R.id.progress_percent);
            this.progressCancel = view.findViewById(R.id.progress_cancel);
            textView.setMaxLines(5);
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            button.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$HeaderViewHolder$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$new$0(view2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(View view) {
            TransitionManager.beginDelayedTransition(AppDetailsRecyclerViewAdapter.this.recyclerView, null);
            if (TextViewCompat.getMaxLines(this.descriptionView) != 5) {
                this.descriptionView.setMaxLines(5);
                this.descriptionMoreView.setText(R.string.more);
                this.descriptionIsExpanded = false;
            } else {
                this.descriptionView.setMaxLines(Integer.MAX_VALUE);
                this.descriptionMoreView.setText(R.string.less);
                this.descriptionIsExpanded = true;
            }
            updateAntiFeaturesWarning();
        }

        void clearProgress() {
            int i = 8;
            this.progressLayout.setVisibility(8);
            this.buttonPrimaryView.setVisibility(AppDetailsRecyclerViewAdapter.this.versions.isEmpty() ? 8 : 0);
            Button button = this.buttonSecondaryView;
            if (AppDetailsRecyclerViewAdapter.this.app != null && AppDetailsRecyclerViewAdapter.this.app.isUninstallable(AppDetailsRecyclerViewAdapter.this.context)) {
                i = 0;
            }
            button.setVisibility(i);
        }

        void setIndeterminateProgress(int i) {
            if (!this.progressBar.isIndeterminate()) {
                this.progressBar.hide();
                this.progressBar.setIndeterminate(true);
            }
            this.progressBar.show();
            this.progressLayout.setVisibility(0);
            this.buttonPrimaryView.setVisibility(8);
            this.buttonSecondaryView.setVisibility(8);
            this.progressLabel.setText(i);
            this.progressLabel.setContentDescription(AppDetailsRecyclerViewAdapter.this.context.getString(R.string.downloading));
            this.progressPercent.setText("");
            if (i == R.string.installing || i == R.string.uninstalling) {
                this.progressCancel.setVisibility(8);
            } else {
                this.progressCancel.setVisibility(0);
            }
        }

        void setProgress(long j, long j2) {
            this.progressLayout.setVisibility(0);
            this.buttonPrimaryView.setVisibility(8);
            this.buttonSecondaryView.setVisibility(8);
            this.progressCancel.setVisibility(0);
            if (j2 <= 0) {
                if (!this.progressBar.isIndeterminate()) {
                    this.progressBar.hide();
                    this.progressBar.setIndeterminate(true);
                }
            } else {
                this.progressBar.setProgressCompat(Utils.getPercent(j, j2), true);
            }
            this.progressBar.show();
            this.progressLabel.setContentDescription("");
            if (j2 <= 0 || j < 0) {
                if (j >= 0) {
                    this.progressLabel.setText(Utils.getFriendlySize(j));
                    this.progressLabel.setContentDescription(AppDetailsRecyclerViewAdapter.this.context.getString(R.string.downloading));
                    this.progressPercent.setText("");
                    return;
                }
                return;
            }
            int percent = Utils.getPercent(j, j2);
            this.progressLabel.setText(Utils.getFriendlySize(j) + " / " + Utils.getFriendlySize(j2));
            this.progressLabel.setContentDescription(AppDetailsRecyclerViewAdapter.this.context.getString(R.string.app__tts__downloading_progress, Integer.valueOf(percent)));
            this.progressPercent.setText(String.format(Locale.ENGLISH, "%d%%", Integer.valueOf(percent)));
        }

        void bindModel() {
            boolean z;
            if (AppDetailsRecyclerViewAdapter.this.app == null) {
                return;
            }
            App app = AppDetailsRecyclerViewAdapter.this.app;
            ImageView imageView = this.iconView;
            Utils.setIconFromRepoOrPM(app, imageView, imageView.getContext());
            this.titleView.setText(AppDetailsRecyclerViewAdapter.this.app.name);
            if (!TextUtils.isEmpty(AppDetailsRecyclerViewAdapter.this.app.authorName)) {
                this.authorView.setText(AppDetailsRecyclerViewAdapter.this.context.getString(R.string.by_author_format, AppDetailsRecyclerViewAdapter.this.app.authorName));
                this.authorView.setVisibility(0);
            } else {
                this.authorView.setVisibility(8);
            }
            if (AppDetailsRecyclerViewAdapter.this.app.lastUpdated != null) {
                String lastUpdated = Utils.formatLastUpdated(this.lastUpdateView.getContext().getResources(), AppDetailsRecyclerViewAdapter.this.app.lastUpdated);
                if (Preferences.get().expertMode() && AppDetailsRecyclerViewAdapter.this.suggestedApk != null && AppDetailsRecyclerViewAdapter.this.suggestedApk.apkFile != null && AppDetailsRecyclerViewAdapter.this.suggestedApk.apkFile.getSize() != null) {
                    lastUpdated = lastUpdated + " (" + Formatter.formatFileSize(AppDetailsRecyclerViewAdapter.this.context, AppDetailsRecyclerViewAdapter.this.suggestedApk.apkFile.getSize().longValue()) + ")";
                }
                this.lastUpdateView.setText(lastUpdated);
                this.lastUpdateView.setVisibility(0);
            } else {
                this.lastUpdateView.setVisibility(8);
            }
            if (AppDetailsRecyclerViewAdapter.this.app != null && AppDetailsRecyclerViewAdapter.this.preferredRepoId != null) {
                Set<String> defaultRepoAddresses = Preferences.get().getDefaultRepoAddresses(AppDetailsRecyclerViewAdapter.this.context);
                Repository repository = FDroidApp.getRepoManager(AppDetailsRecyclerViewAdapter.this.context).getRepository(AppDetailsRecyclerViewAdapter.this.app.repoId);
                if (AppDetailsRecyclerViewAdapter.this.repos.size() > 1 || (repository != null && !defaultRepoAddresses.contains(repository.getAddress()))) {
                    ComposeView composeView = this.repoChooserView;
                    List list = AppDetailsRecyclerViewAdapter.this.repos;
                    long j = AppDetailsRecyclerViewAdapter.this.app.repoId;
                    long jLongValue = AppDetailsRecyclerViewAdapter.this.preferredRepoId.longValue();
                    Consumer consumer = new Consumer() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$HeaderViewHolder$$ExternalSyntheticLambda0
                        @Override // androidx.core.util.Consumer
                        public final void accept(Object obj) {
                            this.f$0.lambda$bindModel$1((Repository) obj);
                        }
                    };
                    final AppDetailsRecyclerViewAdapterCallbacks appDetailsRecyclerViewAdapterCallbacks = AppDetailsRecyclerViewAdapter.this.callbacks;
                    Objects.requireNonNull(appDetailsRecyclerViewAdapterCallbacks);
                    RepoChooserKt.setContentRepoChooser(composeView, list, j, jLongValue, consumer, new Consumer() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$HeaderViewHolder$$ExternalSyntheticLambda1
                        @Override // androidx.core.util.Consumer
                        public final void accept(Object obj) {
                            appDetailsRecyclerViewAdapterCallbacks.onPreferredRepoChanged(((Long) obj).longValue());
                        }
                    });
                    this.repoChooserView.setVisibility(0);
                } else {
                    this.repoChooserView.setVisibility(8);
                }
            } else {
                this.repoChooserView.setVisibility(8);
            }
            if (AppDetailsRecyclerViewAdapter.this.suggestedApk == null && AppDetailsRecyclerViewAdapter.this.repos.size() > 1 && AppDetailsRecyclerViewAdapter.this.app.installedSigner != null && AppDetailsRecyclerViewAdapter.this.preferredRepoId != null && AppDetailsRecyclerViewAdapter.this.preferredRepoId.longValue() == AppDetailsRecyclerViewAdapter.this.app.repoId && !AppDetailsRecyclerViewAdapter.this.versionsLoading) {
                this.warningView.setBackgroundColor(ContextCompat.getColor(AppDetailsRecyclerViewAdapter.this.context, R.color.fdroid_red));
                this.warningView.setText(R.string.warning_no_compat_versions);
                this.warningView.setVisibility(0);
            } else if (SessionInstallManager.canBeUsed(AppDetailsRecyclerViewAdapter.this.context) && AppDetailsRecyclerViewAdapter.this.suggestedApk != null && !SessionInstallManager.isTargetSdkSupported(AppDetailsRecyclerViewAdapter.this.suggestedApk.targetSdkVersion)) {
                this.warningView.setBackgroundColor(MaterialColors.getColor(this.warningView, R.attr.warning));
                this.warningView.setText(R.string.warning_target_sdk);
                this.warningView.setVisibility(0);
            } else {
                this.warningView.setVisibility(8);
            }
            if (!TextUtils.isEmpty(AppDetailsRecyclerViewAdapter.this.app.summary)) {
                this.summaryView.setText(AppDetailsRecyclerViewAdapter.this.app.summary);
                this.summaryView.setVisibility(0);
            } else {
                this.summaryView.setVisibility(8);
            }
            if (AppDetailsRecyclerViewAdapter.this.suggestedApk == null || TextUtils.isEmpty(AppDetailsRecyclerViewAdapter.this.app.whatsNew)) {
                this.whatsNewView.setVisibility(8);
                this.summaryView.setBackgroundResource(0);
            } else {
                Locale locale = ConfigurationCompat.getLocales(AppDetailsRecyclerViewAdapter.this.context.getResources().getConfiguration()).get(0);
                StringBuilder sb = new StringBuilder();
                sb.append(this.whatsNewView.getContext().getString(R.string.details_new_in_version, AppDetailsRecyclerViewAdapter.this.suggestedApk.versionName).toUpperCase(locale));
                sb.append("\n\n");
                sb.append(AppDetailsRecyclerViewAdapter.this.app.whatsNew);
                this.whatsNewView.setText(sb);
                this.whatsNewView.setVisibility(0);
                RecyclerView recyclerView = AppDetailsRecyclerViewAdapter.this.recyclerView;
                View view = this.itemView;
                recyclerView.requestChildFocus(view, view);
            }
            Spanned spannedFromHtml = HtmlCompat.fromHtml(AppDetailsRecyclerViewAdapter.this.app.description, 0, null, new Utils.HtmlTagHandler());
            this.descriptionView.setMovementMethod(LinkMovementMethod.getInstance());
            this.descriptionView.setText(AppDetailsRecyclerViewAdapter.trimTrailingNewlines(spannedFromHtml));
            LinkifyCompat.addLinks(this.descriptionView, 1);
            CharSequence text = this.descriptionView.getText();
            if (text instanceof Spannable) {
                Spannable spannable = (Spannable) text;
                for (URLSpan uRLSpan : (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class)) {
                    int spanStart = spannable.getSpanStart(uRLSpan);
                    int spanEnd = spannable.getSpanEnd(uRLSpan);
                    int spanFlags = spannable.getSpanFlags(uRLSpan);
                    spannable.removeSpan(uRLSpan);
                    spannable.setSpan(new SafeURLSpan(uRLSpan.getURL()), spanStart, spanEnd, spanFlags);
                }
            }
            this.descriptionView.post(new Runnable() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$HeaderViewHolder$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$bindModel$2();
                }
            });
            this.antiFeaturesListingView.setApp(AppDetailsRecyclerViewAdapter.this.app);
            updateAntiFeaturesWarning();
            Iterator it = AppDetailsRecyclerViewAdapter.this.versions.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((Apk) it.next()).compatible) {
                        break;
                    }
                } else if (AppDetailsRecyclerViewAdapter.this.app.isInstalled(AppDetailsRecyclerViewAdapter.this.context)) {
                    break;
                } else {
                    z = false;
                }
            }
            z = true;
            this.buttonPrimaryView.setText(R.string.menu_install);
            this.buttonPrimaryView.setVisibility(z ? 0 : 8);
            this.buttonSecondaryView.setText(R.string.menu_uninstall);
            this.buttonSecondaryView.setVisibility(AppDetailsRecyclerViewAdapter.this.app.isUninstallable(AppDetailsRecyclerViewAdapter.this.context) ? 0 : 8);
            this.buttonSecondaryView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$HeaderViewHolder$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$bindModel$3(view2);
                }
            });
            if (AppDetailsRecyclerViewAdapter.this.callbacks.isAppDownloading()) {
                this.buttonPrimaryView.setText(R.string.downloading);
                this.buttonPrimaryView.setEnabled(false);
                this.buttonPrimaryView.setVisibility(8);
                this.buttonSecondaryView.setVisibility(8);
                this.progressLayout.setVisibility(0);
            } else if (!AppDetailsRecyclerViewAdapter.this.app.isInstalled(AppDetailsRecyclerViewAdapter.this.context) && AppDetailsRecyclerViewAdapter.this.suggestedApk != null) {
                this.progressLayout.setVisibility(8);
                this.buttonPrimaryView.setText(R.string.menu_install);
                this.buttonPrimaryView.setEnabled(true);
                this.buttonPrimaryView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$HeaderViewHolder$$ExternalSyntheticLambda4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f$0.lambda$bindModel$4(view2);
                    }
                });
            } else if (AppDetailsRecyclerViewAdapter.this.app.isInstalled(AppDetailsRecyclerViewAdapter.this.context)) {
                if (AppDetailsRecyclerViewAdapter.this.app.canAndWantToUpdate(AppDetailsRecyclerViewAdapter.this.suggestedApk)) {
                    this.buttonPrimaryView.setText(R.string.menu_upgrade);
                    this.buttonPrimaryView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$HeaderViewHolder$$ExternalSyntheticLambda5
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            this.f$0.lambda$bindModel$5(view2);
                        }
                    });
                } else {
                    Apk mediaApkifInstalled = AppDetailsRecyclerViewAdapter.this.app.getMediaApkifInstalled(AppDetailsRecyclerViewAdapter.this.context);
                    if (!AppDetailsRecyclerViewAdapter.this.context.getPackageName().equals(AppDetailsRecyclerViewAdapter.this.app.packageName) && AppDetailsRecyclerViewAdapter.this.context.getPackageManager().getLaunchIntentForPackage(AppDetailsRecyclerViewAdapter.this.app.packageName) != null) {
                        this.buttonPrimaryView.setText(R.string.menu_launch);
                        this.buttonPrimaryView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$HeaderViewHolder$$ExternalSyntheticLambda6
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view2) {
                                this.f$0.lambda$bindModel$6(view2);
                            }
                        });
                    } else if (!AppDetailsRecyclerViewAdapter.this.app.isApk && mediaApkifInstalled != null) {
                        File installedMediaFile = mediaApkifInstalled.getInstalledMediaFile(AppDetailsRecyclerViewAdapter.this.context);
                        if (!installedMediaFile.toString().startsWith(AppDetailsRecyclerViewAdapter.this.context.getApplicationInfo().dataDir)) {
                            final Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setDataAndType(FileProvider.getUriForFile(AppDetailsRecyclerViewAdapter.this.context, Installer.AUTHORITY, installedMediaFile), MimeTypeMap.getSingleton().getMimeTypeFromExtension(FilenameUtils.getExtension(installedMediaFile.getName())));
                            intent.setFlags(65);
                            if (!AppDetailsRecyclerViewAdapter.this.context.getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
                                this.buttonPrimaryView.setText(R.string.menu_open);
                                this.buttonPrimaryView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$HeaderViewHolder$$ExternalSyntheticLambda7
                                    @Override // android.view.View.OnClickListener
                                    public final void onClick(View view2) {
                                        this.f$0.lambda$bindModel$7(intent, view2);
                                    }
                                });
                            } else {
                                this.buttonPrimaryView.setVisibility(8);
                            }
                        } else {
                            this.buttonPrimaryView.setVisibility(8);
                        }
                    } else {
                        this.buttonPrimaryView.setVisibility(8);
                    }
                }
                this.buttonPrimaryView.setEnabled(true);
                this.progressLayout.setVisibility(8);
            } else {
                this.progressLayout.setVisibility(8);
            }
            this.progressCancel.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$HeaderViewHolder$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$bindModel$8(view2);
                }
            });
            if (AppDetailsRecyclerViewAdapter.this.versionsLoading) {
                this.progressLayout.setVisibility(0);
                this.progressLabel.setVisibility(8);
                this.progressCancel.setVisibility(8);
                this.progressPercent.setVisibility(8);
                this.progressBar.setIndeterminate(true);
                this.progressBar.setVisibility(0);
            } else {
                this.progressLabel.setVisibility(0);
                this.progressCancel.setVisibility(0);
                this.progressPercent.setVisibility(0);
            }
            if (AppDetailsRecyclerViewAdapter.this.preferredRepoId == null || AppDetailsRecyclerViewAdapter.this.preferredRepoId.longValue() == AppDetailsRecyclerViewAdapter.this.app.repoId) {
                return;
            }
            this.buttonPrimaryView.setVisibility(8);
            this.buttonSecondaryView.setVisibility(8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindModel$1(Repository repository) {
            AppDetailsRecyclerViewAdapter.this.callbacks.onRepoChanged(repository.getRepoId());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindModel$2() {
            boolean z = AppDetailsRecyclerViewAdapter.this.app.antiFeatures == null || AppDetailsRecyclerViewAdapter.this.app.antiFeatures.length == 0;
            if (this.descriptionView.getLineCount() <= 5 && z) {
                this.descriptionMoreView.setVisibility(8);
            } else {
                this.descriptionMoreView.setVisibility(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindModel$3(View view) {
            AppDetailsRecyclerViewAdapter.this.callbacks.uninstallApk();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindModel$4(View view) {
            AppDetailsRecyclerViewAdapter.this.callbacks.installApk(AppDetailsRecyclerViewAdapter.this.suggestedApk);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindModel$5(View view) {
            AppDetailsRecyclerViewAdapter.this.callbacks.installApk(AppDetailsRecyclerViewAdapter.this.suggestedApk);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindModel$6(View view) {
            AppDetailsRecyclerViewAdapter.this.callbacks.launchApk();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindModel$7(Intent intent, View view) {
            try {
                AppDetailsRecyclerViewAdapter.this.context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.e(AppDetailsRecyclerViewAdapter.TAG, "Error starting activity: ", e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindModel$8(View view) {
            AppDetailsRecyclerViewAdapter.this.callbacks.installCancel();
        }

        private void updateAntiFeaturesWarning() {
            if (AppDetailsRecyclerViewAdapter.this.app != null && (AppDetailsRecyclerViewAdapter.this.app.antiFeatures == null || AppDetailsRecyclerViewAdapter.this.app.antiFeatures.length == 0)) {
                this.antiFeaturesSectionView.setVisibility(8);
                return;
            }
            if (this.descriptionIsExpanded) {
                this.antiFeaturesSectionView.setVisibility(0);
                this.antiFeaturesWarningView.setVisibility(8);
                this.antiFeaturesLabelView.setVisibility(0);
                this.antiFeaturesListingView.setVisibility(0);
                return;
            }
            this.antiFeaturesSectionView.setVisibility(0);
            this.antiFeaturesWarningView.setVisibility(0);
            this.antiFeaturesLabelView.setVisibility(8);
            this.antiFeaturesListingView.setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = null;
        super.onDetachedFromRecyclerView(recyclerView);
    }

    private static abstract class AppDetailsViewHolder extends RecyclerView.ViewHolder {
        public abstract void bindModel();

        AppDetailsViewHolder(View view) {
            super(view);
        }
    }

    private class ScreenShotsViewHolder extends AppDetailsViewHolder implements ScreenShotsRecyclerViewAdapter.Listener {
        ItemDecorator itemDecorator;
        final RecyclerView recyclerView;

        ScreenShotsViewHolder(View view) {
            super(view);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.screenshots);
            this.recyclerView = recyclerView;
            ItemDecorator itemDecorator = new ItemDecorator(AppDetailsRecyclerViewAdapter.this.context);
            this.itemDecorator = itemDecorator;
            recyclerView.addItemDecoration(itemDecorator);
        }

        @Override // org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter.AppDetailsViewHolder
        public void bindModel() {
            if (AppDetailsRecyclerViewAdapter.this.app == null) {
                return;
            }
            this.recyclerView.setLayoutManager(new LinearLayoutManager(AppDetailsRecyclerViewAdapter.this.context, 0, false));
            this.recyclerView.setAdapter(new ScreenShotsRecyclerViewAdapter(AppDetailsRecyclerViewAdapter.this.app, this));
            this.recyclerView.setHasFixedSize(true);
            this.recyclerView.setNestedScrollingEnabled(false);
        }

        @Override // org.fdroid.fdroid.views.ScreenShotsRecyclerViewAdapter.Listener
        public void onScreenshotClick(int i) {
            App app = AppDetailsRecyclerViewAdapter.this.app;
            Objects.requireNonNull(app);
            AppDetailsRecyclerViewAdapter.this.context.startActivity(ScreenShotsActivity.getStartIntent(AppDetailsRecyclerViewAdapter.this.context, AppDetailsRecyclerViewAdapter.this.app.repoId, app.getAllScreenshots(), i));
        }

        private static class ItemDecorator extends RecyclerView.ItemDecoration {
            private final Context context;

            ItemDecorator(Context context) {
                this.context = context.getApplicationContext();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                int dimension = (int) this.context.getResources().getDimension(R.dimen.details_activity_padding_screenshot);
                if (childAdapterPosition == 0) {
                    rect.set(dimension, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                } else {
                    rect.set(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class DonateViewHolder extends AppDetailsViewHolder {
        final TextView donateHeading;
        final GridLayout donationOptionsLayout;

        DonateViewHolder(View view) {
            super(view);
            this.donateHeading = (TextView) view.findViewById(R.id.donate_header);
            this.donationOptionsLayout = (GridLayout) view.findViewById(R.id.donation_options);
        }

        @Override // org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter.AppDetailsViewHolder
        public void bindModel() {
            if (AppDetailsRecyclerViewAdapter.this.app == null) {
                return;
            }
            if (TextUtils.isEmpty(AppDetailsRecyclerViewAdapter.this.app.authorName)) {
                this.donateHeading.setText(AppDetailsRecyclerViewAdapter.this.context.getString(R.string.app_details_donate_prompt_unknown_author, AppDetailsRecyclerViewAdapter.this.app.name));
            } else {
                this.donateHeading.setText(HtmlCompat.fromHtml(AppDetailsRecyclerViewAdapter.this.context.getString(R.string.app_details_donate_prompt, AppDetailsRecyclerViewAdapter.this.app.name, "<strong>" + AppDetailsRecyclerViewAdapter.this.app.authorName + "</strong>"), 0));
            }
            this.donationOptionsLayout.removeAllViews();
            AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter = AppDetailsRecyclerViewAdapter.this;
            if (appDetailsRecyclerViewAdapter.uriIsSetAndCanBeOpened(appDetailsRecyclerViewAdapter.app.getOpenCollectiveUri())) {
                addDonateOption(R.layout.donate_opencollective, AppDetailsRecyclerViewAdapter.this.app.getOpenCollectiveUri());
            }
            AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter2 = AppDetailsRecyclerViewAdapter.this;
            if (appDetailsRecyclerViewAdapter2.uriIsSetAndCanBeOpened(appDetailsRecyclerViewAdapter2.app.getLiberapayUri())) {
                addDonateOption(R.layout.donate_liberapay, AppDetailsRecyclerViewAdapter.this.app.getLiberapayUri());
            }
            AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter3 = AppDetailsRecyclerViewAdapter.this;
            if (appDetailsRecyclerViewAdapter3.uriIsSetAndCanBeOpened(appDetailsRecyclerViewAdapter3.app.getBitcoinUri())) {
                addDonateOption(R.layout.donate_bitcoin, AppDetailsRecyclerViewAdapter.this.app.getBitcoinUri());
            }
            AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter4 = AppDetailsRecyclerViewAdapter.this;
            if (appDetailsRecyclerViewAdapter4.uriIsSetAndCanBeOpened(appDetailsRecyclerViewAdapter4.app.getLitecoinUri())) {
                addDonateOption(R.layout.donate_litecoin, AppDetailsRecyclerViewAdapter.this.app.getLitecoinUri());
            }
            AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter5 = AppDetailsRecyclerViewAdapter.this;
            if (appDetailsRecyclerViewAdapter5.uriIsSetAndCanBeOpened(appDetailsRecyclerViewAdapter5.app.getFlattrUri())) {
                addDonateOption(R.layout.donate_generic, AppDetailsRecyclerViewAdapter.this.app.getFlattrUri());
            }
            AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter6 = AppDetailsRecyclerViewAdapter.this;
            if (appDetailsRecyclerViewAdapter6.uriIsSetAndCanBeOpened(appDetailsRecyclerViewAdapter6.app.donate)) {
                addDonateOption(R.layout.donate_generic, AppDetailsRecyclerViewAdapter.this.app.donate);
            }
        }

        private void addDonateOption(int i, final String str) {
            View viewInflate = LayoutInflater.from(AppDetailsRecyclerViewAdapter.this.context).inflate(i, (ViewGroup) this.donationOptionsLayout, false);
            if (i == R.layout.donate_generic) {
                if (!str.toLowerCase(Locale.ENGLISH).startsWith("https://")) {
                    return;
                } else {
                    ((TextView) viewInflate).setText(str.substring(8));
                }
            }
            viewInflate.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$DonateViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$addDonateOption$0(str, view);
                }
            });
            this.donationOptionsLayout.addView(viewInflate);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$addDonateOption$0(String str, View view) {
            AppDetailsRecyclerViewAdapter.this.onLinkClicked(str);
        }
    }

    private static abstract class ExpandableLinearLayoutViewHolder extends AppDetailsViewHolder {
        final LinearLayout contentView;
        final TextView headerView;

        protected abstract int getIcon();

        ExpandableLinearLayoutViewHolder(View view) {
            super(view);
            this.headerView = (TextView) view.findViewById(R.id.information);
            this.contentView = (LinearLayout) view.findViewById(R.id.ll_content);
        }

        void updateExpandableItem(boolean z) {
            Drawable drawable = ContextCompat.getDrawable(this.headerView.getContext(), getIcon());
            Drawable drawable2 = ContextCompat.getDrawable(this.headerView.getContext(), R.drawable.ic_expand_less);
            Drawable drawable3 = ContextCompat.getDrawable(this.headerView.getContext(), R.drawable.ic_expand_more);
            TextView textView = this.headerView;
            if (!z) {
                drawable2 = drawable3;
            }
            TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawable, (Drawable) null, drawable2, (Drawable) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class VersionsViewHolder extends ExpandableLinearLayoutViewHolder {
        VersionsViewHolder(View view) {
            super(view);
        }

        @Override // org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter.AppDetailsViewHolder
        public void bindModel() {
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$VersionsViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$bindModel$0(view);
                }
            });
            this.headerView.setText(R.string.versions);
            updateExpandableItem(AppDetailsRecyclerViewAdapter.this.showVersions);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindModel$0(View view) {
            AppDetailsRecyclerViewAdapter.this.setShowVersions(!r3.showVersions, true);
            updateExpandableItem(AppDetailsRecyclerViewAdapter.this.showVersions);
        }

        @Override // org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter.ExpandableLinearLayoutViewHolder
        protected int getIcon() {
            return R.drawable.ic_versions;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class NoVersionsViewHolder extends AppDetailsViewHolder {
        final TextView headerView;

        NoVersionsViewHolder(View view) {
            super(view);
            TextView textView = (TextView) view.findViewById(R.id.information);
            this.headerView = textView;
            Drawable drawable = ContextCompat.getDrawable(textView.getContext(), R.drawable.ic_versions);
            Objects.requireNonNull(drawable);
            Drawable drawableMutate = DrawableCompat.wrap(drawable).mutate();
            DrawableCompat.setTint(drawableMutate, Color.parseColor("#B4B4B4"));
            TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawableMutate, (Drawable) null, (Drawable) null, (Drawable) null);
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$NoVersionsViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$new$0(view2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(View view) {
            explainIncompatibleVersions();
        }

        @Override // org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter.AppDetailsViewHolder
        public void bindModel() {
            Context context = this.headerView.getContext();
            if (hasCompatibleApksDifferentSigners()) {
                this.headerView.setText(context.getString(R.string.app_details__no_versions__no_compatible_signers));
            } else {
                this.headerView.setText(context.getString(R.string.app_details__no_versions__none_compatible_with_device));
            }
        }

        private void explainIncompatibleVersions() {
            String string;
            String string2 = AppDetailsRecyclerViewAdapter.this.context.getString(R.string.app_details__no_versions__show_incompat_versions, AppDetailsRecyclerViewAdapter.this.context.getString(R.string.show_incompat_versions));
            if (hasCompatibleApksDifferentSigners()) {
                string = AppDetailsRecyclerViewAdapter.this.context.getString(R.string.app_details__no_versions__no_compatible_signers);
                string2 = AppDetailsRecyclerViewAdapter.this.context.getString(R.string.app_details__no_versions__explain_incompatible_signers) + "\n\n" + string2;
            } else {
                string = AppDetailsRecyclerViewAdapter.this.context.getString(R.string.app_details__no_versions__none_compatible_with_device);
            }
            new MaterialAlertDialogBuilder(AppDetailsRecyclerViewAdapter.this.context).setTitle((CharSequence) string).setMessage((CharSequence) string2).setPositiveButton(R.string.menu_settings, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$NoVersionsViewHolder$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$explainIncompatibleVersions$1(dialogInterface, i);
                }
            }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$explainIncompatibleVersions$1(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent(AppDetailsRecyclerViewAdapter.this.context, (Class<?>) MainActivity.class);
            intent.putExtra(MainActivity.EXTRA_VIEW_SETTINGS, true);
            AppDetailsRecyclerViewAdapter.this.context.startActivity(intent);
        }

        private boolean hasCompatibleApksDifferentSigners() {
            return !AppDetailsRecyclerViewAdapter.this.compatibleVersionsDifferentSigner.isEmpty();
        }
    }

    private static class VersionsLoadingViewHolder extends AppDetailsViewHolder {
        @Override // org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter.AppDetailsViewHolder
        public void bindModel() {
        }

        VersionsLoadingViewHolder(View view) {
            super(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class PermissionsViewHolder extends ExpandableLinearLayoutViewHolder {
        PermissionsViewHolder(View view) {
            super(view);
        }

        @Override // org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter.AppDetailsViewHolder
        public void bindModel() {
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$PermissionsViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$bindModel$0(view);
                }
            });
            this.headerView.setText(R.string.permissions);
            updateExpandableItem(false);
            this.contentView.removeAllViews();
            if (AppDetailsRecyclerViewAdapter.this.versions.isEmpty()) {
                return;
            }
            this.contentView.addView(new AppSecurityPermissions(AppDetailsRecyclerViewAdapter.this.context, new AppDiff(AppDetailsRecyclerViewAdapter.this.context, (Apk) AppDetailsRecyclerViewAdapter.this.versions.get(0)).apkPackageInfo).getPermissionsView(65535));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindModel$0(View view) {
            boolean z = this.contentView.getVisibility() != 0;
            this.contentView.setVisibility(z ? 0 : 8);
            updateExpandableItem(z);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) AppDetailsRecyclerViewAdapter.this.recyclerView.getLayoutManager();
            if (!z || AppDetailsRecyclerViewAdapter.this.recyclerView == null || linearLayoutManager == null) {
                return;
            }
            linearLayoutManager.scrollToPositionWithOffset(AppDetailsRecyclerViewAdapter.this.items.indexOf(4), 0);
        }

        @Override // org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter.ExpandableLinearLayoutViewHolder
        protected int getIcon() {
            return R.drawable.ic_lock;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class LinksViewHolder extends ExpandableLinearLayoutViewHolder {
        LinksViewHolder(View view) {
            super(view);
        }

        @Override // org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter.AppDetailsViewHolder
        public void bindModel() {
            String str;
            String str2;
            if (AppDetailsRecyclerViewAdapter.this.app == null) {
                return;
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$LinksViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$bindModel$0(view);
                }
            });
            this.headerView.setText(R.string.links);
            updateExpandableItem(false);
            this.contentView.removeAllViews();
            if (!TextUtils.isEmpty(AppDetailsRecyclerViewAdapter.this.app.license)) {
                if (AppDetailsRecyclerViewAdapter.this.app.license.equals("PublicDomain")) {
                    str2 = "https://en.wikipedia.org/wiki/Public_domain";
                } else {
                    str2 = "https://spdx.org/licenses/" + AppDetailsRecyclerViewAdapter.this.app.license + ".html";
                }
                String str3 = str2;
                if (AppDetailsRecyclerViewAdapter.this.uriIsSetAndCanBeOpened(str3)) {
                    AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter = AppDetailsRecyclerViewAdapter.this;
                    appDetailsRecyclerViewAdapter.addLinkItemView(this.contentView, R.string.menu_license, R.drawable.ic_license, str3, appDetailsRecyclerViewAdapter.app.license);
                }
            }
            AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter2 = AppDetailsRecyclerViewAdapter.this;
            if (appDetailsRecyclerViewAdapter2.uriIsSetAndCanBeOpened(appDetailsRecyclerViewAdapter2.app.video)) {
                AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter3 = AppDetailsRecyclerViewAdapter.this;
                appDetailsRecyclerViewAdapter3.addLinkItemView(this.contentView, R.string.menu_video, R.drawable.ic_video, appDetailsRecyclerViewAdapter3.app.video);
            }
            AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter4 = AppDetailsRecyclerViewAdapter.this;
            if (appDetailsRecyclerViewAdapter4.uriIsSetAndCanBeOpened(appDetailsRecyclerViewAdapter4.app.sourceCode)) {
                AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter5 = AppDetailsRecyclerViewAdapter.this;
                appDetailsRecyclerViewAdapter5.addLinkItemView(this.contentView, R.string.menu_source, R.drawable.ic_source_code, appDetailsRecyclerViewAdapter5.app.sourceCode);
            }
            AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter6 = AppDetailsRecyclerViewAdapter.this;
            if (appDetailsRecyclerViewAdapter6.uriIsSetAndCanBeOpened(appDetailsRecyclerViewAdapter6.app.issueTracker)) {
                AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter7 = AppDetailsRecyclerViewAdapter.this;
                appDetailsRecyclerViewAdapter7.addLinkItemView(this.contentView, R.string.menu_issues, R.drawable.ic_error, appDetailsRecyclerViewAdapter7.app.issueTracker);
            }
            AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter8 = AppDetailsRecyclerViewAdapter.this;
            if (appDetailsRecyclerViewAdapter8.uriIsSetAndCanBeOpened(appDetailsRecyclerViewAdapter8.app.translation)) {
                AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter9 = AppDetailsRecyclerViewAdapter.this;
                appDetailsRecyclerViewAdapter9.addLinkItemView(this.contentView, R.string.menu_translation, R.drawable.ic_translation, appDetailsRecyclerViewAdapter9.app.translation);
            }
            AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter10 = AppDetailsRecyclerViewAdapter.this;
            if (appDetailsRecyclerViewAdapter10.uriIsSetAndCanBeOpened(appDetailsRecyclerViewAdapter10.app.changelog)) {
                AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter11 = AppDetailsRecyclerViewAdapter.this;
                appDetailsRecyclerViewAdapter11.addLinkItemView(this.contentView, R.string.menu_changelog, R.drawable.ic_changelog, appDetailsRecyclerViewAdapter11.app.changelog);
            }
            AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter12 = AppDetailsRecyclerViewAdapter.this;
            if (appDetailsRecyclerViewAdapter12.uriIsSetAndCanBeOpened(appDetailsRecyclerViewAdapter12.app.webSite)) {
                AppDetailsRecyclerViewAdapter appDetailsRecyclerViewAdapter13 = AppDetailsRecyclerViewAdapter.this;
                appDetailsRecyclerViewAdapter13.addLinkItemView(this.contentView, R.string.menu_website, R.drawable.ic_website, appDetailsRecyclerViewAdapter13.app.webSite);
            }
            String strEncode = Uri.encode(AppDetailsRecyclerViewAdapter.this.context.getString(R.string.app_details_subject, AppDetailsRecyclerViewAdapter.this.app.name));
            if (AppDetailsRecyclerViewAdapter.this.app.authorEmail == null) {
                str = null;
            } else {
                str = "mailto:" + AppDetailsRecyclerViewAdapter.this.app.authorEmail + "?subject=" + strEncode;
            }
            if (AppDetailsRecyclerViewAdapter.this.uriIsSetAndCanBeOpened(str)) {
                AppDetailsRecyclerViewAdapter.this.addLinkItemView(this.contentView, R.string.menu_email, R.drawable.ic_email, str);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindModel$0(View view) {
            boolean z = this.contentView.getVisibility() != 0;
            this.contentView.setVisibility(z ? 0 : 8);
            updateExpandableItem(z);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) AppDetailsRecyclerViewAdapter.this.recyclerView.getLayoutManager();
            if (!z || AppDetailsRecyclerViewAdapter.this.recyclerView == null || linearLayoutManager == null) {
                return;
            }
            linearLayoutManager.scrollToPositionWithOffset(AppDetailsRecyclerViewAdapter.this.items.indexOf(3), 0);
        }

        @Override // org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter.ExpandableLinearLayoutViewHolder
        protected int getIcon() {
            return R.drawable.ic_website;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class VersionViewHolder extends RecyclerView.ViewHolder {
        final TextView added;
        final TextView api;
        private Apk apk;
        final View busyIndicator;
        Button buttonAction;
        final Button buttonInstallUpgrade;
        final ImageView expandArrow;
        final View expandedLayout;
        final TextView incompatibleReasons;
        final TextView size;
        final TextView statusIncompatible;
        final TextView statusInstalled;
        final TextView statusSuggested;
        final TextView targetArch;
        final TextView version;
        final TextView versionCode;

        VersionViewHolder(View view) {
            super(view);
            this.version = (TextView) view.findViewById(R.id.version);
            this.statusInstalled = (TextView) view.findViewById(R.id.status_installed);
            this.statusSuggested = (TextView) view.findViewById(R.id.status_suggested);
            this.statusIncompatible = (TextView) view.findViewById(R.id.status_incompatible);
            this.versionCode = (TextView) view.findViewById(R.id.versionCode);
            this.added = (TextView) view.findViewById(R.id.added);
            this.expandArrow = (ImageView) view.findViewById(R.id.expand_arrow);
            this.expandedLayout = view.findViewById(R.id.expanded_layout);
            this.size = (TextView) view.findViewById(R.id.size);
            this.api = (TextView) view.findViewById(R.id.api);
            this.buttonInstallUpgrade = (Button) view.findViewById(R.id.button_install_upgrade);
            this.busyIndicator = view.findViewById(R.id.busy_indicator);
            this.incompatibleReasons = (TextView) view.findViewById(R.id.incompatible_reasons);
            this.targetArch = (TextView) view.findViewById(R.id.target_arch);
            int dimensionPixelSize = AppDetailsRecyclerViewAdapter.this.context.getResources().getDimensionPixelSize(R.dimen.layout_horizontal_margin);
            ViewCompat.setPaddingRelative(view, AppDetailsRecyclerViewAdapter.this.context.getResources().getDimensionPixelSize(R.dimen.details_activity_padding) + dimensionPixelSize + ViewCompat.getPaddingStart(view), view.getPaddingTop(), dimensionPixelSize + ViewCompat.getPaddingEnd(view), view.getPaddingBottom());
        }

        void bindModel(final Apk apk) {
            if (AppDetailsRecyclerViewAdapter.this.app == null) {
                return;
            }
            this.apk = apk;
            boolean zIsInstalled = AppDetailsRecyclerViewAdapter.this.app.isInstalled(AppDetailsRecyclerViewAdapter.this.context);
            boolean z = apk.versionCode == AppDetailsRecyclerViewAdapter.this.app.installedVersionCode && TextUtils.equals(apk.signer, AppDetailsRecyclerViewAdapter.this.app.installedSigner);
            boolean zEquals = apk.equals(AppDetailsRecyclerViewAdapter.this.suggestedApk);
            boolean z2 = AppDetailsRecyclerViewAdapter.this.callbacks.isAppDownloading() && AppDetailsRecyclerViewAdapter.this.downloadedApk != null && AppDetailsRecyclerViewAdapter.this.downloadedApk.compareTo(apk) == 0 && TextUtils.equals(apk.getApkPath(), AppDetailsRecyclerViewAdapter.this.downloadedApk.getApkPath());
            boolean z3 = apk.versionCode == AppDetailsRecyclerViewAdapter.this.app.installedVersionCode && apk.compatible && apk.size == 0 && apk.maxSdkVersion == -1;
            this.version.setText(apk.versionName);
            this.statusSuggested.setVisibility((zEquals && apk.compatible) ? 0 : 8);
            this.statusInstalled.setVisibility(z ? 0 : 8);
            this.statusIncompatible.setVisibility(!apk.compatible ? 0 : 8);
            if (this.statusSuggested.getVisibility() == 0 || this.statusInstalled.getVisibility() == 0 || this.statusIncompatible.getVisibility() == 0) {
                this.version.setMaxWidth((int) (((double) Resources.getSystem().getDisplayMetrics().widthPixels) * 0.4d));
            } else {
                this.version.setMaxWidth(Integer.MAX_VALUE);
            }
            if (apk.added != null) {
                DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(AppDetailsRecyclerViewAdapter.this.context);
                this.added.setVisibility(0);
                this.added.setText(AppDetailsRecyclerViewAdapter.this.context.getString(R.string.added_on, dateFormat.format(apk.added)));
            } else {
                this.added.setVisibility(4);
            }
            this.size.setText(AppDetailsRecyclerViewAdapter.this.context.getString(R.string.app_size, Utils.getFriendlySize(apk.size)));
            this.api.setText(getApiText(apk));
            this.buttonInstallUpgrade.setVisibility(8);
            this.buttonInstallUpgrade.setText(AppDetailsRecyclerViewAdapter.this.context.getString(R.string.menu_install));
            showActionButton(this.buttonInstallUpgrade, z, z2);
            if (zIsInstalled && !z) {
                if (apk.versionCode > AppDetailsRecyclerViewAdapter.this.app.installedVersionCode) {
                    this.buttonInstallUpgrade.setText(R.string.menu_upgrade);
                } else if (apk.versionCode < AppDetailsRecyclerViewAdapter.this.app.installedVersionCode) {
                    this.buttonInstallUpgrade.setVisibility(8);
                }
            }
            this.busyIndicator.setVisibility(z2 ? 0 : 8);
            if (Preferences.get().expertMode()) {
                this.versionCode.setText(String.format(Locale.ENGLISH, " (%d) ", Long.valueOf(apk.versionCode)));
                if (!apk.compatible) {
                    String incompatibleReasonsText = getIncompatibleReasonsText(apk);
                    if (incompatibleReasonsText != null) {
                        this.incompatibleReasons.setVisibility(0);
                        this.incompatibleReasons.setText(incompatibleReasonsText);
                    } else {
                        this.incompatibleReasons.setVisibility(8);
                    }
                    this.targetArch.setVisibility(8);
                } else {
                    String targetArchText = getTargetArchText(apk);
                    if (targetArchText != null) {
                        this.targetArch.setVisibility(0);
                        this.targetArch.setText(targetArchText);
                    } else {
                        this.targetArch.setVisibility(8);
                    }
                    this.incompatibleReasons.setVisibility(8);
                }
            } else {
                this.versionCode.setText("");
                this.incompatibleReasons.setVisibility(8);
                this.targetArch.setVisibility(8);
            }
            expand(Boolean.TRUE.equals((Boolean) AppDetailsRecyclerViewAdapter.this.versionsExpandTracker.get(apk.getApkPath())) || z2);
            if (!z3) {
                this.expandArrow.setAlpha(1.0f);
                this.itemView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$VersionViewHolder$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$bindModel$0(view);
                    }
                });
            } else {
                this.expandArrow.setAlpha(0.3f);
                this.itemView.setOnClickListener(null);
            }
            if (apk.versionName != null) {
                this.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$VersionViewHolder$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        return this.f$0.lambda$bindModel$1(apk, view);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bindModel$0(View view) {
            toggleExpanded();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$bindModel$1(Apk apk, View view) {
            Utils.copyToClipboard(AppDetailsRecyclerViewAdapter.this.context, AppDetailsRecyclerViewAdapter.this.app.name, apk.versionName);
            return true;
        }

        private String getApiText(Apk apk) {
            int i = apk.minSdkVersion;
            if (i > 0 && apk.maxSdkVersion < 127) {
                return "Android: " + AppDetailsRecyclerViewAdapter.this.context.getString(R.string.minsdk_up_to_maxsdk, Utils.getAndroidVersionName(apk.minSdkVersion), Utils.getAndroidVersionName(apk.maxSdkVersion));
            }
            if (i > 0) {
                return "Android: " + AppDetailsRecyclerViewAdapter.this.context.getString(R.string.minsdk_or_later, Utils.getAndroidVersionName(apk.minSdkVersion));
            }
            if (apk.maxSdkVersion <= 0) {
                return "Android: ";
            }
            return "Android: " + AppDetailsRecyclerViewAdapter.this.context.getString(R.string.up_to_maxsdk, Utils.getAndroidVersionName(apk.maxSdkVersion));
        }

        private String getIncompatibleReasonsText(Apk apk) {
            if (apk.incompatibleReasons != null) {
                return AppDetailsRecyclerViewAdapter.this.context.getResources().getString(R.string.requires_features, TextUtils.join(", ", apk.incompatibleReasons));
            }
            Objects.requireNonNull(AppDetailsRecyclerViewAdapter.this.app);
            if (AppDetailsRecyclerViewAdapter.this.app.installedSigner == null || TextUtils.equals(AppDetailsRecyclerViewAdapter.this.app.installedSigner, apk.signer)) {
                return null;
            }
            return AppDetailsRecyclerViewAdapter.this.context.getString(R.string.app_details__incompatible_mismatched_signers);
        }

        private String getTargetArchText(Apk apk) {
            if (apk.nativecode == null) {
                return null;
            }
            String property = System.getProperty("os.arch");
            ArrayList arrayList = new ArrayList();
            for (String str : apk.nativecode) {
                if (!TextUtils.equals(str, property)) {
                    arrayList.add(str);
                }
            }
            String strJoin = TextUtils.join(", ", arrayList);
            if (strJoin.isEmpty()) {
                return null;
            }
            return AppDetailsRecyclerViewAdapter.this.context.getResources().getString(R.string.requires_features, strJoin);
        }

        private void showActionButton(Button button, boolean z, boolean z2) {
            this.buttonAction = button;
            if (z2) {
                button.setVisibility(8);
                return;
            }
            button.setVisibility(0);
            boolean z3 = !this.apk.compatible || z || AppDetailsRecyclerViewAdapter.this.callbacks.isAppDownloading();
            this.buttonAction.setEnabled(!z3);
            this.buttonAction.setAlpha(z3 ? 0.15f : 1.0f);
            this.buttonAction.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$VersionViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showActionButton$2(view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$showActionButton$2(View view) {
            AppDetailsRecyclerViewAdapter.this.callbacks.installApk(this.apk);
        }

        private void expand(boolean z) {
            AppDetailsRecyclerViewAdapter.this.versionsExpandTracker.put(this.apk.getApkPath(), Boolean.valueOf(z));
            this.expandedLayout.setVisibility(z ? 0 : 8);
            this.versionCode.setVisibility(z ? 0 : 8);
            this.expandArrow.setImageDrawable(ContextCompat.getDrawable(AppDetailsRecyclerViewAdapter.this.context, z ? R.drawable.ic_expand_less : R.drawable.ic_expand_more));
            this.version.setSelected(z);
            this.size.setSelected(z);
            this.api.setSelected(z);
        }

        private void toggleExpanded() {
            if (this.busyIndicator.getVisibility() == 0) {
                return;
            }
            boolean zEquals = Boolean.FALSE.equals(AppDetailsRecyclerViewAdapter.this.versionsExpandTracker.get(this.apk.getApkPath()));
            expand(zEquals);
            if (zEquals) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) AppDetailsRecyclerViewAdapter.this.recyclerView.getLayoutManager();
                final int bindingAdapterPosition = getBindingAdapterPosition();
                if (linearLayoutManager == null || bindingAdapterPosition < linearLayoutManager.findLastCompletelyVisibleItemPosition()) {
                    return;
                }
                AppDetailsRecyclerViewAdapter.this.recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter.VersionViewHolder.1
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        AppDetailsRecyclerViewAdapter.this.recyclerView.smoothScrollToPosition(bindingAdapterPosition);
                        AppDetailsRecyclerViewAdapter.this.recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLinkItemView(ViewGroup viewGroup, int i, int i2, String str) {
        addLinkItemView(viewGroup, i, i2, str, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLinkItemView(ViewGroup viewGroup, int i, int i2, final String str, String str2) {
        final String string;
        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.app_details2_link_item, viewGroup, false);
        if (str2 == null) {
            string = viewGroup.getContext().getString(i);
        } else {
            string = viewGroup.getContext().getString(i, str2);
        }
        textView.setText(string);
        TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, i2, 0, 0, 0);
        viewGroup.addView(textView);
        textView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$addLinkItemView$0(str, view);
            }
        });
        textView.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.fdroid.fdroid.views.AppDetailsRecyclerViewAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return this.f$0.lambda$addLinkItemView$1(string, str, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addLinkItemView$0(String str, View view) {
        onLinkClicked(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addLinkItemView$1(String str, String str2, View view) {
        Utils.copyToClipboard(this.context, str, str2, R.string.copied_url_to_clipboard);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLinkClicked(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.callbacks.openUrl(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean uriIsSetAndCanBeOpened(String str) {
        return (TextUtils.isEmpty(str) || new Intent("android.intent.action.VIEW", Uri.parse(str)).resolveActivity(this.context.getPackageManager()) == null) ? false : true;
    }

    public static CharSequence trimTrailingNewlines(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return charSequence;
        }
        int length = charSequence.length() - 1;
        while (length >= 0 && charSequence.charAt(length) == '\n') {
            length--;
        }
        return length == charSequence.length() + (-1) ? charSequence : charSequence.subSequence(0, length + 1);
    }

    @SuppressLint({"ParcelCreator"})
    private static final class SafeURLSpan extends URLSpan {
        SafeURLSpan(String str) {
            super(str);
        }

        @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
        public void onClick(View view) {
            try {
                super.onClick(view);
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(view.getContext(), view.getContext().getString(R.string.no_handler_app, getURL()), 1).show();
            }
        }
    }
}
