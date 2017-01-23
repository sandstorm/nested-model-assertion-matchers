package de.sandstorm.junit;

/**
 * base class for {@link Matcher}s already doing the basic work
 */
public abstract class AbstractTypeSafeMatcher<TEntity> implements Matcher {

    private final TEntity expected;

    /**
     * constructor
     *
     * @param expected expected value
     */
    public AbstractTypeSafeMatcher(TEntity expected) {
        this.expected = expected;
    }

    @Override
    public void validate(Object actual) throws Throwable {
        if (expected != actual) {
            if (expected == null || actual == null) {
                throw new MismatchException("incorrect reference", expected, actual);
            } else {
                if (expected.getClass().isAssignableFrom(actual.getClass())) {
                    typeSafeValidate(expected, (TEntity) actual);
                } else {
                    throw new MismatchException("incorrect type", expected.getClass(), actual.getClass());
                }
            }
        }
    }

    /**
     * @param expected expected value (never null)
     * @param actual   actual value (never null)
     * @throws MismatchException the validation failed due to an unexpected value
     * @throws Throwable         the validation failed to an internal error
     */
    protected abstract void typeSafeValidate(TEntity expected, TEntity actual) throws Throwable;
}
