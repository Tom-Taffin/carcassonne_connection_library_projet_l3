package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoreCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new ScoreCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam SCORES Rem 10" ,this.command.build("Sam", "Rem", "10"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "Rem");});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "Rem", "10", "wrong argument");});
    }

}
