package org.acra;

import android.app.Application;
import android.content.SharedPreferences;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.acra.config.CoreConfiguration;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.log.ACRALog;
import org.acra.log.AndroidLogDelegate;
import org.acra.prefs.SharedPreferencesFactory;
import org.acra.reporter.ErrorReporterImpl;
import org.acra.util.StreamReader;
import org.acra.util.StubCreator;

/* JADX INFO: compiled from: ACRA.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ACRA {
    public static boolean DEV_LOGGING;
    public static final ACRA INSTANCE = new ACRA();
    public static final String LOG_TAG;
    private static ErrorReporter errorReporter;
    public static ACRALog log;

    public static final void init(Application app, CoreConfigurationBuilder builder) {
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(builder, "builder");
        init$default(app, builder, false, 4, null);
    }

    private ACRA() {
    }

    static {
        String simpleName = ACRA.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        LOG_TAG = simpleName;
        log = new AndroidLogDelegate();
        errorReporter = StubCreator.INSTANCE.createErrorReporterStub();
    }

    public static final ErrorReporter getErrorReporter() {
        return errorReporter;
    }

    public static /* synthetic */ void init$default(Application application, CoreConfigurationBuilder coreConfigurationBuilder, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            coreConfigurationBuilder = new CoreConfigurationBuilder();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        init(application, coreConfigurationBuilder, z);
    }

    public static final void init(Application app, CoreConfigurationBuilder builder, boolean z) {
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(builder, "builder");
        init(app, builder.build(), z);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final void init(Application app, CoreConfiguration config, boolean z) {
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(config, "config");
        boolean zIsACRASenderServiceProcess = isACRASenderServiceProcess();
        if (zIsACRASenderServiceProcess && DEV_LOGGING) {
            log.d(LOG_TAG, "Not initialising ACRA to listen for uncaught Exceptions as this is the SendWorker process and we only send reports, we don't capture them to avoid infinite loops");
        }
        if (INSTANCE.isInitialised()) {
            ACRALog aCRALog = log;
            String str = LOG_TAG;
            aCRALog.w(str, "ACRA#init called more than once. This might have unexpected side effects. Doing this outside of tests is discouraged.");
            if (DEV_LOGGING) {
                log.d(str, "Removing old ACRA config...");
            }
            ErrorReporter errorReporter2 = errorReporter;
            Intrinsics.checkNotNull(errorReporter2, "null cannot be cast to non-null type org.acra.reporter.ErrorReporterImpl");
            ((ErrorReporterImpl) errorReporter2).unregister();
            errorReporter = StubCreator.INSTANCE.createErrorReporterStub();
        }
        SharedPreferences sharedPreferencesCreate = new SharedPreferencesFactory(app, config).create();
        if (zIsACRASenderServiceProcess) {
            return;
        }
        boolean zShouldEnableACRA = SharedPreferencesFactory.Companion.shouldEnableACRA(sharedPreferencesCreate);
        ACRALog aCRALog2 = log;
        String str2 = LOG_TAG;
        String str3 = zShouldEnableACRA ? "enabled" : "disabled";
        aCRALog2.i(str2, "ACRA is " + str3 + " for " + app.getPackageName() + ", initializing...");
        ErrorReporterImpl errorReporterImpl = new ErrorReporterImpl(app, config, zShouldEnableACRA, true, z);
        errorReporter = errorReporterImpl;
        sharedPreferencesCreate.registerOnSharedPreferenceChangeListener(errorReporterImpl);
    }

    public final boolean isInitialised() {
        return errorReporter instanceof ErrorReporterImpl;
    }

    public static final boolean isACRASenderServiceProcess() {
        String strCurrentProcessName = INSTANCE.currentProcessName();
        if (DEV_LOGGING) {
            log.d(LOG_TAG, "ACRA processName='" + strCurrentProcessName + "'");
        }
        return strCurrentProcessName != null && StringsKt.endsWith$default(strCurrentProcessName, ":acra", false, 2, (Object) null);
    }

    private final String currentProcessName() {
        try {
            String str = new StreamReader("/proc/self/cmdline").read();
            int length = str.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    }
                    length--;
                } else if (z2) {
                    i++;
                } else {
                    z = true;
                }
            }
            return str.subSequence(i, length + 1).toString();
        } catch (IOException unused) {
            return null;
        }
    }
}
