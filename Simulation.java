package simulation;

import java.util.ArrayList;
import java.util.Arrays;

public class Simulation {
/**
 * Texas Holdem Simulator:
 * Calculate the odds of all unique hands, by simulating 1000000 
 * games of each hand, and finding the % of games won with each hand. Assumes
 * there are 8 total players, no betting or folding.
 * @param args 
 */
    public static void main(String[] args) {
        Deck d = new Deck();
        Rank r = new Rank();
        d.newDeck(); //new 52 card deck of same order
        d.dealToPlayers(); //adds 16 random cards to hands[] array
        Player p1 = new Player(d.getHands(0),d.getHands(8)); //gives player 1 the 1st and 9th of those random cards, to imitate a real dealer dealing
        Player p2 = new Player(d.getHands(1),d.getHands(9));
        Player p3 = new Player(d.getHands(2),d.getHands(10));
        Player p4 = new Player(d.getHands(3),d.getHands(11));
        Player p5 = new Player(d.getHands(4),d.getHands(12));
        Player p6 = new Player(d.getHands(5),d.getHands(13));
        Player p7 = new Player(d.getHands(6),d.getHands(14));
        Player p8 = new Player(d.getHands(7),d.getHands(15));
        d.finishDealing();// flop cards
        Compare c = new Compare();  
        String winner = c.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        System.out.println(winner); 
    }
}
