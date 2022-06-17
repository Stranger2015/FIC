package org.stranger2015.opencv.fic.utils;

/**
 *  Thrown when the application is in an inconsistent state. Indicates a problem
 *  with the code.
 *
 *@version 1.7
 */
public class AssertionFailedException extends RuntimeException {

    /**
     *  Creates an <code>AssertionFailedException</code>.
     */
    public AssertionFailedException() {
        super();
    }

    /**
     *  Creates a <code>AssertionFailedException</code> with the given detail
     *  message.
     *
     *@param  message  a description of the assertion
     */
    public AssertionFailedException(String message) {
        super(message);
    }
}

