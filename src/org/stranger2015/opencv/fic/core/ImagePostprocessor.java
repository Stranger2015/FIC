package org.stranger2015.opencv.fic.core;

public
class ImagePostprocessor<N extends TreeNode<N>, M extends Image, C extends CompressedImage> extends ImageProcessor<N, M, C> {
    /**
     * @param image
     * @param scheme
     */
    public
    ImagePostprocessor ( M image, EPartitionScheme scheme ) {
        super(image, scheme);
    }

    /**
     * @param inImage
     */
    @Override
    public
    M process ( M inImage ) {
        return super.process(inImage);
    }
}
