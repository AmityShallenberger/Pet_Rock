import pet.rock.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ClassTest {
    PetRock a = new PetRock("", "", 1, 1, 10);

    @Test
    public void testAddName() {
        a = new PetRock("", "", 1, 1, 10);
        a.setName("Amity");

        assertEquals("Amity", a.getName());
    }
    
    @Test
    public void testSetHunger(){
        a = new PetRock("", "", 1, 1, 10);
        
    }
    
    @Test
    public void testPolishRock() {
        a = new PetRock("", "", 5, 5, 5);
        int diminishingReturning = 0;
         
        a.polishRock(diminishingReturning);
        
        assertEquals(4, a.getHunger());
        assertEquals(4, a.getBoredom());
        assertEquals(6, a.getEnergy());
        assertEquals("Happy", a.getMood());
    }
    
    @Test
    public void testUpdateMood() {
        a = new PetRock("", "", 1, 1, 10);
        
        a.setBoredom(6);
        a.setEnergy(4);
        a.setHunger(5);
        
        a.updateMood();
        
        assertEquals("Bored", a.getMood());
    }
}