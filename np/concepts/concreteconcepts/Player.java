package np.concepts.concreteconcepts;

import np.concepts.abstractconcepts.MontyRandomness;
import np.concepts.abstractconcepts.Strategy;

public class Player extends AudienceMemberPlayer
{
    private MontyDoor playersPick;
    private int winnings;
    private MontyDoor[] playersCurrentChoices;
    private MontyRandomness playersRandomBehavior;
    
    public Player(String playersNameP, Strategy thePlayerStrategyP, MontyRandomness playersRandomBehaviorP)
    {
        super(playersNameP, thePlayerStrategyP);        
        this.playersRandomBehavior = playersRandomBehaviorP;
        this.playersCurrentChoices = null;
        this.playersPick = null;
        this.winnings = 0;
    }

    public MontyDoor getPlayersPick()
    {
        return this.playersPick;
    }
    
    public MontyDoor[] getPlayersCurrentChoices()
    {
        return this.playersCurrentChoices;
    }
    public MontyRandomness getPlayersRandomBehavior()
    {
        return this.playersRandomBehavior;
    }
    public int getWinnings()
    {
        return this.winnings;
    }
        
    public void setWinnings(int winningsP)
    {
        this.winnings = winningsP;
    }
    
    public void setPlayersCurrentChoices(MontyDoor[] playersCurrentChoicesP)
    {
        this.playersCurrentChoices = playersCurrentChoicesP;
    }
    
    /**
     * Forces the player to make a door pick. It sets the players pick to be the index of the door that the player picked
     * if the players choice is within the proper range. Otherwise it forces the player to pick 0.
     * @param playersPickIndexP The index of the door that the players gets to pick.
     */
    public boolean pick(int playersPickIndexP)
    {
        if (0<=playersPickIndexP && playersPickIndexP<this.playersCurrentChoices.length)
        {
            this.playersPick = this.playersCurrentChoices[playersPickIndexP];
        }
        else
        {
            this.playersPick = this.playersCurrentChoices[0];
            return false; // we forced them to pick 0 because they gave us garbage
        }
        return true;
    }
    
    /**
     * Picks a completely random door from the player's current available choices.
     * If the original pick was already removed from the choices (e.g. by Monty), 
     * this effectively acts as a "switch" to one of the remaining available doors.
     */
    public void pick()
    {
        int choicesLengthMinusOne = this.playersCurrentChoices.length-1;
        this.playersPick = this.playersCurrentChoices[this.playersRandomBehavior.getARandomIntBetween0And(choicesLengthMinusOne)];
    }

    @Override
    public String toString()
    {
        String toReturn = super.toString()+" "+this.getPlayersName()+" has won "+this.winnings+"$ so far by using it.";
        return toReturn;
    }
}