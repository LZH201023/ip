package duck;

/**
 * Represents a custom exception used in the Duck application.
 * This exception is thrown when an application-specific error occurs.
 */
public class DuckException extends Exception {

    /**
     * Constructs a {@code DuckException} with the specified error message.
     *
     * @param msg The detail message describing the error.
     */
    public DuckException(String msg) {
        super(msg);
    }

    /**
     * Constructs a {@code DuckException} without a detail message.
     */
    public DuckException() {
        super();
    }
}
