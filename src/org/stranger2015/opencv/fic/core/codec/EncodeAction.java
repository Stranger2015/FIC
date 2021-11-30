package org.stranger2015.opencv.fic.core.codec;

import java.util.function.Consumer;

/**
 *
 */
public
class EncodeAction implements Consumer <String> {
    private final String fn;

    /**
     * @param fn
     */
    public
    EncodeAction ( String fn) {
        this.fn = fn;
    }

    /**
     * Performs this operation on the given argument.
     * @param s the input argument
     */
    @Override
    public
    void accept ( String s ) {

    }

    /**
     * @return
     */
    public
    String getFn () {
        return fn;
    }
}
