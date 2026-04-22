package androidx.compose.foundation.text;

import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UndoManager.kt */
/* JADX INFO: loaded from: classes.dex */
public final class UndoManager {
    private boolean forceNextSnapshot;
    private Long lastSnapshot;
    private final int maxStoredCharacters;
    private Entry redoStack;
    private int storedCharacters;
    private Entry undoStack;

    public UndoManager(int i) {
        this.maxStoredCharacters = i;
    }

    public /* synthetic */ UndoManager(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 100000 : i);
    }

    /* JADX INFO: compiled from: UndoManager.kt */
    private static final class Entry {
        private Entry next;
        private TextFieldValue value;

        public Entry(Entry entry, TextFieldValue textFieldValue) {
            this.next = entry;
            this.value = textFieldValue;
        }

        public final Entry getNext() {
            return this.next;
        }

        public final void setNext(Entry entry) {
            this.next = entry;
        }

        public final TextFieldValue getValue() {
            return this.value;
        }

        public final void setValue(TextFieldValue textFieldValue) {
            this.value = textFieldValue;
        }
    }

    public static /* synthetic */ void snapshotIfNeeded$default(UndoManager undoManager, TextFieldValue textFieldValue, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = UndoManager_jvmKt.timeNowMillis();
        }
        undoManager.snapshotIfNeeded(textFieldValue, j);
    }

    public final void snapshotIfNeeded(TextFieldValue textFieldValue, long j) {
        if (!this.forceNextSnapshot) {
            Long l = this.lastSnapshot;
            if (j <= (l != null ? l.longValue() : 0L) + ((long) UndoManagerKt.getSNAPSHOTS_INTERVAL_MILLIS())) {
                return;
            }
        }
        this.lastSnapshot = Long.valueOf(j);
        makeSnapshot(textFieldValue);
    }

    public final void forceNextSnapshot() {
        this.forceNextSnapshot = true;
    }

    public final void makeSnapshot(TextFieldValue textFieldValue) {
        TextFieldValue value;
        this.forceNextSnapshot = false;
        Entry entry = this.undoStack;
        if (Intrinsics.areEqual(textFieldValue, entry != null ? entry.getValue() : null)) {
            return;
        }
        String text = textFieldValue.getText();
        Entry entry2 = this.undoStack;
        if (Intrinsics.areEqual(text, (entry2 == null || (value = entry2.getValue()) == null) ? null : value.getText())) {
            Entry entry3 = this.undoStack;
            if (entry3 == null) {
                return;
            }
            entry3.setValue(textFieldValue);
            return;
        }
        this.undoStack = new Entry(this.undoStack, textFieldValue);
        this.redoStack = null;
        int length = this.storedCharacters + textFieldValue.getText().length();
        this.storedCharacters = length;
        if (length > this.maxStoredCharacters) {
            removeLastUndo();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void removeLastUndo() {
        /*
            r3 = this;
            androidx.compose.foundation.text.UndoManager$Entry r0 = r3.undoStack
            r1 = 0
            if (r0 == 0) goto La
            androidx.compose.foundation.text.UndoManager$Entry r2 = r0.getNext()
            goto Lb
        La:
            r2 = r1
        Lb:
            if (r2 != 0) goto Le
            return
        Le:
            if (r0 == 0) goto L1b
            androidx.compose.foundation.text.UndoManager$Entry r2 = r0.getNext()
            if (r2 == 0) goto L1b
            androidx.compose.foundation.text.UndoManager$Entry r2 = r2.getNext()
            goto L1c
        L1b:
            r2 = r1
        L1c:
            if (r2 == 0) goto L23
            androidx.compose.foundation.text.UndoManager$Entry r0 = r0.getNext()
            goto Le
        L23:
            if (r0 != 0) goto L26
            goto L29
        L26:
            r0.setNext(r1)
        L29:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.UndoManager.removeLastUndo():void");
    }

    public final TextFieldValue undo() {
        Entry next;
        Entry entry = this.undoStack;
        if (entry == null || (next = entry.getNext()) == null) {
            return null;
        }
        this.undoStack = next;
        this.storedCharacters -= entry.getValue().getText().length();
        this.redoStack = new Entry(this.redoStack, entry.getValue());
        return next.getValue();
    }

    public final TextFieldValue redo() {
        Entry entry = this.redoStack;
        if (entry == null) {
            return null;
        }
        this.redoStack = entry.getNext();
        this.undoStack = new Entry(this.undoStack, entry.getValue());
        this.storedCharacters += entry.getValue().getText().length();
        return entry.getValue();
    }
}
