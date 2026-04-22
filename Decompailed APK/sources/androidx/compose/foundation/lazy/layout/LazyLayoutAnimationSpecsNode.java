package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ParentDataModifierNode;
import androidx.compose.ui.unit.Density;

/* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyLayoutAnimationSpecsNode extends Modifier.Node implements ParentDataModifierNode {
    private FiniteAnimationSpec fadeInSpec;
    private FiniteAnimationSpec fadeOutSpec;
    private FiniteAnimationSpec placementSpec;

    @Override // androidx.compose.ui.node.ParentDataModifierNode
    public Object modifyParentData(Density density, Object obj) {
        return this;
    }

    public final FiniteAnimationSpec getFadeInSpec() {
        return this.fadeInSpec;
    }

    public final void setFadeInSpec(FiniteAnimationSpec finiteAnimationSpec) {
        this.fadeInSpec = finiteAnimationSpec;
    }

    public final FiniteAnimationSpec getPlacementSpec() {
        return this.placementSpec;
    }

    public final void setPlacementSpec(FiniteAnimationSpec finiteAnimationSpec) {
        this.placementSpec = finiteAnimationSpec;
    }

    public final FiniteAnimationSpec getFadeOutSpec() {
        return this.fadeOutSpec;
    }

    public final void setFadeOutSpec(FiniteAnimationSpec finiteAnimationSpec) {
        this.fadeOutSpec = finiteAnimationSpec;
    }

    public LazyLayoutAnimationSpecsNode(FiniteAnimationSpec finiteAnimationSpec, FiniteAnimationSpec finiteAnimationSpec2, FiniteAnimationSpec finiteAnimationSpec3) {
        this.fadeInSpec = finiteAnimationSpec;
        this.placementSpec = finiteAnimationSpec2;
        this.fadeOutSpec = finiteAnimationSpec3;
    }
}
