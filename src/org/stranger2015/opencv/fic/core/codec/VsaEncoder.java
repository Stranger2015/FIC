package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.EnumSet;
import java.util.List;

/**
 *
 */
public
class VsaEncoder<N extends VsaTreeNode <N,A>, M extends Image, C extends CompressedImage, A extends SaAddress <A>>
        extends Encoder <N, M, C, A> {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    VsaEncoder ( M inputImage, Size rangeSize, Size domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    M encode ( M image ) {
        return super.encode(image);
    }

//****************** encoder math ********************

//    /**
//     * @param address1
//     * @param address2
//     * @return
//     */
//    @Override
//    public
//    A plus ( A address1, A address2 ) {
//        int[][] table = this.getAddTable();
//        EDigits digits1 = address1.getDigits();
//        EDigits digits2 = address2.getDigits();
//        for (int i = 0, max = base() - 1; i < max; i++) {
//            digits1.
//        }
//        return null;
//    }
//
//    /**
//     * @param address1
//     * @param address2
//     * @return
//     */
//    @Override
//    public
//    A minus ( A address1, A address2 ) {
//        return null;
//    }

//    /**
//     * @param address1
//     * @param address2
//     * @return
//     */
//    @Override
//    public
//    A mult ( A address1, A address2 ) {
//
//        A result = null;
//        int[][] table = this.getMultTable();
//            for (int i = 0, max= a1.base()-1;i < max; i++) {
//                a1.or();
//            }
//            result = table[][]
//        return result;
//    }

//    /**
//     * @return
//     */
//    public
//    int[][] getAddTable () {
//        return addTable.clone();
//    }
//
//    /**
//     * @return
//     */
//    public
//    int[][] getMultTable () {
//        return multTable.clone();
//    }
//
//    @Override
//    public
//    EnumSet <EDigits> getDigits () {
//        this.getDigits()
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    int base () {
//        return 7;
//    }
//**********************************************************

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
        return null;
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
    List <ImageBlock <M>> generateAllTransformedBlocks ( M image,
                                                         int sourceSize,
                                                         int destinationSize,
                                                         int step ) {
        return null;
    }

    /**
     * @param image
     * @param transform
     * @return N4
     */
    @Override
    public
    M randomTransform ( M image, ImageTransform <M, C> transform ) {
        return null;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M applyTransform ( M image, ImageTransform <M, C> transform ) {
        return null;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <M, C> transform ) {
        return null;
    }
}
