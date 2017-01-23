package de.sandstorm.junit.examples.nestedEntities;

import de.sandstorm.junit.AbstractTypeSafeMatcher;
import de.sandstorm.junit.Matcher;

import static de.sandstorm.junit.Matcher.assertMatches;
import static de.sandstorm.junit.examples.simpleEntity.PersonMatchers.isPersonLike;

/**
 * factory methods to create {@link de.sandstorm.junit.Matcher}s for {@link Mail}s
 */
public class MailMatchers {

    public static Matcher isMailLike(Mail expected) {
        return new MailMatcher(expected);
    }

    private static class MailMatcher extends AbstractTypeSafeMatcher<Mail> {

        public MailMatcher(Mail expected) {
            super(expected);
        }

        @Override
        protected void typeSafeValidate(Mail expected, Mail actual) throws Throwable {
            assertMatches("recipient", isPersonLike(expected.getRecipient()), actual.getRecipient());
            assertMatches("sender", isPersonLike(expected.getSender()), actual.getSender());
        }
    }
}
