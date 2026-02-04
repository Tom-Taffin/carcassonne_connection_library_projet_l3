package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GrantCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new GrantCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam GRANTS Rem EXPELS" ,this.command.build("Sam", "Rem", "EXPELS"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "Rem");});
    }

}
