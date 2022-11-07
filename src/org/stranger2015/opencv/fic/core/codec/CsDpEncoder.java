package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.geom.Point;
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
class CsDpEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Encoder <N, A, G> {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    CsDpEncoder ( EPartitionScheme scheme,
                  ITreeNodeBuilder <N, A, G> nodeBuilder,
                  IPartitionProcessor <N, A, G> partitionProcessor,
                  ISearchProcessor <N, A, G> searchProcessor,
                  ScaleTransform <A, G> scaleTransform,
                  ImageBlockGenerator <N, A, G> imageBlockGenerator,
                  IDistanceator <A> comparator,
                  Set <ImageTransform <A, G>> imageTransforms,
                  Set <IImageFilter <A>> imageFilters,
                  FicFileModel <N, A, G> fractalModel ) {
        super(
                scheme,
                nodeBuilder,
                partitionProcessor,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                imageTransforms,
                imageFilters,
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
    public
    ImageBlockGenerator <N, A, G> createBlockGenerator (
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize ) {

        return imageBlockGenerator.create(
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
     * @throws Exception
     */
    @Override
    public
    void initialize () throws ReflectiveOperationException, Exception {
        super.initialize();
    }

    @Override
    public
    IImage <A> doEncode ( IImage <A> image ) {
        return image;
    }

    @Override
    public
    List <RegionOfInterest <A>> segmentImage ( IImage <A> image, List <Rectangle> bounds )
            throws ValueError {

        return null;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> randomTransform ( IImage <A> image, ImageTransform <A, G> transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> applyTransform ( IImage <A> image, ImageTransform <A, G> transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> applyAffineTransform ( IImage <A> image, AffineTransform <A, G> transform ) {
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
//    List <ImageTransform <M, A, G>> compress ( IImage<A> image, int sourceSize, int destinationSize, int step ) {
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
    List <IImageBlock <A>> generateAllTransformedBlocks ( IImage <A> image,
                                                          int sourceSize,
                                                          int destinationSize,
                                                          int step ) {
        return List.of( new ImageBlock <>(image, 0, 0, 8, List.of(new Point[0]), geometry));
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
    public
    ImageBlockGenerator <N, A, G> createBlockGenerator (
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize
    ) {
        return new SquareImageBlockGenerator <>(
               partitionProcessor,
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
    IImage <A> flipAxis ( IImage <A> image, int axis ) {
        return image;
    }

    /**
     * @return
     */
//    @Override
    public
    List <IImageBlock <A>> getRangeBlocks () {
        return (List <IImageBlock <A>>) image;
    }

    /**
     * @return
     */
//    @Override
    public
    List <IImageBlock <A>> getDomainBlocks () {
        return (List <IImageBlock <A>>) image;
    }

    /**
     * @return
     */
//    @Override
    public
    List <IImageBlock <A>> getCodebookBlocks () {
        return List.of(image.getSubImage());
    }

    /**
     * @return
     */
    @Override
    public
    IPartitionProcessor <N, A, G> getPartitionProcessor () {
        return partitionProcessor;
    }

    /**
     * @return
     */
    @Override
    public
    FicFileModel <N, A, G> getModel () {
        return fractalModel;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    FicFileModel <N, A, G> loadModel ( String filename ) {
        return fractalModel;
    }

    @Override
    public
    void add ( TreeNode <N, A, G> node ) {

    }

    @Override
    public
    void addLeafNode ( TreeNode.LeafNode <N, A, G> node ) {

    }

    @Override
    public
    void addLeafNode ( TreeNodeBase <N, A, G> node ) {

    }

    @Override
    public
    Class <?> getTilerClass () {
        return null;
    }
}
