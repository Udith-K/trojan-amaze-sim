package com.google.zxing;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface Reader {
    Result decode(BinaryBitmap binaryBitmap);

    Result decode(BinaryBitmap binaryBitmap, Map map);

    void reset();
}
