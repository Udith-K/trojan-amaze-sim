package org.fdroid.fdroid.views.categories;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.fdroid.database.AppOverviewItem;
import org.fdroid.database.Category;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.views.apps.AppListActivity;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: loaded from: classes2.dex */
public class CategoryController extends RecyclerView.ViewHolder {
    static final int NUM_OF_APPS_PER_CATEGORY_ON_OVERVIEW = 20;
    private static final String TAG = "CategoryController";
    private final AppCompatActivity activity;
    private final AppPreviewAdapter appCardsAdapter;
    private final FrameLayout background;
    private Category currentCategory;
    private final TextView heading;
    private final ImageView image;
    private final View.OnClickListener onViewAll;
    private final Button viewAll;

    CategoryController(AppCompatActivity appCompatActivity, View view) {
        super(view);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: org.fdroid.fdroid.views.categories.CategoryController.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (CategoryController.this.currentCategory == null) {
                    return;
                }
                Intent intent = new Intent(CategoryController.this.activity, (Class<?>) AppListActivity.class);
                intent.putExtra(AppListActivity.EXTRA_CATEGORY, CategoryController.this.currentCategory.getId());
                intent.putExtra(AppListActivity.EXTRA_CATEGORY_NAME, CategoryController.this.currentCategory.getName(LocaleListCompat.getDefault()));
                CategoryController.this.activity.startActivity(intent);
            }
        };
        this.onViewAll = onClickListener;
        this.activity = appCompatActivity;
        AppPreviewAdapter appPreviewAdapter = new AppPreviewAdapter(appCompatActivity);
        this.appCardsAdapter = appPreviewAdapter;
        Button button = (Button) view.findViewById(R.id.view_all_button);
        this.viewAll = button;
        button.setOnClickListener(onClickListener);
        this.heading = (TextView) view.findViewById(R.id.name);
        this.image = (ImageView) view.findViewById(R.id.category_image);
        this.background = (FrameLayout) view.findViewById(R.id.category_background);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.app_cards);
        recyclerView.setAdapter(appPreviewAdapter);
        recyclerView.addItemDecoration(new ItemDecorator(appCompatActivity));
    }

    private static String translateCategory(Context context, String str) {
        int categoryResource = getCategoryResource(context, str);
        return categoryResource == 0 ? str : context.getString(categoryResource);
    }

    void bindModel(CategoryItem categoryItem, LiveData liveData) {
        loadAppItems(liveData);
        Category category = categoryItem.category;
        this.currentCategory = category;
        String name = category.getName(LocaleListCompat.getDefault());
        if (name == null) {
            name = translateCategory(this.activity, categoryItem.category.getId());
        }
        this.heading.setText(name);
        this.heading.setContentDescription(this.activity.getString(R.string.tts_category_name, name));
        this.background.setBackgroundColor(getBackgroundColour(this.activity, categoryItem.category.getId()));
        FileV2 icon = categoryItem.category.getIcon(LocaleListCompat.getDefault());
        Repository repository = FDroidApp.getRepoManager(this.activity).getRepository(categoryItem.category.getRepoId());
        if (icon != null && repository != null) {
            Log.i(TAG, "Loading remote image for: " + categoryItem.category.getId());
            Glide.with((FragmentActivity) this.activity).m3060load(Utils.getGlideModel(repository, icon)).apply((BaseRequestOptions) Utils.getAlwaysShowIconRequestOptions()).into(this.image);
        } else {
            Glide.with((FragmentActivity) this.activity).clear(this.image);
        }
        Resources resources = this.activity.getResources();
        Button button = this.viewAll;
        int i = R.plurals.button_view_all_apps_in_category;
        int i2 = categoryItem.numApps;
        button.setText(resources.getQuantityString(i, i2, Integer.valueOf(i2)));
        Button button2 = this.viewAll;
        int i3 = R.plurals.tts_view_all_in_category;
        int i4 = categoryItem.numApps;
        button2.setContentDescription(resources.getQuantityString(i3, i4, Integer.valueOf(i4), this.currentCategory));
    }

    private void loadAppItems(final LiveData liveData) {
        setIsRecyclable(false);
        liveData.observe(this.activity, new Observer() { // from class: org.fdroid.fdroid.views.categories.CategoryController.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(List<AppOverviewItem> list) {
                CategoryController.this.appCardsAdapter.setAppCursor(list);
                CategoryController.this.setIsRecyclable(true);
                liveData.removeObserver(this);
            }
        });
    }

    private static int getCategoryResource(Context context, String str) {
        String strReplace = str.replace(" & ", "_").replace(" ", "_").replace("'", "");
        return context.getResources().getIdentifier("category_" + strReplace, "string", context.getPackageName());
    }

    public static int getBackgroundColour(Context context, String str) {
        return Color.HSVToColor(new float[]{new Random(str.toLowerCase(Locale.ENGLISH).hashCode()).nextFloat() * 360.0f, 0.4f, 0.5f});
    }

    private static class ItemDecorator extends RecyclerView.ItemDecoration {
        private final Context context;

        ItemDecorator(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            Resources resources = this.context.getResources();
            int dimension = (int) resources.getDimension(R.dimen.category_preview__app_list__padding__horizontal);
            int dimension2 = (int) resources.getDimension(R.dimen.category_preview__app_list__padding__horizontal__first);
            int dimension3 = (int) resources.getDimension(R.dimen.category_preview__app_list__padding__horizontal__last);
            boolean z = ViewCompat.getLayoutDirection(recyclerView) == 0;
            int childLayoutPosition = recyclerView.getChildLayoutPosition(view);
            boolean z2 = childLayoutPosition == 0;
            if (childLayoutPosition != 19) {
                dimension3 = dimension;
            }
            if (z2) {
                dimension = dimension2;
            }
            int i = z ? dimension : dimension3;
            if (!z) {
                dimension3 = dimension;
            }
            rect.set(i, 0, dimension3, 0);
        }
    }
}
