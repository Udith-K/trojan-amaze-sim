package androidx.compose.runtime.saveable;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: ListSaver.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ListSaverKt {
    public static final Saver listSaver(final Function2 function2, Function1 function1) {
        Function2 function22 = new Function2() { // from class: androidx.compose.runtime.saveable.ListSaverKt.listSaver.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(SaverScope saverScope, Object obj) {
                List list = (List) function2.invoke(saverScope, obj);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    Object obj2 = list.get(i);
                    if (obj2 != null && !saverScope.canBeSaved(obj2)) {
                        throw new IllegalArgumentException("item can't be saved");
                    }
                }
                if (list.isEmpty()) {
                    return null;
                }
                return new ArrayList(list);
            }
        };
        Intrinsics.checkNotNull(function1, "null cannot be cast to non-null type kotlin.Function1<kotlin.Any, Original of androidx.compose.runtime.saveable.ListSaverKt.listSaver?>");
        return SaverKt.Saver(function22, (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1));
    }
}
