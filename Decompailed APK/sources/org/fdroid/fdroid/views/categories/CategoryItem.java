package org.fdroid.fdroid.views.categories;

import org.fdroid.database.Category;

/* JADX INFO: loaded from: classes2.dex */
public class CategoryItem {
    public final Category category;
    final int numApps;

    public CategoryItem(Category category, int i) {
        this.category = category;
        this.numApps = i;
    }
}
