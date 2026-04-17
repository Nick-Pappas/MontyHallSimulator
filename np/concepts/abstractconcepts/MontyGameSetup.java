package np.concepts.abstractconcepts;

import np.concepts.concreteconcepts.MontyDoor;
import np.concepts.concreteconcepts.Player;

public class MontyGameSetup 
{
    private MontyDoor[] myDoors;
    private Player[] thePlayers;
    private int prizePosition;
    private int prizeAmount;
    
    public MontyGameSetup(int noOfDoorsP, Player[] thePlayersP, int prizeAmountP, MontyRandomness randomnessP)
    {
        this.myDoors = new MontyDoor[noOfDoorsP];
        this.thePlayers = thePlayersP;
        this.prizeAmount = prizeAmountP;
        this.resetRound(randomnessP);
    }
    
    /**
     * Wipes the slate clean for a brand new round! We randomly decide where the 
     * prize goes, instantiate all the new doors, and assign those exact same doors 
     * to all of our players so they have a fresh set of choices.
     * 
     * @param randomnessP the randomness generator used to place the prize
     */
    public void resetRound(MontyRandomness randomnessP)
    {
        //get a random number between 0 and noOfDoors-1
        int choices = this.myDoors.length - 1;
        int k = randomnessP.getARandomIntBetween0And(choices);
        this.prizePosition = k;
                
        for (int i=0; i<this.myDoors.length; i++)
        {
            if (i==k)
                this.myDoors[i] = new MontyDoor(true, i);
            else
                this.myDoors[i] = new MontyDoor(false, i);
        }
        for (Player p: this.thePlayers)
        {
            p.setPlayersCurrentChoices(this.myDoors);
        }
    }
    
    public int getPrizePosition()
    {
        return this.prizePosition;
    }
    
    public int getPrizeAmount()
    {
        return this.prizeAmount;
    }
    
    public MontyDoor[] getDoors()
    {
        return this.myDoors;
    }
    
    public Player[] getPlayers()
    {
        return this.thePlayers;
    }
    
}