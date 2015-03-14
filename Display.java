package simulation;

public class Display{

	private String winningHand;

	public Display(String winningHand){
		this.winningHand = winningHand;
	}

	public String findFrequency(){
		//format winning hand to compare with possibleHands[]
		char c1 = winningHand.charAt(0); 
		char c2 = winningHand.charAt(2);
		int card1 = Rank.convert(c1);
		int card2 = Rank.convert(c2);
		String s1 = "";
		String s2 = "";
		if(card1 > card2){
			s1 = Character.toString(c1);
			s2 = Character.toString(c2);
		}else if(card2 > card1){
			s1 = Character.toString(c2);
			s2 = Character.toString(c1);
		}else{
			s1 = Character.toString(c1);
			s2 = Character.toString(c2);
		}
		boolean suited = false;
		if(winningHand.charAt(1) == winningHand.charAt(3)){
			suited = true;
		}
		String a = "n";
		if(suited){
			a = "s";
		}
		String hand = "";
		if(c1 == 't'){
			hand = "tie ";
		}else{
		    hand = s1+s2+" "+a;
		}
		return hand;
	}

}