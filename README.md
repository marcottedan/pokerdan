You need Lombok to run this project.

Swagger available on http://localhost:8080/swagger-ui.html

- [x] Create and delete a game
    * Call DELETE | POST `/game`
- [x] Create a deck
    * Call POST `/deck`
- [x] Add a deck to a game deck
    * Call POST `/deck`
    * [x] Please note that once a deck has been added to a game deck it cannot be
removed.
- [x] Add and remove players from a game
    * call  `/game/join`
- [x] Deal cards to a player in a game from the game deck
    * [x] Specifically, for a game deck containing only one deck of cards, a call to shuffle
followed by 52 calls to dealCards(1) for the same player should result in the
caller being provided all 52 cards of the deck in a random order. If the caller then
makes a 53rd call to dealCard(1), no card is dealt. 
    * ⚠️ This approach is to be followed if the game deck contains more than one deck.
    * **Note**: Not sure I understand how a game can contain more than a deck, skipped this requirement instead of coding something wrong.
- [x] Get the list of cards for a player
- [x] Get the list of players in a game along with the total added value of all the cards each
player holds; use face values of cards only. Then sort the list in descending order, from
the player with the highest value hand to the player with the lowest value hand:
    * For instance if player ‘A’ holds a 10 + King then her total value is 23 and player
‘B’ holds a 7 + Queen then his total value is 19, so player ‘A’ will be listed first
followed by player ‘B’.
- [x] Get the count of how many cards per suit are left undealt in the game deck (example: 5
hearts, 3 spades, etc.)
    * ⚠️ Not sure the difference between this feature and the following one... 🤔
- [x] Get the count of each card (suit and value) remaining in the game deck sorted by suit (
hearts, spades, clubs, and diamonds) and face value from high value to low value (King,
Queen, Jack, 10….2, Ace with value of 1)
    * Call `/deck/count` 
- [x] Shuffle the game deck (shoe)
    * `shuffle` done when the deck is created
- [x] Shuffle returns no value, but results in the cards in the game deck being
randomly permuted. Please do not use library-provided “shuffle” operations to
implement this function. You may use library- provided random number
generators in your solution.
- [x] Shuffle can be called at any time
    * call `/deck/shuffle`
