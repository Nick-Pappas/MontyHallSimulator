package np.concepts.concreteconcepts;

import np.concepts.abstractconcepts.Strategy;

public class AudienceMemberPlayer
{
    private String playersName;
    private Strategy playersStrategy;
    
    /**
     * @param playersNameP
     * @param thePlayerStrategyP
     */
    public AudienceMemberPlayer(String playersNameP, Strategy thePlayerStrategyP)
    {
        this.playersName = playersNameP;
        this.playersStrategy = thePlayerStrategyP;  
    }

    /**
     * @return
     */
    public String getPlayersName()
    {
        return this.playersName;
    }
    
    /**
     * @return
     */
    public Strategy getPlayersStrategy() 
    {
        return this.playersStrategy;
    }
    
    
    /**
     * @param playersStrategyP
     */
    public void setPlayersStrategy(Strategy playersStrategyP) 
    {
        this.playersStrategy = playersStrategyP;
    }
    
    @Override
    public String toString()
    {
        String toReturn = this.playersName+"'s strategy is "+this.playersStrategy.toString();
        return toReturn;
    }
}