package de.sandstorm.junit.examples.simpleEntity;

import org.junit.Test;

import static de.sandstorm.junit.Assert.assertThat;
import static de.sandstorm.junit.examples.simpleEntity.PersonMatchers.isPersonLike;
import static org.junit.Assert.assertFalse;

public class PersonTest {

    @Test
    public void duplicate() {
        final Person expected = new Person("christoph", 31);
        final Person actual = expected.duplicate();

        assertFalse("expecting a new instance", expected == actual);
        assertThat("incorrect duplicate", actual, isPersonLike(expected));
    }
}
