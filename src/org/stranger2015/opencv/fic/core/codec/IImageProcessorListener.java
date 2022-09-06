package org.stranger2015.opencv.fic.core.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
interface IImageProcessorListener<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {
    Logger logger = LoggerFactory.getLogger(IImageProcessorListener.class);

    /**
     *
     */
    default
    void onPreprocess ( IImageProcessor <N, A, G> imageProcessor, String filename, IImage <A> image )
            throws ValueError {

        logger.info("On preprocessing \n");
    }

    /**
     *
     */
    default
    void onProcess ( IImageProcessor <N, A, G> imageProcessor, IImage <A> inputImage ) {
        logger.info("On processing \n");
    }

    /**
     *
     */
    default
    void onPostprocess ( IImageProcessor <N, A, G> imageProcessor, CompressedImage <A> outputImage ) {
        logger.info("On postprocessing \n");
    }
}
