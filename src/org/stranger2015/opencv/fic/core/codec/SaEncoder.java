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
class SaEncoder extends Encoder {

//    private List <IImageBlock > rangePool;

    public
    SaEncoder ( EPartitionScheme scheme,
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
    IPartitionProcessor <N> createPartitionProcessor0 ( ITiler <N> tiler ) {
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
    IImage encode ( IImageimage ) throws ValueError {
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
    List <ImageTransform> compress ( IImageBlock image,
                                               int sourceSize,
                                               int destinationSize,
                                               int step ) {
        return null;
    }

    public
    ImageBlockGenerator <N> createBlockGenerator (
            IPartitionProcessor <N> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N> encoder,
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
     * @return
     */
//    @Override
//    public
//    List <IImageBlock > getRangeBlocks () {
//        imageBlockGenerator.generateRangeBlocks(roi, rangeSize, domainSize);
//    }

    @Override
    public
    void initialize () throws ReflectiveOperationException, Exception {
        
    }

    @Override
    public
    void doEncode ( IImage image ) {
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
    List <IImageBlock > segmentImage ( IImage image, List <Rectangle> bounds ) throws ValueError {
        return null;
    }

    /**
     * @return
     */
//    @Override
    public
    ITiler <N> createPartition0 () {
        return new SaTiler<>();
    }

    /**
     * @return
     */
    @Override
    public
    IPartitionProcessor <N> getPartitionProcessor () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    FCImageModel <N> getMOdel () {
        return null;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    FCImageModel <N> loadModel ( String filename ) {
        return null;
    }

    /**
     * @param node
     */
    @Override
    public
    void add ( TreeNode <N> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode.LeafNode <N> node ) {

    }

    @Override
    public
    void addLeafNode ( TreeNodeBase <N> node ) {

    }

    /**
     * @param node
     */
//    @Override
    public
    void addLeafNode ( TreeNode <N> node ) {

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
    IPartitionProcessor <N> doCreatePartitionProcessor ( ITiler <N> tiler ) {
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
//    IPipeline <IImage , IImage> getLinkedObject () {
//        return null;
//    }
//
//    /**
//     * @param link
//     */
//    @Override
//    public
//    void setNext ( ISingleLinked <IPipeline <IImage , IImage>> link ) {
//
//    }

//    @Override
//    public
//    ISingleLinked <IPipeline <IImage , IImage>> getNext () {
//        return null;
//    }
//
//    @Override
//    public
//    IImage getInput () {
//        return null;
//    }
//
//    @Override
//    public
//    IImage getOutput () {
//        return null;
//    }
}
