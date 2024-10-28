package com.koerich.cardconnect.service;

import com.koerich.cardconnect.dto.PlayerDto;
import com.koerich.cardconnect.dto.response.DeckResponse;
import com.koerich.cardconnect.dto.response.CardResponse;
import com.koerich.cardconnect.exception.NotFoundException;
import com.koerich.cardconnect.exception.UnauthorizedException;
import com.koerich.cardconnect.model.Player;
import com.koerich.cardconnect.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final DeckService deckService;

    public PlayerService(PlayerRepository playerRepository, DeckService deckService) {
        this.playerRepository = playerRepository;
        this.deckService = deckService;
    }

    @Transactional
    public List<PlayerDto> createPlayers(String deckId, int playerCount, int cardsPerHand) throws NotFoundException {
        String gameId = UUID.randomUUID().toString();
        List<PlayerDto> players = new ArrayList<>();

        for (int i = 0; i < playerCount; i++) {
            DeckResponse drawResult = deckService.drawCards(deckId, cardsPerHand);

            if (!drawResult.isSuccess() || drawResult.getCards() == null || drawResult.getCards().isEmpty()) {
                throw new NotFoundException("Not enough cards to distribute to players.");
            }

            List<String> cardCodes = drawResult.getCards().stream()
                    .map(CardResponse::getCode)
                    .collect(Collectors.toList());

            int score = calculateScore(cardCodes);

            PlayerDto playerDto = new PlayerDto("Player " + (i + 1), cardCodes, gameId, score);
            players.add(playerDto);
        }

        List<Player> playersToSave = players.stream()
                .map(dto -> new Player(dto.getName(), dto.getCards(), gameId))
                .collect(Collectors.toList());
        playerRepository.saveAll(playersToSave);

        return players;
    }


    @Transactional
    public List<PlayerDto> getWinners(String gameId) throws NotFoundException {
        List<Player> players = playerRepository.findByGameId(gameId);

        if (players.isEmpty()) {
            throw new NotFoundException("No players found for game ID: " + gameId);
        }

        List<PlayerDto> playerDtos = players.stream()
                .map(player -> {
                    int score = calculateScore(player.getCards());
                    return new PlayerDto(player.getName(), player.getCards(), player.getGameId(), score);
                })
                .collect(Collectors.toList());

        // Encontra a pontuação mais alta
        int highestScore = playerDtos.stream()
                .mapToInt(PlayerDto::getScore)
                .max()
                .orElse(0);

        return playerDtos.stream()
                .filter(player -> player.getScore() == highestScore)
                .collect(Collectors.toList());
    }

    private int calculateScore(List<String> cards) {
        return cards.stream()
                .mapToInt(this::getCardValue)
                .sum();
    }

    private int getCardValue(String card) {
        String value = card.substring(0, card.length() - 1);
        switch (value) {
            case "A":
                return 1;
            case "K":
                return 13;
            case "Q":
                return 12;
            case "J":
                return 11;
            default:
                return Integer.parseInt(value);
        }
    }
}
