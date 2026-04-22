package ch.qos.logback.core.spi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractComponentTracker implements ComponentTracker {
    private static final boolean ACCESS_ORDERED = true;
    public static final long LINGERING_TIMEOUT = 10000;
    public static final long WAIT_BETWEEN_SUCCESSIVE_REMOVAL_ITERATIONS = 1000;
    protected int maxComponents = Integer.MAX_VALUE;
    protected long timeout = ComponentTracker.DEFAULT_TIMEOUT;
    LinkedHashMap<String, Entry> liveMap = new LinkedHashMap<>(32, 0.75f, ACCESS_ORDERED);
    LinkedHashMap<String, Entry> lingerersMap = new LinkedHashMap<>(16, 0.75f, ACCESS_ORDERED);
    long lastCheck = 0;
    private RemovalPredicator byExcedent = new RemovalPredicator() { // from class: ch.qos.logback.core.spi.AbstractComponentTracker.1
        @Override // ch.qos.logback.core.spi.AbstractComponentTracker.RemovalPredicator
        public boolean isSlatedForRemoval(Entry entry, long j) {
            if (AbstractComponentTracker.this.liveMap.size() > AbstractComponentTracker.this.maxComponents) {
                return AbstractComponentTracker.ACCESS_ORDERED;
            }
            return false;
        }
    };
    private RemovalPredicator byTimeout = new RemovalPredicator() { // from class: ch.qos.logback.core.spi.AbstractComponentTracker.2
        @Override // ch.qos.logback.core.spi.AbstractComponentTracker.RemovalPredicator
        public boolean isSlatedForRemoval(Entry entry, long j) {
            return AbstractComponentTracker.this.isEntryStale(entry, j);
        }
    };
    private RemovalPredicator byLingering = new RemovalPredicator() { // from class: ch.qos.logback.core.spi.AbstractComponentTracker.3
        @Override // ch.qos.logback.core.spi.AbstractComponentTracker.RemovalPredicator
        public boolean isSlatedForRemoval(Entry entry, long j) {
            return AbstractComponentTracker.this.isEntryDoneLingering(entry, j);
        }
    };

    private static class Entry {
        Object component;
        String key;
        long timestamp;

        Entry(String str, Object obj, long j) {
            this.key = str;
            this.component = obj;
            this.timestamp = j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return AbstractComponentTracker.ACCESS_ORDERED;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            String str = this.key;
            if (str == null) {
                if (entry.key != null) {
                    return false;
                }
            } else if (!str.equals(entry.key)) {
                return false;
            }
            Object obj2 = this.component;
            Object obj3 = entry.component;
            if (obj2 == null) {
                if (obj3 != null) {
                    return false;
                }
            } else if (!obj2.equals(obj3)) {
                return false;
            }
            return AbstractComponentTracker.ACCESS_ORDERED;
        }

        public int hashCode() {
            return this.key.hashCode();
        }

        public void setTimestamp(long j) {
            this.timestamp = j;
        }

        public String toString() {
            return "(" + this.key + ", " + this.component + ")";
        }
    }

    private interface RemovalPredicator {
        boolean isSlatedForRemoval(Entry entry, long j);
    }

    private void genericStaleComponentRemover(LinkedHashMap<String, Entry> linkedHashMap, long j, RemovalPredicator removalPredicator) {
        Iterator<Map.Entry<String, Entry>> it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry value = it.next().getValue();
            if (!removalPredicator.isSlatedForRemoval(value, j)) {
                return;
            }
            it.remove();
            processPriorToRemoval(value.component);
        }
    }

    private Entry getFromEitherMap(String str) {
        Entry entry = this.liveMap.get(str);
        return entry != null ? entry : this.lingerersMap.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isEntryDoneLingering(Entry entry, long j) {
        if (entry.timestamp + LINGERING_TIMEOUT < j) {
            return ACCESS_ORDERED;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isEntryStale(Entry entry, long j) {
        if (!isComponentStale(entry.component) && entry.timestamp + this.timeout >= j) {
            return false;
        }
        return ACCESS_ORDERED;
    }

    private boolean isTooSoonForRemovalIteration(long j) {
        if (this.lastCheck + 1000 > j) {
            return ACCESS_ORDERED;
        }
        this.lastCheck = j;
        return false;
    }

    private void removeExcedentComponents() {
        genericStaleComponentRemover(this.liveMap, 0L, this.byExcedent);
    }

    private void removeStaleComponentsFromLingerersMap(long j) {
        genericStaleComponentRemover(this.lingerersMap, j, this.byLingering);
    }

    private void removeStaleComponentsFromMainMap(long j) {
        genericStaleComponentRemover(this.liveMap, j, this.byTimeout);
    }

    @Override // ch.qos.logback.core.spi.ComponentTracker
    public Collection<Object> allComponents() {
        ArrayList arrayList = new ArrayList();
        Iterator<Entry> it = this.liveMap.values().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().component);
        }
        Iterator<Entry> it2 = this.lingerersMap.values().iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().component);
        }
        return arrayList;
    }

    @Override // ch.qos.logback.core.spi.ComponentTracker
    public Set<String> allKeys() {
        HashSet hashSet = new HashSet(this.liveMap.keySet());
        hashSet.addAll(this.lingerersMap.keySet());
        return hashSet;
    }

    protected abstract Object buildComponent(String str);

    @Override // ch.qos.logback.core.spi.ComponentTracker
    public void endOfLife(String str) {
        Entry entryRemove = this.liveMap.remove(str);
        if (entryRemove == null) {
            return;
        }
        this.lingerersMap.put(str, entryRemove);
    }

    @Override // ch.qos.logback.core.spi.ComponentTracker
    public synchronized Object find(String str) {
        Entry fromEitherMap = getFromEitherMap(str);
        if (fromEitherMap == null) {
            return null;
        }
        return fromEitherMap.component;
    }

    @Override // ch.qos.logback.core.spi.ComponentTracker
    public int getComponentCount() {
        return this.liveMap.size() + this.lingerersMap.size();
    }

    public int getMaxComponents() {
        return this.maxComponents;
    }

    @Override // ch.qos.logback.core.spi.ComponentTracker
    public synchronized Object getOrCreate(String str, long j) {
        Entry fromEitherMap;
        try {
            fromEitherMap = getFromEitherMap(str);
            if (fromEitherMap == null) {
                Entry entry = new Entry(str, buildComponent(str), j);
                this.liveMap.put(str, entry);
                fromEitherMap = entry;
            } else {
                fromEitherMap.setTimestamp(j);
            }
        } catch (Throwable th) {
            throw th;
        }
        return fromEitherMap.component;
    }

    public long getTimeout() {
        return this.timeout;
    }

    protected abstract boolean isComponentStale(Object obj);

    protected abstract void processPriorToRemoval(Object obj);

    @Override // ch.qos.logback.core.spi.ComponentTracker
    public synchronized void removeStaleComponents(long j) {
        if (isTooSoonForRemovalIteration(j)) {
            return;
        }
        removeExcedentComponents();
        removeStaleComponentsFromMainMap(j);
        removeStaleComponentsFromLingerersMap(j);
    }

    public void setMaxComponents(int i) {
        this.maxComponents = i;
    }

    public void setTimeout(long j) {
        this.timeout = j;
    }
}
