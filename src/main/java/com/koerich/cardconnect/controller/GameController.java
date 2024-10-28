package com.koerich.cardconnect.controller;

import com.koerich.cardconnect.dto.PlayerDto;
import com.koerich.cardconnect.dto.request.GameStartRequest;
import com.koerich.cardconnect.dto.response.DeckResponse;
import com.koerich.cardconnect.exception.BadRequestException;
import com.koerich.cardconnect.exception.NoContentException;
import com.koerich.cardconnect.exception.NotFoundException;
import com.koerich.cardconnect.service.DeckService;
import com.koerich.cardconnect.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final DeckService deckService;
    private final PlayerService playerService;

    public GameController(DeckService deckService, PlayerService playerService) {
        this.deckService = deckService;
        this.playerService = playerService;
    }

    @GetMapping("/start")
    public ResponseEntity<List<PlayerDto>> startGame(
            @ModelAttribute GameStartRequest request) throws BadRequestException {
        DeckResponse deckData = deckService.createAndShuffleDeck(request.getDeckCount());
        String deckId = deckData.getDeckId();
        List<PlayerDto> players = playerService.createPlayers(deckId, request.getPlayerCount(), request.getCardsPerHand());
        return ResponseEntity.ok(players);
    }


    @GetMapping("/winner")
    public ResponseEntity<List<PlayerDto>> getWinners(@RequestParam String gameId) throws NoContentException {
        List<PlayerDto> winners = playerService.getWinners(gameId);
        return ResponseEntity.ok(winners);
    }
}
