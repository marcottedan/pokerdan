package com.pokerdan.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokerdan.demo.messaging.Card;
import com.pokerdan.demo.messaging.Deck;
import com.pokerdan.demo.messaging.DeckRankCount;
import com.pokerdan.demo.messaging.DeckSuitCount;
import com.pokerdan.demo.messaging.PlayerHandCount;
import com.pokerdan.demo.service.DeckService;

import io.swagger.annotations.ApiOperation;

/**
 * Note: I personally wouldn't expose the Deck API to create a deck and add it to a game from the client.
 * Instead, I would lazily create it when the first player of a game asks for a hand.
 * That way, you don't have to manage any state regarding the decks between the client and the server.
 */
// TODO Validate models
@RestController
public class DeckController {

    @Autowired
    private DeckService deckService;


    @ApiOperation(value = "Get a deck for a game")
    @GetMapping(value = "/deck/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Deck> get(@RequestParam("gameId") String gameId) {

        Optional<Deck> deck = deckService.getDeck(gameId);
        if (!deck.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(deck.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a deck for a game")
    @PostMapping(value = "/deck/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Deck> deck(@RequestParam("gameId") String gameId) {

        Optional<Deck> deck = deckService.createDeck(gameId);
        if (!deck.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(deck.get(), HttpStatus.OK);
    }

    // TODO Unify terms for (ask / fetch / draw) in a card game because I don't know that domain well
    @ApiOperation(value = "Ask for a card from a deck")
    @PostMapping(value = "/deck/card/{gameId}/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> fetchCard(@RequestParam("gameId") String gameId, @RequestParam("playerId") String playerId) {

        Optional<Card> card = deckService.fetchCard(gameId, playerId);
        if (!card.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(card.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get the player hand")
    @GetMapping(value = "/deck/card/{gameId}/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Card>> getCard(@RequestParam("gameId") String gameId, @RequestParam("playerId") String playerId) {

        // TODO Use JWT token for playerId to prevent getting other player hands
        List<Card> cards = deckService.getPlayerHand(gameId, playerId);

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @ApiOperation(value = "Shuffle a deck for a game")
    @GetMapping(value = "/deck/shuffle/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> shuffle(@RequestParam("gameId") String gameId) {

        deckService.shuffleDeck(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Count cards by suit")
    @GetMapping(value = "/deck/count/suit/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeckSuitCount> countBySuit(@RequestParam("gameId") String gameId) {

        DeckSuitCount count = deckService.countBySuit(gameId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @ApiOperation(value = "Count cards by rank")
    @GetMapping(value = "/deck/count/rank/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeckRankCount> countByRank(@RequestParam("gameId") String gameId) {

        DeckRankCount count = deckService.countByRank(gameId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @ApiOperation(value = "Count player hands sorted by descending order of their total value")
    @GetMapping(value = "/deck/count/player/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerHandCount> countPlayerHandsValues(@RequestParam("gameId") String gameId) {

        PlayerHandCount count = deckService.countPlayerHandsByValue(gameId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


}
