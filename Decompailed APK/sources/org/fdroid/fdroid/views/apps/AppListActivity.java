package org.fdroid.fdroid.views.apps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.fdroid.database.AppListItem;
import org.fdroid.database.AppListSortOrder;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.UiUtils;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.compat.LocaleCompat;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.views.apps.CategoryTextWatcher;
import org.fdroid.fdroid.views.main.MainActivity;

/* JADX INFO: loaded from: classes2.dex */
public class AppListActivity extends AppCompatActivity implements CategoryTextWatcher.SearchTermsChangedListener {
    public static final String EXTRA_CATEGORY = "org.fdroid.fdroid.views.apps.AppListActivity.EXTRA_CATEGORY";
    public static final String EXTRA_CATEGORY_NAME = "org.fdroid.fdroid.views.apps.AppListActivity.EXTRA_CATEGORY_NAME";
    public static final String EXTRA_REPO_ID = "org.fdroid.fdroid.views.apps.AppListActivity.REPO_ID";
    public static final String EXTRA_SEARCH_TERMS = "org.fdroid.fdroid.views.apps.AppListActivity.EXTRA_SEARCH_TERMS";
    private static final String SEARCH_TERMS_KEY = "searchTerms";
    private static final String SORT_CLAUSE_KEY = "sortClauseSelected";
    public static final String TAG = "AppListActivity";
    private static SharedPreferences savedSearchSettings;
    private AppListAdapter appAdapter;
    private RecyclerView appView;
    private String categoryId;
    private FDroidDatabase db;
    private TextView emptyState;
    private View hiddenAppNotice;
    private LiveData itemsLiveData;
    private Utils.KeyboardStateMonitor keyboardStateMonitor;
    private long repoId;
    private EditText searchInput;
    private String searchTerms;
    private String sortClauseSelected;
    private ImageView sortImage;

