package org.acra.sender;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.attachment.AcraContentProvider;
import org.acra.attachment.AttachmentUriProvider;
import org.acra.attachment.DefaultAttachmentProvider;
import org.acra.config.ConfigUtils;
import org.acra.config.CoreConfiguration;
import org.acra.config.MailSenderConfiguration;
import org.acra.data.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.util.IOUtils;
import org.acra.util.InstanceCreator;

/* JADX INFO: compiled from: EmailIntentSender.kt */
/* JADX INFO: loaded from: classes2.dex */
public class EmailIntentSender implements ReportSender {
    public static final Companion Companion = new Companion(null);
    private final CoreConfiguration config;
    private final MailSenderConfiguration mailConfig;

    @Override // org.acra.sender.ReportSender
    public /* synthetic */ void send(Context context, CrashReportData crashReportData, Bundle bundle) {
        ReportSender.CC.$default$send(this, context, crashReportData, bundle);
    }

    public EmailIntentSender(CoreConfiguration config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
        this.mailConfig = (MailSenderConfiguration) ConfigUtils.getPluginConfiguration(config, MailSenderConfiguration.class);
    }

    @Override // org.acra.sender.ReportSender
    public void send(Context context, CrashReportData errorContent) throws ReportSenderException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(errorContent, "errorContent");
        String strBuildSubject = buildSubject(context);
        try {
            Pair bodyAndAttachments = getBodyAndAttachments(context, this.config.getReportFormat().toFormattedString(errorContent, this.config.getReportContent(), "\n", "\n  ", false));
            String str = (String) bodyAndAttachments.component1();
            List list = (List) bodyAndAttachments.component2();
            if (Build.VERSION.SDK_INT < 33) {
                sendWithSelector(strBuildSubject, str, list, context);
            } else {
                resolveAndSend(strBuildSubject, str, list, context);
            }
        } catch (Exception e) {
            throw new ReportSenderException("Failed to convert Report to text", e);
        }
    }

    private void resolveAndSend(String str, String str2, List list, Context context) throws ReportSenderException {
        PackageManager packageManager = context.getPackageManager();
        Intent intentBuildResolveIntent = buildResolveIntent();
        ComponentName componentNameResolveActivity = intentBuildResolveIntent.resolveActivity(packageManager);
        if (componentNameResolveActivity != null) {
            if (list.isEmpty()) {
                context.startActivity(buildFallbackIntent(str, str2));
                return;
            }
            Intent intentBuildAttachmentIntent = buildAttachmentIntent(str, str2, list);
            Intent intent = new Intent(intentBuildAttachmentIntent);
            intent.setType("*/*");
            Intrinsics.checkNotNull(packageManager);
            List<Intent> listBuildInitialIntents = buildInitialIntents(packageManager, intentBuildResolveIntent, intentBuildAttachmentIntent);
            String packageName = getPackageName(componentNameResolveActivity, listBuildInitialIntents);
            intentBuildAttachmentIntent.setPackage(packageName);
            intent.setPackage(packageName);
            if (packageName == null) {
                for (Intent intent2 : listBuildInitialIntents) {
                    grantPermission(context, intent2, intent2.getPackage(), list);
                }
                showChooser(context, CollectionsKt.toMutableList((Collection) listBuildInitialIntents));
                return;
            }
            if (intentBuildAttachmentIntent.resolveActivity(packageManager) != null) {
                grantPermission(context, intentBuildAttachmentIntent, packageName, list);
                context.startActivity(intentBuildAttachmentIntent);
                return;
            } else if (intent.resolveActivity(packageManager) == null) {
                ACRA.log.w(ACRA.LOG_TAG, "No email client supporting attachments found. Attachments will be ignored");
                context.startActivity(buildFallbackIntent(str, str2));
                return;
            } else {
                grantPermission(context, intent, packageName, list);
                context.startActivity(intent);
                return;
            }
        }
        throw new ReportSenderException("No email client found");
    }

    private void sendWithSelector(String str, String str2, List list, Context context) throws ReportSenderException {
        Intent intentBuildAttachmentIntent;
        if (list.size() == 1) {
            intentBuildAttachmentIntent = buildSingleAttachmentIntent(str, str2, (Uri) CollectionsKt.first(list));
        } else {
            intentBuildAttachmentIntent = buildAttachmentIntent(str, str2, list);
        }
        intentBuildAttachmentIntent.setSelector(buildResolveIntent());
        grantPermission(context, intentBuildAttachmentIntent, null, list);
        try {
            context.startActivity(intentBuildAttachmentIntent);
        } catch (ActivityNotFoundException e) {
            try {
                resolveAndSend(str, str2, list, context);
            } catch (ActivityNotFoundException e2) {
                ReportSenderException reportSenderException = new ReportSenderException("No email client found", e2);
                ExceptionsKt.addSuppressed(reportSenderException, e);
                throw reportSenderException;
            }
        }
    }

    @Override // org.acra.sender.ReportSender
    public boolean requiresForeground() {
        return Build.VERSION.SDK_INT >= 29;
    }

    private String getPackageName(ComponentName componentName, List list) {
        String packageName = componentName.getPackageName();
        if (!Intrinsics.areEqual(packageName, "android")) {
            return packageName;
        }
        if (list.size() > 1) {
            return null;
        }
        return list.size() == 1 ? ((Intent) list.get(0)).getPackage() : packageName;
    }

    protected Intent buildAttachmentIntent(String subject, String str, List attachments) {
        Intrinsics.checkNotNullParameter(subject, "subject");
        Intrinsics.checkNotNullParameter(attachments, "attachments");
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{this.mailConfig.getMailTo()});
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.SUBJECT", subject);
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList) CollectionsKt.toCollection(attachments, new ArrayList()));
        intent.putExtra("android.intent.extra.TEXT", str);
        return intent;
    }

    protected Intent buildSingleAttachmentIntent(String subject, String str, Uri attachment) {
        Intrinsics.checkNotNullParameter(subject, "subject");
        Intrinsics.checkNotNullParameter(attachment, "attachment");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{this.mailConfig.getMailTo()});
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.SUBJECT", subject);
        intent.putExtra("android.intent.extra.STREAM", attachment);
        intent.putExtra("android.intent.extra.TEXT", str);
        return intent;
    }

    protected Intent buildResolveIntent() {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto:"));
        intent.addFlags(268435456);
        return intent;
    }

    protected Intent buildFallbackIntent(String subject, String body) {
        Intrinsics.checkNotNullParameter(subject, "subject");
        Intrinsics.checkNotNullParameter(body, "body");
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto:" + this.mailConfig.getMailTo() + "?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(body)));
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.SUBJECT", subject);
        intent.putExtra("android.intent.extra.TEXT", body);
        return intent;
    }

    private List buildInitialIntents(PackageManager packageManager, Intent intent, Intent intent2) {
        List<ResolveInfo> listQueryDefaultActivities = EmailIntentSenderKt.queryDefaultActivities(packageManager, intent);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : listQueryDefaultActivities) {
            Intent intent3 = new Intent(intent2);
            intent3.setPackage(resolveInfo.activityInfo.packageName);
            if (intent3.resolveActivity(packageManager) != null) {
                arrayList.add(intent3);
            } else {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                intent3.setClassName(activityInfo.packageName, activityInfo.name);
                if (intent3.resolveActivity(packageManager) != null) {
                    arrayList.add(intent3);
                }
            }
        }
        return arrayList;
    }

    private void showChooser(Context context, List list) {
        Intent intent = new Intent("android.intent.action.CHOOSER");
        intent.putExtra("android.intent.extra.INTENT", (Parcelable) list.remove(0));
        intent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) list.toArray(new Intent[0]));
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    private void grantPermission(Context context, Intent intent, String str, List list) {
        if (str == null) {
            PackageManager packageManager = context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
            Iterator it = EmailIntentSenderKt.queryDefaultActivities(packageManager, intent).iterator();
            while (it.hasNext()) {
                grantPermission(context, intent, ((ResolveInfo) it.next()).activityInfo.packageName, list);
            }
            return;
        }
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            context.grantUriPermission(str, (Uri) it2.next(), 1);
        }
    }

    protected String buildSubject(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String subject = this.mailConfig.getSubject();
        if (subject != null && subject.length() > 0) {
            return subject;
        }
        return context.getPackageName() + " Crash Report";
    }

    protected Pair getBodyAndAttachments(Context context, String reportText) {
        Uri uriCreateAttachmentFromString;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reportText, "reportText");
        String body = this.mailConfig.getBody();
        if (this.mailConfig.getReportAsFile()) {
            if (body == null) {
                body = "";
            }
        } else if (body == null || body.length() <= 0) {
            body = reportText;
        } else {
            body = body + "\n" + reportText;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(((AttachmentUriProvider) InstanceCreator.create(this.config.getAttachmentUriProvider(), new Function0() { // from class: org.acra.sender.EmailIntentSender$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return EmailIntentSender.getBodyAndAttachments$lambda$3();
            }
        })).getAttachments(context, this.config));
        if (this.mailConfig.getReportAsFile() && (uriCreateAttachmentFromString = createAttachmentFromString(context, this.mailConfig.getReportFileName(), reportText)) != null) {
            arrayList.add(uriCreateAttachmentFromString);
        }
        return TuplesKt.to(body, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AttachmentUriProvider getBodyAndAttachments$lambda$3() {
        return new DefaultAttachmentProvider();
    }

    protected Uri createAttachmentFromString(Context context, String name, String content) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(content, "content");
        File file = new File(context.getCacheDir(), name);
        try {
            IOUtils.writeStringToFile(file, content);
            return AcraContentProvider.INSTANCE.getUriForFile(context, file);
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX INFO: compiled from: EmailIntentSender.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
