package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ElectCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new ElectCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("ARB ELECTS player Sam" ,this.command.build("ARB", "player", "Sam"));
        assertEquals("ARB ELECTS player Sam Rem Tom" ,this.command.build("ARB", "player", "Sam", "Rem", "Tom"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "player");});
    }

}
