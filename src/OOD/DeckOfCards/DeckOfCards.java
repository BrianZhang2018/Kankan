package OOD.DeckOfCards;

import java.util.Random;

//http://www.mathcs.emory.edu/~cheung/Courses/170/Syllabus/10/cards.html
public class DeckOfCards {
    public static void main(String[] args) {
        DeckOfCards dc = new DeckOfCards();
        dc.shuffle();
        System.out.println(dc.toString());
        //System.out.println(dc.deal());
    }

    private int numOfCards;
    private Card[] deckOfCards;         // Contains all 52 cards
    private int cardsUsed;

    public DeckOfCards() 
    {
        this.numOfCards = Card.getRank().length * Card.getSuit().length;
        this.deckOfCards = new Card[numOfCards];

        int i = 0;

        for (int suit = 0; suit < Card.getSuit().length; suit++)
            for (int rank = 0; rank < Card.getRank().length; rank++)
                this.deckOfCards[i++] = new Card(suit, rank);

        cardsUsed = 0;
    }

    //
    public void shuffle() { // refer from ShuffleAnArray.java
        if (deckOfCards == null || deckOfCards.length < 2) return;

        Random rand = new Random();
        for (int i = 0; i < deckOfCards.length; i++) {
            int r = rand.nextInt(i + 1);
            Card tmp = deckOfCards[i];
            deckOfCards[i] = deckOfCards[r];
            deckOfCards[r] = tmp;
        }
    }

    public Card deal() {
        if (cardsUsed == numOfCards) shuffle();

        return deckOfCards[++cardsUsed - 1];
    }

    public String toString() {

        for (Card card : deckOfCards) {
            System.out.println(card.getCardRank() + card.getCardSuit());
        }
        return "";
    }

} 