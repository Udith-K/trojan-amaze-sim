package org.fdroid.fdroid.panic;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
public class DestructivePreference extends Preference {
    public DestructivePreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DestructivePreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public DestructivePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DestructivePreference(Context context) {
        super(context);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.panic_destructive));
    }
}
