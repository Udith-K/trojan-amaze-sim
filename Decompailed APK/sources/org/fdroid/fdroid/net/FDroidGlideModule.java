package org.fdroid.fdroid.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import java.io.InputStream;
import org.fdroid.download.DownloadRequest;
import org.fdroid.download.glide.DownloadRequestLoader;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: loaded from: classes2.dex */
public class FDroidGlideModule extends AppGlideModule {
    @Override // com.bumptech.glide.module.AppGlideModule
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        glideBuilder.setDefaultTransitionOptions(Drawable.class, DrawableTransitionOptions.withCrossFade()).setDefaultTransitionOptions(Bitmap.class, BitmapTransitionOptions.withCrossFade()).setLogLevel(5).setDefaultRequestOptions((RequestOptions) ((RequestOptions) new RequestOptions().format(DecodeFormat.PREFER_RGB_565)).onlyRetrieveFromCache(!Preferences.get().isBackgroundDownloadAllowed()));
    }

    @Override // com.bumptech.glide.module.LibraryGlideModule
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.append(DownloadRequest.class, InputStream.class, new DownloadRequestLoader.Factory(DownloaderFactory.HTTP_MANAGER));
    }
}
