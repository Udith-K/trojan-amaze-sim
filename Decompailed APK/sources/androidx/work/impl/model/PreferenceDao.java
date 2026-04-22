package androidx.work.impl.model;

/* JADX INFO: compiled from: PreferenceDao.kt */
/* JADX INFO: loaded from: classes.dex */
public interface PreferenceDao {
    Long getLongValue(String str);

    void insertPreference(Preference preference);
}
