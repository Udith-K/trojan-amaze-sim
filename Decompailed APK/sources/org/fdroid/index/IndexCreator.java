package org.fdroid.index;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.system.ErrnoException;
import android.system.Os;
import androidx.core.content.pm.PackageInfoCompat;
import ch.qos.logback.core.joran.action.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IndexCreator.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\u0019\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u001cH\u0004J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001e\u001a\u00020\u001fH\u0004J\u0012\u0010 \u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001e\u001a\u00020\u001fH\u0004J\u0010\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\u0006H\u0004J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\t0\u00142\u0006\u0010\u001e\u001a\u00020\u001fH\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0015\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018¨\u0006$"}, d2 = {"Lorg/fdroid/index/IndexCreator;", "T", "", "packageManager", "Landroid/content/pm/PackageManager;", "repoDir", "Ljava/io/File;", "packageNames", "", "", "<init>", "(Landroid/content/pm/PackageManager;Ljava/io/File;Ljava/util/Set;)V", "getPackageManager", "()Landroid/content/pm/PackageManager;", "getRepoDir", "()Ljava/io/File;", "getPackageNames", "()Ljava/util/Set;", "iconDir", "iconDirs", "", "nativeCodePattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "Ljava/util/regex/Pattern;", "createRepo", "()Ljava/lang/Object;", "prepareIconFolders", "", "copyIconToRepo", "packageInfo", "Landroid/content/pm/PackageInfo;", "copyApkToRepo", "hashFile", Action.FILE_ATTRIBUTE, "parseNativeCode", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class IndexCreator<T> {
    private final File iconDir;
    private final List<String> iconDirs;
    private final Pattern nativeCodePattern;
    private final PackageManager packageManager;
    private final Set<String> packageNames;
    private final File repoDir;

    public abstract T createRepo() throws IOException;

    public IndexCreator(PackageManager packageManager, File repoDir, Set<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageManager, "packageManager");
        Intrinsics.checkNotNullParameter(repoDir, "repoDir");
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        this.packageManager = packageManager;
        this.repoDir = repoDir;
        this.packageNames = packageNames;
        this.iconDir = new File(repoDir, "icons");
        this.iconDirs = CollectionsKt.listOf((Object[]) new String[]{"icons-120", "icons-160", "icons-240", "icons-320", "icons-480", "icons-640"});
        this.nativeCodePattern = Pattern.compile("^lib/([a-z0-9-]+)/.*");
        if (!repoDir.isDirectory()) {
            throw new IllegalArgumentException((repoDir + " is not a directory").toString());
        }
        if (repoDir.canWrite()) {
            return;
        }
        throw new IllegalArgumentException(("Can not write to " + repoDir).toString());
    }

    protected final PackageManager getPackageManager() {
        return this.packageManager;
    }

    protected final File getRepoDir() {
        return this.repoDir;
    }

    protected final Set<String> getPackageNames() {
        return this.packageNames;
    }

    protected final void prepareIconFolders() throws ErrnoException {
        this.iconDir.mkdir();
        Iterator<T> it = this.iconDirs.iterator();
        while (it.hasNext()) {
            File file = new File(this.repoDir, (String) it.next());
            if (!file.exists()) {
                Os.symlink(this.iconDir.getAbsolutePath(), file.getAbsolutePath());
            }
        }
    }

    protected final String copyIconToRepo(PackageInfo packageInfo) {
        Drawable drawableLoadIcon;
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(packageInfo, "packageInfo");
        String packageName = packageInfo.packageName;
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        long longVersionCode = PackageInfoCompat.getLongVersionCode(packageInfo);
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        if (applicationInfo == null || (drawableLoadIcon = applicationInfo.loadIcon(this.packageManager)) == null) {
            return null;
        }
        if (drawableLoadIcon instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawableLoadIcon).getBitmap();
        } else {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawableLoadIcon.getIntrinsicWidth(), drawableLoadIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawableLoadIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawableLoadIcon.draw(canvas);
            bitmap = bitmapCreateBitmap;
        }
        String str = packageName + "_" + longVersionCode + ".png";
        FileOutputStream fileOutputStream = new FileOutputStream(new File(this.iconDir, str));
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            CloseableKt.closeFinally(fileOutputStream, null);
            return str;
        } finally {
        }
    }

    protected final File copyApkToRepo(PackageInfo packageInfo) throws ErrnoException {
        Intrinsics.checkNotNullParameter(packageInfo, "packageInfo");
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        if (applicationInfo == null) {
            return null;
        }
        String packageName = packageInfo.packageName;
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        File file = new File(this.repoDir, packageName + "_" + PackageInfoCompat.getLongVersionCode(packageInfo) + ".apk");
        if (file.exists()) {
            file.delete();
        }
        Os.symlink(applicationInfo.publicSourceDir, file.getAbsolutePath());
        if (!file.exists()) {
            FilesKt.copyTo$default(new File(applicationInfo.publicSourceDir), file, false, 0, 6, null);
        }
        return file;
    }

    protected final String hashFile(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[8192];
                for (int i = fileInputStream.read(bArr); i >= 0; i = fileInputStream.read(bArr)) {
                    messageDigest.update(bArr, 0, i);
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileInputStream, null);
                IndexUtils indexUtils = IndexUtils.INSTANCE;
                byte[] bArrDigest = messageDigest.digest();
                Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
                return indexUtils.toHex$index_release(bArrDigest);
            } finally {
            }
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    protected final List<String> parseNativeCode(PackageInfo packageInfo) {
        String strGroup;
        Intrinsics.checkNotNullParameter(packageInfo, "packageInfo");
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        if (applicationInfo == null) {
            return CollectionsKt.emptyList();
        }
        JarFile jarFile = new JarFile(applicationInfo.publicSourceDir);
        HashSet hashSet = new HashSet();
        Enumeration<JarEntry> enumerationEntries = jarFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            Matcher matcher = this.nativeCodePattern.matcher(enumerationEntries.nextElement().getName());
            if (matcher.matches() && (strGroup = matcher.group(1)) != null) {
                hashSet.add(strGroup);
            }
        }
        return CollectionsKt.toList(hashSet);
    }
}
