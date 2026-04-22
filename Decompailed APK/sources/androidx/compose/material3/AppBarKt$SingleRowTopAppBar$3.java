package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
final class AppBarKt$SingleRowTopAppBar$3 extends Lambda implements Function2 {
    final /* synthetic */ Function2 $actionsRow;
    final /* synthetic */ boolean $centeredTitle;
    final /* synthetic */ TopAppBarColors $colors;
    final /* synthetic */ float $expandedHeight;
    final /* synthetic */ Function2 $navigationIcon;
    final /* synthetic */ Function2 $title;
    final /* synthetic */ TextStyle $titleTextStyle;
    final /* synthetic */ WindowInsets $windowInsets;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AppBarKt$SingleRowTopAppBar$3(WindowInsets windowInsets, float f, TopAppBarScrollBehavior topAppBarScrollBehavior, TopAppBarColors topAppBarColors, Function2 function2, TextStyle textStyle, boolean z, Function2 function22, Function2 function23) {
        super(2);
        this.$windowInsets = windowInsets;
        this.$expandedHeight = f;
        this.$colors = topAppBarColors;
        this.$title = function2;
        this.$titleTextStyle = textStyle;
        this.$centeredTitle = z;
        this.$navigationIcon = function22;
        this.$actionsRow = function23;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        if ((i & 3) != 2 || !composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1943739546, i, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1927)");
            }
            final TopAppBarScrollBehavior topAppBarScrollBehavior = null;
            Modifier modifierM277heightInVpY3zN4$default = SizeKt.m277heightInVpY3zN4$default(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.Companion, this.$windowInsets)), 0.0f, this.$expandedHeight, 1, null);
            boolean zChanged = composer.changed((Object) null);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new ScrolledOffset(topAppBarScrollBehavior) { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$3$$ExternalSyntheticLambda0
                    @Override // androidx.compose.material3.ScrolledOffset
                    public final float offset() {
                        return AppBarKt$SingleRowTopAppBar$3.invoke$lambda$1$lambda$0(null);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ScrolledOffset scrolledOffset = (ScrolledOffset) objRememberedValue;
            long jM776getNavigationIconContentColor0d7_KjU = this.$colors.m776getNavigationIconContentColor0d7_KjU();
            long jM777getTitleContentColor0d7_KjU = this.$colors.m777getTitleContentColor0d7_KjU();
            long jM775getActionIconContentColor0d7_KjU = this.$colors.m775getActionIconContentColor0d7_KjU();
            Function2 function2 = this.$title;
            TextStyle textStyle = this.$titleTextStyle;
            Arrangement arrangement = Arrangement.INSTANCE;
            AppBarKt.m600TopAppBarLayoutkXwM9vE(modifierM277heightInVpY3zN4$default, scrolledOffset, jM776getNavigationIconContentColor0d7_KjU, jM777getTitleContentColor0d7_KjU, jM775getActionIconContentColor0d7_KjU, function2, textStyle, 1.0f, arrangement.getCenter(), this.$centeredTitle ? arrangement.getCenter() : arrangement.getStart(), 0, false, this.$navigationIcon, this.$actionsRow, composer, 113246208, 3126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float invoke$lambda$1$lambda$0(TopAppBarScrollBehavior topAppBarScrollBehavior) {
        if (topAppBarScrollBehavior == null) {
            return 0.0f;
        }
        topAppBarScrollBehavior.getState();
        return 0.0f;
    }
}
