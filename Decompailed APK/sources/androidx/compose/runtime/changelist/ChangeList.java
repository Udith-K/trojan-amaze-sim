package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SlotTable;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.changelist.Operation;
import androidx.compose.runtime.changelist.Operations;
import androidx.compose.runtime.internal.IntRef;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: ChangeList.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ChangeList extends OperationsDebugStringFormattable {
    private final Operations operations = new Operations();

    public final boolean isEmpty() {
        return this.operations.isEmpty();
    }

    public final boolean isNotEmpty() {
        return this.operations.isNotEmpty();
    }

    public final void clear() {
        this.operations.clear();
    }

    public final void executeAndFlushAllPendingChanges(Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
        this.operations.executeAndFlushAllPendingOperations(applier, slotWriter, rememberManager);
    }

    public final void pushRemember(RememberObserver rememberObserver) {
        Operations operations = this.operations;
        Operation.Remember remember = Operation.Remember.INSTANCE;
        operations.pushOp(remember);
        Operations.WriteScope.m1052setObjectDKhxnng(Operations.WriteScope.m1049constructorimpl(operations), Operation.ObjectParameter.m1041constructorimpl(0), rememberObserver);
        if (operations.pushedIntMask == operations.createExpectedArgMask(remember.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(remember.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = remember.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(remember.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = remember.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(remember.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + remember + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushUpdateValue(Object obj, int i) {
        Operations operations = this.operations;
        Operation.UpdateValue updateValue = Operation.UpdateValue.INSTANCE;
        operations.pushOp(updateValue);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(0), obj);
        Operations.WriteScope.m1051setIntA6tL2VI(operationsM1049constructorimpl, Operation.IntParameter.m1039constructorimpl(0), i);
        if (operations.pushedIntMask == operations.createExpectedArgMask(updateValue.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(updateValue.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = updateValue.getInts();
        int i2 = 0;
        for (int i3 = 0; i3 < ints; i3++) {
            if (((1 << i3) & operations.pushedIntMask) != 0) {
                if (i2 > 0) {
                    sb.append(", ");
                }
                sb.append(updateValue.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i3)));
                i2++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = updateValue.getObjects();
        int i4 = 0;
        for (int i5 = 0; i5 < objects; i5++) {
            if (((1 << i5) & operations.pushedObjectMask) != 0) {
                if (i2 > 0) {
                    sb2.append(", ");
                }
                sb2.append(updateValue.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i5)));
                i4++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + updateValue + ". Not all arguments were provided. Missing " + i2 + " int arguments (" + string + ") and " + i4 + " object arguments (" + string2 + ").");
    }

    public final void pushUpdateAnchoredValue(Object obj, Anchor anchor, int i) {
        Operations operations = this.operations;
        Operation.UpdateAnchoredValue updateAnchoredValue = Operation.UpdateAnchoredValue.INSTANCE;
        operations.pushOp(updateAnchoredValue);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(0), obj);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(1), anchor);
        Operations.WriteScope.m1051setIntA6tL2VI(operationsM1049constructorimpl, Operation.IntParameter.m1039constructorimpl(0), i);
        if (operations.pushedIntMask == operations.createExpectedArgMask(updateAnchoredValue.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(updateAnchoredValue.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = updateAnchoredValue.getInts();
        int i2 = 0;
        for (int i3 = 0; i3 < ints; i3++) {
            if (((1 << i3) & operations.pushedIntMask) != 0) {
                if (i2 > 0) {
                    sb.append(", ");
                }
                sb.append(updateAnchoredValue.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i3)));
                i2++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = updateAnchoredValue.getObjects();
        int i4 = 0;
        for (int i5 = 0; i5 < objects; i5++) {
            if (((1 << i5) & operations.pushedObjectMask) != 0) {
                if (i2 > 0) {
                    sb2.append(", ");
                }
                sb2.append(updateAnchoredValue.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i5)));
                i4++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + updateAnchoredValue + ". Not all arguments were provided. Missing " + i2 + " int arguments (" + string + ") and " + i4 + " object arguments (" + string2 + ").");
    }

    public final void pushAppendValue(Anchor anchor, Object obj) {
        Operations operations = this.operations;
        Operation.AppendValue appendValue = Operation.AppendValue.INSTANCE;
        operations.pushOp(appendValue);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(0), anchor);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(1), obj);
        if (operations.pushedIntMask == operations.createExpectedArgMask(appendValue.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(appendValue.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = appendValue.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(appendValue.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = appendValue.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(appendValue.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + appendValue + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushTrimValues(int i) {
        Operations operations = this.operations;
        Operation.TrimParentValues trimParentValues = Operation.TrimParentValues.INSTANCE;
        operations.pushOp(trimParentValues);
        Operations.WriteScope.m1051setIntA6tL2VI(Operations.WriteScope.m1049constructorimpl(operations), Operation.IntParameter.m1039constructorimpl(0), i);
        if (operations.pushedIntMask == operations.createExpectedArgMask(trimParentValues.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(trimParentValues.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = trimParentValues.getInts();
        int i2 = 0;
        for (int i3 = 0; i3 < ints; i3++) {
            if (((1 << i3) & operations.pushedIntMask) != 0) {
                if (i2 > 0) {
                    sb.append(", ");
                }
                sb.append(trimParentValues.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i3)));
                i2++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = trimParentValues.getObjects();
        int i4 = 0;
        for (int i5 = 0; i5 < objects; i5++) {
            if (((1 << i5) & operations.pushedObjectMask) != 0) {
                if (i2 > 0) {
                    sb2.append(", ");
                }
                sb2.append(trimParentValues.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i5)));
                i4++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + trimParentValues + ". Not all arguments were provided. Missing " + i2 + " int arguments (" + string + ") and " + i4 + " object arguments (" + string2 + ").");
    }

    public final void pushResetSlots() {
        this.operations.push(Operation.ResetSlots.INSTANCE);
    }

    public final void pushDeactivateCurrentGroup() {
        this.operations.push(Operation.DeactivateCurrentGroup.INSTANCE);
    }

    public final void pushUpdateAuxData(Object obj) {
        Operations operations = this.operations;
        Operation.UpdateAuxData updateAuxData = Operation.UpdateAuxData.INSTANCE;
        operations.pushOp(updateAuxData);
        Operations.WriteScope.m1052setObjectDKhxnng(Operations.WriteScope.m1049constructorimpl(operations), Operation.ObjectParameter.m1041constructorimpl(0), obj);
        if (operations.pushedIntMask == operations.createExpectedArgMask(updateAuxData.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(updateAuxData.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = updateAuxData.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(updateAuxData.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = updateAuxData.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(updateAuxData.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + updateAuxData + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushEnsureRootStarted() {
        this.operations.push(Operation.EnsureRootGroupStarted.INSTANCE);
    }

    public final void pushEnsureGroupStarted(Anchor anchor) {
        Operations operations = this.operations;
        Operation.EnsureGroupStarted ensureGroupStarted = Operation.EnsureGroupStarted.INSTANCE;
        operations.pushOp(ensureGroupStarted);
        Operations.WriteScope.m1052setObjectDKhxnng(Operations.WriteScope.m1049constructorimpl(operations), Operation.ObjectParameter.m1041constructorimpl(0), anchor);
        if (operations.pushedIntMask == operations.createExpectedArgMask(ensureGroupStarted.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(ensureGroupStarted.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = ensureGroupStarted.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(ensureGroupStarted.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = ensureGroupStarted.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(ensureGroupStarted.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + ensureGroupStarted + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushEndCurrentGroup() {
        this.operations.push(Operation.EndCurrentGroup.INSTANCE);
    }

    public final void pushSkipToEndOfCurrentGroup() {
        this.operations.push(Operation.SkipToEndOfCurrentGroup.INSTANCE);
    }

    public final void pushRemoveCurrentGroup() {
        this.operations.push(Operation.RemoveCurrentGroup.INSTANCE);
    }

    public final void pushInsertSlots(Anchor anchor, SlotTable slotTable) {
        Operations operations = this.operations;
        Operation.InsertSlots insertSlots = Operation.InsertSlots.INSTANCE;
        operations.pushOp(insertSlots);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(0), anchor);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(1), slotTable);
        if (operations.pushedIntMask == operations.createExpectedArgMask(insertSlots.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(insertSlots.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = insertSlots.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(insertSlots.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = insertSlots.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(insertSlots.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + insertSlots + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushInsertSlots(Anchor anchor, SlotTable slotTable, FixupList fixupList) {
        Operations operations = this.operations;
        Operation.InsertSlotsWithFixups insertSlotsWithFixups = Operation.InsertSlotsWithFixups.INSTANCE;
        operations.pushOp(insertSlotsWithFixups);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(0), anchor);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(1), slotTable);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(2), fixupList);
        if (operations.pushedIntMask == operations.createExpectedArgMask(insertSlotsWithFixups.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(insertSlotsWithFixups.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = insertSlotsWithFixups.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(insertSlotsWithFixups.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = insertSlotsWithFixups.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(insertSlotsWithFixups.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + insertSlotsWithFixups + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushMoveCurrentGroup(int i) {
        Operations operations = this.operations;
        Operation.MoveCurrentGroup moveCurrentGroup = Operation.MoveCurrentGroup.INSTANCE;
        operations.pushOp(moveCurrentGroup);
        Operations.WriteScope.m1051setIntA6tL2VI(Operations.WriteScope.m1049constructorimpl(operations), Operation.IntParameter.m1039constructorimpl(0), i);
        if (operations.pushedIntMask == operations.createExpectedArgMask(moveCurrentGroup.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(moveCurrentGroup.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = moveCurrentGroup.getInts();
        int i2 = 0;
        for (int i3 = 0; i3 < ints; i3++) {
            if (((1 << i3) & operations.pushedIntMask) != 0) {
                if (i2 > 0) {
                    sb.append(", ");
                }
                sb.append(moveCurrentGroup.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i3)));
                i2++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = moveCurrentGroup.getObjects();
        int i4 = 0;
        for (int i5 = 0; i5 < objects; i5++) {
            if (((1 << i5) & operations.pushedObjectMask) != 0) {
                if (i2 > 0) {
                    sb2.append(", ");
                }
                sb2.append(moveCurrentGroup.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i5)));
                i4++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + moveCurrentGroup + ". Not all arguments were provided. Missing " + i2 + " int arguments (" + string + ") and " + i4 + " object arguments (" + string2 + ").");
    }

    public final void pushEndCompositionScope(Function1 function1, Composition composition) {
        Operations operations = this.operations;
        Operation.EndCompositionScope endCompositionScope = Operation.EndCompositionScope.INSTANCE;
        operations.pushOp(endCompositionScope);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(0), function1);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(1), composition);
        if (operations.pushedIntMask == operations.createExpectedArgMask(endCompositionScope.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(endCompositionScope.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = endCompositionScope.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(endCompositionScope.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = endCompositionScope.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(endCompositionScope.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + endCompositionScope + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushUseNode(Object obj) {
        if (obj instanceof ComposeNodeLifecycleCallback) {
            this.operations.push(Operation.UseCurrentNode.INSTANCE);
        }
    }

    public final void pushUpdateNode(Object obj, Function2 function2) {
        Operations operations = this.operations;
        Operation.UpdateNode updateNode = Operation.UpdateNode.INSTANCE;
        operations.pushOp(updateNode);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(0), obj);
        int iM1041constructorimpl = Operation.ObjectParameter.m1041constructorimpl(1);
        Intrinsics.checkNotNull(function2, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Unit>");
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, iM1041constructorimpl, (Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2));
        if (operations.pushedIntMask == operations.createExpectedArgMask(updateNode.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(updateNode.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = updateNode.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(updateNode.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = updateNode.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(updateNode.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + updateNode + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushRemoveNode(int i, int i2) {
        Operations operations = this.operations;
        Operation.RemoveNode removeNode = Operation.RemoveNode.INSTANCE;
        operations.pushOp(removeNode);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
        Operations.WriteScope.m1051setIntA6tL2VI(operationsM1049constructorimpl, Operation.IntParameter.m1039constructorimpl(0), i);
        Operations.WriteScope.m1051setIntA6tL2VI(operationsM1049constructorimpl, Operation.IntParameter.m1039constructorimpl(1), i2);
        if (operations.pushedIntMask == operations.createExpectedArgMask(removeNode.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(removeNode.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = removeNode.getInts();
        int i3 = 0;
        for (int i4 = 0; i4 < ints; i4++) {
            if (((1 << i4) & operations.pushedIntMask) != 0) {
                if (i3 > 0) {
                    sb.append(", ");
                }
                sb.append(removeNode.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i4)));
                i3++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = removeNode.getObjects();
        int i5 = 0;
        for (int i6 = 0; i6 < objects; i6++) {
            if (((1 << i6) & operations.pushedObjectMask) != 0) {
                if (i3 > 0) {
                    sb2.append(", ");
                }
                sb2.append(removeNode.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i6)));
                i5++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + removeNode + ". Not all arguments were provided. Missing " + i3 + " int arguments (" + string + ") and " + i5 + " object arguments (" + string2 + ").");
    }

    public final void pushMoveNode(int i, int i2, int i3) {
        Operations operations = this.operations;
        Operation.MoveNode moveNode = Operation.MoveNode.INSTANCE;
        operations.pushOp(moveNode);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
        Operations.WriteScope.m1051setIntA6tL2VI(operationsM1049constructorimpl, Operation.IntParameter.m1039constructorimpl(1), i);
        Operations.WriteScope.m1051setIntA6tL2VI(operationsM1049constructorimpl, Operation.IntParameter.m1039constructorimpl(0), i2);
        Operations.WriteScope.m1051setIntA6tL2VI(operationsM1049constructorimpl, Operation.IntParameter.m1039constructorimpl(2), i3);
        if (operations.pushedIntMask == operations.createExpectedArgMask(moveNode.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(moveNode.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = moveNode.getInts();
        int i4 = 0;
        for (int i5 = 0; i5 < ints; i5++) {
            if (((1 << i5) & operations.pushedIntMask) != 0) {
                if (i4 > 0) {
                    sb.append(", ");
                }
                sb.append(moveNode.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i5)));
                i4++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = moveNode.getObjects();
        int i6 = 0;
        for (int i7 = 0; i7 < objects; i7++) {
            if (((1 << i7) & operations.pushedObjectMask) != 0) {
                if (i4 > 0) {
                    sb2.append(", ");
                }
                sb2.append(moveNode.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i7)));
                i6++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + moveNode + ". Not all arguments were provided. Missing " + i4 + " int arguments (" + string + ") and " + i6 + " object arguments (" + string2 + ").");
    }

    public final void pushAdvanceSlotsBy(int i) {
        Operations operations = this.operations;
        Operation.AdvanceSlotsBy advanceSlotsBy = Operation.AdvanceSlotsBy.INSTANCE;
        operations.pushOp(advanceSlotsBy);
        Operations.WriteScope.m1051setIntA6tL2VI(Operations.WriteScope.m1049constructorimpl(operations), Operation.IntParameter.m1039constructorimpl(0), i);
        if (operations.pushedIntMask == operations.createExpectedArgMask(advanceSlotsBy.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(advanceSlotsBy.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = advanceSlotsBy.getInts();
        int i2 = 0;
        for (int i3 = 0; i3 < ints; i3++) {
            if (((1 << i3) & operations.pushedIntMask) != 0) {
                if (i2 > 0) {
                    sb.append(", ");
                }
                sb.append(advanceSlotsBy.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i3)));
                i2++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = advanceSlotsBy.getObjects();
        int i4 = 0;
        for (int i5 = 0; i5 < objects; i5++) {
            if (((1 << i5) & operations.pushedObjectMask) != 0) {
                if (i2 > 0) {
                    sb2.append(", ");
                }
                sb2.append(advanceSlotsBy.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i5)));
                i4++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + advanceSlotsBy + ". Not all arguments were provided. Missing " + i2 + " int arguments (" + string + ") and " + i4 + " object arguments (" + string2 + ").");
    }

    public final void pushUps(int i) {
        Operations operations = this.operations;
        Operation.Ups ups = Operation.Ups.INSTANCE;
        operations.pushOp(ups);
        Operations.WriteScope.m1051setIntA6tL2VI(Operations.WriteScope.m1049constructorimpl(operations), Operation.IntParameter.m1039constructorimpl(0), i);
        if (operations.pushedIntMask == operations.createExpectedArgMask(ups.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(ups.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = ups.getInts();
        int i2 = 0;
        for (int i3 = 0; i3 < ints; i3++) {
            if (((1 << i3) & operations.pushedIntMask) != 0) {
                if (i2 > 0) {
                    sb.append(", ");
                }
                sb.append(ups.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i3)));
                i2++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = ups.getObjects();
        int i4 = 0;
        for (int i5 = 0; i5 < objects; i5++) {
            if (((1 << i5) & operations.pushedObjectMask) != 0) {
                if (i2 > 0) {
                    sb2.append(", ");
                }
                sb2.append(ups.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i5)));
                i4++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + ups + ". Not all arguments were provided. Missing " + i2 + " int arguments (" + string + ") and " + i4 + " object arguments (" + string2 + ").");
    }

    public final void pushDowns(Object[] objArr) {
        if (objArr.length == 0) {
            return;
        }
        Operations operations = this.operations;
        Operation.Downs downs = Operation.Downs.INSTANCE;
        operations.pushOp(downs);
        Operations.WriteScope.m1052setObjectDKhxnng(Operations.WriteScope.m1049constructorimpl(operations), Operation.ObjectParameter.m1041constructorimpl(0), objArr);
        if (operations.pushedIntMask == operations.createExpectedArgMask(downs.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(downs.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = downs.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(downs.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = downs.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(downs.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + downs + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushSideEffect(Function0 function0) {
        Operations operations = this.operations;
        Operation.SideEffect sideEffect = Operation.SideEffect.INSTANCE;
        operations.pushOp(sideEffect);
        Operations.WriteScope.m1052setObjectDKhxnng(Operations.WriteScope.m1049constructorimpl(operations), Operation.ObjectParameter.m1041constructorimpl(0), function0);
        if (operations.pushedIntMask == operations.createExpectedArgMask(sideEffect.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(sideEffect.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = sideEffect.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(sideEffect.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = sideEffect.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(sideEffect.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + sideEffect + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushDetermineMovableContentNodeIndex(IntRef intRef, Anchor anchor) {
        Operations operations = this.operations;
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        operations.pushOp(determineMovableContentNodeIndex);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(0), intRef);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(1), anchor);
        if (operations.pushedIntMask == operations.createExpectedArgMask(determineMovableContentNodeIndex.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(determineMovableContentNodeIndex.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = determineMovableContentNodeIndex.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(determineMovableContentNodeIndex.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = determineMovableContentNodeIndex.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(determineMovableContentNodeIndex.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + determineMovableContentNodeIndex + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushCopyNodesToNewAnchorLocation(List list, IntRef intRef) {
        if (list.isEmpty()) {
            return;
        }
        Operations operations = this.operations;
        Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
        operations.pushOp(copyNodesToNewAnchorLocation);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(1), list);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(0), intRef);
        if (operations.pushedIntMask == operations.createExpectedArgMask(copyNodesToNewAnchorLocation.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(copyNodesToNewAnchorLocation.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = copyNodesToNewAnchorLocation.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(copyNodesToNewAnchorLocation.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = copyNodesToNewAnchorLocation.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(copyNodesToNewAnchorLocation.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + copyNodesToNewAnchorLocation + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushCopySlotTableToAnchorLocation(MovableContentState movableContentState, CompositionContext compositionContext, MovableContentStateReference movableContentStateReference, MovableContentStateReference movableContentStateReference2) {
        Operations operations = this.operations;
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        operations.pushOp(copySlotTableToAnchorLocation);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(0), movableContentState);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(1), compositionContext);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(3), movableContentStateReference2);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(2), movableContentStateReference);
        if (operations.pushedIntMask == operations.createExpectedArgMask(copySlotTableToAnchorLocation.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(copySlotTableToAnchorLocation.getObjects())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ints = copySlotTableToAnchorLocation.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & operations.pushedIntMask) != 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(copySlotTableToAnchorLocation.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb2 = new StringBuilder();
        int objects = copySlotTableToAnchorLocation.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & operations.pushedObjectMask) != 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(copySlotTableToAnchorLocation.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + copySlotTableToAnchorLocation + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
    }

    public final void pushEndMovableContentPlacement() {
        this.operations.push(Operation.EndMovableContentPlacement.INSTANCE);
    }

    public final void pushExecuteOperationsIn(ChangeList changeList, IntRef intRef) {
        if (changeList.isNotEmpty()) {
            Operations operations = this.operations;
            Operation.ApplyChangeList applyChangeList = Operation.ApplyChangeList.INSTANCE;
            operations.pushOp(applyChangeList);
            Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations);
            Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(0), changeList);
            Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(1), intRef);
            if (operations.pushedIntMask == operations.createExpectedArgMask(applyChangeList.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(applyChangeList.getObjects())) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            int ints = applyChangeList.getInts();
            int i = 0;
            for (int i2 = 0; i2 < ints; i2++) {
                if (((1 << i2) & operations.pushedIntMask) != 0) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(applyChangeList.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i2)));
                    i++;
                }
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
            StringBuilder sb2 = new StringBuilder();
            int objects = applyChangeList.getObjects();
            int i3 = 0;
            for (int i4 = 0; i4 < objects; i4++) {
                if (((1 << i4) & operations.pushedObjectMask) != 0) {
                    if (i > 0) {
                        sb2.append(", ");
                    }
                    sb2.append(applyChangeList.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i4)));
                    i3++;
                }
            }
            String string2 = sb2.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
            PreconditionsKt.throwIllegalStateException("Error while pushing " + applyChangeList + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").");
        }
    }
}
