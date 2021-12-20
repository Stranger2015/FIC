package org.stranger2015.opencv.fic.core;

/**
 * Preprocess the input image.
 * 1. make the input image square-shaped and scale it up to the nearest greater power-of-two
 *
 * @param <M>
 */
//@SuppressWarnings("")
    @Deprecated
public
class InputImageTask<M extends Image> extends Task<M>{

    /**
     * @param image
     */
    public
    InputImageTask ( M image ) {
        super(image);
    }

    /**
     *
     *
     * @return
     */
    @Override
    public
    M execute () {
        return null;//todo
    }

    /**
     * Applies this function to the given argument.
     *
     * @param s the function argument
     * @return the function result
     */
    @Override
    public
    M apply ( String s ) {
        return null;//todo
    }
}
