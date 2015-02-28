package simulation;

import static org.junit.Assert.*;
import org.junit.Test;

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
        assertTrue(r1 == 20);
        assertTrue(r2 == 8);
        d.setFlopped("3s","3c","9d","jh","kc");
        r1 = p1.rankOfHand(d);
        r2 = p2.rankOfHand(d);
        assertTrue(r1 == 88);
        assertTrue(r2 == 21);
    }
}
