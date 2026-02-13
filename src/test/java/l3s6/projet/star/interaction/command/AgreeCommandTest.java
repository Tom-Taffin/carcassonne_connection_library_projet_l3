package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";

        List<String> parts1 = Arrays.asList("inns", "traders", "blitz");
        this.command.execute(id, parts1, mockView);
        verify(mockView).updateOnAgree(id, parts1);

        List<String> parts2 = List.of();
        this.command.execute(id, parts2, mockView);
        verify(mockView).updateOnAgree(id, parts2);
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {

    }

}
