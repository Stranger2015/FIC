package org.stranger2015.opencv.fic.core;

/**
 *
 */
public abstract
class Task<N extends TreeNode<N>, M extends Image> {

    /**
     *
     * @param image
     */
    public abstract M execute (M image );
}
