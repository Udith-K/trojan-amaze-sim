package org.fdroid.fdroid.panic;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreferenceCompat;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
public class DestructiveSwitchPreference extends SwitchPreferenceCompat {
    public DestructiveSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DestructiveSwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public DestructiveSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DestructiveSwitchPreference(Context context) {
        super(context);
    }

    @Override // androidx.preference.SwitchPreferenceCompat, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (preferenceViewHolder.itemView.isEnabled()) {
            preferenceViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.panic_destructive));
        }
    }
}
