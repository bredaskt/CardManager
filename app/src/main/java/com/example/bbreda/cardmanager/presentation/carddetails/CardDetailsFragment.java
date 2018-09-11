package com.example.bbreda.cardmanager.presentation.carddetails;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bbreda.cardmanager.R;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CardDetailsFragment extends Fragment implements  CardDetailsContract.View {

    @BindView(R.id.recycler_view_card_details)
    RecyclerView mCardDetails;

    private ProgressDialog mProgress;
    private CardDetailsContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_details, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setPresenter(CardDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    public static CardDetailsFragment newInstance() {
        return new CardDetailsFragment();
    }

    @Override
    public void showCardDetails(List<CardDetailsWrapper> cardDetailsWrapperList) {
        CardDetailsAdapter mCardDetailsAdapter = new CardDetailsAdapter(cardDetailsWrapperList);
        mCardDetails.setAdapter(mCardDetailsAdapter);
        mCardDetails.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void showLoading() {
        mProgress = new ProgressDialog(getViewContext());
        mProgress.setMax(100);
        mProgress.setMessage("Buscando os dados de extrato de seu cartão...");
        mProgress.setTitle("CardManager");
        mProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgress.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
            try {
                while (mProgress.getProgress() <= mProgress.getMax()) {
                    Thread.sleep(200);
                    handle.sendMessage(handle.obtainMessage());
                    if (mProgress.getProgress() == mProgress.getMax()) {
                        hideLoading();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        }).start();
    }

    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mProgress.incrementProgressBy(15);
        };
    };

    @Override
    public void hideLoading() {
        mProgress.dismiss();
    }

    @Override
    public void onStart() {
        showLoading();
        super.onStart();
        mPresenter.start();
    }
}
