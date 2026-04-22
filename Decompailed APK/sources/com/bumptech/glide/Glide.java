package com.bumptech.glide;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.resource.bitmap.HardwareConfigState;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.ManifestParser;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Glide implements ComponentCallbacks2 {
    private static volatile Glide glide;
    private static volatile boolean isInitializing;
    private final ArrayPool arrayPool;
    private final BitmapPool bitmapPool;
    private final ConnectivityMonitorFactory connectivityMonitorFactory;
    private final RequestOptionsFactory defaultRequestOptionsFactory;
    private final Engine engine;
    private final GlideContext glideContext;
    private final MemoryCache memoryCache;
    private final RequestManagerRetriever requestManagerRetriever;
    private final List managers = new ArrayList();
    private MemoryCategory memoryCategory = MemoryCategory.NORMAL;

    public interface RequestOptionsFactory {
        RequestOptions build();
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    public static File getPhotoCacheDir(Context context) {
        return getPhotoCacheDir(context, "image_manager_disk_cache");
    }

    public static File getPhotoCacheDir(Context context, String str) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File file = new File(cacheDir, str);
            if (file.isDirectory() || file.mkdirs()) {
                return file;
            }
            return null;
        }
        if (Log.isLoggable("Glide", 6)) {
            Log.e("Glide", "default disk cache dir is null");
        }
        return null;
    }

    public static Glide get(Context context) {
        if (glide == null) {
            GeneratedAppGlideModule annotationGeneratedGlideModules = getAnnotationGeneratedGlideModules(context.getApplicationContext());
            synchronized (Glide.class) {
                try {
                    if (glide == null) {
                        checkAndInitializeGlide(context, annotationGeneratedGlideModules);
                    }
                } finally {
                }
            }
        }
        return glide;
    }

    static void checkAndInitializeGlide(Context context, GeneratedAppGlideModule generatedAppGlideModule) {
        if (isInitializing) {
            throw new IllegalStateException("Glide has been called recursively, this is probably an internal library error!");
        }
        isInitializing = true;
        try {
            initializeGlide(context, generatedAppGlideModule);
        } finally {
            isInitializing = false;
        }
    }

    public static synchronized void init(Glide glide2) {
        try {
            if (glide != null) {
                tearDown();
            }
            glide = glide2;
        } catch (Throwable th) {
            throw th;
        }
    }

    public static void init(Context context, GlideBuilder glideBuilder) {
        GeneratedAppGlideModule annotationGeneratedGlideModules = getAnnotationGeneratedGlideModules(context);
        synchronized (Glide.class) {
            try {
                if (glide != null) {
                    tearDown();
                }
                initializeGlide(context, glideBuilder, annotationGeneratedGlideModules);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static synchronized boolean isInitialized() {
        return glide != null;
    }

    public static void enableHardwareBitmaps() {
        HardwareConfigState.getInstance().unblockHardwareBitmaps();
    }

    public static void tearDown() {
        synchronized (Glide.class) {
            try {
                if (glide != null) {
                    glide.getContext().getApplicationContext().unregisterComponentCallbacks(glide);
                    glide.engine.shutdown();
                }
                glide = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static void initializeGlide(Context context, GeneratedAppGlideModule generatedAppGlideModule) {
        initializeGlide(context, new GlideBuilder(), generatedAppGlideModule);
    }

    private static void initializeGlide(Context context, GlideBuilder glideBuilder, GeneratedAppGlideModule generatedAppGlideModule) {
        Context applicationContext = context.getApplicationContext();
        List listEmptyList = Collections.emptyList();
        if (generatedAppGlideModule == null || generatedAppGlideModule.isManifestParsingEnabled()) {
            listEmptyList = new ManifestParser(applicationContext).parse();
        }
        if (generatedAppGlideModule != null && !generatedAppGlideModule.getExcludedModuleClasses().isEmpty()) {
            generatedAppGlideModule.getExcludedModuleClasses();
            Iterator it = listEmptyList.iterator();
            if (it.hasNext()) {
                ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
                throw null;
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            Iterator it2 = listEmptyList.iterator();
            if (it2.hasNext()) {
                ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it2.next());
                new StringBuilder().append("Discovered GlideModule from manifest: ");
                throw null;
            }
        }
        glideBuilder.setRequestManagerFactory(generatedAppGlideModule != null ? generatedAppGlideModule.getRequestManagerFactory() : null);
        Iterator it3 = listEmptyList.iterator();
        if (it3.hasNext()) {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it3.next());
            throw null;
        }
        if (generatedAppGlideModule != null) {
            generatedAppGlideModule.applyOptions(applicationContext, glideBuilder);
        }
        Glide glideBuild = glideBuilder.build(applicationContext, listEmptyList, generatedAppGlideModule);
        applicationContext.registerComponentCallbacks(glideBuild);
        glide = glideBuild;
    }

    private static GeneratedAppGlideModule getAnnotationGeneratedGlideModules(Context context) {
        try {
            return (GeneratedAppGlideModule) GeneratedAppGlideModuleImpl.class.getDeclaredConstructor(Context.class).newInstance(context.getApplicationContext());
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable("Glide", 5)) {
                Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
            return null;
        } catch (IllegalAccessException e) {
            throwIncorrectGlideModule(e);
            return null;
        } catch (InstantiationException e2) {
            throwIncorrectGlideModule(e2);
            return null;
        } catch (NoSuchMethodException e3) {
            throwIncorrectGlideModule(e3);
            return null;
        } catch (InvocationTargetException e4) {
            throwIncorrectGlideModule(e4);
            return null;
        }
    }

    private static void throwIncorrectGlideModule(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    Glide(Context context, Engine engine, MemoryCache memoryCache, BitmapPool bitmapPool, ArrayPool arrayPool, RequestManagerRetriever requestManagerRetriever, ConnectivityMonitorFactory connectivityMonitorFactory, int i, RequestOptionsFactory requestOptionsFactory, Map map, List list, List list2, AppGlideModule appGlideModule, GlideExperiments glideExperiments) {
        this.engine = engine;
        this.bitmapPool = bitmapPool;
        this.arrayPool = arrayPool;
        this.memoryCache = memoryCache;
        this.requestManagerRetriever = requestManagerRetriever;
        this.connectivityMonitorFactory = connectivityMonitorFactory;
        this.defaultRequestOptionsFactory = requestOptionsFactory;
        this.glideContext = new GlideContext(context, arrayPool, RegistryFactory.lazilyCreateAndInitializeRegistry(this, list2, appGlideModule), new ImageViewTargetFactory(), requestOptionsFactory, map, list, engine, glideExperiments, i);
    }

    public BitmapPool getBitmapPool() {
        return this.bitmapPool;
    }

    public ArrayPool getArrayPool() {
        return this.arrayPool;
    }

    public Context getContext() {
        return this.glideContext.getBaseContext();
    }

    ConnectivityMonitorFactory getConnectivityMonitorFactory() {
        return this.connectivityMonitorFactory;
    }

    GlideContext getGlideContext() {
        return this.glideContext;
    }

    public void clearMemory() {
        Util.assertMainThread();
        this.memoryCache.clearMemory();
        this.bitmapPool.clearMemory();
        this.arrayPool.clearMemory();
    }

    public void trimMemory(int i) {
        Util.assertMainThread();
        synchronized (this.managers) {
            try {
                Iterator it = this.managers.iterator();
                while (it.hasNext()) {
                    ((RequestManager) it.next()).onTrimMemory(i);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.memoryCache.trimMemory(i);
        this.bitmapPool.trimMemory(i);
        this.arrayPool.trimMemory(i);
    }

    public void clearDiskCache() {
        Util.assertBackgroundThread();
        this.engine.clearDiskCache();
    }

    public RequestManagerRetriever getRequestManagerRetriever() {
        return this.requestManagerRetriever;
    }

    private static RequestManagerRetriever getRetriever(Context context) {
        Preconditions.checkNotNull(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return get(context).getRequestManagerRetriever();
    }

    public static RequestManager with(Context context) {
        return getRetriever(context).get(context);
    }

    public static RequestManager with(Activity activity) {
        return with(activity.getApplicationContext());
    }

    public static RequestManager with(FragmentActivity fragmentActivity) {
        return getRetriever(fragmentActivity).get(fragmentActivity);
    }

    public static RequestManager with(Fragment fragment) {
        return getRetriever(fragment.getContext()).get(fragment);
    }

    public static RequestManager with(android.app.Fragment fragment) {
        Activity activity = fragment.getActivity();
        Preconditions.checkNotNull(activity, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return with(activity.getApplicationContext());
    }

    public static RequestManager with(View view) {
        return getRetriever(view.getContext()).get(view);
    }

    public Registry getRegistry() {
        return this.glideContext.getRegistry();
    }

    boolean removeFromManagers(Target target) {
        synchronized (this.managers) {
            try {
                Iterator it = this.managers.iterator();
                while (it.hasNext()) {
                    if (((RequestManager) it.next()).untrack(target)) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void registerRequestManager(RequestManager requestManager) {
        synchronized (this.managers) {
            try {
                if (this.managers.contains(requestManager)) {
                    throw new IllegalStateException("Cannot register already registered manager");
                }
                this.managers.add(requestManager);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void unregisterRequestManager(RequestManager requestManager) {
        synchronized (this.managers) {
            try {
                if (!this.managers.contains(requestManager)) {
                    throw new IllegalStateException("Cannot unregister not yet registered manager");
                }
                this.managers.remove(requestManager);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        trimMemory(i);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        clearMemory();
    }
}
