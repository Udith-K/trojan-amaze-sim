package org.acra.collector;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DisplayManagerCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001b\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\b\u001a\u00020\tH\u0012¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0012H\u0012J\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0015H\u0012J\u0018\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0015H\u0012J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0012J\u0018\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0015H\u0012J\u0018\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0015H\u0012J\u0018\u0010 \u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0015H\u0012J\u0018\u0010!\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0015H\u0012J\u0018\u0010\"\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0015H\u0012J\u0018\u0010#\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0015H\u0012J\u0018\u0010$\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0015H\u0012J\u0018\u0010%\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0015H\u0012J\u0018\u0010$\u001a\u00020\u00052\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0018\u001a\u00020\u0015H\u0012J\u001e\u0010(\u001a\u00020\u001b2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001b0*2\u0006\u0010+\u001a\u00020\u001dH\u0012¨\u0006,"}, d2 = {"Lorg/acra/collector/DisplayManagerCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "<init>", "()V", "collect", "", "reportField", "Lorg/acra/ReportField;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "target", "Lorg/acra/data/CrashReportData;", "getDisplays", "", "Landroid/view/Display;", "(Landroid/content/Context;)[Landroid/view/Display;", "collectDisplayData", "Lorg/json/JSONObject;", "display", "collectIsValid", "container", "collectRotation", "rotationToString", "", "rotation", "", "collectRectSize", "collectSize", "collectRealSize", "collectCurrentSizeRange", "collectFlags", "collectName", "collectMetrics", "collectRealMetrics", "metrics", "Landroid/util/DisplayMetrics;", "activeFlags", "flagNames", "Landroid/util/SparseArray;", "bitfield", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class DisplayManagerCollector extends BaseReportFieldCollector {
    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public DisplayManagerCollector() {
        super(ReportField.DISPLAY);
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) {
        Intrinsics.checkNotNullParameter(reportField, "reportField");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(target, "target");
        JSONObject jSONObject = new JSONObject();
        for (Display display : getDisplays(context)) {
            try {
                jSONObject.put(String.valueOf(display.getDisplayId()), collectDisplayData(display));
            } catch (JSONException e) {
                ACRA.log.w(ACRA.LOG_TAG, "Failed to collect data for display " + display.getDisplayId(), e);
            }
        }
        target.put(ReportField.DISPLAY, jSONObject);
    }

    private Display[] getDisplays(Context context) {
        Object systemService = context.getSystemService("display");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.display.DisplayManager");
        Display[] displays = ((DisplayManager) systemService).getDisplays();
        Intrinsics.checkNotNull(displays);
        return displays;
    }

    private JSONObject collectDisplayData(Display display) throws JSONException {
        display.getMetrics(new DisplayMetrics());
        JSONObject jSONObject = new JSONObject();
        collectCurrentSizeRange(display, jSONObject);
        collectFlags(display, jSONObject);
        collectMetrics(display, jSONObject);
        collectRealMetrics(display, jSONObject);
        collectName(display, jSONObject);
        collectRealSize(display, jSONObject);
        collectRectSize(display, jSONObject);
        collectSize(display, jSONObject);
        collectRotation(display, jSONObject);
        collectIsValid(display, jSONObject);
        jSONObject.put("orientation", display.getRotation()).put("refreshRate", display.getRefreshRate()).put("height", display.getHeight()).put("width", display.getWidth()).put("pixelFormat", display.getPixelFormat());
        return jSONObject;
    }

    private void collectIsValid(Display display, JSONObject container) throws JSONException {
        container.put("isValid", display.isValid());
    }

    private void collectRotation(Display display, JSONObject container) throws JSONException {
        container.put("rotation", rotationToString(display.getRotation()));
    }

    private String rotationToString(int rotation) {
        if (rotation == 0) {
            return "ROTATION_0";
        }
        if (rotation == 1) {
            return "ROTATION_90";
        }
        if (rotation == 2) {
            return "ROTATION_180";
        }
        if (rotation == 3) {
            return "ROTATION_270";
        }
        return String.valueOf(rotation);
    }

    private void collectRectSize(Display display, JSONObject container) throws JSONException {
        Rect rect = new Rect();
        display.getRectSize(rect);
        container.put("rectSize", new JSONArray((Collection) CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(rect.top), Integer.valueOf(rect.left), Integer.valueOf(rect.width()), Integer.valueOf(rect.height())})));
    }

    private void collectSize(Display display, JSONObject container) throws JSONException {
        Point point = new Point();
        display.getSize(point);
        container.put("size", new JSONArray((Collection) CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(point.x), Integer.valueOf(point.y)})));
    }

    private void collectRealSize(Display display, JSONObject container) throws JSONException {
        Point point = new Point();
        display.getRealSize(point);
        container.put("realSize", new JSONArray((Collection) CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(point.x), Integer.valueOf(point.y)})));
    }

    private void collectCurrentSizeRange(Display display, JSONObject container) throws JSONException {
        Point point = new Point();
        Point point2 = new Point();
        display.getCurrentSizeRange(point, point2);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("smallest", new JSONArray((Collection) CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(point.x), Integer.valueOf(point.y)})));
        jSONObject.put("largest", new JSONArray((Collection) CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(point2.x), Integer.valueOf(point2.y)})));
        container.put("currentSizeRange", jSONObject);
    }

    private void collectFlags(Display display, JSONObject container) throws JSONException {
        SparseArray<String> sparseArray = new SparseArray<>();
        int flags = display.getFlags();
        Iterator it = ArrayIteratorKt.iterator(display.getClass().getFields());
        while (it.hasNext()) {
            Field field = (Field) it.next();
            String name = field.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            if (StringsKt.startsWith$default(name, "FLAG_", false, 2, (Object) null)) {
                try {
                    sparseArray.put(field.getInt(null), field.getName());
                } catch (IllegalAccessException unused) {
                }
            }
        }
        container.put("flags", activeFlags(sparseArray, flags));
    }

    private void collectName(Display display, JSONObject container) throws JSONException {
        container.put("name", display.getName());
    }

    private void collectMetrics(Display display, JSONObject container) throws JSONException {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        JSONObject jSONObject = new JSONObject();
        collectMetrics(displayMetrics, jSONObject);
        container.put("metrics", jSONObject);
    }

    private void collectRealMetrics(Display display, JSONObject container) throws JSONException {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getRealMetrics(displayMetrics);
        JSONObject jSONObject = new JSONObject();
        collectMetrics(displayMetrics, jSONObject);
        container.put("realMetrics", jSONObject);
    }

    private void collectMetrics(DisplayMetrics metrics, JSONObject container) throws JSONException {
        container.put("density", metrics.density).put("densityDpi", metrics.densityDpi).put("scaledDensity", "x" + metrics.scaledDensity).put("widthPixels", metrics.widthPixels).put("heightPixels", metrics.heightPixels).put("xdpi", metrics.xdpi).put("ydpi", metrics.ydpi);
    }

    private String activeFlags(SparseArray<String> flagNames, int bitfield) {
        StringBuilder sb = new StringBuilder();
        int size = flagNames.size();
        for (int i = 0; i < size; i++) {
            int iKeyAt = flagNames.keyAt(i) & bitfield;
            if (iKeyAt > 0) {
                if (sb.length() > 0) {
                    sb.append('+');
                }
                sb.append(flagNames.get(iKeyAt));
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
