import pet.rock.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    PetRock a = new PetRock("", "", 1, 1, 10);
    
    @Test
    public void testIncrementGameOverCounter()
    {
        a = new PetRock("", "", 1, 1, 0); // rock has no energy.
        
        int counter = PetRockMain.incrementGameOverCounter(a, 0);
        assertEquals(1, counter);
    }
    
    @Test
    public void testDoAction()
    {
        a = new PetRock("", "", 5, 5, 10);
        
        PetRockMain.doAction(a, 1); // Hunger = 3, Boredom = 6, Energy = 9.
        assertEquals(3, a.getHunger());
        assertEquals(6, a.getBoredom());
        assertEquals(9, a.getEnergy());
        
        PetRockMain.doAction(a, 2); // Hunger = 4, Boredom = 3, Energy = 7.
        assertEquals(4, a.getHunger());
        assertEquals(3, a.getBoredom());
        assertEquals(7, a.getEnergy());
        
        PetRockMain.polishDiminishReturnCurrent = 2;
        
        PetRockMain.doAction(a, 3); // Hunger = 3, Boredom = 3, Energy = 7.
        assertEquals(3, a.getHunger());
        assertEquals(3, a.getBoredom());
        assertEquals(7, a.getEnergy());
        
        PetRockMain.doAction(a, 4); // Hunger = 3, Boredom = 3, Energy = 8.
        assertEquals(a.getEnergy(), 8);
    }
    
    @Test
    public void testGameEndCheck() 
    {
        a = new PetRock("", "", 10, 1, 10); // max hunger.
        boolean temp = PetRockMain.gameEndCheck(a, 0);
        assertEquals(true, temp);
        
        a = new PetRock("", "", 1, 10, 10); // max boredom.
        temp = PetRockMain.gameEndCheck(a, 0);
        assertEquals(true, temp);
        
        a = new PetRock("", "", 1, 1, 0); // no energy, first turn.
        temp = PetRockMain.gameEndCheck(a, 0);
        assertEquals(false, temp);
        
        a = new PetRock("", "", 1, 1, 0); // no energy, third turn.
        temp = PetRockMain.gameEndCheck(a, 3);
        assertEquals(true, temp);
    }
    
    @Test
    public void testFeed()
    {
        a = new PetRock("", "", 5, 5, 0);
        PetRockMain.feed(a); // shouldn't do anything bc no energy.
        assertEquals(5, a.getHunger());
        assertEquals(5, a.getBoredom());
        assertEquals(0, a.getEnergy());
        
        a = new PetRock("", "", 5, 5, 10);
        PetRockMain.feed(a); 
        assertEquals(3, a.getHunger());
        assertEquals(6, a.getBoredom());
        assertEquals(9, a.getEnergy());
    }
    
    @Test
    public void testPlay()
    {
        a = new PetRock("", "", 5, 5, 1);
        PetRockMain.play(a); // shouldn't do anything bc not enough energy.
        assertEquals(5, a.getHunger());
        assertEquals(5, a.getBoredom());
        assertEquals(1, a.getEnergy());
        
        a = new PetRock("", "", 5, 5, 10);
        PetRockMain.play(a);
        assertEquals(6, a.getHunger());
        assertEquals(2, a.getBoredom());
        assertEquals(8, a.getEnergy());
    }
    
    @Test
    public void testPolish()
    {
        a = new PetRock("", "", 5, 5, 5);
        PetRockMain.polishDiminishReturnCurrent = 0;
        PetRockMain.polish(a);
        
        assertEquals(4, a.getHunger());
        assertEquals(4, a.getBoredom());
        assertEquals(6, a.getEnergy());
        assertEquals("Happy", a.getMood());
    }
}
