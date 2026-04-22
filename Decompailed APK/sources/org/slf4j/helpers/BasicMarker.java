package org.slf4j.helpers;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes2.dex */
public class BasicMarker implements Marker {
    private final String name;
    private final List referenceList = new CopyOnWriteArrayList();

    BasicMarker(String str) {
        if (str == null) {
            throw new IllegalArgumentException("A marker name cannot be null");
        }
        this.name = str;
    }

    @Override // org.slf4j.Marker
    public String getName() {
        return this.name;
    }

    public boolean hasReferences() {
        return this.referenceList.size() > 0;
    }

    public Iterator iterator() {
        return this.referenceList.iterator();
    }

    @Override // org.slf4j.Marker
    public boolean contains(Marker marker) {
        if (marker == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }
        if (equals(marker)) {
            return true;
        }
        if (!hasReferences()) {
            return false;
        }
        Iterator it = this.referenceList.iterator();
        while (it.hasNext()) {
            if (((Marker) it.next()).contains(marker)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof Marker)) {
            return this.name.equals(((Marker) obj).getName());
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        if (!hasReferences()) {
            return getName();
        }
        Iterator it = iterator();
        StringBuilder sb = new StringBuilder(getName());
        sb.append(' ');
        sb.append("[ ");
        while (it.hasNext()) {
            sb.append(((Marker) it.next()).getName());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }
}
