import pet.rock.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Scanner;

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

    @Test
    public void testmakesave() {
        try {
            a = new PetRock("test", "", 1, 1, 10);
            File f = new File("SavedData.json");
            Scanner readFile = new Scanner(f);
    
            a.makeSavedData(f, a);

            readFile.nextLine();

            String expected = "  \"name\": \"test\",";
            String actual = readFile.nextLine();
            assertEquals(expected, actual);
        } catch (Exception e) {
            
        }
    }
    
    @Test
    public void testgetsave() {
        File f = new File("SavedData.json");
        a.setName("different");

        a.getSavedData(f);

        assertEquals("test", a.getName());
    }
	
	@Test
	public void testUpdateStats() {
		a = new PetRock("", "", 1, 1, 10);
		a.setName("BohanRock");
		
		a.setHunger(-1);
		a.updateStats();
		assertEquals(0, a.getHunger());
		
		a.setHunger(50);
		a.updateStats();
		assertEquals(10, a.getHunger());
		
		a.setBoredom(-1);
		a.updateStats();
		assertEquals(0, a.getBoredom());
		
		a.setBoredom(50);
		a.updateStats();
		assertEquals(10, a.getBoredom());
		
		a.setEnergy(-1);
		a.updateStats();
		assertEquals(0, a.getEnergy());
		
		a.setEnergy(50);
		a.updateStats();
		assertEquals(10, a.getEnergy());
		
		
	}
	
	
}