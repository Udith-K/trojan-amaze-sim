package com.hannesdorfmann.adapterdelegates4;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AdapterDelegate {
    protected abstract boolean isForViewType(Object obj, int i);

    protected abstract void onBindViewHolder(Object obj, int i, RecyclerView.ViewHolder viewHolder, List list);

    protected abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup);

    protected boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        return false;
    }

    protected void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
    }

    protected void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
    }

    protected void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
    }
}
