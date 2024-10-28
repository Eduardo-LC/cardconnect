package com.koerich.cardconnect.dto.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeckResponseTest {

    @Test
    void testConstructorAndGetters() {
        String deckId = "123abc";
        boolean shuffled = true;
        int remaining = 52;
        boolean success = true;
        List<CardResponse> cards = List.of(new CardResponse("5H", "5", "HEARTS", "https://example.com/5H.png"));

        DeckResponse deckResponse = new DeckResponse(deckId, shuffled, remaining, success, cards);
        assertEquals(deckId, deckResponse.getDeckId());
        assertTrue(deckResponse.isShuffled());
        assertEquals(remaining, deckResponse.getRemaining());
        assertTrue(deckResponse.isSuccess());
        assertEquals(cards, deckResponse.getCards());
    }

    @Test
    void testSetters() {
        DeckResponse deckResponse = new DeckResponse();

        deckResponse.setDeckId("456def");
        deckResponse.setShuffled(false);
        deckResponse.setRemaining(50);
        deckResponse.setSuccess(false);
        List<CardResponse> newCards = List.of(new CardResponse("AH", "Ace", "SPADES", "https://example.com/AH.png"));
        deckResponse.setCards(newCards);

        assertEquals("456def", deckResponse.getDeckId());
        assertEquals(false, deckResponse.isShuffled());
        assertEquals(50, deckResponse.getRemaining());
        assertEquals(false, deckResponse.isSuccess());
        assertEquals(newCards, deckResponse.getCards());
    }
}
