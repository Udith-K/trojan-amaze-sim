package com.bumptech.glide.integration.compose;

import android.os.Handler;
import android.os.Looper;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.integration.compose.Transition;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: GlideModifier.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class GlideModifierKt {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(GlideModifierKt.class, "displayedDrawable", "getDisplayedDrawable(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Lkotlin/jvm/functions/Function0;", 1)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(GlideModifierKt.class, "displayedPainter", "getDisplayedPainter(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Lkotlin/jvm/functions/Function0;", 1))};
    private static final Lazy MAIN_HANDLER$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: com.bumptech.glide.integration.compose.GlideModifierKt$MAIN_HANDLER$2
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            return new Handler(Looper.getMainLooper());
        }
    });
    private static final SemanticsPropertyKey DisplayedDrawableKey = new SemanticsPropertyKey("DisplayedDrawable", null, 2, null);
    private static final SemanticsPropertyKey DisplayedPainterKey = new SemanticsPropertyKey("DisplayedPainter", null, 2, null);

    public static final Modifier glideNode(Modifier modifier, RequestBuilder requestBuilder, final String str, Alignment alignment, ContentScale contentScale, Float f, ColorFilter colorFilter, Transition.Factory factory, RequestListener requestListener, Boolean bool, Painter painter, Painter painter2) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(requestBuilder, "requestBuilder");
        return modifier.then(SemanticsModifierKt.semantics$default(ClipKt.clipToBounds(new GlideNodeElement(requestBuilder, contentScale == null ? ContentScale.Companion.getNone() : contentScale, alignment == null ? Alignment.Companion.getCenter() : alignment, f, colorFilter, requestListener, bool, factory, painter, painter2)), false, new Function1() { // from class: com.bumptech.glide.integration.compose.GlideModifierKt.glideNode.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SemanticsPropertyReceiver) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(SemanticsPropertyReceiver semantics) {
                Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                String str2 = str;
                if (str2 != null) {
                    SemanticsPropertiesKt.setContentDescription(semantics, str2);
                }
                SemanticsPropertiesKt.m2016setRolekuIjeqM(semantics, Role.Companion.m2006getImageo7Vup1c());
            }
        }, 1, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Handler getMAIN_HANDLER() {
        return (Handler) MAIN_HANDLER$delegate.getValue();
    }

    public static final void setDisplayedDrawable(SemanticsPropertyReceiver semanticsPropertyReceiver, Function0 function0) {
        Intrinsics.checkNotNullParameter(semanticsPropertyReceiver, "<this>");
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        DisplayedDrawableKey.setValue(semanticsPropertyReceiver, $$delegatedProperties[0], function0);
    }

    public static final void setDisplayedPainter(SemanticsPropertyReceiver semanticsPropertyReceiver, Function0 function0) {
        Intrinsics.checkNotNullParameter(semanticsPropertyReceiver, "<this>");
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        DisplayedPainterKey.setValue(semanticsPropertyReceiver, $$delegatedProperties[1], function0);
    }
}
