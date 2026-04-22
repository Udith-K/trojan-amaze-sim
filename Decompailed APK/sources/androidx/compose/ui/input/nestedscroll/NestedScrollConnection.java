package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.unit.Velocity;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: NestedScrollModifier.kt */
/* JADX INFO: loaded from: classes.dex */
public interface NestedScrollConnection {
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    Object mo216onPostFlingRZ2iAVY(long j, long j2, Continuation continuation);

    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    long mo217onPostScrollDzOQY0M(long j, long j2, int i);

    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    Object mo218onPreFlingQWom1Mo(long j, Continuation continuation);

    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    long mo219onPreScrollOzD1aCk(long j, int i);

    /* JADX INFO: renamed from: androidx.compose.ui.input.nestedscroll.NestedScrollConnection$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: NestedScrollModifier.kt */
    public abstract /* synthetic */ class CC {
        /* JADX INFO: renamed from: onPreFling-QWom1Mo$suspendImpl, reason: not valid java name */
        public static /* synthetic */ Object m1635onPreFlingQWom1Mo$suspendImpl(NestedScrollConnection nestedScrollConnection, long j, Continuation continuation) {
            return Velocity.m2510boximpl(Velocity.Companion.m2524getZero9UxMQ8M());
        }
    }
}
