package androidx.lifecycle.viewmodel;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CreationExtras.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CreationExtras {
    private final Map map = new LinkedHashMap();

    /* JADX INFO: compiled from: CreationExtras.kt */
    public interface Key {
    }

    public abstract Object get(Key key);

    public final Map getMap$lifecycle_viewmodel_release() {
        return this.map;
    }

    /* JADX INFO: compiled from: CreationExtras.kt */
    public static final class Empty extends CreationExtras {
        public static final Empty INSTANCE = new Empty();

        @Override // androidx.lifecycle.viewmodel.CreationExtras
        public Object get(Key key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return null;
        }

        private Empty() {
        }
    }
}
