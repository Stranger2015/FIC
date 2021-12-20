package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 * @param <N>
 * @param <M>
 * @param <C>
 */
public
class QuadTreeEncoder<N extends TreeNode <N, A>, M extends Image, C extends CompressedImage, A extends Address<A>>
        extends Encoder <N, M, C, A> {
    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    protected
    QuadTreeEncoder ( M inputImage, Size rangeSize, Size domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }
//
//    /**
//     * @param address
//     * @return
//     */
//    @Override
//    public
//    <A extends IAddress <A>> A plus ( A address ) {
//        return (A) SaUtils.plus(this, address);
//    }
//
//    /**
//     * @param address
//     * @return
//     */
//    @Override
//    public
//    <A extends IAddress <A>> A mult ( A address ) {
//        return null;
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    int[][] getAddTable () {
//        return new int[0][];
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    int[][] getMultTable () {
//        return new int[0][];
//    }

    /**
     * @param blockGenerator
     */
    public
    QuadTreeEncoder ( ImageBlockGenerator blockGenerator ) {
        super(blockGenerator);
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M randomTransform ( M image, ImageTransform <M, C> transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M applyTransform ( M image, ImageTransform <M, C> transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <M, C> transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    @Override
    public
    List <ImageTransform <M, C>> compress ( M image, int sourceSize, int destinationSize, int step ) {
        return null;//todo
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    @Override
    public
    List <ImageBlock <M>> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
        return null;//todo
    }
}
