package com.google.common.collect;

/* JADX INFO: loaded from: classes.dex */
public abstract class Multimaps {
    static boolean equalsImpl(Multimap multimap, Object obj) {
        if (obj == multimap) {
            return true;
        }
        if (obj instanceof Multimap) {
            return multimap.asMap().equals(((Multimap) obj).asMap());
        }
        return false;
    }
}
