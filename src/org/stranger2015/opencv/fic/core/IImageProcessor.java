package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.Codec;

import java.util.List;

/**
 * @param <N>
 * @param <M>
 * @param <C>
 */
public
interface IImageProcessor<N extends TreeNode <N>, M extends Image, C extends CompressedImage> {

    /**
     *
     */
    M process ( M inImage );

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
    Codec <M, C> getCodec ();

    /**
     * @return
     */
    IImageProcessor <N, M, C> getPreprocessor ();

    /**
     * @return
     */
    IImageProcessor <N, M, C> getPostprocessor ();

    /**
     * @return
     */
    List <Task<N,M>> getTasks ();
}
