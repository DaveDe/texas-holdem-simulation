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
        d.setFlopped("5c","6d","7c","1s","5s");
        Player p3 = new Player("6s","1h");
        Player p4 = new Player("6s","7h");
        int r3 = p3.rankOfHand(d);
        int r4 = p4.rankOfHand(d);
        assertTrue(r3 == 91);
        assertTrue(r4 == 35);
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
    @Test
    public void testHighCardTie(){
        Deck d = new Deck();
        d.newDeck();
        d.setFlopped("3h","4d","6c","8s","js");
        Player p1 = new Player("1s","qh");
        Player p2 = new Player("1s","qs");
        Player p3 = new Player("1s","ks");
        Player p4 = new Player("1s","qh");
        Player p5 = new Player("1s","qs");
        Player p6 = new Player("~s","7s");
        Player p7 = new Player("~s","7h");
        Player p8 = new Player("~s","7s");
        Compare c = new Compare();
        String winner = c.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c.getWinner() == "Player 3");
        d.setFlopped("1h","kd","qc","8s","2s");
        p1 = new Player("4h","3c");
        p2 = new Player("~h","3c");
        p3 = new Player("7h","3c");
        p4 = new Player("jh","3c");
        p5 = new Player("9h","3c");
        p6 = new Player("5h","3c");
        p7 = new Player("~h","3c");
        p8 = new Player("7h","3c");
        Compare c2 = new Compare();
        String winner2 = c2.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c2.getWinner() == "Player 4");
        d.setFlopped("1h","kd","qc","8s","2s");
        p1 = new Player("9h","3c");
        p2 = new Player("9h","4c");
        p3 = new Player("9h","5c");
        p4 = new Player("9h","6c");
        p5 = new Player("9h","7c");
        p6 = new Player("9h","4c");
        p7 = new Player("9h","5c");
        p8 = new Player("9h","6c");
        Compare c3 = new Compare();
        String winner3 = c3.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c3.getWinner() == "tie");
    }
    @Test
    public void testOnePairTie(){
        Deck d = new Deck();
        d.newDeck();
        d.setFlopped("3h","4d","6c","8s","js");
        Player p1 = new Player("3s","2h");
        Player p2 = new Player("3s","5s");
        Player p3 = new Player("3s","9s");
        Player p4 = new Player("3s","kh");
        Player p5 = new Player("3s","~s");
        Player p6 = new Player("3s","1s");
        Player p7 = new Player("3s","7h");
        Player p8 = new Player("3s","5s");
        Compare c = new Compare();
        String winner = c.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c.getWinner() == "Player 6");
        d.setFlopped("3h","4d","kc","7s","1s");
        p1 = new Player("3h","jc");
        p2 = new Player("3h","qc");
        p3 = new Player("3h","5c");
        p4 = new Player("3h","5c");
        p5 = new Player("3h","5c");
        p6 = new Player("3h","5c");
        p7 = new Player("3h","5c");
        p8 = new Player("3h","5c");
        Compare c2 = new Compare();
        String winner2 = c2.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c2.getWinner() == "Player 2");
        d.setFlopped("3h","4d","kc","7s","1s");
        p1 = new Player("3h","qc");
        p2 = new Player("3h","qc");
        p3 = new Player("3h","qc");
        p4 = new Player("3h","qc");
        p5 = new Player("3h","qc");
        p6 = new Player("3h","qc");
        p7 = new Player("3h","qc");
        p8 = new Player("3h","qc");
        Compare c3 = new Compare();
        String winner3 = c3.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c3.getWinner() == "tie");
    }
    @Test
    public void testTwoPairTie(){
        Deck d = new Deck();
        d.newDeck();
        d.setFlopped("3h","3d","4c","4s","8s");
        Player p1 = new Player("6s","9h");
        Player p2 = new Player("6s","~s");
        Player p3 = new Player("6s","js");
        Player p4 = new Player("6s","qh");
        Player p5 = new Player("6s","1s");
        Player p6 = new Player("6s","ks");
        Player p7 = new Player("6s","7h");
        Player p8 = new Player("6s","5s");
        Compare c = new Compare();
        String winner = c.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c.getWinner() == "Player 5");
        d.setFlopped("3h","3d","7c","7s","1s");
        p1 = new Player("6h","jc");
        p2 = new Player("6h","qc");
        p3 = new Player("6h","kc");
        p4 = new Player("6h","kc");
        p5 = new Player("6h","2c");
        p6 = new Player("6h","4c");
        p7 = new Player("6h","5c");
        p8 = new Player("6h","5c");
        Compare c2 = new Compare();
        String winner2 = c2.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c2.getWinner() == "tie");
        d.setFlopped("3h","6d","7c","7s","1s");
        p1 = new Player("6h","1c");
        p2 = new Player("5h","1c");
        p3 = new Player("kh","1c");
        p4 = new Player("qh","1c");
        p5 = new Player("jh","1c");
        p6 = new Player("2h","1c");
        p7 = new Player("2h","1c");
        p8 = new Player("2h","1c");
        Compare c3 = new Compare();
        String winner3 = c3.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c3.getWinner() == "Player 3");
    }
    @Test
    public void testThreeOfAKindTie(){
        Deck d = new Deck();
        d.newDeck();
        d.setFlopped("3h","3d","3c","4s","8s");
        Player p1 = new Player("2s","kh");
        Player p2 = new Player("2s","qs");
        Player p3 = new Player("2s","1s");
        Player p4 = new Player("2s","jh");
        Player p5 = new Player("2s","~s");
        Player p6 = new Player("2s","9s");
        Player p7 = new Player("2s","qh");
        Player p8 = new Player("2s","ks");
        Compare c = new Compare();
        String winner = c.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c.getWinner() == "Player 3");
        d.setFlopped("3h","3d","3c","7s","1s");
        p1 = new Player("6h","2c");
        p2 = new Player("6h","2c");
        p3 = new Player("6h","kc");
        p4 = new Player("6h","kc");
        p5 = new Player("6h","2c");
        p6 = new Player("6h","4c");
        p7 = new Player("6h","5c");
        p8 = new Player("6h","5c");
        Compare c2 = new Compare();
        String winner2 = c2.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c2.getWinner() == "tie");
    }
    @Test
    public void testFlushTie(){
        Deck d = new Deck();
        Compare c = new Compare();
        d.newDeck();
        d.setFlopped("3h","5h","6h","2h","jd");
        Player p1 = new Player("kd","~h");
        Player p2 = new Player("~d","kh");
        Player p3 = new Player("2d","jh");
        Player p4 = new Player("1d","qh");
        Player p5 = new Player("qd","1h");
        Player p6 = new Player("jd","7h");
        Player p7 = new Player("2d","8h");
        Player p8 = new Player("2d","9h");
        String winner = c.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c.getWinner() == "Player 5");
    }
    @Test
    public void testFourOfAKindTie(){
        Deck d = new Deck();
        Compare c = new Compare();
        d.newDeck();
        d.setFlopped("3h","3d","3c","3s","5d");
        Player p1 = new Player("2d","6h");
        Player p2 = new Player("2d","7h");
        Player p3 = new Player("2d","8h");
        Player p4 = new Player("2d","9h");
        Player p5 = new Player("2d","~h");
        Player p6 = new Player("2d","jh");
        Player p7 = new Player("2d","kh");
        Player p8 = new Player("2d","qh");
        String winner = c.winningHand(p1,p2,p3,p4,p5,p6,p7,p8,d);
        assertTrue(c.getWinner() == "Player 7");
    }
}
