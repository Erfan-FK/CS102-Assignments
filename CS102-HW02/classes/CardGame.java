package cardgame;

import java.util.ArrayList;
import java.util.List;

// Cardgame
// author:Erfan Farhang Kia
// date: March 3th, 2023
public class CardGame
{
    // properties
    Cards             fullPack;
    ArrayList<Player> players;
    ScoreCard         scoreCard;
    Cards[]           cardsOnTable;
    int               roundNo;
    int               turnOfPlayer;

    int noOfPlayers = 4;
    int noOfCardForEach = 13;
    List<Integer> winnersOfRound;
    
    // constructors
    public CardGame( Player p1, Player p2, Player p3, Player p4)
    {
        // TODO
        fullPack = new Cards(true);
        fullPack.shuffle();

        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        scoreCard = new ScoreCard(4);

        cardsOnTable = new Cards[5];
        cardsOnTable[0] = new Cards(false);
        cardsOnTable[1] = p1.hand;
        cardsOnTable[2] = p2.hand;
        cardsOnTable[3] = p3.hand;
        cardsOnTable[4] = p4.hand;
        
        for (int i = 0; i < noOfCardForEach; i++) {
            for (int j = 1; j < cardsOnTable.length; j++) {
                cardsOnTable[j].addTopCard(fullPack.getTopCard());
            }
        }

        roundNo = 1;
        turnOfPlayer = 1;
    }
    
    // methods
    public boolean playTurn( Player p, Card c)
    {
        // TODO
        if(isGameOver()) {
            System.out.println("The Game has finised!");
            System.out.println("Exit the menu to see the winners.");
            return false;
        }
        
        if (!isTurnOf(p)) {
            p.add(c);
            System.out.println("It is player " + turnOfPlayer + "'s turns!");
            return false;
        } else {
            cardsOnTable[0].addTopCard(c);
            System.out.println("Player " + turnOfPlayer + " played " + c.toString() + "\n");
            this.turnOfPlayer = (turnOfPlayer + 1) % (noOfPlayers + 1); 
 
            if (this.turnOfPlayer == 0) {
                roundOver();
                this.roundNo++;
            }

            return true;
        }
    }
    
    public boolean isTurnOf( Player p)
    {
        // TODO
        if (p.getName().equals(players.get(turnOfPlayer - 1).getName())){
            return true;
        }
        return false;
    }
    
    public boolean isGameOver()
    {
        // TODO
        if (this.roundNo > noOfCardForEach) {
            return true;
        }
        return false;
    }
    
    public int getScore(int playerNumber)
    {
        // TODO
        return scoreCard.getScore(playerNumber - 1);
    }
    
    public String getName( int playerNumber)
    {
        // TODO
        return players.get(playerNumber - 1).getName();
    }
    
    public int getRoundNo()
    {
        // TODO
        return this.roundNo;
    }
    
    public int getTurnOfPlayerNo()
    {
        // ToDo
        return this.turnOfPlayer;
    }
    
    public Player[] getWinners()
    {
        // TODO
        Player[] winners = new Player[scoreCard.getWinners().length];
        for (int i = 1; i <= scoreCard.getWinners().length; i++) {
            winners[i - 1] = players.get(scoreCard.getWinners()[i - 1] - 1); 
        }
        return winners;
    }
    
    public String showScoreCard()
    {
        return scoreCard.toString();
    }

    // find the winner of a round & Updates scores after end of each round
    public void roundOver(){
        int max = 0;
        this.winnersOfRound = new ArrayList<>();
        int startIndex = noOfPlayers * (roundNo - 1);
        int endIndex = (noOfPlayers * roundNo) - 1;

        for (int i = startIndex; i <= endIndex; i++) {
            if (cardsOnTable[0].cards[i].getFaceValue() > max) {
                max = cardsOnTable[0].cards[i].getFaceValue();
                winnersOfRound.clear();
                winnersOfRound.add((i % 4) + 1);
            } else if (cardsOnTable[0].cards[i].getFaceValue() == max) {
                winnersOfRound.add((i % 4) + 1);

            }
        }

        for (int winnerOfRound : winnersOfRound) {
            scoreCard.update(winnerOfRound, 1);
        }

        this.turnOfPlayer = 1;
    }
}