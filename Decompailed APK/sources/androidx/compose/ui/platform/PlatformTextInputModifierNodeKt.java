package androidx.compose.ui.platform;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: PlatformTextInputModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PlatformTextInputModifierNodeKt {
    private static final ProvidableCompositionLocal LocalChainedPlatformTextInputInterceptor = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.PlatformTextInputModifierNodeKt$LocalChainedPlatformTextInputInterceptor$1
        @Override // kotlin.jvm.functions.Function0
        public final ChainedPlatformTextInputInterceptor invoke() {
            return null;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            invoke();
            return null;
        }
    });

    /* JADX INFO: renamed from: androidx.compose.ui.platform.PlatformTextInputModifierNodeKt$establishTextInputSession$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlatformTextInputModifierNode.kt */
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
            return PlatformTextInputModifierNodeKt.establishTextInputSession(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.PlatformTextInputModifierNodeKt$interceptedTextInputSession$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlatformTextInputModifierNode.kt */
    static final class C01591 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C01591(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlatformTextInputModifierNodeKt.interceptedTextInputSession(null, null, null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object establishTextInputSession(androidx.compose.ui.platform.PlatformTextInputModifierNode r4, kotlin.jvm.functions.Function2 r5, kotlin.coroutines.Continuation r6) {
        /*
            boolean r0 = r6 instanceof androidx.compose.ui.platform.PlatformTextInputModifierNodeKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.ui.platform.PlatformTextInputModifierNodeKt$establishTextInputSession$1 r0 = (androidx.compose.ui.platform.PlatformTextInputModifierNodeKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.platform.PlatformTextInputModifierNodeKt$establishTextInputSession$1 r0 = new androidx.compose.ui.platform.PlatformTextInputModifierNodeKt$establishTextInputSession$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5d
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.compose.ui.Modifier$Node r6 = r4.getNode()
            boolean r6 = r6.isAttached()
            if (r6 == 0) goto L63
            androidx.compose.ui.node.Owner r6 = androidx.compose.ui.node.DelegatableNodeKt.requireOwner(r4)
            androidx.compose.ui.node.LayoutNode r4 = androidx.compose.ui.node.DelegatableNodeKt.requireLayoutNode(r4)
            androidx.compose.runtime.CompositionLocalMap r4 = r4.getCompositionLocalMap()
            androidx.compose.runtime.ProvidableCompositionLocal r2 = androidx.compose.ui.platform.PlatformTextInputModifierNodeKt.LocalChainedPlatformTextInputInterceptor
            java.lang.Object r4 = r4.get(r2)
            androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(r4)
            r0.label = r3
            r4 = 0
            java.lang.Object r4 = interceptedTextInputSession(r6, r4, r5, r0)
            if (r4 != r1) goto L5d
            return r1
        L5d:
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        L63:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "establishTextInputSession called from an unattached node"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.PlatformTextInputModifierNodeKt.establishTextInputSession(androidx.compose.ui.platform.PlatformTextInputModifierNode, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object interceptedTextInputSession(androidx.compose.ui.node.Owner r3, androidx.compose.ui.platform.ChainedPlatformTextInputInterceptor r4, kotlin.jvm.functions.Function2 r5, kotlin.coroutines.Continuation r6) {
        /*
            boolean r4 = r6 instanceof androidx.compose.ui.platform.PlatformTextInputModifierNodeKt.C01591
            if (r4 == 0) goto L13
            r4 = r6
            androidx.compose.ui.platform.PlatformTextInputModifierNodeKt$interceptedTextInputSession$1 r4 = (androidx.compose.ui.platform.PlatformTextInputModifierNodeKt.C01591) r4
            int r0 = r4.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r0 & r1
            if (r2 == 0) goto L13
            int r0 = r0 - r1
            r4.label = r0
            goto L18
        L13:
            androidx.compose.ui.platform.PlatformTextInputModifierNodeKt$interceptedTextInputSession$1 r4 = new androidx.compose.ui.platform.PlatformTextInputModifierNodeKt$interceptedTextInputSession$1
            r4.<init>(r6)
        L18:
            java.lang.Object r6 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L3d
            if (r1 == r2) goto L39
            r3 = 2
            if (r1 == r3) goto L30
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r4)
            throw r3
        L30:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.KotlinNothingValueException r3 = new kotlin.KotlinNothingValueException
            r3.<init>()
            throw r3
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L49
        L3d:
            kotlin.ResultKt.throwOnFailure(r6)
            r4.label = r2
            java.lang.Object r3 = r3.textInputSession(r5, r4)
            if (r3 != r0) goto L49
            return r0
        L49:
            kotlin.KotlinNothingValueException r3 = new kotlin.KotlinNothingValueException
            r3.<init>()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.PlatformTextInputModifierNodeKt.interceptedTextInputSession(androidx.compose.ui.node.Owner, androidx.compose.ui.platform.ChainedPlatformTextInputInterceptor, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
