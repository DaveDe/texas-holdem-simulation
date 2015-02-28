package simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Deck {
    String[] s = {"1h","2h","3h","4h","5h","6h","7h","8h","9h","~h","jh","qh","kh",
                 "1d","2d","3d","4d","5d","6d","7d","8d","9d","~d","jd","qd","kd",
                 "1c","2c","3c","4c","5c","6c","7c","8c","9c","~c","jc","qc","kc",
                 "1s","2s","3s","4s","5s","6s","7s","8s","9s","~s","js","qs","ks"};
    // ~ represents 10
    String[] hands = new String[16];
    String[] flopped = new String[5];
    ArrayList<String> deck = new ArrayList<String>(Arrays.asList(s));
    
    public Deck(){}
    
    //makes a new 52 card deck in the same order
    public void newDeck(){
        deck.clear();
        deck = new ArrayList<String>(Arrays.asList(s));
    }
    //removes 16 random cards from the deck, to be given to players. I remove 
    //random cards to simulate shuffling, rather than shuffle and remove from
    //the top.
    public void dealToPlayers(){
        int max = 52;
        for(int i = 0; i<=15; i++){
            Random r = new Random();
            int index = r.nextInt(max - i);
            hands[i] = deck.get(index);
            deck.remove(index);
        }
      
    }
    //kill a card, flop 3, kill, flop, kill, flop.
    public void finishDealing(){
        Random r = new Random();
        deck.remove(r.nextInt(deck.size())); //kill a card
        flopped[0] = deck.get(r.nextInt(deck.size()));
        deck.remove(flopped[0]);//flop 1
        flopped[1] = deck.get(r.nextInt(deck.size()));
        deck.remove(flopped[1]);//flop 2
        flopped[2] = deck.get(r.nextInt(deck.size()));
        deck.remove(flopped[2]);//flop 3
        deck.remove(r.nextInt(deck.size())); //kill another card
        flopped[3] = deck.get(r.nextInt(deck.size()));
        deck.remove(flopped[3]);//flop 4
        deck.remove(r.nextInt(deck.size())); //kill another card
        flopped[4] = deck.get(r.nextInt(deck.size()));
        deck.remove(flopped[4]);//flop 5
    }
    public String getHands(int index){
        return hands[index];
    }
    public String[] getHandsArray(){
        return hands;
    }
    public String getFlop(int index){
        return flopped[index];
    }
    public String[] getFlopped(){
        return flopped;
    }
    public String getDeck(int i){
        return deck.get(i);
    }
    public ArrayList<String> getDeckList(){
        return deck;
    }
    public void setFlopped(String a, String b, String c, String d, String e){
        flopped[0] = a;
        flopped[1] = b;
        flopped[2] = c;
        flopped[3] = d;
        flopped[4] = e;
    }
    
}
