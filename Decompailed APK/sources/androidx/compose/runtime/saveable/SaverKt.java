package androidx.compose.runtime.saveable;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Saver.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SaverKt {
    private static final Saver AutoSaver = Saver(new Function2() { // from class: androidx.compose.runtime.saveable.SaverKt$AutoSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, Object obj) {
            return obj;
        }
    }, new Function1() { // from class: androidx.compose.runtime.saveable.SaverKt$AutoSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return obj;
        }
    });

    public static final Saver Saver(final Function2 function2, final Function1 function1) {
        return new Saver() { // from class: androidx.compose.runtime.saveable.SaverKt.Saver.1
            @Override // androidx.compose.runtime.saveable.Saver
            public Object save(SaverScope saverScope, Object obj) {
                return function2.invoke(saverScope, obj);
            }

            @Override // androidx.compose.runtime.saveable.Saver
            public Object restore(Object obj) {
                return function1.invoke(obj);
            }
        };
    }

    public static final Saver autoSaver() {
        Saver saver = AutoSaver;
        Intrinsics.checkNotNull(saver, "null cannot be cast to non-null type androidx.compose.runtime.saveable.Saver<T of androidx.compose.runtime.saveable.SaverKt.autoSaver, kotlin.Any>");
        return saver;
    }
}
