package org.stranger2015.opencv.fic.core;

/**
 * @param <N>
 * @param <M>
 * @param <A>
 */
public
class PifsPartitionProcessor<N extends TreeNodeBase <N, A>, M extends Image, C extends CompressedImage, A extends Address <A>>
        extends ImagePartitionProcessor <N, M, C, A> {

    /**
     * @param image
     * @param scheme
     */
    public
    PifsPartitionProcessor ( M image, EPartitionScheme scheme ) {
        super(image, scheme);
    }
}
