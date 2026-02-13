package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PlaceCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new PlaceCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam PLACES f-f-f-f 2:3 bleu" ,this.command.build("Sam", "f-f-f-f", 2, 3, "bleu"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "f-f-f-f", 2, 3);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "f-f-f-f", 2, 3, "bleu", "wrong argument");});
    }

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";
        List<String> parts = List.of("f-f-f-f", "2:3", "meep");

        this.command.execute(id, parts, mockView);

        verify(mockView).updateOnPlace(id, parts.get(0), 2, 3, parts.get(2));
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";
        List<String> invalidParts1 = List.of("f-f-f-f", "2:3");
        List<String> invalidParts2 = List.of("f-f-f-f", "2:3", "meep", "invalid_part");

        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts1, mockView); });
        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts2, mockView); });

        verify(mockView, never()).updateOnPlace(anyString(), anyString(), anyInt(), anyInt(), anyString());
    }

}
