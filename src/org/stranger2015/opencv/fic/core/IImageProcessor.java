package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.Codec;

import java.util.List;

/**
 * @param <N>
 * @param <M>
 * @param <C>
 */
public
interface IImageProcessor<N extends TreeNode <N>, M extends Image, C extends CompressedImage>
        extends IProcessor <M> {
    /**
     *
     */
    M process ();

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
    Codec <N, M, C> getCodec ();

    /**
     * @return
     */
    M preprocess ( );

    /**
     * @return
     */
    M postprocess ();

    /**
     * @return
     */
    List <Task <M>> getTasks ();
}
