package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

/* JADX INFO: loaded from: classes.dex */
final class AnyAIDecoder extends AbstractExpandedDecoder {
    AnyAIDecoder(BitArray bitArray) {
        super(bitArray);
    }

    @Override // com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder
    public String parseInformation() {
        return getGeneralDecoder().decodeAllCodes(new StringBuilder(), 5);
    }
}
