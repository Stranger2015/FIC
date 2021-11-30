package org.stranger2015.opencv.fic.core;

/**
 *
 */
public
class ImageContentSegmentationProcessor<N extends TreeNode<N>, M extends Image> extends ImagePartitionProcessor<N, M> {
    public
    ImageContentSegmentationProcessor (M image) {
        super(image, null);
    }
}
