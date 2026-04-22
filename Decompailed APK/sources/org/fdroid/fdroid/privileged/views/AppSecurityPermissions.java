package org.fdroid.fdroid.privileged.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;

/* JADX INFO: loaded from: classes2.dex */
public class AppSecurityPermissions {
    private static final String TAG = "AppSecurityPermissions";
    public static final int WHICH_ALL = 65535;
    public static final int WHICH_NEW = 4;
    private final Context context;
    private final LayoutInflater inflater;
    private final CharSequence newPermPrefix;
    private final PermissionInfoComparator permComparator;
    private final PermissionGroupInfoComparator permGroupComparator;
    private final Map<String, MyPermissionGroupInfo> permGroups;
    private final List<MyPermissionGroupInfo> permGroupsList;
    private final PackageManager pm;

    private static boolean isNewPermission(PackageInfo packageInfo, int i) {
        return packageInfo != null && (i & 2) == 0;
    }

    @SuppressLint({"ParcelCreator"})
    static class MyPermissionGroupInfo extends PermissionGroupInfo {
        final List<MyPermissionInfo> allPermissions;
        CharSequence label;
        final List<MyPermissionInfo> newPermissions;

        MyPermissionGroupInfo(PermissionInfo permissionInfo) {
            this.newPermissions = new ArrayList();
            this.allPermissions = new ArrayList();
            String str = permissionInfo.packageName;
            ((PermissionGroupInfo) this).name = str;
            ((PermissionGroupInfo) this).packageName = str;
        }

        MyPermissionGroupInfo(PermissionGroupInfo permissionGroupInfo) {
            super(permissionGroupInfo);
            this.newPermissions = new ArrayList();
            this.allPermissions = new ArrayList();
        }

        Drawable loadGroupIcon(Context context, PackageManager packageManager) {
            Drawable drawable;
            if (((PermissionGroupInfo) this).icon != 0) {
                try {
                    drawable = loadUnbadgedIcon(packageManager);
                } catch (NullPointerException unused) {
                    drawable = ContextCompat.getDrawable(context, R.drawable.ic_perm_device_info);
                }
            } else {
                drawable = ContextCompat.getDrawable(context, R.drawable.ic_perm_device_info);
            }
            Preferences.Theme theme = Preferences.get().getTheme();
            Drawable drawableMutate = DrawableCompat.wrap(drawable).mutate();
            DrawableCompat.setTint(drawableMutate, theme == Preferences.Theme.light ? -16777216 : -1);
            return drawableMutate;
        }
    }

    @SuppressLint({"ParcelCreator"})
    private static class MyPermissionInfo extends PermissionInfo {
        int existingReqFlags;
        CharSequence label;
        boolean newPerm;

        MyPermissionInfo(PermissionInfo permissionInfo) {
            super(permissionInfo);
        }
    }

    public static class PermissionItemView extends LinearLayout implements View.OnClickListener, View.OnLongClickListener {
        AlertDialog dialog;
        MyPermissionGroupInfo group;
        MyPermissionInfo perm;

        public PermissionItemView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            setClickable(true);
        }

