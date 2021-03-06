package simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Player {
    String a = "";
    String b = "";
    private int sValueA;//stores value of pair
    private int sValueB;//stores value of 2nd pair
    private int sValueC;//stores value of 3rd pair
    private int tValueA;//stores value of 3 of a kind
    private int tValueB;//stores 2nd 3 of a kind value
    private int qValueA;//stores value of 4 of a kind
    private boolean straight;
    private boolean flush;
    private ArrayList<Integer> straightValues = new ArrayList<Integer>(); //stores values of a straight in ascending order
    private ArrayList<Integer> straightValues2 = new ArrayList<Integer>(); //stores values of 2nd straight (weaker than 1st)
    private ArrayList<Integer> straightValues3 = new ArrayList<Integer>(); //stores values of 3rd straight (weaker than 2nd)
    private ArrayList<Integer> flushValues = new ArrayList<Integer>(); //stores values of a flush in ascending order(only 5 values)
    private ArrayList<Integer> flushValues2 = new ArrayList<Integer>(); //stores values of 2nd flush (weaker than 1st)
    private ArrayList<Integer> flushValues3 = new ArrayList<Integer>(); //stores values of 3rd flush (weaker than 2nd)
    private ArrayList<Integer> flushValuesTemp = new ArrayList<Integer>(); //stores all values of the same suit, assuming there are at least 5
    private String rankGeneral = "";
    
    public Player(String a, String b){
        this.a = a;
        this.b = b;
    }

    //finds the rank of the hand, subrank is handled in main
    //by subrank, I mean the rank is the same. ex: each players have a pair of 10's
    //look through high cards to determine winner.
    public int rankOfHand(Deck d){
        int finalRank = 0;
        String[] flopped = d.getFlopped();
        
        // numeric value of each card stored in ints[]
        // jack gets 11, queen 12, king 13, ace 14
        int[] ints = new int[7];
        ints[0] = Rank.convert(a.charAt(0));
            if(ints[0] == 0){
                ints[0] = Character.getNumericValue(a.charAt(0));
            }
        ints[1] = Rank.convert(b.charAt(0));
            if(ints[1] == 0){
                ints[1] = Character.getNumericValue(a.charAt(0));
            }
        for(int i = 2; i < ints.length; i++){
        ints[i] = Rank.convert(flopped[i-2].charAt(0));
            if(ints[i] == 0){
                ints[i] = Character.getNumericValue(flopped[i-2].charAt(0));
            }
        }

        //find the number of pairs, and the type of pairs
        Arrays.sort(ints);
        int sPairs = 0;
        int tPairs = 0; 
        
        boolean fourOfAKind = false;
        //four of a kind
        for(int i = 0; i<ints.length-3; i++){
            if((ints[i]==ints[i+1])&&(ints[i+1]==ints[i+2])&&(ints[i+2]==ints[i+3])){
                fourOfAKind = true;
                qValueA = ints[i];
            }
        }
       for(int i = 0; i < ints.length-2; i++){                 
           //three of a kind
           if((ints[i] == ints[i+1]) && (ints[i+1] == ints[i+2])&& !fourOfAKind){
               if(tPairs == 0){
                    tValueA = ints[i];
                }
                if(tPairs == 1){
                    tValueB = ints[i];
                }
                tPairs++;
            }
        }
        //couts second 3 of a kind as a single pair, to be counted as full house
        if(tPairs == 2){
            if(tValueA > tValueB){
                sValueA = tValueB;
                sPairs++;
            }else{
                sValueA = tValueA;
                tValueA = tValueB;
                sPairs++;
            }
            tPairs--;
        }
           //single pairs, insure sPairs doesnt increase for 3 of a kind
       for(int i = 0; i < ints.length-2; i++){
           if(i != 0){
                if((ints[i] == ints[i+1]) && (ints[i+1] != ints[i+2]) &&(ints[i-1] != ints[i])){
                    if(sPairs == 0){
                        sValueA = ints[i];
                    }else if(sPairs == 1){
                        sValueB = ints[i];
                    }else{
                        sValueC = ints[i];
                    }
                    sPairs++;
                }
           }else{
               if((ints[i] == ints[i+1]) && (ints[i+1] != ints[i+2])){
                   if(sPairs == 0){
                        sValueA = ints[i];
                    }else if(sPairs == 1){
                        sValueB = ints[i];
                    }else{
                        sValueC = ints[i];
                    }
                   sPairs++;
               }
           }
       }
        
       //chacks for last pair outside loop
       if((ints[5] == ints[6]) && (ints[5] != ints[4])){
                if(sPairs == 0){
                        sValueA = ints[5];
                    }else if(sPairs == 1){
                        sValueB = ints[5];
                    }else{
                        sValueC = ints[5];
                    }
               sPairs++;
           }             
            
        //check for straights, store values in straightValues
        //search for weakest first, strongest last
        straight = false;
         //check for ace - 5 straight first. 
        if(ints[6] == 14){
            boolean two = false;
            boolean three = false;
            boolean four = false;
            boolean five = false;
            for(int i = 0; i <= 5; i++){
                if(ints[i] == 2){
                    two = true;
                }
                if(ints[i] == 3){
                    three = true;
                }
                if(ints[i] == 4){
                    four = true;
                }
                if(ints[i] == 5){
                    five = true;
                }
            }
            if(two && three && four && five){
            straight = true;
            straightValues.clear();
            straightValues.add(14);
            straightValues.add(2);
            straightValues.add(3);
            straightValues.add(4);
            straightValues.add(5);
            }
        }
        //01234
        if((ints[0] == ints[1]-1) && (ints[1] == ints[2]-1) && (ints[2] == ints[3]-1) && (ints[3] == ints[4]-1)){
            straight = true;
            if(straightValues.size() == 5){
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[1]);
            straightValues.add(ints[2]);
            straightValues.add(ints[3]);
            straightValues.add(ints[4]);
        }
        //01235
        if((ints[0] == ints[1]-1) && (ints[1] == ints[2]-1) && (ints[2] == ints[3]-1) && (ints[3] == ints[5]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[1]);
            straightValues.add(ints[2]);
            straightValues.add(ints[3]);
            straightValues.add(ints[5]);
        }
        //01245
        if((ints[0] == ints[1]-1) && (ints[1] == ints[2]-1) && (ints[2] == ints[4]-1) && (ints[4] == ints[5]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[1]);
            straightValues.add(ints[2]);
            straightValues.add(ints[4]);
            straightValues.add(ints[5]);
        }
        //01345
        if((ints[0] == ints[1]-1) && (ints[1] == ints[3]-1) && (ints[3] == ints[4]-1) && (ints[4] == ints[5]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[1]);
            straightValues.add(ints[3]);
            straightValues.add(ints[4]);
            straightValues.add(ints[5]);
        }
        //02345
        if((ints[0] == ints[2]-1) && (ints[2] == ints[3]-1) && (ints[3] == ints[4]-1) && (ints[4] == ints[5]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[2]);
            straightValues.add(ints[3]);
            straightValues.add(ints[4]);
            straightValues.add(ints[5]);
        }
        //12345
        if((ints[1] == ints[2]-1) && (ints[2] == ints[3]-1) && (ints[3] == ints[4]-1) && (ints[4] == ints[5]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[1]);
            straightValues.add(ints[2]);
            straightValues.add(ints[3]);
            straightValues.add(ints[4]);
            straightValues.add(ints[5]);
        }
        //01236
        if((ints[0] == ints[1]-1) && (ints[1] == ints[2]-1) && (ints[2] == ints[3]-1) && (ints[3] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[1]);
            straightValues.add(ints[2]);
            straightValues.add(ints[3]);
            straightValues.add(ints[6]);
        }
        //01246
        if((ints[0] == ints[1]-1) && (ints[1] == ints[2]-1) && (ints[2] == ints[4]-1) && (ints[4] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[1]);
            straightValues.add(ints[2]);
            straightValues.add(ints[4]);
            straightValues.add(ints[6]);
        }
        //01346
        if((ints[0] == ints[1]-1) && (ints[1] == ints[3]-1) && (ints[3] == ints[4]-1) && (ints[4] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[1]);
            straightValues.add(ints[3]);
            straightValues.add(ints[4]);
            straightValues.add(ints[6]);
        }
        //02346
        if((ints[0] == ints[2]-1) && (ints[2] == ints[3]-1) && (ints[3] == ints[4]-1) && (ints[4] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[2]);
            straightValues.add(ints[3]);
            straightValues.add(ints[4]);
            straightValues.add(ints[6]);
        }
        //12346
        if((ints[1] == ints[2]-1) && (ints[2] == ints[3]-1) && (ints[3] == ints[4]-1) && (ints[4] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[1]);
            straightValues.add(ints[2]);
            straightValues.add(ints[3]);
            straightValues.add(ints[4]);
            straightValues.add(ints[6]);
        }
        //01256
        if((ints[0] == ints[1]-1) && (ints[1] == ints[2]-1) && (ints[2] == ints[5]-1) && (ints[5] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[1]);
            straightValues.add(ints[2]);
            straightValues.add(ints[5]);
            straightValues.add(ints[6]);
        }
        //01356
        if((ints[0] == ints[1]-1) && (ints[1] == ints[3]-1) && (ints[3] == ints[5]-1) && (ints[5] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[1]);
            straightValues.add(ints[3]);
            straightValues.add(ints[5]);
            straightValues.add(ints[6]);
        }
        //02356
        if((ints[0] == ints[2]-1) && (ints[2] == ints[3]-1) && (ints[3] == ints[5]-1) && (ints[5] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[2]);
            straightValues.add(ints[3]);
            straightValues.add(ints[5]);
            straightValues.add(ints[6]);
        }
        //12356
        if((ints[1] == ints[2]-1) && (ints[2] == ints[3]-1) && (ints[3] == ints[5]-1) && (ints[5] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[1]);
            straightValues.add(ints[2]);
            straightValues.add(ints[3]);
            straightValues.add(ints[5]);
            straightValues.add(ints[6]);
        }
        //01456
        if((ints[0] == ints[1]-1) && (ints[1] == ints[4]-1) && (ints[4] == ints[5]-1) && (ints[5] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[1]);
            straightValues.add(ints[4]);
            straightValues.add(ints[5]);
            straightValues.add(ints[6]);
        }
        //02456
        if((ints[0] == ints[2]-1) && (ints[2] == ints[4]-1) && (ints[4] == ints[5]-1) && (ints[5] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[2]);
            straightValues.add(ints[4]);
            straightValues.add(ints[5]);
            straightValues.add(ints[6]);
        }
        //12456
        if((ints[1] == ints[2]-1) && (ints[2] == ints[4]-1) && (ints[4] == ints[5]-1) && (ints[5] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[1]);
            straightValues.add(ints[2]);
            straightValues.add(ints[4]);
            straightValues.add(ints[5]);
            straightValues.add(ints[6]);
        }
        //03456
        if((ints[0] == ints[3]-1) && (ints[3] == ints[4]-1) && (ints[4] == ints[5]-1) && (ints[5] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[3]);
            straightValues.add(ints[4]);
            straightValues.add(ints[5]);
            straightValues.add(ints[6]);
        }
        //13456
        if((ints[1] == ints[3]-1) && (ints[3] == ints[4]-1) && (ints[4] == ints[5]-1) && (ints[5] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[1]);
            straightValues.add(ints[3]);
            straightValues.add(ints[4]);
            straightValues.add(ints[5]);
            straightValues.add(ints[6]);
        }
        //23456
        if((ints[2] == ints[3]-1) && (ints[3] == ints[4]-1) && (ints[4] == ints[5]-1) && (ints[5] == ints[6]-1)){
            straight = true;
            if(straightValues.size() == 5){
                if(straightValues2.size() == 5){
                    straightValues3.clear();
                    straightValues3.addAll(straightValues2);
                }
                straightValues2.clear();
                straightValues2.addAll(straightValues);
            }
            straightValues.clear();
            straightValues.add(ints[2]);
            straightValues.add(ints[3]);
            straightValues.add(ints[4]);
            straightValues.add(ints[5]);
            straightValues.add(ints[6]);
        }

        //checks for flush, stores values in flushValues
        flush = false;
        String[] cards = new String[7]; //holds the cards
        cards[0] = a;
        cards[1] = b;
        for(int i = 2; i<cards.length; i++){
            cards[i] = flopped[i-2];
        }
        int clubs = 0;
        int diamonds = 0;
        int hearts = 0;
        int spades = 0;
        //check if theres a flush
        for(int i = 0; i < cards.length; i++){
            char c = cards[i].charAt(1);
            if(c == 'c'){
                clubs++;
            }else if(c == 's'){
                spades++;
            }else if(c == 'h'){
                hearts++;
            }else{
                diamonds++;
            }
        }
        //fill flushValues with appropriate values
        if(spades >= 5){
            flush = true;
            for(int i = 0; i < cards.length; i++){
                if(cards[i].charAt(1) == 's'){
                    if(cards[i].charAt(0) == '1'){
                        flushValues.add(14);
                    }else if(cards[i].charAt(0) == '~'){
                        flushValues.add(10);
                    }else if(cards[i].charAt(0) == 'j'){
                        flushValues.add(11);
                    }else if(cards[i].charAt(0) == 'q'){
                        flushValues.add(12);
                    }else if(cards[i].charAt(0) == 'k'){
                        flushValues.add(13);
                    }else{
                        flushValues.add(Character.getNumericValue(cards[i].charAt(0)));
                    }
                }
            }
        }
        if(clubs >= 5){
            flush = true;
            for(int i = 0; i < cards.length; i++){
                if(cards[i].charAt(1) == 'c'){
                    if(cards[i].charAt(0) == '1'){
                        flushValues.add(14);
                    }else if(cards[i].charAt(0) == '~'){
                        flushValues.add(10);
                    }else if(cards[i].charAt(0) == 'j'){
                        flushValues.add(11);
                    }else if(cards[i].charAt(0) == 'q'){
                        flushValues.add(12);
                    }else if(cards[i].charAt(0) == 'k'){
                        flushValues.add(13);
                    }else{
                        flushValues.add(Character.getNumericValue(cards[i].charAt(0)));
                    }
                }
            }
        }
        if(diamonds >= 5){
            flush = true;
            for(int i = 0; i < cards.length; i++){
                if(cards[i].charAt(1) == 'd'){
                    if(cards[i].charAt(0) == '1'){
                        flushValues.add(14);
                    }else if(cards[i].charAt(0) == '~'){
                        flushValues.add(10);
                    }else if(cards[i].charAt(0) == 'j'){
                        flushValues.add(11);
                    }else if(cards[i].charAt(0) == 'q'){
                        flushValues.add(12);
                    }else if(cards[i].charAt(0) == 'k'){
                        flushValues.add(13);
                    }else{
                        flushValues.add(Character.getNumericValue(cards[i].charAt(0)));
                    }
                }
            }
        }
        if(hearts >= 5){
            flush = true;
            for(int i = 0; i < cards.length; i++){
                if(cards[i].charAt(1) == 'h'){
                    if(cards[i].charAt(0) == '1'){
                        flushValues.add(14);
                    }else if(cards[i].charAt(0) == '~'){
                        flushValues.add(10);
                    }else if(cards[i].charAt(0) == 'j'){
                        flushValues.add(11);
                    }else if(cards[i].charAt(0) == 'q'){
                        flushValues.add(12);
                    }else if(cards[i].charAt(0) == 'k'){
                        flushValues.add(13);
                    }else{
                        flushValues.add(Character.getNumericValue(cards[i].charAt(0)));
                    }
                }
            }
        }
        //ensure flushValues is ascending order
        Collections.sort(flushValues);
        for(int x: flushValues){
            flushValuesTemp.add(x);
        }
        //account for multiple flushes (useful in straight-flush detection)
        if(flushValues.size() == 6){
            int[] values = new int[6];
            for(int i = 0; i < values.length; i++){
                values[i] = flushValues.get(i);
            }
            flushValues.clear();
            flushValues.add(values[1]);
            flushValues.add(values[2]);
            flushValues.add(values[3]);
            flushValues.add(values[4]);
            flushValues.add(values[5]);
            flushValues2.add(values[0]);
            flushValues2.add(values[1]);
            flushValues2.add(values[2]);
            flushValues2.add(values[3]);
            flushValues2.add(values[4]);
        }
        if(flushValues.size() == 7){
            int[] values = new int[7];
            for(int i = 0; i < values.length; i++){
                values[i] = flushValues.get(i);
            }
            flushValues.clear();
            flushValues.add(values[2]);
            flushValues.add(values[3]);
            flushValues.add(values[4]);
            flushValues.add(values[5]);
            flushValues.add(values[6]);
            flushValues2.add(values[1]);
            flushValues2.add(values[2]);
            flushValues2.add(values[3]);
            flushValues2.add(values[4]);
            flushValues2.add(values[5]);
            flushValues3.add(values[0]);
            flushValues3.add(values[1]);
            flushValues3.add(values[2]);
            flushValues3.add(values[3]);
            flushValues3.add(values[4]);
        }
              
        boolean sf = false;
        if(straight && flush){
            Collections.sort(straightValues);
            Collections.sort(straightValues2);
            Collections.sort(straightValues3);
            if(straightValues.equals(flushValues)){
                sf = true;
            }
            if(flushValues2.size() > 1){
                if(straightValues.equals(flushValues2)){
                    sf = true;
                }
            }
            if(flushValues3.size() > 1){
                if(straightValues.equals(flushValues3)){
                    sf = true;
                }
            }
            if(straightValues2.size() > 1){
                if(straightValues2.equals(flushValues)){
                    sf = true;
                }
            }
            if(straightValues2.size() > 1 && flushValues2.size() > 1){
                if(straightValues2.equals(flushValues2)){
                    sf = true;
                }
            }
            if(straightValues2.size() > 1 && flushValues3.size() > 1){
                if(straightValues2.equals(flushValues3)){
                    sf = true;
                }
            }
            if(straightValues3.size() > 1){
                if(straightValues3.equals(flushValues)){
                    sf = true;
                }
            }
            if(straightValues3.size() > 1 && flushValues2.size() > 1){
                if(straightValues3.equals(flushValues2)){
                    sf = true;
                }
            }
            if(straightValues3.size() > 1 && flushValues3.size() > 1){
                if(straightValues3.equals(flushValues3)){
                    sf = true;
                }
            }
        }
        //determine general rank of hand
        if(sf){
                rankGeneral = "Straight flush";
        }else if(fourOfAKind){
            rankGeneral = "Four of a kind";
        }else if((tPairs == 1)&&(sPairs == 1)){
            rankGeneral = "Full house";
        }else if(flush){
            rankGeneral = "Flush";
        }else if(straight){
            rankGeneral = "Straight";
        }else if(tPairs == 1){
            rankGeneral = "Three of a kind";
        }else if(sPairs >= 2){
            rankGeneral = "Two pairs";
        }else if(sPairs == 1){
            rankGeneral = "One pair";
        }else{
            rankGeneral = "High card";
        } 
        
        //evaluate finalRank
        //rank from 0 - 7
        if(rankGeneral == "High card"){
            int max = 0;
            for(int i = 0; i < ints.length; i++){
                if(ints[i] > max){
                    max = ints[i];
                }
                finalRank = max-7;
            }
        }
        //rank from 8-20
        if(rankGeneral == "One pair"){
            finalRank = sValueA + 6;
        }
        //rank from 21-98
        if(rankGeneral == "Two pairs"){
            //if 3 pairs, store top 2 in sValueA and sValueB
            if(sPairs == 3){
                if((sValueA < sValueB) && (sValueA < sValueC)){
                    sValueA = sValueC;
                }else if((sValueB < sValueA) && (sValueB < sValueC)){
                    sValueB = sValueC;
                }
            }
            //ensure sValueA stores the greatest pair
            if(sValueB > sValueA){
                int temp = sValueB;
                sValueB = sValueA;
                sValueA = temp;
            }
            int rank = 21;
            //first pair
            for(int i = 3; i <= sValueA; i++){
                //second pair
                for(int j = 2; j != i; j++){
                    if(sValueA == i && sValueB == j){
                        finalRank = rank;
                    }
                    rank++;
                }
            }
        }
        //rank from 99-111
       if(rankGeneral == "Three of a kind"){
           int rank = 99;
            for(int i = 2; i<=tValueA; i++){
                if(i == tValueA){
                    finalRank = rank;
                }
                rank++;
            }
        }
        //rank from 112-121
        if(rankGeneral == "Straight"){
            int rank = 112;
            if(straightValues.get(0) == 14){
                finalRank = rank;
            }else{
                rank++;
                for(int i = 2; i <= straightValues.get(0); i++){
                    if(i == straightValues.get(0)){
                        finalRank = rank;
                    }
                    rank++;
                }
            }
        }
        //handle flushes in main, allocate a rank of 122
        if(rankGeneral == "Flush"){
            finalRank = 122;
        }
        //rank from 123-278
        if(rankGeneral == "Full house"){
            //ensure sValueA has greatest pair, in case theres 2 pairs and three of a kind
            if(sValueA < sValueB){
                int temp = 0;
                temp = sValueB;
                sValueB = sValueA;
                sValueA = temp;
            }
            int rank = 123;
            //checks 3 of a kind
            for(int i = 2; i <= 14; i++){
                //checks pair
                for(int j = 2; j<=14; j++){
                    if((tValueA == i) && (sValueA == j)){
                        finalRank = rank;
                        break;
                    }
                    if(i != j){
                        rank++;
                    }
                }
            }
        }
        //rank from 279-291
        if(rankGeneral == "Four of a kind"){
            int rank = 279;
            for(int i = 2; i<=14; i++){
                if(qValueA == i){
                    finalRank = rank;
                    break;
                }
                rank++;
            }
        }
        //rank from 292-301
        if(rankGeneral == "Straight flush"){

            //ensure proper straight and flush overlap
            int a = 0;
            if((straightValues.equals(flushValues))||(straightValues.equals(flushValues2))||(straightValues.equals(flushValues3))){
                a = 1;
            }else if((straightValues2.equals(flushValues))||(straightValues2.equals(flushValues2))||(straightValues2.equals(flushValues3))){
                a = 2;
            }else{
                a = 3;
            }
            int rank = 292;
            if(a == 1){
                //check if straight starts with ace
                if((straightValues.get(4) == 14) && (straightValues.get(3) == 5)){
                    finalRank = rank;
                }else{
                    rank++;
                    for(int i = 6; i <= 14; i++){
                        if(i == straightValues.get(4)){
                            finalRank = rank;
                            break;
                        }
                        rank++;
                    }
                }
            }
            if(a == 2){
                //check if straight starts with ace
                if((straightValues2.get(4) == 14) && (straightValues2.get(3) == 5)){//error
                    finalRank = rank;
                }else{
                    rank++;
                    for(int i = 6; i <= 14; i++){
                        if(i == straightValues2.get(4)){
                            finalRank = rank;
                            break;
                        }
                        rank++;
                    }
                }
            }
            if(a == 3){
                //check if straight starts with ace
                if((straightValues3.get(4) == 14) && (straightValues3.get(3) == 5)){//error
                    finalRank = rank;
                }else{
                    rank++;
                    for(int i = 6; i <= 14; i++){
                        if(i == straightValues3.get(4)){
                            finalRank = rank;
                            break;
                        }
                        rank++;
                    }
                }

            }

        }
        return finalRank;
    }
    //finds greatest of two cards, if equal return 0
    public int findGreatestCard(){
        int y = Character.getNumericValue(a.charAt(0));
        int z = Character.getNumericValue(b.charAt(0));
        int max = 0;
        if(y > z){
            max = y;
        }
        if(z > y){
            max = z;
        }
        return max;
    }
    
    //finds smaller of two cards, if equal return 0
    public int findSmallestCard(){
        int y = Character.getNumericValue(a.charAt(0));
        int z = Character.getNumericValue(b.charAt(0));
        int min = 0;
        if(y > z){
            min = z;
        }
        if(z > y){
            min = y;
        }
        return min;
    }

     
    public String getRank(){
        return rankGeneral;
    }   
    public String getA(){
        return a;
    }
    public String getB(){
        return b;
    }
    public int getValueA(){
        return sValueA;
    }
    public int getValueB(){
        return sValueB;
    }
    public int getValueC(){
        return sValueC;
    }
    public int getTValueA(){
        return tValueA;
    }
    public int getTValueB(){
        return tValueB;
    }
    public int getQValueA(){
        return qValueA;
    }
    public ArrayList<Integer> getFlushValues(){
        return flushValues;
    }
    public ArrayList<Integer> getFlushValuesTemp(){
        return flushValuesTemp;
    }
    public ArrayList<Integer> getStraightValues(){
        return straightValues;
    }
    //return a card of the chosen index
    public int getCardsSorted(int index, Deck d){
        String[] flopped = d.getFlopped();
        int[] ints = new int[7];
        ints[0] = Rank.convert(a.charAt(0));
            if(ints[0] == 0){
                ints[0] = Character.getNumericValue(a.charAt(0));
            }
        ints[1] = Rank.convert(b.charAt(0));
            if(ints[1] == 0){
                ints[1] = Character.getNumericValue(a.charAt(0));
            }
        for(int i = 2; i < ints.length; i++){
        ints[i] = Rank.convert(flopped[i-2].charAt(0));
            if(ints[i] == 0){
                ints[i] = Character.getNumericValue(flopped[i-2].charAt(0));
            }
        }
        Arrays.sort(ints);
        return ints[index];
    }
    //return a sorted list of the players cards
    public ArrayList<Integer> getAllCardsSorted(Deck d){
        String[] flopped = d.getFlopped();
        int[] ints = new int[7];
        ints[0] = Rank.convert(a.charAt(0));
            if(ints[0] == 0){
                ints[0] = Character.getNumericValue(a.charAt(0));
            }
        ints[1] = Rank.convert(b.charAt(0));
            if(ints[1] == 0){
                ints[1] = Character.getNumericValue(a.charAt(0));
            }
        for(int i = 2; i < ints.length; i++){
        ints[i] = Rank.convert(flopped[i-2].charAt(0));
            if(ints[i] == 0){
                ints[i] = Character.getNumericValue(flopped[i-2].charAt(0));
            }
        }
        Arrays.sort(ints);
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for(int x: ints){
            ret.add(x);
        }
        return ret;
    }
}