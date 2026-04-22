package org.fdroid.fdroid.views.apps;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.Random;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.App;

/* JADX INFO: loaded from: classes2.dex */
public class FeatureImage extends AppCompatImageView {
    private static final int NUM_SQUARES_HIGH = 2;
    private static final int NUM_SQUARES_WIDE = 4;
    private ValueAnimator alphaAnimator;
    private int baseColour;
    private int currentAlpha;
    private Paint[] trianglePaints;
    private final Path[] triangles;

    public FeatureImage(Context context) {
        super(context);
        this.triangles = new Path[16];
        this.currentAlpha = GF2Field.MASK;
        this.alphaAnimator = null;
        init(context);
    }

    public FeatureImage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.triangles = new Path[16];
        this.currentAlpha = GF2Field.MASK;
        this.alphaAnimator = null;
        init(context);
    }

    public FeatureImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.triangles = new Path[16];
        this.currentAlpha = GF2Field.MASK;
        this.alphaAnimator = null;
        init(context);
    }

    private void init(Context context) {
        float[] fArr = new float[3];
        Color.colorToHSV(ContextCompat.getColor(context, R.color.fdroid_blue), fArr);
        fArr[1] = fArr[1] * 0.5f;
        fArr[2] = fArr[2] * 0.7f;
        this.baseColour = Color.HSVToColor(fArr);
    }

    public void setColour(int i) {
        if (i == 0) {
            this.trianglePaints = null;
            return;
        }
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[1] = fArr[1] * 0.5f;
        fArr[2] = fArr[2] * 0.7f;
        int iHSVToColor = Color.HSVToColor(fArr);
        fArr[2] = fArr[2] * 0.9f;
        int iHSVToColor2 = Color.HSVToColor(fArr);
        Paint paint = new Paint();
        paint.setColor(iHSVToColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2.0f);
        Paint.Style style = Paint.Style.FILL_AND_STROKE;
        paint.setStyle(style);
        Paint paint2 = new Paint();
        paint2.setColor(iHSVToColor2);
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(2.0f);
        paint2.setStyle(style);
        Random random = new Random(iHSVToColor);
        this.trianglePaints = new Paint[this.triangles.length];
        int i2 = 0;
        while (true) {
            Paint[] paintArr = this.trianglePaints;
            if (i2 >= paintArr.length) {
                return;
            }
            paintArr[i2] = random.nextBoolean() ? paint : paint2;
            i2++;
        }
    }

    public void setColorAndAnimateChange(int i) {
        setColour(i);
        animateColourChange();
    }

    private void animateColourChange() {
        ValueAnimator valueAnimator = this.alphaAnimator;
        if (valueAnimator == null) {
            this.alphaAnimator = ValueAnimator.ofInt(0, GF2Field.MASK);
        } else {
            valueAnimator.cancel();
        }
        ValueAnimator duration = ValueAnimator.ofInt(0, GF2Field.MASK).setDuration(150L);
        this.alphaAnimator = duration;
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: org.fdroid.fdroid.views.apps.FeatureImage$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.lambda$animateColourChange$0(valueAnimator2);
            }
        });
        this.currentAlpha = 0;
        invalidate();
        this.alphaAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$animateColourChange$0(ValueAnimator valueAnimator) {
        this.currentAlpha = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        invalidate();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        Path pathCreateTriangle;
        Path pathCreateTriangle2;
        super.onSizeChanged(i, i2, i3, i4);
        int i5 = i / 4;
        int i6 = i2 / 2;
        for (int i7 = 0; i7 < 4; i7++) {
            for (int i8 = 0; i8 < 2; i8++) {
                int i9 = i7 * i5;
                int i10 = i8 * i6;
                int i11 = i9 + i5;
                int i12 = i10 + i6;
                if (i7 % 2 == 0) {
                    pathCreateTriangle = createTriangle(new Point(i9, i10), new Point(i11, i10), new Point(i11, i12));
                    pathCreateTriangle2 = createTriangle(new Point(i9, i10), new Point(i11, i12), new Point(i9, i12));
                } else {
                    pathCreateTriangle = createTriangle(new Point(i9, i10), new Point(i11, i10), new Point(i9, i12));
                    pathCreateTriangle2 = createTriangle(new Point(i9, i12), new Point(i11, i10), new Point(i11, i12));
                }
                Path[] pathArr = this.triangles;
                int i13 = (i8 * 8) + (i7 * 2);
                pathArr[i13] = pathCreateTriangle;
                pathArr[i13 + 1] = pathCreateTriangle2;
            }
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            super.onDraw(canvas);
            return;
        }
        Paint[] paintArr = this.trianglePaints;
        int i = 0;
        if (paintArr != null) {
            for (Paint paint : paintArr) {
                paint.setAlpha(this.currentAlpha);
            }
            canvas.drawColor(this.baseColour);
            while (true) {
                Path[] pathArr = this.triangles;
                if (i >= pathArr.length) {
                    return;
                }
                canvas.drawPath(pathArr[i], this.trianglePaints[i]);
                i++;
            }
        } else {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        }
    }

    private static Path createTriangle(Point point, Point point2, Point point3) {
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(point.x, point.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.close();
        return path;
    }

    public void loadImageAndDisplay(App app) {
        setColour(ContextCompat.getColor(getContext(), R.color.fdroid_blue));
        if (app.featureGraphic == null) {
            loadImageAndExtractColour(app.loadWithGlide(getContext(), app.iconFile));
        } else {
            Utils.loadWithGlide(getContext(), app.repoId, app.featureGraphic, this);
        }
    }

    private void loadImageAndExtractColour(RequestBuilder requestBuilder) {
        requestBuilder.listener(new RequestListener() { // from class: org.fdroid.fdroid.views.apps.FeatureImage.1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj, Target target, DataSource dataSource, boolean z) {
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException glideException, Object obj, Target target, boolean z) {
                FeatureImage.this.setColorAndAnimateChange(-3355444);
                return false;
            }
        }).into(this);
    }
}
