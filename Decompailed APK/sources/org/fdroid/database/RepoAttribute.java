package org.fdroid.database;

import androidx.core.os.LocaleListCompat;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.LocaleChooser;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: compiled from: Repository.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u0011R\"\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u0002`\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\fX \u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\nR\"\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\fX \u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\n¨\u0006\u0014"}, d2 = {"Lorg/fdroid/database/RepoAttribute;", "", "<init>", "()V", "icon", "", "", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileV2;", "getIcon", "()Ljava/util/Map;", "name", "Lorg/fdroid/index/v2/LocalizedTextV2;", "getName$database_release", "description", "getDescription$database_release", "localeList", "Landroidx/core/os/LocaleListCompat;", "getName", "getDescription", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class RepoAttribute {
    public abstract Map<String, String> getDescription$database_release();

    public abstract Map<String, FileV2> getIcon();

    public abstract Map<String, String> getName$database_release();

    public final FileV2 getIcon(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (FileV2) LocaleChooser.INSTANCE.getBestLocale(getIcon(), localeList);
    }

    public final String getName(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (String) LocaleChooser.INSTANCE.getBestLocale(getName$database_release(), localeList);
    }

    public final String getDescription(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (String) LocaleChooser.INSTANCE.getBestLocale(getDescription$database_release(), localeList);
    }
}
