import pet.rock.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class tests {
    PetRock a = new PetRock("", "", 1, 1, 10);

    @Test
    public void testAddName() {
        a.setName("Amity");

        assertEquals("Amity", a.getName());
    }
}