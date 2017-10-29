package com.week1;

import java.util.ArrayList;
import java.util.Collections;


public class Deck { // creates a deck of cards.
    protected ArrayList<Card> deckOfCards;
    protected int indexDeckOfCards = 0;

    protected Deck() {
        this.deckOfCards = new ArrayList<>();
        for (Card.Ranks rank : Card.Ranks.values()) {
            for (Card.Suits suit: Card.Suits.values())
                deckOfCards.add(new Card(rank, suit));
        }
    }

    protected void shuffleDeck() { // shuffles deck
        Collections.shuffle(deckOfCards);
    }

    protected void removeACard(Card card) {
        deckOfCards.remove(card);
    }

    protected Card dealACard() { //deals a card from the deck
        if (indexDeckOfCards < deckOfCards.size()){
            Card currentCard = deckOfCards.get(indexDeckOfCards);
            indexDeckOfCards++;
            return currentCard;
        }
        else {
            return null;
        }
    }

    protected int cardsLeftInDeck() {
        return deckOfCards.size() - indexDeckOfCards;
    }
}
