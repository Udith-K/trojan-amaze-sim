package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public abstract class Collections2 {
    static boolean safeContains(Collection collection, Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }
}
