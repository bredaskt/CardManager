package com.example.bbreda.cardmanager.presentation.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bbreda.cardmanager.R;

import butterknife.ButterKnife;

public class AboutFragment extends Fragment implements AboutContract.View {

    private AboutContract.Presenter mPresenter;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void goToAboutScreen() {
        startActivity(new Intent(getContext(), AboutActivity.class));
        getActivity().finish();

        // TODO
        Toast.makeText(getViewContext(), "Implementar a abertura da tela de sobre!!!!!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setPresenter(AboutContract.Presenter presenter) { mPresenter = presenter; }

    @Override
    public Context getViewContext() {
        return getContext();
    }



}
