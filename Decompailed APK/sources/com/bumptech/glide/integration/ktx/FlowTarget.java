package com.bumptech.glide.integration.ktx;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: Flows.kt */
/* JADX INFO: loaded from: classes.dex */
final class FlowTarget implements Target, RequestListener {
    private volatile Request currentRequest;
    private volatile Resource lastResource;
    private volatile Size resolvedSize;
    private final ProducerScope scope;
    private final ResolvableGlideSize size;
    private final List sizeReadyCallbacks;

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
    }

    public FlowTarget(ProducerScope scope, ResolvableGlideSize size) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(size, "size");
        this.scope = scope;
        this.size = size;
        this.sizeReadyCallbacks = new ArrayList();
        if (size instanceof ImmediateGlideSize) {
            this.resolvedSize = ((ImmediateGlideSize) size).getSize();
        } else if (size instanceof AsyncGlideSize) {
            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(null), 3, null);
        }
    }

    /* JADX INFO: renamed from: com.bumptech.glide.integration.ktx.FlowTarget$1, reason: invalid class name */
    /* JADX INFO: compiled from: Flows.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = FlowTarget.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                AsyncGlideSize asyncGlideSize = (AsyncGlideSize) FlowTarget.this.size;
                this.L$0 = coroutineScope2;
                this.label = 1;
                Object size = asyncGlideSize.getSize(this);
                if (size == coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope = coroutineScope2;
                obj = size;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            Size size2 = (Size) obj;
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            FlowTarget flowTarget = FlowTarget.this;
            synchronized (coroutineScope) {
                flowTarget.resolvedSize = size2;
                ref$ObjectRef.element = new ArrayList(flowTarget.sizeReadyCallbacks);
                flowTarget.sizeReadyCallbacks.clear();
                Unit unit = Unit.INSTANCE;
            }
            Iterator it = ((Iterable) ref$ObjectRef.element).iterator();
            while (it.hasNext()) {
                ((SizeReadyCallback) it.next()).onSizeReady(size2.getWidth(), size2.getHeight());
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadStarted(Drawable drawable) {
        this.lastResource = null;
        this.scope.mo2825trySendJP2dKIU(new Placeholder(Status.RUNNING, drawable));
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadFailed(Drawable drawable) {
        this.scope.mo2825trySendJP2dKIU(new Placeholder(Status.FAILED, drawable));
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onResourceReady(Object resource, Transition transition) {
        Intrinsics.checkNotNullParameter(resource, "resource");
        throw new UnsupportedOperationException();
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadCleared(Drawable drawable) {
        this.lastResource = null;
        this.scope.mo2825trySendJP2dKIU(new Placeholder(Status.CLEARED, drawable));
    }

    @Override // com.bumptech.glide.request.target.Target
    public void getSize(SizeReadyCallback cb) {
        Intrinsics.checkNotNullParameter(cb, "cb");
        Size size = this.resolvedSize;
        if (size != null) {
            cb.onSizeReady(size.getWidth(), size.getHeight());
            return;
        }
        synchronized (this) {
            try {
                Size size2 = this.resolvedSize;
                if (size2 != null) {
                    cb.onSizeReady(size2.getWidth(), size2.getHeight());
                    Unit unit = Unit.INSTANCE;
                } else {
                    this.sizeReadyCallbacks.add(cb);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.bumptech.glide.request.target.Target
    public void removeCallback(SizeReadyCallback cb) {
        Intrinsics.checkNotNullParameter(cb, "cb");
        synchronized (this) {
            this.sizeReadyCallbacks.remove(cb);
        }
    }

    @Override // com.bumptech.glide.request.target.Target
    public void setRequest(Request request) {
        this.currentRequest = request;
    }

    @Override // com.bumptech.glide.request.target.Target
    public Request getRequest() {
        return this.currentRequest;
    }

    @Override // com.bumptech.glide.request.RequestListener
    public boolean onLoadFailed(GlideException glideException, Object obj, Target target, boolean z) {
        Intrinsics.checkNotNullParameter(target, "target");
        Resource resource = this.lastResource;
        Request request = this.currentRequest;
        if (resource == null || request == null || request.isComplete() || request.isRunning()) {
            return false;
        }
        this.scope.getChannel().mo2825trySendJP2dKIU(resource.asFailure());
        return false;
    }

    @Override // com.bumptech.glide.request.RequestListener
    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean z) {
        Intrinsics.checkNotNullParameter(resource, "resource");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        Request request = this.currentRequest;
        Resource resource2 = new Resource((request == null || !request.isComplete()) ? Status.RUNNING : Status.SUCCEEDED, resource, z, dataSource);
        this.lastResource = resource2;
        this.scope.mo2825trySendJP2dKIU(resource2);
        return true;
    }
}
