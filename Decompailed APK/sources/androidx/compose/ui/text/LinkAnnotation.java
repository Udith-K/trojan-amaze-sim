package androidx.compose.ui.text;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LinkAnnotation.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LinkAnnotation {
    public /* synthetic */ LinkAnnotation(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract LinkInteractionListener getLinkInteractionListener();

    public abstract TextLinkStyles getStyles();

    private LinkAnnotation() {
    }

    /* JADX INFO: compiled from: LinkAnnotation.kt */
    public static final class Url extends LinkAnnotation {
        private final TextLinkStyles styles;
        private final String url;

        @Override // androidx.compose.ui.text.LinkAnnotation
        public LinkInteractionListener getLinkInteractionListener() {
            return null;
        }

        public /* synthetic */ Url(String str, TextLinkStyles textLinkStyles, LinkInteractionListener linkInteractionListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : textLinkStyles, (i & 4) != 0 ? null : linkInteractionListener);
        }

        public final String getUrl() {
            return this.url;
        }

        @Override // androidx.compose.ui.text.LinkAnnotation
        public TextLinkStyles getStyles() {
            return this.styles;
        }

        public Url(String str, TextLinkStyles textLinkStyles, LinkInteractionListener linkInteractionListener) {
            super(null);
            this.url = str;
            this.styles = textLinkStyles;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Url)) {
                return false;
            }
            Url url = (Url) obj;
            if (!Intrinsics.areEqual(this.url, url.url) || !Intrinsics.areEqual(getStyles(), url.getStyles())) {
                return false;
            }
            getLinkInteractionListener();
            url.getLinkInteractionListener();
            return Intrinsics.areEqual((Object) null, (Object) null);
        }

        public int hashCode() {
            int iHashCode = this.url.hashCode() * 31;
            TextLinkStyles styles = getStyles();
            int iHashCode2 = (iHashCode + (styles != null ? styles.hashCode() : 0)) * 31;
            getLinkInteractionListener();
            return iHashCode2;
        }

        public String toString() {
            return "LinkAnnotation.Url(url=" + this.url + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }
    }

    /* JADX INFO: compiled from: LinkAnnotation.kt */
    public static final class Clickable extends LinkAnnotation {
        private final TextLinkStyles styles;
        private final String tag;

        @Override // androidx.compose.ui.text.LinkAnnotation
        public LinkInteractionListener getLinkInteractionListener() {
            return null;
        }

        public final String getTag() {
            return this.tag;
        }

        @Override // androidx.compose.ui.text.LinkAnnotation
        public TextLinkStyles getStyles() {
            return this.styles;
        }

        public Clickable(String str, TextLinkStyles textLinkStyles, LinkInteractionListener linkInteractionListener) {
            super(null);
            this.tag = str;
            this.styles = textLinkStyles;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Clickable)) {
                return false;
            }
            Clickable clickable = (Clickable) obj;
            if (!Intrinsics.areEqual(this.tag, clickable.tag) || !Intrinsics.areEqual(getStyles(), clickable.getStyles())) {
                return false;
            }
            getLinkInteractionListener();
            clickable.getLinkInteractionListener();
            return Intrinsics.areEqual((Object) null, (Object) null);
        }

        public int hashCode() {
            int iHashCode = this.tag.hashCode() * 31;
            TextLinkStyles styles = getStyles();
            int iHashCode2 = (iHashCode + (styles != null ? styles.hashCode() : 0)) * 31;
            getLinkInteractionListener();
            return iHashCode2;
        }

        public String toString() {
            return "LinkAnnotation.Clickable(tag=" + this.tag + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }
    }
}
