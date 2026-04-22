package com.bumptech.glide.request.transition;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
public class BitmapTransitionFactory extends BitmapContainerTransitionFactory {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.request.transition.BitmapContainerTransitionFactory
    public Bitmap getBitmap(Bitmap bitmap) {
        return bitmap;
    }

    public BitmapTransitionFactory(TransitionFactory transitionFactory) {
        super(transitionFactory);
    }
}
