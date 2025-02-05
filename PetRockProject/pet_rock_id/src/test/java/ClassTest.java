import pet.rock.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ClassTest {
    PetRock a = new PetRock("", "", 1, 1, 10);

    @Test
    public void testAddName() {
        a.setName("Amity");

        assertEquals("Amity", a.getName());
    }
	
	@Test
    public void testSetBoredom()
    {
        a.setBoredom(50);

        assertEquals(50, a.getBoredom());
    }
}