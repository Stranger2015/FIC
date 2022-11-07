package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;
import java.util.Set;

/**
 * @param <N>
 * @param <A>
 */
public
class HvEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Encoder <N, A, G> {

    protected
    HvEncoder (
            EPartitionScheme scheme,
            ITreeNodeBuilder <N, A, G> nodeBuilder,
            IPartitionProcessor <N, A, G> partitionProcessor,
            ISearchProcessor <N, A, G> searchProcessor,
            ScaleTransform <A, G> scaleTransform,
            ImageBlockGenerator <N, A, G> imageBlockGenerator,
            IDistanceator <A> comparator,
            Set <ImageTransform <A, G>> transforms,
            Set <IImageFilter <A>> filters,
            FicFileModel <N, A, G> fractalModel
    ) {
        super(
                scheme,
                nodeBuilder,
                partitionProcessor,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                transforms,
                filters,
                fractalModel
        );
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
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize ) {

        return new HvBlockGenerator <>(
                partitionProcessor,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize);
    }

    @Override
    public
    IPartitionProcessor <N, A, G> doCreatePartitionProcessor ( ITiler <N, A, G> tiler ) {
        return null;
    }

    @Override
    public
    IPartitionProcessor <N, A, G> createPartitionProcessor0 ( ITiler <N, A, G> tiler ) {
        return null;
    }

    /**
     * @param image
     * @param axis
     * @return
     */
    @Override
    public
    IImage <A> flipAxis ( IImage <A> image, int axis ) {
        return image;
    }

    @Override
    public
    void initialize () throws ReflectiveOperationException, Exception {

    }

    @Override
    public
    IImage <A> doEncode ( IImage <A> image ) {
        return image;
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
        return List.of(new RegionOfInterest <>(image));
    }
//
//    @Override
//    public
//    IPartitionProcessor <N, A, G> getPartitionProcessor () {
//        return new HvPartitionProcessor <>(image, image.getWidth(), image.getHeight());
//    }

/
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
    void addLeafNode ( LeafNode <N, A, G> node ) {

    }

    @Override
    public
    void addLeafNode ( TreeNodeBase <N, A, G> node ) {

    }

    @Override
    public
    Class <?> getTilerClass () {
        return tilerClass;
    }

    /**
     * @param node
     */
//    @Override
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
//    FicFileModel <N, A, G> getModel () {
//        return null;
//    }

//    /**
//     * @param filename
//     * @return
//     */
//    @Override
//    public
//    FicFileModel <N, A, G> loadModel ( String filename ) {
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
    IImage <A> randomTransform ( IImage <A> image, ImageTransform <A, G> transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> applyTransform ( IImage <A> image, ImageTransform <A, G> transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> applyAffineTransform ( IImage <A> image, AffineTransform <A, G> transform ) {
        return image;
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
    List <ImageTransform <A, G>> compress ( IImage <A> image, int sourceSize, int destinationSize, int step ) {
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
    List <IImageBlock <A>> generateAllTransformedBlocks ( IImage <A> image, int sourceSize, int destinationSize, int step ) {
        return null;//todo
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