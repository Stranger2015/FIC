package org.stranger2015.opencv.fic.core.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.*;

/**
 *
 */
public
interface IImageProcessorListener {
    Logger logger = LoggerFactory.getLogger(IImageProcessorListener.class);

    /**
     *
     */
    default
    void onPreprocess ( IImageProcessor imageProcessor, String filename, IImage image )
            throws ValueError {

        logger.info("On preprocessing \n");
    }

    /**
     *
     */
    default
    void onProcess ( IImageProcessor imageProcessor, IImage inputImage ) {
        logger.info("On processing \n");
    }

    /**
     *
     */
    default
    void onPostprocess ( IImageProcessor processor, ICompressedImage outputImage ) {
        logger.info("On postprocessing \n");
    }
}
