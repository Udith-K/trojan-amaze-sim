package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.OffsetApplier;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.SlotTable;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.internal.IntRef;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.Action;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: Operation.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Operation {
    private final int ints;
    private final int objects;

    /* JADX INFO: compiled from: Operation.kt */
    public static final class IntParameter {
        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static int m1039constructorimpl(int i) {
            return i;
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m1040equalsimpl0(int i, int i2) {
            return i == i2;
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class ObjectParameter {
        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static int m1041constructorimpl(int i) {
            return i;
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m1042equalsimpl0(int i, int i2) {
            return i == i2;
        }
    }

    public /* synthetic */ Operation(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2);
    }

    public abstract void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager);

    private Operation(int i, int i2) {
        this.ints = i;
        this.objects = i2;
    }

    public /* synthetic */ Operation(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, null);
    }

    public final int getInts() {
        return this.ints;
    }

    public final int getObjects() {
        return this.objects;
    }

    public final String getName() {
        String simpleName = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
        return simpleName == null ? "" : simpleName;
    }

    /* JADX INFO: renamed from: intParamName-w8GmfQM, reason: not valid java name */
    public String mo1037intParamNamew8GmfQM(int i) {
        return "IntParameter(" + i + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: renamed from: objectParamName-31yXWZQ, reason: not valid java name */
    public String mo1038objectParamName31yXWZQ(int i) {
        return "ObjectParameter(" + i + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public String toString() {
        return getName();
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class Ups extends Operation {
        public static final Ups INSTANCE = new Ups();

        private Ups() {
            super(1, 0, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            int iMo1043getIntw8GmfQM = operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(0));
            for (int i = 0; i < iMo1043getIntw8GmfQM; i++) {
                applier.up();
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo1037intParamNamew8GmfQM(int i) {
            return IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(0)) ? "count" : super.mo1037intParamNamew8GmfQM(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class Downs extends Operation {
        public static final Downs INSTANCE = new Downs();

        /* JADX WARN: Illegal instructions before constructor call */
        private Downs() {
            int i = 1;
            super(0, i, i, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "nodes" : super.mo1038objectParamName31yXWZQ(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            for (Object obj : (Object[]) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0))) {
                applier.down(obj);
            }
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class AdvanceSlotsBy extends Operation {
        public static final AdvanceSlotsBy INSTANCE = new AdvanceSlotsBy();

        private AdvanceSlotsBy() {
            super(1, 0, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.advanceBy(operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(0)));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo1037intParamNamew8GmfQM(int i) {
            return IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(0)) ? "distance" : super.mo1037intParamNamew8GmfQM(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class SideEffect extends Operation {
        public static final SideEffect INSTANCE = new SideEffect();

        /* JADX WARN: Illegal instructions before constructor call */
        private SideEffect() {
            int i = 1;
            super(0, i, i, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            rememberManager.sideEffect((Function0) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0)));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "effect" : super.mo1038objectParamName31yXWZQ(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class Remember extends Operation {
        public static final Remember INSTANCE = new Remember();

        /* JADX WARN: Illegal instructions before constructor call */
        private Remember() {
            int i = 1;
            super(0, i, i, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            rememberManager.remembering((RememberObserver) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0)));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? Action.VALUE_ATTRIBUTE : super.mo1038objectParamName31yXWZQ(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class AppendValue extends Operation {
        public static final AppendValue INSTANCE = new AppendValue();

        private AppendValue() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            Anchor anchor = (Anchor) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0));
            Object objMo1044getObject31yXWZQ = operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(1));
            if (objMo1044getObject31yXWZQ instanceof RememberObserverHolder) {
                rememberManager.remembering(((RememberObserverHolder) objMo1044getObject31yXWZQ).getWrapped());
            }
            slotWriter.appendSlot(anchor, objMo1044getObject31yXWZQ);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "anchor" : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(1)) ? Action.VALUE_ATTRIBUTE : super.mo1038objectParamName31yXWZQ(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class TrimParentValues extends Operation {
        public static final TrimParentValues INSTANCE = new TrimParentValues();

        private TrimParentValues() {
            super(1, 0, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            int iMo1043getIntw8GmfQM = operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(0));
            int slotsSize = slotWriter.getSlotsSize();
            int parent = slotWriter.getParent();
            int iSlotsStartIndex$runtime_release = slotWriter.slotsStartIndex$runtime_release(parent);
            int iSlotsEndIndex$runtime_release = slotWriter.slotsEndIndex$runtime_release(parent);
            for (int iMax = Math.max(iSlotsStartIndex$runtime_release, iSlotsEndIndex$runtime_release - iMo1043getIntw8GmfQM); iMax < iSlotsEndIndex$runtime_release; iMax++) {
                Object obj = slotWriter.slots[slotWriter.dataIndexToDataAddress(iMax)];
                if (obj instanceof RememberObserverHolder) {
                    rememberManager.forgetting(((RememberObserverHolder) obj).getWrapped(), slotsSize - iMax, -1, -1);
                } else if (obj instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) obj).release();
                }
            }
            slotWriter.trimTailSlots(iMo1043getIntw8GmfQM);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo1037intParamNamew8GmfQM(int i) {
            return IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(0)) ? "count" : super.mo1037intParamNamew8GmfQM(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class UpdateValue extends Operation {
        public static final UpdateValue INSTANCE = new UpdateValue();

        /* JADX WARN: Illegal instructions before constructor call */
        private UpdateValue() {
            int i = 1;
            super(i, i, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            Object objMo1044getObject31yXWZQ = operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0));
            int iMo1043getIntw8GmfQM = operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(0));
            if (objMo1044getObject31yXWZQ instanceof RememberObserverHolder) {
                rememberManager.remembering(((RememberObserverHolder) objMo1044getObject31yXWZQ).getWrapped());
            }
            Object obj = slotWriter.set(iMo1043getIntw8GmfQM, objMo1044getObject31yXWZQ);
            if (obj instanceof RememberObserverHolder) {
                rememberManager.forgetting(((RememberObserverHolder) obj).getWrapped(), slotWriter.getSlotsSize() - slotWriter.slotIndexOfGroupSlotIndex(slotWriter.getCurrentGroup(), iMo1043getIntw8GmfQM), -1, -1);
            } else if (obj instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) obj).release();
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? Action.VALUE_ATTRIBUTE : super.mo1038objectParamName31yXWZQ(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo1037intParamNamew8GmfQM(int i) {
            return IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(0)) ? "groupSlotIndex" : super.mo1037intParamNamew8GmfQM(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class UpdateAnchoredValue extends Operation {
        public static final UpdateAnchoredValue INSTANCE = new UpdateAnchoredValue();

        private UpdateAnchoredValue() {
            super(1, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            int iAnchorIndex;
            int slotsSize;
            Object objMo1044getObject31yXWZQ = operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0));
            Anchor anchor = (Anchor) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(1));
            int iMo1043getIntw8GmfQM = operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(0));
            if (objMo1044getObject31yXWZQ instanceof RememberObserverHolder) {
                rememberManager.remembering(((RememberObserverHolder) objMo1044getObject31yXWZQ).getWrapped());
            }
            int iAnchorIndex2 = slotWriter.anchorIndex(anchor);
            Object obj = slotWriter.set(iAnchorIndex2, iMo1043getIntw8GmfQM, objMo1044getObject31yXWZQ);
            if (obj instanceof RememberObserverHolder) {
                int slotsSize2 = slotWriter.getSlotsSize() - slotWriter.slotIndexOfGroupSlotIndex(iAnchorIndex2, iMo1043getIntw8GmfQM);
                RememberObserverHolder rememberObserverHolder = (RememberObserverHolder) obj;
                Anchor after = rememberObserverHolder.getAfter();
                if (after == null || !after.getValid()) {
                    iAnchorIndex = -1;
                    slotsSize = -1;
                } else {
                    iAnchorIndex = slotWriter.anchorIndex(after);
                    slotsSize = slotWriter.getSlotsSize() - slotWriter.slotsEndAllIndex$runtime_release(iAnchorIndex);
                }
                rememberManager.forgetting(rememberObserverHolder.getWrapped(), slotsSize2, iAnchorIndex, slotsSize);
                return;
            }
            if (obj instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) obj).release();
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? Action.VALUE_ATTRIBUTE : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(1)) ? "anchor" : super.mo1038objectParamName31yXWZQ(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo1037intParamNamew8GmfQM(int i) {
            return IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(0)) ? "groupSlotIndex" : super.mo1037intParamNamew8GmfQM(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class UpdateAuxData extends Operation {
        public static final UpdateAuxData INSTANCE = new UpdateAuxData();

        /* JADX WARN: Illegal instructions before constructor call */
        private UpdateAuxData() {
            int i = 1;
            super(0, i, i, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.updateAux(operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0)));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "data" : super.mo1038objectParamName31yXWZQ(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class EnsureRootGroupStarted extends Operation {
        public static final EnsureRootGroupStarted INSTANCE = new EnsureRootGroupStarted();

        /* JADX WARN: Illegal instructions before constructor call */
        private EnsureRootGroupStarted() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.ensureStarted(0);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class EnsureGroupStarted extends Operation {
        public static final EnsureGroupStarted INSTANCE = new EnsureGroupStarted();

        /* JADX WARN: Illegal instructions before constructor call */
        private EnsureGroupStarted() {
            int i = 1;
            super(0, i, i, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.ensureStarted((Anchor) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0)));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "anchor" : super.mo1038objectParamName31yXWZQ(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class RemoveCurrentGroup extends Operation {
        public static final RemoveCurrentGroup INSTANCE = new RemoveCurrentGroup();

        /* JADX WARN: Illegal instructions before constructor call */
        private RemoveCurrentGroup() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            ComposerKt.removeCurrentGroup(slotWriter, rememberManager);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class MoveCurrentGroup extends Operation {
        public static final MoveCurrentGroup INSTANCE = new MoveCurrentGroup();

        private MoveCurrentGroup() {
            super(1, 0, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.moveGroup(operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(0)));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo1037intParamNamew8GmfQM(int i) {
            return IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(0)) ? "offset" : super.mo1037intParamNamew8GmfQM(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class EndCurrentGroup extends Operation {
        public static final EndCurrentGroup INSTANCE = new EndCurrentGroup();

        /* JADX WARN: Illegal instructions before constructor call */
        private EndCurrentGroup() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.endGroup();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class SkipToEndOfCurrentGroup extends Operation {
        public static final SkipToEndOfCurrentGroup INSTANCE = new SkipToEndOfCurrentGroup();

        /* JADX WARN: Illegal instructions before constructor call */
        private SkipToEndOfCurrentGroup() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.skipToGroupEnd();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class EndCompositionScope extends Operation {
        public static final EndCompositionScope INSTANCE = new EndCompositionScope();

        private EndCompositionScope() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            ((Function1) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0))).invoke((Composition) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(1)));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "anchor" : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(1)) ? "composition" : super.mo1038objectParamName31yXWZQ(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class UseCurrentNode extends Operation {
        public static final UseCurrentNode INSTANCE = new UseCurrentNode();

        /* JADX WARN: Illegal instructions before constructor call */
        private UseCurrentNode() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            Object current = applier.getCurrent();
            Intrinsics.checkNotNull(current, "null cannot be cast to non-null type androidx.compose.runtime.ComposeNodeLifecycleCallback");
            ((ComposeNodeLifecycleCallback) current).onReuse();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class UpdateNode extends Operation {
        public static final UpdateNode INSTANCE = new UpdateNode();

        private UpdateNode() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            ((Function2) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(1))).invoke(applier.getCurrent(), operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0)));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? Action.VALUE_ATTRIBUTE : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(1)) ? "block" : super.mo1038objectParamName31yXWZQ(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class RemoveNode extends Operation {
        public static final RemoveNode INSTANCE = new RemoveNode();

        /* JADX WARN: Illegal instructions before constructor call */
        private RemoveNode() {
            int i = 2;
            super(i, 0, i, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            applier.remove(operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(0)), operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(1)));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo1037intParamNamew8GmfQM(int i) {
            return IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(0)) ? "removeIndex" : IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(1)) ? "count" : super.mo1037intParamNamew8GmfQM(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class MoveNode extends Operation {
        public static final MoveNode INSTANCE = new MoveNode();

        private MoveNode() {
            super(3, 0, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            applier.move(operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(0)), operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(1)), operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(2)));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo1037intParamNamew8GmfQM(int i) {
            return IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(0)) ? "from" : IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(1)) ? "to" : IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(2)) ? "count" : super.mo1037intParamNamew8GmfQM(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class InsertSlots extends Operation {
        public static final InsertSlots INSTANCE = new InsertSlots();

        private InsertSlots() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "anchor" : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(1)) ? "from" : super.mo1038objectParamName31yXWZQ(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            SlotTable slotTable = (SlotTable) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(1));
            Anchor anchor = (Anchor) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0));
            slotWriter.beginInsert();
            slotWriter.moveFrom(slotTable, anchor.toIndexFor(slotTable), false);
            slotWriter.endInsert();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class InsertSlotsWithFixups extends Operation {
        public static final InsertSlotsWithFixups INSTANCE = new InsertSlotsWithFixups();

        private InsertSlotsWithFixups() {
            super(0, 3, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "anchor" : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(1)) ? "from" : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(2)) ? "fixups" : super.mo1038objectParamName31yXWZQ(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            SlotTable slotTable = (SlotTable) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(1));
            Anchor anchor = (Anchor) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0));
            FixupList fixupList = (FixupList) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(2));
            SlotWriter slotWriterOpenWriter = slotTable.openWriter();
            try {
                fixupList.executeAndFlushAllPendingFixups(applier, slotWriterOpenWriter, rememberManager);
                Unit unit = Unit.INSTANCE;
                slotWriterOpenWriter.close(true);
                slotWriter.beginInsert();
                slotWriter.moveFrom(slotTable, anchor.toIndexFor(slotTable), false);
                slotWriter.endInsert();
            } catch (Throwable th) {
                slotWriterOpenWriter.close(false);
                throw th;
            }
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class InsertNodeFixup extends Operation {
        public static final InsertNodeFixup INSTANCE = new InsertNodeFixup();

        private InsertNodeFixup() {
            super(1, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            Object objInvoke = ((Function0) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0))).invoke();
            Anchor anchor = (Anchor) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(1));
            int iMo1043getIntw8GmfQM = operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(0));
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            slotWriter.updateNode(anchor, objInvoke);
            applier.insertTopDown(iMo1043getIntw8GmfQM, objInvoke);
            applier.down(objInvoke);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "factory" : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(1)) ? "groupAnchor" : super.mo1038objectParamName31yXWZQ(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo1037intParamNamew8GmfQM(int i) {
            return IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(0)) ? "insertIndex" : super.mo1037intParamNamew8GmfQM(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class PostInsertNodeFixup extends Operation {
        public static final PostInsertNodeFixup INSTANCE = new PostInsertNodeFixup();

        /* JADX WARN: Illegal instructions before constructor call */
        private PostInsertNodeFixup() {
            int i = 1;
            super(i, i, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo1037intParamNamew8GmfQM(int i) {
            return IntParameter.m1040equalsimpl0(i, IntParameter.m1039constructorimpl(0)) ? "insertIndex" : super.mo1037intParamNamew8GmfQM(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            Anchor anchor = (Anchor) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0));
            int iMo1043getIntw8GmfQM = operationArgContainer.mo1043getIntw8GmfQM(IntParameter.m1039constructorimpl(0));
            applier.up();
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            applier.insertBottomUp(iMo1043getIntw8GmfQM, slotWriter.node(anchor));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "groupAnchor" : super.mo1038objectParamName31yXWZQ(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class DeactivateCurrentGroup extends Operation {
        public static final DeactivateCurrentGroup INSTANCE = new DeactivateCurrentGroup();

        /* JADX WARN: Illegal instructions before constructor call */
        private DeactivateCurrentGroup() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            ComposerKt.deactivateCurrentGroup(slotWriter, rememberManager);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class ResetSlots extends Operation {
        public static final ResetSlots INSTANCE = new ResetSlots();

        /* JADX WARN: Illegal instructions before constructor call */
        private ResetSlots() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.reset();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class DetermineMovableContentNodeIndex extends Operation {
        public static final DetermineMovableContentNodeIndex INSTANCE = new DetermineMovableContentNodeIndex();

        private DetermineMovableContentNodeIndex() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            IntRef intRef = (IntRef) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0));
            Anchor anchor = (Anchor) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(1));
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            intRef.setElement(OperationKt.positionToInsert(slotWriter, anchor, applier));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "effectiveNodeIndexOut" : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(1)) ? "anchor" : super.mo1038objectParamName31yXWZQ(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class CopyNodesToNewAnchorLocation extends Operation {
        public static final CopyNodesToNewAnchorLocation INSTANCE = new CopyNodesToNewAnchorLocation();

        private CopyNodesToNewAnchorLocation() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            int element = ((IntRef) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0))).getElement();
            List list = (List) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(1));
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj = list.get(i);
                Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
                int i2 = element + i;
                applier.insertBottomUp(i2, obj);
                applier.insertTopDown(i2, obj);
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "effectiveNodeIndex" : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(1)) ? "nodes" : super.mo1038objectParamName31yXWZQ(i);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class CopySlotTableToAnchorLocation extends Operation {
        public static final CopySlotTableToAnchorLocation INSTANCE = new CopySlotTableToAnchorLocation();

        private CopySlotTableToAnchorLocation() {
            super(0, 4, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "resolvedState" : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(1)) ? "resolvedCompositionContext" : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(2)) ? "from" : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(3)) ? "to" : super.mo1038objectParamName31yXWZQ(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            MovableContentStateReference movableContentStateReference = (MovableContentStateReference) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(2));
            CompositionContext compositionContext = (CompositionContext) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(1));
            compositionContext.movableContentStateResolve$runtime_release(movableContentStateReference);
            ComposerKt.composeRuntimeError("Could not resolve state for movable content");
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class EndMovableContentPlacement extends Operation {
        public static final EndMovableContentPlacement INSTANCE = new EndMovableContentPlacement();

        /* JADX WARN: Illegal instructions before constructor call */
        private EndMovableContentPlacement() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            OperationKt.positionToParentOf(slotWriter, applier, 0);
            slotWriter.endGroup();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    public static final class ApplyChangeList extends Operation {
        public static final ApplyChangeList INSTANCE = new ApplyChangeList();

        private ApplyChangeList() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo1038objectParamName31yXWZQ(int i) {
            return ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(0)) ? "changes" : ObjectParameter.m1042equalsimpl0(i, ObjectParameter.m1041constructorimpl(1)) ? "effectiveNodeIndex" : super.mo1038objectParamName31yXWZQ(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
            IntRef intRef = (IntRef) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(1));
            int element = intRef != null ? intRef.getElement() : 0;
            ChangeList changeList = (ChangeList) operationArgContainer.mo1044getObject31yXWZQ(ObjectParameter.m1041constructorimpl(0));
            if (element > 0) {
                applier = new OffsetApplier(applier, element);
            }
            changeList.executeAndFlushAllPendingChanges(applier, slotWriter, rememberManager);
        }
    }
}
