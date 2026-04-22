package org.acra.collector;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.util.SparseArray;
import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.collector.Collector;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MediaCodecListCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u0000 *2\u00020\u0001:\u0002)*B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J(\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010 \u001a\u00020\u0013H\u0012J\b\u0010!\u001a\u00020\"H\u0013J\u0018\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0006H\u0013J\u0012\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010$\u001a\u00020%H\u0013R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006+"}, d2 = {"Lorg/acra/collector/MediaCodecListCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "<init>", "()V", "mColorFormatValues", "Landroid/util/SparseArray;", "", "mAVCLevelValues", "mAVCProfileValues", "mH263LevelValues", "mH263ProfileValues", "mMPEG4LevelValues", "mMPEG4ProfileValues", "mAACProfileValues", "order", "Lorg/acra/collector/Collector$Order;", "getOrder", "()Lorg/acra/collector/Collector$Order;", "collect", "", "reportField", "Lorg/acra/ReportField;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "target", "Lorg/acra/data/CrashReportData;", "shouldCollect", "", "prepare", "collectMediaCodecList", "Lorg/json/JSONObject;", "collectCapabilitiesForType", "codecInfo", "Landroid/media/MediaCodecInfo;", BonjourPeer.TYPE, "identifyCodecType", "Lorg/acra/collector/MediaCodecListCollector$CodecType;", "CodecType", "Companion", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class MediaCodecListCollector extends BaseReportFieldCollector {
    private static final String COLOR_FORMAT_PREFIX = "COLOR_";
    private final SparseArray<String> mAACProfileValues;
    private final SparseArray<String> mAVCLevelValues;
    private final SparseArray<String> mAVCProfileValues;
    private final SparseArray<String> mColorFormatValues;
    private final SparseArray<String> mH263LevelValues;
    private final SparseArray<String> mH263ProfileValues;
    private final SparseArray<String> mMPEG4LevelValues;
    private final SparseArray<String> mMPEG4ProfileValues;
    private static final String[] MPEG4_TYPES = {"mp4", "mpeg4", "MP4", "MPEG4"};
    private static final String[] AVC_TYPES = {"avc", "h264", "AVC", "H264"};
    private static final String[] H263_TYPES = {"h263", "H263"};
    private static final String[] AAC_TYPES = {"aac", "AAC"};

    /* JADX INFO: compiled from: MediaCodecListCollector.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CodecType.values().length];
            try {
                iArr[CodecType.AVC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CodecType.H263.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CodecType.MPEG4.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CodecType.AAC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: MediaCodecListCollector.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/acra/collector/MediaCodecListCollector$CodecType;", "", "<init>", "(Ljava/lang/String;I)V", "AVC", "H263", "MPEG4", "AAC", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class CodecType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ CodecType[] $VALUES;
        public static final CodecType AVC = new CodecType("AVC", 0);
        public static final CodecType H263 = new CodecType("H263", 1);
        public static final CodecType MPEG4 = new CodecType("MPEG4", 2);
        public static final CodecType AAC = new CodecType("AAC", 3);

        private static final /* synthetic */ CodecType[] $values() {
            return new CodecType[]{AVC, H263, MPEG4, AAC};
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        private CodecType(String str, int i) {
        }

        static {
            CodecType[] codecTypeArr$values = $values();
            $VALUES = codecTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(codecTypeArr$values);
        }

        public static CodecType valueOf(String str) {
            return (CodecType) Enum.valueOf(CodecType.class, str);
        }

        public static CodecType[] values() {
            return (CodecType[]) $VALUES.clone();
        }
    }

    public MediaCodecListCollector() {
        super(ReportField.MEDIA_CODEC_LIST);
        this.mColorFormatValues = new SparseArray<>();
        this.mAVCLevelValues = new SparseArray<>();
        this.mAVCProfileValues = new SparseArray<>();
        this.mH263LevelValues = new SparseArray<>();
        this.mH263ProfileValues = new SparseArray<>();
        this.mMPEG4LevelValues = new SparseArray<>();
        this.mMPEG4ProfileValues = new SparseArray<>();
        this.mAACProfileValues = new SparseArray<>();
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector
    public Collector.Order getOrder() {
        return Collector.Order.LATE;
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) throws JSONException {
        Intrinsics.checkNotNullParameter(reportField, "reportField");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(target, "target");
        target.put(ReportField.MEDIA_CODEC_LIST, collectMediaCodecList());
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public boolean shouldCollect(Context context, CoreConfiguration config, ReportField collect, ReportBuilder reportBuilder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(collect, "collect");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        return super.shouldCollect(context, config, collect, reportBuilder);
    }

    private void prepare() {
        try {
            Iterator it = ArrayIteratorKt.iterator(Class.forName("android.media.MediaCodecInfo$CodecCapabilities").getFields());
            while (it.hasNext()) {
                Field field = (Field) it.next();
                if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
                    String name = field.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    if (StringsKt.startsWith$default(name, COLOR_FORMAT_PREFIX, false, 2, (Object) null)) {
                        this.mColorFormatValues.put(field.getInt(null), field.getName());
                    }
                }
            }
            Iterator it2 = ArrayIteratorKt.iterator(Class.forName("android.media.MediaCodecInfo$CodecProfileLevel").getFields());
            while (it2.hasNext()) {
                Field field2 = (Field) it2.next();
                if (Modifier.isStatic(field2.getModifiers()) && Modifier.isFinal(field2.getModifiers())) {
                    String name2 = field2.getName();
                    Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                    if (StringsKt.startsWith$default(name2, "AVCLevel", false, 2, (Object) null)) {
                        this.mAVCLevelValues.put(field2.getInt(null), field2.getName());
                    } else {
                        String name3 = field2.getName();
                        Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
                        if (StringsKt.startsWith$default(name3, "AVCProfile", false, 2, (Object) null)) {
                            this.mAVCProfileValues.put(field2.getInt(null), field2.getName());
                        } else {
                            String name4 = field2.getName();
                            Intrinsics.checkNotNullExpressionValue(name4, "getName(...)");
                            if (StringsKt.startsWith$default(name4, "H263Level", false, 2, (Object) null)) {
                                this.mH263LevelValues.put(field2.getInt(null), field2.getName());
                            } else {
                                String name5 = field2.getName();
                                Intrinsics.checkNotNullExpressionValue(name5, "getName(...)");
                                if (StringsKt.startsWith$default(name5, "H263Profile", false, 2, (Object) null)) {
                                    this.mH263ProfileValues.put(field2.getInt(null), field2.getName());
                                } else {
                                    String name6 = field2.getName();
                                    Intrinsics.checkNotNullExpressionValue(name6, "getName(...)");
                                    if (StringsKt.startsWith$default(name6, "MPEG4Level", false, 2, (Object) null)) {
                                        this.mMPEG4LevelValues.put(field2.getInt(null), field2.getName());
                                    } else {
                                        String name7 = field2.getName();
                                        Intrinsics.checkNotNullExpressionValue(name7, "getName(...)");
                                        if (StringsKt.startsWith$default(name7, "MPEG4Profile", false, 2, (Object) null)) {
                                            this.mMPEG4ProfileValues.put(field2.getInt(null), field2.getName());
                                        } else {
                                            String name8 = field2.getName();
                                            Intrinsics.checkNotNullExpressionValue(name8, "getName(...)");
                                            if (StringsKt.startsWith$default(name8, "AAC", false, 2, (Object) null)) {
                                                this.mAACProfileValues.put(field2.getInt(null), field2.getName());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | SecurityException unused) {
        }
    }

    @TargetApi(16)
    private JSONObject collectMediaCodecList() throws JSONException {
        prepare();
        MediaCodecInfo[] codecInfos = new MediaCodecList(1).getCodecInfos();
        Intrinsics.checkNotNull(codecInfos);
        JSONObject jSONObject = new JSONObject();
        int length = codecInfos.length;
        for (int i = 0; i < length; i++) {
            MediaCodecInfo mediaCodecInfo = codecInfos[i];
            JSONObject jSONObject2 = new JSONObject();
            Intrinsics.checkNotNull(mediaCodecInfo);
            String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
            jSONObject2.put("name", mediaCodecInfo.getName()).put("isEncoder", mediaCodecInfo.isEncoder());
            JSONObject jSONObject3 = new JSONObject();
            Iterator it = ArrayIteratorKt.iterator(supportedTypes);
            while (it.hasNext()) {
                String str = (String) it.next();
                Intrinsics.checkNotNull(str);
                jSONObject3.put(str, collectCapabilitiesForType(mediaCodecInfo, str));
            }
            jSONObject2.put("supportedTypes", jSONObject3);
            jSONObject.put(String.valueOf(i), jSONObject2);
        }
        return jSONObject;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f6, code lost:
    
        r0.put("profileLevels", r1);
     */
    @android.annotation.TargetApi(16)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.json.JSONObject collectCapabilitiesForType(android.media.MediaCodecInfo r10, java.lang.String r11) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.collector.MediaCodecListCollector.collectCapabilitiesForType(android.media.MediaCodecInfo, java.lang.String):org.json.JSONObject");
    }

    @TargetApi(16)
    private CodecType identifyCodecType(MediaCodecInfo codecInfo) {
        String name = codecInfo.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        for (String str : AVC_TYPES) {
            if (StringsKt.contains$default((CharSequence) name, (CharSequence) str, false, 2, (Object) null)) {
                return CodecType.AVC;
            }
        }
        for (String str2 : H263_TYPES) {
            if (StringsKt.contains$default((CharSequence) name, (CharSequence) str2, false, 2, (Object) null)) {
                return CodecType.H263;
            }
        }
        for (String str3 : MPEG4_TYPES) {
            if (StringsKt.contains$default((CharSequence) name, (CharSequence) str3, false, 2, (Object) null)) {
                return CodecType.MPEG4;
            }
        }
        for (String str4 : AAC_TYPES) {
            if (StringsKt.contains$default((CharSequence) name, (CharSequence) str4, false, 2, (Object) null)) {
                return CodecType.AAC;
            }
        }
        return null;
    }
}
