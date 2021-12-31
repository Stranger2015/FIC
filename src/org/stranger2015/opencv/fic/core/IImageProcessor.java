package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.Codec;

import java.util.List;

/**
 * @param <N>
 * @param <M>
 */
public
interface IImageProcessor<N extends TreeNodeBase <N, A>, M extends Image, A extends Address<A,?>>
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
    Codec <N, M, A> getCodec ();

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
