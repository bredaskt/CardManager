package com.example.bbreda.cardmanager.data.model;

import com.example.bbreda.cardmanager.R;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Card {

    @SerializedName("card_number")
    private String cardNumber;

    @SerializedName("image_name")
    private String imageName;

    @SerializedName("image_card")
    private String imageCard;

    @SerializedName("color_card")
    private String colorCard;

    @SerializedName("due_date")
    private String dueDate;

    @SerializedName("image_flag")
    private String imageFlag;

    @SerializedName("limit_available")
    private String limitAvailable;

    @SerializedName("invoice_amount")
    private String invoiceAmount;

    @SerializedName("extract")
    private List<Extract> mExtract;

    public List<Extract> getmExtract() {
        return mExtract;
    }

    public void setmExtract(List<Extract> mExtract) {
        mExtract = mExtract;
    }

    public String getImageFlag() { return imageFlag; }

    public void setImageFlag(String imageFlag) { imageFlag = imageFlag; }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        cardNumber = cardNumber;
    }

    public String getImageName() { return imageName; }

    public void setImageName(String imageName) {
        imageName = imageName;
    }

    public String getImageCard() { return imageCard; }

    public int getDrawableCard() {

        int card;

        switch (colorCard) {
            case "red":
                card =  R.drawable.img_cartao_vermelho;
                break;

            case "green":
                card = R.drawable.img_cartao_verde;
                break;

            case "purple":
                card =  R.drawable.img_cartao_roxo;
                break;

            default:
                card = R.drawable.img_cartao_roxo;
        }
        return card;

    }

    public void setImageCard(String imageCard) {
        imageCard = imageCard;
    }

    public String getColorCard() {
        return colorCard;
    }

    public void setColorCard(String colorCard) {
        colorCard = colorCard;
    }

    public String getDueDate() { return dueDate; }

    public void setDueDate(String dueDate) {
        dueDate = dueDate;
    }

    public String getLimitAvailable() {
        return limitAvailable;
    }

    public void setLimitAvailable(String limitAvailable) {
        limitAvailable = limitAvailable;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        invoiceAmount = invoiceAmount;
    }

}
