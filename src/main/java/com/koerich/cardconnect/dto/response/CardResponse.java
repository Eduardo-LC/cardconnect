package com.koerich.cardconnect.dto.response;

public class CardResponse {
    private String code;
    private String image;
    private String suit;
    private String value;

    public CardResponse(String code, String value, String suit, String image) {
        this.code = code;
        this.value = value;
        this.suit = suit;
        this.image = image;
    }

    public CardResponse() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
