package org.fdroid.fdroid.views.repos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.activity.compose.BackHandlerKt;
import androidx.activity.compose.ComponentActivityKt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import info.guardianproject.netcipher.NetCipher;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KFunction;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import org.bouncycastle.asn1.eac.EACTags;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.nearby.SwapService;
import org.fdroid.fdroid.ui.theme.ThemeKt;
import org.fdroid.fdroid.views.apps.AppListActivity;
import org.fdroid.fdroid.views.repos.AddRepoActivity;
import org.fdroid.fdroid.work.RepoUpdateWorker;
import org.fdroid.index.RepoManager;
import org.fdroid.repo.AddRepoError;
import org.fdroid.repo.AddRepoState;
import org.fdroid.repo.Added;

/* JADX INFO: compiled from: AddRepoActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u0010\f\u001a\u00020\tH\u0014J\b\u0010\r\u001a\u00020\tH\u0014J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0010H\u0002R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lorg/fdroid/fdroid/views/repos/AddRepoActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "repoManager", "Lorg/fdroid/index/RepoManager;", "getRepoManager", "()Lorg/fdroid/index/RepoManager;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onDestroy", "onFetchRepo", "uriStr", "", "fetchIfRepoUri", "str", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AddRepoActivity extends AppCompatActivity {
    public static final int $stable = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public final RepoManager getRepoManager() {
        RepoManager repoManager = FDroidApp.getRepoManager(this);
        Intrinsics.checkNotNullExpressionValue(repoManager, "getRepoManager(...)");
        return repoManager;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable$default(this, null, null, 3, null);
        super.onCreate(savedInstanceState);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(null), 3, null);
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(-90938685, true, new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoActivity.onCreate.2

            /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.AddRepoActivity$onCreate$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: AddRepoActivity.kt */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            static final class AnonymousClass1 implements Function2 {
                final /* synthetic */ AddRepoActivity this$0;

                AnonymousClass1(AddRepoActivity addRepoActivity) {
                    this.this$0 = addRepoActivity;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    if ((i & 3) == 2 && composer.getSkipping()) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1220021235, i, -1, "org.fdroid.fdroid.views.repos.AddRepoActivity.onCreate.<anonymous>.<anonymous> (AddRepoActivity.kt:59)");
                    }
                    AddRepoState addRepoState = (AddRepoState) SnapshotStateKt.collectAsState(this.this$0.getRepoManager().getAddRepoState(), null, composer, 0, 1).getValue();
                    boolean z = addRepoState instanceof AddRepoError;
                    composer.startReplaceGroup(1883035530);
                    boolean zChanged = composer.changed(this.this$0);
                    final AddRepoActivity addRepoActivity = this.this$0;
                    Object objRememberedValue = composer.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.repos.AddRepoActivity$onCreate$2$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AddRepoActivity.AnonymousClass2.AnonymousClass1.invoke$lambda$1$lambda$0(addRepoActivity);
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    composer.endReplaceGroup();
                    BackHandlerKt.BackHandler(z, (Function0) objRememberedValue, composer, 0, 0);
                    AddRepoActivity addRepoActivity2 = this.this$0;
                    composer.startReplaceGroup(1883043341);
                    boolean zChanged2 = composer.changed(addRepoActivity2);
                    Object objRememberedValue2 = composer.rememberedValue();
                    if (zChanged2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                        objRememberedValue2 = new AddRepoActivity$onCreate$2$1$2$1(addRepoActivity2);
                        composer.updateRememberedValue(objRememberedValue2);
                    }
                    composer.endReplaceGroup();
                    Function1 function1 = (Function1) ((KFunction) objRememberedValue2);
                    composer.startReplaceGroup(1883044994);
                    boolean zChanged3 = composer.changed(this.this$0);
                    final AddRepoActivity addRepoActivity3 = this.this$0;
                    Object objRememberedValue3 = composer.rememberedValue();
                    if (zChanged3 || objRememberedValue3 == Composer.Companion.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: org.fdroid.fdroid.views.repos.AddRepoActivity$onCreate$2$1$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AddRepoActivity.AnonymousClass2.AnonymousClass1.invoke$lambda$4$lambda$3(addRepoActivity3);
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue3);
                    }
                    Function0 function0 = (Function0) objRememberedValue3;
                    composer.endReplaceGroup();
                    composer.startReplaceGroup(1883047431);
                    boolean zChanged4 = composer.changed(this.this$0);
                    final AddRepoActivity addRepoActivity4 = this.this$0;
                    Object objRememberedValue4 = composer.rememberedValue();
                    if (zChanged4 || objRememberedValue4 == Composer.Companion.getEmpty()) {
                        objRememberedValue4 = new Function0() { // from class: org.fdroid.fdroid.views.repos.AddRepoActivity$onCreate$2$1$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AddRepoActivity.AnonymousClass2.AnonymousClass1.invoke$lambda$6$lambda$5(addRepoActivity4);
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue4);
                    }
                    composer.endReplaceGroup();
                    AddRepoIntroScreenKt.AddRepoIntroScreen(addRepoState, function1, function0, (Function0) objRememberedValue4, composer, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$1$lambda$0(AddRepoActivity addRepoActivity) {
                    addRepoActivity.getRepoManager().abortAddingRepository();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$4$lambda$3(AddRepoActivity addRepoActivity) {
                    addRepoActivity.getRepoManager().addFetchedRepository();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$6$lambda$5(AddRepoActivity addRepoActivity) {
                    addRepoActivity.getOnBackPressedDispatcher().onBackPressed();
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Composer) obj, ((Number) obj2).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i) {
                if ((i & 3) != 2 || !composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-90938685, i, -1, "org.fdroid.fdroid.views.repos.AddRepoActivity.onCreate.<anonymous> (AddRepoActivity.kt:58)");
                    }
                    ThemeKt.FDroidContent(false, false, ComposableLambdaKt.rememberComposableLambda(-1220021235, true, new AnonymousClass1(AddRepoActivity.this), composer, 54), composer, 384, 3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer.skipToGroupEnd();
            }
        }), 1, null);
        addOnNewIntentListener(new Consumer() { // from class: org.fdroid.fdroid.views.repos.AddRepoActivity$$ExternalSyntheticLambda0
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                AddRepoActivity.onCreate$lambda$2(this.f$0, (Intent) obj);
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            onNewIntent(intent);
            intent.setData(null);
            intent.replaceExtras(new Bundle());
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.AddRepoActivity$onCreate$1, reason: invalid class name */
    /* JADX INFO: compiled from: AddRepoActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.repos.AddRepoActivity$onCreate$1", f = "AddRepoActivity.kt", l = {EACTags.DATE_OF_BIRTH}, m = "invokeSuspend")
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return AddRepoActivity.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.AddRepoActivity$onCreate$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: AddRepoActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "org.fdroid.fdroid.views.repos.AddRepoActivity$onCreate$1$1", f = "AddRepoActivity.kt", l = {EACTags.CARDHOLDER_NATIONALITY}, m = "invokeSuspend")
        static final class C00681 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ AddRepoActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00681(AddRepoActivity addRepoActivity, Continuation continuation) {
                super(2, continuation);
                this.this$0 = addRepoActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00681(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((C00681) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow addRepoState = this.this$0.getRepoManager().getAddRepoState();
                    final AddRepoActivity addRepoActivity = this.this$0;
                    FlowCollector flowCollector = new FlowCollector() { // from class: org.fdroid.fdroid.views.repos.AddRepoActivity.onCreate.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(AddRepoState addRepoState2, Continuation continuation) {
                            if (addRepoState2 instanceof Added) {
                                RepoUpdateWorker.Companion companion = RepoUpdateWorker.INSTANCE;
                                Context applicationContext = addRepoActivity.getApplicationContext();
                                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                                Added added = (Added) addRepoState2;
                                companion.updateNow(applicationContext, added.getRepo().getRepoId());
                                Intent intent = new Intent(addRepoActivity, (Class<?>) AppListActivity.class);
                                intent.putExtra(AppListActivity.EXTRA_REPO_ID, added.getRepo().getRepoId());
                                addRepoActivity.startActivity(intent);
                                addRepoActivity.finish();
                            }
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (addRepoState.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AddRepoActivity addRepoActivity = AddRepoActivity.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                C00681 c00681 = new C00681(addRepoActivity, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(addRepoActivity, state, c00681, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(AddRepoActivity addRepoActivity, Intent intent) {
        String stringExtra;
        String dataString;
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (action != null) {
            int iHashCode = action.hashCode();
            if (iHashCode == -1173264947) {
                if (action.equals("android.intent.action.SEND") && (stringExtra = intent.getStringExtra("android.intent.extra.TEXT")) != null) {
                    addRepoActivity.fetchIfRepoUri(stringExtra);
                    return;
                }
                return;
            }
            if (iHashCode == -1173171990 && action.equals("android.intent.action.VIEW") && (dataString = intent.getDataString()) != null) {
                addRepoActivity.onFetchRepo(dataString);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        FDroidApp.checkStartTor(this, Preferences.get());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (isChangingConfigurations()) {
            return;
        }
        getRepoManager().abortAddingRepository();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFetchRepo(String uriStr) {
        Uri uri = Uri.parse(StringsKt.trim(uriStr).toString());
        if (getRepoManager().isSwapUri(uri)) {
            Intent intent = new Intent(this, (Class<?>) SwapService.class);
            intent.setData(uri);
            ContextCompat.startForegroundService(this, intent);
        } else {
            getRepoManager().abortAddingRepository();
            RepoManager repoManager = getRepoManager();
            String string = uri.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            repoManager.fetchRepositoryPreview(string, NetCipher.getProxy());
        }
    }

    private final void fetchIfRepoUri(String str) {
        MatchGroupCollection groups;
        MatchGroup matchGroup;
        MatchGroupCollection groups2;
        MatchGroup matchGroup2;
        RegexOption regexOption = RegexOption.IGNORE_CASE;
        RegexOption regexOption2 = RegexOption.MULTILINE;
        String value = null;
        MatchResult matchResultFind$default = Regex.find$default(new Regex("^.*((https|fdroidrepos)://.+/repo(\\?fingerprint=[A-F0-9]+)?) ?.*$", SetsKt.setOf((Object[]) new RegexOption[]{regexOption, regexOption2})), str, 0, 2, null);
        String value2 = (matchResultFind$default == null || (groups2 = matchResultFind$default.getGroups()) == null || (matchGroup2 = groups2.get(1)) == null) ? null : matchGroup2.getValue();
        if (value2 != null) {
            Log.d(Reflection.getOrCreateKotlinClass(AddRepoActivity.class).getSimpleName(), "Found match: " + value2);
            onFetchRepo(value2);
            return;
        }
        MatchResult matchResultFind$default2 = Regex.find$default(new Regex("^.*(https://fdroid.link/.+) ?.*$", SetsKt.setOf((Object[]) new RegexOption[]{regexOption, regexOption2})), str, 0, 2, null);
        if (matchResultFind$default2 != null && (groups = matchResultFind$default2.getGroups()) != null && (matchGroup = groups.get(1)) != null) {
            value = matchGroup.getValue();
        }
        if (value != null) {
            Log.d(Reflection.getOrCreateKotlinClass(AddRepoActivity.class).getSimpleName(), "Found match: " + value);
            onFetchRepo(value);
            return;
        }
        Toast.makeText(this, R.string.repo_share_not_found, 1).show();
        finishAfterTransition();
    }
}
