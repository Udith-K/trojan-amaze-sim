package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.data.InputStreamRewinder;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.ByteBufferUtil;
import com.bumptech.glide.util.Preconditions;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
interface ImageReader {
    Bitmap decodeBitmap(BitmapFactory.Options options);

    int getImageOrientation();

    ImageHeaderParser.ImageType getImageType();

    void stopGrowingBuffers();

    public static final class ByteBufferReader implements ImageReader {
        private final ByteBuffer buffer;
        private final ArrayPool byteArrayPool;
        private final List parsers;

        @Override // com.bumptech.glide.load.resource.bitmap.ImageReader
        public void stopGrowingBuffers() {
        }

        ByteBufferReader(ByteBuffer byteBuffer, List list, ArrayPool arrayPool) {
            this.buffer = byteBuffer;
            this.parsers = list;
            this.byteArrayPool = arrayPool;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.ImageReader
        public Bitmap decodeBitmap(BitmapFactory.Options options) {
            return BitmapFactory.decodeStream(stream(), null, options);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.ImageReader
        public ImageHeaderParser.ImageType getImageType() {
            return ImageHeaderParserUtils.getType(this.parsers, ByteBufferUtil.rewind(this.buffer));
        }

        @Override // com.bumptech.glide.load.resource.bitmap.ImageReader
        public int getImageOrientation() {
            return ImageHeaderParserUtils.getOrientation(this.parsers, ByteBufferUtil.rewind(this.buffer), this.byteArrayPool);
        }

        private InputStream stream() {
            return ByteBufferUtil.toStream(ByteBufferUtil.rewind(this.buffer));
        }
    }

    public static final class InputStreamImageReader implements ImageReader {
        private final ArrayPool byteArrayPool;
        private final InputStreamRewinder dataRewinder;
        private final List parsers;

        InputStreamImageReader(InputStream inputStream, List list, ArrayPool arrayPool) {
            this.byteArrayPool = (ArrayPool) Preconditions.checkNotNull(arrayPool);
            this.parsers = (List) Preconditions.checkNotNull(list);
            this.dataRewinder = new InputStreamRewinder(inputStream, arrayPool);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.ImageReader
        public Bitmap decodeBitmap(BitmapFactory.Options options) {
            return BitmapFactory.decodeStream(this.dataRewinder.rewindAndGet(), null, options);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.ImageReader
        public ImageHeaderParser.ImageType getImageType() {
            return ImageHeaderParserUtils.getType(this.parsers, this.dataRewinder.rewindAndGet(), this.byteArrayPool);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.ImageReader
        public int getImageOrientation() {
            return ImageHeaderParserUtils.getOrientation(this.parsers, this.dataRewinder.rewindAndGet(), this.byteArrayPool);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.ImageReader
        public void stopGrowingBuffers() {
            this.dataRewinder.fixMarkLimits();
        }
    }

    public static final class ParcelFileDescriptorImageReader implements ImageReader {
        private final ArrayPool byteArrayPool;
        private final ParcelFileDescriptorRewinder dataRewinder;
        private final List parsers;

        @Override // com.bumptech.glide.load.resource.bitmap.ImageReader
        public void stopGrowingBuffers() {
        }

        ParcelFileDescriptorImageReader(ParcelFileDescriptor parcelFileDescriptor, List list, ArrayPool arrayPool) {
            this.byteArrayPool = (ArrayPool) Preconditions.checkNotNull(arrayPool);
            this.parsers = (List) Preconditions.checkNotNull(list);
            this.dataRewinder = new ParcelFileDescriptorRewinder(parcelFileDescriptor);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.ImageReader
        public Bitmap decodeBitmap(BitmapFactory.Options options) {
            return BitmapFactory.decodeFileDescriptor(this.dataRewinder.rewindAndGet().getFileDescriptor(), null, options);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.ImageReader
        public ImageHeaderParser.ImageType getImageType() {
            return ImageHeaderParserUtils.getType(this.parsers, this.dataRewinder, this.byteArrayPool);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.ImageReader
        public int getImageOrientation() {
            return ImageHeaderParserUtils.getOrientation(this.parsers, this.dataRewinder, this.byteArrayPool);
        }
    }
}
