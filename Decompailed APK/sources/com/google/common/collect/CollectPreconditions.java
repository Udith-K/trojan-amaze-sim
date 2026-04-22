package com.google.common.collect;

import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes.dex */
abstract class CollectPreconditions {
    static int checkNonnegative(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " cannot be negative but was: " + i);
    }

    static void checkRemove(boolean z) {
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
    }
}
