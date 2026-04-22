package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class MonthAdapter extends BaseAdapter {
    final CalendarConstraints calendarConstraints;
    CalendarStyle calendarStyle;
    final Month month;
    private Collection previouslySelectedDates;
    static final int MAXIMUM_WEEKS = UtcDates.getUtcCalendar().getMaximum(4);
    private static final int MAXIMUM_GRID_CELLS = (UtcDates.getUtcCalendar().getMaximum(5) + UtcDates.getUtcCalendar().getMaximum(7)) - 1;

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    MonthAdapter(Month month, DateSelector dateSelector, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator) {
        this.month = month;
        this.calendarConstraints = calendarConstraints;
        this.previouslySelectedDates = dateSelector.getSelectedDays();
    }

    @Override // android.widget.Adapter
    public Long getItem(int i) {
        if (i < firstPositionInMonth() || i > lastPositionInMonth()) {
            return null;
        }
        return Long.valueOf(this.month.getDay(positionToDay(i)));
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i / this.month.daysInWeek;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return MAXIMUM_GRID_CELLS;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0054  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.widget.TextView getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            r0 = 1
            android.content.Context r1 = r8.getContext()
            r5.initializeStyles(r1)
            r1 = r7
            android.widget.TextView r1 = (android.widget.TextView) r1
            r2 = 0
            if (r7 != 0) goto L1f
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r1 = com.google.android.material.R$layout.mtrl_calendar_day
            android.view.View r7 = r7.inflate(r1, r8, r2)
            r1 = r7
            android.widget.TextView r1 = (android.widget.TextView) r1
        L1f:
            int r7 = r5.firstPositionInMonth()
            int r7 = r6 - r7
            if (r7 < 0) goto L54
            com.google.android.material.datepicker.Month r8 = r5.month
            int r3 = r8.daysInMonth
            if (r7 < r3) goto L2e
            goto L54
        L2e:
            int r7 = r7 + r0
            r1.setTag(r8)
            android.content.res.Resources r8 = r1.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            java.util.Locale r8 = r8.locale
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r4 = new java.lang.Object[r0]
            r4[r2] = r3
            java.lang.String r3 = "%d"
            java.lang.String r8 = java.lang.String.format(r8, r3, r4)
            r1.setText(r8)
            r1.setVisibility(r2)
            r1.setEnabled(r0)
            goto L5d
        L54:
            r7 = 8
            r1.setVisibility(r7)
            r1.setEnabled(r2)
            r7 = -1
        L5d:
            java.lang.Long r6 = r5.getItem(r6)
            if (r6 != 0) goto L64
            return r1
        L64:
            long r2 = r6.longValue()
            r5.updateSelectedState(r1, r2, r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.MonthAdapter.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    public void updateSelectedStates(MaterialCalendarGridView materialCalendarGridView) {
        Iterator it = this.previouslySelectedDates.iterator();
        while (it.hasNext()) {
            updateSelectedStateForDate(materialCalendarGridView, ((Long) it.next()).longValue());
        }
    }

    private void updateSelectedStateForDate(MaterialCalendarGridView materialCalendarGridView, long j) {
        if (Month.create(j).equals(this.month)) {
            int dayOfMonth = this.month.getDayOfMonth(j);
            updateSelectedState((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter().dayToPosition(dayOfMonth) - materialCalendarGridView.getFirstVisiblePosition()), j, dayOfMonth);
        }
    }

    private void updateSelectedState(TextView textView, long j, int i) {
        CalendarItemStyle calendarItemStyle;
        if (textView == null) {
            return;
        }
        textView.setContentDescription(getDayContentDescription(textView.getContext(), j));
        if (this.calendarConstraints.getDateValidator().isValid(j)) {
            textView.setEnabled(true);
            boolean zIsSelected = isSelected(j);
            textView.setSelected(zIsSelected);
            if (zIsSelected) {
                calendarItemStyle = this.calendarStyle.selectedDay;
            } else if (isToday(j)) {
                calendarItemStyle = this.calendarStyle.todayDay;
            } else {
                calendarItemStyle = this.calendarStyle.day;
            }
        } else {
            textView.setEnabled(false);
            calendarItemStyle = this.calendarStyle.invalidDay;
        }
        calendarItemStyle.styleItem(textView);
    }

    private String getDayContentDescription(Context context, long j) {
        return DateStrings.getDayContentDescription(context, j, isToday(j), isStartOfRange(j), isEndOfRange(j));
    }

    private boolean isToday(long j) {
        return UtcDates.getTodayCalendar().getTimeInMillis() == j;
    }

    boolean isStartOfRange(long j) {
        throw null;
    }

    boolean isEndOfRange(long j) {
        throw null;
    }

    private boolean isSelected(long j) {
        throw null;
    }

    private void initializeStyles(Context context) {
        if (this.calendarStyle == null) {
            this.calendarStyle = new CalendarStyle(context);
        }
    }

    int firstPositionInMonth() {
        return this.month.daysFromStartOfWeekToFirstOfMonth(this.calendarConstraints.getFirstDayOfWeek());
    }

    int lastPositionInMonth() {
        return (firstPositionInMonth() + this.month.daysInMonth) - 1;
    }

    int positionToDay(int i) {
        return (i - firstPositionInMonth()) + 1;
    }

    int dayToPosition(int i) {
        return firstPositionInMonth() + (i - 1);
    }

    boolean withinMonth(int i) {
        return i >= firstPositionInMonth() && i <= lastPositionInMonth();
    }
}
