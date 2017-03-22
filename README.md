# Validate deep Models in jUnit tests with a single assert

Some Units under test deal with deeply nested data models, i.e. repositories.
Writing tests and asserting each and every property recursively can be quite cumbersome.
To avoid copy-paste code and to get meaningful test-failure description at the same time is not as straight forward.

This small library fills this gap and makes your Unit tests become as clean and well structures as your production code:

* elimination of copy-paste code
* meaningful structuring into smaller components
* short and readable test cases
* descriptive error messages

Since I used similar approaches in several projects already I decided to create this library.
It make my tests easy to write, read, maintain and fix.

# Quick start

Add this library to your test dependencies.

```
repositories {
  maven {
    url "https://dl.bintray.com/sandstorm/maven"
  }
}
dependencies {
  testCompile 'de.sandstorm.junit:nested-model-assertion-matchers:1.0.1'
}
```

1. create a `Matcher` to validate your model (mirror the class hierarchy of the model in the matchers)
2. write a unit test

```java
@Test
public void duplicate() {
  final Person expected = new Person("christoph", 31);
  final Person actual = expected.duplicate();

  assertFalse("expecting a new instance", expected == actual);
  assertThat("incorrect duplicate", actual, isPersonLike(expected));
}
```

Full source available at [PersonTest.java](https://github.com/sandstorm/nested-model-assertion-matchers/blob/master/src/test/java/de/sandstorm/junit/examples/simpleEntity/PersonTest.java).

```java
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
```

See [PersonMatchers.java](https://github.com/sandstorm/nested-model-assertion-matchers/blob/master/src/test/java/de/sandstorm/junit/examples/simpleEntity/PersonMatchers.java).

# advanced usage

Please have a look at the
[example test suite](https://github.com/sandstorm/nested-model-assertion-matchers/blob/master/src/test/java/de/sandstorm/junit/examples/).

# FAQ

## Which jUnit version are you using?

None! I don't want to be the reason you cannot update jUnit while using this library. So we do not depend on jUnit.

## Can I just use the *org.hamcrest.Matcher* already provided by jUnit instead?

Sure, but they have their downsides. I used them a lot before switching to this own approach. Mainly because:

* writing hamcrest matchers requires a bit more code
* descriptive error messages are even harder to get

## Can't I just implement it myself

You can use this library for free. If a feature is missing, please consider to create a [Pull Request](https://help.github.com/articles/about-pull-requests/).
