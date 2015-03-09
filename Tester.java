package simulation;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;

public class Tester {
    @Test
    public void testDealing(){
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
    }
    @Test
    public void testOnePair(){
        Deck d = new Deck();
        d.newDeck();
        d.setFlopped("3s","5c","9d","jh","kc");
        Player p1 = new Player("1s","1h");
        Player p2 = new Player("2s","2h");
        int r1 = p1.rankOfHand(d);
        int r2 = p2.rankOfHand(d);
        assertTrue(r1 == 20);
        assertTrue(r2 == 8);
    }
    @Test
    public void testTwoPairs(){
        Deck d = new Deck();
        d.newDeck();
        d.setFlopped("3s","3c","9d","jh","kc");
        Player p1 = new Player("1s","1h");
        Player p2 = new Player("2s","2h");
        int r1 = p1.rankOfHand(d);
        int r2 = p2.rankOfHand(d);
        assertTrue(r1 == 88);
        assertTrue(r2 == 21);
    }
    @Test
    public void testThreeOfAKind(){
        Deck d = new Deck();
        d.newDeck();
        d.setFlopped("1d","2d","9d","jh","kc");
        Player p1 = new Player("1s","1h");
        Player p2 = new Player("2s","2h");
        int r1 = p1.rankOfHand(d);
        int r2 = p2.rankOfHand(d);
        assertTrue(r1 == 111);
        assertTrue(r2 == 99);
    }
    @Test
    public void testStraight(){
        Deck d = new Deck();
        d.newDeck();
        d.setFlopped("3s","4c","5d","6h","9c");
        Player p1 = new Player("8s","7h");
        Player p2 = new Player("2s","1h");
        int r1 = p1.rankOfHand(d);
        int r2 = p2.rankOfHand(d);
        d.setFlopped("2s","3c","4d","7h","kc");
        Player p3 = new Player("5s","6h");
        Player p4 = new Player("5s","1h");
        int r3 = p3.rankOfHand(d);
        int r4 = p4.rankOfHand(d);
        d.setFlopped("2s","3c","~d","jh","kc");
        Player p5 = new Player("qs","1h");
        int r5 = p5.rankOfHand(d);
        assertTrue(r1 == 116);
        assertTrue(r2 == 113);
        assertTrue(r3 == 114);
        assertTrue(r4 == 112);
        assertTrue(r5 == 121);
    }
    @Test
    public void testFlush(){
        Deck d = new Deck();
        d.newDeck();
        d.setFlopped("2s","3s","~h","js","ks");
        Player p1 = new Player("8d","7s");
        Player p2 = new Player("4d","1s");
        int r1 = p1.rankOfHand(d);
        int r2 = p2.rankOfHand(d);
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
    }
    @Test
    public void testFullHouse(){
        Deck d = new Deck();
        d.newDeck();
        d.setFlopped("2s","2s","~s","~h","ks");
        Player p1 = new Player("~s","7h");
        Player p2 = new Player("2c","1s");
        int r1 = p1.rankOfHand(d);
        int r2 = p2.rankOfHand(d);
        assertTrue(r1 == 219);
        assertTrue(r2 == 130);
        d.setFlopped("2s","2c","js","jd","1d");
        p1 = new Player("2s","kh");
        p2 = new Player("2d","js");
        r1 = p1.rankOfHand(d);
        r2 = p2.rankOfHand(d);
        assertTrue(r1 == 131);
        assertTrue(r2 == 231);
    }
    @Test
    public void testFourOfAKind(){
        Deck d = new Deck();
        d.newDeck();
        d.setFlopped("1s","1s","1s","3h","3s");
        Player p1 = new Player("3s","3h");
        Player p2 = new Player("7j","1s");
        int r1 = p1.rankOfHand(d);
        int r2 = p2.rankOfHand(d);
        assertTrue(r1 == 280);
        assertTrue(r2 == 291);
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
        String winner = "";
            //check next highest card
            if(list1.get(2) > list2.get(2)){
                winner = "Player 1";
            }
            if(list1.get(2) < list2.get(2)){
                winner = "Player 2";
            }
        assertTrue(winner == "Player 1");
    }
    @Test
    public void testStraightFlush(){
        Deck d = new Deck();
        d.newDeck();
        d.setFlopped("jh","2s","3s","4s","5s");
        Player p1 = new Player("1s","3h");
        Player p2 = new Player("7s","6s");
        Player p3 = new Player("1s","6s");
        int r1 = p1.rankOfHand(d);
        int r2 = p2.rankOfHand(d);
        int r3 = p3.rankOfHand(d);
        d.setFlopped("jh","qh","~h","8h","5h");
        Player p4 = new Player("1h","kh");
        Player p5 = new Player("kh","9h");
        int r4 = p4.rankOfHand(d);
        int r5 = p5.rankOfHand(d);
        assertTrue(r1 == 292);
        assertTrue(r2 == 294);
        assertTrue(r3 == 293);
        assertTrue(r4 == 301);
        assertTrue(r5 == 300);
    }
}
