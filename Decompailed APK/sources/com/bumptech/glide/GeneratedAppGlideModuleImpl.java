package com.bumptech.glide;

import android.content.Context;
import android.util.Log;
import java.util.Collections;
import java.util.Set;
import org.fdroid.fdroid.net.FDroidGlideModule;

/* JADX INFO: loaded from: classes.dex */
final class GeneratedAppGlideModuleImpl extends GeneratedAppGlideModule {
    private final FDroidGlideModule appGlideModule = new FDroidGlideModule();

    public GeneratedAppGlideModuleImpl(Context context) {
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Discovered AppGlideModule from annotation: org.fdroid.fdroid.net.FDroidGlideModule");
        }
    }

    @Override // com.bumptech.glide.module.AppGlideModule
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        this.appGlideModule.applyOptions(context, glideBuilder);
    }

    @Override // com.bumptech.glide.module.LibraryGlideModule
    public void registerComponents(Context context, Glide glide, Registry registry) {
        this.appGlideModule.registerComponents(context, glide, registry);
    }

    @Override // com.bumptech.glide.module.AppGlideModule
    public boolean isManifestParsingEnabled() {
        return this.appGlideModule.isManifestParsingEnabled();
    }

    @Override // com.bumptech.glide.GeneratedAppGlideModule
    public Set getExcludedModuleClasses() {
        return Collections.emptySet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bumptech.glide.GeneratedAppGlideModule
    public GeneratedRequestManagerFactory getRequestManagerFactory() {
        return new GeneratedRequestManagerFactory();
    }
}
