package de.sandstorm.junit;

/**
 * collection of static assertion methods for unit tests
 */
public class Assert {

    /**
     * @param actual  actual value
     * @param matcher matcher to validate actual value
     */
    public static void assertThat(Object actual, Matcher matcher) {
        assertThat("", actual, matcher);
    }

    /**
     * @param message custom message
     * @param actual  actual value
     * @param matcher matcher to validate actual value
     */
    public static void assertThat(String message, Object actual, Matcher matcher) {
        try {
            matcher.validate(actual);
        } catch (Throwable e) {
            if (message != null && message.length() > 0) {
                throw new AssertionError(String.format("%s: %s", message, e.getMessage()));
            } else {
                throw new AssertionError(e.getMessage());
            }
        }
    }

}
