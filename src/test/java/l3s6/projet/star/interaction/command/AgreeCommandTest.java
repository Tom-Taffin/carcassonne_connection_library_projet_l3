package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AgreeCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new AgreeCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam AGREES inns traders blitz" ,this.command.build("Sam", "inns", "traders", "blitz"));
        assertEquals("Sam AGREES inns" ,this.command.build("Sam", "inns"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
    }

}
