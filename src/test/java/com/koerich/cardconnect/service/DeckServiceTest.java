package com.koerich.cardconnect.service;

import com.koerich.cardconnect.dto.response.CardResponse;
import com.koerich.cardconnect.dto.response.DeckResponse;
import com.koerich.cardconnect.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class DeckServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DeckService deckService;

    private final String DECK_ID = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAndShuffleDeck_ShouldReturnDeckData() throws BadRequestException {
        DeckResponse mockResponse = new DeckResponse();
        mockResponse.setShuffled(true);
        mockResponse.setRemaining(52);

        when(restTemplate.getForObject(anyString(), eq(DeckResponse.class))).thenReturn(mockResponse);

        DeckResponse result = deckService.createAndShuffleDeck(1);

        assertTrue(result.isShuffled());
        assertEquals(52, result.getRemaining());
    }

    @Test
    void drawCards_ShouldReturnCardData() {
        DeckResponse mockDrawResponse = new DeckResponse();
        mockDrawResponse.setSuccess(true);
        CardResponse card = new CardResponse();
        card.setCode("5H");
        mockDrawResponse.setCards(List.of(card));

        String url = "https://deckofcardsapi.com/api/deck/" + DECK_ID + "/draw/?count=2";

        when(restTemplate.getForObject(eq(url), eq(DeckResponse.class))).thenReturn(mockDrawResponse);

        DeckResponse result = deckService.drawCards(DECK_ID, 2);

        assertTrue(result.isSuccess());
        assertEquals("5H", result.getCards().get(0).getCode());
    }
}
