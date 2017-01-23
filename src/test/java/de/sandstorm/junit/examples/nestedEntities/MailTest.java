package de.sandstorm.junit.examples.nestedEntities;

import de.sandstorm.junit.examples.simpleEntity.Person;
import org.junit.Test;

import static de.sandstorm.junit.Assert.assertThat;
import static de.sandstorm.junit.examples.nestedEntities.MailMatchers.isMailLike;
import static org.junit.Assert.assertFalse;

public class MailTest {

    @Test
    public void duplicate_senderAndRecipient() {
        final Mail expected = new Mail(
            new Person("Alice", 32),
            new Person("Bob", 35)
        );
        final Mail actual = expected.duplicate();

        assertFalse("expecting a new instance", expected == actual);
        assertThat("incorrect duplicate", actual, isMailLike(expected));
    }

    @Test
    public void duplicate_onlyRecipient() {
        final Mail expected = new Mail(
            new Person("Alice", 32)
        );
        final Mail actual = expected.duplicate();

        assertFalse("expecting a new instance", expected == actual);
        assertThat("incorrect duplicate", actual, isMailLike(expected));
    }
}
