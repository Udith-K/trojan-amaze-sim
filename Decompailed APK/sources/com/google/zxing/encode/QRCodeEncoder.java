package com.google.zxing.encode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import ch.qos.logback.core.CoreConstants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import java.util.EnumMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class QRCodeEncoder {
    private String contents;
    private final int dimension;
    private String displayContents;
    private final boolean encoded;
    private BarcodeFormat format;
    private String title;

    public QRCodeEncoder(String str, Bundle bundle, String str2, String str3, int i) {
        this.dimension = i;
        this.encoded = encodeContents(str, bundle, str2, str3);
    }

    private boolean encodeContents(String str, Bundle bundle, String str2, String str3) {
        this.format = null;
        if (str3 != null) {
            try {
                this.format = BarcodeFormat.valueOf(str3);
            } catch (IllegalArgumentException unused) {
            }
        }
        BarcodeFormat barcodeFormat = this.format;
        if (barcodeFormat == null || barcodeFormat == BarcodeFormat.QR_CODE) {
            this.format = BarcodeFormat.QR_CODE;
            encodeQRCodeContents(str, bundle, str2);
        } else if (str != null && str.length() > 0) {
            this.contents = str;
            this.displayContents = str;
            this.title = "Text";
        }
        String str4 = this.contents;
        return str4 != null && str4.length() > 0;
    }

    private void encodeQRCodeContents(String str, Bundle bundle, String str2) {
        int i;
        i = 0;
        str2.hashCode();
        switch (str2) {
            case "PHONE_TYPE":
                String strTrim = trim(str);
                if (strTrim != null) {
                    this.contents = "tel:" + strTrim;
                    this.displayContents = PhoneNumberUtils.formatNumber(strTrim);
                    this.title = "Phone";
                    break;
                }
                break;
            case "CONTACT_TYPE":
                if (bundle != null) {
                    StringBuilder sb = new StringBuilder(100);
                    StringBuilder sb2 = new StringBuilder(100);
                    sb.append("MECARD:");
                    String strTrim2 = trim(bundle.getString("name"));
                    if (strTrim2 != null) {
                        sb.append("N:");
                        sb.append(escapeMECARD(strTrim2));
                        sb.append(';');
                        sb2.append(strTrim2);
                    }
                    String strTrim3 = trim(bundle.getString("postal"));
                    if (strTrim3 != null) {
                        sb.append("ADR:");
                        sb.append(escapeMECARD(strTrim3));
                        sb.append(';');
                        sb2.append('\n');
                        sb2.append(strTrim3);
                    }
                    HashSet<String> hashSet = new HashSet(Contents.PHONE_KEYS.length);
                    int i2 = 0;
                    while (true) {
                        String[] strArr = Contents.PHONE_KEYS;
                        if (i2 < strArr.length) {
                            String strTrim4 = trim(bundle.getString(strArr[i2]));
                            if (strTrim4 != null) {
                                hashSet.add(strTrim4);
                            }
                            i2++;
                        } else {
                            for (String str3 : hashSet) {
                                sb.append("TEL:");
                                sb.append(escapeMECARD(str3));
                                sb.append(';');
                                sb2.append('\n');
                                sb2.append(PhoneNumberUtils.formatNumber(str3));
                            }
                            HashSet<String> hashSet2 = new HashSet(Contents.EMAIL_KEYS.length);
                            while (true) {
                                String[] strArr2 = Contents.EMAIL_KEYS;
                                if (i < strArr2.length) {
                                    String strTrim5 = trim(bundle.getString(strArr2[i]));
                                    if (strTrim5 != null) {
                                        hashSet2.add(strTrim5);
                                    }
                                    i++;
                                } else {
                                    for (String str4 : hashSet2) {
                                        sb.append("EMAIL:");
                                        sb.append(escapeMECARD(str4));
                                        sb.append(';');
                                        sb2.append('\n');
                                        sb2.append(str4);
                                    }
                                    String strTrim6 = trim(bundle.getString("URL_KEY"));
                                    if (strTrim6 != null) {
                                        sb.append("URL:");
                                        sb.append(strTrim6);
                                        sb.append(';');
                                        sb2.append('\n');
                                        sb2.append(strTrim6);
                                    }
                                    String strTrim7 = trim(bundle.getString("NOTE_KEY"));
                                    if (strTrim7 != null) {
                                        sb.append("NOTE:");
                                        sb.append(escapeMECARD(strTrim7));
                                        sb.append(';');
                                        sb2.append('\n');
                                        sb2.append(strTrim7);
                                    }
                                    if (sb2.length() > 0) {
                                        sb.append(';');
                                        this.contents = sb.toString();
                                        this.displayContents = sb2.toString();
                                        this.title = "Contact";
                                    } else {
                                        this.contents = null;
                                        this.displayContents = null;
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
                break;
            case "SMS_TYPE":
                String strTrim8 = trim(str);
                if (strTrim8 != null) {
                    this.contents = "sms:" + strTrim8;
                    this.displayContents = PhoneNumberUtils.formatNumber(strTrim8);
                    this.title = "SMS";
                    break;
                }
                break;
            case "LOCATION_TYPE":
                if (bundle != null) {
                    float f = bundle.getFloat("LAT", Float.MAX_VALUE);
                    float f2 = bundle.getFloat("LONG", Float.MAX_VALUE);
                    if (f != Float.MAX_VALUE && f2 != Float.MAX_VALUE) {
                        this.contents = "geo:" + f + CoreConstants.COMMA_CHAR + f2;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(f);
                        sb3.append(",");
                        sb3.append(f2);
                        this.displayContents = sb3.toString();
                        this.title = "Location";
                        break;
                    }
                }
                break;
            case "TEXT_TYPE":
                if (str != null && str.length() > 0) {
                    this.contents = str;
                    this.displayContents = str;
                    this.title = "Text";
                    break;
                }
                break;
            case "EMAIL_TYPE":
                String strTrim9 = trim(str);
                if (strTrim9 != null) {
                    this.contents = "mailto:" + strTrim9;
                    this.displayContents = strTrim9;
                    this.title = "E-Mail";
                    break;
                }
                break;
        }
    }

    public Bitmap encodeAsBitmap() {
        EnumMap enumMap = null;
        if (!this.encoded) {
            return null;
        }
        String strGuessAppropriateEncoding = guessAppropriateEncoding(this.contents);
        if (strGuessAppropriateEncoding != null) {
            enumMap = new EnumMap(EncodeHintType.class);
            enumMap.put(EncodeHintType.CHARACTER_SET, strGuessAppropriateEncoding);
        }
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        String str = this.contents;
        BarcodeFormat barcodeFormat = this.format;
        int i = this.dimension;
        BitMatrix bitMatrixEncode = multiFormatWriter.encode(str, barcodeFormat, i, i, enumMap);
        int width = bitMatrixEncode.getWidth();
        int height = bitMatrixEncode.getHeight();
        int[] iArr = new int[width * height];
        for (int i2 = 0; i2 < height; i2++) {
            int i3 = i2 * width;
            for (int i4 = 0; i4 < width; i4++) {
                iArr[i3 + i4] = bitMatrixEncode.get(i4, i2) ? -16777216 : -1;
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }

    private static String guessAppropriateEncoding(CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (charSequence.charAt(i) > 255) {
                return "UTF-8";
            }
        }
        return null;
    }

    private static String trim(String str) {
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return null;
        }
        return strTrim;
    }

    private static String escapeMECARD(String str) {
        if (str == null) {
            return str;
        }
        if (str.indexOf(58) < 0 && str.indexOf(59) < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == ':' || cCharAt == ';') {
                sb.append(CoreConstants.ESCAPE_CHAR);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }
}
