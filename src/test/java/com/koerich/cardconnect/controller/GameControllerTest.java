package com.koerich.cardconnect.controller;

import com.koerich.cardconnect.dto.PlayerDto;
import com.koerich.cardconnect.dto.request.GameStartRequest;
import com.koerich.cardconnect.dto.response.DeckResponse;
import com.koerich.cardconnect.exception.BadRequestException;
import com.koerich.cardconnect.exception.NoContentException;
import com.koerich.cardconnect.exception.NotFoundException;
import com.koerich.cardconnect.exception.UnauthorizedException;
import com.koerich.cardconnect.service.DeckService;
import com.koerich.cardconnect.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class GameControllerTest {

    @Mock
    private DeckService deckService;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private GameController gameController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void startGame_ShouldReturnListOfPlayers() throws NotFoundException, BadRequestException {
        String deckId = UUID.randomUUID().toString();

        DeckResponse mockDeckResponse = new DeckResponse();
        mockDeckResponse.setDeckId(deckId);
        mockDeckResponse.setShuffled(true);
        mockDeckResponse.setRemaining(52);

        when(deckService.createAndShuffleDeck(anyInt())).thenReturn(mockDeckResponse);

        List<PlayerDto> mockPlayers = List.of(
                new PlayerDto("Player 1", List.of("5D", "7S", "AC", "JD", "2H"), deckId, 32),
                new PlayerDto("Player 2", List.of("8C", "KS", "QC", "JC", "AH"), deckId, 35)
        );

        when(playerService.createPlayers(eq(deckId), anyInt(), anyInt())).thenReturn(mockPlayers);

        GameStartRequest request = new GameStartRequest(1, 2, 5);

        ResponseEntity<List<PlayerDto>> response = gameController.startGame(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        assertEquals("Player 1", response.getBody().get(0).getName());
        assertEquals(deckId, response.getBody().get(0).getGameId());
    }

    @Test
    void getWinners_ShouldReturnListOfWinners() throws NoContentException {
        String gameId = UUID.randomUUID().toString();
        List<PlayerDto> mockWinners = List.of(
                new PlayerDto("Player 2", List.of("8C", "KS", "QC", "JC", "AH"), gameId, 35)
        );

        when(playerService.getWinners(eq(gameId))).thenReturn(mockWinners);

        ResponseEntity<List<PlayerDto>> response = gameController.getWinners(gameId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Player 2", response.getBody().get(0).getName());
        assertEquals(gameId, response.getBody().get(0).getGameId());
    }
}
