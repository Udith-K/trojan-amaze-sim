package androidx.compose.ui;

import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.ObserverNodeOwnerScope;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: compiled from: Modifier.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Modifier {
    public static final Companion Companion = Companion.$$INSTANCE;

    boolean all(Function1 function1);

    Object foldIn(Object obj, Function2 function2);

    Modifier then(Modifier modifier);

    /* JADX INFO: renamed from: androidx.compose.ui.Modifier$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Modifier.kt */
    public abstract /* synthetic */ class CC {
        public static Modifier $default$then(Modifier modifier, Modifier modifier2) {
            return modifier2 == Modifier.Companion ? modifier : new CombinedModifier(modifier, modifier2);
        }
    }

    /* JADX INFO: compiled from: Modifier.kt */
    public interface Element extends Modifier {

        /* JADX INFO: renamed from: androidx.compose.ui.Modifier$Element$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: Modifier.kt */
        public abstract /* synthetic */ class CC {
        }
    }

    /* JADX INFO: compiled from: Modifier.kt */
    public static abstract class Node implements DelegatableNode {
        private Node child;
        private NodeCoordinator coordinator;
        private boolean insertedNodeAwaitingAttachForInvalidation;
        private boolean isAttached;
        private int kindSet;
        private boolean onAttachRunExpected;
        private boolean onDetachRunExpected;
        private ObserverNodeOwnerScope ownerScope;
        private Node parent;
        private CoroutineScope scope;
        private boolean updatedNodeAwaitingAttachForInvalidation;
        private Node node = this;
        private int aggregateChildKindSet = -1;

        public boolean getShouldAutoInvalidate() {
            return true;
        }

        public void onAttach() {
        }

        public void onDetach() {
        }

        public void onReset() {
        }

        @Override // androidx.compose.ui.node.DelegatableNode
        public final Node getNode() {
            return this.node;
        }

        public final CoroutineScope getCoroutineScope() {
            CoroutineScope coroutineScope = this.scope;
            if (coroutineScope != null) {
                return coroutineScope;
            }
            CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(DelegatableNodeKt.requireOwner(this).getCoroutineContext().plus(JobKt.Job((Job) DelegatableNodeKt.requireOwner(this).getCoroutineContext().get(Job.Key))));
            this.scope = CoroutineScope;
            return CoroutineScope;
        }

        public final int getKindSet$ui_release() {
            return this.kindSet;
        }

        public final void setKindSet$ui_release(int i) {
            this.kindSet = i;
        }

        public final int getAggregateChildKindSet$ui_release() {
            return this.aggregateChildKindSet;
        }

        public final void setAggregateChildKindSet$ui_release(int i) {
            this.aggregateChildKindSet = i;
        }

        public final Node getParent$ui_release() {
            return this.parent;
        }

        public final void setParent$ui_release(Node node) {
            this.parent = node;
        }

        public final Node getChild$ui_release() {
            return this.child;
        }

        public final void setChild$ui_release(Node node) {
            this.child = node;
        }

        public final ObserverNodeOwnerScope getOwnerScope$ui_release() {
            return this.ownerScope;
        }

        public final void setOwnerScope$ui_release(ObserverNodeOwnerScope observerNodeOwnerScope) {
            this.ownerScope = observerNodeOwnerScope;
        }

        public final NodeCoordinator getCoordinator$ui_release() {
            return this.coordinator;
        }

        public final boolean getInsertedNodeAwaitingAttachForInvalidation$ui_release() {
            return this.insertedNodeAwaitingAttachForInvalidation;
        }

        public final void setInsertedNodeAwaitingAttachForInvalidation$ui_release(boolean z) {
            this.insertedNodeAwaitingAttachForInvalidation = z;
        }

        public final boolean getUpdatedNodeAwaitingAttachForInvalidation$ui_release() {
            return this.updatedNodeAwaitingAttachForInvalidation;
        }

        public final void setUpdatedNodeAwaitingAttachForInvalidation$ui_release(boolean z) {
            this.updatedNodeAwaitingAttachForInvalidation = z;
        }

        public final boolean isAttached() {
            return this.isAttached;
        }

        public void updateCoordinator$ui_release(NodeCoordinator nodeCoordinator) {
            this.coordinator = nodeCoordinator;
        }

        public void markAsAttached$ui_release() {
            if (this.isAttached) {
                InlineClassHelperKt.throwIllegalStateException("node attached multiple times");
            }
            if (!(this.coordinator != null)) {
                InlineClassHelperKt.throwIllegalStateException("attach invoked on a node without a coordinator");
            }
            this.isAttached = true;
            this.onAttachRunExpected = true;
        }

        public void runAttachLifecycle$ui_release() {
            if (!this.isAttached) {
                InlineClassHelperKt.throwIllegalStateException("Must run markAsAttached() prior to runAttachLifecycle");
            }
            if (!this.onAttachRunExpected) {
                InlineClassHelperKt.throwIllegalStateException("Must run runAttachLifecycle() only once after markAsAttached()");
            }
            this.onAttachRunExpected = false;
            onAttach();
            this.onDetachRunExpected = true;
        }

        public void runDetachLifecycle$ui_release() {
            if (!this.isAttached) {
                InlineClassHelperKt.throwIllegalStateException("node detached multiple times");
            }
            if (!(this.coordinator != null)) {
                InlineClassHelperKt.throwIllegalStateException("detach invoked on a node without a coordinator");
            }
            if (!this.onDetachRunExpected) {
                InlineClassHelperKt.throwIllegalStateException("Must run runDetachLifecycle() once after runAttachLifecycle() and before markAsDetached()");
            }
            this.onDetachRunExpected = false;
            onDetach();
        }

        public void markAsDetached$ui_release() {
            if (!this.isAttached) {
                InlineClassHelperKt.throwIllegalStateException("Cannot detach a node that is not attached");
            }
            if (this.onAttachRunExpected) {
                InlineClassHelperKt.throwIllegalStateException("Must run runAttachLifecycle() before markAsDetached()");
            }
            if (this.onDetachRunExpected) {
                InlineClassHelperKt.throwIllegalStateException("Must run runDetachLifecycle() before markAsDetached()");
            }
            this.isAttached = false;
            CoroutineScope coroutineScope = this.scope;
            if (coroutineScope != null) {
                CoroutineScopeKt.cancel(coroutineScope, new ModifierNodeDetachedCancellationException());
                this.scope = null;
            }
        }

        public void reset$ui_release() {
            if (!this.isAttached) {
                InlineClassHelperKt.throwIllegalStateException("reset() called on an unattached node");
            }
            onReset();
        }

        public final void sideEffect(Function0 function0) {
            DelegatableNodeKt.requireOwner(this).registerOnEndApplyChangesListener(function0);
        }

        public void setAsDelegateTo$ui_release(Node node) {
            this.node = node;
        }
    }

    /* JADX INFO: compiled from: Modifier.kt */
    public static final class Companion implements Modifier {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @Override // androidx.compose.ui.Modifier
        public boolean all(Function1 function1) {
            return true;
        }

        @Override // androidx.compose.ui.Modifier
        public Object foldIn(Object obj, Function2 function2) {
            return obj;
        }

        @Override // androidx.compose.ui.Modifier
        public Modifier then(Modifier modifier) {
            return modifier;
        }

        private Companion() {
        }

        public String toString() {
            return "Modifier";
        }
    }
}
