package com.koerich.cardconnect.service;

import com.koerich.cardconnect.dto.response.DeckResponse;
import com.koerich.cardconnect.exception.BadRequestException;
import com.koerich.cardconnect.exception.NotFoundException;
import org.springframework.http.HttpStatus;
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

    public DeckResponse createAndShuffleDeck(int deckCount) throws BadRequestException {
        if(deckCount <= 0){
            throw new BadRequestException("deck count must be greater than 0", HttpStatus.BAD_REQUEST);
        }
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
}
