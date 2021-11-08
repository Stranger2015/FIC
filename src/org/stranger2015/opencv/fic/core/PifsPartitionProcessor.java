package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;

public
class PifsPartitionProcessor<N extends TreeNode<N>, M extends Mat> extends ImagePartitionProcessor <N, M> {

    public
    PifsPartitionProcessor ( M image, PartitionScheme scheme ) {
        super(image, scheme);
    }
}
