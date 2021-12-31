package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.SaAddress;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 *
 * A Spiral Architecture Based Variable Range Fractal Image Compression Method
 *
 * @param <N>
 * @param <M>
 * @param <A>
 */
public
class SabVsrEncoder<N extends TreeNode <N, A, M>, A extends SaAddress <A>, M extends Image>
        extends VsaEncoder <N, A, M> {
    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SabVsrEncoder ( M inputImage, Size rangeSize, Size domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }
}
