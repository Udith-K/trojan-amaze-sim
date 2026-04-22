package org.fdroid.fdroid.nearby.httpish;

import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Header {
    private static final Header[] VALID_HEADERS = {new ContentLengthHeader(), new ETagHeader()};

    protected abstract String getName();

    protected abstract void handle(FileDetails fileDetails, String str);

    public static void process(FileDetails fileDetails, String str, String str2) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        for (Header header : VALID_HEADERS) {
            if (header.getName().equals(lowerCase)) {
                header.handle(fileDetails, str2);
                return;
            }
        }
    }
}
