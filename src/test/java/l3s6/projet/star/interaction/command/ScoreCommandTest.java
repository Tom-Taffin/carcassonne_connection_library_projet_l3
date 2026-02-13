package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";
        List<String> parts = List.of("Rem", "10");

        this.command.execute(id, parts, mockView);

        verify(mockView).updateOnScore(id, "Rem", 10);
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";
        List<String> invalidParts1 = List.of("Rem");
        List<String> invalidParts2 = List.of("Rem", "10", "invalid_part");

        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts1, mockView); });
        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts2, mockView); });

        verify(mockView, never()).updateOnScore(anyString(), anyString(), anyInt());
    }

}
