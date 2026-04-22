package com.google.zxing.oned.rss.expanded.decoders;

import ch.qos.logback.core.CoreConstants;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

/* JADX INFO: loaded from: classes.dex */
final class AI01393xDecoder extends AI01decoder {
    AI01393xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    @Override // com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder
    public String parseInformation() throws NotFoundException {
        if (getInformation().getSize() < 48) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder sb = new StringBuilder();
        encodeCompressedGtin(sb, 8);
        int iExtractNumericValueFromBitArray = getGeneralDecoder().extractNumericValueFromBitArray(48, 2);
        sb.append("(393");
        sb.append(iExtractNumericValueFromBitArray);
        sb.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
        int iExtractNumericValueFromBitArray2 = getGeneralDecoder().extractNumericValueFromBitArray(50, 10);
        if (iExtractNumericValueFromBitArray2 / 100 == 0) {
            sb.append('0');
        }
        if (iExtractNumericValueFromBitArray2 / 10 == 0) {
            sb.append('0');
        }
        sb.append(iExtractNumericValueFromBitArray2);
        sb.append(getGeneralDecoder().decodeGeneralPurposeField(60, null).getNewString());
        return sb.toString();
    }
}
