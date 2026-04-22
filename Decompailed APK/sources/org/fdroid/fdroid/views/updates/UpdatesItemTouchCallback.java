package org.fdroid.fdroid.views.updates;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import org.fdroid.fdroid.views.apps.AppListItemController;

/* JADX INFO: loaded from: classes2.dex */
public class UpdatesItemTouchCallback extends ItemTouchHelper.Callback {
    private final UpdatesAdapter adapter;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        return false;
    }

    UpdatesItemTouchCallback(UpdatesAdapter updatesAdapter) {
        this.adapter = updatesAdapter;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return ItemTouchHelper.Callback.makeMovementFlags(0, ((viewHolder instanceof AppListItemController) && ((AppListItemController) viewHolder).canDismiss()) ? 48 : 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        ((AppListItemController) viewHolder).onDismiss(this.adapter);
    }
}
