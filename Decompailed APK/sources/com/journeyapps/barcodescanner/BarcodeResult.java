package com.journeyapps.barcodescanner;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BarcodeResult {
    protected Result mResult;
    private final int mScaleFactor = 2;
    protected SourceData sourceData;

    public BarcodeResult(Result result, SourceData sourceData) {
        this.mResult = result;
        this.sourceData = sourceData;
    }

    public Bitmap getBitmap() {
        return this.sourceData.getBitmap(null, 2);
    }

    public byte[] getRawBytes() {
        return this.mResult.getRawBytes();
    }

    public BarcodeFormat getBarcodeFormat() {
        return this.mResult.getBarcodeFormat();
    }

    public Map getResultMetadata() {
        return this.mResult.getResultMetadata();
    }

    public String toString() {
        return this.mResult.getText();
    }

    public static List transformResultPoints(List list, SourceData sourceData) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(sourceData.translateResultPoint((ResultPoint) it.next()));
        }
        return arrayList;
    }
}
