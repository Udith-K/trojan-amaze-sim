package androidx.work.impl.constraints;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import androidx.work.Constraints;
import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintsState;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.model.WorkSpec;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: WorkConstraintsTracker.kt */
/* JADX INFO: loaded from: classes.dex */
public final class NetworkRequestConstraintController implements ConstraintController {
    private final ConnectivityManager connManager;
    private final long timeoutMs;

    public NetworkRequestConstraintController(ConnectivityManager connManager, long j) {
        Intrinsics.checkNotNullParameter(connManager, "connManager");
        this.connManager = connManager;
        this.timeoutMs = j;
    }

    public /* synthetic */ NetworkRequestConstraintController(ConnectivityManager connectivityManager, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(connectivityManager, (i & 2) != 0 ? WorkConstraintsTrackerKt.DefaultNetworkRequestTimeoutMs : j);
    }

    /* JADX INFO: renamed from: androidx.work.impl.constraints.NetworkRequestConstraintController$track$1, reason: invalid class name */
    /* JADX INFO: compiled from: WorkConstraintsTracker.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Constraints $constraints;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ NetworkRequestConstraintController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Constraints constraints, NetworkRequestConstraintController networkRequestConstraintController, Continuation continuation) {
            super(2, continuation);
            this.$constraints = constraints;
            this.this$0 = networkRequestConstraintController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$constraints, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope producerScope, Continuation continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v2, types: [android.net.ConnectivityManager$NetworkCallback, androidx.work.impl.constraints.NetworkRequestConstraintController$track$1$networkCallback$1] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope = (ProducerScope) this.L$0;
                NetworkRequest requiredNetworkRequest = this.$constraints.getRequiredNetworkRequest();
                if (requiredNetworkRequest != null) {
                    final Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(producerScope, null, null, new NetworkRequestConstraintController$track$1$job$1(this.this$0, producerScope, null), 3, null);
                    final ?? r4 = new ConnectivityManager.NetworkCallback() { // from class: androidx.work.impl.constraints.NetworkRequestConstraintController$track$1$networkCallback$1
                        @Override // android.net.ConnectivityManager.NetworkCallback
                        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                            Intrinsics.checkNotNullParameter(network, "network");
                            Intrinsics.checkNotNullParameter(networkCapabilities, "networkCapabilities");
                            Job.DefaultImpls.cancel$default(jobLaunch$default, null, 1, null);
                            Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController onCapabilitiesChanged callback");
                            producerScope.mo2825trySendJP2dKIU(ConstraintsState.ConstraintsMet.INSTANCE);
                        }

                        @Override // android.net.ConnectivityManager.NetworkCallback
                        public void onLost(Network network) {
                            Intrinsics.checkNotNullParameter(network, "network");
                            Job.DefaultImpls.cancel$default(jobLaunch$default, null, 1, null);
                            Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController onLost callback");
                            producerScope.mo2825trySendJP2dKIU(new ConstraintsState.ConstraintsNotMet(7));
                        }
                    };
                    Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController register callback");
                    this.this$0.connManager.registerNetworkCallback(requiredNetworkRequest, (ConnectivityManager.NetworkCallback) r4);
                    final NetworkRequestConstraintController networkRequestConstraintController = this.this$0;
                    Function0 function0 = new Function0() { // from class: androidx.work.impl.constraints.NetworkRequestConstraintController.track.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Object invoke() {
                            m2562invoke();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                        public final void m2562invoke() {
                            Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController unregister callback");
                            networkRequestConstraintController.connManager.unregisterNetworkCallback(r4);
                        }
                    };
                    this.label = 1;
                    if (ProduceKt.awaitClose(producerScope, function0, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    SendChannel.DefaultImpls.close$default(producerScope.getChannel(), null, 1, null);
                    return Unit.INSTANCE;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public Flow track(Constraints constraints) {
        Intrinsics.checkNotNullParameter(constraints, "constraints");
        return FlowKt.callbackFlow(new AnonymousClass1(constraints, this, null));
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(WorkSpec workSpec) {
        Intrinsics.checkNotNullParameter(workSpec, "workSpec");
        return workSpec.constraints.getRequiredNetworkRequest() != null;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean isCurrentlyConstrained(WorkSpec workSpec) {
        Intrinsics.checkNotNullParameter(workSpec, "workSpec");
        if (hasConstraint(workSpec)) {
            throw new IllegalStateException("isCurrentlyConstrained() must never be called onNetworkRequestConstraintController. isCurrentlyConstrained() is called only on older platforms where NetworkRequest isn't supported");
        }
        return false;
    }
}
