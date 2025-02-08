import pet.rock.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Scanner;

public class ClassTest {
    PetRock a = new PetRock("", "", 1, 1, 10);

    @Test
    public void testAddName() 
    {
        a = new PetRock("", "", 1, 1, 10);
        a.setName("Amity");

        assertEquals("Amity", a.getName());
    }
    
    @Test
    public void testSetHunger()
    {
        a = new PetRock("", "", 1, 1, 10);
        
        a.setHunger(99);
        assertEquals(10,a.getHunger());
        
        a.setHunger(-99);
        assertEquals(0,a.getHunger());
    }
    
    @Test
    public void testSetBoredom() 
    {
        a = new PetRock("", "", 1, 1, 10);
        
        a.setBoredom(99);
        assertEquals(10,a.getBoredom());
        
        a.setBoredom(-99);
        assertEquals(0,a.getBoredom());
    }
    
    public void testSetEnergy()
    {
        a = new PetRock("", "", 1, 1, 10);
        
        a.setEnergy(99);
        assertEquals(10,a.getEnergy());
        
        a.setEnergy(-99);
        assertEquals(0,a.getEnergy());
    }
  
    @Test
    public void testFeedRock()
    {
        a = new PetRock("", "", 5, 5, 5);
        
        a.feedRock(); // Hunger -= 2, Boredom++, Energy--.
        assertEquals(3, a.getHunger());
        assertEquals(6, a.getBoredom());
        assertEquals(4, a.getEnergy());
    }
  
    @Test
    public void testPlayRock()
    {
        a = new PetRock("", "", 5, 5, 5);
        
        a.playRock(); // Hunger++, Boredom -= 3, Energy -= 2.
        assertEquals(6, a.getHunger());
        assertEquals(2, a.getBoredom());
        assertEquals(3, a.getEnergy());

    }

    @Test
    public void testPolishRock() 
    {
        a = new PetRock("", "", 5, 5, 5);
         
        a.polishRock(0); // Hunger = 4, Boredom = 4, Energy = 6, Mood = "Happy".
        assertEquals(4, a.getHunger());
        assertEquals(4, a.getBoredom());
        assertEquals(6, a.getEnergy());
        assertEquals("Happy", a.getMood());
        
        a.polishRock(1); // Hunger = 3, Boredom = 4, Energy = 7, Mood = "Happy".
        assertEquals(3, a.getHunger());
        assertEquals(4, a.getBoredom());
        assertEquals(7, a.getEnergy());
        assertEquals("Happy", a.getMood());
        
        a.polishRock(2); // Hunger = 2, Boredom = 4, Energy = 7, Mood = "Happy".
        assertEquals(2, a.getHunger());
        assertEquals(4, a.getBoredom());
        assertEquals(7, a.getEnergy());
        assertEquals("Happy", a.getMood());
        
        a.polishRock(3); // All stats remain the same.
        assertEquals("Happy", a.getMood());
    }
    
    @Test
    public void testUpdateMood() 
    {
        a = new PetRock("", "", 1, 1, 1);
        a.updateMood();
        assertEquals("Tired", a.getMood());
        
        a = new PetRock("", "", 3, 3, 4);
        a.updateMood();
        assertEquals("Happy", a.getMood());
        
        a = new PetRock("", "", 6, 4, 5);
        a.updateMood();
        assertEquals("Bored", a.getMood());
        
        a = new PetRock("", "", 8, 8, 3);
        a.updateMood();
        assertEquals("Sad", a.getMood());
    }

    @Test
    public void testMakeSave() 
    {
        try 
        {
            a = new PetRock("test", "", 1, 1, 10);
            File f = new File("SavedData.json");
            Scanner readFile = new Scanner(f);
    
            a.makeSavedData(f, a);

            readFile.nextLine();

            String expected = "  \"name\": \"test\",";
            String actual = readFile.nextLine();
            assertEquals(expected, actual);
        } 
        
        catch (Exception e) 
        {
            
        }
    }
    
    @Test
    public void testGetSave() 
    {
        File f = new File("SavedData.json");
        a.setName("test");

        a.getSavedData(f);

        assertEquals("test", a.getName());
    }
}