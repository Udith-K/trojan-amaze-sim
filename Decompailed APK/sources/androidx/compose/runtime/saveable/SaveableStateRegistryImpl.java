package androidx.compose.runtime.saveable;

import androidx.compose.runtime.saveable.SaveableStateRegistry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: SaveableStateRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
final class SaveableStateRegistryImpl implements SaveableStateRegistry {
    private final Function1 canBeSaved;
    private final Map restored;
    private final Map valueProviders;

    public SaveableStateRegistryImpl(Map map, Function1 function1) {
        Map mutableMap;
        this.canBeSaved = function1;
        this.restored = (map == null || (mutableMap = MapsKt.toMutableMap(map)) == null) ? new LinkedHashMap() : mutableMap;
        this.valueProviders = new LinkedHashMap();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public boolean canBeSaved(Object obj) {
        return ((Boolean) this.canBeSaved.invoke(obj)).booleanValue();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Object consumeRestored(String str) {
        List list = (List) this.restored.remove(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            this.restored.put(str, list.subList(1, list.size()));
        }
        return list.get(0);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public SaveableStateRegistry.Entry registerProvider(final String str, final Function0 function0) {
        if (SaveableStateRegistryKt.fastIsBlank(str)) {
            throw new IllegalArgumentException("Registered key is empty or blank");
        }
        Map map = this.valueProviders;
        Object arrayList = map.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList();
            map.put(str, arrayList);
        }
        ((List) arrayList).add(function0);
        return new SaveableStateRegistry.Entry() { // from class: androidx.compose.runtime.saveable.SaveableStateRegistryImpl.registerProvider.3
            @Override // androidx.compose.runtime.saveable.SaveableStateRegistry.Entry
            public void unregister() {
                List list = (List) SaveableStateRegistryImpl.this.valueProviders.remove(str);
                if (list != null) {
                    list.remove(function0);
                }
                if (list == null || list.isEmpty()) {
                    return;
                }
                SaveableStateRegistryImpl.this.valueProviders.put(str, list);
            }
        };
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Map performSave() {
        Map mutableMap = MapsKt.toMutableMap(this.restored);
        for (Map.Entry entry : this.valueProviders.entrySet()) {
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            if (list.size() == 1) {
                Object objInvoke = ((Function0) list.get(0)).invoke();
                if (objInvoke == null) {
                    continue;
                } else {
                    if (!canBeSaved(objInvoke)) {
                        throw new IllegalStateException(RememberSaveableKt.generateCannotBeSavedErrorMessage(objInvoke).toString());
                    }
                    mutableMap.put(str, CollectionsKt.arrayListOf(objInvoke));
                }
            } else {
                int size = list.size();
                ArrayList arrayList = new ArrayList(size);
                for (int i = 0; i < size; i++) {
                    Object objInvoke2 = ((Function0) list.get(i)).invoke();
                    if (objInvoke2 != null && !canBeSaved(objInvoke2)) {
                        throw new IllegalStateException(RememberSaveableKt.generateCannotBeSavedErrorMessage(objInvoke2).toString());
                    }
                    arrayList.add(objInvoke2);
                }
                mutableMap.put(str, arrayList);
            }
        }
        return mutableMap;
    }
}
