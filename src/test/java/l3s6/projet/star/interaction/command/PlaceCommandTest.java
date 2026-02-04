package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlaceCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new PlaceCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam PLACES f-f-f-f 2:3 bleu" ,this.command.build("Sam", "f-f-f-f", 2, 3, "bleu"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "f-f-f-f", 2, 3);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "f-f-f-f", 2, 3, "bleu", "wrong argument");});
    }

}
