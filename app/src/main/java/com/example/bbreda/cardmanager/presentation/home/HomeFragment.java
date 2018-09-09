package com.example.bbreda.cardmanager.presentation.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.data.model.Card;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements HomeContract.View {

    @BindView(R.id.textView_show_due_date)
    TextView mDueDate;

    @BindView(R.id.textView_show_limit_available)
    TextView mLimitAvailable;

    @BindView(R.id.textView_show_invoice_value)
    TextView mInvoiceValue;

    @BindView(R.id.imageView_card)
    ImageView mCardImage;

    private ProgressDialog progressDoalog;
    
    private HomeContract.Presenter mPresenter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void showCardData(Card card) {
        mCardImage.setImageResource(card.getDrawableCard());
        mDueDate.setText(card.getDueDate());
        mLimitAvailable.setText(card.getLimitAvailable());
        mInvoiceValue.setText(card.getInvoiceAmount());

    }

}
