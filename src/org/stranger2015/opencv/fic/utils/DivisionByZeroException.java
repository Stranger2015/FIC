package org.stranger2015.opencv.fic.utils;

import org.stranger2015.opencv.fic.core.ValueError;

public
class DivisionByZeroException extends ValueError {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public
    DivisionByZeroException ( String message ) {
        super(message);
    }
}
