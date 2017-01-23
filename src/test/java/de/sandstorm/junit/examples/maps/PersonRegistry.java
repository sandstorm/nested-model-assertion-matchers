package de.sandstorm.junit.examples.maps;

import de.sandstorm.junit.examples.simpleEntity.Person;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * test entity containing maps
 */
public class PersonRegistry {

    private final Collection<Person> persons;

    public PersonRegistry(Collection<Person> persons) {
        this.persons = persons;

    }

    /**
     * @return exact clone of this instance
     */
    public PersonRegistry duplicate() {
        return new PersonRegistry(
            persons.stream().map(Person::duplicate).collect(Collectors.toList())
        );
    }

    public Map<String, Person> getPersonByName() {
        final Map<String, Person> personByName = new HashMap<>();
        for (Person person : persons) {
            personByName.put(person.getName(), person);
        }
        return personByName;
    }

    public Map<Integer, Person> getPersonByAge() {
        final Map<Integer, Person> personByAge = new HashMap<>();
        for (Person person : persons) {
            personByAge.put(person.getAge(), person);
        }
        return personByAge;
    }
}
