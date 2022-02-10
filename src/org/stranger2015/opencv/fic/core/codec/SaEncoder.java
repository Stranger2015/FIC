package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
class SaEncoder<N extends TreeNode <N, A, M>, A extends Address <A>, M extends IImage>
        extends Encoder <N, A, M> {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SaEncoder ( M inputImage, Size rangeSize, Size domainSize ) {
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

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    @Override
    public
    List <ImageTransform <M>> compress ( M image,
                                         int sourceSize,
                                         int destinationSize,
                                         int step ) {
        return null;
    }

    @Override
    protected
    SaImageBlockGenerator createBlockGenerator ( IEncoder <N, A, M> encoder,
                                                 IImage image,
                                                 Size rangeSize,
                                                 Size domainSize ) {

        return new SaImageBlockGenerator(encoder, image, rangeSize, domainSize);
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
    List <ImageBlock> generateAllTransformedBlocks ( M image,
                                                     int sourceSize,
                                                     int destinationSize,
                                                     int step ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageTransform <M>> getTransforms () {
        return transforms;
    }

    /**
     * @param image
     * @param transform
     * @return N4
     */
    @Override
    public
    M randomTransform ( M image, ImageTransform <M> transform ) {
        return null;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M applyTransform ( M image, ImageTransform <M> transform ) {
        return null;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <M> transform ) {
        return null;
    }

    /**
     *
     */
    @Override
    public
    void onPreprocess () {

    }

    /**
     *
     */
    @Override
    public
    void onProcess () {

    }

    /**
     *
     */
    @Override
    public
    void onPostprocess () {

    }
}
