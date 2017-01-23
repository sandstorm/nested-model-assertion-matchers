package de.sandstorm.junit.examples.simpleEntity;

import de.sandstorm.junit.AbstractTypeSafeMatcher;
import de.sandstorm.junit.Matcher;

import static de.sandstorm.junit.Matcher.assertAreEqual;

/**
 * factory methods to create {@link de.sandstorm.junit.Matcher}s for {@link Person}s
 */
public class PersonMatchers {

    /**
     * @param expected expected value
     * @return matcher to validate an actual value
     */
    public static Matcher isPersonLike(Person expected) {
        return new PersonMatcher(expected);
    }

    private static class PersonMatcher extends AbstractTypeSafeMatcher<Person> {

        /**
         * constructor
         *
         * @param expected expected value
         */
        PersonMatcher(Person expected) {
            super(expected);
        }

        @Override
        protected void typeSafeValidate(Person expected, Person actual) throws Throwable {
            assertAreEqual("name", expected.getName(), actual.getName());
            assertAreEqual("age", expected.getAge(), actual.getAge());
        }
    }
}
