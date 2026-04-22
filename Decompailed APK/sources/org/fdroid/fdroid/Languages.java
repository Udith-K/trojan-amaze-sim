package org.fdroid.fdroid;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes2.dex */
public final class Languages {
    private static final Locale CHINESE_HONG_KONG;
    private static final Locale DEFAULT_LOCALE;
    public static final Locale[] LOCALES_TO_TEST;
    public static final String TAG = "Languages";
    private static final Locale TIBETAN;
    private static final Map<String, String> TMP_MAP;
    public static final String USE_SYSTEM_DEFAULT = "";
    private static Locale locale;
    private static Map<String, String> nameMap;
    private static Languages singleton;

    static {
        Locale locale2 = new Locale("bo");
        TIBETAN = locale2;
        Locale locale3 = new Locale("zh", "HK");
        CHINESE_HONG_KONG = locale3;
        TMP_MAP = new TreeMap();
        DEFAULT_LOCALE = Locale.getDefault();
        LOCALES_TO_TEST = new Locale[]{Locale.ENGLISH, Locale.FRENCH, Locale.GERMAN, Locale.ITALIAN, Locale.JAPANESE, Locale.KOREAN, Locale.SIMPLIFIED_CHINESE, Locale.TRADITIONAL_CHINESE, locale3, locale2, new Locale("af"), new Locale("ar"), new Locale("be"), new Locale("bg"), new Locale("ca"), new Locale("cs"), new Locale("da"), new Locale("el"), new Locale("es"), new Locale("eo"), new Locale("et"), new Locale("eu"), new Locale("fa"), new Locale("fi"), new Locale("he"), new Locale("hi"), new Locale("hu"), new Locale("hy"), new Locale("id"), new Locale("is"), new Locale("it"), new Locale("ml"), new Locale("my"), new Locale("nb"), new Locale("nl"), new Locale("pl"), new Locale("pt"), new Locale("ro"), new Locale("ru"), new Locale("sc"), new Locale("sk"), new Locale("sn"), new Locale("sr"), new Locale("sv"), new Locale("th"), new Locale("tr"), new Locale("uk"), new Locale("vi")};
    }

    private Languages(AppCompatActivity appCompatActivity) {
        for (Locale locale2 : new LinkedHashSet(Arrays.asList(LOCALES_TO_TEST))) {
            Locale locale3 = TIBETAN;
            if (locale2.equals(locale3)) {
                TMP_MAP.put(locale3.getLanguage(), "Tibetan བོད་སྐད།");
            } else {
                Locale locale4 = Locale.SIMPLIFIED_CHINESE;
                if (locale2.equals(locale4)) {
                    TMP_MAP.put(locale4.toString(), "中文 (中国)");
                } else {
                    Locale locale5 = Locale.TRADITIONAL_CHINESE;
                    if (locale2.equals(locale5)) {
                        TMP_MAP.put(locale5.toString(), "中文 (台灣)");
                    } else {
                        Locale locale6 = CHINESE_HONG_KONG;
                        if (locale2.equals(locale6)) {
                            TMP_MAP.put(locale6.toString(), "中文 (香港)");
                        } else {
                            TMP_MAP.put(locale2.getLanguage(), capitalize(locale2.getDisplayLanguage(locale2)));
                        }
                    }
                }
            }
        }
        Map<String, String> map = TMP_MAP;
        map.remove(Locale.getDefault().getLanguage());
        map.put("", appCompatActivity.getString(R.string.pref_language_default));
        nameMap = Collections.unmodifiableMap(map);
    }

    public static Languages get(AppCompatActivity appCompatActivity) {
        if (singleton == null) {
            singleton = new Languages(appCompatActivity);
        }
        return singleton;
    }

    public static void setLanguage(ContextWrapper contextWrapper) {
        if (Build.VERSION.SDK_INT >= 24) {
            Utils.debugLog(TAG, "Languages.setLanguage() ignored on >= android-24");
            Preferences.get().clearLanguage();
            return;
        }
        String language = Preferences.get().getLanguage();
        Locale locale2 = DEFAULT_LOCALE;
        if (TextUtils.equals(language, locale2.getLanguage())) {
            Preferences.get().clearLanguage();
            locale = locale2;
        } else {
            Locale locale3 = locale;
            if (locale3 != null && TextUtils.equals(locale3.getLanguage(), language)) {
                return;
            }
            if (language == null || language.equals("")) {
                Preferences.get().clearLanguage();
                locale = locale2;
            } else {
                String[] strArrSplit = language.split("_");
                if (strArrSplit.length > 1) {
                    locale = new Locale(strArrSplit[0], strArrSplit[1]);
                } else {
                    locale = new Locale(language);
                }
            }
        }
        Locale.setDefault(locale);
        Resources resources = contextWrapper.getBaseContext().getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public static void forceChangeLanguage(AppCompatActivity appCompatActivity) {
        if (Build.VERSION.SDK_INT >= 24) {
            Utils.debugLog(TAG, "Languages.forceChangeLanguage() ignored on >= android-24");
            return;
        }
        Intent intent = appCompatActivity.getIntent();
        if (intent == null) {
            return;
        }
        intent.addFlags(PKIFailureInfo.notAuthorized);
        appCompatActivity.finish();
        appCompatActivity.overridePendingTransition(0, 0);
        appCompatActivity.startActivity(intent);
        appCompatActivity.overridePendingTransition(0, 0);
    }

    public String[] getAllNames() {
        return (String[]) nameMap.values().toArray(new String[0]);
    }

    public String[] getSupportedLocales() {
        return (String[]) nameMap.keySet().toArray(new String[0]);
    }

    private String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
