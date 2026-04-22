package com.bumptech.glide.load;

import ch.qos.logback.core.CoreConstants;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class Option {
    private static final CacheKeyUpdater EMPTY_UPDATER = new CacheKeyUpdater() { // from class: com.bumptech.glide.load.Option.1
        @Override // com.bumptech.glide.load.Option.CacheKeyUpdater
        public void update(byte[] bArr, Object obj, MessageDigest messageDigest) {
        }
    };
    private final CacheKeyUpdater cacheKeyUpdater;
    private final Object defaultValue;
    private final String key;
    private volatile byte[] keyBytes;

    public interface CacheKeyUpdater {
        void update(byte[] bArr, Object obj, MessageDigest messageDigest);
    }

    public static Option memory(String str) {
        return new Option(str, null, emptyUpdater());
    }

    public static Option memory(String str, Object obj) {
        return new Option(str, obj, emptyUpdater());
    }

    public static Option disk(String str, Object obj, CacheKeyUpdater cacheKeyUpdater) {
        return new Option(str, obj, cacheKeyUpdater);
    }

    private Option(String str, Object obj, CacheKeyUpdater cacheKeyUpdater) {
        this.key = Preconditions.checkNotEmpty(str);
        this.defaultValue = obj;
        this.cacheKeyUpdater = (CacheKeyUpdater) Preconditions.checkNotNull(cacheKeyUpdater);
    }

    public Object getDefaultValue() {
        return this.defaultValue;
    }

    public void update(Object obj, MessageDigest messageDigest) {
        this.cacheKeyUpdater.update(getKeyBytes(), obj, messageDigest);
    }

    private byte[] getKeyBytes() {
        if (this.keyBytes == null) {
            this.keyBytes = this.key.getBytes(Key.CHARSET);
        }
        return this.keyBytes;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Option) {
            return this.key.equals(((Option) obj).key);
        }
        return false;
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    private static CacheKeyUpdater emptyUpdater() {
        return EMPTY_UPDATER;
    }

    public String toString() {
        return "Option{key='" + this.key + CoreConstants.SINGLE_QUOTE_CHAR + CoreConstants.CURLY_RIGHT;
    }
}
