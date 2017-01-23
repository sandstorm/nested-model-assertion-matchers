package de.sandstorm.junit.examples.customValidators;


import de.sandstorm.junit.AbstractTypeSafeMatcher;
import de.sandstorm.junit.Matcher;

import static de.sandstorm.junit.Matcher.assertAreEqual;
import static de.sandstorm.junit.Matcher.assertMatches;

/**
 * factory methods to create {@link de.sandstorm.junit.Matcher}s for {@link Utf8String}s
 */
public class Utf8StringMatchers {

    public static Matcher isUtf8StringLike(Utf8String expected) {
        return new Utf8StringMatcher(expected);
    }

    private static class Utf8StringMatcher extends AbstractTypeSafeMatcher<Utf8String> {

        public Utf8StringMatcher(Utf8String expected) {
            super(expected);
        }

        @Override
        protected void typeSafeValidate(Utf8String expected, Utf8String actual) throws Throwable {
            assertMatches("getBytes()", expected.getBytes(), actual.getBytes(), (e, a) -> {
                assertAreEqual("asString", new String(e, "UTF-8"), new String((byte[]) a, "UTF-8"));
            });
        }
    }
}
