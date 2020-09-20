package com.pokerdan.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokerdan.demo.enumeration.Ranks;
import com.pokerdan.demo.enumeration.Suits;
import com.pokerdan.demo.messaging.Card;
import com.pokerdan.demo.messaging.Deck;
import com.pokerdan.demo.messaging.DeckRankCount;
import com.pokerdan.demo.messaging.DeckSuitCount;
import com.pokerdan.demo.messaging.Game;
import com.pokerdan.demo.messaging.PlayerCount;
import com.pokerdan.demo.messaging.PlayerHandCount;
import com.pokerdan.demo.repository.MemoryDeckRepository;
import com.pokerdan.demo.util.DeckFactory;
import com.pokerdan.demo.util.DeckShuffler;
import com.pokerdan.demo.util.DeckView;

@Service
public class DeckService {

    @Autowired
    private MemoryDeckRepository repository;

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private DeckFactory factory;

    @Autowired
    private DeckShuffler shuffler;

    @Autowired
    private DeckView view;

    public Optional<Deck> createDeck(String gameId) {

        // Validate that the game exists
        Optional<Game> game = gameService.get(gameId);
        if (!game.isPresent()) {
            return Optional.empty();
        }

        Deck deck = shuffler.shuffleDeck(factory.newDeck());
        repository.save(deck, gameId);
        return Optional.of(deck);
    }

    public Optional<Deck> getDeck(String gameId) {
        // Validate that the game exists
        Optional<Game> game = gameService.get(gameId);
        if (!game.isPresent()) {
            return Optional.empty();
        }

        return repository.get(gameId);
    }

    public Optional<Card> fetchCard(String gameId, String playerId) {
        // TODO validate player
        // TODO validate game

        Optional<Deck> deckOpt = repository.get(gameId);
        // TODO validate deck
        Deck deck = deckOpt.get();

        // TODO Transaction here
        Optional<Card> card = shuffler.getCard(deck);
        repository.save(deck, gameId);
        playerService.addCard(card.get(), gameId, playerId);

        return card;
    }

    public List<Card> getPlayerHand(String gameId, String playerId) {
        // TODO validate player
        // TODO validate game

        List<Card> cards = playerService.getPlayerHand(gameId, playerId);
        return cards;
    }

    public void shuffleDeck(String gameId) {
        // TODO validate game
        Optional<Deck> deckOpt = repository.get(gameId);
        if (!deckOpt.isPresent()) {
            return;
        }
        Deck deck = deckOpt.get();
        Deck shuffledDeck = shuffler.shuffleDeck(deck);
        repository.save(shuffledDeck, gameId);
    }

    public DeckSuitCount countBySuit(String gameId) {
        // TODO validate game
        Optional<Deck> deckOpt = repository.get(gameId);
        if (!deckOpt.isPresent()) {
            return DeckSuitCount.builder().build();
        }
        Deck deck = deckOpt.get();
        long clubs = view.countBySuit(deck, Suits.Clubs);
        long spades = view.countBySuit(deck, Suits.Spades);
        long diamonds = view.countBySuit(deck, Suits.Diamonds);
        long hearts = view.countBySuit(deck, Suits.Hearts);

        return DeckSuitCount.builder().clubs(clubs).spades(spades).diamonds(diamonds).hearts(hearts).build();
    }

    public DeckRankCount countByRank(String gameId) {
        // TODO validate game
        Optional<Deck> deckOpt = repository.get(gameId);
        if (!deckOpt.isPresent()) {
            return DeckRankCount.builder().build();
        }
        Deck deck = deckOpt.get();
        long ace = view.countByRank(deck, Ranks.Ace);
        long two = view.countByRank(deck, Ranks.Two);
        long three = view.countByRank(deck, Ranks.Three);
        long four = view.countByRank(deck, Ranks.Four);
        long five = view.countByRank(deck, Ranks.Five);
        long six = view.countByRank(deck, Ranks.Six);
        long seven = view.countByRank(deck, Ranks.Seven);
        long eight = view.countByRank(deck, Ranks.Eight);
        long nine = view.countByRank(deck, Ranks.Nine);
        long ten = view.countByRank(deck, Ranks.Ten);
        long jack = view.countByRank(deck, Ranks.Jack);
        long queen = view.countByRank(deck, Ranks.Queen);
        long king = view.countByRank(deck, Ranks.King);

        return DeckRankCount.builder()
                .ace(ace)
                .two(two)
                .three(three)
                .four(four)
                .five(five)
                .six(six)
                .seven(seven)
                .eight(eight)
                .nine(nine)
                .ten(ten)
                .jack(jack)
                .queen(queen)
                .king(king)
                .build();
    }

    public PlayerHandCount countPlayerHandsByValue(String gameId) {

        List<String> playerIds = gameService.get(gameId).get().getPlayerIds();

        TreeMap<Long, String> orderedPlayerCount = new TreeMap<>();

        playerIds.stream().forEach(playerId -> {
            List<Card> cards = playerService.getPlayerHand(gameId, playerId);
            long sumFaceValue = view.sumFaceValue(cards);
            orderedPlayerCount.put(sumFaceValue, playerId);
        });

        // Sort by highest score
        List<PlayerCount> playerValues = orderedPlayerCount.descendingMap().entrySet()
                .stream()
                .map(entry -> PlayerCount.builder().playerId(entry.getValue()).count(entry.getKey()).build())
                .collect(Collectors.toList());

        return PlayerHandCount.builder().playerValues(playerValues).build();
    }
}
