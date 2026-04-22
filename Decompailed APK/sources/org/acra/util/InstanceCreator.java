package org.acra.util;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;

/* JADX INFO: compiled from: InstanceCreator.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class InstanceCreator {
    public static final InstanceCreator INSTANCE = new InstanceCreator();

    private InstanceCreator() {
    }

    public static final Object create(Class clazz, Function0 fallback) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(fallback, "fallback");
        Object objCreate = create(clazz);
        return objCreate == null ? fallback.invoke() : objCreate;
    }

    public static final Object create(Class clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException e) {
            ACRA.log.e(ACRA.LOG_TAG, "Failed to create instance of class " + clazz.getName(), e);
            return null;
        } catch (InstantiationException e2) {
            ACRA.log.e(ACRA.LOG_TAG, "Failed to create instance of class " + clazz.getName(), e2);
            return null;
        }
    }
}
