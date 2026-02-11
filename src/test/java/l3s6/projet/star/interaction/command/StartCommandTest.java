package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StartCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new StartCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("ABR STARTS" ,this.command.build("ABR", null));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("ABR", "wrong argument");});
    }

}
