package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;

/**
 *
 */
public
class ImageContentSegmentationProcessor<M extends Mat> extends ImagePartitionProcessor<TreeNode, M> {
    public
    ImageContentSegmentationProcessor (M image) {
        super(image, null);
    }
}
