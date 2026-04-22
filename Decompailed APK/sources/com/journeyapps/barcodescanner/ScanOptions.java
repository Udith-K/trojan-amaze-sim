package com.journeyapps.barcodescanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import ch.qos.logback.core.CoreConstants;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public class ScanOptions {
    private Class captureActivity;
    private Collection desiredBarcodeFormats;
    private final Map moreExtras = new HashMap(3);
    public static final Collection PRODUCT_CODE_TYPES = list("UPC_A", "UPC_E", "EAN_8", "EAN_13", "RSS_14");
    public static final Collection ONE_D_CODE_TYPES = list("UPC_A", "UPC_E", "EAN_8", "EAN_13", "RSS_14", "CODE_39", "CODE_93", "CODE_128", "ITF", "RSS_14", "RSS_EXPANDED");
    public static final Collection ALL_CODE_TYPES = null;

    protected Class getDefaultCaptureActivity() {
        return CaptureActivity.class;
    }

    public Class getCaptureActivity() {
        if (this.captureActivity == null) {
            this.captureActivity = getDefaultCaptureActivity();
        }
        return this.captureActivity;
    }

    public final ScanOptions addExtra(String str, Object obj) {
        this.moreExtras.put(str, obj);
        return this;
    }

    public final ScanOptions setPrompt(String str) {
        if (str != null) {
            addExtra("PROMPT_MESSAGE", str);
        }
        return this;
    }

    public ScanOptions setOrientationLocked(boolean z) {
        addExtra("SCAN_ORIENTATION_LOCKED", Boolean.valueOf(z));
        return this;
    }

    public ScanOptions setBeepEnabled(boolean z) {
        addExtra("BEEP_ENABLED", Boolean.valueOf(z));
        return this;
    }

    public ScanOptions setDesiredBarcodeFormats(String... strArr) {
        this.desiredBarcodeFormats = Arrays.asList(strArr);
        return this;
    }

    public Intent createScanIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) getCaptureActivity());
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
