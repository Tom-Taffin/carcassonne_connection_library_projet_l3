package l3s6.projet.star.interaction.command;

/**
 * Exception thrown when a command receives an invalid number of arguments.
 */
public class InvalidArgumentNumberException extends Exception {

    /**
     * Constructs an InvalidArgumentNumberException with the specified detail message.
     * @param message the detail message describing the cause of the exception
     */
    public InvalidArgumentNumberException(String message) {
        super(message);
    }
}