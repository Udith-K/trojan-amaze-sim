package kotlin.jvm.internal;

import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVariance;

/* JADX INFO: compiled from: TypeParameterReference.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class TypeParameterReference implements KTypeParameter {
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: compiled from: TypeParameterReference.kt */
    public static final class Companion {

        /* JADX INFO: compiled from: TypeParameterReference.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[KVariance.values().length];
                try {
                    iArr[KVariance.INVARIANT.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[KVariance.IN.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[KVariance.OUT.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String toString(KTypeParameter typeParameter) {
            Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
            StringBuilder sb = new StringBuilder();
            int i = WhenMappings.$EnumSwitchMapping$0[typeParameter.getVariance().ordinal()];
            if (i == 2) {
                sb.append("in ");
            } else if (i == 3) {
                sb.append("out ");
            }
            sb.append(typeParameter.getName());
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
    }
}
