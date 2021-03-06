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
import android.widget.Toast;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.presentation.baselogged.BaseLoggedActivity;
import com.example.bbreda.cardmanager.presentation.home.HomeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCardsFragment extends Fragment implements MyCardsContract.View {

    @BindView(R.id.recycler_view_cards)
    RecyclerView mMyCards;

    ProgressDialog progressDoalog;

    private MyCardsContract.Presenter mPresenter;

    public static MyCardsFragment newInstance() { return new MyCardsFragment(); }

    public void setPresenter(MyCardsContract.Presenter presenter) { mPresenter = presenter; }

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
    public Context getViewContext() { return getContext(); }

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
        progressDoalog = new ProgressDialog(getViewContext());
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Verificando seus cartões, aguarde por favor...");
        progressDoalog.setTitle("CardManager");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDoalog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDoalog.getProgress() <= progressDoalog.getMax()) {
                        Thread.sleep(200);
                        handle.sendMessage(handle.obtainMessage());
                        if (progressDoalog.getProgress() == progressDoalog.getMax()) {
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
            progressDoalog.incrementProgressBy(15);
        };
    };

    @Override
    public void hideLoading() {
        progressDoalog.dismiss();

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


