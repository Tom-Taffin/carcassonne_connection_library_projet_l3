package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CloseCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new CloseCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam CLOSES" ,this.command.build("Sam", null));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "wrong argument");});
    }

}
