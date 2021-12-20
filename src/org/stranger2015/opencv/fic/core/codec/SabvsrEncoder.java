package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.TreeNode;

/**
 * @param <N>
 * @param <M>
 * @param <C>
 */
public
class SabvsrEncoder<N extends TreeNode <N>, M extends Image, C extends CompressedImage> extends VsaEncoder<N,M,C> {
    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SabvsrEncoder ( M inputImage, Size rangeSize, Size domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }
}
