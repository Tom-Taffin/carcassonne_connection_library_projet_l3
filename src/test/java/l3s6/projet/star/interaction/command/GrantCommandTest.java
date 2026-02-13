package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;
import l3s6.projet.star.interaction.view.AdminView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class GrantCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new GrantCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam GRANTS Rem EXPELS" ,this.command.build("Sam", "Rem", "EXPELS"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "Rem");});
    }

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        AdminView mockView = mock(AdminView.class);
        String id = "Sam";

        List<String> parts1 = List.of("Rem", "CMD");
        this.command.execute(id, parts1, mockView);
        verify(mockView).updateOnGrant(id, parts1.get(0), parts1.subList(1, parts1.size()));

        List<String> parts2 = List.of("Rem", "CMD1", "CMD2", "CMD3");
        this.command.execute(id, parts2, mockView);
        verify(mockView).updateOnGrant(id, parts1.get(0), parts2.subList(1, parts2.size()));
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {
        AdminView mockView = mock(AdminView.class);
        String id = "Sam";
        List<String> invalidParts1 = List.of();
        List<String> invalidParts2 = List.of("Rem");

        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts1, mockView); });
        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts2, mockView); });

        verify(mockView, never()).updateOnGrant(anyString(), anyString(), anyList());
    }

}
