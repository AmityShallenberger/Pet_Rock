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
        a.setHunger(99);
        assertEquals(10,a.getHunger());
        a.setHunger(-99);
        assertEquals(0,a.getHunger());

    }
    @Test
    public void testFeedRock(){
        a = new PetRock("", "", 5, 5, 5);
        a.feedRock();
        
        //   hunger -= 2;
        //   boredom += 1;
        //   energy -= 1;
        assertEquals(3, a.getHunger());
        assertEquals(6, a.getBoredom());
        assertEquals(4, a.getEnergy());
    }
    @Test
    public void testPlayRock(){
        a = new PetRock("", "", 5, 5, 5);
        a.playRock();

        //boredom -= 3;
        //hunger += 1;
        //energy -= 2;
        assertEquals(6, a.getHunger());
        assertEquals(2, a.getBoredom());
        assertEquals(3, a.getEnergy());

    }

}