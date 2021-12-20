package org.stranger2015.opencv.fic.core;

import java.util.function.Function;

/**
 *
 */
public abstract
class Task<M extends Image> implements Function <String, M> {

    private final M image;

    /**
     * @param image
     */
    public
    Task (M image) {
        this.image = image;
    }

    /**
     */
    public
    M execute () {
//        return apply(image);
        return image;
    }

    public
    M getImage () {
        return image;
    }
}
