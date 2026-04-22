package org.fdroid.fdroid.panic;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import info.guardianproject.panic.PanicResponder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.installer.PrivilegedInstaller;

/* JADX INFO: loaded from: classes2.dex */
public class PanicPreferencesFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String PREF_APP = "pref_panic_app";
    private PreferenceCategory categoryAppsToUninstall;
    private PackageManager pm;
    private ListPreference prefApp;
    private SwitchPreferenceCompat prefExit;
    private SwitchPreferenceCompat prefHide;
    private SwitchPreferenceCompat prefResetRepos;

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        addPreferencesFromResource(R.xml.preferences_panic);
        this.pm = requireActivity().getPackageManager();
        this.prefExit = (SwitchPreferenceCompat) findPreference(Preferences.PREF_PANIC_EXIT);
        this.prefApp = (ListPreference) findPreference(PREF_APP);
        SwitchPreferenceCompat switchPreferenceCompat = (SwitchPreferenceCompat) findPreference(Preferences.PREF_PANIC_HIDE);
        this.prefHide = switchPreferenceCompat;
        switchPreferenceCompat.setTitle(getString(R.string.panic_hide_title, getString(R.string.app_name)));
        this.prefResetRepos = (SwitchPreferenceCompat) findPreference(Preferences.PREF_PANIC_RESET_REPOS);
        this.categoryAppsToUninstall = (PreferenceCategory) findPreference("pref_panic_apps_to_uninstall");
        if (PanicResponder.checkForDisconnectIntent(requireActivity())) {
            requireActivity().finish();
            return;
        }
        String connectIntentSender = PanicResponder.getConnectIntentSender(requireActivity());
        if (!TextUtils.isEmpty(connectIntentSender) && !TextUtils.equals(connectIntentSender, PanicResponder.getTriggerPackageName(getActivity()))) {
            showOptInDialog();
        }
        this.prefApp.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: org.fdroid.fdroid.panic.PanicPreferencesFragment$$ExternalSyntheticLambda3
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return this.f$0.lambda$onCreatePreferences$0(preference, obj);
            }
        });
        showPanicApp(PanicResponder.getTriggerPackageName(getActivity()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onCreatePreferences$0(Preference preference, Object obj) {
        String str = (String) obj;
        PanicResponder.setTriggerPackageName(requireActivity(), str);
        if (str.equals("NONE")) {
            this.prefHide.setChecked(false);
            this.prefHide.setEnabled(false);
            this.prefResetRepos.setChecked(false);
            this.prefResetRepos.setEnabled(false);
            requireActivity().setResult(0);
        } else {
            this.prefHide.setEnabled(true);
            this.prefResetRepos.setEnabled(true);
        }
        showPanicApp(str);
        return true;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        if (!PrivilegedInstaller.isDefault(getActivity())) {
            getPreferenceScreen().removePreference(this.categoryAppsToUninstall);
        } else {
            showWipeList();
        }
    }

    private void showWipeList() {
        Intent intent = new Intent(getActivity(), (Class<?>) SelectInstalledAppsActivity.class);
        intent.setAction("android.intent.action.MAIN");
        Set<String> panicWipeSet = Preferences.get().getPanicWipeSet();
        this.categoryAppsToUninstall.removeAll();
        if ("NONE".equals(this.prefApp.getValue())) {
            this.categoryAppsToUninstall.setEnabled(false);
            return;
        }
        this.categoryAppsToUninstall.setEnabled(true);
        if (panicWipeSet.size() > 0) {
            for (String str : panicWipeSet) {
                DestructivePreference destructivePreference = new DestructivePreference(getActivity());
                destructivePreference.setSingleLineTitle(true);
                destructivePreference.setIntent(intent);
                this.categoryAppsToUninstall.addPreference(destructivePreference);
                try {
                    PackageManager packageManager = this.pm;
                    destructivePreference.setTitle(packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)));
                    destructivePreference.setIcon(this.pm.getApplicationIcon(str));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    destructivePreference.setTitle(str);
                }
            }
            return;
        }
        Preference preference = new Preference(requireActivity());
        preference.setIntent(intent);
        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_add_circle_outline);
        drawable.setColorFilter(new LightingColorFilter(0, getResources().getColor(R.color.swap_light_grey_icon)));
        preference.setSingleLineTitle(true);
        preference.setTitle(R.string.panic_add_apps_to_uninstall);
        preference.setIcon(drawable);
        this.categoryAppsToUninstall.addPreference(preference);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str.equals(Preferences.PREF_PANIC_HIDE) && sharedPreferences.getBoolean(Preferences.PREF_PANIC_HIDE, false)) {
            showHideConfirmationDialog();
        }
        if (!str.equals(Preferences.PREF_PANIC_EXIT) || sharedPreferences.getBoolean(Preferences.PREF_PANIC_EXIT, true)) {
            return;
        }
        this.prefHide.setChecked(false);
    }

    private void showPanicApp(String str) {
        ArrayList arrayList = new ArrayList(Collections.singletonList(getString(R.string.panic_app_setting_none)));
        ArrayList arrayList2 = new ArrayList(Collections.singletonList("NONE"));
        for (ResolveInfo resolveInfo : PanicResponder.resolveTriggerApps(this.pm)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null) {
                arrayList.add(activityInfo.loadLabel(this.pm));
                arrayList2.add(resolveInfo.activityInfo.packageName);
            }
        }
        this.prefApp.setEntries((CharSequence[]) arrayList.toArray(new CharSequence[0]));
        this.prefApp.setEntryValues((CharSequence[]) arrayList2.toArray(new CharSequence[0]));
        this.prefApp.setDefaultValue("NONE");
        if (arrayList.size() <= 1) {
            this.prefApp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: org.fdroid.fdroid.panic.PanicPreferencesFragment$$ExternalSyntheticLambda0
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$showPanicApp$1(preference);
                }
            });
        }
        if (TextUtils.isEmpty(str) || str.equals("NONE")) {
            this.prefApp.setValue("NONE");
            this.prefApp.setSummary(getString(R.string.panic_app_setting_summary));
            this.prefApp.setIcon((Drawable) null);
            Drawable drawable = ContextCompat.getDrawable(requireActivity(), R.drawable.ic_cancel);
            TypedValue typedValue = new TypedValue();
            requireActivity().getTheme().resolveAttribute(R.attr.appListItem, typedValue, true);
            drawable.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_IN);
            this.prefApp.setIcon(drawable);
            this.prefHide.setEnabled(false);
            showWipeList();
            return;
        }
        try {
            this.prefApp.setValue(str);
            ListPreference listPreference = this.prefApp;
            PackageManager packageManager = this.pm;
            listPreference.setSummary(packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)));
            this.prefApp.setIcon(this.pm.getApplicationIcon(str));
            this.prefHide.setEnabled(true);
            this.prefResetRepos.setEnabled(true);
            showWipeList();
        } catch (PackageManager.NameNotFoundException unused) {
            PanicResponder.setTriggerPackageName(requireActivity(), "NONE");
            showPanicApp("NONE");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$showPanicApp$1(Preference preference) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=info.guardianproject.ripple"));
        intent.addFlags(268435456);
        if (intent.resolveActivity(requireActivity().getPackageManager()) == null) {
            return true;
        }
        startActivity(intent);
        return true;
    }

    private void showOptInDialog() {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.panic.PanicPreferencesFragment$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showOptInDialog$2(dialogInterface, i);
            }
        };
        DialogInterface.OnClickListener onClickListener2 = new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.panic.PanicPreferencesFragment$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showOptInDialog$3(dialogInterface, i);
            }
        };
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireActivity());
        materialAlertDialogBuilder.setTitle((CharSequence) getString(R.string.panic_app_dialog_title));
        CharSequence string = getString(R.string.panic_app_unknown_app);
        String callingPackageName = getCallingPackageName();
        if (callingPackageName != null) {
            try {
                PackageManager packageManager = this.pm;
                string = packageManager.getApplicationLabel(packageManager.getApplicationInfo(callingPackageName, 0));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        materialAlertDialogBuilder.setMessage((CharSequence) String.format(getString(R.string.panic_app_dialog_message), string));
        materialAlertDialogBuilder.setNegativeButton(R.string.allow, onClickListener);
        materialAlertDialogBuilder.setPositiveButton(R.string.cancel, onClickListener2);
        materialAlertDialogBuilder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOptInDialog$2(DialogInterface dialogInterface, int i) {
        PanicResponder.setTriggerPackageName(requireActivity());
        showPanicApp(PanicResponder.getTriggerPackageName(getActivity()));
        requireActivity().setResult(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOptInDialog$3(DialogInterface dialogInterface, int i) {
        requireActivity().setResult(0);
        requireActivity().finish();
    }

    private String getCallingPackageName() {
        ComponentName callingActivity = requireActivity().getCallingActivity();
        if (callingActivity != null) {
            return callingActivity.getPackageName();
        }
        return null;
    }

    private void showHideConfirmationDialog() {
        String string = getString(R.string.app_name);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireActivity());
        materialAlertDialogBuilder.setTitle(R.string.panic_hide_warning_title);
        materialAlertDialogBuilder.setMessage((CharSequence) getString(R.string.panic_hide_warning_message, string, Integer.valueOf(HidingManager.getUnhidePin(requireActivity())), getString(R.string.hiding_calculator)));
        materialAlertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.panic.PanicPreferencesFragment$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showHideConfirmationDialog$4(dialogInterface, i);
            }
        });
        materialAlertDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.panic.PanicPreferencesFragment$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        materialAlertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: org.fdroid.fdroid.panic.PanicPreferencesFragment$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$showHideConfirmationDialog$6(dialogInterface);
            }
        });
        materialAlertDialogBuilder.setView(R.layout.dialog_app_hiding);
        materialAlertDialogBuilder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showHideConfirmationDialog$4(DialogInterface dialogInterface, int i) {
        this.prefExit.setChecked(true);
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showHideConfirmationDialog$6(DialogInterface dialogInterface) {
        this.prefHide.setChecked(false);
        this.prefResetRepos.setChecked(false);
    }
}
