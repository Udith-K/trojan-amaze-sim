package org.apache.commons.io.filefilter;

import java.io.FileFilter;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractFileFilter implements FileFilter, FilenameFilter {
    public String toString() {
        return getClass().getSimpleName();
    }
}
