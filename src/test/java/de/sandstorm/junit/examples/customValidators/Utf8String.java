package de.sandstorm.junit.examples.customValidators;

import java.io.UnsupportedEncodingException;

/**
 * test entity containing a property which cannot be validated with {@link java.util.Objects#equals(Object, Object)}
 */
public class Utf8String {

    private static final String Encoding = "UTF-8";

    private final String value;

    /**
     * constructor
     *
     * @param value string value
     */
    public Utf8String(String value) {
        this.value = value;
    }

    /**
     * @return exact clone of this instance
     */
    public Utf8String duplicate() {
        return new Utf8String(value);
    }

    public byte[] getBytes() throws UnsupportedEncodingException {
        return value.getBytes(Encoding);
    }
}
