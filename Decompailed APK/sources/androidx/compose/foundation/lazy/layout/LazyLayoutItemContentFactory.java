package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyLayoutItemContentFactory.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyLayoutItemContentFactory {
    private final Function0 itemProvider;
    private final Map lambdasCache = new LinkedHashMap();
    private final SaveableStateHolder saveableStateHolder;

    public LazyLayoutItemContentFactory(SaveableStateHolder saveableStateHolder, Function0 function0) {
        this.saveableStateHolder = saveableStateHolder;
        this.itemProvider = function0;
    }

    public final Function0 getItemProvider() {
        return this.itemProvider;
    }

    public final Object getContentType(Object obj) {
        if (obj == null) {
            return null;
        }
        CachedItemContent cachedItemContent = (CachedItemContent) this.lambdasCache.get(obj);
        if (cachedItemContent != null) {
            return cachedItemContent.getContentType();
        }
        LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) this.itemProvider.invoke();
        int index = lazyLayoutItemProvider.getIndex(obj);
        if (index != -1) {
            return lazyLayoutItemProvider.getContentType(index);
        }
        return null;
    }

    public final Function2 getContent(int i, Object obj, Object obj2) {
        CachedItemContent cachedItemContent = (CachedItemContent) this.lambdasCache.get(obj);
        if (cachedItemContent != null && cachedItemContent.getIndex() == i && Intrinsics.areEqual(cachedItemContent.getContentType(), obj2)) {
            return cachedItemContent.getContent();
        }
        CachedItemContent cachedItemContent2 = new CachedItemContent(i, obj, obj2);
        this.lambdasCache.put(obj, cachedItemContent2);
        return cachedItemContent2.getContent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: LazyLayoutItemContentFactory.kt */
    final class CachedItemContent {
        private Function2 _content;
        private final Object contentType;
        private int index;
        private final Object key;

        public CachedItemContent(int i, Object obj, Object obj2) {
            this.key = obj;
            this.contentType = obj2;
            this.index = i;
        }

        public final Object getKey() {
            return this.key;
        }

        public final Object getContentType() {
            return this.contentType;
        }

        public final int getIndex() {
            return this.index;
        }

        public final Function2 getContent() {
            Function2 function2 = this._content;
            if (function2 != null) {
                return function2;
            }
            Function2 function2CreateContentLambda = createContentLambda();
            this._content = function2CreateContentLambda;
            return function2CreateContentLambda;
        }

        private final Function2 createContentLambda() {
            final LazyLayoutItemContentFactory lazyLayoutItemContentFactory = LazyLayoutItemContentFactory.this;
            return ComposableLambdaKt.composableLambdaInstance(1403994769, true, new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    if ((i & 3) != 2 || !composer.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1403994769, i, -1, "androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory.CachedItemContent.createContentLambda.<anonymous> (LazyLayoutItemContentFactory.kt:91)");
                        }
                        LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) lazyLayoutItemContentFactory.getItemProvider().invoke();
                        int index = this.getIndex();
                        if ((index >= lazyLayoutItemProvider.getItemCount() || !Intrinsics.areEqual(lazyLayoutItemProvider.getKey(index), this.getKey())) && (index = lazyLayoutItemProvider.getIndex(this.getKey())) != -1) {
                            this.index = index;
                        }
                        int i2 = index;
                        if (i2 != -1) {
                            composer.startReplaceGroup(-660479623);
                            LazyLayoutItemContentFactoryKt.m345SkippableItemJVlU9Rs(lazyLayoutItemProvider, StableValue.m358constructorimpl(lazyLayoutItemContentFactory.saveableStateHolder), i2, StableValue.m358constructorimpl(this.getKey()), composer, 0);
                            composer.endReplaceGroup();
                        } else {
                            composer.startReplaceGroup(-660272047);
                            composer.endReplaceGroup();
                        }
                        Object key = this.getKey();
                        boolean zChangedInstance = composer.changedInstance(this);
                        final LazyLayoutItemContentFactory.CachedItemContent cachedItemContent = this;
                        Object objRememberedValue = composer.rememberedValue();
                        if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1$1$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                                    final LazyLayoutItemContentFactory.CachedItemContent cachedItemContent2 = cachedItemContent;
                                    return new DisposableEffectResult() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1$1$1$invoke$$inlined$onDispose$1
                                        @Override // androidx.compose.runtime.DisposableEffectResult
                                        public void dispose() {
                                            cachedItemContent2._content = null;
                                        }
                                    };
                                }
                            };
                            composer.updateRememberedValue(objRememberedValue);
                        }
                        EffectsKt.DisposableEffect(key, (Function1) objRememberedValue, composer, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer.skipToGroupEnd();
                }
            });
        }
    }
}
