package cardgame;

import java.util.Random;

// Cards - Maintains a collection of zero or more playing cards.
//         Provides facilities to create a full pack of 52 cards
//         and to shuffle the cards.
// author:Erfan Farhang Kia
// date: March 3th, 2023
public class Cards
{
    final int NOOFCARDSINFULLPACK = 52;
    
    // properties
    Card[] cards;
    int    valid;  // number of cards currently in collection
    
    // constructors
    public Cards( boolean fullPack)
    {
        cards = new Card[ NOOFCARDSINFULLPACK ];
        valid = 0;
        
        if (fullPack)
            createFullPackOfCards();
    }
    
    // methods
    public Card getTopCard()
    {
        Card tmp;

        if ( valid <= 0)
            return null;
        else
        {
            valid--;
            tmp = cards[valid];
            cards[valid] = null;
            return tmp;
        }
    }
    
    public boolean addTopCard( Card c)
    {
        if ( valid < cards.length)
        {
            cards[valid] = c;   // should this be cloned?
            valid++;
            return true;
        }
        return false;
    }
    
    private void createFullPackOfCards()
    {
        // TODO
        for (int i = 0; i < NOOFCARDSINFULLPACK; i++) {
            this.cards[i] = new Card(i);
            this.valid = 52;
        }
    }
    
    public void shuffle()
    {
        // TODO
        // Shuffle using the Fisher-Yates Algo
        Random random = new Random();
        for (int i = this.cards.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            // swap to cards
            Card tmp = this.cards[i];
            this.cards[i] = this.cards[j];
            this.cards[j] = tmp;
        }
    }
    
    
    // For testOnly... remove from production version!
    public void testOnlyPrint()
    {
        for ( int i =0; i < NOOFCARDSINFULLPACK; i++)
        {
            System.out.println( this.cards[i] );
        }
    }
    
} // end class Cards