    private interface SortClause {
        public static final String LAST_UPDATED = "lastUpdated";
        public static final String WORDS = "name";
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        FDroidApp fDroidApp = (FDroidApp) getApplication();
        fDroidApp.setSecureWindow(this);
        fDroidApp.applyPureBlackBackgroundInDarkTheme(this);
        EdgeToEdge.enable(this);
        super.onCreate(bundle);
        setContentView(R.layout.activity_app_list);
        this.db = DBHelper.getDb(getApplicationContext());
        this.keyboardStateMonitor = new Utils.KeyboardStateMonitor(findViewById(R.id.app_list_root));
        SharedPreferences savedSearchSettings2 = getSavedSearchSettings(this);
        savedSearchSettings = savedSearchSettings2;
        this.searchTerms = savedSearchSettings2.getString(SEARCH_TERMS_KEY, null);
        this.sortClauseSelected = savedSearchSettings.getString(SORT_CLAUSE_KEY, SortClause.LAST_UPDATED);
        EditText editText = (EditText) findViewById(R.id.search);
        this.searchInput = editText;
        editText.setText(this.searchTerms);
        EditText editText2 = this.searchInput;
        editText2.addTextChangedListener(new CategoryTextWatcher(this, editText2, this));
        this.searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: org.fdroid.fdroid.views.apps.AppListActivity$$ExternalSyntheticLambda1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return this.f$0.lambda$onCreate$0(textView, i, keyEvent);
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.sort);
        this.sortImage = imageView;
        imageView.setImageResource("name".equals(this.sortClauseSelected) ? R.drawable.ic_sort : R.drawable.ic_last_updated);
        this.sortImage.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.apps.AppListActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$1(view);
            }
        });
        View viewFindViewById = findViewById(R.id.hiddenAppNotice);
        this.hiddenAppNotice = viewFindViewById;
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.apps.AppListActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$2(view);
            }
        });
        this.emptyState = (TextView) findViewById(R.id.empty_state);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.apps.AppListActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$3(view);
            }
        });
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.apps.AppListActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$4(view);
            }
        });
        this.appAdapter = new AppListAdapter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.app_list);
        this.appView = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.appView.setLayoutManager(new LinearLayoutManager(this));
        this.appView.setAdapter(this.appAdapter);
        UiUtils.setupEdgeToEdge(this.appView, false, true);
        parseIntentForSearchQuery();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onCreate$0(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 3) {
            return false;
        }
        ((InputMethodManager) ContextCompat.getSystemService(this, InputMethodManager.class)).hideSoftInputFromWindow(this.searchInput.getWindowToken(), 0);
        this.appView.requestFocus();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        String str = this.sortClauseSelected;
        str.hashCode();
        if (str.equals("name")) {
            this.sortClauseSelected = SortClause.LAST_UPDATED;
            this.sortImage.setImageResource(R.drawable.ic_last_updated);
        } else if (str.equals(SortClause.LAST_UPDATED)) {
            this.sortClauseSelected = "name";
            this.sortImage.setImageResource(R.drawable.ic_sort);
        } else {
            Log.e(TAG, "Unknown sort clause: " + this.sortClauseSelected);
            this.sortClauseSelected = SortClause.LAST_UPDATED;
        }
        putSavedSearchSettings(getApplicationContext(), SORT_CLAUSE_KEY, this.sortClauseSelected);
        loadItems();
        this.appView.scrollToPosition(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(View view) {
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_VIEW_SETTINGS, true);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$3(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$4(View view) {
        this.searchInput.setText("");
        this.searchInput.requestFocus();
        if (this.keyboardStateMonitor.isKeyboardVisible()) {
            return;
        }
        ((InputMethodManager) ContextCompat.getSystemService(this, InputMethodManager.class)).toggleSoftInputFromWindow(view.getApplicationWindowToken(), 2, 0);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Glide.with((FragmentActivity) this).applyDefaultRequestOptions((RequestOptions) new RequestOptions().onlyRetrieveFromCache(!Preferences.get().isBackgroundDownloadAllowed()));
    }

    private void parseIntentForSearchQuery() {
        Intent intent = getIntent();
        this.categoryId = intent.hasExtra(EXTRA_CATEGORY) ? intent.getStringExtra(EXTRA_CATEGORY) : null;
        this.searchTerms = intent.hasExtra(EXTRA_SEARCH_TERMS) ? intent.getStringExtra(EXTRA_SEARCH_TERMS) : null;
        long longExtra = intent.hasExtra(EXTRA_REPO_ID) ? intent.getLongExtra(EXTRA_REPO_ID, -1L) : -1L;
        this.repoId = longExtra;
        if (longExtra > 0) {
            Repository repository = FDroidApp.getRepoManager(this).getRepository(this.repoId);
            if (repository != null) {
                this.searchInput.setText(getSearchText(repository.getName(LocaleListCompat.getDefault()), this.searchTerms));
            }
        } else {
            this.searchInput.setText(getSearchText(intent.hasExtra(EXTRA_CATEGORY_NAME) ? intent.getStringExtra(EXTRA_CATEGORY_NAME) : null, this.searchTerms));
        }
        EditText editText = this.searchInput;
        editText.setSelection(editText.getText().length());
        if (this.categoryId != null) {
            this.appView.requestFocus();
        }
    }

    private void loadItems() {
        String str = this.searchTerms;
        LiveData liveData = this.itemsLiveData;
        if (liveData != null) {
            liveData.removeObservers(this);
        }
        AppListSortOrder appListSortOrder = "name".equals(this.sortClauseSelected) ? AppListSortOrder.NAME : AppListSortOrder.LAST_UPDATED;
        if (this.repoId > 0) {
            this.itemsLiveData = this.db.getAppDao().getAppListItems(getPackageManager(), this.repoId, str, appListSortOrder);
        } else if (this.categoryId == null) {
            this.itemsLiveData = this.db.getAppDao().getAppListItems(getPackageManager(), str, appListSortOrder);
        } else {
            this.itemsLiveData = this.db.getAppDao().getAppListItems(getPackageManager(), this.categoryId, str, appListSortOrder);
        }
        this.itemsLiveData.observe(this, new Observer() { // from class: org.fdroid.fdroid.views.apps.AppListActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.onAppsLoaded((List) obj);
            }
        });
    }

    private CharSequence getSearchText(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
            sb.append(":");
        }
        if (str2 != null) {
            sb.append(str2);
        }
        return sb.toString();
    }

    private void setShowHiddenAppsNotice(boolean z) {
        this.hiddenAppNotice.setVisibility(z ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAppsLoaded(List<AppListItem> list) {
        setShowHiddenAppsNotice(false);
        this.appAdapter.setHasHiddenAppsCallback(new Runnable() { // from class: org.fdroid.fdroid.views.apps.AppListActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAppsLoaded$5();
            }
        });
        if (this.searchTerms != null) {
            Collections.sort(list, new Comparator() { // from class: org.fdroid.fdroid.views.apps.AppListActivity$$ExternalSyntheticLambda7
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return this.f$0.lambda$onAppsLoaded$6((AppListItem) obj, (AppListItem) obj2);
                }
            });
        }
        this.appAdapter.setHideInstallButton(this.repoId > 0);
        this.appAdapter.setItems(list);
        if (list.isEmpty()) {
            this.emptyState.setVisibility(0);
            this.appView.setVisibility(8);
        } else {
            this.emptyState.setVisibility(8);
            this.appView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAppsLoaded$5() {
        setShowHiddenAppsNotice(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ int lambda$onAppsLoaded$6(AppListItem appListItem, AppListItem appListItem2) {
        if (this.sortClauseSelected.equals(SortClause.LAST_UPDATED)) {
            return Long.compare(appListItem2.getLastUpdated(), appListItem.getLastUpdated());
        }
        if (this.sortClauseSelected.equals("name")) {
            return (appListItem.getName() == null ? "" : appListItem.getName()).toLowerCase(LocaleCompat.getDefault()).compareTo((appListItem2.getName() != null ? appListItem2.getName() : "").toLowerCase(LocaleCompat.getDefault()));
        }
        return 0;
    }

    @Override // org.fdroid.fdroid.views.apps.CategoryTextWatcher.SearchTermsChangedListener
    public void onSearchTermsChanged(String str, String str2) {
        if (str == null) {
            this.categoryId = null;
            this.repoId = -1L;
        }
        this.searchTerms = str2.isEmpty() ? null : str2;
        this.appView.scrollToPosition(0);
        loadItems();
        if (TextUtils.isEmpty(str2)) {
            removeSavedSearchSettings(this, SEARCH_TERMS_KEY);
        } else {
            putSavedSearchSettings(this, SEARCH_TERMS_KEY, str2);
        }
    }

    private static void putSavedSearchSettings(Context context, String str, String str2) {
        if (savedSearchSettings == null) {
            savedSearchSettings = getSavedSearchSettings(context);
        }
        savedSearchSettings.edit().putString(str, str2).apply();
    }

    private static void removeSavedSearchSettings(Context context, String str) {
        if (savedSearchSettings == null) {
            savedSearchSettings = getSavedSearchSettings(context);
        }
        savedSearchSettings.edit().remove(str).apply();
    }

    private static SharedPreferences getSavedSearchSettings(Context context) {
        return context.getSharedPreferences("saved-search-settings", 0);
    }
}
