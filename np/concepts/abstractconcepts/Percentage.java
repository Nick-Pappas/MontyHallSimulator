package np.concepts.abstractconcepts;

public class Percentage 
{
    private float percentage;
    
    public Percentage(float aPercentageP)
    {
        this.setPercentage(aPercentageP);
    }

    public Percentage(Percentage anotherPercentageP)
    {
        this.percentage = anotherPercentageP.getPercentage();
    }
    
    public float getPercentage() 
    {
        return this.percentage;
    }

    public void setPercentage(float aPercentageP) 
    {
        if (aPercentageP>100.0)
            this.percentage = (float)100.0;
        else if (aPercentageP<0.0)
            this.percentage = (float)0.0;
        else
            this.percentage = aPercentageP;
    }
    
    public String toString()
    {
        return this.percentage+"%";
    }
}