package np.concepts.abstractconcepts;

import java.util.Random;

public class MontyRandomness 
{
    private Random r;
    
    public MontyRandomness(long seedP)
    {
        this.r = new Random(seedP);  
    }
    
    public void reset(long seedP)
    {
        this.r.setSeed(seedP);
    }
    
    public Random getRandom()
    {
        return this.r;
    }
    
    /**
     * Generates a random double value that falls strictly between the fromP and toP parameters.
     * The result includes the lower bound but is strictly less than the upper bound.
     * 
     * @param fromP the lower bound for the random double
     * @param toP the upper bound for the random double
     * @return a randomly generated double
     */
    public double getARandomDoubleBetweenTheTwoIndexes(int fromP, int toP)
    {
        double randomValue = fromP + (toP - fromP) * this.r.nextDouble();
        return randomValue;
    }
    
    public int getARandomIntBetweenTheTwoIndexes(int fromP, int toP)
    {
        int range = toP - fromP + 1;
        int randomValue =  this.r.nextInt(range) + fromP;
        return randomValue;
    }
    
    public int getARandomIntBetween0And(int toP)
    {
        return this.getARandomIntBetweenTheTwoIndexes(0, toP);
    }
}