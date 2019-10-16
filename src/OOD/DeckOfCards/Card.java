package OOD.DeckOfCards;
/*
   An object of class card represents one of the 52 cards in a
   standard deck of playing cards.  Each card has a suit and
   a value.
*/
public class Card {

    private static final String[] Suit = {"d", "c", "h", "s"};
    private static final String[] Rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    private int cardSuit;
    private int cardRank;

    public Card(int suit, int rank) {
        this.cardSuit = suit;
        this.cardRank = rank;
    }

    //translate to real suit value
    public String getCardSuit() {
        return Suit[cardSuit];
    }

    //translate to real card value
    public String getCardRank() {
        return Rank[cardRank];
    }

    public static String[] getSuit() {
        return Suit;
    }

    public static String[] getRank() {
        return Rank;
    }

    @Override
    public String toString() {
        return Suit[cardSuit] + Rank[cardRank];
    }

}