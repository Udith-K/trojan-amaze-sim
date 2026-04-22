package com.bumptech.glide.request.transition;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.Transition;

/* JADX INFO: loaded from: classes.dex */
public abstract class BitmapContainerTransitionFactory implements TransitionFactory {
    private final TransitionFactory realFactory;

    protected abstract Bitmap getBitmap(Object obj);

    public BitmapContainerTransitionFactory(TransitionFactory transitionFactory) {
        this.realFactory = transitionFactory;
    }

    @Override // com.bumptech.glide.request.transition.TransitionFactory
    public Transition build(DataSource dataSource, boolean z) {
        return new BitmapGlideAnimation(this.realFactory.build(dataSource, z));
    }

    private final class BitmapGlideAnimation implements Transition {
        private final Transition transition;

        BitmapGlideAnimation(Transition transition) {
            this.transition = transition;
        }

        @Override // com.bumptech.glide.request.transition.Transition
        public boolean transition(Object obj, Transition.ViewAdapter viewAdapter) {
            return this.transition.transition(new BitmapDrawable(viewAdapter.getView().getResources(), BitmapContainerTransitionFactory.this.getBitmap(obj)), viewAdapter);
        }
    }
}
