package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.changelist.Operation;
import androidx.compose.runtime.changelist.Operations;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: FixupList.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FixupList extends OperationsDebugStringFormattable {
    private final Operations operations = new Operations();
    private final Operations pendingOperations = new Operations();

    public final boolean isEmpty() {
        return this.operations.isEmpty();
    }

    public final void clear() {
        this.pendingOperations.clear();
        this.operations.clear();
    }

    public final void executeAndFlushAllPendingFixups(Applier applier, SlotWriter slotWriter, RememberManager rememberManager) {
        if (!this.pendingOperations.isEmpty()) {
            ComposerKt.composeImmediateRuntimeError("FixupList has pending fixup operations that were not realized. Were there mismatched insertNode() and endNodeInsert() calls?");
        }
        this.operations.executeAndFlushAllPendingOperations(applier, slotWriter, rememberManager);
    }

    public final void createAndInsertNode(Function0 function0, int i, Anchor anchor) {
        Operations operations;
        Operations operations2;
        Operations operations3 = this.operations;
        Operation.InsertNodeFixup insertNodeFixup = Operation.InsertNodeFixup.INSTANCE;
        operations3.pushOp(insertNodeFixup);
        Operations operationsM1049constructorimpl = Operations.WriteScope.m1049constructorimpl(operations3);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(0), function0);
        Operations.WriteScope.m1051setIntA6tL2VI(operationsM1049constructorimpl, Operation.IntParameter.m1039constructorimpl(0), i);
        int i2 = 1;
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl, Operation.ObjectParameter.m1041constructorimpl(1), anchor);
        if (!(operations3.pushedIntMask == operations3.createExpectedArgMask(insertNodeFixup.getInts()) && operations3.pushedObjectMask == operations3.createExpectedArgMask(insertNodeFixup.getObjects()))) {
            StringBuilder sb = new StringBuilder();
            int ints = insertNodeFixup.getInts();
            int i3 = 0;
            int i4 = 0;
            while (i4 < ints) {
                if (((i2 << i4) & operations3.pushedIntMask) != 0) {
                    if (i3 > 0) {
                        sb.append(", ");
                    }
                    sb.append(insertNodeFixup.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i4)));
                    i3++;
                }
                i4++;
                i2 = 1;
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
            StringBuilder sb2 = new StringBuilder();
            int objects = insertNodeFixup.getObjects();
            int i5 = 0;
            int i6 = 0;
            while (i6 < objects) {
                if (((1 << i6) & operations3.pushedObjectMask) != 0) {
                    if (i3 > 0) {
                        sb2.append(", ");
                    }
                    operations2 = operations3;
                    sb2.append(insertNodeFixup.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i6)));
                    i5++;
                } else {
                    operations2 = operations3;
                }
                i6++;
                operations3 = operations2;
            }
            String string2 = sb2.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "StringBuilder().apply(builderAction).toString()");
            PreconditionsKt.throwIllegalStateException("Error while pushing " + insertNodeFixup + ". Not all arguments were provided. Missing " + i3 + " int arguments (" + string + ") and " + i5 + " object arguments (" + string2 + ").");
        }
        Operations operations4 = this.pendingOperations;
        Operation.PostInsertNodeFixup postInsertNodeFixup = Operation.PostInsertNodeFixup.INSTANCE;
        operations4.pushOp(postInsertNodeFixup);
        Operations operationsM1049constructorimpl2 = Operations.WriteScope.m1049constructorimpl(operations4);
        Operations.WriteScope.m1051setIntA6tL2VI(operationsM1049constructorimpl2, Operation.IntParameter.m1039constructorimpl(0), i);
        Operations.WriteScope.m1052setObjectDKhxnng(operationsM1049constructorimpl2, Operation.ObjectParameter.m1041constructorimpl(0), anchor);
        if (operations4.pushedIntMask == operations4.createExpectedArgMask(postInsertNodeFixup.getInts()) && operations4.pushedObjectMask == operations4.createExpectedArgMask(postInsertNodeFixup.getObjects())) {
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        int ints2 = postInsertNodeFixup.getInts();
        int i7 = 0;
        for (int i8 = 0; i8 < ints2; i8++) {
            if (((1 << i8) & operations4.pushedIntMask) != 0) {
                if (i7 > 0) {
                    sb3.append(", ");
                }
                sb3.append(postInsertNodeFixup.mo1037intParamNamew8GmfQM(Operation.IntParameter.m1039constructorimpl(i8)));
                i7++;
            }
        }
        String string3 = sb3.toString();
        Intrinsics.checkNotNullExpressionValue(string3, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb4 = new StringBuilder();
        int objects2 = postInsertNodeFixup.getObjects();
        int i9 = 0;
        int i10 = 0;
        while (i10 < objects2) {
            if (((1 << i10) & operations4.pushedObjectMask) != 0) {
                if (i7 > 0) {
                    sb4.append(", ");
                }
                operations = operations4;
                sb4.append(postInsertNodeFixup.mo1038objectParamName31yXWZQ(Operation.ObjectParameter.m1041constructorimpl(i10)));
                i9++;
            } else {
                operations = operations4;
            }
            i10++;
            operations4 = operations;
        }
        String string4 = sb4.toString();
        Intrinsics.checkNotNullExpressionValue(string4, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + postInsertNodeFixup + ". Not all arguments were provided. Missing " + i7 + " int arguments (" + string3 + ") and " + i9 + " object arguments (" + string4 + ").");
    }

    public final void endNodeInsert() {
        if (!this.pendingOperations.isNotEmpty()) {
            ComposerKt.composeImmediateRuntimeError("Cannot end node insertion, there are no pending operations that can be realized.");
        }
        this.pendingOperations.popInto(this.operations);
    }

    public final void updateNode(Object obj, Function2 function2) {
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
}
