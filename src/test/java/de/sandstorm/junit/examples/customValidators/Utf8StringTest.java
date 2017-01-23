package de.sandstorm.junit.examples.customValidators;

import org.junit.Test;

import static de.sandstorm.junit.Assert.assertThat;
import static de.sandstorm.junit.examples.customValidators.Utf8StringMatchers.isUtf8StringLike;
import static org.junit.Assert.assertFalse;

public class Utf8StringTest {

    @Test
    public void duplicate() {
        final Utf8String expected = new Utf8String("Hello World!");
        final Utf8String actual = expected.duplicate();

        assertFalse("expecting a new instance", expected == actual);
        assertThat("incorrect duplicate", actual, isUtf8StringLike(expected));
    }
}
