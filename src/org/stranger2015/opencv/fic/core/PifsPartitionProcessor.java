package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <M>
 * @param <A>
 */
public
class PifsPartitionProcessor<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends ImagePartitionProcessor <N, A, M> {

    /**
     * @param image
     * @param scheme
     */
    public
    PifsPartitionProcessor ( M image, EPartitionScheme scheme ) {
        super(image, scheme);
    }
}
