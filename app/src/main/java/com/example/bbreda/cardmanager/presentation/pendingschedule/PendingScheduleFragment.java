package com.example.bbreda.cardmanager.presentation.pendingschedule;

public class PendingScheduleFragment {

    private PendingScheduleContract.Presenter mPresenter;

    public static PendingScheduleFragment newInstance() {
        return new PendingScheduleFragment();
    }

    public void setPresenter(PendingScheduleContract.Presenter presenter) { mPresenter = presenter; }


}
