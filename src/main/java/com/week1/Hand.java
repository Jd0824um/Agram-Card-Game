package com.week1;

import java.util.ArrayList;

public class Hand {

    protected ArrayList<Card> hand;

    public Hand() { // Constructor for a players hand
        this.hand = new ArrayList<>();
    }

    public void showHand() { //Shows hand
        System.out.println(hand.toString());
    }

    public int handSize() { // Shows hand size
        return hand.size();
    }

    public void removeCardFromHand(int card){ // Removes a card from hand
        hand.remove(card);
    }
}
