package com.koerich.cardconnect.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DeckResponse {

    @JsonProperty("deck_id")
    private String deckId;
    private boolean shuffled;
    private int remaining;
    private boolean success;
    private List<CardResponse> cards;

    public DeckResponse(String deckId, boolean shuffled, int remaining, boolean success, List<CardResponse> cards) {
        this.deckId = deckId;
        this.shuffled = shuffled;
        this.remaining = remaining;
        this.success = success;
        this.cards = cards;
    }

    public DeckResponse() {
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<CardResponse> getCards() {
        return cards;
    }

    public void setCards(List<CardResponse> cards) {
        this.cards = cards;
    }
}
