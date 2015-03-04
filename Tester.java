package simulation;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;

public class Tester {
    @Test
    public void testDealing() {
        Deck d = new Deck();
        d.newDeck(); //new 52 card deck of same order
        assertTrue(d.getDeck(0) == "1h");
        assertTrue(d.getDeck(51) == "ks");
        d.dealToPlayers(); //adds 16 random cards to hands[] array
        assertTrue(d.getHandsArray().length == 16);
        Player p1 = new Player(d.getHands(0),d.getHands(8)); //gives player 1 the 1st and 9th of those random cards, to imitate a real dealer dealing
        Player p2 = new Player(d.getHands(1),d.getHands(9));
        Player p3 = new Player(d.getHands(2),d.getHands(10));
        Player p4 = new Player(d.getHands(3),d.getHands(11));
        Player p5 = new Player(d.getHands(4),d.getHands(12));
        Player p6 = new Player(d.getHands(5),d.getHands(13));
        Player p7 = new Player(d.getHands(6),d.getHands(14));
        Player p8 = new Player(d.getHands(7),d.getHands(15));
        assertTrue(p1.getA() == d.getHands(0));
        assertTrue(p6.getB() == d.getHands(13));
        d.finishDealing();// flop cards
        assertTrue(d.getDeckList().size() == 28);
        d.setFlopped("3s","5c","9d","jh","kc");
        p1 = new Player("1s","1h");
        p2 = new Player("2s","2h");
        int r1 = p1.rankOfHand(d);
        int r2 = p2.rankOfHand(d);
        //test one pair
        assertTrue(r1 == 20);
        assertTrue(r2 == 8);
        d.setFlopped("3s","3c","9d","jh","kc");
        r1 = p1.rankOfHand(d);
        r2 = p2.rankOfHand(d);
        //test two pair
        assertTrue(r1 == 88);
        assertTrue(r2 == 21);
        d.setFlopped("1d","2d","9d","jh","kc");
        r1 = p1.rankOfHand(d);
        r2 = p2.rankOfHand(d);
        //test 3 of a kind
        assertTrue(r1 == 111);
        assertTrue(r2 == 99);
        d.setFlopped("3s","4c","5d","6h","kc");
        p1 = new Player("8s","7h");
        p2 = new Player("2s","1h");
        r1 = p1.rankOfHand(d);
        r2 = p2.rankOfHand(d);
        d.setFlopped("2s","3c","4d","7h","kc");
        p3 = new Player("5s","6h");
        p4 = new Player("5s","1h");
        int r3 = p3.rankOfHand(d);
        int r4 = p4.rankOfHand(d);
        d.setFlopped("2s","3c","~d","jh","kc");
        p5 = new Player("qs","1h");
        int r5 = p5.rankOfHand(d);
        //test straight
        assertTrue(r1 == 115);
        assertTrue(r2 == 113);
        assertTrue(r3 == 114);
        assertTrue(r4 == 112);
        assertTrue(r5 == 121);
        d.setFlopped("2s","3s","~s","jh","ks");
        p1 = new Player("8s","7h");
        p2 = new Player("2j","1s");
        r1 = p1.rankOfHand(d);
        r2 = p2.rankOfHand(d);
        //test flush
        assertTrue(r1 == 122);
        assertTrue(r2 == 122);
        String winner = "";
        ArrayList<Integer> v1 = p1.getFlushValues();
        ArrayList<Integer> v2 = p2.getFlushValues();
            for(int i = 0; i <= 4; i++){
                if(v1.get(i) > v2.get(i)){
                    winner = "Player 1";
                    break;
                }
                if(v2.get(i) > v1.get(i)){
                    winner = "Player 2";
                    break;
                }
            }
        assertTrue(winner == "Player 2");
        
        //test four of a kind
        d.setFlopped("1s","1s","1s","3h","3s");
        p1 = new Player("1s","7h");
        p2 = new Player("3j","3s");
        r1 = p1.rankOfHand(d);
        r2 = p2.rankOfHand(d);
        assertTrue(r1 == 172);
        assertTrue(r2 == 161);
        d.setFlopped("1s","1s","1s","1h","3d");
        p1 = new Player("ks","7h");
        p2 = new Player("jd","3s");
        r1 = p1.rankOfHand(d);
        r2 = p2.rankOfHand(d);
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
        list1.remove(Integer.valueOf(p1.getQValueA()));
        list1.remove(Integer.valueOf(p1.getQValueA()));
        list1.remove(Integer.valueOf(p1.getQValueA()));
        list1.remove(Integer.valueOf(p1.getQValueA()));
        list2.remove(Integer.valueOf(p2.getQValueA()));
        list2.remove(Integer.valueOf(p2.getQValueA()));
        list2.remove(Integer.valueOf(p2.getQValueA()));
        list2.remove(Integer.valueOf(p2.getQValueA()));
            //check next highest card
            if(list1.get(2) > list2.get(2)){
                winner = "Player 1";
            }
            if(list1.get(2) < list2.get(2)){
                winner = "Player 2";
            }
        assertTrue(winner == "Player 1");
    }
}
