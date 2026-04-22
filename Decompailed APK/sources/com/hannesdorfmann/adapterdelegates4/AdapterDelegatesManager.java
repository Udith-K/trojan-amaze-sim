package com.hannesdorfmann.adapterdelegates4;

import android.view.ViewGroup;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AdapterDelegatesManager {
    private static final List PAYLOADS_EMPTY_LIST = Collections.emptyList();
    protected SparseArrayCompat delegates = new SparseArrayCompat();
    protected AdapterDelegate fallbackDelegate;

    public AdapterDelegatesManager addDelegate(AdapterDelegate adapterDelegate) {
        int size = this.delegates.size();
        while (this.delegates.get(size) != null) {
            size++;
            if (size == 2147483646) {
                throw new IllegalArgumentException("Oops, we are very close to Integer.MAX_VALUE. It seems that there are no more free and unused view type integers left to add another AdapterDelegate.");
            }
        }
        return addDelegate(size, false, adapterDelegate);
    }

    public AdapterDelegatesManager addDelegate(int i, boolean z, AdapterDelegate adapterDelegate) {
        if (adapterDelegate == null) {
            throw new NullPointerException("AdapterDelegate is null!");
        }
        if (i == 2147483646) {
            throw new IllegalArgumentException("The view type = 2147483646 is reserved for fallback adapter delegate (see setFallbackDelegate() ). Please use another view type.");
        }
        if (!z && this.delegates.get(i) != null) {
            throw new IllegalArgumentException("An AdapterDelegate is already registered for the viewType = " + i + ". Already registered AdapterDelegate is " + this.delegates.get(i));
        }
        this.delegates.put(i, adapterDelegate);
        return this;
    }

    public int getItemViewType(Object obj, int i) {
        if (obj == null) {
            throw new NullPointerException("Items datasource is null!");
        }
        int size = this.delegates.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((AdapterDelegate) this.delegates.valueAt(i2)).isForViewType(obj, i)) {
                return this.delegates.keyAt(i2);
            }
        }
        if (this.fallbackDelegate != null) {
            return 2147483646;
        }
        throw new NullPointerException(obj instanceof List ? "No AdapterDelegate added that matches item=" + ((List) obj).get(i).toString() + " at position=" + i + " in data source" : "No AdapterDelegate added for item at position=" + i + ". items=" + obj);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AdapterDelegate delegateForViewType = getDelegateForViewType(i);
        if (delegateForViewType == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + i);
        }
        RecyclerView.ViewHolder viewHolderOnCreateViewHolder = delegateForViewType.onCreateViewHolder(viewGroup);
        if (viewHolderOnCreateViewHolder != null) {
            return viewHolderOnCreateViewHolder;
        }
        throw new NullPointerException("ViewHolder returned from AdapterDelegate " + delegateForViewType + " for ViewType =" + i + " is null!");
    }

    public void onBindViewHolder(Object obj, int i, RecyclerView.ViewHolder viewHolder, List list) {
        AdapterDelegate delegateForViewType = getDelegateForViewType(viewHolder.getItemViewType());
        if (delegateForViewType == null) {
            throw new NullPointerException("No delegate found for item at position = " + i + " for viewType = " + viewHolder.getItemViewType());
        }
        if (list == null) {
            list = PAYLOADS_EMPTY_LIST;
        }
        delegateForViewType.onBindViewHolder(obj, i, viewHolder, list);
    }

    public void onBindViewHolder(Object obj, int i, RecyclerView.ViewHolder viewHolder) {
        onBindViewHolder(obj, i, viewHolder, PAYLOADS_EMPTY_LIST);
    }

    public AdapterDelegate getDelegateForViewType(int i) {
        return (AdapterDelegate) this.delegates.get(i, this.fallbackDelegate);
    }
}
