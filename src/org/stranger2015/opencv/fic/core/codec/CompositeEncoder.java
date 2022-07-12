package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class CompositeEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Encoder <N, A, G> {

    /**
     * @return
     */
    public
    IPipeline <IImage <A>, IImage <A>> getPipeline () {
        return pipeline;
    }

    /**
     *
     */
protected IPipeline<IImage<A>, IImage<A>> pipeline;
    /**
     * @param scheme
     * @param nodeBuilder
     * @param searchProcessor
     * @param scaleTransform
     * @param imageBlockGenerator
     * @param comparator
     * @param imageTransforms
     * @param imageFilters
     * @param fractalModel
     */
    protected
    CompositeEncoder ( EPartitionScheme scheme,
                       ITreeNodeBuilder <N, A, G> nodeBuilder,
                       ISearchProcessor <N, A, G> searchProcessor,
                       ScaleTransform <A, G> scaleTransform,
                       ImageBlockGenerator <N, A, G> imageBlockGenerator,
                       IDistanceator <A> comparator,
                       Set <ImageTransform <A, G>> imageTransforms,
                       Set <IImageFilter <A>> imageFilters,
                       FractalModel <N, A, G> fractalModel ) {

        super(  scheme,
                nodeBuilder,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                imageTransforms,
                imageFilters,
                fractalModel
        );
    }

    @Override
    public
    void initialize () {

    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    IImage <A> encode ( IImage <A> image ) throws ValueError {
        for (IPipeline<IImage<A>, IImage<A>> pipeline = new IPipeline <>() {
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

            @Override
            public
            IPipeline <IImage <A>, IImage <A>> getLinkedObject () {
                return null;
            }

            @Override
            public
            void setNext ( ISingleLinked <IPipeline <IImage <A>, IImage <A>>> link ) {

            }
        } ;; ){



        }
        ///return image;
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
     */
    @Override
    public
    List <RegionOfInterest <A>> segmentImage ( IImage <A> image, List <Rectangle> bounds ) throws ValueError{
        return new ArrayList <>();
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
        return image;
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
    @Override
    public
    List <IImageBlock <A>> generateAllTransformedBlocks ( @NotNull IImage <A> image,
                                                          int sourceSize,
                                                          int destinationSize,
                                                          int step ) {
        return List.of(new ImageBlock<>(image.getMat()));//fixme
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
    void addLeafNode ( LeafNode <N, A, G> node ) {

    }

    /**
     * @param node
     */
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
