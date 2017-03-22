package de.sandstorm.junit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MismatchExceptionTest {

    @Test
    public void getMessage_simple() {
        assertEquals(
            "incorrect message",
            incorrect("foo", "bar", "not bar"),
            new MismatchException("foo", "bar", "not bar").getMessage()
        );
    }

    @Test
    public void getMessage_nested() {
        assertEquals(
            "incorrect message",
            incorrect("long.path.to.error.0.foo", "bar", "not bar"),
            new MismatchException("long",
                new MismatchException("path",
                    new MismatchException("to",
                        new MismatchException("error",
                            new MismatchException("0",
                                new MismatchException("foo", "bar", "not bar")
                            )
                        )
                    )
                )
            ).getMessage()
        );
    }

    @Test
    public void getMessage_exception_during_comparison() {
        assertEquals(
            "incorrect message",
            "incorrect 'long.path.to.error.0', java.lang.Exception: here the test code crashed for some reason",
            new MismatchException("long",
                new MismatchException("path",
                    new MismatchException("to",
                        new MismatchException("error",
                            new MismatchException("0",
                                new Exception("here the test code crashed for some reason")
                            )
                        )
                    )
                )
            ).getMessage()
        );
    }

    private static String incorrect(String propertyPath, String expected, String actual) {
        return String.format("incorrect '%s', expected '%s' but is '%s'", propertyPath, expected, actual);
    }

}
