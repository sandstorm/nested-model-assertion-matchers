package de.sandstorm.junit.examples.maps;

import de.sandstorm.junit.examples.simpleEntity.Person;
import org.junit.Test;

import java.util.Arrays;

import static de.sandstorm.junit.Assert.assertThat;
import static de.sandstorm.junit.examples.maps.PersonRegistryMatchers.isPersonRegistryLike;
import static org.junit.Assert.assertFalse;

public class PersonRegistryTest {

    @Test
    public void duplicate() {
        final PersonRegistry expected = new PersonRegistry(Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 36),
            new Person("Carol", 47),
            new Person("Dave", 58)
        ));
        final PersonRegistry actual = expected.duplicate();

        assertFalse("expecting a new instance", expected == actual);
        assertThat("incorrect duplicate", actual, isPersonRegistryLike(expected));
    }
}
