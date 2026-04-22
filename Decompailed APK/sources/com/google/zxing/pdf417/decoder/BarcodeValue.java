package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class BarcodeValue {
    private final Map values = new HashMap();

    BarcodeValue() {
    }

    void setValue(int i) {
        Integer num = (Integer) this.values.get(Integer.valueOf(i));
        if (num == null) {
            num = 0;
        }
        this.values.put(Integer.valueOf(i), Integer.valueOf(num.intValue() + 1));
    }

    int[] getValue() {
        ArrayList arrayList = new ArrayList();
        int iIntValue = -1;
        for (Map.Entry entry : this.values.entrySet()) {
            if (((Integer) entry.getValue()).intValue() > iIntValue) {
                iIntValue = ((Integer) entry.getValue()).intValue();
                arrayList.clear();
                arrayList.add(entry.getKey());
            } else if (((Integer) entry.getValue()).intValue() == iIntValue) {
                arrayList.add(entry.getKey());
            }
        }
        return PDF417Common.toIntArray(arrayList);
    }
}
