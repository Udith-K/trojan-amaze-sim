package org.fdroid.fdroid;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.net.DownloaderService;
import org.fdroid.fdroid.views.AppDetailsActivity;
import org.fdroid.fdroid.views.main.MainActivity;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationHelper {
    static final String BROADCAST_NOTIFICATIONS_ALL_INSTALLED_CLEARED = "org.fdroid.fdroid.installer.notifications.allinstalled.cleared";
    static final String BROADCAST_NOTIFICATIONS_ALL_UPDATES_CLEARED = "org.fdroid.fdroid.installer.notifications.allupdates.cleared";
    static final String BROADCAST_NOTIFICATIONS_INSTALLED_CLEARED = "org.fdroid.fdroid.installer.notifications.installed.cleared";
    static final String BROADCAST_NOTIFICATIONS_UPDATE_CLEARED = "org.fdroid.fdroid.installer.notifications.update.cleared";
    private static final String CHANNEL_INSTALLS = "install-channel";
    public static final String CHANNEL_SWAPS = "swap-channel";
    static final String CHANNEL_UPDATES = "update-channel";
    private static final String GROUP_INSTALLED = "installed";
    private static final String GROUP_UPDATES = "updates";
    private static final int MAX_INSTALLED_TO_SHOW = 10;
    private static final int MAX_UPDATES_TO_SHOW = 5;
    private static final int NOTIFY_ID_INSTALLED = 2;
    private static final int NOTIFY_ID_UPDATES = 1;
    private final Context context;
    private final NotificationManagerCompat notificationManager;
    private final ArrayList<AppUpdateStatusManager.AppUpdateStatus> updates = new ArrayList<>();
    private final ArrayList<AppUpdateStatusManager.AppUpdateStatus> installed = new ArrayList<>();

    NotificationHelper(Context context) {
        this.context = context;
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(context);
        this.notificationManager = notificationManagerCompatFrom;
        notificationManagerCompatFrom.createNotificationChannelsCompat(Arrays.asList(new NotificationChannelCompat.Builder(CHANNEL_INSTALLS, 2).setName(context.getString(R.string.notification_channel_installs_title)).setDescription(context.getString(R.string.notification_channel_installs_description)).build(), new NotificationChannelCompat.Builder(CHANNEL_SWAPS, 2).setName(context.getString(R.string.notification_channel_swaps_title)).setDescription(context.getString(R.string.notification_channel_swaps_description)).build(), new NotificationChannelCompat.Builder(CHANNEL_UPDATES, 2).setName(context.getString(R.string.notification_channel_updates_title)).setDescription(context.getString(R.string.notification_channel_updates_description)).build()));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppUpdateStatusManager.BROADCAST_APPSTATUS_LIST_CHANGED);
        intentFilter.addAction(AppUpdateStatusManager.BROADCAST_APPSTATUS_ADDED);
        intentFilter.addAction(AppUpdateStatusManager.BROADCAST_APPSTATUS_CHANGED);
        intentFilter.addAction(AppUpdateStatusManager.BROADCAST_APPSTATUS_REMOVED);
        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() { // from class: org.fdroid.fdroid.NotificationHelper.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                AppUpdateStatusManager appUpdateStatusManager;
                if (intent == null) {
                }
                appUpdateStatusManager = AppUpdateStatusManager.getInstance(context2);
                String action = intent.getAction();
                action.hashCode();
                switch (action) {
                    case "org.fdroid.fdroid.installer.appstatus.listchange":
                        NotificationHelper.this.notificationManager.cancelAll();
                        NotificationHelper.this.updateStatusLists();
                        NotificationHelper.this.createSummaryNotifications();
                        Iterator<AppUpdateStatusManager.AppUpdateStatus> it = appUpdateStatusManager.getAll().iterator();
                        while (it.hasNext()) {
                            NotificationHelper.this.createNotification(it.next());
                        }
                        break;
                    case "org.fdroid.fdroid.installer.appstatus.appchange.add":
                        NotificationHelper.this.updateStatusLists();
                        NotificationHelper.this.createSummaryNotifications();
                        AppUpdateStatusManager.AppUpdateStatus appUpdateStatus = appUpdateStatusManager.get(intent.getStringExtra(DownloaderService.EXTRA_CANONICAL_URL));
                        if (appUpdateStatus != null) {
                            NotificationHelper.this.createNotification(appUpdateStatus);
                            break;
                        }
                        break;
                    case "org.fdroid.fdroid.installer.appstatus.appchange.change":
                        AppUpdateStatusManager.AppUpdateStatus appUpdateStatus2 = appUpdateStatusManager.get(intent.getStringExtra(DownloaderService.EXTRA_CANONICAL_URL));
                        NotificationHelper.this.updateStatusLists();
                        if (appUpdateStatus2 != null) {
                            NotificationHelper.this.createNotification(appUpdateStatus2);
                        }
                        if (intent.getBooleanExtra(AppUpdateStatusManager.EXTRA_IS_STATUS_UPDATE, false)) {
                            NotificationHelper.this.createSummaryNotifications();
                            break;
                        }
                        break;
                    case "org.fdroid.fdroid.installer.appstatus.appchange.remove":
                        String stringExtra = intent.getStringExtra(DownloaderService.EXTRA_CANONICAL_URL);
                        NotificationHelper.this.notificationManager.cancel(stringExtra, 2);
                        NotificationHelper.this.notificationManager.cancel(stringExtra, 1);
                        NotificationHelper.this.updateStatusLists();
                        NotificationHelper.this.createSummaryNotifications();
                        break;
                }
            }
        }, intentFilter);
    }

    private boolean useStackedNotifications() {
        return Build.VERSION.SDK_INT >= 24;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStatusLists() {
        if (this.notificationManager.areNotificationsEnabled()) {
            this.updates.clear();
            this.installed.clear();
            for (AppUpdateStatusManager.AppUpdateStatus appUpdateStatus : AppUpdateStatusManager.getInstance(this.context).getAll()) {
                if (appUpdateStatus.status == AppUpdateStatusManager.Status.Installed) {
                    this.installed.add(appUpdateStatus);
                } else if (!shouldIgnoreEntry(appUpdateStatus)) {
                    this.updates.add(appUpdateStatus);
                }
            }
        }
    }

    private boolean shouldIgnoreEntry(AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        AppUpdateStatusManager.Status status = appUpdateStatus.status;
        if (status == AppUpdateStatusManager.Status.DownloadInterrupted) {
            return true;
        }
        return (status == AppUpdateStatusManager.Status.Downloading || status == AppUpdateStatusManager.Status.ReadyToInstall || status == AppUpdateStatusManager.Status.InstallError) && AppDetailsActivity.isAppVisible(appUpdateStatus.app.packageName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createNotification(AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.POST_NOTIFICATIONS") != 0) {
            return;
        }
        if (shouldIgnoreEntry(appUpdateStatus)) {
            this.notificationManager.cancel(appUpdateStatus.getCanonicalUrl(), 1);
            this.notificationManager.cancel(appUpdateStatus.getCanonicalUrl(), 2);
            return;
        }
        if (!this.notificationManager.areNotificationsEnabled() || Preferences.get().hideAllNotifications()) {
            return;
        }
        if (appUpdateStatus.status == AppUpdateStatusManager.Status.Installed) {
            if (useStackedNotifications()) {
                Notification notificationCreateInstalledNotification = createInstalledNotification(appUpdateStatus);
                this.notificationManager.cancel(appUpdateStatus.getCanonicalUrl(), 1);
                this.notificationManager.notify(appUpdateStatus.getCanonicalUrl(), 2, notificationCreateInstalledNotification);
                return;
            } else {
                if (this.installed.size() == 1) {
                    Notification notificationCreateInstalledNotification2 = createInstalledNotification(appUpdateStatus);
                    this.notificationManager.cancel(appUpdateStatus.getCanonicalUrl(), 1);
                    this.notificationManager.notify(GROUP_INSTALLED, 2, notificationCreateInstalledNotification2);
                    return;
                }
                return;
            }
        }
        if (useStackedNotifications()) {
            Notification notificationCreateUpdateNotification = createUpdateNotification(appUpdateStatus);
            this.notificationManager.cancel(appUpdateStatus.getCanonicalUrl(), 2);
            this.notificationManager.notify(appUpdateStatus.getCanonicalUrl(), 1, notificationCreateUpdateNotification);
        } else if (this.updates.size() == 1) {
            Notification notificationCreateUpdateNotification2 = createUpdateNotification(appUpdateStatus);
            this.notificationManager.cancel(appUpdateStatus.getCanonicalUrl(), 2);
            this.notificationManager.notify(GROUP_UPDATES, 1, notificationCreateUpdateNotification2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createSummaryNotifications() {
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.POST_NOTIFICATIONS") == 0 && this.notificationManager.areNotificationsEnabled() && !Preferences.get().hideAllNotifications()) {
            if (this.updates.size() != 1 || useStackedNotifications()) {
                if (this.updates.isEmpty()) {
                    this.notificationManager.cancel(GROUP_UPDATES, 1);
                } else {
                    this.notificationManager.notify(GROUP_UPDATES, 1, createUpdateSummaryNotification(this.updates));
                }
            }
            if (this.installed.size() != 1 || useStackedNotifications()) {
                if (this.installed.isEmpty()) {
                    this.notificationManager.cancel(GROUP_INSTALLED, 2);
                } else {
                    this.notificationManager.notify(GROUP_INSTALLED, 2, createInstalledSummaryNotification(this.installed));
                }
            }
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.NotificationHelper$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status;

        static {
            int[] iArr = new int[AppUpdateStatusManager.Status.values().length];
            $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status = iArr;
            try {
                iArr[AppUpdateStatusManager.Status.UpdateAvailable.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[AppUpdateStatusManager.Status.PendingInstall.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[AppUpdateStatusManager.Status.Downloading.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[AppUpdateStatusManager.Status.Installing.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[AppUpdateStatusManager.Status.ReadyToInstall.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[AppUpdateStatusManager.Status.Installed.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[AppUpdateStatusManager.Status.InstallError.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private NotificationCompat.Action getAction(AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        if (appUpdateStatus.intent == null) {
            return null;
        }
        int i = AnonymousClass3.$SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[appUpdateStatus.status.ordinal()];
        if (i == 1) {
            return new NotificationCompat.Action(R.drawable.ic_file_download, this.context.getString(R.string.notification_action_update), appUpdateStatus.intent);
        }
        if (i == 2 || i == 3 || i == 4) {
            return new NotificationCompat.Action(R.drawable.ic_cancel, this.context.getString(R.string.notification_action_cancel), appUpdateStatus.intent);
        }
        if (i != 5) {
            return null;
        }
        return new NotificationCompat.Action(R.drawable.ic_file_install, this.context.getString(R.string.notification_action_install), appUpdateStatus.intent);
    }

    private String getSingleItemTitleString(App app, AppUpdateStatusManager.Status status) {
        switch (AnonymousClass3.$SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[status.ordinal()]) {
            case 1:
                return this.context.getString(R.string.notification_title_single_update_available);
            case 2:
            case 3:
            case 4:
            case 6:
                return app.name;
            case 5:
                Context context = this.context;
                return context.getString(app.isInstalled(context) ? R.string.notification_title_single_ready_to_install_update : R.string.notification_title_single_ready_to_install);
            case 7:
                return this.context.getString(R.string.notification_title_single_install_error);
            default:
                return "";
        }
    }

    private String getSingleItemContentString(App app, AppUpdateStatusManager.Status status) {
        switch (AnonymousClass3.$SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[status.ordinal()]) {
            case 1:
            case 5:
            case 7:
                return app.name;
            case 2:
            case 3:
                Context context = this.context;
                return context.getString(app.isInstalled(context) ? R.string.notification_content_single_downloading_update : R.string.notification_content_single_downloading, app.name);
            case 4:
                return this.context.getString(R.string.notification_content_single_installing, app.name);
            case 6:
                return this.context.getString(R.string.notification_content_single_installed);
            default:
                return "";
        }
    }

    private String getMultiItemContentString(App app, AppUpdateStatusManager.Status status) {
        switch (AnonymousClass3.$SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[status.ordinal()]) {
            case 1:
                return this.context.getString(R.string.notification_title_summary_update_available);
            case 2:
            case 3:
                Context context = this.context;
                return context.getString(app.isInstalled(context) ? R.string.notification_title_summary_downloading_update : R.string.notification_title_summary_downloading);
            case 4:
                return this.context.getString(R.string.notification_title_summary_installing);
            case 5:
                Context context2 = this.context;
                return context2.getString(app.isInstalled(context2) ? R.string.notification_title_summary_ready_to_install_update : R.string.notification_title_summary_ready_to_install);
            case 6:
                return this.context.getString(R.string.notification_title_summary_installed);
            case 7:
                return this.context.getString(R.string.notification_title_summary_install_error);
            default:
                return "";
        }
    }

    private Notification createUpdateNotification(AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        App app = appUpdateStatus.app;
        AppUpdateStatusManager.Status status = appUpdateStatus.status;
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(this.context, CHANNEL_UPDATES).setAutoCancel(true).setContentTitle(getSingleItemTitleString(app, status)).setContentText(getSingleItemContentString(app, status)).setSmallIcon(R.drawable.ic_notification).setColor(ContextCompat.getColor(this.context, R.color.fdroid_blue)).setLocalOnly(true).setVisibility(-1).setContentIntent(appUpdateStatus.intent);
        if (useStackedNotifications()) {
            contentIntent.setGroup(GROUP_UPDATES);
        }
        NotificationCompat.Action action = getAction(appUpdateStatus);
        if (action != null) {
            contentIntent.addAction(action);
        }
        if (status == AppUpdateStatusManager.Status.Downloading) {
            long j = appUpdateStatus.progressMax;
            if (j == 0) {
                contentIntent.setProgress(100, 0, true);
            } else {
                contentIntent.setProgress(Utils.bytesToKb(j), Utils.bytesToKb(appUpdateStatus.progressCurrent), false);
            }
        } else if (status == AppUpdateStatusManager.Status.Installing) {
            contentIntent.setProgress(100, 0, true);
        }
        Intent intent = new Intent(BROADCAST_NOTIFICATIONS_UPDATE_CLEARED);
        intent.putExtra(DownloaderService.EXTRA_CANONICAL_URL, appUpdateStatus.getCanonicalUrl());
        intent.setClass(this.context, NotificationBroadcastReceiver.class);
        contentIntent.setDeleteIntent(PendingIntent.getBroadcast(this.context, 0, intent, 201326592));
        loadLargeIconForEntry(appUpdateStatus, contentIntent, 1, appUpdateStatus.getCanonicalUrl());
        return contentIntent.build();
    }

    private Notification createUpdateSummaryNotification(ArrayList<AppUpdateStatusManager.AppUpdateStatus> arrayList) {
        String quantityString = this.context.getResources().getQuantityString(R.plurals.notification_summary_updates, arrayList.size(), Integer.valueOf(arrayList.size()));
        StringBuilder sb = new StringBuilder();
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(quantityString);
        for (int i = 0; i < 5 && i < arrayList.size(); i++) {
            AppUpdateStatusManager.AppUpdateStatus appUpdateStatus = arrayList.get(i);
            App app = appUpdateStatus.app;
            String multiItemContentString = getMultiItemContentString(app, appUpdateStatus.status);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(app.name);
            spannableStringBuilder.setSpan(new StyleSpan(1), 0, spannableStringBuilder.length(), 17);
            spannableStringBuilder.append((CharSequence) " ");
            spannableStringBuilder.append((CharSequence) multiItemContentString);
            inboxStyle.addLine(spannableStringBuilder);
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(app.name);
        }
        if (arrayList.size() > 5) {
            int size = arrayList.size() - 5;
            inboxStyle.setSummaryText(this.context.getResources().getQuantityString(R.plurals.notification_summary_more, size, Integer.valueOf(size)));
        }
        Intent intent = new Intent(this.context, (Class<?>) MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_VIEW_UPDATES, true);
        NotificationCompat.Builder style = new NotificationCompat.Builder(this.context, CHANNEL_UPDATES).setAutoCancel(!useStackedNotifications()).setSmallIcon(R.drawable.ic_notification).setColor(ContextCompat.getColor(this.context, R.color.fdroid_blue)).setContentTitle(quantityString).setContentText(sb).setContentIntent(PendingIntent.getActivity(this.context, 0, intent, 201326592)).setLocalOnly(true).setVisibility(-1).setStyle(inboxStyle);
        if (useStackedNotifications()) {
            style.setGroup(GROUP_UPDATES).setGroupSummary(true);
        }
        Intent intent2 = new Intent(BROADCAST_NOTIFICATIONS_ALL_UPDATES_CLEARED);
        intent2.setClass(this.context, NotificationBroadcastReceiver.class);
        style.setDeleteIntent(PendingIntent.getBroadcast(this.context, 0, intent2, 201326592));
        return style.build();
    }

    private Notification createInstalledNotification(AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(this.context, CHANNEL_INSTALLS).setAutoCancel(true).setSmallIcon(R.drawable.ic_notification).setColor(ContextCompat.getColor(this.context, R.color.fdroid_blue)).setContentTitle(appUpdateStatus.app.name).setContentText(this.context.getString(R.string.notification_content_single_installed)).setLocalOnly(true).setVisibility(-1).setContentIntent(appUpdateStatus.intent);
        if (useStackedNotifications()) {
            contentIntent.setGroup(GROUP_INSTALLED);
        }
        Intent intent = new Intent(BROADCAST_NOTIFICATIONS_INSTALLED_CLEARED);
        intent.putExtra(DownloaderService.EXTRA_CANONICAL_URL, appUpdateStatus.getCanonicalUrl());
        intent.setClass(this.context, NotificationBroadcastReceiver.class);
        contentIntent.setDeleteIntent(PendingIntent.getBroadcast(this.context, 0, intent, 201326592));
        loadLargeIconForEntry(appUpdateStatus, contentIntent, 2, appUpdateStatus.getCanonicalUrl());
        return contentIntent.build();
    }

    private Notification createInstalledSummaryNotification(ArrayList<AppUpdateStatusManager.AppUpdateStatus> arrayList) {
        String quantityString = this.context.getResources().getQuantityString(R.plurals.notification_summary_installed, arrayList.size(), Integer.valueOf(arrayList.size()));
        StringBuilder sb = new StringBuilder();
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(quantityString);
        for (int i = 0; i < 10 && i < arrayList.size(); i++) {
            App app = arrayList.get(i).app;
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(app.name);
        }
        bigTextStyle.bigText(sb);
        if (arrayList.size() > 10) {
            int size = arrayList.size() - 10;
            bigTextStyle.setSummaryText(this.context.getResources().getQuantityString(R.plurals.notification_summary_more, size, Integer.valueOf(size)));
        }
        NotificationCompat.Builder visibility = new NotificationCompat.Builder(this.context, CHANNEL_INSTALLS).setAutoCancel(!useStackedNotifications()).setSmallIcon(R.drawable.ic_notification).setColor(ContextCompat.getColor(this.context, R.color.fdroid_blue)).setContentTitle(quantityString).setContentText(sb).setContentIntent(PendingIntent.getActivity(this.context, 0, new Intent(this.context, (Class<?>) MainActivity.class), 201326592)).setLocalOnly(true).setVisibility(-1);
        if (useStackedNotifications()) {
            visibility.setGroup(GROUP_INSTALLED).setGroupSummary(true);
        }
        Intent intent = new Intent(BROADCAST_NOTIFICATIONS_ALL_INSTALLED_CLEARED);
        intent.setClass(this.context, NotificationBroadcastReceiver.class);
        visibility.setDeleteIntent(PendingIntent.getBroadcast(this.context, 0, intent, 201326592));
        return visibility.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Point getLargeIconSize() {
        return new Point(this.context.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_width), this.context.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_height));
    }

    private void loadLargeIconForEntry(AppUpdateStatusManager.AppUpdateStatus appUpdateStatus, final NotificationCompat.Builder builder, final int i, final String str) {
        Context context = this.context;
        App app = appUpdateStatus.app;
        ((RequestBuilder) ((RequestBuilder) App.loadBitmapWithGlide(context, app.repoId, app.iconFile).fallback(R.drawable.ic_notification_download)).error(R.drawable.ic_notification_download)).into(new CustomTarget() { // from class: org.fdroid.fdroid.NotificationHelper.2
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
            }

            @Override // com.bumptech.glide.request.target.Target
            public void onResourceReady(Bitmap bitmap, Transition transition) {
                if (ContextCompat.checkSelfPermission(NotificationHelper.this.context, "android.permission.POST_NOTIFICATIONS") != 0) {
                    return;
                }
                builder.setLargeIcon(bitmap);
                NotificationHelper.this.notificationManager.notify(str, i, builder.build());
            }

            @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Drawable drawable) {
                if (drawable != null && ContextCompat.checkSelfPermission(NotificationHelper.this.context, "android.permission.POST_NOTIFICATIONS") == 0) {
                    Point largeIconSize = NotificationHelper.this.getLargeIconSize();
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(largeIconSize.x, largeIconSize.y, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmapCreateBitmap);
                    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                    drawable.draw(canvas);
                    builder.setLargeIcon(bitmapCreateBitmap);
                    NotificationHelper.this.notificationManager.notify(str, i, builder.build());
                }
            }
        });
    }
}
