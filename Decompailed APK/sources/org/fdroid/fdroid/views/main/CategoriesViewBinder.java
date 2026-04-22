package org.fdroid.fdroid.views.main;

import android.content.Intent;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.LocaleListCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import org.fdroid.database.Category;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.panic.HidingManager;
import org.fdroid.fdroid.views.apps.AppListActivity;
import org.fdroid.fdroid.views.categories.CategoryAdapter;
import org.fdroid.fdroid.views.categories.CategoryItem;
import org.fdroid.fdroid.work.RepoUpdateWorker;

/* JADX INFO: loaded from: classes2.dex */
class CategoriesViewBinder implements Observer {
    public static final String TAG = "CategoriesViewBinder";
    private final RecyclerView categoriesList;
    private final CategoryAdapter categoryAdapter;
    private final FDroidDatabase db;
    private final String[] defaultCategories;
    private Disposable disposable;
    private final TextView emptyState;

    CategoriesViewBinder(final AppCompatActivity appCompatActivity, FrameLayout frameLayout) {
        FDroidDatabase db = DBHelper.getDb(appCompatActivity);
        this.db = db;
        Transformations.distinctUntilChanged(db.getRepositoryDao().getLiveCategories()).observe(appCompatActivity, this);
        this.defaultCategories = appCompatActivity.getResources().getStringArray(R.array.defaultCategories);
        View viewInflate = appCompatActivity.getLayoutInflater().inflate(R.layout.main_tab_categories, (ViewGroup) frameLayout, true);
        CategoryAdapter categoryAdapter = new CategoryAdapter(appCompatActivity, db);
        this.categoryAdapter = categoryAdapter;
        this.emptyState = (TextView) viewInflate.findViewById(R.id.empty_state);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.category_list);
        this.categoriesList = recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(appCompatActivity));
        recyclerView.setAdapter(categoryAdapter);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) viewInflate.findViewById(R.id.swipe_to_refresh);
        Utils.applySwipeLayoutColors(swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: org.fdroid.fdroid.views.main.CategoriesViewBinder$$ExternalSyntheticLambda3
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                CategoriesViewBinder.lambda$new$0(swipeRefreshLayout, appCompatActivity);
            }
        });
        FloatingActionButton floatingActionButton = (FloatingActionButton) viewInflate.findViewById(R.id.fab_search);
        floatingActionButton.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.main.CategoriesViewBinder$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CategoriesViewBinder.lambda$new$1(appCompatActivity, view);
            }
        });
        floatingActionButton.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.fdroid.fdroid.views.main.CategoriesViewBinder$$ExternalSyntheticLambda5
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return CategoriesViewBinder.lambda$new$2(appCompatActivity, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(SwipeRefreshLayout swipeRefreshLayout, AppCompatActivity appCompatActivity) {
        swipeRefreshLayout.setRefreshing(false);
        RepoUpdateWorker.updateNow(appCompatActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$1(AppCompatActivity appCompatActivity, View view) {
        appCompatActivity.startActivity(new Intent(appCompatActivity, (Class<?>) AppListActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$2(AppCompatActivity appCompatActivity, View view) {
        if (!Preferences.get().hideOnLongPressSearch()) {
            return false;
        }
        HidingManager.showHideDialog(appCompatActivity);
        return true;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(final List<Category> list) {
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.disposable = Single.fromCallable(new Callable() { // from class: org.fdroid.fdroid.views.main.CategoriesViewBinder$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$onChanged$3(list);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: org.fdroid.fdroid.views.main.CategoriesViewBinder$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                this.f$0.onItemsLoaded((List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onItemsLoaded(List<CategoryItem> list) {
        if (list.size() == 0) {
            this.emptyState.setVisibility(0);
            this.categoriesList.setVisibility(8);
        } else {
            this.categoryAdapter.setCategories(list);
            this.emptyState.setVisibility(8);
            this.categoriesList.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadCategoryItems, reason: merged with bridge method [inline-methods] */
    public List<CategoryItem> lambda$onChanged$3(List<Category> list) {
        CategoriesViewBinder categoriesViewBinder = this;
        ArrayList arrayList = new ArrayList();
        final ArraySet arraySet = new ArraySet(list.size());
        for (Category category : list) {
            int numberOfAppsInCategory = categoriesViewBinder.db.getAppDao().getNumberOfAppsInCategory(category.getId());
            if (numberOfAppsInCategory > 0) {
                arraySet.add(category.getId());
                arrayList.add(new CategoryItem(category, numberOfAppsInCategory));
            } else {
                Log.d(TAG, "Not adding " + category.getId() + " because it has no apps.");
            }
        }
        String[] strArr = categoriesViewBinder.defaultCategories;
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            if (!arraySet.contains(str)) {
                int numberOfAppsInCategory2 = categoriesViewBinder.db.getAppDao().getNumberOfAppsInCategory(str);
                if (numberOfAppsInCategory2 > 0) {
                    arrayList.add(new CategoryItem(new Category(2L, str, Collections.emptyMap(), Collections.emptyMap(), Collections.emptyMap()), numberOfAppsInCategory2));
                } else {
                    Log.d(TAG, "Not adding default " + str + " because it has no apps.");
                }
            }
            i++;
            categoriesViewBinder = this;
        }
        final LocaleListCompat localeListCompat = LocaleListCompat.getDefault();
        Collections.sort(arrayList, new Comparator() { // from class: org.fdroid.fdroid.views.main.CategoriesViewBinder$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return CategoriesViewBinder.lambda$loadCategoryItems$4(arraySet, localeListCompat, (CategoryItem) obj, (CategoryItem) obj2);
            }
        });
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$loadCategoryItems$4(ArraySet arraySet, LocaleListCompat localeListCompat, CategoryItem categoryItem, CategoryItem categoryItem2) {
        arraySet.add(categoryItem2.category.getId());
        String name = categoryItem.category.getName(localeListCompat);
        if (name == null) {
            name = categoryItem.category.getId();
        }
        String name2 = categoryItem2.category.getName(localeListCompat);
        if (name2 == null) {
            name2 = categoryItem2.category.getId();
        }
        return name.compareToIgnoreCase(name2);
    }
}
