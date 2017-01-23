package de.sandstorm.junit;

import org.junit.Test;

import static de.sandstorm.junit.Matcher.assertAreEqual;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class AbstractTypeSafeMatcherTest {

    @Test
    public void validate_green_bothNull() throws Throwable {
        final TestAbstractTypeSafeMatcher matcher = new TestAbstractTypeSafeMatcher(null);
        matcher.validate(null);
    }

    @Test
    public void validate_green_sameInstance() throws Throwable {
        final TestEntity entity = new TestEntity("christoph", 31);
        final TestAbstractTypeSafeMatcher matcher = new TestAbstractTypeSafeMatcher(entity);
        matcher.validate(entity);
    }

    @Test
    public void validate_red_expectedNull() throws Throwable {
        final TestAbstractTypeSafeMatcher matcher = new TestAbstractTypeSafeMatcher(null);
        try {
            matcher.validate(new TestEntity("christoph", 31));
            fail("expecting " + MismatchException.class.getSimpleName());
        } catch (MismatchException e) {
            assertTrue("incorrect message", e.getMessage().contains("null"));
        }
    }

    @Test
    public void validate_red_unexpectedType() throws Throwable {
        final TestAbstractTypeSafeMatcher matcher = new TestAbstractTypeSafeMatcher(new TestEntity("christoph", 31));
        try {
            matcher.validate("Hello World");
            fail("expecting " + MismatchException.class.getSimpleName());
        } catch (MismatchException e) {
            assertTrue("incorrect message", e.getMessage().contains(TestEntity.class.getSimpleName()));
            assertTrue("incorrect message", e.getMessage().contains(String.class.getSimpleName()));
        }
    }

    @Test
    public void validate_red_actualNull() throws Throwable {
        final TestAbstractTypeSafeMatcher matcher = new TestAbstractTypeSafeMatcher(new TestEntity("christoph", 31));
        try {
            matcher.validate(null);
            fail("expecting " + MismatchException.class.getSimpleName());
        } catch (MismatchException e) {
            assertTrue("incorrect message", e.getMessage().contains("null"));
        }
    }

    @Test
    public void validate_green_correctValues() throws Throwable {
        final TestAbstractTypeSafeMatcher matcher = new TestAbstractTypeSafeMatcher(new TestEntity("christoph", 31));
        matcher.validate(new TestEntity("christoph", 31));
    }

    @Test
    public void validate_red_incorrectValues() throws Throwable {
        final TestAbstractTypeSafeMatcher matcher = new TestAbstractTypeSafeMatcher(new TestEntity("christoph", 31));
        try {
            matcher.validate(new TestEntity("christoph", 32));
            fail("expecting " + MismatchException.class.getSimpleName());
        } catch (MismatchException e) {
            assertTrue("incorrect message", e.getMessage().contains("age"));
        }
    }

    @Test
    public void validate_red_internalError() throws Throwable {
        final TestAbstractTypeSafeMatcher matcher = new TestAbstractTypeSafeMatcher(new TestEntity("christoph", 31));
        try {
            matcher.validate(new TestEntity(null, 32));
            fail("expecting " + IllegalStateException.class.getSimpleName());
        } catch (IllegalStateException e) {
            assertTrue("incorrect message", e.getMessage().equals("missing name"));
        }
    }

    private static class TestEntity {
        private final String name;
        private final int age;

        TestEntity(String name, int age) {
            this.name = name;
            this.age = age;
        }

        String getName() throws Throwable {
            if (name == null) {
                throw new IllegalStateException("missing name");
            } else {
                return name;
            }
        }

        int getAge() throws Throwable {
            return age;
        }
    }

    private static final class TestAbstractTypeSafeMatcher extends AbstractTypeSafeMatcher<TestEntity> {

        /**
         * constructor
         *
         * @param expected expected value
         */
        TestAbstractTypeSafeMatcher(TestEntity expected) {
            super(expected);
        }

        @Override
        protected void typeSafeValidate(TestEntity expected, TestEntity actual) throws Throwable {
            assertAreEqual("name", expected.getName(), actual.getName());
            assertAreEqual("age", expected.getAge(), actual.getAge());
        }
    }
}
