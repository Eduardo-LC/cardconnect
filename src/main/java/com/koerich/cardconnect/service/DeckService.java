package com.koerich.cardconnect.service;

import com.koerich.cardconnect.dto.response.DeckResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DeckService {

    private static final String BASE_URL = "https://deckofcardsapi.com/api/deck";
    private RestTemplate restTemplate;

    public DeckService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DeckResponse createAndShuffleDeck(int deckCount) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/new/shuffle/")
                .queryParam("deck_count", deckCount)
                .toUriString();
        return restTemplate.getForObject(url, DeckResponse.class);
    }

    public DeckResponse drawCards(String deckId, int count) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/" + deckId + "/draw/")
                .queryParam("count", count)
                .toUriString();
        return restTemplate.getForObject(url, DeckResponse.class);
    }

    //futuras implementações ...

    public DeckResponse shuffleDeck(String deckId) {
        String url = BASE_URL + "/" + deckId + "/shuffle/";
        return restTemplate.getForObject(url, DeckResponse.class);
    }

    public DeckResponse addToPile(String deckId, String pileName, String cards) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/" + deckId + "/pile/" + pileName + "/add/")
                .queryParam("cards", cards)
                .toUriString();
        return restTemplate.getForObject(url, DeckResponse.class);
    }

    public DeckResponse listPileCards(String deckId, String pileName) {
        String url = BASE_URL + "/" + deckId + "/pile/" + pileName + "/list/";
        return restTemplate.getForObject(url, DeckResponse.class);
    }

    public DeckResponse drawFromPile(String deckId, String pileName, int count) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/" + deckId + "/pile/" + pileName + "/draw/")
                .queryParam("count", count)
                .toUriString();
        return restTemplate.getForObject(url, DeckResponse.class);
    }
}
