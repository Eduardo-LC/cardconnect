package com.koerich.cardconnect.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameStartRequestTest {

    @Test
    void testConstructorAndGetters() {
        int deckCount = 2;
        int playerCount = 4;
        int cardsPerHand = 5;

        GameStartRequest request = new GameStartRequest(deckCount, cardsPerHand, playerCount);
        assertEquals(deckCount, request.getDeckCount());
        assertEquals(playerCount, request.getPlayerCount());
        assertEquals(cardsPerHand, request.getCardsPerHand());
    }

    @Test
    void testSetters() {
        GameStartRequest request = new GameStartRequest(1, 2, 3);

        request.setDeckCount(3);
        request.setPlayerCount(5);
        request.setCardsPerHand(7);

        assertEquals(3, request.getDeckCount());
        assertEquals(5, request.getPlayerCount());
        assertEquals(7, request.getCardsPerHand());
    }
}
