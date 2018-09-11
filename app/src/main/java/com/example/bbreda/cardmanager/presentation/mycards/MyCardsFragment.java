package com.example.bbreda.cardmanager.presentation.mycards;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.presentation.home.HomeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCardsFragment extends Fragment implements MyCardsContract.View {

    @BindView(R.id.recycler_view_cards)
    RecyclerView mMyCards;

    ProgressDialog mProgress;

    private MyCardsContract.Presenter mPresenter;

    public static MyCardsFragment newInstance() {
        return new MyCardsFragment();
    }

    public void setPresenter(MyCardsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private CardItemListener mMyCardsListener = new CardItemListener() {
        @Override
        public void onItemClick(int cardPosition) {
           mPresenter.onCardItemClick(cardPosition);
        }
    } ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycards, container, false);

        ButterKnife.bind(this, view);

        return  view;
    }

    @Override
    public void showCards(List<Card> cardList) {
        MyCardsAdapter myCardsAdapter = new MyCardsAdapter(cardList, mMyCardsListener);
        mMyCards.setAdapter(myCardsAdapter);
        mMyCards.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void goToHomeScreen() {
        startActivity(new Intent(getContext(), HomeActivity.class));
        getActivity().finish();
    }

    @Override
    public void showLoading() {
        mProgress = new ProgressDialog(getViewContext());
        mProgress.setMax(100);
        mProgress.setMessage("Verificando seus cartões, aguarde por favor...");
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
    public Context getViewContext() {
        return getContext();
    }

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

    public interface CardItemListener {
        void onItemClick(int cardPosition);
    }

}


