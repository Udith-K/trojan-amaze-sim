package androidx.compose.foundation;

import android.view.View;
import android.widget.Magnifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;

/* JADX INFO: compiled from: PlatformMagnifier.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PlatformMagnifierFactoryApi28Impl implements PlatformMagnifierFactory {
    public static final PlatformMagnifierFactoryApi28Impl INSTANCE = new PlatformMagnifierFactoryApi28Impl();
    private static final boolean canUpdateZoom = false;

    private PlatformMagnifierFactoryApi28Impl() {
    }

    @Override // androidx.compose.foundation.PlatformMagnifierFactory
    public boolean getCanUpdateZoom() {
        return canUpdateZoom;
    }

    @Override // androidx.compose.foundation.PlatformMagnifierFactory
    /* JADX INFO: renamed from: create-nHHXs2Y */
    public PlatformMagnifierImpl mo144createnHHXs2Y(View view, boolean z, long j, float f, float f2, boolean z2, Density density, float f3) {
        return new PlatformMagnifierImpl(new Magnifier(view));
    }

    /* JADX INFO: compiled from: PlatformMagnifier.android.kt */
    public static class PlatformMagnifierImpl implements PlatformMagnifier {
        private final Magnifier magnifier;

        public PlatformMagnifierImpl(Magnifier magnifier) {
            this.magnifier = magnifier;
        }

        public final Magnifier getMagnifier() {
            return this.magnifier;
        }

        @Override // androidx.compose.foundation.PlatformMagnifier
        /* JADX INFO: renamed from: getSize-YbymL2g */
        public long mo142getSizeYbymL2g() {
            return IntSizeKt.IntSize(this.magnifier.getWidth(), this.magnifier.getHeight());
        }

        @Override // androidx.compose.foundation.PlatformMagnifier
        public void updateContent() {
            this.magnifier.update();
        }

        @Override // androidx.compose.foundation.PlatformMagnifier
        /* JADX INFO: renamed from: update-Wko1d7g */
        public void mo143updateWko1d7g(long j, long j2, float f) {
            this.magnifier.show(Offset.m1159getXimpl(j), Offset.m1160getYimpl(j));
        }

        @Override // androidx.compose.foundation.PlatformMagnifier
        public void dismiss() {
            this.magnifier.dismiss();
        }
    }
}
