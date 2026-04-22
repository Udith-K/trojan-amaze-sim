package org.fdroid.fdroid.privileged.views;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
class TabsAdapter extends PagerAdapter implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
    private final Context context;
    private TabHost.OnTabChangeListener onTabChangeListener;
    private final TabHost tabHost;
    private final List<View> tabs = new ArrayList();
    private final Rect tempRect = new Rect();
    private final ViewPager viewPager;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    static class DummyTabFactory implements TabHost.TabContentFactory {
        private final Context context;

        DummyTabFactory(Context context) {
            this.context = context;
        }

        @Override // android.widget.TabHost.TabContentFactory
        public View createTabContent(String str) {
            View view = new View(this.context);
            view.setMinimumWidth(0);
            view.setMinimumHeight(0);
            return view;
        }
    }

    TabsAdapter(AppCompatActivity appCompatActivity, TabHost tabHost, ViewPager viewPager) {
        this.context = appCompatActivity;
        this.tabHost = tabHost;
        this.viewPager = viewPager;
        tabHost.setOnTabChangedListener(this);
        viewPager.setAdapter(this);
        viewPager.setOnPageChangeListener(this);
    }

    void addTab(TabHost.TabSpec tabSpec, View view) {
        tabSpec.setContent(new DummyTabFactory(this.context));
        this.tabs.add(view);
        this.tabHost.addTab(tabSpec);
        notifyDataSetChanged();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.tabs.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = this.tabs.get(i);
        viewGroup.addView(view);
        return view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.onTabChangeListener = onTabChangeListener;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    public void onTabChanged(String str) {
        this.viewPager.setCurrentItem(this.tabHost.getCurrentTab());
        TabHost.OnTabChangeListener onTabChangeListener = this.onTabChangeListener;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        TabWidget tabWidget = this.tabHost.getTabWidget();
        int descendantFocusability = tabWidget.getDescendantFocusability();
        tabWidget.setDescendantFocusability(393216);
        this.tabHost.setCurrentTab(i);
        tabWidget.setDescendantFocusability(descendantFocusability);
        View childTabViewAt = tabWidget.getChildTabViewAt(i);
        this.tempRect.set(childTabViewAt.getLeft(), childTabViewAt.getTop(), childTabViewAt.getRight(), childTabViewAt.getBottom());
        tabWidget.requestRectangleOnScreen(this.tempRect, false);
        View view = this.tabs.get(i);
        if (view instanceof CaffeinatedScrollView) {
            ((CaffeinatedScrollView) view).awakenScrollBars();
        }
    }
}
