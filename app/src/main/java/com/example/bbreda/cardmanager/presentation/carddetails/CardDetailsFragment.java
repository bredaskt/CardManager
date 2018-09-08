package com.example.bbreda.cardmanager.presentation.carddetails;

import android.os.Bundle;
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

    private CardDetailsContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_details, container, false);

        ButterKnife.bind(this, view);

        return view;

    }

    @Override
    public void setPresenter(CardDetailsContract.Presenter presenter) { mPresenter = presenter; }

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
    public void onStart() {
        super.onStart();
        mPresenter.start();

    }

}
