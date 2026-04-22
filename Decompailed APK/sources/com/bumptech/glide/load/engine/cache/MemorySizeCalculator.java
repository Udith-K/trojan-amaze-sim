package com.bumptech.glide.load.engine.cache;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public final class MemorySizeCalculator {
    private final int arrayPoolSize;
    private final int bitmapPoolSize;
    private final Context context;
    private final int memoryCacheSize;

    interface ScreenDimensions {
        int getHeightPixels();

        int getWidthPixels();
    }

    MemorySizeCalculator(Builder builder) {
        int i;
        this.context = builder.context;
        if (isLowMemoryDevice(builder.activityManager)) {
            i = builder.arrayPoolSizeBytes / 2;
        } else {
            i = builder.arrayPoolSizeBytes;
        }
        this.arrayPoolSize = i;
        int maxSize = getMaxSize(builder.activityManager, builder.maxSizeMultiplier, builder.lowMemoryMaxSizeMultiplier);
        float widthPixels = builder.screenDimensions.getWidthPixels() * builder.screenDimensions.getHeightPixels() * 4;
        int iRound = Math.round(builder.bitmapPoolScreens * widthPixels);
        int iRound2 = Math.round(widthPixels * builder.memoryCacheScreens);
        int i2 = maxSize - i;
        int i3 = iRound2 + iRound;
        if (i3 <= i2) {
            this.memoryCacheSize = iRound2;
            this.bitmapPoolSize = iRound;
        } else {
            float f = i2;
            float f2 = builder.bitmapPoolScreens;
            float f3 = builder.memoryCacheScreens;
            float f4 = f / (f2 + f3);
            this.memoryCacheSize = Math.round(f3 * f4);
            this.bitmapPoolSize = Math.round(f4 * builder.bitmapPoolScreens);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calculation complete, Calculated memory cache size: ");
            sb.append(toMb(this.memoryCacheSize));
            sb.append(", pool size: ");
            sb.append(toMb(this.bitmapPoolSize));
            sb.append(", byte array size: ");
            sb.append(toMb(i));
            sb.append(", memory class limited? ");
            sb.append(i3 > maxSize);
            sb.append(", max size: ");
            sb.append(toMb(maxSize));
            sb.append(", memoryClass: ");
            sb.append(builder.activityManager.getMemoryClass());
            sb.append(", isLowMemoryDevice: ");
            sb.append(isLowMemoryDevice(builder.activityManager));
            Log.d("MemorySizeCalculator", sb.toString());
        }
    }

    public int getMemoryCacheSize() {
        return this.memoryCacheSize;
    }

    public int getBitmapPoolSize() {
        return this.bitmapPoolSize;
    }

    public int getArrayPoolSizeInBytes() {
        return this.arrayPoolSize;
    }

    private static int getMaxSize(ActivityManager activityManager, float f, float f2) {
        float memoryClass = activityManager.getMemoryClass() * PKIFailureInfo.badCertTemplate;
        if (isLowMemoryDevice(activityManager)) {
            f = f2;
        }
        return Math.round(memoryClass * f);
    }

    private String toMb(int i) {
        return Formatter.formatFileSize(this.context, i);
    }

    static boolean isLowMemoryDevice(ActivityManager activityManager) {
        return activityManager.isLowRamDevice();
    }

    public static final class Builder {
        static final int BITMAP_POOL_TARGET_SCREENS;
        ActivityManager activityManager;
        float bitmapPoolScreens;
        final Context context;
        ScreenDimensions screenDimensions;
        float memoryCacheScreens = 2.0f;
        float maxSizeMultiplier = 0.4f;
        float lowMemoryMaxSizeMultiplier = 0.33f;
        int arrayPoolSizeBytes = 4194304;

        static {
            BITMAP_POOL_TARGET_SCREENS = Build.VERSION.SDK_INT < 26 ? 4 : 1;
        }

        public Builder(Context context) {
            this.bitmapPoolScreens = BITMAP_POOL_TARGET_SCREENS;
            this.context = context;
            this.activityManager = (ActivityManager) context.getSystemService("activity");
            this.screenDimensions = new DisplayMetricsScreenDimensions(context.getResources().getDisplayMetrics());
            if (Build.VERSION.SDK_INT < 26 || !MemorySizeCalculator.isLowMemoryDevice(this.activityManager)) {
                return;
            }
            this.bitmapPoolScreens = 0.0f;
        }

        public MemorySizeCalculator build() {
            return new MemorySizeCalculator(this);
        }
    }

    private static final class DisplayMetricsScreenDimensions implements ScreenDimensions {
        private final DisplayMetrics displayMetrics;

        DisplayMetricsScreenDimensions(DisplayMetrics displayMetrics) {
            this.displayMetrics = displayMetrics;
        }

        @Override // com.bumptech.glide.load.engine.cache.MemorySizeCalculator.ScreenDimensions
        public int getWidthPixels() {
            return this.displayMetrics.widthPixels;
        }

        @Override // com.bumptech.glide.load.engine.cache.MemorySizeCalculator.ScreenDimensions
        public int getHeightPixels() {
            return this.displayMetrics.heightPixels;
        }
    }
}
