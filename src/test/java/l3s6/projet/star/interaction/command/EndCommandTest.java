package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";
        List<String> parts = Arrays.asList("Rem", "Tom");

        this.command.execute(id, parts, mockView);

        verify(mockView).updateOnEnd("Sam", parts);
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {

    }

}
