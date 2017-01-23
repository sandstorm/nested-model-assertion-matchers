package de.sandstorm.junit.examples.collections;

import de.sandstorm.junit.AbstractTypeSafeMatcher;
import de.sandstorm.junit.Matcher;

import static de.sandstorm.junit.Matcher.assertEachMatches;
import static de.sandstorm.junit.examples.nestedEntities.MailMatchers.isMailLike;

/**
 * factory methods to create {@link de.sandstorm.junit.Matcher}s for {@link Inbox}s
 */
public class InboxMatchers {

    public static Matcher isInboxLike(Inbox expected) {
        return new InboxMatcher(expected);
    }

    private static class InboxMatcher extends AbstractTypeSafeMatcher<Inbox> {

        public InboxMatcher(Inbox expected) {
            super(expected);
        }

        @Override
        protected void typeSafeValidate(Inbox expected, Inbox actual) throws Throwable {
            assertEachMatches("content", expected.getContent(), actual.getContent(), (e, a) -> isMailLike(e).validate(a));
        }
    }
}
