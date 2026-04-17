package np.concepts.concreteconcepts;

public class Door 
{
    int ID;
    
    public Door(int IDP)
    {
        this.ID = IDP;
    }

    public int getID()
    {
        return this.ID;
    }
    
    @Override
    public String toString()
    {
        return ""+this.ID;
    }
    
    @Override
    public boolean equals(Object objP)
    {
        if (this == objP) 
            return true;
        if (objP == null || this.getClass() != objP.getClass()) 
            return false;
        
        Door anotherDoor = (Door) objP;
        if (this.ID == anotherDoor.getID())
            return true;
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        return Integer.hashCode(this.ID);
    }
}