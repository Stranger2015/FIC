package org.stranger2015.opencv.fic.utils;

import static java.lang.String.format;

/**
 * A utility for making programming assertions.
 *
 * @version 1.7
 */
public
class Assert {

    /**
     * Throws an <code>AssertionFailedException</code> if the given assertion is
     * not true.
     *
     * @param assertion a condition that is supposed to be true
     * @throws AssertionFailedException if the condition is false
     */
    public static
    void isTrue ( boolean assertion ) {
        isTrue(assertion, null);
    }

    /**
     * Throws an <code>AssertionFailedException</code> with the given message if
     * the given assertion is not true.
     *
     * @param assertion a condition that is supposed to be true
     * @param message   a description of the assertion
     * @throws AssertionFailedException if the condition is false
     */
    public static
    void isTrue ( boolean assertion, String message ) {
        if (!assertion) {
            if (message == null) {
                throw new AssertionFailedException();
            }
            else {
                throw new AssertionFailedException(message);
            }
        }
    }

    /**
     * Throws an <code>AssertionFailedException</code> if the given objects are
     * not equal, according to the <code>equals</code> method.
     *
     * @param expectedValue the correct value
     * @param actualValue   the value being checked
     * @throws AssertionFailedException if the two objects are not equal
     */
    public static
    void equals ( Object expectedValue, Object actualValue ) {
        equals(expectedValue, actualValue, null);
    }

    /**
     * Throws an <code>AssertionFailedException</code> with the given message if
     * the given objects are not equal, according to the <code>equals</code>
     * method.
     *
     * @param expectedValue the correct value
     * @param actualValue   the value being checked
     * @param message       a description of the assertion
     * @throws AssertionFailedException if the two objects are not equal
     */
    public static
    void equals ( Object expectedValue, Object actualValue, String message ) {
        if (!actualValue.equals(expectedValue)) {
            throw new AssertionFailedException(format(
                    "Expected %s but encountered %s%s",
                    expectedValue,
                    actualValue,
                    message != null ? ": " + message : ""));
        }
    }

    /**
     * Always throws an <code>AssertionFailedException</code>.
     *
     * @throws AssertionFailedException thrown always
     */
    public static
    void shouldNeverReachHere () {
        shouldNeverReachHere(null);
    }

    /**
     * Always throws an <code>AssertionFailedException</code> with the given
     * message.
     *
     * @param message a description of the assertion
     * @throws AssertionFailedException thrown always
     */
    public static
    void shouldNeverReachHere ( String message ) {
        throw new AssertionFailedException(format("Should never reach here%s", message != null ? ": " + message : ""));
    }
}

