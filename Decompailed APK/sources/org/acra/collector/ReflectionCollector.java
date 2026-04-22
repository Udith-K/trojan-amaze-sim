package org.acra.collector;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ReflectionCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\u00052\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0012J\u001c\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0012¨\u0006\u0017"}, d2 = {"Lorg/acra/collector/ReflectionCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "<init>", "()V", "collect", "", "reportField", "Lorg/acra/ReportField;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "target", "Lorg/acra/data/CrashReportData;", "collectStaticGettersResults", "someClass", "Ljava/lang/Class;", "container", "Lorg/json/JSONObject;", "getBuildConfigClass", "Companion", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class ReflectionCollector extends BaseReportFieldCollector {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: ReflectionCollector.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReportField.values().length];
            try {
                iArr[ReportField.BUILD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportField.BUILD_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReportField.ENVIRONMENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public ReflectionCollector() {
        super(ReportField.BUILD, ReportField.BUILD_CONFIG, ReportField.ENVIRONMENT);
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) throws JSONException, ClassNotFoundException {
        Intrinsics.checkNotNullParameter(reportField, "reportField");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(target, "target");
        JSONObject jSONObject = new JSONObject();
        int i = WhenMappings.$EnumSwitchMapping$0[reportField.ordinal()];
        if (i == 1) {
            Companion companion = INSTANCE;
            companion.collectConstants(Build.class, jSONObject, CollectionsKt.listOf("SERIAL"));
            JSONObject jSONObject2 = new JSONObject();
            Companion.collectConstants$default(companion, Build.VERSION.class, jSONObject2, null, 4, null);
            jSONObject.put("VERSION", jSONObject2);
        } else if (i == 2) {
            Companion.collectConstants$default(INSTANCE, getBuildConfigClass(context, config), jSONObject, null, 4, null);
        } else if (i == 3) {
            collectStaticGettersResults(Environment.class, jSONObject);
        } else {
            throw new IllegalArgumentException();
        }
        target.put(reportField, jSONObject);
    }

    private void collectStaticGettersResults(Class<?> someClass, JSONObject container) throws JSONException {
        Iterator it = ArrayIteratorKt.iterator(someClass.getMethods());
        while (it.hasNext()) {
            Method method = (Method) it.next();
            Class<?>[] parameterTypes = method.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(parameterTypes, "getParameterTypes(...)");
            if (parameterTypes.length == 0) {
                String name = method.getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                if (!StringsKt.startsWith$default(name, "get", false, 2, (Object) null)) {
                    String name2 = method.getName();
                    Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                    if (StringsKt.startsWith$default(name2, "is", false, 2, (Object) null)) {
                    }
                }
                if (!Intrinsics.areEqual("getClass", method.getName())) {
                    try {
                        container.put(method.getName(), method.invoke(null, null));
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
                    }
                }
            }
        }
    }

    private Class<?> getBuildConfigClass(Context context, CoreConfiguration config) throws ClassNotFoundException {
        Class<?> buildConfigClass = config.getBuildConfigClass();
        if (buildConfigClass != null) {
            return buildConfigClass;
        }
        Class<?> cls = Class.forName(context.getPackageName() + ".BuildConfig");
        Intrinsics.checkNotNullExpressionValue(cls, "forName(...)");
        return cls;
    }

    /* JADX INFO: compiled from: ReflectionCollector.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        static /* synthetic */ void collectConstants$default(Companion companion, Class cls, JSONObject jSONObject, Collection collection, int i, Object obj) {
            if ((i & 4) != 0) {
                collection = CollectionsKt.emptyList();
            }
            companion.collectConstants(cls, jSONObject, collection);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void collectConstants(Class cls, JSONObject jSONObject, Collection collection) {
            Iterator it = ArrayIteratorKt.iterator(cls.getFields());
            while (it.hasNext()) {
                Field field = (Field) it.next();
                if (!collection.contains(field.getName())) {
                    try {
                        Object obj = field.get(null);
                        if (obj != null) {
                            if (field.getType().isArray()) {
                                Object[] objArr = (Object[]) obj;
                                jSONObject.put(field.getName(), new JSONArray((Collection) CollectionsKt.listOf(Arrays.copyOf(objArr, objArr.length))));
                            } else {
                                jSONObject.put(field.getName(), obj);
                            }
                        }
                    } catch (IllegalAccessException | IllegalArgumentException unused) {
                    }
                }
            }
        }
    }
}
