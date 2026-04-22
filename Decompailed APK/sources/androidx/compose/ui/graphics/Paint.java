package androidx.compose.ui.graphics;

import android.graphics.Shader;

/* JADX INFO: compiled from: Paint.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Paint {
    android.graphics.Paint asFrameworkPaint();

    float getAlpha();

    /* JADX INFO: renamed from: getBlendMode-0nO6VwU */
    int mo1222getBlendMode0nO6VwU();

    /* JADX INFO: renamed from: getColor-0d7_KjU */
    long mo1223getColor0d7_KjU();

    ColorFilter getColorFilter();

    /* JADX INFO: renamed from: getFilterQuality-f-v9h1I */
    int mo1224getFilterQualityfv9h1I();

    PathEffect getPathEffect();

    Shader getShader();

    /* JADX INFO: renamed from: getStrokeCap-KaPHkGw */
    int mo1225getStrokeCapKaPHkGw();

    /* JADX INFO: renamed from: getStrokeJoin-LxFBmk8 */
    int mo1226getStrokeJoinLxFBmk8();

    float getStrokeMiterLimit();

    float getStrokeWidth();

    void setAlpha(float f);

    /* JADX INFO: renamed from: setBlendMode-s9anfk8 */
    void mo1227setBlendModes9anfk8(int i);

    /* JADX INFO: renamed from: setColor-8_81llA */
    void mo1228setColor8_81llA(long j);

    void setColorFilter(ColorFilter colorFilter);

    /* JADX INFO: renamed from: setFilterQuality-vDHp3xo */
    void mo1229setFilterQualityvDHp3xo(int i);

    void setPathEffect(PathEffect pathEffect);

    void setShader(Shader shader);

    /* JADX INFO: renamed from: setStrokeCap-BeK7IIE */
    void mo1230setStrokeCapBeK7IIE(int i);

    /* JADX INFO: renamed from: setStrokeJoin-Ww9F2mQ */
    void mo1231setStrokeJoinWw9F2mQ(int i);

    void setStrokeMiterLimit(float f);

    void setStrokeWidth(float f);

    /* JADX INFO: renamed from: setStyle-k9PVt8s */
    void mo1232setStylek9PVt8s(int i);
}
