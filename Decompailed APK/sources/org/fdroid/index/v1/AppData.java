package org.fdroid.index.v1;

import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IndexV1StreamProcessor.kt */
/* JADX INFO: loaded from: classes2.dex */
final class AppData {
    private final Map antiFeatures;
    private final List categories;
    private final Long suggestedVersionCode;
    private final Map whatsNew;

    public AppData(Map antiFeatures, Map map, Long l, List categories) {
        Intrinsics.checkNotNullParameter(antiFeatures, "antiFeatures");
        Intrinsics.checkNotNullParameter(categories, "categories");
        this.antiFeatures = antiFeatures;
        this.whatsNew = map;
        this.suggestedVersionCode = l;
        this.categories = categories;
    }

    public final Map getAntiFeatures() {
        return this.antiFeatures;
    }

    public final Map getWhatsNew() {
        return this.whatsNew;
    }

    public final Long getSuggestedVersionCode() {
        return this.suggestedVersionCode;
    }

    public final List getCategories() {
        return this.categories;
    }
}
