package androidx.compose.animation.core;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArcSpline.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ArcSpline {
    private final Arc[][] arcs;
    private final boolean isExtrapolate = true;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX WARN: Removed duplicated region for block: B:14:0x0027 A[PHI: r10
  0x0027: PHI (r10v1 int) = (r10v0 int), (r10v5 int), (r10v6 int) binds: [B:5:0x0018, B:10:0x0021, B:12:0x0024] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ArcSpline(int[] r24, float[] r25, float[][] r26) {
        /*
            r23 = this;
            r0 = r23
            r1 = r25
            r23.<init>()
            r2 = 1
            r0.isExtrapolate = r2
            int r3 = r1.length
            int r3 = r3 - r2
            androidx.compose.animation.core.ArcSpline$Arc[][] r4 = new androidx.compose.animation.core.ArcSpline.Arc[r3][]
            r5 = 0
            r7 = r2
            r8 = r7
            r6 = r5
        L12:
            if (r6 >= r3) goto L6e
            r9 = r24[r6]
            r10 = 3
            r11 = 2
            if (r9 == 0) goto L27
            if (r9 == r2) goto L30
            if (r9 == r11) goto L2e
            if (r9 == r10) goto L29
            r10 = 4
            if (r9 == r10) goto L27
            r10 = 5
            if (r9 == r10) goto L27
            goto L32
        L27:
            r8 = r10
            goto L32
        L29:
            if (r7 != r2) goto L30
            goto L2e
        L2c:
            r8 = r7
            goto L32
        L2e:
            r7 = r11
            goto L2c
        L30:
            r7 = r2
            goto L2c
        L32:
            r9 = r26[r6]
            int r10 = r9.length
            int r10 = r10 / r11
            int r9 = r9.length
            int r9 = r9 % r11
            int r10 = r10 + r9
            androidx.compose.animation.core.ArcSpline$Arc[] r9 = new androidx.compose.animation.core.ArcSpline.Arc[r10]
            r11 = r5
        L3c:
            if (r11 >= r10) goto L69
            int r12 = r11 * 2
            androidx.compose.animation.core.ArcSpline$Arc r20 = new androidx.compose.animation.core.ArcSpline$Arc
            r14 = r1[r6]
            int r13 = r6 + 1
            r15 = r1[r13]
            r16 = r26[r6]
            r17 = r16[r12]
            int r18 = r12 + 1
            r19 = r16[r18]
            r13 = r26[r13]
            r21 = r13[r12]
            r22 = r13[r18]
            r12 = r20
            r13 = r8
            r16 = r17
            r17 = r19
            r18 = r21
            r19 = r22
            r12.<init>(r13, r14, r15, r16, r17, r18, r19)
            r9[r11] = r20
            int r11 = r11 + 1
            goto L3c
        L69:
            r4[r6] = r9
            int r6 = r6 + 1
            goto L12
        L6e:
            r0.arcs = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.ArcSpline.<init>(int[], float[], float[][]):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0022, code lost:
    
        if (r9 > r0[r0.length - 1][0].getTime2()) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void getPos(float r9, float[] r10) {
        /*
            Method dump skipped, instruction units count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.ArcSpline.getPos(float, float[]):void");
    }

    public final void getSlope(float f, float[] fArr) {
        if (f < this.arcs[0][0].getTime1()) {
            f = this.arcs[0][0].getTime1();
        } else {
            Arc[][] arcArr = this.arcs;
            if (f > arcArr[arcArr.length - 1][0].getTime2()) {
                Arc[][] arcArr2 = this.arcs;
                f = arcArr2[arcArr2.length - 1][0].getTime2();
            }
        }
        int length = this.arcs.length;
        boolean z = false;
        for (int i = 0; i < length; i++) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < fArr.length) {
                if (f <= this.arcs[i][i3].getTime2()) {
                    if (this.arcs[i][i3].isLinear()) {
                        fArr[i2] = this.arcs[i][i3].getLinearDX();
                        fArr[i2 + 1] = this.arcs[i][i3].getLinearDY();
                    } else {
                        this.arcs[i][i3].setPoint(f);
                        fArr[i2] = this.arcs[i][i3].calcDX();
                        fArr[i2 + 1] = this.arcs[i][i3].calcDY();
                    }
                    z = true;
                }
                i2 += 2;
                i3++;
            }
            if (z) {
                return;
            }
        }
    }

    /* JADX INFO: compiled from: ArcSpline.kt */
    public static final class Arc {
        private static float[] _ourPercent;
        private float arcDistance;
        private final float arcVelocity;
        private final float ellipseA;
        private final float ellipseB;
        private final float ellipseCenterX;
        private final float ellipseCenterY;
        private final boolean isLinear;
        private final boolean isVertical;
        private final float[] lut;
        private final float oneOverDeltaTime;
        private final float time1;
        private final float time2;
        private float tmpCosAngle;
        private float tmpSinAngle;
        private final float x1;
        private final float x2;
        private final float y1;
        private final float y2;
        public static final Companion Companion = new Companion(null);
        public static final int $stable = 8;

        public Arc(int i, float f, float f2, float f3, float f4, float f5, float f6) {
            this.time1 = f;
            this.time2 = f2;
            this.x1 = f3;
            this.y1 = f4;
            this.x2 = f5;
            this.y2 = f6;
            float f7 = f5 - f3;
            float f8 = f6 - f4;
            boolean z = true;
            boolean z2 = i == 1 || (i == 4 ? f8 > 0.0f : !(i != 5 || f8 >= 0.0f));
            this.isVertical = z2;
            float f9 = 1 / (f2 - f);
            this.oneOverDeltaTime = f9;
            boolean z3 = 3 == i;
            if (z3 || Math.abs(f7) < 0.001f || Math.abs(f8) < 0.001f) {
                float fHypot = (float) Math.hypot(f8, f7);
                this.arcDistance = fHypot;
                this.arcVelocity = fHypot * f9;
                this.ellipseCenterX = f7 / (f2 - f);
                this.ellipseCenterY = f8 / (f2 - f);
                this.lut = new float[101];
                this.ellipseA = Float.NaN;
                this.ellipseB = Float.NaN;
            } else {
                this.lut = new float[101];
                this.ellipseA = f7 * (z2 ? -1 : 1);
                this.ellipseB = f8 * (z2 ? 1 : -1);
                this.ellipseCenterX = z2 ? f5 : f3;
                this.ellipseCenterY = z2 ? f4 : f6;
                buildTable(f3, f4, f5, f6);
                this.arcVelocity = this.arcDistance * f9;
                z = z3;
            }
            this.isLinear = z;
        }

        public final float getTime1() {
            return this.time1;
        }

        public final float getTime2() {
            return this.time2;
        }

        public final boolean isLinear() {
            return this.isLinear;
        }

        public final void setPoint(float f) {
            double dLookup = lookup((this.isVertical ? this.time2 - f : f - this.time1) * this.oneOverDeltaTime) * 1.5707964f;
            this.tmpSinAngle = (float) Math.sin(dLookup);
            this.tmpCosAngle = (float) Math.cos(dLookup);
        }

        public final float calcX() {
            return this.ellipseCenterX + (this.ellipseA * this.tmpSinAngle);
        }

        public final float calcY() {
            return this.ellipseCenterY + (this.ellipseB * this.tmpCosAngle);
        }

        public final float calcDX() {
            float f = this.ellipseA * this.tmpCosAngle;
            float fHypot = this.arcVelocity / ((float) Math.hypot(f, (-this.ellipseB) * this.tmpSinAngle));
            if (this.isVertical) {
                f = -f;
            }
            return f * fHypot;
        }

        public final float calcDY() {
            float f = this.ellipseA * this.tmpCosAngle;
            float f2 = (-this.ellipseB) * this.tmpSinAngle;
            float fHypot = this.arcVelocity / ((float) Math.hypot(f, f2));
            return this.isVertical ? (-f2) * fHypot : f2 * fHypot;
        }

        public final float getLinearX(float f) {
            float f2 = (f - this.time1) * this.oneOverDeltaTime;
            float f3 = this.x1;
            return f3 + (f2 * (this.x2 - f3));
        }

        public final float getLinearY(float f) {
            float f2 = (f - this.time1) * this.oneOverDeltaTime;
            float f3 = this.y1;
            return f3 + (f2 * (this.y2 - f3));
        }

        public final float getLinearDX() {
            return this.ellipseCenterX;
        }

        public final float getLinearDY() {
            return this.ellipseCenterY;
        }

        private final float lookup(float f) {
            if (f <= 0.0f) {
                return 0.0f;
            }
            if (f >= 1.0f) {
                return 1.0f;
            }
            float[] fArr = this.lut;
            float length = f * (fArr.length - 1);
            int i = (int) length;
            float f2 = length - i;
            float f3 = fArr[i];
            return f3 + (f2 * (fArr[i + 1] - f3));
        }

        private final void buildTable(float f, float f2, float f3, float f4) {
            float f5 = f3 - f;
            float f6 = f2 - f4;
            int length = Companion.getOurPercent().length;
            float fHypot = 0.0f;
            float f7 = 0.0f;
            float f8 = 0.0f;
            int i = 0;
            while (i < length) {
                Companion companion = Companion;
                double radians = (float) Math.toRadians((((double) i) * 90.0d) / ((double) (companion.getOurPercent().length - 1)));
                float fSin = ((float) Math.sin(radians)) * f5;
                float fCos = ((float) Math.cos(radians)) * f6;
                if (i > 0) {
                    fHypot += (float) Math.hypot(fSin - f7, fCos - f8);
                    companion.getOurPercent()[i] = fHypot;
                }
                i++;
                f8 = fCos;
                f7 = fSin;
            }
            this.arcDistance = fHypot;
            int length2 = Companion.getOurPercent().length;
            for (int i2 = 0; i2 < length2; i2++) {
                float[] ourPercent = Companion.getOurPercent();
                ourPercent[i2] = ourPercent[i2] / fHypot;
            }
            int length3 = this.lut.length;
            for (int i3 = 0; i3 < length3; i3++) {
                float length4 = i3 / (this.lut.length - 1);
                Companion companion2 = Companion;
                int iBinarySearch$default = ArraysKt.binarySearch$default(companion2.getOurPercent(), length4, 0, 0, 6, null);
                if (iBinarySearch$default >= 0) {
                    this.lut[i3] = iBinarySearch$default / (companion2.getOurPercent().length - 1);
                } else if (iBinarySearch$default == -1) {
                    this.lut[i3] = 0.0f;
                } else {
                    int i4 = -iBinarySearch$default;
                    int i5 = i4 - 2;
                    this.lut[i3] = (i5 + ((length4 - companion2.getOurPercent()[i5]) / (companion2.getOurPercent()[i4 - 1] - companion2.getOurPercent()[i5]))) / (companion2.getOurPercent().length - 1);
                }
            }
        }

        /* JADX INFO: compiled from: ArcSpline.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final float[] getOurPercent() {
                if (Arc._ourPercent != null) {
                    float[] fArr = Arc._ourPercent;
                    Intrinsics.checkNotNull(fArr);
                    return fArr;
                }
                Arc._ourPercent = new float[91];
                float[] fArr2 = Arc._ourPercent;
                Intrinsics.checkNotNull(fArr2);
                return fArr2;
            }
        }
    }

    /* JADX INFO: compiled from: ArcSpline.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
