package com.koerich.cardconnect.dto.request;

public class GameStartRequest {

    private int deckCount;
    private int playerCount;
    private int cardsPerHand;

    public GameStartRequest(int deckCount, int cardsPerHand, int playerCount) {
        this.deckCount = deckCount;
        this.cardsPerHand = cardsPerHand;
        this.playerCount = playerCount;
    }

    public int getCardsPerHand() {
        return cardsPerHand;
    }

    public void setCardsPerHand(int cardsPerHand) {
        this.cardsPerHand = cardsPerHand;
    }

    public int getDeckCount() {
        return deckCount;
    }

    public void setDeckCount(int deckCount) {
        this.deckCount = deckCount;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
}
