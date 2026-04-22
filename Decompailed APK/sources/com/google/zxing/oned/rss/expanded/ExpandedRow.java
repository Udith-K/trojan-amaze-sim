package com.google.zxing.oned.rss.expanded;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class ExpandedRow {
    private final List pairs;
    private final int rowNumber;
    private final boolean wasReversed;

    ExpandedRow(List list, int i, boolean z) {
        this.pairs = new ArrayList(list);
        this.rowNumber = i;
        this.wasReversed = z;
    }

    List getPairs() {
        return this.pairs;
    }

    int getRowNumber() {
        return this.rowNumber;
    }

    boolean isEquivalent(List list) {
        return this.pairs.equals(list);
    }

    public String toString() {
        return "{ " + this.pairs + " }";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ExpandedRow)) {
            return false;
        }
        ExpandedRow expandedRow = (ExpandedRow) obj;
        return this.pairs.equals(expandedRow.getPairs()) && this.wasReversed == expandedRow.wasReversed;
    }

    public int hashCode() {
        return this.pairs.hashCode() ^ Boolean.valueOf(this.wasReversed).hashCode();
    }
}
