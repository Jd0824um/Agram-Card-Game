package com.week1;

import java.util.*;

import static input.InputUtils.*;

public class Agram {

    private Map<Player, Hand> playersAndHands = new LinkedHashMap<>(); // Global variable to keep track of players and their hands
    private AgramUI agramUI = new AgramUI(); // Creates the UI for the game
    private ArrayList<Card> pile = new ArrayList<>(); // Creates the pile for cards

    public static void main(String[] args) {
        new Agram().menu();
    }

    private static final int PLAY_AGRAM = 1; // Static menu options
    private static final int HOW_TO_PLAY = 2;
    private static final int QUIT = 3;

    private static int GAME_OVER = 0; // Game over counter

    void menu() { // menu method
        TreeMap<Integer, String> options = configureMenuOptions();
        boolean quit = false;
        System.out.println("    AGRAM\n" + "-------------");

        while (!quit) {
            int userChoice = agramUI.showMenuGetChoice(options);
            switch (userChoice) {
                case PLAY_AGRAM:
                    playAgram(createAgramDeck());
                    break;
                case HOW_TO_PLAY:
                    agramUI.howToPlay();
                    break;
                case QUIT:
                    quit = true;
                    break;
            }
        }
    }
    private TreeMap<Integer, String> configureMenuOptions() {
        TreeMap<Integer, String> options = new TreeMap<>();

        options.put(PLAY_AGRAM, "Start the game!");
        options.put(HOW_TO_PLAY, "How to play");
        options.put(QUIT, "Quit");

        return options;
    }


    private void playAgram(Deck newAgramDeck) { //Starts the game of Agram
        numberOfPlayers();
        dealCards(newAgramDeck);
        dealCards(newAgramDeck);
        playCard();
    }

    private void playCard(){
        for (Map.Entry<Player,Hand> entry : playersAndHands.entrySet()) {
            Hand playersHand = entry.getValue();

            if (playersHand.hand.isEmpty()){
                break;
            }else {
                System.out.println(entry.getKey().toString() + " your hand: \n"); // I'm sure there's a easier way to write this
                playersHand.showHand();
                int userChoice = intInput("\nPress 1 to play a card\nPress 2 to show the pile\n");

                while (userChoice < 0 || userChoice > 2) {
                    userChoice = intInput("\nPress 1 to play a card\nPress 2 to show the pile\n");
                }
                while (userChoice == 2) {
                    showPile();
                    userChoice = intInput("\nPress 1 to play a card\nPress 2 to show the pile\n");
                    System.out.println("\n");
                }
                if (userChoice == 1) {
                    int playCard = intInput("Which card would you like to play " + entry.getKey().toString() +
                            "?\n" + "Enter a number between 1 and " + playersHand.handSize()) -1;

                    while (playCard < 0 || playCard > playersHand.handSize() - 1) {
                        playCard = intInput("Please choose a card within your hand:");
                    }

                    Card card = playersHand.hand.get(playCard);
                    addPile(card); // Adds card to pile
                    playersHand.removeCardFromHand(playCard); // Removes card from hand
                    int counter = pile.size();

                    if (counter == playersAndHands.size()) { //If every player played a card, the winner of the trick is decided
                        Card.Suits trickSuit = pile.get(0).getCardSuit();
                        int winningPlayer = trick(pile, trickSuit);
                        wonTrick(winningPlayer);
                    }
                }
            }
        }
    }
    private void showPile() { //Shows the cards in the pile
        if (pile.isEmpty()) {
            System.out.println("The card pile is empty!\n");
        }
        else {
            System.out.println(pile.toString());
        }
    }

    private void addPile (Card card) { // Adds card to the pile
        pile.add(card);
    }

    private void wonTrick(int player) {
        int counter = 0;

        for (Player p : playersAndHands.keySet()) {
            counter++;
            if (counter == player) {
                String winner = p.getName();
                System.out.println(winner + " won this trick!");
                if (GAME_OVER == 5) {
                    GAME_OVER = 0; //Resets game over counter if user decides to play again
                    String winnerGame = p.getName();
                    agramUI.gameOver(winnerGame);
                }
                boolean nextRound = agramUI.nextRound(); //Gives option for users to quit in the middle of a game
                if (nextRound) {
                    pile.clear();
                    counter++;
                    GAME_OVER++;
                    playCard();
                }else {
                    menu();
                }
            }
        }
    }

    public int trick(ArrayList<Card> card, Card.Suits trick) {
        Card.Ranks maxRank = null; //sets max rank to null
        int player = 0;

        for (Card c : card){ // gets the highest valued card within the pile
            if (c.getCardSuit().equals(trick)){
                Card.Ranks cardRank = c.getCardRank();
                if (maxRank == null || cardRank.ordinal() > maxRank.ordinal());{
                    maxRank = cardRank;
                }
            }
        }
        for (Card c : card){ // returns the index of the player who won the trick
            player++;
            if (c.getCardSuit().equals(trick)) {
                if (c.getCardRank().equals(maxRank)) {
                    return player;
                }
            }
        }return player;
    }

    public void dealCards(Deck newAgramDeck){
        for (Map.Entry<Player, Hand> entry : playersAndHands.entrySet()){ //Deals 3 cards and adds it to the players hand
            Hand playersHand = entry.getValue();
            playersHand.hand.add(newAgramDeck.dealACard());
            playersHand.hand.add(newAgramDeck.dealACard());
            playersHand.hand.add(newAgramDeck.dealACard());
        }
    }

    public void numberOfPlayers(){
        int howManyPlayers = intInput("How many players will be playing Agram?"); //Asks for number of players

        while (howManyPlayers < 2 || howManyPlayers > 5) { //validation check for number of players
            howManyPlayers = intInput("Must have 2-5 players to play Agram");
        }

        for (int x = 1; x <= howManyPlayers; x++) {
            Player player = new Player(stringInput("Whats your name player " + x));//Creates a player object
            Hand hand = new Hand(); // Creates a player's hand
            playersAndHands.put(player, hand);
        }
    }

    public Deck createAgramDeck() {
        Deck newAgramDeck = new Deck(); //Creates a new deck for Agram
        newAgramDeck.deckOfCards.subList(36,48).clear(); //Removes Jacks, Queens, Kings from deck
        newAgramDeck.deckOfCards.subList(37,38).clear(); // Removes the Ace of Spades from deck
        newAgramDeck.deckOfCards.subList(0,4).clear(); // Removes deuces from deck
        pile.clear();
        playersAndHands.clear();

        newAgramDeck.shuffleDeck();
        return newAgramDeck;
    }
}
