package com.example.bbreda.cardmanager.presentation.registration;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.presentation.termsofuse.TermsOfUseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class RegistrationFragment extends Fragment implements RegistrationContract.View {

    private static final int REQUEST_SCAN = 101;
    private static final int REQUEST_AUTOTEST = 200;

    @BindView(R.id.btn_read_card_from_cam)
    Button mButtonReadCardFromCam;

    @BindView(R.id.textView_cardDetails)
    TextView mTextViewCardDetails;

    @BindView(R.id.btn_account_request)
    Button mButtonAccountRequest;

    @BindView(R.id.et_name)
    EditText mName;

    @BindView(R.id.et_email)
    EditText mEmail;

    @BindView(R.id.checkbox_terms_of_use)
    CheckBox mCheckbox;

    private Boolean isChecked = false;
    private RegistrationContract.Presenter mPresenter;

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    @Override
    public void setPresenter(RegistrationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButtonAccountRequest.setOnClickListener(mButtonAccountRequestListener);
        mButtonReadCardFromCam.setOnClickListener(mButtonReadCardFromCamListener);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }


    @Override
    public void goToTermsOfUse() {
        startActivity(new Intent(getContext(), TermsOfUseActivity.class));
        getActivity().finish();
    }

    @Override
    public void readCardFromCam() {
        Intent intent = new Intent(getViewContext(), CardIOActivity.class)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true)
                .putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, true)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, true)
                .putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, "en")
                .putExtra(CardIOActivity.EXTRA_GUIDE_COLOR, Color.GREEN)
                .putExtra(CardIOActivity.EXTRA_RETURN_CARD_IMAGE, true);
        startActivityForResult(intent, REQUEST_SCAN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == 101 || requestCode == REQUEST_AUTOTEST)
                && data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {

            mTextViewCardDetails.setVisibility(View.VISIBLE);

            String resultDisplayStr;

            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
                resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";

                if (scanResult.isExpiryValid()) {
                    resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + " / " + scanResult.expiryYear + " / ";
                }

                if (scanResult.cvv != null) {
                    resultDisplayStr += "CVV has " + scanResult.cvv.length() + "digits. \n";
                }

                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code " + scanResult.postalCode + "\n";
                } else {
                    resultDisplayStr = "Scan was cancelled!!!";
                }

                mTextViewCardDetails.setText(resultDisplayStr);
                mTextViewCardDetails.setVisibility(View.INVISIBLE);
            }
        }
    }

    private View.OnClickListener mButtonReadCardFromCamListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onClickReadCardFromCam();
        }

    };

    private View.OnClickListener mButtonAccountRequestListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mCheckbox.isChecked()) {
                mPresenter.onClickRequestAccount();
            } else if (!mCheckbox.isChecked()) {
                Toast.makeText(getViewContext(), "Você deve aceitar os termos para efetivar a registração de seu usuário no sistema!", Toast.LENGTH_SHORT).show();
            }
        }

    };

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();

    }

}

