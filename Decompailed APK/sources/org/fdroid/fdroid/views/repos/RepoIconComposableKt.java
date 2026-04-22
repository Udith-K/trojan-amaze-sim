package org.fdroid.fdroid.views.repos;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.BaseRequestOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.R;

/* JADX INFO: compiled from: RepoIconComposable.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"RepoIcon", "", "repo", "Lorg/fdroid/database/Repository;", "modifier", "Landroidx/compose/ui/Modifier;", "(Lorg/fdroid/database/Repository;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app_fullRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RepoIconComposableKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoIcon$lambda$2(Repository repository, Modifier modifier, int i, int i2, Composer composer, int i3) {
        RepoIcon(repository, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void RepoIcon(final org.fdroid.database.Repository r20, androidx.compose.ui.Modifier r21, androidx.compose.runtime.Composer r22, final int r23, final int r24) {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.views.repos.RepoIconComposableKt.RepoIcon(org.fdroid.database.Repository, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RequestBuilder RepoIcon$lambda$1$lambda$0(RequestBuilder requestBuilder) {
        Intrinsics.checkNotNullParameter(requestBuilder, "requestBuilder");
        BaseRequestOptions baseRequestOptionsError = ((RequestBuilder) requestBuilder.fallback(R.drawable.ic_repo_app_default)).error(R.drawable.ic_repo_app_default);
        Intrinsics.checkNotNullExpressionValue(baseRequestOptionsError, "error(...)");
        return (RequestBuilder) baseRequestOptionsError;
    }
}
