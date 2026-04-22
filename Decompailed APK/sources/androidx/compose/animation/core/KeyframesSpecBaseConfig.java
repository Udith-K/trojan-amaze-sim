package androidx.compose.animation.core;

import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class KeyframesSpecBaseConfig {
    private int delayMillis;
    private int durationMillis;
    private final MutableIntObjectMap keyframes;

    public /* synthetic */ KeyframesSpecBaseConfig(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private KeyframesSpecBaseConfig() {
        this.durationMillis = 300;
        this.keyframes = IntObjectMapKt.mutableIntObjectMapOf();
    }

    public final int getDurationMillis() {
        return this.durationMillis;
    }

    public final void setDurationMillis(int i) {
        this.durationMillis = i;
    }

    public final int getDelayMillis() {
        return this.delayMillis;
    }

    public final MutableIntObjectMap getKeyframes$animation_core_release() {
        return this.keyframes;
    }

    public final KeyframeBaseEntity using(KeyframeBaseEntity keyframeBaseEntity, Easing easing) {
        keyframeBaseEntity.setEasing$animation_core_release(easing);
        return keyframeBaseEntity;
    }
}
