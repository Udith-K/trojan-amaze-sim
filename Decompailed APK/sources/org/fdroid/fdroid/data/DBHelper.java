package org.fdroid.fdroid.data;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.database.FDroidDatabaseHolder;
import org.fdroid.database.FDroidFixture;
import org.fdroid.database.InitialRepository;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.work.RepoUpdateWorker;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes2.dex */
public class DBHelper {
    static final int REPO_XML_ITEM_COUNT = 7;
    private static final String TAG = "DBHelper";

    public static FDroidDatabase getDb(final Context context) {
        return FDroidDatabaseHolder.getDb(context, "fdroid_db", new FDroidFixture() { // from class: org.fdroid.fdroid.data.DBHelper$$ExternalSyntheticLambda0
            @Override // org.fdroid.database.FDroidFixture
            public final void prePopulateDb(FDroidDatabase fDroidDatabase) {
                DBHelper.prePopulateDb(context, fDroidDatabase);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void prePopulateDb(final Context context, FDroidDatabase fDroidDatabase) {
        List<String> list;
        int i;
        List<String> listLoadInitialRepos = loadInitialRepos(context);
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        while (i3 < listLoadInitialRepos.size()) {
            boolean zEquals = listLoadInitialRepos.get(i3 + 4).equals("1");
            ArrayList arrayList = new ArrayList();
            String[] strArrSplit = listLoadInitialRepos.get(i3 + 1).split("\\s+");
            int length = strArrSplit.length;
            for (int i4 = i2; i4 < length; i4++) {
                String str = strArrSplit[i4];
                if (!str.isEmpty()) {
                    arrayList.add(str);
                }
            }
            try {
                i = i3;
                list = listLoadInitialRepos;
                try {
                    InitialRepository initialRepository = new InitialRepository(listLoadInitialRepos.get(i3), (String) arrayList.get(i2), arrayList.subList(1, arrayList.size()), listLoadInitialRepos.get(i3 + 2), listLoadInitialRepos.get(i3 + 6), Integer.parseInt(listLoadInitialRepos.get(i3 + 3)), zEquals);
                    z = z || zEquals;
                    fDroidDatabase.getRepositoryDao().insert(initialRepository);
                    i2 = 0;
                } catch (IllegalArgumentException e) {
                    e = e;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid repo: ");
                    i2 = 0;
                    sb.append((String) arrayList.get(0));
                    Log.e(TAG, sb.toString(), e);
                }
            } catch (IllegalArgumentException e2) {
                e = e2;
                list = listLoadInitialRepos;
                i = i3;
            }
            i3 = i + 7;
            listLoadInitialRepos = list;
        }
        ContentProviderMigrator contentProviderMigrator = new ContentProviderMigrator();
        if (!contentProviderMigrator.needsMigration(context)) {
            if (z) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: org.fdroid.fdroid.data.DBHelper$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        RepoUpdateWorker.updateNow(context);
                    }
                });
            }
        } else {
            Log.d(TAG, "Migrating DB...");
            contentProviderMigrator.runMigrations(context, fDroidDatabase);
            contentProviderMigrator.removeOldDb(context);
            resetTransient(context);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: org.fdroid.fdroid.data.DBHelper$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    RepoUpdateWorker.updateNow(context);
                }
            });
        }
    }

    public static void resetTransient(Context context) {
        final FDroidDatabase db = getDb(context);
        Objects.requireNonNull(db);
        Utils.runOffUiThread(new Runnable() { // from class: org.fdroid.fdroid.data.DBHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                db.clearAllAppData();
            }
        });
    }

    public static void resetRepos(final Context context) {
        final FDroidDatabase db = getDb(context);
        Utils.runOffUiThread(new Runnable() { // from class: org.fdroid.fdroid.data.DBHelper$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                DBHelper.lambda$resetRepos$4(db, context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$resetRepos$4(final FDroidDatabase fDroidDatabase, final Context context) {
        fDroidDatabase.runInTransaction(new Runnable() { // from class: org.fdroid.fdroid.data.DBHelper$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DBHelper.lambda$resetRepos$3(fDroidDatabase, context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$resetRepos$3(FDroidDatabase fDroidDatabase, Context context) {
        fDroidDatabase.getRepositoryDao().clearAll();
        prePopulateDb(context, fDroidDatabase);
    }

    static List<String> loadInitialRepos(Context context) throws IllegalArgumentException {
        List<String> listLoadAdditionalRepos = loadAdditionalRepos(context.getPackageName());
        if (listLoadAdditionalRepos.size() % 7 != 0) {
            throw new IllegalArgumentException("additional_repos.xml has wrong item count: " + listLoadAdditionalRepos.size() + " % REPO_XML_ARG_COUNT(7) != 0");
        }
        List listAsList = Arrays.asList(context.getResources().getStringArray(R.array.default_repos));
        if (listAsList.size() % 7 != 0) {
            throw new IllegalArgumentException("default_repos.xml has wrong item count: " + listAsList.size() + " % REPO_XML_ARG_COUNT(7) != 0, FYI the priority item was removed in v1.16");
        }
        ArrayList arrayList = new ArrayList(listLoadAdditionalRepos.size() + listAsList.size());
        arrayList.addAll(listAsList);
        arrayList.addAll(listLoadAdditionalRepos);
        for (int i = 2; i < arrayList.size(); i += 7) {
            arrayList.set(i, ((String) arrayList.get(i)).replaceAll("\\s+", " "));
        }
        return arrayList;
    }

    private static List<String> loadAdditionalRepos(String str) {
        LinkedList linkedList = new LinkedList();
        Iterator it = Arrays.asList("/system", "/product", "/vendor", "/odm", "/oem").iterator();
        while (it.hasNext()) {
            File file = new File(((String) it.next()) + "/etc/" + str + "/additional_repos.xml");
            try {
                if (file.isFile()) {
                    linkedList.addAll(parseAdditionalReposXml(file));
                }
            } catch (Exception e) {
                Log.e(TAG, "Error loading " + file + ": " + e.getMessage());
            }
        }
        return linkedList;
    }

    static List<String> parseAdditionalReposXml(File file) throws XmlPullParserException, IOException {
        LinkedList linkedList = new LinkedList();
        FileInputStream fileInputStream = new FileInputStream(file);
        XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
        xmlPullParserFactoryNewInstance.setNamespaceAware(true);
        XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
        xmlPullParserNewPullParser.setInput(fileInputStream, "UTF-8");
        boolean z = false;
        for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
            String name = xmlPullParserNewPullParser.getName();
            if (eventType != 2) {
                if (eventType == 3) {
                    z = false;
                } else if (eventType == 4 && z) {
                    linkedList.add(xmlPullParserNewPullParser.getText());
                }
            } else if ("item".equals(name)) {
                z = true;
            }
        }
        fileInputStream.close();
        if (linkedList.size() % 7 == 0) {
            return linkedList;
        }
        Log.e(TAG, "Ignoring " + file + ", wrong number of items: " + linkedList.size() + " % 6 != 0");
        return new LinkedList();
    }

    public static List<String> getDefaultRepoAddresses(Context context) {
        List listAsList = Arrays.asList(context.getResources().getStringArray(R.array.default_repos));
        if (listAsList.size() % 7 != 0) {
            throw new IllegalArgumentException("default_repos.xml has wrong item count: " + listAsList.size() + " % REPO_XML_ARG_COUNT(7) != 0, FYI the priority item was removed in v1.16");
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < listAsList.size(); i += 7) {
            if (((String) listAsList.get(i + 4)).equals("1")) {
                String[] strArrSplit = ((String) listAsList.get(i + 1)).split("\\s+");
                int length = strArrSplit.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        String str = strArrSplit[i2];
                        if (!str.isEmpty()) {
                            arrayList.add(str);
                            break;
                        }
                        i2++;
                    }
                }
            }
        }
        return arrayList;
    }
}
