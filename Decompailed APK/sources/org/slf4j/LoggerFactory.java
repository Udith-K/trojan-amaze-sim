package org.slf4j;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.event.SubstituteLoggingEvent;
import org.slf4j.helpers.NOP_FallbackServiceProvider;
import org.slf4j.helpers.Reporter;
import org.slf4j.helpers.SubstituteLogger;
import org.slf4j.helpers.SubstituteServiceProvider;
import org.slf4j.helpers.Util;
import org.slf4j.spi.SLF4JServiceProvider;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LoggerFactory {
    static volatile int INITIALIZATION_STATE;
    static volatile SLF4JServiceProvider PROVIDER;
    static final SubstituteServiceProvider SUBST_PROVIDER = new SubstituteServiceProvider();
    static final NOP_FallbackServiceProvider NOP_FALLBACK_SERVICE_PROVIDER = new NOP_FallbackServiceProvider();
    static boolean DETECT_LOGGER_NAME_MISMATCH = Util.safeGetBooleanSystemProperty("slf4j.detectLoggerNameMismatch");
    private static final String[] API_COMPATIBILITY_LIST = {"2.0"};

    static List findServiceProviders() {
        ArrayList arrayList = new ArrayList();
        ClassLoader classLoader = LoggerFactory.class.getClassLoader();
        SLF4JServiceProvider sLF4JServiceProviderLoadExplicitlySpecified = loadExplicitlySpecified(classLoader);
        if (sLF4JServiceProviderLoadExplicitlySpecified != null) {
            arrayList.add(sLF4JServiceProviderLoadExplicitlySpecified);
            return arrayList;
        }
        Iterator it = getServiceLoader(classLoader).iterator();
        while (it.hasNext()) {
            safelyInstantiate(arrayList, it);
        }
        return arrayList;
    }

    private static ServiceLoader getServiceLoader(final ClassLoader classLoader) {
        if (System.getSecurityManager() == null) {
            return ServiceLoader.load(SLF4JServiceProvider.class, classLoader);
        }
        return (ServiceLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.slf4j.LoggerFactory$$ExternalSyntheticLambda0
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return LoggerFactory.lambda$getServiceLoader$0(classLoader);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ServiceLoader lambda$getServiceLoader$0(ClassLoader classLoader) {
        return ServiceLoader.load(SLF4JServiceProvider.class, classLoader);
    }

    private static void safelyInstantiate(List list, Iterator it) {
        try {
            list.add((SLF4JServiceProvider) it.next());
        } catch (ServiceConfigurationError e) {
            Reporter.error("A service provider failed to instantiate:\n" + e.getMessage());
        }
    }

    private static final void performInitialization() {
        bind();
        if (INITIALIZATION_STATE == 3) {
            versionSanityCheck();
        }
    }

    private static final void bind() {
        try {
            List listFindServiceProviders = findServiceProviders();
            reportMultipleBindingAmbiguity(listFindServiceProviders);
            if (listFindServiceProviders != null && !listFindServiceProviders.isEmpty()) {
                PROVIDER = (SLF4JServiceProvider) listFindServiceProviders.get(0);
                PROVIDER.initialize();
                INITIALIZATION_STATE = 3;
                reportActualBinding(listFindServiceProviders);
            } else {
                INITIALIZATION_STATE = 4;
                Reporter.warn("No SLF4J providers were found.");
                Reporter.warn("Defaulting to no-operation (NOP) logger implementation");
                Reporter.warn("See https://www.slf4j.org/codes.html#noProviders for further details.");
                reportIgnoredStaticLoggerBinders(findPossibleStaticLoggerBinderPathSet());
            }
            postBindCleanUp();
        } catch (Exception e) {
            failedBinding(e);
            throw new IllegalStateException("Unexpected initialization failure", e);
        }
    }

    static SLF4JServiceProvider loadExplicitlySpecified(ClassLoader classLoader) {
        String property = System.getProperty("slf4j.provider");
        if (property != null && !property.isEmpty()) {
            try {
                Reporter.info(String.format("Attempting to load provider \"%s\" specified via \"%s\" system property", property, "slf4j.provider"));
                return (SLF4JServiceProvider) classLoader.loadClass(property).getConstructor(null).newInstance(null);
            } catch (ClassCastException e) {
                Reporter.error(String.format("Specified SLF4JServiceProvider (%s) does not implement SLF4JServiceProvider interface", property), e);
                return null;
            } catch (ClassNotFoundException e2) {
                e = e2;
                Reporter.error(String.format("Failed to instantiate the specified SLF4JServiceProvider (%s)", property), e);
                return null;
            } catch (IllegalAccessException e3) {
                e = e3;
                Reporter.error(String.format("Failed to instantiate the specified SLF4JServiceProvider (%s)", property), e);
                return null;
            } catch (InstantiationException e4) {
                e = e4;
                Reporter.error(String.format("Failed to instantiate the specified SLF4JServiceProvider (%s)", property), e);
                return null;
            } catch (NoSuchMethodException e5) {
                e = e5;
                Reporter.error(String.format("Failed to instantiate the specified SLF4JServiceProvider (%s)", property), e);
                return null;
            } catch (InvocationTargetException e6) {
                e = e6;
                Reporter.error(String.format("Failed to instantiate the specified SLF4JServiceProvider (%s)", property), e);
                return null;
            }
        }
        return null;
    }

    private static void reportIgnoredStaticLoggerBinders(Set set) {
        if (set.isEmpty()) {
            return;
        }
        Reporter.warn("Class path contains SLF4J bindings targeting slf4j-api versions 1.7.x or earlier.");
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Reporter.warn("Ignoring binding found at [" + ((URL) it.next()) + "]");
        }
        Reporter.warn("See https://www.slf4j.org/codes.html#ignoredBindings for an explanation.");
    }

    static Set findPossibleStaticLoggerBinderPathSet() {
        Enumeration<URL> resources;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            ClassLoader classLoader = LoggerFactory.class.getClassLoader();
            if (classLoader == null) {
                resources = ClassLoader.getSystemResources("org/slf4j/impl/StaticLoggerBinder.class");
            } else {
                resources = classLoader.getResources("org/slf4j/impl/StaticLoggerBinder.class");
            }
            while (resources.hasMoreElements()) {
                linkedHashSet.add(resources.nextElement());
            }
        } catch (IOException e) {
            Reporter.error("Error getting resources from path", e);
        }
        return linkedHashSet;
    }

    private static void postBindCleanUp() {
        fixSubstituteLoggers();
        replayEvents();
        SUBST_PROVIDER.getSubstituteLoggerFactory().clear();
    }

    private static void fixSubstituteLoggers() {
        SubstituteServiceProvider substituteServiceProvider = SUBST_PROVIDER;
        synchronized (substituteServiceProvider) {
            try {
                substituteServiceProvider.getSubstituteLoggerFactory().postInitialization();
                for (SubstituteLogger substituteLogger : substituteServiceProvider.getSubstituteLoggerFactory().getLoggers()) {
                    substituteLogger.setDelegate(getLogger(substituteLogger.getName()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static void failedBinding(Throwable th) {
        INITIALIZATION_STATE = 2;
        Reporter.error("Failed to instantiate SLF4J LoggerFactory", th);
    }

    private static void replayEvents() {
        LinkedBlockingQueue eventQueue = SUBST_PROVIDER.getSubstituteLoggerFactory().getEventQueue();
        int size = eventQueue.size();
        ArrayList<SubstituteLoggingEvent> arrayList = new ArrayList(128);
        int i = 0;
        while (eventQueue.drainTo(arrayList, 128) != 0) {
            for (SubstituteLoggingEvent substituteLoggingEvent : arrayList) {
                replaySingleEvent(substituteLoggingEvent);
                int i2 = i + 1;
                if (i == 0) {
                    emitReplayOrSubstituionWarning(substituteLoggingEvent, size);
                }
                i = i2;
            }
            arrayList.clear();
        }
    }

    private static void emitReplayOrSubstituionWarning(SubstituteLoggingEvent substituteLoggingEvent, int i) {
        if (substituteLoggingEvent.getLogger().isDelegateEventAware()) {
            emitReplayWarning(i);
        } else {
            if (substituteLoggingEvent.getLogger().isDelegateNOP()) {
                return;
            }
            emitSubstitutionWarning();
        }
    }

    private static void replaySingleEvent(SubstituteLoggingEvent substituteLoggingEvent) {
        if (substituteLoggingEvent == null) {
            return;
        }
        SubstituteLogger logger = substituteLoggingEvent.getLogger();
        String name = logger.getName();
        if (logger.isDelegateNull()) {
            throw new IllegalStateException("Delegate logger cannot be null at this state.");
        }
        if (logger.isDelegateNOP()) {
            return;
        }
        if (logger.isDelegateEventAware()) {
            if (logger.isEnabledForLevel(substituteLoggingEvent.getLevel())) {
                logger.log(substituteLoggingEvent);
                return;
            }
            return;
        }
        Reporter.warn(name);
    }

    private static void emitSubstitutionWarning() {
        Reporter.warn("The following set of substitute loggers may have been accessed");
        Reporter.warn("during the initialization phase. Logging calls during this");
        Reporter.warn("phase were not honored. However, subsequent logging calls to these");
        Reporter.warn("loggers will work as normally expected.");
        Reporter.warn("See also https://www.slf4j.org/codes.html#substituteLogger");
    }

    private static void emitReplayWarning(int i) {
        Reporter.warn("A number (" + i + ") of logging calls during the initialization phase have been intercepted and are");
        Reporter.warn("now being replayed. These are subject to the filtering rules of the underlying logging system.");
        Reporter.warn("See also https://www.slf4j.org/codes.html#replay");
    }

    private static final void versionSanityCheck() {
        try {
            String requestedApiVersion = PROVIDER.getRequestedApiVersion();
            boolean z = false;
            for (String str : API_COMPATIBILITY_LIST) {
                if (requestedApiVersion.startsWith(str)) {
                    z = true;
                }
            }
            if (z) {
                return;
            }
            Reporter.warn("The requested version " + requestedApiVersion + " by your slf4j provider is not compatible with " + Arrays.asList(API_COMPATIBILITY_LIST).toString());
            Reporter.warn("See https://www.slf4j.org/codes.html#version_mismatch for further details.");
        } catch (Throwable th) {
            Reporter.error("Unexpected problem occurred during version sanity check", th);
        }
    }

    private static boolean isAmbiguousProviderList(List list) {
        return list.size() > 1;
    }

    private static void reportMultipleBindingAmbiguity(List list) {
        if (isAmbiguousProviderList(list)) {
            Reporter.warn("Class path contains multiple SLF4J providers.");
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Reporter.warn("Found provider [" + ((SLF4JServiceProvider) it.next()) + "]");
            }
            Reporter.warn("See https://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }

    private static void reportActualBinding(List list) {
        if (list.isEmpty()) {
            throw new IllegalStateException("No providers were found which is impossible after successful initialization.");
        }
        if (isAmbiguousProviderList(list)) {
            Reporter.info("Actual provider is of type [" + list.get(0) + "]");
            return;
        }
        Reporter.debug("Connected with provider of type [" + ((SLF4JServiceProvider) list.get(0)).getClass().getName() + "]");
    }

    public static Logger getLogger(String str) {
        return getILoggerFactory().getLogger(str);
    }

    public static Logger getLogger(Class cls) {
        Class callingClass;
        Logger logger = getLogger(cls.getName());
        if (DETECT_LOGGER_NAME_MISMATCH && (callingClass = Util.getCallingClass()) != null && nonMatchingClasses(cls, callingClass)) {
            Reporter.warn(String.format("Detected logger name mismatch. Given name: \"%s\"; computed name: \"%s\".", logger.getName(), callingClass.getName()));
            Reporter.warn("See https://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
        }
        return logger;
    }

    private static boolean nonMatchingClasses(Class cls, Class cls2) {
        return !cls2.isAssignableFrom(cls);
    }

    public static ILoggerFactory getILoggerFactory() {
        return getProvider().getLoggerFactory();
    }

    static SLF4JServiceProvider getProvider() {
        if (INITIALIZATION_STATE == 0) {
            synchronized (LoggerFactory.class) {
                try {
                    if (INITIALIZATION_STATE == 0) {
                        INITIALIZATION_STATE = 1;
                        performInitialization();
                    }
                } finally {
                }
            }
        }
        int i = INITIALIZATION_STATE;
        if (i == 1) {
            return SUBST_PROVIDER;
        }
        if (i == 2) {
            throw new IllegalStateException("org.slf4j.LoggerFactory in failed state. Original exception was thrown EARLIER. See also https://www.slf4j.org/codes.html#unsuccessfulInit");
        }
        if (i == 3) {
            return PROVIDER;
        }
        if (i == 4) {
            return NOP_FALLBACK_SERVICE_PROVIDER;
        }
        throw new IllegalStateException("Unreachable code");
    }
}
