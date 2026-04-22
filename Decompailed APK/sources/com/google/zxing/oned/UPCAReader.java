package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.FormatException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class UPCAReader extends UPCEANReader {
    private final UPCEANReader ean13Reader = new EAN13Reader();

    @Override // com.google.zxing.oned.UPCEANReader
    public Result decodeRow(int i, BitArray bitArray, int[] iArr, Map map) {
        return maybeReturnResult(this.ean13Reader.decodeRow(i, bitArray, iArr, map));
    }

    @Override // com.google.zxing.oned.UPCEANReader, com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map map) {
        return maybeReturnResult(this.ean13Reader.decodeRow(i, bitArray, map));
    }

    @Override // com.google.zxing.oned.OneDReader, com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) {
        return maybeReturnResult(this.ean13Reader.decode(binaryBitmap));
    }

    @Override // com.google.zxing.oned.OneDReader, com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Map map) {
        return maybeReturnResult(this.ean13Reader.decode(binaryBitmap, map));
    }

    @Override // com.google.zxing.oned.UPCEANReader
    BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.UPC_A;
    }

    @Override // com.google.zxing.oned.UPCEANReader
    protected int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder sb) {
        return this.ean13Reader.decodeMiddle(bitArray, iArr, sb);
    }

    private static Result maybeReturnResult(Result result) throws FormatException {
        String text = result.getText();
        if (text.charAt(0) == '0') {
            Result result2 = new Result(text.substring(1), null, result.getResultPoints(), BarcodeFormat.UPC_A);
            if (result.getResultMetadata() != null) {
                result2.putAllMetadata(result.getResultMetadata());
            }
            return result2;
        }
        throw FormatException.getFormatInstance();
    }
}
