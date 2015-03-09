package simulation;

public class Compare{
	
	public Compare(){}

	//returns hand of winning player as a string
	public String winningHand(Player p1, Player p2, Player p3, Player p4, Player p5, Player p6, Player p7, Player p8, Deck d){

		int[] p1Cards = Rank.getInts(Rank.getChars(p1.getA(), p1.getB(), d.getFlopped()));//all of p1 cards(dealt and flopped)
        int[] p2Cards = Rank.getInts(Rank.getChars(p2.getA(), p2.getB(), d.getFlopped()));//stored in ascending order, ace is 14
        int[] p3Cards = Rank.getInts(Rank.getChars(p3.getA(), p3.getB(), d.getFlopped()));
        int[] p4Cards = Rank.getInts(Rank.getChars(p4.getA(), p4.getB(), d.getFlopped()));
        int[] p5Cards = Rank.getInts(Rank.getChars(p5.getA(), p5.getB(), d.getFlopped()));
        int[] p6Cards = Rank.getInts(Rank.getChars(p6.getA(), p6.getB(), d.getFlopped()));
        int[] p7Cards = Rank.getInts(Rank.getChars(p7.getA(), p7.getB(), d.getFlopped()));
        int[] p8Cards = Rank.getInts(Rank.getChars(p8.getA(), p8.getB(), d.getFlopped()));
        int[] ranks = new int[8]
        ranks[0] = p1.rankOfHand(d); //rank of p1
        ranks[1] = p2.rankOfHand(d);
        ranks[2] = p3.rankOfHand(d);
        ranks[3] = p4.rankOfHand(d);
        ranks[4] = p5.rankOfHand(d);
        ranks[5] = p6.rankOfHand(d);
        ranks[6] = p7.rankOfHand(d);
        ranks[7] = p8.rankOfHand(d);

        //print flopped cards
        for(String x: d.getFlopped()){
            System.out.print(x+ " ");
        }
        System.out.println();
    	//print player cards and corresponding rank
        for(int x: p1Cards){
            System.out.print(x+ " ");
        }
        System.out.println();
        System.out.println(ranks[0]);
        for(int x: p2Cards){
            System.out.print(x+ " ");
        }
        System.out.println();
        System.out.println(ranks[1]);
        for(int x: p3Cards){
            System.out.print(x+ " ");
        }
        System.out.println();
        System.out.println(ranks[2]);
        for(int x: p4Cards){
            System.out.print(x+ " ");
        }
        System.out.println();
        System.out.println(ranks[3]);
        for(int x: p5Cards){
            System.out.print(x+ " ");
        }
        System.out.println();
        System.out.println(ranks[4]);
        for(int x: p6Cards){
            System.out.print(x+ " ");
        }
        System.out.println();
        System.out.println(ranks[5]);
        for(int x: p7Cards){
            System.out.print(x+ " ");
        }
        System.out.println();
        System.out.println(ranks[6]);
        for(int x: p8Cards){
            System.out.print(x+ " ");
        }
        System.out.println();
        System.out.println(ranks[7]);

        // find winner
        String winner = "tie";
        Arrays.sort(ranks);
        //find if the top ranks are tied, store them in an arraylist
        ArrayList<Integer> tied = new ArrayList<Integer>();
        tied.add(ranks[7]);
        for(int i = 6; i >= 0; i--){
        	if(ranks[i] == ranks[i+1]){
        		tied.add(ranks[i]);
        	}else{
        		break;
        	}
        }
        //find winner if a player is alone with the best rank
        if(tied.size() == 1){
        	int winningRank = tied.get(0);
        	if(p1.rankOfHand == winningRank){
        		winner = "Player 1";
        	}else if(p2.rankOfHand == winningRank){
        		winner = "Player 2";
        	}else if(p3.rankOfHand == winningRank){
        		winner = "Player 3";
        	}else if(p4.rankOfHand == winningRank){
        		winner = "Player 4";
        	}else if(p5.rankOfHand == winningRank){
        		winner = "Player 5";
        	}else if(p6.rankOfHand == winningRank){
        		winner = "Player 6";
        	}else if(p7.rankOfHand == winningRank){
        		winner = "Player 7";
        	}else{
        		winner = "Player 8";
        	}
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
        //find winner if both have the same pair
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
        //find winner if both have the same 2 pairs
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
        //find winner if both have the same 3 of a kind
        if((r1 == r2) && (r1>=99) && (r1 <= 111)){
            list1.remove(Integer.valueOf(p1.getTValueA()));
            list1.remove(Integer.valueOf(p1.getTValueA()));
            list1.remove(Integer.valueOf(p1.getTValueA()));
            list2.remove(Integer.valueOf(p2.getTValueA()));
            list2.remove(Integer.valueOf(p2.getTValueA()));
            list2.remove(Integer.valueOf(p2.getTValueA()));
            //check the next 2 highest cards
               for(int i = 3; i>=2; i--){
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
        // if straights are the same its a tie
        //compare flush's (too many possible ranks to allocate in Player class)
        if((r1 == 122) && (r2 == 122)){
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
        }
        // if full house's are the same its a tie
        // compare four of a kind
        if((r1 == r2) && (r1 >=279) && (r1 <= 291)){
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
        }
        //same straight flushes are a tie, this only happens when all flopped cards are straight flush
        System.out.println(winner);
	}
}
