package org.fdroid.fdroid.views.main;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.views.PreferencesFragment;

/* JADX INFO: loaded from: classes2.dex */
public class SettingsView extends FrameLayout {
    private FragmentTransaction currentTransaction;

    public SettingsView(Context context) {
        super(context);
        setId(R.id.preference_fragment_parent);
    }

    public SettingsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setId(R.id.preference_fragment_parent);
    }

    public SettingsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setId(R.id.preference_fragment_parent);
    }

    public SettingsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setId(R.id.preference_fragment_parent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        AppCompatActivity appCompatActivity = (AppCompatActivity) getContext();
        if (this.currentTransaction == null) {
            this.currentTransaction = appCompatActivity.getSupportFragmentManager().beginTransaction();
        }
        this.currentTransaction.replace(getId(), new PreferencesFragment(), "preferences-fragment");
        this.currentTransaction.commitAllowingStateLoss();
        this.currentTransaction = null;
        appCompatActivity.getSupportFragmentManager().executePendingTransactions();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AppCompatActivity appCompatActivity = (AppCompatActivity) getContext();
        Fragment fragmentFindFragmentByTag = appCompatActivity.getSupportFragmentManager().findFragmentByTag("preferences-fragment");
        if (fragmentFindFragmentByTag == null) {
            return;
        }
        if (this.currentTransaction == null) {
            this.currentTransaction = appCompatActivity.getSupportFragmentManager().beginTransaction();
        }
        this.currentTransaction.remove(fragmentFindFragmentByTag);
        this.currentTransaction.commitAllowingStateLoss();
        this.currentTransaction = null;
        appCompatActivity.getSupportFragmentManager().executePendingTransactions();
    }
}
