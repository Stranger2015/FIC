package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.IConverter;

import java.util.Collections;

/**
 *       y = ( y0 * a + y1 * ( 255 - a ) ) / 255
 *
 *       u = ( u0 * a + u1 * ( 255 - a ) ) / 255
 *
 *       v  = ( v0 * a + v1 * ( 255 - a ) ) / 255
 *
 * @param <N>
 * @param <M>
 * @param <C>
 */
public
class ImagePreprocessor<N extends TreeNode<N>, M extends Image, C extends CompressedImage,
                        I extends EPartitionScheme, O extends EPartitionScheme>
        extends ImageProcessor<N, M, C> implements IConverter<I, O> {

    /**
     * @param image
     * @param scheme
     */
    public
    ImagePreprocessor ( M image, EPartitionScheme scheme ) {
        super(image, scheme, Collections.emptyList(),"");//fixme;
    }

    /**
     * @param inImage
     */
    @Override
    public
    M process ( M inImage ) {
        return inImage;
    }

    /**
     * @param input
     * @return
     */
    @Override
    public
    O map ( I input ) {
        return null;
    }

    /**
     * @param output
     * @return
     */
    @Override
    public
    I unmap ( O output ) {
        return null;
    }
}
