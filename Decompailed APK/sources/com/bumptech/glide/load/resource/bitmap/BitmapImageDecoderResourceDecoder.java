package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.util.Log;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.resource.DefaultOnHeaderDecodedListener;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class BitmapImageDecoderResourceDecoder implements ResourceDecoder {
    private final BitmapPool bitmapPool = new BitmapPoolAdapter();

    public boolean handles(ImageDecoder.Source source, Options options) {
        return true;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public /* bridge */ /* synthetic */ Resource decode(Object obj, int i, int i2, Options options) {
        return decode(BitmapImageDecoderResourceDecoder$$ExternalSyntheticApiModelOutline0.m(obj), i, i2, options);
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public /* bridge */ /* synthetic */ boolean handles(Object obj, Options options) {
        return handles(BitmapImageDecoderResourceDecoder$$ExternalSyntheticApiModelOutline0.m(obj), options);
    }

    public Resource decode(ImageDecoder.Source source, int i, int i2, Options options) throws IOException {
        Bitmap bitmapDecodeBitmap = ImageDecoder.decodeBitmap(source, new DefaultOnHeaderDecodedListener(i, i2, options));
        if (Log.isLoggable("BitmapImageDecoder", 2)) {
            Log.v("BitmapImageDecoder", "Decoded [" + bitmapDecodeBitmap.getWidth() + "x" + bitmapDecodeBitmap.getHeight() + "] for [" + i + "x" + i2 + "]");
        }
        return new BitmapResource(bitmapDecodeBitmap, this.bitmapPool);
    }
}
