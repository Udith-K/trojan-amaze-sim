package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Util;

/* JADX INFO: loaded from: classes.dex */
public final class UnitBitmapDecoder implements ResourceDecoder {
    @Override // com.bumptech.glide.load.ResourceDecoder
    public boolean handles(Bitmap bitmap, Options options) {
        return true;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public Resource decode(Bitmap bitmap, int i, int i2, Options options) {
        return new NonOwnedBitmapResource(bitmap);
    }

    private static final class NonOwnedBitmapResource implements Resource {
        private final Bitmap bitmap;

        @Override // com.bumptech.glide.load.engine.Resource
        public void recycle() {
        }

        NonOwnedBitmapResource(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        public Class getResourceClass() {
            return Bitmap.class;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        public Bitmap get() {
            return this.bitmap;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        public int getSize() {
            return Util.getBitmapByteSize(this.bitmap);
        }
    }
}
