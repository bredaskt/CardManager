package com.example.bbreda.cardmanager.presentation.mycards;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.data.model.Card;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCardsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Card> mCards;

    private MyCardsFragment.CardItemListener mMyCardsListener;

    public MyCardsAdapter(List<Card> cards, MyCardsFragment.CardItemListener myCardsListener) {
        mCards = cards;
        mMyCardsListener = myCardsListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mycard, parent, false);

        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((CardViewHolder) holder).configItem(mCards.get(position));

    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_card)
        ImageView mCardImage;

        public CardViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMyCardsListener.onItemClick(getAdapterPosition());
                }
            });

        }

        public void configItem(Card card) {
            mCardImage.setImageResource(card.getDrawableCard());

        }

    }

}
