package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.Task;

import java.util.function.Consumer;

/**
 *
 */
public
class EncodeAction extends Task<Image> implements Consumer <String> {
    private final String fn;

    /**
     * @param fn
     */
    public
    EncodeAction ( String fn) {
        super(image);
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

    /**
     * Applies this function to the given argument.
     *
     * @param s the function argument
     * @return the function result
     */
    @Override
    public
    Image apply ( String s ) {
        return null;
    }
}
