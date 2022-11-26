package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.SaTiler;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;
import java.util.Set;

/**
 *
 */
public
class SaEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Encoder <N, A, G> {

//    private List <IImageBlock <A>> rangePool;

    public
    SaEncoder ( EPartitionScheme scheme,
                ITreeNodeBuilder <N, A, G> nodeBuilder,
                IPartitionProcessor<N,A,G> partitionProcessor,
                ISearchProcessor <N, A, G> searchProcessor,
                ScaleTransform <A, G> scaleTransform,
                ImageBlockGenerator <N, A, G> imageBlockGenerator,
                IDistanceator <A> comparator,
                Set <ImageTransform < A, G>> transforms,
                Set <IImageFilter <A>> filters,
                FCImageModel <N, A, G> fractalModel
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

    @Override
    public
    IPartitionProcessor <N, A, G> createPartitionProcessor0 ( ITiler <N, A, G> tiler ) {
        return null;
    }

//    public
//    SaEncoder ( IImage<A> image, IIntSize rangeSize, IIntSize domainSize ) {
//        super(image,rangeSize,domainSize);
//
//    }

    /**
     * @param image
     * @return
     */
//    @Override
    public
    IImage<A> encode ( IImage<A>image ) throws ValueError {
        return super.encode(image);
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
    List <ImageTransform <A, G>> compress ( IImageBlock<A> image,
                                               int sourceSize,
                                               int destinationSize,
                                               int step ) {
        return null;
    }

    public
    ImageBlockGenerator <N, A, G> createBlockGenerator (
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IntSize rangeSize,
            IntSize domainSize
    ) {

        return new SaImageBlockGenerator <>(
                partitionProcessor,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize);
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
    List <IImageBlock <A>> generateAllTransformedBlocks(IImageBlock<A> image,
                                                         int sourceSize,
                                                         int destinationSize,
                                                         int step ) {
        return List.of(image); //fixme
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

    /**
     * @return
     */
//    @Override
//    public
//    List <IImageBlock <A>> getRangeBlocks () {
//        imageBlockGenerator.generateRangeBlocks(roi, rangeSize, domainSize);
//    }

    @Override
    public
    void initialize () throws ReflectiveOperationException, Exception {
        
    }

    @Override
    public
    IImage <A> doEncode ( IImage <A> image ) {
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
    List <IImageBlock <A>> segmentImage ( IImage <A> image, List <Rectangle> bounds ) throws ValueError {
        return null;
    }

    /**
     * @return
     */
//    @Override
    public
    ITiler <N, A, G> createPartition0 () {
        return new SaTiler<>();
    }

    /**
     * @return
     */
    @Override
    public
    IPartitionProcessor <N, A, G> getPartitionProcessor () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    FCImageModel <N, A, G> getModel () {
        return null;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    FCImageModel <N, A, G> loadModel ( String filename ) {
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

    @Override
    public
    void addLeafNode ( TreeNodeBase <N, A, G> node ) {

    }

    /**
     * @param node
     */
//    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    /**
     * @param image
     * @param transform
     * @return N4
     */
//    @Override
    public
    IImage<A> randomTransform ( IImage<A> image, ImageTransform <A, G> transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
//    @Override
    public
    IImage<A> applyTransform ( IImage<A> image, ImageTransform <A, G> transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
//    @Override
    public
    IImage<A> applyAffineTransform ( IImage<A> image, AffineTransform <A, G> transform ) {
        return null;
    }

    @Override
    public
    IPartitionProcessor <N, A, G> doCreatePartitionProcessor ( ITiler <N, A, G> tiler ) {
        return null;
    }

    /**
     *
     */
//    @Override
    public
    void onPreprocess () {

    }

    /**
     *
     */
//    @Override
    public
    void onProcess () {

    }

    /**
     *
     */
//    @Override
    public
    void onPostprocess () {

    }

    /**
     * @return
     */
//    @Override
//    public
//    IPipeline <IImage <A>, IImage <A>> getLinkedObject () {
//        return null;
//    }
//
//    /**
//     * @param link
//     */
//    @Override
//    public
//    void setNext ( ISingleLinked <IPipeline <IImage <A>, IImage <A>>> link ) {
//
//    }

//    @Override
//    public
//    ISingleLinked <IPipeline <IImage <A>, IImage <A>>> getNext () {
//        return null;
//    }
//
//    @Override
//    public
//    IImage <A> getInput () {
//        return null;
//    }
//
//    @Override
//    public
//    IImage <A> getOutput () {
//        return null;
//    }
}
