package cardgame;

import java.util.ArrayList;
import java.util.List;

// ScoreCard - Maintains one integer score per player, for any number of players
//             Caution: invalid playernumbers result in run-time exception!
// author:Erfan Farhang Kia
// date: March 3th, 2023
public class ScoreCard
{
    // properties
    int[] scores;
    
    // constructors
    public ScoreCard(int noOfPlayers)
    {
        scores = new int[noOfPlayers];
        
        // init all scores to zero
        for ( int i = 0; i < scores.length; i++)
            scores[i] = 0;
    }
    
    // methods
    public int getScore(int playerNo)
    {
        // fix
        return scores[playerNo - 1];
    }
    
    public void update(int playerNo, int amount)
    {
        // fix
        scores[playerNo - 1] += amount;
    }
    
    public String toString()
    {
        String s;
        s = "\n"
            + "_____________\n"
            + "\nPlayer\tScore\n"
            + "_____________\n";
        
        for ( int playerNo = 0; playerNo < scores.length; playerNo++)
        {
            s = s + (playerNo + 1) + "\t" + scores[playerNo] + "\n";
        }
        
        s += "_____________\n";
        return s;
    }
    
    public int[] getWinners()
    {
        // TODO
        // find max and winners in same loop
        int max = 0;
        int length = this.scores.length;
        List<Integer> winners = new ArrayList<>();
        
        for (int i = length - 1; i >= length / 2 ; i--){
            if (scores[i] > max) {
                max = scores[i];
                winners.clear();
                winners.add(i + 1);
            } else if (scores[i] == max) {
                winners.add(i + 1);
            }

            if (scores[length - i - 1] > max) {
                max = scores[length - i - 1];
                winners.clear();
                winners.add(length - i);
            } else if (scores[length - i - 1] == max) {
                winners.add(length - i);
            }
        }        

        // cast list to array 
        int[] winnersAr = new int[winners.size()];
        for (int i = 0; i < winners.size(); i++){
            winnersAr[i] = winners.get(i);
        }
        return winnersAr;
    }
    
} // end class ScoreCard
