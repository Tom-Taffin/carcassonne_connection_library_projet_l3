package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;
import l3s6.projet.star.interaction.view.AdminView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ElectCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new ElectCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("ARB ELECTS player Sam" ,this.command.build("ARB", "player", "Sam"));
        assertEquals("ARB ELECTS player Sam Rem Tom" ,this.command.build("ARB", "player", "Sam", "Rem", "Tom"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "player");});
    }

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";

        List<String> parts1 = List.of("player", "Rem");
        this.command.execute(id, parts1, mockView);
        verify(mockView).updateOnElect(id, parts1.get(0), parts1.subList(1, parts1.size()));

        List<String> parts2 = List.of("player", "Rem", "Tom", "Sam");
        this.command.execute(id, parts2, mockView);
        verify(mockView).updateOnElect(id, parts1.get(0), parts2.subList(1, parts2.size()));
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";
        List<String> invalidParts1 = List.of();
        List<String> invalidParts2 = List.of("player");

        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts1, mockView); });
        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts2, mockView); });

        verify(mockView, never()).updateOnElect(anyString(), anyString(), anyList());
    }

}
