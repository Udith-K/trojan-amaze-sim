package com.bumptech.glide.integration.compose;

import android.graphics.PointF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.ScaleFactor;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.node.NodeMeasuringIntrinsics;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import ch.qos.logback.core.CoreConstants;
import com.bumptech.glide.ModelExtractorKt;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.integration.compose.DoNotTransition;
import com.bumptech.glide.integration.compose.RequestState;
import com.bumptech.glide.integration.compose.Transition;
import com.bumptech.glide.integration.ktx.AsyncGlideSize;
import com.bumptech.glide.integration.ktx.FlowsKt;
import com.bumptech.glide.integration.ktx.GlideFlowInstant;
import com.bumptech.glide.integration.ktx.ImmediateGlideSize;
import com.bumptech.glide.integration.ktx.ResolvableGlideSize;
import com.bumptech.glide.integration.ktx.Resource;
import com.bumptech.glide.integration.ktx.Size;
import com.bumptech.glide.integration.ktx.Status;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.util.Preconditions;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: GlideModifier.kt */
/* JADX INFO: loaded from: classes.dex */
public final class GlideNode extends Modifier.Node implements DrawModifierNode, LayoutModifierNode, SemanticsModifierNode {
    private Alignment alignment;
    private ColorFilter colorFilter;
    private ContentScale contentScale;
    private Job currentJob;
    private CachedPositionAndSize drawablePositionAndSize;
    private Painter errorPlaceholder;
    private boolean hasFixedSize;
    private Size inferredGlideSize;
    private Painter loadingPlaceholder;
    private Painter placeholder;
    private CachedPositionAndSize placeholderPositionAndSize;
    private Primary primary;
    private RequestBuilder requestBuilder;
    private RequestListener requestListener;
    private ResolvableGlideSize resolvableGlideSize;
    private float alpha = 1.0f;
    private Transition.Factory transitionFactory = DoNotTransition.Factory.INSTANCE;
    private boolean draw = true;
    private RequestState state = RequestState.Loading.INSTANCE;
    private boolean isFirstResource = true;
    private Transition transition = DoNotTransition.INSTANCE;
    private final Lazy callback$delegate = LazyKt.lazy(new Function0() { // from class: com.bumptech.glide.integration.compose.GlideNode$callback$2
        {
            super(0);
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.bumptech.glide.integration.compose.GlideNode$callback$2$1] */
        @Override // kotlin.jvm.functions.Function0
        public final AnonymousClass1 invoke() {
            final GlideNode glideNode = this.this$0;
            return new Drawable.Callback() { // from class: com.bumptech.glide.integration.compose.GlideNode$callback$2.1
                @Override // android.graphics.drawable.Drawable.Callback
                public void invalidateDrawable(Drawable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                    DrawModifierNodeKt.invalidateDraw(glideNode);
                }

                @Override // android.graphics.drawable.Drawable.Callback
                public void scheduleDrawable(Drawable d, Runnable what, long j) {
                    Intrinsics.checkNotNullParameter(d, "d");
                    Intrinsics.checkNotNullParameter(what, "what");
                    GlideModifierKt.getMAIN_HANDLER().postAtTime(what, j);
                }

                @Override // android.graphics.drawable.Drawable.Callback
                public void unscheduleDrawable(Drawable d, Runnable what) {
                    Intrinsics.checkNotNullParameter(d, "d");
                    Intrinsics.checkNotNullParameter(what, "what");
                    GlideModifierKt.getMAIN_HANDLER().removeCallbacks(what);
                }
            };
        }
    });

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public /* synthetic */ boolean getShouldClearDescendantSemantics() {
        return SemanticsModifierNode.CC.$default$getShouldClearDescendantSemantics(this);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public /* synthetic */ boolean getShouldMergeDescendantSemantics() {
        return SemanticsModifierNode.CC.$default$getShouldMergeDescendantSemantics(this);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return NodeMeasuringIntrinsics.INSTANCE.maxHeight$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.maxIntrinsicHeight.1
            AnonymousClass1() {
            }

            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo1803measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                return LayoutModifierNode.this.mo36measure3p2s80s(measureScope, measurable, j);
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return NodeMeasuringIntrinsics.INSTANCE.maxWidth$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.maxIntrinsicWidth.1
            C01511() {
            }

            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo1803measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                return LayoutModifierNode.this.mo36measure3p2s80s(measureScope, measurable, j);
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return NodeMeasuringIntrinsics.INSTANCE.minHeight$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.minIntrinsicHeight.1
            C01521() {
            }

            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo1803measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                return LayoutModifierNode.this.mo36measure3p2s80s(measureScope, measurable, j);
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return NodeMeasuringIntrinsics.INSTANCE.minWidth$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.minIntrinsicWidth.1
            C01531() {
            }

            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo1803measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                return LayoutModifierNode.this.mo36measure3p2s80s(measureScope, measurable, j);
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public /* synthetic */ void onMeasureResultChanged() {
        DrawModifierNode.CC.$default$onMeasureResultChanged(this);
    }

    private final Drawable.Callback getCallback() {
        return (Drawable.Callback) this.callback$delegate.getValue();
    }

    private final ImmediateGlideSize maybeImmediateSize(RequestBuilder requestBuilder) {
        Size sizeOverrideSize = SizesKt.overrideSize(requestBuilder);
        if (sizeOverrideSize != null) {
            return new ImmediateGlideSize(sizeOverrideSize);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onNewRequest(com.bumptech.glide.RequestBuilder r4, androidx.compose.ui.layout.ContentScale r5, androidx.compose.ui.Alignment r6, java.lang.Float r7, androidx.compose.ui.graphics.ColorFilter r8, com.bumptech.glide.integration.compose.RequestListener r9, java.lang.Boolean r10, com.bumptech.glide.integration.compose.Transition.Factory r11, androidx.compose.ui.graphics.painter.Painter r12, androidx.compose.ui.graphics.painter.Painter r13) {
        /*
            r3 = this;
            java.lang.String r9 = "requestBuilder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r9)
            java.lang.String r0 = "contentScale"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "alignment"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            com.bumptech.glide.RequestBuilder r0 = r3.requestBuilder
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L34
            if (r0 != 0) goto L1b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r0 = r2
        L1b:
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
            if (r9 == 0) goto L34
            androidx.compose.ui.graphics.painter.Painter r9 = r3.loadingPlaceholder
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r12, r9)
            if (r9 == 0) goto L34
            androidx.compose.ui.graphics.painter.Painter r9 = r3.errorPlaceholder
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r13, r9)
            if (r9 != 0) goto L32
            goto L34
        L32:
            r9 = 0
            goto L35
        L34:
            r9 = r1
        L35:
            r3.requestBuilder = r4
            r3.contentScale = r5
            r3.alignment = r6
            if (r7 == 0) goto L42
            float r5 = r7.floatValue()
            goto L44
        L42:
            r5 = 1065353216(0x3f800000, float:1.0)
        L44:
            r3.alpha = r5
            r3.colorFilter = r8
            if (r10 == 0) goto L4e
            boolean r1 = r10.booleanValue()
        L4e:
            r3.draw = r1
            if (r11 != 0) goto L54
            com.bumptech.glide.integration.compose.DoNotTransition$Factory r11 = com.bumptech.glide.integration.compose.DoNotTransition.Factory.INSTANCE
        L54:
            r3.transitionFactory = r11
            r3.loadingPlaceholder = r12
            r3.errorPlaceholder = r13
            com.bumptech.glide.integration.ktx.ImmediateGlideSize r5 = r3.maybeImmediateSize(r4)
            if (r5 == 0) goto L61
            goto L75
        L61:
            com.bumptech.glide.integration.ktx.Size r5 = r3.inferredGlideSize
            if (r5 == 0) goto L6c
            com.bumptech.glide.integration.ktx.ImmediateGlideSize r6 = new com.bumptech.glide.integration.ktx.ImmediateGlideSize
            r6.<init>(r5)
            r5 = r6
            goto L6d
        L6c:
            r5 = r2
        L6d:
            if (r5 == 0) goto L70
            goto L75
        L70:
            com.bumptech.glide.integration.ktx.AsyncGlideSize r5 = new com.bumptech.glide.integration.ktx.AsyncGlideSize
            r5.<init>()
        L75:
            r3.resolvableGlideSize = r5
            if (r9 == 0) goto L89
            r3.clear()
            r3.updatePrimary(r2)
            boolean r5 = r3.isAttached()
            if (r5 == 0) goto L8c
            r3.launchRequest(r4)
            goto L8c
        L89:
            androidx.compose.ui.node.DrawModifierNodeKt.invalidateDraw(r3)
        L8c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.integration.compose.GlideNode.onNewRequest(com.bumptech.glide.RequestBuilder, androidx.compose.ui.layout.ContentScale, androidx.compose.ui.Alignment, java.lang.Float, androidx.compose.ui.graphics.ColorFilter, com.bumptech.glide.integration.compose.RequestListener, java.lang.Boolean, com.bumptech.glide.integration.compose.Transition$Factory, androidx.compose.ui.graphics.painter.Painter, androidx.compose.ui.graphics.painter.Painter):void");
    }

    private final boolean isValidDimension(float f) {
        return (f <= 0.0f || Float.isInfinite(f) || Float.isNaN(f)) ? false : true;
    }

    /* JADX INFO: renamed from: isValid-uvyYCjk, reason: not valid java name */
    private final boolean m2578isValiduvyYCjk(long j) {
        return m2580isValidWidthuvyYCjk(j) && m2579isValidHeightuvyYCjk(j);
    }

    /* JADX INFO: renamed from: roundToInt-OLKMvJU, reason: not valid java name */
    private final long m2582roundToIntOLKMvJU(long j) {
        return IntSizeKt.IntSize(MathKt.roundToInt(androidx.compose.ui.geometry.Size.m1196getWidthimpl(j)), MathKt.roundToInt(androidx.compose.ui.geometry.Size.m1194getHeightimpl(j)));
    }

    /* JADX INFO: renamed from: toPointF--gyyYBs, reason: not valid java name */
    private final PointF m2583toPointFgyyYBs(long j) {
        return new PointF(IntOffset.m2457getXimpl(j), IntOffset.m2458getYimpl(j));
    }

    /* JADX INFO: compiled from: GlideModifier.kt */
    public static final class CachedPositionAndSize {
        private final PointF position;
        private final long size;

        public /* synthetic */ CachedPositionAndSize(PointF pointF, long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(pointF, j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CachedPositionAndSize)) {
                return false;
            }
            CachedPositionAndSize cachedPositionAndSize = (CachedPositionAndSize) obj;
            return Intrinsics.areEqual(this.position, cachedPositionAndSize.position) && androidx.compose.ui.geometry.Size.m1193equalsimpl0(this.size, cachedPositionAndSize.size);
        }

        public int hashCode() {
            return (this.position.hashCode() * 31) + androidx.compose.ui.geometry.Size.m1197hashCodeimpl(this.size);
        }

        public String toString() {
            return "CachedPositionAndSize(position=" + this.position + ", size=" + ((Object) androidx.compose.ui.geometry.Size.m1199toStringimpl(this.size)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        private CachedPositionAndSize(PointF position, long j) {
            Intrinsics.checkNotNullParameter(position, "position");
            this.position = position;
            this.size = j;
        }

        public final PointF getPosition() {
            return this.position;
        }

        /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
        public final long m2584getSizeNHjbRc() {
            return this.size;
        }
    }

    private final CachedPositionAndSize drawOne(ContentDrawScope contentDrawScope, Painter painter, CachedPositionAndSize cachedPositionAndSize, Function2 function2) {
        float fM1196getWidthimpl;
        float fM1194getHeightimpl;
        long jM1202getZeroNHjbRc;
        Alignment alignment;
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (painter == null) {
            return null;
        }
        if (cachedPositionAndSize == null) {
            if (m2580isValidWidthuvyYCjk(painter.mo1548getIntrinsicSizeNHjbRc())) {
                fM1196getWidthimpl = androidx.compose.ui.geometry.Size.m1196getWidthimpl(painter.mo1548getIntrinsicSizeNHjbRc());
            } else {
                fM1196getWidthimpl = androidx.compose.ui.geometry.Size.m1196getWidthimpl(contentDrawScope.mo1483getSizeNHjbRc());
            }
            if (m2579isValidHeightuvyYCjk(painter.mo1548getIntrinsicSizeNHjbRc())) {
                fM1194getHeightimpl = androidx.compose.ui.geometry.Size.m1194getHeightimpl(painter.mo1548getIntrinsicSizeNHjbRc());
            } else {
                fM1194getHeightimpl = androidx.compose.ui.geometry.Size.m1194getHeightimpl(contentDrawScope.mo1483getSizeNHjbRc());
            }
            long jSize = SizeKt.Size(fM1196getWidthimpl, fM1194getHeightimpl);
            if (m2578isValiduvyYCjk(contentDrawScope.mo1483getSizeNHjbRc())) {
                ContentScale contentScale = this.contentScale;
                if (contentScale == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("contentScale");
                    contentScale = null;
                }
                jM1202getZeroNHjbRc = ScaleFactorKt.m1783timesmw2e94(contentScale.mo1734computeScaleFactorH7hwNQA(jSize, contentDrawScope.mo1483getSizeNHjbRc()), jSize);
            } else {
                jM1202getZeroNHjbRc = androidx.compose.ui.geometry.Size.Companion.m1202getZeroNHjbRc();
            }
            Alignment alignment2 = this.alignment;
            if (alignment2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alignment");
                alignment = null;
            } else {
                alignment = alignment2;
            }
            cachedPositionAndSize = new CachedPositionAndSize(m2583toPointFgyyYBs(alignment.mo1066alignKFBX0sM(m2582roundToIntOLKMvJU(jM1202getZeroNHjbRc), m2582roundToIntOLKMvJU(contentDrawScope.mo1483getSizeNHjbRc()), contentDrawScope.getLayoutDirection())), jM1202getZeroNHjbRc, defaultConstructorMarker);
        }
        float fM1196getWidthimpl2 = androidx.compose.ui.geometry.Size.m1196getWidthimpl(contentDrawScope.mo1483getSizeNHjbRc());
        float fM1194getHeightimpl2 = androidx.compose.ui.geometry.Size.m1194getHeightimpl(contentDrawScope.mo1483getSizeNHjbRc());
        int iM1289getIntersectrtfAjoo = ClipOp.Companion.m1289getIntersectrtfAjoo();
        DrawContext drawContext = contentDrawScope.getDrawContext();
        long jMo1487getSizeNHjbRc = drawContext.mo1487getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo1490clipRectN_I0leg(0.0f, 0.0f, fM1196getWidthimpl2, fM1194getHeightimpl2, iM1289getIntersectrtfAjoo);
        float f = cachedPositionAndSize.getPosition().x;
        float f2 = cachedPositionAndSize.getPosition().y;
        contentDrawScope.getDrawContext().getTransform().translate(f, f2);
        function2.invoke(contentDrawScope, androidx.compose.ui.geometry.Size.m1190boximpl(cachedPositionAndSize.m2584getSizeNHjbRc()));
        contentDrawScope.getDrawContext().getTransform().translate(-f, -f2);
        drawContext.getCanvas().restore();
        drawContext.mo1488setSizeuvyYCjk(jMo1487getSizeNHjbRc);
        return cachedPositionAndSize;
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        final Painter painter;
        Intrinsics.checkNotNullParameter(contentDrawScope, "<this>");
        if (this.draw) {
            final Function5 drawPlaceholder = this.transition.getDrawPlaceholder();
            if (drawPlaceholder == null) {
                drawPlaceholder = DoNotTransition.INSTANCE.getDrawPlaceholder();
            }
            final Painter painter2 = this.placeholder;
            if (painter2 != null) {
                Canvas canvas = contentDrawScope.getDrawContext().getCanvas();
                try {
                    canvas.save();
                    this.placeholderPositionAndSize = drawOne(contentDrawScope, painter2, this.placeholderPositionAndSize, new Function2() { // from class: com.bumptech.glide.integration.compose.GlideNode$draw$1$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            m2586invoked16Qtg0((DrawScope) obj, ((androidx.compose.ui.geometry.Size) obj2).m1200unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-d16Qtg0, reason: not valid java name */
                        public final void m2586invoked16Qtg0(DrawScope drawOne, long j) {
                            Intrinsics.checkNotNullParameter(drawOne, "$this$drawOne");
                            drawPlaceholder.invoke(drawOne, painter2, androidx.compose.ui.geometry.Size.m1190boximpl(j), Float.valueOf(this.alpha), this.colorFilter);
                        }
                    });
                    canvas.restore();
                } finally {
                }
            }
            Primary primary = this.primary;
            if (primary != null && (painter = primary.getPainter()) != null) {
                try {
                    contentDrawScope.getDrawContext().getCanvas().save();
                    this.drawablePositionAndSize = drawOne(contentDrawScope, painter, this.drawablePositionAndSize, new Function2() { // from class: com.bumptech.glide.integration.compose.GlideNode$draw$2$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            m2587invoked16Qtg0((DrawScope) obj, ((androidx.compose.ui.geometry.Size) obj2).m1200unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-d16Qtg0, reason: not valid java name */
                        public final void m2587invoked16Qtg0(DrawScope drawOne, long j) {
                            Intrinsics.checkNotNullParameter(drawOne, "$this$drawOne");
                            this.this$0.transition.getDrawCurrent().invoke(drawOne, painter, androidx.compose.ui.geometry.Size.m1190boximpl(j), Float.valueOf(this.this$0.alpha), this.this$0.colorFilter);
                        }
                    });
                } finally {
                }
            }
        }
        contentDrawScope.drawContent();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        super.onAttach();
        if (this.currentJob == null) {
            RequestBuilder requestBuilder = this.requestBuilder;
            if (requestBuilder == null) {
                Intrinsics.throwUninitializedPropertyAccessException("requestBuilder");
                requestBuilder = null;
            }
            launchRequest(requestBuilder);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onReset() {
        super.onReset();
        clear();
        updatePrimary(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void maybeAnimate(CoroutineScope coroutineScope, Resource resource) {
        if (resource.getDataSource() == DataSource.MEMORY_CACHE || !this.isFirstResource || Intrinsics.areEqual(this.transitionFactory, DoNotTransition.Factory.INSTANCE)) {
            this.isFirstResource = false;
            this.transition = DoNotTransition.INSTANCE;
        } else {
            this.isFirstResource = false;
            this.transition = this.transitionFactory.build();
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C01751(null), 3, null);
        }
    }

    /* JADX INFO: renamed from: com.bumptech.glide.integration.compose.GlideNode$maybeAnimate$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: GlideModifier.kt */
    static final class C01751 extends SuspendLambda implements Function2 {
        int label;

        C01751(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return GlideNode.this.new C01751(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Transition transition = GlideNode.this.transition;
                final GlideNode glideNode = GlideNode.this;
                Function0 function0 = new Function0() { // from class: com.bumptech.glide.integration.compose.GlideNode.maybeAnimate.1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m2589invoke();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m2589invoke() {
                        DrawModifierNodeKt.invalidateDraw(glideNode);
                    }
                };
                this.label = 1;
                if (transition.transition(function0, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void launchRequest(final RequestBuilder requestBuilder) {
        sideEffect(new Function0() { // from class: com.bumptech.glide.integration.compose.GlideNode.launchRequest.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m2588invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m2588invoke() {
                RequestBuilder requestBuilder2 = GlideNode.this.requestBuilder;
                if (requestBuilder2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("requestBuilder");
                    requestBuilder2 = null;
                }
                if (Intrinsics.areEqual(requestBuilder2, requestBuilder)) {
                    Preconditions.checkArgument(GlideNode.this.currentJob == null);
                    GlideNode glideNode = GlideNode.this;
                    glideNode.currentJob = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.plus(glideNode.getCoroutineScope(), Dispatchers.getMain().getImmediate()), null, null, new C00441(GlideNode.this, requestBuilder, null), 3, null);
                }
            }

            /* JADX INFO: renamed from: com.bumptech.glide.integration.compose.GlideNode$launchRequest$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: GlideModifier.kt */
            static final class C00441 extends SuspendLambda implements Function2 {
                final /* synthetic */ RequestBuilder $requestBuilder;
                private /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ GlideNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00441(GlideNode glideNode, RequestBuilder requestBuilder, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = glideNode;
                    this.$requestBuilder = requestBuilder;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    C00441 c00441 = new C00441(this.this$0, this.$requestBuilder, continuation);
                    c00441.L$0 = obj;
                    return c00441;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                    return ((C00441) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                        ResolvableGlideSize resolvableGlideSize = null;
                        this.this$0.placeholder = null;
                        this.this$0.placeholderPositionAndSize = null;
                        RequestBuilder requestBuilder = this.$requestBuilder;
                        ResolvableGlideSize resolvableGlideSize2 = this.this$0.resolvableGlideSize;
                        if (resolvableGlideSize2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("resolvableGlideSize");
                        } else {
                            resolvableGlideSize = resolvableGlideSize2;
                        }
                        Flow flowFlowResolvable = FlowsKt.flowResolvable(requestBuilder, resolvableGlideSize);
                        final GlideNode glideNode = this.this$0;
                        final RequestBuilder requestBuilder2 = this.$requestBuilder;
                        FlowCollector flowCollector = new FlowCollector() { // from class: com.bumptech.glide.integration.compose.GlideNode.launchRequest.1.1.1

                            /* JADX INFO: renamed from: com.bumptech.glide.integration.compose.GlideNode$launchRequest$1$1$1$WhenMappings */
                            /* JADX INFO: compiled from: GlideModifier.kt */
                            public /* synthetic */ class WhenMappings {
                                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                                static {
                                    int[] iArr = new int[Status.values().length];
                                    try {
                                        iArr[Status.RUNNING.ordinal()] = 1;
                                    } catch (NoSuchFieldError unused) {
                                    }
                                    try {
                                        iArr[Status.CLEARED.ordinal()] = 2;
                                    } catch (NoSuchFieldError unused2) {
                                    }
                                    try {
                                        iArr[Status.FAILED.ordinal()] = 3;
                                    } catch (NoSuchFieldError unused3) {
                                    }
                                    try {
                                        iArr[Status.SUCCEEDED.ordinal()] = 4;
                                    } catch (NoSuchFieldError unused4) {
                                    }
                                    $EnumSwitchMapping$0 = iArr;
                                }
                            }

                            @Override // kotlinx.coroutines.flow.FlowCollector
                            public final Object emit(GlideFlowInstant glideFlowInstant, Continuation continuation) {
                                Object obj2;
                                Painter painter;
                                Primary primaryDrawable;
                                Pair pair;
                                if (glideFlowInstant instanceof Resource) {
                                    Resource resource = (Resource) glideFlowInstant;
                                    glideNode.maybeAnimate(coroutineScope, resource);
                                    pair = new Pair(new RequestState.Success(resource.getDataSource()), new Primary.PrimaryDrawable((Drawable) resource.getResource()));
                                } else if (glideFlowInstant instanceof com.bumptech.glide.integration.ktx.Placeholder) {
                                    int i2 = WhenMappings.$EnumSwitchMapping$0[glideFlowInstant.getStatus().ordinal()];
                                    if (i2 == 1 || i2 == 2) {
                                        obj2 = RequestState.Loading.INSTANCE;
                                    } else {
                                        if (i2 != 3) {
                                            if (i2 != 4) {
                                                throw new NoWhenBranchMatchedException();
                                            }
                                            throw new IllegalStateException();
                                        }
                                        obj2 = RequestState.Failure.INSTANCE;
                                    }
                                    if (obj2 instanceof RequestState.Loading) {
                                        painter = glideNode.loadingPlaceholder;
                                    } else if (obj2 instanceof RequestState.Failure) {
                                        painter = glideNode.errorPlaceholder;
                                    } else {
                                        if (obj2 instanceof RequestState.Success) {
                                            throw new IllegalStateException();
                                        }
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    if (painter != null) {
                                        primaryDrawable = new Primary.PrimaryPainter(painter);
                                    } else {
                                        primaryDrawable = new Primary.PrimaryDrawable(((com.bumptech.glide.integration.ktx.Placeholder) glideFlowInstant).getPlaceholder());
                                    }
                                    glideNode.placeholder = primaryDrawable.getPainter();
                                    glideNode.placeholderPositionAndSize = null;
                                    pair = new Pair(obj2, primaryDrawable);
                                } else {
                                    throw new NoWhenBranchMatchedException();
                                }
                                RequestState requestState = (RequestState) pair.component1();
                                Primary primary = (Primary) pair.component2();
                                glideNode.updatePrimary(primary);
                                RequestListener requestListener = glideNode.requestListener;
                                if (requestListener != null) {
                                    requestListener.onStateChanged(ModelExtractorKt.getInternalModel(requestBuilder2), primary.getPainter(), requestState);
                                }
                                glideNode.state = requestState;
                                if (glideNode.hasFixedSize) {
                                    DrawModifierNodeKt.invalidateDraw(glideNode);
                                } else {
                                    LayoutModifierNodeKt.invalidateMeasurement(glideNode);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        this.label = 1;
                        if (flowFlowResolvable.collect(flowCollector, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }
        });
    }

    /* JADX INFO: compiled from: GlideModifier.kt */
    private static abstract class Primary {
        public /* synthetic */ Primary(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract Drawable getDrawable();

        public abstract Painter getPainter();

        public abstract void onSet(Drawable.Callback callback);

        public abstract void onUnset();

        /* JADX INFO: compiled from: GlideModifier.kt */
        public static final class PrimaryDrawable extends Primary {
            private final Drawable drawable;
            private final Painter painter;

            public PrimaryDrawable(Drawable drawable) {
                super(0 == true ? 1 : 0);
                this.drawable = drawable;
                Drawable drawable2 = getDrawable();
                this.painter = drawable2 != null ? PainterKt.toPainter(drawable2) : null;
            }

            @Override // com.bumptech.glide.integration.compose.GlideNode.Primary
            public Drawable getDrawable() {
                return this.drawable;
            }

            @Override // com.bumptech.glide.integration.compose.GlideNode.Primary
            public Painter getPainter() {
                return this.painter;
            }

            @Override // com.bumptech.glide.integration.compose.GlideNode.Primary
            public void onUnset() {
                Drawable drawable = getDrawable();
                if (drawable != null) {
                    drawable.setCallback(null);
                }
                Drawable drawable2 = getDrawable();
                if (drawable2 != null) {
                    drawable2.setVisible(false, false);
                }
                Object drawable3 = getDrawable();
                Animatable animatable = drawable3 instanceof Animatable ? (Animatable) drawable3 : null;
                if (animatable != null) {
                    animatable.stop();
                }
            }

            @Override // com.bumptech.glide.integration.compose.GlideNode.Primary
            public void onSet(Drawable.Callback callback) {
                Intrinsics.checkNotNullParameter(callback, "callback");
                Drawable drawable = getDrawable();
                if (drawable != null) {
                    drawable.setCallback(callback);
                }
                Drawable drawable2 = getDrawable();
                if (drawable2 != null) {
                    drawable2.setVisible(true, true);
                }
                Object drawable3 = getDrawable();
                Animatable animatable = drawable3 instanceof Animatable ? (Animatable) drawable3 : null;
                if (animatable != null) {
                    animatable.start();
                }
            }
        }

        private Primary() {
        }

        /* JADX INFO: compiled from: GlideModifier.kt */
        public static final class PrimaryPainter extends Primary {
            private final Void drawable;
            private final Painter painter;

            @Override // com.bumptech.glide.integration.compose.GlideNode.Primary
            public void onSet(Drawable.Callback callback) {
                Intrinsics.checkNotNullParameter(callback, "callback");
            }

            @Override // com.bumptech.glide.integration.compose.GlideNode.Primary
            public void onUnset() {
            }

            public PrimaryPainter(Painter painter) {
                super(null);
                this.painter = painter;
            }

            @Override // com.bumptech.glide.integration.compose.GlideNode.Primary
            public /* bridge */ /* synthetic */ Drawable getDrawable() {
                return (Drawable) m2585getDrawable();
            }

            @Override // com.bumptech.glide.integration.compose.GlideNode.Primary
            public Painter getPainter() {
                return this.painter;
            }

            /* JADX INFO: renamed from: getDrawable, reason: collision with other method in class */
            public Void m2585getDrawable() {
                return this.drawable;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePrimary(Primary primary) {
        Primary primary2 = this.primary;
        if (primary2 != null) {
            primary2.onUnset();
        }
        this.primary = primary;
        if (primary != null) {
            primary.onSet(getCallback());
        }
        this.drawablePositionAndSize = null;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        super.onDetach();
        clear();
        if (Intrinsics.areEqual(this.transition, DoNotTransition.INSTANCE)) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C01761(null), 3, null);
    }

    /* JADX INFO: renamed from: com.bumptech.glide.integration.compose.GlideNode$onDetach$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: GlideModifier.kt */
    static final class C01761 extends SuspendLambda implements Function2 {
        int label;

        C01761(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return GlideNode.this.new C01761(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01761) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Transition transition = GlideNode.this.transition;
                this.label = 1;
                if (transition.stop(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void clear() {
        this.isFirstResource = true;
        Job job = this.currentJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, null, 1, null);
        }
        this.currentJob = null;
        this.state = RequestState.Loading.INSTANCE;
        updatePrimary(null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo36measure3p2s80s(MeasureScope measure, Measurable measurable, long j) {
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        ResolvableGlideSize resolvableGlideSize = null;
        this.placeholderPositionAndSize = null;
        this.drawablePositionAndSize = null;
        this.hasFixedSize = m2577hasFixedSizeBRTryo0(j);
        this.inferredGlideSize = SizesKt.m2590inferredGlideSizeBRTryo0(j);
        ResolvableGlideSize resolvableGlideSize2 = this.resolvableGlideSize;
        if (resolvableGlideSize2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolvableGlideSize");
        } else {
            resolvableGlideSize = resolvableGlideSize2;
        }
        if (resolvableGlideSize instanceof AsyncGlideSize) {
            Size size = this.inferredGlideSize;
            if (size != null) {
                ((AsyncGlideSize) resolvableGlideSize).setSize(size);
            }
        } else {
            boolean z = resolvableGlideSize instanceof ImmediateGlideSize;
        }
        final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(m2581modifyConstraintsZezNO4M(j));
        return MeasureScope.CC.layout$default(measure, placeableMo1743measureBRTryo0.getWidth(), placeableMo1743measureBRTryo0.getHeight(), null, new Function1() { // from class: com.bumptech.glide.integration.compose.GlideNode$measure$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Placeable.PlacementScope layout) {
                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                Placeable.PlacementScope.placeRelative$default(layout, placeableMo1743measureBRTryo0, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    /* JADX INFO: renamed from: hasFixedSize-BRTryo0, reason: not valid java name */
    private final boolean m2577hasFixedSizeBRTryo0(long j) {
        return Constraints.m2387getHasFixedWidthimpl(j) && Constraints.m2386getHasFixedHeightimpl(j);
    }

    /* JADX INFO: renamed from: modifyConstraints-ZezNO4M, reason: not valid java name */
    private final long m2581modifyConstraintsZezNO4M(long j) {
        Painter painter;
        int iM2391getMinWidthimpl;
        int iM2390getMinHeightimpl;
        if (m2577hasFixedSizeBRTryo0(j)) {
            return Constraints.m2381copyZbe2FdA$default(j, Constraints.m2389getMaxWidthimpl(j), 0, Constraints.m2388getMaxHeightimpl(j), 0, 10, null);
        }
        Primary primary = this.primary;
        if (primary == null || (painter = primary.getPainter()) == null) {
            return j;
        }
        long jMo1548getIntrinsicSizeNHjbRc = painter.mo1548getIntrinsicSizeNHjbRc();
        if (Constraints.m2387getHasFixedWidthimpl(j)) {
            iM2391getMinWidthimpl = Constraints.m2389getMaxWidthimpl(j);
        } else if (m2580isValidWidthuvyYCjk(jMo1548getIntrinsicSizeNHjbRc)) {
            iM2391getMinWidthimpl = MathKt.roundToInt(androidx.compose.ui.geometry.Size.m1196getWidthimpl(jMo1548getIntrinsicSizeNHjbRc));
        } else {
            iM2391getMinWidthimpl = Constraints.m2391getMinWidthimpl(j);
        }
        if (Constraints.m2386getHasFixedHeightimpl(j)) {
            iM2390getMinHeightimpl = Constraints.m2388getMaxHeightimpl(j);
        } else if (m2579isValidHeightuvyYCjk(jMo1548getIntrinsicSizeNHjbRc)) {
            iM2390getMinHeightimpl = MathKt.roundToInt(androidx.compose.ui.geometry.Size.m1194getHeightimpl(jMo1548getIntrinsicSizeNHjbRc));
        } else {
            iM2390getMinHeightimpl = Constraints.m2390getMinHeightimpl(j);
        }
        int iM2403constrainWidthK40F9xA = ConstraintsKt.m2403constrainWidthK40F9xA(j, iM2391getMinWidthimpl);
        int iM2402constrainHeightK40F9xA = ConstraintsKt.m2402constrainHeightK40F9xA(j, iM2390getMinHeightimpl);
        long jSize = SizeKt.Size(iM2391getMinWidthimpl, iM2390getMinHeightimpl);
        ContentScale contentScale = this.contentScale;
        if (contentScale == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentScale");
            contentScale = null;
        }
        long jMo1734computeScaleFactorH7hwNQA = contentScale.mo1734computeScaleFactorH7hwNQA(jSize, SizeKt.Size(iM2403constrainWidthK40F9xA, iM2402constrainHeightK40F9xA));
        if (ScaleFactor.m1778equalsimpl0(jMo1734computeScaleFactorH7hwNQA, ScaleFactor.Companion.m1781getUnspecified_hLwfpc())) {
            return j;
        }
        long jM1782timesUQTWf7w = ScaleFactorKt.m1782timesUQTWf7w(jSize, jMo1734computeScaleFactorH7hwNQA);
        return Constraints.m2381copyZbe2FdA$default(j, ConstraintsKt.m2403constrainWidthK40F9xA(j, MathKt.roundToInt(androidx.compose.ui.geometry.Size.m1196getWidthimpl(jM1782timesUQTWf7w))), 0, ConstraintsKt.m2402constrainHeightK40F9xA(j, MathKt.roundToInt(androidx.compose.ui.geometry.Size.m1194getHeightimpl(jM1782timesUQTWf7w))), 0, 10, null);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        Intrinsics.checkNotNullParameter(semanticsPropertyReceiver, "<this>");
        GlideModifierKt.setDisplayedDrawable(semanticsPropertyReceiver, new Function0() { // from class: com.bumptech.glide.integration.compose.GlideNode.applySemantics.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                Primary primary = GlideNode.this.primary;
                if (primary != null) {
                    return primary.getDrawable();
                }
                return null;
            }
        });
        GlideModifierKt.setDisplayedPainter(semanticsPropertyReceiver, new Function0() { // from class: com.bumptech.glide.integration.compose.GlideNode.applySemantics.2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Painter invoke() {
                Primary primary = GlideNode.this.primary;
                if (primary != null) {
                    return primary.getPainter();
                }
                return null;
            }
        });
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GlideNode)) {
            return false;
        }
        RequestBuilder requestBuilder = this.requestBuilder;
        Alignment alignment = null;
        if (requestBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestBuilder");
            requestBuilder = null;
        }
        GlideNode glideNode = (GlideNode) obj;
        RequestBuilder requestBuilder2 = glideNode.requestBuilder;
        if (requestBuilder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestBuilder");
            requestBuilder2 = null;
        }
        if (!Intrinsics.areEqual(requestBuilder, requestBuilder2)) {
            return false;
        }
        ContentScale contentScale = this.contentScale;
        if (contentScale == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentScale");
            contentScale = null;
        }
        ContentScale contentScale2 = glideNode.contentScale;
        if (contentScale2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentScale");
            contentScale2 = null;
        }
        if (!Intrinsics.areEqual(contentScale, contentScale2)) {
            return false;
        }
        Alignment alignment2 = this.alignment;
        if (alignment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alignment");
            alignment2 = null;
        }
        Alignment alignment3 = glideNode.alignment;
        if (alignment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alignment");
        } else {
            alignment = alignment3;
        }
        return Intrinsics.areEqual(alignment2, alignment) && Intrinsics.areEqual(this.colorFilter, glideNode.colorFilter) && Intrinsics.areEqual(this.requestListener, glideNode.requestListener) && this.draw == glideNode.draw && Intrinsics.areEqual(this.transitionFactory, glideNode.transitionFactory) && this.alpha == glideNode.alpha && Intrinsics.areEqual(this.loadingPlaceholder, glideNode.loadingPlaceholder) && Intrinsics.areEqual(this.errorPlaceholder, glideNode.errorPlaceholder);
    }

    public int hashCode() {
        RequestBuilder requestBuilder = this.requestBuilder;
        Alignment alignment = null;
        if (requestBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestBuilder");
            requestBuilder = null;
        }
        int iHashCode = requestBuilder.hashCode() * 31;
        ContentScale contentScale = this.contentScale;
        if (contentScale == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentScale");
            contentScale = null;
        }
        int iHashCode2 = (iHashCode + contentScale.hashCode()) * 31;
        Alignment alignment2 = this.alignment;
        if (alignment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alignment");
        } else {
            alignment = alignment2;
        }
        int iHashCode3 = (iHashCode2 + alignment.hashCode()) * 31;
        ColorFilter colorFilter = this.colorFilter;
        int iHashCode4 = (((iHashCode3 + (colorFilter != null ? colorFilter.hashCode() : 0)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.draw)) * 31;
        RequestListener requestListener = this.requestListener;
        int iHashCode5 = (((((iHashCode4 + (requestListener != null ? requestListener.hashCode() : 0)) * 31) + this.transitionFactory.hashCode()) * 31) + Float.floatToIntBits(this.alpha)) * 31;
        Painter painter = this.loadingPlaceholder;
        int iHashCode6 = (iHashCode5 + (painter != null ? painter.hashCode() : 0)) * 31;
        Painter painter2 = this.errorPlaceholder;
        return iHashCode6 + (painter2 != null ? painter2.hashCode() : 0);
    }

    /* JADX INFO: renamed from: isValidWidth-uvyYCjk, reason: not valid java name */
    private final boolean m2580isValidWidthuvyYCjk(long j) {
        return j != androidx.compose.ui.geometry.Size.Companion.m1201getUnspecifiedNHjbRc() && isValidDimension(androidx.compose.ui.geometry.Size.m1196getWidthimpl(j));
    }

    /* JADX INFO: renamed from: isValidHeight-uvyYCjk, reason: not valid java name */
    private final boolean m2579isValidHeightuvyYCjk(long j) {
        return j != androidx.compose.ui.geometry.Size.Companion.m1201getUnspecifiedNHjbRc() && isValidDimension(androidx.compose.ui.geometry.Size.m1194getHeightimpl(j));
    }
}
