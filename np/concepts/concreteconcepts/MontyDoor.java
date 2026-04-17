package np.concepts.concreteconcepts;

public class MontyDoor extends Door
{
    private boolean hasPrize;

    public MontyDoor(boolean prizeP, int IDP)
    {
        super(IDP);
        this.hasPrize = prizeP;
    }
    
    public boolean hasPrize()
    {
        return this.hasPrize;
    }
    
    @Override
    public boolean equals(Object objP)
    {
        if (this == objP) 
            return true;
        if (objP == null || this.getClass() != objP.getClass()) 
            return false;
        if (!super.equals(objP)) 
            return false;
            
        MontyDoor anotherMontyDoor = (MontyDoor) objP;
        return (this.hasPrize == anotherMontyDoor.hasPrize());
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (this.hasPrize ? 1 : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        if (this.hasPrize)
            return "Door "+this.getID()+" has the PRIZE!!";
        else
            return "Door "+this.getID()+" has no prize.";
    }
}