package com.bumptech.glide.integration.compose;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.node.ModifierNodeElement;
import ch.qos.logback.core.CoreConstants;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.integration.compose.Transition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GlideModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\r\b\u0081\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Bk\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010 \u001a\u00020\u001fHÖ\u0001¢\u0006\u0004\b \u0010!J\u0010\u0010#\u001a\u00020\"HÖ\u0001¢\u0006\u0004\b#\u0010$J\u001a\u0010'\u001a\u00020\u00102\b\u0010&\u001a\u0004\u0018\u00010%HÖ\u0003¢\u0006\u0004\b'\u0010(R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010)R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010*R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010+R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010,R\u0016\u0010\r\u001a\u0004\u0018\u00010\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010-R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010.R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010/R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u00100R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u00101R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u00101¨\u00062"}, d2 = {"Lcom/bumptech/glide/integration/compose/GlideNodeElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Lcom/bumptech/glide/integration/compose/GlideNode;", "Lcom/bumptech/glide/RequestBuilder;", "Landroid/graphics/drawable/Drawable;", "requestBuilder", "Landroidx/compose/ui/layout/ContentScale;", "contentScale", "Landroidx/compose/ui/Alignment;", "alignment", "", "alpha", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "Lcom/bumptech/glide/integration/compose/RequestListener;", "requestListener", "", "draw", "Lcom/bumptech/glide/integration/compose/Transition$Factory;", "transitionFactory", "Landroidx/compose/ui/graphics/painter/Painter;", "loadingPlaceholder", "errorPlaceholder", "<init>", "(Lcom/bumptech/glide/RequestBuilder;Landroidx/compose/ui/layout/ContentScale;Landroidx/compose/ui/Alignment;Ljava/lang/Float;Landroidx/compose/ui/graphics/ColorFilter;Lcom/bumptech/glide/integration/compose/RequestListener;Ljava/lang/Boolean;Lcom/bumptech/glide/integration/compose/Transition$Factory;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;)V", "create", "()Lcom/bumptech/glide/integration/compose/GlideNode;", "node", "", "update", "(Lcom/bumptech/glide/integration/compose/GlideNode;)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "Lcom/bumptech/glide/RequestBuilder;", "Landroidx/compose/ui/layout/ContentScale;", "Landroidx/compose/ui/Alignment;", "Ljava/lang/Float;", "Landroidx/compose/ui/graphics/ColorFilter;", "Lcom/bumptech/glide/integration/compose/RequestListener;", "Ljava/lang/Boolean;", "Lcom/bumptech/glide/integration/compose/Transition$Factory;", "Landroidx/compose/ui/graphics/painter/Painter;", "compose_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class GlideNodeElement extends ModifierNodeElement {
    private final Alignment alignment;
    private final Float alpha;
    private final ColorFilter colorFilter;
    private final ContentScale contentScale;
    private final Boolean draw;
    private final Painter errorPlaceholder;
    private final Painter loadingPlaceholder;
    private final RequestBuilder requestBuilder;
    private final RequestListener requestListener;
    private final Transition.Factory transitionFactory;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GlideNodeElement)) {
            return false;
        }
        GlideNodeElement glideNodeElement = (GlideNodeElement) other;
        return Intrinsics.areEqual(this.requestBuilder, glideNodeElement.requestBuilder) && Intrinsics.areEqual(this.contentScale, glideNodeElement.contentScale) && Intrinsics.areEqual(this.alignment, glideNodeElement.alignment) && Intrinsics.areEqual(this.alpha, glideNodeElement.alpha) && Intrinsics.areEqual(this.colorFilter, glideNodeElement.colorFilter) && Intrinsics.areEqual(this.requestListener, glideNodeElement.requestListener) && Intrinsics.areEqual(this.draw, glideNodeElement.draw) && Intrinsics.areEqual(this.transitionFactory, glideNodeElement.transitionFactory) && Intrinsics.areEqual(this.loadingPlaceholder, glideNodeElement.loadingPlaceholder) && Intrinsics.areEqual(this.errorPlaceholder, glideNodeElement.errorPlaceholder);
    }

    public int hashCode() {
        int iHashCode = ((((this.requestBuilder.hashCode() * 31) + this.contentScale.hashCode()) * 31) + this.alignment.hashCode()) * 31;
        Float f = this.alpha;
        int iHashCode2 = (iHashCode + (f == null ? 0 : f.hashCode())) * 31;
        ColorFilter colorFilter = this.colorFilter;
        int iHashCode3 = (iHashCode2 + (colorFilter == null ? 0 : colorFilter.hashCode())) * 31;
        RequestListener requestListener = this.requestListener;
        int iHashCode4 = (iHashCode3 + (requestListener == null ? 0 : requestListener.hashCode())) * 31;
        Boolean bool = this.draw;
        int iHashCode5 = (iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
        Transition.Factory factory = this.transitionFactory;
        int iHashCode6 = (iHashCode5 + (factory == null ? 0 : factory.hashCode())) * 31;
        Painter painter = this.loadingPlaceholder;
        int iHashCode7 = (iHashCode6 + (painter == null ? 0 : painter.hashCode())) * 31;
        Painter painter2 = this.errorPlaceholder;
        return iHashCode7 + (painter2 != null ? painter2.hashCode() : 0);
    }

    public String toString() {
        return "GlideNodeElement(requestBuilder=" + this.requestBuilder + ", contentScale=" + this.contentScale + ", alignment=" + this.alignment + ", alpha=" + this.alpha + ", colorFilter=" + this.colorFilter + ", requestListener=" + this.requestListener + ", draw=" + this.draw + ", transitionFactory=" + this.transitionFactory + ", loadingPlaceholder=" + this.loadingPlaceholder + ", errorPlaceholder=" + this.errorPlaceholder + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public GlideNodeElement(RequestBuilder requestBuilder, ContentScale contentScale, Alignment alignment, Float f, ColorFilter colorFilter, RequestListener requestListener, Boolean bool, Transition.Factory factory, Painter painter, Painter painter2) {
        Intrinsics.checkNotNullParameter(requestBuilder, "requestBuilder");
        Intrinsics.checkNotNullParameter(contentScale, "contentScale");
        Intrinsics.checkNotNullParameter(alignment, "alignment");
        this.requestBuilder = requestBuilder;
        this.contentScale = contentScale;
        this.alignment = alignment;
        this.alpha = f;
        this.colorFilter = colorFilter;
        this.draw = bool;
        this.transitionFactory = factory;
        this.loadingPlaceholder = painter;
        this.errorPlaceholder = painter2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public GlideNode getNode() {
        GlideNode glideNode = new GlideNode();
        update(glideNode);
        return glideNode;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(GlideNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        node.onNewRequest(this.requestBuilder, this.contentScale, this.alignment, this.alpha, this.colorFilter, this.requestListener, this.draw, this.transitionFactory, this.loadingPlaceholder, this.errorPlaceholder);
    }
}
