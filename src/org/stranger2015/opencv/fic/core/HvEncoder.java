package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
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
class HvEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
        extends Encoder <N, A, G> {

    protected
    HvEncoder (
            EPartitionScheme scheme,
            ITreeNodeBuilder <N, A, G> nodeBuilder,
            ISearchProcessor <N, A, G> searchProcessor,
            ScaleTransform <A, G> scaleTransform,
            ImageBlockGenerator <N, A, G> imageBlockGenerator,
            IDistanceator <A> comparator,
            Set <ImageTransform <A, G>> transforms,
            Set <IImageFilter <M, A>> filters,
            FractalModel <N, A, G> fractalModel
    ) {
        super(
                scheme,
                nodeBuilder ,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                transforms,
                filters,
                fractalModel);
    }

    /**
     * @param tiler
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    ImageBlockGenerator <N, A, G> createBlockGenerator (
            ITiler <N, A, G> tiler,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize ) {

        return new HvBlockGenerator <>(
                tiler,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize);
    }

    /**
     * @param image
     * @param axis
     * @return
     */
    @Override
    public
    IImage <A> flipAxis ( IImage <A> image, int axis ) {
        return null;
    }

    /**
     * @param image
     * @param bounds
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <RegionOfInterest <A>> segmentImage ( IImage <A> image, List <Rectangle> bounds ) throws ValueError {
        return null;
    }

    @Override
    public
    ITiler <N, A, G> getTiler () {
        return new HvTiler <N, A, G>(image, image.getWidth(), image.getHeight());
    }

    /**
     * @return
     */
    @Override
    public
    FractalModel <N, A, G> getModel () {
        return null;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    FractalModel <N, A, G> loadModel ( String filename ) {
        return null;
    }

    /**
     * @param node
     */
    @Override
    public
    void add ( TreeNode <N, A, G> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode.LeafNode <N, A, G> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

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
//    @Override
//    public
//    ImageBlockGenerator <N, A, G> createBlockGenerator ( ITiler <M, A> tiler,
//                                                            EPartitionScheme scheme,
//                                                            IEncoder <N, A, G> encoder,
//                                                            GrayScaleImage <A> image,
//                                                            IntSize rangeSize,
//                                                            IntSize domainSize ) {
//        return new ;
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    FractalModel <N, A, G> getModel () {
//        return null;
//    }

//    /**
//     * @param filename
//     * @return
//     */
//    @Override
//    public
//    FractalModel <N, A, G> loadModel ( String filename ) {
//        return null;
//    }

//    /**
//     * @param image
//     * @return
//     */
//    @Override
//    public
//    void segmentImage ( IImage<A> image ) {
//        blockGenerator.generateRangeBlocks(image);
//        blockGenerator.generateDomainBlocks(image);
//        blockGenerator.createCodebookBlocks(image, domainBlocks);
//    }

//    /**
//     * @param encoder
//     * @param image
//     * @param rangeSize
//     * @param domainSize
//     * @return
//     */
//    @Override
//    protected
//    ImageBlockGenerator <N, A, G> createBlockGenerator ( IEncoder <N, A, G> encoder,
//                                                            IImage<A> image,
//                                                            IntSize rangeSize,
//                                                            IntSize domainSize ) {
//        return new HvBlockGenerator <>(new HvTiler <>(image.rows(), image.cols()), encoder, image, rangeSize, domainSize);
//    }


    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    GrayScaleImage <A> randomTransform ( GrayScaleImage <A> image, ImageTransform <A, G> transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    GrayScaleImage <A> applyTransform ( GrayScaleImage <A> image, ImageTransform <A, G> transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    GrayScaleImage <A> applyAffineTransform ( GrayScaleImage<A> image, AffineTransform <A, G> transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
//    @Override
    public
    List <ImageTransform <M, A, G>> compress ( IImage<A> image, int sourceSize, int destinationSize, int step ) {
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
    List <IImageBlock <A>> generateAllTransformedBlocks ( GrayScaleImage <A> image, int sourceSize, int destinationSize, int step ) {
        return null;//todo
    }

    /**
     * @return
     */
    @Override
    public
    IPipeline <IImage <A>, IImage <A>> getLinkedObject () {
        return null;
    }

    /**
     * @param link
     */
    @Override
    public
    void setNext ( ISingleLinked <IPipeline <IImage <A>, IImage <A>>> link ) {

    }

    @Override
    public
    ISingleLinked <IPipeline <IImage <A>, IImage <A>>> getNext () {
        return null;
    }

    @Override
    public
    IImage <A> getInput () {
        return null;
    }

    @Override
    public
    IImage <A> getOutput () {
        return null;
    }

//    /**
//     *
//     */
//    @Override
//    public
//    void onPreprocess () {
//
//    }
//
//    /**
//     *
//     */
//    @Override
//    public
//    void onProcess () {
//
//    }
//
//    /**
//     *
//     */
//    @Override
//    public
//    void onPostprocess () {
//
//    }
//
//    /**
//     *
//     */
//    @Override
//    public
//    void onCompress () {
//
//    }
}