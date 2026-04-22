package org.fdroid.fdroid.views.repos;

import kotlin.Metadata;
import org.fdroid.download.Mirror;

/* JADX INFO: compiled from: RepoDetailsScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0003H&J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0012"}, d2 = {"Lorg/fdroid/fdroid/views/repos/RepoDetailsScreenCallbacks;", "", "onBackClicked", "", "onShareClicked", "onShowQrCodeClicked", "onDeleteClicked", "onInfoClicked", "onShowAppsClicked", "onToggleArchiveClicked", "enabled", "", "onEditCredentialsClicked", "setMirrorEnabled", "mirror", "Lorg/fdroid/download/Mirror;", "onShareMirror", "onDeleteMirror", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface RepoDetailsScreenCallbacks {
    void onBackClicked();

    void onDeleteClicked();

    void onDeleteMirror(Mirror mirror);

    void onEditCredentialsClicked();

    void onInfoClicked();

    void onShareClicked();

    void onShareMirror(Mirror mirror);

    void onShowAppsClicked();

    void onShowQrCodeClicked();

    void onToggleArchiveClicked(boolean enabled);

    void setMirrorEnabled(Mirror mirror, boolean enabled);
}
