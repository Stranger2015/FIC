package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;
import java.util.Set;

/**
 * @param <N>
 
 * @param <A>
 */
public
class CsDpEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
        extends Encoder <N, A, G> {
    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    CsDpEncoder (M inputImage,
                  IntSize rangeSize,
                  IntSize domainSize,
                  Set <ImageTransform <M, A, G>> transforms,
                  ScaleTransform <A, G> scaleTransform,
                  IDistanceator <M, A> comparator,
                  Set <IImageFilter <M, A>> filters,
                  FractalModel <N, A, G> fractalModel
    ) {
        super(
                inputImage,
                rangeSize,
                rangeSize, domainSize,
                transforms,
                scaleTransform,
                comparator,
                filters,
                fractalModel
        );
    }

    /**
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
//    @Override
    protected
    ImageBlockGenerator <N, A, G> createBlockGenerator (
            IEncoder <N, A, G> encoder,
            GrayScaleImage<A> image,
            IntSize rangeSize,
            IntSize domainSize ) {
        return new ImageBlockGenerator <>(
                getTiler(),

                );
    }

    /**
     * @return
     */
    @Override
    public
    GrayScaleImage<A> encode ( GrayScaleImage<A> image ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    GrayScaleImage<A> randomTransform ( GrayScaleImage<A> image, ImageTransform <A, G> transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    GrayScaleImage<A> applyTransform ( GrayScaleImage<A> image, ImageTransform <A, G> transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    GrayScaleImage<A> applyAffineTransform ( GrayScaleImage<A> image, AffineTransform <A, G> transform ) {
        return image;//todo
    }

//    /**
//     * @param image
//     * @param sourceSize
//     * @param destinationSize
//     * @param step
//     * @return
//     */
//    @Override
//    public
//    List <ImageTransform <M, A, G>> compress ( GrayScaleImage<A> image, int sourceSize, int destinationSize, int step ) {
//        return image;//todo
//    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> generateAllTransformedBlocks ( GrayScaleImage<A> image, int sourceSize, int destinationSize, int step ) {
        return image;//todo
    }

    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    ImageBlockGenerator <N, A, G> createBlockGenerator (
            ITiler <M, A> tiler,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            GrayScaleImage <A> image,
            Size rangeSize,
            Size domainSize
    ) {

        return new SquareImageBlockGenerator <>(
                tiler,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize
        );
    }


    /**
     * @param image
     * @param axis
     * @return
     */
    @Override
    public
    GrayScaleImage <A> flipAxis ( GrayScaleImage <A> image, int axis ) {
        return image;
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageBlock <A>> getRangeBlocks () {
        return image;
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageBlock <A>> getDomainBlocks () {
        return image;
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageBlock <A>> getCodebookBlocks () {
        return image;
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <M, A> getTiler () {
        return image;
    }

    /**
     * @return
     */
    @Override
    public
    FractalModel <N, A, G> getModel () {
        return image;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    FractalModel <N, A, G> loadModel ( String filename ) {
        return image;
    }
}
