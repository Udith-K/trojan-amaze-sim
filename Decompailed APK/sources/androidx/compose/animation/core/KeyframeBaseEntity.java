package androidx.compose.animation.core;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class KeyframeBaseEntity {
    private Easing easing;
    private final Object value;

    public /* synthetic */ KeyframeBaseEntity(Object obj, Easing easing, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, easing);
    }

    private KeyframeBaseEntity(Object obj, Easing easing) {
        this.value = obj;
        this.easing = easing;
    }

    public final Object getValue$animation_core_release() {
        return this.value;
    }

    public final Easing getEasing$animation_core_release() {
        return this.easing;
    }

    public final void setEasing$animation_core_release(Easing easing) {
        this.easing = easing;
    }
}
