package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;

import java.util.List;
import java.util.Set;

/**
 * @param <?>
 * @param
 */
public
class HvEncoder extends Encoder {

    protected
    HvEncoder (
            EPartitionScheme scheme,
            ITreeNodeBuilder <?> nodeBuilder,
            IPartitionProcessor partitionProcessor,
            ISearchProcessor searchProcessor,
            ScaleTransform  scaleTransform,
            ImageBlockGenerator <?> imageBlockGenerator,
            IDistanceator  comparator,
            Set <ImageTransform> transforms,
            Set <IImageFilter > filters,
            FCImageModel fractalModel
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
    ImageBlockGenerator <?> createBlockGenerator (
            IPartitionProcessor partitionProcessor,
            EPartitionScheme scheme,
            IEncoder encoder,
            IImage image,
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
    IPartitionProcessor doCreatePartitionProcessor ( ITiler tiler ) {
        return null;
    }

//    @Override
    public
    IPartitionProcessor createPartitionProcessor0 ( ITiler tiler ) {
        return null;
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

    @Override
    public
    void initialize () throws ReflectiveOperationException, Exception {

    }

    @Override
    public
    void doEncode ( IImage image ) {
        return image;
    }

    /**
     * @param image
     * @param bounds
     * @return
     * @throws ValueError
     */
//    @Override
    public
    List <IImageBlock > segmentImage ( IImage image, List <Rectangle> bounds ) throws ValueError {
        return List.of(new ImageBlock((IImage) image.getMat()));
    }
//
//    @Override
//    public
//    IPartitionProcessor <?> getPartitionProcessor () {
//        return new HvPartitionProcessor <>(image, image.getWidth(), image.getHeight());
//    }

    /**
     * @param node
     */
    @Override
    public
    void add ( TreeNode <?> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <?> node ) {

    }

    /**
     * @param node
     */
//    @Override
    public
    void addLeafNode ( TreeNodeBase <?> node ) {

    }

    @Override
    public
    Class <? extends ITiler> getTilerClass () {
        return tilerClass;
    }

    /**
     * @param tilerClass
     */
    @Override
    public
    void addAllowableSubtiler ( Class <ITiler> tilerClass ) {

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
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage randomTransform ( IImage image, ImageTransform transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage applyTransform ( IImage image, ImageTransform transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage applyAffineTransform ( IImage image, AffineTransform  transform ) {
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
    List <ImageTransform> compress ( IImage image, int sourceSize, int destinationSize, int step ) {
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
    List <IImageBlock > generateAllTransformedBlocks ( IImage image,
                                                          int sourceSize,
                                                          int destinationSize,
                                                          int step ) {
        return null;//todo
    }
//    @Override
    public
    IImage getInput () {
        return null;
    }

//    @Override
    public
    IImage getOutput () {
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