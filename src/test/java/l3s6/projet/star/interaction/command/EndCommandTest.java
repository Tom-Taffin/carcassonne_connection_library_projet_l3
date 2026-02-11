package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EndCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new EndCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam ENDS Rem Tom" ,this.command.build("Sam", "Rem", "Tom"));
        assertEquals("Sam ENDS Andrei" ,this.command.build("Sam", "Andrei"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
    }

}
