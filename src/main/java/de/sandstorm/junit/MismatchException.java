package de.sandstorm.junit;

/**
 * Indicates a validation error contains information about which property is incorrect and why.
 */
public class MismatchException extends RuntimeException {

    private final String propertyName;
    private final Object expected;
    private final Object actual;

    /**
     * constructor
     *
     * @param propertyName name of the property with incorrect values somewhere inside
     * @param cause        sub-mismatch
     */
    public MismatchException(String propertyName, Throwable cause) {
        this(propertyName, null, null, cause);
    }

    /**
     * constuctor
     *
     * @param propertyName name of the incorrect property
     * @param expected     expected value
     * @param actual       actual value
     */
    public MismatchException(String propertyName, Object expected, Object actual) {
        this(propertyName, expected, actual, null);
    }

    private MismatchException(String propertyName, Object expected, Object actual, Throwable cause) {
        super(null, cause);
        this.propertyName = propertyName;
        this.expected = expected;
        this.actual = actual;
    }

    @Override
    public String getMessage() {
        return String.format("incorrect '%s', %s", createPropertyPath(), createDescription());
    }

    private String createPropertyPath() {
        final StringBuilder path = new StringBuilder();
        appendPropertyPath(path);
        return path.substring(1);
    }

    private void appendPropertyPath(StringBuilder path) {
        path.append(".");
        path.append(propertyName);
        final Throwable cause = this.getCause();
        if (cause != null && cause instanceof MismatchException) {
            ((MismatchException) cause).appendPropertyPath(path);
        }
    }

    private String createDescription() {
        final Throwable cause = this.getCause();
        if (cause != null) {
            if (cause instanceof MismatchException) {
                return ((MismatchException) cause).createDescription();
            } else {
                return cause.toString();
            }
        } else {
            return String.format("expected '%s' but is '%s'", String.valueOf(expected), String.valueOf(actual));
        }
    }
}
