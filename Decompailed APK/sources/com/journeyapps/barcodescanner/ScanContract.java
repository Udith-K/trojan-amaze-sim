package com.journeyapps.barcodescanner;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContract;

/* JADX INFO: loaded from: classes.dex */
public class ScanContract extends ActivityResultContract {
    @Override // androidx.activity.result.contract.ActivityResultContract
    public Intent createIntent(Context context, ScanOptions scanOptions) {
        return scanOptions.createScanIntent(context);
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public ScanIntentResult parseResult(int i, Intent intent) {
        return ScanIntentResult.parseActivityResult(i, intent);
    }
}
