package androidx.work.impl.constraints;

import androidx.work.impl.model.WorkSpec;

/* JADX INFO: compiled from: WorkConstraintsTracker.kt */
/* JADX INFO: loaded from: classes.dex */
public interface OnConstraintsStateChangedListener {
    void onConstraintsStateChanged(WorkSpec workSpec, ConstraintsState constraintsState);
}
