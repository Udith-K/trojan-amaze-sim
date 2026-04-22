package com.bumptech.glide.module;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ManifestParser {
    private final Context context;

    public ManifestParser(Context context) {
        this.context = context;
    }

    private ApplicationInfo getOurApplicationInfo() {
        return this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
    }

    public List parse() {
        ApplicationInfo ourApplicationInfo;
        if (Log.isLoggable("ManifestParser", 3)) {
            Log.d("ManifestParser", "Loading Glide modules");
        }
        ArrayList arrayList = new ArrayList();
        try {
            ourApplicationInfo = getOurApplicationInfo();
        } catch (PackageManager.NameNotFoundException e) {
            if (Log.isLoggable("ManifestParser", 6)) {
                Log.e("ManifestParser", "Failed to parse glide modules", e);
            }
        }
        if (ourApplicationInfo != null && ourApplicationInfo.metaData != null) {
            if (Log.isLoggable("ManifestParser", 2)) {
                Log.v("ManifestParser", "Got app info metadata: " + ourApplicationInfo.metaData);
            }
            for (String str : ourApplicationInfo.metaData.keySet()) {
                if ("GlideModule".equals(ourApplicationInfo.metaData.get(str))) {
                    parseModule(str);
                    arrayList.add(null);
                    if (Log.isLoggable("ManifestParser", 3)) {
                        Log.d("ManifestParser", "Loaded Glide module: " + str);
                    }
                }
            }
            if (Log.isLoggable("ManifestParser", 3)) {
                Log.d("ManifestParser", "Finished loading Glide modules");
            }
            return arrayList;
        }
        if (Log.isLoggable("ManifestParser", 3)) {
            Log.d("ManifestParser", "Got null app info metadata");
        }
        return arrayList;
    }

    private static GlideModule parseModule(String str) {
        try {
            Class<?> cls = Class.forName(str);
            Object objNewInstance = null;
            try {
                objNewInstance = cls.getDeclaredConstructor(null).newInstance(null);
            } catch (IllegalAccessException e) {
                throwInstantiateGlideModuleException(cls, e);
            } catch (InstantiationException e2) {
                throwInstantiateGlideModuleException(cls, e2);
            } catch (NoSuchMethodException e3) {
                throwInstantiateGlideModuleException(cls, e3);
            } catch (InvocationTargetException e4) {
                throwInstantiateGlideModuleException(cls, e4);
            }
            throw new RuntimeException("Expected instanceof GlideModule, but found: " + objNewInstance);
        } catch (ClassNotFoundException e5) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e5);
        }
    }

    private static void throwInstantiateGlideModuleException(Class cls, Exception exc) {
        throw new RuntimeException("Unable to instantiate GlideModule implementation for " + cls, exc);
    }
}
