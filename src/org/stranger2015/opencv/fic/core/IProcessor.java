package org.stranger2015.opencv.fic.core;

/**
 *
 */
public
interface IProcessor<M extends Image> {
    /**
     * @return
     */
   M preprocess();

    /**
     * @return
     */
   M process();

    /**
     * @return
     */
   M postprocess();
}
