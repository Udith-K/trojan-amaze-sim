package com.bumptech.glide.request.transition;

import com.bumptech.glide.load.DataSource;

/* JADX INFO: loaded from: classes.dex */
public class DrawableCrossFadeFactory implements TransitionFactory {
    private final int duration;
    private final boolean isCrossFadeEnabled;
    private DrawableCrossFadeTransition resourceTransition;

    protected DrawableCrossFadeFactory(int i, boolean z) {
        this.duration = i;
        this.isCrossFadeEnabled = z;
    }

    @Override // com.bumptech.glide.request.transition.TransitionFactory
    public Transition build(DataSource dataSource, boolean z) {
        if (dataSource == DataSource.MEMORY_CACHE) {
            return NoTransition.get();
        }
        return getResourceTransition();
    }

    private Transition getResourceTransition() {
        if (this.resourceTransition == null) {
            this.resourceTransition = new DrawableCrossFadeTransition(this.duration, this.isCrossFadeEnabled);
        }
        return this.resourceTransition;
    }

    public static class Builder {
        private final int durationMillis;
        private boolean isCrossFadeEnabled;

        public Builder() {
            this(300);
        }

        public Builder(int i) {
            this.durationMillis = i;
        }

        public DrawableCrossFadeFactory build() {
            return new DrawableCrossFadeFactory(this.durationMillis, this.isCrossFadeEnabled);
        }
    }
}
