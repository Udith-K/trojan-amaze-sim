package org.slf4j;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public interface Marker extends Serializable {
    boolean contains(Marker marker);

    String getName();
}
