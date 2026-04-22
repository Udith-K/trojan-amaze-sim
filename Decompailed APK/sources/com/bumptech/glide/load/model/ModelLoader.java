package com.bumptech.glide.load.model;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.util.Preconditions;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ModelLoader {
    LoadData buildLoadData(Object obj, int i, int i2, Options options);

    boolean handles(Object obj);

    public static class LoadData {
        public final List alternateKeys;
        public final DataFetcher fetcher;
        public final Key sourceKey;

        public LoadData(Key key, DataFetcher dataFetcher) {
            this(key, Collections.emptyList(), dataFetcher);
        }

        public LoadData(Key key, List list, DataFetcher dataFetcher) {
            this.sourceKey = (Key) Preconditions.checkNotNull(key);
            this.alternateKeys = (List) Preconditions.checkNotNull(list);
            this.fetcher = (DataFetcher) Preconditions.checkNotNull(dataFetcher);
        }
    }
}
