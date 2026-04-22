package androidx.compose.ui.semantics;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.ui.platform.JvmActuals_jvmKt;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: SemanticsConfiguration.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SemanticsConfiguration implements SemanticsPropertyReceiver, Iterable, KMappedMarker {
    private boolean isClearingSemantics;
    private boolean isMergingSemanticsOfDescendants;
    private final Map props = new LinkedHashMap();

    public final Object get(SemanticsPropertyKey semanticsPropertyKey) {
        Object obj = this.props.get(semanticsPropertyKey);
        if (obj != null) {
            return obj;
        }
        throw new IllegalStateException("Key not present: " + semanticsPropertyKey + " - consider getOrElse or getOrNull");
    }

    public final Object getOrElse(SemanticsPropertyKey semanticsPropertyKey, Function0 function0) {
        Object obj = this.props.get(semanticsPropertyKey);
        return obj == null ? function0.invoke() : obj;
    }

    public final Object getOrElseNullable(SemanticsPropertyKey semanticsPropertyKey, Function0 function0) {
        Object obj = this.props.get(semanticsPropertyKey);
        return obj == null ? function0.invoke() : obj;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return this.props.entrySet().iterator();
    }

    @Override // androidx.compose.ui.semantics.SemanticsPropertyReceiver
    public void set(SemanticsPropertyKey semanticsPropertyKey, Object obj) {
        if ((obj instanceof AccessibilityAction) && contains(semanticsPropertyKey)) {
            Object obj2 = this.props.get(semanticsPropertyKey);
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>");
            AccessibilityAction accessibilityAction = (AccessibilityAction) obj2;
            Map map = this.props;
            AccessibilityAction accessibilityAction2 = (AccessibilityAction) obj;
            String label = accessibilityAction2.getLabel();
            if (label == null) {
                label = accessibilityAction.getLabel();
            }
            Function action = accessibilityAction2.getAction();
            if (action == null) {
                action = accessibilityAction.getAction();
            }
            map.put(semanticsPropertyKey, new AccessibilityAction(label, action));
            return;
        }
        this.props.put(semanticsPropertyKey, obj);
    }

    public final boolean contains(SemanticsPropertyKey semanticsPropertyKey) {
        return this.props.containsKey(semanticsPropertyKey);
    }

    public final boolean containsImportantForAccessibility$ui_release() {
        Set setKeySet = this.props.keySet();
        if ((setKeySet instanceof Collection) && setKeySet.isEmpty()) {
            return false;
        }
        Iterator it = setKeySet.iterator();
        while (it.hasNext()) {
            if (((SemanticsPropertyKey) it.next()).isImportantForAccessibility$ui_release()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isMergingSemanticsOfDescendants() {
        return this.isMergingSemanticsOfDescendants;
    }

    public final void setMergingSemanticsOfDescendants(boolean z) {
        this.isMergingSemanticsOfDescendants = z;
    }

    public final boolean isClearingSemantics() {
        return this.isClearingSemantics;
    }

    public final void setClearingSemantics(boolean z) {
        this.isClearingSemantics = z;
    }

    public final void mergeChild$ui_release(SemanticsConfiguration semanticsConfiguration) {
        for (Map.Entry entry : semanticsConfiguration.props.entrySet()) {
            SemanticsPropertyKey semanticsPropertyKey = (SemanticsPropertyKey) entry.getKey();
            Object value = entry.getValue();
            Object obj = this.props.get(semanticsPropertyKey);
            Intrinsics.checkNotNull(semanticsPropertyKey, "null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsPropertyKey<kotlin.Any?>");
            Object objMerge = semanticsPropertyKey.merge(obj, value);
            if (objMerge != null) {
                this.props.put(semanticsPropertyKey, objMerge);
            }
        }
    }

    public final void collapsePeer$ui_release(SemanticsConfiguration semanticsConfiguration) {
        if (semanticsConfiguration.isMergingSemanticsOfDescendants) {
            this.isMergingSemanticsOfDescendants = true;
        }
        if (semanticsConfiguration.isClearingSemantics) {
            this.isClearingSemantics = true;
        }
        for (Map.Entry entry : semanticsConfiguration.props.entrySet()) {
            SemanticsPropertyKey semanticsPropertyKey = (SemanticsPropertyKey) entry.getKey();
            Object value = entry.getValue();
            if (!this.props.containsKey(semanticsPropertyKey)) {
                this.props.put(semanticsPropertyKey, value);
            } else if (value instanceof AccessibilityAction) {
                Object obj = this.props.get(semanticsPropertyKey);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>");
                AccessibilityAction accessibilityAction = (AccessibilityAction) obj;
                Map map = this.props;
                String label = accessibilityAction.getLabel();
                if (label == null) {
                    label = ((AccessibilityAction) value).getLabel();
                }
                Function action = accessibilityAction.getAction();
                if (action == null) {
                    action = ((AccessibilityAction) value).getAction();
                }
                map.put(semanticsPropertyKey, new AccessibilityAction(label, action));
            }
        }
    }

    public final SemanticsConfiguration copy() {
        SemanticsConfiguration semanticsConfiguration = new SemanticsConfiguration();
        semanticsConfiguration.isMergingSemanticsOfDescendants = this.isMergingSemanticsOfDescendants;
        semanticsConfiguration.isClearingSemantics = this.isClearingSemantics;
        semanticsConfiguration.props.putAll(this.props);
        return semanticsConfiguration;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SemanticsConfiguration)) {
            return false;
        }
        SemanticsConfiguration semanticsConfiguration = (SemanticsConfiguration) obj;
        return Intrinsics.areEqual(this.props, semanticsConfiguration.props) && this.isMergingSemanticsOfDescendants == semanticsConfiguration.isMergingSemanticsOfDescendants && this.isClearingSemantics == semanticsConfiguration.isClearingSemantics;
    }

    public int hashCode() {
        return (((this.props.hashCode() * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.isMergingSemanticsOfDescendants)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.isClearingSemantics);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = "";
        if (this.isMergingSemanticsOfDescendants) {
            sb.append("");
            sb.append("mergeDescendants=true");
            str = ", ";
        }
        if (this.isClearingSemantics) {
            sb.append(str);
            sb.append("isClearingSemantics=true");
            str = ", ";
        }
        for (Map.Entry entry : this.props.entrySet()) {
            SemanticsPropertyKey semanticsPropertyKey = (SemanticsPropertyKey) entry.getKey();
            Object value = entry.getValue();
            sb.append(str);
            sb.append(semanticsPropertyKey.getName());
            sb.append(" : ");
            sb.append(value);
            str = ", ";
        }
        return JvmActuals_jvmKt.simpleIdentityToString(this, null) + "{ " + ((Object) sb) + " }";
    }
}
