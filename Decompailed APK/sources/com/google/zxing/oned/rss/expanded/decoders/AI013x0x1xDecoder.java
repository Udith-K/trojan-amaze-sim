package com.google.zxing.oned.rss.expanded.decoders;

import ch.qos.logback.core.CoreConstants;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

/* JADX INFO: loaded from: classes.dex */
final class AI013x0x1xDecoder extends AI01weightDecoder {
    private final String dateCode;
    private final String firstAIdigits;

    AI013x0x1xDecoder(BitArray bitArray, String str, String str2) {
        super(bitArray);
        this.dateCode = str2;
        this.firstAIdigits = str;
    }

    @Override // com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder
    public String parseInformation() throws NotFoundException {
        if (getInformation().getSize() != 84) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder sb = new StringBuilder();
        encodeCompressedGtin(sb, 8);
        encodeCompressedWeight(sb, 48, 20);
        encodeCompressedDate(sb, 68);
        return sb.toString();
    }

    private void encodeCompressedDate(StringBuilder sb, int i) {
        int iExtractNumericValueFromBitArray = getGeneralDecoder().extractNumericValueFromBitArray(i, 16);
        if (iExtractNumericValueFromBitArray == 38400) {
            return;
        }
        sb.append(CoreConstants.LEFT_PARENTHESIS_CHAR);
        sb.append(this.dateCode);
        sb.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
        int i2 = iExtractNumericValueFromBitArray % 32;
        int i3 = iExtractNumericValueFromBitArray / 32;
        int i4 = (i3 % 12) + 1;
        int i5 = i3 / 12;
        if (i5 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i5);
        if (i4 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i4);
        if (i2 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i2);
    }

    @Override // com.google.zxing.oned.rss.expanded.decoders.AI01weightDecoder
    protected void addWeightCode(StringBuilder sb, int i) {
        sb.append(CoreConstants.LEFT_PARENTHESIS_CHAR);
        sb.append(this.firstAIdigits);
        sb.append(i / 100000);
        sb.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
    }

    @Override // com.google.zxing.oned.rss.expanded.decoders.AI01weightDecoder
    protected int checkWeight(int i) {
        return i % 100000;
    }
}
