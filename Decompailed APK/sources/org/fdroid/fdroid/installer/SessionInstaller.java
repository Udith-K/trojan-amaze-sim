package org.fdroid.fdroid.installer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;

/* JADX INFO: loaded from: classes2.dex */
class SessionInstaller extends Installer {
    private final SessionInstallManager sessionInstallManager;

    @Override // org.fdroid.fdroid.installer.Installer
    public Intent getUninstallScreen() {
        return null;
    }

    SessionInstaller(Context context, App app, Apk apk) {
        super(context, app, apk);
        this.sessionInstallManager = FDroidApp.sessionInstallManager;
    }

    @Override // org.fdroid.fdroid.installer.Installer
    protected void installPackageInternal(Uri uri, Uri uri2) throws Throwable {
        this.sessionInstallManager.install(this.app, this.apk, uri, uri2);
    }

    @Override // org.fdroid.fdroid.installer.Installer
    protected void uninstallPackage() {
        this.sessionInstallManager.uninstall(this.app.packageName);
    }

    @Override // org.fdroid.fdroid.installer.Installer
    protected boolean isUnattended() {
        return SessionInstallManager.canBeUsed(this.context);
    }
}
