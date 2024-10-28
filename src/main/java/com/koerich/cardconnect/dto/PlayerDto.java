package com.koerich.cardconnect.dto;

import java.util.List;

public class PlayerDto {
    private String name;
    private List<String> cards;
    private String gameId;
    private int score;

    public PlayerDto(String name, List<String> cards, String gameId, int score) {
        this.name = name;
        this.cards = cards;
        this.gameId = gameId;
        this.score = score;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCards() {
        return cards;
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
