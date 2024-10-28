package com.koerich.cardconnect.dto.response;

import java.util.List;

public class WinnerResponse {

    private List<String> cards;
    private int score;
    private String playerName;

    public WinnerResponse(String playerName, List<String> cards, int score) {
        this.playerName = playerName;
        this.cards = cards;
        this.score = score;
    }

    public List<String> getCards() {
        return cards;
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
