package de.sandstorm.junit.examples.simpleEntity;

/**
 * simple plain test entity
 */
public class Person {

    private final String name;
    private final int age;

    /**
     * constructor
     *
     * @param name
     * @param age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * @return exact clone of this instance
     */
    public Person duplicate() {
        return new Person(name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
