package simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Compare{
	
    private String winner;

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
        int[] ranks = new int[8];
        ranks[0] = p1.rankOfHand(d); //rank of p1, cant call this method more than once without unwanted side effects
        ranks[1] = p2.rankOfHand(d);
        ranks[2] = p3.rankOfHand(d);
        ranks[3] = p4.rankOfHand(d);
        ranks[4] = p5.rankOfHand(d);
        ranks[5] = p6.rankOfHand(d);
        ranks[6] = p7.rankOfHand(d);
        ranks[7] = p8.rankOfHand(d);
        int[] staticRanks = new int[8];
        for(int i = 0; i < ranks.length; i++){
            staticRanks[i] = ranks[i];
        }
      /*  //print flopped cards
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
        System.out.println(ranks[7]);*/

        // find winner
        winner = "tie ";
        Arrays.sort(ranks);
        //find if the top ranks are tied, store the ranks in an arraylist
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
        	if(staticRanks[0] == winningRank){
        		winner = "Player 1";
        	}else if(staticRanks[1] == winningRank){
        		winner = "Player 2";
        	}else if(staticRanks[2] == winningRank){
        		winner = "Player 3";
        	}else if(staticRanks[3] == winningRank){
        		winner = "Player 4";
        	}else if(staticRanks[4] == winningRank){
        		winner = "Player 5";
        	}else if(staticRanks[5] == winningRank){
        		winner = "Player 6";
        	}else if(staticRanks[6] == winningRank){
        		winner = "Player 7";
        	}else{
        		winner = "Player 8";
        	}
        }
        //match tied ranks to players
        int a = 0;
        int b = 0; 
        int c = 0;
        int d2 = 0;
        int e = 0;
        int f = 0;
        int g = 0;
        int h = 0;
        ArrayList<Player> tiedPlayers = new ArrayList<Player>(); //stores all tied players of top rank
        if(staticRanks[0] == tied.get(0)){
                tiedPlayers.add(p1);
                a = 1;
        }
        if(staticRanks[1] == tied.get(0)){
                tiedPlayers.add(p2);
                b = 2;
        }
        if(staticRanks[2] == tied.get(0)){
                tiedPlayers.add(p3);
                c = 3;
        }
        if(staticRanks[3] == tied.get(0)){
                tiedPlayers.add(p4);
                d2 = 4;
        }
        if(staticRanks[4] == tied.get(0)){
                tiedPlayers.add(p5);
                e = 5;
        }
        if(staticRanks[5] == tied.get(0)){
                tiedPlayers.add(p6);
                f = 6;
        }
        if(staticRanks[6] == tied.get(0)){
                tiedPlayers.add(p7);
                g = 7;
        }
        if(staticRanks[7] == tied.get(0)){
                tiedPlayers.add(p8);
                h = 8;
        }
        //System.out.println(tiedPlayers.get(0).getFlushValuesTemp().size());
        ArrayList<Integer> players = new ArrayList<Integer>();//stores the player numbers of those who are tied for 1st
        if(a == 1){
            players.add(a);
        }
        if(b == 2){
            players.add(b);
        }
        if(c == 3){
            players.add(c);
        }
        if(d2 == 4){
            players.add(d2);
        }
        if(e == 5){
            players.add(e);
        }
        if(f == 6){
            players.add(f);
        }
        if(g == 7){
            players.add(g);
        }
        if(h == 8){
            players.add(h);
        }

        //find winner if tied players have high cards
        if((players.size() > 1) && (ranks[7] <= 7) && (winner == "tie ")){
            for(int i = 0; i <= players.size()-2; i++){ //loop through all tied players(excluding the last one)
                for(int j = 5; j >= 2; j--){ //compare cards between the 2 players, up to the 5th card
                    if(tiedPlayers.get(i).getCardsSorted(j,d) > tiedPlayers.get(i+1).getCardsSorted(j,d)){ //compare next highest cards
                        tiedPlayers.remove(tiedPlayers.get(i+1));
                        players.remove(players.get(i+1));
                        i= -1;//look at the same player again, compared to someone else
                        j = 1;//break out of inner loop
                    }else if(tiedPlayers.get(i).getCardsSorted(j,d) < tiedPlayers.get(i+1).getCardsSorted(j,d)){
                        tiedPlayers.remove(tiedPlayers.get(i));
                        players.remove(players.get(i));
                        i = -1;//look at the same player again, compared to someone else
                        j = 1;//break out of inner loop
                    }
                    if(tiedPlayers.size() == 1){
                        int temp = players.get(0);
                        switch(temp){
                            case 1:
                                winner = "Player 1";
                                break;
                            case 2:
                                winner = "Player 2";
                                break;
                            case 3:
                                winner = "Player 3";
                                break;
                            case 4:
                                winner = "Player 4";
                                break;
                            case 5:
                                winner = "Player 5";
                                break;
                            case 6:
                                winner = "Player 6";
                                break;
                            case 7:
                                winner = "Player 7";
                                break;
                            case 8:
                                winner = "Player 8";
                                break;
                        }
                    }
                    if(winner != "tie "){
                        //leave both loops
                        j = 1;
                        i = 10;
                    }
                }       
            }
        }
        //find winner if tied players have one pair of the same value
        if((players.size() > 1) && (ranks[7] >= 8) && (ranks[7] <= 20) && (winner == "tie ")){
            ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
            for(int i = 0; i <= players.size()-1; i++){//loop through players, add lists of there cards minus the pair to lists arraylist
                ArrayList<Integer> cards = tiedPlayers.get(i).getAllCardsSorted(d);
                cards.remove((Integer)tiedPlayers.get(i).getValueA());
                cards.remove((Integer)tiedPlayers.get(i).getValueA());
                lists.add(cards);
            }
            //compare the top three cards from these lists, remove players as necesary
            for(int i = 0; i <= lists.size() - 2; i++){//loop through players
                for(int j = 4; j >= 2; j--){//loop through cards
                    if(lists.get(i).get(j) > lists.get(i+1).get(j)){ //compare next highest cards
                        lists.remove(lists.get(i+1));
                        tiedPlayers.remove(tiedPlayers.get(i+1));
                        players.remove(players.get(i+1));
                        i= -1;//go back to the first player
                        j = 1;//break out of inner loop
                    }else if(lists.get(i).get(j) < lists.get(i+1).get(j)){
                        lists.remove(lists.get(i));
                        tiedPlayers.remove(tiedPlayers.get(i));
                        players.remove(players.get(i));
                        i = -1;//go back to first player
                        j = 1;//break out of inner loop
                    }
                    if(tiedPlayers.size() == 1){
                        int temp = players.get(0);
                        switch(temp){
                            case 1:
                                winner = "Player 1";
                                break;
                            case 2:
                                winner = "Player 2";
                                break;
                            case 3:
                                winner = "Player 3";
                                break;
                            case 4:
                                winner = "Player 4";
                                break;
                            case 5:
                                winner = "Player 5";
                                break;
                            case 6:
                                winner = "Player 6";
                                break;
                            case 7:
                                winner = "Player 7";
                                break;
                            case 8:
                                winner = "Player 8";
                                break;
                        }
                    }
                    if(winner != "tie "){
                        //leave both loops
                        j = 1;
                        i = 10;
                    }
                }
            }
        }
        //find winner if tied players have the same two pair
        if((players.size() > 1) && (ranks[7] >= 21) && (ranks[7] <= 98) && (winner == "tie ")){
            ArrayList<Integer> max = new ArrayList<Integer>();
            for(int i = 0; i <= players.size()-1; i++){//loop through players, add highest card after their 2 pairs to max (to compare their high card)
                ArrayList<Integer> cards = tiedPlayers.get(i).getAllCardsSorted(d);
                cards.remove((Integer)tiedPlayers.get(i).getValueA());
                cards.remove((Integer)tiedPlayers.get(i).getValueA());
                cards.remove((Integer)tiedPlayers.get(i).getValueB());
                cards.remove((Integer)tiedPlayers.get(i).getValueB());
                max.add(cards.get(2));
            }
                int maxVal = 0;
                int maxIndex = 0;
            for(int i = 0; i < max.size() - 1; i++){//find index of max card
                if(max.get(i) > maxVal){
                    maxVal = max.get(i);
                    maxIndex = i;
                }
            }
            int aa = 0;
            for(int i = 0; i < max.size() - 1; i++){//check for ties
                if(max.get(i) == maxVal){
                    aa++;
                }
            }
            if(aa > 1){
                winner = "tie ";
            }else{
            int temp = players.get(maxIndex);
            switch(temp){
                    case 1:
                        winner = "Player 1";
                        break;
                    case 2:
                        winner = "Player 2";
                        break;
                    case 3:
                        winner = "Player 3";
                        break;
                    case 4:
                        winner = "Player 4";
                        break;
                    case 5:
                        winner = "Player 5";
                        break;
                    case 6:
                        winner = "Player 6";
                        break;
                    case 7:
                        winner = "Player 7";
                        break;
                    case 8:
                        winner = "Player 8";
                        break;
            }
            }
        }
        //find winner if tied players have the same 3 of a kind
        if((players.size() > 1) && (ranks[7] >= 99) && (ranks[7] <= 111) && (winner == "tie ")){
            ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
            for(int i = 0; i <= players.size()-1; i++){//loop through players, add lists of there cards minus the three of a kind 
                ArrayList<Integer> cards = tiedPlayers.get(i).getAllCardsSorted(d);
                cards.remove((Integer)tiedPlayers.get(i).getTValueA());
                cards.remove((Integer)tiedPlayers.get(i).getTValueA());
                cards.remove((Integer)tiedPlayers.get(i).getTValueA());
                lists.add(cards);
            }
            //compare the top two cards from these lists, remove players as necesary
            for(int i = 0; i <= lists.size() - 2; i++){//loop through players
                for(int j = 3; j >= 2; j--){//loop through cards
                    if(lists.get(i).get(j) > lists.get(i+1).get(j)){ //compare next highest cards
                        lists.remove(lists.get(i+1));
                        tiedPlayers.remove(tiedPlayers.get(i+1));
                        players.remove(players.get(i+1));
                        i= -1;//go back to the first player
                        j = 1;//break out of inner loop
                    }else if(lists.get(i).get(j) < lists.get(i+1).get(j)){
                        lists.remove(lists.get(i));
                        tiedPlayers.remove(tiedPlayers.get(i));
                        players.remove(players.get(i));
                        i = -1;//go back to first player
                        j = 1;//break out of inner loop
                    }
                    if(tiedPlayers.size() == 1){
                        int temp = players.get(0);
                        switch(temp){
                            case 1:
                                winner = "Player 1";
                                break;
                            case 2:
                                winner = "Player 2";
                                break;
                            case 3:
                                winner = "Player 3";
                                break;
                            case 4:
                                winner = "Player 4";
                                break;
                            case 5:
                                winner = "Player 5";
                                break;
                            case 6:
                                winner = "Player 6";
                                break;
                            case 7:
                                winner = "Player 7";
                                break;
                            case 8:
                                winner = "Player 8";
                                break;
                        }
                    }
                    if(winner != "tie "){
                        //leave both loops
                        j = 1;
                        i = 10;
                    }
                }
            }
        }
        //same straights is a tie
        //find winner if tied players have flushes
        if((players.size() > 1) && (ranks[7] == 122) && (winner == "tie ")){
            ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
            for(int i = 0; i <= players.size()-1; i++){//loop through players, add lists of there top flush cards
                ArrayList<Integer> cards = tiedPlayers.get(i).getFlushValues();
                lists.add(cards);
            }
            //compare the top five cards from these lists, remove players as necesary
            for(int i = 0; i < lists.size() - 1; i++){//loop through players
                for(int j = 4; j >= 0; j--){//loop through cards
                    if(lists.get(i).get(j) > lists.get(i+1).get(j)){ //compare next highest cards
                        lists.remove(lists.get(i+1));
                        tiedPlayers.remove(tiedPlayers.get(i+1));
                        players.remove(players.get(i+1));
                        i = -1;//go back to the first player
                        j = -10;//break out of inner loop
                    }else if(lists.get(i).get(j) < lists.get(i+1).get(j)){
                        lists.remove(lists.get(i));
                        tiedPlayers.remove(tiedPlayers.get(i));
                        players.remove(players.get(i));
                        i = -1;//go back to first player
                        j = -10;//break out of inner loop
                    }
                    if(tiedPlayers.size() == 1){
                        int temp = players.get(0);
                        switch(temp){
                            case 1:
                                winner = "Player 1";
                                break;
                            case 2:
                                winner = "Player 2";
                                break;
                            case 3:
                                winner = "Player 3";
                                break;
                            case 4:
                                winner = "Player 4";
                                break;
                            case 5:
                                winner = "Player 5";
                                break;
                            case 6:
                                winner = "Player 6";
                                break;
                            case 7:
                                winner = "Player 7";
                                break;
                            case 8:
                                winner = "Player 8";
                                break;
                        }
                    }
                    if(winner != "tie "){
                        //leave both loops
                        j = -10;
                        i = 10;
                    }
                }
            }
        }
        //same full house's is a tie
        //find winner if tied players have the same four of a kind
        if((players.size() > 1) && (ranks[7] >= 279) && (ranks[7] <= 291) && (winner == "tie ")){
            ArrayList<Integer> topCard = new ArrayList<Integer>();
            for(int i = 0; i < tiedPlayers.size(); i++){
                ArrayList<Integer> cards = tiedPlayers.get(i).getAllCardsSorted(d);
                cards.remove(Integer.valueOf(tiedPlayers.get(i).getQValueA()));
                cards.remove(Integer.valueOf(tiedPlayers.get(i).getQValueA()));
                cards.remove(Integer.valueOf(tiedPlayers.get(i).getQValueA()));
                cards.remove(Integer.valueOf(tiedPlayers.get(i).getQValueA()));
                topCard.add(cards.get(2));
                cards.clear();
            }
            for(int i = 0; i < topCard.size() - 1; i++){
                if(topCard.get(i) > topCard.get(i+1)){
                    topCard.remove(topCard.get(i+1));
                    players.remove(players.get(i+1));
                    i = -1;
                }else if(topCard.get(i) < topCard.get(i+1)){
                    topCard.remove(topCard.get(i));
                    players.remove(players.get(i));
                    i = -1;
                }
                if(players.size() == 1){
                        int temp = players.get(0);
                        switch(temp){
                            case 1:
                                winner = "Player 1";
                                break;
                            case 2:
                                winner = "Player 2";
                                break;
                            case 3:
                                winner = "Player 3";
                                break;
                            case 4:
                                winner = "Player 4";
                                break;
                            case 5:
                                winner = "Player 5";
                                break;
                            case 6:
                                winner = "Player 6";
                                break;
                            case 7:
                                winner = "Player 7";
                                break;
                            case 8:
                                winner = "Player 8";
                                break;
                        }
                    }
                    if(winner != "tie "){
                        i = 10;
                    }
            }
        }
        //same straight flush's are a tie
        //find winners hand and return it
        String winningHand = "";
        switch(winner){
            case "Player 1":
                winningHand = ("" + p1.getA() + p1.getB());
                break;
            case "Player 2":
                winningHand = ("" + p2.getA() + p2.getB());
                break;
            case "Player 3":
                winningHand = ("" + p3.getA() + p3.getB());
                break;
            case "Player 4":
                winningHand = ("" + p4.getA() + p4.getB());
                break;
            case "Player 5":
                winningHand = ("" + p5.getA() + p5.getB());
                break;
            case "Player 6":
                winningHand = ("" + p6.getA() + p6.getB());
                break;
            case "Player 7":
                winningHand = ("" + p7.getA() + p7.getB());
                break;
            case "Player 8":
                winningHand = ("" + p8.getA() + p8.getB());
                break;
        }
        if(winner == "tie "){
            winningHand = "tie ";
        }
        return winningHand;
	}
    public String getWinner(){
        return winner;
    }
}
