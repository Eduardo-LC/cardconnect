package com.koerich.cardconnect.dto.response;

import java.util.List;

public class StartGameResponse {
    private String name;
    private List<String> cards;

    public StartGameResponse(String name, List<String> cards) {
        this.name = name;
        this.cards = cards;
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
}
