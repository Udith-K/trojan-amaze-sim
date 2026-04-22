package org.acra.dialog;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.acra.config.ConfigUtils;
import org.acra.config.DialogConfiguration;
import org.acra.prefs.SharedPreferencesFactory;

/* JADX INFO: compiled from: CrashReportDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0017\u0018\u0000 /2\u00020\u00012\u00020\u0002:\u0001/B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0012\u0010\u001c\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u001eH\u0014J\b\u0010!\u001a\u00020\u001eH\u0014J\n\u0010\"\u001a\u0004\u0018\u00010\u001eH\u0014J\u0012\u0010#\u001a\u00020\b2\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\n\u0010&\u001a\u0004\u0018\u00010\u001eH\u0014J\u0012\u0010'\u001a\u00020\b2\b\u0010(\u001a\u0004\u0018\u00010%H\u0014J\u0018\u0010)\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0011H\u0016J\u0010\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u001bH\u0015J\b\u0010.\u001a\u00020\u0011H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0092.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0092\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0092\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0092.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0092.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0092.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0092\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0094.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u00060"}, d2 = {"Lorg/acra/dialog/CrashReportDialog;", "Landroid/app/Activity;", "Landroid/content/DialogInterface$OnClickListener;", "<init>", "()V", "scrollable", "Landroid/widget/LinearLayout;", "userCommentView", "Landroid/widget/EditText;", "userEmailView", "sharedPreferencesFactory", "Lorg/acra/prefs/SharedPreferencesFactory;", "dialogConfiguration", "Lorg/acra/config/DialogConfiguration;", "helper", "Lorg/acra/dialog/CrashReportDialogHelper;", "padding", "", "dialog", "Landroid/app/AlertDialog;", "getDialog", "()Landroid/app/AlertDialog;", "setDialog", "(Landroid/app/AlertDialog;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "buildAndShowDialog", "buildCustomView", "Landroid/view/View;", "addViewToDialog", "v", "getMainView", "getCommentLabel", "getCommentPrompt", "savedComment", "", "getEmailLabel", "getEmailPrompt", "savedEmail", "onClick", "Landroid/content/DialogInterface;", "which", "onSaveInstanceState", "outState", "loadPaddingFromTheme", "Companion", "acra-dialog_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class CrashReportDialog extends Activity implements DialogInterface.OnClickListener {
    protected AlertDialog dialog;
    private DialogConfiguration dialogConfiguration;
    private CrashReportDialogHelper helper;
    private int padding;
    private LinearLayout scrollable;
    private SharedPreferencesFactory sharedPreferencesFactory;
    private EditText userCommentView;
    private EditText userEmailView;

    protected AlertDialog getDialog() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            return alertDialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dialog");
        return null;
    }

    protected void setDialog(AlertDialog alertDialog) {
        Intrinsics.checkNotNullParameter(alertDialog, "<set-?>");
        this.dialog = alertDialog;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
            this.helper = new CrashReportDialogHelper(this, intent);
            LinearLayout linearLayout = new LinearLayout(this);
            this.scrollable = linearLayout;
            linearLayout.setOrientation(1);
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            CrashReportDialogHelper crashReportDialogHelper = this.helper;
            DialogConfiguration dialogConfiguration = null;
            if (crashReportDialogHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helper");
                crashReportDialogHelper = null;
            }
            this.sharedPreferencesFactory = new SharedPreferencesFactory(applicationContext, crashReportDialogHelper.getConfig());
            CrashReportDialogHelper crashReportDialogHelper2 = this.helper;
            if (crashReportDialogHelper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helper");
                crashReportDialogHelper2 = null;
            }
            DialogConfiguration dialogConfiguration2 = (DialogConfiguration) ConfigUtils.getPluginConfiguration(crashReportDialogHelper2.getConfig(), DialogConfiguration.class);
            this.dialogConfiguration = dialogConfiguration2;
            if (dialogConfiguration2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogConfiguration");
            } else {
                dialogConfiguration = dialogConfiguration2;
            }
            Integer resTheme = dialogConfiguration.getResTheme();
            if (resTheme != null) {
                setTheme(resTheme.intValue());
            }
            this.padding = loadPaddingFromTheme();
            buildAndShowDialog(savedInstanceState);
        } catch (IllegalArgumentException unused) {
            finish();
        }
    }

    protected void buildAndShowDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        DialogConfiguration dialogConfiguration = this.dialogConfiguration;
        DialogConfiguration dialogConfiguration2 = null;
        if (dialogConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogConfiguration");
            dialogConfiguration = null;
        }
        String title = dialogConfiguration.getTitle();
        if (title != null) {
            if (title.length() <= 0) {
                title = null;
            }
            if (title != null) {
                builder.setTitle(title);
            }
        }
        DialogConfiguration dialogConfiguration3 = this.dialogConfiguration;
        if (dialogConfiguration3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogConfiguration");
            dialogConfiguration3 = null;
        }
        Integer resIcon = dialogConfiguration3.getResIcon();
        if (resIcon != null) {
            builder.setIcon(resIcon.intValue());
        }
        AlertDialog.Builder view = builder.setView(buildCustomView(savedInstanceState));
        DialogConfiguration dialogConfiguration4 = this.dialogConfiguration;
        if (dialogConfiguration4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogConfiguration");
            dialogConfiguration4 = null;
        }
        String positiveButtonText = dialogConfiguration4.getPositiveButtonText();
        if (positiveButtonText == null) {
            positiveButtonText = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(positiveButtonText, "getString(...)");
        }
        AlertDialog.Builder positiveButton = view.setPositiveButton(positiveButtonText, this);
        DialogConfiguration dialogConfiguration5 = this.dialogConfiguration;
        if (dialogConfiguration5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogConfiguration");
        } else {
            dialogConfiguration2 = dialogConfiguration5;
        }
        String negativeButtonText = dialogConfiguration2.getNegativeButtonText();
        if (negativeButtonText == null) {
            negativeButtonText = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(negativeButtonText, "getString(...)");
        }
        positiveButton.setNegativeButton(negativeButtonText, this);
        setDialog(builder.create());
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: org.acra.dialog.CrashReportDialog$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f$0.finish();
            }
        });
        getDialog().show();
    }

    protected View buildCustomView(Bundle savedInstanceState) {
        ScrollView scrollView = new ScrollView(this);
        int i = this.padding;
        scrollView.setPadding(i, i, i, i);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        LinearLayout linearLayout = this.scrollable;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scrollable");
            linearLayout = null;
        }
        scrollView.addView(linearLayout);
        addViewToDialog(getMainView());
        View commentLabel = getCommentLabel();
        if (commentLabel != null) {
            commentLabel.setPadding(commentLabel.getPaddingLeft(), this.padding, commentLabel.getPaddingRight(), commentLabel.getPaddingBottom());
            addViewToDialog(commentLabel);
            EditText commentPrompt = getCommentPrompt(savedInstanceState != null ? savedInstanceState.getString("comment") : null);
            addViewToDialog(commentPrompt);
            this.userCommentView = commentPrompt;
        }
        View emailLabel = getEmailLabel();
        if (emailLabel != null) {
            emailLabel.setPadding(emailLabel.getPaddingLeft(), this.padding, emailLabel.getPaddingRight(), emailLabel.getPaddingBottom());
            addViewToDialog(emailLabel);
            EditText emailPrompt = getEmailPrompt(savedInstanceState != null ? savedInstanceState.getString("email") : null);
            addViewToDialog(emailPrompt);
            this.userEmailView = emailPrompt;
        }
        return scrollView;
    }

    protected void addViewToDialog(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        LinearLayout linearLayout = this.scrollable;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scrollable");
            linearLayout = null;
        }
        linearLayout.addView(v);
    }

    protected View getMainView() {
        TextView textView = new TextView(this);
        DialogConfiguration dialogConfiguration = this.dialogConfiguration;
        if (dialogConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogConfiguration");
            dialogConfiguration = null;
        }
        String text = dialogConfiguration.getText();
        if (text != null) {
            String str = text.length() > 0 ? text : null;
            if (str != null) {
                textView.setText(str);
            }
        }
        return textView;
    }

    protected View getCommentLabel() {
        DialogConfiguration dialogConfiguration = this.dialogConfiguration;
        if (dialogConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogConfiguration");
            dialogConfiguration = null;
        }
        String commentPrompt = dialogConfiguration.getCommentPrompt();
        if (commentPrompt == null) {
            return null;
        }
        if (commentPrompt.length() <= 0) {
            commentPrompt = null;
        }
        if (commentPrompt == null) {
            return null;
        }
        TextView textView = new TextView(this);
        textView.setText(commentPrompt);
        return textView;
    }

    protected EditText getCommentPrompt(CharSequence savedComment) {
        EditText editText = new EditText(this);
        editText.setLines(2);
        if (savedComment != null) {
            editText.setText(savedComment);
        }
        return editText;
    }

    protected View getEmailLabel() {
        DialogConfiguration dialogConfiguration = this.dialogConfiguration;
        if (dialogConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogConfiguration");
            dialogConfiguration = null;
        }
        String emailPrompt = dialogConfiguration.getEmailPrompt();
        if (emailPrompt == null) {
            return null;
        }
        if (emailPrompt.length() <= 0) {
            emailPrompt = null;
        }
        if (emailPrompt == null) {
            return null;
        }
        TextView textView = new TextView(this);
        textView.setText(emailPrompt);
        return textView;
    }

    protected EditText getEmailPrompt(CharSequence savedEmail) {
        EditText editText = new EditText(this);
        editText.setSingleLine();
        editText.setInputType(33);
        if (savedEmail == null) {
            SharedPreferencesFactory sharedPreferencesFactory = this.sharedPreferencesFactory;
            if (sharedPreferencesFactory == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferencesFactory");
                sharedPreferencesFactory = null;
            }
            savedEmail = sharedPreferencesFactory.create().getString("acra.user.email", "");
        }
        editText.setText(savedEmail);
        return editText;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        String string;
        String string2;
        Editable text;
        Editable text2;
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        CrashReportDialogHelper crashReportDialogHelper = null;
        if (which == -1) {
            EditText editText = this.userCommentView;
            if (editText == null || (text2 = editText.getText()) == null || (string = text2.toString()) == null) {
                string = "";
            }
            SharedPreferencesFactory sharedPreferencesFactory = this.sharedPreferencesFactory;
            if (sharedPreferencesFactory == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferencesFactory");
                sharedPreferencesFactory = null;
            }
            SharedPreferences sharedPreferencesCreate = sharedPreferencesFactory.create();
            EditText editText2 = this.userEmailView;
            if (editText2 == null || (text = editText2.getText()) == null || (string2 = text.toString()) == null) {
                string2 = sharedPreferencesCreate.getString("acra.user.email", "");
                Intrinsics.checkNotNull(string2);
            } else {
                sharedPreferencesCreate.edit().putString("acra.user.email", string2).apply();
            }
            CrashReportDialogHelper crashReportDialogHelper2 = this.helper;
            if (crashReportDialogHelper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helper");
            } else {
                crashReportDialogHelper = crashReportDialogHelper2;
            }
            crashReportDialogHelper.sendCrash(string, string2);
        } else {
            CrashReportDialogHelper crashReportDialogHelper3 = this.helper;
            if (crashReportDialogHelper3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helper");
            } else {
                crashReportDialogHelper = crashReportDialogHelper3;
            }
            crashReportDialogHelper.cancelReports();
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Editable text;
        Editable text2;
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        EditText editText = this.userCommentView;
        if (editText != null && (text2 = editText.getText()) != null) {
            outState.putString("comment", text2.toString());
        }
        EditText editText2 = this.userEmailView;
        if (editText2 == null || (text = editText2.getText()) == null) {
            return;
        }
        outState.putString("email", text.toString());
    }

    protected int loadPaddingFromTheme() {
        TypedValue typedValue = new TypedValue();
        if (getTheme().resolveAttribute(R.attr.dialogPreferredPadding, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
        }
        return 10;
    }
}
