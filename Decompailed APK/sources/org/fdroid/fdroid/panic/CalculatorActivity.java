package org.fdroid.fdroid.panic;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.regex.Pattern;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
public class CalculatorActivity extends AppCompatActivity {
    private static final String DIVIDED = "÷";
    private static final String MINUS = "-";
    private static final String PERCENT = "%";
    private static final String PLUS = "+";
    private static final String TIMES = "×";
    private String lastOp;
    private TextView textView;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        FDroidApp fDroidApp = (FDroidApp) getApplication();
        fDroidApp.setSecureWindow(this);
        fDroidApp.applyPureBlackBackgroundInDarkTheme(this);
        super.onCreate(bundle);
        setContentView(R.layout.activity_calculator);
        setSupportActionBar((MaterialToolbar) findViewById(R.id.toolbar));
        this.textView = (TextView) findViewById(R.id.textView);
    }

    public void ce(View view) {
        this.textView.setText((CharSequence) null);
    }

    public void c(View view) {
        if (this.textView.length() > 0) {
            this.textView.setText(this.textView.getText().toString().substring(0, r4.length() - 1));
        }
    }

    public void number(View view) {
        String str = String.format("%s%s", this.textView.getText(), ((Button) view).getText().toString());
        if (str.equals(String.valueOf(HidingManager.getUnhidePin(this)))) {
            HidingManager.show(this);
        }
        this.textView.setText(str);
    }

    public void op(View view) {
        double dDoubleValue;
        String string = this.textView.getText().toString();
        if (string.length() == 0 || containsBinaryOperator(String.valueOf(string.charAt(string.length() - 1)))) {
            return;
        }
        String string2 = ((Button) view).getText().toString();
        if (containsBinaryOperator(string2)) {
            this.lastOp = string2;
            this.textView.setText(String.format("%s%s", string, string2));
        } else if (string2.equals(PERCENT)) {
            try {
                dDoubleValue = Double.valueOf(eval(string)).doubleValue();
            } catch (NumberFormatException unused) {
                dDoubleValue = 0.0d;
            }
            this.textView.setText(toString(dDoubleValue / 100.0d));
        } else if ("=".equals(string2)) {
            this.textView.setText(eval(string));
        } else {
            Toast.makeText(this, "Error: Unknown Operation", 0).show();
        }
    }

    private String eval(String str) {
        double dDoubleValue;
        double dDoubleValue2;
        String str2 = this.lastOp;
        if (str2 == null || !str.contains(str2)) {
            return str;
        }
        String str3 = this.lastOp;
        this.lastOp = null;
        String[] strArrSplit = str.split(Pattern.quote(str3));
        try {
            dDoubleValue = Double.valueOf(strArrSplit[0]).doubleValue();
            dDoubleValue2 = Double.valueOf(strArrSplit[1]).doubleValue();
            str3.hashCode();
            switch (str3) {
                case "+":
                    return toString(dDoubleValue + dDoubleValue2);
                case "-":
                    return toString(dDoubleValue - dDoubleValue2);
                case "×":
                    return toString(dDoubleValue * dDoubleValue2);
                case "÷":
                    if (dDoubleValue2 == 0.0d) {
                        return "";
                    }
                    return toString(dDoubleValue / dDoubleValue2);
                default:
                    Toast.makeText(this, "Error: Unknown Operation", 0).show();
                    return str;
            }
        } catch (NumberFormatException unused) {
            return "";
        }
    }

    private boolean containsBinaryOperator(String str) {
        return str.contains(TIMES) || str.contains(DIVIDED) || str.contains(PLUS) || str.contains(MINUS);
    }

    private String toString(double d) {
        String strValueOf = String.valueOf(d);
        return (strValueOf.length() <= 2 || !strValueOf.endsWith(".0")) ? strValueOf : strValueOf.substring(0, strValueOf.length() - 2);
    }
}
