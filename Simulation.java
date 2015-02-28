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
        //print flopped cards
        for(String x: d.getFlopped()){
            System.out.print(x+ " ");
        }
        System.out.println();
        int[] p1Cards = Rank.getInts(Rank.getChars(p1.getA(), p1.getB(), d.getFlopped()));//all of p1 cards(dealt and flopped)
        int[] p2Cards = Rank.getInts(Rank.getChars(p2.getA(), p2.getB(), d.getFlopped()));
        int[] p3Cards = Rank.getInts(Rank.getChars(p3.getA(), p3.getB(), d.getFlopped()));
        int[] p4Cards = Rank.getInts(Rank.getChars(p4.getA(), p4.getB(), d.getFlopped()));
        int[] p5Cards = Rank.getInts(Rank.getChars(p5.getA(), p5.getB(), d.getFlopped()));
        int[] p6Cards = Rank.getInts(Rank.getChars(p6.getA(), p6.getB(), d.getFlopped()));
        int[] p7Cards = Rank.getInts(Rank.getChars(p7.getA(), p7.getB(), d.getFlopped()));
        int[] p8Cards = Rank.getInts(Rank.getChars(p8.getA(), p8.getB(), d.getFlopped()));
        //find rank of p1
        int r1 = p1.rankOfHand(d);
        //find rank of p2
        int r2 = p2.rankOfHand(d);
        //print p1 cards
        for(int x: p1Cards){
            System.out.print(x+ " ");
        }
        System.out.println();
        System.out.println(r1);//print p1 rank
        //print p2 cards
        for(int x: p2Cards){
            System.out.print(x+ " ");
        }
        System.out.println();
        System.out.println(r2);//print p2 rank
        // find winner
        String winner = "tie";
        //compare general ranks to find winner
        if(r1 > r2){
            winner = "player 1";
        }
        if(r2 > r1){
            winner = "player 2";
        }
        int[] intsA = Rank.getInts(Rank.getChars(p1.getA(),p1.getB(),d.getFlopped()));
        int[] intsB = Rank.getInts(Rank.getChars(p2.getA(),p2.getB(),d.getFlopped()));
        //list of p1 cards
        ArrayList<Integer> list1 = new ArrayList<Integer>();
            for(int i = 0; i<intsA.length; i++){
                list1.add(intsA[i]);
            }
        //list of p2 cards
        ArrayList<Integer> list2 = new ArrayList<Integer>();
            for(int i = 0; i<intsB.length; i++){
                list2.add(intsB[i]);
            }
        //find winner if both have high cards
        if((r1 == r2) && (r1 <=7)){
           winner = Rank.compareHighCards(p1Cards,p2Cards);
        }
        //find winner if both have 1 pair
        if((r1 == r2) && (r1 >= 8)&&(r1 <=20)){             
               //remove the pair from arraylists
               list1.remove(Integer.valueOf(p1.getValueA()));
               list1.remove(Integer.valueOf(p1.getValueA()));
               list2.remove(Integer.valueOf(p2.getValueA()));
               list2.remove(Integer.valueOf(p2.getValueA()));
               //check the next 3 highest cards
               for(int i = 4; i>=2; i--){
                   if(list1.get(i) > list2.get(i)){
                       winner = "Player 1";
                       break;
                   }
                   if(list1.get(i) < list2.get(i)){
                       winner = "Player 2";
                       break;
                   }
               }                        
        }
        //find winner if both have 2 pair
        if((r1 == r2) && (r1 >= 21) && (r1 <=98)){
            list1.remove(Integer.valueOf(p1.getValueA()));
            list1.remove(Integer.valueOf(p1.getValueA()));
            list1.remove(Integer.valueOf(p1.getValueB()));
            list1.remove(Integer.valueOf(p1.getValueB()));
            list2.remove(Integer.valueOf(p2.getValueA()));
            list2.remove(Integer.valueOf(p2.getValueA()));
            list2.remove(Integer.valueOf(p2.getValueB()));
            list2.remove(Integer.valueOf(p2.getValueB()));
            //check the next highest card(last card in each list)
            if(list1.get(2) > list2.get(2)){
                winner = "Player 1";
            }else if(list1.get(2) < list2.get(2)){
                winner = "Player 2";
            }     
        }
        //find winner if both have 3 of a kind
        System.out.println(winner);
    }
    
}
