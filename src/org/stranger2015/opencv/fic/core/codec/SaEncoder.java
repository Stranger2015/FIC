package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.SaTiler;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public
class SaEncoder extends Encoder {
    private static final Set <ImageTransform> imageTransforms = new HashSet <>();

//    private List <IImageBlock > rangePool;

    public
    SaEncoder ( String fileName,
                EPartitionScheme scheme,
                ICodec codec,
                List <Task> tasks,
                EtvColorSpace colorSpace,
                ITreeNodeBuilder <?> nodeBuilder,
                IPartitionProcessor partitionProcessor,
                ISearchProcessor searchProcessor,
                ScaleTransform  scaleTransform,
                ImageBlockGenerator <?> imageBlockGenerator,
                IDistanceator  comparator,
                Set <ImageTransform > transforms,
                Set <IImageFilter > filters,
                FCImageModel fractalModel
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
                transforms,
                filters,
                fractalModel,
                encoders);
    }

//    @Override
    public
    IPartitionProcessor createPartitionProcessor0 ( ITiler tiler ) {
        return null;
    }

//    public
//    SaEncoder ( IImage image, IIntSize rangeSize, IIntSize domainSize ) {
//        super(image,rangeSize,domainSize);
//
//    }

    /**
     * @param image
     * @return
     */
//    @Override
    public
    void encode ( IImage image) throws Exception {
        super.encode(image);
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
    List <ImageTransform> compress ( IImageBlock image,
                                               int sourceSize,
                                               int destinationSize,
                                               int step ) {
        return null;
    }

    public
    ImageBlockGenerator <?> createBlockGenerator (
            IPartitionProcessor partitionProcessor,
            EPartitionScheme scheme,
            IEncoder encoder,
            IImage image,
            IntSize rangeSize,
            IntSize domainSize
    ) {

        return new SaImageBlockGenerator <>(
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
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
//    @Override
    public
    List <IImageBlock > generateAllTransformedBlocks(IImageBlock image,
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
    IImage flipAxis ( IImage image, int axis ) {
        return image;
    }

    /**
     * @throws ReflectiveOperationException
     * @throws Exception
     */
    @Override
    public
    void initialize () throws Exception {
        
    }

    @Override
    public
    IImage doEncode ( IImage image ) {

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
        return null;
    }

    /**
     * @return
     */
//    @Override
    public
    ITiler createPartition0 () {
        return new SaTiler(
                inputImage,
                rangeSize,
                domainSize,
                this,
                nodeBuilder);
    }

    /**
     * @return
     */
    @Override
    public
    IPartitionProcessor getPartitionProcessor () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    FCImageModel getModel () {
        return null;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    FCImageModel saveModel ( String filename ) {
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

    public
    void addLeafNode ( TreeNodeBase <?> node ) {

    }

    /**
     * @param node
     */
//    @Override
    public
    void addLeafNode ( TreeNode <?> node ) {

    }

    /**
     * @param image
     * @param transform
     * @return N4
     */
//    @Override
    public
    IImage randomTransform ( IImage image, ImageTransform transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
//    @Override
    public
    IImage applyTransform ( IImage image, ImageTransform transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
//    @Override
    public
    IImage applyAffineTransform ( IImage image, AffineTransform  transform ) {
        return null;
    }

    @Override
    public
    IPartitionProcessor doCreatePartitionProcessor ( ITiler tiler ) {
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
}
