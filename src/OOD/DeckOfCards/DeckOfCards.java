package OOD.DeckOfCards;

//http://www.mathcs.emory.edu/~cheung/Courses/170/Syllabus/10/cards.html
public class DeckOfCards {
    public static void main(String[] args) {
        DeckOfCards dc = new DeckOfCards();
        dc.shuffle();
        System.out.println(dc.toString());
        //System.out.println(dc.deal());
    }

    private int NCARDS;
    private Card[] deckOfCards;         // Contains all 52 cards
    private int cardsUsed;

    public DeckOfCards() 
    {
        this.NCARDS = Card.getRank().length * Card.getSuit().length;
        this.deckOfCards = new Card[NCARDS];

        int i = 0;

        for (int suit = 0; suit < Card.getSuit().length; suit++)
            for (int rank = 0; rank < Card.getRank().length; rank++)
                this.deckOfCards[i++] = new Card(suit, rank);

        cardsUsed = 0;
    }

    /* ---------------------------------
      shuffle(n): shuffle the deck
      --------------------------------- */
    public void shuffle() {
        int i, j, k;

        for (k = 0; k < NCARDS; k++) {
            i = (int) (NCARDS * Math.random());  // Pick 2 random cards
            j = (int) (NCARDS * Math.random());  // in the deck

            Card tmp = deckOfCards[i];
            deckOfCards[i] = deckOfCards[j];
            deckOfCards[j] = tmp;
        }

        cardsUsed = 0;   // Reset cardsUsed
    }

    /* -------------------------------------------
      deal(): deal deckOfCards[currentCard] out
      ------------------------------------------- */
    public Card deal() {
        if (cardsUsed == NCARDS) {
            shuffle();
        }
        cardsUsed++;
        return deckOfCards[cardsUsed - 1];
    }

    public String toString() {

        for (Card card : deckOfCards) {
            System.out.println(card.getCardRank() + card.getCardSuit());
        }
        return "";
    }

} 