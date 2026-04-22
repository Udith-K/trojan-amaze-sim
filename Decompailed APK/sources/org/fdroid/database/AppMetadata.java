package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.i18n.ErrorBundle;
import org.fdroid.fdroid.views.apps.AppListActivity;

/* JADX INFO: compiled from: App.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\bF\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 n2\u00020\u0001:\u0001nB¯\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u001c\b\u0002\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\n\u0012\u001c\b\u0002\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\n\u0012\u001c\b\u0002\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\u001c\b\u0002\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\n\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001c\u0012\u0006\u0010$\u001a\u00020%¢\u0006\u0004\b&\u0010'J\t\u0010J\u001a\u00020\u0003HÆ\u0003J\t\u0010K\u001a\u00020\u0005HÆ\u0003J\t\u0010L\u001a\u00020\u0003HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\u001d\u0010N\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\nHÆ\u0003J\u001d\u0010O\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\nHÆ\u0003J\u001d\u0010P\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\nHÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001d\u0010Z\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\nHÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010_\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001cHÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001cHÆ\u0003J\t\u0010g\u001a\u00020%HÆ\u0003J»\u0003\u0010h\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u001c\b\u0002\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\n2\u001c\b\u0002\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\n2\u001c\b\u0002\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u001c\b\u0002\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\n2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001c2\b\b\u0002\u0010$\u001a\u00020%HÆ\u0001J\u0013\u0010i\u001a\u00020%2\b\u0010j\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010k\u001a\u00020lHÖ\u0001J\t\u0010m\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010)R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010)R%\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\n¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R%\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\n¢\u0006\b\n\u0000\u001a\u0004\b0\u0010/R%\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\n¢\u0006\b\n\u0000\u001a\u0004\b1\u0010/R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u0010+R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010+R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u0010+R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010+R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010+R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010+R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010+R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010+R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010+R%\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tj\u0004\u0018\u0001`\n¢\u0006\b\n\u0000\u001a\u0004\b;\u0010/R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b<\u0010+R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b=\u0010+R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b>\u0010+R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u0010+R\u0019\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001c¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bB\u0010+R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bC\u0010+R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bD\u0010+R\u0013\u0010 \u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bE\u0010+R\u0013\u0010!\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bF\u0010+R\u0013\u0010\"\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bG\u0010+R\u0019\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001c¢\u0006\b\n\u0000\u001a\u0004\bH\u0010AR\u0011\u0010$\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b$\u0010I¨\u0006o"}, d2 = {"Lorg/fdroid/database/AppMetadata;", "", "repoId", "", "packageName", "", "added", AppListActivity.SortClause.LAST_UPDATED, "name", "", "Lorg/fdroid/index/v2/LocalizedTextV2;", ErrorBundle.SUMMARY_ENTRY, "description", "localizedName", "localizedSummary", "webSite", "changelog", "license", "sourceCode", "issueTracker", "translation", "preferredSigner", "video", "authorName", "authorEmail", "authorWebSite", "authorPhone", "donate", "", "liberapayID", "liberapay", "openCollective", "bitcoin", "litecoin", "flattrID", "categories", "isCompatible", "", "<init>", "(JLjava/lang/String;JJLjava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Z)V", "getRepoId", "()J", "getPackageName", "()Ljava/lang/String;", "getAdded", "getLastUpdated", "getName", "()Ljava/util/Map;", "getSummary", "getDescription", "getLocalizedName", "getLocalizedSummary", "getWebSite", "getChangelog", "getLicense", "getSourceCode", "getIssueTracker", "getTranslation", "getPreferredSigner", "getVideo", "getAuthorName", "getAuthorEmail", "getAuthorWebSite", "getAuthorPhone", "getDonate", "()Ljava/util/List;", "getLiberapayID", "getLiberapay", "getOpenCollective", "getBitcoin", "getLitecoin", "getFlattrID", "getCategories", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AppMetadata {
    public static final String TABLE = "AppMetadata";
    private final long added;
    private final String authorEmail;
    private final String authorName;
    private final String authorPhone;
    private final String authorWebSite;
    private final String bitcoin;
    private final List<String> categories;
    private final String changelog;
    private final Map<String, String> description;
    private final List<String> donate;
    private final String flattrID;
    private final boolean isCompatible;
    private final String issueTracker;
    private final long lastUpdated;
    private final String liberapay;
    private final String liberapayID;
    private final String license;
    private final String litecoin;
    private final String localizedName;
    private final String localizedSummary;
    private final Map<String, String> name;
    private final String openCollective;
    private final String packageName;
    private final String preferredSigner;
    private final long repoId;
    private final String sourceCode;
    private final Map<String, String> summary;
    private final String translation;
    private final Map<String, String> video;
    private final String webSite;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getRepoId() {
        return this.repoId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getWebSite() {
        return this.webSite;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getChangelog() {
        return this.changelog;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getLicense() {
        return this.license;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getSourceCode() {
        return this.sourceCode;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getIssueTracker() {
        return this.issueTracker;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getTranslation() {
        return this.translation;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getPreferredSigner() {
        return this.preferredSigner;
    }

    public final Map<String, String> component17() {
        return this.video;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getAuthorName() {
        return this.authorName;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getAuthorEmail() {
        return this.authorEmail;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getAuthorWebSite() {
        return this.authorWebSite;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getAuthorPhone() {
        return this.authorPhone;
    }

    public final List<String> component22() {
        return this.donate;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getLiberapayID() {
        return this.liberapayID;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getLiberapay() {
        return this.liberapay;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final String getOpenCollective() {
        return this.openCollective;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final String getBitcoin() {
        return this.bitcoin;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final String getLitecoin() {
        return this.litecoin;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final String getFlattrID() {
        return this.flattrID;
    }

    public final List<String> component29() {
        return this.categories;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getAdded() {
        return this.added;
    }

    /* JADX INFO: renamed from: component30, reason: from getter */
    public final boolean getIsCompatible() {
        return this.isCompatible;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getLastUpdated() {
        return this.lastUpdated;
    }

    public final Map<String, String> component5() {
        return this.name;
    }

    public final Map<String, String> component6() {
        return this.summary;
    }

    public final Map<String, String> component7() {
        return this.description;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getLocalizedName() {
        return this.localizedName;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getLocalizedSummary() {
        return this.localizedSummary;
    }

    public final AppMetadata copy(long repoId, String packageName, long added, long lastUpdated, Map<String, String> name, Map<String, String> summary, Map<String, String> description, String localizedName, String localizedSummary, String webSite, String changelog, String license, String sourceCode, String issueTracker, String translation, String preferredSigner, Map<String, String> video, String authorName, String authorEmail, String authorWebSite, String authorPhone, List<String> donate, String liberapayID, String liberapay, String openCollective, String bitcoin, String litecoin, String flattrID, List<String> categories, boolean isCompatible) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return new AppMetadata(repoId, packageName, added, lastUpdated, name, summary, description, localizedName, localizedSummary, webSite, changelog, license, sourceCode, issueTracker, translation, preferredSigner, video, authorName, authorEmail, authorWebSite, authorPhone, donate, liberapayID, liberapay, openCollective, bitcoin, litecoin, flattrID, categories, isCompatible);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppMetadata)) {
            return false;
        }
        AppMetadata appMetadata = (AppMetadata) other;
        return this.repoId == appMetadata.repoId && Intrinsics.areEqual(this.packageName, appMetadata.packageName) && this.added == appMetadata.added && this.lastUpdated == appMetadata.lastUpdated && Intrinsics.areEqual(this.name, appMetadata.name) && Intrinsics.areEqual(this.summary, appMetadata.summary) && Intrinsics.areEqual(this.description, appMetadata.description) && Intrinsics.areEqual(this.localizedName, appMetadata.localizedName) && Intrinsics.areEqual(this.localizedSummary, appMetadata.localizedSummary) && Intrinsics.areEqual(this.webSite, appMetadata.webSite) && Intrinsics.areEqual(this.changelog, appMetadata.changelog) && Intrinsics.areEqual(this.license, appMetadata.license) && Intrinsics.areEqual(this.sourceCode, appMetadata.sourceCode) && Intrinsics.areEqual(this.issueTracker, appMetadata.issueTracker) && Intrinsics.areEqual(this.translation, appMetadata.translation) && Intrinsics.areEqual(this.preferredSigner, appMetadata.preferredSigner) && Intrinsics.areEqual(this.video, appMetadata.video) && Intrinsics.areEqual(this.authorName, appMetadata.authorName) && Intrinsics.areEqual(this.authorEmail, appMetadata.authorEmail) && Intrinsics.areEqual(this.authorWebSite, appMetadata.authorWebSite) && Intrinsics.areEqual(this.authorPhone, appMetadata.authorPhone) && Intrinsics.areEqual(this.donate, appMetadata.donate) && Intrinsics.areEqual(this.liberapayID, appMetadata.liberapayID) && Intrinsics.areEqual(this.liberapay, appMetadata.liberapay) && Intrinsics.areEqual(this.openCollective, appMetadata.openCollective) && Intrinsics.areEqual(this.bitcoin, appMetadata.bitcoin) && Intrinsics.areEqual(this.litecoin, appMetadata.litecoin) && Intrinsics.areEqual(this.flattrID, appMetadata.flattrID) && Intrinsics.areEqual(this.categories, appMetadata.categories) && this.isCompatible == appMetadata.isCompatible;
    }

    public int hashCode() {
        int iM = ((((((LongObjectMap$$ExternalSyntheticBackport0.m(this.repoId) * 31) + this.packageName.hashCode()) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.added)) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.lastUpdated)) * 31;
        Map<String, String> map = this.name;
        int iHashCode = (iM + (map == null ? 0 : map.hashCode())) * 31;
        Map<String, String> map2 = this.summary;
        int iHashCode2 = (iHashCode + (map2 == null ? 0 : map2.hashCode())) * 31;
        Map<String, String> map3 = this.description;
        int iHashCode3 = (iHashCode2 + (map3 == null ? 0 : map3.hashCode())) * 31;
        String str = this.localizedName;
        int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.localizedSummary;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.webSite;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.changelog;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.license;
        int iHashCode8 = (iHashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.sourceCode;
        int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.issueTracker;
        int iHashCode10 = (iHashCode9 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.translation;
        int iHashCode11 = (iHashCode10 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.preferredSigner;
        int iHashCode12 = (iHashCode11 + (str9 == null ? 0 : str9.hashCode())) * 31;
        Map<String, String> map4 = this.video;
        int iHashCode13 = (iHashCode12 + (map4 == null ? 0 : map4.hashCode())) * 31;
        String str10 = this.authorName;
        int iHashCode14 = (iHashCode13 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.authorEmail;
        int iHashCode15 = (iHashCode14 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.authorWebSite;
        int iHashCode16 = (iHashCode15 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.authorPhone;
        int iHashCode17 = (iHashCode16 + (str13 == null ? 0 : str13.hashCode())) * 31;
        List<String> list = this.donate;
        int iHashCode18 = (iHashCode17 + (list == null ? 0 : list.hashCode())) * 31;
        String str14 = this.liberapayID;
        int iHashCode19 = (iHashCode18 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.liberapay;
        int iHashCode20 = (iHashCode19 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.openCollective;
        int iHashCode21 = (iHashCode20 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.bitcoin;
        int iHashCode22 = (iHashCode21 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.litecoin;
        int iHashCode23 = (iHashCode22 + (str18 == null ? 0 : str18.hashCode())) * 31;
        String str19 = this.flattrID;
        int iHashCode24 = (iHashCode23 + (str19 == null ? 0 : str19.hashCode())) * 31;
        List<String> list2 = this.categories;
        return ((iHashCode24 + (list2 != null ? list2.hashCode() : 0)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.isCompatible);
    }

    public String toString() {
        return "AppMetadata(repoId=" + this.repoId + ", packageName=" + this.packageName + ", added=" + this.added + ", lastUpdated=" + this.lastUpdated + ", name=" + this.name + ", summary=" + this.summary + ", description=" + this.description + ", localizedName=" + this.localizedName + ", localizedSummary=" + this.localizedSummary + ", webSite=" + this.webSite + ", changelog=" + this.changelog + ", license=" + this.license + ", sourceCode=" + this.sourceCode + ", issueTracker=" + this.issueTracker + ", translation=" + this.translation + ", preferredSigner=" + this.preferredSigner + ", video=" + this.video + ", authorName=" + this.authorName + ", authorEmail=" + this.authorEmail + ", authorWebSite=" + this.authorWebSite + ", authorPhone=" + this.authorPhone + ", donate=" + this.donate + ", liberapayID=" + this.liberapayID + ", liberapay=" + this.liberapay + ", openCollective=" + this.openCollective + ", bitcoin=" + this.bitcoin + ", litecoin=" + this.litecoin + ", flattrID=" + this.flattrID + ", categories=" + this.categories + ", isCompatible=" + this.isCompatible + ")";
    }

    public AppMetadata(long j, String packageName, long j2, long j3, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Map<String, String> map4, String str10, String str11, String str12, String str13, List<String> list, String str14, String str15, String str16, String str17, String str18, String str19, List<String> list2, boolean z) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.repoId = j;
        this.packageName = packageName;
        this.added = j2;
        this.lastUpdated = j3;
        this.name = map;
        this.summary = map2;
        this.description = map3;
        this.localizedName = str;
        this.localizedSummary = str2;
        this.webSite = str3;
        this.changelog = str4;
        this.license = str5;
        this.sourceCode = str6;
        this.issueTracker = str7;
        this.translation = str8;
        this.preferredSigner = str9;
        this.video = map4;
        this.authorName = str10;
        this.authorEmail = str11;
        this.authorWebSite = str12;
        this.authorPhone = str13;
        this.donate = list;
        this.liberapayID = str14;
        this.liberapay = str15;
        this.openCollective = str16;
        this.bitcoin = str17;
        this.litecoin = str18;
        this.flattrID = str19;
        this.categories = list2;
        this.isCompatible = z;
    }

    public /* synthetic */ AppMetadata(long j, String str, long j2, long j3, Map map, Map map2, Map map3, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, Map map4, String str11, String str12, String str13, String str14, List list, String str15, String str16, String str17, String str18, String str19, String str20, List list2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, j2, j3, (i & 16) != 0 ? null : map, (i & 32) != 0 ? null : map2, (i & 64) != 0 ? null : map3, (i & 128) != 0 ? null : str2, (i & 256) != 0 ? null : str3, (i & 512) != 0 ? null : str4, (i & 1024) != 0 ? null : str5, (i & 2048) != 0 ? null : str6, (i & PKIFailureInfo.certConfirmed) != 0 ? null : str7, (i & 8192) != 0 ? null : str8, (i & 16384) != 0 ? null : str9, (32768 & i) != 0 ? null : str10, (65536 & i) != 0 ? null : map4, (131072 & i) != 0 ? null : str11, (262144 & i) != 0 ? null : str12, (524288 & i) != 0 ? null : str13, (1048576 & i) != 0 ? null : str14, (2097152 & i) != 0 ? null : list, (4194304 & i) != 0 ? null : str15, (8388608 & i) != 0 ? null : str16, (16777216 & i) != 0 ? null : str17, (33554432 & i) != 0 ? null : str18, (67108864 & i) != 0 ? null : str19, (134217728 & i) != 0 ? null : str20, (i & 268435456) != 0 ? null : list2, z);
    }

    public final long getRepoId() {
        return this.repoId;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final long getAdded() {
        return this.added;
    }

    public final long getLastUpdated() {
        return this.lastUpdated;
    }

    public final Map<String, String> getName() {
        return this.name;
    }

    public final Map<String, String> getSummary() {
        return this.summary;
    }

    public final Map<String, String> getDescription() {
        return this.description;
    }

    public final String getLocalizedName() {
        return this.localizedName;
    }

    public final String getLocalizedSummary() {
        return this.localizedSummary;
    }

    public final String getWebSite() {
        return this.webSite;
    }

    public final String getChangelog() {
        return this.changelog;
    }

    public final String getLicense() {
        return this.license;
    }

    public final String getSourceCode() {
        return this.sourceCode;
    }

    public final String getIssueTracker() {
        return this.issueTracker;
    }

    public final String getTranslation() {
        return this.translation;
    }

    public final String getPreferredSigner() {
        return this.preferredSigner;
    }

    public final Map<String, String> getVideo() {
        return this.video;
    }

    public final String getAuthorName() {
        return this.authorName;
    }

    public final String getAuthorEmail() {
        return this.authorEmail;
    }

    public final String getAuthorWebSite() {
        return this.authorWebSite;
    }

    public final String getAuthorPhone() {
        return this.authorPhone;
    }

    public final List<String> getDonate() {
        return this.donate;
    }

    public final String getLiberapayID() {
        return this.liberapayID;
    }

    public final String getLiberapay() {
        return this.liberapay;
    }

    public final String getOpenCollective() {
        return this.openCollective;
    }

    public final String getBitcoin() {
        return this.bitcoin;
    }

    public final String getLitecoin() {
        return this.litecoin;
    }

    public final String getFlattrID() {
        return this.flattrID;
    }

    public final List<String> getCategories() {
        return this.categories;
    }

    public final boolean isCompatible() {
        return this.isCompatible;
    }
}
