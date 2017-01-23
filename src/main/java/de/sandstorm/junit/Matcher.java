package de.sandstorm.junit;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * function to validate an actual value
 *
 * static members shall be used in implementing classes as helper methods
 */
public interface Matcher {

    /**
     * @param actual actual value to validate
     * @throws MismatchException the validation failed due to an unexpected value
     * @throws Throwable         the validation failed to an internal error
     */
    void validate(Object actual) throws Throwable;

    /**
     * @param propertyName name of the property under validation
     * @param expected     expected value
     * @param actual       actual value
     * @param <TValue>     type of the values
     * @throws MismatchException the validation failed due to an unexpected value
     */
    static <TValue> void assertAreEqual(String propertyName, TValue expected, TValue actual) {
        if (expected != actual && !Objects.equals(expected, actual)) {
            throw new MismatchException(propertyName, expected, actual);
        }
    }

    /**
     * @param propertyName name of the property under validation
     * @param matcher      matcher to validate the actual value
     * @param actual       actual value
     * @throws MismatchException the validation failed due to an unexpected value
     */
    static void assertMatches(String propertyName, Matcher matcher, Object actual) {
        try {
            matcher.validate(actual);
        } catch (Throwable t) {
            throw new MismatchException(propertyName, t);
        }
    }

    /**
     * @param propertyName name of the property under validation
     * @param expected     expected value
     * @param actual       actual value
     * @param validator    validator to compare expected and actual values
     * @param <TValue>     expected type
     * @throws MismatchException the validation failed due to an unexpected value
     */
    static <TValue> void assertMatches(String propertyName, TValue expected, Object actual, Validator<TValue> validator) {
        assertMatches(propertyName, (a) -> validator.validate(expected, a), actual);
    }

    /**
     * @param propertyName  name of the property under validation
     * @param expected      expected value
     * @param actual        actual value
     * @param itemValidator validator to compare items (order does matter)
     * @param <TValue>      expected item type
     */
    static <TValue> void assertEachMatches(String propertyName, Collection<TValue> expected, Collection actual, Validator<TValue> itemValidator) {
        if (expected != actual) {
            if (expected == null || actual == null) {
                throw new MismatchException(propertyName, expected, actual);
            } else {
                if (expected.size() != actual.size()) {
                    throw new MismatchException(String.format("incorrect %s.size()", propertyName), expected.size(), actual.size());
                } else {
                    int i = 0;
                    final Iterator actualIterator = actual.iterator();
                    for (final Iterator<TValue> expectedIterator = expected.iterator(); expectedIterator.hasNext(); i++) {
                        assertMatches(
                            String.format("%s.%d", propertyName, i),
                            expectedIterator.next(),
                            actualIterator.next(),
                            itemValidator
                        );
                    }
                }
            }
        }
    }

    /**
     * @param propertyName   name of the property under validation
     * @param expected       expected value
     * @param actual         actual value
     * @param valueValidator validator to compare values under same keys
     * @param <TKey>         type of the map keys
     * @param <TValue>       expected value type
     */
    static <TKey, TValue> void assertEachMatches(String propertyName, Map<TKey, TValue> expected, Map<TKey, TValue> actual, Validator<TValue> valueValidator) {
        if (expected != actual) {
            if (expected == null || actual == null) {
                throw new MismatchException(propertyName, expected, actual);
            } else {
                if (!expected.keySet().containsAll(actual.keySet()) || !actual.keySet().containsAll(expected.keySet())) {
                    throw new MismatchException(String.format("%s.keySet()", propertyName), expected.keySet(), actual.keySet());
                } else {
                    for (final TKey key : expected.keySet()) {
                        assertMatches(
                            String.format("%s.%s", propertyName, key),
                            expected.get(key),
                            actual.get(key),
                            valueValidator
                        );
                    }
                }
            }
        }
    }

    /**
     * one method interface for lambda implementations
     *
     * @param <TValue> expected type
     */
    interface Validator<TValue> {
        /**
         * @param expected expected value
         * @param actual   actual value to validate
         * @throws MismatchException the validation failed due to an unexpected value
         * @throws Throwable         the validation failed to an internal error
         */
        void validate(TValue expected, Object actual) throws Throwable;
    }
}
