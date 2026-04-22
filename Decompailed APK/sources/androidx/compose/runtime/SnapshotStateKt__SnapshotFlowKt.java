package androidx.compose.runtime;

import androidx.collection.MutableScatterSet;
import java.util.Set;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: SnapshotFlow.kt */
/* JADX INFO: loaded from: classes.dex */
abstract /* synthetic */ class SnapshotStateKt__SnapshotFlowKt {
    public static final State collectAsState(StateFlow stateFlow, CoroutineContext coroutineContext, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        CoroutineContext coroutineContext2 = coroutineContext;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1439883919, i, -1, "androidx.compose.runtime.collectAsState (SnapshotFlow.kt:49)");
        }
        State stateCollectAsState = SnapshotStateKt.collectAsState(stateFlow, stateFlow.getValue(), coroutineContext2, composer, (i & 14) | ((i << 3) & 896), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateCollectAsState;
    }

    public static final State collectAsState(Flow flow, Object obj, CoroutineContext coroutineContext, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        CoroutineContext coroutineContext2 = coroutineContext;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-606625098, i, -1, "androidx.compose.runtime.collectAsState (SnapshotFlow.kt:65)");
        }
        boolean zChangedInstance = composer.changedInstance(coroutineContext2) | composer.changedInstance(flow);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1(coroutineContext2, flow, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        State stateProduceState = SnapshotStateKt.produceState(obj, flow, coroutineContext2, (Function2) objRememberedValue, composer, ((i >> 3) & 14) | ((i << 3) & 112) | (i & 896));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateProduceState;
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1, reason: invalid class name */
    /* JADX INFO: compiled from: SnapshotFlow.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function0 $block;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Function0 function0, Continuation continuation) {
            super(2, continuation);
            this.$block = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$block, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector flowCollector, Continuation continuation) {
            return ((AnonymousClass1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Path cross not found for [B:34:0x00e1, B:38:0x00ea], limit reached: 76 */
        /* JADX WARN: Path cross not found for [B:42:0x00f9, B:58:0x0138], limit reached: 76 */
        /* JADX WARN: Path cross not found for [B:58:0x0138, B:42:0x00f9], limit reached: 76 */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00d5 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00d6  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00f9 A[Catch: all -> 0x0055, TRY_LEAVE, TryCatch #1 {all -> 0x0055, blocks: (B:32:0x00dd, B:34:0x00e1, B:39:0x00eb, B:42:0x00f9, B:46:0x010f, B:48:0x0118, B:56:0x0134, B:57:0x0137, B:15:0x0050, B:43:0x0104, B:45:0x010c, B:54:0x0130, B:55:0x0133), top: B:71:0x0050, inners: #2 }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 334
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final Flow snapshotFlow(Function0 function0) {
        return FlowKt.flow(new AnonymousClass1(function0, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean intersects$SnapshotStateKt__SnapshotFlowKt(MutableScatterSet mutableScatterSet, Set set) {
        Object[] objArr = mutableScatterSet.elements;
        long[] jArr = mutableScatterSet.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return false;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128 && set.contains(objArr[(i << 3) + i3])) {
                        return true;
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return false;
                }
            }
            if (i == length) {
                return false;
            }
            i++;
        }
    }
}
