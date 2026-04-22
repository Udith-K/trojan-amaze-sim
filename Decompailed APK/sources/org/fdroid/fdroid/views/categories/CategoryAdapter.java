package org.fdroid.fdroid.views.categories;

import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import java.util.HashMap;
import java.util.List;
import org.fdroid.database.Category;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
public class CategoryAdapter extends ListAdapter {
    private final AppCompatActivity activity;
    private final FDroidDatabase db;
    private final HashMap<Category, LiveData> liveData;

    public CategoryAdapter(AppCompatActivity appCompatActivity, FDroidDatabase fDroidDatabase) {
        super(new DiffUtil.ItemCallback() { // from class: org.fdroid.fdroid.views.categories.CategoryAdapter.1
            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areContentsTheSame(CategoryItem categoryItem, CategoryItem categoryItem2) {
                return false;
            }

            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areItemsTheSame(CategoryItem categoryItem, CategoryItem categoryItem2) {
                return categoryItem.category.equals(categoryItem2.category);
            }
        });
        this.liveData = new HashMap<>();
        this.activity = appCompatActivity;
        this.db = fDroidDatabase;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CategoryController onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppCompatActivity appCompatActivity = this.activity;
        return new CategoryController(appCompatActivity, appCompatActivity.getLayoutInflater().inflate(R.layout.category_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(CategoryController categoryController, int i) {
        CategoryItem categoryItem = (CategoryItem) getItem(i);
        categoryController.bindModel(categoryItem, this.liveData.get(categoryItem.category));
    }

    public void setCategories(List<CategoryItem> list) {
        submitList(list);
        for (CategoryItem categoryItem : list) {
            this.liveData.put(categoryItem.category, this.db.getAppDao().getAppOverviewItems(categoryItem.category.getId(), 20));
        }
    }
}
