package simulation;

import java.util.Arrays;

//determines rank of a hand
//also has a bunch of static methods
public class Rank {
    
    
    //returns String of winning player
    //if top 5 cards are the same then its a tie
    public static String compareHighCards(int[] a, int[] b){
        String winner = "tie";
        for(int i = 6; i>=2; i--){
            if(a[i] > b[i]){
                winner = "player 1";
                i = 1;
            }
            if(a[i] < b[i]){
                winner = "player 2";
                i = 1;
            }
            
        }
        return winner;//all cards match, its a tie
    }
    //get a players hand as chars
    public static char[] getChars(String a, String b, String[] c){
        char[] chars = new char[7];        
        chars[0] = a.charAt(0);
        chars[1] = b.charAt(0);
        for(int i = 0; i<c.length; i++){
            chars[i+2] = c[i].charAt(0);
        }
        Arrays.sort(chars); 
        return chars;
    }
    //get a players hand as ints in ascending order
    public static int[] getInts(char[] chars){  
        int[] ints = new int[7];
        for(int i = 0; i<chars.length; i++){
            if(chars[i] == '~'){
                ints[i] = 10;
            }else if(chars[i] == 'j'){
                ints[i] = 11;
            }else if(chars[i] == 'q'){
                ints[i] = 12;
            }else if(chars[i] == 'k'){
                ints[i] = 13;
            }else if(chars[i] == '1'){
                ints[i] = 14;
            }else{
                ints[i] = Character.getNumericValue(chars[i]);
            }
        }
        Arrays.sort(ints);
        return ints;
    }
  
    public static int convert(char c){
        int num = 0;
        if(c == '~'){
            num = 10;
        }else if(c == 'j'){
            num = 11;
        }else if(c == 'q'){
            num = 12;
        }else if(c == 'k'){
            num = 13;
        }else if(c == '1'){
            num = 14;
        }else{
            num = Character.getNumericValue(c);
        }
        return num;
    }
    //find max of 3 numbers
    public static int max(int a, int b, int c){
        int max = 0;
        if((a > b) && (a > c)){
           max = a; 
        }
        if((b > a) && (b > c)){
           max = b; 
        }
        if((c > a) && (c > b)){
           max = c; 
        }
        return max;
    }
    //find min of 3 numbers
    public static int min(int a, int b, int c){
        int min = 14;
        if((a < b) && (a < c)){
           min = a; 
        }
        if((b < a) && (b < c)){
           min = b; 
        }
        if((c < a) && (c < b)){
           min = c; 
        }
        return min;
    }      
}
