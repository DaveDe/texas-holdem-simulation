package simulation;

import java.util.Arrays;

//determines rank of a hand
//also has a bunch of static methods
public class Rank {

    static String[] possibleHands = {"11 n","1k s","1q s","1j s","1~ s","19 s","18 s","17 s","16 s","15 s","14 s",
    "13 s","12 s","1k n","1q n","1j n","1~ n","19 n","18 n","17 n","16 n","15 n","14 n","13 n","12 n","kk n","kq s","kj s",
    "k~ s","k9 s","k8 s","k7 s","k6 s","k5 s","k4 s","k3 s","k2 s","kq n","kj n","k~ n","k9 n","k8 n","k7 n","k6 n",
    "k5 n","k4 n","k3 n","k2 n","qq n","qj s","q~ s","q9 s","q8 s","q7 s","q6 s","q5 s","q4 s","q3 s","q2 s","qj n",
    "q~ n","q9 n","q8 n","q7 n","q6 n","q5 n","q4 n","q3 n","q2 n","jj n","j~ s","j9 s","j8 s","j7 s","j6 s","j5 s",
    "j4 s","j3 s","j2 s","j~ n","j9 n","j8 n","j7 n","j6 n","j5 n","j4 n","j3 n","j2 n","~~ n","~9 s","~8 s","~7 s",
    "~6 s","~5 s","~4 s","~3 s","~2 s","~9 n","~8 n","~7 n","~6 n","~5 n","~4 n","~3 n","~2 n","99 n","98 s","97 s",
    "96 s","95 s","94 s","93 s","92 s","98 n","97 n","96 n","95 n","94 n","93 n","92 n","88 n","87 s","86 s","85 s",
    "84 s","83 s","82 s","87 n","86 n","85 n","84 n","83 n","82 n","77 n","76 s","75 s","74 s","73 s","72 s","76 n",
    "75 n","74 n","73 n","72 n","66 n","65 s","64 s","63 s","62 s","65 n","64 n","63 n","62 n","55 n","54 s","53 s",
    "52 s","54 n","53 n","52 n","44 n","43 s","42 s","43 n","42 n","33 n","32 s","32 n","22 n","tie "};//ace is 1, 10 is ~, s means suited, n means not suited
  /*  possibleHands[0] = "11 n";
    possibleHands[1] = "1k s";
    possibleHands[2] = "1q s";
    possibleHands[3] = "1j s";
    possibleHands[4] = "1~ s";
    possibleHands[5] = "19 s";
    possibleHands[6] = "18 s";
    possibleHands[7] = "17 s";
    possibleHands[8] = "16 s";
    possibleHands[9] = "15 s";
    possibleHands[10] = "14 s";
    possibleHands[11] = "13 s";
    possibleHands[12] = "12 s";
    possibleHands[13] = "1k n";
    possibleHands[14] = "1q n";
    possibleHands[15] = "1j n";
    possibleHands[16] = "1~ n";
    possibleHands[17] = "19 n";
    possibleHands[18] = "18 n";
    possibleHands[19] = "17 n";
    possibleHands[20] = "16 n";
    possibleHands[21] = "15 n";
    possibleHands[22] = "14 n";
    possibleHands[23] = "13 n";
    possibleHands[24] = "12 n";
    possibleHands[25] = "kk n";
    possibleHands[26] = "kq s";
    possibleHands[27] = "kj s";
    possibleHands[28] = "k~ s";
    possibleHands[29] = "k9 s";
    possibleHands[30] = "k8 s";
    possibleHands[31] = "k7 s";
    possibleHands[32] = "k6 s";
    possibleHands[33] = "k5 s";
    possibleHands[34] = "k4 s";
    possibleHands[35] = "k3 s";
    possibleHands[36] = "k2 s";
    possibleHands[37] = "kq n";
    possibleHands[38] = "kj n";
    possibleHands[39] = "k~ n";
    possibleHands[40] = "k9 n";
    possibleHands[41] = "k8 n";
    possibleHands[42] = "k7 n";
    possibleHands[43] = "k6 n";
    possibleHands[44] = "k5 n";
    possibleHands[45] = "k4 n";
    possibleHands[46] = "k3 n";
    possibleHands[47] = "k2 n";
    possibleHands[48] = "qq n";
    possibleHands[49] = "qj s";
    possibleHands[50] = "q~ s";
    possibleHands[51] = "q9 s";
    possibleHands[52] = "q8 s";
    possibleHands[53] = "q7 s";
    possibleHands[54] = "q6 s";
    possibleHands[55] = "q5 s";
    possibleHands[56] = "q4 s";
    possibleHands[57] = "q3 s";
    possibleHands[58] = "q2 s";
    possibleHands[59] = "qj n";
    possibleHands[60] = "q~ n";
    possibleHands[61] = "q9 n";
    possibleHands[62] = "q8 n";
    possibleHands[63] = "q7 n";
    possibleHands[64] = "q6 n";
    possibleHands[65] = "q5 n";
    possibleHands[66] = "q4 n";
    possibleHands[67] = "q3 n";
    possibleHands[68] = "q2 n";
    possibleHands[69] = "jj n";
    possibleHands[70] = "j~ s";
    possibleHands[71] = "j9 s";
    possibleHands[72] = "j8 s";
    possibleHands[73] = "j7 s";
    possibleHands[74] = "j6 s";
    possibleHands[75] = "j5 s";
    possibleHands[76] = "j4 s";
    possibleHands[77] = "j3 s";
    possibleHands[78] = "j2 s";
    possibleHands[79] = "j~ n";
    possibleHands[80] = "j9 n";
    possibleHands[81] = "j8 n";
    possibleHands[82] = "j7 n";
    possibleHands[83] = "j6 n";
    possibleHands[84] = "j5 n";
    possibleHands[85] = "j4 n";
    possibleHands[86] = "j3 n";
    possibleHands[87] = "j2 n";
    possibleHands[88] = "~~ n";
    possibleHands[89] = "~9 s";
    possibleHands[90] = "~8 s";
    possibleHands[91] = "~7 s";
    possibleHands[92] = "~6 s";
    possibleHands[93] = "~5 s";
    possibleHands[94] = "~4 s";
    possibleHands[95] = "~3 s";
    possibleHands[96] = "~2 s";
    possibleHands[97] = "~9 n";
    possibleHands[98] = "~8 n";
    possibleHands[99] = "~7 n";
    possibleHands[100] = "~6 n";
    possibleHands[101] = "~5 n";
    possibleHands[102] = "~4 n";
    possibleHands[103] = "~3 n";
    possibleHands[104] = "~2 n";
    possibleHands[105] = "99 n";
    possibleHands[106] = "98 s";
    possibleHands[107] = "97 s";
    possibleHands[108] = "96 s";
    possibleHands[109] = "95 s";
    possibleHands[110] = "94 s";
    possibleHands[111] = "93 s";
    possibleHands[112] = "92 s";
    possibleHands[113] = "98 n";
    possibleHands[114] = "97 n";
    possibleHands[115] = "96 n";
    possibleHands[116] = "95 n";
    possibleHands[117] = "94 n";
    possibleHands[118] = "93 n";
    possibleHands[119] = "92 n";
    possibleHands[120] = "88 n";
    possibleHands[121] = "87 s";
    possibleHands[122] = "86 s";
    possibleHands[123] = "85 s";
    possibleHands[124] = "84 s";
    possibleHands[125] = "83 s";
    possibleHands[126] = "82 s";
    possibleHands[127] = "87 n";
    possibleHands[128] = "86 n";
    possibleHands[129] = "85 n";
    possibleHands[130] = "84 n";
    possibleHands[131] = "83 n";
    possibleHands[132] = "82 n";
    possibleHands[133] = "77 n";
    possibleHands[134] = "76 s";
    possibleHands[135] = "75 s";
    possibleHands[136] = "74 s";
    possibleHands[137] = "73 s";
    possibleHands[138] = "72 s";
    possibleHands[139] = "76 n";
    possibleHands[140] = "75 n";
    possibleHands[141] = "74 n";
    possibleHands[142] = "73 n";
    possibleHands[143] = "72 n";
    possibleHands[144] = "66 n";
    possibleHands[145] = "65 s";
    possibleHands[146] = "64 s";
    possibleHands[147] = "63 s";
    possibleHands[148] = "62 s";
    possibleHands[149] = "65 n";
    possibleHands[150] = "64 n";
    possibleHands[151] = "63 n";
    possibleHands[152] = "62 n";
    possibleHands[153] = "55 n";
    possibleHands[154] = "54 s";
    possibleHands[155] = "53 s";
    possibleHands[156] = "52 s";
    possibleHands[157] = "54 n";
    possibleHands[158] = "53 n";
    possibleHands[159] = "52 n";
    possibleHands[160] = "44 n";
    possibleHands[161] = "43 s";
    possibleHands[162] = "42 s";
    possibleHands[163] = "43 n";
    possibleHands[164] = "42 n";
    possibleHands[165] = "33 n";
    possibleHands[166] = "32 s";
    possibleHands[167] = "32 n";
    possibleHands[168] = "22 n";*/
    
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
