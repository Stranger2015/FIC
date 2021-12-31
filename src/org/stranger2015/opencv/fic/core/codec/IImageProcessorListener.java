package org.stranger2015.opencv.fic.core.codec;

/**
 *
 */
public
interface IImageProcessorListener {
    /**
     *
     */
    void onPreprocess();

    /**
     *
     */
    void onProcess();

    /**
     *
     */
    void onPostprocess();

}
