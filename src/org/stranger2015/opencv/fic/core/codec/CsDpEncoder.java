package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
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
    CsDpEncoder (
            String fileName,
            EPartitionScheme scheme,
            ICodec codec,
            List <Task> tasks,
            EtvColorSpace colorSpace,
            ITreeNodeBuilder <?> nodeBuilder,
            IPartitionProcessor partitionProcessor,
            ISearchProcessor searchProcessor,
            ScaleTransform scaleTransform,
            ImageBlockGenerator <?> imageBlockGenerator,
            IDistanceator comparator,
            Set <ImageTransform> imageTransforms,
            Set <IImageFilter> imageFilters,
            FCImageModel fractalModel,
            IEncoder ... encoders
    ) {
        super(
                fileName,
                scheme,
                codec,
                tasks,
                colorSpace,
                nodeBuilder,
                partitionProcessor,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                imageTransforms,
                imageFilters,
                fractalModel,
                encoders
        );
    }

    /**
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

        return imageBlockGenerator.create(
                partitionProcessor,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize);
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
     * @param tiler
     * @return
     */
        @Override
    public
    IPartitionProcessor createPartitionProcessor0 ( ITiler tiler ) {
        return null;
    }

    /**
     * @throws Exception
     */
    @Override
    public
    void initialize () throws Exception {
        super.initialize();
    }

    /**
     * @param image
     * @param bounds
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock> segmentImage ( IImageBlock image, List <Rectangle> bounds )
            throws ValueError {

        return List.of(image.getSubImage());
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImageBlock randomTransform ( IImageBlock image, ImageTransform transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImageBlock applyTransform ( IImageBlock image, ImageTransform transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImageBlock applyAffineTransform ( IImageBlock image, AffineTransform transform ) {
        return null;
    }

    /**
     * @param tilerClass
     */
    @Override
    public
    void addAllowableSubtiler ( Class <ITiler> tilerClass ) {

    }

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
    FCImageModel getModel () {
        return fractalModel;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    FCImageModel loadModel ( String filename ) throws Exception {
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
    List <IImageBlock> segmentImage ( IImage image, List <Rectangle> bounds ) throws ValueError {
        return null;
    }

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
     * @param tiler
     * @param imageBlockGenerator
     * @param nodeBuilder
     * @return
     */
    @Override
    public
    IPartitionProcessor doCreatePartitionProcessor ( ITiler tiler,
                                                     ImageBlockGenerator <?> imageBlockGenerator,
                                                     ITreeNodeBuilder <?> nodeBuilder ) {
        return null;
    }

        @Override
    public
    void addLeafNode ( TreeNodeBase <?> node ) {

    }

    @Override
    public
    Class <?> getTilerClass () {
        return tilerClass;
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
