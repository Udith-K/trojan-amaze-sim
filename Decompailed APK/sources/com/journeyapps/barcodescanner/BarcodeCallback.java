package com.journeyapps.barcodescanner;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface BarcodeCallback {
    void barcodeResult(BarcodeResult barcodeResult);

    void possibleResultPoints(List list);
}
