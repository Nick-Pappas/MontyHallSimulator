package np.executable;

import np.concepts.abstractconcepts.MontyGameSetup;
import np.concepts.abstractconcepts.MontyRandomness;
import np.concepts.abstractconcepts.Percentage;
import np.concepts.abstractconcepts.Strategy;
import np.concepts.concreteconcepts.Monty;
import np.concepts.concreteconcepts.Player;
import java.util.Random;

public class PlayMontyExample
{
    public static void main(String[] args) 
    {
        long seed = 11;
        Percentage percentage1 = new Percentage((float)100);
        Percentage percentage2 = new Percentage((float)50);
        Percentage percentage3 = new Percentage((float)0);
        Percentage percentage4 = new Percentage((float)33);
        Strategy strategy1 = new Strategy(percentage1, "Nick is always switching");
        Strategy strategy2 = new Strategy(percentage2, "Carl is switching about half the time");
        Strategy strategy3 = new Strategy(percentage3, "George is never switching");
        Strategy strategy4 = new Strategy(percentage4, "Jenna is switching one out of three times");
        MontyRandomness randomness = new MontyRandomness(seed);
        Player p1 = new Player("Nick", strategy1, randomness);
        Player p2 = new Player("Carl", strategy2, randomness);
        Player p3 = new Player("George", strategy3, randomness);
        Player p4 = new Player("Jenna", strategy4, randomness);
        Player[] players = {p1, p2, p3, p4};
        
        MontyGameSetup mgSetup = new MontyGameSetup(3, players, 1, randomness);
        Monty m = new Monty(mgSetup);
        
        Random masterSeedGenerator = new Random(seed);

        for (int i=0; i<1000000; i++)
        {
            // Instead of throwing away objects, we simply reset the state for the new round.
            mgSetup.resetRound(randomness);
            
            m.askPlayersToPickADoor();
            m.openANonPrizeDoorAmongTheRemainingDoorsForEachPlayer(randomness);
            m.askThePlayersIfTheyWantToSwitch(randomness);
            m.calculatePlayersWinnings();
            
            // Re-seed the randomness for the next round. This guarantees determinism.
            // When I have to grade 700 people, I need to know that the 500th run does not 
            // depend on what happened during the 499th run. Giving each iteration a brand 
            // new seed from the master generator ensures consistent, repeatable execution.
            randomness.reset(masterSeedGenerator.nextLong());
        }
        
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        System.out.println(p4.toString());
    }
}