        void setPermission(MyPermissionGroupInfo myPermissionGroupInfo, MyPermissionInfo myPermissionInfo, boolean z, CharSequence charSequence) {
            this.group = myPermissionGroupInfo;
            this.perm = myPermissionInfo;
            ImageView imageView = (ImageView) findViewById(R.id.perm_icon);
            TextView textView = (TextView) findViewById(R.id.perm_name);
            Drawable drawableLoadGroupIcon = z ? myPermissionGroupInfo.loadGroupIcon(getContext(), getContext().getPackageManager()) : null;
            CharSequence charSequence2 = myPermissionInfo.label;
            CharSequence charSequence3 = charSequence2;
            charSequence3 = charSequence2;
            if (myPermissionInfo.newPerm && charSequence != null) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                Parcel parcelObtain = Parcel.obtain();
                TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                parcelObtain.setDataPosition(0);
                CharSequence charSequence4 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcelObtain);
                parcelObtain.recycle();
                spannableStringBuilder.append(charSequence4);
                spannableStringBuilder.append((CharSequence) " ");
                spannableStringBuilder.append(charSequence2);
                charSequence3 = spannableStringBuilder;
            }
            imageView.setImageDrawable(drawableLoadGroupIcon);
            textView.setText(charSequence3);
            setOnClickListener(this);
            setOnLongClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CharSequence charSequenceLoadLabel;
            if (this.group == null || this.perm == null) {
                return;
            }
            AlertDialog alertDialog = this.dialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            PackageManager packageManager = getContext().getPackageManager();
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(getContext());
            materialAlertDialogBuilder.setTitle(this.group.label);
            MyPermissionInfo myPermissionInfo = this.perm;
            if (((PermissionInfo) myPermissionInfo).descriptionRes != 0) {
                materialAlertDialogBuilder.setMessage(myPermissionInfo.loadDescription(packageManager));
            } else {
                try {
                    charSequenceLoadLabel = packageManager.getApplicationInfo(((PermissionInfo) myPermissionInfo).packageName, 0).loadLabel(packageManager);
                } catch (PackageManager.NameNotFoundException unused) {
                    charSequenceLoadLabel = ((PermissionInfo) this.perm).packageName;
                }
                materialAlertDialogBuilder.setMessage((CharSequence) (getContext().getString(R.string.perms_description_app, charSequenceLoadLabel) + "\n\n" + ((PermissionInfo) this.perm).name));
            }
            materialAlertDialogBuilder.setCancelable(true);
            materialAlertDialogBuilder.setIcon(this.group.loadGroupIcon(getContext(), packageManager));
            AlertDialog alertDialogShow = materialAlertDialogBuilder.show();
            this.dialog = alertDialogShow;
            alertDialogShow.setCanceledOnTouchOutside(true);
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            Utils.copyToClipboard(getContext(), String.valueOf(this.perm.label), ((PermissionInfo) this.perm).name, R.string.copied_permission_to_clipboard);
            return true;
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            AlertDialog alertDialog = this.dialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        }
    }

    private AppSecurityPermissions(Context context) {
        this.permGroups = new HashMap();
        this.permGroupsList = new ArrayList();
        this.permGroupComparator = new PermissionGroupInfoComparator();
        this.permComparator = new PermissionInfoComparator();
        this.context = context;
        this.inflater = (LayoutInflater) ContextCompat.getSystemService(context, LayoutInflater.class);
        this.pm = context.getPackageManager();
        this.newPermPrefix = context.getText(R.string.perms_new_perm_prefix);
    }

    public AppSecurityPermissions(Context context, PackageInfo packageInfo) {
        PackageInfo packageInfo2;
        this(context);
        if (packageInfo == null) {
            return;
        }
        HashSet hashSet = new HashSet();
        if (packageInfo.requestedPermissions != null) {
            try {
                packageInfo2 = this.pm.getPackageInfo(packageInfo.packageName, PKIFailureInfo.certConfirmed);
            } catch (PackageManager.NameNotFoundException unused) {
                packageInfo2 = null;
            }
            extractPerms(packageInfo, hashSet, packageInfo2);
        }
        setPermissions(new ArrayList(hashSet));
    }

    private int[] getRequestedPermissionFlags(PackageInfo packageInfo) {
        return packageInfo.requestedPermissionsFlags;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0035 A[Catch: NameNotFoundException -> 0x0096, TryCatch #0 {NameNotFoundException -> 0x0096, blocks: (B:10:0x0010, B:14:0x001c, B:17:0x0021, B:19:0x0026, B:25:0x0035, B:27:0x003d, B:30:0x0044, B:32:0x0048, B:34:0x004e, B:37:0x005a, B:40:0x0064, B:44:0x007e, B:41:0x006a, B:43:0x0079, B:45:0x0085, B:22:0x002f), top: B:50:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0044 A[Catch: NameNotFoundException -> 0x0096, TryCatch #0 {NameNotFoundException -> 0x0096, blocks: (B:10:0x0010, B:14:0x001c, B:17:0x0021, B:19:0x0026, B:25:0x0035, B:27:0x003d, B:30:0x0044, B:32:0x0048, B:34:0x004e, B:37:0x005a, B:40:0x0064, B:44:0x007e, B:41:0x006a, B:43:0x0079, B:45:0x0085, B:22:0x002f), top: B:50:0x0010 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void extractPerms(android.content.pm.PackageInfo r10, java.util.Set<org.fdroid.fdroid.privileged.views.AppSecurityPermissions.MyPermissionInfo> r11, android.content.pm.PackageInfo r12) {
        /*
            r9 = this;
            java.lang.String[] r10 = r10.requestedPermissions
            if (r10 == 0) goto Lb0
            int r0 = r10.length
            if (r0 != 0) goto L9
            goto Lb0
        L9:
            int r0 = r10.length
            r1 = 0
            r2 = r1
        Lc:
            if (r2 >= r0) goto Lb0
            r3 = r10[r2]
            android.content.pm.PackageManager r4 = r9.pm     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            android.content.pm.PermissionInfo r4 = r4.getPermissionInfo(r3, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            if (r4 != 0) goto L1a
            goto Lac
        L1a:
            if (r12 == 0) goto L32
            java.lang.String[] r5 = r12.requestedPermissions     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            if (r5 == 0) goto L32
            r5 = r1
        L21:
            java.lang.String[] r6 = r12.requestedPermissions     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            int r7 = r6.length     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            if (r5 >= r7) goto L32
            r6 = r6[r5]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            boolean r6 = r3.equals(r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            if (r6 == 0) goto L2f
            goto L33
        L2f:
            int r5 = r5 + 1
            goto L21
        L32:
            r5 = -1
        L33:
            if (r5 < 0) goto L3c
            int[] r6 = r9.getRequestedPermissionFlags(r12)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            r5 = r6[r5]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            goto L3d
        L3c:
            r5 = r1
        L3d:
            boolean r6 = r9.isDisplayablePermission(r4, r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            if (r6 != 0) goto L44
            goto Lac
        L44:
            java.lang.String r6 = r4.group     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            if (r6 != 0) goto L4d
            java.lang.String r7 = r4.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            r4.group = r7     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            goto L4e
        L4d:
            r7 = r6
        L4e:
            java.util.Map<java.lang.String, org.fdroid.fdroid.privileged.views.AppSecurityPermissions$MyPermissionGroupInfo> r8 = r9.permGroups     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            java.lang.Object r7 = r8.get(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            org.fdroid.fdroid.privileged.views.AppSecurityPermissions$MyPermissionGroupInfo r7 = (org.fdroid.fdroid.privileged.views.AppSecurityPermissions.MyPermissionGroupInfo) r7     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            if (r7 != 0) goto L85
            if (r6 == 0) goto L61
            android.content.pm.PackageManager r7 = r9.pm     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            android.content.pm.PermissionGroupInfo r6 = r7.getPermissionGroupInfo(r6, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            goto L62
        L61:
            r6 = 0
        L62:
            if (r6 == 0) goto L6a
            org.fdroid.fdroid.privileged.views.AppSecurityPermissions$MyPermissionGroupInfo r7 = new org.fdroid.fdroid.privileged.views.AppSecurityPermissions$MyPermissionGroupInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            r7.<init>(r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            goto L7e
        L6a:
            java.lang.String r6 = r4.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            r4.group = r6     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            java.util.Map<java.lang.String, org.fdroid.fdroid.privileged.views.AppSecurityPermissions$MyPermissionGroupInfo> r7 = r9.permGroups     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            java.lang.Object r6 = r7.get(r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            r7 = r6
            org.fdroid.fdroid.privileged.views.AppSecurityPermissions$MyPermissionGroupInfo r7 = (org.fdroid.fdroid.privileged.views.AppSecurityPermissions.MyPermissionGroupInfo) r7     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            if (r7 != 0) goto L7e
            org.fdroid.fdroid.privileged.views.AppSecurityPermissions$MyPermissionGroupInfo r7 = new org.fdroid.fdroid.privileged.views.AppSecurityPermissions$MyPermissionGroupInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            r7.<init>(r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
        L7e:
            java.util.Map<java.lang.String, org.fdroid.fdroid.privileged.views.AppSecurityPermissions$MyPermissionGroupInfo> r6 = r9.permGroups     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            java.lang.String r8 = r4.group     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            r6.put(r8, r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
        L85:
            org.fdroid.fdroid.privileged.views.AppSecurityPermissions$MyPermissionInfo r6 = new org.fdroid.fdroid.privileged.views.AppSecurityPermissions$MyPermissionInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            r6.<init>(r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            r6.existingReqFlags = r5     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            boolean r4 = isNewPermission(r12, r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            r6.newPerm = r4     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            r11.add(r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L96
            goto Lac
        L96:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Ignoring unknown permission:"
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            java.lang.String r4 = "AppSecurityPermissions"
            android.util.Log.i(r4, r3)
        Lac:
            int r2 = r2 + 1
            goto Lc
        Lb0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.privileged.views.AppSecurityPermissions.extractPerms(android.content.pm.PackageInfo, java.util.Set, android.content.pm.PackageInfo):void");
    }

    private List<MyPermissionInfo> getPermissionList(MyPermissionGroupInfo myPermissionGroupInfo, int i) {
        if (i == 4) {
            return myPermissionGroupInfo.newPermissions;
        }
        return myPermissionGroupInfo.allPermissions;
    }

    public int getPermissionCount(int i) {
        Iterator<MyPermissionGroupInfo> it = this.permGroupsList.iterator();
        int size = 0;
        while (it.hasNext()) {
            size += getPermissionList(it.next(), i).size();
        }
        return size;
    }

    public View getPermissionsView(int i) {
        LinearLayout linearLayout = (LinearLayout) this.inflater.inflate(R.layout.app_perms_summary, (ViewGroup) null);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.perms_list);
        View viewFindViewById = linearLayout.findViewById(R.id.no_permissions);
        displayPermissions(this.permGroupsList, linearLayout2, i);
        if (linearLayout2.getChildCount() <= 0) {
            viewFindViewById.setVisibility(0);
        }
        return linearLayout;
    }

    private void displayPermissions(List<MyPermissionGroupInfo> list, LinearLayout linearLayout, int i) {
        linearLayout.removeAllViews();
        int i2 = (int) (this.context.getResources().getDisplayMetrics().density * 8.0f);
        for (MyPermissionGroupInfo myPermissionGroupInfo : list) {
            List<MyPermissionInfo> permissionList = getPermissionList(myPermissionGroupInfo, i);
            int i3 = 0;
            while (i3 < permissionList.size()) {
                View permissionItemView = getPermissionItemView(myPermissionGroupInfo, permissionList.get(i3), i3 == 0, i != 4 ? this.newPermPrefix : null);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                if (i3 == 0) {
                    layoutParams.topMargin = i2;
                }
                if (i3 == myPermissionGroupInfo.allPermissions.size() - 1) {
                    layoutParams.bottomMargin = i2;
                }
                if (linearLayout.getChildCount() == 0) {
                    layoutParams.topMargin *= 2;
                }
                linearLayout.addView(permissionItemView, layoutParams);
                i3++;
            }
        }
    }

    private PermissionItemView getPermissionItemView(MyPermissionGroupInfo myPermissionGroupInfo, MyPermissionInfo myPermissionInfo, boolean z, CharSequence charSequence) {
        PermissionItemView permissionItemView = (PermissionItemView) this.inflater.inflate((((PermissionInfo) myPermissionInfo).flags & 1) != 0 ? R.layout.app_permission_item_money : R.layout.app_permission_item, (ViewGroup) null);
        permissionItemView.setPermission(myPermissionGroupInfo, myPermissionInfo, z, charSequence);
        return permissionItemView;
    }

    private boolean isDisplayablePermission(PermissionInfo permissionInfo, int i) {
        int i2 = permissionInfo.protectionLevel;
        int i3 = i2 & 15;
        boolean z = i3 == 0;
        boolean z2 = i3 == 1 || (i2 & 128) != 0;
        if (z || z2) {
            return true;
        }
        return ((i2 & 32) != 0) && ((i & 2) != 0);
    }

    private static class PermissionGroupInfoComparator implements Comparator<MyPermissionGroupInfo> {
        private final Collator collator;

        private PermissionGroupInfoComparator() {
            this.collator = Collator.getInstance();
        }

        @Override // java.util.Comparator
        public final int compare(MyPermissionGroupInfo myPermissionGroupInfo, MyPermissionGroupInfo myPermissionGroupInfo2) {
            return this.collator.compare(myPermissionGroupInfo.label, myPermissionGroupInfo2.label);
        }
    }

    private static class PermissionInfoComparator implements Comparator<MyPermissionInfo> {
        private final Collator collator = Collator.getInstance();

        PermissionInfoComparator() {
        }

        @Override // java.util.Comparator
        public final int compare(MyPermissionInfo myPermissionInfo, MyPermissionInfo myPermissionInfo2) {
            return this.collator.compare(myPermissionInfo.label, myPermissionInfo2.label);
        }
    }

    private void addPermToList(List<MyPermissionInfo> list, MyPermissionInfo myPermissionInfo) {
        if (myPermissionInfo.label == null) {
            myPermissionInfo.label = myPermissionInfo.loadLabel(this.pm);
        }
        if (Collections.binarySearch(list, myPermissionInfo, this.permComparator) < 0) {
            list.add((-r0) - 1, myPermissionInfo);
        }
    }

    private void setPermissions(List<MyPermissionInfo> list) {
        MyPermissionGroupInfo myPermissionGroupInfo;
        if (list != null) {
            for (MyPermissionInfo myPermissionInfo : list) {
                if (isDisplayablePermission(myPermissionInfo, myPermissionInfo.existingReqFlags) && (myPermissionGroupInfo = this.permGroups.get(((PermissionInfo) myPermissionInfo).group)) != null) {
                    myPermissionInfo.label = myPermissionInfo.loadLabel(this.pm);
                    addPermToList(myPermissionGroupInfo.allPermissions, myPermissionInfo);
                    if (myPermissionInfo.newPerm) {
                        addPermToList(myPermissionGroupInfo.newPermissions, myPermissionInfo);
                    }
                }
            }
        }
        for (MyPermissionGroupInfo myPermissionGroupInfo2 : this.permGroups.values()) {
            if (((PermissionGroupInfo) myPermissionGroupInfo2).labelRes != 0 || ((PermissionGroupInfo) myPermissionGroupInfo2).nonLocalizedLabel != null) {
                myPermissionGroupInfo2.label = myPermissionGroupInfo2.loadLabel(this.pm);
            } else {
                try {
                    myPermissionGroupInfo2.label = this.pm.getApplicationInfo(((PermissionGroupInfo) myPermissionGroupInfo2).packageName, 0).loadLabel(this.pm);
                } catch (PackageManager.NameNotFoundException unused) {
                    myPermissionGroupInfo2.label = myPermissionGroupInfo2.loadLabel(this.pm);
                }
            }
            this.permGroupsList.add(myPermissionGroupInfo2);
        }
        Collections.sort(this.permGroupsList, this.permGroupComparator);
    }
}
