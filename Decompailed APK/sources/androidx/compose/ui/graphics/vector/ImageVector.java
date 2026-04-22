package androidx.compose.ui.graphics.vector;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.unit.Dp;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageVector.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ImageVector {
    public static final Companion Companion = new Companion(null);
    private static int imageVectorCount;
    private final boolean autoMirror;
    private final float defaultHeight;
    private final float defaultWidth;
    private final int genId;
    private final String name;
    private final VectorGroup root;
    private final int tintBlendMode;
    private final long tintColor;
    private final float viewportHeight;
    private final float viewportWidth;

    public /* synthetic */ ImageVector(String str, float f, float f2, float f3, float f4, VectorGroup vectorGroup, long j, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, f, f2, f3, f4, vectorGroup, j, i, z, i2);
    }

    /* JADX INFO: compiled from: ImageVector.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int generateImageVectorId$ui_release() {
            int i;
            synchronized (this) {
                i = ImageVector.imageVectorCount;
                ImageVector.imageVectorCount = i + 1;
            }
            return i;
        }

        private Companion() {
        }
    }

    private ImageVector(String str, float f, float f2, float f3, float f4, VectorGroup vectorGroup, long j, int i, boolean z, int i2) {
        this.name = str;
        this.defaultWidth = f;
        this.defaultHeight = f2;
        this.viewportWidth = f3;
        this.viewportHeight = f4;
        this.root = vectorGroup;
        this.tintColor = j;
        this.tintBlendMode = i;
        this.autoMirror = z;
        this.genId = i2;
    }

    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: getDefaultWidth-D9Ej5fM, reason: not valid java name */
    public final float m1554getDefaultWidthD9Ej5fM() {
        return this.defaultWidth;
    }

    /* JADX INFO: renamed from: getDefaultHeight-D9Ej5fM, reason: not valid java name */
    public final float m1553getDefaultHeightD9Ej5fM() {
        return this.defaultHeight;
    }

    public final float getViewportWidth() {
        return this.viewportWidth;
    }

    public final float getViewportHeight() {
        return this.viewportHeight;
    }

    public final VectorGroup getRoot() {
        return this.root;
    }

    /* JADX INFO: renamed from: getTintColor-0d7_KjU, reason: not valid java name */
    public final long m1556getTintColor0d7_KjU() {
        return this.tintColor;
    }

    /* JADX INFO: renamed from: getTintBlendMode-0nO6VwU, reason: not valid java name */
    public final int m1555getTintBlendMode0nO6VwU() {
        return this.tintBlendMode;
    }

    public final boolean getAutoMirror() {
        return this.autoMirror;
    }

    public /* synthetic */ ImageVector(String str, float f, float f2, float f3, float f4, VectorGroup vectorGroup, long j, int i, boolean z, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, f, f2, f3, f4, vectorGroup, j, i, z, (i3 & 512) != 0 ? Companion.generateImageVectorId$ui_release() : i2, null);
    }

    public final int getGenId$ui_release() {
        return this.genId;
    }

    /* JADX INFO: compiled from: ImageVector.kt */
    public static final class Builder {
        private final boolean autoMirror;
        private final float defaultHeight;
        private final float defaultWidth;
        private boolean isConsumed;
        private final String name;
        private final ArrayList nodes;
        private GroupParams root;
        private final int tintBlendMode;
        private final long tintColor;
        private final float viewportHeight;
        private final float viewportWidth;

        public /* synthetic */ Builder(String str, float f, float f2, float f3, float f4, long j, int i, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, f, f2, f3, f4, j, i, z);
        }

        private Builder(String str, float f, float f2, float f3, float f4, long j, int i, boolean z) {
            this.name = str;
            this.defaultWidth = f;
            this.defaultHeight = f2;
            this.viewportWidth = f3;
            this.viewportHeight = f4;
            this.tintColor = j;
            this.tintBlendMode = i;
            this.autoMirror = z;
            ArrayList arrayList = new ArrayList();
            this.nodes = arrayList;
            GroupParams groupParams = new GroupParams(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, null, null, 1023, null);
            this.root = groupParams;
            ImageVectorKt.push(arrayList, groupParams);
        }

        public /* synthetic */ Builder(String str, float f, float f2, float f3, float f4, long j, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? "" : str, f, f2, f3, f4, (i2 & 32) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j, (i2 & 64) != 0 ? BlendMode.Companion.m1274getSrcIn0nO6VwU() : i, (i2 & 128) != 0 ? false : z, null);
        }

        private final GroupParams getCurrentGroup() {
            return (GroupParams) ImageVectorKt.peek(this.nodes);
        }

        public final Builder clearGroup() {
            ensureNotConsumed();
            getCurrentGroup().getChildren().add(asVectorGroup((GroupParams) ImageVectorKt.pop(this.nodes)));
            return this;
        }

        /* JADX INFO: renamed from: addPath-oIyEayM, reason: not valid java name */
        public final Builder m1558addPathoIyEayM(List list, int i, String str, Brush brush, float f, Brush brush2, float f2, float f3, int i2, int i3, float f4, float f5, float f6, float f7) {
            ensureNotConsumed();
            getCurrentGroup().getChildren().add(new VectorPath(str, list, i, brush, f, brush2, f2, f3, i2, i3, f4, f5, f6, f7, null));
            return this;
        }

        public final ImageVector build() {
            ensureNotConsumed();
            while (this.nodes.size() > 1) {
                clearGroup();
            }
            ImageVector imageVector = new ImageVector(this.name, this.defaultWidth, this.defaultHeight, this.viewportWidth, this.viewportHeight, asVectorGroup(this.root), this.tintColor, this.tintBlendMode, this.autoMirror, 0, 512, null);
            this.isConsumed = true;
            return imageVector;
        }

        private final void ensureNotConsumed() {
            if (this.isConsumed) {
                InlineClassHelperKt.throwIllegalStateException("ImageVector.Builder is single use, create a new instance to create a new ImageVector");
            }
        }

        private final VectorGroup asVectorGroup(GroupParams groupParams) {
            return new VectorGroup(groupParams.getName(), groupParams.getRotate(), groupParams.getPivotX(), groupParams.getPivotY(), groupParams.getScaleX(), groupParams.getScaleY(), groupParams.getTranslationX(), groupParams.getTranslationY(), groupParams.getClipPathData(), groupParams.getChildren());
        }

        /* JADX INFO: compiled from: ImageVector.kt */
        private static final class GroupParams {
            private List children;
            private List clipPathData;
            private String name;
            private float pivotX;
            private float pivotY;
            private float rotate;
            private float scaleX;
            private float scaleY;
            private float translationX;
            private float translationY;

            public GroupParams(String str, float f, float f2, float f3, float f4, float f5, float f6, float f7, List list, List list2) {
                this.name = str;
                this.rotate = f;
                this.pivotX = f2;
                this.pivotY = f3;
                this.scaleX = f4;
                this.scaleY = f5;
                this.translationX = f6;
                this.translationY = f7;
                this.clipPathData = list;
                this.children = list2;
            }

            public /* synthetic */ GroupParams(String str, float f, float f2, float f3, float f4, float f5, float f6, float f7, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? 0.0f : f, (i & 4) != 0 ? 0.0f : f2, (i & 8) != 0 ? 0.0f : f3, (i & 16) != 0 ? 1.0f : f4, (i & 32) == 0 ? f5 : 1.0f, (i & 64) != 0 ? 0.0f : f6, (i & 128) == 0 ? f7 : 0.0f, (i & 256) != 0 ? VectorKt.getEmptyPath() : list, (i & 512) != 0 ? new ArrayList() : list2);
            }

            public final String getName() {
                return this.name;
            }

            public final float getRotate() {
                return this.rotate;
            }

            public final float getPivotX() {
                return this.pivotX;
            }

            public final float getPivotY() {
                return this.pivotY;
            }

            public final float getScaleX() {
                return this.scaleX;
            }

            public final float getScaleY() {
                return this.scaleY;
            }

            public final float getTranslationX() {
                return this.translationX;
            }

            public final float getTranslationY() {
                return this.translationY;
            }

            public final List getClipPathData() {
                return this.clipPathData;
            }

            public final List getChildren() {
                return this.children;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageVector)) {
            return false;
        }
        ImageVector imageVector = (ImageVector) obj;
        return Intrinsics.areEqual(this.name, imageVector.name) && Dp.m2418equalsimpl0(this.defaultWidth, imageVector.defaultWidth) && Dp.m2418equalsimpl0(this.defaultHeight, imageVector.defaultHeight) && this.viewportWidth == imageVector.viewportWidth && this.viewportHeight == imageVector.viewportHeight && Intrinsics.areEqual(this.root, imageVector.root) && Color.m1296equalsimpl0(this.tintColor, imageVector.tintColor) && BlendMode.m1246equalsimpl0(this.tintBlendMode, imageVector.tintBlendMode) && this.autoMirror == imageVector.autoMirror;
    }

    public int hashCode() {
        return (((((((((((((((this.name.hashCode() * 31) + Dp.m2419hashCodeimpl(this.defaultWidth)) * 31) + Dp.m2419hashCodeimpl(this.defaultHeight)) * 31) + Float.floatToIntBits(this.viewportWidth)) * 31) + Float.floatToIntBits(this.viewportHeight)) * 31) + this.root.hashCode()) * 31) + Color.m1302hashCodeimpl(this.tintColor)) * 31) + BlendMode.m1247hashCodeimpl(this.tintBlendMode)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.autoMirror);
    }
}
