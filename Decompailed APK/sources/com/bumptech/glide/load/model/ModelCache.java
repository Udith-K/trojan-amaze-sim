package com.bumptech.glide.load.model;

import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
public class ModelCache {
    private final LruCache cache;

    public ModelCache(long j) {
        this.cache = new LruCache(j) { // from class: com.bumptech.glide.load.model.ModelCache.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.bumptech.glide.util.LruCache
            public void onItemEvicted(ModelKey modelKey, Object obj) {
                modelKey.release();
            }
        };
    }

    public Object get(Object obj, int i, int i2) {
        ModelKey modelKey = ModelKey.get(obj, i, i2);
        Object obj2 = this.cache.get(modelKey);
        modelKey.release();
        return obj2;
    }

    public void put(Object obj, int i, int i2, Object obj2) {
        this.cache.put(ModelKey.get(obj, i, i2), obj2);
    }

    static final class ModelKey {
        private static final Queue KEY_QUEUE = Util.createQueue(0);
        private int height;
        private Object model;
        private int width;

        static ModelKey get(Object obj, int i, int i2) {
            ModelKey modelKey;
            Queue queue = KEY_QUEUE;
            synchronized (queue) {
                modelKey = (ModelKey) queue.poll();
            }
            if (modelKey == null) {
                modelKey = new ModelKey();
            }
            modelKey.init(obj, i, i2);
            return modelKey;
        }

        private ModelKey() {
        }

        private void init(Object obj, int i, int i2) {
            this.model = obj;
            this.width = i;
            this.height = i2;
        }

        public void release() {
            Queue queue = KEY_QUEUE;
            synchronized (queue) {
                queue.offer(this);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ModelKey)) {
                return false;
            }
            ModelKey modelKey = (ModelKey) obj;
            return this.width == modelKey.width && this.height == modelKey.height && this.model.equals(modelKey.model);
        }

        public int hashCode() {
            return (((this.height * 31) + this.width) * 31) + this.model.hashCode();
        }
    }
}
