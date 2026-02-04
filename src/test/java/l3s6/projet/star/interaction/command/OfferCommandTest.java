package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OfferCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new OfferCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam OFFERS Rem f-f-f-f" ,this.command.build("Sam", "Rem", "f-f-f-f"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "Rem");});
    }

}
