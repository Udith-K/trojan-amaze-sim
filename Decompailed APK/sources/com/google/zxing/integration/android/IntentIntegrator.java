package com.google.zxing.integration.android;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import ch.qos.logback.core.CoreConstants;
import com.journeyapps.barcodescanner.CaptureActivity;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public class IntentIntegrator {
    private final Activity activity;
    private Class captureActivity;
    private Collection desiredBarcodeFormats;
    private Fragment fragment;
    private final Map moreExtras = new HashMap(3);
    private int requestCode = 49374;
    private androidx.fragment.app.Fragment supportFragment;
    public static final Collection PRODUCT_CODE_TYPES = list("UPC_A", "UPC_E", "EAN_8", "EAN_13", "RSS_14");
    public static final Collection ONE_D_CODE_TYPES = list("UPC_A", "UPC_E", "EAN_8", "EAN_13", "RSS_14", "CODE_39", "CODE_93", "CODE_128", "ITF", "RSS_14", "RSS_EXPANDED");
    public static final Collection ALL_CODE_TYPES = null;

    protected Class getDefaultCaptureActivity() {
        return CaptureActivity.class;
    }

    public IntentIntegrator(Activity activity) {
        this.activity = activity;
    }

    public Class getCaptureActivity() {
        if (this.captureActivity == null) {
            this.captureActivity = getDefaultCaptureActivity();
        }
        return this.captureActivity;
    }

    public final void initiateScan() {
        startActivityForResult(createScanIntent(), this.requestCode);
    }

    public Intent createScanIntent() {
        Intent intent = new Intent(this.activity, (Class<?>) getCaptureActivity());
        intent.setAction("com.google.zxing.client.android.SCAN");
        if (this.desiredBarcodeFormats != null) {
            StringBuilder sb = new StringBuilder();
            for (String str : this.desiredBarcodeFormats) {
                if (sb.length() > 0) {
                    sb.append(CoreConstants.COMMA_CHAR);
                }
                sb.append(str);
            }
            intent.putExtra("SCAN_FORMATS", sb.toString());
        }
        intent.addFlags(67108864);
        intent.addFlags(PKIFailureInfo.signerNotTrusted);
        attachMoreExtras(intent);
        return intent;
    }

    protected void startActivityForResult(Intent intent, int i) {
        Fragment fragment = this.fragment;
        if (fragment != null) {
            fragment.startActivityForResult(intent, i);
            return;
        }
        androidx.fragment.app.Fragment fragment2 = this.supportFragment;
        if (fragment2 != null) {
            fragment2.startActivityForResult(intent, i);
        } else {
            this.activity.startActivityForResult(intent, i);
        }
    }

    public static IntentResult parseActivityResult(int i, int i2, Intent intent) {
        if (i == 49374) {
            return parseActivityResult(i2, intent);
        }
        return null;
    }

    public static IntentResult parseActivityResult(int i, Intent intent) {
        if (i == -1) {
            String stringExtra = intent.getStringExtra("SCAN_RESULT");
            String stringExtra2 = intent.getStringExtra("SCAN_RESULT_FORMAT");
            byte[] byteArrayExtra = intent.getByteArrayExtra("SCAN_RESULT_BYTES");
            int intExtra = intent.getIntExtra("SCAN_RESULT_ORIENTATION", Integer.MIN_VALUE);
            return new IntentResult(stringExtra, stringExtra2, byteArrayExtra, intExtra == Integer.MIN_VALUE ? null : Integer.valueOf(intExtra), intent.getStringExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL"), intent.getStringExtra("SCAN_RESULT_IMAGE_PATH"), intent);
        }
        return new IntentResult(intent);
    }

    private static List list(String... strArr) {
        return Collections.unmodifiableList(Arrays.asList(strArr));
    }

    private void attachMoreExtras(Intent intent) {
        for (Map.Entry entry : this.moreExtras.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Integer) {
                intent.putExtra(str, (Integer) value);
            } else if (value instanceof Long) {
                intent.putExtra(str, (Long) value);
            } else if (value instanceof Boolean) {
                intent.putExtra(str, (Boolean) value);
            } else if (value instanceof Double) {
                intent.putExtra(str, (Double) value);
            } else if (value instanceof Float) {
                intent.putExtra(str, (Float) value);
            } else if (value instanceof Bundle) {
                intent.putExtra(str, (Bundle) value);
            } else if (value instanceof int[]) {
                intent.putExtra(str, (int[]) value);
            } else if (value instanceof long[]) {
                intent.putExtra(str, (long[]) value);
            } else if (value instanceof boolean[]) {
                intent.putExtra(str, (boolean[]) value);
            } else if (value instanceof double[]) {
                intent.putExtra(str, (double[]) value);
            } else if (value instanceof float[]) {
                intent.putExtra(str, (float[]) value);
            } else if (value instanceof String[]) {
                intent.putExtra(str, (String[]) value);
            } else {
                intent.putExtra(str, value.toString());
            }
        }
    }
}
