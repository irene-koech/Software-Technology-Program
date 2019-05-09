package ik222hy_assignment3;

public class Game123 {
	public boolean play() {
        Deck deck = new Deck();
        deck.shuffle();

        int i = 1;
        while(deck.deckSize() > 0) {
            Card card = deck.handOutNextCard();
            switch(i % 3) {
                case 1:
                    if(card.getRank() == Card.Rank.ACE) {
                        return false;
                    }
                    break;

                case 2:
                    if(card.getRank() == Card.Rank.TWO) {
                        return false;
                    }
                    break;

                case 0:
                    if(card.getRank() == Card.Rank.THREE) {
                        return false;
                    }
                    break;
            }
            i++;
        }
        return true;
    }

}
