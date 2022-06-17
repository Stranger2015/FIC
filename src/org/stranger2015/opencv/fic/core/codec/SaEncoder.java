package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;
import java.util.Set;

/**
 *
 */
public
class SaEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>

        extends Encoder <N, A, G> {

    public
    SaEncoder ( EPartitionScheme scheme,
                ITreeNodeBuilder <N, A, G> nodeBuilder,
                ISearchProcessor <N, A, G> searchProcessor,
                ScaleTransform <A, G> scaleTransform,
                ImageBlockGenerator <N, A, G> imageBlockGenerator,
                IDistanceator <A> comparator,
                Set <ImageTransform < A, G>> transforms,
                Set <IImageFilter <M, A>> filters,
                FractalModel <N, A, G> fractalModel
    ) {
        super(
                scheme,
                nodeBuilder,
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
     * @param image
     * @return
     */
//    @Override
    public
    GrayScaleImage<A> encode ( GrayScaleImage<A>image ) throws ValueError {
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
    List <ImageTransform <M, A, G>> compress ( M image,
                                               int sourceSize,
                                               int destinationSize,
                                               int step ) {
        return null;
    }

    public
    ImageBlockGenerator <N, A, G> createBlockGenerator (
            ITiler <N, A, G>  tiler,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            GrayScaleImage <A> image,
            IntSize rangeSize,
            IntSize domainSize
    ) {

        return new SaImageBlockGenerator <>(tiler, scheme, encoder, image, rangeSize, domainSize);
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
    List <IImageBlock <A>> generateAllTransformedBlocks ( GrayScaleImage<A> image,
                                                         int sourceSize,
                                                         int destinationSize,
                                                         int step ) {
        return (List <IImageBlock <A>>) List.of(getInputImage());//fixme
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
    List <IImageBlock <A>> getRangeBlocks () {
        return rangePool;
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

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> createTiler0 () {
        return new SaTiler();
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> getTiler () {
        return null;
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
     * @param image
     * @param transform
     * @return N4
     */
//    @Override
    public
    M randomTransform ( GrayScaleImage<A> image, ImageTransform <A, G> transform ) {
        return null;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
//    @Override
    public
    GrayScaleImage<A> applyTransform ( GrayScaleImage<A> image, ImageTransform <A, G> transform ) {
        return null;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
//    @Override
    public
    GrayScaleImage<A> applyAffineTransform ( GrayScaleImage<A> image, AffineTransform <A, G> transform ) {
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
}
