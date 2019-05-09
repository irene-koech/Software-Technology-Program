package ik222hy_assignment3;

public class Card {
	public enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES
	};

	public enum Rank {
		ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), 
		EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(
				12), KING(13);

		private int rank;

		Rank(int cardValue) {
			rank = cardValue;
		}

		public int getRank() {
			return rank;
		}
	};

	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Card() {
	}

	public Card(Rank rank) {
		this.rank = rank;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

}
