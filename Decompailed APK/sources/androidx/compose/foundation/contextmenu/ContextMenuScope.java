package androidx.compose.foundation.contextmenu;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ContextMenuUi.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ContextMenuScope {
    private final SnapshotStateList composables = SnapshotStateKt.mutableStateListOf();

    public final void Content$foundation_release(final ContextMenuColors contextMenuColors, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1320309496);
        int i2 = (i & 6) == 0 ? (composerStartRestartGroup.changed(contextMenuColors) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(this) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1320309496, i2, -1, "androidx.compose.foundation.contextmenu.ContextMenuScope.Content (ContextMenuUi.android.kt:233)");
            }
            SnapshotStateList snapshotStateList = this.composables;
            int size = snapshotStateList.size();
            for (int i3 = 0; i3 < size; i3++) {
                ((Function3) snapshotStateList.get(i3)).invoke(contextMenuColors, composerStartRestartGroup, Integer.valueOf(i2 & 14));
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.contextmenu.ContextMenuScope$Content$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i4) {
                    this.$tmp0_rcvr.Content$foundation_release(contextMenuColors, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public final void clear$foundation_release() {
        this.composables.clear();
    }

    public static /* synthetic */ void item$default(ContextMenuScope contextMenuScope, Function2 function2, Modifier modifier, boolean z, Function3 function3, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            modifier = Modifier.Companion;
        }
        Modifier modifier2 = modifier;
        if ((i & 4) != 0) {
            z = true;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            function3 = null;
        }
        contextMenuScope.item(function2, modifier2, z2, function3, function0);
    }

    public final void item(final Function2 function2, final Modifier modifier, final boolean z, final Function3 function3, final Function0 function0) {
        this.composables.add(ComposableLambdaKt.composableLambdaInstance(262103052, true, new Function3() { // from class: androidx.compose.foundation.contextmenu.ContextMenuScope.item.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                invoke((ContextMenuColors) obj, (Composer) obj2, ((Number) obj3).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ContextMenuColors contextMenuColors, Composer composer, int i) {
                if ((i & 6) == 0) {
                    i |= composer.changed(contextMenuColors) ? 4 : 2;
                }
                if ((i & 19) != 18 || !composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(262103052, i, -1, "androidx.compose.foundation.contextmenu.ContextMenuScope.item.<anonymous> (ContextMenuUi.android.kt:275)");
                    }
                    String str = (String) function2.invoke(composer, 0);
                    if (StringsKt.isBlank(str)) {
                        throw new IllegalStateException("Label must not be blank");
                    }
                    ContextMenuUi_androidKt.ContextMenuItem(str, z, contextMenuColors, modifier, function3, function0, composer, (i << 6) & 896, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer.skipToGroupEnd();
            }
        }));
    }
}
