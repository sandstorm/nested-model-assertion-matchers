package de.sandstorm.junit;

/**
 * assertion failure
 */
public class AssertionError extends RuntimeException {

    /**
     * constructor
     *
     * @param message description of the error
     */
    public AssertionError(String message) {
        super(message);
    }
}
