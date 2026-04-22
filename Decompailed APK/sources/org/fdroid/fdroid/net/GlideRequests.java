package org.fdroid.fdroid.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class GlideRequests extends RequestManager {
    public GlideRequests(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context) {
        super(glide, lifecycle, requestManagerTreeNode, context);
    }

    @Override // com.bumptech.glide.RequestManager
    public <ResourceType> GlideRequest<ResourceType> as(Class<ResourceType> cls) {
        return new GlideRequest<>(this.glide, this, cls, this.context);
    }

    @Override // com.bumptech.glide.RequestManager
    public synchronized GlideRequests applyDefaultRequestOptions(RequestOptions requestOptions) {
        return (GlideRequests) super.applyDefaultRequestOptions(requestOptions);
    }

    @Override // com.bumptech.glide.RequestManager
    public synchronized GlideRequests setDefaultRequestOptions(RequestOptions requestOptions) {
        return (GlideRequests) super.setDefaultRequestOptions(requestOptions);
    }

    @Override // com.bumptech.glide.RequestManager
    public synchronized GlideRequests clearOnStop() {
        return (GlideRequests) super.clearOnStop();
    }

    @Override // com.bumptech.glide.RequestManager
    public GlideRequests addDefaultRequestListener(RequestListener requestListener) {
        return (GlideRequests) super.addDefaultRequestListener(requestListener);
    }

    @Override // com.bumptech.glide.RequestManager
    public GlideRequest<Bitmap> asBitmap() {
        return (GlideRequest) super.asBitmap();
    }

    @Override // com.bumptech.glide.RequestManager
    public GlideRequest<GifDrawable> asGif() {
        return (GlideRequest) super.asGif();
    }

    @Override // com.bumptech.glide.RequestManager
    public GlideRequest<Drawable> asDrawable() {
        return (GlideRequest) super.asDrawable();
    }

    @Override // com.bumptech.glide.RequestManager
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public GlideRequest<Drawable> m3055load(Bitmap bitmap) {
        return (GlideRequest) super.m3055load(bitmap);
    }

    @Override // com.bumptech.glide.RequestManager
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public GlideRequest<Drawable> m3056load(Drawable drawable) {
        return (GlideRequest) super.m3056load(drawable);
    }

    @Override // com.bumptech.glide.RequestManager
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public GlideRequest<Drawable> m3061load(String str) {
        return (GlideRequest) super.m3061load(str);
    }

    @Override // com.bumptech.glide.RequestManager
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public GlideRequest<Drawable> m3057load(Uri uri) {
        return (GlideRequest) super.m3057load(uri);
    }

    @Override // com.bumptech.glide.RequestManager
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public GlideRequest<Drawable> m3058load(File file) {
        return (GlideRequest) super.m3058load(file);
    }

    @Override // com.bumptech.glide.RequestManager
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public GlideRequest<Drawable> m3059load(Integer num) {
        return (GlideRequest) super.m3059load(num);
    }

    @Override // com.bumptech.glide.RequestManager
    @Deprecated
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public GlideRequest<Drawable> m3062load(URL url) {
        return (GlideRequest) super.m3062load(url);
    }

    @Override // com.bumptech.glide.RequestManager
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public GlideRequest<Drawable> m3063load(byte[] bArr) {
        return (GlideRequest) super.m3063load(bArr);
    }

    @Override // com.bumptech.glide.RequestManager
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public GlideRequest<Drawable> m3060load(Object obj) {
        return (GlideRequest) super.m3060load(obj);
    }

    @Override // com.bumptech.glide.RequestManager
    public GlideRequest<File> downloadOnly() {
        return (GlideRequest) super.downloadOnly();
    }

    @Override // com.bumptech.glide.RequestManager
    public GlideRequest<File> download(Object obj) {
        return (GlideRequest) super.download(obj);
    }

    @Override // com.bumptech.glide.RequestManager
    public GlideRequest<File> asFile() {
        return (GlideRequest) super.asFile();
    }

    @Override // com.bumptech.glide.RequestManager
    protected void setRequestOptions(RequestOptions requestOptions) {
        if (requestOptions instanceof GlideOptions) {
            super.setRequestOptions(requestOptions);
        } else {
            super.setRequestOptions(new GlideOptions().apply((BaseRequestOptions) requestOptions));
        }
    }
}
