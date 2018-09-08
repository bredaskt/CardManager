package com.example.bbreda.cardmanager.presentation.schedulepayment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.presentation.pickers.DatePickerFragment;
import com.example.bbreda.cardmanager.presentation.pickers.TimePickerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchedulePaymentFragment extends Fragment implements SchedulePaymentContract.View {

    //EditText selectDate,selectTime;
    @BindView(R.id.editText_schedule_payment_time)
    EditText mTimeSchedule;

    @BindView(R.id.editText_schedule_payment_date)
    EditText mDateSchedule;

    @BindView(R.id.button_date_picker_schedule_payment)
    Button mButtonTimeSchedule;

    @BindView(R.id.button_time_picker_schedule_payment)
    Button mButtonDateSchedule;

    private int mYear, mMonth, mDay, mHour, mMinute;

    SchedulePaymentContract.Presenter mPresenter;

    public static SchedulePaymentFragment newInstance() {
        return new SchedulePaymentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_payment, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButtonDateSchedule.setOnClickListener(mButtonDatePickerListener);
        mButtonTimeSchedule.setOnClickListener(mButtonTimePickerListener);
    }

    private View.OnClickListener mButtonTimePickerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getActivity().getFragmentManager(), "timePicker");

        }
    };

    private View.OnClickListener mButtonDatePickerListener;

    {
        mButtonDatePickerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getActivity().getFragmentManager(), "timePicker");
            }
        };
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void setPresenter(SchedulePaymentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setTimePicker() {

        Toast.makeText(getViewContext(), "Fragment - setTimePicker", Toast.LENGTH_SHORT).show();

    }

}
