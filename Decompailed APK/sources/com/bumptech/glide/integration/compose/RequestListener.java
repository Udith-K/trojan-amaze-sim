package com.bumptech.glide.integration.compose;

import androidx.compose.ui.graphics.painter.Painter;

/* JADX INFO: compiled from: GlideModifier.kt */
/* JADX INFO: loaded from: classes.dex */
public interface RequestListener {
    void onStateChanged(Object obj, Painter painter, RequestState requestState);
}
