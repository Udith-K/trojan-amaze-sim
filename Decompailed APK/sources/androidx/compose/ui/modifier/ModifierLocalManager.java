package androidx.compose.ui.modifier;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.BackwardsCompatNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: ModifierLocalManager.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ModifierLocalManager {
    private boolean invalidated;
    private final Owner owner;
    private final MutableVector inserted = new MutableVector(new BackwardsCompatNode[16], 0);
    private final MutableVector insertedLocal = new MutableVector(new ModifierLocal[16], 0);
    private final MutableVector removed = new MutableVector(new LayoutNode[16], 0);
    private final MutableVector removedLocal = new MutableVector(new ModifierLocal[16], 0);

    public ModifierLocalManager(Owner owner) {
        this.owner = owner;
    }

    public final void invalidate() {
        if (this.invalidated) {
            return;
        }
        this.invalidated = true;
        this.owner.registerOnEndApplyChangesListener(new Function0() { // from class: androidx.compose.ui.modifier.ModifierLocalManager.invalidate.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m1786invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1786invoke() {
                ModifierLocalManager.this.triggerUpdates();
            }
        });
    }

    public final void triggerUpdates() {
        int i = 0;
        this.invalidated = false;
        HashSet hashSet = new HashSet();
        MutableVector mutableVector = this.removed;
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i2 = 0;
            do {
                LayoutNode layoutNode = (LayoutNode) content[i2];
                ModifierLocal modifierLocal = (ModifierLocal) this.removedLocal.getContent()[i2];
                if (layoutNode.getNodes$ui_release().getHead$ui_release().isAttached()) {
                    invalidateConsumersOfNodeForKey(layoutNode.getNodes$ui_release().getHead$ui_release(), modifierLocal, hashSet);
                }
                i2++;
            } while (i2 < size);
        }
        this.removed.clear();
        this.removedLocal.clear();
        MutableVector mutableVector2 = this.inserted;
        int size2 = mutableVector2.getSize();
        if (size2 > 0) {
            Object[] content2 = mutableVector2.getContent();
            do {
                BackwardsCompatNode backwardsCompatNode = (BackwardsCompatNode) content2[i];
                ModifierLocal modifierLocal2 = (ModifierLocal) this.insertedLocal.getContent()[i];
                if (backwardsCompatNode.isAttached()) {
                    invalidateConsumersOfNodeForKey(backwardsCompatNode, modifierLocal2, hashSet);
                }
                i++;
            } while (i < size2);
        }
        this.inserted.clear();
        this.insertedLocal.clear();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((BackwardsCompatNode) it.next()).updateModifierLocalConsumer();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r9v9 */
    private final void invalidateConsumersOfNodeForKey(Modifier.Node node, ModifierLocal modifierLocal, Set set) {
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(32);
        if (!node.getNode().isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$ui_release = node.getNode().getChild$ui_release();
        if (child$ui_release == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, node.getNode());
        } else {
            mutableVector.add(child$ui_release);
        }
        while (mutableVector.isNotEmpty()) {
            Modifier.Node node2 = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((node2.getAggregateChildKindSet$ui_release() & iM1895constructorimpl) != 0) {
                for (Modifier.Node child$ui_release2 = node2; child$ui_release2 != null; child$ui_release2 = child$ui_release2.getChild$ui_release()) {
                    if ((child$ui_release2.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                        ?? Pop = child$ui_release2;
                        ?? mutableVector2 = 0;
                        while (Pop != 0) {
                            if (!(Pop instanceof ModifierLocalModifierNode)) {
                                if ((Pop.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (Pop instanceof DelegatingNode)) {
                                    Modifier.Node delegate$ui_release = ((DelegatingNode) Pop).getDelegate$ui_release();
                                    int i = 0;
                                    Pop = Pop;
                                    mutableVector2 = mutableVector2;
                                    while (delegate$ui_release != null) {
                                        if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                                            i++;
                                            mutableVector2 = mutableVector2;
                                            if (i == 1) {
                                                Pop = delegate$ui_release;
                                            } else {
                                                if (mutableVector2 == 0) {
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (Pop != 0) {
                                                    mutableVector2.add(Pop);
                                                    Pop = 0;
                                                }
                                                mutableVector2.add(delegate$ui_release);
                                            }
                                        }
                                        delegate$ui_release = delegate$ui_release.getChild$ui_release();
                                        Pop = Pop;
                                        mutableVector2 = mutableVector2;
                                    }
                                    if (i == 1) {
                                    }
                                }
                            } else {
                                ModifierLocalModifierNode modifierLocalModifierNode = (ModifierLocalModifierNode) Pop;
                                if (modifierLocalModifierNode instanceof BackwardsCompatNode) {
                                    BackwardsCompatNode backwardsCompatNode = (BackwardsCompatNode) modifierLocalModifierNode;
                                    if ((backwardsCompatNode.getElement() instanceof ModifierLocalConsumer) && backwardsCompatNode.getReadValues().contains(modifierLocal)) {
                                        set.add(modifierLocalModifierNode);
                                    }
                                }
                                if (modifierLocalModifierNode.getProvidedValues().contains$ui_release(modifierLocal)) {
                                    break;
                                }
                            }
                            Pop = DelegatableNodeKt.pop(mutableVector2);
                        }
                    }
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, node2);
        }
    }

    public final void updatedProvider(BackwardsCompatNode backwardsCompatNode, ModifierLocal modifierLocal) {
        this.inserted.add(backwardsCompatNode);
        this.insertedLocal.add(modifierLocal);
        invalidate();
    }

    public final void insertedProvider(BackwardsCompatNode backwardsCompatNode, ModifierLocal modifierLocal) {
        this.inserted.add(backwardsCompatNode);
        this.insertedLocal.add(modifierLocal);
        invalidate();
    }

    public final void removedProvider(BackwardsCompatNode backwardsCompatNode, ModifierLocal modifierLocal) {
        this.removed.add(DelegatableNodeKt.requireLayoutNode(backwardsCompatNode));
        this.removedLocal.add(modifierLocal);
        invalidate();
    }
}
