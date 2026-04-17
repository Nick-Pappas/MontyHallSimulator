package np.concepts.abstractconcepts;

public class Strategy 
{
    private Percentage howOftenShouldWeSwitch;
    private String strategyName;
    
    public Strategy(Percentage howOftenTheStrategySwitchesP , String strategyNameP)
    {
        this.howOftenShouldWeSwitch = howOftenTheStrategySwitchesP;
        this.strategyName = strategyNameP;
    }

    public Percentage getHowOftenTheStrategySwitches() 
    {
        return this.howOftenShouldWeSwitch;
    }

    public void setHowOftenShouldWeSwitch(Percentage howOftenTheStrategySwitchesP) 
    {
        this.howOftenShouldWeSwitch = howOftenTheStrategySwitchesP;
    }

    public String getStrategyName() 
    {
        return this.strategyName;
    }

    public void setStrategyName(String strategyNameP) 
    {
        this.strategyName = strategyNameP;
    }
    
    public String toString()
    {
        return "\""+this.strategyName+"\": a strategy in which we switch "+this.howOftenShouldWeSwitch.toString()+" of the time.";
    }
}