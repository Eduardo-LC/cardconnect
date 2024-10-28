package com.koerich.cardconnect.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerDtoTest {

    @Test
    void testConstructorAndGetters() {
        String name = "Player 1";
        List<String> cards = List.of("5H", "7D", "AS", "9C", "3S");
        String gameId = "game123";
        int score = 50;

        PlayerDto playerDto = new PlayerDto(name, cards, gameId, score);
        assertEquals(name, playerDto.getName());
        assertEquals(cards, playerDto.getCards());
        assertEquals(gameId, playerDto.getGameId());
        assertEquals(score, playerDto.getScore());
    }

    @Test
    void testSetters() {
        PlayerDto playerDto = new PlayerDto("", List.of(), "", 0);

        playerDto.setName("Player 2");
        playerDto.setCards(List.of("10H", "KD", "QS"));
        playerDto.setGameId("game456");
        playerDto.setScore(35);

        assertEquals("Player 2", playerDto.getName());
        assertEquals(List.of("10H", "KD", "QS"), playerDto.getCards());
        assertEquals("game456", playerDto.getGameId());
        assertEquals(35, playerDto.getScore());
    }
}
