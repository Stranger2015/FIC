package org.stranger2015.opencv.fic.core;

/**
 * Preprocess the input image.
 * 1. make the input image square-shaped and scale it up to the nearest greater power-of-two
 *
 * @param <N>
 * @param <M>
 */
public
class InputImageTask<N extends TreeNode<N>, M extends Image> extends Task<N,M>{

    public
    InputImageTask () {

    }

    /**
     * @param preprocessedImage
     */
    @Override
    public
    M execute ( M preprocessedImage ) {
        return null;
    }
}
