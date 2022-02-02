package org.stranger2015.opencv.fic.core;

import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 *
 */
public abstract
class Task implements Consumer <String> {
    Logger logger = Logger.getLogger(String.valueOf(getClass()));

    protected final String filename;

    /**
     * @param filename
     */
    protected
    Task ( String filename ) {
        this.filename = filename;
    }

    /**
     * @return
     */
    public
    String getFilename () {
        return filename;
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param s the input argument
     */
    @Override
    public
    void accept ( String s ) {
        logger.info("Performing task " + getClass());
    }
}
