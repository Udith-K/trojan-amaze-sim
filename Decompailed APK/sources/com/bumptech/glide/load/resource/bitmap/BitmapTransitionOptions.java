package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.BitmapTransitionFactory;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.TransitionFactory;

/* JADX INFO: loaded from: classes.dex */
public final class BitmapTransitionOptions extends TransitionOptions {
    public static BitmapTransitionOptions withCrossFade() {
        return new BitmapTransitionOptions().crossFade();
    }

    public BitmapTransitionOptions crossFade() {
        return crossFade(new DrawableCrossFadeFactory.Builder());
    }

    public BitmapTransitionOptions transitionUsing(TransitionFactory transitionFactory) {
        return (BitmapTransitionOptions) transition(new BitmapTransitionFactory(transitionFactory));
    }

    public BitmapTransitionOptions crossFade(DrawableCrossFadeFactory.Builder builder) {
        return transitionUsing(builder.build());
    }

    @Override // com.bumptech.glide.TransitionOptions
    public boolean equals(Object obj) {
        return (obj instanceof BitmapTransitionOptions) && super.equals(obj);
    }

    @Override // com.bumptech.glide.TransitionOptions
    public int hashCode() {
        return super.hashCode();
    }
}
