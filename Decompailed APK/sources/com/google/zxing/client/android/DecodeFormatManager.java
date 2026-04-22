package com.google.zxing.client.android;

import android.content.Intent;
import com.google.zxing.BarcodeFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class DecodeFormatManager {
    static final Set AZTEC_FORMATS;
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    static final Set DATA_MATRIX_FORMATS;
    private static final Map FORMATS_FOR_MODE;
    static final Set INDUSTRIAL_FORMATS;
    private static final Set ONE_D_FORMATS;
    static final Set PDF417_FORMATS;
    static final Set PRODUCT_FORMATS;
    static final Set QR_CODE_FORMATS;

    static {
        EnumSet enumSetOf = EnumSet.of(BarcodeFormat.QR_CODE);
        QR_CODE_FORMATS = enumSetOf;
        EnumSet enumSetOf2 = EnumSet.of(BarcodeFormat.DATA_MATRIX);
        DATA_MATRIX_FORMATS = enumSetOf2;
        EnumSet enumSetOf3 = EnumSet.of(BarcodeFormat.AZTEC);
        AZTEC_FORMATS = enumSetOf3;
        EnumSet enumSetOf4 = EnumSet.of(BarcodeFormat.PDF_417);
        PDF417_FORMATS = enumSetOf4;
        EnumSet enumSetOf5 = EnumSet.of(BarcodeFormat.UPC_A, BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED);
        PRODUCT_FORMATS = enumSetOf5;
        EnumSet enumSetOf6 = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
        INDUSTRIAL_FORMATS = enumSetOf6;
        EnumSet enumSetCopyOf = EnumSet.copyOf((Collection) enumSetOf5);
        ONE_D_FORMATS = enumSetCopyOf;
        enumSetCopyOf.addAll(enumSetOf6);
        HashMap map = new HashMap();
        FORMATS_FOR_MODE = map;
        map.put("ONE_D_MODE", enumSetCopyOf);
        map.put("PRODUCT_MODE", enumSetOf5);
        map.put("QR_CODE_MODE", enumSetOf);
        map.put("DATA_MATRIX_MODE", enumSetOf2);
        map.put("AZTEC_MODE", enumSetOf3);
        map.put("PDF417_MODE", enumSetOf4);
    }

    public static Set parseDecodeFormats(Intent intent) {
        String stringExtra = intent.getStringExtra("SCAN_FORMATS");
        return parseDecodeFormats(stringExtra != null ? Arrays.asList(COMMA_PATTERN.split(stringExtra)) : null, intent.getStringExtra("SCAN_MODE"));
    }

    private static Set parseDecodeFormats(Iterable iterable, String str) {
        if (iterable != null) {
            EnumSet enumSetNoneOf = EnumSet.noneOf(BarcodeFormat.class);
            try {
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    enumSetNoneOf.add(BarcodeFormat.valueOf((String) it.next()));
                }
                return enumSetNoneOf;
            } catch (IllegalArgumentException unused) {
            }
        }
        if (str != null) {
            return (Set) FORMATS_FOR_MODE.get(str);
        }
        return null;
    }
}
