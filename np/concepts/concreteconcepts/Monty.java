package np.concepts.concreteconcepts;

import np.concepts.abstractconcepts.MontyGameSetup;
import np.concepts.abstractconcepts.MontyRandomness;
import np.containers.MontyDoors;

public class Monty 
{
    private MontyGameSetup todaysGame;

    public Monty(MontyGameSetup aMontyGameP)
    {
        this.todaysGame = aMontyGameP;
    }
    
    public void setUpANewGame(MontyGameSetup anotherMontyGameP)
    {
        this.todaysGame = anotherMontyGameP; 
    }
    
    public void askPlayersToPickADoor()
    {
        for (Player p: this.todaysGame.getPlayers())//now for each of the players:
        {
            p.pick();
        }
    }
    
    /**
     * Monty acts as the host here. He goes through each player, sees what they picked, 
     * and then opens a door that does NOT contain the prize. Crucially, he also removes 
     * the player's original pick from their current choices array. 
     * By doing this, the player is only left with the "other" door(s) to switch to!
     * 
     * @param randomnessP randomness instance to pick which non-prize door to open
     */
    public void openANonPrizeDoorAmongTheRemainingDoorsForEachPlayer(MontyRandomness randomnessP)
    {
        for (Player p: this.todaysGame.getPlayers())//now for each of the players:
        {
            MontyDoor[] currentChoicesForCurrentPlayer = p.getPlayersCurrentChoices();
            MontyDoor playersPick = p.getPlayersPick();
            int playersPickId = playersPick.getID();
            
            int currentPlayersChoiceIDindex = MontyDoors.getLocationOfDoorWithID(currentChoicesForCurrentPlayer, playersPickId);
             
            if (currentPlayersChoiceIDindex!=-1)//if we found the door
            {
                //first make sure we do not open the one the player chose
                MontyDoor[] updatedChoicesForCurrentPlayer = MontyDoors.removeADoor(currentChoicesForCurrentPlayer, currentPlayersChoiceIDindex);
                //now we need to make sure that the one with the prize remains available in the players choices.
                //monty needs to open (i.e. remove a non prize door among the remaining ones)
                updatedChoicesForCurrentPlayer = MontyDoors.removeARandomNonPrizeWinningDoor(updatedChoicesForCurrentPlayer, randomnessP);
                p.setPlayersCurrentChoices(updatedChoicesForCurrentPlayer);
            }
            else
            {
                System.out.println("no such id");
                System.exit(0);// <--- lets commit harakiri. I have not taught them yet exception handling
            }
        }   
    }
    
    /**
     * Asks each player if they want to switch based on their individual strategy.
     * If the random threshold falls below their strategy's switching percentage, 
     * they will make a completely new pick from their currently available doors. 
     * Because their original door was removed from their current choices pool in the 
     * previous step, picking again guarantees they actually switch to a completely different door!
     * 
     * @param ourRandomnesP the randomness generator used to determine if they switch
     */
    public void askThePlayersIfTheyWantToSwitch(MontyRandomness ourRandomnesP)
    {
        for (Player p: this.todaysGame.getPlayers())
        {
            double currentThreshold = ourRandomnesP.getARandomDoubleBetweenTheTwoIndexes(0, 100);
            double switchingThresholdForThisPlayer = p.getPlayersStrategy().getHowOftenTheStrategySwitches().getPercentage();
            
            if (currentThreshold < switchingThresholdForThisPlayer) // Changed <= to < to save George's honor because 0 is inclusive. 
            {
                p.pick();
            }
        }
    }
    
    /**
     * Loops through all the players at the end of the round and checks if their 
     * final pick contains the prize. If it does, we deposit the prize amount 
     * into their winnings!
     */
    public void calculatePlayersWinnings()
    {
        for (Player p: this.todaysGame.getPlayers())
        {
            if(p.getPlayersPick().hasPrize())
            {
                int currentWinnings = p.getWinnings();
                int updatedWinnings = currentWinnings + this.todaysGame.getPrizeAmount();
                p.setWinnings(updatedWinnings);
            }
        }
    }
    
}