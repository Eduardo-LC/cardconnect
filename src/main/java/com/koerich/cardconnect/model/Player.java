package com.koerich.cardconnect.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private String gameId;

    @ElementCollection
    @CollectionTable(name = "player_cards", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "card")
    private List<String> cards;

    public Player() {
    }

    public Player(String name, List<String> cards, String gameId) {
        this.name = name;
        this.cards = cards;
        this.gameId = gameId;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<String> getCards() {
        return cards;
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
    }
}
