package np.containers;

import np.concepts.abstractconcepts.MontyRandomness;
import np.concepts.concreteconcepts.MontyDoor;

public class MontyDoors 
{

    /**
     * 
     * @param aDoorListP an array of type MontyDoor
     * @param positionToBeRemovedP the index of the door that we wish to remove from the array aDoorListP
     * @return Returns an array with the appropriate size that contains all the doors contained in aDoorListP, except the one in position positionToBeRemovedP
     */
    public static MontyDoor[] removeADoor(MontyDoor[] aDoorListP, int positionToBeRemovedP)
    {
        MontyDoor[] toReturn;
        if (positionToBeRemovedP>=0 && positionToBeRemovedP < aDoorListP.length)
        {
            toReturn = new MontyDoor[aDoorListP.length-1];
            System.arraycopy(aDoorListP, 0, toReturn, 0, positionToBeRemovedP);
            System.arraycopy(aDoorListP, positionToBeRemovedP+1, toReturn, positionToBeRemovedP, aDoorListP.length-positionToBeRemovedP-1);
        }
        else 
            return aDoorListP;
        return toReturn;
    }
    
    /**
     *  
     * @param aDoorListP an array of type MontyDoor
     * @return Returns the index of the prize winning door within the array, and -1 if the array has no winning door, or more than one winning doors.
     */
    public static int getLocationOfPrizeWinningDoor(MontyDoor[] aDoorListP)
    {
        int prizeLocation = -1;
        for (int i=0; i<aDoorListP.length; i++)
        {
            if(aDoorListP[i].hasPrize() && prizeLocation==-1)
                prizeLocation = i;
            else if (aDoorListP[i].hasPrize() && prizeLocation>=0)
                return -1; // Fail fast if we somehow found more than one prize!
        }
        return prizeLocation;
    }
    
    /**
     * 
     * @param aDoorListP an array of type MontyDoor
     * @return Returns the door that contains the prize within the array or null, in the case the array has no winning or more than one winning doors.
     */
    public static MontyDoor getPrizeWinningDoor(MontyDoor[] aDoorListP)
    {
        int indexOfDoorToReturn = getLocationOfPrizeWinningDoor(aDoorListP);
        if (indexOfDoorToReturn!=-1)
            return aDoorListP[indexOfDoorToReturn];
        else
            return null;
    }
    
    /**
     * 
     * @param aDoorListP an array of type MontyDoor
     * @return Returns an array that consists of ONLY the doors that do not contain the prize
     */
    public static MontyDoor[] getDoorsThatDoNOTContainThePrize(MontyDoor[] aDoorListP)
    {
        return removeADoor(aDoorListP, getLocationOfPrizeWinningDoor(aDoorListP));
    }
    
    /**
     * 
     * @param aDoorListP an array of type MontyDoor
     * @param idWeWantP the id we are looking for, given as an integer
     * @return Returns the index of the door with id equal to idWeWantP. If no such door exist, or more than one doors have the same id then it returns -1;
     */
    public static int getLocationOfDoorWithID(MontyDoor[] aDoorListP, int idWeWantP)
    {
        int doorWithCorrectIDLocationIndex = -1;
        for (int i=0; i<aDoorListP.length; i++)
        {
            if(aDoorListP[i].getID()==idWeWantP && doorWithCorrectIDLocationIndex==-1)
                doorWithCorrectIDLocationIndex = i;
            else if (aDoorListP[i].getID()==idWeWantP && doorWithCorrectIDLocationIndex>=0)
                return -1; // Fail fast if we have duplicates!
        }
        return doorWithCorrectIDLocationIndex;
    }
    
    /**
     * Removes a random door from the list that does NOT have a prize behind it.
     * @param aDoorListP
     * @param randomnessP
     * @return
     */
    public static MontyDoor[] removeARandomNonPrizeWinningDoor(MontyDoor[] aDoorListP, MontyRandomness randomnessP)
    {
        MontyDoor[] doorsThatDoNotContainAPrize = getDoorsThatDoNOTContainThePrize(aDoorListP);
        int indexOfDoorToBeRemoved = randomnessP.getARandomIntBetween0And(doorsThatDoNotContainAPrize.length-1);
        MontyDoor doorToRemove = doorsThatDoNotContainAPrize[indexOfDoorToBeRemoved];
        int doorToRemoveID = doorToRemove.getID();
        int locationOfTheDoorWeAreRemovingInaDoorListP = getLocationOfDoorWithID(aDoorListP, doorToRemoveID);
        return removeADoor(aDoorListP, locationOfTheDoorWeAreRemovingInaDoorListP);
    }
}