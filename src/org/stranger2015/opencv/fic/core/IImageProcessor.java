package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.Codec;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.fic.core.codec.ICompressor;
import org.stranger2015.opencv.fic.core.codec.IDecompressor;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <M>
 */
public
interface IImageProcessor<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>,
        G extends BitBuffer>

        extends IProcessor <N, A, M, G> {
    /**
     * @param inputImage
     */
    M process ( M inputImage ) throws ValueError;

    /**
     * @return
     */
    M getImage ();

    /**
     * @return
     */
    EPartitionScheme getScheme ();

    /**
     * @return
     */
    ICodec <N, A, M, G> getCodec ();

    /**
     * @param filename
     * @return
     */
    M preprocess ( String filename );

    /**
     * @param image
     * @return
     */
    M postprocess ( M image );
}
