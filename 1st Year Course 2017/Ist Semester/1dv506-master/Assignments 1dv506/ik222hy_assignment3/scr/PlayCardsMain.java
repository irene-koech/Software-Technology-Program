package ik222hy_assignment3;

public class PlayCardsMain {

	public static void main(String[] args) {
			
			Deck deck1 = new Deck();
			Deck deck2 = new Deck();
			
			Card c1 = deck1.handOutNextCard();
			Card c2 = deck1.handOutNextCard();
			Card c3 = deck1.handOutNextCard();
			Card c4 = deck1.handOutNextCard();
			Card c5 = deck1.handOutNextCard();
			
			System.out.println("Card No.1 is: "+c1.getSuit()+" | " + c1.getRank());
			System.out.println("Card No.2 is: "+c2.getSuit()+" | " + c2.getRank());
			System.out.println("Card No.3 is: "+c3.getSuit()+" | " + c3.getRank());
			System.out.println("Card No.4 is: "+c4.getSuit()+" | " + c4.getRank());
			System.out.println("Card No.5 is: "+c5.getSuit()+" | " + c5.getRank());
			
			System.out.println("\nCardsLeft in deck1 are: " +deck1.deckSize());
			System.out.println("Trying to shuffle used deck1 returns: "
			                                              +deck1.shuffle());
			
			System.out.println("\nCardsLeft in deck2 are: " +deck2.deckSize());
			System.out.println("Trying to shuffle unused deck2 returns: " 
			                                              +deck2.shuffle());

	}

}
