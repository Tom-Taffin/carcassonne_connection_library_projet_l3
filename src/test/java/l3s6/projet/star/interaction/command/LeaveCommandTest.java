package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LeaveCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new LeaveCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam LEAVES" ,this.command.build("Sam", null));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "wrong argument");});
    }

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";
        List<String> parts = List.of();

        this.command.execute(id, parts, mockView);

        verify(mockView).updateOnLeave(id);
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";
        List<String> invalidParts = Arrays.asList("invalid_part");

        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts, mockView); });

        verify(mockView, never()).updateOnLeave(anyString());
    }

}
