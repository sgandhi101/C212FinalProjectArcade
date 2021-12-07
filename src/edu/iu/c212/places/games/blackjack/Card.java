package edu.iu.c212.places.games.blackjack;

public enum Card {

    ACE_CLUBS("Ace of Clubs", -1),
    TWO_CLUBS("2 of Clubs", 2),
    THREE_CLUBS("3 of Clubs", 3),
    FOUR_CLUBS("4 of Clubs", 4),
    FIVE_CLUBS("5 of Clubs", 5),
    SIX_CLUBS("6 of Clubs", 6),
    SEVEN_CLUBS("7 of Clubs", 7),
    EIGHT_CLUBS("8 of Clubs", 8),
    NINE_CLUBS("9 of Clubs", 9),
    TEN_CLUBS("10 of Clubs", 10),
    JACK_CLUBS("Jack of Clubs", 10),
    QUEEN_CLUBS("Queen of Clubs", 10),
    KING_CLUBS("King of Clubs", 10),


    ACE_SPADES("Ace of Spades", -1),
    TWO_SPADES("2 of Spades", 2),
    THREE_SPADES("3 of Spades", 3),
    FOUR_SPADES("4 of Spades", 4),
    FIVE_SPADES("5 of Spades", 5),
    SIX_SPADES("6 of Spades", 6),
    SEVEN_SPADES("7 of Spades", 7),
    EIGHT_SPADES("8 of Spades", 8),
    NINE_SPADES("9 of Spades", 9),
    TEN_SPADES("10 of Spades", 10),
    JACK_SPADES("Jack of Spades", 10),
    QUEEN_SPADES("Queen of Spades", 10),
    KING_SPADES("King of Spades", 10),

    ACE_HEARTS("Ace of Hearts", -1),
    TWO_HEARTS("2 of Hearts", 2),
    THREE_HEARTS("3 of Hearts", 3),
    FOUR_HEARTS("4 of Hearts", 4),
    FIVE_HEARTS("5 of Hearts", 5),
    SIX_HEARTS("6 of Hearts", 6),
    SEVEN_HEARTS("7 of Hearts", 7),
    EIGHT_HEARTS("8 of Hearts", 8),
    NINE_HEARTS("9 of Hearts", 9),
    TEN_HEARTS("10 of Hearts", 10),
    JACK_HEARTS("Jack of Hearts", 10),
    QUEEN_HEARTS("Queen of Hearts", 10),
    KING_HEARTS("King of Hearts", 10),


    ACE_DIAMONDS("Ace of Diamonds", -1),
    TWO_DIAMONDS("2 of Diamonds", 2),
    THREE_DIAMONDS("3 of Diamonds", 3),
    FOUR_DIAMONDS("4 of Diamonds", 4),
    FIVE_DIAMONDS("5 of Diamonds", 5),
    SIX_DIAMONDS("6 of Diamonds", 6),
    SEVEN_DIAMONDS("7 of Diamonds", 7),
    EIGHT_DIAMONDS("8 of Diamonds", 8),
    NINE_DIAMONDS("9 of Diamonds", 9),
    TEN_DIAMONDS("10 of Diamonds", 10),
    JACK_DIAMONDS("Jack of Diamonds", 10),
    QUEEN_DIAMONDS("Queen of Diamonds", 10),
    KING_DIAMONDS("King of Diamonds", 10);

    final String cardName;
    final Integer value;

    Card(String cardName, Integer value) {
        this.cardName = cardName;
        this.value = value;
    }

    public String getCardName() {
        return this.cardName;
    }

    public Integer getValue() {
        return this.value;
    }

}
