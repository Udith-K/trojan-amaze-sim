package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionData;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Composer.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Composer {
    public static final Companion Companion = Companion.$$INSTANCE;

    void apply(Object obj, Function2 function2);

    CompositionContext buildContext();

    boolean changed(float f);

    boolean changed(int i);

    boolean changed(long j);

    boolean changed(Object obj);

    boolean changed(boolean z);

    boolean changedInstance(Object obj);

    void collectParameterInformation();

    Object consume(CompositionLocal compositionLocal);

    void createNode(Function0 function0);

    void deactivateToEndGroup(boolean z);

    void endDefaults();

    void endMovableGroup();

    void endNode();

    void endProvider();

    void endProviders();

    void endReplaceGroup();

    void endReplaceableGroup();

    ScopeUpdateScope endRestartGroup();

    void endReusableGroup();

    Applier getApplier();

    CoroutineContext getApplyCoroutineContext();

    CompositionData getCompositionData();

    int getCompoundKeyHash();

    CompositionLocalMap getCurrentCompositionLocalMap();

    boolean getDefaultsInvalid();

    boolean getInserting();

    RecomposeScope getRecomposeScope();

    boolean getSkipping();

    void recordSideEffect(Function0 function0);

    void recordUsed(RecomposeScope recomposeScope);

    Object rememberedValue();

    void skipToGroupEnd();

    void startDefaults();

    void startMovableGroup(int i, Object obj);

    void startProvider(ProvidedValue providedValue);

    void startProviders(ProvidedValue[] providedValueArr);

    void startReplaceGroup(int i);

    void startReplaceableGroup(int i);

    Composer startRestartGroup(int i);

    void startReusableGroup(int i, Object obj);

    void startReusableNode();

    void updateRememberedValue(Object obj);

    void useNode();

    /* JADX INFO: compiled from: Composer.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Object Empty = new Object() { // from class: androidx.compose.runtime.Composer$Companion$Empty$1
            public String toString() {
                return "Empty";
            }
        };

        private Companion() {
        }

        public final Object getEmpty() {
            return Empty;
        }
    }
}
