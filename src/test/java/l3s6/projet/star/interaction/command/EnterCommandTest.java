package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnterCommandTest extends AbstractCommandTest {

    @BeforeEach
    public void init(){
        this.command = this.getCommand();
    }

    public AbstractCommand getCommand(){
        return new EnterCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam ENTERS" ,this.command.build("Sam", null));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "wrong argument");});
    }

}
