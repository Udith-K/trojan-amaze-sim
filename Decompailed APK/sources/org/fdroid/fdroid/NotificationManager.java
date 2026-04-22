package org.fdroid.fdroid;

import android.app.Notification;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J)\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\u0006\u0010\u0013\u001a\u00020\u000bJ#\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/fdroid/fdroid/NotificationManager;", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "nm", "Landroid/app/NotificationManager;", "lastRepoUpdateNotification", "", "showUpdateRepoNotification", "", "msg", "", "throttle", "", "progress", "", "(Ljava/lang/String;ZLjava/lang/Integer;)V", "cancelUpdateRepoNotification", "getRepoUpdateNotification", "Landroidx/core/app/NotificationCompat$Builder;", "(Ljava/lang/String;Ljava/lang/Integer;)Landroidx/core/app/NotificationCompat$Builder;", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NotificationManager {
    public static final int $stable = 8;
    private final Context context;
    private long lastRepoUpdateNotification;
    private final android.app.NotificationManager nm;

    public NotificationManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) ContextCompat.getSystemService(context, android.app.NotificationManager.class);
        if (notificationManager == null) {
            throw new IllegalStateException("No NotificationManager");
        }
        this.nm = notificationManager;
    }

    public static /* synthetic */ void showUpdateRepoNotification$default(NotificationManager notificationManager, String str, boolean z, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            num = null;
        }
        notificationManager.showUpdateRepoNotification(str, z, num);
    }

    public final void showUpdateRepoNotification(String msg, boolean throttle, Integer progress) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (!throttle || System.currentTimeMillis() - this.lastRepoUpdateNotification > 500) {
            Notification notificationBuild = getRepoUpdateNotification(msg, progress).build();
            Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
            this.lastRepoUpdateNotification = System.currentTimeMillis();
            this.nm.notify(0, notificationBuild);
        }
    }

    public final void cancelUpdateRepoNotification() {
        this.nm.cancel(0);
    }

    public static /* synthetic */ NotificationCompat.Builder getRepoUpdateNotification$default(NotificationManager notificationManager, String str, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            num = null;
        }
        return notificationManager.getRepoUpdateNotification(str, num);
    }

    public final NotificationCompat.Builder getRepoUpdateNotification(String msg, Integer progress) {
        NotificationCompat.Builder progress2 = new NotificationCompat.Builder(this.context, "update-channel").setSmallIcon(R.drawable.ic_refresh).setCategory("service").setContentTitle(this.context.getString(R.string.banner_updating_repositories)).setContentText(msg).setOngoing(true).setProgress(100, progress != null ? progress.intValue() : 0, progress == null);
        Intrinsics.checkNotNullExpressionValue(progress2, "setProgress(...)");
        return progress2;
    }
}
