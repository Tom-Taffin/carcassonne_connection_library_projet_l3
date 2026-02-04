package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpelCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new ExpelCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam EXPELS Rem" ,this.command.build("Sam", "Rem"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "Rem", "wrong argument");});
    }

}
