package androidx.compose.ui.semantics;

/* JADX INFO: compiled from: SemanticsProperties.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CollectionInfo {
    private final int columnCount;
    private final int rowCount;

    public CollectionInfo(int i, int i2) {
        this.rowCount = i;
        this.columnCount = i2;
    }

    public final int getColumnCount() {
        return this.columnCount;
    }

    public final int getRowCount() {
        return this.rowCount;
    }
}
