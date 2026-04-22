package org.fdroid.index.v2;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.i18n.ErrorBundle;
import org.fdroid.fdroid.views.apps.AppListActivity;

/* JADX INFO: compiled from: PackageV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u0082\u00012\u00020\u0001:\u0004\u0081\u0001\u0082\u0001Bÿ\u0003\u0012\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005\u0012\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005\u0012\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0004\u0012\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0013\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0004\u0012\u001c\b\u0002\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!\u0012\u001c\b\u0002\u0010\"\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!\u0012\u001c\b\u0002\u0010#\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!\u0012\u001c\b\u0002\u0010$\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!\u0012\u001c\b\u0002\u0010%\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'¢\u0006\u0004\b(\u0010)B¯\u0003\b\u0010\u0012\u0006\u0010*\u001a\u00020+\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004\u0012\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0013\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004\u0012\u0014\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003\u0012\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003\u0012\u0014\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003\u0012\u0014\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003\u0012\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010&\u001a\u0004\u0018\u00010'\u0012\b\u0010,\u001a\u0004\u0018\u00010-¢\u0006\u0004\b(\u0010.J\u001c\u0010R\u001a\u00020S2\u0014\u0010T\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010 \u0012\u0004\u0012\u00020S0UJ\u001d\u0010V\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005HÆ\u0003J\u001d\u0010W\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005HÆ\u0003J\u001d\u0010X\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005HÆ\u0003J\t\u0010Y\u001a\u00020\tHÆ\u0003J\t\u0010Z\u001a\u00020\tHÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0013HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00040\u0013HÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u001d\u0010n\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!HÆ\u0003J\u001d\u0010o\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!HÆ\u0003J\u001d\u0010p\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!HÆ\u0003J\u001d\u0010q\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!HÆ\u0003J\u001d\u0010r\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005HÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010'HÆ\u0003J\u0085\u0004\u0010t\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u00052\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u00052\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00042\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u00132\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00042\u001c\b\u0002\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!2\u001c\b\u0002\u0010\"\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!2\u001c\b\u0002\u0010#\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!2\u001c\b\u0002\u0010$\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!2\u001c\b\u0002\u0010%\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u00052\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'HÆ\u0001J\u0013\u0010u\u001a\u00020v2\b\u0010w\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010x\u001a\u00020+HÖ\u0001J\t\u0010y\u001a\u00020\u0004HÖ\u0001J&\u0010z\u001a\u00020S2\u0006\u0010{\u001a\u00020\u00002\u0006\u0010|\u001a\u00020}2\u0006\u0010~\u001a\u00020\u007fH\u0001¢\u0006\u0003\b\u0080\u0001R%\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R%\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u00100R%\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u00100R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b5\u00104R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u00107R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u00107R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u00107R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u00107R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u00107R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u00107R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0013¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u00107R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u00107R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u00107R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u00107R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0013¢\u0006\b\n\u0000\u001a\u0004\bD\u0010?R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u00107R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u00107R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u00107R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u00107R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u00107R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u00107R%\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!¢\u0006\b\n\u0000\u001a\u0004\bK\u00100R%\u0010\"\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!¢\u0006\b\n\u0000\u001a\u0004\bL\u00100R%\u0010#\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!¢\u0006\b\n\u0000\u001a\u0004\bM\u00100R%\u0010$\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 \u0018\u00010\u0003j\u0004\u0018\u0001`!¢\u0006\b\n\u0000\u001a\u0004\bN\u00100R%\u0010%\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\bO\u00100R\u0013\u0010&\u001a\u0004\u0018\u00010'¢\u0006\b\n\u0000\u001a\u0004\bP\u0010Q¨\u0006\u0083\u0001"}, d2 = {"Lorg/fdroid/index/v2/MetadataV2;", "", "name", "", "", "Lorg/fdroid/index/v2/LocalizedTextV2;", ErrorBundle.SUMMARY_ENTRY, "description", "added", "", AppListActivity.SortClause.LAST_UPDATED, "webSite", "changelog", "license", "sourceCode", "issueTracker", "translation", "preferredSigner", "categories", "", "authorName", "authorEmail", "authorWebSite", "authorPhone", "donate", "liberapayID", "liberapay", "openCollective", "bitcoin", "litecoin", "flattrID", "icon", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileV2;", "featureGraphic", "promoGraphic", "tvBanner", "video", "screenshots", "Lorg/fdroid/index/v2/Screenshots;", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lorg/fdroid/index/v2/Screenshots;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/util/Map;Ljava/util/Map;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lorg/fdroid/index/v2/Screenshots;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName", "()Ljava/util/Map;", "getSummary", "getDescription", "getAdded", "()J", "getLastUpdated", "getWebSite", "()Ljava/lang/String;", "getChangelog", "getLicense", "getSourceCode", "getIssueTracker", "getTranslation", "getPreferredSigner", "getCategories", "()Ljava/util/List;", "getAuthorName", "getAuthorEmail", "getAuthorWebSite", "getAuthorPhone", "getDonate", "getLiberapayID", "getLiberapay", "getOpenCollective", "getBitcoin", "getLitecoin", "getFlattrID", "getIcon", "getFeatureGraphic", "getPromoGraphic", "getTvBanner", "getVideo", "getScreenshots", "()Lorg/fdroid/index/v2/Screenshots;", "walkFiles", "", "fileConsumer", "Lkotlin/Function1;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class MetadataV2 {
    private static final KSerializer[] $childSerializers;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long added;
    private final String authorEmail;
    private final String authorName;
    private final String authorPhone;
    private final String authorWebSite;
    private final String bitcoin;
    private final List<String> categories;
    private final String changelog;
    private final Map<String, String> description;
    private final List<String> donate;
    private final Map<String, FileV2> featureGraphic;
    private final String flattrID;
    private final Map<String, FileV2> icon;
    private final String issueTracker;
    private final long lastUpdated;
    private final String liberapay;
    private final String liberapayID;
    private final String license;
    private final String litecoin;
    private final Map<String, String> name;
    private final String openCollective;
    private final String preferredSigner;
    private final Map<String, FileV2> promoGraphic;
    private final Screenshots screenshots;
    private final String sourceCode;
    private final Map<String, String> summary;
    private final String translation;
    private final Map<String, FileV2> tvBanner;
    private final Map<String, String> video;
    private final String webSite;

    public final Map<String, String> component1() {
        return this.name;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getIssueTracker() {
        return this.issueTracker;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getTranslation() {
        return this.translation;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getPreferredSigner() {
        return this.preferredSigner;
    }

    public final List<String> component13() {
        return this.categories;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getAuthorName() {
        return this.authorName;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getAuthorEmail() {
        return this.authorEmail;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getAuthorWebSite() {
        return this.authorWebSite;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getAuthorPhone() {
        return this.authorPhone;
    }

    public final List<String> component18() {
        return this.donate;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getLiberapayID() {
        return this.liberapayID;
    }

    public final Map<String, String> component2() {
        return this.summary;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getLiberapay() {
        return this.liberapay;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getOpenCollective() {
        return this.openCollective;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getBitcoin() {
        return this.bitcoin;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getLitecoin() {
        return this.litecoin;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getFlattrID() {
        return this.flattrID;
    }

    public final Map<String, FileV2> component25() {
        return this.icon;
    }

    public final Map<String, FileV2> component26() {
        return this.featureGraphic;
    }

    public final Map<String, FileV2> component27() {
        return this.promoGraphic;
    }

    public final Map<String, FileV2> component28() {
        return this.tvBanner;
    }

    public final Map<String, String> component29() {
        return this.video;
    }

    public final Map<String, String> component3() {
        return this.description;
    }

    /* JADX INFO: renamed from: component30, reason: from getter */
    public final Screenshots getScreenshots() {
        return this.screenshots;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getAdded() {
        return this.added;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getLastUpdated() {
        return this.lastUpdated;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getWebSite() {
        return this.webSite;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getChangelog() {
        return this.changelog;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getLicense() {
        return this.license;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getSourceCode() {
        return this.sourceCode;
    }

    public final MetadataV2 copy(Map<String, String> name, Map<String, String> summary, Map<String, String> description, long added, long lastUpdated, String webSite, String changelog, String license, String sourceCode, String issueTracker, String translation, String preferredSigner, List<String> categories, String authorName, String authorEmail, String authorWebSite, String authorPhone, List<String> donate, String liberapayID, String liberapay, String openCollective, String bitcoin, String litecoin, String flattrID, Map<String, FileV2> icon, Map<String, FileV2> featureGraphic, Map<String, FileV2> promoGraphic, Map<String, FileV2> tvBanner, Map<String, String> video, Screenshots screenshots) {
        Intrinsics.checkNotNullParameter(categories, "categories");
        Intrinsics.checkNotNullParameter(donate, "donate");
        return new MetadataV2(name, summary, description, added, lastUpdated, webSite, changelog, license, sourceCode, issueTracker, translation, preferredSigner, categories, authorName, authorEmail, authorWebSite, authorPhone, donate, liberapayID, liberapay, openCollective, bitcoin, litecoin, flattrID, icon, featureGraphic, promoGraphic, tvBanner, video, screenshots);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MetadataV2)) {
            return false;
        }
        MetadataV2 metadataV2 = (MetadataV2) other;
        return Intrinsics.areEqual(this.name, metadataV2.name) && Intrinsics.areEqual(this.summary, metadataV2.summary) && Intrinsics.areEqual(this.description, metadataV2.description) && this.added == metadataV2.added && this.lastUpdated == metadataV2.lastUpdated && Intrinsics.areEqual(this.webSite, metadataV2.webSite) && Intrinsics.areEqual(this.changelog, metadataV2.changelog) && Intrinsics.areEqual(this.license, metadataV2.license) && Intrinsics.areEqual(this.sourceCode, metadataV2.sourceCode) && Intrinsics.areEqual(this.issueTracker, metadataV2.issueTracker) && Intrinsics.areEqual(this.translation, metadataV2.translation) && Intrinsics.areEqual(this.preferredSigner, metadataV2.preferredSigner) && Intrinsics.areEqual(this.categories, metadataV2.categories) && Intrinsics.areEqual(this.authorName, metadataV2.authorName) && Intrinsics.areEqual(this.authorEmail, metadataV2.authorEmail) && Intrinsics.areEqual(this.authorWebSite, metadataV2.authorWebSite) && Intrinsics.areEqual(this.authorPhone, metadataV2.authorPhone) && Intrinsics.areEqual(this.donate, metadataV2.donate) && Intrinsics.areEqual(this.liberapayID, metadataV2.liberapayID) && Intrinsics.areEqual(this.liberapay, metadataV2.liberapay) && Intrinsics.areEqual(this.openCollective, metadataV2.openCollective) && Intrinsics.areEqual(this.bitcoin, metadataV2.bitcoin) && Intrinsics.areEqual(this.litecoin, metadataV2.litecoin) && Intrinsics.areEqual(this.flattrID, metadataV2.flattrID) && Intrinsics.areEqual(this.icon, metadataV2.icon) && Intrinsics.areEqual(this.featureGraphic, metadataV2.featureGraphic) && Intrinsics.areEqual(this.promoGraphic, metadataV2.promoGraphic) && Intrinsics.areEqual(this.tvBanner, metadataV2.tvBanner) && Intrinsics.areEqual(this.video, metadataV2.video) && Intrinsics.areEqual(this.screenshots, metadataV2.screenshots);
    }

    public int hashCode() {
        Map<String, String> map = this.name;
        int iHashCode = (map == null ? 0 : map.hashCode()) * 31;
        Map<String, String> map2 = this.summary;
        int iHashCode2 = (iHashCode + (map2 == null ? 0 : map2.hashCode())) * 31;
        Map<String, String> map3 = this.description;
        int iHashCode3 = (((((iHashCode2 + (map3 == null ? 0 : map3.hashCode())) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.added)) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.lastUpdated)) * 31;
        String str = this.webSite;
        int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.changelog;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.license;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.sourceCode;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.issueTracker;
        int iHashCode8 = (iHashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.translation;
        int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.preferredSigner;
        int iHashCode10 = (((iHashCode9 + (str7 == null ? 0 : str7.hashCode())) * 31) + this.categories.hashCode()) * 31;
        String str8 = this.authorName;
        int iHashCode11 = (iHashCode10 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.authorEmail;
        int iHashCode12 = (iHashCode11 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.authorWebSite;
        int iHashCode13 = (iHashCode12 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.authorPhone;
        int iHashCode14 = (((iHashCode13 + (str11 == null ? 0 : str11.hashCode())) * 31) + this.donate.hashCode()) * 31;
        String str12 = this.liberapayID;
        int iHashCode15 = (iHashCode14 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.liberapay;
        int iHashCode16 = (iHashCode15 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.openCollective;
        int iHashCode17 = (iHashCode16 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.bitcoin;
        int iHashCode18 = (iHashCode17 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.litecoin;
        int iHashCode19 = (iHashCode18 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.flattrID;
        int iHashCode20 = (iHashCode19 + (str17 == null ? 0 : str17.hashCode())) * 31;
        Map<String, FileV2> map4 = this.icon;
        int iHashCode21 = (iHashCode20 + (map4 == null ? 0 : map4.hashCode())) * 31;
        Map<String, FileV2> map5 = this.featureGraphic;
        int iHashCode22 = (iHashCode21 + (map5 == null ? 0 : map5.hashCode())) * 31;
        Map<String, FileV2> map6 = this.promoGraphic;
        int iHashCode23 = (iHashCode22 + (map6 == null ? 0 : map6.hashCode())) * 31;
        Map<String, FileV2> map7 = this.tvBanner;
        int iHashCode24 = (iHashCode23 + (map7 == null ? 0 : map7.hashCode())) * 31;
        Map<String, String> map8 = this.video;
        int iHashCode25 = (iHashCode24 + (map8 == null ? 0 : map8.hashCode())) * 31;
        Screenshots screenshots = this.screenshots;
        return iHashCode25 + (screenshots != null ? screenshots.hashCode() : 0);
    }

    public String toString() {
        return "MetadataV2(name=" + this.name + ", summary=" + this.summary + ", description=" + this.description + ", added=" + this.added + ", lastUpdated=" + this.lastUpdated + ", webSite=" + this.webSite + ", changelog=" + this.changelog + ", license=" + this.license + ", sourceCode=" + this.sourceCode + ", issueTracker=" + this.issueTracker + ", translation=" + this.translation + ", preferredSigner=" + this.preferredSigner + ", categories=" + this.categories + ", authorName=" + this.authorName + ", authorEmail=" + this.authorEmail + ", authorWebSite=" + this.authorWebSite + ", authorPhone=" + this.authorPhone + ", donate=" + this.donate + ", liberapayID=" + this.liberapayID + ", liberapay=" + this.liberapay + ", openCollective=" + this.openCollective + ", bitcoin=" + this.bitcoin + ", litecoin=" + this.litecoin + ", flattrID=" + this.flattrID + ", icon=" + this.icon + ", featureGraphic=" + this.featureGraphic + ", promoGraphic=" + this.promoGraphic + ", tvBanner=" + this.tvBanner + ", video=" + this.video + ", screenshots=" + this.screenshots + ")";
    }

    /* JADX INFO: compiled from: PackageV2.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v2/MetadataV2$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v2/MetadataV2;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return MetadataV2$$serializer.INSTANCE;
        }
    }

    static {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        LinkedHashMapSerializer linkedHashMapSerializer = new LinkedHashMapSerializer(stringSerializer, stringSerializer);
        LinkedHashMapSerializer linkedHashMapSerializer2 = new LinkedHashMapSerializer(stringSerializer, stringSerializer);
        LinkedHashMapSerializer linkedHashMapSerializer3 = new LinkedHashMapSerializer(stringSerializer, stringSerializer);
        ArrayListSerializer arrayListSerializer = new ArrayListSerializer(stringSerializer);
        ArrayListSerializer arrayListSerializer2 = new ArrayListSerializer(stringSerializer);
        FileV2$$serializer fileV2$$serializer = FileV2$$serializer.INSTANCE;
        $childSerializers = new KSerializer[]{linkedHashMapSerializer, linkedHashMapSerializer2, linkedHashMapSerializer3, null, null, null, null, null, null, null, null, null, arrayListSerializer, null, null, null, null, arrayListSerializer2, null, null, null, null, null, null, new LinkedHashMapSerializer(stringSerializer, fileV2$$serializer), new LinkedHashMapSerializer(stringSerializer, fileV2$$serializer), new LinkedHashMapSerializer(stringSerializer, fileV2$$serializer), new LinkedHashMapSerializer(stringSerializer, fileV2$$serializer), new LinkedHashMapSerializer(stringSerializer, stringSerializer), null};
    }

    public /* synthetic */ MetadataV2(int i, Map map, Map map2, Map map3, long j, long j2, String str, String str2, String str3, String str4, String str5, String str6, String str7, List list, String str8, String str9, String str10, String str11, List list2, String str12, String str13, String str14, String str15, String str16, String str17, Map map4, Map map5, Map map6, Map map7, Map map8, Screenshots screenshots, SerializationConstructorMarker serializationConstructorMarker) {
        if (24 != (i & 24)) {
            PluginExceptionsKt.throwMissingFieldException(i, 24, MetadataV2$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.name = null;
        } else {
            this.name = map;
        }
        if ((i & 2) == 0) {
            this.summary = null;
        } else {
            this.summary = map2;
        }
        if ((i & 4) == 0) {
            this.description = null;
        } else {
            this.description = map3;
        }
        this.added = j;
        this.lastUpdated = j2;
        if ((i & 32) == 0) {
            this.webSite = null;
        } else {
            this.webSite = str;
        }
        if ((i & 64) == 0) {
            this.changelog = null;
        } else {
            this.changelog = str2;
        }
        if ((i & 128) == 0) {
            this.license = null;
        } else {
            this.license = str3;
        }
        if ((i & 256) == 0) {
            this.sourceCode = null;
        } else {
            this.sourceCode = str4;
        }
        if ((i & 512) == 0) {
            this.issueTracker = null;
        } else {
            this.issueTracker = str5;
        }
        if ((i & 1024) == 0) {
            this.translation = null;
        } else {
            this.translation = str6;
        }
        if ((i & 2048) == 0) {
            this.preferredSigner = null;
        } else {
            this.preferredSigner = str7;
        }
        this.categories = (i & PKIFailureInfo.certConfirmed) == 0 ? CollectionsKt.emptyList() : list;
        if ((i & 8192) == 0) {
            this.authorName = null;
        } else {
            this.authorName = str8;
        }
        if ((i & 16384) == 0) {
            this.authorEmail = null;
        } else {
            this.authorEmail = str9;
        }
        if ((32768 & i) == 0) {
            this.authorWebSite = null;
        } else {
            this.authorWebSite = str10;
        }
        if ((65536 & i) == 0) {
            this.authorPhone = null;
        } else {
            this.authorPhone = str11;
        }
        this.donate = (131072 & i) == 0 ? CollectionsKt.emptyList() : list2;
        if ((262144 & i) == 0) {
            this.liberapayID = null;
        } else {
            this.liberapayID = str12;
        }
        if ((524288 & i) == 0) {
            this.liberapay = null;
        } else {
            this.liberapay = str13;
        }
        if ((1048576 & i) == 0) {
            this.openCollective = null;
        } else {
            this.openCollective = str14;
        }
        if ((2097152 & i) == 0) {
            this.bitcoin = null;
        } else {
            this.bitcoin = str15;
        }
        if ((4194304 & i) == 0) {
            this.litecoin = null;
        } else {
            this.litecoin = str16;
        }
        if ((8388608 & i) == 0) {
            this.flattrID = null;
        } else {
            this.flattrID = str17;
        }
        if ((16777216 & i) == 0) {
            this.icon = null;
        } else {
            this.icon = map4;
        }
        if ((33554432 & i) == 0) {
            this.featureGraphic = null;
        } else {
            this.featureGraphic = map5;
        }
        if ((67108864 & i) == 0) {
            this.promoGraphic = null;
        } else {
            this.promoGraphic = map6;
        }
        if ((134217728 & i) == 0) {
            this.tvBanner = null;
        } else {
            this.tvBanner = map7;
        }
        if ((268435456 & i) == 0) {
            this.video = null;
        } else {
            this.video = map8;
        }
        if ((i & PKIFailureInfo.duplicateCertReq) == 0) {
            this.screenshots = null;
        } else {
            this.screenshots = screenshots;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(MetadataV2 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.name != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, kSerializerArr[0], self.name);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.summary != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, kSerializerArr[1], self.summary);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.description != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, kSerializerArr[2], self.description);
        }
        output.encodeLongElement(serialDesc, 3, self.added);
        output.encodeLongElement(serialDesc, 4, self.lastUpdated);
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.webSite != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.webSite);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.changelog != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.changelog);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.license != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.license);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.sourceCode != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.sourceCode);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 9) || self.issueTracker != null) {
            output.encodeNullableSerializableElement(serialDesc, 9, StringSerializer.INSTANCE, self.issueTracker);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 10) || self.translation != null) {
            output.encodeNullableSerializableElement(serialDesc, 10, StringSerializer.INSTANCE, self.translation);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 11) || self.preferredSigner != null) {
            output.encodeNullableSerializableElement(serialDesc, 11, StringSerializer.INSTANCE, self.preferredSigner);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 12) || !Intrinsics.areEqual(self.categories, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 12, kSerializerArr[12], self.categories);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 13) || self.authorName != null) {
            output.encodeNullableSerializableElement(serialDesc, 13, StringSerializer.INSTANCE, self.authorName);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 14) || self.authorEmail != null) {
            output.encodeNullableSerializableElement(serialDesc, 14, StringSerializer.INSTANCE, self.authorEmail);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 15) || self.authorWebSite != null) {
            output.encodeNullableSerializableElement(serialDesc, 15, StringSerializer.INSTANCE, self.authorWebSite);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 16) || self.authorPhone != null) {
            output.encodeNullableSerializableElement(serialDesc, 16, StringSerializer.INSTANCE, self.authorPhone);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 17) || !Intrinsics.areEqual(self.donate, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 17, kSerializerArr[17], self.donate);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 18) || self.liberapayID != null) {
            output.encodeNullableSerializableElement(serialDesc, 18, StringSerializer.INSTANCE, self.liberapayID);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 19) || self.liberapay != null) {
            output.encodeNullableSerializableElement(serialDesc, 19, StringSerializer.INSTANCE, self.liberapay);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 20) || self.openCollective != null) {
            output.encodeNullableSerializableElement(serialDesc, 20, StringSerializer.INSTANCE, self.openCollective);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 21) || self.bitcoin != null) {
            output.encodeNullableSerializableElement(serialDesc, 21, StringSerializer.INSTANCE, self.bitcoin);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 22) || self.litecoin != null) {
            output.encodeNullableSerializableElement(serialDesc, 22, StringSerializer.INSTANCE, self.litecoin);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 23) || self.flattrID != null) {
            output.encodeNullableSerializableElement(serialDesc, 23, StringSerializer.INSTANCE, self.flattrID);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 24) || self.icon != null) {
            output.encodeNullableSerializableElement(serialDesc, 24, kSerializerArr[24], self.icon);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 25) || self.featureGraphic != null) {
            output.encodeNullableSerializableElement(serialDesc, 25, kSerializerArr[25], self.featureGraphic);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 26) || self.promoGraphic != null) {
            output.encodeNullableSerializableElement(serialDesc, 26, kSerializerArr[26], self.promoGraphic);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 27) || self.tvBanner != null) {
            output.encodeNullableSerializableElement(serialDesc, 27, kSerializerArr[27], self.tvBanner);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 28) || self.video != null) {
            output.encodeNullableSerializableElement(serialDesc, 28, kSerializerArr[28], self.video);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 29) && self.screenshots == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 29, Screenshots$$serializer.INSTANCE, self.screenshots);
    }

    public MetadataV2(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, long j, long j2, String str, String str2, String str3, String str4, String str5, String str6, String str7, List<String> categories, String str8, String str9, String str10, String str11, List<String> donate, String str12, String str13, String str14, String str15, String str16, String str17, Map<String, FileV2> map4, Map<String, FileV2> map5, Map<String, FileV2> map6, Map<String, FileV2> map7, Map<String, String> map8, Screenshots screenshots) {
        Intrinsics.checkNotNullParameter(categories, "categories");
        Intrinsics.checkNotNullParameter(donate, "donate");
        this.name = map;
        this.summary = map2;
        this.description = map3;
        this.added = j;
        this.lastUpdated = j2;
        this.webSite = str;
        this.changelog = str2;
        this.license = str3;
        this.sourceCode = str4;
        this.issueTracker = str5;
        this.translation = str6;
        this.preferredSigner = str7;
        this.categories = categories;
        this.authorName = str8;
        this.authorEmail = str9;
        this.authorWebSite = str10;
        this.authorPhone = str11;
        this.donate = donate;
        this.liberapayID = str12;
        this.liberapay = str13;
        this.openCollective = str14;
        this.bitcoin = str15;
        this.litecoin = str16;
        this.flattrID = str17;
        this.icon = map4;
        this.featureGraphic = map5;
        this.promoGraphic = map6;
        this.tvBanner = map7;
        this.video = map8;
        this.screenshots = screenshots;
    }

    public final Map<String, String> getName() {
        return this.name;
    }

    public final Map<String, String> getSummary() {
        return this.summary;
    }

    public final Map<String, String> getDescription() {
        return this.description;
    }

    public final long getAdded() {
        return this.added;
    }

    public final long getLastUpdated() {
        return this.lastUpdated;
    }

    public final String getWebSite() {
        return this.webSite;
    }

    public final String getChangelog() {
        return this.changelog;
    }

    public final String getLicense() {
        return this.license;
    }

    public final String getSourceCode() {
        return this.sourceCode;
    }

    public final String getIssueTracker() {
        return this.issueTracker;
    }

    public final String getTranslation() {
        return this.translation;
    }

    public final String getPreferredSigner() {
        return this.preferredSigner;
    }

    public /* synthetic */ MetadataV2(Map map, Map map2, Map map3, long j, long j2, String str, String str2, String str3, String str4, String str5, String str6, String str7, List list, String str8, String str9, String str10, String str11, List list2, String str12, String str13, String str14, String str15, String str16, String str17, Map map4, Map map5, Map map6, Map map7, Map map8, Screenshots screenshots, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : map, (i & 2) != 0 ? null : map2, (i & 4) != 0 ? null : map3, j, j2, (i & 32) != 0 ? null : str, (i & 64) != 0 ? null : str2, (i & 128) != 0 ? null : str3, (i & 256) != 0 ? null : str4, (i & 512) != 0 ? null : str5, (i & 1024) != 0 ? null : str6, (i & 2048) != 0 ? null : str7, (i & PKIFailureInfo.certConfirmed) != 0 ? CollectionsKt.emptyList() : list, (i & 8192) != 0 ? null : str8, (i & 16384) != 0 ? null : str9, (32768 & i) != 0 ? null : str10, (65536 & i) != 0 ? null : str11, (131072 & i) != 0 ? CollectionsKt.emptyList() : list2, (262144 & i) != 0 ? null : str12, (524288 & i) != 0 ? null : str13, (1048576 & i) != 0 ? null : str14, (2097152 & i) != 0 ? null : str15, (4194304 & i) != 0 ? null : str16, (8388608 & i) != 0 ? null : str17, (16777216 & i) != 0 ? null : map4, (33554432 & i) != 0 ? null : map5, (67108864 & i) != 0 ? null : map6, (134217728 & i) != 0 ? null : map7, (268435456 & i) != 0 ? null : map8, (i & PKIFailureInfo.duplicateCertReq) != 0 ? null : screenshots);
    }

    public final List<String> getCategories() {
        return this.categories;
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

    public final List<String> getDonate() {
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

    public final Map<String, FileV2> getIcon() {
        return this.icon;
    }

    public final Map<String, FileV2> getFeatureGraphic() {
        return this.featureGraphic;
    }

    public final Map<String, FileV2> getPromoGraphic() {
        return this.promoGraphic;
    }

    public final Map<String, FileV2> getTvBanner() {
        return this.tvBanner;
    }

    public final Map<String, String> getVideo() {
        return this.video;
    }

    public final Screenshots getScreenshots() {
        return this.screenshots;
    }

    public final void walkFiles(Function1 fileConsumer) {
        Map<String, List<FileV2>> tv;
        Collection<List<FileV2>> collectionValues;
        Map<String, List<FileV2>> wear;
        Collection<List<FileV2>> collectionValues2;
        Map<String, List<FileV2>> tenInch;
        Collection<List<FileV2>> collectionValues3;
        Map<String, List<FileV2>> sevenInch;
        Collection<List<FileV2>> collectionValues4;
        Map<String, List<FileV2>> phone;
        Collection<List<FileV2>> collectionValues5;
        Collection<FileV2> collectionValues6;
        Collection<FileV2> collectionValues7;
        Collection<FileV2> collectionValues8;
        Collection<FileV2> collectionValues9;
        Intrinsics.checkNotNullParameter(fileConsumer, "fileConsumer");
        Map<String, FileV2> map = this.icon;
        if (map != null && (collectionValues9 = map.values()) != null) {
            Iterator<T> it = collectionValues9.iterator();
            while (it.hasNext()) {
                fileConsumer.invoke((FileV2) it.next());
            }
        }
        Map<String, FileV2> map2 = this.featureGraphic;
        if (map2 != null && (collectionValues8 = map2.values()) != null) {
            Iterator<T> it2 = collectionValues8.iterator();
            while (it2.hasNext()) {
                fileConsumer.invoke((FileV2) it2.next());
            }
        }
        Map<String, FileV2> map3 = this.promoGraphic;
        if (map3 != null && (collectionValues7 = map3.values()) != null) {
            Iterator<T> it3 = collectionValues7.iterator();
            while (it3.hasNext()) {
                fileConsumer.invoke((FileV2) it3.next());
            }
        }
        Map<String, FileV2> map4 = this.tvBanner;
        if (map4 != null && (collectionValues6 = map4.values()) != null) {
            Iterator<T> it4 = collectionValues6.iterator();
            while (it4.hasNext()) {
                fileConsumer.invoke((FileV2) it4.next());
            }
        }
        Screenshots screenshots = this.screenshots;
        if (screenshots != null && (phone = screenshots.getPhone()) != null && (collectionValues5 = phone.values()) != null) {
            Iterator<T> it5 = collectionValues5.iterator();
            while (it5.hasNext()) {
                Iterator it6 = ((List) it5.next()).iterator();
                while (it6.hasNext()) {
                    fileConsumer.invoke(it6.next());
                }
            }
        }
        Screenshots screenshots2 = this.screenshots;
        if (screenshots2 != null && (sevenInch = screenshots2.getSevenInch()) != null && (collectionValues4 = sevenInch.values()) != null) {
            Iterator<T> it7 = collectionValues4.iterator();
            while (it7.hasNext()) {
                Iterator it8 = ((List) it7.next()).iterator();
                while (it8.hasNext()) {
                    fileConsumer.invoke(it8.next());
                }
            }
        }
        Screenshots screenshots3 = this.screenshots;
        if (screenshots3 != null && (tenInch = screenshots3.getTenInch()) != null && (collectionValues3 = tenInch.values()) != null) {
            Iterator<T> it9 = collectionValues3.iterator();
            while (it9.hasNext()) {
                Iterator it10 = ((List) it9.next()).iterator();
                while (it10.hasNext()) {
                    fileConsumer.invoke(it10.next());
                }
            }
        }
        Screenshots screenshots4 = this.screenshots;
        if (screenshots4 != null && (wear = screenshots4.getWear()) != null && (collectionValues2 = wear.values()) != null) {
            Iterator<T> it11 = collectionValues2.iterator();
            while (it11.hasNext()) {
                Iterator it12 = ((List) it11.next()).iterator();
                while (it12.hasNext()) {
                    fileConsumer.invoke(it12.next());
                }
            }
        }
        Screenshots screenshots5 = this.screenshots;
        if (screenshots5 == null || (tv = screenshots5.getTv()) == null || (collectionValues = tv.values()) == null) {
            return;
        }
        Iterator<T> it13 = collectionValues.iterator();
        while (it13.hasNext()) {
            Iterator it14 = ((List) it13.next()).iterator();
            while (it14.hasNext()) {
                fileConsumer.invoke(it14.next());
            }
        }
    }
}
