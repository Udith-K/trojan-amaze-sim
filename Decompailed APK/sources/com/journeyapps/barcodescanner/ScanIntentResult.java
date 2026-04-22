package com.journeyapps.barcodescanner;

import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public final class ScanIntentResult {
    private final String barcodeImagePath;
    private final String contents;
    private final String errorCorrectionLevel;
    private final String formatName;
    private final Integer orientation;
    private final Intent originalIntent;
    private final byte[] rawBytes;

    ScanIntentResult(Intent intent) {
        this(null, null, null, null, null, null, intent);
    }

    ScanIntentResult(String str, String str2, byte[] bArr, Integer num, String str3, String str4, Intent intent) {
        this.contents = str;
        this.formatName = str2;
        this.rawBytes = bArr;
        this.orientation = num;
        this.errorCorrectionLevel = str3;
        this.barcodeImagePath = str4;
        this.originalIntent = intent;
    }

    public String getContents() {
        return this.contents;
    }

    public String toString() {
        byte[] bArr = this.rawBytes;
        return "Format: " + this.formatName + "\nContents: " + this.contents + "\nRaw bytes: (" + (bArr == null ? 0 : bArr.length) + " bytes)\nOrientation: " + this.orientation + "\nEC level: " + this.errorCorrectionLevel + "\nBarcode image: " + this.barcodeImagePath + "\nOriginal intent: " + this.originalIntent + '\n';
    }

    public static ScanIntentResult parseActivityResult(int i, Intent intent) {
        if (i == -1) {
            String stringExtra = intent.getStringExtra("SCAN_RESULT");
            String stringExtra2 = intent.getStringExtra("SCAN_RESULT_FORMAT");
            byte[] byteArrayExtra = intent.getByteArrayExtra("SCAN_RESULT_BYTES");
            int intExtra = intent.getIntExtra("SCAN_RESULT_ORIENTATION", Integer.MIN_VALUE);
            return new ScanIntentResult(stringExtra, stringExtra2, byteArrayExtra, intExtra == Integer.MIN_VALUE ? null : Integer.valueOf(intExtra), intent.getStringExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL"), intent.getStringExtra("SCAN_RESULT_IMAGE_PATH"), intent);
        }
        return new ScanIntentResult(intent);
    }
}
