package androidx.compose.material3.internal;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutIdParentData;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: TextFieldImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextFieldImplKt {
    private static final Modifier IconDefaultSizeModifier;
    private static final float MinFocusedLabelLineHeight;
    private static final float MinSupportingTextLineHeight;
    private static final float TextFieldPadding;
    private static final long ZeroConstraints = ConstraintsKt.Constraints(0, 0, 0, 0);
    private static final float HorizontalIconPadding = Dp.m2416constructorimpl(12);
    private static final float SupportingTopPadding = Dp.m2416constructorimpl(4);
    private static final float PrefixSuffixTextPadding = Dp.m2416constructorimpl(2);
    private static final float MinTextLineHeight = Dp.m2416constructorimpl(24);

    /* JADX INFO: compiled from: TextFieldImpl.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TextFieldType.values().length];
            try {
                iArr[TextFieldType.Filled.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextFieldType.Outlined.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[InputPhase.values().length];
            try {
                iArr2[InputPhase.Focused.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[InputPhase.UnfocusedEmpty.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[InputPhase.UnfocusedNotEmpty.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private static final boolean CommonDecorationBox$lambda$15$lambda$7(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean CommonDecorationBox$lambda$15$lambda$9(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x03fb  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x049f  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x04b0  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x04d4  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x04de  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x04e9  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x0502  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0561  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0573  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x058f  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x059f  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x05a5  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x05b7  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x05fe  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x060c  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x060f  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0617  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x062b  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0633  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0653  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0661  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x0664  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x066c  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x0685  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x0693  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x069b  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x06e2  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x06ed  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x0709  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x0729  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x0734  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x074d  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0753  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x075a  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x079e  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x07a0  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x07d8  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x081a  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x082d  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0869  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x089a  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x08aa  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x08b2  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x08d4  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x08dc  */
    /* JADX WARN: Removed duplicated region for block: B:483:0x08fe  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x0903  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x091f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:499:0x09c8  */
    /* JADX WARN: Removed duplicated region for block: B:502:0x0a1b  */
    /* JADX WARN: Removed duplicated region for block: B:506:0x0a2f  */
    /* JADX WARN: Removed duplicated region for block: B:508:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0134 A[PHI: r38
  0x0134: PHI (r38v13 int) = (r38v4 int), (r38v7 int), (r38v8 int) binds: [B:98:0x0132, B:106:0x014a, B:105:0x0147] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void CommonDecorationBox(final androidx.compose.material3.internal.TextFieldType r48, final java.lang.String r49, final kotlin.jvm.functions.Function2 r50, final androidx.compose.ui.text.input.VisualTransformation r51, final kotlin.jvm.functions.Function2 r52, kotlin.jvm.functions.Function2 r53, kotlin.jvm.functions.Function2 r54, kotlin.jvm.functions.Function2 r55, kotlin.jvm.functions.Function2 r56, kotlin.jvm.functions.Function2 r57, kotlin.jvm.functions.Function2 r58, boolean r59, boolean r60, boolean r61, final androidx.compose.foundation.interaction.InteractionSource r62, final androidx.compose.foundation.layout.PaddingValues r63, final androidx.compose.material3.TextFieldColors r64, final kotlin.jvm.functions.Function2 r65, androidx.compose.runtime.Composer r66, final int r67, final int r68, final int r69) {
        /*
            Method dump skipped, instruction units count: 2653
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.TextFieldImplKt.CommonDecorationBox(androidx.compose.material3.internal.TextFieldType, java.lang.String, kotlin.jvm.functions.Function2, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.foundation.layout.PaddingValues, androidx.compose.material3.TextFieldColors, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Decoration-3J-VO9M, reason: not valid java name */
    public static final void m785Decoration3JVO9M(final long j, final TextStyle textStyle, final Function2 function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1208685580);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(textStyle) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1208685580, i2, -1, "androidx.compose.material3.internal.Decoration (TextFieldImpl.kt:298)");
            }
            ProvideContentColorTextStyleKt.m782ProvideContentColorTextStyle3JVO9M(j, textStyle, function2, composerStartRestartGroup, i2 & 1022);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.internal.TextFieldImplKt$Decoration$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    TextFieldImplKt.m785Decoration3JVO9M(j, textStyle, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Decoration-Iv8Zu3U, reason: not valid java name */
    public static final void m786DecorationIv8Zu3U(final long j, final Function2 function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(660142980);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(660142980, i2, -1, "androidx.compose.material3.internal.Decoration (TextFieldImpl.kt:303)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m1290boximpl(j)), function2, composerStartRestartGroup, (i2 & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.internal.TextFieldImplKt$Decoration$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    TextFieldImplKt.m786DecorationIv8Zu3U(j, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static final Modifier defaultErrorSemantics(Modifier modifier, boolean z, final String str) {
        return z ? SemanticsModifierKt.semantics$default(modifier, false, new Function1() { // from class: androidx.compose.material3.internal.TextFieldImplKt.defaultErrorSemantics.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SemanticsPropertyReceiver) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                SemanticsPropertiesKt.error(semanticsPropertyReceiver, str);
            }
        }, 1, null) : modifier;
    }

    public static final Modifier textFieldBackground(Modifier modifier, final ColorProducer colorProducer, final Shape shape) {
        return DrawModifierKt.drawWithCache(modifier, new Function1() { // from class: androidx.compose.material3.internal.TextFieldImplKt.textFieldBackground.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DrawResult invoke(CacheDrawScope cacheDrawScope) {
                final Outline outlineMo125createOutlinePq9zytI = shape.mo125createOutlinePq9zytI(cacheDrawScope.m1077getSizeNHjbRc(), cacheDrawScope.getLayoutDirection(), cacheDrawScope);
                final ColorProducer colorProducer2 = colorProducer;
                return cacheDrawScope.onDrawBehind(new Function1() { // from class: androidx.compose.material3.internal.TextFieldImplKt.textFieldBackground.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((DrawScope) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(DrawScope drawScope) {
                        OutlineKt.m1377drawOutlinewDX37Ww(drawScope, outlineMo125createOutlinePq9zytI, colorProducer2.mo670invoke0d7_KjU(), (60 & 4) != 0 ? 1.0f : 0.0f, (60 & 8) != 0 ? Fill.INSTANCE : null, (60 & 16) != 0 ? null : null, (60 & 32) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : 0);
                    }
                });
            }
        });
    }

    public static final int widthOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getWidth();
        }
        return 0;
    }

    public static final int heightOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getHeight();
        }
        return 0;
    }

    /* JADX INFO: renamed from: animateBorderStrokeAsState-NuRrP5Q, reason: not valid java name */
    public static final State m789animateBorderStrokeAsStateNuRrP5Q(boolean z, boolean z2, boolean z3, TextFieldColors textFieldColors, float f, float f2, Composer composer, int i) {
        State stateRememberUpdatedState;
        State stateRememberUpdatedState2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2047013045, i, -1, "androidx.compose.material3.internal.animateBorderStrokeAsState (TextFieldImpl.kt:441)");
        }
        long jM747indicatorColorXeAY9LY$material3_release = textFieldColors.m747indicatorColorXeAY9LY$material3_release(z, z2, z3);
        if (z) {
            composer.startReplaceGroup(1023053998);
            stateRememberUpdatedState = SingleValueAnimationKt.m43animateColorAsStateeuL9pac(jM747indicatorColorXeAY9LY$material3_release, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer, 48, 12);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(1023165505);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m1290boximpl(jM747indicatorColorXeAY9LY$material3_release), composer, 0);
            composer.endReplaceGroup();
        }
        State state = stateRememberUpdatedState;
        if (z) {
            composer.startReplaceGroup(1023269417);
            stateRememberUpdatedState2 = AnimateAsStateKt.m44animateDpAsStateAjpBEmI(z3 ? f : f2, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer, 48, 12);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(1023478388);
            stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(Dp.m2414boximpl(f2), composer, (i >> 15) & 14);
            composer.endReplaceGroup();
        }
        State stateRememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(BorderStrokeKt.m111BorderStrokecXLIe8U(((Dp) stateRememberUpdatedState2.getValue()).m2422unboximpl(), ((Color) state.getValue()).m1304unboximpl()), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateRememberUpdatedState3;
    }

    public static final Object getLayoutId(IntrinsicMeasurable intrinsicMeasurable) {
        Object parentData = intrinsicMeasurable.getParentData();
        LayoutIdParentData layoutIdParentData = parentData instanceof LayoutIdParentData ? (LayoutIdParentData) parentData : null;
        if (layoutIdParentData != null) {
            return layoutIdParentData.getLayoutId();
        }
        return null;
    }

    static {
        float f = 16;
        TextFieldPadding = Dp.m2416constructorimpl(f);
        MinFocusedLabelLineHeight = Dp.m2416constructorimpl(f);
        MinSupportingTextLineHeight = Dp.m2416constructorimpl(f);
        float f2 = 48;
        IconDefaultSizeModifier = SizeKt.m273defaultMinSizeVpY3zN4(Modifier.Companion, Dp.m2416constructorimpl(f2), Dp.m2416constructorimpl(f2));
    }

    public static final long getZeroConstraints() {
        return ZeroConstraints;
    }

    public static final float getTextFieldPadding() {
        return TextFieldPadding;
    }

    public static final float getHorizontalIconPadding() {
        return HorizontalIconPadding;
    }

    public static final float getSupportingTopPadding() {
        return SupportingTopPadding;
    }

    public static final float getPrefixSuffixTextPadding() {
        return PrefixSuffixTextPadding;
    }

    public static final float getMinTextLineHeight() {
        return MinTextLineHeight;
    }

    public static final float getMinFocusedLabelLineHeight() {
        return MinFocusedLabelLineHeight;
    }

    public static final float getMinSupportingTextLineHeight() {
        return MinSupportingTextLineHeight;
    }

    public static final Modifier getIconDefaultSizeModifier() {
        return IconDefaultSizeModifier;
    }
}
