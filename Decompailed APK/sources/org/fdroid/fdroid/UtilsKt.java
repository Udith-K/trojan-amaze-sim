package org.fdroid.fdroid;

import android.graphics.Bitmap;
import android.text.format.DateUtils;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.DisplayCompat;
import ch.qos.logback.core.CoreConstants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.encode.QRCodeEncoder;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\u0086@¢\u0006\u0002\u0010\u0007\u001a\n\u0010\u000b\u001a\u00020\u0001*\u00020\f\u001aC\u0010\r\u001a\u00020\u000e*\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0010H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0017\u0010\b\u001a\u0004\u0018\u00010\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"TAG", "", "generateQrBitmapKt", "Landroid/graphics/Bitmap;", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "qrData", "(Landroidx/appcompat/app/AppCompatActivity;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flagEmoji", "getFlagEmoji", "(Ljava/lang/String;)Ljava/lang/String;", "asRelativeTimeString", "", "copy", "Landroidx/compose/foundation/layout/PaddingValues;", "start", "Landroidx/compose/ui/unit/Dp;", "top", "end", "bottom", "copy-cKdBLrg", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/ui/unit/Dp;Landroidx/compose/ui/unit/Dp;Landroidx/compose/ui/unit/Dp;Landroidx/compose/ui/unit/Dp;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/layout/PaddingValues;", "app_fullRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class UtilsKt {
    private static final String TAG = "Utils";

    /* JADX INFO: renamed from: org.fdroid.fdroid.UtilsKt$generateQrBitmapKt$1, reason: invalid class name */
    /* JADX INFO: compiled from: Utils.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.UtilsKt", f = "Utils.kt", l = {35}, m = "generateQrBitmapKt")
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return UtilsKt.generateQrBitmapKt(null, null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object generateQrBitmapKt(androidx.appcompat.app.AppCompatActivity r5, java.lang.String r6, kotlin.coroutines.Continuation r7) {
        /*
            boolean r0 = r7 instanceof org.fdroid.fdroid.UtilsKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            org.fdroid.fdroid.UtilsKt$generateQrBitmapKt$1 r0 = (org.fdroid.fdroid.UtilsKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            org.fdroid.fdroid.UtilsKt$generateQrBitmapKt$1 r0 = new org.fdroid.fdroid.UtilsKt$generateQrBitmapKt$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r7)
            goto L47
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getDefault()
            org.fdroid.fdroid.UtilsKt$generateQrBitmapKt$2 r2 = new org.fdroid.fdroid.UtilsKt$generateQrBitmapKt$2
            r4 = 0
            r2.<init>(r5, r6, r4)
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r2, r0)
            if (r7 != r1) goto L47
            return r1
        L47:
            java.lang.String r5 = "withContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.UtilsKt.generateQrBitmapKt(androidx.appcompat.app.AppCompatActivity, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.UtilsKt$generateQrBitmapKt$2, reason: invalid class name */
    /* JADX INFO: compiled from: Utils.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.UtilsKt$generateQrBitmapKt$2", f = "Utils.kt", l = {}, m = "invokeSuspend")
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ AppCompatActivity $activity;
        final /* synthetic */ String $qrData;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(AppCompatActivity appCompatActivity, String str, Continuation continuation) {
            super(2, continuation);
            this.$activity = appCompatActivity;
            this.$qrData = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$activity, this.$qrData, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AppCompatActivity appCompatActivity = this.$activity;
            DisplayCompat.ModeCompat mode = DisplayCompat.getMode(appCompatActivity, appCompatActivity.getWindowManager().getDefaultDisplay());
            Intrinsics.checkNotNullExpressionValue(mode, "getMode(...)");
            int iMin = Math.min(mode.getPhysicalWidth(), mode.getPhysicalHeight());
            Utils.debugLog(UtilsKt.TAG, "generating QRCode Bitmap of " + iMin + "x" + iMin);
            try {
                return new QRCodeEncoder(this.$qrData, null, "TEXT_TYPE", BarcodeFormat.QR_CODE.toString(), iMin).encodeAsBitmap();
            } catch (WriterException e) {
                Log.e(UtilsKt.TAG, "Could not encode QR as bitmap", e);
                return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            }
        }
    }

    public static final String getFlagEmoji(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (str.length() != 2) {
            return null;
        }
        String upperCase = str.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        char[] charArray = upperCase.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        int i = charArray[0] - 3675;
        int i2 = charArray[1] - 3675;
        char[] chars = Character.toChars(i);
        Intrinsics.checkNotNullExpressionValue(chars, "toChars(...)");
        char[] chars2 = Character.toChars(i2);
        Intrinsics.checkNotNullExpressionValue(chars2, "toChars(...)");
        return new String(ArraysKt.plus(chars, chars2));
    }

    public static final String asRelativeTimeString(long j) {
        return DateUtils.getRelativeTimeSpanString(j, System.currentTimeMillis(), CoreConstants.MILLIS_IN_ONE_MINUTE, PKIFailureInfo.signerNotTrusted).toString();
    }

    /* JADX INFO: renamed from: copy-cKdBLrg, reason: not valid java name */
    public static final PaddingValues m2961copycKdBLrg(PaddingValues copy, Dp dp, Dp dp2, Dp dp3, Dp dp4, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(copy, "$this$copy");
        composer.startReplaceGroup(393674536);
        if ((i2 & 1) != 0) {
            dp = null;
        }
        if ((i2 & 2) != 0) {
            dp2 = null;
        }
        if ((i2 & 4) != 0) {
            dp3 = null;
        }
        if ((i2 & 8) != 0) {
            dp4 = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(393674536, i, -1, "org.fdroid.fdroid.copy (Utils.kt:81)");
        }
        LayoutDirection layoutDirection = (LayoutDirection) composer.consume(CompositionLocalsKt.getLocalLayoutDirection());
        PaddingValues paddingValuesM260PaddingValuesa9UjIt4 = PaddingKt.m260PaddingValuesa9UjIt4(dp != null ? dp.m2422unboximpl() : PaddingKt.calculateStartPadding(copy, layoutDirection), dp2 != null ? dp2.m2422unboximpl() : copy.mo255calculateTopPaddingD9Ej5fM(), dp3 != null ? dp3.m2422unboximpl() : PaddingKt.calculateEndPadding(copy, layoutDirection), dp4 != null ? dp4.m2422unboximpl() : copy.mo252calculateBottomPaddingD9Ej5fM());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return paddingValuesM260PaddingValuesa9UjIt4;
    }
}
