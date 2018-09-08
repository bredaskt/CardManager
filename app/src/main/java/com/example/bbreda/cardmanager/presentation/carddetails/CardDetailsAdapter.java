package com.example.bbreda.cardmanager.presentation.carddetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.infrastructure.ImageHelper;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CardDetailsWrapper> mCardDetailsWrapperList;

    public CardDetailsAdapter(List<CardDetailsWrapper> cardDetailsWrapperList) {
        mCardDetailsWrapperList = cardDetailsWrapperList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;

        if (viewType == CardDetailsWrapper.Type.HEADER.ordinal()) {
            viewHolder = new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_extract_details, parent, false));
        } else {
            viewHolder = new ExtractViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_extract_details, parent, false));
        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (mCardDetailsWrapperList.get(position).getType() == CardDetailsWrapper.Type.HEADER.ordinal()) {
            try {
                ((HeaderViewHolder) holder).configItem(mCardDetailsWrapperList.get(position));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ((ExtractViewHolder) holder).configItem(mCardDetailsWrapperList.get(position));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return mCardDetailsWrapperList.get(position).getType();
    }

    @Override
    public int getItemCount() { return mCardDetailsWrapperList.size(); }

    public class ExtractViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_extract_value)
        TextView mExtractValue;

        @BindView(R.id.textview_extract_description)
        TextView mDescription;

        @BindView(R.id.textview_extract_purchase_data)
        TextView mPurchaseData;

        public ExtractViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }

        public void configItem(CardDetailsWrapper cardDetailsWrapper) {
            mExtractValue.setText(cardDetailsWrapper.getValue());
            mDescription.setText(cardDetailsWrapper.getSpent());
            mPurchaseData.setText(cardDetailsWrapper.getPurchaseData());
        }

    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_card_number)
        TextView mCardNumber;

        @BindView(R.id.imageView_card_flag)
        ImageView mCardFlag;


        public HeaderViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }

        public void configItem(CardDetailsWrapper cardDetailsWrapper) throws IOException {
            mCardNumber.setText(cardDetailsWrapper.getCardNumber());
            ImageHelper.loadImageByUrl(cardDetailsWrapper.getImageFlagUrl(), mCardFlag, R.drawable.ic_launcher_background);
        }
    }
}
