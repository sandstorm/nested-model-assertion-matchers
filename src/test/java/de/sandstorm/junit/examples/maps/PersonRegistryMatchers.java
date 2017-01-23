package de.sandstorm.junit.examples.maps;

import de.sandstorm.junit.AbstractTypeSafeMatcher;
import de.sandstorm.junit.Matcher;

import static de.sandstorm.junit.Matcher.assertEachMatches;
import static de.sandstorm.junit.examples.simpleEntity.PersonMatchers.isPersonLike;

/**
 * factory methods to create {@link de.sandstorm.junit.Matcher}s for {@link PersonRegistry}s
 */
public class PersonRegistryMatchers {

    public static Matcher isPersonRegistryLike(PersonRegistry expected) {
        return new PersonRegistryMatcher(expected);
    }

    private static class PersonRegistryMatcher extends AbstractTypeSafeMatcher<PersonRegistry> {
        public PersonRegistryMatcher(PersonRegistry expected) {
            super(expected);
        }

        @Override
        protected void typeSafeValidate(PersonRegistry expected, PersonRegistry actual) throws Throwable {
            assertEachMatches("personByName", expected.getPersonByName(), actual.getPersonByName(), (e, a) -> isPersonLike(e).validate(a));
            assertEachMatches("personByAge", expected.getPersonByAge(), actual.getPersonByAge(), (e, a) -> isPersonLike(e).validate(a));
        }
    }
}
