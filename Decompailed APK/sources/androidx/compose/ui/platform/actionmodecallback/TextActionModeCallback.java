package androidx.compose.ui.platform.actionmodecallback;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import androidx.compose.ui.geometry.Rect;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextActionModeCallback.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextActionModeCallback {
    private final Function0 onActionModeDestroy;
    private Function0 onCopyRequested;
    private Function0 onCutRequested;
    private Function0 onPasteRequested;
    private Function0 onSelectAllRequested;
    private Rect rect;

    public TextActionModeCallback(Function0 function0, Rect rect, Function0 function02, Function0 function03, Function0 function04, Function0 function05) {
        this.onActionModeDestroy = function0;
        this.rect = rect;
        this.onCopyRequested = function02;
        this.onPasteRequested = function03;
        this.onCutRequested = function04;
        this.onSelectAllRequested = function05;
    }

    public /* synthetic */ TextActionModeCallback(Function0 function0, Rect rect, Function0 function02, Function0 function03, Function0 function04, Function0 function05, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : function0, (i & 2) != 0 ? Rect.Companion.getZero() : rect, (i & 4) != 0 ? null : function02, (i & 8) != 0 ? null : function03, (i & 16) != 0 ? null : function04, (i & 32) != 0 ? null : function05);
    }

    public final Rect getRect() {
        return this.rect;
    }

    public final void setRect(Rect rect) {
        this.rect = rect;
    }

    public final void setOnCopyRequested(Function0 function0) {
        this.onCopyRequested = function0;
    }

    public final void setOnPasteRequested(Function0 function0) {
        this.onPasteRequested = function0;
    }

    public final void setOnCutRequested(Function0 function0) {
        this.onCutRequested = function0;
    }

    public final void setOnSelectAllRequested(Function0 function0) {
        this.onSelectAllRequested = function0;
    }

    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException("onCreateActionMode requires a non-null menu");
        }
        if (actionMode == null) {
            throw new IllegalArgumentException("onCreateActionMode requires a non-null mode");
        }
        if (this.onCopyRequested != null) {
            addMenuItem$ui_release(menu, MenuItemOption.Copy);
        }
        if (this.onPasteRequested != null) {
            addMenuItem$ui_release(menu, MenuItemOption.Paste);
        }
        if (this.onCutRequested != null) {
            addMenuItem$ui_release(menu, MenuItemOption.Cut);
        }
        if (this.onSelectAllRequested == null) {
            return true;
        }
        addMenuItem$ui_release(menu, MenuItemOption.SelectAll);
        return true;
    }

    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        if (actionMode == null || menu == null) {
            return false;
        }
        updateMenuItems$ui_release(menu);
        return true;
    }

    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        Intrinsics.checkNotNull(menuItem);
        int itemId = menuItem.getItemId();
        if (itemId == MenuItemOption.Copy.getId()) {
            Function0 function0 = this.onCopyRequested;
            if (function0 != null) {
                function0.invoke();
            }
        } else if (itemId == MenuItemOption.Paste.getId()) {
            Function0 function02 = this.onPasteRequested;
            if (function02 != null) {
                function02.invoke();
            }
        } else if (itemId == MenuItemOption.Cut.getId()) {
            Function0 function03 = this.onCutRequested;
            if (function03 != null) {
                function03.invoke();
            }
        } else {
            if (itemId != MenuItemOption.SelectAll.getId()) {
                return false;
            }
            Function0 function04 = this.onSelectAllRequested;
            if (function04 != null) {
                function04.invoke();
            }
        }
        if (actionMode == null) {
            return true;
        }
        actionMode.finish();
        return true;
    }

    public final void onDestroyActionMode() {
        Function0 function0 = this.onActionModeDestroy;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final void updateMenuItems$ui_release(Menu menu) {
        addOrRemoveMenuItem(menu, MenuItemOption.Copy, this.onCopyRequested);
        addOrRemoveMenuItem(menu, MenuItemOption.Paste, this.onPasteRequested);
        addOrRemoveMenuItem(menu, MenuItemOption.Cut, this.onCutRequested);
        addOrRemoveMenuItem(menu, MenuItemOption.SelectAll, this.onSelectAllRequested);
    }

    public final void addMenuItem$ui_release(Menu menu, MenuItemOption menuItemOption) {
        menu.add(0, menuItemOption.getId(), menuItemOption.getOrder(), menuItemOption.getTitleResource()).setShowAsAction(1);
    }

    private final void addOrRemoveMenuItem(Menu menu, MenuItemOption menuItemOption, Function0 function0) {
        if (function0 != null && menu.findItem(menuItemOption.getId()) == null) {
            addMenuItem$ui_release(menu, menuItemOption);
        } else {
            if (function0 != null || menu.findItem(menuItemOption.getId()) == null) {
                return;
            }
            menu.removeItem(menuItemOption.getId());
        }
    }
}
