package org.fdroid.fdroid.views.apps;

import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.App;

/* JADX INFO: loaded from: classes2.dex */
public class AppListItemState {
    private final App app;
    private boolean checkBoxChecked;
    private boolean showCheckBox;
    private boolean showInstallButton;
    private CharSequence mainText = null;
    private CharSequence actionButtonText = null;
    private CharSequence secondaryButtonText = null;
    private CharSequence statusText = null;
    private CharSequence secondaryStatusText = null;
    private int progressCurrent = -1;
    private int progressMax = -1;

    public AppListItemState(App app) {
        this.app = app;
    }

    public AppListItemState setMainText(CharSequence charSequence) {
        this.mainText = charSequence;
        return this;
    }

    public AppListItemState showActionButton(CharSequence charSequence) {
        this.actionButtonText = charSequence;
        return this;
    }

    public AppListItemState showSecondaryButton(CharSequence charSequence) {
        this.secondaryButtonText = charSequence;
        return this;
    }

    public AppListItemState setStatusText(CharSequence charSequence) {
        this.statusText = charSequence;
        return this;
    }

    public AppListItemState setSecondaryStatusText(CharSequence charSequence) {
        this.secondaryStatusText = charSequence;
        return this;
    }

    public AppListItemState setProgress(int i, int i2) {
        this.progressCurrent = i;
        this.progressMax = i2;
        return this;
    }

    public AppListItemState setShowInstallButton(boolean z) {
        this.showInstallButton = z;
        return this;
    }

    public CharSequence getMainText() {
        if (this.showCheckBox) {
            return this.app.name;
        }
        CharSequence charSequence = this.mainText;
        if (charSequence != null) {
            return charSequence;
        }
        App app = this.app;
        return Utils.formatAppNameAndSummary(app.name, app.summary);
    }

    public boolean shouldShowInstall() {
        return this.showInstallButton;
    }

    public boolean shouldShowActionButton() {
        return this.actionButtonText != null;
    }

    public CharSequence getActionButtonText() {
        return this.actionButtonText;
    }

    public boolean shouldShowSecondaryButton() {
        return this.secondaryButtonText != null;
    }

    public CharSequence getSecondaryButtonText() {
        return this.secondaryButtonText;
    }

    public boolean showProgress() {
        return this.progressCurrent >= 0;
    }

    public boolean isProgressIndeterminate() {
        return this.progressMax <= 0;
    }

    public int getProgressCurrent() {
        return this.progressCurrent;
    }

    public int getProgressMax() {
        return this.progressMax;
    }

    public CharSequence getStatusText() {
        return this.statusText;
    }

    public CharSequence getSecondaryStatusText() {
        return this.secondaryStatusText;
    }

    public boolean shouldShowCheckBox() {
        return this.showCheckBox;
    }

    public boolean isCheckBoxChecked() {
        return this.checkBoxChecked;
    }

    public AppListItemState setCheckBoxStatus(boolean z) {
        this.showCheckBox = true;
        this.checkBoxChecked = z;
        return this;
    }
}
