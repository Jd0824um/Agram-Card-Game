package com.week1;

import java.util.TreeMap;

import static input.InputUtils.intInput;
import static input.InputUtils.yesNoInput;

public class AgramUI {


    protected int showMenuGetChoice(TreeMap<Integer, String> allOptions) { //Shows menu options (modified from Week 7 lab)
        while (true) {
            for (int option : allOptions.keySet()) {
                System.out.println(String.format("%d: %s", option, allOptions.get(option)));
            }
            int task = intInput("\nEnter your selection:");

            if (allOptions.keySet().contains(task)) {
                return task;
            }
        }
    }


    public void howToPlay() {// rules to agram
        System.out.println("                                     HOW TO PLAY AGRAM");
        System.out.println("                              -------------------------------\n");
        System.out.println(
                "                                          THE PACK\n"  +
                        "-----------------------------------------------------------------------------------------------\n" +
                        "The kings, queens, jacks, the 2s of all suits and the ace of spades are removed from the deck. \n" +
                        "The cards of each suit rank, from high to low: A, 10, 9, 8, 7, 6, 5, 4, 3. Because the ace of\n" +
                        "spades (called \"Chief\"') is removed from the deck, the highest card in the spade suit is the 10.\n" +
                        "\n" +
                        "                                          THE DEAL\n" +
                        "-----------------------------------------------------------------------------------------------\n" +
                        "The dealer will deal six cards to each player, three at a time.\n" +
                        "\n" +
                        "                                          THE PLAY\n" +
                        "-----------------------------------------------------------------------------------------------\n" +
                        "The player to the left of the dealer leads with a card of their choice. The next player to the \n" +
                        "left then follows with their card. If possible they must follow suit. However, if they cannot, \n" +
                        "they may play a card of any suit. If the card played does not belong to the original suit, it \n" +
                        "has no value. After all players have played their card, the player who has the highest card of \n" +
                        "the original suit (suit of the leading card of the round) wins the trick.\n" +
                        "\n" +
                        "The winner of the trick leads any card from his hand to begin the next trick, playing it face\n" +
                        "up on top of the pile. Once again, the other players must each play a card of the same suit as\n" +
                        "the card that was led, if possible. Otherwise they may play any card.\n" +
                        "\n" +
                        "This continues until six tricks have been played. Whoever wins the sixth and last trick wins the" +
                        "game.\n");
    }

    public boolean nextRound(){
        return yesNoInput("Ready to play the next round?");
    }

    public void gameOver(String winner) {
        System.out.println(winner + " WINS THE GAME!\n");
        boolean playAgain = yesNoInput("Would you like to play again?: ");
        if (playAgain) {
            new Agram().menu();
        }else {
            System.out.println("Thank you for playing!");
        }
    }
}
