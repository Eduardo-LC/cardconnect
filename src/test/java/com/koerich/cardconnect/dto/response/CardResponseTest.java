package com.koerich.cardconnect.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardResponseTest {

    @Test
    void testConstructorAndGetters() {
        String code = "5H";
        String value = "5";
        String suit = "HEARTS";
        String image = "https://example.com/card.png";

        CardResponse card = new CardResponse(code, value, suit, image);
        assertEquals(code, card.getCode());
        assertEquals(value, card.getValue());
        assertEquals(suit, card.getSuit());
        assertEquals(image, card.getImage());
    }

    @Test
    void testSetters() {
        CardResponse card = new CardResponse();

        card.setCode("AH");
        card.setValue("Ace");
        card.setSuit("SPADES");
        card.setImage("https://example.com/ace_spades.png");

        assertEquals("AH", card.getCode());
        assertEquals("Ace", card.getValue());
        assertEquals("SPADES", card.getSuit());
        assertEquals("https://example.com/ace_spades.png", card.getImage());
    }
}
