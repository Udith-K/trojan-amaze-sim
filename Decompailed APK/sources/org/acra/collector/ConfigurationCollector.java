package org.acra.collector;

import android.content.Context;
import android.content.res.Configuration;
import android.util.SparseArray;
import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.collector.ConfigurationCollector;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;
import org.bouncycastle.i18n.TextBundle;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ConfigurationCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0017\u0018\u0000 '2\u00020\u00012\u00020\u0002:\u0002&'B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J0\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0016H\u0012J\u001a\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u0018H\u0012J2\u0010\u001c\u001a\u00020\u001d2\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u00182\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 H\u0012J\u001e\u0010!\u001a\u00020\u001b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010#\u001a\u00020$H\u0012J\u0012\u0010%\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0012R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0092\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lorg/acra/collector/ConfigurationCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "Lorg/acra/collector/ApplicationStartupCollector;", "<init>", "()V", "initialConfiguration", "Lorg/json/JSONObject;", "collect", "", "reportField", "Lorg/acra/ReportField;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "target", "Lorg/acra/data/CrashReportData;", "collectApplicationStartUp", "configToJson", "conf", "Landroid/content/res/Configuration;", "getValueArrays", "", "Lorg/acra/collector/ConfigurationCollector$Prefix;", "Landroid/util/SparseArray;", "", "getFieldValueName", "", "valueArrays", "f", "Ljava/lang/reflect/Field;", "activeFlags", "valueNames", "bitfield", "", "collectConfiguration", "Prefix", "Companion", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class ConfigurationCollector extends BaseReportFieldCollector implements ApplicationStartupCollector {
    private static final String FIELD_MCC = "mcc";
    private static final String FIELD_MNC = "mnc";
    private static final String FIELD_SCREENLAYOUT = "screenLayout";
    private static final String FIELD_UIMODE = "uiMode";
    private static final String SUFFIX_MASK = "_MASK";
    private JSONObject initialConfiguration;

    /* JADX INFO: compiled from: ConfigurationCollector.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReportField.values().length];
            try {
                iArr[ReportField.INITIAL_CONFIGURATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportField.CRASH_CONFIGURATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public ConfigurationCollector() {
        super(ReportField.INITIAL_CONFIGURATION, ReportField.CRASH_CONFIGURATION);
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) {
        Intrinsics.checkNotNullParameter(reportField, "reportField");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(target, "target");
        int i = WhenMappings.$EnumSwitchMapping$0[reportField.ordinal()];
        if (i == 1) {
            target.put(ReportField.INITIAL_CONFIGURATION, this.initialConfiguration);
        } else {
            if (i == 2) {
                target.put(ReportField.CRASH_CONFIGURATION, collectConfiguration(context));
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    @Override // org.acra.collector.ApplicationStartupCollector
    public void collectApplicationStartUp(Context context, CoreConfiguration config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        if (config.getReportContent().contains(ReportField.INITIAL_CONFIGURATION)) {
            this.initialConfiguration = collectConfiguration(context);
        }
    }

    private JSONObject configToJson(Configuration conf) {
        JSONObject jSONObject = new JSONObject();
        Map<Prefix, SparseArray<String>> valueArrays = getValueArrays();
        Iterator it = ArrayIteratorKt.iterator(conf.getClass().getFields());
        while (it.hasNext()) {
            Field field = (Field) it.next();
            try {
                if (!Modifier.isStatic(field.getModifiers())) {
                    String name = field.getName();
                    try {
                        if (Intrinsics.areEqual(field.getType(), Integer.TYPE)) {
                            Intrinsics.checkNotNull(field);
                            jSONObject.put(name, getFieldValueName(valueArrays, conf, field));
                        } else if (field.get(conf) != null) {
                            jSONObject.put(name, field.get(conf));
                        }
                        Unit unit = Unit.INSTANCE;
                    } catch (JSONException e) {
                        ACRA.log.w(ACRA.LOG_TAG, "Could not collect configuration field " + name, e);
                        Unit unit2 = Unit.INSTANCE;
                    }
                }
            } catch (IllegalAccessException e2) {
                ACRA.log.e(ACRA.LOG_TAG, "Error while inspecting device configuration: ", e2);
            } catch (IllegalArgumentException e3) {
                ACRA.log.e(ACRA.LOG_TAG, "Error while inspecting device configuration: ", e3);
            }
        }
        return jSONObject;
    }

    private Map<Prefix, SparseArray<String>> getValueArrays() {
        Pair pair;
        Field[] fields = Configuration.class.getFields();
        Intrinsics.checkNotNullExpressionValue(fields, "getFields(...)");
        ArrayList arrayList = new ArrayList();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
                arrayList.add(field);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = arrayList.iterator();
        while (true) {
            Prefix prefix = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            Field field2 = (Field) next;
            Prefix[] prefixArrValues = Prefix.values();
            int length = prefixArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Prefix prefix2 = prefixArrValues[i];
                String name = field2.getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                if (StringsKt.startsWith$default(name, prefix2.getText(), false, 2, (Object) null)) {
                    prefix = prefix2;
                    break;
                }
                i++;
            }
            Object arrayList2 = linkedHashMap.get(prefix);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap.put(prefix, arrayList2);
            }
            ((List) arrayList2).add(next);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (((Prefix) entry.getKey()) != null) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList arrayList3 = new ArrayList(linkedHashMap2.size());
        for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
            Prefix prefix3 = (Prefix) entry2.getKey();
            List<Field> list = (List) entry2.getValue();
            Intrinsics.checkNotNull(prefix3);
            SparseArray sparseArray = new SparseArray();
            for (Field field3 : list) {
                try {
                    pair = TuplesKt.to(Integer.valueOf(field3.getInt(null)), field3.getName());
                } catch (IllegalAccessException e) {
                    ACRA.log.w(ACRA.LOG_TAG, "Error while inspecting device configuration: ", e);
                    pair = null;
                } catch (IllegalArgumentException e2) {
                    ACRA.log.w(ACRA.LOG_TAG, "Error while inspecting device configuration: ", e2);
                    pair = null;
                }
                if (pair != null) {
                    sparseArray.put(((Number) pair.component1()).intValue(), pair.component2());
                }
            }
            arrayList3.add(TuplesKt.to(prefix3, sparseArray));
        }
        return MapsKt.withDefaultMutable(MapsKt.toMap(arrayList3, new EnumMap(Prefix.class)), new Function1() { // from class: org.acra.collector.ConfigurationCollector$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ConfigurationCollector.getValueArrays$lambda$11((ConfigurationCollector.Prefix) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SparseArray getValueArrays$lambda$11(Prefix prefix) {
        return new SparseArray();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
    
        if (r0.equals(org.acra.collector.ConfigurationCollector.FIELD_MCC) != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
    
        if (r0.equals(org.acra.collector.ConfigurationCollector.FIELD_MNC) == false) goto L21;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.Object getFieldValueName(java.util.Map<org.acra.collector.ConfigurationCollector.Prefix, ? extends android.util.SparseArray<java.lang.String>> r9, android.content.res.Configuration r10, java.lang.reflect.Field r11) throws java.lang.IllegalAccessException {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.collector.ConfigurationCollector.getFieldValueName(java.util.Map, android.content.res.Configuration, java.lang.reflect.Field):java.lang.Object");
    }

    private String activeFlags(SparseArray<String> valueNames, int bitfield) {
        int i;
        StringBuilder sb = new StringBuilder();
        int size = valueNames.size();
        for (int i2 = 0; i2 < size; i2++) {
            int iKeyAt = valueNames.keyAt(i2);
            String str = valueNames.get(iKeyAt);
            Intrinsics.checkNotNullExpressionValue(str, "get(...)");
            if (StringsKt.endsWith$default(str, SUFFIX_MASK, false, 2, (Object) null) && (i = iKeyAt & bitfield) > 0) {
                if (sb.length() > 0) {
                    sb.append('+');
                }
                sb.append(valueNames.get(i));
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private JSONObject collectConfiguration(Context context) {
        try {
            Configuration configuration = context.getResources().getConfiguration();
            Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
            return configToJson(configuration);
        } catch (RuntimeException e) {
            ACRA.log.w(ACRA.LOG_TAG, "Couldn't retrieve CrashConfiguration for : " + context.getPackageName(), e);
            return null;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ConfigurationCollector.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lorg/acra/collector/ConfigurationCollector$Prefix;", "", TextBundle.TEXT_ENTRY, "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getText", "()Ljava/lang/String;", "UI_MODE", "TOUCHSCREEN", "SCREENLAYOUT", "ORIENTATION", "NAVIGATIONHIDDEN", "NAVIGATION", "KEYBOARDHIDDEN", "KEYBOARD", "HARDKEYBOARDHIDDEN", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Prefix {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Prefix[] $VALUES;
        private final String text;
        public static final Prefix UI_MODE = new Prefix("UI_MODE", 0, "UI_MODE_");
        public static final Prefix TOUCHSCREEN = new Prefix("TOUCHSCREEN", 1, "TOUCHSCREEN_");
        public static final Prefix SCREENLAYOUT = new Prefix("SCREENLAYOUT", 2, "SCREENLAYOUT_");
        public static final Prefix ORIENTATION = new Prefix("ORIENTATION", 3, "ORIENTATION_");
        public static final Prefix NAVIGATIONHIDDEN = new Prefix("NAVIGATIONHIDDEN", 4, "NAVIGATIONHIDDEN_");
        public static final Prefix NAVIGATION = new Prefix("NAVIGATION", 5, "NAVIGATION_");
        public static final Prefix KEYBOARDHIDDEN = new Prefix("KEYBOARDHIDDEN", 6, "KEYBOARDHIDDEN_");
        public static final Prefix KEYBOARD = new Prefix("KEYBOARD", 7, "KEYBOARD_");
        public static final Prefix HARDKEYBOARDHIDDEN = new Prefix("HARDKEYBOARDHIDDEN", 8, "HARDKEYBOARDHIDDEN_");

        private static final /* synthetic */ Prefix[] $values() {
            return new Prefix[]{UI_MODE, TOUCHSCREEN, SCREENLAYOUT, ORIENTATION, NAVIGATIONHIDDEN, NAVIGATION, KEYBOARDHIDDEN, KEYBOARD, HARDKEYBOARDHIDDEN};
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        private Prefix(String str, int i, String str2) {
            this.text = str2;
        }

        public final String getText() {
            return this.text;
        }

        static {
            Prefix[] prefixArr$values = $values();
            $VALUES = prefixArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(prefixArr$values);
        }

        public static Prefix valueOf(String str) {
            return (Prefix) Enum.valueOf(Prefix.class, str);
        }

        public static Prefix[] values() {
            return (Prefix[]) $VALUES.clone();
        }
    }
}
