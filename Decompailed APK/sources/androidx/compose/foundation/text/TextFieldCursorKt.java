package androidx.compose.foundation.text;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Dp;
import androidx.room.RoomDatabase;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: TextFieldCursor.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextFieldCursorKt {
    private static final AnimationSpec cursorAnimationSpec = AnimationSpecKt.m47infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.foundation.text.TextFieldCursorKt$cursorAnimationSpec$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((KeyframesSpec.KeyframesSpecConfig) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig) {
            keyframesSpecConfig.setDurationMillis(1000);
            Float fValueOf = Float.valueOf(1.0f);
            keyframesSpecConfig.at(fValueOf, 0);
            keyframesSpecConfig.at(fValueOf, 499);
            Float fValueOf2 = Float.valueOf(0.0f);
            keyframesSpecConfig.at(fValueOf2, 500);
            keyframesSpecConfig.at(fValueOf2, RoomDatabase.MAX_BIND_PARAMETER_CNT);
        }
    }), null, 0, 6, null);
    private static final float DefaultCursorThickness = Dp.m2416constructorimpl(2);

    public static final Modifier cursor(Modifier modifier, final LegacyTextFieldState legacyTextFieldState, final TextFieldValue textFieldValue, final OffsetMapping offsetMapping, final Brush brush, boolean z) {
        return z ? ComposedModifierKt.composed$default(modifier, null, new Function3() { // from class: androidx.compose.foundation.text.TextFieldCursorKt.cursor.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
            }

            /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
                jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r11v15 ??, still in use, count: 1, list:
                  (r11v15 ?? I:java.lang.Object) from 0x00d3: INVOKE (r10v0 ?? I:androidx.compose.runtime.Composer), (r11v15 ?? I:java.lang.Object) INTERFACE call: androidx.compose.runtime.Composer.updateRememberedValue(java.lang.Object):void A[MD:(java.lang.Object):void (m)] (LINE:1228)
                	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
                	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
                	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
                	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
                	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
                	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
                */
            public final androidx.compose.ui.Modifier invoke(
            /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
                jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r11v15 ??, still in use, count: 1, list:
                  (r11v15 ?? I:java.lang.Object) from 0x00d3: INVOKE (r10v0 ?? I:androidx.compose.runtime.Composer), (r11v15 ?? I:java.lang.Object) INTERFACE call: androidx.compose.runtime.Composer.updateRememberedValue(java.lang.Object):void A[MD:(java.lang.Object):void (m)] (LINE:1228)
                	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
                	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
                	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
                	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
                	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
                */
            /*  JADX ERROR: Method generation error
                jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r9v0 ??
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
                	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:303)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(Unknown Source)
                	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:845)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                	at jadx.core.codegen.InsnGen.makeTernary(InsnGen.java:1187)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:536)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:303)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(Unknown Source)
                	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:272)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:159)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
                	at jadx.core.ProcessClass.process(ProcessClass.java:88)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:126)
                	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
                	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
                */
        }, 1, null) : modifier;
    }

    public static final float getDefaultCursorThickness() {
        return DefaultCursorThickness;
    }
}
