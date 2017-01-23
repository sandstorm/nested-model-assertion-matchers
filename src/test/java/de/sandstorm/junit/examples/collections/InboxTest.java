package de.sandstorm.junit.examples.collections;

import de.sandstorm.junit.examples.nestedEntities.Mail;
import de.sandstorm.junit.examples.simpleEntity.Person;
import org.junit.Test;

import java.util.Arrays;

import static de.sandstorm.junit.Assert.assertThat;
import static de.sandstorm.junit.examples.collections.InboxMatchers.isInboxLike;
import static org.junit.Assert.assertFalse;

public class InboxTest {

    @Test
    public void duplicate() {
        final Inbox expected = new Inbox(Arrays.asList(
            new Mail(
                new Person("Alice", 32),
                new Person("Bob", 35)
            ),
            new Mail(
                new Person("Carol", 25)
            )
        ));
        final Inbox actual = expected.duplicate();

        assertFalse("expecting a new instance", expected == actual);
        assertThat("incorrect duplicate", actual, isInboxLike(expected));
    }
}
