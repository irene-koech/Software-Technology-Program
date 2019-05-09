package ik222hy_assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import ik222hy_assignment3.Card.Rank;
import ik222hy_assignment3.Card.Suit;

public class Deck {
	private ArrayList<Card> cards = new ArrayList<Card>();

	public Deck() {

		for (int n = 0; n <= 3; n++) {
			Suit suit = Suit.values()[n];

			for (int i = 0; i <= 12; i++) {
				Rank rank = Rank.values()[i];
				cards.add(new Card(suit, rank));
			}
		}
		shuffle();
	}

	public Boolean shuffle() {
		if (cards.size() == 52) {
			Collections.shuffle(cards);
			return true;
		}
		return false;
	}

	public Card handOutNextCard() {
		ListIterator<Card> iterator = cards.listIterator();
		Card retCard = new Card();
		if (iterator.hasNext()) {
			retCard = iterator.next();
			cards.remove(retCard);
		}

		return retCard;
	}

	public int deckSize() {
		return cards.size();
	}

}
