package org.acra.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.acra.config.CoreConfiguration;

/* JADX INFO: compiled from: SharedPreferencesFactory.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class SharedPreferencesFactory {
    public static final Companion Companion = new Companion(null);
    private final CoreConfiguration config;
    private final Context context;

    public SharedPreferencesFactory(Context context, CoreConfiguration config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.context = context;
        this.config = config;
    }

    public final SharedPreferences create() {
        if (this.config.getSharedPreferencesName() != null) {
            SharedPreferences sharedPreferences = this.context.getSharedPreferences(this.config.getSharedPreferencesName(), 0);
            Intrinsics.checkNotNull(sharedPreferences);
            return sharedPreferences;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        Intrinsics.checkNotNull(defaultSharedPreferences);
        return defaultSharedPreferences;
    }

    /* JADX INFO: compiled from: SharedPreferencesFactory.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean shouldEnableACRA(SharedPreferences prefs) {
            Intrinsics.checkNotNullParameter(prefs, "prefs");
            try {
                return prefs.getBoolean("acra.enable", !prefs.getBoolean("acra.disable", false));
            } catch (Exception unused) {
                return true;
            }
        }
    }
}
