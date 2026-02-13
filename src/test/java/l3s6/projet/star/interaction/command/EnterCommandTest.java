package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.Test;

import l3s6.projet.star.interaction.view.AbstractView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

public class EnterCommandTest extends AbstractCommandTest {

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

    @Test
    public void testExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";
        List<String> parts = List.of();
        this.command.execute(id, parts, mockView);
        verify(mockView).updateOnEnter("Sam");
    }

}
