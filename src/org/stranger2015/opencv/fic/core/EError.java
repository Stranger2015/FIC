package org.stranger2015.opencv.fic.core;

import static java.lang.String.format;

/**
 * Representation of possible errors coupled with error codes
 */
public
enum EError {
    ARG_COUNT("Unexpected argument count: %d"),
    FILE_READ("Couldn't read file: %s"),
    INVALID_VALUE("Invalid value for option: %s %s"),
    MISSING_ARG("Missing argument for option: %s"),
    REQUIRED_ARG_NOT_FOUND("Required argument missing: %s"),
    STREAM_WRITE("Couldn't write to stream"),
    UNKNOWN_ARG("Unknown argument: %s"),
    ;

    private final String description;

    EError ( String description ) {
        this.description = format("==> ERROR: %s", description);
    }

    /**
     * @return a description of the error
     */
    public
    String description () {
        return this.description;
    }

    /**
     * @param msg extra message to attach
     * @return a description of the error
     */
    public
    String description ( String msg ) {
        return format(this.description, msg);
    }

    /**
     * @param opt specify the option
     * @param val value of the option
     * @return a description of the error
     */
    public
    String description ( String opt, String val ) {
        return format(this.description, opt, val);
    }

    /**
     * @param num a number to attach
     * @return a description of the error
     */
    public
    String description ( int num ) {
        return format(this.description, num);
    }

    /**
     * The error code representing the error.
     * This will change along with the ordering of the errors.
     *
     * @return the error's code
     */
    public
    int errcode () {
        return this.ordinal() + 1;
    }
}
