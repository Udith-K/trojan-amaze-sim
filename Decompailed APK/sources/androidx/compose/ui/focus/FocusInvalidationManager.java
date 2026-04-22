package androidx.compose.ui.focus;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: FocusInvalidationManager.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FocusInvalidationManager {
    private final Function0 invalidateOwnerFocusState;
    private final Function1 onRequestApplyChangesListener;
    private final MutableScatterSet focusTargetNodes = ScatterSetKt.mutableScatterSetOf();
    private final MutableScatterSet focusEventNodes = ScatterSetKt.mutableScatterSetOf();
    private final MutableScatterSet focusPropertiesNodes = ScatterSetKt.mutableScatterSetOf();
    private final MutableScatterSet focusTargetsWithInvalidatedFocusEvents = ScatterSetKt.mutableScatterSetOf();

    public FocusInvalidationManager(Function1 function1, Function0 function0) {
        this.onRequestApplyChangesListener = function1;
        this.invalidateOwnerFocusState = function0;
    }

    public final void scheduleInvalidation(FocusTargetNode focusTargetNode) {
        scheduleInvalidation(this.focusTargetNodes, focusTargetNode);
    }

    public final void scheduleInvalidation(FocusEventModifierNode focusEventModifierNode) {
        scheduleInvalidation(this.focusEventNodes, focusEventModifierNode);
    }

    public final void scheduleInvalidation(FocusPropertiesModifierNode focusPropertiesModifierNode) {
        scheduleInvalidation(this.focusPropertiesNodes, focusPropertiesModifierNode);
    }

    public final boolean hasPendingInvalidation() {
        return this.focusTargetNodes.isNotEmpty() || this.focusPropertiesNodes.isNotEmpty() || this.focusEventNodes.isNotEmpty();
    }

    private final void scheduleInvalidation(MutableScatterSet mutableScatterSet, Object obj) {
        if (mutableScatterSet.add(obj) && this.focusTargetNodes.getSize() + this.focusEventNodes.getSize() + this.focusPropertiesNodes.getSize() == 1) {
            this.onRequestApplyChangesListener.invoke(new AnonymousClass1(this));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.focus.FocusInvalidationManager$scheduleInvalidation$1, reason: invalid class name */
    /* JADX INFO: compiled from: FocusInvalidationManager.kt */
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0 {
        AnonymousClass1(Object obj) {
            super(0, obj, FocusInvalidationManager.class, "invalidateNodes", "invalidateNodes()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m1104invoke();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1104invoke() {
            ((FocusInvalidationManager) this.receiver).invalidateNodes();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invalidateNodes() {
        int i;
        long[] jArr;
        Object[] objArr;
        long[] jArr2;
        Object[] objArr2;
        FocusState focusState;
        MutableVector mutableVector;
        int i2;
        int i3;
        int i4;
        long[] jArr3;
        Object[] objArr3;
        boolean z;
        Object[] objArr4;
        boolean z2;
        int i5;
        MutableVector mutableVector2;
        long[] jArr4;
        long[] jArr5;
        int i6;
        long[] jArr6;
        long[] jArr7;
        MutableScatterSet mutableScatterSet = this.focusPropertiesNodes;
        Object[] objArr5 = mutableScatterSet.elements;
        long[] jArr8 = mutableScatterSet.metadata;
        int length = jArr8.length - 2;
        char c = 7;
        long j = -9187201950435737472L;
        int i7 = 8;
        int i8 = 1;
        if (length >= 0) {
            int i9 = 0;
            while (true) {
                long j2 = jArr8[i9];
                if ((((~j2) << c) & j2 & j) != j) {
                    int i10 = 8 - ((~(i9 - length)) >>> 31);
                    int i11 = 0;
                    while (i11 < i10) {
                        if ((j2 & 255) < 128) {
                            FocusPropertiesModifierNode focusPropertiesModifierNode = (FocusPropertiesModifierNode) objArr5[(i9 << 3) + i11];
                            if (focusPropertiesModifierNode.getNode().isAttached()) {
                                int iM1895constructorimpl = NodeKind.m1895constructorimpl(1024);
                                Modifier.Node node = focusPropertiesModifierNode.getNode();
                                MutableVector mutableVector3 = null;
                                while (node != null) {
                                    if (node instanceof FocusTargetNode) {
                                        this.focusTargetNodes.add((FocusTargetNode) node);
                                    } else {
                                        if ((node.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (node instanceof DelegatingNode)) {
                                            Modifier.Node delegate$ui_release = ((DelegatingNode) node).getDelegate$ui_release();
                                            int i12 = 0;
                                            while (delegate$ui_release != null) {
                                                if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                                                    i12++;
                                                    if (i12 == i8) {
                                                        jArr7 = jArr8;
                                                        node = delegate$ui_release;
                                                    } else {
                                                        if (mutableVector3 == null) {
                                                            jArr7 = jArr8;
                                                            mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                                        } else {
                                                            jArr7 = jArr8;
                                                        }
                                                        if (node != null) {
                                                            mutableVector3.add(node);
                                                            node = null;
                                                        }
                                                        mutableVector3.add(delegate$ui_release);
                                                    }
                                                } else {
                                                    jArr7 = jArr8;
                                                }
                                                delegate$ui_release = delegate$ui_release.getChild$ui_release();
                                                jArr8 = jArr7;
                                                i8 = 1;
                                            }
                                            jArr6 = jArr8;
                                            int i13 = i8;
                                            if (i12 == i13) {
                                                i8 = i13;
                                                jArr8 = jArr6;
                                            }
                                        }
                                        node = DelegatableNodeKt.pop(mutableVector3);
                                        jArr8 = jArr6;
                                        i8 = 1;
                                    }
                                    jArr6 = jArr8;
                                    node = DelegatableNodeKt.pop(mutableVector3);
                                    jArr8 = jArr6;
                                    i8 = 1;
                                }
                                jArr5 = jArr8;
                                if (!focusPropertiesModifierNode.getNode().isAttached()) {
                                    throw new IllegalStateException("visitChildren called on an unattached node");
                                }
                                MutableVector mutableVector4 = new MutableVector(new Modifier.Node[16], 0);
                                Modifier.Node child$ui_release = focusPropertiesModifierNode.getNode().getChild$ui_release();
                                if (child$ui_release == null) {
                                    DelegatableNodeKt.addLayoutNodeChildren(mutableVector4, focusPropertiesModifierNode.getNode());
                                } else {
                                    mutableVector4.add(child$ui_release);
                                }
                                while (mutableVector4.isNotEmpty()) {
                                    Modifier.Node nodePop = (Modifier.Node) mutableVector4.removeAt(mutableVector4.getSize() - 1);
                                    if ((nodePop.getAggregateChildKindSet$ui_release() & iM1895constructorimpl) == 0) {
                                        DelegatableNodeKt.addLayoutNodeChildren(mutableVector4, nodePop);
                                    } else {
                                        while (true) {
                                            if (nodePop == null) {
                                                break;
                                            }
                                            if ((nodePop.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                                                MutableVector mutableVector5 = null;
                                                while (nodePop != null) {
                                                    if (nodePop instanceof FocusTargetNode) {
                                                        this.focusTargetNodes.add((FocusTargetNode) nodePop);
                                                    } else if ((nodePop.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                                        int i14 = 0;
                                                        for (Modifier.Node delegate$ui_release2 = ((DelegatingNode) nodePop).getDelegate$ui_release(); delegate$ui_release2 != null; delegate$ui_release2 = delegate$ui_release2.getChild$ui_release()) {
                                                            if ((delegate$ui_release2.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                                                                i14++;
                                                                if (i14 == 1) {
                                                                    nodePop = delegate$ui_release2;
                                                                } else {
                                                                    if (mutableVector5 == null) {
                                                                        mutableVector5 = new MutableVector(new Modifier.Node[16], 0);
                                                                    }
                                                                    if (nodePop != null) {
                                                                        mutableVector5.add(nodePop);
                                                                        nodePop = null;
                                                                    }
                                                                    mutableVector5.add(delegate$ui_release2);
                                                                }
                                                            }
                                                        }
                                                        if (i14 == 1) {
                                                        }
                                                    }
                                                    nodePop = DelegatableNodeKt.pop(mutableVector5);
                                                }
                                            } else {
                                                nodePop = nodePop.getChild$ui_release();
                                            }
                                        }
                                    }
                                }
                            } else {
                                jArr5 = jArr8;
                            }
                            i6 = 8;
                        } else {
                            jArr5 = jArr8;
                            i6 = i7;
                        }
                        j2 >>= i6;
                        i11++;
                        i7 = i6;
                        jArr8 = jArr5;
                        i8 = 1;
                    }
                    jArr4 = jArr8;
                    if (i10 != i7) {
                        break;
                    }
                } else {
                    jArr4 = jArr8;
                }
                if (i9 == length) {
                    break;
                }
                i9++;
                jArr8 = jArr4;
                c = 7;
                j = -9187201950435737472L;
                i8 = 1;
                i7 = 8;
            }
        }
        this.focusPropertiesNodes.clear();
        MutableScatterSet mutableScatterSet2 = this.focusEventNodes;
        Object[] objArr6 = mutableScatterSet2.elements;
        long[] jArr9 = mutableScatterSet2.metadata;
        int length2 = jArr9.length - 2;
        if (length2 >= 0) {
            int i15 = 0;
            while (true) {
                long j3 = jArr9[i15];
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i16 = 8 - ((~(i15 - length2)) >>> 31);
                    int i17 = 0;
                    while (i17 < i16) {
                        if ((j3 & 255) < 128) {
                            FocusEventModifierNode focusEventModifierNode = (FocusEventModifierNode) objArr6[(i15 << 3) + i17];
                            if (focusEventModifierNode.getNode().isAttached()) {
                                int iM1895constructorimpl2 = NodeKind.m1895constructorimpl(1024);
                                Modifier.Node node2 = focusEventModifierNode.getNode();
                                boolean z3 = false;
                                boolean z4 = true;
                                FocusTargetNode focusTargetNode = null;
                                MutableVector mutableVector6 = null;
                                while (node2 != null) {
                                    if (node2 instanceof FocusTargetNode) {
                                        FocusTargetNode focusTargetNode2 = (FocusTargetNode) node2;
                                        if (focusTargetNode != null) {
                                            z3 = true;
                                        }
                                        if (this.focusTargetNodes.contains(focusTargetNode2)) {
                                            this.focusTargetsWithInvalidatedFocusEvents.add(focusTargetNode2);
                                            z4 = false;
                                        }
                                        jArr3 = jArr9;
                                        objArr3 = objArr6;
                                        focusTargetNode = focusTargetNode2;
                                    } else {
                                        if ((node2.getKindSet$ui_release() & iM1895constructorimpl2) == 0 || !(node2 instanceof DelegatingNode)) {
                                            jArr3 = jArr9;
                                            objArr3 = objArr6;
                                            z = z3;
                                        } else {
                                            Modifier.Node delegate$ui_release3 = ((DelegatingNode) node2).getDelegate$ui_release();
                                            jArr3 = jArr9;
                                            int i18 = 0;
                                            while (delegate$ui_release3 != null) {
                                                if ((delegate$ui_release3.getKindSet$ui_release() & iM1895constructorimpl2) != 0) {
                                                    i18++;
                                                    objArr4 = objArr6;
                                                    if (i18 == 1) {
                                                        node2 = delegate$ui_release3;
                                                    } else {
                                                        if (mutableVector6 == null) {
                                                            i5 = i18;
                                                            z2 = z3;
                                                            mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                        } else {
                                                            i5 = i18;
                                                            z2 = z3;
                                                            mutableVector2 = mutableVector6;
                                                        }
                                                        if (node2 != null) {
                                                            mutableVector2.add(node2);
                                                            node2 = null;
                                                        }
                                                        mutableVector2.add(delegate$ui_release3);
                                                        mutableVector6 = mutableVector2;
                                                        i18 = i5;
                                                        delegate$ui_release3 = delegate$ui_release3.getChild$ui_release();
                                                        objArr6 = objArr4;
                                                        z3 = z2;
                                                    }
                                                } else {
                                                    objArr4 = objArr6;
                                                }
                                                z2 = z3;
                                                delegate$ui_release3 = delegate$ui_release3.getChild$ui_release();
                                                objArr6 = objArr4;
                                                z3 = z2;
                                            }
                                            objArr3 = objArr6;
                                            z = z3;
                                            if (i18 == 1) {
                                                jArr9 = jArr3;
                                                objArr6 = objArr3;
                                                z3 = z;
                                            }
                                        }
                                        z3 = z;
                                    }
                                    node2 = DelegatableNodeKt.pop(mutableVector6);
                                    jArr9 = jArr3;
                                    objArr6 = objArr3;
                                }
                                jArr2 = jArr9;
                                objArr2 = objArr6;
                                boolean z5 = z3;
                                if (!focusEventModifierNode.getNode().isAttached()) {
                                    throw new IllegalStateException("visitChildren called on an unattached node");
                                }
                                MutableVector mutableVector7 = new MutableVector(new Modifier.Node[16], 0);
                                Modifier.Node child$ui_release2 = focusEventModifierNode.getNode().getChild$ui_release();
                                if (child$ui_release2 == null) {
                                    DelegatableNodeKt.addLayoutNodeChildren(mutableVector7, focusEventModifierNode.getNode());
                                } else {
                                    mutableVector7.add(child$ui_release2);
                                }
                                boolean z6 = z5;
                                while (mutableVector7.isNotEmpty()) {
                                    Modifier.Node nodePop2 = (Modifier.Node) mutableVector7.removeAt(mutableVector7.getSize() - 1);
                                    if ((nodePop2.getAggregateChildKindSet$ui_release() & iM1895constructorimpl2) == 0) {
                                        DelegatableNodeKt.addLayoutNodeChildren(mutableVector7, nodePop2);
                                    } else {
                                        while (nodePop2 != null) {
                                            if ((nodePop2.getKindSet$ui_release() & iM1895constructorimpl2) != 0) {
                                                MutableVector mutableVector8 = null;
                                                while (nodePop2 != null) {
                                                    if (nodePop2 instanceof FocusTargetNode) {
                                                        FocusTargetNode focusTargetNode3 = (FocusTargetNode) nodePop2;
                                                        if (focusTargetNode != null) {
                                                            z6 = true;
                                                        }
                                                        if (this.focusTargetNodes.contains(focusTargetNode3)) {
                                                            this.focusTargetsWithInvalidatedFocusEvents.add(focusTargetNode3);
                                                            z4 = false;
                                                        }
                                                        mutableVector = mutableVector7;
                                                        focusTargetNode = focusTargetNode3;
                                                    } else if ((nodePop2.getKindSet$ui_release() & iM1895constructorimpl2) == 0 || !(nodePop2 instanceof DelegatingNode)) {
                                                        mutableVector = mutableVector7;
                                                    } else {
                                                        Modifier.Node delegate$ui_release4 = ((DelegatingNode) nodePop2).getDelegate$ui_release();
                                                        mutableVector = mutableVector7;
                                                        int i19 = 0;
                                                        while (delegate$ui_release4 != null) {
                                                            if ((delegate$ui_release4.getKindSet$ui_release() & iM1895constructorimpl2) != 0) {
                                                                i19++;
                                                                i3 = iM1895constructorimpl2;
                                                                if (i19 == 1) {
                                                                    nodePop2 = delegate$ui_release4;
                                                                } else {
                                                                    if (mutableVector8 == null) {
                                                                        i4 = i19;
                                                                        mutableVector8 = new MutableVector(new Modifier.Node[16], 0);
                                                                    } else {
                                                                        i4 = i19;
                                                                    }
                                                                    if (nodePop2 != null) {
                                                                        mutableVector8.add(nodePop2);
                                                                        nodePop2 = null;
                                                                    }
                                                                    mutableVector8.add(delegate$ui_release4);
                                                                    i19 = i4;
                                                                    delegate$ui_release4 = delegate$ui_release4.getChild$ui_release();
                                                                    iM1895constructorimpl2 = i3;
                                                                }
                                                            } else {
                                                                i3 = iM1895constructorimpl2;
                                                            }
                                                            delegate$ui_release4 = delegate$ui_release4.getChild$ui_release();
                                                            iM1895constructorimpl2 = i3;
                                                        }
                                                        i2 = iM1895constructorimpl2;
                                                        if (i19 != 1) {
                                                            nodePop2 = DelegatableNodeKt.pop(mutableVector8);
                                                        }
                                                        mutableVector7 = mutableVector;
                                                        iM1895constructorimpl2 = i2;
                                                    }
                                                    i2 = iM1895constructorimpl2;
                                                    nodePop2 = DelegatableNodeKt.pop(mutableVector8);
                                                    mutableVector7 = mutableVector;
                                                    iM1895constructorimpl2 = i2;
                                                }
                                            } else {
                                                nodePop2 = nodePop2.getChild$ui_release();
                                                iM1895constructorimpl2 = iM1895constructorimpl2;
                                            }
                                        }
                                    }
                                    mutableVector7 = mutableVector7;
                                    iM1895constructorimpl2 = iM1895constructorimpl2;
                                }
                                if (z4) {
                                    if (z6) {
                                        focusState = FocusEventModifierNodeKt.getFocusState(focusEventModifierNode);
                                    } else if (focusTargetNode == null || (focusState = focusTargetNode.getFocusState()) == null) {
                                        focusState = FocusStateImpl.Inactive;
                                    }
                                    focusEventModifierNode.onFocusEvent(focusState);
                                }
                            } else {
                                focusEventModifierNode.onFocusEvent(FocusStateImpl.Inactive);
                                jArr2 = jArr9;
                                objArr2 = objArr6;
                            }
                        } else {
                            jArr2 = jArr9;
                            objArr2 = objArr6;
                        }
                        j3 >>= 8;
                        i17++;
                        jArr9 = jArr2;
                        objArr6 = objArr2;
                    }
                    jArr = jArr9;
                    objArr = objArr6;
                    i = 0;
                    if (i16 != 8) {
                        break;
                    }
                } else {
                    jArr = jArr9;
                    objArr = objArr6;
                    i = 0;
                }
                if (i15 == length2) {
                    break;
                }
                i15++;
                jArr9 = jArr;
                objArr6 = objArr;
            }
        } else {
            i = 0;
        }
        this.focusEventNodes.clear();
        MutableScatterSet mutableScatterSet3 = this.focusTargetNodes;
        Object[] objArr7 = mutableScatterSet3.elements;
        long[] jArr10 = mutableScatterSet3.metadata;
        int length3 = jArr10.length - 2;
        if (length3 >= 0) {
            int i20 = i;
            while (true) {
                long j4 = jArr10[i20];
                if ((((~j4) << 7) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i21 = 8 - ((~(i20 - length3)) >>> 31);
                    for (int i22 = i; i22 < i21; i22++) {
                        if ((j4 & 255) < 128) {
                            FocusTargetNode focusTargetNode4 = (FocusTargetNode) objArr7[(i20 << 3) + i22];
                            if (focusTargetNode4.isAttached()) {
                                FocusStateImpl focusState2 = focusTargetNode4.getFocusState();
                                focusTargetNode4.invalidateFocus$ui_release();
                                if (focusState2 != focusTargetNode4.getFocusState() || this.focusTargetsWithInvalidatedFocusEvents.contains(focusTargetNode4)) {
                                    FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode4);
                                }
                            }
                        }
                        j4 >>= 8;
                    }
                    if (i21 != 8) {
                        break;
                    }
                }
                if (i20 == length3) {
                    break;
                } else {
                    i20++;
                }
            }
        }
        this.focusTargetNodes.clear();
        this.focusTargetsWithInvalidatedFocusEvents.clear();
        this.invalidateOwnerFocusState.invoke();
        if (!this.focusPropertiesNodes.isEmpty()) {
            InlineClassHelperKt.throwIllegalStateException("Unprocessed FocusProperties nodes");
        }
        if (!this.focusEventNodes.isEmpty()) {
            InlineClassHelperKt.throwIllegalStateException("Unprocessed FocusEvent nodes");
        }
        if (this.focusTargetNodes.isEmpty()) {
            return;
        }
        InlineClassHelperKt.throwIllegalStateException("Unprocessed FocusTarget nodes");
    }
}
