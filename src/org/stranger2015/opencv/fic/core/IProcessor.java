package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
interface IProcessor<N extends TreeNode <N, A, M, G>, A extends Address <A>,  M extends IImage <A>,
        G extends BitBuffer> {

    /**
     * @return
     * @param filename
     */
   M preprocess ( String filename );

    /**
     * @return
     */
   M process( M inputImage) throws ValueError;

    /**
     * @return
     * @param outputImage
     */
   M postprocess ( M outputImage );
}
