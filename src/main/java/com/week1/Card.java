package com.week1;

public class Card { // Class for creating playing cards
    private final Suits cardSuit;
    private final Ranks cardRank;

    public enum Ranks { //enum of card ranks
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }
    public enum Suits { // enum of card suits
        HEARTS,
        SPADES,
        DIAMONDS,
        CLUBS;
    }

    public Card(Ranks rank, Suits suit) {
        this.cardSuit = suit;
        this.cardRank = rank;
    }

    public Suits getCardSuit() {
        return cardSuit;
    }

    public Ranks getCardRank() {
        return cardRank;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", cardRank, cardSuit);
    }
}

