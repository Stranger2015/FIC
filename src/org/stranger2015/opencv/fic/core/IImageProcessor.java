package org.stranger2015.opencv.fic.core;


import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.Codec;

import java.util.List;

/**
 * @param <N>
 * @param <M>
 */
public
interface IImageProcessor<N extends TreeNode <N, A, M>, A extends Address <A>, M extends IImage>
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
    Codec <N, A, M> getCodec ();

    /**
     * @return
     */
    M preprocess ();

    /**
     * @return
     */
    M postprocess ();

    /**
     * @return
     */
    List <Task> getTasks ();
}
