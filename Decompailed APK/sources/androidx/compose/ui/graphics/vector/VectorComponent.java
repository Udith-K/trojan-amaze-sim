package androidx.compose.ui.graphics.vector;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Vector.kt */
/* JADX INFO: loaded from: classes.dex */
public final class VectorComponent extends VNode {
    private final DrawCache cacheDrawScope;
    private final Function1 drawVectorBlock;
    private final MutableState intrinsicColorFilter$delegate;
    private Function0 invalidateCallback;
    private boolean isDirty;
    private String name;
    private long previousDrawSize;
    private final GroupComponent root;
    private float rootScaleX;
    private float rootScaleY;
    private ColorFilter tintFilter;
    private final MutableState viewportSize$delegate;

    public VectorComponent(GroupComponent groupComponent) {
        super(null);
        this.root = groupComponent;
        groupComponent.setInvalidateListener$ui_release(new Function1() { // from class: androidx.compose.ui.graphics.vector.VectorComponent.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((VNode) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(VNode vNode) {
                VectorComponent.this.doInvalidate();
            }
        });
        this.name = "";
        this.isDirty = true;
        this.cacheDrawScope = new DrawCache();
        this.invalidateCallback = new Function0() { // from class: androidx.compose.ui.graphics.vector.VectorComponent$invalidateCallback$1
            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1565invoke() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m1565invoke();
                return Unit.INSTANCE;
            }
        };
        this.intrinsicColorFilter$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        Size.Companion companion = Size.Companion;
        this.viewportSize$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m1190boximpl(companion.m1202getZeroNHjbRc()), null, 2, null);
        this.previousDrawSize = companion.m1201getUnspecifiedNHjbRc();
        this.rootScaleX = 1.0f;
        this.rootScaleY = 1.0f;
        this.drawVectorBlock = new Function1() { // from class: androidx.compose.ui.graphics.vector.VectorComponent$drawVectorBlock$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((DrawScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(DrawScope drawScope) {
                GroupComponent root = this.this$0.getRoot();
                VectorComponent vectorComponent = this.this$0;
                float f = vectorComponent.rootScaleX;
                float f2 = vectorComponent.rootScaleY;
                long jM1171getZeroF1C5BW0 = Offset.Companion.m1171getZeroF1C5BW0();
                DrawContext drawContext = drawScope.getDrawContext();
                long jMo1487getSizeNHjbRc = drawContext.mo1487getSizeNHjbRc();
                drawContext.getCanvas().save();
                try {
                    drawContext.getTransform().mo1493scale0AR0LA0(f, f2, jM1171getZeroF1C5BW0);
                    root.draw(drawScope);
                } finally {
                    drawContext.getCanvas().restore();
                    drawContext.mo1488setSizeuvyYCjk(jMo1487getSizeNHjbRc);
                }
            }
        };
    }

    public final GroupComponent getRoot() {
        return this.root;
    }

    public final void setName(String str) {
        this.name = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doInvalidate() {
        this.isDirty = true;
        this.invalidateCallback.invoke();
    }

    /* JADX INFO: renamed from: getCacheBitmapConfig-_sVssgQ$ui_release, reason: not valid java name */
    public final int m1562getCacheBitmapConfig_sVssgQ$ui_release() {
        ImageBitmap mCachedImage = this.cacheDrawScope.getMCachedImage();
        return mCachedImage != null ? mCachedImage.mo1217getConfig_sVssgQ() : ImageBitmapConfig.Companion.m1348getArgb8888_sVssgQ();
    }

    public final void setInvalidateCallback$ui_release(Function0 function0) {
        this.invalidateCallback = function0;
    }

    public final ColorFilter getIntrinsicColorFilter$ui_release() {
        return (ColorFilter) this.intrinsicColorFilter$delegate.getValue();
    }

    public final void setIntrinsicColorFilter$ui_release(ColorFilter colorFilter) {
        this.intrinsicColorFilter$delegate.setValue(colorFilter);
    }

    /* JADX INFO: renamed from: getViewportSize-NH-jbRc$ui_release, reason: not valid java name */
    public final long m1563getViewportSizeNHjbRc$ui_release() {
        return ((Size) this.viewportSize$delegate.getValue()).m1200unboximpl();
    }

    /* JADX INFO: renamed from: setViewportSize-uvyYCjk$ui_release, reason: not valid java name */
    public final void m1564setViewportSizeuvyYCjk$ui_release(long j) {
        this.viewportSize$delegate.setValue(Size.m1190boximpl(j));
    }

    public final void draw(DrawScope drawScope, float f, ColorFilter colorFilter) {
        int iM1348getArgb8888_sVssgQ;
        if (this.root.isTintable() && this.root.m1552getTintColor0d7_KjU() != 16 && VectorKt.tintableWithAlphaMask(getIntrinsicColorFilter$ui_release()) && VectorKt.tintableWithAlphaMask(colorFilter)) {
            iM1348getArgb8888_sVssgQ = ImageBitmapConfig.Companion.m1347getAlpha8_sVssgQ();
        } else {
            iM1348getArgb8888_sVssgQ = ImageBitmapConfig.Companion.m1348getArgb8888_sVssgQ();
        }
        int i = iM1348getArgb8888_sVssgQ;
        if (this.isDirty || !Size.m1193equalsimpl0(this.previousDrawSize, drawScope.mo1483getSizeNHjbRc()) || !ImageBitmapConfig.m1343equalsimpl0(i, m1562getCacheBitmapConfig_sVssgQ$ui_release())) {
            this.tintFilter = ImageBitmapConfig.m1343equalsimpl0(i, ImageBitmapConfig.Companion.m1347getAlpha8_sVssgQ()) ? ColorFilter.Companion.m1311tintxETnrds$default(ColorFilter.Companion, this.root.m1552getTintColor0d7_KjU(), 0, 2, null) : null;
            this.rootScaleX = Size.m1196getWidthimpl(drawScope.mo1483getSizeNHjbRc()) / Size.m1196getWidthimpl(m1563getViewportSizeNHjbRc$ui_release());
            this.rootScaleY = Size.m1194getHeightimpl(drawScope.mo1483getSizeNHjbRc()) / Size.m1194getHeightimpl(m1563getViewportSizeNHjbRc$ui_release());
            this.cacheDrawScope.m1550drawCachedImageFqjB98A(i, IntSizeKt.IntSize((int) Math.ceil(Size.m1196getWidthimpl(drawScope.mo1483getSizeNHjbRc())), (int) Math.ceil(Size.m1194getHeightimpl(drawScope.mo1483getSizeNHjbRc()))), drawScope, drawScope.getLayoutDirection(), this.drawVectorBlock);
            this.isDirty = false;
            this.previousDrawSize = drawScope.mo1483getSizeNHjbRc();
        }
        if (colorFilter == null) {
            if (getIntrinsicColorFilter$ui_release() != null) {
                colorFilter = getIntrinsicColorFilter$ui_release();
            } else {
                colorFilter = this.tintFilter;
            }
        }
        this.cacheDrawScope.drawInto(drawScope, f, colorFilter);
    }

    @Override // androidx.compose.ui.graphics.vector.VNode
    public void draw(DrawScope drawScope) {
        draw(drawScope, 1.0f, null);
    }

    public String toString() {
        String str = "Params: \tname: " + this.name + "\n\tviewportWidth: " + Size.m1196getWidthimpl(m1563getViewportSizeNHjbRc$ui_release()) + "\n\tviewportHeight: " + Size.m1194getHeightimpl(m1563getViewportSizeNHjbRc$ui_release()) + "\n";
        Intrinsics.checkNotNullExpressionValue(str, "StringBuilder().apply(builderAction).toString()");
        return str;
    }
}
