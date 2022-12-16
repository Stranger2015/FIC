package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.geom.Point;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;

import java.util.List;
import java.util.Set;

/**
 * @param <N>
 * @param
 */
public
class CsDpEncoder extends Encoder {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    CsDpEncoder ( EPartitionScheme scheme,
                  ITreeNodeBuilder <?> nodeBuilder,
                  IPartitionProcessor partitionProcessor,
                  ISearchProcessor searchProcessor,
                  ScaleTransform  scaleTransform,
                  ImageBlockGenerator <?> imageBlockGenerator,
                  IDistanceator  comparator,
                  Set <ImageTransform> imageTransforms,
                  Set <IImageFilter > imageFilters,
                  FCImageModel fractalModel ) {
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
    ImageBlockGenerator <?> createBlockGenerator (
            IPartitionProcessor partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N> encoder,
            IImage image,
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
    IPartitionProcessor doCreatePartitionProcessor ( ITiler tiler ) {
        return null;
    }

//    @Override
    public
    IPartitionProcessor createPartitionProcessor0 ( ITiler tiler ) {
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
    void doEncode ( IImage image ) {
        return image;
    }

    @Override
    public
    List <IImageBlock > segmentImage ( IImage image, List <Rectangle> bounds )
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
    IImage randomTransform ( IImage image, ImageTransform transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage applyTransform ( IImage image, ImageTransform transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage applyAffineTransform ( IImage image, AffineTransform  transform ) {
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
//    List <ImageTransform <M, A, G>> compress ( IImage image, int sourceSize, int destinationSize, int step ) {
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
    List <IImageBlock > generateAllTransformedBlocks ( IImage image,
                                                          int sourceSize,
                                                          int destinationSize,
                                                          int step ) {
        return List.of( new ImageBlock (image, 0, 0, 8, List.of(new Point[0]), geometry));
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
    ImageBlockGenerator <?> createBlockGenerator (
            IPartitionProcessor partitionProcessor,
            EPartitionScheme scheme,
            IEncoder  encoder,
            IImage image,
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
    IImage flipAxis ( IImage image, int axis ) {
        return image;
    }

    /**
     * @return
     */
//    @Override
    @Override
    public
    List <IImageBlock > getRangeBlocks () {
        return (List <IImageBlock >) image;
    }

    /**
     * @return
     */
//    @Override
    @Override
    public
    List <IImageBlock > getDomainBlocks () {
        return (List <IImageBlock >) image;
    }

    /**
     * @param tilerClass
     */
    @Override
    public
    void addAllowableSubtiler ( Class <ITiler> tilerClass ) {

    }

//    /**
//     * @return
//     */
//    @Override
//    public
//    List <IImageBlock > getCodebookBlocks () {
//        return List.of(image.getSubImage());
//    }

    /**
     * @return
     */
    @Override
    public
    IPartitionProcessor getPartitionProcessor () {
        return partitionProcessor;
    }

    /**
     * @return
     */
    @Override
    public
    FCImageModel getMOdel () {
        return fractalModel;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    FCImageModel loadModel ( String filename ) {
        return fractalModel;
    }

    @Override
    public
    void add ( TreeNode <?> node ) {

    }

    @Override
    public
    void addLeafNode ( TreeNode.LeafNode <?> node ) {

    }

//    @Override
    public
    void addLeafNode ( TreeNodeBase <?> node ) {

    }

    @Override
    public
    Class <? extends ITiler> getTilerClass () {
        return null;
    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor doCreatePartitionProcessor ( ITiler tiler ) {
        return null;
    }

    /**
     * @param outputImage
     * @return
     */
    @Override
    public
    IImage postprocess ( IImage outputImage ) {
        return null;
    }
}
