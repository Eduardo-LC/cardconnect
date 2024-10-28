package com.koerich.cardconnect.service;

import com.koerich.cardconnect.dto.PlayerDto;
import com.koerich.cardconnect.dto.response.CardResponse;
import com.koerich.cardconnect.dto.response.DeckResponse;
import com.koerich.cardconnect.exception.NotFoundException;
import com.koerich.cardconnect.model.Player;
import com.koerich.cardconnect.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private DeckService deckService;

    @InjectMocks
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPlayers_ShouldReturnPlayerList() throws NotFoundException {
        String deckId = UUID.randomUUID().toString();
        int playerCount = 2;
        int cardsPerHand = 5;

        DeckResponse mockDeckResponse = new DeckResponse();
        mockDeckResponse.setSuccess(true);
        List<CardResponse> mockCards = List.of(
                new CardResponse("5H", "5", "HEARTS", "image_url_5H"),
                new CardResponse("7D", "7", "DIAMONDS", "image_url_7D"),
                new CardResponse("AS", "ACE", "SPADES", "image_url_AS"),
                new CardResponse("9C", "9", "CLUBS", "image_url_9C"),
                new CardResponse("3S", "3", "SPADES", "image_url_3S")
        );

        mockDeckResponse.setCards(mockCards);

        when(deckService.drawCards(eq(deckId), eq(cardsPerHand))).thenReturn(mockDeckResponse);

        List<Player> playersToSave = new ArrayList<>();
        when(playerRepository.saveAll(anyList())).thenAnswer(invocation -> {
            playersToSave.addAll(invocation.getArgument(0));
            return playersToSave;
        });

        List<PlayerDto> players = playerService.createPlayers(deckId, playerCount, cardsPerHand);

        assertEquals(playerCount, players.size());
        assertEquals("Player 1", players.get(0).getName());
        assertEquals("Player 2", players.get(1).getName());
        assertNotNull(players.get(0).getGameId());
        assertNotNull(players.get(1).getGameId());
        assertEquals(5, players.get(0).getCards().size());
    }

    @Test
    void createPlayers_ShouldThrowNotFoundException_WhenNotEnoughCards() {
        String deckId = UUID.randomUUID().toString();

        // Mock para retornar falha ao distribuir as cartas
        DeckResponse mockDeckResponse = new DeckResponse();
        mockDeckResponse.setSuccess(false);
        when(deckService.drawCards(eq(deckId), anyInt())).thenReturn(mockDeckResponse);

        assertThrows(NotFoundException.class, () -> playerService.createPlayers(deckId, 2, 5));
    }

    @Test
    void getWinners_ShouldReturnPlayersWithHighestScore() throws NotFoundException {
        String gameId = UUID.randomUUID().toString();

        List<Player> mockPlayers = List.of(
                new Player("Player 1", List.of("5H", "7D", "AS", "9C", "3S"), gameId),
                new Player("Player 2", List.of("10H", "KD", "QS", "JC", "2S"), gameId)
        );
        when(playerRepository.findByGameId(eq(gameId))).thenReturn(mockPlayers);

        // Execução do método
        List<PlayerDto> winners = playerService.getWinners(gameId);

        assertEquals(1, winners.size());
        assertEquals("Player 2", winners.get(0).getName());
    }

    @Test
    void getWinners_ShouldThrowNotFoundException_WhenNoPlayersFound() {
        String gameId = UUID.randomUUID().toString();

        when(playerRepository.findByGameId(eq(gameId))).thenReturn(new ArrayList<>());

        assertThrows(NotFoundException.class, () -> playerService.getWinners(gameId));
    }
}
