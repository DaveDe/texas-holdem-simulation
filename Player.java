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
    private int qValueA;//stores value of 4 of a kind
    private boolean straight;
    private boolean flush;
    private ArrayList<Integer> straightValues = new ArrayList<Integer>(); //stores values of a straight in descending order
    private ArrayList<Integer> flushValues = new ArrayList<Integer>(); //stores values of a flush in descending order
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
               tPairs++;
               tValueA = ints[i];
           }
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
        //01
        if(ints[0] == ints[1]-1){
            //012
            if(ints[1] == ints[2]-1){
                //0123                
                if(ints[2] == ints[3]-1){
                    //01234
                    if(ints[3] == ints[4]-1){
                        straight = true;
                        straightValues.clear();
                        straightValues.add(ints[0]);
                        straightValues.add(ints[1]);
                        straightValues.add(ints[2]);
                        straightValues.add(ints[3]);
                        straightValues.add(ints[4]);
                    }
                    //01235
                    else if(ints[3] == ints[5]-1){
                        straight = true;
                        straightValues.clear();
                        straightValues.add(ints[0]);
                        straightValues.add(ints[1]);
                        straightValues.add(ints[2]);
                        straightValues.add(ints[3]);
                        straightValues.add(ints[5]);
                    }
                    //01236
                    else if(ints[3] == ints[6]-1){
                        straight = true;
                        straightValues.clear();
                        straightValues.add(ints[0]);
                        straightValues.add(ints[1]);
                        straightValues.add(ints[2]);
                        straightValues.add(ints[3]);
                        straightValues.add(ints[6]);
                    }
                }
                //0124
                if(ints[2] == ints[4]-1){
                    //01245
                    if(ints[4] == ints[5]-1){
                        straight = true;
                        straightValues.clear();
                        straightValues.add(ints[0]);
                        straightValues.add(ints[1]);
                        straightValues.add(ints[2]);
                        straightValues.add(ints[4]);
                        straightValues.add(ints[5]);
                    }
                    //01246
                    else if(ints[4] == ints[6]-1){
                        straight = true;
                        straightValues.clear();
                        straightValues.add(ints[0]);
                        straightValues.add(ints[1]);
                        straightValues.add(ints[2]);
                        straightValues.add(ints[4]);
                        straightValues.add(ints[6]);
                    }
                }
                //01256
                if((ints[2] == ints[5]-1) && (ints[5] == ints[6]-1)){
                    straight = true;
                    straightValues.clear();
                    straightValues.add(ints[0]);
                    straightValues.add(ints[1]);
                    straightValues.add(ints[2]);
                    straightValues.add(ints[5]);
                    straightValues.add(ints[6]);
                }
            }
            //013
            else if(ints[1] == ints[3]-1){
                //0134
                if(ints[3] == ints[4]-1){
                    //01345
                    if(ints[4] == ints[5]-1){
                        straight = true;
                        straightValues.clear();
                        straightValues.add(ints[0]);
                        straightValues.add(ints[1]);
                        straightValues.add(ints[3]);
                        straightValues.add(ints[4]);
                        straightValues.add(ints[5]);
                    }
                    //01346
                    else if(ints[4] == ints[6]-1){
                        straight = true;
                        straightValues.clear();
                        straightValues.add(ints[0]);
                        straightValues.add(ints[1]);
                        straightValues.add(ints[3]);
                        straightValues.add(ints[4]);
                        straightValues.add(ints[6]);
                    }
                }
                //01356
                if((ints[3] == ints[5]-1)&&(ints[5] == ints[6]-1)){
                    straight = true;
                    straightValues.clear();
                    straightValues.add(ints[0]);
                    straightValues.add(ints[1]);
                    straightValues.add(ints[3]);
                    straightValues.add(ints[5]);
                    straightValues.add(ints[6]);
                }
            }
            //01456
            else if((ints[1] == ints[4]-1)&&(ints[4] == ints[5]-1)&&(ints[5] == ints[6]-1)){
                straight = true;
                straightValues.clear();
                straightValues.add(ints[0]);
                straightValues.add(ints[1]);
                straightValues.add(ints[4]);
                straightValues.add(ints[5]);
                straightValues.add(ints[6]);
            }
        }
        //02
        else if(ints[0] == ints[2]-1){
            //023
            if(ints[2] == ints[3]-1){
                //0234
                if(ints[3] == ints[4]-1){
                    //02345
                    if(ints[4] == ints[5]-1){
                        straight = true;
                        straightValues.clear();
                        straightValues.add(ints[0]);
                        straightValues.add(ints[2]);
                        straightValues.add(ints[3]);
                        straightValues.add(ints[4]);
                        straightValues.add(ints[5]);
                    }
                    //02346
                    else if(ints[4] == ints[6]-1){
                        straight = true;
                        straightValues.clear();
                        straightValues.add(ints[0]);
                        straightValues.add(ints[2]);
                        straightValues.add(ints[3]);
                        straightValues.add(ints[4]);
                        straightValues.add(ints[6]);
                    }
                }
                //02356
                else if((ints[3] == ints[5]-1)&&(ints[5] == ints[6]-1)){
                    straight = true;
                    straightValues.clear();
                    straightValues.add(ints[0]);
                    straightValues.add(ints[2]);
                    straightValues.add(ints[3]);
                    straightValues.add(ints[5]);
                    straightValues.add(ints[6]);
                }
            }
            //02456
            else if((ints[2] == ints[4]-1)&&(ints[4]==ints[5]-1)&&(ints[5]==ints[6]-1)){
                straight = true;
                straightValues.clear();
                straightValues.add(ints[0]);
                straightValues.add(ints[2]);
                straightValues.add(ints[4]);
                straightValues.add(ints[5]);
                straightValues.add(ints[6]);
            }
        }
        //03456
        else if((ints[0] == ints[3]-1)&&(ints[3] == ints[4]-1)&&(ints[4]==ints[5]-1)&&(ints[5]==ints[6]-1)){ 
            straight = true;
            straightValues.clear();
            straightValues.add(ints[0]);
            straightValues.add(ints[3]);
            straightValues.add(ints[4]);
            straightValues.add(ints[5]);
            straightValues.add(ints[6]);
        }
        //12
        else if(ints[1] == ints[2]-1){
            //123
            if(ints[2]==ints[3]-1){
                //1234
                if(ints[3]==ints[4]-1){
                    //12345
                    if(ints[4]==ints[5]-1){
                        straight = true;
                        straightValues.clear();
                        straightValues.add(ints[1]);
                        straightValues.add(ints[2]);
                        straightValues.add(ints[3]);
                        straightValues.add(ints[4]);
                        straightValues.add(ints[5]);
                    }
                    //12346
                    else if(ints[4]==ints[6]-1){
                        straight = true;
                        straightValues.clear();
                        straightValues.add(ints[1]);
                        straightValues.add(ints[2]);
                        straightValues.add(ints[3]);
                        straightValues.add(ints[4]);
                        straightValues.add(ints[6]);
                    }
                }
                //12356
                else if((ints[3]==ints[5]-1)&&(ints[5]==ints[6]-1)){
                    straight = true;
                    straightValues.clear();
                    straightValues.add(ints[1]);
                    straightValues.add(ints[2]);
                    straightValues.add(ints[3]);
                    straightValues.add(ints[5]);
                    straightValues.add(ints[6]);
                }
            }
            //12456
            else if((ints[2]==ints[4]-1)&&(ints[4]==ints[5]-1)&&(ints[5]==ints[6]-1)){
                straight = true;
                straightValues.clear();
                straightValues.add(ints[1]);
                straightValues.add(ints[2]);
                straightValues.add(ints[4]);
                straightValues.add(ints[5]);
                straightValues.add(ints[6]);
            }
        }
        //13456
        else if((ints[1] == ints[3]-1)&&(ints[3]==ints[4]-1)&&(ints[4]==ints[5]-1)&&(ints[5]==ints[6]-1)){   
            straight = true;
            straightValues.clear();
            straightValues.add(ints[1]);
            straightValues.add(ints[3]);
            straightValues.add(ints[4]);
            straightValues.add(ints[5]);
            straightValues.add(ints[6]);
        }
        //check for ace - 5 straight. previous checks didnt account for this specific type of straight
        if(Rank.findNum(ints, 14) && Rank.findNum(ints, 2) && Rank.findNum(ints, 3) && Rank.findNum(ints, 4) && Rank.findNum(ints, 5)){
            straight = true;
            straightValues.clear();
            straightValues.add(14);
            straightValues.add(2);
            straightValues.add(3);
            straightValues.add(4);
            straightValues.add(5);
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
        if(spades == 5){
            flush = true;
            for(int i = 0; i < cards.length; i++){
                if(cards[i].charAt(1) == 's'){
                    flushValues.add(Character.getNumericValue(cards[i].charAt(0)));
                }
            }
        }
        if(clubs == 5){
            flush = true;
            for(int i = 0; i < cards.length; i++){
                if(cards[i].charAt(1) == 'c'){
                    flushValues.add(Character.getNumericValue(cards[i].charAt(0)));
                }
            }
        }
        if(diamonds == 5){
            flush = true;
            for(int i = 0; i < cards.length; i++){
                if(cards[i].charAt(1) == 'd'){
                    flushValues.add(Character.getNumericValue(cards[i].charAt(0)));
                }
            }
        }
        if(hearts == 5){
            flush = true;
            for(int i = 0; i < cards.length; i++){
                if(cards[i].charAt(1) == 'h'){
                    flushValues.add(Character.getNumericValue(cards[i].charAt(0)));
                }
            }
        }
        //ensure flushValues is descending order
        Collections.sort(flushValues);
        
              
        //determine general rank of hand
        if(straight && flush && (flushValues == straightValues)){
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
        }else if(sPairs == 2){
            rankGeneral = "Two pairs";
        }else if(sPairs == 1){
            rankGeneral = "One pair";
        }else{
            rankGeneral = "High card";
        } 
        
        //evaluate finalRank
        if(rankGeneral == "High card"){
            int max = 0;
            for(int i = 0; i < ints.length; i++){
                if(ints[i] > max){
                    max = ints[i];
                }
                finalRank = max-7;
            }
        }
        if(rankGeneral == "One pair"){
            finalRank = sValueA + 6;
        }
        if(rankGeneral == "Two pairs"){
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
       if(rankGeneral == "Three of a kind"){
           int rank = 99;
            for(int i = 2; i<=tValueA; i++){
                if(i == tValueA){
                    finalRank = rank;
                }
                rank++;
            }
        }
        if(rankGeneral == "Straight"){
            int rank = 112;
            if(straightValues.get(0) == 14){
                finalRank = rank;
            }
            rank++;
            for(int i = 2; i <= straightValues.get(0); i++){
                if(i == straightValues.get(0)){
                    finalRank = rank;
                }
                rank++;
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
    public int getQValueA(){
        return qValueA;
    }
}