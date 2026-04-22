package org.fdroid.fdroid.views.repos;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.activity.compose.ComponentActivityKt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import ch.qos.logback.core.CoreConstants;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import fi.iki.elonen.NanoHTTPD;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.fdroid.database.Repository;
import org.fdroid.download.Mirror;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.ui.theme.ThemeKt;
import org.fdroid.fdroid.views.apps.AppListActivity;
import org.fdroid.fdroid.views.repos.RepoDetailsViewModel;

/* JADX INFO: compiled from: RepoDetailsActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\b\u0007\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0001\u001dB\t\b\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\bH\u0016J\b\u0010\u000f\u001a\u00020\bH\u0016J\b\u0010\u0010\u001a\u00020\bH\u0016J\b\u0010\u0011\u001a\u00020\bH\u0016J\b\u0010\u0012\u001a\u00020\bH\u0016J\b\u0010\u0013\u001a\u00020\bH\u0016J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\bH\u0016J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001e²\u0006\f\u0010\u001f\u001a\u0004\u0018\u00010\nX\u008a\u0084\u0002²\u0006\n\u0010 \u001a\u00020!X\u008a\u0084\u0002²\u0006\n\u0010\"\u001a\u00020#X\u008a\u0084\u0002"}, d2 = {"Lorg/fdroid/fdroid/views/repos/RepoDetailsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lorg/fdroid/fdroid/views/repos/RepoDetailsScreenCallbacks;", "<init>", "()V", "viewModel", "Lorg/fdroid/fdroid/views/repos/RepoDetailsViewModel;", "initViewModel", "", "repo", "Lorg/fdroid/database/Repository;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onBackClicked", "onShareClicked", "onShowQrCodeClicked", "onDeleteClicked", "onInfoClicked", "onShowAppsClicked", "onToggleArchiveClicked", "enabled", "", "onEditCredentialsClicked", "setMirrorEnabled", "mirror", "Lorg/fdroid/download/Mirror;", "onShareMirror", "onDeleteMirror", "Companion", "app_fullRelease", "repoState", "archiveState", "Lorg/fdroid/fdroid/views/repos/ArchiveState;", "numberOfApps", ""}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RepoDetailsActivity extends AppCompatActivity implements RepoDetailsScreenCallbacks {
    public static final String ARG_REPO_ID = "repo_id";
    private static final String TAG = "RepoDetailsActivity";
    private RepoDetailsViewModel viewModel;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: RepoDetailsActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/fdroid/fdroid/views/repos/RepoDetailsActivity$Companion;", "", "<init>", "()V", "TAG", "", "ARG_REPO_ID", "launch", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "repoId", "", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void launch(Context context, long repoId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) RepoDetailsActivity.class);
            intent.putExtra(RepoDetailsActivity.ARG_REPO_ID, repoId);
            context.startActivity(intent);
        }
    }

    private final void initViewModel(Repository repo) {
        RepoDetailsViewModel.Companion companion = RepoDetailsViewModel.INSTANCE;
        ViewModelProvider.Factory factory = companion.getFactory();
        MutableCreationExtras mutableCreationExtras = new MutableCreationExtras(null, 1, null);
        mutableCreationExtras.set(companion.getAPP_KEY(), getApplication());
        mutableCreationExtras.set(companion.getREPO_KEY(), repo);
        this.viewModel = (RepoDetailsViewModel) ViewModelProvider.Companion.create(this, factory, mutableCreationExtras).get(RepoDetailsViewModel.class);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type org.fdroid.fdroid.FDroidApp");
        ((FDroidApp) application).setSecureWindow(this);
        Application application2 = getApplication();
        Intrinsics.checkNotNull(application2, "null cannot be cast to non-null type org.fdroid.fdroid.FDroidApp");
        ((FDroidApp) application2).applyPureBlackBackgroundInDarkTheme(this);
        EdgeToEdge.enable$default(this, null, null, 3, null);
        final Repository repository = FDroidApp.getRepoManager(this).getRepository(getIntent().getLongExtra(ARG_REPO_ID, 0L));
        if (repository == null) {
            finish();
        } else {
            initViewModel(repository);
            ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(-905568157, true, new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsActivity.onCreate.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    if ((i & 3) != 2 || !composer.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-905568157, i, -1, "org.fdroid.fdroid.views.repos.RepoDetailsActivity.onCreate.<anonymous> (RepoDetailsActivity.kt:71)");
                        }
                        RepoDetailsViewModel repoDetailsViewModel = RepoDetailsActivity.this.viewModel;
                        RepoDetailsViewModel repoDetailsViewModel2 = null;
                        if (repoDetailsViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            repoDetailsViewModel = null;
                        }
                        State stateCollectAsState = SnapshotStateKt.collectAsState(repoDetailsViewModel.getRepoFlow(), repository, null, composer, 0, 2);
                        RepoDetailsViewModel repoDetailsViewModel3 = RepoDetailsActivity.this.viewModel;
                        if (repoDetailsViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            repoDetailsViewModel3 = null;
                        }
                        final State stateCollectAsState2 = SnapshotStateKt.collectAsState(repoDetailsViewModel3.getArchiveStateFlow(), ArchiveState.UNKNOWN, null, composer, 48, 2);
                        RepoDetailsViewModel repoDetailsViewModel4 = RepoDetailsActivity.this.viewModel;
                        if (repoDetailsViewModel4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        } else {
                            repoDetailsViewModel2 = repoDetailsViewModel4;
                        }
                        final State stateCollectAsState3 = SnapshotStateKt.collectAsState(repoDetailsViewModel2.getNumberAppsFlow(), 0, null, composer, 48, 2);
                        final Repository repositoryInvoke$lambda$0 = invoke$lambda$0(stateCollectAsState);
                        if (repositoryInvoke$lambda$0 == null) {
                            RepoDetailsActivity.this.finish();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        final RepoDetailsActivity repoDetailsActivity = RepoDetailsActivity.this;
                        ThemeKt.FDroidContent(false, false, ComposableLambdaKt.rememberComposableLambda(-1033844819, true, new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsActivity.onCreate.1.1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i2) {
                                if ((i2 & 3) != 2 || !composer2.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1033844819, i2, -1, "org.fdroid.fdroid.views.repos.RepoDetailsActivity.onCreate.<anonymous>.<anonymous> (RepoDetailsActivity.kt:82)");
                                    }
                                    RepoDetailsScreenKt.RepoDetailsScreen(repositoryInvoke$lambda$0, AnonymousClass1.invoke$lambda$1(stateCollectAsState2), AnonymousClass1.invoke$lambda$2(stateCollectAsState3), repoDetailsActivity, composer2, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer2.skipToGroupEnd();
                            }
                        }, composer, 54), composer, 384, 3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer.skipToGroupEnd();
                }

                private static final Repository invoke$lambda$0(State state) {
                    return (Repository) state.getValue();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final ArchiveState invoke$lambda$1(State state) {
                    return (ArchiveState) state.getValue();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final int invoke$lambda$2(State state) {
                    return ((Number) state.getValue()).intValue();
                }
            }), 1, null);
        }
    }

    @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
    public void onBackClicked() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
    public void onShareClicked() {
        RepoDetailsViewModel repoDetailsViewModel = this.viewModel;
        if (repoDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            repoDetailsViewModel = null;
        }
        Repository repository = (Repository) repoDetailsViewModel.getRepoFlow().getValue();
        if (repository == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(NanoHTTPD.MIME_PLAINTEXT);
        intent.putExtra("android.intent.extra.TEXT", repository.getShareUri());
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.share_repository)));
    }

    @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
    public void onShowQrCodeClicked() {
        ImageView imageView = new ImageView(this);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new C02101(imageView, null), 2, null);
        new MaterialAlertDialogBuilder(this).setTitle(R.string.share_repository).setView((View) imageView).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsActivity$onShowQrCodeClicked$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoDetailsActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.repos.RepoDetailsActivity$onShowQrCodeClicked$1", f = "RepoDetailsActivity.kt", l = {112}, m = "invokeSuspend")
    static final class C02101 extends SuspendLambda implements Function2 {
        final /* synthetic */ ImageView $imageView;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02101(ImageView imageView, Continuation continuation) {
            super(2, continuation);
            this.$imageView = imageView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return RepoDetailsActivity.this.new C02101(this.$imageView, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02101) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                RepoDetailsViewModel repoDetailsViewModel = RepoDetailsActivity.this.viewModel;
                if (repoDetailsViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    repoDetailsViewModel = null;
                }
                RepoDetailsActivity repoDetailsActivity = RepoDetailsActivity.this;
                this.label = 1;
                obj = repoDetailsViewModel.generateQrCode(repoDetailsActivity, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.$imageView.setImageBitmap((Bitmap) obj);
            return Unit.INSTANCE;
        }
    }

    @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
    public void onDeleteClicked() {
        new MaterialAlertDialogBuilder(this).setTitle(R.string.repo_confirm_delete_title).setMessage(R.string.repo_confirm_delete_body).setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RepoDetailsActivity.onDeleteClicked$lambda$2(this.f$0, dialogInterface, i);
            }
        }).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDeleteClicked$lambda$2(RepoDetailsActivity repoDetailsActivity, DialogInterface dialogInterface, int i) {
        RepoDetailsViewModel repoDetailsViewModel = repoDetailsActivity.viewModel;
        if (repoDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            repoDetailsViewModel = null;
        }
        repoDetailsViewModel.deleteRepository();
        dialogInterface.dismiss();
    }

    @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
    public void onInfoClicked() {
        new MaterialAlertDialogBuilder(this).setTitle(R.string.repo_details).setMessage(R.string.repo_details_info_text).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).show();
    }

    @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
    public void onShowAppsClicked() {
        RepoDetailsViewModel repoDetailsViewModel = this.viewModel;
        if (repoDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            repoDetailsViewModel = null;
        }
        Repository repository = (Repository) repoDetailsViewModel.getRepoFlow().getValue();
        if (repository == null) {
            return;
        }
        if (!repository.getEnabled()) {
            throw new IllegalStateException("Show-Apps button should not even be shown");
        }
        Intent intent = new Intent(this, (Class<?>) AppListActivity.class);
        intent.putExtra(AppListActivity.EXTRA_REPO_ID, repository.getRepoId());
        startActivity(intent);
    }

    @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
    public void onToggleArchiveClicked(boolean enabled) {
        RepoDetailsViewModel repoDetailsViewModel = this.viewModel;
        if (repoDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            repoDetailsViewModel = null;
        }
        repoDetailsViewModel.setArchiveRepoEnabled(enabled);
    }

    @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
    public void onEditCredentialsClicked() {
        RepoDetailsViewModel repoDetailsViewModel = this.viewModel;
        if (repoDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            repoDetailsViewModel = null;
        }
        Repository repository = (Repository) repoDetailsViewModel.getRepoFlow().getValue();
        if (repository == null) {
            return;
        }
        View viewInflate = getLayoutInflater().inflate(R.layout.login, (ViewGroup) null, false);
        final TextInputLayout textInputLayout = (TextInputLayout) viewInflate.findViewById(R.id.edit_name);
        final TextInputLayout textInputLayout2 = (TextInputLayout) viewInflate.findViewById(R.id.edit_password);
        EditText editText = textInputLayout.getEditText();
        if (editText != null) {
            String username = repository.getUsername();
            if (username == null) {
                username = "";
            }
            editText.setText(username);
        }
        textInputLayout2.requestFocus();
        new MaterialAlertDialogBuilder(this).setTitle(R.string.repo_basic_auth_title).setView(viewInflate).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsActivity$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RepoDetailsActivity.onEditCredentialsClicked$lambda$4(textInputLayout, textInputLayout2, this, dialogInterface, i);
            }
        }).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onEditCredentialsClicked$lambda$4(TextInputLayout textInputLayout, TextInputLayout textInputLayout2, RepoDetailsActivity repoDetailsActivity, DialogInterface dialogInterface, int i) {
        EditText editText = textInputLayout.getEditText();
        RepoDetailsViewModel repoDetailsViewModel = null;
        String strValueOf = String.valueOf(editText != null ? editText.getText() : null);
        EditText editText2 = textInputLayout2.getEditText();
        String strValueOf2 = String.valueOf(editText2 != null ? editText2.getText() : null);
        if (!StringsKt.isBlank(strValueOf)) {
            RepoDetailsViewModel repoDetailsViewModel2 = repoDetailsActivity.viewModel;
            if (repoDetailsViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                repoDetailsViewModel = repoDetailsViewModel2;
            }
            repoDetailsViewModel.updateUsernameAndPassword(strValueOf, strValueOf2);
        } else {
            Toast.makeText(repoDetailsActivity, R.string.repo_error_empty_username, 1).show();
        }
        dialogInterface.dismiss();
    }

    @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
    public void setMirrorEnabled(Mirror mirror, boolean enabled) {
        Intrinsics.checkNotNullParameter(mirror, "mirror");
        RepoDetailsViewModel repoDetailsViewModel = this.viewModel;
        if (repoDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            repoDetailsViewModel = null;
        }
        repoDetailsViewModel.setMirrorEnabled(mirror, enabled);
    }

    @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
    public void onShareMirror(Mirror mirror) {
        Intrinsics.checkNotNullParameter(mirror, "mirror");
        RepoDetailsViewModel repoDetailsViewModel = this.viewModel;
        if (repoDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            repoDetailsViewModel = null;
        }
        Repository repository = (Repository) repoDetailsViewModel.getRepoFlow().getValue();
        if (repository == null) {
            return;
        }
        String fDroidLinkUrl = mirror.getFDroidLinkUrl(repository.getFingerprint());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(NanoHTTPD.MIME_PLAINTEXT);
        intent.putExtra("android.intent.extra.TEXT", fDroidLinkUrl);
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.share_mirror)));
    }

    @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
    public void onDeleteMirror(final Mirror mirror) {
        Intrinsics.checkNotNullParameter(mirror, "mirror");
        new MaterialAlertDialogBuilder(this).setTitle(R.string.repo_confirm_delete_mirror_title).setMessage(R.string.repo_confirm_delete_mirror_body).setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RepoDetailsActivity.onDeleteMirror$lambda$6(this.f$0, mirror, dialogInterface, i);
            }
        }).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDeleteMirror$lambda$6(RepoDetailsActivity repoDetailsActivity, Mirror mirror, DialogInterface dialogInterface, int i) {
        RepoDetailsViewModel repoDetailsViewModel = repoDetailsActivity.viewModel;
        if (repoDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            repoDetailsViewModel = null;
        }
        repoDetailsViewModel.deleteUserMirror(mirror);
        dialogInterface.dismiss();
    }
}
