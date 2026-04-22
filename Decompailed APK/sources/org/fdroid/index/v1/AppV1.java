package org.fdroid.index.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.i18n.ErrorBundle;
import org.fdroid.fdroid.views.apps.AppListActivity;
import org.fdroid.index.IndexConverterKt;
import org.fdroid.index.v2.FileV2;
import org.fdroid.index.v2.MetadataV2;
import org.fdroid.index.v2.Screenshots;

/* JADX INFO: compiled from: AppV1.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u0090\u00012\u00020\u0001:\u0004\u008f\u0001\u0090\u0001B\u008d\u0003\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u001b\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010 \u001a\u00020\u0004\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001e\u0012\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020$\u0018\u00010#\u0012\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b&\u0010'Bï\u0002\b\u0010\u0012\u0006\u0010(\u001a\u00020)\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0004\u0012\b\u0010!\u001a\u0004\u0018\u00010\u001e\u0012\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020$\u0018\u00010#\u0012\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010*\u001a\u0004\u0018\u00010+¢\u0006\u0004\b&\u0010,J\u001a\u0010Q\u001a\u00020R2\b\u0010S\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010T\u001a\u00020\u0004JD\u0010U\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010#j\u0004\u0018\u0001`V2\b\u0010W\u001a\u0004\u0018\u00010\u00042\u0006\u0010T\u001a\u00020\u00042\u0014\u0010X\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0006\u0012\u0004\u0018\u00010\u00040YH\u0002JD\u0010Z\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010#j\u0004\u0018\u0001`V*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020$\u0018\u00010#2\u0014\u0010X\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0006\u0012\u0004\u0018\u00010\u00040YH\u0002JD\u0010[\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\\\u0018\u00010#j\u0004\u0018\u0001`]*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020$\u0018\u00010#2\u0014\u0010X\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0006\u0012\u0004\u0018\u00010\u00040YH\u0002JX\u0010^\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\\0\u0003\u0018\u00010#j\u0004\u0018\u0001`_*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020$\u0018\u00010#2\u0006\u0010`\u001a\u00020\u00042\u001a\u0010X\u001a\u0016\u0012\u0004\u0012\u00020$\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030YH\u0002J\u000f\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010q\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010r\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010t\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010u\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010v\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010w\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010x\u001a\u00020\u0004HÆ\u0003J\u000b\u0010y\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010z\u001a\u0004\u0018\u00010\u001eHÆ\u0003¢\u0006\u0002\u0010IJ\u000b\u0010{\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010|\u001a\u00020\u0004HÆ\u0003J\u0010\u0010}\u001a\u0004\u0018\u00010\u001eHÆ\u0003¢\u0006\u0002\u0010IJ\u0017\u0010~\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020$\u0018\u00010#HÆ\u0003J\u0011\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u009a\u0003\u0010\u0080\u0001\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u001b\u001a\u00020\u00042\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010 \u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001e2\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020$\u0018\u00010#2\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0003\u0010\u0081\u0001J\u0016\u0010\u0082\u0001\u001a\u00030\u0083\u00012\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\n\u0010\u0085\u0001\u001a\u00020)HÖ\u0001J\n\u0010\u0086\u0001\u001a\u00020\u0004HÖ\u0001J-\u0010\u0087\u0001\u001a\u00030\u0088\u00012\u0007\u0010\u0089\u0001\u001a\u00020\u00002\b\u0010\u008a\u0001\u001a\u00030\u008b\u00012\b\u0010\u008c\u0001\u001a\u00030\u008d\u0001H\u0001¢\u0006\u0003\b\u008e\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010.R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00101R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u00101R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u00101R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00101R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00101R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u00101R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u00101R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u00101R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u00101R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u00101R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u00101R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u00101R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u00101R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u00101R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u00101R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u00101R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u00101R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u00101R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u00101R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u00101R\u0011\u0010\u001b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u00101R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u00101R\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\n\n\u0002\u0010J\u001a\u0004\bH\u0010IR\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u00101R\u0011\u0010 \u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u00101R\u0015\u0010!\u001a\u0004\u0018\u00010\u001e¢\u0006\n\n\u0002\u0010J\u001a\u0004\bM\u0010IR\u001f\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020$\u0018\u00010#¢\u0006\b\n\u0000\u001a\u0004\bN\u0010OR\u0019\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bP\u0010.¨\u0006\u0091\u0001"}, d2 = {"Lorg/fdroid/index/v1/AppV1;", "", "categories", "", "", "antiFeatures", ErrorBundle.SUMMARY_ENTRY, "description", "changelog", "translation", "issueTracker", "sourceCode", "binaries", "name", "authorName", "authorEmail", "authorWebSite", "authorPhone", "donate", "liberapayID", "liberapay", "openCollective", "bitcoin", "litecoin", "flattrID", "suggestedVersionName", "suggestedVersionCode", "license", "webSite", "added", "", "icon", "packageName", AppListActivity.SortClause.LAST_UPDATED, "localized", "", "Lorg/fdroid/index/v1/Localized;", "allowedAPKSigningKeys", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCategories", "()Ljava/util/List;", "getAntiFeatures", "getSummary", "()Ljava/lang/String;", "getDescription", "getChangelog", "getTranslation", "getIssueTracker", "getSourceCode", "getBinaries", "getName", "getAuthorName", "getAuthorEmail", "getAuthorWebSite", "getAuthorPhone", "getDonate", "getLiberapayID", "getLiberapay", "getOpenCollective", "getBitcoin", "getLitecoin", "getFlattrID", "getSuggestedVersionName", "getSuggestedVersionCode", "getLicense", "getWebSite", "getAdded", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getIcon", "getPackageName", "getLastUpdated", "getLocalized", "()Ljava/util/Map;", "getAllowedAPKSigningKeys", "toMetadataV2", "Lorg/fdroid/index/v2/MetadataV2;", "preferredSigner", "locale", "getLocalizedTextV2", "Lorg/fdroid/index/v2/LocalizedTextV2;", "s", "selector", "Lkotlin/Function1;", "toLocalizedTextV2", "toLocalizedFileV2", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileV2;", "toLocalizedFileListV2", "Lorg/fdroid/index/v2/LocalizedFileListV2;", "kind", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "copy", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;Ljava/util/List;)Lorg/fdroid/index/v1/AppV1;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class AppV1 {
    private static final KSerializer[] $childSerializers;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Long added;
    private final List<String> allowedAPKSigningKeys;
    private final List<String> antiFeatures;
    private final String authorEmail;
    private final String authorName;
    private final String authorPhone;
    private final String authorWebSite;
    private final String binaries;
    private final String bitcoin;
    private final List<String> categories;
    private final String changelog;
    private final String description;
    private final String donate;
    private final String flattrID;
    private final String icon;
    private final String issueTracker;
    private final Long lastUpdated;
    private final String liberapay;
    private final String liberapayID;
    private final String license;
    private final String litecoin;
    private final Map<String, Localized> localized;
    private final String name;
    private final String openCollective;
    private final String packageName;
    private final String sourceCode;
    private final String suggestedVersionCode;
    private final String suggestedVersionName;
    private final String summary;
    private final String translation;
    private final String webSite;

    public final List<String> component1() {
        return this.categories;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getAuthorName() {
        return this.authorName;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getAuthorEmail() {
        return this.authorEmail;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getAuthorWebSite() {
        return this.authorWebSite;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getAuthorPhone() {
        return this.authorPhone;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getDonate() {
        return this.donate;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getLiberapayID() {
        return this.liberapayID;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getLiberapay() {
        return this.liberapay;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getOpenCollective() {
        return this.openCollective;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getBitcoin() {
        return this.bitcoin;
    }

    public final List<String> component2() {
        return this.antiFeatures;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getLitecoin() {
        return this.litecoin;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getFlattrID() {
        return this.flattrID;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getSuggestedVersionName() {
        return this.suggestedVersionName;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getSuggestedVersionCode() {
        return this.suggestedVersionCode;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getLicense() {
        return this.license;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final String getWebSite() {
        return this.webSite;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final Long getAdded() {
        return this.added;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component29, reason: from getter */
    public final Long getLastUpdated() {
        return this.lastUpdated;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSummary() {
        return this.summary;
    }

    public final Map<String, Localized> component30() {
        return this.localized;
    }

    public final List<String> component31() {
        return this.allowedAPKSigningKeys;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getChangelog() {
        return this.changelog;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getTranslation() {
        return this.translation;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getIssueTracker() {
        return this.issueTracker;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getSourceCode() {
        return this.sourceCode;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getBinaries() {
        return this.binaries;
    }

    public final AppV1 copy(List<String> categories, List<String> antiFeatures, String summary, String description, String changelog, String translation, String issueTracker, String sourceCode, String binaries, String name, String authorName, String authorEmail, String authorWebSite, String authorPhone, String donate, String liberapayID, String liberapay, String openCollective, String bitcoin, String litecoin, String flattrID, String suggestedVersionName, String suggestedVersionCode, String license, String webSite, Long added, String icon, String packageName, Long lastUpdated, Map<String, Localized> localized, List<String> allowedAPKSigningKeys) {
        Intrinsics.checkNotNullParameter(categories, "categories");
        Intrinsics.checkNotNullParameter(antiFeatures, "antiFeatures");
        Intrinsics.checkNotNullParameter(license, "license");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return new AppV1(categories, antiFeatures, summary, description, changelog, translation, issueTracker, sourceCode, binaries, name, authorName, authorEmail, authorWebSite, authorPhone, donate, liberapayID, liberapay, openCollective, bitcoin, litecoin, flattrID, suggestedVersionName, suggestedVersionCode, license, webSite, added, icon, packageName, lastUpdated, localized, allowedAPKSigningKeys);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppV1)) {
            return false;
        }
        AppV1 appV1 = (AppV1) other;
        return Intrinsics.areEqual(this.categories, appV1.categories) && Intrinsics.areEqual(this.antiFeatures, appV1.antiFeatures) && Intrinsics.areEqual(this.summary, appV1.summary) && Intrinsics.areEqual(this.description, appV1.description) && Intrinsics.areEqual(this.changelog, appV1.changelog) && Intrinsics.areEqual(this.translation, appV1.translation) && Intrinsics.areEqual(this.issueTracker, appV1.issueTracker) && Intrinsics.areEqual(this.sourceCode, appV1.sourceCode) && Intrinsics.areEqual(this.binaries, appV1.binaries) && Intrinsics.areEqual(this.name, appV1.name) && Intrinsics.areEqual(this.authorName, appV1.authorName) && Intrinsics.areEqual(this.authorEmail, appV1.authorEmail) && Intrinsics.areEqual(this.authorWebSite, appV1.authorWebSite) && Intrinsics.areEqual(this.authorPhone, appV1.authorPhone) && Intrinsics.areEqual(this.donate, appV1.donate) && Intrinsics.areEqual(this.liberapayID, appV1.liberapayID) && Intrinsics.areEqual(this.liberapay, appV1.liberapay) && Intrinsics.areEqual(this.openCollective, appV1.openCollective) && Intrinsics.areEqual(this.bitcoin, appV1.bitcoin) && Intrinsics.areEqual(this.litecoin, appV1.litecoin) && Intrinsics.areEqual(this.flattrID, appV1.flattrID) && Intrinsics.areEqual(this.suggestedVersionName, appV1.suggestedVersionName) && Intrinsics.areEqual(this.suggestedVersionCode, appV1.suggestedVersionCode) && Intrinsics.areEqual(this.license, appV1.license) && Intrinsics.areEqual(this.webSite, appV1.webSite) && Intrinsics.areEqual(this.added, appV1.added) && Intrinsics.areEqual(this.icon, appV1.icon) && Intrinsics.areEqual(this.packageName, appV1.packageName) && Intrinsics.areEqual(this.lastUpdated, appV1.lastUpdated) && Intrinsics.areEqual(this.localized, appV1.localized) && Intrinsics.areEqual(this.allowedAPKSigningKeys, appV1.allowedAPKSigningKeys);
    }

    public int hashCode() {
        int iHashCode = ((this.categories.hashCode() * 31) + this.antiFeatures.hashCode()) * 31;
        String str = this.summary;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.description;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.changelog;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.translation;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.issueTracker;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.sourceCode;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.binaries;
        int iHashCode8 = (iHashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.name;
        int iHashCode9 = (iHashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.authorName;
        int iHashCode10 = (iHashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.authorEmail;
        int iHashCode11 = (iHashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.authorWebSite;
        int iHashCode12 = (iHashCode11 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.authorPhone;
        int iHashCode13 = (iHashCode12 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.donate;
        int iHashCode14 = (iHashCode13 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.liberapayID;
        int iHashCode15 = (iHashCode14 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.liberapay;
        int iHashCode16 = (iHashCode15 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.openCollective;
        int iHashCode17 = (iHashCode16 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.bitcoin;
        int iHashCode18 = (iHashCode17 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.litecoin;
        int iHashCode19 = (iHashCode18 + (str18 == null ? 0 : str18.hashCode())) * 31;
        String str19 = this.flattrID;
        int iHashCode20 = (iHashCode19 + (str19 == null ? 0 : str19.hashCode())) * 31;
        String str20 = this.suggestedVersionName;
        int iHashCode21 = (iHashCode20 + (str20 == null ? 0 : str20.hashCode())) * 31;
        String str21 = this.suggestedVersionCode;
        int iHashCode22 = (((iHashCode21 + (str21 == null ? 0 : str21.hashCode())) * 31) + this.license.hashCode()) * 31;
        String str22 = this.webSite;
        int iHashCode23 = (iHashCode22 + (str22 == null ? 0 : str22.hashCode())) * 31;
        Long l = this.added;
        int iHashCode24 = (iHashCode23 + (l == null ? 0 : l.hashCode())) * 31;
        String str23 = this.icon;
        int iHashCode25 = (((iHashCode24 + (str23 == null ? 0 : str23.hashCode())) * 31) + this.packageName.hashCode()) * 31;
        Long l2 = this.lastUpdated;
        int iHashCode26 = (iHashCode25 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Map<String, Localized> map = this.localized;
        int iHashCode27 = (iHashCode26 + (map == null ? 0 : map.hashCode())) * 31;
        List<String> list = this.allowedAPKSigningKeys;
        return iHashCode27 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "AppV1(categories=" + this.categories + ", antiFeatures=" + this.antiFeatures + ", summary=" + this.summary + ", description=" + this.description + ", changelog=" + this.changelog + ", translation=" + this.translation + ", issueTracker=" + this.issueTracker + ", sourceCode=" + this.sourceCode + ", binaries=" + this.binaries + ", name=" + this.name + ", authorName=" + this.authorName + ", authorEmail=" + this.authorEmail + ", authorWebSite=" + this.authorWebSite + ", authorPhone=" + this.authorPhone + ", donate=" + this.donate + ", liberapayID=" + this.liberapayID + ", liberapay=" + this.liberapay + ", openCollective=" + this.openCollective + ", bitcoin=" + this.bitcoin + ", litecoin=" + this.litecoin + ", flattrID=" + this.flattrID + ", suggestedVersionName=" + this.suggestedVersionName + ", suggestedVersionCode=" + this.suggestedVersionCode + ", license=" + this.license + ", webSite=" + this.webSite + ", added=" + this.added + ", icon=" + this.icon + ", packageName=" + this.packageName + ", lastUpdated=" + this.lastUpdated + ", localized=" + this.localized + ", allowedAPKSigningKeys=" + this.allowedAPKSigningKeys + ")";
    }

    /* JADX INFO: compiled from: AppV1.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v1/AppV1$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v1/AppV1;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return AppV1$$serializer.INSTANCE;
        }
    }

    static {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        $childSerializers = new KSerializer[]{new ArrayListSerializer(stringSerializer), new ArrayListSerializer(stringSerializer), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new LinkedHashMapSerializer(stringSerializer, Localized$$serializer.INSTANCE), new ArrayListSerializer(stringSerializer)};
    }

    public /* synthetic */ AppV1(int i, List list, List list2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, Long l, String str24, String str25, Long l2, Map map, List list3, SerializationConstructorMarker serializationConstructorMarker) {
        if (142606336 != (i & 142606336)) {
            PluginExceptionsKt.throwMissingFieldException(i, 142606336, AppV1$$serializer.INSTANCE.getDescriptor());
        }
        this.categories = (i & 1) == 0 ? CollectionsKt.emptyList() : list;
        this.antiFeatures = (i & 2) == 0 ? CollectionsKt.emptyList() : list2;
        if ((i & 4) == 0) {
            this.summary = null;
        } else {
            this.summary = str;
        }
        if ((i & 8) == 0) {
            this.description = null;
        } else {
            this.description = str2;
        }
        if ((i & 16) == 0) {
            this.changelog = null;
        } else {
            this.changelog = str3;
        }
        if ((i & 32) == 0) {
            this.translation = null;
        } else {
            this.translation = str4;
        }
        if ((i & 64) == 0) {
            this.issueTracker = null;
        } else {
            this.issueTracker = str5;
        }
        if ((i & 128) == 0) {
            this.sourceCode = null;
        } else {
            this.sourceCode = str6;
        }
        if ((i & 256) == 0) {
            this.binaries = null;
        } else {
            this.binaries = str7;
        }
        if ((i & 512) == 0) {
            this.name = null;
        } else {
            this.name = str8;
        }
        if ((i & 1024) == 0) {
            this.authorName = null;
        } else {
            this.authorName = str9;
        }
        if ((i & 2048) == 0) {
            this.authorEmail = null;
        } else {
            this.authorEmail = str10;
        }
        if ((i & PKIFailureInfo.certConfirmed) == 0) {
            this.authorWebSite = null;
        } else {
            this.authorWebSite = str11;
        }
        if ((i & 8192) == 0) {
            this.authorPhone = null;
        } else {
            this.authorPhone = str12;
        }
        if ((i & 16384) == 0) {
            this.donate = null;
        } else {
            this.donate = str13;
        }
        if ((32768 & i) == 0) {
            this.liberapayID = null;
        } else {
            this.liberapayID = str14;
        }
        if ((65536 & i) == 0) {
            this.liberapay = null;
        } else {
            this.liberapay = str15;
        }
        if ((131072 & i) == 0) {
            this.openCollective = null;
        } else {
            this.openCollective = str16;
        }
        if ((262144 & i) == 0) {
            this.bitcoin = null;
        } else {
            this.bitcoin = str17;
        }
        if ((524288 & i) == 0) {
            this.litecoin = null;
        } else {
            this.litecoin = str18;
        }
        if ((1048576 & i) == 0) {
            this.flattrID = null;
        } else {
            this.flattrID = str19;
        }
        if ((2097152 & i) == 0) {
            this.suggestedVersionName = null;
        } else {
            this.suggestedVersionName = str20;
        }
        if ((4194304 & i) == 0) {
            this.suggestedVersionCode = null;
        } else {
            this.suggestedVersionCode = str21;
        }
        this.license = str22;
        if ((16777216 & i) == 0) {
            this.webSite = null;
        } else {
            this.webSite = str23;
        }
        if ((33554432 & i) == 0) {
            this.added = null;
        } else {
            this.added = l;
        }
        if ((67108864 & i) == 0) {
            this.icon = null;
        } else {
            this.icon = str24;
        }
        this.packageName = str25;
        if ((268435456 & i) == 0) {
            this.lastUpdated = null;
        } else {
            this.lastUpdated = l2;
        }
        if ((536870912 & i) == 0) {
            this.localized = null;
        } else {
            this.localized = map;
        }
        if ((i & 1073741824) == 0) {
            this.allowedAPKSigningKeys = null;
        } else {
            this.allowedAPKSigningKeys = list3;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(AppV1 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.categories, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 0, kSerializerArr[0], self.categories);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.antiFeatures, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.antiFeatures);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.summary != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.summary);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.description != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.description);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.changelog != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.changelog);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.translation != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.translation);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.issueTracker != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.issueTracker);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.sourceCode != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.sourceCode);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.binaries != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.binaries);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 9) || self.name != null) {
            output.encodeNullableSerializableElement(serialDesc, 9, StringSerializer.INSTANCE, self.name);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 10) || self.authorName != null) {
            output.encodeNullableSerializableElement(serialDesc, 10, StringSerializer.INSTANCE, self.authorName);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 11) || self.authorEmail != null) {
            output.encodeNullableSerializableElement(serialDesc, 11, StringSerializer.INSTANCE, self.authorEmail);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 12) || self.authorWebSite != null) {
            output.encodeNullableSerializableElement(serialDesc, 12, StringSerializer.INSTANCE, self.authorWebSite);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 13) || self.authorPhone != null) {
            output.encodeNullableSerializableElement(serialDesc, 13, StringSerializer.INSTANCE, self.authorPhone);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 14) || self.donate != null) {
            output.encodeNullableSerializableElement(serialDesc, 14, StringSerializer.INSTANCE, self.donate);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 15) || self.liberapayID != null) {
            output.encodeNullableSerializableElement(serialDesc, 15, StringSerializer.INSTANCE, self.liberapayID);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 16) || self.liberapay != null) {
            output.encodeNullableSerializableElement(serialDesc, 16, StringSerializer.INSTANCE, self.liberapay);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 17) || self.openCollective != null) {
            output.encodeNullableSerializableElement(serialDesc, 17, StringSerializer.INSTANCE, self.openCollective);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 18) || self.bitcoin != null) {
            output.encodeNullableSerializableElement(serialDesc, 18, StringSerializer.INSTANCE, self.bitcoin);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 19) || self.litecoin != null) {
            output.encodeNullableSerializableElement(serialDesc, 19, StringSerializer.INSTANCE, self.litecoin);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 20) || self.flattrID != null) {
            output.encodeNullableSerializableElement(serialDesc, 20, StringSerializer.INSTANCE, self.flattrID);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 21) || self.suggestedVersionName != null) {
            output.encodeNullableSerializableElement(serialDesc, 21, StringSerializer.INSTANCE, self.suggestedVersionName);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 22) || self.suggestedVersionCode != null) {
            output.encodeNullableSerializableElement(serialDesc, 22, StringSerializer.INSTANCE, self.suggestedVersionCode);
        }
        output.encodeStringElement(serialDesc, 23, self.license);
        if (output.shouldEncodeElementDefault(serialDesc, 24) || self.webSite != null) {
            output.encodeNullableSerializableElement(serialDesc, 24, StringSerializer.INSTANCE, self.webSite);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 25) || self.added != null) {
            output.encodeNullableSerializableElement(serialDesc, 25, LongSerializer.INSTANCE, self.added);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 26) || self.icon != null) {
            output.encodeNullableSerializableElement(serialDesc, 26, StringSerializer.INSTANCE, self.icon);
        }
        output.encodeStringElement(serialDesc, 27, self.packageName);
        if (output.shouldEncodeElementDefault(serialDesc, 28) || self.lastUpdated != null) {
            output.encodeNullableSerializableElement(serialDesc, 28, LongSerializer.INSTANCE, self.lastUpdated);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 29) || self.localized != null) {
            output.encodeNullableSerializableElement(serialDesc, 29, kSerializerArr[29], self.localized);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 30) && self.allowedAPKSigningKeys == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 30, kSerializerArr[30], self.allowedAPKSigningKeys);
    }

    public AppV1(List<String> categories, List<String> antiFeatures, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String license, String str22, Long l, String str23, String packageName, Long l2, Map<String, Localized> map, List<String> list) {
        Intrinsics.checkNotNullParameter(categories, "categories");
        Intrinsics.checkNotNullParameter(antiFeatures, "antiFeatures");
        Intrinsics.checkNotNullParameter(license, "license");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.categories = categories;
        this.antiFeatures = antiFeatures;
        this.summary = str;
        this.description = str2;
        this.changelog = str3;
        this.translation = str4;
        this.issueTracker = str5;
        this.sourceCode = str6;
        this.binaries = str7;
        this.name = str8;
        this.authorName = str9;
        this.authorEmail = str10;
        this.authorWebSite = str11;
        this.authorPhone = str12;
        this.donate = str13;
        this.liberapayID = str14;
        this.liberapay = str15;
        this.openCollective = str16;
        this.bitcoin = str17;
        this.litecoin = str18;
        this.flattrID = str19;
        this.suggestedVersionName = str20;
        this.suggestedVersionCode = str21;
        this.license = license;
        this.webSite = str22;
        this.added = l;
        this.icon = str23;
        this.packageName = packageName;
        this.lastUpdated = l2;
        this.localized = map;
        this.allowedAPKSigningKeys = list;
    }

    public /* synthetic */ AppV1(List list, List list2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, Long l, String str24, String str25, Long l2, Map map, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, (i & 512) != 0 ? null : str8, (i & 1024) != 0 ? null : str9, (i & 2048) != 0 ? null : str10, (i & PKIFailureInfo.certConfirmed) != 0 ? null : str11, (i & 8192) != 0 ? null : str12, (i & 16384) != 0 ? null : str13, (32768 & i) != 0 ? null : str14, (65536 & i) != 0 ? null : str15, (131072 & i) != 0 ? null : str16, (262144 & i) != 0 ? null : str17, (524288 & i) != 0 ? null : str18, (1048576 & i) != 0 ? null : str19, (2097152 & i) != 0 ? null : str20, (4194304 & i) != 0 ? null : str21, str22, (16777216 & i) != 0 ? null : str23, (33554432 & i) != 0 ? null : l, (67108864 & i) != 0 ? null : str24, str25, (268435456 & i) != 0 ? null : l2, (536870912 & i) != 0 ? null : map, (i & 1073741824) != 0 ? null : list3);
    }

    public final List<String> getCategories() {
        return this.categories;
    }

    public final List<String> getAntiFeatures() {
        return this.antiFeatures;
    }

    public final String getSummary() {
        return this.summary;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getChangelog() {
        return this.changelog;
    }

    public final String getTranslation() {
        return this.translation;
    }

    public final String getIssueTracker() {
        return this.issueTracker;
    }

    public final String getSourceCode() {
        return this.sourceCode;
    }

    public final String getBinaries() {
        return this.binaries;
    }

    public final String getName() {
        return this.name;
    }

    public final String getAuthorName() {
        return this.authorName;
    }

    public final String getAuthorEmail() {
        return this.authorEmail;
    }

    public final String getAuthorWebSite() {
        return this.authorWebSite;
    }

    public final String getAuthorPhone() {
        return this.authorPhone;
    }

    public final String getDonate() {
        return this.donate;
    }

    public final String getLiberapayID() {
        return this.liberapayID;
    }

    public final String getLiberapay() {
        return this.liberapay;
    }

    public final String getOpenCollective() {
        return this.openCollective;
    }

    public final String getBitcoin() {
        return this.bitcoin;
    }

    public final String getLitecoin() {
        return this.litecoin;
    }

    public final String getFlattrID() {
        return this.flattrID;
    }

    public final String getSuggestedVersionName() {
        return this.suggestedVersionName;
    }

    public final String getSuggestedVersionCode() {
        return this.suggestedVersionCode;
    }

    public final String getLicense() {
        return this.license;
    }

    public final String getWebSite() {
        return this.webSite;
    }

    public final Long getAdded() {
        return this.added;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final Long getLastUpdated() {
        return this.lastUpdated;
    }

    public final Map<String, Localized> getLocalized() {
        return this.localized;
    }

    public final List<String> getAllowedAPKSigningKeys() {
        return this.allowedAPKSigningKeys;
    }

    public static /* synthetic */ MetadataV2 toMetadataV2$default(AppV1 appV1, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = IndexConverterKt.DEFAULT_LOCALE;
        }
        return appV1.toMetadataV2(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toMetadataV2$lambda$0(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getName();
    }

    public final MetadataV2 toMetadataV2(String preferredSigner, String locale) {
        String str;
        String str2;
        Map<String, FileV2> mapMapOf;
        Intrinsics.checkNotNullParameter(locale, "locale");
        Map<String, String> localizedTextV2 = getLocalizedTextV2(this.name, locale, new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$0((Localized) obj);
            }
        });
        Map<String, String> localizedTextV22 = getLocalizedTextV2(this.summary, locale, new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$1((Localized) obj);
            }
        });
        Map<String, String> localizedTextV23 = getLocalizedTextV2(this.description, locale, new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$2((Localized) obj);
            }
        });
        Long l = this.added;
        long jLongValue = l != null ? l.longValue() : 0L;
        Long l2 = this.lastUpdated;
        long jLongValue2 = l2 != null ? l2.longValue() : 0L;
        String str3 = this.webSite;
        String str4 = this.changelog;
        String str5 = this.license;
        String str6 = this.sourceCode;
        String str7 = this.issueTracker;
        String str8 = this.translation;
        List<String> list = this.categories;
        String str9 = this.authorName;
        String str10 = this.authorEmail;
        String str11 = this.authorWebSite;
        String str12 = this.authorPhone;
        String str13 = this.donate;
        List listEmptyList = str13 == null ? CollectionsKt.emptyList() : CollectionsKt.listOf(str13);
        String str14 = this.liberapayID;
        String str15 = this.liberapay;
        String str16 = this.openCollective;
        String str17 = this.bitcoin;
        String str18 = this.litecoin;
        String str19 = this.flattrID;
        Map<String, FileV2> localizedFileV2 = toLocalizedFileV2(this.localized, new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$3((Localized) obj);
            }
        });
        if (localizedFileV2 == null) {
            String str20 = this.icon;
            if (str20 != null) {
                str = str7;
                StringBuilder sb = new StringBuilder();
                str2 = str6;
                sb.append("/icons/");
                sb.append(str20);
                mapMapOf = MapsKt.mapOf(TuplesKt.to(locale, new FileV2(sb.toString(), (String) null, (Long) null, (String) null, 14, (DefaultConstructorMarker) null)));
            } else {
                str = str7;
                str2 = str6;
                mapMapOf = null;
            }
        } else {
            str = str7;
            str2 = str6;
            mapMapOf = localizedFileV2;
        }
        Map<String, FileV2> localizedFileV22 = toLocalizedFileV2(this.localized, new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$5((Localized) obj);
            }
        });
        Map<String, FileV2> localizedFileV23 = toLocalizedFileV2(this.localized, new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$6((Localized) obj);
            }
        });
        Map<String, FileV2> localizedFileV24 = toLocalizedFileV2(this.localized, new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$7((Localized) obj);
            }
        });
        Map<String, String> localizedTextV24 = toLocalizedTextV2(this.localized, new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$8((Localized) obj);
            }
        });
        Screenshots screenshots = new Screenshots(toLocalizedFileListV2(this.localized, "phoneScreenshots", new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$9((Localized) obj);
            }
        }), toLocalizedFileListV2(this.localized, "sevenInchScreenshots", new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$10((Localized) obj);
            }
        }), toLocalizedFileListV2(this.localized, "tenInchScreenshots", new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$11((Localized) obj);
            }
        }), toLocalizedFileListV2(this.localized, "wearScreenshots", new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$12((Localized) obj);
            }
        }), toLocalizedFileListV2(this.localized, "tvScreenshots", new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toMetadataV2$lambda$13((Localized) obj);
            }
        }));
        return new MetadataV2(localizedTextV2, localizedTextV22, localizedTextV23, jLongValue, jLongValue2, str3, str4, str5, str2, str, str8, preferredSigner, list, str9, str10, str11, str12, listEmptyList, str14, str15, str16, str17, str18, str19, mapMapOf, localizedFileV22, localizedFileV23, localizedFileV24, localizedTextV24, !screenshots.isNull() ? screenshots : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toMetadataV2$lambda$1(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getSummary();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toMetadataV2$lambda$2(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getDescription();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toMetadataV2$lambda$3(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getIcon();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toMetadataV2$lambda$5(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getFeatureGraphic();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toMetadataV2$lambda$6(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getPromoGraphic();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toMetadataV2$lambda$7(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getTvBanner();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toMetadataV2$lambda$8(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getVideo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List toMetadataV2$lambda$9(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getPhoneScreenshots();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List toMetadataV2$lambda$10(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getSevenInchScreenshots();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List toMetadataV2$lambda$11(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getTenInchScreenshots();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List toMetadataV2$lambda$12(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getWearScreenshots();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List toMetadataV2$lambda$13(Localized it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getTvScreenshots();
    }

    private final Map<String, String> getLocalizedTextV2(String s, String locale, Function1 selector) {
        if (s != null) {
            return MapsKt.mapOf(TuplesKt.to(locale, s));
        }
        Map<String, Localized> map = this.localized;
        if (map != null) {
            return toLocalizedTextV2(map, selector);
        }
        return null;
    }

    private final Map<String, String> toLocalizedTextV2(Map<String, Localized> map, final Function1 function1) {
        if (map == null) {
            return null;
        }
        return IndexConverterKt.mapValuesNotNull(map, new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toLocalizedTextV2$lambda$15(function1, (Map.Entry) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toLocalizedTextV2$lambda$15(Function1 function1, Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (String) function1.invoke(it.getValue());
    }

    private final Map<String, FileV2> toLocalizedFileV2(Map<String, Localized> map, final Function1 function1) {
        if (map == null) {
            return null;
        }
        return IndexConverterKt.mapValuesNotNull(map, new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toLocalizedFileV2$lambda$17(function1, this, (Map.Entry) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FileV2 toLocalizedFileV2$lambda$17(Function1 function1, AppV1 appV1, Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = (String) function1.invoke(it.getValue());
        if (str == null) {
            return null;
        }
        return new FileV2("/" + appV1.packageName + "/" + it.getKey() + "/" + str, (String) null, (Long) null, (String) null, 14, (DefaultConstructorMarker) null);
    }

    private final Map<String, List<FileV2>> toLocalizedFileListV2(Map<String, Localized> map, final String str, final Function1 function1) {
        if (map == null) {
            return null;
        }
        return IndexConverterKt.mapValuesNotNull(map, new Function1() { // from class: org.fdroid.index.v1.AppV1$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppV1.toLocalizedFileListV2$lambda$19(function1, this, str, (Map.Entry) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List toLocalizedFileListV2$lambda$19(Function1 function1, AppV1 appV1, String str, Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        List<String> list = (List) function1.invoke(it.getValue());
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (String str2 : list) {
            arrayList.add(new FileV2("/" + appV1.packageName + "/" + it.getKey() + "/" + str + "/" + str2, (String) null, (Long) null, (String) null, 14, (DefaultConstructorMarker) null));
        }
        return arrayList;
    }
}
