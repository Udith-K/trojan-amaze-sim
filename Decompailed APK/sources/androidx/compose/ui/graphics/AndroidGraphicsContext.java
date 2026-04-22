package androidx.compose.ui.graphics;

import android.content.ComponentCallbacks2;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerImpl;
import androidx.compose.ui.graphics.layer.GraphicsLayerV23;
import androidx.compose.ui.graphics.layer.GraphicsLayerV29;
import androidx.compose.ui.graphics.layer.GraphicsViewLayer;
import androidx.compose.ui.graphics.layer.view.DrawChildContainer;
import androidx.compose.ui.graphics.layer.view.ViewLayerContainer;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AndroidGraphicsContext.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class AndroidGraphicsContext implements GraphicsContext {
    public static final Companion Companion = new Companion(null);
    private static boolean isRenderNodeCompatible = true;
    private final ViewGroup ownerView;
    private DrawChildContainer viewLayerContainer;
    private final Object lock = new Object();
    private final ComponentCallbacks2 componentCallback = null;

    public AndroidGraphicsContext(ViewGroup viewGroup) {
        this.ownerView = viewGroup;
    }

    @Override // androidx.compose.ui.graphics.GraphicsContext
    public GraphicsLayer createGraphicsLayer() {
        GraphicsLayerImpl graphicsViewLayer;
        GraphicsLayer graphicsLayer;
        synchronized (this.lock) {
            try {
                long uniqueDrawingId = getUniqueDrawingId(this.ownerView);
                if (Build.VERSION.SDK_INT >= 29) {
                    graphicsViewLayer = new GraphicsLayerV29(uniqueDrawingId, null, null, 6, null);
                } else if (isRenderNodeCompatible) {
                    try {
                        graphicsViewLayer = new GraphicsLayerV23(this.ownerView, uniqueDrawingId, null, null, 12, null);
                    } catch (Throwable unused) {
                        isRenderNodeCompatible = false;
                        graphicsViewLayer = new GraphicsViewLayer(obtainViewLayerContainer(this.ownerView), uniqueDrawingId, null, null, 12, null);
                    }
                } else {
                    graphicsViewLayer = new GraphicsViewLayer(obtainViewLayerContainer(this.ownerView), uniqueDrawingId, null, null, 12, null);
                }
                graphicsLayer = new GraphicsLayer(graphicsViewLayer, null);
            } catch (Throwable th) {
                throw th;
            }
        }
        return graphicsLayer;
    }

    @Override // androidx.compose.ui.graphics.GraphicsContext
    public void releaseGraphicsLayer(GraphicsLayer graphicsLayer) {
        synchronized (this.lock) {
            graphicsLayer.release$ui_graphics_release();
            Unit unit = Unit.INSTANCE;
        }
    }

    private final DrawChildContainer obtainViewLayerContainer(ViewGroup viewGroup) {
        DrawChildContainer drawChildContainer = this.viewLayerContainer;
        if (drawChildContainer != null) {
            return drawChildContainer;
        }
        ViewLayerContainer viewLayerContainer = new ViewLayerContainer(viewGroup.getContext());
        viewGroup.addView(viewLayerContainer);
        this.viewLayerContainer = viewLayerContainer;
        return viewLayerContainer;
    }

    private final long getUniqueDrawingId(View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return UniqueDrawingIdApi29.getUniqueDrawingId(view);
        }
        return -1L;
    }

    /* JADX INFO: compiled from: AndroidGraphicsContext.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: AndroidGraphicsContext.android.kt */
    private static final class UniqueDrawingIdApi29 {
        public static final UniqueDrawingIdApi29 INSTANCE = new UniqueDrawingIdApi29();

        private UniqueDrawingIdApi29() {
        }

        public static final long getUniqueDrawingId(View view) {
            return view.getUniqueDrawingId();
        }
    }
}
