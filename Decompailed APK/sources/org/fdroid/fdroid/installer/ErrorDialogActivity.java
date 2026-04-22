package org.fdroid.fdroid.installer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorDialogActivity extends FragmentActivity {
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_TITLE = "title";

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("title");
        String stringExtra2 = intent.getStringExtra(EXTRA_MESSAGE);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        materialAlertDialogBuilder.setTitle((CharSequence) stringExtra);
        materialAlertDialogBuilder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.installer.ErrorDialogActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreate$0(dialogInterface, i);
            }
        });
        materialAlertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: org.fdroid.fdroid.installer.ErrorDialogActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$onCreate$1(dialogInterface);
            }
        });
        TextView textView = new TextView(materialAlertDialogBuilder.getContext());
        textView.setText(stringExtra2);
        int iRound = Math.round(TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        textView.setPadding(iRound, iRound, iRound, 0);
        textView.setTextIsSelectable(true);
        materialAlertDialogBuilder.setView((View) textView);
        materialAlertDialogBuilder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(DialogInterface dialogInterface, int i) {
        setResult(-1);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(DialogInterface dialogInterface) {
        setResult(0);
        finish();
    }
}
